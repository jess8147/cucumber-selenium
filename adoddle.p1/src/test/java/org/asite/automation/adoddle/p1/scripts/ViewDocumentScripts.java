package org.asite.automation.adoddle.p1.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;

public class ViewDocumentScripts extends AdoddleCommonAppMethods {
	private 		String			parentWindow;
	final private   String			selectViewerType[]	= { "HTML Viewer", "Advanced Viewer" };
	final private static Logger	log					= AutomationLogger.getInstance().getLogger(ViewDocumentScripts.class);

	public void gotoFileDocument() {
		parentWindow = getCurrentWindow();
		searchFiles(ResourceHandler.loadProperty("view.document.test.file").trim());
		collectionDataMap.put("Test File", ResourceHandler.loadProperty("view.document.test.file").trim());
		clickElementAndWait(FilesTab.lnk_DocListingFirstDocRef);
	}
	
	public void gotoFileDocumentInUniversalWebViewer() {
		parentWindow = getCurrentWindow();
		searchFiles(ResourceHandler.loadProperty("view.document.universalWebViewer.test.file").trim());
		collectionDataMap.put("Test File", ResourceHandler.loadProperty("view.document.universalWebViewer.test.file").trim());
		clickElementAndWait(FilesTab.lnk_DocListingFirstDocRef);
	}

	public void verifyFileOpenedInAdvancedViewer() throws Exception {
		String viewerPageSource;
			switchWindow();
			clickElementAndWaitForElement(FilesTab.sel_FileBetaViewViewerDropdown, FilesTab.lnk_FileBetaViewSelectAdvancedViewer);
			clickElementAndWait(FilesTab.lnk_FileBetaViewSelectAdvancedViewer);
			log.info("viewerType :" + selectViewerType[1]);
			waitForCompletePageLoad();
			waitUntilElementIsDisplayed(FilesTab.frm_CompareFilesViewerIFrame);
			viewerPageSource = getWebDriver().getPageSource();
			log.error("Final PageSource::" + viewerPageSource);
			AutomationAssert.verifyTrue(viewerPageSource.contains(ResourceHandler.loadProperty("view.document.test.file").trim()));
			waitUtils.waitInterval(2);
			takeScreenShot(TestDriverFactory.scenario);
	}

	public void gotoFileOpenWindow() {
		String currentWindowTitle = getWebDriver().getTitle();
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchToSecondWindow(currentWindowTitle);
	}

	public void closeFileOpenedWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}
	
	public void verifyFileInUniversalWebViewer() throws Exception {
		openFileInViewerWindow();
		waitAndSwitchIframe(FilesTab.frm_UniversalWebViewerFrame);
		waitUntilElementIsDisplayed(FilesTab.Viewer_UniversalWebViewer);
		AutomationAssert.verifyTrue("File view could not be verified", isDisplayed(FilesTab.Viewer_UniversalWebViewer));
		takeScreenShot(TestDriverFactory.scenario);
		switchDefault();
		
	}
	
	public void verifyFileAttributeOnTop() {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileDetails);
			clickElementAndWait(FilesTab.btn_BetaFileViewFileDetails);
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.ele_BetaViewFileStandardAtrributesTable, true), isDisplayed(FilesTab.ele_BetaViewFileStandardAtrributesTable));
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.ele_BetaViewFileStandardAtrributesTable, true), isDisplayed(FilesTab.ele_BetaViewFileStandardAtrributesTable));
	}
}