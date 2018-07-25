package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HtmlUploadWithCustomAttributeFilesScripts extends AdoddleCommonAppMethods {

	private String				parentWindow, createFile1, createFile2, attachFile1, attachFile2;
	final private Map<String, String>	uploadCustomFileList1		= new HashMap<String, String>();
	final private Map<String, String>	uploadCustomFileList2		= new HashMap<String, String>();
	final private Map<String, String>	uploadCustomFileListValue1	= new HashMap<String, String>();
	final private Map<String, String>	uploadCustomFileListValue2	= new HashMap<String, String>();
	final private List<String>		expectedCitiesGujarat		= Arrays.asList("Ahmedabad", "Surat");
	final private List<String>		expectedCitiesMaharastra	= Arrays.asList("Mumbai", "Pune");
	final private List<String>		expectedCitiesRajasthan		= Arrays.asList("Jaipur", "Udaipur");
	private int					filesIncompleteActions1;
	final private List<String>		multiSelectOptions			= Arrays.asList("Option1", "Option3");
	final private List<String>		statesListTest				= Arrays.asList("Gujarat", "Maharashtra", "Rajastan");
	final private static Logger	log							= AutomationLogger.getInstance().getLogger(HtmlUploadWithCustomAttributeFilesScripts.class);

	public void clickOnAddFiles() {
		clickElementAndWaitForElement(FilesTab.lbl_FilesListingForFilesTab, FilesTab.btn_AddFiles);
		clickElementAndWait(FilesTab.btn_AddFiles);
	}

	public void clickOnSelectFilesAndUpload() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile1 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION, nodeIP).trim();
		createFile2 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION, nodeIP).trim();
		List<String> fileList = sysUtils.getFileList(createFile1 + "," + createFile2);
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
		collectionDataMap.put("Upload File List", fileList.toString());
	}

	public void bulkApplyCheckboxSelect() {
		if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply))
			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
	}

	public void verifyHeaderPanel() {
		AutomationAssert.verifyTrue(isDisplayed(FilesTab.ele_PopUploadHeaderPanel));
	}

	private void customRadioSelect() {
			if (!isSelected(FilesTab.rad_PopUploadHeaderRadioYes))
				clickElementAndWait(FilesTab.rad_PopUploadHeaderRadioYes);
	}

	private void verifyHierarchicalCategory() {
		List<String> categoryItemList = new ArrayList<String>();
		List<WebElement> citiesList;
		List<WebElement> statesList = findElements(FilesTab.css_CustomSelectStatesOptions);

		log.info("expectedCitiesGujarat :" + expectedCitiesGujarat);
		log.info("expectedCitiesMaharastra :" + expectedCitiesMaharastra);
		log.info("expectedCitiesRajasthan :" + expectedCitiesRajasthan);

		for (WebElement selectStates : statesList) {
			selectByVisibleText(FilesTab.drp_PopUploadHeaderStates, selectStates.getText());
			citiesList = findElements(FilesTab.css_CustomSelectCitiesOptions);

			for (String state : statesListTest) {
				if (selectStates.getText().equalsIgnoreCase(state)) {
					for (WebElement selectCities : citiesList)
						categoryItemList.add(selectCities.getText().trim());
					categoryItemList.remove(0);
					log.info("categoryItemList :" + categoryItemList);
					try {
						Assert.assertEquals("Expected Gujarat Cities :" + expectedCitiesGujarat, categoryItemList, expectedCitiesGujarat);
					}
					catch (Throwable t) {
						try {
							Assert.assertEquals("Expected Maharastra Cities :" + expectedCitiesMaharastra, categoryItemList, expectedCitiesMaharastra);
						}
						catch (Throwable e) {
							Assert.assertEquals("Expected Rajasthan Cities :" + expectedCitiesRajasthan, categoryItemList, expectedCitiesRajasthan);
						}
					}
					categoryItemList.clear();
				}
			}
		}
	}

	private void multiSelectionCheckboxDroupdownSelect() {
		clickElementAndWait(FilesTab.chk_MultiSelectionCheckbox);
		for (String checkboxSelect : multiSelectOptions) {
			if (!isSelected(By.xpath(".//th//div[contains(@class,'dropdownchecklist')]//label[text()='" + checkboxSelect.trim() + "']"))) {
				clickElementAndWait(By.xpath(".//th//div[contains(@class,'dropdownchecklist')]//label[text()='" + checkboxSelect.trim() + "']"));
			}
		}
	}

	private void selectCurrentDate() {
		clickElementAndWaitForElement(FilesTab.txt_DatePicker, FilesTab.lnk_DatePickerToday);
		clickElementAndWait(FilesTab.lnk_DatePickerToday);
	}

	public void enterCustomAttributes() throws IOException, InterruptedException {
		clickElementAndWait(FilesTab.btn_CopyFileName);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		List<WebElement> poiList = findElements(FilesTab.css_PopUploadFilesPurposeOfIssuesDropDowns);
		List<WebElement> statusList = findElements(FilesTab.css_PopUploadFilesStatusDropDowns);
		List<WebElement> integerBoxElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesIntegerTextBoxes);
		List<WebElement> emailBoxElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesEmailTextBoxes);
		List<WebElement> lettersElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesLetters);
		List<WebElement> coordinatesXList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesX);
		List<WebElement> coordinatesXXXList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesXXX);
		List<WebElement> coordinatesYYYList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesYYY);
		List<WebElement> coordinatesZZZList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesZZZ);
		List<WebElement> statesDropdowns = findElements(FilesTab.css_PopUploadCustomAttributesFilesStates);
		List<WebElement> citiesDropdowns = findElements(FilesTab.css_PopUploadCustomAttributesFilesCities);
		List<WebElement> decimalTexBoxes = findElements(FilesTab.css_PopUploadCustomAttributesFilesDecimalBoxes);
		List<WebElement> lettersNumbersTexBoxes = findElements(FilesTab.css_PopUploadCustomAttributesFilesLettersNumbersBoxes);
		List<WebElement> coordinatesXXList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesXX);
		List<WebElement> coordinatesYYList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesYY);

		for (WebElement e : poiList)
			selectByIndex(e, 1);

		for (WebElement e : statusList)
			selectByIndex(e, 1);

		for (WebElement e : integerBoxElements) {
			if (e.isEnabled())
				sendKeys(e, JavaUtils.getRandomNumber(3));
		}

		for (WebElement e : emailBoxElements) {
			e.clear();
			waitForCompletePageLoad();
			e.sendKeys(javaUtils.getRandomString(4) + AdoddleCommonStringPool.AT_STRING + javaUtils.getRandomString(4) + AdoddleCommonStringPool.DOT_STRING + javaUtils.getRandomString(3));
		}

		for (WebElement e : lettersElements)
			sendKeys(e, javaUtils.getRandomString(4));

		customRadioSelect();

		for (WebElement e : coordinatesXList)
			sendKeys(e, JavaUtils.getRandomNumber(1));

		for (WebElement e : coordinatesXXXList)
			sendKeys(e, JavaUtils.getRandomNumber(3));

		for (WebElement e : coordinatesYYYList)
			sendKeys(e, JavaUtils.getRandomNumber(3));

		for (WebElement e : coordinatesZZZList)
			sendKeys(e, JavaUtils.getRandomNumber(3));

		verifyHierarchicalCategory();

		index = javaUtils.resetIndex(index, 1);
		for (WebElement e : statesDropdowns)
			selectByIndex(e, index);
		index = javaUtils.resetIndex(index, 1);

		for (WebElement e : citiesDropdowns)
			selectByIndex(e, index);

		attachExternalFiles();

		for (WebElement d : decimalTexBoxes)
			sendKeys(d, JavaUtils.getRandomNumber(3) + AdoddleCommonStringPool.DOT_STRING + JavaUtils.getRandomNumber(2));

		for (WebElement l : lettersNumbersTexBoxes) {
			if (l.isEnabled())
				sendKeys(l, javaUtils.getRandomString(4) + JavaUtils.getRandomNumber(3));
		}

		multiSelectionCheckboxDroupdownSelect();

		selectCurrentDate();

		for (WebElement cx : coordinatesXXList)
			sendKeys(cx, JavaUtils.getRandomNumber(2));

		for (WebElement cy : coordinatesYYList)
			sendKeys(cy, JavaUtils.getRandomNumber(2));
	}

	public void clickOnApplytoAll() {
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void getCustomFilesAttributesValue() {
		uploadCustomFileList1.put("filename", getElementText(FilesTab.lbl_UploadFileName1));
		uploadCustomFileList1.put("rev", "1");
		uploadCustomFileList1.put("doctitle", findElement(FilesTab.txt_DocTitleFile1).getAttribute("value"));

		uploadCustomFileList1.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile1)).getFirstSelectedOption().getText());
		uploadCustomFileList1.put("status", new Select(findElement(FilesTab.drp_StatusFile1)).getFirstSelectedOption().getText());

		uploadCustomFileList1.put("integertextbox", findElement(FilesTab.txt_IntegerTextboxFile1).getAttribute("value"));
		uploadCustomFileList1.put("emailtextbox", findElement(FilesTab.txt_UploadFileFirstEmailTextbox).getAttribute("value"));
		uploadCustomFileList1.put("letter", findElement(FilesTab.txt_UploadFileFirstLetterTextbox).getAttribute("value"));
		uploadCustomFileList1.put("radio", "Yes");
		uploadCustomFileList1.put("coordinates1dimensionx", findElement(FilesTab.txt_Coordinates1DimensionXFile1).getAttribute("value"));
		uploadCustomFileList1.put("coordinates3dimensionx", findElement(FilesTab.txt_Coordinates3DimensionXFile1).getAttribute("value"));
		uploadCustomFileList1.put("coordinates3dimensiony", findElement(FilesTab.txt_Coordinates3DimensionYFile1).getAttribute("value"));
		uploadCustomFileList1.put("coordinates3dimensionz", findElement(FilesTab.txt_Coordinates3DimensionZFile1).getAttribute("value"));

		uploadCustomFileList1.put("states", new Select(findElement(FilesTab.drp_StatesFile1)).getFirstSelectedOption().getText().trim() + "(1)");
		uploadCustomFileList1.put("cities", new Select(findElement(FilesTab.drp_CitiesFile1)).getFirstSelectedOption().getText().trim() + "(1)");

		uploadCustomFileList1.put("decimaltextbox", findElement(FilesTab.txt_DecimalTextboxFile1).getAttribute("value"));
		uploadCustomFileList1.put("lettersnumberstextbox", findElement(FilesTab.txt_LettersNumbersTextboxFile1).getAttribute("value"));
		uploadCustomFileList1.put("multiselectioncheckbox", getElementText(FilesTab.sel_MultiSelectionCheckboxFile1).replace(", ", ","));
		uploadCustomFileList1.put("datepicker", findElement(FilesTab.txt_DatePickerFile1).getAttribute("value"));
		uploadCustomFileList1.put("coordinates2dimensionx", findElement(FilesTab.txt_Coordinates2DimensionXFile1).getAttribute("value"));
		uploadCustomFileList1.put("Coordinates2dimensiony", findElement(FilesTab.txt_Coordinates2DimensionYFile1).getAttribute("value"));

		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		log.info("uploadCustomFileList1 :" + uploadCustomFileList1);

		uploadCustomFileList2.put("filename", getElementText(FilesTab.lbl_UploadFileName2));
		uploadCustomFileList2.put("rev", "1");
		uploadCustomFileList2.put("doctitle", findElement(FilesTab.txt_DocTitleFile2).getAttribute("value"));

		uploadCustomFileList2.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile2)).getFirstSelectedOption().getText());
		uploadCustomFileList2.put("status", new Select(findElement(FilesTab.drp_StatusFile2)).getFirstSelectedOption().getText());

		uploadCustomFileList2.put("integertextbox", findElement(FilesTab.txt_IntegerTextboxFile2).getAttribute("value"));
		uploadCustomFileList2.put("emailtextbox", findElement(FilesTab.txt_UploadFileSecondEmailTextbox).getAttribute("value"));
		uploadCustomFileList2.put("letter", findElement(FilesTab.txt_UploadFileSecondLetterTextbox).getAttribute("value"));

		uploadCustomFileList2.put("radio", "Yes");
		uploadCustomFileList2.put("coordinates1dimensionx", findElement(FilesTab.txt_Coordinates1DimensionXFile2).getAttribute("value"));
		uploadCustomFileList2.put("coordinates3dimensionx", findElement(FilesTab.txt_Coordinates3DimensionXFile2).getAttribute("value"));
		uploadCustomFileList2.put("coordinates3dimensiony", findElement(FilesTab.txt_Coordinates3DimensionYFile2).getAttribute("value"));
		uploadCustomFileList2.put("coordinates3dimensionz", findElement(FilesTab.txt_Coordinates3DimensionZFile2).getAttribute("value"));

		uploadCustomFileList2.put("states", new Select(findElement(FilesTab.drp_StatesFile2)).getFirstSelectedOption().getText() + "(1)");
		uploadCustomFileList2.put("cities", new Select(findElement(FilesTab.drp_CitiesFile2)).getFirstSelectedOption().getText() + "(1)");

		uploadCustomFileList2.put("decimaltextbox", findElement(FilesTab.txt_DecimalTextboxFile2).getAttribute("value"));
		uploadCustomFileList2.put("lettersnumberstextbox", findElement(FilesTab.txt_LettersNumbersTextboxFile2).getAttribute("value"));
		uploadCustomFileList2.put("multiselectioncheckbox", getElementText(FilesTab.sel_MultiSelectionCheckboxFile2).replace(", ", ","));
		uploadCustomFileList2.put("datepicker", findElement(FilesTab.txt_DatePickerFile2).getAttribute("value"));
		uploadCustomFileList2.put("coordinates2dimensionx", findElement(FilesTab.txt_Coordinates2DimensionXFile2).getAttribute("value"));
		uploadCustomFileList2.put("Coordinates2dimensiony", findElement(FilesTab.txt_Coordinates2DimensionYFile2).getAttribute("value"));

		log.info("uploadCustomFileList2 :" + uploadCustomFileList2);
	}

	public void clickOnDistributeFiles(String DistributeFiles) {
		clickElementAndWait(By.xpath(".//*[@id='adv-btn-distribute' and text()='" + DistributeFiles.trim() + "']"));
	}

	public void verifyDistributeTextboxes(String To, String Subject) {
		AutomationAssert.verifyTrue(isDisplayed(By.xpath(".//div[@style='display: block;']//label[text()='" + To.trim() + "']")));
		AutomationAssert.verifyTrue(isDisplayed(By.xpath(".//div[@style='display: block;']//label[text()='" + Subject.trim() + "']")));
	}

	public void verifyUploadFileActionAndAttributesValue() {
		String[] uploadFile = (strUtils.extractFileNameString(createFile1) + "," + strUtils.extractFileNameString(createFile2)).split(",");
		for (String file : uploadFile) {
			waitForCompletePageLoad();
			searchFiles(file);
			verifyAttachedFile();
			parentWindow = clickElementAndSwitchWindow(FilesTab.lnk_FileName);
			waitForCompletePageLoad();
			if (fileBetaViewFlag) {
				clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileDetails);
				clickElementAndWait(FilesTab.btn_BetaFileViewFileDetails);
				if (file.equalsIgnoreCase(uploadFile[0])) {
					getFileAttributes(uploadCustomFileListValue1);
					log.info("uploadCustomFileListValue1 :" + uploadCustomFileListValue1);
				}
				else {
					getFileAttributes(uploadCustomFileListValue2);
					log.info("uploadCustomFileListValue2 :" + uploadCustomFileListValue2);
				}

				closeFileOpenedWindow();
			}
			else {
				clickElementAndWait(FilesTab.lnk_FileViewerHeaderShowMore);

				if (file.equalsIgnoreCase(uploadFile[0])) {
					getFileAttributes(uploadCustomFileListValue1);
					log.info("uploadCustomFileListValue1 :" + uploadCustomFileListValue1);
				}
				else {
					getFileAttributes(uploadCustomFileListValue2);
					log.info("uploadCustomFileListValue2 :" + uploadCustomFileListValue2);
				}

				closeFileOpenedWindow();
			}
		}
	}

	public void verifyFilesAndAttributes() {
		mapListStore(uploadCustomFileListValue1);
		mapListStore(uploadCustomFileListValue2);
		javaUtils.compareMapList(uploadCustomFileList1, uploadCustomFileListValue1);
		javaUtils.compareMapList(uploadCustomFileList2, uploadCustomFileListValue2);
	}

	private void getFileAttributes(Map<String, String> mapList) {
		if (fileBetaViewFlag) {
			mapList.put("doctitle", getElementText(FilesTab.lbl_FileBetaViewCustomDocTitle));
			mapList.put("status", getElementText(FilesTab.lbl_FileBetaViewCustomStatus));
			mapList.put("rev", getElementText(FilesTab.lbl_FileBetaViewCustomRev));
			mapList.put("poi", getElementText(FilesTab.lbl_FileBetaViewCustomPoi));
			mapList.put("filename", getElementText(FilesTab.lbl_FileBetaViewCustomFileName));
			log.info("mapList-1 : " + mapList);
		}
		else {
			mapList.put("doctitle", getElementText(FilesTab.lbl_CustomDocTitle).split(": ")[1]);
			mapList.put("status", getElementText(FilesTab.lbl_CustomStatus).split(": ")[1]);
			mapList.put("rev", getElementText(FilesTab.lbl_CustomRev).split(": ")[1]);
			mapList.put("poi", getElementText(FilesTab.lbl_CustomPoi).split(": ")[1]);
			mapList.put("filename", getElementText(FilesTab.lbl_CustomFileName).split(": ")[1]);
			log.info("mapList-1 : " + mapList);
		}

		List<String> customAttributesKeys = Arrays.asList("integertextbox", "decimaltextbox", "emailtextbox", "lettersnumberstextbox", "letter", "radio", "datepicker", "states", "cities", "coordinates1dimension");

		for (String attribute : customAttributesKeys) {

			if (fileBetaViewFlag) {
				for (WebElement uiAttribute : findElements(FilesTab.css_BetaViewFileCustomAttributesList)) {

					if (!uiAttribute.getText().contains(",") || uiAttribute.getText().toLowerCase().contains(customAttributesKeys.get(6))) {

						if (uiAttribute.getText().toLowerCase().contains(attribute)) {

							if (attribute.contains("coordinates1dimension"))
								mapList.put(attribute + "x", uiAttribute.getText().split(" ")[1].split(",")[0]);
							else
								mapList.put(attribute, uiAttribute.findElement(By.tagName("td")).getText().replace(" (", "("));

							break;
						}
					}
				}
			}
			else {
				for (WebElement uiAttribute : findElements(FilesTab.css_ViewFileCustomAttributesList)) {

					if (!uiAttribute.getText().contains(",") || uiAttribute.getText().toLowerCase().contains(customAttributesKeys.get(6))) {

						if (uiAttribute.getText().split(": ")[0].toLowerCase().equalsIgnoreCase(attribute)) {

							if (attribute.contains("coordinates1dimension"))
								mapList.put(attribute + "x", uiAttribute.getText().split(": ")[1]);
							else
								mapList.put(attribute, uiAttribute.getText().replace(" (", "(").split(": ")[1]);
							break;
						}
					}

				}
			}
		}
		log.info("mapList-2 : " + mapList);

		if (fileBetaViewFlag) {
			for (WebElement attribute : findElements(FilesTab.css_BetaViewFileCustomAttributesList)) {
				if (attribute.getText().contains(",")) {

					if ((StringUtils.countMatches(attribute.getText(), ",") == 1) && attribute.getText().contains("Option")) {
						mapList.put("multiselectioncheckbox", attribute.getText().split(" ")[1]);

					}
					else if ((StringUtils.countMatches(attribute.getText(), ",") == 1) && !attribute.getText().contains("Date")) {
						mapList.put("coordinates2dimensionx", attribute.getText().split(" ")[1].split(",")[0]);
						mapList.put("Coordinates2dimensiony", attribute.getText().split(",")[1]);

					}
					else if (StringUtils.countMatches(attribute.getText(), ",") == 2) {
						mapList.put("coordinates3dimensionx", attribute.getText().split(" ")[1].split(",")[0]);
						mapList.put("coordinates3dimensiony", attribute.getText().split(",")[1]);
						mapList.put("coordinates3dimensionz", attribute.getText().split(",")[2]);
					}
				}
			}
		}
		else {
			for (WebElement attribute : findElements(FilesTab.css_ViewFileCustomAttributesList)) {
				if (attribute.getText().contains(",")) {

					if ((StringUtils.countMatches(attribute.getText(), ",") == 1) && attribute.getText().contains("Option")) {
						mapList.put("multiselectioncheckbox", attribute.getText().split(": ")[1]);

					}
					else if ((StringUtils.countMatches(attribute.getText(), ",") == 1) && !attribute.getText().contains("Option")) {
						mapList.put("coordinates2dimensionx", attribute.getText().split(": ")[1].split(",")[0]);
						mapList.put("Coordinates2dimensiony", attribute.getText().split(": ")[1].split(",")[1]);

					}
					else if (StringUtils.countMatches(attribute.getText(), ",") == 2) {
						mapList.put("coordinates3dimensionx", attribute.getText().split(": ")[1].split(",")[0]);
						mapList.put("coordinates3dimensiony", attribute.getText().split(": ")[1].split(",")[1]);
						mapList.put("coordinates3dimensionz", attribute.getText().split(": ")[1].split(",")[2]);
					}
				}
			}
		}
		log.info("mapList-3 : " + mapList);
	}

	public void deleteCreatedFiles() {
		try {
			deactivateFile(createFile1.split("\\\\")[6]);
			deactivateFile(createFile2.split("\\\\")[6]);
			sysUtils.deleteFile(createFile1);
			sysUtils.deleteFile(createFile2);
			sysUtils.deleteFile(attachFile1);
			sysUtils.deleteFile(attachFile2);
		}
		catch (Throwable t) {
			log.info("error while deactivating or deleting physical files");
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

	private void closeFileOpenedWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void enterDistributeGroupAndAction() {
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, System.getProperty("primary.username"));
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
		clickElementAndWait(FilesTab.lnk_DistributeGroupText);
		selectByVisibleText(FilesTab.sel_Action, AdoddleCommonStringPool.FOR_INFORMATION);
		clickElement(FilesTab.btn_LstClose);
	}

	public void performDistributionAction(String distributeTo) {
		String actionArray[] = { AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT, AdoddleCommonStringPool.ACTION_FOR_ACTION, AdoddleCommonStringPool.ACTION_FOR_COMMENT, AdoddleCommonStringPool.ACTION_FOR_COMMENT_COORDINATION, AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION, AdoddleCommonStringPool.FOR_INFORMATION, AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE };
		String testSubjectForDistribution = javaUtils.getRandomString(10) + dateUtils.getEpoch();

		int j = 0;
		for (int i = 1; i <= actionArray.length; i++) {
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, distributeTo);
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
			clickElementAndWait(By.xpath(".//div[@id='s2id_inptDistTo']//ul/li[" + i + "]"));
			selectByVisibleText(FilesTab.drp_ActionList, actionArray[j]);
			clickElementAndWait(FilesTab.lnk_CurrentDate);
			j++;
		}
		sendKeys(FilesTab.txt_DistributeSubjectName, testSubjectForDistribution);
	}

	public void getFilesTabIncompleteActionCount() {
		filesIncompleteActions1 = getIncompleteActionCount();
		log.info("filesIncompleteActions1 :" + filesIncompleteActions1);
	}

	public void verifyIncompleteActionCount() {
		navigateTab(LandingPage.lnk_Files);
		int filesIncompleteActions2 = getIncompleteActionCount();
		clickElementAndWait(FilesTab.lbl_FilesListingForFilesTab);
		AutomationAssert.verifyTrue("filesIncompleteActions1 :" + filesIncompleteActions1 + "\tfilesIncompleteActions2 :" + filesIncompleteActions2, filesIncompleteActions1 < filesIncompleteActions2);
	}

	private int getIncompleteActionCount() {
		return Integer.parseInt(getToolTipText(FilesTab.lbl_FilesIncompleteActionsCount).split(":")[1].trim());
	}

	private void attachExternalFiles() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		attachFile1 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + ".txt").trim();
		attachFile2 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + ".txt").trim();
		collectionDataMap.put("Attachment File1", attachFile1);
		collectionDataMap.put("Attachment File2", attachFile2);
		List<String> fileList1 = sysUtils.getFileList(attachFile1);
		List<String> fileList2 = sysUtils.getFileList(attachFile2);
		uploadFileUsingKeys(FilesTab.btn_PopUploadFirstAttachFile, fileList1);
		uploadFileUsingKeys(FilesTab.btn_PopUploadSecondAttachFile, fileList2);
	}

	private void verifyAttachedFile() {
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.img_FileAttachment, true), isDisplayed(FilesTab.img_FileAttachment));
	}
}
