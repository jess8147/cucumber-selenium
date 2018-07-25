package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class UploadFilesScripts extends AdoddleCommonAppMethods {
	private String				createFile1;
	private String				createFile2;
	private String				attachFile1;
	private String				attachFile2;
	private String				pdfExtension			= ".pdf";
	private Map<String, String>	uploadFileList1			= new HashMap<String, String>();
	private Map<String, String>	uploadFileList2			= new HashMap<String, String>();
	private Map<String, String>	uploadFileListValue1	= new HashMap<String, String>();
	private Map<String, String>	uploadFileListValue2	= new HashMap<String, String>();
	private double				file1Size, file2Size;
	private List<String>		fileList				= new ArrayList<String>();
	private List<String>		docRefList				= Arrays.asList("DocRef_File1", "DocRef_File2");
	public static Logger		log						= AutomationLogger.getInstance().getLogger(UploadFilesScripts.class);

	public void clickOnProjectAndFolderAndAddFiles(String projectName, String folderName, String addFiles) throws InterruptedException {
		clickElementWithText(projectName.trim());
		clickElementWithText(folderName);
		clickElementAndWait(FilesTab.btn_AddFiles);

	}

	public void clickOnSelectFilesAndUpload() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile1 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + pdfExtension, nodeIP).trim();
		createFile2 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + pdfExtension, nodeIP).trim();
		file1Size = sysUtils.getFileSize(createFile1.toString());
		file2Size = sysUtils.getFileSize(createFile1.toString());
		log.info("first file created as: " + createFile1 + " with size " + file1Size);
		log.info("second file created as :" + createFile2 + " with size " + file2Size);
		fileList = sysUtils.getFileList(createFile1 + "," + createFile2);
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
	}

	public void bulkApplyCheckboxSelect() {
		if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply)) {
			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		}
	}

	public void enterDocRefText() throws InterruptedException {
		sendKeys(FilesTab.txt_DocRefFile1, docRefList.get(0) + dateUtils.getCurrentDateTimeWithZone("yyyyMMddHHmmss", "IST"));
		sendKeys(FilesTab.txt_DocRefFile2, docRefList.get(1) + dateUtils.getCurrentDateTimeWithZone("yyyyMMddHHmmss", "IST"));
	}

	public void enterMendatoryAttributes() throws InterruptedException, IOException {
		bulkApplyCheckboxSelect();
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		enterDocRefText();
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		mouseHover(FilesTab.btn_PopUploadApplytoAll);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
		attachExternalFiles();
	}

	public void getUploadFilesAttributesValue() {
		uploadFileList1.put("filename", getElementText(FilesTab.lbl_UploadFileName1));
		clickElement(FilesTab.txt_DocRefFile1);
		uploadFileList1.put("docref", findElement(FilesTab.txt_DocRefFile1).getAttribute("title"));
		uploadFileList1.put("rev", "1");
		clickElementAndWait(FilesTab.txt_DocTitleFile1);
		uploadFileList1.put("doctitle", findElement(FilesTab.txt_DocTitleFile1).getAttribute("title"));
		uploadFileList1.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile1)).getFirstSelectedOption().getText());
		uploadFileList1.put("status", new Select(findElement(FilesTab.drp_StatusFile1)).getFirstSelectedOption().getText());

		uploadFileList2.put("filename", getElementText(FilesTab.lbl_UploadFileName2));
		clickElement(FilesTab.txt_DocRefFile2);
		uploadFileList2.put("docref", findElement(FilesTab.txt_DocRefFile2).getAttribute("title"));
		uploadFileList2.put("rev", "1");
		clickElementAndWait(FilesTab.txt_DocTitleFile2);
		uploadFileList2.put("doctitle", findElement(FilesTab.txt_DocTitleFile2).getAttribute("title"));
		uploadFileList2.put("poi", new Select(findElement(FilesTab.drp_PurposeOfIssueFile2)).getFirstSelectedOption().getText());
		uploadFileList2.put("status", new Select(findElement(FilesTab.drp_StatusFile2)).getFirstSelectedOption().getText());

		log.info("uploadFileList1 :" + uploadFileList1);
		log.info("uploadFileList2 :" + uploadFileList2);
	}

	public void clickOnDistributeFiles(String DistributeFiles) {
		clickElementAndWait(By.xpath(".//*[@id='adv-btn-distribute' and text()='" + DistributeFiles.trim() + "']"));
	}

	public void enterDistributeGroupText() throws InterruptedException {
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, System.getProperty("primary.username"));
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.TAB);
	}

	public void clickOnUpload() {
		waitUntilElementIsDisplayed(FilesTab.btn_PopUploadFileDistributeUpload);
		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeUpload);
		if (isDisplayed(FilesTab.pop_Handle)) {
			clickElementAndWait(FilesTab.btn_PopupContinue);
		}
	}

	public void verifyUploadedFilesAttributesValue() throws InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append(strUtils.extractFileNameString(createFile1)).append(",").append(strUtils.extractFileNameString(createFile2));
		String[] uploadedFileList = builder.toString().split(",");
		collectionDataMap.put("Uploaded Files", uploadedFileList.toString());
		for (String file : uploadedFileList) {
			searchFiles(file);
			if (file.equalsIgnoreCase(uploadedFileList[0])) {
				uploadFileListValue1.put("filename", getElementText(FilesTab.lnk_UploadedFileName));
				uploadFileListValue1.put("docref", getElementText(FilesTab.lnk_UploadedFileDocRef));
				uploadFileListValue1.put("rev", getElementText(FilesTab.lnk_UploadedFileRev));
				uploadFileListValue1.put("doctitle", getElementText(FilesTab.lbl_UploadedFileDocTitle));
				uploadFileListValue1.put("poi", getElementText(FilesTab.lbl_UploadedFilePurposeOfIssue));
				uploadFileListValue1.put("status", getElementText(FilesTab.lnk_UploadedFileStatus));
				verifyAttachedFile();
				log.info("uploadFileListValue1 :" + uploadFileListValue1);
			}
			else {
				uploadFileListValue2.put("filename", getElementText(FilesTab.lnk_UploadedFileName));
				uploadFileListValue2.put("docref", getElementText(FilesTab.lnk_UploadedFileDocRef));
				uploadFileListValue2.put("rev", getElementText(FilesTab.lnk_UploadedFileRev));
				uploadFileListValue2.put("doctitle", getElementText(FilesTab.lbl_UploadedFileDocTitle));
				uploadFileListValue2.put("poi", getElementText(FilesTab.lbl_UploadedFilePurposeOfIssue));
				uploadFileListValue2.put("status", getElementText(FilesTab.lnk_UploadedFileStatus));
				verifyAttachedFile();
				log.info("uploadFileListValue2 :" + uploadFileListValue2);
			}
		}
	}

	public void verifyUploadFilesAndAttributes() throws InterruptedException, IOException {
		javaUtils.compareMapList(uploadFileList1, uploadFileListValue1);
		javaUtils.compareMapList(uploadFileList2, uploadFileListValue2);
	}

	public void downloadAndVerifySize() throws IOException, InterruptedException {
		searchFiles(strUtils.extractFileNameString(fileList.get(0)));
		String downloadFileName = getElementText(FilesTab.lnk_DocListingFirstFileName);
		clickElementAndWait(FilesTab.lnk_FilesListingFirstFileTypeIcon);
		File file1 = new File(nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path").trim() + downloadFileName);
		waitUntilFileIsDownloaded(file1);
		File file2 = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + dateUtils.getEpoch() + pdfExtension);
		file1.renameTo(file2);
		waitUntilFileIsDownloaded(file2);
		AutomationAssert.verifyTrue(! file1.exists());
		log.info("Uploaded file size: " + file1Size);
		log.info("Downloaded file size: " + sysUtils.getFileSize(file2.toString()));
		Assert.assertTrue(sysUtils.getFileSize(file2.toString()) == file1Size);
	}

	public void deleteCreatedFiles() throws IOException {
		try {
			deactivateFile(strUtils.extractFileNameString(createFile1));
			deactivateFile(strUtils.extractFileNameString(createFile2));
			sysUtils.deleteFile(createFile1);
			sysUtils.deleteFile(createFile2);
			sysUtils.deleteFile(attachFile1);
			sysUtils.deleteFile(attachFile2);
		}
		catch (Throwable t) {
			log.info("error while deactivating or deleting physical files");
		}
	}

	public void attachExternalFiles() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		attachFile1 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + ".txt").trim();
		attachFile2 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + ".txt").trim();
		List<String> fileList1 = sysUtils.getFileList(attachFile1);
		List<String> fileList2 = sysUtils.getFileList(attachFile2);
		uploadFileUsingKeys(FilesTab.btn_PopUploadFirstAttachFile, fileList1);
		uploadFileUsingKeys(FilesTab.btn_PopUploadSecondAttachFile, fileList2);
	}

	public void verifyAttachedFile() {
		Assert.assertTrue(isDisplayed(FilesTab.img_FileAttachment));
	}
}
