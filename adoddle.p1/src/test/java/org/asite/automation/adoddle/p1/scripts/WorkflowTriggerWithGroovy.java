package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;

public class WorkflowTriggerWithGroovy extends AdoddleCommonAppMethods {

	private String				WF_TestFile1		= "Groovy_WF_TestFile" + epoch+ 1;
	private String				WF_TestFile2		= "Groovy_WF_TestFile" + epoch+ 2;
	private String				projectFolder		= "Automation_WF_Groovy";
	private String				groovyDocTitle		= "Automation_WF_Groovy";
	private String				groovyfileRev		= "097531";
	private String				groovyRevisionNotes	= "Auto_Revision_Notes";
	private static List<String>	docRefList			= new ArrayList<String>();
	private List<String>		fileList			= new ArrayList<String>();
	public static Logger		log					= AutomationLogger.getInstance().getLogger(WorkflowTriggerWithGroovy.class);

	public void focusProjectFolder(String folderName) {
		clickElementWithText(folderName);
	}

	public void uploadMultipleFiles() throws IOException, InterruptedException {
		docRefList = sysUtils.getFileList(WF_TestFile1 + "," + WF_TestFile2);
		uploadMultipleFile();
	}

	public void bulkApplyCheckboxSelect() {
		if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply)) {
			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		}
	}

	public void mandatoryFilesAttributes() throws InterruptedException {
		bulkApplyCheckboxSelect();
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		enterDocRefText();
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		mouseHover(FilesTab.btn_PopUploadApplytoAll);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void enterDocRefText() throws InterruptedException {
		sendKeys(FilesTab.txt_DocRefFile1, WF_TestFile1);
		sendKeys(FilesTab.txt_DocRefFile2, WF_TestFile2);
	}

	public void waitAndRefreshWorkflowAttributes() {
		try {
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			navigateTab(LandingPage.lnk_Files);
			clickElementWithText(projectFolder);
		}
		catch (Exception e) {

			log.error("ERROR: Failed while refreshing page");
		}
	}

	public void postUploadFileValidation(String wStatus, String wStage) {
		log.info("DocRefListSize1:: " + docRefList.size());
		for (String str : docRefList) {
			searchFiles(str);
			waitUntilElementContainsText(FilesTab.lnk_workflowStatus, wStatus);
			waitUntilElementContainsText(FilesTab.ele_workflowStage, wStage);
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(wStatus));
			Assert.assertTrue(getElementText(FilesTab.ele_workflowStage).equalsIgnoreCase(wStage));
		}

	}

	public void validateActionAndClick(String fAction, String cAction) {
		log.info("DocRefListSize2:: " + docRefList.size());
		clickElementWithText(projectFolder);
		for (String str : docRefList) {
			searchFiles(str);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fAction);
			clickElementAndWait(FilesTab.lnk_FilesFirstAction);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
			Assert.assertTrue(isDisplayed(FilesTab.lnk_PopupAcknowledgementReceipt));
			clickElementAndWait(FilesTab.lnk_PopupAcknowledgementReceipt);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
		}

	}

	public void validatePopUp() {
		log.info("Covered in Above Definiton");
	}

	public void performFileAction() {
		log.info("Covered in Above Definiton");
	}

	public void postFileAttributesValidation() {
		log.info("DocRefListSize3:: " + docRefList.size());
		for (String str : docRefList) {
			navigateTab(LandingPage.lnk_Files);
			clickElementWithText(projectFolder);
			searchFiles(str);
			waitUntilElementContainsText(FilesTab.lbl_UploadedFileDocTitle, groovyDocTitle);
			waitUntilElementContainsText(FilesTab.lnk_UploadedFileRev, groovyfileRev);
			waitUntilElementContainsText(FilesTab.lnk_UploadedFileRevisionNotes, groovyRevisionNotes);
			Assert.assertTrue(getElementText(FilesTab.lbl_UploadedFileDocTitle).contains(groovyDocTitle));
			Assert.assertTrue(getElementText(FilesTab.lnk_UploadedFileRev).contains(groovyfileRev));
			Assert.assertTrue(getElementText(FilesTab.lnk_UploadedFileRevisionNotes).contains(groovyRevisionNotes));
		}

	}

	public void uploadMultipleFile() throws IOException, InterruptedException {
		List<String> fileArrayList = new ArrayList<String>();
		fileArrayList.add(WF_TestFile1);
		fileArrayList.add(WF_TestFile2);
		int i = 1;
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (String fileName : fileArrayList) {
			log.info("FileList: " + fileList.size());
			fileName = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + epoch + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			fileList.add(fileName);
			log.info("fileName :" + fileName);
			i++;
		}
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
		collectionDataMap.put("Uploaded Files", fileList.toString());
	}

	public void cleanUpOperation() {
		deactivateTestData();
	}

	public void deactivateTestData() {
		log.info("DocRefListSize4:: " + docRefList.size());
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(projectFolder);
		try {
			for (String str : docRefList) {
				deactivateFile(str);
				waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();
			}
		}
		catch (Throwable e) {
			log.error("Failure in CleanUp operation");
		}
	}

}
