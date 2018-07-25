/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class EditInsertSecondaryFileScripts extends AdoddleCommonAppMethods {

	private String			filePath, pdfFilePath, parentHandle;
	File					downloadFile		= null;
	public static Logger	log					= AutomationLogger.getInstance().getLogger(EditInsertSecondaryFileScripts.class);

	public void selectFilesOnListing(String fileFlag) {

		clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
		if (fileFlag.equalsIgnoreCase("multiple"))
			clickElementAndWait(FilesTab.chk_DocListingSecondCheckBox);
	}

	public void contextClickAndSelectEditOption() {
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHover(FilesTab.opt_FileContextClickEdit);
		waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickEditStatus);
	}

	public void verifySecondaryFileOption() {
		Assert.assertTrue(isDisplayed(FilesTab.opt_FileContextClickEditSecondaryFileDisabled));
	}

	public void searchFileWithLink(String linkType) {
		if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC))
			clickElementWithText(AdoddleCommonStringPool.FOLDER_DYNAMICLINKTARGETFOLDER);
		else if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC)) {
			clickElementWithText(AdoddleCommonStringPool.FOLDER_DYNAMICLINKTARGETFOLDER);
			searchFiles(ResourceHandler.loadProperty("static.link.testdata.file"));
		}
	}

	public void selectContextMenuOption(String secondaryFilesOption) {

		clickElementAndWaitForElement(FilesTab.opt_FileContextClickEditSecondaryFileEnabled, GlobalPageElements.pop_PopUpElement);
		findElement(FilesTab.btn_PopUploadFileAttachmentsSelectFiles).sendKeys(ResourceHandler.loadProperty("single.file.path"));
		waitUntilElementCountToBe(FilesTab.css_PopUploadFileAttachmentsCount, 1);
		waitForCompletePageLoad();

	}

	public void verifySingleFileUploadEnabled() {

		Assert.assertTrue(!isEnabled(FilesTab.btn_PopUploadFileAttachmentsSelectFiles));
	}

	public void uploadFileWithSecondaryFile(String fileType) {

		clickElementAndWaitForElement(FilesTab.btn_DocListingAddFiles, GlobalPageElements.pop_PopUpElement);
		sysUtils.authenticateRemoteMachine(nodeIP);
		filePath = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);
		collectionDataMap.put("parent file", filePath.toString());
		waitForCompletePageLoad();
		findElement(FilesTab.btn_PopUploadFileDistributeSelectFiles).sendKeys(filePath);
		waitForCompletePageLoad();
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadRevision, "1");
		selectByIndex(FilesTab.drp_PopUploadPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadStatus, 1);
		findElement(FilesTab.btn_PopUploadAttachSecondaryFile).sendKeys(ResourceHandler.loadProperty("single.file.path"));
		collectionDataMap.put("secondary file", ResourceHandler.loadProperty("single.file.path"));
		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeUpload);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void searchUploadedFile() {
		parentHandle = getCurrentWindow();
		navigateTab(LandingPage.lnk_Files);
		searchFiles(strUtils.extractFileNameString(filePath));
		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		if(!fileBetaViewFlag) {
			clickElementAndWait(FilesTab.lnk_FileViewLHHistoryBlock);
			List<WebElement> fileHistorySubInfoElements = findElements(FilesTab.css_FileViewHistorySubInfoRows);
			for (WebElement e : fileHistorySubInfoElements) {
				if (e.getText().contains(AdoddleCommonStringPool.ADDED_SECONDARY_FILE_HISTORY_REMARKS)) {
					Assert.assertTrue(e.getText() + " should contain " + strUtils.extractFileNameString(ResourceHandler.loadProperty("single.file.path")), e.getText().contains(strUtils.extractFileNameString(ResourceHandler.loadProperty("single.file.path"))));
				}
			}
		}
		else {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileHistory);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileHistory, FilesTab.ele_BetaFileViewHistoryPanel);
			clickElementAndWaitForElement(FilesTab.img_BetaFileViewMaximizePanel, FilesTab.lbl_BetaFileViewHeaderRemarks);
			selectByVisibleText(FilesTab.drp_BetaFileViewHistoryTypeDropdown, "Access");
			waitUntilElementIsDisplayed(FilesTab.lbl_BetaFileViewHeaderRemarks);
			waitUntilElementCountToBeMoreThan(FilesTab.css_BetaViewFileHistoryAccessRemarksRecords, 0);
			List<WebElement> recordRows = findElements(FilesTab.css_BetaViewFileHistoryAccessRemarksRecords);
			for(WebElement e: recordRows) {
				if(e.getText().contains(AdoddleCommonStringPool.EDITED_SECONDARY_FILE_HISTORY_REMARKS))
					AutomationAssert.verifyTrue(getElementText(e).contains(strUtils.extractFileNameString(filePath)));
			}
		}
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void editAttachSecondaryFile(String fileType) {
		sysUtils.authenticateRemoteMachine(nodeIP);
		pdfFilePath = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);
		collectionDataMap.put("edited secondary file", pdfFilePath.toString());
		findElement(FilesTab.btn_PopUploadFileAttachmentsSelectFiles).sendKeys(pdfFilePath);
		waitUntilElementIsClickable(FilesTab.btn_PopUploadFileAttachmentsAttach);
		clickElementAndWait(FilesTab.btn_PopUploadFileAttachmentsAttach);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
	}

	public void verifySecondaryFileIsEdited() {
		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		if(!fileBetaViewFlag) {
			clickElementAndWait(FilesTab.lnk_FileViewLHHistoryBlock);
			List<WebElement> fileHistorySubInfoElements = findElements(FilesTab.css_FileViewHistorySubInfoRows);
			for (WebElement e : fileHistorySubInfoElements) {
				if (e.getText().contains(AdoddleCommonStringPool.EDITED_SECONDARY_FILE_HISTORY_REMARKS)) {
					Assert.assertTrue(e.getText() + " should contain " + strUtils.extractFileNameString(pdfFilePath), e.getText().contains(strUtils.extractFileNameString(pdfFilePath)));
				}
			}
		}
		else {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileHistory);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileHistory, FilesTab.ele_BetaFileViewHistoryPanel);
			clickElementAndWaitForElement(FilesTab.img_BetaFileViewMaximizePanel, FilesTab.lbl_BetaFileViewHeaderRemarks);
			selectByVisibleText(FilesTab.drp_BetaFileViewHistoryTypeDropdown, "Access");
			waitUntilElementIsDisplayed(FilesTab.lbl_BetaFileViewHeaderRemarks);
			waitUntilElementCountToBeMoreThan(FilesTab.css_BetaViewFileHistoryAccessRemarksRecords, 0);
			List<WebElement> recordRows = findElements(FilesTab.css_BetaViewFileHistoryAccessRemarksRecords);
			for(WebElement e: recordRows) {
				if(e.getText().contains(AdoddleCommonStringPool.EDITED_SECONDARY_FILE_HISTORY_REMARKS))
					AutomationAssert.verifyTrue(getElementText(e).contains(strUtils.extractFileNameString(pdfFilePath)));
			}
		}

		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void verifyAuditHistoryForSecondaryFile() {
		log.info("covered in previous definition");
	}

	public void downloadPrimaryFileWithAssociations() throws IOException, InterruptedException {

		if (!isSelected(FilesTab.chk_DocListingFirstCheckBox))
			clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
		clickElementAndWait(FilesTab.btn_DocListingDownloadButton);
		clickElementAndWait(FilesTab.chk_PopDownloadFilesAssociatedFiles);
		clickElementAndWait(FilesTab.chk_PopDownloadFilesAppendVersion);
		clickElementAndWait(FilesTab.btn_PopDownloadFilesDownload);
		String downloadFilePath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + dateUtils.getEpoch() + AdoddleCommonStringPool.ZIP_EXTENSION;
		downloadFile = new File(downloadFilePath);
		sysUtils.waitForMultipleFileDownload(nodeIP, downloadFilePath);
		collectionDataMap.put("downloaded zip file", downloadFilePath.toString());
	}

	public void verifyReplacedSecondaryFileIsDownloaded(String extension) {

		sysUtils.unZipFile(downloadFile.toString(), nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch);
		List<String> downloadedFileList = sysUtils.getFileListOfSystemFolder(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch);
		Assert.assertTrue(downloadedFileList + " should contain " + strUtils.extractFileNameString(pdfFilePath), downloadedFileList.contains(strUtils.extractFileNameString(pdfFilePath)));

	}
}
