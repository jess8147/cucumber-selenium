package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;

import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.openqa.selenium.WebElement;

public class DownloadMulitipleTypesOfFilesScripts extends AdoddleCommonAppMethods
{
	private String fileExtension;
	private File downloadedFile;
	
	public void searchDownloadAndVerifyFileWithFileSize(String searchFileList) throws IOException, InterruptedException
	{
		collectionDataMap.put("Downloaded File List", searchFileList.toString());
		
		for (String file : searchFileList.split(",")) {
			waitForCompletePageLoad();
			searchFiles(file);
			AutomationAssert.verifyTrue(findElements(FilesTab.css_NumberOfFiles).size() == 2);
			
			int i=0;

			for (WebElement webFile : findElements(FilesTab.css_NumberOfFiles)) {
				String fileTitle = getElementText(FilesTab.lnk_DocListingFirstFileName);
				waitForCompletePageLoad();
				AutomationAssert.verifyTrue(webFile.getText().contains(file));
				fileExtension = ".".concat(webFile.getText().substring(webFile.getText().lastIndexOf(".") + 1));
				log.info("fileExtension : "+fileExtension);
				waitUntilListElementIsClickable(findElements(FilesTab.css_FilesTabFileTypeImageList).get(i));
				if(findElements(FilesTab.css_FilesTabFileTypeImageList).get(i).getAttribute("src").contains("static")) {
					log.info(": Into Linked File :");
					findElements(FilesTab.css_FilesTabFileTypeImageList).get(i).click();
				}
				else {
					log.info(": Into Actual File :");
					findElements(FilesTab.css_FilesTabFileTypeImageList).get(i).click();
				}
				downloadAndVerifyDocumentInLocalDirectory(fileTitle);
				getAndCompareFileSize(nodeIP + ResourceHandler.loadProperty("remote.download.multipletype.file.path").trim() + file + fileExtension, downloadedFile.toString());
				i++;
			}
		}
	}
	
	public void downloadAndVerifyDocumentInLocalDirectory(String fileName) throws IOException, InterruptedException
	{
		waitForCompletePageLoad();
		String downloadFilePath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + dateUtils.getEpoch() + fileExtension;
		downloadedFile = new File(sysUtils.waitForSingleDirectFileDownload(nodeIP, fileName, downloadFilePath));
	}
	
	public void getAndCompareFileSize(String file1, String file2)
	{
		log.info("Test Data File Size : " + sysUtils.getFileSize(file1));
		log.info("Downloaded File Size : "+sysUtils.getFileSize(file2));
		AutomationAssert.verifyTrue(Math.round(sysUtils.getFileSize(file1)) == Math.round(sysUtils.getFileSize(file2)));
	}
}