package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Resource;

public class DownloadFileScripts extends AdoddleCommonAppMethods {

	private int					i;
	private List<String>		lstFileValSystem		= new ArrayList<String>();
	private List<String>		lstFileValLocal			= new ArrayList<String>();
	private String				extension, fileName1, testDataFolderName = "DownloadFiles";
	private String				parentWindow;
	private String				outputZipFolder			= nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + epoch.trim();
	private String				inputZipFile			= outputZipFolder + ".zip";
	private String				downloadFileReplaceMent	= "ParentFile";
	private String				downloadfileTitle		= "AutoDownloadFile_Title";
	private String				downloadFileAttachMent	= "AutoDownloadAttachmentFile";
	public static Logger		log						= AutomationLogger.getInstance().getLogger(DownloadFileScripts.class);

	public void clickOnType() throws InterruptedException {
		searchFiles("*pdf");
		fileName1 = getElementText(FilesTab.lnk_FileName);
		extension = ".".concat(fileName1.substring(fileName1.lastIndexOf(".") + 1));
		clickElementAndWait(FilesTab.lnk_FilesListingFirstFileTypeIcon);
		collectionDataMap.put("Download File", fileName1);
	}

	public void verifyDocumentInLocalDirectory() throws IOException, InterruptedException {
		String downloadPath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + epoch + extension;
		sysUtils.waitForSingleDirectFileDownload(nodeIP, fileName1, downloadPath);
		collectionDataMap.put("Downloaded File", downloadPath.toString());
	}

	public void clickOnProjectName(String dc) throws InterruptedException {
		clickElementWithText(dc.trim());
	}

	public void clickOnFileName() throws InterruptedException {
		searchFiles(ResourceHandler.loadProperty("download.test.downloadfile").trim());
		clickLinkWithText(ResourceHandler.loadProperty("download.test.downloadfile").trim());
	}

