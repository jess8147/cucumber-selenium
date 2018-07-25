package org.asite.automation.adoddle.p1.scripts;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.WebElement;

public class WorkFlowScripts extends AdoddleCommonAppMethods {

	private String firstDueDate, secondDueDate;
	private String currentWindowHandle = null;
	final private static String docRef1 = "WFFileDocRef1_" + JavaUtils.getRandomNumber(14);
	final private static String docRef2 = "WFFileDocRef2_" + JavaUtils.getRandomNumber(14);
	final private static String docRef3 = "WFFileDocRef3_" + JavaUtils.getRandomNumber(14);
	private List<String> fileList;
	private static List<String> docRefList = new ArrayList<String>();
	final private static Logger log = AutomationLogger.getInstance().getLogger(WorkFlowScripts.class);

	/*
	 * public void selectUploadingUser(String user) { switchToUser(user); }
	 */

	public void validateUsersDashboard(String userDashboard) {
		AutomationAssert.verifyTrue(getToolTipText(LandingPage.ele_LoggedInUser).contains(userDashboard));
	}

	public void selectMultipleFilesAndUpload() {
		String createFile1, createFile2, createFile3;
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile1 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		createFile2 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		createFile3 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		fileList = sysUtils.getFileList(createFile1 + "," + createFile2 + "," + createFile3);
		docRefList = sysUtils.getFileList(docRef1 + "," + docRef2 + "," + docRef3);
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
		collectionDataMap.put("Workflow Files", fileList.toString());
	}

