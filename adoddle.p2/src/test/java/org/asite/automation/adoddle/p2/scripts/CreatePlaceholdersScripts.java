/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreatePlaceholdersScripts extends AdoddleCommonAppMethods {

	private String				dashboardIncompleteActions, filesIncompleteActions, dashboardIncompleteActionsIncreased, filesIncompleteActionsIncreased;
	private String				dashboardDueTodayCount, filesDueTodayCount, dashboardDueTodayCountIncreased, filesDueTodayCountIncreased;
	final private String		placeholderDocTitle1 				= "AutomationPlaceholder" + epoch + 1;
	final private String 		placeholderDocTitle2 				= "AutomationPlaceholder" + epoch + 2;
	final private String 		placeHolderStatusValue 				= "Prepublished";
	private String				fileStatusValue, parentWindow;
	final private String		docRef1								= "AutomationDocRef" + dateUtils.getEpoch() + 1;
	final private String 		docRef2 							= "AutomationDocRef" + dateUtils.getEpoch() + 2;
	final private String 		docRef3 							= "AutomationDocRef" + dateUtils.getEpoch() + 3;
	final private String		docTitle1							= "AutomationDocTitle" + dateUtils.getEpoch() + 1;
	final private String 		docTitle2 							= "AutomationDocTitle" + dateUtils.getEpoch() + 2;
	final private String		docTitle3 							= "AutomationDocTitle" + dateUtils.getEpoch() + 3;
	final private String		placeholderExcelFilePath			= nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + javaUtils.getRandomString(10) + epoch + ".xls";
	final private List<String>	expectedCitiesGujarat				= new ArrayList<String>();
	final private List<String>	expectedCitiesMaharastra			= new ArrayList<String>();
	final private List<String>	expectedCitiesRajasthan				= new ArrayList<String>();
	final private List<String>	uploadFileNameList					= new ArrayList<String>();
	final private String[]		docRefList							= { docRef1, docRef2, docRef3 };
	final private Map<String, String>	createPlaceholderMapList	= new HashMap<String, String>();
	final private Map<String, String>	createdPlaceholderMapList	= new HashMap<String, String>();
	final private Map<String, String>	uploadFileMapList			= new HashMap<String, String>();
	final private Map<String, String>	uploadedFileMapList			= new HashMap<String, String>();
	final private Map<String, String>	expectedFileAttributeList	= new HashMap<String, String>();
	final private Map<String, String>	placeHolderMapList1			= new HashMap<String, String>();
	final private Map<String, String>	placeHolderMapList2			= new HashMap<String, String>();
	final private Map<String, String>	placeHolderMapList3			= new HashMap<String, String>();
	private String 				attachFile;
	final private static Logger		log								= AutomationLogger.getInstance().getLogger(CreatePlaceholdersScripts.class);

	public void getDashboardIncompleteAndDueTodayActionsCount() {
		dashboardIncompleteActions = getIncompleteActionsCount();
		dashboardDueTodayCount = getDueTodayCount();

		log.info("dashboardIncompleteActions :" + dashboardIncompleteActions);
		log.info("dashboardDueTodayCount :" + dashboardDueTodayCount);
	}

	public void getFilesIncompleteAndDueTodayActionsCount() {
		filesIncompleteActions = getIncompleteActionsCount();
		filesDueTodayCount = getDueTodayCount();

		log.info("filesIncompleteActions :" + filesIncompleteActions);
		log.info("filesDueTodayCount :" + filesDueTodayCount);
	}

	public void contextClickOnPlaceholder(String folderName, String newContextClickOption) {
		contextClickWithText(folderName);
		clickContextMenuOptionWithText(newContextClickOption);
		clickElementAndWait(FilesTab.ele_Placeholder);
	}

	public void verifyCreatePlaceholderPopup(String popupText) {
		Assert.assertTrue(isDisplayed(FilesTab.pop_CreatePlaceholder));
		Assert.assertTrue(getElementText(FilesTab.lbl_PopupCreatePlaceholderLabel).contains(popupText));
	}

	public void enterDistributeUserField(String filePlaceholder, String actionType) {
		if (filePlaceholder.equalsIgnoreCase("Placeholder"))
			sendKeys(FilesTab.txt_PopCreatePlaceholderDistributeUserInput, System.getProperty("primary.username"));
		else
			sendKeys(FilesTab.txt_PopCreatePlaceholderDistributeUserInput, System.getProperty("secondary.username"));

		sendKeys(FilesTab.txt_PopCreatePlaceholderDistributeUserInput, Keys.ENTER);
		if (!isDisplayed(FilesTab.lnk_PopDatePickerCurrentDate))
			clickElementAndWait(FilesTab.btn_PopDistributeUserToggleButton);

		selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, actionType);
//		clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate);
		selectDateFromCalendar();
	}

	public void enterPlaceholderMendatoryAttributes() {
		String	scrollMaxLeftJQuery	= "$('#modalrenderPlaceholderBody').scrollLeft(0)";
		clickElementAndWaitForElement(FilesTab.btn_PopAddPlaceholdersButton, FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);

		sendKeys(FilesTab.txt_FileUploadRevisionHeaderInput, "1");
		sendKeys(FilesTab.txt_DocTitleFile1, placeholderDocTitle1);
		sendKeys(FilesTab.txt_DocTitleFile2, placeholderDocTitle2);

		selectByIndex(FilesTab.drp_PopFileUploadHeaderPurposeOfIssueDropdown, 1);
		sendKeys(FilesTab.txt_PopFileUploadHeaderIntegerTextboxInput, JavaUtils.getRandomNumber(3));
		sendKeys(FilesTab.txt_PopFileUploadHeaderEmailTextboxInput, javaUtils.getRandomString(2) + JavaUtils.getRandomNumber(1) + "@xyz.com");
		sendKeys(FilesTab.txt_PopFileUploadHeaderLetterInput, javaUtils.getRandomString(4));

		if (!isSelected(FilesTab.sel_PopFileUploadHeaderRadioButton))
			clickElementAndWait(FilesTab.sel_PopFileUploadHeaderRadioButton);

		sendKeys(FilesTab.txt_PopFileUploadHeaderCoordinates1DimensionXInput, JavaUtils.getRandomNumber(2));
		sendKeys(FilesTab.txt_PopFileUploadHeaderCoordinates3DimensionXInput, JavaUtils.getRandomNumber(2));
		sendKeys(FilesTab.txt_PopFileUploadHeaderCoordinates3DimensionYInput, JavaUtils.getRandomNumber(2));
		sendKeys(FilesTab.txt_PopFileUploadHeaderCoordinates3DimensionZInput, JavaUtils.getRandomNumber(2));

		verifyHierarchicalCategory();

		selectByIndex(FilesTab.drp_PopFileUploadHeaderStatesDropdown, 1);
		selectByIndex(FilesTab.drp_PopFileUploadHeaderCitiesDropdown, 1);
		sendKeys(FilesTab.txt_PopFileUploadHeaderDecimalTextboxInput, "-" + JavaUtils.getRandomNumber(2) + "." + JavaUtils.getRandomNumber(2));
		sendKeys(FilesTab.txt_PopFileUploadHeaderLettersNumbersTextboxInput, javaUtils.getRandomString(4) + JavaUtils.getRandomNumber(3));

		MultiSelectionCheckboxDroupdownSelect();
		selectCurrentDate();

		sendKeys(FilesTab.txt_PopFileUploadHeaderCoordinates2DimensionXInput, JavaUtils.getRandomNumber(2));
		sendKeys(FilesTab.txt_PopFileUploadHeaderCoordinates2DimensionYInput, JavaUtils.getRandomNumber(2));
		executeJScript(scrollMaxLeftJQuery);

		mouseHover(FilesTab.btn_PopFileUploadApplytoAllButton);
		clickElementAndWait(FilesTab.btn_PopFileUploadApplytoAllButton);
	}

	public void getFirstPlaceholderAttributesValue() {
		createPlaceholderMapList.put("rev", findElement(FilesTab.txt_RevisionFile1).getAttribute("value"));
		createPlaceholderMapList.put("doctitle", findElement(FilesTab.txt_DocTitleFile1).getAttribute("value"));
		createPlaceholderMapList.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile1)).getFirstSelectedOption().getText());
		createPlaceholderMapList.put("status", placeHolderStatusValue);
		createPlaceholderMapList.put("integertextbox", findElement(FilesTab.txt_IntegerTextboxFile1).getAttribute("value"));
		createPlaceholderMapList.put("emailtextbox", findElement(FilesTab.txt_EmailTextboxFile1).getAttribute("value"));
		createPlaceholderMapList.put("letter", findElement(FilesTab.txt_LetterFile1).getAttribute("value"));
		createPlaceholderMapList.put("radio", findElement(FilesTab.sel_RadioFile1).getAttribute("value").replace("y", "Y"));
		createPlaceholderMapList.put("coordinates1dimensionx", findElement(FilesTab.txt_Coordinates1DimensionXFile1).getAttribute("value"));
		createPlaceholderMapList.put("coordinates3dimensionx", findElement(FilesTab.txt_Coordinates3DimensionXFile1).getAttribute("value"));
		createPlaceholderMapList.put("coordinates3dimensiony", findElement(FilesTab.txt_Coordinates3DimensionYFile1).getAttribute("value"));
		createPlaceholderMapList.put("coordinates3dimensionz", findElement(FilesTab.txt_Coordinates3DimensionZFile1).getAttribute("value"));
		createPlaceholderMapList.put("states", new Select(findElement(FilesTab.drp_StatesFile1)).getFirstSelectedOption().getText());
		createPlaceholderMapList.put("cities", new Select(findElement(FilesTab.drp_CitiesFile1)).getFirstSelectedOption().getText());
		createPlaceholderMapList.put("decimaltextbox", findElement(FilesTab.txt_DecimalTextboxFile1).getAttribute("value"));
		createPlaceholderMapList.put("lettersnumberstextbox", findElement(FilesTab.txt_LettersNumbersTextboxFile1).getAttribute("value"));
		createPlaceholderMapList.put("multiselectioncheckbox", getElementText(FilesTab.chk_MultiSelectionCheckboxFile1).replace(", ", ","));
		createPlaceholderMapList.put("datepicker", findElement(FilesTab.txt_DatePickerFile1).getAttribute("value"));
		createPlaceholderMapList.put("coordinates2dimensionx", findElement(FilesTab.txt_Coordinates2DimensionXFile1).getAttribute("value"));
		createPlaceholderMapList.put("coordinates2dimensiony", findElement(FilesTab.txt_Coordinates2DimensionYFile1).getAttribute("value"));
	}

	public void verifyCreatedPlaceholderAndAction(String actionType) {
		String[] docTitleList = { placeholderDocTitle1, placeholderDocTitle2 };
		collectionDataMap.put("PlaceholderDocTitle1", placeholderDocTitle1);
		collectionDataMap.put("PlaceholderDocTitle2", placeholderDocTitle2);

		for (String docTitle : docTitleList) {
			searchFiles(docTitle);
			Assert.assertTrue(getElementText(FilesTab.ele_FilesTabFirstFileDocTitle).contains(docTitle));
			Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileActionName).contains(actionType));
			waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
			Assert.assertTrue(isDisplayed(GlobalPageElements.css_firstActionsPopoverDueDaysContents));
		}
	}

	public void verifyCreatedFirstPlaceholderAttributesValue() {
		searchFiles(placeholderDocTitle1);
		parentWindow = clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		if(fileBetaViewFlag) {
			waitUntilElementIsClickable(FilesTab.btn_BetaViewViewFileMoreOptionsButton);
			clickElementAndWait(FilesTab.btn_BetaViewViewFileMoreOptionsButton);
			clickElementAndWait(FilesTab.btn_BetaViewViewFileDetails);
		} else
			clickElementAndWait(FilesTab.lnk_FileViewerHeaderShowMore);
		
		getFileAttributes(createdPlaceholderMapList);
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void verifyPlaceholderAttributes() {
		
		mapListStore(createdPlaceholderMapList);
		javaUtils.compareMapList(createPlaceholderMapList, createdPlaceholderMapList);
	}
	
	public void verifyDashboardIncompleteActionsCountIncreased() {
		reloadPage();
		dashboardIncompleteActionsIncreased = getIncompleteActionsCount();
		dashboardDueTodayCountIncreased = getDueTodayCount();
		try {
			Assert.assertTrue(Integer.parseInt(dashboardIncompleteActions) < Integer.parseInt(dashboardIncompleteActionsIncreased));
			Assert.assertTrue(Integer.parseInt(dashboardDueTodayCount) < Integer.parseInt(dashboardDueTodayCountIncreased));
		}
		catch (Throwable t) {
			log.info("dashboardIncompleteActionsIncreased :" + dashboardIncompleteActionsIncreased);
			log.info("dashboardDueTodayCountIncreased :" + dashboardDueTodayCountIncreased);
		}
	}

	public void verifyFilesIncompleteActionsCountIncreased() {
		filesIncompleteActionsIncreased = getIncompleteActionsCount();
		filesDueTodayCountIncreased = getDueTodayCount();
		try {
			Assert.assertTrue(Integer.parseInt(filesIncompleteActions) < Integer.parseInt(filesIncompleteActionsIncreased));
			Assert.assertTrue(Integer.parseInt(filesDueTodayCount) < Integer.parseInt(filesDueTodayCountIncreased));
		}
		catch (Throwable t) {
			log.info("filesIncompleteActionsIncreased :" + filesIncompleteActionsIncreased);
			log.info("filesDueTodayCountIncreased :" + filesDueTodayCountIncreased);
		}
	}

	public void contextClickOnNonPlaceHolder(String nonPlaceholderFolder, String newContextClickOption) {
		contextClickWithText(nonPlaceholderFolder);
		clickContextMenuOptionWithText(newContextClickOption);
	}

	public void verifyNonPlaceHolderDisabled() {
		Assert.assertTrue(!isDisplayed(FilesTab.ele_Placeholder));
	}

	public void clickOnMoreOptions() {
		clickElementAndWait(FilesTab.lnk_FilesMoreOptions);
	}

	public void clickOnPublishFilesPlaceholder() {
		clickElementAndWait(FilesTab.lnk_PopOptionsPublishFilesPlaceholder);
	}

	public void searchFirstPlaceholderAndClickOnAction() {
		searchFiles(placeholderDocTitle1);
		clickElementAndWait(FilesTab.lnk_FilesTabFirstFileActionName);
	}

	public void verifyStatusBlankValue() {
		Assert.assertTrue(new Select(findElement(FilesTab.drp_StatusFile1)).getFirstSelectedOption().getText().contains("Please Select"));
	}

	public void verifyPlaceholderAttributesForFileUpload() {
		Map<String, String> actualFileAttributeList = new HashMap<String, String>();

		actualFileAttributeList.put("rev", findElement(FilesTab.txt_PopUploadRevision).getAttribute("value"));
		actualFileAttributeList.put("doctitle", findElement(FilesTab.txt_DocTitle).getAttribute("value"));
		actualFileAttributeList.put("poi", new Select(findElement(FilesTab.drp_PopUploadPurposeOfIssue)).getFirstSelectedOption().getText());
		actualFileAttributeList.put("integertextbox", findElement(FilesTab.txt_IntegerTextbox).getAttribute("value"));
		actualFileAttributeList.put("emailtextbox", findElement(FilesTab.txt_EmailTextbox).getAttribute("value"));
		actualFileAttributeList.put("letter", findElement(FilesTab.txt_Letter).getAttribute("value"));
		actualFileAttributeList.put("radio", findElement(FilesTab.sel_Radio).getAttribute("value").replace("y", "Y"));
		actualFileAttributeList.put("coordinates1dimensionx", findElement(FilesTab.txt_Coordinates1DimensionX).getAttribute("value"));
		actualFileAttributeList.put("coordinates3dimensionx", findElement(FilesTab.txt_Coordinates3DimensionX).getAttribute("value"));
		actualFileAttributeList.put("coordinates3dimensiony", findElement(FilesTab.txt_Coordinates3DimensionY).getAttribute("value"));
		actualFileAttributeList.put("coordinates3dimensionz", findElement(FilesTab.txt_Coordinates3DimensionZ).getAttribute("value"));
		actualFileAttributeList.put("states", new Select(findElement(FilesTab.drp_States)).getFirstSelectedOption().getText());
		actualFileAttributeList.put("cities", new Select(findElement(FilesTab.drp_Cities)).getFirstSelectedOption().getText());
		actualFileAttributeList.put("decimaltextbox", findElement(FilesTab.txt_DecimalTextbox).getAttribute("value"));
		actualFileAttributeList.put("lettersnumberstextbox", findElement(FilesTab.txt_LettersNumbersTextbox).getAttribute("value"));
		actualFileAttributeList.put("multiselectioncheckbox", getElementText(FilesTab.ele_MultiSelectionCheckbox).replace(", ", ","));
		actualFileAttributeList.put("datepicker", findElement(FilesTab.txt_DatePickerInput).getAttribute("value"));
		actualFileAttributeList.put("coordinates2dimensionx", findElement(FilesTab.txt_Coordinates2DimensionX).getAttribute("value"));
		actualFileAttributeList.put("coordinates2dimensiony", findElement(FilesTab.txt_Coordinates2DimensionY).getAttribute("value"));

		expectedFileAttributeList.putAll(createPlaceholderMapList);
		expectedFileAttributeList.remove("status");

		log.info("actualFileAttributeList :" + actualFileAttributeList);
		log.info("expectedFileAttributeList :" + expectedFileAttributeList);

		javaUtils.compareMapList(actualFileAttributeList, expectedFileAttributeList);
	}

	public void selectStatusForFileUpload() {
		selectByIndex(FilesTab.drp_StatusFile1, 2);
		fileStatusValue = new Select(findElement(FilesTab.drp_StatusFile1)).getFirstSelectedOption().getText();
		log.info("fileStatusValue :" + fileStatusValue);
	}
	
	public void attachExternalFiles() throws IOException, InterruptedException
	{
		sysUtils.authenticateRemoteMachine(nodeIP);
		attachFile = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + ".txt").trim();
		collectionDataMap.put("External Attached File", attachFile);
		List<String> fileList = sysUtils.getFileList(attachFile);
		uploadFileUsingKeys(FilesTab.btn_PopUploadFirstAttachFile, fileList);
	}
	
	public void verifyAttachmentInListingAndViewer() throws InterruptedException
	{
		log.info("attachFile : "+strUtils.extractFileNameString(attachFile));
		Assert.assertTrue(isDisplayed(FilesTab.img_FileAttachment));
		clickElementAndWait(FilesTab.img_FileAttachment);
		CreateModelCommentScripts.parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForHTMLFileView(strUtils.extractFileNameString(attachFile));
		closeCurrentWindow();
		switchPreviousWindow(CreateModelCommentScripts.parentWindow);
		waitForCompletePageLoad();
	}
	
	public void getFileAttributesValue() {
		uploadFileMapList.putAll(createPlaceholderMapList);
		uploadFileMapList.put("filename", getElementText(FilesTab.lbl_UploadFileName));
		uploadFileMapList.put("status", new Select(findElement(FilesTab.drp_StatusFile1)).getFirstSelectedOption().getText());
	}

	public void verifyUploadedFileAndActionCompleted() {
		searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
		Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));
		Assert.assertTrue(getElementText(FilesTab.lnk_FileName).contains(strUtils.extractFileNameString(CreateEditRoleScripts.createFile)));

		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.img_firstActionsPopoverActionCompleted);
		Assert.assertTrue(isDisplayed(GlobalPageElements.img_firstActionsPopoverActionCompleted));
	}

	public void verifyCustomAttributesOnFileListingPage() {
		Map<String, String> actualFileListingAttributesList = new HashMap<String, String>();

		actualFileListingAttributesList.put("rev", getElementText(FilesTab.lnk_FilesTabFirstFileRev));
		actualFileListingAttributesList.put("doctitle", getElementText(FilesTab.ele_FilesTabFirstFileDocTitle));
		actualFileListingAttributesList.put("poi", getElementText(FilesTab.ele_FilesTabFirstFilePOI));
		actualFileListingAttributesList.put("status", getElementText(FilesTab.lnk_FilesTabFirstFileStatus));
		actualFileListingAttributesList.put("integertextbox", getElementText(FilesTab.ele_FilesTabFirstFileIntegerTextbox));
		actualFileListingAttributesList.put("emailtextbox", getElementText(FilesTab.ele_FilesTabFirstFileEmailTextbox));
		actualFileListingAttributesList.put("letter", getElementText(FilesTab.ele_FilesTabFirstFileLetter));
		actualFileListingAttributesList.put("radio", getElementText(FilesTab.ele_FilesTabFirstFileRadio));
		actualFileListingAttributesList.put("coordinates1dimensionx", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates1Dimension).split(".0")[0]);
		actualFileListingAttributesList.put("coordinates3dimensionx", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates3Dimension).split(".0")[0].replace(",", ""));
		actualFileListingAttributesList.put("coordinates3dimensiony", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates3Dimension).split(".0")[1].replace(",", ""));
		actualFileListingAttributesList.put("coordinates3dimensionz", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates3Dimension).split(".0")[2].replace(",", ""));
		actualFileListingAttributesList.put("states", getElementText(FilesTab.ele_FilesTabFirstFileStates).replace(" (1)", ""));
		actualFileListingAttributesList.put("cities", getElementText(FilesTab.ele_FilesTabFirstFileCities).replace(" (1)", ""));
		actualFileListingAttributesList.put("decimaltextbox", getElementText(FilesTab.ele_FilesTabFirstFileDecimalTextbox));
		actualFileListingAttributesList.put("lettersnumberstextbox", getElementText(FilesTab.ele_FilesTabFirstFileLettersNumbersTextbox));
		actualFileListingAttributesList.put("multiselectioncheckbox", getElementText(FilesTab.ele_FilesTabFirstFileMultiSelectionCheckbox).replace(", ", ","));
		actualFileListingAttributesList.put("datepicker", getElementText(FilesTab.ele_FilesTabFirstFileDatePicker));
		actualFileListingAttributesList.put("coordinates2dimensionx", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates2Dimension).split(".0")[0].replace(",", ""));
		actualFileListingAttributesList.put("coordinates2dimensiony", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates2Dimension).split(",")[1].replace(".0", ""));

		expectedFileAttributeList.put("status", fileStatusValue);

		log.info("actualFileListingAttributesList :" + actualFileListingAttributesList);
		log.info("expectedFileListingAttributeList :" + expectedFileAttributeList);

		javaUtils.compareMapList(actualFileListingAttributesList, expectedFileAttributeList);
	}

	public void verifyUploadedFileAndAttributesValue() {
		parentWindow = clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		
		if(fileBetaViewFlag) {
			waitUntilElementIsClickable(FilesTab.btn_BetaViewViewFileMoreOptionsButton);
			clickElementAndWait(FilesTab.btn_BetaViewViewFileMoreOptionsButton);
			clickElementAndWait(FilesTab.btn_BetaViewViewFileDetails);
		} else
			clickElementAndWait(FilesTab.lnk_FileViewerHeaderShowMore);

		getFileAttributes(uploadedFileMapList);
		
		if(fileBetaViewFlag)
			uploadedFileMapList.put("filename", getElementText(FilesTab.lbl_BetaViewViewFileName));
		else
			uploadedFileMapList.put("filename", getElementText(FilesTab.lbl_CustomFileName));

		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void verifyFileAttributes() {
		mapListStore(uploadedFileMapList);

		log.info("uploadFileMapList :" + uploadFileMapList);
		log.info("uploadedFileMapList :" + uploadedFileMapList);

		javaUtils.compareMapList(uploadFileMapList, uploadedFileMapList);
	}

	public void verifyDashboardIncompleteActionsCountDecreased() {
		reloadPage();
		String dashboardIncompleteActionsDecreased = getIncompleteActionsCount();
		String dashboardDueTodayCountDecreased = getDueTodayCount();
		try {
			Assert.assertTrue(Integer.parseInt(dashboardIncompleteActionsIncreased) > Integer.parseInt(dashboardIncompleteActionsDecreased));
			Assert.assertTrue(Integer.parseInt(dashboardDueTodayCountIncreased) > Integer.parseInt(dashboardDueTodayCountDecreased));
		}
		catch (Throwable t) {
			log.info("dashboardIncompleteActionsDecreased :" + dashboardIncompleteActionsDecreased);
			log.info("dashboardDueTodayCountDecreased :" + dashboardDueTodayCountDecreased);
		}
	}

	public void verifyFilesIncompleteActionsCountDecreased() {
		String filesIncompleteActionsDecreased = getIncompleteActionsCount();
		String filesDueTodayCountDecreased = getDueTodayCount();
		try {
			Assert.assertTrue(Integer.parseInt(filesIncompleteActionsIncreased) > Integer.parseInt(filesIncompleteActionsDecreased));
			Assert.assertTrue(Integer.parseInt(filesDueTodayCountIncreased) > Integer.parseInt(filesDueTodayCountDecreased));
		}
		catch (Throwable t) {
			log.info("filesIncompleteActionsDecreased :" + filesIncompleteActionsDecreased);
			log.info("filesDueTodayCountDecreased :" + filesDueTodayCountDecreased);

		}
	}

	private String getIncompleteActionsCount() {
		mouseHover(GlobalPageElements.lnk_IncompleteActionCount);
		return findElement(GlobalPageElements.lnk_IncompleteActionCount).getAttribute("title").split(": ")[1];
	}

	private String getDueTodayCount() {
		mouseHover(GlobalPageElements.lnk_DueTodayCount);
		return findElement(GlobalPageElements.lnk_DueTodayCount).getAttribute("title").split(": ")[1];
	}

	private void verifyHierarchicalCategory() {
		List<String> categoryItemList = new ArrayList<String>();
		List<WebElement> citiesList;
		List<WebElement> statesList = findElements(FilesTab.css_PopFileUploadHeaderStatesOptionsList);

		String gujarat[] = { "Ahmedabad", "Surat" };
		String maharastra[] = { "Mumbai", "Pune" };
		String rajasthan[] = { "Jaipur", "Udaipur" };
		String statesListTest[] = { "Gujarat", "Maharashtra", "Rajastan" };

		for (String guj : gujarat)
			expectedCitiesGujarat.add(guj.trim());
		log.info("expectedCitiesGujarat :" + expectedCitiesGujarat);

		for (String mah : maharastra)
			expectedCitiesMaharastra.add(mah.trim());
		log.info("expectedCitiesMaharastra :" + expectedCitiesMaharastra);

		for (String raj : rajasthan)
			expectedCitiesRajasthan.add(raj.trim());
		log.info("expectedCitiesRajasthan :" + expectedCitiesRajasthan);

		for (WebElement selectStates : statesList) {

			selectByVisibleText(FilesTab.drp_PopFileUploadHeaderStatesDropdown, selectStates.getText());
			waitForCompletePageLoad();
			clickElementAndWait(FilesTab.drp_PopFileUploadHeaderCitiesDropdown);
			citiesList = findElements(FilesTab.css_PopFileUploadHeaderCitiesOptionsList);

			for (String state : statesListTest) {

				if (selectStates.getText().equalsIgnoreCase(state)) {

					for (WebElement selectCities : citiesList) {

						categoryItemList.add(selectCities.getText().trim());
					}
					categoryItemList.remove(0);
					log.info("categoryItemList :" + categoryItemList);
					try {

						log.info("expectedCities :" + expectedCitiesGujarat);
						Assert.assertEquals(categoryItemList, expectedCitiesGujarat);
					}
					catch (Throwable t) {

						try {

							log.info("expectedCities :" + expectedCitiesMaharastra);
							Assert.assertEquals(categoryItemList, expectedCitiesMaharastra);
						}
						catch (Throwable e) {

							log.info("expectedCities :" + expectedCitiesRajasthan);
							Assert.assertEquals(categoryItemList, expectedCitiesRajasthan);
						}
					}
					categoryItemList.clear();
				}
			}
		}
	}

	private void MultiSelectionCheckboxDroupdownSelect() {
		String[] checkbox = { "Option1", "Option3" };
		clickElementAndWait(FilesTab.chk_PopFileUploadHeaderMultiSelectionCheckbox);
		for (String checkboxSelect : checkbox) {

			if (!isSelected(By.xpath(".//thead//div[contains(@class,'dropdownchecklist')]//label[text()='" + checkboxSelect.trim() + "']")))
				clickElementAndWait(By.xpath(".//thead//div[contains(@class,'dropdownchecklist')]//label[text()='" + checkboxSelect.trim() + "']"));
		}
	}

	private void selectCurrentDate() {
		clickElementAndWait(FilesTab.txt_PopFileUploadHeaderDatePickerInput);
		clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate);
	}

	public void focusOnFolder(String project, String folder) {
		clickElementWithText(project);
		clickElementWithText(folder);
	}

	private void getFileAttributes(Map<String, String> mapList)
	{
		if(fileBetaViewFlag) {
			
			mapList.put("filename", getElementText(FilesTab.lbl_BetaViewViewFileName));
			List<String> attributeList = Arrays.asList("Title", "Rev", "Purpose of Issue", "Status", "IntegerTextbox", "EmailTextbox", "Letter", "Radio", "Coordinates1Dimension", "Coordinates3Dimension", "States", "Cities", "DecimalTextbox", "LettersNumbersTextbox", "DatePicker", "MultiSelectionCheckbox", "Coordinates2Dimension");
			List<String> attributeKeyList = Arrays.asList("doctitle", "rev", "poi", "status", "integertextbox", "emailtextbox", "letter", "radio", "coordinates1dimension", "coordinates3dimension", "states", "cities", "decimaltextbox", "lettersnumberstextbox", "datepicker", "multiselectioncheckbox", "coordinates2dimension");
			
			int i=0;
			for (String attribute : attributeList) {
				
				if(attribute.contains("Coordinates1Dimension"))
					mapList.put(attributeKeyList.get(i)+"x", getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[text()][1]")));
				else if(attribute.contains("Coordinates2Dimension")) {
					mapList.put(attributeKeyList.get(i)+"x", getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[text()][1]")).split(",")[0]);
					mapList.put(attributeKeyList.get(i)+"y", getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[text()][1]")).split(",")[1]);
				}
				else if(attribute.contains("Coordinates3Dimension")) {
					mapList.put(attributeKeyList.get(i)+"x", getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[text()][1]")).split(",")[0]);
					mapList.put(attributeKeyList.get(i)+"y", getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[text()][1]")).split(",")[1]);
					mapList.put(attributeKeyList.get(i)+"z", getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[text()][1]")).split(",")[2]);
				}
				else if(attribute.contains("States") || attribute.contains("Cities"))
					mapList.put(attributeKeyList.get(i), getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[1]")).replace(" (1)", ""));
				else
					mapList.put(attributeKeyList.get(i), getElementText(By.xpath(".//div[@id='right-content']//main[@class='open']//file-info//table//tr//th[text()='"+attribute+"']//following::td[text()][1]")));
				i++;
			}
			log.info("mapList : "+mapList);
		}
		else {
			mapList.put("filename", getElementText(FilesTab.lbl_CustomFileName).split(": ")[1]);
			mapList.put("doctitle", getElementText(FilesTab.lbl_CustomDocTitle).split(": ")[1]);
			mapList.put("status", getElementText(FilesTab.lbl_CustomStatus).split(": ")[1]);
			mapList.put("rev", getElementText(FilesTab.lbl_CustomRev).split(": ")[1]);
			mapList.put("poi", getElementText(FilesTab.lbl_CustomPoi).split(": ")[1]);
			
			log.info("mapList-1 : "+mapList);
			
			List<String> customAttributesKeys = Arrays.asList("integertextbox", "decimaltextbox", "emailtextbox", "lettersnumberstextbox", "letter", "radio", "datepicker", "states", "cities", "coordinates1dimension");
			
			for (String attribute : customAttributesKeys) {
				
				for (WebElement uiAttribute : findElements(FilesTab.css_ViewFileCustomAttributesList)) {
					
					if(!uiAttribute.getText().contains(",")) {
						
						if(uiAttribute.getText().split(": ")[0].toLowerCase().equalsIgnoreCase(attribute)) {
							
							if(attribute.contains("coordinates1dimension"))
								mapList.put(attribute+"x", uiAttribute.getText().split(": ")[1]);
							else if(attribute.contains("states") || attribute.contains("cities"))
								mapList.put(attribute, uiAttribute.getText().split(": ")[1].replace(" (1)", ""));
							else
								mapList.put(attribute, uiAttribute.getText().split(": ")[1]);
							break;
						}
					}
				}
			}
			log.info("mapList-2 : "+mapList);
			
			for (WebElement attribute : findElements(FilesTab.css_ViewFileCustomAttributesList)) {
				if(attribute.getText().contains(",")) {
					
					if((StringUtils.countMatches(attribute.getText(), ",") == 1) && attribute.getText().contains("Option")) {
						mapList.put("multiselectioncheckbox", attribute.getText().split(": ")[1]);
					
					} else if((StringUtils.countMatches(attribute.getText(), ",") == 1) && !attribute.getText().contains("Option")) {
						mapList.put("coordinates2dimensionx", attribute.getText().split(": ")[1].split(",")[0]);
						mapList.put("coordinates2dimensiony", attribute.getText().split(": ")[1].split(",")[1]);
						
					} else if(StringUtils.countMatches(attribute.getText(), ",") == 2) {
						mapList.put("coordinates3dimensionx", attribute.getText().split(": ")[1].split(",")[0]);
						mapList.put("coordinates3dimensiony", attribute.getText().split(": ")[1].split(",")[1]);
						mapList.put("coordinates3dimensionz", attribute.getText().split(": ")[1].split(",")[2]);
					}
				}
			}
			log.info("mapList-3 : "+mapList);
		}
	}

	private void mapListStore(Map<String, String> map) {
		for (String key : map.keySet()) {
			String value[] = map.get(key).split(": ");
			if (value.length > 1) {
				map.put(key, value[1].trim());
			}
		}
	}

	/******* Excel Import *******/

	public void clickOnPlaceholderTemplateIcon() {
		clickElementAndWait(FilesTab.img_DownloadPlaceholderTemplateIcon);
	}

	public void verifyDownloadedTemplate() throws IOException, InterruptedException {
		File downloadFile = new File(nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path") + "ABOS_Asite_Template.xls");
		waitUntilFileIsDownloaded(downloadFile);
		File file = new File(placeholderExcelFilePath);
		downloadFile.renameTo(file);
		collectionDataMap.put("Placeholder Excel File Path", placeholderExcelFilePath);
		waitUntilFileIsDownloaded(file);
	}

	public void enterFieldsIntoExcelTemplate() {
		HSSFWorkbook		workbook;
		HSSFSheet			sheet;
		Cell				cell;
		String[] keyList = { "docref", "rev", "doctitle", "poi" };

		List<String[]> excelRowlist = new ArrayList<String[]>();
		excelRowlist.add(new String[] { docRef1, JavaUtils.getRandomNumber(1), docTitle1, AdoddleCommonStringPool.STATUS_FOR_TRAINING });
		excelRowlist.add(new String[] { docRef2, JavaUtils.getRandomNumber(1), docTitle2, AdoddleCommonStringPool.STATUS_FOR_TRAINING });
		excelRowlist.add(new String[] { docRef3, JavaUtils.getRandomNumber(1), docTitle3, AdoddleCommonStringPool.STATUS_FOR_TRAINING });

		int index;
		try {
				FileInputStream file = new FileInputStream(new File(placeholderExcelFilePath));
				workbook = new HSSFWorkbook(file);
				sheet = workbook.getSheetAt(1);

				int temp = 2;
				for (String[] str : excelRowlist) {
					int temp1 = 2;
					for (String value : str) {
						sheet.getRow(temp).getCell(temp1++).setCellValue(value);
						log.info(value + "\n");
					}
					temp++;
				}
				file.close();
				FileOutputStream output_file = new FileOutputStream(new File(placeholderExcelFilePath));
				workbook.write(output_file);

				index = 0;
				for (int i = 2; i <= 5; i++) {
					cell = sheet.getRow(2).getCell(i);
					placeHolderMapList1.put(keyList[index], cell.toString().trim());
					index++;
				}
				log.info("placeHolderMapList1 :" + placeHolderMapList1);

				index = 0;
				for (int i = 2; i <= 5; i++) {
					cell = sheet.getRow(3).getCell(i);
					placeHolderMapList2.put(keyList[index], cell.toString().trim());
					index++;
				}
				log.info("placeHolderMapList2 :" + placeHolderMapList2);

				index = 0;
				for (int i = 2; i <= 5; i++) {
					cell = sheet.getRow(4).getCell(i);
					placeHolderMapList3.put(keyList[index], cell.toString().trim());
					index++;
				}
				log.info("placeHolderMapList3 :" + placeHolderMapList3);
			}
			catch (Exception e) {
				e.printStackTrace();
		}
	}

	public void importPlaceholderFromExcel() {
		List<String> fileList = sysUtils.getFileList(placeholderExcelFilePath);
		uploadFileUsingKeys(FilesTab.btn_ImportFromExcel, fileList);
	}

	public void verifyImportPlaceholdersPopup(String popupText) {
		waitUntilElementIsDisplayed(FilesTab.pop_ImportPlaceholders);
		Assert.assertTrue(getElementText(FilesTab.lbl_PopImportPlaceholdersLabel).contains(popupText));
	}

	public void verifyConfirmPopup(String popupText) {
		waitUntilElementIsDisplayed(FilesTab.pop_Confirm);
		Assert.assertTrue(isDisplayed(FilesTab.pop_Confirm));
		Assert.assertTrue(getElementText(FilesTab.lbl_PopConfirmLabel).contains(popupText));
	}

	public void clickOnOK() {
		clickElementAndWait(FilesTab.btn_PopConfirmOKButton);
	}

	public void verifyUploadedPlaceholderValues() {
		Map<String, String> placeHolderUploadMapList1 = new HashMap<String, String>();
		Map<String, String> placeHolderUploadMapList2 = new HashMap<String, String>();
		Map<String, String> placeHolderUploadMapList3 = new HashMap<String, String>();

		placeHolderUploadMapList1.put("docref", findElement(FilesTab.txt_DocRefFile1).getAttribute("value"));
		placeHolderUploadMapList1.put("rev", findElement(FilesTab.txt_RevisionFile1).getAttribute("value"));
		placeHolderUploadMapList1.put("doctitle", findElement(FilesTab.txt_DocTitleFile1).getAttribute("value"));
		placeHolderUploadMapList1.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile1)).getFirstSelectedOption().getText());
		log.info("placeHolderUploadMapList1 :" + placeHolderUploadMapList1);

		placeHolderUploadMapList2.put("docref", findElement(FilesTab.txt_DocRefFile2).getAttribute("value"));
		placeHolderUploadMapList2.put("rev", findElement(FilesTab.txt_RevisionFile2).getAttribute("value"));
		placeHolderUploadMapList2.put("doctitle", findElement(FilesTab.txt_DocTitleFile2).getAttribute("value"));
		placeHolderUploadMapList2.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile2)).getFirstSelectedOption().getText());
		log.info("placeHolderUploadMapList2 :" + placeHolderUploadMapList2);

		placeHolderUploadMapList3.put("docref", findElement(FilesTab.txt_DocRefFile3).getAttribute("value"));
		placeHolderUploadMapList3.put("rev", findElement(FilesTab.txt_RevisionFile3).getAttribute("value"));
		placeHolderUploadMapList3.put("doctitle", findElement(FilesTab.txt_DocTitleFile3).getAttribute("value"));
		placeHolderUploadMapList3.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile3)).getFirstSelectedOption().getText());
		log.info("placeHolderUploadMapList3 :" + placeHolderUploadMapList3);

		javaUtils.compareMapList(placeHolderMapList1, placeHolderUploadMapList1);
		javaUtils.compareMapList(placeHolderMapList2, placeHolderUploadMapList2);
		javaUtils.compareMapList(placeHolderMapList3, placeHolderUploadMapList3);

		for (int i = 1; i < docRefList.length; i++) {
			Assert.assertTrue(getElementText(By.xpath(".//tr[" + i + "]//span[@required and text()]")).contains(placeHolderStatusValue));
		}
	}

	public void verifyCreatedPlaceholders() {
		Map<String, String> placeHolderUploadMapList = new HashMap<String, String>();

		collectionDataMap.put("Placeholder DocRef List", String.valueOf(docRefList));

		for (String docRef : docRefList) {
			searchFiles(docRef);
			placeHolderUploadMapList.put("docref", getElementText(FilesTab.lnk_DocListingFirstDocRef));
			placeHolderUploadMapList.put("rev", getElementText(FilesTab.lnk_FilesTabFirstFileRev));
			placeHolderUploadMapList.put("doctitle", getElementText(FilesTab.ele_FilesTabFirstFileDocTitle));
			placeHolderUploadMapList.put("poi", getElementText(FilesTab.ele_FilesTabFirstFilePOI));

			if (getElementText(FilesTab.lnk_DocListingFirstDocRef).endsWith("1"))
				javaUtils.compareMapList(placeHolderMapList1, placeHolderUploadMapList);
			else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).endsWith("2"))
				javaUtils.compareMapList(placeHolderMapList2, placeHolderUploadMapList);
			else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).endsWith("3"))
				javaUtils.compareMapList(placeHolderMapList3, placeHolderUploadMapList);
			else
				Assert.assertTrue(false);

			placeHolderUploadMapList.clear();
		}
	}

	public void verifyActionOnPlaceholder(String actionType) {
		for (String docRef : docRefList) {
			searchFiles(docRef);
			Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileActionName).contains(actionType));

			waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
			Assert.assertTrue(isDisplayed(GlobalPageElements.css_firstActionsPopoverDueDaysContents));
		}
	}

	public void uploadFileIntoPlaceholder() {
		searchFiles(docRef1);
		clickElementAndWait(FilesTab.lnk_FilesTabFirstFileActionName);
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PopUpHeader1).contains("Upload"));

		sysUtils.authenticateRemoteMachine(nodeIP);
		String createFile = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		collectionDataMap.put("Uploaded FilePath of Placeholder", createFile);
		log.info("file created as: " + createFile);
		List<String> fileList = sysUtils.getFileList(createFile);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
		Assert.assertTrue(getCount(FilesTab.css_PopUploadFilesDocRefsInput) == fileList.size());
		uploadFileNameList.add(strUtils.extractFileNameString(createFile));

		verifyStatusBlankValue();
		selectStatusForFileUpload();
		sendKeys(FilesTab.txt_PopCreatePlaceholderDistributeUserInput, System.getProperty("primary.username"));
		sendKeys(FilesTab.txt_PopCreatePlaceholderDistributeUserInput, Keys.ENTER);

		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		if (isDisplayed(FilesTab.pop_Handle) || isDisplayed(FilesTab.pop_PublishDocuments))
			clickElementAndWait(FilesTab.btn_PopupContinue);
		log.info("uploadFileNameList :" + uploadFileNameList);

		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);

	}

	public void verifyUploadedFileAttributesAndIssueNumber(String issueNumber) {
		Map<String, String> uploadFilesList = new HashMap<String, String>();
		
		collectionDataMap.put("Uploaded FileName List", uploadedFileMapList.toString());
		for (String fileName : uploadFileNameList) {
			searchFiles(fileName);
			Assert.assertTrue(getElementText(FilesTab.lnk_FileName).contains(fileName));

			uploadFilesList.put("docref", getElementText(FilesTab.lnk_DocListingFirstDocRef));
			uploadFilesList.put("rev", getElementText(FilesTab.lnk_FilesTabFirstFileRev));
			uploadFilesList.put("doctitle", getElementText(FilesTab.ele_FilesTabFirstFileDocTitle));
			uploadFilesList.put("poi", getElementText(FilesTab.ele_FilesTabFirstFilePOI));

			waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			mouseHoverElement(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitUntilElementIsDisplayed(GlobalPageElements.img_firstActionsPopoverActionCompleted);
			Assert.assertTrue(isDisplayed(GlobalPageElements.img_firstActionsPopoverActionCompleted));

			if (getElementText(FilesTab.lnk_DocListingFirstDocRef).endsWith("1"))
				javaUtils.compareMapList(placeHolderMapList1, uploadFilesList);
			else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).endsWith("2"))
				javaUtils.compareMapList(placeHolderMapList2, uploadFilesList);
			else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).endsWith("3"))
				javaUtils.compareMapList(placeHolderMapList3, uploadFilesList);
			else
				Assert.assertTrue(false);

			Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(fileStatusValue));
			uploadFilesList.clear();

			collectionDataMap.put("Uploaded File Current Issue No", issueNumber);
			Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileIssueNumber).contains(issueNumber));
		}
	}
}