	public void gotoFileOpenWindow() {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void gotoDiscussionsTab() {
		waitForCompletePageLoad();
		if(!fileBetaViewFlag)
		clickElementAndWait(FilesTab.lnk_FileViewLHDiscussionBlock);
	}

	public void gotoAttachmentAndAssociations() {
		if(fileBetaViewFlag){
			List<WebElement> attachmentList = new ArrayList<WebElement>();

			waitForCompletePageLoad();
			clickElementAndWait(FilesTab.img_FileBetaViewCommentAttachmentAssociationLink);
			waitUntilElementIsDisplayed(FilesTab.pop_FileBetaViewAttachmentAndAssociationPopup);
			clickElementAndWait(FilesTab.pop_FileBetaViewAttachmentAndAssociationPopup);

			String[] headerLinkArray = { "Attachments", "Files" };
			for (String link : headerLinkArray) {

				clickElementAndWait(By.cssSelector("div[class='file-comment-wrapper'] li div[class*='clearfix'] div[class='discussion-footer'] ul[class*='nav '] li[class='ng-scope active'][ng-click*='"+ link +"']"));

				if (getElementText(FilesTab.lnk_BetaViewPopAttachmentAndAssociationActiveTab).contains("Attachments")) {

					attachmentList = findElements(FilesTab.css_BetaViewPopAttachmentAndAssociationFilesNameList);
					for (WebElement file : attachmentList) {
						lstFileValSystem.add(file.getText().substring(0, file.getText().lastIndexOf('.')));
					}
					clickElementAndWait(FilesTab.css_BetaViewPopFileAssociationTab);
					log.info("lstFileValSystem-1 : " + lstFileValSystem);
				}
				else {

					attachmentList = findElements(FilesTab.css_BetaViewPopAttachmentAndAssociationDocRefList);
					for (WebElement file : attachmentList) {
						lstFileValSystem.add(file.getText());
					}
					log.info("lstFileValSystem-2 : " + lstFileValSystem);
				}
			}
			log.info("lstFileValSystem : " + lstFileValSystem);
		}
		else{
				List<WebElement> attachmentList = new ArrayList<WebElement>();

				waitForCompletePageLoad();
				clickElementAndWait(FilesTab.img_DownloadFilesCommentAttachmentAssociationLink);
				waitUntilElementIsDisplayed(FilesTab.pop_AttachmentAndAssociationPopup);

				String[] headerLinkArray = { "Attachment", "Files" };
				for (String link : headerLinkArray) {

					clickElementAndWait(By.xpath(".//div[@id='pageLayoutHeader']//div[@id]//a[contains(text(),'" + link + "')]"));

					if (getElementText(FilesTab.lnk_PopAttachmentAndAssociationActiveTab).contains("Attachments")) {

						attachmentList = findElements(FilesTab.css_PopAttachmentAndAssociationFilesNameList);
						for (WebElement file : attachmentList) {
							lstFileValSystem.add(file.getText().substring(0, file.getText().lastIndexOf('.')));
						}
						log.info("lstFileValSystem-1 : " + lstFileValSystem);
					}
					else {

						attachmentList = findElements(FilesTab.css_PopAttachmentAndAssociationDocRefList);
						for (WebElement file : attachmentList) {
							lstFileValSystem.add(file.getText());
						}
						log.info("lstFileValSystem-2 : " + lstFileValSystem);
					}
				}
				log.info("lstFileValSystem : " + lstFileValSystem);
			}
		}

	public void closeFileOpenedWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void gotoDownload() {
		clickElementAndWait(FilesTab.lnk_FileDownload);
	}

	public void downloadDocumentsCheckboxSelect() {
		List<WebElement> checkList = findElements(FilesTab.chk_cssDocuments);
		for (WebElement e : checkList) {
			if (e.isDisplayed() && !e.isSelected()) {
				e.click();
			}
		}

		if (isDisplayed(FilesTab.lbl_DiscussionsAndMarkups)) {
			checkList = findElements(FilesTab.chk_cssDiscussionsAndMarkups);
			for (WebElement e : checkList) {
				if (e.isDisplayed() && !e.isSelected()) {
					e.click();
				}
			}
		}

		checkList = findElements(FilesTab.chk_cssAppendNameString);
		for (WebElement e : checkList) {
			if (e.isDisplayed() && !e.getAttribute("id").equalsIgnoreCase("isFolderStructure") && !e.isSelected()) {
				e.click();
			}
		}
	}

	public void clickOnDownload() throws InterruptedException {
		clickElementAndWait(FilesTab.btn_Download);
	}

	public void createBatchFile() {
		waitUntilElementIsInvisible(FilesTab.pop_BatchFiles);
	}

	public void downloadZipDocumentIntoLocal() throws InterruptedException, IOException {
		sysUtils.waitForMultipleFileDownload(nodeIP, inputZipFile);
	}

	public void zipIntoUnZip() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		sysUtils.unZipFile(inputZipFile, sysUtils.createDirectory(outputZipFolder));
	}

