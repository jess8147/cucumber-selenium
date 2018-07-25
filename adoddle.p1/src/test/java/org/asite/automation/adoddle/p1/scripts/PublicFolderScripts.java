package org.asite.automation.adoddle.p1.scripts;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PublicFolderScripts extends AdoddleCommonAppMethods {

	private List<String>	downloadFileListIntoWeb		= new ArrayList<String>();
	private List<String>	downloadFileListIntoLocal	= new ArrayList<String>();
	private String			outputZipFolder				= nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + epoch.trim();
	private String			inputZipFile				= outputZipFolder + ".zip";
	public static Logger	log							= AutomationLogger.getInstance().getLogger(PublicFolderScripts.class);
	private String copiedURL;

	public void clickOnFolderAndSelectOption(String folderName, String menuOption) {
		contextClickWithText(folderName.trim());
		clickContextMenuOptionWithText(menuOption);
	}

	public void clickOnPopupClipIcon() {
		clickElement(FilesTab.lnk_EditFolderClipIcon);
	}

	public void getclipboardText() throws AWTException, HeadlessException, UnsupportedFlavorException, IOException, InterruptedException {
		waitUntilAlertIsPresent();
		Alert alert = getWebDriver().switchTo().alert();
		autoItUtils.handleAlertWindowPopup("Copy", nodeIP);
		//alert.sendKeys(Keys.CONTROL + "c");
		try {
			alert.dismiss();
		}
		catch (Throwable t) {
			log.error("Alert was not found");
		}
	}

	public void clickOnPopupCancel() throws InterruptedException {
		clickElementAndWait(FilesTab.btn_EditFolderCancel);
		
		/*** Get CopiedURL ***/
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(FilesTab.txt_FilesFilterInput);
		clear(FilesTab.txt_FilesFilterInput);
		getWebDriver().findElement(FilesTab.txt_FilesFilterInput).sendKeys(Keys.CONTROL + "v");
		copiedURL = getElementAttributeValue(FilesTab.txt_FilesFilterInput, "value");
		log.info("copiedURL : "+copiedURL);
		clear(FilesTab.txt_FilesFilterInput);
	}

	public void hitCopiedUrl() throws IOException {
//		autoItUtils.handleAlertWindowPopup("Paste", nodeIP);
		navigateURL(copiedURL);
		waitForCompletePageLoad();
	}

	public void verifyOpenPublicFolder(String projectName) {
		Assert.assertTrue(getElementText(FilesTab.lbl_PublicFolderText).contains(projectName));
	}

	public void selectMultipleFiles() {
		 
		waitUntilElementIsDisplayed(FilesTab.ele_PublicFoldersListingSetting); 
		clickElementAndWaitForElement(FilesTab.ele_PublicFoldersListingSetting, FilesTab.ele_PublicFoldersListingSettingPopUp);
		selectByVisibleText(FilesTab.dd_PublicFolderListingCountSetting, "50");
		waitForCompletePageLoad();
		
		if (isDisplayed(FilesTab.chk_MultiFilesSelectionCheckbox)) {
			if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox)) {
				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			}
		}
		List<WebElement> files = findElements(FilesTab.css_PublicFolderFilesDocRef);
		for (WebElement e : files) {
			String fileLst[] = e.getText().split("\\.");
			downloadFileListIntoWeb.add(fileLst[0]);
		}
		log.info("Public folder files web List :" + downloadFileListIntoWeb.toString());
		collectionDataMap.put("Expected File List", downloadFileListIntoWeb.toString());
	}

	public void clickOnDownload() {
		clickElementAndWait(FilesTab.lnk_Download);
	}

	public void createBatchFile() {
		waitUntilElementIsInvisible(FilesTab.pop_BatchFiles);
	}

	public void downloadZipDocumentIntoLocal() throws InterruptedException, IOException {
		//autoItUtils.downloadAutoIt(inputZipFile, nodeIP);
		sysUtils.waitForMultipleFileDownload(nodeIP, inputZipFile, dateUtils.getCurrentTimeInMilliSeconds().toString());
	}

	public void zipIntoUnZip() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		sysUtils.unZipFile(inputZipFile, sysUtils.createDirectory(outputZipFolder));
	}

	public void getFileNamesFromLocalFolder() {
		File folder = new File(outputZipFolder);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				downloadFileListIntoLocal.add(listOfFiles[i].getName());
			}
		}
		log.info("Public folder downloaded files List :" + downloadFileListIntoLocal);
		collectionDataMap.put("Actual File List", downloadFileListIntoLocal.toString());


	}

	public void verifyFilesNameIntoSystem() {
		boolean flag = false;

		if (downloadFileListIntoWeb.size() == downloadFileListIntoLocal.size()) {
			for (String webFile : downloadFileListIntoWeb) {
				for (String LocalFile : downloadFileListIntoLocal) {
					if (LocalFile.contains(webFile)) {
						log.info("Local File Verified :" + LocalFile);
						log.info("Web File Verified :" + webFile);
						flag = true;
						break;
					}
					else
						flag = false;
				}

				if (!flag) {
					log.error("Web file string not found in downloaded files");
					Assert.assertTrue("Web file string not found in downloaded files", flag);
				}
			}
		}
		else {
			log.error("List size not Matched");
			Assert.assertTrue("List size not Matched", false);
		}
	}

	public void deleteZipFileAndUnzipFolder() throws IOException {
		sysUtils.deleteDirectory(outputZipFolder);
		sysUtils.deleteFile(inputZipFile);
	}
}