	public void enterMandatoryAttributes() {
		if (!isSelected(FilesTab.chk_PopUploadFilesBulkApply)) {
			clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		}
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_DocRefFile1, docRef1);
		sendKeys(FilesTab.txt_DocRefFile2, docRef2);
		sendKeys(FilesTab.txt_DocRefFile3, docRef3);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		mouseHover(FilesTab.btn_PopUploadApplytoAll);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void clickUploadButton() {
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopUploadFileDistributeUpload,
				FilesTab.btn_PopUploadFileDistributeUpload);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void waitAndRefreshWorkflowAttributes() {
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Files);
	}

	public void verifyWorkflowFiles() {

		for (String file : fileList) {
			searchFiles(strUtils.extractFileNameString(file));
			String uploadTime = getElementText(FilesTab.ele_workflowDateAndtime) + "," + " "
					+ findElement(FilesTab.ele_workflowDateAndtime).getAttribute("title");
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(
					AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			AutomationAssert.verifyTrue(!getElementText(FilesTab.ele_workflowStage).equalsIgnoreCase("--"));
			clickElementAndWait(FilesTab.lnk_workflowStatus);
			waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
			waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
			AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_WorkflowStatus).equalsIgnoreCase(
					AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			log.info(uploadTime + " Vs " + getElementText(FilesTab.lbl_WorkflowStartTime));

			try {
				AutomationAssert
						.verifyTrue(getElementText(FilesTab.lbl_WorkflowStartTime).equalsIgnoreCase(uploadTime));
			} catch (Throwable t) {
				String expectedTime = getElementText(FilesTab.lbl_WorkflowStartTime);
				int replaceNum = Integer.parseInt(expectedTime.substring(expectedTime.lastIndexOf(":") + 1).replace(
						" WET", "")) - 1;
				expectedTime = expectedTime.replace(expectedTime.substring(expectedTime.lastIndexOf(":") + 1),
						String.valueOf((replaceNum < 10 ? "0" : "") + replaceNum));
				log.info("Expected Time: " + expectedTime);
				AutomationAssert.verifyTrue("Expected time:  " + expectedTime + "\nActualTime:" + uploadTime,
						uploadTime.contains(expectedTime));
			}
			AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_WorkflowEndTime).equalsIgnoreCase("--"));
			switchDefault();
			clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
		}
	}

	/*
	 * public void validateLockedActivitiesOnFiles() {
	 * 
	 * List<WebElement> lockedActivityList =
	 * findElements(FilesTab.css_DocListingActivities);
	 * 
	 * for (WebElement web : lockedActivityList) {
	 * 
	 * if (web.getText().contains("Revision Upload"))
	 * AutomationAssert.verifyTrue("Revision Uplaod Activity not Locked",
	 * web.getText
	 * ().contains(AdoddleCommonStringPool.ACTIVITY_REVISION_UPLOAD)); else if
	 * (web.getText().contains("File Distribution"))
	 * AutomationAssert.verifyTrue("File Distribution Activity not Locked",
	 * web.getText
	 * ().contains(AdoddleCommonStringPool.ACTIVITY_FILE_DISTRIBUTION)); else if
	 * (web.getText().contains("Edit Attribute"))
	 * AutomationAssert.verifyTrue("Edit Attribute Activity not Locked",
	 * web.getText().contains(AdoddleCommonStringPool.ACTIVITY_EDIT_ATTRIBUTE));
	 * else if (web.getText().contains("Update Status"))
	 * AutomationAssert.verifyTrue("Update Status Activity not Locked",
	 * web.getText().contains(AdoddleCommonStringPool.ACTIVITY_UPDATE_STATUS));
	 * else AutomationAssert.verifyTrue("Commenting Activity not Locked",
	 * web.getText().contains(AdoddleCommonStringPool.ACTIVITY_COMMENTING));
	 * 
	 * }
	 * 
	 * }
	 */

	public void checkWorkflowStatus() {
		log.info("Covered in <verifyWorkflowFiles> definition");
	}

	public void checkWorkflowFileAttributes() {
		log.info("Covered in <verifyWorkflowFiles> definition");
	}

	public void workflowStatusAndStage() {
		log.info("Covered in <verifyWorkflowFiles> definition");
	}

	private String getDateFormat() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_UK;
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_US;
		else
			return AdoddleCommonStringPool.DATE_FORMAT_AUS;
	}

	public void rightClickFirstFileWithStatusWorkflowRuning() throws ParseException {
		currentWindowHandle = getCurrentWindow();

		if (fileBetaViewFlag) {
			for (String file : fileList) {
				/* searchFiles(file.split("\\\\")[6]); */
				searchFiles(strUtils.extractFileNameString(file));
				String dateFormat = getDateFormat();
				log.info("dateFormat : " + dateFormat);
				contextClick(FilesTab.lnk_DocListingFirstFileName);
				mouseHoverAndClickElement(FilesTab.lbl_History, FilesTab.lbl_Distribution);
				waitForSwitchWindow(2);
				switchWindow();
				clickElementAndWait(FilesTab.ele_FileBetaViewclkFirstRecord);

				if (dateFormat.equalsIgnoreCase(AdoddleCommonStringPool.DATE_FORMAT_UK)) {

					firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);
					log.info("FirstActioDueDate:: " + firstDueDate);

					secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 4);
					log.info("SecondActioDueDate:: " + secondDueDate);

				} else if (dateFormat.equalsIgnoreCase(AdoddleCommonStringPool.DATE_FORMAT_US)) {

					firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_US,
							AdoddleCommonStringPool.TIMEZONE_ID_US, 3);
					log.info("FirstActioDueDate:: " + firstDueDate);

					secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_US,
							AdoddleCommonStringPool.TIMEZONE_ID_US, 4);
					log.info("SecondActioDueDate:: " + secondDueDate);

				} else {

					firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_AUS,
							AdoddleCommonStringPool.TIMEZONE_ID_AUS, 3);
					log.info("FirstActioDueDate:: " + firstDueDate);

					secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_AUS,
							AdoddleCommonStringPool.TIMEZONE_ID_AUS, 4);
					log.info("SecondActioDueDate:: " + secondDueDate);
				}
				validateActionAndDueDateInAuditTrail(firstDueDate, secondDueDate);
				closeCurrentWindow();
				switchPreviousWindow(currentWindowHandle);
			}
		} else {
			for (String file : fileList) {
				/* searchFiles(file.split("\\\\")[6]); */
				searchFiles(strUtils.extractFileNameString(file));
				String dateFormat = getDateFormat();
				log.info("dateFormat : " + dateFormat);

				contextClick(FilesTab.lnk_DocListingFirstFileName);
				mouseHoverAndClickElement(FilesTab.lbl_History, FilesTab.lbl_Distribution);
				waitForSwitchWindow(2);
				switchWindow();
				clickElementAndWait(FilesTab.ele_clkFirstRecord);

				if (dateFormat.equalsIgnoreCase(AdoddleCommonStringPool.DATE_FORMAT_UK)) {

					firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);
					log.info("FirstActioDueDate:: " + firstDueDate);

					secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
							AdoddleCommonStringPool.TIMEZONE_ID_UK, 4);
					log.info("SecondActioDueDate:: " + secondDueDate);

				} else if (dateFormat.equalsIgnoreCase(AdoddleCommonStringPool.DATE_FORMAT_US)) {

					firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_US,
							AdoddleCommonStringPool.TIMEZONE_ID_US, 3);
					log.info("FirstActioDueDate:: " + firstDueDate);

					secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_US,
							AdoddleCommonStringPool.TIMEZONE_ID_US, 4);
					log.info("SecondActioDueDate:: " + secondDueDate);

				} else {

					firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_AUS,
							AdoddleCommonStringPool.TIMEZONE_ID_AUS, 3);
					log.info("FirstActioDueDate:: " + firstDueDate);

					secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_AUS,
							AdoddleCommonStringPool.TIMEZONE_ID_AUS, 4);
					log.info("SecondActioDueDate:: " + secondDueDate);
				}
				validateActionAndDueDateInAuditTrail(firstDueDate, secondDueDate);
				closeCurrentWindow();
				switchPreviousWindow(currentWindowHandle);
			}
		}
	}

	private void validateActionAndDueDateInAuditTrail(String firstActionDueDate, String secondActionDueDate)
			throws ParseException {

		if (fileBetaViewFlag) {

			boolean flag = false;
			List<WebElement> userDistributionList;
			waitUntilElementCountToBe(FilesTab.css_BetaViewFileViewerActionDistributionRecords, 4);
			userDistributionList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
			for (WebElement web : userDistributionList) {
				String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
				String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();
				String actionStatus = web.findElement(FilesTab.css_BetaFileViewerRecordsActionStatus).getText();
				String actionDueDate = web.findElement(FilesTab.css_BetaFileViewerRecordsActionDueDate).getText();

				firstDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
						AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);
				secondDueDate = DateUtils.addDaysToDate(AdoddleCommonStringPool.DATE_FORMAT_UK,
						AdoddleCommonStringPool.TIMEZONE_ID_UK, 4);
				log.info("UserName :: " + userName);

				if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {

					AutomationAssert.verifyTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_COMMENT,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));
					AutomationAssert.verifyTrue("Expected actionStatus:: " + AdoddleCommonStringPool.STRING_INCOMPLETE,
							actionStatus.contains(AdoddleCommonStringPool.STRING_INCOMPLETE));
					AutomationAssert.verifyTrue("Expected actionDueDate:: " + firstDueDate + " But found:: "
							+ actionDueDate, actionDueDate.contains(firstDueDate));

				}

				else {

					if (dataCenter.contains(AdoddleCommonStringPool.UK_DC)) {

						if (userName.contains(ResourceHandler.loadProperty("test.user.automation.primary.name"))
								|| userName.contains("current.user.uk.username")
								|| userName.contains(ResourceHandler.loadProperty("test.user.automation.uk.name"))) {

							AutomationAssert.verifyTrue("Expected Action: "
									+ AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + "But was " + action,
									action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

							AutomationAssert.verifyTrue("Expected ActionDueDate: " + secondActionDueDate + " But was: "
									+ actionDueDate, actionDueDate.contains(secondActionDueDate));
							flag = true;
						}
					}

					else if (dataCenter.contains(AdoddleCommonStringPool.US_DC)) {

						if (userName.contains(ResourceHandler.loadProperty("test.user.automation.primary.name"))
								|| userName.contains("current.user.us.username")
								|| userName.contains(ResourceHandler.loadProperty("test.user.automation.us.name"))) {

							AutomationAssert.verifyTrue("Expected Action: "
									+ AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + "But was " + action,
									action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

							AutomationAssert.verifyTrue("Expected ActionDueDate: " + secondActionDueDate + " But was: "
									+ actionDueDate, actionDueDate.contains(secondActionDueDate));
							flag = true;
						}
					} else {

						AutomationAssert.verifyTrue("Expected Action: "
								+ AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + "But was " + action,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

						AutomationAssert.verifyTrue("Expected ActionDueDate: " + secondActionDueDate + " But was: "
								+ actionDueDate, actionDueDate.contains(secondActionDueDate));
						flag = true;
					}
				}
			}

			AutomationAssert.verifyTrue("Failure while validating Audit Trial", flag);
		}

		else {

			boolean flag = false;
			waitUntilListOfElementIsDisplayed(FilesTab.css_FileViewerActionDistributionRecords);
			List<WebElement> auditTrailActionList = findElements(FilesTab.css_FileViewerActionDistributionRecords);

			for (WebElement web : auditTrailActionList) {

				String user = web.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser)
						.getAttribute("title");
				String action = web.findElement(FilesTab.css_FileViewerRecordsAction).getText();
				String actionDueDate = web.findElement(FilesTab.css_FileViewerRecordsActionDueDate).getText();

				if (user.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {

					AutomationAssert.verifyTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_COMMENT
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));

					AutomationAssert.verifyTrue("Expected ActionDueDate: " + firstActionDueDate + " But was: "
							+ actionDueDate, actionDueDate.contains(firstActionDueDate));

					flag = true;
				} else {

					if (dataCenter.contains(AdoddleCommonStringPool.UK_DC)) {

						if (user.contains(ResourceHandler.loadProperty("test.user.automation.primary.name"))
								|| user.contains("current.user.uk.username")
								|| user.contains(ResourceHandler.loadProperty("test.user.automation.uk.name"))) {

							AutomationAssert.verifyTrue("Expected Action: "
									+ AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + "But was " + action,
									action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

							AutomationAssert.verifyTrue("Expected ActionDueDate: " + secondActionDueDate + " But was: "
									+ actionDueDate, actionDueDate.contains(secondActionDueDate));
							flag = true;
						}
					}

					else if (dataCenter.contains(AdoddleCommonStringPool.US_DC)) {

						if (user.contains(ResourceHandler.loadProperty("test.user.automation.primary.name"))
								|| user.contains("current.user.us.username")
								|| user.contains(ResourceHandler.loadProperty("test.user.automation.us.name"))) {

							AutomationAssert.verifyTrue("Expected Action: "
									+ AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + "But was " + action,
									action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

							AutomationAssert.verifyTrue("Expected ActionDueDate: " + secondActionDueDate + " But was: "
									+ actionDueDate, actionDueDate.contains(secondActionDueDate));
							flag = true;
						}
					} else {

						AutomationAssert.verifyTrue("Expected Action: "
								+ AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + "But was " + action,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

						AutomationAssert.verifyTrue("Expected ActionDueDate: " + secondActionDueDate + " But was: "
								+ actionDueDate, actionDueDate.contains(secondActionDueDate));
						flag = true;
					}
				}
			}
			AutomationAssert.verifyTrue("Failure while validating Audit Trial", flag);
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

	public void validateFileAction(String fileAction, String scenario) {
		String workFlowFolder = "Automation_WF_Folder";
		currentWindowHandle = getCurrentWindow();
		clickElementWithText(workFlowFolder);

		if (scenario.equalsIgnoreCase("Org Users")) {
			searchFiles(docRef1);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
			clickElementAndSwitchWindow(FilesTab.lnk_FilesFirstAction);
		}

		else if (scenario.equalsIgnoreCase("individual user")) {
			searchFiles(docRef1);

			if (fileAction.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
				waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
				clickElementAndSwitchWindow(FilesTab.lnk_FilesFirstAction);
			} else {
				waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
				clickElementAndWait(FilesTab.lnk_FilesFirstAction);
			}

		} else if (scenario.equalsIgnoreCase("system task")) {
			searchFiles(docRef2);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
			clickElementAndWait(FilesTab.lnk_FilesFirstAction);
		}

		else {
			searchFiles(docRef3);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileAction);
			clickElementAndWait(FilesTab.lnk_FilesFirstAction);
		}

	}

	public void clickMyActions() {
		log.info("Covered in <validateFileAction> previous definition");
	}

	public void performFileAction(String actionName) {

		if (actionName.contains(AdoddleCommonStringPool.FOR_COMMENT)) {

			if (!fileBetaViewFlag) {
				waitUntilElementIsDisplayed(FilesTab.pop_StartNewDiscussion);
				sendKeys(FilesTab.txt_NewDiscussionTitleInput, javaUtils.getRandomString(10) + epoch);
				sendKeys(FilesTab.txt_NewDiscussionDescInput, javaUtils.getRandomString(10) + epoch);
				clickElement(FilesTab.btn_NewDiscussionSubmit);
				try {
					waitUntilElementIsDisplayed(FilesTab.lbl_FileAddCommentSuccessMsg);
				} catch (Throwable t) {
					log.error("Create comment success message verification failed");
				}
			} else {
				waitUntilElementIsDisplayed(FilesTab.txt_BetaFileViewNewDiscussionTitle);
				String commentTitle = javaUtils.getRandomString(10) + epoch;
				sendKeys(FilesTab.txt_BetaFileViewNewDiscussionTitle, commentTitle);
				sendKeys(FilesTab.btn_BetaFileViewNewDiscussionDescBody, javaUtils.getRandomString(10) + epoch);
				clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_BetaFileViewNewDiscussionSubmit,
						FilesTab.btn_BetaFileViewNewDiscussionSubmit);
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(
						getElementText(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion), commentTitle),
						getElementText(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion).contains(commentTitle));
			}
			waitForCompletePageLoad();
			closeCurrentWindow();
			switchPreviousWindow(currentWindowHandle);
		} else
			clickElementAndWait(FilesTab.lnk_PopupAcknowledgementReceipt);

		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void validateDocumentCompletedAction() {
		log.info("Covered in <checkWorkflowStatusAndStage> Definition");
	}

	public void checkWorkflowStatusAndStage(String wStatus, String wStage, String workflowScenario) {

		navigateTab(LandingPage.lnk_Files);

		if (workflowScenario.equalsIgnoreCase("Org users")) {
			verifyStageAndStatus(docRef1, wStatus, wStage, true);
		} else if (workflowScenario.equalsIgnoreCase("individual user")) {
			verifyStageAndStatus(docRef1, wStatus, wStage, false);
		} else if (workflowScenario.equalsIgnoreCase("system task")) {
			log.info("Code disabled due to NOODLE-67731");
			/*
			 * verifyStageAndStatus(docRef2, wStatus, wStage, false);
			 * AutomationAssert
			 * .verifyTrue(!isDisplayed(FilesTab.img_DocListingActivityLockIcon
			 * ));
			 */
		} else if (workflowScenario.equalsIgnoreCase("failed status")) {
			verifyStageAndStatus(docRef3, wStatus, wStage, true);
		}
	}

	private void verifyStageAndStatus(String docRef, String wStatus, String wStage, boolean endTimeCheck) {
		searchFiles(docRef);
		waitUntilElementContainsText(FilesTab.lnk_workflowStatus, wStatus);
		waitUntilElementContainsText(FilesTab.ele_workflowStage, wStage);
		AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(wStatus));
		AutomationAssert.verifyTrue(getElementText(FilesTab.ele_workflowStage).equalsIgnoreCase(wStage));
		clickElementAndWaitForElement(FilesTab.lnk_workflowStatus, FilesTab.frm_WorkflowIframe);
		waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
		waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, wStatus);
		AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_WorkflowStatus).equalsIgnoreCase(wStatus));
		if (endTimeCheck)
			AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_WorkflowEndTime).equalsIgnoreCase("--"));
		else
			AutomationAssert.verifyTrue(!getElementText(FilesTab.lbl_WorkflowEndTime).equalsIgnoreCase("--"));
		switchDefault();
		clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
	}

	public void performForStatusChange(String changeStatus) {
		selectByVisibleText(FilesTab.sel_StatusChangedDropdown, changeStatus);
		sendKeys(FilesTab.txt_StatusChangeTextArea, javaUtils.getRandomString(10));
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_ChangeStatus, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void closeWindow() {
		closeCurrentWindow();
		switchPreviousWindow(currentWindowHandle);
	}

	public void selectThirdUser(String thirdUser) {
		switchToUser(thirdUser);
	}

	public void validateThirdUser(String userdasboard) {
		AutomationAssert.verifyTrue(getToolTipText(LandingPage.ele_LoggedInUser).contains(userdasboard));
	}

	public void deactivateAllFiles() {

		try {
			log.info("docRefList Size:::" + docRefList.size());
			for (String docRef : docRefList) {
				deactivateFile(docRef);
			}

		} catch (Throwable t) {
			log.error("failure in deactivating file");
		}
	}

}