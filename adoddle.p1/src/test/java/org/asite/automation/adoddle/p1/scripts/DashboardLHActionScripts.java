package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleTasksLocators.TasksTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cucumber.api.PendingException;

public class DashboardLHActionScripts extends AdoddleCommonAppMethods {

	private int totalActions = 0, appCount = 0;
	private int actionSize, tooltipCount;
	private int filesIncompleteActions, formsIncompleteActions, unreadDiscussions;
	private int filesOverdueActions, formsOverdueActions, filesDueTodayActions, formsDueTodayActions;
	private String formSubject, testFileDocRef, parentHandle;
	private List<WebElement> incompleteTaskList = new ArrayList<WebElement>();
	private List<WebElement> workspacesGroups = new ArrayList<WebElement>();
	private List<WebElement> availableTasksStatuses = new ArrayList<WebElement>();
	private List<String> fileDocRefs = new ArrayList<String>();
	private List<String> userList1 = new ArrayList<String>(Arrays.asList(ResourceHandler
			.loadProperty("actions.test.user1.id")));
	private List<String> userList2 = new ArrayList<String>(Arrays.asList(ResourceHandler
			.loadProperty("actions.test.user2.id")));
	private List<String> multiActionList1 = new ArrayList<String>(Arrays.asList(
			AdoddleCommonStringPool.ACTION_FOR_INFORMATION, AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
	private List<String> multiActionList2 = new ArrayList<String>(Arrays.asList(
			AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT, AdoddleCommonStringPool.ACTION_FOR_ACTION));
	private List<String> multiActionList3 = new ArrayList<String>(Arrays.asList(
			AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION, AdoddleCommonStringPool.ACTION_FOR_COMMENT));

	private String AutoTestFileDocRef1 = "AutoTestTaskFile1_" + JavaUtils.getRandomNumber(14);
	private String AutoTestFileDocRef2 = "AutoTestTaskFile2_" + JavaUtils.getRandomNumber(14);
	private String AutoTestFileDocRef3 = "AutoTestTaskFile3_" + JavaUtils.getRandomNumber(14);
	private String AutoTestFileDocRef4 = "AutoTestTaskFile4_" + JavaUtils.getRandomNumber(14);
	private static String appGroupName = "DashboardLHTaskGroup", groupName;

	public static Logger log = AutomationLogger.getInstance().getLogger(DashboardLHActionScripts.class);

	public void verifyDashboardTab() throws InterruptedException {
		verifyElementText(DashboardTab.lbl_DashboardTitle, "System Dashboard");
	}

	public void verifyActionCount(String actionType) throws InterruptedException, IOException {

		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			createTestData(actionType);
			loggedInAsUser(ResourceHandler.loadProperty("test.user.actions.test.user1.name"));
			try {
				Assert.assertTrue(Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteActionsCount)
						.split(":")[1].trim()) > 0);
			} catch (Throwable t) {
				throw new PendingException();
			}
		}

		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			loggedInAsUser(ResourceHandler.loadProperty("test.user.actions.test.user1.name"));
			try {
				AutomationAssert.verifyTrue(Integer.parseInt(getToolTipText(
						DashboardTab.lbl_DashboardOverDueActionsCount).split(":")[1].trim()) > 0);
			} catch (Throwable t) {
				throw new PendingException();
			}
		}

		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			createTestData(actionType);
			loggedInAsUser(ResourceHandler.loadProperty("test.user.actions.test.user2.name"));
			try {
				waitUtils.waitInterval(2);
				Assert.assertTrue(Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardDueTodayActionsCount)
						.split(":")[1].trim()) > 0);
			} catch (Throwable t) {
				throw new PendingException();
			}

		}

		else {
			loggedInAsUser(ResourceHandler.loadProperty("test.user.actions.test.user1.name"));
			try {
				AutomationAssert.verifyTrue(Integer.parseInt(getToolTipText(
						DashboardTab.lbl_DashboardOverDueActionsCount).split(":")[1].trim()) > 0);
			} catch (Throwable t) {
				throw new PendingException();
			}

		}
	}

	public void verifyActionCountForForm(String actionType) throws InterruptedException, IOException {

		navigateTab(LandingPage.lnk_ProjectForms);
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)
				|| actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			try {
				createAppActionsData(actionType);
				waitForCompletePageLoad();
				loggedInAsUser(ResourceHandler.loadProperty("test.user.actions.test.user1.name"));
			} catch (Throwable t) {
				log.info("Error in creating app action,Execution resumed for available  test data.");
			}

			waitForCompletePageLoad();
			navigateTab(LandingPage.lnk_ProjectForms);
			try {
				Assert.assertTrue(Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsIncompleteActionsCount)
						.split(":")[1].trim()) > 0);
			} catch (Throwable t) {
				throw new PendingException();
			}
		}

		else {
			try {
				Assert.assertTrue(Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsOverdueActionsCount).split(
						":")[1].trim()) > 0);
			} catch (Throwable t) {
				throw new PendingException();
			}
		}
	}

	public void dragMouseOnActions() {
		log.info("Covered in <verifyTotalActionCount> Defination");
	}

	public void verifyTotalActionCount(String actionType) {

		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			navigateTab(LandingPage.lnk_Files);
			filesIncompleteActions = Integer.parseInt(getToolTipText(FilesTab.lbl_FilesIncompleteActionsCount).split(
					":")[1].trim());
			log.info("Files Incomplete Tasks:: " + filesIncompleteActions);

			navigateTab(LandingPage.lnk_ProjectForms);
			formsIncompleteActions = Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsIncompleteActionsCount)
					.split(":")[1].trim());
			log.info("Forms Incomplete Tasks:: " + formsIncompleteActions);

			navigateToDiscussionTab();
			unreadDiscussions = Integer
					.parseInt(getToolTipText(DiscussionsTab.lbl_UnreadDiscussionsCount).split(":")[1].trim());
			log.info("Discussion Incomplete Tasks:: " + unreadDiscussions);

			totalActions = filesIncompleteActions + formsIncompleteActions + unreadDiscussions;
			log.info("Total Incomplete Actions: " + totalActions);

			navigateTab(LandingPage.lnk_Dashboard);
			tooltipCount = Integer
					.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteActionsCount).split(":")[1].trim());
			try {
				AutomationAssert.verifyTrue(tooltipCount == totalActions);
			} catch (Throwable t) {
				log.error("Incomplete Action counts are not valid");
			}

		} else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {

			log.info("Dashboard Tooltip Overdue count: " + tooltipCount);
			navigateTab(LandingPage.lnk_Files);
			filesOverdueActions = Integer.parseInt(getToolTipText(FilesTab.lbl_FilesOverdueActionsCount).split(":")[1]
					.trim());
			log.info("FilesTab Tooltip Overdue count: " + filesOverdueActions);
			navigateTab(LandingPage.lnk_ProjectForms);
			formsOverdueActions = Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsOverdueActionsCount).split(
					":")[1].trim());
			totalActions = filesOverdueActions + formsOverdueActions;
			navigateTab(LandingPage.lnk_Dashboard);
			tooltipCount = Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardOverDueActionsCount).split(":")[1]
					.trim());
			try {
				AutomationAssert.verifyTrue(tooltipCount == totalActions);
			} catch (Exception t) {
				t.printStackTrace();
				log.error("Overdue Action counts are not valid");
			}
		}

		else {

			navigateTab(LandingPage.lnk_Files);
			filesDueTodayActions = Integer
					.parseInt(getToolTipText(FilesTab.lbl_FilesDueTodayActionsCount).split(":")[1].trim());
			navigateTab(LandingPage.lnk_ProjectForms);
			formsDueTodayActions = Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsDueTodayActionsCount)
					.split(":")[1].trim());
			totalActions = filesDueTodayActions + formsDueTodayActions;
			navigateTab(LandingPage.lnk_Dashboard);
			tooltipCount = Integer
					.parseInt(getToolTipText(DashboardTab.lbl_DashboardDueTodayActionsCount).split(":")[1].trim());
			try {
				AutomationAssert.verifyTrue(tooltipCount == totalActions);
			} catch (Throwable t) {
				log.error("Due-Today Action counts are not valid");
			}
		}

	}

	public void clickOnActionLink(String actionType) {

		navigateTab(LandingPage.lnk_Dashboard);
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS))
			clickElementAndWait(DashboardTab.lnk_DashboardIncompleteActions);
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS))
			clickElementAndWait(DashboardTab.lnk_DashboardOverdueActions);
		else
			clickElementAndWait(DashboardTab.lnk_DashboardDueTodayActions);

	}

	public void verifyFilesActions(int expectedFileCount, String actionType) {

		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)
				|| actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)
				|| actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)
				&& (filesIncompleteActions >= 2 || filesDueTodayActions >= 2 || filesOverdueActions >= 2))
			verifyTopFilesCounts(expectedFileCount);

		else
			log.info("Skipped Tests for " + actionType + " due to insufficient data");

	}

	public void verifyFormsActions(int expectedFormsCount, String actionType) {

		int actualActionCount = getCount(DashboardTab.css_DashboardIncompleteFormsActions);
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS) && formsIncompleteActions >= 5)
			verifyTopFormCounts(expectedFormsCount, formsIncompleteActions, actualActionCount);
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS) && formsOverdueActions >= 5)
			verifyTopFormCounts(expectedFormsCount, formsOverdueActions, actualActionCount);
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY) && formsDueTodayActions >= 5)
			verifyTopFormCounts(expectedFormsCount, formsDueTodayActions, actualActionCount);
		else
			log.info("Skipped Tests for " + actionType + " due to insufficient data");

	}

	public void verifyTopFilesCounts(int expectedFilesCount) {
		try {
			int actualTasksCount = getCount(TasksTab.css_TaskListingTasksCount);
			AutomationAssert.verifyTrue(Integer.valueOf(actualTasksCount) >= 1
					&& Integer.valueOf(actualTasksCount) <= expectedFilesCount);
		} catch (Exception e) {
			log.error("Expected Count for Forms for Actions did not match");
		}
	}

	public void verifyTopFormCounts(int expectedFormsCount, int expectedActionCount, int actualActionCounts) {
		try {
			int actualFormsCount = getCount(DashboardTab.css_DashboardTopActionForms);
			AutomationAssert.verifyTrue(Integer.valueOf(actualFormsCount) >= 1
					&& Integer.valueOf(actualFormsCount) <= expectedFormsCount);
		} catch (Throwable t) {
			log.error("Expected Count for Forms for Actions did not match");
		}
	}

	public void verifyDiscussionsActions(int expectedCount) {
		waitForCompletePageLoad();
		if (unreadDiscussions >= 5) {
			try {
				int actualCount = getCount(DashboardTab.css_DashboardIncompleteDiscussionsActions);
				Assert.assertEquals(Integer.valueOf(actualCount), Integer.valueOf(expectedCount));
			} catch (Exception e) {
				log.error("Expected Count for Unread Discussions did not match");
			}
		}
	}

	public void performPendingTasks(String taskType, String actionType) throws InterruptedException, IOException {

		if ((actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)
				|| actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY) || actionType
					.contains(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) && taskType.equalsIgnoreCase("Files")) {

			if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)
					&& filesIncompleteActions > 0) {
				for (String task : fileDocRefs) {
					searchTasks(task);
					waitUntilElementCountToBe(TasksTab.css_TaskListingTasksCount, 2);
					AutomationAssert.assertTrue("Expected Tasks Count:: 2 " + " But was"
							+ getCount(TasksTab.css_TaskListingTasksCount),
							getCount(TasksTab.css_TaskListingTasksCount) == 2);
					performMultiActionsOnFile(actionType);
				}
			}

			else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY) && filesDueTodayActions > 0) {
				searchTasks(AutoTestFileDocRef4);
				waitUntilElementCountToBe(TasksTab.css_TaskListingTasksCount, 2);
				AutomationAssert.assertTrue("Expected Tasks Count:: 2 " + " But was"
						+ getCount(TasksTab.css_TaskListingTasksCount),
						getCount(TasksTab.css_TaskListingTasksCount) == 2);
				performMultiActionsOnFile(actionType);

			}

			else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)
					&& filesOverdueActions > 0) {
				searchTasks("AutoTestTaskFile");
				performMultiActionsOnFile(actionType);
			}

			else
				throw new PendingException();
		}

		else if ((actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS) || actionType
				.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY))
				&& (taskType.contains("Apps") && (formsIncompleteActions > 0) || formsDueTodayActions > 0)) {
			searchTasks(formSubject);
			if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS))
				waitUntilElementCountToBe(TasksTab.css_TaskListingTasksCount, 7);
			else
				waitUntilElementCountToBe(TasksTab.css_TaskListingTasksCount, 1);
			AutomationAssert.assertTrue("Expected Tasks Count:: 1 or 7" + " But was"
					+ getCount(TasksTab.css_TaskListingTasksCount), getCount(TasksTab.css_TaskListingTasksCount) == 7
					|| getCount(TasksTab.css_TaskListingTasksCount) == 1);
			performMultiActionsOnApp(actionType);

		}

		else
			throw new PendingException();

	}

	public void performMultiActionsOnFile(String actionType) throws InterruptedException, IOException {

		parentHandle = getCurrentWindow();
		waitUntilTasksLoaded();
		incompleteTaskList = findElements(TasksTab.css_TaskListingTasksCount);
		log.info("Post Filter TaskList Count:: " + incompleteTaskList.size());

		if (actionType.contains(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)
				|| actionType.contains(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {

			for (WebElement task : incompleteTaskList) {
				WebElement taskLink = task.findElement(TasksTab.btn_CompleteTask);
				String taskName = task.findElement(TasksTab.css_TaskListingTaskName).getText();
				log.info("TaskName:: " + taskName);

				waitUntilListElementIsClickable(taskLink);
				clickElementAndWait(taskLink);
				waitForSwitchWindow(2);
				switchWindow();

				if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT)) {
					waitUntilElementIsDisplayed(TasksTab.drp_BetaViewViewFileActionDropdown);
					clickElementAndWait(TasksTab.btn_BetaViewViewFileActionComplete);
					validateTaskSuccessMessage();
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
					try {
						waitUntilElementIsDisplayed(TasksTab.img_BetaViewViewFileActionCheckMark);
						AutomationAssert.assertTrue("Failure while completing action :: "
								+ AdoddleCommonStringPool.ACTION_FOR_INFORMATION,
								isDisplayed(TasksTab.img_BetaViewViewFileActionCheckMark));
					} catch (Throwable e) {
						log.error("ERROR : For Info verification Failed");
					}
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION)) {
					waitUntilElementIsDisplayed(TasksTab.drp_BetaViewViewFileActionDropdown);
					sendKeys(TasksTab.txt_BetaViewViewFileForActionTextArea, javaUtils.getRandomString(20));
					clickElementAndWait(TasksTab.btn_BetaViewViewFileActionComplete);
					validateTaskSuccessMessage();
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE)) {
					waitUntilElementIsDisplayed(TasksTab.sel_BetaViewViewFileStatusChangeDropdown);
					AutomationAssert.assertTrue(
							"Failure while validating Pop-up Status Change",
							getElementText(TasksTab.lbl_BetaViewViewFileStatusChange).contains(
									AdoddleCommonStringPool.LABEL_STATUS_CHANGE));
					waitUntilElementIsDisplayed(TasksTab.sel_BetaViewViewFileStatusChangeDropdown);
					if (!getElementText(TasksTab.sel_BetaViewViewFileOldStatus).contains(
							AdoddleCommonStringPool.STATUS_FOR_APPROVAL))
						selectByVisibleText(TasksTab.sel_BetaViewViewFileStatusChangeDropdown,
								AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
					else
						selectByVisibleText(TasksTab.sel_BetaViewViewFileStatusChangeDropdown,
								AdoddleCommonStringPool.STATUS_FOR_REVIEW);
					sendKeys(TasksTab.txt_BetaViewViewFileStatusChangeTextarea, javaUtils.getRandomString(10));
					waitUntilElementIsClickable(TasksTab.btn_BetaViewViewFileStatusChangeSave);
					clickElementAndWait(TasksTab.btn_BetaViewViewFileStatusChangeSave);
					waitForCompletePageLoad();

				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT)) {
					String taskCommentTittle = "AutoTestTaskComment" + epoch;
					waitUntilElementIsDisplayed(TasksTab.txt_BetaViewViewFileDistributeToUserField);
					sendKeys(TasksTab.txt_BetaViewViewFileDistributeToUserField,
							System.getProperty("secondary.username"));
					findElement(TasksTab.txt_BetaViewViewFileDistributeToUserField).sendKeys(
							Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
					sendKeys(FilesTab.txt_BetaFileViewNewDiscussionTitle, taskCommentTittle);
					sendKeys(FilesTab.btn_BetaFileViewNewDiscussionDescBody, javaUtils.getRandomString(20));
					clickElementAndWait(FilesTab.btn_BetaFileViewNewDiscussionSubmit);
					validateTaskSuccessMessage();

				}

				else {
					waitUntilElementIsDisplayed(TasksTab.txt_BetaViewViewFileDistributeToUserField);
					sendKeys(TasksTab.txt_BetaViewViewFileDistributeToUserField,
							ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
					findElement(TasksTab.txt_BetaViewViewFileDistributeToUserField).sendKeys(
							Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
					sendKeys(TasksTab.txt_BetaViewViewFileDistributeSubject, javaUtils.getRandomString(10));
					clickElementAndWait(TasksTab.btn_BetaViewViewFileDistribute);
					validateTaskSuccessMessage();

				}

				closeCurrentWindow();
				switchPreviousWindow(parentHandle);

			}

		}

		else {

			for (WebElement task : incompleteTaskList) {
				WebElement taskLink = task.findElement(TasksTab.btn_CompleteTask);
				String taskName = task.findElement(TasksTab.css_TaskListingTaskName).getText();
				log.info("TaskName:: " + taskName);
				waitUntilElementIsClickable(taskLink);
				clickElementAndWait(taskLink);
				waitForSwitchWindow(2);
				switchWindow();
				if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT)) {
					waitUntilElementIsDisplayed(TasksTab.drp_BetaViewViewFileActionDropdown);
					clickElementAndWait(TasksTab.btn_BetaViewViewFileActionComplete);
					validateTaskSuccessMessage();
					break;
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
					try {
						waitUntilElementIsDisplayed(TasksTab.img_BetaViewViewFileActionCheckMark);
						AutomationAssert.assertTrue("Failure while completing action :: "
								+ AdoddleCommonStringPool.ACTION_FOR_INFORMATION,
								isDisplayed(TasksTab.img_BetaViewViewFileActionCheckMark));
					} catch (Throwable e) {

						clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsViewPageFormDetails,
								TasksTab.img_BetaViewViewFileActionCheckMark);
						AutomationAssert.assertTrue("Failure while completing action :: "
								+ AdoddleCommonStringPool.ACTION_FOR_INFORMATION,
								isDisplayed(TasksTab.img_BetaViewViewFileActionCheckMark));
						log.error("ERROR : For Info verification Failed");
					}
					break;
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION)) {
					waitUntilElementIsDisplayed(TasksTab.drp_BetaViewViewFileActionDropdown);
					sendKeys(TasksTab.txt_BetaViewViewFileForActionTextArea, javaUtils.getRandomString(10));
					clickElementAndWait(TasksTab.btn_BetaViewViewFileActionComplete);
					validateTaskSuccessMessage();
					break;
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE)) {
					waitUntilElementIsDisplayed(TasksTab.sel_BetaViewViewFileStatusChangeDropdown);
					AutomationAssert.assertTrue(
							"Failure while validating Pop-up Status Change",
							getElementText(TasksTab.lbl_BetaViewViewFileStatusChange).contains(
									AdoddleCommonStringPool.LABEL_STATUS_CHANGE));
					waitUntilElementIsDisplayed(TasksTab.sel_BetaViewViewFileStatusChangeDropdown);
					if (!getElementText(TasksTab.sel_BetaViewViewFileOldStatus).contains(
							AdoddleCommonStringPool.STATUS_FOR_APPROVAL))
						selectByVisibleText(TasksTab.sel_BetaViewViewFileStatusChangeDropdown,
								AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
					else
						selectByVisibleText(TasksTab.sel_BetaViewViewFileStatusChangeDropdown,
								AdoddleCommonStringPool.STATUS_FOR_REVIEW);
					sendKeys(TasksTab.txt_BetaViewViewFileStatusChangeTextarea, javaUtils.getRandomString(10));
					clickElementAndWait(TasksTab.btn_BetaViewViewFileStatusChangeSave);
					break;
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT)) {
					waitUntilElementIsDisplayed(TasksTab.txt_BetaViewViewFileDistributeToUserField);
					String taskCommentTittle = "AutoTestTaskComment" + epoch;
					sendKeys(FilesTab.txt_BetaFileViewNewDiscussionTitle, taskCommentTittle);
					sendKeys(FilesTab.btn_BetaFileViewNewDiscussionDescBody, javaUtils.getRandomString(10));
					clickElementAndWait(FilesTab.btn_BetaFileViewNewDiscussionSubmit);
					validateTaskSuccessMessage();
					break;
				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION)) {
					waitUntilElementIsDisplayed(TasksTab.txt_BetaViewViewFileDistributeToUserField);
					sendKeys(TasksTab.txt_BetaViewViewFileDistributeToUserField,
							ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
					findElement(TasksTab.txt_BetaViewViewFileDistributeToUserField).sendKeys(
							Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
					sendKeys(TasksTab.txt_BetaViewViewFileDistributeSubject, javaUtils.getRandomString(10));
					clickElementAndWait(TasksTab.btn_BetaViewViewFileDistribute);
					validateTaskSuccessMessage();
					break;
				}

				else {
					log.info("OverDue Tasks not available");
					throw new PendingException();
				}

			}

			closeCurrentWindow();
			switchPreviousWindow(parentHandle);

		}

	}

	public void validateTaskSuccessMessage() {
		try {
			waitUntilElementIsDisplayed(TasksTab.lbl_TaskCompleted);
			waitUntilElementIsInvisible(TasksTab.lbl_TaskCompleted);
			waitForCompletePageLoad();

		} catch (Throwable e) {
			log.error("Error: Failure while validating task completion message");
		}

	}

	public void performMultiActionsOnApp(String actionType) throws InterruptedException, IOException {

		parentHandle = getCurrentWindow();
		waitUntilTasksLoaded();
		if (actionType.contains(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)
				|| actionType.contains(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {

			incompleteTaskList = findElements(TasksTab.css_TaskListingTasksCount);
			log.info("Post Filter TaskList Count:: " + incompleteTaskList.size());
			for (WebElement task : incompleteTaskList) {

				WebElement taskLink = task.findElement(TasksTab.btn_CompleteTask);
				String taskName = task.findElement(TasksTab.css_TaskListingTaskName).getText();
				log.info("TaskName:: " + taskName);

				if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT)) {
					waitUntilListElementIsClickable(taskLink);
					clickElementAndWait(taskLink);
					waitForSwitchWindow(2);
					switchWindow();
					waitUntilElementIsDisplayed(TasksTab.btn_BetaViewViewFileActionComplete);
					clickElementAndWait(TasksTab.btn_BetaViewViewFileActionComplete);
					validateTaskSuccessMessage();
					closeCurrentWindow();
					switchPreviousWindow(parentHandle);

				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION)) {

					waitUntilListElementIsClickable(taskLink);
					clickElementAndWait(taskLink);
					waitForSwitchWindow(2);
					switchWindow();
					waitUntilElementIsDisplayed(TasksTab.txt_BetaViewViewFileForActionTextArea);
					sendKeys(TasksTab.txt_BetaViewViewFileForActionTextArea, javaUtils.getRandomString(14));
					clickElementAndWait(TasksTab.btn_BetaViewViewFileActionComplete);
					validateTaskSuccessMessage();
					closeCurrentWindow();
					switchPreviousWindow(parentHandle);
				}

				/*
				 * else if
				 * (taskName.contains(AdoddleCommonStringPool.ACTION_RESPOND)) {
				 * waitUntilListElementIsClickable(taskLink); taskLink.click();
				 * waitForSwitchWindow(2); switchWindow();
				 * waitAndSwitchIframe(ProjectFormsTab
				 * .frm_BetaViewReplyFormIframe);
				 * waitUntilElementIsDisplayed(ProjectFormsTab
				 * .txt_CreateFormYourEditableResponse);
				 * sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse,
				 * javaUtils.getRandomString(20));
				 * sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse,
				 * Keys.TAB); waitUntilElementIsClickable(ProjectFormsTab.
				 * btn_CreateFormSendButton);
				 * clickElementAndWait(ProjectFormsTab
				 * .btn_CreateFormSendButton); switchDefault();
				 * closeCurrentWindow(); switchPreviousWindow(parentHandle); }
				 */

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_DISTRIBUTE)) {
					waitUntilListElementIsClickable(taskLink);
					clickElementAndWait(taskLink);
					waitForSwitchWindow(2);
					switchWindow();
					waitUntilElementIsDisplayed(TasksTab.txt_BetaViewViewFileDistributeToUserField);
					sendKeys(TasksTab.txt_BetaViewViewFileDistributeToUserField,
							System.getProperty("secondary.username"));
					findElement(TasksTab.txt_BetaViewViewFileDistributeToUserField).sendKeys(
							Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
					sendKeys(TasksTab.txt_BetaViewViewFileDistributeSubject, javaUtils.getRandomString(30));
					clickElementAndWait(TasksTab.btn_BetaViewViewFileDistribute);
					validateTaskSuccessMessage();
					closeCurrentWindow();
					switchPreviousWindow(parentHandle);

				}

				else if (taskName.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {

					waitUntilListElementIsClickable(taskLink);
					clickElementAndWait(taskLink);
					waitForSwitchWindow(2);
					switchWindow();
					try {
						waitUntilElementIsDisplayed(ProjectFormsTab.lbl_RequestForInformationFormTitle);
						AutomationAssert.verifyTrue(
								"Failure while validating Form XSN : Request For Information",
								getElementText(ProjectFormsTab.lbl_RequestForInformationFormTitle).contains(
										"Request For Information"));
					} catch (Throwable e) {
						log.error("ERROR : For Info verification Failed");
					}

					closeCurrentWindow();
					switchPreviousWindow(parentHandle);

				}

				else
					log.info("Expected Task not found :: " + taskName);

			}

		}

	}

	public void verifyActionCountDecreasedOnFilesTab(String actionType, int count) {
		waitUntilElementIsDisplayed(LandingPage.lnk_Files);
		clickElementAndWaitForElement(LandingPage.lnk_Files, FilesTab.lnk_DocListingFirstFileName);
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			try {
				filesIncompleteActions = filesIncompleteActions - count;
				Assert.assertEquals(
						Integer.parseInt(getToolTipText(FilesTab.lnk_FilesIncompleteActions).split(":")[1].trim()),
						filesIncompleteActions);
			} catch (Throwable t) {
				log.info(" Incomplete Expected Count: " + filesIncompleteActions);
			}

		} else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			try {
				Assert.assertEquals(
						Integer.parseInt(getToolTipText(FilesTab.lnk_FilesOverdueActions).split(":")[1].trim()),
						filesOverdueActions - count);
			} catch (Throwable t) {
				log.info("Overdue Expected: "
						+ Integer.parseInt(getToolTipText(FilesTab.lnk_FilesOverdueActions).split(":")[1].trim()));
				log.info("Overdue Actual: " + (filesOverdueActions - count));
			}
			filesOverdueActions = filesOverdueActions - count;
		}

		else {
			try {
				Assert.assertEquals(
						Integer.parseInt(getToolTipText(FilesTab.lnk_FilesDueTodayActions).split(":")[1].trim()),
						filesDueTodayActions - count);
			} catch (Throwable t) {
				log.info("DueToday Expected: "
						+ Integer.parseInt(getToolTipText(FilesTab.lnk_FilesDueTodayActions).split(":")[1].trim()));
				log.info("DueToday Actual: " + (filesDueTodayActions - count));
			}
			filesDueTodayActions = filesDueTodayActions - count;
		}
	}

	public void verifyDocActionSuccess(String docRef, String action) {
		boolean flag = false;
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Files);
		searchFiles(testFileDocRef);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		List<WebElement> actionCompletedList = findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList);
		for (WebElement e : actionCompletedList) {
			if (e.getText().contains(action)) {
				flag = true;
				break;
			}
		}
		AutomationAssert.verifyTrue(eStringUtils.getActionCompletionError(action), flag);

	}

	public void verifyTotalActionCountDecreasedOnDashboard(String actionType, int count) {
		navigateTab(LandingPage.lnk_Dashboard);
		log.info("Total Tab Actions: " + totalActions);
		log.info("Total Dashboard Actions: " + tooltipCount);
		log.info("All Action Size: " + actionSize);
		log.info("Single Action Size: " + count);

		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			try {
				log.info("Total Label Actions: "
						+ Integer
								.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteActionsCount).split(":")[1]
										.trim()));
				Assert.assertEquals(Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteActionsCount)
						.split(":")[1].trim()), tooltipCount - count);
				tooltipCount = tooltipCount - count;
			} catch (AssertionError e) {
				log.error("Total Incomplete Action Count did not decrease after Incomplete Actions performed");
			}
		} else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			try {
				log.info("Overdue Count-1 :::::"
						+ Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardOverDueActionsCount).split(": ")[1]
								.trim()));
				log.info("Overdue Count-2 :::::" + (tooltipCount - count));
				Assert.assertEquals(Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardOverDueActionsCount)
						.split(": ")[1].trim()), (tooltipCount - count));
				tooltipCount = tooltipCount - count;
			} catch (AssertionError e) {
				log.error("Total Overdue Action Count did not decrease after Overdue Actions performed");
			}
		}

		else {
			try {
				Assert.assertEquals(Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardDueTodayActionsCount)
						.split(":")[1].trim()), tooltipCount - count);
				tooltipCount = tooltipCount - count;
			} catch (AssertionError e) {
				log.error("Total Overdue Action Count did not decrease after Overdue Actions performed");
			}
		}
	}

	public void verifyActionCountDecreasedOnApp(String actionType, int count) {
		navigateTab(LandingPage.lnk_ProjectForms);

		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			log.info("App action label count: " + getToolTipText(ProjectFormsTab.lbl_FormsIncompleteActionsCount));
			log.info("App action decreased automation count: " + (formsIncompleteActions - count));
			AutomationAssert
					.verifyTrue(Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsIncompleteActionsCount).split(
							":")[1].trim()) == (formsIncompleteActions - count));
		}

		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			log.info("App action label count: " + getToolTipText(ProjectFormsTab.lbl_FormsOverdueActionsCount));
			log.info("App action decreased automation count: " + (formsOverdueActions - count));
			AutomationAssert.verifyTrue(Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsOverdueActionsCount)
					.split(":")[1].trim()) == (formsOverdueActions - count));
		}

		else {
			log.info("App action label count: " + getToolTipText(ProjectFormsTab.lbl_FormsDueTodayActionsCount));
			log.info("App action Previous automation count: " + formsDueTodayActions);
			try {
				AutomationAssert.verifyTrue(
						Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsDueTodayActionsCount).split(":")[1]
								.trim()) + " != " + (formsDueTodayActions - count),
						Integer.parseInt(getToolTipText(ProjectFormsTab.lbl_FormsDueTodayActionsCount).split(":")[1]
								.trim()) == (formsDueTodayActions - count));
			} catch (Throwable t) {
				log.error("ERROR: Ignored Count mismatch error due to concurrent execution");
			}
		}
	}

	public void verifyTotalActionCountDecreasedOnApp() {
		navigateTab(LandingPage.lnk_Dashboard);
		log.info("Actual app count on Dashboard:"
				+ Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteActionsCount).split(":")[1]
						.trim()));
		log.info("Expected Tooltip: " + tooltipCount);
		log.info("Expected App count: " + appCount);
		AutomationAssert
				.verifyTrue(
						Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteActionsCount).split(":")[1]
								.trim()) + " != " + (tooltipCount - appCount),
						Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteActionsCount).split(":")[1]
								.trim()) == (tooltipCount - appCount));

	}

	public void performActionFromDiscussionListing(String actionType) {

		log.info("This test is verified in another feature: Discussion-LH-Actions");
	}

	public void verifyActionCountDecreasedOnDiscussion(String actionType, int count) {
		log.info("This test is verified in another feature: Discussion-LH-Actions");
	}

	public void verifyTotalActionCountsDecreasedOnFilesTab(String actionType) {
		verifyActionCountDecreasedOnFilesTab(actionType, 6);

	}

	public void createAppActionsData(String actionType) throws InterruptedException {

		formSubject = "AutoTestTaskApp" + dateUtils.getEpoch();
		collectionDataMap.put("Form:: ", formSubject);
		clickElementWithText(ResourceHandler.loadProperty("create.form.testdata.folder"));
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_EmbeddedEmailCreateForm);
		clickElementAndWait(ProjectFormsTab.lnk_EmbeddedEmailCreateForm);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		switchIframe(ProjectFormsTab.frm_createFormIframe);

		// if
		// (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true"))
		// {

		waitUntilElementIsDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		if (actionType.contains(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, appGroupName);
			waitUntilElementIsDisplayed(By
					.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'"
							+ appGroupName + "')]"));
			clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + appGroupName
					+ "')]//preceding::input[@type='checkbox']"));
		}

		else {

			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo,
					ResourceHandler.loadProperty("actions.test.user1.id"));
			waitUntilElementIsDisplayed(By
					.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'"
							+ ResourceHandler.loadProperty("actions.test.user1.id") + "')]"));
			clickElementAndWait(By
					.xpath(".//div[@id='distInputTo']//span[contains(text(),'"
							+ ResourceHandler.loadProperty("actions.test.user1.id")
							+ "')]//preceding::input[@type='checkbox']"));

			clickElementAndWait(ProjectFormsTab.txt_BetaViewRFIFormDistributeAction);
			waitUntilElementIsDisplayed(ProjectFormsTab.drp_BetaViewCreateFormAssignActionToUser);
			selectByVisibleText(ProjectFormsTab.drp_BetaViewCreateFormAssignActionToUser,
					AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
			clickElementAndWaitForElement(ProjectFormsTab.img_CreateControllerFormDatePicker,
					ProjectFormsTab.img_CreateControllerFormAngularActiveDate);
			clickElementAndWait(ProjectFormsTab.img_CreateControllerFormAngularActiveDate);
			clickElement(ProjectFormsTab.txt_BetaViewRFIFormDistributeAction);

		}
		// }

		/*
		 * else {
		 * 
		 * sendKeys(ProjectFormsTab.txt_FormInputToField,
		 * ResourceHandler.loadProperty("actions.test.user1.id"));
		 * sendKeys(ProjectFormsTab.txt_FormInputToField, Keys.TAB);
		 * waitForCompletePageLoad();
		 * clickElementAndWait(ProjectFormsTab.drp_CreateFormDistributionUserAction
		 * ); waitUntilElementIsDisplayed(ProjectFormsTab.
		 * lnk_CreateFormDistributionActionActiveDate);
		 * clickElementAndWait(ProjectFormsTab
		 * .lnk_CreateFormDistributionActionActiveDate); }
		 */

		clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		sendKeys(ProjectFormsTab.txt_CreateFormSubject, formSubject);
		sendKeys(ProjectFormsTab.txt_CreateFormDescription, javaUtils.getRandomString(30));
		try {
			clickElement(ProjectFormsTab.btn_CreateFormSave);
			clickElementAndWait(ProjectFormsTab.btn_CreateFormSave);
			verifyAppSuccessMessage();
		} catch (Throwable t) {
			log.info("error while clicking save button multiple times and verifying message");
		}
		switchDefault();
	}

	public void verifyAppSuccessMessage() {
		try {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_FormAddSuccessMsg);
			verifyElementText(ProjectFormsTab.lbl_FormAddSuccessMsg, AdoddleCommonStringPool.FORM_SUCCESS_MESSAGE);
		} catch (Throwable t) {
			log.info("Error while clicking save button multiple times and verifying message");
		}
	}

	public void createTestData(String actionType) throws InterruptedException, IOException {

		navigateTab(LandingPage.lnk_Files);
		clickElementWithText("AutomationUploadFiles");
		if (actionType.contains(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {

			fileDocRefs = sysUtils.getFileList(AutoTestFileDocRef1 + "," + AutoTestFileDocRef2 + ","
					+ AutoTestFileDocRef3);
			collectionDataMap.put("Uploaded fileDocRefs:: ", fileDocRefs.toString());
			uploadDocuments(null, 3, fileDocRefs, null, false, 1, null, null, null, null);
			for (String docRef : fileDocRefs) {
				searchFiles(docRef);
				AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
				contextClick(FilesTab.lnk_DocListingFirstFileName);
				mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare,
						FilesTab.opt_FileContextClickShareDistribute);
				if (docRef.contains(AutoTestFileDocRef1))
					assignFileActionsToMultipleUsers(userList1, multiActionList1);
				else if (docRef.contains(AutoTestFileDocRef2))
					assignFileActionsToMultipleUsers(userList1, multiActionList2);
				else
					assignFileActionsToMultipleUsers(userList1, multiActionList3);
				sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.TAB);
				sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject,
						javaUtils.getRandomString(10) + dateUtils.getEpoch());
				clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);

			}
		}

		else {

			fileDocRefs = sysUtils.getFileList(AutoTestFileDocRef4);
			collectionDataMap.put("Uploaded fileDocRefs:: ", fileDocRefs.toString());
			uploadDocuments(null, 1, fileDocRefs, null, false, 1, null, null, null, null);
			searchFiles(AutoTestFileDocRef4);
			AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
			contextClick(FilesTab.lnk_DocListingFirstFileName);
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
			assignFileActionsToMultipleUsers(userList2, multiActionList3);
			sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.TAB);
			sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject,
					javaUtils.getRandomString(10) + dateUtils.getEpoch());
			clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);

		}

	}

	public void createGroupAndMember(String group, String member1, String member2, String workspace)
			throws InterruptedException {

		groupName = group + epoch;
		collectionDataMap.put("Group:: ", groupName);
		clickElementAndWait(TasksTab.btn_TasksGroupCreateLink);
		waitUntilElementIsDisplayed(TasksTab.txt_TasksGroupName);
		sendKeys(TasksTab.txt_TasksGroupName, groupName);
		sendKeys(TasksTab.txt_TasksGroupProject, workspace);
		waitUntilElementContainsText(TasksTab.css_TasksGroupProjectSearchResult, workspace);
		sendKeys(TasksTab.txt_TasksGroupProject, Keys.ENTER);
		waitUntilElementIsDisplayed(TasksTab.txt_TasksGroupMembers);
		waitUntilElementIsEnabled(TasksTab.txt_TasksGroupMembers);
		sendKeys(TasksTab.txt_TasksGroupMembers, member1);
		waitUntilElementContainsText(TasksTab.css_TasksGroupMemembersSearchResult, member1);
		sendKeys(TasksTab.txt_TasksGroupMembers, Keys.ENTER);
		waitUntilElementIsDisplayed(TasksTab.txt_TasksGroupMembers);
		waitUntilElementIsEnabled(TasksTab.txt_TasksGroupMembers);
		sendKeys(TasksTab.txt_TasksGroupMembers, member2);
		waitUntilElementContainsText(TasksTab.css_TasksGroupMemembersSearchResult, member2);
		sendKeys(TasksTab.txt_TasksGroupMembers, Keys.ENTER);
		sendKeys(TasksTab.txt_TasksGroupDescriptions, ResourceHandler.loadProperty("special.char.test.string"));
		clickElementAndWait(TasksTab.btn_TasksGroupSave);

	}

	public void validateGroupCreationSuccessMessage() {
		waitUntilElementIsDisplayed(TasksTab.lbl_TasksGroupNotificationMessage);
		try {
			verifyElementText(TasksTab.lbl_TasksGroupNotificationMessage,
					AdoddleCommonStringPool.GROUP_SUCCESS_MESSAGE, 10);
		} catch (Throwable t) {
			log.error("Group Success Message could not be verified");
		}
		waitForCompletePageLoad();

	}

	public void createAdhocTasks(String adhocTask) throws InterruptedException {
		clickElementWithText(groupName);
		for (int i = 1; i <= 6; i++) {
			String adhocMemberTask = adhocTask + i + "_" + epoch;
			collectionDataMap.put("Group Members:: ", adhocMemberTask);
			sendKeys(TasksTab.txt_TasksGroupTaskTitle, adhocMemberTask);
			clickElementAndWait(TasksTab.lnk_TasksGroupAddTaskLink);
			waitUntilElementContainsValue(TasksTab.txt_TasksGroupSummary, adhocMemberTask);
			waitForCompletePageLoad();

		}

	}

	public void assignAdhocTasks(String member1, String member2) throws InterruptedException {
		navigateTab(LandingPage.lnk_Tasks);
		clickElementWithText(groupName);
		waitUntilElementContainsText(TasksTab.lbl_TasksTaskPanelHeader, groupName);
		waitUntilTasksLoaded();
		AutomationAssert.assertTrue("Failure while loading Adhoc Tasks",
				getElementText(TasksTab.lbl_TasksTaskPanelHeader).contains(groupName));
		incompleteTaskList = findElements(TasksTab.css_AdhocTaskListingTasksCount);
		log.info("Post Filter TaskList Count:: " + incompleteTaskList.size());
		AutomationAssert.assertTrue("Failure while creating Adhoc tasks", incompleteTaskList.size() == 6);
		for (WebElement ele : incompleteTaskList) {
			WebElement taskAsignee = ele.findElement(TasksTab.css_AdhocTaskListingTaskAsignee);
			clickElementAndWaitForElement(ele, taskAsignee);
			clickElementAndWait(taskAsignee);
			waitUtils.waitInterval(1);
			clickElementAndWait(ele.findElement(TasksTab.css_AdhocTaskListingTaskAsigneeClearLink));

			if (ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
					.contains("AdhocAutoUserTask1")
					|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
							.contains("AdhocAutoUserTask2")
					|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
							.contains("AdhocAutoUserTask3")) {
				sendKeys(TasksTab.txt_AdhocTaskListingTaskAsigneeInput, member1);
				waitUntilElementContainsText(TasksTab.txt_AdhocTaskListingTaskAsigneeResult, member1);
			}

			else {
				sendKeys(TasksTab.txt_AdhocTaskListingTaskAsigneeInput, member2);
				waitUntilElementContainsText(TasksTab.txt_AdhocTaskListingTaskAsigneeResult, member2);
			}

			clickElementAndWait(TasksTab.txt_AdhocTaskListingTaskAsigneeResult);
			clickElementAndWait(ele);
		}

	}

	public void validatePendingAdhocTasks() {
		log.info("Covered in <updateAndDelegateMultipleTasks> definition");
	}

	public void updateAndDelegateMultipleTasks(String member2) throws InterruptedException {

		clickElementWithText(groupName);
		waitUntilElementContainsText(TasksTab.lbl_TasksTaskPanelHeader, groupName);
		waitUntilTasksLoaded();
		AutomationAssert.assertTrue("Failure while loading Adhoc Tasks",
				getElementText(TasksTab.lbl_TasksTaskPanelHeader).contains(groupName));
		incompleteTaskList.clear();
		incompleteTaskList = findElements(TasksTab.css_AdhocTaskListingTasksCount);
		log.info("Post Filter TaskList Count:: " + incompleteTaskList.size());
		AutomationAssert.assertTrue("Failure while creating Adhoc tasks", incompleteTaskList.size() == 3);
		for (WebElement ele : incompleteTaskList) {

			if (ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
					.contains("AdhocAutoUserTask2")
					|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
							.contains("AdhocAutoUserTask3")) {

				WebElement taskAsignee = ele.findElement(TasksTab.css_AdhocTaskListingTaskAsignee);
				clickElementAndWait(ele);
				clickElementAndWait(taskAsignee);
				waitUtils.waitInterval(1);
				clickElementAndWait(ele.findElement(TasksTab.css_AdhocTaskListingTaskAsigneeClearLink));
				sendKeys(TasksTab.txt_AdhocTaskListingTaskAsigneeInput, member2);
				waitUntilElementContainsText(TasksTab.txt_AdhocTaskListingTaskAsigneeResult, member2);
				clickElementAndWait(TasksTab.txt_AdhocTaskListingTaskAsigneeResult);
				clickElementAndWait(ele);
				waitForCompletePageLoad();

			}

			else {
				WebElement taskStausLink = ele.findElement(TasksTab.btn_AdhocTaskListingTaskStatus);
				clickElementAndWait(taskStausLink);
				availableTasksStatuses = ele.findElements(TasksTab.css_AdhocTaskListingTasksStatuses);
				log.info("Post Filter TaskList Count:: " + availableTasksStatuses.size());
				for (WebElement taskStatus : availableTasksStatuses) {
					if (taskStatus.getText().contains("In Progress"))
						clickElementAndWait(taskStatus);
					else
						log.info("Expected Status not found");

				}

			}

		}

	}

	public void updateMultipleTasksStatus(String user) throws InterruptedException {

		clickElementWithText(groupName);
		waitUntilElementContainsText(TasksTab.lbl_TasksTaskPanelHeader, groupName);
		waitUntilTasksLoaded();
		AutomationAssert.assertTrue("Failure while loading Adhoc Tasks",
				getElementText(TasksTab.lbl_TasksTaskPanelHeader).contains(groupName));
		incompleteTaskList.clear();
		incompleteTaskList = findElements(TasksTab.css_AdhocTaskListingTasksCount);
		log.info("Post Filter TaskList Count:: " + incompleteTaskList.size());
		AutomationAssert.assertTrue("Failure while validating Adhoc User Task Count.", incompleteTaskList.size() == 5
				|| incompleteTaskList.size() == 6);
		for (WebElement ele : incompleteTaskList) {

			WebElement taskStausLink = ele.findElement(TasksTab.btn_AdhocTaskListingTaskStatus);
			taskStausLink.click();
			availableTasksStatuses = ele.findElements(TasksTab.css_AdhocTaskListingTasksStatuses);
			log.info("Post Filter TaskList Count:: " + availableTasksStatuses.size());
			for (WebElement taskStatus : availableTasksStatuses) {

				if (user.contains(ResourceHandler.loadProperty("test.user.actions.test.user2.name"))) {

					if (ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
							.contains("AdhocAutoUserTask2")
							&& taskStatus.getText().contains("Cancelled")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask3")
							&& taskStatus.getText().contains("Completed")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask4")
							&& taskStatus.getText().contains("Archived")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask5")
							&& taskStatus.getText().contains("On Hold")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask6") && taskStatus.getText().contains("In Progress"))
						taskStatus.click();

					else
						log.info("Expected Status not found");

				}

				else {

					if (ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
							.contains("AdhocAutoUserTask1")
							&& taskStatus.getText().contains("Cancelled")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask2")
							&& taskStatus.getText().contains("Cancelled")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask3")
							&& taskStatus.getText().contains("Cancelled")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask4")
							&& taskStatus.getText().contains("Completed")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask5")
							&& taskStatus.getText().contains("Completed")
							|| ele.findElement(TasksTab.css_AdhocTaskListingTaskName).getAttribute("value")
									.contains("AdhocAutoUserTask6") && taskStatus.getText().contains("Completed"))
						taskStatus.click();

				}
			}

		}

	}

	public void deactivateTasksGroup() {

		try {
			workspacesGroups = findElements(TasksTab.css_TasksGroupsList);
			log.info("Group Count:: " + workspacesGroups.size());
			for (WebElement group : workspacesGroups) {
				if (group.getAttribute("title").contains(groupName)) {
					mouseHoverWebElement(group);
					group.findElement(TasksTab.css_TasksGroupEditIcon).click();
					waitUntilElementIsDisplayed(TasksTab.css_TasksEditGroupInactiveSwitchTab);
					clickElementAndWait(TasksTab.css_TasksEditGroupInactiveSwitchTab);
					clickElementAndWait(TasksTab.btn_TasksGroupSave);
					validateGroupUpdatedSuccessMessage();
					break;

				}

			}
		} catch (Throwable e) {

			log.error("Failure While Deactivating Task Group.");

		}

	}

	public void applyTasksFilter() {
		clickElementWithText(groupName);
		waitUntilElementContainsText(TasksTab.lbl_TasksTaskPanelHeader, groupName);
		waitUntilTasksLoaded();
		clickElementAndWaitForElement(TasksTab.btn_TasksFilter, TasksTab.css_TasksFilterDropdown);
		clickElementAndWait(TasksTab.lbl_TasksFilterDropdownStatusAll);
		clickElementAndWait(TasksTab.lbl_TasksFilterDropdownAsigneeAll);
		waitForCompletePageLoad();

	}

	public void validateGroupUpdatedSuccessMessage() {
		waitUntilElementIsDisplayed(TasksTab.lbl_TasksGroupNotificationMessage);
		try {
			verifyElementText(TasksTab.lbl_TasksGroupNotificationMessage,
					AdoddleCommonStringPool.GROUP_UPDATED_MESSAGE, 10);
		} catch (Throwable t) {
			log.error("Group Success Message could not be verified");
		}
		waitForCompletePageLoad();

	}

	public void loggedInAsUser(String userName) throws InterruptedException {
		logOut();
		if (userName.contains(ResourceHandler.loadProperty("test.user.actions.test.user1.name"))) {
			login(ResourceHandler.loadProperty("actions.test.user1.id"), resourceUtils.getCommonUserPassword());
		} else if (userName.contains(ResourceHandler.loadProperty("test.user.actions.test.user2.name"))) {
			login(ResourceHandler.loadProperty("actions.test.user2.id"), resourceUtils.getCommonUserPassword());
		} else
			log.info("User Creds/password failed to match ");

		setGlobalProject(System.getProperty("global.test.project"));

	}

	public String getToolTipText(By locator) {
		String tooltipText;
		tooltipText = findElement(locator).getAttribute("title");
		log.info("ToolTip Text:: " + tooltipText);
		waitUtils.waitForCompletePageLoad();
		releaseElementClick(locator);
		return tooltipText;
	}

	public void waitUntilTasksLoaded() {
		try {
			waitUntilElementIsDisplayed(GlobalPageElements.ele_LoadingCircle, 5);
		} catch (Throwable e) {
			log.info("Tasks Loaded successfully.");
			waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);

		}
	}

	public void setGlobalProject(String selectedProject) {

		waitUntilElementIsDisplayed(GlobalPageElements.lbl_FilterProject);
		if (!getElementText(GlobalPageElements.lbl_FilterProject).contains(selectedProject)) {
			log.info("Project filter not set to All, current project: "
					+ getElementText(GlobalPageElements.lbl_FilterProject).split(":")[1] + "\t<THID: "
					+ Thread.currentThread().getId() + ">");
			clickElementAndWait(GlobalPageElements.btn_SearchProjectFilterButton);
			waitUntilListOfElementIsDisplayed(GlobalPageElements.css_FilterDropdownSuggestedTypeList);

			if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
				clickElementAndWait(GlobalPageElements.lnk_FilterProjectClearAll);

			for (WebElement project : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
				if (project.getAttribute("title").contains(selectedProject)) {
					project.click();
					break;
				}
			}
			clickElementAndWaitForElement(GlobalPageElements.btn_SearchProjectFilterButton, LandingPage.lnk_Dashboard);
			clickElementAndWait(LandingPage.lnk_Dashboard);
		}

	}

	// ******************Override SendKey Method********************

	public void sendKeys(By locator, String keys) {
		waitUntilElementIsClickable(locator);
		waitUtils.waitForCompletePageLoad();
		try {
			clear(locator);
		} catch (Throwable t) {
			log.info("Clear operation failure");
		}
		findElement(locator).sendKeys(keys);
		waitUtils.waitForCompletePageLoad();
	}
}