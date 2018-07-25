package org.asite.automation.adoddle.p1.scripts;

import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;

public class FileComparisonScripts extends AdoddleCommonAppMethods {
	private String				parentWindow;
	
	public void selectTwoFilesCheckbox() {
		String	searchFileType	= "FileCompareTestDataFile";
		searchFiles(searchFileType);
		clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
		clickElementAndWait(FilesTab.chk_FilesTabSecondFileCheckbox);
		String  fileName1 = getElementText(FilesTab.lnk_DocListingFirstFileName);
		String fileName2 = getElementText(FilesTab.lnk_DocListingSecondFileName);
		collectionDataMap.put("Comparison File1", fileName1);
		collectionDataMap.put("Comparison File2", fileName2);
	}

	public void clickOnSelectedFiles() {
		contextClick(FilesTab.chk_DocListingFirstCheckBox);
	}

	public void clickOnCompareFiles() {
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickCompare, FilesTab.opt_FileContextClickCompareFiles);
	}

	public void gotoFileOpenWindow() {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void verifyCompareFilesInViewer() throws Exception {
		waitForCompletePageLoad();
		verifyCompareFilesView(collectionDataMap.get("Comparison File1"), collectionDataMap.get("Comparison File2"));
	}

	public void closeFileOpenedWindow() {
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}
}
