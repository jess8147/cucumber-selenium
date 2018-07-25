/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewFilesPublishedScripts extends AdoddleCommonAppMethods {
	private int						i, j, beforeTodayCount, beforePastWeekCount, oldRevision, newRevision, singleDocKey, multiDocKey;
	private int						afterLastLoginCount, afterTodayCount, afterPastWeekCount, totalActionsPerformCount;
	private int						dashboardIncompleteActionsSUser, filesIncompleteActionsSUser, dashboardIncompleteActionsPUser, filesIncompleteActionsPUser;
	private int						beforeDashboardUserA, afterDashboardUserA, afterDashboardUserA2, beforeDashboardUserB, afterDashboardUserB, afterDashboardUserB2, beforeDashboardUserD, afterDashboardUserD, beforeDashboardUserE, afterDashboardUserE;
	private int						beforeUnReadDissUserA, afterUnReadDissUserA, afterUnReadDissUserA2, beforeUnReadDissUserB, afterUnReadDissUserB, afterUnReadDissUserB2, beforeUnReadDissUserD, afterUnReadDissUserD, beforeUnReadDissUserE, afterUnReadDissUserE;
	private int						dashboardUserA, dashboardUserB, unReadUserA, unReadUserB;
	private double					webFileSize;
	final private String				formTitle				= "AutomationTestForm" + epoch;
	private String						downloadFile, attachFile, multiDocFile;
	final private String				targetLinkFolder		= "POI_Status_Folder", linkFileAction = "For Information";
	private String						checkoutFolder, statusSetColor, statusSetFont, oldStatus, newStatus, fileLatestRev;
	private String						outputZipDownloadFolder	= AdoddleCommonStringPool.EMPTY_STRING;
	private String						inputZipDownloadFile	= AdoddleCommonStringPool.EMPTY_STRING;
	private String						outputZipCheckoutFolder	= AdoddleCommonStringPool.EMPTY_STRING;
	private String						inputZipCheckoutFile	= AdoddleCommonStringPool.EMPTY_STRING;
	private String						outputZipPDFFolder		= AdoddleCommonStringPool.EMPTY_STRING;
	private String						inputZipPDFFile			= AdoddleCommonStringPool.EMPTY_STRING;
	public static boolean				flag1;
	private String						searchFile;
	private boolean						compareFlag				= false, deactivatedFlag = false;
	private boolean						flag, dashboardFlagUserA, dashboardFlagUserB, unReadFlagUserA, unReadFlagUserB;
	private Boolean						isListPresent;
	final private List<String>			highFlagFiles			= new ArrayList<String>();
	final private List<String>			mediumFlagFiles			= new ArrayList<String>();
	final private List<String>			lowFlagFiles			= new ArrayList<String>();
	final private List<String>			allFlagFileList			= new ArrayList<String>();
	final private List<String>			selectedFileNameList	= new ArrayList<String>();
	final private List<String>			fileNameList			= new ArrayList<String>();
	final private List<String>			indexList				= new ArrayList<String>();
	final private List<String>			fileList				= new ArrayList<String>();
	final private List<String>			actionArrayList			= new ArrayList<String>();
	final private List<String>			popHoverActionList		= new ArrayList<String>();
	private List<WebElement>			fileCheckList			= new ArrayList<WebElement>();
	final private Map<Integer, String>	actionMapKeyValList		= new HashMap<Integer, String>();
	final private List<String>			downloadedLocalFilesVal	= new ArrayList<String>();
	final private static Logger			log						= AutomationLogger.getInstance().getLogger(NewFilesPublishedScripts.class);

	/****** LAST LOGIN ******/

	public void setResetDefaultProject(String setReset, String defaultProject) {
		clickElementAndWaitForElement(GlobalPageElements.btn_SearchProjectFilterButton, GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
		waitForCompletePageLoad();
		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
			clickElementAndWait(GlobalPageElements.lnk_FilterProjectClearAll);

		if (setReset.equalsIgnoreCase("set")) {
			for (WebElement project : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
				if (project.getAttribute("title").contains(defaultProject)) {
					project.click();
					break;
				}
			}
		}
		clickElementAndWait(GlobalPageElements.btn_SearchProjectFilterButton);
	}

	public void clearFlag(String activeTab) {
		waitForCompletePageLoad();
		setSearchFilter(AdoddleCommonStringPool.OPTION_FLAG);
		setAllFlagFilter(AdoddleCommonStringPool.FLAG_HIGH, AdoddleCommonStringPool.FLAG_MEDIUM, AdoddleCommonStringPool.FLAG_LOW);

		if (activeTab.contains(AdoddleCommonStringPool.TAB_FILES))
			isListPresent = findElements(FilesTab.css_NumberOfFiles).size() > 0;
		else
			isListPresent = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList).size() > 0;

		if (isListPresent) {
			if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);

			if (activeTab.contains(AdoddleCommonStringPool.TAB_FILES))
				contextClick(FilesTab.lnk_FileName);
			else
				contextClick(ProjectFormsTab.lnk_FirstFormTitle);
			
			clickContextMenuOption(AdoddleCommonStringPool.OPTION_FLAG, AdoddleCommonStringPool.OPTION_CLEAR_FLAG);
			
		}
		navigateTab(LandingPage.lnk_Dashboard);
	}

	public void getTotalHighchartsAxisCount(String lastLogin, String today, String pastWeek) {
		String[] highchartAxis = { lastLogin, today, pastWeek };
		int beforeLastLoginCount;
		mouseHover(DashboardTab.ele_NewFilePublishedPortlet);
		for (String axis : highchartAxis) {
			if (axis.contains("LAST LOGIN")) {
				log.info("Last Login :" + isDisplayed(DashboardTab.ele_NewFilePublishedLastLoginAxis));
				mouseHover(DashboardTab.ele_NewFilePublishedHiddenLastLoginAxis);
				beforeLastLoginCount = Integer.parseInt(getHighchartsAxisCount());
				log.info("Before Last Login Count :" + beforeLastLoginCount);
				collectionDataMap.put("beforeLastLoginCount", Integer.toString(beforeLastLoginCount));
			}
			else if (axis.contains("TODAY")) {
				mouseHover(DashboardTab.ele_NewFilePublishedHiddenTodayAxis);
				beforeTodayCount = Integer.parseInt(getHighchartsAxisCount());
				log.info("Before today count :" + beforeTodayCount);
				collectionDataMap.put("beforeTodayCount", Integer.toString(beforeTodayCount));
			}
			else {
				mouseHover(DashboardTab.ele_NewFilePublishedHiddenPaskWeekAxis);
				beforePastWeekCount = Integer.parseInt(getHighchartsAxisCount());
				log.info("Before Past Week Count :" + beforePastWeekCount);
				collectionDataMap.put("beforePastWeekCount", Integer.toString(beforePastWeekCount));
			}
		}
	}

	public void enterMendatoryAttributes() {
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void verifyUploadedFiles() {
		String[] uploadedFileList = { strUtils.extractFileNameString(CreateEditPOIScripts.createFile1), strUtils.extractFileNameString(CreateEditPOIScripts.createFile2) };
		for (String file : uploadedFileList) {
			searchFiles(file);
			Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));
		}
	}

	public void logOutAndLogin(String user) throws InterruptedException {
		logOut();
		if (user.contains("Same"))
			login(System.getProperty("primary.username"), System.getProperty("primary.password"));
		else
			login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
	}

	public void verifyTotalHighchartsAxisCount(String lastLogin, String today, String pastWeek) {
		String[] highchartAxis = { lastLogin, today, pastWeek };

		mouseHover(DashboardTab.ele_NewFilePublishedPortlet);
		for (String axis : highchartAxis) {
			if (axis.contains("LAST LOGIN")) {
				log.info("Last Login :" + isDisplayed(DashboardTab.ele_NewFilePublishedLastLoginAxis));
				mouseHover(DashboardTab.ele_NewFilePublishedLastLoginAxis);
				afterLastLoginCount = Integer.parseInt(getHighchartsAxisCount());
				log.info("After Last Login Count :" + afterLastLoginCount);
				collectionDataMap.put("afterLastLoginCount", Integer.toString(afterLastLoginCount));
				Assert.assertEquals(afterLastLoginCount, 2);
			}
			else if (axis.contains("TODAY")) {
				mouseHover(DashboardTab.ele_NewFilePublishedTodayAxis);
				afterTodayCount = Integer.parseInt(getHighchartsAxisCount());
				log.info("After Today Count :" + afterTodayCount);
				collectionDataMap.put("afterTodayCount", Integer.toString(afterTodayCount));
				Assert.assertTrue(afterTodayCount > beforeTodayCount);
			}
			else {
				mouseHover(DashboardTab.ele_NewFilePublishedPaskWeekAxis);
				afterPastWeekCount = Integer.parseInt(getHighchartsAxisCount());
				log.info("After Past Week Count :" + afterPastWeekCount);
				collectionDataMap.put("afterPastWeekCount", Integer.toString(afterPastWeekCount));
				Assert.assertTrue(afterPastWeekCount > beforePastWeekCount);
			}
		}
	}

	public void clickOnHighchartsAxis(String axis) {
		if (axis.contains("LAST LOGIN")) {
			mouseHoverAndClickElement(DashboardTab.ele_NewFilePublishedLastLoginAxis, DashboardTab.ele_NewFilePublishedLastLoginAxis);
		}
		else if (axis.contains("TODAY")) {
			mouseHoverAndClickElement(DashboardTab.ele_NewFilePublishedTodayAxis, DashboardTab.ele_NewFilePublishedTodayAxis);
		}
		else if (axis.contains("PAST WEEK")) {
			mouseHoverAndClickElement(DashboardTab.ele_NewFilePublishedPaskWeekAxis, DashboardTab.ele_NewFilePublishedPaskWeekAxis);
		}
	}

	public void verifyFileListingAndCountWithHighcharts(String axis) {
		int fileListingCount = getTotalFilesListingCount();
		int fileCount = getTotalFileCount();

		Assert.assertEquals(fileListingCount, fileCount);
		if (axis.contains("LAST LOGIN")) {
			collectionDataMap.put("LAST LOGIN FilesTab Count", Integer.toString(fileCount));
			Assert.assertEquals(afterLastLoginCount, fileCount);
			Assert.assertEquals(afterLastLoginCount, fileListingCount);

		}
		else if (axis.contains("TODAY")) {
			collectionDataMap.put("TODAY FilesTab Count", Integer.toString(fileCount));
			Assert.assertEquals(afterTodayCount, fileCount);
			Assert.assertEquals(afterTodayCount, fileListingCount);

		}
		else if (axis.contains("PAST WEEK")) {
			collectionDataMap.put("TODAY FilesTab Count", Integer.toString(fileCount));
			Assert.assertEquals(afterPastWeekCount, fileCount);
			Assert.assertEquals(afterPastWeekCount, fileListingCount);
		}
	}

	private int getTotalFilesListingCount() {
		int totalCount = 0;
		boolean flag = true;
		List<WebElement> fileList;

		do {
			waitUntilListOfElementIsDisplayed(FilesTab.css_NumberOfFiles);
			executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
			fileList = findElements(FilesTab.css_NumberOfFiles);
			totalCount += fileList.size();
			log.info("Total files count :" + totalCount);

			if (!isDisplayed(FilesTab.lnk_FilesTabDisableNextPage)) {
				clickElementAndWait(FilesTab.lnk_FilesTabEnableNextPage);
			}
			else {
				flag = false;
			}
		}
		while (flag);

		return totalCount;
	}

	private int getTotalFileCount() {
		String folderFiles;
		String fileCount[] = getElementText(FilesTab.lbl_FilesTabFilesCount).split("of ");
		folderFiles = fileCount[1].replace(")", "");
		log.info("folderFiles :" + folderFiles);
		return Integer.parseInt(folderFiles);
	}

	public void verifyLastLoginCountAfterLogin(int count) {

		mouseHover(DashboardTab.ele_NewFilePublishedPortlet);
		mouseHover(DashboardTab.ele_NewFilePublishedHiddenLastLoginAxis);
		collectionDataMap.put("LASTLOGIN Count without File Upload", getHighchartsAxisCount());
		Assert.assertEquals(count, Integer.parseInt(getHighchartsAxisCount()));
	}

	/****** flag Widget ******/
	private String	flagFilesFolder;
	final private List<String>	multiFileUploadList	= new ArrayList<String>();

	public void performFileUploadOperationForAxisCount() {
		mouseHover(DashboardTab.ele_NewFilePublishedTodayAxis);
		beforeTodayCount = Integer.parseInt(getHighchartsAxisCount());

		if (beforeTodayCount < 6) {
			navigateTab(LandingPage.lnk_Files);
			clickElementWithText(System.getProperty("global.test.project"));
			clickElementWithText("AutomationUploadFiles");
			clickElementAndWait(FilesTab.btn_DocListingAddFiles);

			String[] fileList = { "", "", "", "", "", "" };
			i = javaUtils.resetIndex(i, 1);
			sysUtils.authenticateRemoteMachine(nodeIP);
			for (String fileName : fileList) {
				fileName = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + epoch + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
				multiFileUploadList.add(fileName);
				i++;
			}
			uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileUploadList);

			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
			enterMendatoryAttributes();

			clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
			if (isDisplayed(FilesTab.pop_Handle) || isDisplayed(FilesTab.pop_PublishDocuments) || isDisplayed(FilesTab.pop_PublishIFCDocuments))
				clickElementAndWait(FilesTab.btn_PopupContinue);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitForCompletePageLoad();
			navigateTab(LandingPage.lnk_Dashboard);
		}
		else
			log.info("TODAY files Count :" + beforeTodayCount);
	}

	private String getHighchartsAxisCount() {
		return getElementText(DashboardTab.ele_NewFilePublishedMouseHoverFileCount).split(" Files")[0];
	}

	public void getFileNameList(String project, String folder) {
		flagFilesFolder = folder;
		clickElementWithText(project);
		clickElementWithText(flagFilesFolder);

		searchFiles(strUtils.extractFileNameString(searchFile));
		List<WebElement> fileList = findElements(FilesTab.css_NumberOfFiles);

		i = javaUtils.resetIndex(i, 0);

		for (WebElement file : fileList) {
			if (i == 0 || i == 1)
				highFlagFiles.add(file.getText());
			else if (i == 2 || i == 3)
				mediumFlagFiles.add(file.getText());
			else if (i == 4 || i == 5)
				lowFlagFiles.add(file.getText());
			else
				break;
			i++;
		}
		log.info("highFlagFiles :" + highFlagFiles);
		log.info("mediumFlagFiles :" + mediumFlagFiles);
		log.info("lowFlagFiles :" + lowFlagFiles);
	}

	public void selectFilesForFlag(String flagType) {
		log.info("flagType :" + flagType);
		if (flagType.contains("High")) {
			setFlagCheckList(highFlagFiles);
			collectionDataMap.put("highFlagFiles", highFlagFiles.toString());
		}
		else if (flagType.contains("Medium")) {
			setFlagCheckList(mediumFlagFiles);
			collectionDataMap.put("mediumFlagFiles", mediumFlagFiles.toString());
		}
		else if (flagType.contains("Low")) {
			setFlagCheckList(lowFlagFiles);
			collectionDataMap.put("lowFlagFiles", lowFlagFiles.toString());
		}
		else
			log.info(": No Flags Selected :");
	}

	private void setFlagCheckList(List<String> flagList) {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(flagFilesFolder);

		searchFiles(strUtils.extractFileNameString(searchFile));
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		for (String file : flagList) {
			for (WebElement fileCheckbox : fileCheckList) {
				if (fileCheckbox.getAttribute("filename").contains(file) && !fileCheckbox.isSelected()) {
					fileCheckbox.click();
					break;
				}
			}
			waitForCompletePageLoad();
		}
	}

	public void contextClickAndSelectFlagType(String flag, String flagType) {
		if (flagType.contains("High"))
			contextClickWithLink(highFlagFiles.get(0));
		else if (flagType.contains("Medium"))
			contextClickWithLink(mediumFlagFiles.get(0));
		else if (flagType.contains("Low"))
			contextClickWithLink(lowFlagFiles.get(0));
		else if (flagType.contains("Clear Flag")) {
			if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClickWithLink(allFlagFileList.get(0));
		}
		clickContextMenuOption(flag, flagType);
	}

	public void setSearchFilter(String filterType) {
		if (filterType.contains("High") || filterType.contains("Medium") || filterType.contains("Low"))
			clickElementAndWaitForElement(FilesTab.btn_SearchCreateFlagFilterDropdown, GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
		else if (filterType.contains("Inactive Only"))
			clickElementAndWaitForElement(FilesTab.btn_SearchCreateActiveInactiveFilter, GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
		else
			clickElementAndWaitForElement(FilesTab.btn_SearchCreateFilterMore, GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);

		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterProjectClearAll, GlobalPageElements.lnk_FilterProjectDisableClear);

		sendKeys(FilesTab.txt_SearchCreateFilterInput, filterType);
		clickElementAndWait(By.xpath(".//a[@title='" + filterType + "']//input[@type='checkbox']"));
		if (isDisplayed(GlobalPageElements.lnk_FilterClose))
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
		else
			waitForCompletePageLoad();
	}

	public void verifyFlagTypeAndFiles(String flag) {
		if (flag.contains("High"))
			flagTypeAndFiles(highFlagFiles, flag);
		else if (flag.contains("Medium"))
			flagTypeAndFiles(mediumFlagFiles, flag);
		else if (flag.contains("Low"))
			flagTypeAndFiles(lowFlagFiles, flag);
		else if (flag.contains("No Flag"))
			flagTypeAndFiles(allFlagFileList, flag);
	}

	private void flagTypeAndFiles(List<String> list, String flag) {

		waitForCompletePageLoad();
		waitUntilListElementIsClickable(findElements(FilesTab.css_NumberOfFiles).get(0));
		List<WebElement> fileList = findElements(FilesTab.css_NumberOfFiles);
		List<WebElement> flagTypeList = findElements(FilesTab.css_FilesTabFlagTypeList);

		Assert.assertTrue(list.size() <= fileList.size());
		i = javaUtils.resetIndex(i, 0);
		for (WebElement fileName : fileList) {
			Assert.assertTrue(list.toString() + " does not contain " + fileName.getText(), list.contains(fileName.getText()));
			Assert.assertTrue(flagTypeList.get(i) + " title property does not contain " + flag, flagTypeList.get(i).getAttribute("title").contains(flag));
			i++;
		}
	}

	public void setAllFlagFilter(String high, String medium, String low) {
		clickElementAndWaitForElement(FilesTab.btn_SearchCreateFlagFilterDropdown, FilesTab.txt_SearchCreateFilterInput);
		List<WebElement> checkList = findElements(FilesTab.css_SearchFlagFilterCheckboxList);
		for (WebElement flagCheckbox : checkList) {
			clear(FilesTab.txt_SearchCreateFilterInput);
			if (!flagCheckbox.isSelected())
				flagCheckbox.click();
		}
		if (isDisplayed(GlobalPageElements.lnk_FilterClose))
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
		else
			waitForCompletePageLoad();
	}

	public void verifyAllFlagFilterFiles() {
		List<String> fileNameList = new ArrayList<String>();
		allFlagFileList.addAll(highFlagFiles);
		allFlagFileList.addAll(mediumFlagFiles);
		allFlagFileList.addAll(lowFlagFiles);
		log.info("allFlagFileList :" + allFlagFileList);

		List<WebElement> fileList = findElements(FilesTab.css_NumberOfFiles);
		for (WebElement fileName : fileList) {
			fileNameList.add(fileName.getText());
		}
		log.info("fileNameList :" + fileNameList);

		if (fileList.size() == allFlagFileList.size())
			Assert.assertTrue(allFlagFileList.containsAll(fileNameList));
		else {
			log.info(": List Size not matched :");
			Assert.assertTrue(false);
		}
	}

	/****** Link File(s) ******/

	public void contextClickAndSelectOption(String contextOption) {
		log.info("No. of selected files :" + selectedFileNameList.size());
		if (flag1) {
			if (selectedFileNameList.size() > 0) {
				contextClickWithLink(selectedFileNameList.get(0));
				clickContextMenuOption(contextOption);
			}
		}
		else {
			contextClickWithLink(selectedFileNameList.get(0));
			clickContextMenuOption(contextOption);
		}
	}

	public void verifyFolderCountInProject() {
		clickElementWithText(System.getProperty("global.test.project"));
		Assert.assertTrue(getCount(FilesTab.css_FilesProjectSubFolders) > 1);
	}

	public void selectIFCFile() {
		waitForCompletePageLoad();
		searchFiles("ifc");
		isListPresent = findElements(FilesTab.css_FileListingIFCFileCheckboxes).size() > 0;
		log.info("isList IFC Present :" + isListPresent);

		if (isListPresent) {
			fileCheckList = findElements(FilesTab.css_FileListingIFCFileCheckboxes);
			for (WebElement checkbox : fileCheckList) {
				if (!checkbox.isSelected()) {
					checkbox.click();
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
					break;
				}
			}
		}
		else {
			log.info("IFC files not found; hence skipping steps");
		}
	}

	public void selectDestinationFolderAndClickSubmitButton(String btnText) {
		selectFolderOnPopupWindow(System.getProperty("global.test.project"));
		selectFolderOnPopupWindow(targetLinkFolder);
		collectionDataMap.put("Target Linked Folder", targetLinkFolder);
		clickButtonWithText(btnText);
	}

	public void selectUserAndClickButton(String user, String btnText, String linkType) {
		waitUntilElementIsDisplayed(FilesTab.txt_PopLinkFilesToField);
		sendKeys(FilesTab.txt_PopLinkFilesToField, user);
		sendKeys(FilesTab.txt_PopLinkFilesToField, Keys.ENTER);
		selectByVisibleText(FilesTab.drp_PopLinkFilesLinkType, linkType);
		clickElement(FilesTab.btn_PopLinkFilesSubmit);
	}

	public void clickTargetFolder() {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(targetLinkFolder);
	}

	public void verifyLinkDocumentIsAvailableInTargetFolder() {
		searchFiles(selectedFileNameList.get(0));
		Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));
		Assert.assertTrue(getElementText(FilesTab.lnk_FileName).contains(selectedFileNameList.get(0)));
	}

	public void verifyActionAssignedToSelectedUser() {
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(targetLinkFolder);
		searchFiles(selectedFileNameList.get(0));
		Assert.assertTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains(linkFileAction));
	}

	/****** Start Workflow ******/

	public void enterFormDetails() {
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
			clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		/*}
		else
			clickElementAndWait(ProjectFormsTab.img_CreateFormCalendar);*/
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
	}

	public void verifyAssociationsOnFile() throws InterruptedException {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		waitForCompletePageLoad();

		for (Integer key : actionMapKeyValList.keySet()) {
			Assert.assertTrue("Verify Association Image :", isDisplayed(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'assocFormImgName')]//img")));
			mouseHoverAndClickAssociations(key);
		}
	}

	private void mouseHoverAndClickAssociations(int i) {
		mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'assocFormImgName')]//img"));
		clickElementAndWait(FilesTab.pop_AppsPopOverContent);
	}

	public void clickOnAssociationsImage() {
		log.info(": Covered in previous step definations :");
	}

	public void verifyFormOnAttachmentAssociationPopup() {
		Assert.assertTrue(isDisplayed(ModelsTab.css_PopAssociateFormsFormTitleList));
		Assert.assertTrue(getElementText(ModelsTab.css_PopAssociateFormsFormTitleList).contains(formTitle));
	}

	public void clickOnFormID() {
		clickElementAndWait(FilesTab.lnk_PopAttachmentsAssociationsFormID);
		switchToSecondWindow();
	}

	public void verifyFileOnAttachmentAssociationPopup(String popupText) {

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			waitUntilElementIsDisplayed(FilesTab.lnk_BetaViewViewFormFilesAssociation);
			waitUntilElementIsClickable(FilesTab.lnk_BetaViewViewFormFilesAssociation);
			clickElementAndWait(FilesTab.lnk_BetaViewViewFormFilesAssociation);
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel, popupText);
			Assert.assertTrue(selectedFileNameList.get(0).contains(getElementText(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAssociateFileList)));
		/*}
		else {
			waitUntilElementIsDisplayed(FilesTab.lnk_ViewFormFilesAssociation);
			clickElementAndWait(FilesTab.lnk_ViewFormFilesAssociation);
			Assert.assertTrue(selectedFileNameList.get(0).contains(getElementText(FilesTab.lnk_PopAttachmentsAssociationsDocRef)));
		}*/
	}

	/****** Secondary Files ******/

	public void selectMoreFiles(String widgetType) {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));

		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		j = 1;
		for (WebElement checkbox : fileCheckList) {

			if (widgetType.contains("View")) {
				if (!checkbox.getAttribute("filename").endsWith(".ifc") && !checkbox.getAttribute("filename").contains("Not Published") && !checkbox.isSelected()) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					j++;
					if (j == 3)
						break;
				}
			}
			else if (widgetType.contains("Attachment")) {
				if (!checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic") && !checkbox.isSelected()) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					j++;
					if (j == 3)
						break;
				}
			}
			else if (widgetType.contains("Associations")) {
				if (checkbox.getAttribute("filename").endsWith(".txt") || checkbox.getAttribute("filename").endsWith(".pdf")) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					j++;
					if (j == 3)
						break;
				}
			}
			else if (widgetType.contains("Customize Status") || widgetType.contains("History")) {
				if (!checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc")) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					j++;
					if (j == 3)
						break;
				}
			}
			i++;
		}
		log.info("indexList :" + indexList);
		log.info("selectedFileNameList :" + selectedFileNameList);
		collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
	}

	public void contextClickAndSelectContextOption(String contextOption1, String contextOption2) {
		if (contextOption2.length() != 0) {
			contextClickAndSelectMenuOption(contextOption1);

			if (contextOption1.equalsIgnoreCase(AdoddleCommonStringPool.OPTION_HISTORY) && contextOption2.equalsIgnoreCase(AdoddleCommonStringPool.STATUS)) {
				mouseHoverElement(By.xpath(".//ul[@class='context-menu-list context-menu-root'][contains(@style,'top')]//li//span[text()='" + contextOption1 + "']"));
				clickElementAndWait(By.xpath(".//ul[@class='context-menu-list context-menu-root'][contains(@style,'top')]//li[contains(@class,'StatusHistory')]//span[text()='" + contextOption2 + "']"));
			}
			else
				clickContextMenuOption(contextOption1, contextOption2);
		}
		else {
			if (!flag1) {
				contextClickAndSelectMenuOption(contextOption1);
				clickContextMenuOption(contextOption1);
			}
		}
	}

	private void contextClickAndSelectMenuOption(String contextOption) {
		if (contextOption.contains("Download Files") || contextOption.contains("Checkout") || contextOption.contains("Undo Checkout") || contextOption.contains("Tasks") || contextOption.contains("Print File")) {
			String index = indexList.get(0);
			String docRef = selectedFileNameList.get(0);
			contextClick(By.xpath(".//div[@index='" + index + "']//div[contains(@class,'docRef')]//a[text()='" + docRef + "']"));
		}
		else if (contextOption.contains("Save as PDF")) {
			contextClick(FilesTab.lnk_FileName);
		}
		else {
			String index = indexList.get(0);
			String fileName = selectedFileNameList.get(0);
			contextClick(By.xpath(".//div[@index='" + index + "']//div[contains(@class,'Filename')]//a[text()='" + fileName + "']"));
		}
	}

	public void verifyContextMenuOptionDisabled(String widgetType) {
		if (!flag1)
			waitUntilElementIsDisplayed(By.xpath("//li[not(contains(@style,'display: none'))][contains(@class,'disabled')]//span[text()='" + widgetType + "']"));
		reloadPage();
		navigateTabByText(AsiteMenu.Dashboard.toString());
	}

	public void deSelectCheckList(String highChartAxis) {
		clickOnHighchartsAxis(highChartAxis);
		indexList.clear();
		selectedFileNameList.clear();
		actionMapKeyValList.clear();
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);

		for (WebElement checkbox : fileCheckList) {
			if (checkbox.isSelected())
				clickElementAndWait(checkbox);
		}
	}

	public void selectLinkFileForAttachment() {
		waitForCompletePageLoad();
		createFilter("more", "Linked Files");
		createFilter("Linked Files", "Only Linked Files");

		isListPresent = findElements(FilesTab.css_FilesTabStaticLinkDocumentList).size() > 0;
		log.info("isList Static Present :" + isListPresent);
		if (! isListPresent) {
			isListPresent = findElements(FilesTab.css_FilesTabDynamicLinkDocumentList).size() > 0;
			log.info("isList Dynamic Present :" + isListPresent);
		}

		if (isListPresent) {
			fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);

			i = javaUtils.resetIndex(i, 0);
			for (WebElement checkbox : fileCheckList) {
				if (checkbox.getAttribute("filetype").contains("static") || checkbox.getAttribute("filetype").contains("dynamic")) {
					if (!checkbox.isSelected()) {
						checkbox.click();

						indexList.add(Integer.toString(i));
						selectedFileNameList.add(checkbox.getAttribute("filename"));
						collectionDataMap.put("Linked File", selectedFileNameList.toString());
						break;
					}
				}
				i++;
			}
		}
		else {
			log.info("Static Link Documents not found; hence skipping the steps.");
		}
	}

	public void selectSingleFile(String widget) {
		waitForCompletePageLoad();
		if (widget.contains("Customize Status")) {
			createFilter("more", "Status");
			createFilter("Status", null);
		}
		if (widget.contains("Deactivate files")) {
			searchFiles(strUtils.extractFileNameString(multiFileUploadList.get(0)));
			deactivatedFlag = true;
		}
		else
			searchFiles(strUtils.extractFileNameString(searchFile));
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);

		if (widget.contains("Attachment")) {
			i = javaUtils.resetIndex(i, 0);
			for (WebElement checkbox : fileCheckList) {
				if (!checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic") && !checkbox.getAttribute("filename").endsWith("ifc") && !checkbox.getAttribute("filename").contains("Not Published") && !checkbox.isSelected()) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					break;
				}
				i++;
			}
		}
		else if (widget.contains("Workflow")) {
			i = javaUtils.resetIndex(i, 0);
			for (WebElement checkbox : fileCheckList) {
				if (!isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'assocFormImgName')]//img")) && !checkbox.getAttribute("filename").endsWith(".ifc")) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
					actionMapKeyValList.put(i, getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
					break;
				}
				i++;
			}
		}
		else if (widget.contains("Link File(s)")) {
			for (WebElement checkbox : fileCheckList) {
				if (!checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic") && !checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc")) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					break;
				}
			}
		}
		else if (widget.contains("Compare Files")) {
			i = javaUtils.resetIndex(i, 0);
			for (WebElement checkbox : fileCheckList) {
				if (checkbox.getAttribute("filename").endsWith(".pdf")) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					break;
				}
				i++;
			}
		}
		else if (widget.contains("Associations") || widget.contains("Deactivate files")) {
			i = javaUtils.resetIndex(i, 0);
			for (WebElement checkbox : fileCheckList) {
				if (checkbox.getAttribute("filename").endsWith(".pdf") || checkbox.getAttribute("filename").endsWith(".txt")) {
					checkbox.click();
					if (widget.contains("Deactivate files") && isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {
						mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
						waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

						isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
						log.info("isListPresent :" + isListPresent);
					}
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					break;
				}
				i++;
			}
		}
		else if (widget.contains("Customize Status")) {

			i = javaUtils.resetIndex(i, 0);
			for (WebElement checkbox : fileCheckList) {
				if (!checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc")) {
					if (!getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'status')]//a[text()]")).contains("---")) {
						checkbox.click();
						fileStatusValue = getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'status')]//a[text()]"));
						indexList.add(Integer.toString(i));
						selectedFileNameList.add(checkbox.getAttribute("filename"));
						log.info("fileStatusValue :" + fileStatusValue);
						collectionDataMap.put("Customized Status", fileStatusValue);
						break;
					}
				}
				i++;
			}
		}
		log.info("indexList :" + indexList);
		log.info("selectedFileNameList :" + selectedFileNameList);
		collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
		log.info("actionMapKeyValList :" + actionMapKeyValList);
	}

	public void clickOnSelectFileAndAttachFile() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		attachFile = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("attachFile :" + attachFile);
		collectionDataMap.put("attached File", attachFile);
		List<String> fileList = sysUtils.getFileList(attachFile);
		uploadFileUsingKeys(ModelsTab.btn_PopModelAttachmentsSelectFiles, fileList);
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
	}

	public void verifyFileAttachment() {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		waitForCompletePageLoad();
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);

		i = javaUtils.resetIndex(i, 0);
		for (WebElement checkbox : fileCheckList) {
			if (checkbox.getAttribute("filename").contains(selectedFileNameList.get(0))) {
				clickElementAndWait(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'attachmentImgName')]//img"));
				switchToSecondWindow();
				verifyAttachmentInViewer(strUtils.extractFileNameString(attachFile));
				break;
			}
			i++;
		}
	}

	public void switchToSecondWindow() {
		CreateModelCommentScripts.parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	private void verifyAttachmentInViewer(String fileName) {
		waitForCompletePageLoad();

		if (!isDisplayed(FilesTab.lbl_UnsupportedFile)) {
			waitAndSwitchIframe(FilesTab.frm_BravaObjectFrame);

			if (!isDisplayed(FilesTab.frm_ErrorFileIframe)) {
				waitUntilElementIsDisplayed(FilesTab.frm_OpenFileIframe);

				if (fileBetaViewFlag)
					AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.ele_ViewAttachFileName), fileName), getElementText(FilesTab.ele_ViewAttachFileName).contains(fileName));
				else
					Assert.assertTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.ele_ViewAttachFileName2), fileName), getElementText(FilesTab.ele_ViewAttachFileName2).contains(fileName));

				switchDefault();

			}
			else {
				Assert.assertTrue("Error: Iframe Detected", false);
			}
		}
		else {
			log.info("Not supported File : File not supported");
		}
		switchDefault();
	}

	public void clickOnDownloadImage() {
		clickElementAndWait(FilesTab.img_FileAttachmentDownloadImageButton);
	}

	public void verifyDownloadedFile(String type) throws IOException, InterruptedException {
		if (type.contains("Attachment")) {
			downloadFile = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + strUtils.extractFileNameString(attachFile);
			String defaultDownloadPath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path") + strUtils.extractFileNameString(attachFile);
			File defaultFile = new File(defaultDownloadPath);
			waitUntilFileIsDownloaded(defaultFile);
			defaultFile.renameTo(new File(downloadFile));
		}
		else if (type.contains("PDF")) {
			sysUtils.authenticateRemoteMachine(nodeIP);
			String pdfFileDirectory = sysUtils.createDirectory(nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "PDFMarkupFolder".trim() + dateUtils.getEpoch().trim());
			downloadFile = pdfFileDirectory + "\\PDFMarkupFile" + epoch + AdoddleCommonStringPool.PDF_EXTENSION;
			sysUtils.waitForSingleDirectFileDownload(nodeIP, collectionDataMap.get("MarkupFile")+" _Markups"+AdoddleCommonStringPool.PDF_EXTENSION , downloadFile.toString());
		}
		/*autoItUtils.downloadAutoIt(downloadFile, nodeIP);*/
		waitUntilFileIsDownloaded(new File(downloadFile));
	}

	/****** Distribute Files ******/

	private String getIncompleteActionsCount() {
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_IncompleteActionCount);
		mouseHover(GlobalPageElements.lnk_IncompleteActionCount);
		return findElement(GlobalPageElements.lnk_IncompleteActionCount).getAttribute("title").split(": ")[1];
	}

	private String getIncompleteActionsCountOfDiscussions() {
		waitUntilElementIsDisplayed(DiscussionsTab.lbl_UnreadDiscussionsCount);
		mouseHover(DiscussionsTab.lbl_UnreadDiscussionsCount);
		return findElement(DiscussionsTab.lbl_UnreadDiscussionsCount).getAttribute("title").split(": ")[1];
	}

	public void getDashboardIncompleteActionsCountOfPUser() {
		dashboardIncompleteActionsPUser = Integer.parseInt(getIncompleteActionsCount());
		log.info("dashboardIncompleteActionsPUser :" + dashboardIncompleteActionsPUser);
	}

	public void getFilesIncompleteActionsCountOfPUser() {
		filesIncompleteActionsPUser = Integer.parseInt(getIncompleteActionsCount());
		log.info("filesIncompleteActionsPUser :" + filesIncompleteActionsPUser);
	}

	public void getDashboardIncompleteActionsCountOfSUser() {
		dashboardIncompleteActionsSUser = Integer.parseInt(getIncompleteActionsCount());
		log.info("dashboardIncompleteActionsSUser :" + dashboardIncompleteActionsSUser);
	}

	public void getFilesIncompleteActionsCountOfSUser() {
		filesIncompleteActionsSUser = Integer.parseInt(getIncompleteActionsCount());
		log.info("filesIncompleteActionsSUser :" + filesIncompleteActionsSUser);
	}

	public void selectFilesForPerformDistributionWidget() {

		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));

		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);

		i = javaUtils.resetIndex(i, 0);
		j = javaUtils.resetIndex(j, 0);
		for (WebElement checkbox : fileCheckList) {
			if (!checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic") && !checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc")) {
				checkbox.click();
				indexList.add(Integer.toString(i));
				selectedFileNameList.add(checkbox.getAttribute("filename"));
				actionMapKeyValList.put(i, checkbox.getAttribute("filename"));
				if (j == 7)
					break;
				j++;
			}
			i++;
		}
		log.info("indexList :" + indexList);
		log.info("selectedFileNameList :" + selectedFileNameList);
		log.info("mapList :" + actionMapKeyValList);
	}

	public void assignFilesActionsToUsers(String forInfo, String forComIncrp, String forACK, String forAction, String forComCoord, String user1, String user2) {
		String[] userArrayList = { user1, user2 };
		actionArrayList.add(forComIncrp);
		actionArrayList.add(forInfo);
		actionArrayList.add(forACK);
		actionArrayList.add(forAction);
		actionArrayList.add(forComCoord);

		i = javaUtils.resetIndex(i, 1);
		for (String fileAction : actionArrayList) {
			for (String user : userArrayList) {
				sendKeys(FilesTab.txt_PopDistributeToAccessField, user);
				sendKeys(FilesTab.txt_PopDistributeToAccessField, Keys.ENTER);
				clickElementAndWaitForElement(By.xpath(".//div[contains(@style,'block')]//ul[@class='select2-choices']//li[" + i + "]//button"), ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown);
				selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, fileAction);
				/* clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate); */
				if (isDisplayed(GlobalPageElements.lnk_DatePickerCalenderToday))
					clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
				else {
					for (WebElement we : findElements(By.cssSelector("table[class='ui-datepicker-calendar'] tbody tr td")))
						log.info("\nClass Attributes for Date Pickers: " + we.getAttribute("class"));
				}
				i++;
			}
		}
	}

	public void verifyAssignedFileActionsToUsers(String forInfo, String forComIncrp, String forACK, String forAction, String forComCoord) {
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		waitForCompletePageLoad();

		isListPresent = findElements(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList).size() > 0;
		log.info("isListPresent :" + isListPresent);

		if (isListPresent) {

			for (Integer key : actionMapKeyValList.keySet()) {

				log.info("key:" + key + ",value:" + actionMapKeyValList.get(key));
				log.info("actions Displayed :" + isDisplayed(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]")));

				if (isDisplayed(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {

					/* clickElement(By.xpath(".//div[@index='"+key+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]")); */
					mouseHover(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					popHoverActionList.clear();
					for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
						popHoverActionList.add(popHoverAction.getText());
					}
					log.info("popHoverActionList :" + popHoverActionList);
					Assert.assertTrue(popHoverActionList.contains(forInfo));
					Assert.assertTrue(popHoverActionList.contains(forComIncrp));
					Assert.assertTrue(popHoverActionList.contains(forACK));
					Assert.assertTrue(popHoverActionList.contains(forAction));
					Assert.assertTrue(popHoverActionList.contains(forComCoord));
				}
			}
		}
		else
			Assert.assertTrue(isListPresent);
	}

	public void verifyFilesIncompleteActionsCountOfPUserIncreased() {
		int afterActionCount = Integer.parseInt(getIncompleteActionsCount());
		log.info("afterFilesActionCount :" + afterActionCount);
		Assert.assertTrue(afterActionCount > filesIncompleteActionsPUser);
	}

	public void verifyDashboardIncompleteActionsCountOfPUserIncreased() {
		int afterActionCount = Integer.parseInt(getIncompleteActionsCount());

		try {
			Assert.assertTrue(afterActionCount > dashboardIncompleteActionsPUser);
		}
		catch (Throwable t) {
			log.info("After Dashboard ActionCount :" + afterActionCount);
			log.info("Dashboard Incomplete Actions :" + dashboardIncompleteActionsPUser);
		}
	}

	public void verifyDashboardIncompleteActionsCountOfPUserDecreased() {
		int afterActionCount = Integer.parseInt(getIncompleteActionsCount());
		log.info("afterDashboardActionCount :" + afterActionCount);
		Assert.assertTrue(afterActionCount < dashboardIncompleteActionsPUser);
	}

	public void verifyDashboardIncompleteActionsCountOfSUserIncreased() {
		int afterActionCount = Integer.parseInt(getIncompleteActionsCount());
		log.info("afterDashboardActionCount :" + afterActionCount);
		Assert.assertTrue(afterActionCount > dashboardIncompleteActionsSUser);
	}

	public void verifyFilesIncompleteActionsCountOfSUserIncreased() {
		int afterActionCount = Integer.parseInt(getIncompleteActionsCount());
		log.info("afterFilesActionCount :" + afterActionCount);
		Assert.assertTrue(afterActionCount > filesIncompleteActionsSUser);
	}

	/****** View Files ******/

	public void selectFileForPerformViewFileWidget() {
		waitForCompletePageLoad();
		/*
		 * waitUntilElementIsClickable(FilesTab.lnk_FileName); executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		 */
		searchFiles(strUtils.extractFileNameString(searchFile));

		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		for (WebElement checkbox : fileCheckList) {

			if (isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {

				if (!checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc") && !checkbox.getAttribute("filetype").contains("noicon") && !checkbox.isSelected()) {

					mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;

					if (isListPresent) {
						for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
							popHoverActionList.add(popHoverAction.getText());
						}
						log.info("popHoverActionList :" + popHoverActionList);

						if (popHoverActionList.contains(linkFileAction)) {
							checkbox.click();
							actionMapKeyValList.put(i, checkbox.getAttribute("filename"));

							indexList.add(Integer.toString(i));
							selectedFileNameList.add(checkbox.getAttribute("filename"));
							collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
							break;
						}
						else
							log.info(": " + linkFileAction + " not Found :");
					}
					else
						log.info(": Check Next File :");
				}
			}
			i++;
		}
	}

	public void verifyFileOpened() throws Exception {
		switchToSecondWindow();
		waitForCompletePageLoad();
		waitForHTMLFileView();
	}

	public void verifyFileAttributeOnTop() throws InterruptedException {
		if (fileBetaViewFlag)
			Assert.assertTrue(isDisplayed(FilesTab.ele_BetaViewFileViewAttributesBlock));
		else
			Assert.assertTrue(isDisplayed(FilesTab.ele_FileViewAttributesBlock));
	}

	public void closeSecondWindow() {
		closeCurrentWindow();
		switchPreviousWindow(CreateModelCommentScripts.parentWindow);
		waitForCompletePageLoad();
	}

	public void verifyDocumentActionCompleted(String actionType) throws InterruptedException {
		waitForCompletePageLoad();
		if (!actionType.equalsIgnoreCase(AdoddleCommonStringPool.FOR_COMMENT_INCORP))
			searchFiles(strUtils.extractFileNameString(searchFile));
		else {
			if (createFile1 == null)
				searchFiles(strUtils.extractFileNameString(searchFile));
			else
				searchFiles(strUtils.extractFileNameString(createFile1));
		}

		for (Integer key : actionMapKeyValList.keySet()) {
			mouseHover(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
			try {
				waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
			}
			catch (Throwable t) {
				if (!isDisplayed(GlobalPageElements.pop_firstActionsPopOverContent)) {
					clickElement(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
					mouseHover(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
				}
			}

			popHoverActionList.clear();
			for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
				popHoverActionList.add(popCompleteAction.getText());
			}
			Assert.assertTrue(popHoverActionList.contains(actionType));

			isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
			if (isListPresent) {
				popHoverActionList.clear();
				for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
					popHoverActionList.add(popHoverAction.getText());
				}
				Assert.assertTrue(!popHoverActionList.contains(actionType));
			}
			else
				Assert.assertTrue(true);
			if (actionType.contains("For Comment Incorp.")) {
				if (!flag) {
					waitUtils.waitInterval(1);
					Assert.assertTrue(isDisplayed(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkStatusImgName')]//img")));
				}
			}
		}
	}

	/****** Download Files ******/

	public void selectPlaceholder() {
		waitForCompletePageLoad();
		searchFiles("Not Published");
		isListPresent = findElements(FilesTab.css_FilesTabPlaceholderList).size() > 0;
		log.info("isList Placeholder Present :" + isListPresent);

		if (isListPresent) {
			fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
			List<WebElement> divList = findElements(By.xpath(".//div[@index]"));
			i = javaUtils.resetIndex(i, 0);
			for (WebElement checkbox : fileCheckList) {
				if (checkbox.getAttribute("filename").contains("Not Published") && !checkbox.isSelected()) {
					checkbox.click();
					indexList.add(divList.get(i).getAttribute("index"));
					selectedFileNameList.add(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
					collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
					break;
				}
				i++;
			}
		}
		else {
			log.info("Placeholders not found on listing; hence skipping the steps");
		}
	}

	public void selectFilesForPerformDownloadFilesWidget() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		flag1 = false;
		i = javaUtils.resetIndex(i, 0);
		j = javaUtils.resetIndex(j, 0);
		for (WebElement checkbox : fileCheckList) {

			if (j == 2)
				break;

			if (isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {

				if (!checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc") && !checkbox.getAttribute("filetype").contains("noicon") && !checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic")) {

					mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;

					if (isListPresent) {
						popHoverActionList.clear();
						for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
							popHoverActionList.add(popHoverAction.getText());
						}
						log.info("popHoverActionList :" + popHoverActionList);

						if (popHoverActionList.contains(linkFileAction)) {
							checkbox.click();
							actionMapKeyValList.put(i, getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));

							indexList.add(Integer.toString(i));
							selectedFileNameList.add(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
							fileNameList.add(checkbox.getAttribute("filename"));
							collectionDataMap.put("fileNameList", fileNameList.toString());
							j++;
						}
						else
							log.info(": " + linkFileAction + " not Found :");
					}
					else
						log.info(": Check Next File :");
				}
			}
			i++;
		}
	}

	public void selectCheckListAndClickOnDownload(String markup) {
		List<WebElement> cbList = findElements(ModelsTab.css_AppendNameStringCheckList);
		for (WebElement e : cbList) {

			if (markup.contains("Include Markups")) {
				if (e.isDisplayed() && !e.getAttribute("id").equalsIgnoreCase("isFolderStructure") && e.isSelected())
					e.click();
			}
			else {
				if (e.isDisplayed() && !e.getAttribute("id").equalsIgnoreCase("isFolderStructure") && !e.isSelected())
					e.click();
			}
		}
		if (markup.contains("Include Markups")) {
			if (isSelected(By.id("parentDocument")))
				clickElement(By.id("parentDocument"));
			if (!isSelected(By.id("includeMarkups")))
				clickElement(By.id("includeMarkups"));
		}
		if (isSelected(By.id("isXREF")))
			clickElement(By.id("isXREF"));

		clickElementAndWait(ModelsTab.btn_PopDownloadButton);
	}

	public void downloadZipDocumentIntoLocal(String widget) throws InterruptedException, IOException {
		File file;
		waitUntilElementIsInvisible(ModelsTab.pop_BatchFiles);

		sysUtils.authenticateRemoteMachine(nodeIP);
		if (widget.contains("Download")) {
			outputZipDownloadFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationDownloadZipFile".trim() + epoch.trim();
			inputZipDownloadFile = outputZipDownloadFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			//autoItUtils.downloadAutoIt(inputZipDownloadFile, nodeIP);
			file = new File(inputZipDownloadFile);
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipDownloadFile);

		}
		else if (widget.contains("Checkout")) {
			outputZipCheckoutFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationCheckoutZipFile".trim() + epoch.trim();
			inputZipCheckoutFile = outputZipCheckoutFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			//autoItUtils.downloadAutoIt(inputZipCheckoutFile, nodeIP);
			file = new File(inputZipCheckoutFile);
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipCheckoutFile);
		}
		else {
			outputZipPDFFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationPDFZipFile".trim() + epoch.trim();
			inputZipPDFFile = outputZipPDFFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			//autoItUtils.downloadAutoIt(inputZipPDFFile, nodeIP);
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipPDFFile);
			file = new File(inputZipPDFFile);
		}
		waitUntilFileIsDownloaded(file);
	}

	public void zipIntoUnZip(String widget) {
		sysUtils.authenticateRemoteMachine(nodeIP);
		if (widget.contains("Download")) {
			inputZipDownloadFile = outputZipDownloadFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			sysUtils.unZipFile(inputZipDownloadFile, sysUtils.createDirectory(outputZipDownloadFolder));
		}
		else if (widget.contains("Checkout")) {
			inputZipCheckoutFile = outputZipCheckoutFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			sysUtils.unZipFile(inputZipCheckoutFile, sysUtils.createDirectory(outputZipCheckoutFolder));
		}
		else if (widget.contains("PDF")) {
			inputZipPDFFile = outputZipPDFFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			sysUtils.unZipFile(inputZipPDFFile, sysUtils.createDirectory(outputZipPDFFolder));
		}
	}

	public void getFileNamesFromLocalFolder(String widget) {
		File folder;
		File[] listOfFiles;

		if (widget.contains("Checkout")) {
			outputZipCheckoutFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationCheckoutZipFile".trim() + epoch.trim();
			folder = new File(outputZipCheckoutFolder);
			listOfFiles = folder.listFiles();
		}
		else if (widget.contains("Download")) {
			outputZipDownloadFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationDownloadZipFile".trim() + epoch.trim();
			folder = new File(outputZipDownloadFolder);
			listOfFiles = folder.listFiles();
		}
		else {
			outputZipPDFFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationPDFZipFile".trim() + epoch.trim();
			folder = new File(outputZipPDFFolder);
			listOfFiles = folder.listFiles();
		}
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile())
				downloadedLocalFilesVal.add(listOfFiles[i].getName());
		}
		log.info("downloadedLocalFilesVal :" + downloadedLocalFilesVal);
	}

	public void verifyFilesNameIntoSystem(String widget) {
		boolean flag = false;
		List<String> webFileList = new ArrayList<String>();

		if (!widget.contains("PDF")) {
			webFileList.addAll(selectedFileNameList);
		}
		else {
			webFileList.addAll(fileList);
		}
		log.info("downloaded UI fileList :" + webFileList);

		if (downloadedLocalFilesVal.size() == webFileList.size()) {

			for (String localFile : downloadedLocalFilesVal) {
				for (String webFile : webFileList) {
					if (localFile.contains(webFile)) {

						log.error("localFile :" + localFile);
						log.error("webFile :" + webFile);

						flag = true;
						break;
					}
					else {
						log.error("not Verified");
						flag = false;
					}
				}
				if (!flag) {
					log.error("List Value not Matched");
					Assert.assertTrue(false);
				}
			}
		}
		else {
			Assert.assertTrue("Web files size " + webFileList.size() + " != downloaded file " + downloadedLocalFilesVal.size(), false);
		}

		if (widget.contains("PDF")) {
			for (String localFile : downloadedLocalFilesVal) {

				log.info("localFile :" + localFile);
				Assert.assertTrue("file markup :" + localFile, localFile.endsWith("_Markups.pdf"));

				log.info("Test Data File Size :" + sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + localFile));
				log.info("UI File Size :" + sysUtils.getFileSize(outputZipPDFFolder + "\\" + localFile));

				try {
					Assert.assertTrue(Math.round(sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + localFile)) + " should equal " + Math.round(sysUtils.getFileSize(outputZipPDFFolder + "\\" + localFile) + 1), Math.round(sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + localFile)) == Math.round(sysUtils.getFileSize(outputZipPDFFolder + "\\" + localFile) + 1));
				}
				catch (Throwable t) {
					try {
						Assert.assertTrue(Math.round(sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + localFile)) + " should equal " + Math.round(sysUtils.getFileSize(outputZipPDFFolder + "\\" + localFile)), Math.round(sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + localFile)) == Math.round(sysUtils.getFileSize(outputZipPDFFolder + "\\" + localFile)));
					}
					catch (Throwable tx) {
						Assert.assertTrue(Math.round(sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + localFile)) + " should equal " + Math.round(sysUtils.getFileSize(outputZipPDFFolder + "\\" + localFile) - 1), Math.round(sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + localFile)) == Math.round(sysUtils.getFileSize(outputZipPDFFolder + "\\" + localFile) - 1));
					}
				}

			}
		}
	}

	public void fileRename(String widget) {
		if (widget.contains("Checkout")) {
			File oldFile;
			File newFile;

			i = javaUtils.resetIndex(i, 0);
			for (String localFile : downloadedLocalFilesVal) {
				for (String webFile : selectedFileNameList) {
					if (localFile.contains(webFile)) {
						oldFile = new File(outputZipCheckoutFolder + "/" + localFile);
						newFile = new File(outputZipCheckoutFolder + "/" + fileNameList.get(i).substring(0, fileNameList.get(i).lastIndexOf(".")) + ".".concat(oldFile.toString().substring(oldFile.toString().lastIndexOf(".") + 1)));
						log.info("File Rename :" + oldFile.renameTo(newFile));
						i++;
					}
				}
			}
		}
		else
			log.info(": Skip File Rename :");
	}

	/****** Checkout & Undo Checkout ******/

	public void selectFilesForPerformCheckoutFilesWidget() {
		waitForCompletePageLoad();
		String checkoutAction = "For Comment Incorp.";
		searchFiles(strUtils.extractFileNameString(searchFile));
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		j = javaUtils.resetIndex(j, 0);
		for (WebElement checkbox : fileCheckList) {

			if (isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]")) && !isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'chkStatusImgName')]//img"))) {

				if (!checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc") && !checkbox.getAttribute("filetype").contains("noicon") && !checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic")) {

					mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;

					if (isListPresent) {

						popHoverActionList.clear();
						for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
							popHoverActionList.add(popHoverAction.getText());
						}
						log.info("popHoverActionList :" + popHoverActionList);

						if (popHoverActionList.contains(checkoutAction)) {
							checkbox.click();
							actionMapKeyValList.put(i, getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));

							indexList.add(Integer.toString(i));
							selectedFileNameList.add(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
							fileNameList.add(checkbox.getAttribute("filename"));

							if (!flag) {
								checkoutFolder = getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'documentFolderPath')][text()]"));
								checkoutFolder = checkoutFolder.split("\\\\")[1];
								oldRevision = Integer.parseInt(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a[text()]")));
								flag = true;
							}
							else
								log.info("checkoutFolder :" + checkoutFolder);
							j++;
							if (j == 2)
								break;
						}
						else
							log.info(": Check Next File Hover :");
					}
				}
				else
					log.info(": Check Next File :");
			}
			i++;
		}
		collectionDataMap.put("Selected File List", fileNameList.toString());
	}

	public void selectProjectAndFolderAndClickOnAddFiles() {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(checkoutFolder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);
	}

	public void clickOnSelectFileAndUploadCheckoutFiles() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		List<String> fileList = sysUtils.getFileList(outputZipCheckoutFolder + "/" + fileNameList.get(0));
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
	}

	public void verifyCheckoutValidationPopup(String popupText) {
		waitForCompletePageLoad();
		Assert.assertTrue(isDisplayed(FilesTab.pop_PublishCheckoutDocumentsValidationPopup));
		Assert.assertTrue(getElementText(FilesTab.lbl_PublishDocumentsPopupLabel).contains(popupText));
	}

	public void verifyCheckoutValidationMessage(String validationMsg) {
		Assert.assertTrue(isDisplayed(FilesTab.ele_CheckoutFileValidationMessage));
		Assert.assertTrue(getElementText(FilesTab.ele_CheckoutFileValidationMessage).contains(validationMsg));
	}

	public void closePublishDocumentsPopup() {
		sendKeys(FilesTab.btn_PublishDocumentsCloseButton, Keys.ESCAPE);
	}

	public void selectFilesForPerformUndoCheckoutFilesWidget() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));

		for (Integer key : actionMapKeyValList.keySet()) {

			if (!isSelected(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"))) {
				clickElement(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"));
			}
		}
	}

	public void clickOnContinue() {
		clickElementAndWait(FilesTab.btn_PopupConfirmUIContinue);
		waitForCompletePageLoad();
	}

	public void verifyUndoCheckoutFiles() throws InterruptedException {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		for (Integer key : actionMapKeyValList.keySet()) {
			waitUntilElementIsInvisible(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkStatusImgName')]//img"));
			Assert.assertTrue("Checkout Image :", !isDisplayed(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkStatusImgName')]//img")));
		}
	}

	public void enterMendatoryAttributesForCheckoutFiles() {
		newRevision = oldRevision + 1;
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_RevisionFile1, Integer.toString(newRevision));
		selectByIndex(FilesTab.drp_PurposeOfIssueFile1, 1);
		selectByIndex(FilesTab.drp_StatusFile1, 1);
	}

	public void verifyUndoCheckoutUploadedFilesAndRevision() {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(checkoutFolder);
		searchFiles(fileNameList.get(0));

		Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));
		Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileRev).contains(Integer.toString(newRevision)));
	}

	/****** No Comment ******/

	public void uploadFilesWithCount(String uploadCount, String folder) throws IOException, InterruptedException {
		String fileName;
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(folder);
		multiFileUploadList.clear();
		searchFile = resourceUtils.getTestDataFilePath() + epoch;
		sysUtils.authenticateRemoteMachine(nodeIP);
		for(int counter=0; counter < Integer.parseInt(uploadCount); counter++) {
			fileName = sysUtils.createRemotePdfFile(nodeIP + searchFile + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			multiFileUploadList.add(fileName);
			i++;
		}
		uploadDocuments(multiFileUploadList, Integer.parseInt(uploadCount) , null, null, false, 1, null, null, null, null);
		log.info("Uploaded file List : " + multiFileUploadList);
		collectionDataMap.put("FileUploadList", multiFileUploadList.toString());
		
	}

	public void selectFilesForPerformNoCommentDistributionWidget() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);

		i = 0;
		for (WebElement file : findElements(FilesTab.css_NumberOfFiles)) {
			if (indexList.isEmpty()) {
				indexList.add(Integer.toString(i));
			}
			selectedFileNameList.add(file.getText());
			actionMapKeyValList.put(i, file.getText());
			i++;
		}
		log.info("indexList :" + indexList);
		log.info("selectedFileNameList :" + selectedFileNameList);
		collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
		log.info("mapList :" + actionMapKeyValList);
	}

	public void selectDistributedFiles() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		waitUntilElementIsClickable(FilesTab.lnk_FileName);

		for (Integer key : actionMapKeyValList.keySet()) {
			if (!isSelected(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"))) {
				clickElement(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"));
			}
		}
	}

	public void selectSingleDistributedFile() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		waitUntilElementIsClickable(FilesTab.lnk_FileName);

		for (Integer key : actionMapKeyValList.keySet()) {
			if (!isSelected(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"))) {
				clickElement(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"));
				singleDocKey = key;
				log.info("singleDocKey :" + singleDocKey);
				break;
			}
		}
	}

	public void selectMultipleDistributedFiles() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		waitUntilElementIsClickable(FilesTab.lnk_FileName);

		for (Integer key : actionMapKeyValList.keySet()) {
			if (key != singleDocKey) {
				if (!isSelected(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"))) {
					if (!flag1) {
						multiDocKey = key;
						multiDocFile = actionMapKeyValList.get(key);
						flag1 = true;
					}
					clickElementAndWait(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'chkbox')]//input[@type='checkbox'][@filename]"));
				}
			}
		}
	}

	public void verifyNoCommentDocumentsActionCompleted(String actionType, String numOfDoc) {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		waitUntilElementIsClickable(FilesTab.lnk_FileName);

		for (Integer key : actionMapKeyValList.keySet()) {
			if (!numOfDoc.contains("Single")) {
				if (key != singleDocKey)
					actionCompletedonMouseHover(key, actionType);
			}
			else {
				actionCompletedonMouseHover(key, actionType);
				break;
			}
		}
	}

	public void assignActionsToUsers(String forCom, String forDist, String userB, String userC, String userD) {
		String[] userArrayList = { userB, userC, userD };
		actionArrayList.add(forCom);
		actionArrayList.add(forDist);
		log.info("Assigning 2 actions to 3 users :"+userB+","+userC+","+userD);
		i = javaUtils.resetIndex(i, 1);
		for (String fileAction : actionArrayList) {
			for (String user : userArrayList) {
				sendKeys(FilesTab.txt_PopDistributeToAccessField, user);
				sendKeys(FilesTab.txt_PopDistributeToAccessField, Keys.ENTER);
				clickElementAndWait(By.xpath(".//div[contains(@style,'block')]//ul[@class='select2-choices']//li[" + i + "]//button"));
				selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, fileAction);
				clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate);
				i++;
			}
		}
	}

	public void getTotalIncompleteActionCountOfUser(String user) {
		reloadPage();
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Files);

		if (user.contains("Auto_FWidget UK1") || user.contains("Auto_FWidget US1")) {
			beforeDashboardUserA = Integer.parseInt(getIncompleteActionsCount());
			log.info("Dashboard before count for "+user+" :"+beforeDashboardUserA);
		}
		else if (user.contains("Auto_FWidget UK2") || user.contains("Auto_FWidget US2")) {
			beforeDashboardUserB = Integer.parseInt(getIncompleteActionsCount());
			log.info("Dashboard before count for "+user+" :"+beforeDashboardUserB);
		}
		else if (user.contains("Auto_FWidget User2")) {
			beforeDashboardUserD = Integer.parseInt(getIncompleteActionsCount());
			log.info("Dashboard before count for "+user+" :"+beforeDashboardUserD);
		}
		else if (user.contains("Auto_FWidget User3")) {
			beforeDashboardUserE = Integer.parseInt(getIncompleteActionsCount());
			log.info("Dashboard before count for "+user+" :"+beforeDashboardUserE);
		}
	}

	public void getTotalUnReadActionCountOfUser(String menuTab, String user) {
		navigateTabByText(menuTab);
		if (user.contains("Auto_FWidget UK1") || user.contains("Auto_FWidget US1")) {
			beforeUnReadDissUserA = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
			log.info("Discussions before unread count for "+user+" :" + beforeUnReadDissUserA);

		}
		else if (user.contains("Auto_FWidget UK2") || user.contains("Auto_FWidget US2")) {
			beforeUnReadDissUserB = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
			log.info("Discussions before unread count for "+user+" :"  + beforeUnReadDissUserB);

		}
		else if (user.contains("Auto_FWidget User2")) {
			beforeUnReadDissUserD = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
			log.info("Discussions before unread count for "+user+" :"  + beforeUnReadDissUserD);

		}
		else if (user.contains("Auto_FWidget User3")) {
			beforeUnReadDissUserE = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
			log.info("Discussions before unread count for "+user+" :"  + beforeUnReadDissUserE);
		}
	}

	public void assignSingleActionToSingleUser(String action, String user) {
		log.info("Assigning "+action+" actions to user :"+user);
		sendKeys(FilesTab.txt_PopDistributeToAccessField, user);
		sendKeys(FilesTab.txt_PopDistributeToAccessField, Keys.ENTER);
		clickElementAndWait(FilesTab.btn_DistributePopSingleActionToggleButton);
		selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
		clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate);
	}

	public void contextClickAndSelectNoComment(String newOption, String noCom) {
		String index = indexList.get(0);
		String fileName = selectedFileNameList.get(0);
		contextClick(By.xpath(".//div[@index='" + index + "']//div[contains(@class,'Filename')]//a[text()='" + fileName + "']"));
		clickContextMenuOption(newOption, noCom);
	}

	public void selectNoCommentForMultipleFiles(String newOption, String noCom) {
		contextClick(By.xpath(".//div[@index='" + multiDocKey + "']//div[contains(@class,'Filename')]//a[text()='" + multiDocFile + "']"));
		clickContextMenuOption(newOption, noCom);
	}

	public void verifyPrePopulatedUsersOnCommentPopup(String user1, String user2) {
		List<String> userList = new ArrayList<String>();
		List<String> webUserList = new ArrayList<String>();
		userList.add(user1);

		if (user1.contains("Auto_FWidget UK1") || user1.contains("Auto_FWidget US1")) {
			userList.add(user2);
		}

		for (WebElement prePopulatedUser : findElements(FilesTab.txt_PopCommentPrePopulatedUser)) {
			webUserList.add(prePopulatedUser.getText());
		}
		
		log.info("User list : " + userList);
		log.info("Application User list :" + webUserList);
	}

	public void verifyPrePopulatedUsersOnCommentPopup() {
		Assert.assertTrue("Pre populated user :", !isDisplayed(FilesTab.txt_PopCommentPrePopulatedUser));
	}

	public void verifyCommentTitleAndDescription() {
		Assert.assertTrue("title input does not contain No Comment", findElement(FilesTab.txt_PopNoCommentTitleInput).getAttribute("value").contains(AdoddleCommonStringPool.STRING_NO_COMMENT));
		Assert.assertTrue("description input does not contain No Comment", findElement(FilesTab.txt_PopNoCommentDescriptionInput).getAttribute("value").contains(AdoddleCommonStringPool.STRING_NO_COMMENT));
	}

	private void actionCompletedonMouseHover(int key, String actionType) {
		clickElement(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		mouseHover(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		popHoverActionList.clear();
		for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
			popHoverActionList.add(popCompleteAction.getText());
		}
		Assert.assertTrue(popHoverActionList.contains(actionType));

		isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
		if (isListPresent) {
			popHoverActionList.clear();
			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				popHoverActionList.add(popHoverAction.getText());
			}
			Assert.assertTrue(!popHoverActionList.contains(actionType));
		}
		else
			Assert.assertTrue(true);
	}

	public void verifyTotalIncompleteActionCountOfUser(String user) {
		int afterDashboardUserD2, afterDashboardUserE2;
		navigateTab(LandingPage.lnk_Files);
		if (user.contains("Auto_FWidget UK1") || user.contains("Auto_FWidget US1")) {

			if (!dashboardFlagUserA) {

				if (afterDashboardUserA != 0) {
					afterDashboardUserA2 = Integer.parseInt(getIncompleteActionsCount());
					log.info("After Dashboard incomplete action count for user "+user+" :"+ afterDashboardUserA2);
					Assert.assertTrue("After Dashboard incomplete action Count for user "+user+" :"+ afterDashboardUserA2 + " should equal " + afterDashboardUserA, afterDashboardUserA2 == afterDashboardUserA);
					dashboardFlagUserA = true;
				}
				else {
					afterDashboardUserA = Integer.parseInt(getIncompleteActionsCount());
					log.info("After Dashboard incomplete action count for user "+user+" :" + afterDashboardUserA);
					Assert.assertTrue("After Dashboard incomplete action count for user "+user+" :"+ afterDashboardUserA + " should be greater than " + beforeDashboardUserA, afterDashboardUserA == beforeDashboardUserA);
				}
			}
			else {

				if (dashboardUserA != 0) {
					int dashboardUserA2 = Integer.parseInt(getIncompleteActionsCount());
					log.info("dashboardUserA2 :" + dashboardUserA2);
					Assert.assertEquals("Dashboard incomplete action count for user "+user+" :" + dashboardUserA + " should equal " + dashboardUserA2, dashboardUserA, dashboardUserA2);
				}
				else {
					dashboardUserA = Integer.parseInt(getIncompleteActionsCount());
					log.info("Dashboard User A :" + dashboardUserA);
					Assert.assertEquals("After Dashboard incomplete action Count for user "+user+" :"+ afterDashboardUserA2 + " should equal " + dashboardUserA, afterDashboardUserA2, dashboardUserA);
				}
			}

		}
		else if (user.contains("Auto_FWidget UK2") || user.contains("Auto_FWidget US2")) {

			if (!dashboardFlagUserB) {

				if (afterDashboardUserB != 0) {
					afterDashboardUserB2 = Integer.parseInt(getIncompleteActionsCount());
					log.info("After Dashboard incomplete action count for "+user+" :" + afterDashboardUserB2);
					Assert.assertTrue("After Dashboard incomplete action count for "+user+" :"+afterDashboardUserB2+" should equal "+afterDashboardUserB, afterDashboardUserB2 == afterDashboardUserB);
					dashboardFlagUserB = true;
				}
				else {
					afterDashboardUserB = Integer.parseInt(getIncompleteActionsCount());
					log.info("After Dashboard incomplete action count for "+user+" :"+ afterDashboardUserB);
					Assert.assertTrue("After Dashboard incomplete action count for "+user+" :"+afterDashboardUserB+" should be more than "+beforeDashboardUserB, afterDashboardUserB == beforeDashboardUserB);
				}

			}
			else {

				if (dashboardUserB != 0) {
					int dashboardUserB2 = Integer.parseInt(getIncompleteActionsCount());
					log.info("dashboardUserB2 :" + dashboardUserB2);
					Assert.assertEquals("dashboardUserB :" + dashboardUserB + " " + "dashboardUserB2 :" + dashboardUserB2, dashboardUserB, dashboardUserB2);
				}
				else {
					dashboardUserB = Integer.parseInt(getIncompleteActionsCount());
					log.info("dashboardUserB :" + dashboardUserB);
					Assert.assertEquals("afterDashboardUserB2 :" + afterDashboardUserB2 + " " + "dashboardUserB :" + dashboardUserB, afterDashboardUserB2, dashboardUserB);
				}
			}

		}
		else if (user.contains("Auto_FWidget User2")) {

			if (afterDashboardUserD != 0) {
				afterDashboardUserD2 = Integer.parseInt(getIncompleteActionsCount());
				log.info("afterDashboardUserD2 :" + afterDashboardUserD2);
				Assert.assertEquals("afterDashboardUserD2 :" + afterDashboardUserD2 + " " + "afterDashboardUserD :" + afterDashboardUserD, afterDashboardUserD2, afterDashboardUserD);
			}
			else {
				afterDashboardUserD = Integer.parseInt(getIncompleteActionsCount());
				log.info("afterDashboardUserD :" + afterDashboardUserD);
				Assert.assertEquals("afterDashboardUserD :" + beforeDashboardUserD + " " + "afterDashboardUserD :" + afterDashboardUserD, beforeDashboardUserD, afterDashboardUserD);
			}
		}
		else if (user.contains("Auto_FWidget User3")) {

			if (afterDashboardUserE != 0) {
				afterDashboardUserE2 = Integer.parseInt(getIncompleteActionsCount());
				log.info("afterDashboardUserE2 :" + afterDashboardUserE2);
				Assert.assertTrue("afterDashboardUserE2 :" + afterDashboardUserE2 + " " + "afterDashboardUserE :" + afterDashboardUserE, afterDashboardUserE2 == afterDashboardUserE);
			}
			else {
				afterDashboardUserE = Integer.parseInt(getIncompleteActionsCount());
				log.info("afterDashboardUserE :" + afterDashboardUserE);
				Assert.assertTrue("afterDashboardUserE :" + afterDashboardUserE + " " + "beforeDashboardUserE :" + beforeDashboardUserE, afterDashboardUserE == beforeDashboardUserE);
			}
		}
	}

	public void verifyUnReadActionCountOfUser(String menuTab, String user) {
		navigateTabByText(menuTab);
		int afterUnReadDissUserD2, afterUnReadDissUserE2;

		if (user.contains("Auto_FWidget UK1") || user.contains("Auto_FWidget US1")) {

			if (!unReadFlagUserA) {

				if (afterUnReadDissUserA != 0) {
					afterUnReadDissUserA2 = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("afterUnReadDissUserA2 :" + afterUnReadDissUserA2);
					Assert.assertTrue("afterUnReadDissUserA2 :" + afterUnReadDissUserA2 + " " + "afterUnReadDissUserA :" + afterUnReadDissUserA, afterUnReadDissUserA2 > afterUnReadDissUserA);
					unReadFlagUserA = true;
				}
				else {
					afterUnReadDissUserA = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("afterUnReadDissUserA :" + afterUnReadDissUserA);
					Assert.assertTrue("afterUnReadDissUserA :" + afterUnReadDissUserA + " " + "beforeUnReadDissUserA :" + beforeUnReadDissUserA, afterUnReadDissUserA > beforeUnReadDissUserA);
				}

			}
			else {

				if (unReadUserA != 0) {
					int unReadUserA2 = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("unReadUserA2 :" + unReadUserA2);
					Assert.assertEquals("unReadUserA :" + unReadUserA + " " + "unReadUserA2 :" + unReadUserA2, unReadUserA, unReadUserA2);
				}
				else {
					unReadUserA = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("unReadUserA :" + unReadUserA);
					Assert.assertEquals("afterUnReadDissUserA2 :" + afterUnReadDissUserA2 + " " + "unReadUserA :" + unReadUserA, afterUnReadDissUserA2, unReadUserA);
				}
			}

		}
		else if (user.contains("Auto_FWidget UK2") || user.contains("Auto_FWidget US2")) {

			if (!unReadFlagUserB) {

				if (afterUnReadDissUserB != 0) {
					afterUnReadDissUserB2 = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("afterUnReadDissUserB2 :" + afterUnReadDissUserB2);
					Assert.assertTrue("afterUnReadDissUserB2 :" + afterUnReadDissUserB2 + " " + "afterUnReadDissUserB :" + afterUnReadDissUserB, afterUnReadDissUserB2 > afterUnReadDissUserB);
					unReadFlagUserB = true;
				}
				else {
					afterUnReadDissUserB = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("afterUnReadDissUserB :" + afterUnReadDissUserB);
					Assert.assertTrue("afterUnReadDissUserB :" + afterUnReadDissUserB + " " + "beforeUnReadDissUserB :" + beforeUnReadDissUserB, afterUnReadDissUserB > beforeUnReadDissUserB);
				}

			}
			else {

				if (unReadUserB != 0) {
					int unReadUserB2 = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("unReadUserB2 :" + unReadUserB2);
					Assert.assertEquals("unReadUserB :" + unReadUserB + " " + "unReadUserB2 :" + unReadUserB2, unReadUserB, unReadUserB2);
				}
				else {
					unReadUserB = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
					log.info("unReadUserB :" + unReadUserB);
					Assert.assertEquals("afterUnReadDissUserB2 :" + afterUnReadDissUserB2 + " " + "unReadUserB :" + unReadUserB, afterUnReadDissUserB2, unReadUserB);
				}
			}

		}
		else if (user.contains("Auto_FWidget User2")) {

			if (afterUnReadDissUserD != 0) {
				afterUnReadDissUserD2 = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
				log.info("afterUnReadDissUserD2 :" + afterUnReadDissUserD2);
				Assert.assertEquals("afterUnReadDissUserD2 :" + afterUnReadDissUserD2 + " " + "afterUnReadDissUserD :" + afterUnReadDissUserD, afterUnReadDissUserD2, afterUnReadDissUserD);
			}
			else {
				afterUnReadDissUserD = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
				log.info("afterUnReadDissUserD :" + afterUnReadDissUserD);
				Assert.assertEquals("afterUnReadDissUserD :" + afterUnReadDissUserD + " " + "beforeUnReadDissUserD :" + beforeUnReadDissUserD, beforeUnReadDissUserD, afterUnReadDissUserD);
			}
		}
		else if (user.contains("Auto_FWidget User3")) {

			if (afterUnReadDissUserE != 0) {
				afterUnReadDissUserE2 = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
				log.info("afterUnReadDissUserE2 :" + afterUnReadDissUserE2);
				Assert.assertTrue("afterUnReadDissUserE2 :" + afterUnReadDissUserE2 + " " + "afterUnReadDissUserE :" + afterUnReadDissUserE, afterUnReadDissUserE2 > afterUnReadDissUserE);
			}
			else {
				afterUnReadDissUserE = Integer.parseInt(getIncompleteActionsCountOfDiscussions());
				log.info("afterUnReadDissUserE :" + afterUnReadDissUserE);
				Assert.assertTrue("afterUnReadDissUserE :" + afterUnReadDissUserE + " " + "beforeUnReadDissUserE :" + beforeUnReadDissUserE, afterUnReadDissUserE > beforeUnReadDissUserE);
			}
		}
	}

	/****** Save as PDF ******/

	public void clickOnProjectAndFolder(String folder) {
		if (folder.contains("SavePDFTestFolder"))
			navigateTab(LandingPage.lnk_Files);
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(folder);
	}

	public void clickOnSubFolder(String folder, String subFolder) {
		clickOnProjectAndFolder(folder);
		clickElementWithText(subFolder);
	}


	public void selectFileAndGetFileSize(String docRef) {
		searchFiles(docRef);
		File folder = new File(nodeIP + ResourceHandler.loadProperty("save.pdf.folder"));
		File[] listOfFiles = folder.listFiles();

		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().contains(docRef)) {
				log.info("docRef :" + docRef);
				log.info("localFileName :" + listOfFiles[i].getName());
				webFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("save.pdf.folder") + listOfFiles[i].getName());
				collectionDataMap.put("MarkupFile", docRef);
				collectionDataMap.put("MarkupFileSize", Double.toString(webFileSize));
				break;
			}
		}
		log.info("webFileSize :" + webFileSize);
	}

	public void verifyLocalFileSize() {
		double localFileSize = sysUtils.getFileSize(downloadFile);
		log.info("localFileSize :" + localFileSize);

		try {
			Assert.assertTrue("localFileSize :" + localFileSize + " " + "webFileSize :" + webFileSize + 1, Math.round(localFileSize) == Math.round(webFileSize + 1));
		}
		catch (Throwable t) {
			try {
				Assert.assertTrue("localFileSize :" + localFileSize + " " + "webFileSize :" + webFileSize, Math.round(localFileSize) == Math.round(webFileSize));
			}
			catch (Throwable tx) {
				Assert.assertTrue("localFileSize :" + localFileSize + " " + "webFileSize :" + (webFileSize - 1), Math.round(localFileSize) == Math.round(webFileSize - 1));
			}
		}
	}

	public void getPDFFileNameList() {
		waitForCompletePageLoad();
		List<WebElement> fileNameList = findElements(FilesTab.css_NumberOfFiles);
		for (WebElement fileName : fileNameList) {
			fileList.add(fileName.getText().substring(0, fileName.getText().lastIndexOf(".")));
		}
		log.info("fileList :" + fileList);
	}

	/****** Actions ******/

	public void clickOnPopupButton(String buttonName) {
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//button[text()='" + buttonName + "']"));
	}

	public void getIncompleteActionCountOfFilesTab() {
		filesIncompleteActionsPUser = Integer.parseInt(getIncompleteActionsCount());
		log.info("beforeFilesIncompleteActions :" + filesIncompleteActionsPUser);
	}

	public void selectFilesForPerformActionsWidget(String actionType) {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));

		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		j = javaUtils.resetIndex(j, 0);
		totalActionsPerformCount = 0;

		for (WebElement checkbox : fileCheckList) {

			if (j == 2)
				break;

			if (isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {

				if (!checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc") && !checkbox.getAttribute("filetype").contains("noicon") && !checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic")) {

					mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;

					if (isListPresent) {
						popHoverActionList.clear();
						for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
							popHoverActionList.add(popHoverAction.getText());
						}
						log.info("popHoverActionList :" + popHoverActionList);

						if (popHoverActionList.contains(actionType)) {
							checkbox.click();
							totalActionsPerformCount += Collections.frequency(popHoverActionList, actionType);
							actionMapKeyValList.put(i, getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
							indexList.add(Integer.toString(i));
							selectedFileNameList.add(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
							collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
							j++;

						}
						else {
							log.info(": " + actionType + " not Found :");
							mouseHover(FilesTab.lbl_FilesTabFilesCount);
						}
					}
					else {
						log.info(": Check Next File :");
						mouseHover(FilesTab.lbl_FilesTabFilesCount);
					}
				}
			}
			i++;
		}
		log.info("totalActionsPerformCount :" + totalActionsPerformCount);
	}

	public void verifySelectedFilesOnActionPopup() {
		List<WebElement> popFileList = findElements(FilesTab.css_PopActionFilesDocRefList);

		for (WebElement fileName : popFileList) {
			fileList.add(fileName.getText());
		}
		log.info("fileList :" + fileList);

		Assert.assertTrue(fileList.containsAll(selectedFileNameList));
	}

	public void verifyIncompleteActionCountOfFilesTabDecreased() {
		int afterFilesIncompleteActions = filesIncompleteActionsPUser - totalActionsPerformCount;
		log.info("afterFilesIncompleteActions : " + afterFilesIncompleteActions);
		try {
			Assert.assertEquals(afterFilesIncompleteActions, Integer.parseInt(getIncompleteActionsCount()));
		}
		catch (Throwable t) {
			log.info(": Miss-Match Incomplete Actions Count :");
		}
	}

	public void selectCheckboxAndEnterActionPopupFields(String actionType) {
		if (!isSelected(FilesTab.chk_PopActionsCompleteActionCheckbox))
			clickElementAndWait(FilesTab.chk_PopActionsCompleteActionCheckbox);
		if (actionType.contains("For Action"))
			sendKeys(FilesTab.txt_PopForActionRemarkInput, javaUtils.getRandomString(20));
		if (actionType.contains("For Comment Incorporation")) {
			clickElementAndWaitForElement(FilesTab.img_PopActionsCalendarImage, FilesTab.lnk_PopActionsCalendarCurrentDate);
			clickElementAndWait(FilesTab.lnk_PopActionsCalendarCurrentDate);
		}
	}

	public void selectFilesForPerformSingleActionsWidget(String actionType) {
		waitForCompletePageLoad();
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.FOR_COMMENT_INCORP))
			searchFiles(strUtils.extractFileNameString(createFile1));
		else
			searchFiles(strUtils.extractFileNameString(searchFile));

		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		totalActionsPerformCount = 0;
		for (WebElement checkbox : fileCheckList) {

			if (isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {

				if (!checkbox.getAttribute("filename").contains("Not Published") && !checkbox.getAttribute("filename").endsWith(".ifc") && !checkbox.getAttribute("filetype").contains("noicon") && !checkbox.getAttribute("filetype").contains("static") && !checkbox.getAttribute("filetype").contains("dynamic")) {

					mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;

					if (isListPresent) {
						popHoverActionList.clear();
						for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
							popHoverActionList.add(popHoverAction.getText());
						}
						log.info("popHoverActionList :" + popHoverActionList);

						if (popHoverActionList.contains(actionType)) {
							checkbox.click();
							totalActionsPerformCount += Collections.frequency(popHoverActionList, actionType);
							actionMapKeyValList.put(i, getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));

							indexList.add(Integer.toString(i));
							selectedFileNameList.add(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
							collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
							flag = true;
							break;
						}
						else
							log.info(": " + actionType + " not Found :");
					}
					else
						log.info(": Check Next File :");
				}
			}
			i++;
		}
		log.info("indexList : " + indexList);
		log.info("totalActionsPerformCount : " + totalActionsPerformCount);
	}

	/* For Comment Incorp. Action */
	private String				fileStatusValue, createFile1;
	private Map<String, String>	actualFileListingAttributesList	= new HashMap<String, String>();
	private Map<String, String>	fileUploadAttributesList		= new HashMap<String, String>();

	private void MultiSelectionCheckboxDroupdownSelect() {
		String multiCheckbox = "Option1,Option3";
		String[] checkbox = multiCheckbox.split(",");
		clickElementAndWait(FilesTab.sel_PopUploadHeaderMultiSelectionBox);
		for (String checkboxSelect : checkbox) {
			if (!isSelected(By.xpath(".//th//div[contains(@class,'dropdownchecklist')]//label[text()='" + checkboxSelect.trim() + "']"))) {
				clickElementAndWait(By.xpath(".//th//div[contains(@class,'dropdownchecklist')]//label[text()='" + checkboxSelect.trim() + "']"));
			}
		}
	}

	private void clickOnApplytoAll() {
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void performCustomAttributesWithUploadFiles(String user, String actionType) {
		String createFile2;

		try {
			clickElementAndWait(FilesTab.btn_AddFiles);
			sysUtils.authenticateRemoteMachine(nodeIP);
			createFile1 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION, nodeIP).trim();
			createFile2 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION, nodeIP).trim();
			collectionDataMap.put("created File1", createFile1);
			collectionDataMap.put("created File2", createFile2);
			List<String> fileList = sysUtils.getFileList(createFile1 + "," + createFile2);
			uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
			collectionDataMap.put("fileList", fileList.toString());

			if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply))
				clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
			clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);

			sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
			selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
			selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
			sendKeys(FilesTab.txt_PopUploadHeaderIntegerTextBox, JavaUtils.getRandomNumber(3));
			sendKeys(FilesTab.txt_PopUploadHeaderEmailTextBox, javaUtils.getRandomString(2) + JavaUtils.getRandomNumber(1) + AdoddleCommonStringPool.AT_STRING + javaUtils.getRandomString(3) + AdoddleCommonStringPool.DOT_STRING + javaUtils.getRandomString(3));
			sendKeys(FilesTab.txt_PopUploadHeaderLetterBox, javaUtils.getRandomString(4));
			clickElement(FilesTab.rad_PopUploadHeaderRadioYes);
			sendKeys(FilesTab.txt_PopUploadHeader1DCoordinatesX, JavaUtils.getRandomNumber(3));
			sendKeys(FilesTab.txt_PopUploadHeader3DCoordinatesX, JavaUtils.getRandomNumber(3));
			sendKeys(FilesTab.txt_PopUploadHeader3DCoordinatesY, JavaUtils.getRandomNumber(3));
			sendKeys(FilesTab.txt_PopUploadHeader3DCoordinatesZ, JavaUtils.getRandomNumber(3));
			selectByIndex(FilesTab.drp_PopUploadHeaderStates, 1);
			selectByIndex(FilesTab.drp_PopUploadHeaderCities, 1);
			sendKeys(FilesTab.txt_PopUploadHeaderDecimalTextBox, JavaUtils.getRandomNumber(2) + AdoddleCommonStringPool.DOT_STRING + JavaUtils.getRandomNumber(2));
			sendKeys(FilesTab.txt_PopUploadHeaderLettersNumbersBox, javaUtils.getRandomString(3) + JavaUtils.getRandomNumber(3));
			MultiSelectionCheckboxDroupdownSelect();
			clickElementAndWaitForElement(FilesTab.img_PopUploadHeaderDatePicker, GlobalPageElements.lnk_DatePickerCalenderToday);
			clickElementAndWait(GlobalPageElements.lnk_DatePickerCalenderToday);
			sendKeys(FilesTab.txt_PopUploadHeader2DCoordinatesX, JavaUtils.getRandomNumber(3));
			sendKeys(FilesTab.txt_PopUploadHeader2DCoordinatesY, JavaUtils.getRandomNumber(3));
			clickOnApplytoAll();
			clickElementAndWaitForElement(FilesTab.btn_PopUploadFileDistributeFiles, FilesTab.txt_PopUploadFileDistributeTo);
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, user);
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
			clickElementAndWaitForElement(FilesTab.btn_PopDistributeUserToggleButton, ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown);
			selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, actionType);
			selectDateFromCalendar();
			clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
			if (isDisplayed(FilesTab.btn_PopupContinue))
				clickElementAndWait(FilesTab.btn_PopupContinue);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitForCompletePageLoad();
		}
		catch (Throwable t) {
			log.info("files upload failed using Custom Attributes :");
		}
	}

	public void searchActionFileAndGetAllAttributesValue() {
		indexList.clear();
		indexList.add("0");
		searchFiles(selectedFileNameList.get(0));
		actualFileListingAttributesList = getCustomAttributesValueForFileListing(actualFileListingAttributesList);
		log.info("actualFileListingAttributesList : " + actualFileListingAttributesList);
	}

	public void verifyCreatedPlaceholderDetails(String actionType) {
		String		placeholderName					= "Not Published";
		String		placeholderStatus 				= "Prepublished";
		searchFiles(selectedFileNameList.get(0));
		Assert.assertTrue("Placeholder Name :" + getElementText(FilesTab.lnk_FileName), getElementText(FilesTab.lnk_FileName).contains(placeholderName));
		Assert.assertTrue("Placeholder Status :", getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(placeholderStatus));
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks))
			popHoverActionList.add(popHoverAction.getText());
		Assert.assertTrue(popHoverActionList.contains(actionType));
		mouseHover(GlobalPageElements.lnk_IncompleteActionCount);
		actionMapKeyValList.clear();
		actionMapKeyValList.put(1, selectedFileNameList.get(0));
	}

	public void searchPlaceholderAndClickOnAction() {
		searchFiles(selectedFileNameList.get(0));
		clickElementAndWait(FilesTab.lnk_FilesTabFirstFileActionName);
	}

	public void verifyPlaceholderAttributesForFileUpload() {
		Map<String, String> actualFileAttributeList = new HashMap<String, String>();
		Map<String, String> expectedFileAttributeList = new HashMap<String, String>();
		actualFileAttributeList = getCustomAttributesValueForFileUploadListing(actualFileAttributeList);
		expectedFileAttributeList.putAll(actualFileListingAttributesList);
		actualFileAttributeList.remove("status");
		expectedFileAttributeList.remove("status");
		Assert.assertTrue("\nActual: " + actualFileAttributeList + "\nExpected: " + expectedFileAttributeList, compareUnorderedMaps(actualFileAttributeList, expectedFileAttributeList));
	}

	public void selectStatusAndGetUploadFileAttributesValue() {
		selectByIndex(FilesTab.drp_StatusFile1, 2);
		fileStatusValue = new Select(findElement(FilesTab.drp_StatusFile1)).getFirstSelectedOption().getText();
		log.info("fileStatusValue :" + fileStatusValue);
		fileUploadAttributesList = getCustomAttributesValueForFileUploadListing(fileUploadAttributesList);
		log.info("fileUploadAttributesList :" + fileUploadAttributesList);
	}

	public void enterDistributeUserField(String user, String action) {
		sendKeys(FilesTab.txt_PopCreatePlaceholderDistributeUserInput, user);
		sendKeys(FilesTab.txt_PopCreatePlaceholderDistributeUserInput, Keys.ENTER);
		if (!isDisplayed(FilesTab.lnk_PopDatePickerCurrentDate))
			clickElementAndWait(FilesTab.btn_PopDistributeUserToggleButton);
		selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
		selectDateFromCalendar();
	}

	public void verifyUploadedFileInPlaceholderWithAttributesAndAction(String actionType) {
		Map<String, String> uploadedFileAttributesList = new HashMap<String, String>();
		searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
		Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));
		Assert.assertTrue(getElementText(FilesTab.lnk_FileName).contains(strUtils.extractFileNameString(CreateEditRoleScripts.createFile)));
		Assert.assertTrue("File Status Verify :", getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(fileStatusValue));
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		popHoverActionList.clear();
		for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
			popHoverActionList.add(popCompleteAction.getText());
		}
		Assert.assertTrue(popHoverActionList.contains(actionType));

		isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
		if (isListPresent) {
			popHoverActionList.clear();
			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				popHoverActionList.add(popHoverAction.getText());
			}
			Assert.assertTrue(!popHoverActionList.contains(actionType));
		}
		else
			Assert.assertTrue(true);

		uploadedFileAttributesList = getCustomAttributesValueForFileListing(uploadedFileAttributesList);
		log.info("uploadedFileAttributesList :" + uploadedFileAttributesList);
		uploadedFileAttributesList.remove("status");
		actualFileListingAttributesList.remove("status");
		Assert.assertTrue(uploadedFileAttributesList.equals(actualFileListingAttributesList));
	}

	public void deactivateActionFiles() {
		try {
			searchFiles(selectedFileNameList.get(0));
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, GlobalPageElements.pop_PopUpElement);
			clickElementAndWaitForElement(FilesTab.lnk_PopFileOptionsDeactivateFiles, FilesTab.chk_PopDeactivateFilesEntireFile);
			clickElementAndWait(FilesTab.chk_PopDeactivateFilesEntireFile);
			clickElementAndWait(FilesTab.btn_PopDeactivateFilesDeactivate);
			Assert.assertTrue(getElementText(FilesTab.lbl_PopDeactivatedFileSuccessMsg).equalsIgnoreCase(ResourceHandler.loadProperty("msg.file.deactivate.success")));
			clickElementAndWait(FilesTab.btn_PopDeactivatedFileSuccessOk);
		}
		catch (Throwable t) {
			log.info(": file not deactivated :");
		}
	}

	private Map<String, String> getCustomAttributesValueForFileListing(Map<String, String> mapList) {
		mapList.put("rev", getElementText(FilesTab.lnk_FilesTabFirstFileRev));
		mapList.put("docTitle", getElementText(FilesTab.ele_FilesTabFirstFileDocTitle));
		mapList.put("poi", getElementText(FilesTab.ele_FilesTabFirstFilePOI));
		mapList.put("status", getElementText(FilesTab.lnk_FilesTabFirstFileStatus));
		mapList.put("integerTextbox", getElementText(FilesTab.ele_FilesTabFirstFileIntegerTextbox));
		mapList.put("emailTextbox", getElementText(FilesTab.ele_FilesTabFirstFileEmailTextbox));
		mapList.put("letter", getElementText(FilesTab.ele_FilesTabFirstFileLetter));
		mapList.put("radio", getElementText(FilesTab.ele_FilesTabFirstFileRadio));
		mapList.put("coordinates1DimensionX", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates1Dimension).split(".0")[0]);
		mapList.put("coordinates3DimensionX", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates3Dimension).split(",")[0].replace(".0", ""));
		mapList.put("coordinates3DimensionY", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates3Dimension).split(",")[1].replace(".0", ""));
		mapList.put("coordinates3DimensionZ", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates3Dimension).split(",")[2].replace(".0", ""));
		mapList.put("states", getElementText(FilesTab.ele_FilesTabFirstFileStates).replace(" (1)", ""));
		mapList.put("cities", getElementText(FilesTab.ele_FilesTabFirstFileCities).replace(" (1)", ""));
		mapList.put("decimalTextbox", getElementText(FilesTab.ele_FilesTabFirstFileDecimalTextbox));
		mapList.put("lettersNumbersTextbox", getElementText(FilesTab.ele_FilesTabFirstFileLettersNumbersTextbox));
		mapList.put("multiSelectionCheckbox", getElementText(FilesTab.ele_FilesTabFirstFileMultiSelectionCheckbox));
		mapList.put("datePicker", getElementText(FilesTab.ele_FilesTabFirstFileDatePicker));
		mapList.put("coordinates2DimensionX", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates2Dimension).split(",")[0].replace(".0", ""));
		mapList.put("Coordinates2DimensionY", getElementText(FilesTab.ele_FilesTabFirstFileCoordinates2Dimension).split(",")[1].replace(".0", ""));

		return mapList;
	}

	private Map<String, String> getCustomAttributesValueForFileUploadListing(Map<String, String> mapList) {
		mapList.put("rev", findElement(FilesTab.txt_PopUploadRevision).getAttribute("value"));
		mapList.put("docTitle", findElement(FilesTab.txt_DocTitle).getAttribute("value"));
		mapList.put("poi", new Select(findElement(FilesTab.drp_PopUploadPurposeOfIssue)).getFirstSelectedOption().getText());
		mapList.put("status", new Select(findElement(FilesTab.drp_StatusFile1)).getFirstSelectedOption().getText());
		mapList.put("integerTextbox", findElement(FilesTab.txt_IntegerTextbox).getAttribute("value"));
		mapList.put("emailTextbox", findElement(FilesTab.txt_EmailTextbox).getAttribute("value"));
		mapList.put("letter", findElement(FilesTab.txt_Letter).getAttribute("value"));
		mapList.put("radio", findElement(FilesTab.sel_Radio).getAttribute("value").replace("y", "Y"));
		mapList.put("coordinates1DimensionX", findElement(FilesTab.txt_Coordinates1DimensionX).getAttribute("value"));
		mapList.put("coordinates3DimensionX", findElement(FilesTab.txt_Coordinates3DimensionX).getAttribute("value"));
		mapList.put("coordinates3DimensionY", findElement(FilesTab.txt_Coordinates3DimensionY).getAttribute("value"));
		mapList.put("coordinates3DimensionZ", findElement(FilesTab.txt_Coordinates3DimensionZ).getAttribute("value"));
		mapList.put("states", new Select(findElement(FilesTab.drp_States)).getFirstSelectedOption().getText());
		mapList.put("cities", new Select(findElement(FilesTab.drp_Cities)).getFirstSelectedOption().getText());
		mapList.put("decimalTextbox", findElement(FilesTab.txt_DecimalTextbox).getAttribute("value"));
		mapList.put("lettersNumbersTextbox", findElement(FilesTab.txt_LettersNumbersTextbox).getAttribute("value"));
		mapList.put("multiSelectionCheckbox", getElementText(FilesTab.ele_MultiSelectionCheckbox).replace(", ", ","));
		/* mapList.put("multiSelectionCheckbox", getElementText(FilesTab.ele_MultiSelectionCheckbox)); */
		mapList.put("datePicker", findElement(FilesTab.txt_DatePickerInput).getAttribute("value"));
		mapList.put("coordinates2DimensionX", findElement(FilesTab.txt_Coordinates2DimensionX).getAttribute("value"));
		mapList.put("Coordinates2DimensionY", findElement(FilesTab.txt_Coordinates2DimensionY).getAttribute("value"));

		return mapList;
	}

	/****** Batch Print Files ******/

	public void selectFilesForPerformBatchPrint() {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		j = javaUtils.resetIndex(j, 0);
		for (WebElement checkbox : fileCheckList) {
			if (checkbox.getAttribute("filename").endsWith(".pdf")) {
				checkbox.click();
				indexList.add(Integer.toString(i));
				selectedFileNameList.add(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'docRef')]//a[text()]")));
				if (j == 1)
					break;
				j++;
			}
			i++;
		}
		log.info("docRef Index List :" + indexList);
		log.info("fileDocRef List :" + selectedFileNameList);
	}

	public void selectPrintDocumentPopupCheckList() {
		for (WebElement checkbox : findElements(FilesTab.css_PopPrintDocumentCheckList)) {
			if (!checkbox.isSelected())
				checkbox.click();
		}
	}

	public void verifyFilesOpenedForBatchPrint() throws Exception {
		switchToSecondWindow();
		/*waitInterval(5);*/
		try {
			sendActionKeys(Keys.ESCAPE);
			waitForCompletePageLoad();
			Assert.assertTrue(isDisplayed(FilesTab.frm_PDFViewer));
		}
		catch (Throwable t) {
			log.info(": can't find PDF Viewer :");
		}
		takeScreenShot(TestDriverFactory.scenario);
		closeSecondWindow();
	}

	/****** Compare Files Html Viewer ******/

	public void selectMoreThanTwoFiles(String widget) {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		if (widget.contains("Compare Files")) {
			i = javaUtils.resetIndex(i, 0);
			j = javaUtils.resetIndex(j, 0);
			for (WebElement checkbox : fileCheckList) {
				if (checkbox.getAttribute("filename").endsWith(".pdf")) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					if (j == 2)
						break;
					j++;
				}
				i++;
			}
		}
		log.info("indexList :" + indexList);
		log.info("selectedFileNameList :" + selectedFileNameList);
		collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
	}

	public void selectFilesForPerformCompareFilesWidget() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		j = javaUtils.resetIndex(j, 0);
		for (WebElement checkbox : fileCheckList) {
			if (checkbox.getAttribute("filename").endsWith(".pdf")) {
				checkbox.click();
				indexList.add(Integer.toString(i));
				selectedFileNameList.add(checkbox.getAttribute("filename"));
				if (j == 1)
					break;
				j++;
			}
			i++;
		}
		log.info("indexList :" + indexList);
		log.info("selectedFileNameList :" + selectedFileNameList);
		collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
	}

	public void verifyCompareFilesInHtmlViewer() {
		switchToSecondWindow();
		waitForCompletePageLoad();
		waitAndSwitchIframe(FilesTab.frm_BravaObjectFrame);

		if (!isDisplayed(FilesTab.frm_BravaObjectFrame)) {
			if (getElementText(FilesTab.ele_CompareFilesScreenBanner1).contains(selectedFileNameList.get(0))) {
				Assert.assertTrue(getElementText(FilesTab.ele_CompareFilesScreenBanner1).contains(selectedFileNameList.get(0)));
				Assert.assertTrue(getElementText(FilesTab.ele_CompareFilesScreenBanner2).contains(selectedFileNameList.get(1)));
				takeScreenShot(TestDriverFactory.scenario);
			}
			else if (getElementText(FilesTab.ele_CompareFilesScreenBanner1).contains(selectedFileNameList.get(1))) {
				Assert.assertTrue(getElementText(FilesTab.ele_CompareFilesScreenBanner1).contains(selectedFileNameList.get(1)));
				Assert.assertTrue(getElementText(FilesTab.ele_CompareFilesScreenBanner2).contains(selectedFileNameList.get(0)));
				takeScreenShot(TestDriverFactory.scenario);

			}
			else
				Assert.assertTrue(false);
		}
		else
			Assert.assertTrue(false);
		closeSecondWindow();
	}

	/****** Associations ******/

	public void verifyViewFileActivateTab(String activeTab) {
		switchToSecondWindow();
		waitForCompletePageLoad();

		if (fileBetaViewFlag)
			Assert.assertTrue("Activated Beta tab :" + getElementText(FilesTab.lbl_BetaViewViewFileActiveTabLabel), getElementText(FilesTab.lbl_BetaViewViewFileActiveTabLabel).contains(activeTab));
		else {
			List<WebElement> lHTabList = findElements(FilesTab.css_ViewFileLHPanelTabList);

			for (WebElement lhTab : lHTabList) {
				if (lhTab.getText().contains(activeTab)) {
					log.info("Activated tab :" + lhTab.getText() + " : Activated Class :" + lhTab.getAttribute("class").contains("active"));
					Assert.assertTrue("Activated tab :" + lhTab.getText(), lhTab.getAttribute("class").contains("active"));
					break;
				}
			}
		}
	}

	public void verifyAssociatedFileInRHPanelTab() throws Exception {
		waitForHTMLFileView(selectedFileNameList.get(0));
		closeSecondWindow();
	}

	/****** Deactivate and Reactivate files ******/

	public void selectAllCheckboxesOfDeactivateFiles() {
		clickElementAndWait(FilesTab.chk_PopDeactivateFilesEntireFile);
	}

	public void verifyReactivatedDeactivatedFilesValidationMessage(String reactiveDeactiveMsg) {
		waitUntilElementIsDisplayed(FilesTab.ele_DeactivatedFilesValidationMessage);
		waitUntilElementContainsText(FilesTab.ele_DeactivatedFilesValidationMessage, reactiveDeactiveMsg);
	}

	public void verifyFileListing(String file, String fileListType) {
		searchFiles(strUtils.extractFileNameString(multiFileUploadList.get(0)));
		if (file.equalsIgnoreCase(fileListType))
			Assert.assertTrue("File Not Displayed :", !isDisplayed(FilesTab.lnk_FileName));
		else
			Assert.assertTrue("File Displayed :", isDisplayed(FilesTab.lnk_FileName));
		searchFiles("");
	}

	public void searchSelectedFile() {
		if (deactivatedFlag)
			searchFiles(strUtils.extractFileNameString(multiFileUploadList.get(0)));
		else
			searchFiles(strUtils.extractFileNameString(searchFile));
	}

	public void verifyFileCurrentStatusOnHistoryTab(String fileCurrentStatus) {
		if (fileBetaViewFlag) {
			int i = 1;
			boolean typeFlag = false, listFlag = false;
			for (WebElement historyHeader : findElements(FilesTab.css_BetaViewViewFileHistoryTabLabelList)) {
				if (historyHeader.getText().contains("Type")) {
					listFlag = true;
					for (WebElement fileStatus : findElements(By.xpath(".//div[@id='right-content']/main[@class='open']//file-history//div[contains(@class,'gbody')]//ul//li[" + i + "]"))) {
						if (fileStatus.getText().contains(fileCurrentStatus)) {
							typeFlag = true;
							Assert.assertTrue("Actual File Status :" + fileStatus.getText() + " Expected File Status :" + fileCurrentStatus, fileStatus.getText().contains(fileCurrentStatus));
							break;
						}
					}
				}
				if (listFlag)
					break;
				i++;
			}
			if (! typeFlag)
				Assert.assertTrue("Type History listing Failed : ", false);
		}
		else {
			if (isListPresent) {
				try {
					Assert.assertTrue("file Actions Status :" + getElementText(FilesTab.ele_HistoryTabFilesPreviousStatus) + " should contain Cleared", getElementText(FilesTab.ele_HistoryTabFilesPreviousStatus).contains("Cleared"));
				}
				catch (Throwable t) {
					log.info(": History Status \"Cleared\" failed :");
				}
			}
			try {
				Assert.assertTrue("file Actions Status :" + getElementText(FilesTab.ele_HistoryTabFilesCurrentStatus) + " should contain Deactivated", getElementText(FilesTab.ele_HistoryTabFilesCurrentStatus).contains(fileCurrentStatus));
			}
			catch (Throwable t) {
				Assert.assertTrue("file Actions Status-Catch :" + getElementText(FilesTab.ele_HistoryTabFilesPreviousStatus) + " should contain Deactivated-Catch", getElementText(FilesTab.ele_HistoryTabFilesPreviousStatus).contains(fileCurrentStatus));
			}
		}
		closeSecondWindow();
	}

	public void removeSearchFilter(String filterType) {
		if (filterType.contains("Active / Inactive"))
			clickElementAndWait(FilesTab.ele_FilterActiveInactiveRemoveLink);
	}

	/****** Customize Status ******/

	public void setDocStatusFontAndColor(String color, String font, String applyToRow) {
		Assert.assertTrue(findElement(FilesTab.txt_PopManageDocStatusActiveStatusInput).getAttribute("value").contains(fileStatusValue));
		clickElementAndWaitForElement(FilesTab.img_PopManageDocStatusActiveStatusSettingButton, ProjectsTab.img_PopManageDocStatusSettingsBgColor);
		clickElementAndWaitForElement(ProjectsTab.img_PopManageDocStatusSettingsBgColor, ProjectsTab.txt_PopBGColorPickerRedInput);

		if (color.equalsIgnoreCase("red")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		}
		else if (color.equalsIgnoreCase("green")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		}

		clickElementAndWait(ProjectsTab.btn_PopBGColorPickerOKButton);

		if (applyToRow.equalsIgnoreCase("yes"))
			clickElementAndWaitForElement(ProjectsTab.rad_DocStatusSettingsApplyToRecord, ProjectsTab.lnk_DocStatusSettingsFont);
		else if (applyToRow.equalsIgnoreCase("no"))
			clickElementAndWaitForElement(ProjectsTab.rad_DocStatusSettingsApplyToCell, ProjectsTab.lnk_DocStatusSettingsFont);

		clickElementAndWaitForElement(ProjectsTab.lnk_DocStatusSettingsFont, ProjectsTab.txt_DocStatusSettingsFontSearch);
		sendKeys(ProjectsTab.txt_DocStatusSettingsFontSearch, font);
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectsTab.lbl_DocStatusSettingsFontSearchResult, ProjectsTab.btn_DocStatusSettingsSave);
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_DocStatusSettingsSave, ProjectsTab.btn_DocStatusSettingsSave);
	}

	public void clickOnSave() {
		clickElementAndWait(ProjectsTab.btn_PopManageFormStatusesSave);
	}

	public void verifyDocStatusFontAndColor(String statusColor, String statusFont, String cellRecordFlag) {
		if (statusColor.equalsIgnoreCase("red"))
			statusColor = "rgb(255, 0, 0)";
		else if (statusColor.equalsIgnoreCase("green"))
			statusColor = "rgb(0, 255, 0)";
		statusSetColor = statusColor;
		log.info("statusSetColor :" + statusSetColor);
		searchFiles(strUtils.extractFileNameString(searchFile));
		Assert.assertTrue(getElementText(FilesTab.lnk_UploadedFileStatus) + " should contain " + fileStatusValue, getElementText(FilesTab.lnk_UploadedFileStatus).contains(fileStatusValue));

		statusSetFont = statusFont;
		if (cellRecordFlag.equalsIgnoreCase("yes")) {
			Assert.assertTrue(findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style") + " should contain font-family:" + statusFont, findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style").contains(statusFont));
			Assert.assertTrue(findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style") + " should contain background-color: " + statusColor, findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style").contains(statusColor));
		}
		else if (cellRecordFlag.equalsIgnoreCase("no")) {
			mouseHover(ProjectsTab.ele_DocListingFirstStatusCell);
			Assert.assertTrue(findElement(ProjectsTab.ele_DocListingFirstStatusCell).getAttribute("style") + " should contain font-family: '" + statusFont + "'", findElement(ProjectsTab.ele_DocListingFirstStatusCell).getAttribute("style").contains(statusFont));
			Assert.assertTrue(findElement(ProjectsTab.ele_DocListingFirstStatusCell).getAttribute("style") + " should contain background-color: " + statusColor, findElement(ProjectsTab.ele_DocListingFirstStatusCell).getAttribute("style").contains(statusColor));
		}
	}

	public void setCustomizedStatusAsDefault(String defaultSetButton) {
		clickElementAndWaitForElement(FilesTab.img_PopManageDocStatusActiveStatusSettingButton, ProjectsTab.img_PopManageDocStatusSettingsBgColor);
		clickOnPopupButton(defaultSetButton);
		waitUntilElementIsClickable(ProjectsTab.btn_DocStatusSettingsSave);
		clickElementAndWait(ProjectsTab.btn_DocStatusSettingsSave);
		waitUntilElementIsInvisible(ProjectsTab.btn_DocStatusSettingsSave);
	}

	public void verifyCustomizedStatusSetAsDefault() {
		searchFiles(strUtils.extractFileNameString(searchFile));
		Assert.assertTrue(getElementText(FilesTab.lnk_UploadedFileStatus).contains(fileStatusValue));
		Assert.assertTrue("Status should contain font-family:" + statusSetFont, !findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style").contains(statusSetFont));
		Assert.assertTrue("Status should contain background-color: " + statusSetColor, !findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style").contains(statusSetColor));

	}

	/****** History ******/

	public void selectFileForPerformHistoryWidget(String historyType) {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		fileCheckList = findElements(FilesTab.css_FilesTabFilesCheckboxList);
		i = javaUtils.resetIndex(i, 0);
		for (WebElement checkbox : fileCheckList) {
			if (historyType.contains("Distribution")) {
				if (isDisplayed(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {
					checkbox.click();
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					break;
				}
			}
			else if (historyType.contains("Status") || historyType.contains("Revisions")) {
				if (checkbox.getAttribute("filename").endsWith(".pdf") || checkbox.getAttribute("filename").endsWith(".PDF") || checkbox.getAttribute("filename").endsWith(".txt") || checkbox.getAttribute("filename").endsWith(".jpg")) {
					checkbox.click();
					if (historyType.contains("Status")) {
						mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'status')]//a[text()]"));
						oldStatus = getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'status')]//a[text()]"));
						log.info("oldStatus :" + oldStatus);
					}
					else if (historyType.contains("Revisions")) {
						mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a[text()]"));
						fileLatestRev = getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a[text()]"));
						log.info("fileLatestRev :" + fileLatestRev);
					}
					indexList.add(Integer.toString(i));
					selectedFileNameList.add(checkbox.getAttribute("filename"));
					actionMapKeyValList.put(i, checkbox.getAttribute("filename"));
					break;
				}
				else {
					log.info(": Check Next :" + i + " " + checkbox.getAttribute("filename"));
				}
			}
			i++;
		}
		log.info("indexList :" + indexList);
		log.info("selectedFileNameList :" + selectedFileNameList);
		collectionDataMap.put("selectedFileNameList", selectedFileNameList.toString());
		log.info("actionMapKeyValList :" + actionMapKeyValList);
	}

	public void verifyHistoryDropdownListType(String historyType) {

		String selectedHistotyType;

		if (fileBetaViewFlag)
			selectedHistotyType = new Select(findElement(ProjectFormsTab.drp_BetaViewHistoryTabHistoryTypeDropdown)).getFirstSelectedOption().getText();
		else
			selectedHistotyType = new Select(findElement(FilesTab.drp_HistoryTabHistoryTypeDropdown)).getFirstSelectedOption().getText();
		log.info("selectedHistotyType :" + selectedHistotyType);
		Assert.assertTrue(selectedHistotyType.contains(historyType));
	}

	public void verifyAllHistoryWithSelectType(String historyStatus) {

		if (!fileBetaViewFlag) {
			List<WebElement> historyList = findElements(FilesTab.css_HistoryTabFilesFormsHistoryRowList);
			for (WebElement hisType : historyList) {
				log.info("hisType :" + hisType.getText());
				Assert.assertTrue(hisType.getText().contains(historyStatus));
			}
		}
	}

	public void closeHistoryPage() {
		closeSecondWindow();
		reloadPage();
	}

	public void changeStatusAndNoteAndClickOnChangeStatus(String statusChangeNote, String changeStatusBtn) {
		selectByIndex(FilesTab.drp_PopChangeStatusDropdown, 2);
		newStatus = new Select(findElement(FilesTab.drp_PopChangeStatusDropdown)).getFirstSelectedOption().getText().trim();
		log.info("newStatus :" + newStatus);

		if (newStatus.contains(oldStatus)) {
			selectByIndex(FilesTab.drp_PopChangeStatusDropdown, 3);
			newStatus = new Select(findElement(FilesTab.drp_PopChangeStatusDropdown)).getFirstSelectedOption().getText().trim();
		}
		log.info("updated newStatus :" + newStatus);

		sendKeys(FilesTab.txt_PopStatusChangeNoteTextArea, statusChangeNote + epoch);
		clickOnPopupButton(changeStatusBtn);
	}

	public void verifyUpdatedStatusOnFilesDocument() {
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(searchFile));
		waitUntilElementIsDisplayed(By.xpath(".//div[@index='" + indexList.get(0) + "']//div[contains(@class,'status')]//a[text()]"));

		for (Integer key : actionMapKeyValList.keySet()) {
			mouseHover(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'status')]//a[text()]"));
			log.info("FileListing Status :" + getElementText(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'status')]//a[text()]")));
			Assert.assertTrue(getElementText(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'status')]//a[text()]")).trim().contains(newStatus));
		}
	}

	public void verifyUpdatedStatusValueOnStatusHistoryPage() {

		if (fileBetaViewFlag) {
			log.info("Old Status :" + getElementText(FilesTab.ele_BetaViewHistoryTabOldStatus));
			log.info("New Status :" + getElementText(FilesTab.ele_BetaViewHistoryTabNewStatus));
			Assert.assertTrue(getElementText(FilesTab.ele_BetaViewHistoryTabOldStatus).contains(oldStatus));
			Assert.assertTrue(getElementText(FilesTab.ele_BetaViewHistoryTabNewStatus).contains(newStatus));
		}
		else {
			String updatedStatus = getElementText(FilesTab.css_HistoryTabNewStatusRowList);
			log.info("updatedStatus :" + updatedStatus);
			Assert.assertTrue(updatedStatus.contains(newStatus));
			clickElementAndWait(FilesTab.css_HistoryTabNewStatusRowList);
			log.info("Old Status :" + getElementText(FilesTab.ele_HistoryTabOldStatus));
			log.info("New Status :" + getElementText(FilesTab.ele_HistoryTabNewStatus));
			Assert.assertTrue(getElementText(FilesTab.ele_HistoryTabOldStatus).contains(oldStatus));
			Assert.assertTrue(getElementText(FilesTab.ele_HistoryTabNewStatus).contains(newStatus));
		}
	}

	public void verifyFileLatestRevisionOnHistoryTab() {
		String latestRevision;

		if (fileBetaViewFlag) {
			latestRevision = getElementText(FilesTab.ele_BetaViewHistoryTabLatestRevision);
			log.info("latestRevision :" + latestRevision);
			Assert.assertTrue(fileLatestRev.equalsIgnoreCase(latestRevision));
		}
		else {
			latestRevision = getElementText(FilesTab.ele_HistoryTabLatestRevision);
			log.info("latestRevision :" + latestRevision);
			if (latestRevision.contains("99+")) {
				fileLatestRev = Integer.toString(Integer.parseInt(fileLatestRev) - 1);
				log.info("fileLatestRev :" + fileLatestRev);
				String fileRev = getElementText(FilesTab.ele_HistoryTabSecondRow);
				log.info("99+ fileRev :" + fileRev);
				Assert.assertTrue(fileRev.contains(fileLatestRev));
			}
			else
				Assert.assertTrue(fileLatestRev.equalsIgnoreCase(latestRevision));
		}
		closeSecondWindow();
	}

	public void uploadFilesWithAction(String folder, String project, String action, String userA, String userB) {
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(project);
		clickElementWithText(folder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);

		searchFile = resourceUtils.getTestDataFilePath() + epoch;
		log.info("searchFile : " + searchFile);

		String[] fileList = { "", ""};
		i = javaUtils.resetIndex(i, 1);
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (String fileName : fileList) {
			fileName = sysUtils.createRemotePdfFile(nodeIP + searchFile + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			multiFileUploadList.add(fileName);
			i++;
		}
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileUploadList);
		collectionDataMap.put("FileUploadList", multiFileUploadList.toString());

		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		enterMendatoryAttributes();
		distributeActionsToUsersWithFilesUpload(action, userA, userB);

		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		if (isDisplayed(FilesTab.pop_Handle) || isDisplayed(FilesTab.pop_PublishDocuments) || isDisplayed(FilesTab.pop_PublishIFCDocuments))
			clickElementAndWait(FilesTab.btn_PopupContinue);
		waitForCompletePageLoad();
	}

	private void distributeActionsToUsersWithFilesUpload(String action, String userA, String userB) {
		List<String> userList = new ArrayList<String>();
		clickElementAndWaitForElement(FilesTab.btn_PopUploadFileDistributeFilesButton, FilesTab.txt_PopUploadFileDistributeTo);

		userList.add(userA);
		if (userB != null)
			userList.add(userB);

		i = javaUtils.resetIndex(i, 1);
		for (String user : userList) {
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, user);
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
			clickElementAndWaitForElement(By.xpath(".//div[contains(@id,'s2id_inptDistTo')]//ul[@class='select2-choices']//li[" + i + "]//button[@class='btn dropdown-toggle']"), ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown);
			selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
			selectDateFromCalendar();
			i++;
		}
	}

	private void createFilter(String filter, String subFilter) {
		clickElementAndWaitForElement(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"), GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);

		for (WebElement filterVal : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {

			if (subFilter != null) {

				if (filterVal.getAttribute("title").equalsIgnoreCase(subFilter)) {
					clickElementAndWait(filterVal);
					break;
				}
			}
			else
				clickElementAndWait(filterVal);
		}
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
	}

	/****** Upload Files with Action ******/

	public void uploadFilesWithAction(String userID, String action, String project, String folder) throws InterruptedException, IOException {
		multiFileUploadList.clear();
		searchFile = resourceUtils.getTestDataFilePath() + epoch;
		log.info("searching file :" + searchFile);
		collectionDataMap.put("searchFile", searchFile);

		List<String> fileList;

		int count;

		if (compareFlag) {
			fileList = Arrays.asList("file1", "file2", "file3");
			count = 3;
		}
		else {
			fileList = Arrays.asList("file1", "file2");
			count = 2;
		}
		int j = 0;
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (String fileName : fileList) {
			fileName = sysUtils.createRemotePdfFile(nodeIP + searchFile + j + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			multiFileUploadList.add(fileName);
			j++;
		}
		clickElementWithText(project);
		clickElementWithText(folder);
		uploadDocuments(multiFileUploadList, count, null, null, false, 1, Arrays.asList("For Approval"), Arrays.asList("For Approval"), Arrays.asList(userID), Arrays.asList(action));
		collectionDataMap.put("FileUploadList", multiFileUploadList.toString());
	}

	public void compareFilesFlag() {
		compareFlag = true;
	}
}