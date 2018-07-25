/*  Testdata required for this script as follows.
     1). Template: AutomationTestProject_Template  
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.CommonLocators.AdoddleWorkflowsLocators.WorkflowsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.utils.JavaUtils;
import org.asite.automation.common.utils.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateEditSystemActionsScripts extends AdoddleCommonAppMethods {

	private String systemActionName;
	private String workflowName;
	private String workflowTrigger;
	private static String editedSystemActionName;
	private static String editedWorkflowDefinitionName;
	final private static List<String> systemActionNames = new ArrayList<String>();
	final private String fileDocRef1 = "BatchPrintDocRef1_" + dateUtils.getEpoch();
	final private String fileDocRef2 = "BatchPrintDocRef2_" + dateUtils.getEpoch();
	final private String fileDocRef3 = "BatchPrintDocRef3_" + dateUtils.getEpoch();
	final private String fileDocRef4 = "BatchPrintDocRef4_" + dateUtils.getEpoch();
	final private String fileDocRef5 = "BatchPrintDocRef5_" + dateUtils.getEpoch();

	final private String projectFolder = "Automation_WF_Folder";
	private List<String> filedocRefList = new ArrayList<String>();
	final private List<String> docSatusList = new ArrayList<String>();
	private List<WebElement> userDistributionList = new ArrayList<WebElement>();
	private String currentWindowHandle = null;
	final private static List<String> uploadMultiDocumentsList = new ArrayList<String>();
	final private static Logger log = AutomationLogger.getInstance().getLogger(CreateEditSystemActionsScripts.class);

	public void setUpWorkspaceCalenderInClonedWorkspace() {

		searchProjects(clonedProject);
		Assert.assertTrue("Failure while validating Workspace in Listing" + clonedProject,
				getElementText(ProjectsTab.lnk_ProjectsTabFirstProjectName).contains(clonedProject));
		contextClick(ProjectsTab.lnk_ProjectsListFirstProject);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit,
				ProjectsTab.opt_ProjectContextClickEditProject);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectsTab.lnk_PopEditProjectWorkingCalender);
		clickElementAndWait(ProjectsTab.lnk_PopEditProjectWorkingCalender);
		setWorkspaceWorkingDays();
		clickElementAndWait(ProjectsTab.btn_PopEditProjectEdit);
		waitUntilElementIsInvisible(ProjectsTab.btn_PopEditProjectEdit);
		waitForCompletePageLoad();

	}

	private void setWorkspaceWorkingDays() {
		List<WebElement> workspaceWorkingDays = findElements(ProjectsTab.chk_PopEditProjectWorkingDaysList);
		for (WebElement web : workspaceWorkingDays) {
			if (web.getAttribute("name").contains("saturday") || web.getAttribute("name").contains("sunday"))
				web.click();
			else
				log.info("Workspace working days");
		}

	}

	public void clickLinks(String configureSystemAction) {
		if (configureSystemAction.contains("Configure System Action")) {
			waitUntilElementIsClickable(WorkflowsTab.lnk__WorkflowsConfigureSystemActionLink);
			clickElementAndWait(WorkflowsTab.lnk__WorkflowsConfigureSystemActionLink);
			waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureSystemActionName);
		}

		else if (configureSystemAction.contains("Create Workflow Definition")) {
			waitUntilElementIsClickable(WorkflowsTab.lnk__WorkflowsCreateWorkflowDefinitionLink);
			clickElementAndWait(WorkflowsTab.lnk__WorkflowsCreateWorkflowDefinitionLink);
			waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsCreateWorkflowDefinitionWorkflowName);
		}

		else {

			waitUntilElementIsClickable(WorkflowsTab.lnk__WorkflowsCreateTriggerLink);
			clickElementAndWait(WorkflowsTab.lnk__WorkflowsCreateTriggerLink);
			waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureTriggerName);

		}

	}

	public void createEditSystemAction(String actionName) {

		systemActionName = actionName + dateUtils.getEpoch();
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionName, systemActionName);
	}

	public void selectActionType(String actionType) {
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureSystemActionTypeDropdown, actionType);
	}

	public void selectDistributeToUser(String distributedUser1, String distributedUser2, String distributionGroup,
			String userRole, String Org) {
		waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown, distributedUser1);
		waitUntilElementIsPresent(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown, distributedUser2);
		waitUntilElementIsPresent(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown, distributionGroup);
		waitUntilElementIsPresent(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown, userRole);
		waitUntilElementIsPresent(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown, Org);
		waitUntilElementIsDisplayed(By
				.xpath(".//ul[@class='select2-results']//li//div[@class='select2-result-label'][contains(text(),'Organisation')]//following::span[contains(text(),'"
						+ Org + "')]"));

		clickElementAndWait(By
				.xpath(".//ul[@class='select2-results']//li//div[@class='select2-result-label'][contains(text(),'Organisation')]//following::span[contains(text(),'"
						+ Org + "')]"));

	}

	public void assignActionAndDueDays(String role, String org) {

		waitUntilListOfElementIsDisplayed(WorkflowsTab.pop_EditActionConfigurationUserList);
		List<WebElement> usersGroupList = findElements(WorkflowsTab.pop_EditActionConfigurationUserList);

		for (WebElement web : usersGroupList) {

			if (web.getAttribute("title").contains(ResourceHandler.getPropertyValue("test.user.auto.rfi.name"))
					|| web.getAttribute("title").contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))) {

				WebElement toggleDropDown = web.findElement(WorkflowsTab.btn_EditActionConfigurationToggleDropDown);
				waitUntilElementIsClickable(toggleDropDown);
				toggleDropDown.click();
				waitUntilElementIsDisplayed(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActions);
				selectByVisibleText(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActions,
						AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION);
				selectByVisibleText(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActionDueDate, "12");
				clickElementAndWait(WorkflowsTab.css_EditActionConfigurationToggleDropDownSpanDismiss);
				waitUntilElementIsInvisible(WorkflowsTab.css_EditActionConfigurationToggleDropDownSpanDismiss);
				waitForCompletePageLoad();

			}

			else if (web.getAttribute("title").contains(role)) {

				WebElement toggleDropDown = web.findElement(WorkflowsTab.btn_EditActionConfigurationToggleDropDown);

				waitUntilElementIsClickable(toggleDropDown);
				toggleDropDown.click();
				waitUntilElementIsDisplayed(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActions);
				selectByVisibleText(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActions,
						AdoddleCommonStringPool.ACTION_FOR_COMMENT);
				selectByVisibleText(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActionDueDate, "14");
				clickElementAndWait(WorkflowsTab.css_EditActionConfigurationToggleDropDownSpanDismiss);
				waitUntilElementIsInvisible(WorkflowsTab.css_EditActionConfigurationToggleDropDownSpanDismiss);
				waitForCompletePageLoad();
			}

			else if (web.getAttribute("title").contains(org)) {

				WebElement toggleDropDown = web.findElement(WorkflowsTab.btn_EditActionConfigurationToggleDropDown);

				waitUntilElementIsClickable(toggleDropDown);
				toggleDropDown.click();
				waitUntilElementIsDisplayed(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActions);
				selectByVisibleText(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActions,
						AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE);
				selectByVisibleText(WorkflowsTab.sel_EditActionConfigurationToggleDropDownActionDueDate, "3");
				clickElementAndWait(WorkflowsTab.css_EditActionConfigurationToggleDropDownSpanDismiss);
				waitUntilElementIsInvisible(WorkflowsTab.css_EditActionConfigurationToggleDropDownSpanDismiss);
				waitForCompletePageLoad();
			}

			else
				log.info("Already Action assigned Distribution Group");

		}

	}

	public void putSubject() {
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionSubject,
				ResourceHandler.loadProperty("special.char.test.string"));
	}

	public void clickcreateButton() {
		clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureSystemActionCreateButton);
	}

	public void validateSystemAction(String createdSystemAction) {

		List<WebElement> systemActionsElements = findElements(WorkflowsTab.css_WorkflowsAllSystemActionList);

		int i = 0;

		for (WebElement e : systemActionsElements) {

			if (e.getText().contains("Auto_Distribute_SystemTask")
					|| e.getText().contains("Auto_StatusChange_SystemTask")
					|| e.getText().contains("Auto_ClearTasks_SystemTask")
					|| e.getText().contains("Auto_UpdateFilePrivacy_SystemTask")
					|| e.getText().contains("Auto_PublishAsPDF_SystemTask")) {
				Assert.assertTrue(e.getText().contains(systemActionNames.get(i)));
			} else
				log.info("Expected:  " + e.getText() + "\nActual: " + systemActionNames.get(i));

			i++;
		}
		AdoddleScenarioMarkers.createEditSystemActionsScriptFlag = createdSystemAction.contains("PublishAsPDF");
	}

	public void verifyEditpopup() {
		log.info("Covered in <validateEditedSystemAction> defination");
	}

	public void editSystemAction() {
		log.info("Covered in <validateEditedSystemAction> defination");
	}

	public void changeSystemActionName() {
		log.info("Covered in <validateEditedSystemAction> defination");
	}

	public void validateEditedSystemAction(String editedSysAction) {
		if (editedSysAction.contains("Auto_Distribute_SystemTask")) {
			editedSystemActionName = editedSysAction + dateUtils.getEpoch();
			systemActionNames.add(editedSystemActionName);
			waitUntilLinkWithTextIsVisible(systemActionName);
			clickLinkWithText(systemActionName);
			waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureSystemActionName);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionName, editedSystemActionName);
			clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureSystemActionCreateButton);
		}

		else {

			clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelSystemActionLink);
			clickLinkWithText(systemActionNames.get(4));
			editedSystemActionName = editedSysAction + dateUtils.getEpoch();
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionName, editedSystemActionName);
			if (isSelected(WorkflowsTab.chk_PublishAsPDFStartWorkflowOnFilePublish))
				clickElement(WorkflowsTab.chk_PublishAsPDFStartWorkflowOnFilePublish);
			clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureSystemActionCreateButton);

		}

	}

	public void createSystemAction(String sysAction) {

		systemActionName = sysAction + dateUtils.getEpoch();
		systemActionNames.add(systemActionName);
		log.info("systemActionNames ::::" + systemActionNames);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionName, systemActionName);

	}

	public void performStatusChange(String fromStatus, String toStatus) {
		waitUntilElementIsDisplayed(WorkflowsTab.sel_workflowsConfigureSystemActionStatusFrom);
		selectByVisibleText(WorkflowsTab.sel_workflowsConfigureSystemActionStatusFrom, fromStatus);
		selectByVisibleText(WorkflowsTab.sel_workflowsConfigureSystemActionStatusTo, toStatus);
	}

	public void putStatusChangeReason(String statusChangeReason) {
		sendKeys(WorkflowsTab.txt_workflowsConfigureSystemActionReasonforStatusChange,
				ResourceHandler.loadProperty("special.char.test.string"));
	}

	public void selectClearActionsAttributes() {
		log.info("Covered in <createSystemAction> Definition");
	}

	public void selectUpdateFilePrivacyAttributes() {

		if (!isSelected(WorkflowsTab.rad_SystemActionMarkfileVersionPrivate)) {
			clickElement(WorkflowsTab.rad_SystemActionMarkfileVersionPrivate);
		}

	}

	public void createWorkflowDefinition(String wrkflowName) {
		String workflowdays = JavaUtils.getRandomNumber(2);
		workflowName = wrkflowName + dateUtils.getEpoch();
		systemActionNames.add(systemActionName);
		log.info("systemActionNames ::::" + systemActionNames);
		sendKeys(WorkflowsTab.txt_WorkflowsCreateWorkflowDefinitionWorkflowName, workflowName);
		sendKeys(WorkflowsTab.txt_WorkflowsCreateWorkflowDefinitionWorkflowDescription, javaUtils.getRandomString(10));
		sendKeys(WorkflowsTab.txt_WorkflowsCreateWorkflowDefinitionWorkflowDays, workflowdays);
		clickElementAndWait(WorkflowsTab.btn_WorkflowsCreateWorkflowDefinitionCreateButton);

	}

	public void saveWorkflowDefinition() {
		log.info("Covered in <createWorkflowDefinition> definiton");
	}

	public void validateVisualWorkflowDesigner() {
		String workflowHeading = getElementText(WorkflowsTab.ele_WorkflowsVisualDesigner);
		log.info("Workflow Heading::" + workflowHeading);
		log.info("Created WorkflowDefinition::" + workflowName);
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(WorkflowsTab.ele_WorkflowsVisualDesigner).contains(workflowName));

	}

	public void designWorkflow() throws InterruptedException {

		int x = 300, y = 200, index;

		waitAndSwitchIframe(WorkflowsTab.frm_Iframe);
		waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeTasks);
		clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeTasks,
				WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTask);
		clickElementAndWait(WorkflowsTab.ele_WorkflowVisualDesignerIframeEndEvent);
		Actions builder = new Actions(getWebDriver());
		List<WebElement> nodes = new ArrayList<WebElement>();
		nodes.add(findElement(WorkflowsTab.ele_WorkflowsVisualDesignerStartNode));

		for (index = 1; index < 8; index++) {
			WebElement fromElement;

			switch (index) {
			case 2:
				builder.dragAndDrop(findElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTask),
						findElement(WorkflowsTab.ele_IframeWorkflowDesignArea)).build().perform();
				if (isDisplayed(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse))
					clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);

				fromElement = findElement(By
						.cssSelector("g[class='stencils'] g[class='children']  g[id*='svg-sid']:nth-child("
								+ (Integer.toString(index + 1)) + ") rect[stroke='black']"));
				nodes.add(fromElement);
				break;
			case 7:
				builder.dragAndDrop(findElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeEndTask),
						findElement(WorkflowsTab.ele_IframeWorkflowDesignArea)).build().perform();
				if (isDisplayed(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse))
					clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				fromElement = findElement(By
						.cssSelector("g[class='stencils'] g[class='children']  g[id*='svg-sid']:nth-child("
								+ (Integer.toString(index + 1)) + ") circle[stroke='black']"));
				nodes.add(fromElement);
				break;
			default:
				builder.dragAndDrop(findElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTask),
						findElement(WorkflowsTab.ele_IframeWorkflowDesignArea)).build().perform();
				if (isDisplayed(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse))
					clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				fromElement = findElement(By
						.cssSelector("g[class='stencils'] g[class='children']  g[id*='svg-sid']:nth-child("
								+ (Integer.toString(index + 1)) + ") rect[stroke='black']"));
				nodes.add(fromElement);
				break;
			}

			WebElement toElement = findElement(WorkflowsTab.ele_IframeWorkflowDesignArea);
			if (isDisplayed(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse))
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
			builder.clickAndHold(fromElement).build().perform();
			builder.moveToElement(toElement, x, y).build().perform();
			builder.release(toElement).build().perform();
			waitUtils.waitInterval(1);
			x += 200;

		}

		for (index = 0; index < nodes.size(); index++) {
			log.info("Nodes size::" + nodes.size());

			switch (index) {
			case 0: {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				break;
			}
			case 1: {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName,
						AdoddleCommonStringPool.OPTION_DISTRIBUTE_FILES);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField,
						WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription);
				sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription, "DistributeFiles"
						+ dateUtils.getEpoch());
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType,
						AdoddleCommonStringPool.OPTION_DISTRIBUTE_FILES);
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				Assert.assertTrue(getElementText(WorkflowsTab.pop_WorkflowVisualDesignerIframeSystemTaskPopup)
						.contains(AdoddleCommonStringPool.WORKFLOW_POPUP_SYSTEM_TASK));
				if (!isSelected(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask)) {
					clickElement(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask);

				}
				clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeSelectActionPopupOkay);

				break;
			}
			case 2: {
				nodes.get(index).click();
				String workflowDays = "5D";
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName,
						AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField,
						WorkflowsTab.txt_WorkflowVisualDesignerIframeUserTaskDescription);
				sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription,
						AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT + dateUtils.getEpoch());
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction,
						AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT);
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
						ResourceHandler.loadProperty("multi.project.user.username"));
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
				waitUtils.waitInterval(1);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
						ResourceHandler.loadProperty("test.user.rfi.builder.name"));
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDaysField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDaysField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDays);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDays, workflowDays);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserWorflowTransitionConditonField);
				clickElementAndWait(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserWorflowTransitionConditonField);
				clickElementAndWaitForElement(
						WorkflowsTab.ele_WorkflowVisualDesignerIframeUserWorflowTransitionConditonField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeUserWorflowTransitionConditon);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeUserWorflowTransitionConditon,
						AdoddleCommonStringPool.WORKFLOW_PROCEED_AFTER_ANY_ONE_PERSON_COMPLETES_TASK);
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedWorflowTransitionConditon);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedWorflowTransitionConditon);

				break;
			}
			case 3: {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName, "Status Change");
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField,
						WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription);
				sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription,
						"StatusChange" + dateUtils.getEpoch());
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType, "Status Change");
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				Assert.assertTrue(getElementText(WorkflowsTab.pop_WorkflowVisualDesignerIframeSystemTaskPopup)
						.contains(AdoddleCommonStringPool.WORKFLOW_POPUP_SYSTEM_TASK));
				if (!isSelected(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask)) {
					clickElement(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask);

				}

				clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeSelectActionPopupOkay);

				break;
			}
			case 4: {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName, "Clear Tasks");
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField,
						WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription);
				sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription,
						"ClearTasks" + dateUtils.getEpoch());
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType, "Clear Tasks");
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				Assert.assertTrue(getElementText(WorkflowsTab.pop_WorkflowVisualDesignerIframeSystemTaskPopup)
						.contains(AdoddleCommonStringPool.WORKFLOW_POPUP_SYSTEM_TASK));
				if (!isSelected(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask)) {
					clickElement(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask);

				}

				clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeSelectActionPopupOkay);

				break;
			}
			case 5: {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName, "UpdateFilePrivacy");
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField,
						WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription);
				sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription, "UpdateFilePrivacy Desc"
						+ dateUtils.getEpoch());
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType, "Update File Privacy");
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				Assert.assertTrue(getElementText(WorkflowsTab.pop_WorkflowVisualDesignerIframeSystemTaskPopup)
						.contains(AdoddleCommonStringPool.WORKFLOW_POPUP_SYSTEM_TASK));
				if (!isSelected(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask)) {
					clickElement(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask);

				}

				clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeSelectActionPopupOkay);

				break;
			}
			case 6: {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName, "PublishPDF");
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField,
						WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription);
				sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription, "PublishPDF Desc"
						+ dateUtils.getEpoch());
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskTypeField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType, "Publish As PDF");
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				Assert.assertTrue(getElementText(WorkflowsTab.pop_WorkflowVisualDesignerIframeSystemTaskPopup)
						.contains(AdoddleCommonStringPool.WORKFLOW_POPUP_SYSTEM_TASK));
				if (!isSelected(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask)) {
					clickElement(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask);

				}

				clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeSelectActionPopupOkay);

				break;
			}
			default:
				log.info("End Node");
				break;
			}

		}

	}

	public void saveWorkflowDefiniton() {
		waitUntilElementIsDisplayed(WorkflowsTab.btn_WorkflowVisualDesignerIframeWorkflowSave);
		clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeWorkflowSave);
		waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeWorkflowMessagePopup);
		Assert.assertTrue(getElementText(WorkflowsTab.ele_WorkflowVisualDesignerIframeWorkflowMessagePopup)
				.equalsIgnoreCase("Message"));
		clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeWorkflowMessagePopupYes);
		if (isDisplayed(WorkflowsTab.btn_WorkflowVisualDesignerIframeWorkflowMessageTrigerPopupLater))
			clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeWorkflowMessageTrigerPopupLater);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		switchDefault();
		clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerClose);
	}

	public void contextClickWorkflowDefiniton() {
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
		contextClick(WorkflowsTab.lnk_WorkflowsDefinitionFirstWorkflowName);
		waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowsDefinitionsEditWorkflowDetail);
		clickElementAndWait(WorkflowsTab.ele_WorkflowsDefinitionsEditWorkflowDetail);

	}

	public void editWorkflowDefiniton(String newWokflowDefinitionName) {
		editedWorkflowDefinitionName = newWokflowDefinitionName + dateUtils.getEpoch();
		sendKeys(WorkflowsTab.txt_WorkflowsEditWorkflowDetailWorkflowName, editedWorkflowDefinitionName);
		try {
			waitUntilElementIsClickable(WorkflowsTab.btn_WorkflowsEditWorkflowDetailUpdateButton);
			doubleClick(WorkflowsTab.btn_WorkflowsEditWorkflowDetailUpdateButton);
		} catch (Throwable t) {
			log.error("ERROR: failure in call back in editing workflow definition");
		}
		waitForCompletePageLoad();

	}

	public void createWorkflowTrigger(String triggerName) throws InterruptedException {

		workflowTrigger = triggerName + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureTriggerName);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerName, workflowTrigger);
		selectByVisibleText(WorkflowsTab.txt_WorkflowsConfigureTriggerPriority,
				AdoddleCommonStringPool.TRIGGER_PRIORITY_HIGH);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerDescription, workflowTrigger);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerField, AdoddleCommonStringPool.WORKFLOW_TRIGGER_FIELD_FOLDER);
		clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
		if (triggerName.contains("Automation_Test_Workflow_Trigger")) {
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, projectFolder);
			waitUntilElementIsDisplayed(WorkflowsTab.css_WorkflowsConfigureTriggerValueSearchResult);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, Keys.TAB);
			clickElementAndWait(WorkflowsTab.lnk_WorkflowsConfigureTriggerAddLink);
			waitUntilElementIsDisplayed(WorkflowsTab.sel_WorkflowsConfigureTriggerLastField);
			sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerLastField,
					AdoddleCommonStringPool.WORKFLOW_TRIGGER_FIELD_DOCUMENT_STATUS);
			clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
			selectByVisibleText(WorkflowsTab.sel_WorkflowsConfigureTriggerSecondryValue,
					AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
		}

		else if (triggerName.contains("PublishPDF_Workflow_Trigger")) {
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, "AutomationUploadFiles");
			waitUntilElementIsDisplayed(WorkflowsTab.css_WorkflowsConfigureTriggerValueSearchResult);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, Keys.TAB);
		}

		else {
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, "PublicFolder");
			waitUntilElementIsDisplayed(WorkflowsTab.css_WorkflowsConfigureTriggerValueSearchResult);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, Keys.TAB);
		}

		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerEvent,
				AdoddleCommonStringPool.TRIGGER_EVENT_UPLOAD_DOCUMENTS);
		if (!isSelected(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode))
			selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode,
					AdoddleCommonStringPool.TRIGGER_ACTION_MODE_POST);
		log.info("Edit Workflow Name::::" + editedWorkflowDefinitionName);
		if (triggerName.contains("Automation_Test_Workflow_Trigger"))
			selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerAction, editedWorkflowDefinitionName);
		else if (triggerName.contains("PublishPDF_Workflow_Trigger"))
			selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerAction, editedSystemActionName);
		else
			selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerAction, systemActionNames.get(0));

		clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void editWorkflowTrigger(String newTriggerName) {

		String editedWorkflowTrigger = newTriggerName + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		clickLinkWithText(workflowTrigger);
		waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureTriggerName);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerName, editedWorkflowTrigger);
		clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void focusProjectAndFolder(String projectFolder) {
		log.info("Cloned Project:::" + clonedProject);
		clickElementWithText(clonedProject);
		clickElementWithText(projectFolder);
	}

	public void publishMultipleDocuments() throws IOException, InterruptedException {

		String fileExtension, fileName;
		filedocRefList = sysUtils.getFileList(fileDocRef1 + "," + fileDocRef2 + "," + fileDocRef3 + "," + fileDocRef4
				+ "," + fileDocRef5);

		docSatusList.add(AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
		docSatusList.add(AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
		docSatusList.add(AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
		docSatusList.add(AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
		docSatusList.add(AdoddleCommonStringPool.STATUS_FOR_APPROVAL);

		sysUtils.authenticateRemoteMachine(nodeIP);
		List<String> systemMultiDocumentsList = sysUtils.getFileListOfSystemFolder(nodeIP
				+ ResourceHandler.loadProperty("print.multi.document.testdata.filepath"));
		uploadMultiDocumentsList.clear();
		log.info("Documents Name" + systemMultiDocumentsList);
		for (String str : systemMultiDocumentsList) {

			fileExtension = "." + str.split("\\.")[1];
			sysUtils.authenticateRemoteMachine(nodeIP);
			log.info("Renamed Document Name: " + strUtils.extractFileNameString(str));

			if (strUtils.extractFileNameString(str).split("\\.")[0].contains("BatchPrintTestDataFile")) {

				fileName = copyAndRenameMultipleDocuments(
						nodeIP + ResourceHandler.loadProperty("print.multi.document.testdata.filepath") + str, nodeIP
								+ ResourceHandler.loadProperty("remote.copy.file.folderpath"), fileExtension);

				log.info("String " + fileName);
				uploadMultiDocumentsList.add(fileName);

			}

			else
				log.info("Expected Document not found in Local Directory");
		}

		log.info("Documents Name" + uploadMultiDocumentsList);
		uploadDocuments(uploadMultiDocumentsList, 5, filedocRefList, null, false, 1, null, docSatusList, null, null);

	}

	public void checkFileStatus(String fileStatus) {
		clickElementWithText(projectFolder);
		for (String docRef : filedocRefList) {

			searchFiles(docRef);
			waitUntilElementContainsText(FilesTab.lnk_FilesFirstAction, fileStatus);
			Assert.assertTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains(fileStatus));
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(
					AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			Assert.assertTrue(getElementText(FilesTab.ele_workflowStage).contains(fileStatus));
			clickElementAndWait(FilesTab.lnk_FilesFirstAction);
			clickElementAndWait(FilesTab.lnk_PopupAcknowledgementReceipt);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
		}

	}

	public void searchFileAndClickAction() {

		log.info("Covered in <checkFileStatus> Definition");

	}

	public void performMyAction() {

		log.info("Covered in <checkFileStatus> Definition");

	}

	public void validateFileVersion() throws InterruptedException {

		navigateTab(LandingPage.lnk_Files);
		focusProjectAndFolder(projectFolder);
		waitUntilElementIsDisplayed(FilesTab.img_DocListingFirstTypeIcon);

		for (String docRef : filedocRefList) {

			searchFiles(docRef);

			if (findElement(FilesTab.img_DocListingFirstTypeIcon).getAttribute("src").contains("rm.gif")) {
				Assert.assertTrue(getElementText(FilesTab.lnk_FileListingFirstVersion).contains("1"));
				Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(
						AdoddleCommonStringPool.WORKFLOW_STATUS_COMPLETED));
				Assert.assertTrue(getElementText(FilesTab.ele_workflowStage).contains(
						AdoddleCommonStringPool.DOUBLE_DASH));

			} else {

				Assert.assertTrue(findElement(FilesTab.img_DocListingFirstTypeIcon).getAttribute("src").contains(
						"pdf.gif"));
				Assert.assertTrue(Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstVersion)) == 2);
				waitUntilElementIsPresent(FilesTab.img_DocListingFirstPrivateImage);
				Assert.assertTrue(isElementPresent(FilesTab.img_DocListingFirstPrivateImage));
				Assert.assertTrue(getElementText(FilesTab.ele_FilesTabFirstFilePOI).contains(
						AdoddleCommonStringPool.POI_FOR_REVIEW));
				Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(
						AdoddleCommonStringPool.STATUS_FOR_APPROVAL));
			}

		}

	}

	public void validateWorkflowStatusAndStage(String wStatus, String wStage) throws InterruptedException {
		searchFiles(fileDocRef5);
		waitUntilElementContainsText(FilesTab.lnk_workflowStatus, wStatus);
		Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).equalsIgnoreCase(wStatus));
		Assert.assertTrue(getElementText(FilesTab.ele_workflowStage).contains(wStage));
		clickElementAndWaitForElement(FilesTab.lnk_workflowStatus, FilesTab.btn_WorkflowStatusCancel);
		clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
	}

	public void validateActionAndDueDateInAuditTrail(String validationInstance) {

		String expectedActionDueDate;
		String dateFormat = getDateFormat();
		currentWindowHandle = getCurrentWindow();
		List<String> holidayList = new ArrayList<String>(Arrays.asList("sunday", "saturday"));
		Boolean flag = false;
		searchFiles(fileDocRef5);
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickHistory,
				FilesTab.opt_FileContextClickHistoryDistribution);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();

		if (fileBetaViewFlag) {

			waitUntilListOfElementIsDisplayed(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
			userDistributionList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
			AutomationAssert.assertTrue("Expected List Count :: 10" + "But was" + userDistributionList.size(),
					userDistributionList.size() == 10);

			for (WebElement web : userDistributionList) {

				String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
				String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();
				String actionDueDate = web.findElement(FilesTab.css_BetaFileViewerRecordsActionDueDate).getText();

				log.info("UserName :: " + userName);

				if (userName.contains(ResourceHandler.loadProperty("multi.project.user.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 5);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 5);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 5);

					log.info("Calculated Action Due Date:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

				}

				else if (userName.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 14);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 14);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 14);

					log.info("Calculated Action Due Date:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_COMMENT,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

				}

				else if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 10);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 10);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 10);

					log.info("Calculated Action Due Date:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

				}

				else if (userName.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 12);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 12);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 12);

					log.info("Calculated Actionduedate:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

					flag = true;

				}

				else if (userName.contains(ResourceHandler.loadProperty("test.user.rs.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.ps.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 3);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 3);

					log.info("Calculated Actionduedate:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

					flag = true;

				}

				else {
					log.info("Failure while validating Audit Trails for User:: " + userName);
					flag = false;
				}

			}

			AutomationAssert.assertTrue("Failure while validation userAction and dueDate", flag);
		}

		else {

			waitUntilElementIsDisplayed(FilesTab.ele_FileHistoryFirstRecord);
			clickElementAndWait(FilesTab.ele_FileHistoryFirstRecord);
			waitUntilListOfElementIsDisplayed(FilesTab.css_FileViewerActionDistributionRecords);
			List<WebElement> auditTrailActionList = findElements(FilesTab.css_FileViewerActionDistributionRecords);
			for (WebElement web : auditTrailActionList) {

				String user = web.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser)
						.getAttribute("title");
				String action = web.findElement(FilesTab.css_FileViewerRecordsAction).getText();
				String actionDueDate = web.findElement(FilesTab.css_FileViewerRecordsActionDueDate).getText();
				String actionStatus = web.findElement(FilesTab.css_FileViewerRecordsActionStatus).getText();

				if (user.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 12);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 12);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 12);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));

					Assert.assertTrue(
							"Expected ActionDueDate: " + expectedActionDueDate + " But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

					if (validationInstance.contains("preActionValidation"))
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE
								+ " But was " + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE));
					else
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_CLEARED
								+ " But was" + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_CLEARED));

				}

				else if (user.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 10);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 10);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 10);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));

					Assert.assertTrue("Expected ActionDueDate: " + expectedActionDueDate + "But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

					if (validationInstance.contains("preActionValidation"))
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE
								+ " But was " + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE));
					else
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_CLEARED
								+ " But was" + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_CLEARED));

				}

				else if (user.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 14);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 14);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 14);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_COMMENT + "But was "
							+ action, action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));

					Assert.assertTrue("Expected ActionDueDate: " + expectedActionDueDate + "But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

					if (validationInstance.contains("preActionValidation"))
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE
								+ " But was " + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE));
					else
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_CLEARED
								+ " But was" + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_CLEARED));

				}

				else if (user.contains(ResourceHandler.loadProperty("multi.project.user.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 5);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 5);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 5);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));

					Assert.assertTrue("Expected ActionDueDate: " + expectedActionDueDate + "But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

					if (validationInstance.contains("preActionValidation"))
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE
								+ " But was " + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE));
					else
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_COMPLETE
								+ " But was" + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_COMPLETE));

					flag = true;

				}

				else if (user.contains(ResourceHandler.loadProperty("test.user.rs.bloggs.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.ps.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 3);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 3);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

					Assert.assertTrue("Expected ActionDueDate: " + expectedActionDueDate + "But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

					if (validationInstance.contains("preActionValidation"))
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE
								+ " But was " + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE));
					else
						Assert.assertTrue("Expected ActionStatus" + AdoddleCommonStringPool.ACTION_STATUS_COMPLETE
								+ " But was" + actionStatus,
								actionStatus.contains(AdoddleCommonStringPool.ACTION_STATUS_COMPLETE));

					flag = true;

				}

			}

			Assert.assertTrue("Failure While validating HistoryRecords", flag);

		}

		if (validationInstance.contains("postActionValidation")) {

			if (fileBetaViewFlag) {

				selectByVisibleText(ProjectFormsTab.drp_BetaViewHistoryTabHistoryTypeDropdown,
						AdoddleCommonStringPool.STATUS);
				waitUntilElementIsDisplayed(FilesTab.css_BetaViewFileHistoryFirstRecordOldActionStatus);
				Assert.assertTrue("Expected oldStatus:::" + AdoddleCommonStringPool.STATUS_FOR_APPROVAL,
						isDisplayed(FilesTab.css_BetaViewFileHistoryFirstRecordOldActionStatus));
				Assert.assertTrue("Expected newStatus:::" + AdoddleCommonStringPool.STATUS_FOR_INFORMATION,
						isDisplayed(FilesTab.css_BetaViewFileHistoryFirstRecordNewActionStatus));
			}

			else {

				selectByIndex(FilesTab.drp_FileViewHistoryTypeDropdown, 3);
				waitUntilElementIsDisplayed(FilesTab.css_FileHistoryFirstRecordOldActionStatus);
				Assert.assertTrue(
						"Expected oldStatus:::" + AdoddleCommonStringPool.STATUS_FOR_APPROVAL,
						getElementText(FilesTab.css_FileHistoryFirstRecordOldActionStatus).contains(
								AdoddleCommonStringPool.STATUS_FOR_APPROVAL));
				Assert.assertTrue(
						"Expected newStatus:::" + AdoddleCommonStringPool.STATUS_FOR_INFORMATION,
						getElementText(FilesTab.css_FileHistoryFirstRecordNewActionStatus).contains(
								AdoddleCommonStringPool.STATUS_FOR_INFORMATION));

			}
		}

		closeCurrentWindow();
		switchPreviousWindow(currentWindowHandle);
	}

	public void validateActionAndDueDateInHistory() {
		String expectedActionDueDate;
		String dateFormat = getDateFormat();
		currentWindowHandle = getCurrentWindow();
		List<String> holidayList = new ArrayList<String>(Arrays.asList("sunday", "saturday"));
		Boolean flag = false;
		navigateTab(LandingPage.lnk_Files);
		searchFiles(strUtils.extractFileNameString(PublishPDFviaWorkflow.uploadMultiDocumentsList.get(0)).split("\\.")[0]);
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickHistory,
				FilesTab.opt_FileContextClickHistoryDistribution);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();

		if (fileBetaViewFlag) {

			waitUntilListOfElementIsDisplayed(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
			userDistributionList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
			AutomationAssert.assertTrue("Expected List Count :: 8" + " But was" + userDistributionList.size(),
					userDistributionList.size() == 8);

			for (WebElement web : userDistributionList) {

				String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
				String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();
				String actionDueDate = web.findElement(FilesTab.css_BetaFileViewerRecordsActionDueDate).getText();

				log.info("UserName :: " + userName);

				if (userName.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 14);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 14);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 14);

					log.info("Calculated Action Due Date:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_COMMENT + " But found "
							+ action, action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

				}

				else if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 10);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 10);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 10);

					log.info("Calculated Action Due Date:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

				}

				else if (userName.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 12);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 12);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 12);

					log.info("Calculated Actionduedate:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

					flag = true;

				}

				else if (userName.contains(ResourceHandler.loadProperty("test.user.rs.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.ps.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 3);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 3);

					log.info("Calculated Actionduedate:: " + expectedActionDueDate);

					Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
					Assert.assertTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));

					flag = true;

				}

				else {
					log.info("Failure while validating Audit Trails for User:: " + userName);
					flag = false;
				}

			}

			AutomationAssert.assertTrue("Failure while validation userAction and dueDate", flag);
		}

		else {

			waitUntilElementIsDisplayed(FilesTab.ele_FileHistoryFirstRecord);
			clickElementAndWait(FilesTab.ele_FileHistoryFirstRecord);
			waitUntilListOfElementIsDisplayed(FilesTab.css_FileViewerActionDistributionRecords);
			List<WebElement> auditTrailActionList = findElements(FilesTab.css_FileViewerActionDistributionRecords);
			for (WebElement web : auditTrailActionList) {

				String user = web.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser)
						.getAttribute("title");
				String action = web.findElement(FilesTab.css_FileViewerRecordsAction).getText();
				String actionDueDate = web.findElement(FilesTab.css_FileViewerRecordsActionDueDate).getText();

				if (user.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 12);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 12);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 12);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));

					Assert.assertTrue(
							"Expected ActionDueDate: " + expectedActionDueDate + " But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

				}

				else if (user.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 10);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 10);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 10);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));

					Assert.assertTrue("Expected ActionDueDate: " + expectedActionDueDate + "But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

				}

				else if (user.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 14);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 14);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 14);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_COMMENT + "But was "
							+ action, action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));

					Assert.assertTrue("Expected ActionDueDate: " + expectedActionDueDate + "But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

				}

				else if (user.contains(ResourceHandler.loadProperty("test.user.rs.bloggs.name"))
						|| user.contains(ResourceHandler.loadProperty("test.user.ps.bloggs.name"))) {

					if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_UK))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_UK, AdoddleCommonStringPool.TIMEZONE_ID_UK, 3);

					else if (dateFormat.contains(AdoddleCommonStringPool.DATE_FORMAT_US))
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_US, AdoddleCommonStringPool.TIMEZONE_ID_US, 3);

					else
						expectedActionDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, null,
								AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 3);

					Assert.assertTrue("Expected Action: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE
							+ "But was " + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

					Assert.assertTrue("Expected ActionDueDate: " + expectedActionDueDate + "But was: " + actionDueDate,
							actionDueDate.contains(expectedActionDueDate));

					flag = true;

				}

			}

			Assert.assertTrue("Failure While validating HistoryRecords", flag);

		}

		closeCurrentWindow();
		switchPreviousWindow(currentWindowHandle);

	}

	public void updateSystemActionAttributes(String status, String poi) {

		if (!isSelected(WorkflowsTab.rad_PublishAsPDFPoiModify))
			clickElementAndWait(WorkflowsTab.rad_PublishAsPDFPoiModify);

		if (isEnabled(WorkflowsTab.sel_PublishAsPDFPoiModifyDropdown))
			selectByVisibleText(WorkflowsTab.sel_PublishAsPDFPoiModifyDropdown, poi);

		if (!isSelected(WorkflowsTab.rad_PublishAsPDFStatusModify))
			clickElement(WorkflowsTab.rad_PublishAsPDFStatusModify);

		if (isEnabled(WorkflowsTab.sel_PublishAsPDFStatusModifyDropdown))
			selectByVisibleText(WorkflowsTab.sel_PublishAsPDFStatusModifyDropdown, status);

		clickElement(WorkflowsTab.chk_PublishAsPDFAttachments);
		clickElement(WorkflowsTab.chk_PublishAsPDFCommentContents);
		clickElement(WorkflowsTab.chk_PublishAsPDFCommentAttachments);
		if (!isSelected(WorkflowsTab.chk_PublishAsPDFStartWorkflowOnFilePublish))
			clickElement(WorkflowsTab.chk_PublishAsPDFStartWorkflowOnFilePublish);

	}

	public void validateDocumentVersionAndWorkflowStatus(String version) {

		boolean flag = false;

		for (int index = 0; index < 10; index++) {
			navigateTab(LandingPage.lnk_Files);
			searchFiles(strUtils.extractFileNameString(PublishPDFviaWorkflow.uploadMultiDocumentsList.get(0)).split(
					"\\.")[0]);
			if (getElementText(FilesTab.lnk_FileListingFirstVersion).contains(version)) {
				flag = true;
				Assert.assertTrue(findElement(FilesTab.img_DocListingFirstTypeIcon).getAttribute("src").contains(
						"pdf.gif"));
				Assert.assertTrue(getElementText(FilesTab.ele_FilesTabFirstFilePOI).contains(
						AdoddleCommonStringPool.POI_FOR_REVIEW));
				Assert.assertTrue(getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(
						AdoddleCommonStringPool.STATUS_FOR_APPROVAL));
			}

			else if (Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstVersion)) > 6)
				Assert.assertTrue("Expected PDF Version should not more than: " + version, false);

			else
				log.info("Expected PDF file:: " + getElementText(FilesTab.lnk_FileListingFirstVersion));

		}

		Assert.assertTrue("Document PDF failed to publish or goes Infinite loop", flag);

	}

	public void downloadMultipleDocumentsInLocal() throws IOException, InterruptedException {

		File batchDownloadFilePathFile;
		String batchDownloadFilePath;

		for (String docRef : filedocRefList) {

			searchFiles(docRef);
			String fileName = getElementText(FilesTab.lnk_DocListingFirstFileName);
			batchDownloadFilePath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + docRef
					+ AdoddleCommonStringPool.PDF_EXTENSION;
			batchDownloadFilePathFile = new File(batchDownloadFilePath);

			clickElementAndWait(FilesTab.img_DocListingFirstTypeIcon);
			log.info("Download filePath:: " + batchDownloadFilePath);
			;
			// autoItUtils.downloadAutoIt(batchDownloadFilePath.toString(),
			// nodeIP);
			sysUtils.waitForSingleDirectFileDownload(nodeIP, fileName, batchDownloadFilePath);
			Assert.assertTrue("Invalid File Size: " + sysUtils.getFileSize(batchDownloadFilePathFile.toString()),
					sysUtils.getFileSize(batchDownloadFilePathFile.toString()) != 0);
		}

	}

	private String getDateFormat() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_UK;
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_US;
		else
			return AdoddleCommonStringPool.DATE_FORMAT_AUS;
	}

	private String copyAndRenameMultipleDocuments(String sourceFilePath, String destFilePath, String fileExtension) {
		InputStream inStream;
		OutputStream outStream;
		destFilePath = destFilePath + epoch + fileExtension;

		try {
			File source = new File(sourceFilePath);
			File target = new File(destFilePath);
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			inStream.close();
			outStream.close();

			log.info("File Copied successfully!");
		} catch (IOException e) {
			log.info("failure in copying file");
		}
		return destFilePath;
	}

	public void deactivateClonedProject() {
		try {
			deactivateInheritanceProject(clonedProject);

		} catch (Throwable t) {
			log.error("Failure in Deactivating Workspace");
		}

	}

}