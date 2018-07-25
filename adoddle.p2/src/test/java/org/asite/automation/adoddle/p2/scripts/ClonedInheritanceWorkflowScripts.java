/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.utils.DateUtils;
import org.asite.automation.common.utils.JavaUtils;
import org.asite.automation.common.utils.ResourceUtils;
import org.openqa.selenium.WebElement;

public class ClonedInheritanceWorkflowScripts extends AdoddleCommonAppMethods {

	final private static String docRef1 = "WFFileDocRef1_" + JavaUtils.getRandomNumber(10);
	final private static String docRef2 = "WFFileDocRef2_" + JavaUtils.getRandomNumber(10);
	final private static String docRef3 = "WFFileDocRef3_" + JavaUtils.getRandomNumber(10);
	private static List<String> fileList;
	final private static Logger log = AutomationLogger.getInstance().getLogger(ClonedInheritanceWorkflowScripts.class);

	public void loggedInAsUser(String userName) throws InterruptedException {

		logOut();

		if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.tc.bloggs.name")))
			login(ResourceHandler.loadProperty("test.user.tc.bloggs.id"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
			login(ResourceHandler.loadProperty("test.user.rfi.bloggs.id"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.pa.bloggs.name")))
			login(ResourceHandler.loadProperty("test.user.pa.bloggs.id"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.dc.bloggs.name")))
			login(ResourceHandler.loadProperty("test.user.dc.bloggs.id"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.rfi.builder.name")))
			login(ResourceHandler.loadProperty("test.user.rfi.builder.id"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.pa.builder.name")))
			login(ResourceHandler.loadProperty("test.user.pa.builder.id"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.auto.rfi.name")))
			login(ResourceHandler.loadProperty("forth.user.username"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.jk.bloggs.name")))
			login(ResourceHandler.loadProperty("test.user.jk.bloggs.id"), resourceUtils.getCommonUserPassword());
		else if (userName.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.rs.bloggs.name"))) {
			login(ResourceHandler.loadProperty("test.user.rs.bloggs.id"), resourceUtils.getCommonUserPassword());
			if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_QA2))
				AutomationAssert.verifyTrue(
						eStringUtils.getVisibilityStringError(DashboardTab.css_DashboardBrandingBomBardier, true),
						isDisplayed(DashboardTab.css_DashboardBrandingBomBardier));
		}

		else
			log.info("Invalid UserID/Password");

	}

	public void validateUsersDashboard(String userDashboard) {
		AutomationAssert.verifyTrue(
				eStringUtils.getContainsStringError(getToolTipText(LandingPage.ele_LoggedInUser), userDashboard),
				getToolTipText(LandingPage.ele_LoggedInUser).contains(userDashboard));
	}

	public void selectMultipleFilesAndUpload() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		String createFile1 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		String createFile2 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		String createFile3 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		fileList = sysUtils.getFileList(createFile1 + "," + createFile2 + "," + createFile3);
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
	}

	private void bulkApplyCheckboxSelect() {
		if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply))
			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
	}

	private void enterDocRefText() {
		sendKeys(FilesTab.txt_DocRefFile1, docRef1);
		sendKeys(FilesTab.txt_DocRefFile2, docRef2);
		sendKeys(FilesTab.txt_DocRefFile3, docRef3);
	}

	public void enterMandatoryAttributes() {
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

	public void waitAndRefreshWorkflowAttributes() {
		try {
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			navigateTab(LandingPage.lnk_Files);
		} catch (Exception e) {
			log.error("ERROR: Failed while refreshing page");
		}

	}

	public void verifyWorkflowFiles() {
		String uploadTime;
		for (String file : fileList) {

			searchFiles(strUtils.extractFileNameString(file));
			uploadTime = getElementText(FilesTab.ele_workflowDateAndtime) + "," + " "
					+ findElement(FilesTab.ele_workflowDateAndtime).getAttribute("title");
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_workflowStatus) + " should equal "
					+ AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING, getElementText(FilesTab.lnk_workflowStatus)
					.equalsIgnoreCase(AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			AutomationAssert.verifyTrue(getElementText(FilesTab.ele_workflowStage) + " should not equal --",
					!getElementText(FilesTab.ele_workflowStage).equalsIgnoreCase("--"));
			clickElementAndWait(FilesTab.lnk_workflowStatus);
			waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
			waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
			AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_WorkflowStatus).equalsIgnoreCase(
					AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			log.info(uploadTime + " Vs " + getElementText(FilesTab.lbl_WorkflowStartTime));
			try {
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(
						getElementText(FilesTab.lbl_WorkflowStartTime), uploadTime),
						getElementText(FilesTab.lbl_WorkflowStartTime).equalsIgnoreCase(uploadTime));
			} catch (Throwable t) {
				String expectedTime = getElementText(FilesTab.lbl_WorkflowStartTime);
				int replaceNum = Integer.parseInt(expectedTime.substring(expectedTime.lastIndexOf(":") + 1).replace(
						" WET", "")) - 1;
				expectedTime = expectedTime.replace(expectedTime.substring(expectedTime.lastIndexOf(":") + 1),
						String.valueOf((replaceNum < 10 ? "0" : "") + replaceNum));
				log.info("Expected Time: " + expectedTime);
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(uploadTime, expectedTime),
						uploadTime.contains(expectedTime));
			}
			AutomationAssert.verifyTrue(
					eStringUtils.getInEqualityStringError(getElementText(FilesTab.lbl_WorkflowEndTime), "--"),
					getElementText(FilesTab.lbl_WorkflowEndTime).equalsIgnoreCase("--"));
			switchDefault();
			clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
		}

		AdoddleScenarioMarkers.clonedInheritanceWorkflowFlag = true;
	}

	public void checkWorkflowStatus() {
		log.info("covered in previous definition");
	}

	public void checkWorkflowFileAttributes() {
		log.info("covered in previous definition");
	}

	public void workflowStatusAndStage() {
		log.info("covered in previous definition");
	}

	public void rightClickFirstFileWithStatusWorkflowRuning() throws ParseException {
		String currentWindowHandle;
		List<WebElement> userDistributionList;

		for (String file : fileList) {
			searchFiles(strUtils.extractFileNameString(file));
			currentWindowHandle = getCurrentWindow();
			contextClick(FilesTab.lnk_DocListingFirstFileName);
			clickContextMenuOption(AdoddleCommonStringPool.OPTION_HISTORY, AdoddleCommonStringPool.OPTION_DISTRIBUTION);
			waitForSwitchWindow(2);
			switchWindow();

			if (fileBetaViewFlag) {

				boolean flag = false;
				waitUntilListOfElementIsDisplayed(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				userDistributionList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				AutomationAssert.assertTrue(eStringUtils.getInEqualityStringError(userDistributionList.size(), 4),
						userDistributionList.size() == 4);

				for (WebElement web : userDistributionList) {

					String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
					String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();
					String actionStatus = web.findElement(FilesTab.css_BetaFileViewerRecordsActionStatus).getText();
					String actionDueDate = web.findElement(FilesTab.css_BetaFileViewerRecordsActionDueDate).getText();

					String firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);
					String secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 4);

					log.info("distribution user name :" + userName);

					if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))
							|| userName.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {
						AutomationAssert.assertTrue(
								eStringUtils.getContainsStringError(action, AdoddleCommonStringPool.ACTION_FOR_ACTION),
								action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
						AutomationAssert.assertTrue(eStringUtils.getContainsStringError(actionStatus,
								AdoddleCommonStringPool.STRING_INCOMPLETE), actionStatus
								.contains(AdoddleCommonStringPool.STRING_INCOMPLETE));
						AutomationAssert.assertTrue(eStringUtils.getContainsStringError(actionDueDate, firstDueDate),
								actionDueDate.contains(firstDueDate));
					} else if (userName.contains(ResourceHandler.loadProperty("multi.project.user.name"))
							|| userName.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))) {
						AutomationAssert.assertTrue(eStringUtils.getContainsStringError(action,
								AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE), action
								.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
						AutomationAssert.assertTrue(eStringUtils.getContainsStringError(actionStatus,
								AdoddleCommonStringPool.STRING_INCOMPLETE), actionStatus
								.contains(AdoddleCommonStringPool.STRING_INCOMPLETE));
						AutomationAssert.assertTrue(eStringUtils.getContainsStringError(actionDueDate, secondDueDate),
								actionDueDate.contains(secondDueDate));
						flag = true;
					} else
						flag = false;

				}

				AutomationAssert.assertTrue("Failure while validation userAction and dueDate", flag);

			}

			else {

				waitUntilElementIsDisplayed(FilesTab.ele_FileHistoryFirstRecord);
				clickElementAndWait(FilesTab.ele_FileHistoryFirstRecord);
				if (findElement(FilesTab.img_FileHistoryFirstRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.rfi.builder.name"))) {
					String firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);
					AutomationAssert.verifyTrue(
							eStringUtils.getContainsStringError(
									getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction),
									AdoddleCommonStringPool.FOR_ACTION),
							getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction).contains(
									AdoddleCommonStringPool.FOR_ACTION));
					AutomationAssert.verifyTrue(
							eStringUtils.getInEqualityStringError(
									getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstDueDate), firstDueDate),
							getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstDueDate).equalsIgnoreCase(
									firstDueDate));
				} else {
					String secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 4);
					AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction)
							.contains(AdoddleCommonStringPool.FOR_STATUS_CHANGE));
					AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstDueDate)
							.equalsIgnoreCase(secondDueDate));
				}
				if (findElement(FilesTab.img_FileHistorySecondRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.rfi.builder.name"))) {
					String firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);
					AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction)
							.contains(AdoddleCommonStringPool.FOR_ACTION));
					AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstDueDate)
							.equalsIgnoreCase(firstDueDate));
				} else {
					String secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 4);
					AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction)
							.contains(AdoddleCommonStringPool.FOR_STATUS_CHANGE));
					AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstDueDate)
							.equalsIgnoreCase(secondDueDate));
				}
			}

			closeCurrentWindow();
			switchPreviousWindow(currentWindowHandle);

		}

	}

	public void dragMouseOnHistoryAndOptDistribution() {
		log.info("Covered in <rightClickFirstFileWithStatusWorkflowRuning> definition");
	}

	public void verifyHTMLViewer() {
		log.info("Covered in <rightClickFirstFileWithStatusWorkflowRuning> definition");
	}

	public void clickFirstHistoryRecord() {
		log.info("Covered in <rightClickFirstFileWithStatusWorkflowRuning> definition");
	}

	public void recordExpand() {
		log.info("Covered in <rightClickFirstFileWithStatusWorkflowRuning> definition");
	}

	public void checkFilesRecordAttributes() {

		log.info("Covered in <rightClickFirstFileWithStatusWorkflowRuning> definition");

	}

	public void checkFileStatus(String fileName, String scenario) {
		String workFlowFolder = "Automation_WF_Folder";

		clickElementWithText(workFlowFolder);

		if (scenario.equalsIgnoreCase("Org Users"))
			searchFiles(docRef1);
		else if (scenario.equalsIgnoreCase("individual user"))
			searchFiles(docRef1);
		else if (scenario.equalsIgnoreCase("system task"))
			searchFiles(docRef2);
		else if (scenario.equalsIgnoreCase("failed status"))
			searchFiles(docRef3);
		else
			log.info("Check failed");

		waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileName);
	}

	public void clickMyActions() {
		clickElementAndWait(FilesTab.lnk_FilesFirstAction);
	}

	public void performForAction() {
		clickElementAndWait(FilesTab.chk_forActionCheckbox);
		sendKeys(FilesTab.txt_forActionRemarkTextbox, javaUtils.getRandomString(10));
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_forActionSubmit, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void validateAction() {
		log.info("Covered in <checkFileStatus> Definition");
	}

	public void checkWorkflowStatusAndStage(String wStatus, String wStage, String workflowScenario) {

		navigateTab(LandingPage.lnk_Files);
		if (workflowScenario.equalsIgnoreCase("Org users")) {
			searchFiles(docRef1);
		} else if (workflowScenario.equalsIgnoreCase("individual user")) {
			searchFiles(docRef1);
		} else if (workflowScenario.equalsIgnoreCase("system task")) {
			searchFiles(docRef2);
		} else if (workflowScenario.equalsIgnoreCase("failed status")) {
			searchFiles(docRef3);
		}
		verifyWorkflowStatusAndStage(workflowScenario, wStatus, wStage);

	}

	public void performForStatusChange(String changeStatus) {
		selectByVisibleText(FilesTab.sel_StatusChangedDropdown, changeStatus);
		sendKeys(FilesTab.txt_StatusChangeTextArea, javaUtils.getRandomString(10));
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_ChangeStatus, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void checkfileStatusAfterStatusChange(String actionName) {
		navigateTab(LandingPage.lnk_Files);
		searchFiles(docRef1);
		waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, actionName);
	}

	public void performForInformationAction() {
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickActions,
				FilesTab.opt_FileContextClickActionsForInformation);
		waitUntilListOfElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopActionsForInformationOK,
				GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void validateFileInHTMLViewer() {
		if (!isDisplayed(FilesTab.lbl_UnsupportedFile)) {
			waitUntilElementIsDisplayed(FilesTab.frm_BravaObjectFrame);
			switchIframe(FilesTab.frm_BravaObjectFrame);

			if (!isDisplayed(FilesTab.frm_ErrorFileIframe)) {
				waitUntilElementIsDisplayed(FilesTab.frm_OpenFileIframe);
				AutomationAssert.verifyTrue(isDisplayed(FilesTab.frm_OpenFileIframe));
				log.info("Expected Iframe: Expected Iframe Detected");
				switchDefault();

			} else {
				log.error("Error Iframe: Error Iframe Detected");
				AutomationAssert.verifyTrue(false);
			}
		} else {
			log.info("Unsupported File : File not supported");
		}

	}

	/**** Common Script functions ***/

	private void verifyWorkflowStatusAndStage(String scenario, String wStatus, String wStage) {
		waitUntilElementContainsText(FilesTab.lnk_workflowStatus, wStatus);
		waitUntilElementContainsText(FilesTab.ele_workflowStage, wStage);
		clickElementAndWaitForElement(FilesTab.lnk_workflowStatus, FilesTab.frm_WorkflowIframe);
		waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
		waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, wStatus);
		if (scenario.equalsIgnoreCase("Org users") || scenario.equalsIgnoreCase("failed status"))
			AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_WorkflowEndTime).equalsIgnoreCase("--"));
		else
			AutomationAssert.verifyTrue(!getElementText(FilesTab.lbl_WorkflowEndTime).equalsIgnoreCase("--"));
		switchDefault();
		clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
	}

}