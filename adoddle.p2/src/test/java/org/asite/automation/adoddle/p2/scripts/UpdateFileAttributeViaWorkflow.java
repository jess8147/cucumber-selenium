/**  Testdata required for this script as follows.
     1). Automation_UpdatePrivacy_WF_DC_Project for respective DC's 
     2). WF_UpdatePrivacy_AutoTestFile revisions in respective folder's
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UpdateFileAttributeViaWorkflow extends AdoddleCommonAppMethods {

	private int oldRevision;
	private String updatedRevision;
	final private String filterType1 = "preFilter";
	final private List<String> multiFileList = new ArrayList<String>();
	private List<WebElement> filesDocRefs = new ArrayList<WebElement>();
	private List<WebElement> filesListPrivateIcons = new ArrayList<WebElement>();
	final private static Logger log = AutomationLogger.getInstance().getLogger(UpdateFileAttributeViaWorkflow.class);

	public void focusProjectAndFolder(String folder, String project) {
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(project);
		clickElementWithText(folder);
	}

	public void validateOldRevisions(String workspace, String scenarioFolder, String docRef, String scenario)
			throws InterruptedException {

		searchFileAndGetActiveRevsion(docRef);
		setFileRevisionFilter(scenario, filterType1);
		filesDocRefs = findElements(FilesTab.css_DocRefList);
		log.info("DocRefListSize::" + filesDocRefs.size());
		filesListPrivateIcons = findElements(FilesTab.css_DocRefListPrivateImages);
		log.info("Private Files size :: " + filesListPrivateIcons.size());

		if (scenario.contains("Private")) {

			if (filesListPrivateIcons.size() > 0) {

				clickElementAndWaitForElement(FilesTab.chk_MultiFilesSelectionCheckbox, FilesTab.lnk_DocListingFirstDocRef);
				contextClick(FilesTab.lnk_DocListingFirstDocRef);
				waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickEdit);
				mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditStatus);
				selectByVisibleText(FilesTab.sel_StatusChangeStatusDropdown, "To Be Approved");
				sendKeys(FilesTab.txt_StatusChangeReasonNote, javaUtils.getRandomString(10));
				if (!isSelected(FilesTab.rad_StatusChangeAllDocumentsPublic))
					clickElement(FilesTab.rad_StatusChangeAllDocumentsPublic);
				clickElementAndWaitForElement(FilesTab.btn_PopFileStatusChangeStatusChange, GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();
				filesListPrivateIcons.clear();
				filesListPrivateIcons = findElements(FilesTab.css_DocRefListPrivateImages);
				AutomationAssert.verifyTrue(filesListPrivateIcons.size() == 0);

			} else
				log.error("Revisions Already in Public");

			filesListPrivateIcons.clear();
			filesDocRefs.clear();
		}

		else {

			if (filesListPrivateIcons.size() == 0) {

				clickElementAndWaitForElement(FilesTab.chk_MultiFilesSelectionCheckbox, FilesTab.lnk_DocListingFirstDocRef);
				contextClick(FilesTab.lnk_DocListingFirstDocRef);
				mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditStatus);
				selectByVisibleText(FilesTab.sel_StatusChangeStatusDropdown, "To Be Approved");
				sendKeys(FilesTab.txt_StatusChangeReasonNote, javaUtils.getRandomString(10));
				if (!isSelected(FilesTab.rad_StatusChangeAllDocumentsPrivate))
					clickElement(FilesTab.rad_StatusChangeAllDocumentsPrivate);
				clickElementAndWaitUntilElementInvisible(FilesTab.btn_PopFileStatusChangeStatusChange, GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();
				filesListPrivateIcons.clear();
				navigateTab(LandingPage.lnk_Files);
				clickElementWithText(workspace);
				clickElementWithText(scenarioFolder);
				waitUntilElementIsDisplayed(FilesTab.css_FileLists);
				setFileRevisionFilter(scenario, filterType1);
				filesListPrivateIcons = findElements(FilesTab.css_DocRefListPrivateImages);
				log.info("PrivateDocuments Count:: " + filesListPrivateIcons.size());
				AutomationAssert.verifyTrue(filesListPrivateIcons.size() == filesDocRefs.size());

			} else
				log.error("Revisions Already in Private");

		}

		filesListPrivateIcons.clear();
		filesDocRefs.clear();

	}

	public void searchFileAndGetActiveRevsion(String fileDocRef) {
		searchFiles(fileDocRef);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
		oldRevision = Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstVersion));
		log.info("Old revision of file: " + oldRevision);

	}

	public void clickOnAddFilesButton() {
		clickElementAndWaitForElement(FilesTab.btn_AddFiles, GlobalPageElements.pop_PopUpElement);

	}

	public void selectFileRevisonsAndUpload(String revisionType) {

		if (revisionType.contains("multiple")) {

			String filePath = ResourceHandler.loadProperty("multi.workflow.testfile.path");
			sysUtils.authenticateRemoteMachine(nodeIP);
			multiFileList.add(filePath.split(", ")[0]);
			multiFileList.add(filePath.split(", ")[1]);
			multiFileList.add(filePath.split(", ")[2]);

			log.info("List Size" + multiFileList.size());
			collectionDataMap.put("Upload Dcouments:: ", multiFileList.toString());
			uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileList);

		}

		else {

			sysUtils.authenticateRemoteMachine(nodeIP);
			findElement(FilesTab.btn_PopUploadFileDistributeSelectFiles).sendKeys(
					ResourceHandler.loadProperty("single.workflow.testfile.path"));

		}

	}

	public void enteraMandatoryAttributes(String AcessModifier) {

		updatedRevision = Integer.toString(oldRevision + 1);
		log.info("New Revision " + updatedRevision);

		if (AcessModifier.contains("Private")) {

			if (AcessModifier.contains("AllVersions_Private")) {

				bulkApplyCheckboxSelect();
				clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
				sendKeys(FilesTab.txt_PopUploadHeaderRevInput, updatedRevision);
				selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
				selectByVisibleText(FilesTab.drp_PopUploadHeaderStatus, "To Be Approved");
				AutomationAssert.verifyTrue(!isSelected(FilesTab.chk_PopUploadFilesBulkPublishAsPrivate));
				executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
				mouseHover(FilesTab.btn_PopUploadApplytoAll);
				clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);

			}

			else {

				clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
				sendKeys(FilesTab.txt_PopUploadFileFirstFileRevision, updatedRevision);
				selectByIndex(FilesTab.drp_PurposeOfIssueFile1, 1);
				selectByIndex(FilesTab.drp_StatusFile1, 4);
				AutomationAssert.verifyTrue(!isEnabled(FilesTab.chk_PublishAsPrivate));
				clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);

			}
		}

		else {

			if (AcessModifier.contains("ActiveVersion_Public")) {

				bulkApplyCheckboxSelect();
				clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
				sendKeys(FilesTab.txt_PopUploadHeaderRevInput, updatedRevision);
				selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
				selectByVisibleText(FilesTab.drp_PopUploadHeaderStatus, "To Be Approved");
				executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
				mouseHover(FilesTab.btn_PopUploadApplytoAll);
				clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);

			}

			else {

				clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
				sendKeys(FilesTab.txt_PopUploadFileFirstFileRevision, updatedRevision);
				selectByIndex(FilesTab.drp_PurposeOfIssueFile1, 1);
				selectByIndex(FilesTab.drp_StatusFile1, 4);
				clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);

			}
		}
	}

	private void bulkApplyCheckboxSelect() {
		if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply)) {
			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		}
	}

	public void clickOnUpload() {
		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeUpload);
		try {
			if (getElementText(FilesTab.pop_AsiteSameRevision).contains(AdoddleCommonStringPool.POP_ASITE_SAMEREVISION))
				clickElementAndWait(FilesTab.btn_PopAsiteContinue);
		} catch (Exception e) {

			log.info("Continue Popup not found");
		}

	}

	public void waitUntilUploadPopInvisible() {
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void checkFileStatus(String fileName, String wStatus, String wStage, String scenario) {

		if (scenario.contains("AllVersions_Private") || scenario.contains("ActiveVersion_Public")) {
			for (String str : multiFileList)
				searchFiles(strUtils.extractFileNameString(str));
		} else
			searchFiles(fileName);

		waitUntilElementContainsText(FilesTab.lnk_workflowStatus, wStatus);
		AutomationAssert.verifyTrue("Workflow Status is not found to be : "+wStatus, getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(wStatus));
		AutomationAssert.verifyTrue("Workflow Stage is not found to be : "+wStage, getElementText(FilesTab.ele_workflowStage).contains(wStage));
		AutomationAssert.verifyTrue("Workflow Revision is not updated to : "+updatedRevision, getElementText(FilesTab.lnk_FileListingFirstRevision).contains(updatedRevision));

	}

	public void validateFileMyAction() {

		log.info("Covered in <performMyActions> Definition");

	}

	public void validateActionPopup() {
		log.info("Covered in <performMyActions> Definition");

	}

	public void performMyActions(String fileAction, String scenario) {
		String workflowFileName = "AutoTestFile_WF_UpdatePrivacy";
		if (scenario.contains("AllVersions_Private") || scenario.contains("ActiveVersion_Public")) {

			for (String str : multiFileList) {

				searchFiles(strUtils.extractFileNameString(str));
				AutomationAssert.verifyTrue("First file revision " + getElementText(FilesTab.lnk_FilesTabFirstFileRev) + " != "
						+ updatedRevision, getElementText(FilesTab.lnk_FilesTabFirstFileRev).contains(updatedRevision));
				waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
				AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains(fileAction));
				clickElementAndWait(FilesTab.lnk_FilesFirstAction);
				waitUntilElementIsDisplayed(FilesTab.sel_StatusChangedDropdown);
				if (scenario.contains("Private"))
					selectByVisibleText(FilesTab.sel_StatusChangedDropdown, "Approved");
				else
					selectByVisibleText(FilesTab.sel_StatusChangedDropdown, "Rejected");
				sendKeys(FilesTab.txt_StatusChangeTextArea, javaUtils.getRandomString(10));
				clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_ChangeStatus, GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();
			}

		}

		else {

			searchFiles(workflowFileName);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains(fileAction));
			clickElementAndWaitForElement(FilesTab.lnk_FilesFirstAction, FilesTab.sel_StatusChangedDropdown);
			selectByVisibleText(FilesTab.sel_StatusChangedDropdown, "Approved");
			sendKeys(FilesTab.txt_StatusChangeTextArea, javaUtils.getRandomString(10));
			clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_ChangeStatus, GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
		}

	}

	public void validateFileVersion(String wScenario) throws InterruptedException {
		String filterType2 = "postFilter";
		setFileRevisionFilter(wScenario, filterType2);
		filesListPrivateIcons = findElements(FilesTab.css_DocRefListPrivateImages);
		log.info("Private Icons size:: " + filesListPrivateIcons.size());
		filesDocRefs = findElements(FilesTab.css_DocRefList);
		log.info("File DocRefs size:: " + filesDocRefs.size());
		if (wScenario.contains("Public"))
			AutomationAssert.verifyTrue(filesListPrivateIcons.size() == 0);
		else
			AutomationAssert.verifyTrue(filesListPrivateIcons.size() == filesDocRefs.size());
	}

	public void validateWorkflowStatusAndStage(String wStatus) {
		waitUntilElementContainsText(FilesTab.lnk_workflowStatus, wStatus);
		clickElementAndWaitForElement(FilesTab.lnk_workflowStatus, FilesTab.btn_WorkflowStatusCancel);
		clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
	}

	private void setFileRevisionFilter(String scenarioName, String filterType) throws InterruptedException {

		String previousRevision = Integer.toString(oldRevision);
		String activeVersion = updatedRevision;
		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.FILTER_REVISIONS);
		clickElementAndWaitForElement(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox, FilesTab.lbl_DocListingCreatedFirstFilterDropDownLabel);
		clickElementAndWaitForElement(FilesTab.lbl_DocListingCreatedFirstFilterDropDownLabel,
				GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch);

		if (scenarioName.contains("AllVersions")) {
			sendKeys(GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch, AdoddleCommonStringPool.FILTER_OPT_SUPERSEDED);
			if (!isSelected(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox))
				clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
			clickElementAndWait(FilesTab.lnk_FilterClose);
			waitForCompletePageLoad();

		}

		else if (scenarioName.contains("AllSupersededVersions")) {

			sendKeys(GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch, AdoddleCommonStringPool.FILTER_OPT_CURRENTSET);
			if (isSelected(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox))
				clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
			sendKeys(GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch, AdoddleCommonStringPool.FILTER_OPT_SUPERSEDED);
			if (!isSelected(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox))
				clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);

			clickElementAndWait(FilesTab.lnk_FilterClose);
			waitForCompletePageLoad();

		}

		else if (scenarioName.contains("PerviousVersionOnly")) {

			sendKeys(GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch, AdoddleCommonStringPool.FILTER_OPT_SUPERSEDED);
			if (!isSelected(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox))
				clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
			clickElementAndWait(FilesTab.lnk_FilterClose);
			waitForCompletePageLoad();
			clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
			sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.VER);
			clickElementAndWaitForElement(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox, FilesTab.txt_DocListingCreatedSecondFilterRev);
			sendKeys(FilesTab.txt_DocListingCreatedSecondFilterRev, previousRevision);
			sendKeys(FilesTab.txt_DocListingCreatedSecondFilterRev, Keys.ENTER);
			waitForCompletePageLoad();
		}

		else if (scenarioName.contains("ActiveVersion")) {

			sendKeys(GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch, AdoddleCommonStringPool.FILTER_OPT_SUPERSEDED);
			if (!isSelected(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox))
				clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
			clickElementAndWait(FilesTab.lnk_FilterClose);
			waitForCompletePageLoad();
			clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
			sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.VER);
			clickElementAndWaitForElement(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox, FilesTab.txt_DocListingCreatedSecondFilterRev);
			if (filterType.contains(filterType1))
				sendKeys(FilesTab.txt_DocListingCreatedSecondFilterRev, previousRevision);
			else
				sendKeys(FilesTab.txt_DocListingCreatedSecondFilterRev, activeVersion);
			sendKeys(FilesTab.txt_DocListingCreatedSecondFilterRev, Keys.ENTER);
			waitForCompletePageLoad();
		}

	}

	public void deactivateFileRevisions() {

		try {
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			clickContextMenuOptionWithText(AdoddleCommonStringPool.OPT_MOREOPTIONS);
			clickContextMenuOptionWithText(AdoddleCommonStringPool.OPT_DEACTIVATE_FILES);
			waitUntilElementIsDisplayed(FilesTab.ele_MenuOptionsDeactivate);
			clickElementAndWaitForElement(FilesTab.ele_MenuOptionsDeactivate, FilesTab.btn_UplaodConfirmOKButton);
			clickElementAndWait(FilesTab.btn_UplaodConfirmOKButton);

		} catch (Throwable e) {

			log.error("Failure while Deactivating Files");

		}
	}

}