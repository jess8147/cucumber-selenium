/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateEditPOIScripts extends AdoddleCommonAppMethods {

	private int poiCounter;
	public static String createFile1, createFile2, testDataFolder;
	private String projectName, poi;
	final private String poiTextList[] = { "POI_Access_Admin", "POI_Access_to_USE_Attribute_Change",
			"POI_Access_to_USE_Publish", "POI_No_Access" };
	final private String poiText[] = { AdoddleCommonStringPool.ADMIN,
			AdoddleCommonStringPool.ACCESS_TO_USE_ATTRIBUTES_CHANGE, AdoddleCommonStringPool.ACCESS_TO_USE_PUBLISH,
			AdoddleCommonStringPool.NO_ACCESS };
	private List<WebElement> poiWebList;
	private List<WebElement> poiElementList = new ArrayList<WebElement>();
	final private List<String> uploadFilesPoiList = new ArrayList<String>();
	final private List<String> uploadedFilesPoiList = new ArrayList<String>();
	final private List<String> poiElementStringList = new ArrayList<String>();
	final private static Logger log = AutomationLogger.getInstance().getLogger(CreateEditPOIScripts.class);

	public void createPOIFolder(String project) {
		testDataFolder = javaUtils.getRandomString(10) + AdoddleCommonStringPool.UNDERSCORE_STRING + epoch;
		collectionDataMap.put("Test Data Folder", testDataFolder);
		createProjectFolderMoreOptions(project, testDataFolder);
	}

	public void setScriptData(String project, String purposeOfIssue) {
		projectName = project;
		poi = purposeOfIssue;
	}

	public void createPurposeOfIssue() {
		clickElementAndWait(ProjectsTab.btn_AddNewPurposeOfIssue);
		clickElementAndWait(ProjectsTab.btn_AddNewPurposeOfIssue);
		clickElementAndWait(ProjectsTab.btn_AddNewPurposeOfIssue);
		clickElementAndWait(ProjectsTab.btn_AddNewPurposeOfIssue);

		poiWebList = findElements(ProjectsTab.css_AddPOIAndStatusTextList);
		List<WebElement> userCloseList = findElements(ProjectsTab.css_CloseAssignedUserList);
		List<WebElement> defaultUserList = findElements(ProjectsTab.css_DefaultUserList);

		poiCounter = javaUtils.resetIndex(poiCounter, 0);

		for (String poi : poiTextList) {
			poiWebList.get(poiCounter).sendKeys(poi + epoch);
			if (poi.contains("POI_Access_Admin") || poi.contains("POI_Access_to_USE_Publish")) {
				uploadFilesPoiList.add(poi + epoch);
			} else if (poi.contains("POI_Access_to_USE_Attribute_Change")) {
				uploadedFilesPoiList.add(poi + epoch);
			} else {
				log.info("poi :" + poi);
			}
			userCloseList.get(poiCounter).click();
			defaultUserList.get(poiCounter).click();
			waitUntilElementIsDisplayed(ProjectsTab.opt_PopManagePOIRoleAdmin);
			clickElement(ProjectsTab.opt_PopManagePOIRoleAdmin);
			poiCounter++;
		}

		collectionDataMap.put("Created POI List1", uploadFilesPoiList.toString());
		collectionDataMap.put("Created POI List2", uploadedFilesPoiList.toString());
		log.info("uploadFilesPoiList :" + uploadFilesPoiList);
		log.info("uploadedFilesPoiList :" + uploadedFilesPoiList);

		poiWebList = findElements(ProjectsTab.css_AddUserTextList);
		for (WebElement e : poiWebList) {
			e.clear();
			e.sendKeys(System.getProperty("primary.username"));
			e.sendKeys(Keys.ENTER);
		}

		poiWebList = findElements(ProjectsTab.css_PrimaryUserList);

		poiCounter = javaUtils.resetIndex(poiCounter, 0);

		for (String poi : poiText) {
			clickElementAndWait(ProjectsTab.txt_SearchPOIAndStatusFilter);
			poiWebList.get(poiCounter).click();
			waitUntilElementIsDisplayed(ProjectsTab.btn_ContextClickMenuAdmin);
			if (poi.equalsIgnoreCase("Admin"))
				clickElementAndWait(ProjectsTab.opt_PopManagePOIRoleAdmin);
			else if (poi.equalsIgnoreCase("Access to Use - Publish"))
				clickElementAndWait(ProjectsTab.opt_PopManagePOIRolePublish);
			else if (poi.equalsIgnoreCase("Access to Use - Attributes Change"))
				clickElementAndWait(ProjectsTab.opt_PopManagePOIRoleAttributeChange);
			else if (poi.equalsIgnoreCase("No Access"))
				clickElementAndWait(ProjectsTab.opt_PopManagePOIRoleNoAccess);

			poiCounter++;
		}
	}

	public void clickOnSave() {
		clickElementAndWaitUntilElementInvisible(ProjectsTab.btn_PopManageFormStatusesSave,
				ProjectsTab.btn_PopManageFormStatusesSave);
	}

	public void clickOnProjectAndFolderAndAddFiles(String projectName, String folderName) {
		clickElementWithText(projectName.trim());
		if (folderName.equalsIgnoreCase("TestDataFolder"))
			clickElementWithText(testDataFolder);
		else
			clickElementWithText(folderName);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);

	}

	public void clickOnSelectFilesAndUpload() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile1 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		createFile2 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("first file created as: " + createFile1);
		log.info("second file created as :" + createFile2);
		List<String> fileList = sysUtils.getFileList(createFile1 + "," + createFile2);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
		collectionDataMap.put("Uploaded FileList", fileList.toString());
	}

	public void bulkApplyCheckboxSelect() {
		if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply)) {
			log.info("checking unchecked box");
			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		}
	}

	public void bulkApplyCheckboxEditAttributes() {
		if (!isSelected(FilesTab.chk_PopEditAttributesHeaderBulkApply)) {
			log.info("checking unchecked box");
			clickElementAndWait(FilesTab.chk_PopEditAttributesHeaderBulkApply);
		}
	}

	public void verifyAccessablePOI() {
		log.info("uploadFilesPoiList::::" + uploadFilesPoiList);
		clickElementAndWait(FilesTab.drp_PopUploadHeaderPurposeOfIssue);
		poiElementList = findElements(FilesTab.css_UploadFilesPurposeOfIssueList);
		for (WebElement e : poiElementList)
			poiElementStringList.add(e.getText());
		compareWebStringList(uploadFilesPoiList, poiElementStringList);
	}

	public void enterMendatoryAttributes() {
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByVisibleText(FilesTab.drp_PopUploadHeaderPurposeOfIssue, poiTextList[2] + epoch);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void clickOnUpload() {
		waitUntilElementIsDisplayed(FilesTab.btn_PopUploadFileUpload);
		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		if (isDisplayed(FilesTab.pop_Handle) || isDisplayed(FilesTab.pop_PublishDocuments))
			clickElementAndWait(FilesTab.btn_PopupContinue);
		else if (isDisplayed(FilesTab.pop_PublishIFCDocuments))
			clickElementAndWait(FilesTab.btn_PopPublishIFCDocContinue);

		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
	}

	public void verifyUploadedFilesAndPOI(String poi) {
		List<String> uploadFile = Arrays.asList(strUtils.extractFileNameString(createFile1), strUtils.extractFileNameString(createFile2));
		reloadPage();
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(projectName);
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		clickElementWithText(testDataFolder);
		waitUntilElementIsClickable(FilesTab.lnk_FileName);

		for (String file : uploadFile) {
			searchFiles(file);
			Assert.assertTrue("Expected# " + getElementText(FilesTab.ele_FilesTabFirstFilePOI) + "\nActual# "
					+ poi, getElementText(FilesTab.ele_FilesTabFirstFilePOI).contains(poi));
		}
		searchFiles("");
	}

	public void selectMultipleFilesCheckboxSelect() {
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox)) {
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		}
	}

	public void clickOnFilesAndEditAndAttributes(String edit, String attributes) {
		contextClick(FilesTab.lnk_FileName);
		/*clickContextMenuOptionWithText(edit);
		clickContextMenuOptionWithText(attributes);*/
		clickContextMenuOption(edit, attributes);
	}

	public void verifyChangeablePOI() {
		uploadedFilesPoiList.addAll(uploadFilesPoiList);
		clickElementAndWait(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue);
		poiElementList = findElements(FilesTab.css_EditAttributesPurposeOfIssueDropdownList);
		for (WebElement e : poiElementList)
			poiElementStringList.add(e.getText());
		compareWebStringList(uploadedFilesPoiList, poiElementStringList);

	}

	public void changePOIAndClickOnAssignAttributes(String poi, String assignAttributes) {
		selectByIndex(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, 0);
		clickElementAndWait(FilesTab.lbl_PurposeOfIssueChevronDown);
		selectByVisibleText(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, poi + epoch);
		clickElementAndWait(FilesTab.lbl_PurposeOfIssueChevronDown);
		clickButtonWithText(assignAttributes);
		clickElementAndWait(FilesTab.btn_PopupConfirmUIContinue);
	}

	public void deactivatePOI() throws InterruptedException {
		selectProjectEditOption(projectName, poi);
		// for(String poi: Arrays.asList(poiTextList)) {
		sendKeys(ProjectsTab.txt_SearchPOIAndStatusFilter, epoch);
		/* sendKeys(ProjectsTab.txt_SearchPOIAndStatusFilter, poi); */
		poiWebList = findElements(ProjectsTab.css_AllPOIAndStatusCheckList);
		for (WebElement poiCheckBox : poiWebList) {
			if (!poiCheckBox.isSelected()) {
				poiCheckBox.click();
			}
		}
		// }
		clickOnSave();
	}

	public void verifyPurposeOfIssueDeactivate(String poiStatusFolder) {
		clickElementWithText(projectName);
		clickElementWithText(poiStatusFolder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);
		clickOnSelectFilesAndUpload();
		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWait(FilesTab.drp_PopUploadHeaderPurposeOfIssue);

		poiElementList = findElements(FilesTab.css_UploadFilesPurposeOfIssueList);
		verifyDeactivatePOI(poiElementList);

		clickElementAndWait(FilesTab.drp_PurposeOfIssueFile1);
		poiElementList = findElements(FilesTab.css_UploadFilesPurposeOfIssueFileList1);
		verifyDeactivatePOI(poiElementList);

		clickElementAndWait(FilesTab.drp_PurposeOfIssueFile2);
		poiElementList = findElements(FilesTab.css_UploadFilesPurposeOfIssueFileList2);
		verifyDeactivatePOI(poiElementList);
	}

	private void verifyDeactivatePOI(List<WebElement> list) {
		for (WebElement poiWeb : list) {
			for (String poiValue : poiTextList) {
				if (!poiWeb.getText().contains(poiValue + epoch))
					log.info(": poi Successfully deactivated :");
				else {
					log.error("poiWeb :" + poiWeb.getText());
					Assert.assertTrue(false);
				}
			}
		}
	}

	private void compareWebStringList(List<String> stringList, List<String> poiStringList) {
		boolean flag = false;
		for (String valueList2 : poiStringList) {
			for (String valueList1 : stringList) {
				if (valueList2.equalsIgnoreCase(valueList1)) {
					flag = true;
					break;
				} else {
					log.info("not Verified");
					flag = false;
				}
			}
			if (!flag) {
				if (valueList2.contains(epoch)) {
					Assert.assertTrue(valueList2 + " should not contain " + epoch, false);
				} else {
					log.info("skip poi :" + valueList2);
				}
			}
		}
	}

	public void deactivateAllDocuments() {
		try {
			deactivateFile(testDataFolder);
		} catch (Throwable t) {
			log.info("Failure while deactivating files");
		}
	}
}
