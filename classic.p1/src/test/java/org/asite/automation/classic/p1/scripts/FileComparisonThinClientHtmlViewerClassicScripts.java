package org.asite.automation.classic.p1.scripts;

import java.util.ArrayList;
import java.util.List;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicWSLandingLocators.WSLandingPage;
import org.asite.automation.CommonLocators.ClassicWorkspaceLocators.WorkspaceTabPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;

public class FileComparisonThinClientHtmlViewerClassicScripts extends ClassicCommonAppMethods {
	private String	viewerPageSource;
	private String	pdfFiles	= ResourceHandler.loadProperty("file.compare.testdata.file");
	List<String>	fileList	= new ArrayList<String>();

	public void selectEnableFileCompareCheckBox(String adminDropdownList) {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		selectByVisibleText(WSLandingPage.drp_AdminDropdown, adminDropdownList.trim());
		waitForCompletePageLoad();
		verifyEditWorkspacePage(adminDropdownList);
		enableFileCompareCheckbox();
	}

	public void selectTwoFilesCheckbox() throws InterruptedException {

		searchFiles(pdfFiles);
		fileList.add(getElementText(DocListingPage.lnk_DocListingFirstDocTitle));
		fileList.add(getElementText(DocListingPage.lnk_DocListingSecondDocTitle));
		if (!isSelected(DocListingPage.chk_AllWorkSpaceDocsTopCheckBOx))
			clickElementAndWait(DocListingPage.chk_AllWorkSpaceDocsTopCheckBOx);
	}

	public void clickOnCompareDocuments() throws Exception {
		waitForCompletePageLoad();
		clickElementAndWait(DocListingPage.lnk_DocListingCompareDocumentsImage);
		switchWindow(DocListingPage.frm_CompareDocumentIFrame);
	}

	public void verifyCompareFilesInViewer() throws Exception {
		waitUntilElementIsDisplayed(DocListingPage.frm_CompareDocumentIFrame, 180);
		switchIframe(DocListingPage.frm_CompareDocumentIFrame);
		waitUntilElementIsDisplayed(DocListingPage.ele_CompareFilesViewerElement);
		waitUntilElementIsDisplayed(DocListingPage.frm_CompareFilesViewerIFrame);
		viewerPageSource = getWebDriver().getPageSource();
		log.info("Page source:: " + viewerPageSource);
		log.info(fileList.get(0));
		AutomationAssert.verifyTrue(viewerPageSource.contains(fileList.get(0)));
		AutomationAssert.verifyTrue(viewerPageSource.contains(fileList.get(1)));
		waitUtils.waitInterval(20);
		closeFileOpenedWindow();
	}

	public void closeFileOpenedWindow() {
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(superWindow);
	}

	public void verifyEditWorkspacePage(String adminDropdownList) {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
		waitAndSwitchIframe(LandingPage.frm_PmFrame);

		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(adminDropdownList));
	}

	public void enableFileCompareCheckbox() {
		waitUntilElementIsDisplayed(WorkspaceTabPage.lnk_WorkspaceSettingsShow);
		clickElementAndWait(WorkspaceTabPage.lnk_WorkspaceSettingsShow);
		if (!isSelected(WorkspaceTabPage.chk_EnableFileCompare)) {
			clickElementAndWait(WorkspaceTabPage.chk_EnableFileCompare);
			clickElementAndWait(WorkspaceTabPage.lnk_WorkspaceSettingsSave);
		}
		clickElementAndWait(WorkspaceTabPage.lnk_WorkspaceSettingsHide);

		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		clickElementAndWait(WSLandingPage.lnk_WorkspaceHome);
	}

	public void insertIntoMultipleFrame() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}
}
