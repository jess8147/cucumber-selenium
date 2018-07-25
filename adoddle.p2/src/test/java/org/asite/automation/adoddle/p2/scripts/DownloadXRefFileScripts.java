/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DownloadXRefFileScripts extends AdoddleCommonAppMethods {

	private double				expectedFileSize, actualFileSize;
	private File				xRefFile;
	private String				parentHandle, parentDoc, xRefVersion, fileTitle;
	private List<String>		XRefChildFilesList	= new ArrayList<String>();
	private List<WebElement>	XRefChildElements	= null;
	public static Logger	log				= AutomationLogger.getInstance().getLogger(DownloadXRefFileScripts.class);
	
	public void searchXRefDocument(String parentDocTitle) {
		parentDoc = parentDocTitle;
		parentHandle = getCurrentWindow();
		searchFiles(parentDoc);
		collectionDataMap.put("parent file", parentDoc);
	}

	public void verifyDocumentIcon() {

		AutomationAssert.verifyTrue(findElement(FilesTab.img_DocListingFirstTypeIcon).getAttribute(AdoddleCommonStringPool.ATTRIBUTE_SRC).contains("dwg_mf.gif"));

	}

	public void assignActionToCurrentUser(String action) throws InterruptedException {

		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickShare);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
		sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, System.getProperty("primary.username"));
		sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.TAB);
		sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject, javaUtils.getRandomString(20));
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void clickOnBlueXrefFileIcon() {
		expectedFileSize = Integer.parseInt(getElementText(FilesTab.lbl_DocListingFirstFileSize).split(" ")[0]);
		fileTitle = getElementText(FilesTab.lnk_DocListingFirstFileName);
		clickElementAndWait(FilesTab.img_DocListingFirstTypeIcon);

	}

	public void downloadXRefFile(String extension) throws IOException, InterruptedException {
		xRefFile = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch + AdoddleCommonStringPool.DOT_STRING + extension);
		log.info("Download XRef File: "+xRefFile.toString());
		collectionDataMap.put("downloaded xref", xRefFile.toString());

		if(extension.equalsIgnoreCase("dwg")) {
			sysUtils.waitForSingleDirectFileDownload(nodeIP, fileTitle, xRefFile.toString());
		}
		else if(extension.equalsIgnoreCase("zip")){
			/*autoItUtils.downloadAutoIt(xRefFile.toString(), nodeIP);*/
			sysUtils.waitForMultipleFileDownload(nodeIP, xRefFile.toString());
		}
	}

	public void verifyDownloadedFile() throws InterruptedException {
		waitUntilFileIsDownloaded(xRefFile);
	}

	public void verifyDownloadedFileSize() {
		actualFileSize = sysUtils.getFileSize(xRefFile.toString());
		AutomationAssert.verifyTrue("Expected Filesize# " + expectedFileSize + "\nActual Filesize# " + actualFileSize, expectedFileSize == Math.round(actualFileSize * 10.0) / 10.0);
	}

	public void verifyActionIsCleared(String actionTitle) {
		reloadPage();
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Files);
		searchFiles(parentDoc);
		AutomationAssert.verifyTrue(!isDisplayedLinkWithText(actionTitle));
	}

	public void clickFileDocRef() {
		xRefVersion = getElementText(FilesTab.lnk_FileListingFirstVersion);
		clickElementAndWait(FilesTab.lnk_DocListingFirstDocRef);
	}

	public void verifyXRefFileIsOpened() {

		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
	}

	public void clickOnAssociationLHPanel() {
		if(!fileBetaViewFlag) {
			clickElementAndWaitForElement(FilesTab.lnk_FileViewLHAssociationBlock, FilesTab.lnk_FileViewAssociationsXRefParentFile);
			XRefChildFilesList.add(getElementText(FilesTab.lnk_FileViewAssociationsXRefParentFile).replace(AdoddleCommonStringPool.DWG_EXTENSION, "_Ver" + xRefVersion + AdoddleCommonStringPool.DWG_EXTENSION));
		}
		else {
			clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewFormDetailsAttachmentsLink, FilesTab.lnk_BetaFileViewAssociationsExternalreferences);
			if(!isDisplayed(FilesTab.ele_BetaFileViewAssociationsExternalReferences))
				clickElementAndWait(FilesTab.lnk_BetaFileViewAssociationsExternalreferences);
			
			XRefChildFilesList.add(getElementText(FilesTab.lnk_BetaFileViewAssociationsXRefParentFile).replace(AdoddleCommonStringPool.DWG_EXTENSION, "_Ver" + xRefVersion + AdoddleCommonStringPool.DWG_EXTENSION));
		}
	}

	public void getXRefChildAssociationFilesList() {

		XRefChildElements = findElements(!fileBetaViewFlag ? FilesTab.css_FileViewAssociationsXRefChilds: FilesTab.css_BetaFileViewAssociationsXRefChilds);
		for (WebElement e : XRefChildElements)
			XRefChildFilesList.add(e.getText());
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void selectXRefFileCheckbox() {
		if (!isSelected(FilesTab.chk_DocListingFirstCheckBox))
			clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
	}

	public void clickDownloadLinkButton() {
		fileTitle = getElementText(FilesTab.ele_FilesTabFirstFileDocTitle) + "_" + "Ver1" + AdoddleCommonStringPool.DWG_EXTENSION;
		log.info("Download File Title : "+fileTitle);
		clickElementAndWaitForElement(FilesTab.btn_DocListingDownloadButton, GlobalPageElements.pop_PopUpElement);
	}

	public void selectXRefOptionAndClickDownload(String flag) {
		if (flag.equalsIgnoreCase("yes")) {
			if (!isSelected(FilesTab.chk_PopDownloadFilesIncludeXRef))
				clickElement(FilesTab.chk_PopDownloadFilesIncludeXRef);
		}
		else if (flag.equalsIgnoreCase("no")) {
			if (isSelected(FilesTab.chk_PopDownloadFilesIncludeXRef))
				clickElement(FilesTab.chk_PopDownloadFilesIncludeXRef);
		}

		clickElementAndWait(FilesTab.btn_PopDownloadFilesDownload);
	}

	public void unzipDownloadedXRefZipFile() {
		sysUtils.unZipFile(xRefFile.toString(), nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch);
	}

	public void verifyAllParentAndChildFiles() {
		List<String> systemFileList = sysUtils.getFileListOfSystemFolder(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch);
		log.info("Expected File List# " + XRefChildFilesList.toString());
		log.info("Actual File List# " + systemFileList.toString());
		collectionDataMap.put("actual downloaded childs", systemFileList.toString());
		collectionDataMap.put("expected web childs", XRefChildFilesList.toString());
		javaUtils.compareUnorderedList(systemFileList, XRefChildFilesList);

	}
}