	public void getFileNamesFromLocalFolder() {
		String parentFile = ResourceHandler.loadProperty("download.test.downloadfile").trim();
		String[] parentFileSplit = parentFile.split("\\.");
		StringBuilder parentFileSet = new StringBuilder();
		parentFileSet.append(parentFileSplit[0]).append("_").append(downloadfileTitle.trim());

		File folder = new File(outputZipFolder);
		File[] listOfFiles = folder.listFiles();
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if ((listOfFiles[i].getName().contains(parentFileSet))) {
					lstFileValLocal.add(listOfFiles[i].getName().replace(parentFileSet, downloadFileReplaceMent.trim()));
				}
				lstFileValLocal.add(listOfFiles[i].getName());
			}
			log.info("lstFileValLocal :" + lstFileValLocal);
		}
	}

	public void verifyFilesNameIntoSystem() {
		boolean flag = false;
		lstFileValSystem.add(downloadFileReplaceMent.trim());
		lstFileValSystem.add(downloadFileAttachMent.trim());
		log.info("lstFileValSystem :" + lstFileValSystem);

		for (String attachItems : lstFileValSystem) {
			for (String attachLocalItems : lstFileValLocal) {
				if (attachLocalItems.contains(attachItems)) {
					log.info("Web File Verified :" + attachItems);
					log.info("Local File Verified :" + attachLocalItems);
					flag = true;
					break;
				}
				else {
					flag = false;
				}
			}
			if (!flag) {
				AutomationAssert.verifyTrue(false);
			}
		}

		verifyDownloadedFileSize();
	}

	public void verifyDownloadedFileSize() {
		String localFolder = nodeIP + ResourceHandler.loadProperty("remote.download.testdata.filespath").trim();
		String downloadedFolder = outputZipFolder;

		File localFile = new File(localFolder);
		File downloadedFile = new File(downloadedFolder);

		File[] localFilesArray = localFile.listFiles();
		File[] downloadedFilesArray = downloadedFile.listFiles();

		File oldFile, newFile;
		@SuppressWarnings("unused")
		boolean flag = false;
		for (File downloadFile : downloadedFilesArray) {

			for (String webFile : lstFileValSystem) {

				if (downloadFile.getName().contains(webFile)) {

					oldFile = new File(downloadedFolder + "/" + downloadFile.getName());
					newFile = new File(downloadedFolder + "/" + webFile + AdoddleCommonStringPool.DOT_STRING + downloadFile.getName().substring(downloadFile.getName().lastIndexOf(".") + 1));

					log.info("Downloaded File Rename Successfully : " + oldFile.renameTo(newFile));
					flag = true;
					break;
				}
				else {
					log.info("Local File : " + downloadFile.getName() + " ::NOT MATCH:: " + "Web File : " + webFile);
					flag = false;
				}
			}
		}

		File[] downloadedFilesArray1 = downloadedFile.listFiles();
		Arrays.sort(downloadedFilesArray1);
		Arrays.sort(localFilesArray);

		List<String> downloadFileList = new ArrayList<String>();
		List<String> localFileList = new ArrayList<String>();

		for (File file : downloadedFilesArray1) {
			downloadFileList.add(file.getName());
		}
		log.info("downloadFileList : " + downloadFileList);

		for (File file : localFilesArray) {
			localFileList.add(file.getName());
		}
		log.info("localFileList : " + localFileList);

		i = 0;
		for (File file : downloadedFilesArray1) {

			log.info("Downloaded File : " + file.getName());
			log.info("Local File : " + localFilesArray[i].getName());
			log.info("Downloaded File Size : " + sysUtils.getFileSize(file.toString()));
			log.info("Local File Size : " + sysUtils.getFileSize(localFilesArray[i].toString()));
			AutomationAssert.verifyTrue(sysUtils.getFileSize(file.toString()) == sysUtils.getFileSize(localFilesArray[i].toString()));
			i++;
		}
	}

	public void deleteZipFileAndUnzipFolder() throws IOException {
		/*
		 * sysUtils.deleteDirectory(outputZipFolder); sysUtils.deleteFile(inputZipFile);
		 */

		String currentDate = dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST");
		log.info("Today's Date : " + currentDate);

		if (currentDate.startsWith("01-")) {
			log.info("Need To Flush Data Cleanup : " + currentDate);

			File dirPath = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim());
			log.info("dirPath : " + dirPath);

			for (File file : dirPath.listFiles()) {
				if (!file.isDirectory())
					file.delete();
			}

			File[] files = dirPath.listFiles();
			if (files.length == 0)
				log.info("The directory is empty");
			else {
				for (File aFile : files) {
					if (!aFile.getName().equalsIgnoreCase(testDataFolderName)) {
						log.info("Folder Name : " + aFile.getName());
						sysUtils.deleteDirectory(aFile.toString());
					}
				}
			}
		}
		else
			log.info("Today's not First Day of Month : " + currentDate);
	}
}
