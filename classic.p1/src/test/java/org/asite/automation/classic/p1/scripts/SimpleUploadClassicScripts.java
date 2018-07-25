package org.asite.automation.classic.p1.scripts;

import java.io.IOException;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicWSLandingLocators.WSLandingPage;
import org.asite.automation.CommonLocators.ClassicWorkspaceLocators.WorkspaceTabPage;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;

public class SimpleUploadClassicScripts extends ClassicCommonAppMethods {
	private String	parentHandle, folderTitle;
	private boolean	revertChanges	= false;

	public void selectEditWorkspace(String adminDropdownList) {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		selectByVisibleText(WSLandingPage.drp_AdminDropdown, adminDropdownList.trim());
		waitForCompletePageLoad();
	}

	public void selectEnableSimpleUploadCheckbox() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
		waitAndSwitchIframe(LandingPage.frm_PmFrame);

		waitUntilElementIsDisplayed(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoShow);
		clickElementAndWaitForElement(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoShow, WorkspaceTabPage.chk_CreateEditWorkspaceEnablePublicUpload);
		if (!isSelected(WorkspaceTabPage.chk_CreateEditWorkspaceEnablePublicUpload)) {
			clickElementAndWait(WorkspaceTabPage.chk_CreateEditWorkspaceEnablePublicUpload);
			clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoSave);
			revertChanges = true;
		}
		clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoHide);
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		clickElementAndWait(WSLandingPage.lnk_WorkspaceHome);
	}

	public void clickOnFolder(String folderName) {
		folderTitle = folderName;
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderName);
	}

	public void clickOnPublishStandardDocument() {
		parentHandle = getCurrentWindow();
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingMenuPublishMenu);
		mouseHoverAndClickElement(DocListingPage.img_DocListingMenuPublishMenu, DocListingPage.img_DocListingMenuPublishStandardDoc);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
	}

	public void clickOnStartUpload() {
		clickElementAndWait(DocListingPage.btn_PublishWindowSimpleUploadStartUpload);
		waitForCompletePageLoad();
	}

	public void verifyDocumentUploadSuccess() throws InterruptedException {

		switchPreviousWindow(parentHandle);
		switchToMultipleFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderTitle);
		waitForCompletePageLoad();
		switchToMultipleFrames();
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitForCompletePageLoad();
		searchFiles(ThinClientUploadClassicScripts.simpleFirstDocRef);
		waitUntilElementIsDisplayed(DocListingPage.css_DocListingDocRefList);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(DocListingPage.css_DocListingDocRefList), 0), getCount(DocListingPage.css_DocListingDocRefList) > 0);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(DocListingPage.lnk_DocListingFirstDocRef), ThinClientUploadClassicScripts.simpleFirstDocRef), getElementText(DocListingPage.lnk_DocListingFirstDocRef).contains(ThinClientUploadClassicScripts.simpleFirstDocRef));
		searchFiles(ThinClientUploadClassicScripts.simpleSecondDocRef);
		waitUntilElementIsDisplayed(DocListingPage.css_DocListingDocRefList);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(DocListingPage.css_DocListingDocRefList), 0), getCount(DocListingPage.css_DocListingDocRefList) > 0);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(DocListingPage.lnk_DocListingFirstDocRef), ThinClientUploadClassicScripts.simpleFirstDocRef), getElementText(DocListingPage.lnk_DocListingFirstDocRef).contains(ThinClientUploadClassicScripts.simpleSecondDocRef));

	}

	public void verifyDocumentIsAvailable() throws IOException {
		log.info("This script step is covered in previous step definition");
		sysUtils.deleteFile(ThinClientUploadClassicScripts.simpleFilePath1);
		sysUtils.deleteFile(ThinClientUploadClassicScripts.simpleFilePath2);

	}

	public void revertSimpleUploadChanges() {
		if (revertChanges) {
			switchDefault();
			waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
			waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
			waitAndSwitchIframe(LandingPage.frm_PmFrame);
			waitUntilElementIsDisplayed(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoShow);
			clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoShow);
			clickElementAndWait(WorkspaceTabPage.chk_CreateEditWorkspaceEnablePublicUpload);
			clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoSave);
		}

	}

	public void switchToMultipleFrames() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}
}
