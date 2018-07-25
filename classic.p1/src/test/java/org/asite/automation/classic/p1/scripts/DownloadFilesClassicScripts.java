package org.asite.automation.classic.p1.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class DownloadFilesClassicScripts extends ClassicCommonAppMethods {
	private int i;
	private String fileExtension;
	private String epoch = getCurrentDate();
	private String parentWindow = null, parentHandle1 = null, parentHandle2 = null;
	private String zipDownloadFile = "AutomationZipFile";
	private List<String> downloadFileList1 = new ArrayList<String>();
	private List<String> downloadFileList2 = new ArrayList<String>();
	private String outputZipFolder = null;
	private String extractFolder = "outputZipFolder";
	private String extractFilesFolder = null;
	private String inputZipFile = null;
	private String downloadTestFile = "AutomationDownloadTestFile";
	private String parentFileName = "AutomationTestFile_DownloadTestFile";
	private String attachFileName = "AutomationTestFile_DownloadTestFile_Attachment";
	private String fileNameReplacement = "ParentFile";
	private String parentFileTitle = "AutomationTestFile_DownloadTestFile_Title";
	private String fileName1 ;

	private String extractDownloadFolder = "extractDownloadFolder" + epoch;
	private String extractDownloadFolderPath = null;

	public void clickOnType() {
		getFileNameFromHistory();
		clickElement(DocListingPage.img_DocListingFirstFileTypeIcon);
	}

	public void getFileNameFromHistory() {
		parentWindow = parentHandle1 = getCurrentWindow();
		insertIntoMultipleFrame();
		clickElement(DocListingPage.lnk_DocListingFirstDocDistributionHistory);
		waitForSwitchWindow(2);
		switchWindow();
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingHistoryFileName);
		fileName1 = getElementText(DocListingPage.lnk_DocListingHistoryFileName);
		fileExtension = ".".concat(fileName1.substring(fileName1.lastIndexOf(".") + 1));
		closeFileOpenedWindow();
		insertIntoMultipleFrame();
	}

	public void verifyDocumentInLocalDirectory() throws IOException, InterruptedException {
		String downloadPath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + fileName1.trim();
		log.info("Download File Path :" + downloadPath);
		sysUtils.waitForSingleDirectFileDownload(nodeIP, fileName1.trim(), downloadPath);
		sysUtils.deleteFile(nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim()
				+ downloadTestFile.trim() + epoch + fileExtension);
	}

	public void clickOnFileCheckboxAndDocumentDistributionHistory() {
		parentHandle1 = getCurrentWindow();
		insertIntoMultipleFrame();
		clickElementAndWait(DocListingPage.lnk_DocListingAdvanceSearch);
		waitUntilElementIsInvisible(DocListingPage.lnk_DocListingAdvanceSearch);
		if (!isDisplayed(DocListingPage.lnk_DocListingAdvanceSearch)) {
			waitUntilElementIsDisplayed(DocListingPage.txt_DocListingAdvanceSearchFileName);
			sendKeys(DocListingPage.txt_DocListingAdvanceSearchFileName, parentFileName);
			clickElementAndWait(DocListingPage.btn_DocListingAdvanceSearch);
		} else {
			Assert.assertTrue(false);
		}
		clickElementAndWait(DocListingPage.lnk_DocListingBasicSearch);
		waitUntilElementIsInvisible(DocListingPage.lnk_DocListingBasicSearch);
		if (!isDisplayed(DocListingPage.lnk_DocListingBasicSearch)) {
			waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingFirstFileDocRef);
			if (!isSelected(DocListingPage.chk_DocListingFirstDocCheckbox)) {
				clickElementAndWait(DocListingPage.chk_DocListingFirstDocCheckbox);
			}
			clickElementAndWait(DocListingPage.lnk_DocListingFirstDocDistributionHistory);
		} else {
			Assert.assertTrue(false);
		}
	}

	public void clickOnCommentsAssociationsAndViewCommentDetails(String commentsAssociations, String viewCommentDetails) {
		parentHandle2 = getCurrentWindow();
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingHistoryFileName);
		downloadFileList1.add(getElementText(DocListingPage.lnk_DocListingHistoryFileName).split("\\.")[0].replace(
				parentFileName, fileNameReplacement));

		waitUntilElementIsDisplayed(DocListingPage.lnk_DocumentDistributionAccessLink);
		reloadPage();
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocumentDistributionAccessLink);

		clickLinkWithText(commentsAssociations.trim());
		clickLinkWithText(viewCommentDetails.trim());
	}

	public void getFileNamesFromApplication() {
		downloadFileList1.add(attachFileName);
		waitUntilElementIsDisplayed(DocListingPage.lbl_CreateCommentDetailsCommentTitle);
		List<WebElement> commentAssociatedNameList = findElements(DocListingPage.css_CreateCommentDetailsAssociatedDocs);

		for (WebElement e : commentAssociatedNameList) {
			downloadFileList1.add(e.getText());
		}

		List<WebElement> attachFileList = findElements(DocListingPage.css_DocListigHistoryAttachFilesList);
		for (WebElement e : attachFileList) {
			downloadFileList1.add(e.getText().split("\\.")[0]);
		}
		closeCurrentWindow();
		switchPreviousWindow(parentHandle2);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);

	}

	public void clickOnDownloadSelectedDocuments() {
		insertIntoMultipleFrame();
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingDownloadSelectedDocumentsButton);
		clickElement(DocListingPage.img_DocListingDownloadSelectedDocumentsButton);
	}

	public void selectAllCheckboxAndDownload() throws IOException, InterruptedException {
		List<WebElement> downloadCheckList = findElements(DocListingPage.css_DownloadDocumentsCheckboxList);
		for (WebElement e : downloadCheckList) {
			if (!e.isSelected() && !e.getAttribute("id").equalsIgnoreCase("isFolderStructure")
					&& !e.getAttribute("id").equalsIgnoreCase("isExtractFiles")
					&& !e.getAttribute("id").equalsIgnoreCase("isLockForEditing")) {
				e.click();
			}
		}
		clickElementAndWait(DocListingPage.btn_DownloadDocumentsDownload);
	}

	public void downloadZipDocumentIntoLocal() throws IOException, InterruptedException {
		outputZipFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + zipDownloadFile
				+ epoch;
		inputZipFile = outputZipFolder + ".zip";

		waitUntilElementIsInvisible(DocListingPage.btn_DownloadDocumentsInvisiableZipDownload);
		waitUntilElementIsDisplayed(DocListingPage.btn_DownloadDocumentsVisibleZipDownload);
		clickElement(DocListingPage.btn_DownloadDocumentsVisibleZipDownload);
		//autoItUtils.downloadAutoIt(inputZipFile, nodeIP);
		sysUtils.waitForMultipleFileDownload(nodeIP, inputZipFile);
		closeCurrentWindow();
		switchPreviousWindow(superWindow);
	}

	public void zipIntoUnZip() {
		unZipFile(inputZipFile, createDirectory(outputZipFolder));
	}

	public void getFileNamesFromLocalFolder() {
		StringBuilder parentFileSet = new StringBuilder();
		parentFileSet.append(parentFileName).append("_").append(parentFileTitle).toString().trim();

		File folder = new File(outputZipFolder);
		File[] listOfFiles = folder.listFiles();
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (!(listOfFiles[i].getName().contains(parentFileSet))) {
					downloadFileList2.add(listOfFiles[i].getName());
				} else
					downloadFileList2.add(listOfFiles[i].getName().replace(parentFileSet, fileNameReplacement.trim()));
			}
		}

	}

	public void verifyDownloadedFiles() {
		log.info("Application File List: " + downloadFileList1);
		log.info("Local File List: " + downloadFileList2);

		if (downloadFileList1.size() == downloadFileList2.size()) {
			boolean flag = false;
			for (String webFile : downloadFileList1) {
				for (String LocalFile : downloadFileList2) {
					if (LocalFile.contains(webFile)) {
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
				if (!flag) {
					log.error("Files match failed");
					Assert.assertTrue(false);
				}
			}
		} else {
			log.error("File list size not Matched");
			Assert.assertTrue(false);
		}
	}

	public void deleteZipFileAndUnzipFolder() throws IOException {
		sysUtils.deleteDirectory(outputZipFolder);
		sysUtils.deleteFile(inputZipFile);
	}

	public void selectAllCheckboxWithExtractFiles() throws IOException, InterruptedException {
		List<WebElement> checkList = new ArrayList<WebElement>();
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentParentDocument));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentAssociatedFiles));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentAssociatedDocuments));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentAttachedDocuments));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentRebameWithDocRef));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentAppendDocTitle));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentAppenedIssNo));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentAppenedRevNo));
		checkList.add(findElement(DocListingPage.chk_DownloadDocumentExtractFilesDownload));

		for (WebElement e : checkList) {
			try {
				if (!e.isSelected()) {
					e.click();
					waitForCompletePageLoad();
				}
			} catch (StaleElementReferenceException ex) {
				log.error("stale element reference while selecting all checkbox in extract file");
			}

		}
		waitUntilElementIsDisplayed(DocListingPage.btn_DownloadDocumentBrowse);
	}

	public void selectPathAndClickOnDownload() throws IOException, InterruptedException {
		extractDownloadFolderPath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim()
				+ extractDownloadFolder;

		waitForCompletePageLoad();
		clickElementAndWait(DocListingPage.btn_DownloadDocumentBrowse);
		createDirectory(extractDownloadFolderPath);
		autoItUtils.uploadFileAutoIt(ResourceHandler.loadProperty("download.test.file.path") + extractDownloadFolder,
				nodeIP);
		waitForCompletePageLoad();
		clickElementAndWait(DocListingPage.btn_DownloadDocumentsDownload);
	}

	public void waitForCreatingBatchFile() throws InterruptedException {
		waitUntilElementIsDisplayed(DocListingPage.lbl_DownloadDocumentExtractFiles);
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle1);
	}

	public void getFilesNameIntoLocalDirectoryFolder() {
		StringBuilder parentFileSet = new StringBuilder();
		parentFileSet.append(parentFileName).append("_").append(parentFileTitle).toString().trim();

		File folder = new File(extractDownloadFolderPath);
		File[] listOfFiles = folder.listFiles();
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (!(listOfFiles[i].getName().contains(parentFileSet))) {
					downloadFileList2.add(listOfFiles[i].getName());
				} else
					downloadFileList2.add(listOfFiles[i].getName().replace(parentFileSet, fileNameReplacement.trim()));
			}
		}

	}

	public void deleteAllFilesIntoFolder() {
		extractFilesFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + extractFolder;
		File folder = new File(extractFilesFolder);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			try {
				if (listOfFiles[i].isFile()) {
					listOfFiles[i].delete();
				}
			} catch (Exception e) {
				log.error("failure while deleting all files into folder");
			}
		}
	}

	public void verifyNewTabWindow() {
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void verifyCommentDetailsWindow() {
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitForCompletePageLoad();
	}

	public void closeMultipleOpenedWindow() {
		int totalTabs;
		ArrayList<String> tabs = new ArrayList<String>(getWindowHandles());
		totalTabs = tabs.size() - 1;
		for (i = totalTabs; i > 0; i--) {
			getWebDriver().switchTo().window(tabs.get(i));
			closeCurrentWindow();
		}
		getWebDriver().switchTo().window(tabs.get(0));
	}

	public void closeFileOpenedWindow() {
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void insertIntoMultipleFrame() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
	}
}