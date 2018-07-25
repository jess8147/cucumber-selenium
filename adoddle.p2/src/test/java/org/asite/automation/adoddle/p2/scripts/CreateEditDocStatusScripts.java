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
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateEditDocStatusScripts extends AdoddleCommonAppMethods {

	private int counter;
	final private String statusTextList[] = { AdoddleCommonStringPool.DOC_STATUS_ACCESS_ADMIN,
			AdoddleCommonStringPool.DOC_STATUS_ACCESS_TO_USE_STATUS_CHANGE,
			AdoddleCommonStringPool.DOC_STATUS_ACCESS_TO_USE_PUBLISH, AdoddleCommonStringPool.DOC_STATUS_NO_ACCESS };
	final private String statusText[] = { AdoddleCommonStringPool.ADMIN, AdoddleCommonStringPool.ACCESS_TO_USE_STATUS_CHANGE,
			AdoddleCommonStringPool.ACCESS_TO_USE_PUBLISH, AdoddleCommonStringPool.NO_ACCESS };
	private List<WebElement> statusWebList, statusElementList;
	private List<String> uploadFilesStatusList, uploadedFilesStatusList;
	private String projectName, createFile1, createFile2;
	private static String testDataFolder;
	final private static Logger log = AutomationLogger.getInstance().getLogger(CreateEditDocStatusScripts.class);

	public void setScriptData(String project) {
		testDataFolder = CreateEditPOIScripts.testDataFolder;
		projectName = project;
	}

	public void createDocumentStatuses() {
		uploadFilesStatusList = new ArrayList<String>();
		uploadedFilesStatusList = new ArrayList<String>();

		clickElementAndWait(ProjectsTab.btn_AddNewStatus);
		clickElementAndWait(ProjectsTab.btn_AddNewStatus);
		clickElementAndWait(ProjectsTab.btn_AddNewStatus);
		clickElementAndWait(ProjectsTab.btn_AddNewStatus);

		statusWebList = findElements(ProjectsTab.css_AddPOIAndStatusTextList);
		List<WebElement> userCloseList = findElements(ProjectsTab.css_CloseAssignedUserList);
		List<WebElement> defaultUserList = findElements(ProjectsTab.css_DefaultUserList);
		List<WebElement> adminRoleList = findElements(ProjectsTab.css_adminRoleList);

		counter = javaUtils.resetIndex(counter, 0);

		for (String status : statusTextList) {
			statusWebList.get(counter).sendKeys(status + epoch);
			if (status.contains(AdoddleCommonStringPool.DOC_STATUS_ACCESS_ADMIN)
					|| status.contains(AdoddleCommonStringPool.DOC_STATUS_ACCESS_TO_USE_PUBLISH)) {
				uploadFilesStatusList.add(status + epoch);
			} else if (status.contains(AdoddleCommonStringPool.DOC_STATUS_ACCESS_TO_USE_STATUS_CHANGE)) {
				uploadedFilesStatusList.add(status + epoch);
			} else {
				log.info("status :" + status);
			}
			userCloseList.get(counter).click();
			defaultUserList.get(counter).click();
			waitUntilElementIsDisplayed(ProjectsTab.opt_PopFileStatusNewStatusRoleAdmin);
			clickElement(ProjectsTab.opt_PopFileStatusNewStatusRoleAdmin);
			waitUntilListElementIsClickable(adminRoleList.get(counter));
			adminRoleList.get(counter).click();
			waitUntilElementIsDisplayed(ProjectsTab.opt_PopFileStatusNewStatusRoleNoAccess);
			clickElement(ProjectsTab.opt_PopFileStatusNewStatusRoleNoAccess);
			counter++;
		}

		collectionDataMap.put("Created Status List1", uploadFilesStatusList.toString());
		collectionDataMap.put("Created Status List2", uploadedFilesStatusList.toString());
		log.info("uploadFilesStatusList :" + uploadFilesStatusList + "\nuploadedFilesStatusList :"
				+ uploadedFilesStatusList);

		statusWebList = findElements(ProjectsTab.css_AddUserTextList);
		for (WebElement e : statusWebList) {
			e.clear();
			e.sendKeys(System.getProperty("primary.username"));
			e.sendKeys(Keys.ENTER);
		}

		waitForCompletePageLoad();
		statusWebList = findElements(ProjectsTab.css_PrimaryUserList);
		counter = javaUtils.resetIndex(counter, 0);
		for (String status : statusText) {
			statusWebList.get(counter).click();
			waitUntilElementIsDisplayed(ProjectsTab.btn_ContextClickMenuAdmin);
			if (status.equalsIgnoreCase(AdoddleCommonStringPool.ADMIN))
				clickElementAndWait(ProjectsTab.opt_PopFileStatusNewStatusRoleAdmin);
			else if (status.equalsIgnoreCase(AdoddleCommonStringPool.ACCESS_TO_USE_PUBLISH))
				clickElementAndWait(ProjectsTab.opt_PopFileStatusNewStatusRolePublish);
			else if (status.equalsIgnoreCase(AdoddleCommonStringPool.ACCESS_TO_USE_STATUS_CHANGE))
				clickElementAndWait(ProjectsTab.opt_PopFileStatusNewStatusRoleStatusChange);
			else if (status.equalsIgnoreCase(AdoddleCommonStringPool.NO_ACCESS))
				clickElementAndWait(ProjectsTab.opt_PopFileStatusNewStatusRoleNoAccess);
			clickElementAndWait(ProjectsTab.txt_PopManageFormStatusesStatusNameFilter);
			counter++;
		}
	}

	public void setDocStatusFontAndColor(String status, String color, String font, String applyToRow) {

		if (status.equalsIgnoreCase(AdoddleCommonStringPool.DOC_STATUS_ACCESS_TO_USE_PUBLISH)
				|| status.equalsIgnoreCase(AdoddleCommonStringPool.DOC_STATUS_ACCESS_TO_USE_STATUS_CHANGE)) {

			sendKeys(ProjectsTab.txt_PopManageFormStatusesStatusNameFilter, status + epoch);
			waitUntilElementCountToBe(ProjectsTab.txt_PopManageDocFormStatusesFilteredStatuses, 1);
			List<WebElement> statusRows = findElements(ProjectsTab.css_PopManageDocFormStatusesRows);
			for (WebElement e : statusRows) {
				if (e.findElements(ProjectsTab.txt_PopManageDocFormStatusesFilteredStatuses).size() > 0) {
					e.findElement(ProjectsTab.lnk_PopManageDocFormFilteredStatusSettings).click();
					break;
				}
			}

		}

		clickElementAndWaitForElement(ProjectsTab.img_PopManageDocStatusSettingsBgColor,
				ProjectsTab.txt_PopBGColorPickerRedInput);

		if (color.equalsIgnoreCase("red")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		} else if (color.equalsIgnoreCase("green")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		}

		clickElementAndWait(ProjectsTab.btn_PopBGColorPickerOKButton);

		if (applyToRow.equalsIgnoreCase("yes"))
			clickElementAndWait(ProjectsTab.rad_DocStatusSettingsApplyToRecord);
		else if (applyToRow.equalsIgnoreCase("no"))
			clickElementAndWait(ProjectsTab.rad_DocStatusSettingsApplyToCell);

		clickElementAndWaitForElement(ProjectsTab.lnk_DocStatusSettingsFont, ProjectsTab.txt_DocStatusSettingsFontSearch);
		sendKeys(ProjectsTab.txt_DocStatusSettingsFontSearch, font);
		clickElementAndWaitForElement(ProjectsTab.lbl_DocStatusSettingsFontSearchResult,
				ProjectsTab.btn_DocStatusSettingsSave);
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_DocStatusSettingsSave, ProjectsTab.btn_DocStatusSettingsSave);

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

	public void verifyAccessableStatuses() {
		log.info("uploadFilesStatusList :" + uploadFilesStatusList);
		clickElementAndWait(FilesTab.drp_PopUploadHeaderStatus);
		statusElementList = findElements(FilesTab.css_UploadFilesStatusesList);
		compareWebStringList(uploadFilesStatusList, statusElementList);
	}

	public void enterMendatoryAttributes() {
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByVisibleText(FilesTab.drp_PopUploadHeaderStatus, statusTextList[2] + epoch);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void verifyUploadedFilesAndStatus(String status, String statusColor, String statusFont, String cellRecordFlag) {
		if (statusColor.equalsIgnoreCase("red"))
			statusColor = "rgb(255, 0, 0)";
		else if (statusColor.equalsIgnoreCase("green"))
			statusColor = "rgb(0, 255, 0)";

		String[] uploadedFiles = (strUtils.extractFileNameString(createFile1) + "," + strUtils.extractFileNameString(createFile2)).split(",");

		for (String file : uploadedFiles) {
			reloadPage();
			waitForCompletePageLoad();
			navigateTab(LandingPage.lnk_Files);
			searchFiles(file);
			Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileStatus) + " should contain " + status,
					getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(status));

			if (cellRecordFlag.equalsIgnoreCase("yes")) {
				Assert.assertTrue(findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style")
						+ " should contain font-family:" + statusFont, findElement(ProjectsTab.ele_DocListingFirstRow)
						.getAttribute("style").contains(statusFont));
				Assert.assertTrue(findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style")
						+ " should contain background-color: " + statusColor,
						findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style").contains(statusColor));

			} else if (cellRecordFlag.equalsIgnoreCase("no")) {

				mouseHover(ProjectsTab.ele_DocListingFirstFileStatusCell);
				Assert.assertTrue(
						findElement(ProjectsTab.ele_DocListingFirstFileStatusCell).getAttribute("style")
								+ " should contain font-family: '" + statusFont + "'",
						findElement(ProjectsTab.ele_DocListingFirstFileStatusCell).getAttribute("style").contains(
								statusFont));
				Assert.assertTrue(
						findElement(ProjectsTab.ele_DocListingFirstFileStatusCell).getAttribute("style")
								+ " should contain background-color: " + statusColor,
						findElement(ProjectsTab.ele_DocListingFirstFileStatusCell).getAttribute("style").contains(
								statusColor));
			}

		}

		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(projectName);
		clickElementWithText(testDataFolder);
	}

	public void clickOnFilesAndEditAndStatus(String edit, String status) {
		contextClick(FilesTab.lnk_FileName);
		clickContextMenuOption(edit, status);
	}

	public void verifyChangeableStatus() {
		uploadedFilesStatusList.addAll(uploadFilesStatusList);
		clickElementAndWait(FilesTab.sel_StatusChangeStatusDropdown);
		statusElementList = findElements(FilesTab.css_StatusChangeStatusDropdownList);
		compareWebStringList(uploadedFilesStatusList, statusElementList);
	}

	public void changeStatusAndNoteAndClickOnChangeStatus(String status, String statusChangeNote) {
		selectByVisibleText(FilesTab.sel_StatusChangeStatusDropdown, status + epoch);
		sendKeys(FilesTab.txt_StatusChangeReasonNote, statusChangeNote + epoch);
		clickElementAndWait(FilesTab.btn_PopFileStatusChangeStatusChange);
	}

	public void deactivateStatuses() throws InterruptedException {
		selectProjectEditOption(projectName, AdoddleCommonStringPool.OPT_FILE_STATUSES);

		for (String status : Arrays.asList(statusTextList)) {
			sendKeys(ProjectsTab.txt_SearchPOIAndStatusFilter, status);
			statusWebList = findElements(ProjectsTab.css_AllPOIAndStatusCheckList);
			for (WebElement poiCheckBox : statusWebList) {
				if (!poiCheckBox.isSelected()) {
					poiCheckBox.click();
				}
			}
		}
		clickElementAndWait(ProjectsTab.btn_POIAndStatusSaveButton);
	}

	public void verifyStatusesDeactivate() {
		clickElementWithText(projectName);
		clickElementWithText(testDataFolder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);
		clickOnSelectFilesAndUpload();
		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWait(FilesTab.drp_PopUploadHeaderStatus);
		statusElementList = findElements(FilesTab.css_UploadFilesStatusList);
		verifyDeactivateStatuses(statusElementList);
		clickElementAndWait(FilesTab.drp_StatusFile1);
		statusElementList = findElements(FilesTab.css_UploadFilesStatusFileList1);
		verifyDeactivateStatuses(statusElementList);
		clickElementAndWait(FilesTab.drp_StatusFile2);
		statusElementList = findElements(FilesTab.css_UploadFilesStatusFileList2);
		verifyDeactivateStatuses(statusElementList);
	}

	private void verifyDeactivateStatuses(List<WebElement> list) {
		for (WebElement statusWeb : list) {
			for (String statusValue : statusTextList) {
				if (!statusWeb.getText().contains(statusValue + epoch))
					log.info("Status Successfully deactivated");
				else {
					Assert.assertTrue("statusWeb :" + statusWeb.getText(), false);
				}
			}
		}
	}

	private void compareWebStringList(List<String> stringList, List<WebElement> elementList) {
		boolean flag = false;
		for (WebElement valueList2 : elementList) {
			for (String valueList1 : stringList) {
				if (valueList2.getText().trim().equalsIgnoreCase(valueList1)) {
					log.info("verified valueList2 :" + valueList2.getText().trim());
					log.info("verified valueList1 :" + valueList1);
					flag = true;
					break;
				} else {
					log.info("not Verified");
					flag = false;
				}
			}
			if (!flag) {
				if (valueList2.getText().trim().contains(epoch)) {
					Assert.assertTrue(valueList2.getText().trim() + " does not contain " + epoch, false);
				} else {
					log.info("skip status :" + valueList2.getText());
				}
			}
		}
	}

	public void deactivateAllDocuments() {
		try {
			deactivateFile(testDataFolder);
			collectionDataMap.put("Deactivated TestData Folder", testDataFolder);
		} catch (Throwable t) {
			log.info("Failure while deactivating files");
		}
	}
}