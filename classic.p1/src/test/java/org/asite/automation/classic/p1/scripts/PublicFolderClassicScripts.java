package org.asite.automation.classic.p1.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicWSLandingLocators.WSLandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class PublicFolderClassicScripts extends ClassicCommonAppMethods {

	private String directAccessLink;
	private String zipDownloadFile = "AutomationZipFile";
	private List<String> expecteddownloadFileList = new ArrayList<String>();
	private List<String> actualdownloadFileList = new ArrayList<String>();
	private String outputZipFolder = null;
	private String inputZipFile = null;

	public void clickOnWorkspace() {
		log.info("Tests covered in pre-conditions");
	}

	public void verifyWorkspaceHomePage(String projectName) {
		waitUntilElementIsDisplayed(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteHeaderFrame);
		waitUntilElementIsDisplayed(WSLandingPage.lbl_WorkspaceHeaderName);
		Assert.assertTrue(getElementText(WSLandingPage.lbl_WorkspaceHeaderName).equalsIgnoreCase(projectName));
	}

	public void clickOnFolderWithTitle(String folderTitle) {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(WSLandingPage.lnk_PublicFolder);
		Assert.assertTrue(getElementText(WSLandingPage.lnk_PublicFolder).trim().equalsIgnoreCase(folderTitle.trim()));
		clickElementAndWait(WSLandingPage.lnk_PublicFolder);
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderTitle);
		waitForCompletePageLoad();
		try {
			clickFolderWithTitle(folderTitle);
		} catch (Exception e) {

			log.error("ERROR: failure while clicking folder title");

		}
		waitForCompletePageLoad();
	}

	public void verifyDocumentListingPage(String folderTitle) {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lbl_DocListingFolderPath);
		waitUntilElementContainsText(DocListingPage.lbl_DocListingFolderPath, folderTitle);
		Assert.assertTrue(getElementText(DocListingPage.lbl_DocListingFolderPath).contains(folderTitle));
	}

	public void selectEditFolderFromFolderMenu(String editFolderTitle) {
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingFolderMenu);

		for (int index = 0; index < 5; index++) {
			try {
				mouseHoverAndClickElement(DocListingPage.img_DocListingFolderMenu,
						DocListingPage.img_DocListingFolderMenuEditFolder);
				waitForCompletePageLoad();
				if (isDisplayed(DocListingPage.lnk_DocListingEditFolderDirectAccess))
					break;
			} catch (Throwable t) {
				log.error("element found missing after edit folder operation");
			}
		}

	}

	public void copyDirectAccessURL() {
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingEditFolderDirectAccess);
		directAccessLink = getElementText(DocListingPage.lnk_DocListingEditFolderDirectAccess);
		log.info("public folder direct access link: " + directAccessLink);
	}

	public void logoutFromClassic() {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteHeaderFrame);
		signOut();
	}

	public void openPageWithCopiedURL() {
		for (int index = 0; index < 5; index++) {
			try {
				getWebDriver().get(directAccessLink);
				waitForCompletePageLoad();
				switchDefault();
				switchIframe(LandingPage.frm_AsiteWorkingFrame);
				switchIframe(LandingPage.frm_AsiteDirectAccessContent);
				if (isDisplayed(DocListingPage.lbl_DocListingDirectAccessPageWorkspace))
					break;
			} catch (Throwable t) {
				log.error("Could not load public folder URL in try " + index);
			}
		}

	}

	public void verifyPublicFolder(String projectName) {
		waitUntilElementIsDisplayed(DocListingPage.lbl_DocListingDirectAccessPageWorkspace);
		Assert.assertTrue(getElementText(DocListingPage.lbl_DocListingDirectAccessPageWorkspace).equalsIgnoreCase(
				projectName));
	}

	public void selectMultipleFiles() {
		clickElementAndWait(DocListingPage.chk_DocListingDirecAccessCheckAll);
		List<WebElement> documentRefs = findElements(DocListingPage.css_DocListingDocRefListPublic);
		for (WebElement docref : documentRefs) {
			String fileLst[] = docref.getText().split("\\.");
			expecteddownloadFileList.add(fileLst[0]);
			log.info("expecteddownloadFileList :" + expecteddownloadFileList);
		}
	}

	public void clickDownloadSelectedButton(String downloadSelected) {
		waitUntilElementIsDisplayed(DocListingPage.btn_DocListingDirectAccessDownloadSelected);
		Assert.assertTrue(getToolTipText(DocListingPage.btn_DocListingDirectAccessDownloadSelected).contains(
				downloadSelected));
		clickElementAndWait(DocListingPage.btn_DocListingDirectAccessDownloadSelected);
	}

	public void verifyDownloadWindowOpens(String downloadWindowTitle) {
		waitForSwitchWindow(2);
		switchWindow();
		waitUntilElementIsDisplayed(DocListingPage.lbl_DownloadDocumentTitle);
		Assert.assertTrue(getElementText(DocListingPage.lbl_DownloadDocumentTitle)
				.equalsIgnoreCase(downloadWindowTitle));

	}

	public void selectAllCheckboxAndDownload() throws IOException, InterruptedException {
		outputZipFolder = ResourceHandler.loadProperty("remote.download.file.path").trim() + zipDownloadFile + epoch;
		inputZipFile = nodeIP + outputZipFolder + ".zip";
		clickElementAndWait(DocListingPage.chk_DownloadDocumentRebameWithDocRef);
		clickElementAndWait(DocListingPage.chk_DownloadDocumentAppendDocTitle);
		clickElementAndWait(DocListingPage.chk_DownloadDocumentAppenedIssNo);
		clickElementAndWait(DocListingPage.chk_DownloadDocumentAppenedRevNo);
		clickElementAndWait(DocListingPage.btn_DownloadDocumentsDownload);
		waitUntilElementIsClickable(DocListingPage.btn_DownloadDocumentZipDownload);
		clickElement(DocListingPage.btn_DownloadDocumentZipDownload);
//		autoItUtils.downloadAutoIt(inputZipFile, nodeIP);
		sysUtils.waitForMultipleFileDownload(nodeIP, inputZipFile, dateUtils.getCurrentTimeInMilliSeconds().toString());
	}

	public void verifyZipDownload() throws InterruptedException {
		File file = new File(inputZipFile);
		waitUntilFileIsDownloaded(file);
	}

	public void zipIntoUnZip() {
		unZipFile(inputZipFile, createDirectory(nodeIP + outputZipFolder));
		getFileNamesFromLocalFolder();
	}

	public void getFileNamesFromLocalFolder() {
		File folder = new File(nodeIP + outputZipFolder);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println(listOfFiles[i].getName());
				actualdownloadFileList.add(listOfFiles[i].getName());
			}
		}
	}

	public void verifyFilesNameIntoSystem() throws IOException {
		if (expecteddownloadFileList.size() == actualdownloadFileList.size()) {
			boolean flag = false;
			for (String expectedFile : expecteddownloadFileList) {
				for (String actualFile : actualdownloadFileList) {
					if (actualFile.contains(expectedFile)) {
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
				if (!flag) {
					log.error("Files Not Matched");
					Assert.assertTrue(false);
				}
			}
		} else {
			log.error("List size not Matched");
			Assert.assertTrue(false);
		}

		deleteZipFileAndUnzipFolder();
	}

	public void deleteZipFileAndUnzipFolder() throws IOException {
		sysUtils.deleteDirectory(outputZipFolder);
		sysUtils.deleteFile(inputZipFile);
	}
}
