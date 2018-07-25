package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;

public class WorkflowBoundaryTimerEmailScripts extends AdoddleCommonAppMethods {

	private static String	fileDocRef1	= "AutoTest_WF_File1" + JavaUtils.getRandomNumber(14);
	private static String	fileDocRef2	= "AutoTest_WF_File2" + JavaUtils.getRandomNumber(14);
	private static String	fileDocRef3	= "AutoTest_WF_File3" + JavaUtils.getRandomNumber(14);
	private List<String>	fileDocRefs	= new ArrayList<String>();
	public static Logger	log			= AutomationLogger.getInstance().getLogger(WorkflowBoundaryTimerEmailScripts.class);

	public void focusSubDirectory(String subDirectory, String parentFolder) {

		log.info("SubDirectory ::" + subDirectory);
		clickElementWithText(parentFolder);
		clickElementWithText(subDirectory);

	}

	public void publishMultipleDocumentsInSubDirectory() throws InterruptedException, IOException {

		fileDocRefs = sysUtils.getFileList(fileDocRef1 + "," + fileDocRef2 + "," + fileDocRef3);

		uploadDocuments(null, 3, fileDocRefs, null, false, 1, null, null, null, null);

	}

	public void validateDocumentsWorkflowAttributes(String workflowStatus, String workflowStage) {

		navigateTab(LandingPage.lnk_Files);

		for (String docRef : fileDocRefs) {

			searchFiles(docRef);
			waitUntilElementContainsText(FilesTab.lnk_workflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			Assert.assertTrue(getElementText(FilesTab.ele_workflowStage) != "--");
			clickElementAndWait(FilesTab.lnk_workflowStatus);
			waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
			waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowStatus).equalsIgnoreCase(AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowEndTime).equalsIgnoreCase("--"));
			switchDefault();
			clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);

		}

	}

	public void validateDocumentAction(String fileName, String fileAction) {

		if (fileName.contains("AutoTest_WF_File1")) {
			searchFiles(fileDocRef1);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
			Assert.assertTrue("Failure while validating file Action" + fileAction, getElementText(FilesTab.lnk_FilesFirstAction).contains(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT));
		}

		else if (fileName.contains("AutoTest_WF_File2")) {

			searchFiles(fileDocRef2);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
			Assert.assertTrue("Failure while validating file Action" + fileAction, getElementText(FilesTab.lnk_FilesFirstAction).contains(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT));

		}

		else
			searchFiles(fileDocRef3);
		waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
		Assert.assertTrue("Failure while validating file Action" + fileAction, getElementText(FilesTab.lnk_FilesFirstAction).contains(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT));

	}

	public void performDocumentAction() {

		clickElementAndWait(FilesTab.lnk_FilesFirstAction);
		clickElementAndWait(FilesTab.lnk_PopupAcknowledgementReceipt);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void validatefileWorkflowAttributes(String fileName, String workflowStatus, String workflowStage) {

		log.info("Covered in <validateEmailOccurenceOnPopUp> Definition");

	}

	public void validateEmailOccurenceOnPopUp(String emailOccurence) throws InterruptedException {

		String emailTriggerTimes;

		if (emailOccurence.contains("1 time(s)")) {
			searchFiles(fileDocRef2);
			waitUntilElementContainsText(FilesTab.lnk_workflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			Assert.assertTrue(getElementText(FilesTab.ele_workflowStage).equalsIgnoreCase(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT));

		}

		else if (emailOccurence.contains("2 time(s)")) {
			searchFiles(fileDocRef3);
			waitUntilElementContainsText(FilesTab.lnk_workflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			Assert.assertTrue(getElementText(FilesTab.ele_workflowStage).equalsIgnoreCase(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT));

		}

		else
			searchFiles(fileDocRef1);

		clickElementAndWait(FilesTab.lnk_workflowStatus);
		waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
		mouseHover(By.cssSelector("div[id='diagramHolder'] div[id*='process_'][class='diagram'] ellipse[fill='none'][stroke-width='1']"));
		waitUntilElementIsDisplayed(By.xpath(".//div[@id='overlayBox']//div[contains(@style,'display: block')]"));
		if (emailOccurence.contains("1") || emailOccurence.contains("2"))
			emailTriggerTimes = getElementText(By.xpath(".//div[@id='overlayBox']//div[contains(@style,'display: block')]//div[contains(text(),'time(s)')]"));
		else
			emailTriggerTimes = getElementText(By.xpath(".//div[@id='overlayBox']//div[contains(@style,'display: block')]"));

		System.out.println("Email Trigger:: " + emailTriggerTimes);
		Assert.assertTrue("Failure while validating emailOccurence", emailTriggerTimes.contains(emailOccurence));
		switchDefault();
		clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);

	}

	public void validateEmailContentsUserMailbox(String emailSubject) throws InterruptedException {

		boolean flag = false;
		clickElementAndWaitForElement(GlobalPageElements.lnk_WebMailExpandInbox, GlobalPageElements.ele_WebmailFolderNotifications);
		clickElementAndWait(GlobalPageElements.ele_WebmailFolderNotifications);

		for (int index = 0; index < 30; index++) {
			if (isDisplayed(GlobalPageElements.ele_WebMailFirstEmailUnreadSubject) && getElementText(GlobalPageElements.ele_WebMailFirstEmailUnreadSubject).contains(emailSubject)) {
				clickElementAndWait(GlobalPageElements.ele_WebMailFirstEmailUnreadSubject);
				Assert.assertTrue("Failure while validating email content:: ", getElementText(GlobalPageElements.ele_WebMailContent).contains("Automated  Message : Action Pending"));
				flag = true;
				break;
			}
			else
				waitUtils.waitInterval(10);
		}

		Assert.assertTrue(flag);

	}
}
