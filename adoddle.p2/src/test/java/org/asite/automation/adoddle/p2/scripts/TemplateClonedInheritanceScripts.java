/*Testdata dynamically created and deactivated.Increase Default Wait Timeout :240 While Runing in Hybrid DC's */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleClassicLocators.ClassicLocators;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleWorkflowsLocators.WorkflowsTab;
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
import org.openqa.selenium.interactions.Actions;

public class TemplateClonedInheritanceScripts extends AdoddleCommonAppMethods {

	private static String workflowTrigger;
	private static String workflowName;
	private static String clonedProject, InheritedClonnedProject;
	private static String editedWorkflowDefinition;
	private static String editedSystemAction;
	private static String editedWorkflowTrigger;
	private String parentWindow = null;
	private List<String> fileList1 = new ArrayList<String>();
	private List<String> fileList2 = new ArrayList<String>();
	private List<String> fileList3 = new ArrayList<String>();
	private List<WebElement> commonFileList = new ArrayList<WebElement>();
	private List<WebElement> actionList = new ArrayList<WebElement>();
	private List<WebElement> userTaskList = new ArrayList<WebElement>();
	private List<WebElement> userDistributionList = new ArrayList<WebElement>();
	private static List<WebElement> systemActionList = new ArrayList<WebElement>();
	private static List<WebElement> workflowDefList = new ArrayList<WebElement>();
	private static List<WebElement> workflowTriggerList = new ArrayList<WebElement>();
	public static Logger log = AutomationLogger.getInstance().getLogger(TemplateClonedInheritanceScripts.class);

	public void clickOnWorkSpaceTab() {
		clickElementAndWait(ClassicLocators.tab_WorkSpace);
		waitForCompletePageLoad();
	}

	public void validateWorkspaceInAllWorkspaceList(String workspaceName) throws InterruptedException {

		boolean flag = false;
		int maxWaitTimeout = 7;

		for (int index = 0; index < 5; index++) {

			switchDefault();
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
			switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteMainFrame);
			switchIframe(ClassicLocators.frm_AsiteMainFrame);
			waitForCompletePageLoad();
			clickElementAndWait(ClassicLocators.lbl_ALlWorkspacesTab);
			waitUtils.waitInterval(1);
			if (isDisplayedLinkWithText(workspaceName)) {
				flag = true;
				break;
			} else {
				reloadPage();
				if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage) && index != maxWaitTimeout) {
					waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
					waitForCompletePageLoad();
					index++;
				}
			}

		}

		if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage)) {
			waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
			waitForCompletePageLoad();
		}

		Assert.assertTrue("Workspace not found in Listing:: " + workspaceName, flag);

	}

	public void clickOnWorkspace(String workSpace) {
		log.info("Workspace ::" + workSpace);
		clickLinkWithText(workSpace);

	}

	public void verifyWorkspacePage(String wSpace) {

		switchDefault();
		waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
		switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceHeaderName);
		Assert.assertTrue(getElementText(ClassicLocators.lbl_WorkspaceHeaderName).equalsIgnoreCase(wSpace));

	}

	public void selectdropDownOption(String dropDownOption) {

		selectByVisibleText(ClassicLocators.drp_AdminDropdown, dropDownOption);
		waitForCompletePageLoad();
		switchDefault();
		switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteMainFrame);
		switchIframe(ClassicLocators.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(ClassicLocators.frm_PmFrame);
		switchIframe(ClassicLocators.frm_PmFrame);

	}

	public void verifyCreateEditWorkspacePage(String pageTitle) {
		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(pageTitle));
	}

	public void clickOnSaveAsTemplateLink() {
		waitUntilElementIsDisplayed(ClassicLocators.lnk_EditWorkspaceSaveWorkspaceAsTemplate);
		clickElementAndWait(ClassicLocators.lnk_EditWorkspaceSaveWorkspaceAsTemplate);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(ClassicLocators.lnk_EditWorkspaceSaveWorkspaceAsTemplate);

	}

	public void verifyCreateWorkspaceTemplatePage(String pageTitle) {
		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(pageTitle));

	}

	public void editWorkspaceTemplateName(String templateName) {

		globalWorkspaceTemplateName = templateName + epoch;
		collectionDataMap.put("GlobalTemplate:: ", globalWorkspaceTemplateName);
		waitUntilElementIsDisplayed(ClassicLocators.txt_CreateEditWorkspaceNameInput);
		sendKeys(ClassicLocators.txt_CreateEditWorkspaceNameInput, globalWorkspaceTemplateName);

	}

	public void saveWorkspaceTemplate() throws InterruptedException {

		waitUntilElementIsDisplayed(ClassicLocators.lnk_CreateEditWorkspaceSave);
		clickElementAndWait(ClassicLocators.lnk_CreateEditWorkspaceSave);
		waitUntilElementIsPresent(ClassicLocators.lnk_CreateEditWorkspaceSave, 600);

	}

	public void verifyWorkspaceTemplate() {

		reloadPage();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		clickElementAndWait(ClassicLocators.lnk_WorkspaceMyHome);
		waitForCompletePageLoad();
		clickElementAndWait(ClassicLocators.tab_WorkSpace);
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceTemplatesTab);
		clickElementAndWait(ClassicLocators.lbl_WorkspaceTemplatesTab);
		waitUntilElementIsDisplayed(ClassicLocators.existingTemplateList);
		searchTemplateName();

	}

	public void validateTemplateAndClickClonnedIcon() {

		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceTemplatesTab);
		clickElementAndWait(ClassicLocators.lbl_WorkspaceTemplatesTab);
		waitUntilElementIsDisplayed(ClassicLocators.existingTemplateList);
		validateTemplateAndClickCloneIcon();

	}

	public void clickClonnedImageLink() {
		log.info("<validateTemplateAndClickClonnedIcon> Covered in previous definition ");

	}

	public void focusWorkflowTemplates() {

		clickElementAndWait(ClassicLocators.lbl_WorkspaceTemplatesTab);
		waitUntilElementIsDisplayed(ClassicLocators.existingTemplateList);
		validateTemplateAndClickCloneIcon();
		waitForCompletePageLoad();

	}

	public void verifyCreateWorkspacePage(String pageTitle) {

		waitForCompletePageLoad();
		switchDefault();
		waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
		switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteMainFrame);
		switchIframe(ClassicLocators.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(ClassicLocators.frm_PmFrame);
		switchIframe(ClassicLocators.frm_PmFrame);
		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(pageTitle));

	}

	public void enterswitchDefaultProjectMandatoryAttributes(String clonnedProjectIdentifier, String scenarioType) {

		boolean dataCenterType = scenarioType.contains("Like_DataCenter") ? true : false;

		if (clonnedProjectIdentifier.split("_")[0].equalsIgnoreCase("Cloned")) {
			clonedProject = clonnedProjectIdentifier + epoch;
			log.info("Cloned Project::" + clonedProject);
			collectionDataMap.put("ClonedProject:: ", clonedProject);
			waitUntilElementIsDisplayed(ClassicLocators.txt_CreateEditWorkspaceNameInput);
			sendKeys(ClassicLocators.txt_CreateEditWorkspaceNameInput, clonedProject);
			if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceClient))
				selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceClient, "Automation Testing Classic");
			clickElementAndWait(ClassicLocators.lnk_CreateEditWorkspaceShowAdvanced);
			waitUntilElementIsDisplayed(ClassicLocators.chk_CreateWorkspaceInheritChanges);
			if (isSelected(ClassicLocators.chk_CreateWorkspaceInheritChanges))
				clickElement(ClassicLocators.chk_CreateWorkspaceInheritChanges);

		} else {

			InheritedClonnedProject = clonnedProjectIdentifier + epoch;
			log.info("Inherited Project:: " + InheritedClonnedProject);
			collectionDataMap.put("InheritedProject:: ", InheritedClonnedProject);
			waitUntilElementIsDisplayed(ClassicLocators.txt_CreateEditWorkspaceNameInput);
			sendKeys(ClassicLocators.txt_CreateEditWorkspaceNameInput, InheritedClonnedProject);
			if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceClient))
				selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceClient, "Automation Testing Classic");
		}

		if (dataCenterType == true)
			resetGeographyAsTemplate(clonnedProjectIdentifier);
		else
			resetGeographyUnlikeTemplate(clonnedProjectIdentifier);

	}

	public void resetGeographyAsTemplate(String projectIdentifier) {

		if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography)
				&& projectIdentifier.split("_")[2].equalsIgnoreCase(AdoddleCommonStringPool.UK_DC))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.UK_GEOGRAPHY);

		else if ((!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography) && projectIdentifier.split("_")[2]
				.equalsIgnoreCase(AdoddleCommonStringPool.US_DC)))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.US_GEOGRAPHY);
		else
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.AUS_GEOGRAPHY);

	}

	public void resetGeographyUnlikeTemplate(String projectIdentifier) {

		if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography)
				&& projectIdentifier.split("_")[2].equalsIgnoreCase(AdoddleCommonStringPool.US_DC))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.US_GEOGRAPHY);

		else if ((!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography) && projectIdentifier.split("_")[2]
				.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC)))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.UK_GEOGRAPHY);
		else
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.UK_GEOGRAPHY);

	}

	public void brkInheritance() {

		log.info("Covered in <enterClonnedProjectMandatoryAttributes> definition");

	}

	public void verifyClonnedProjectInAllWorkspacesList(String projectType) throws InterruptedException {
		reloadPage();
		waitForCompletePageLoad();
		if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage))
			waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
		if (projectType.equalsIgnoreCase("Cloned_AutomationTestProject"))
			validateWorkspaceInAllWorkspaceList(clonedProject);

		else
			validateWorkspaceInAllWorkspaceList(InheritedClonnedProject);

	}

	public void switchIntoAdoddleView() {
		reloadPage();
		waitForCompletePageLoad();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_WorkspaceFrame);
		mouseHoverAndClickElement(ClassicLocators.lnk_WorkspaceSettings, ClassicLocators.lnk_AdoddleView);
		waitForCompletePageLoad();
		reloadPage();
		waitForCompletePageLoad();

	}

	public void validateAdoddleDashboard() {

		waitUntilElementIsDisplayed(LandingPage.lnk_Dashboard);
		Assert.assertTrue(isDisplayed(LandingPage.lnk_Dashboard));

	}

	public void focusWorkspaceTemplate() {

		log.info("Template Name::" + globalWorkspaceTemplateName);
		waitUntilElementIsClickable(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
		Assert.assertTrue("Template not found:: " + globalWorkspaceTemplateName,
				isDisplayedElementWithText(globalWorkspaceTemplateName));
		clickElementWithText(globalWorkspaceTemplateName);
	}

	public void createWorkflowDefinition(String wrkflowName) {
		String workflowdays = JavaUtils.getRandomNumber(2);
		workflowName = wrkflowName + dateUtils.getEpoch();
		sendKeys(WorkflowsTab.txt_WorkflowsCreateWorkflowDefinitionWorkflowName, workflowName);
		sendKeys(WorkflowsTab.txt_WorkflowsCreateWorkflowDefinitionWorkflowDescription, javaUtils.getRandomString(10));
		sendKeys(WorkflowsTab.txt_WorkflowsCreateWorkflowDefinitionWorkflowDays, workflowdays);
		clickElementAndWait(WorkflowsTab.btn_WorkflowsCreateWorkflowDefinitionCreateButton);

	}

	public void validateVisualWorkflowDesigner() {
		try {

			waitUntilElementIsDisplayed(WorkflowsTab.frm_Iframe);
			Assert.assertTrue(isDisplayed(WorkflowsTab.frm_Iframe));
		} catch (Exception e) {

			log.error(" Failed To Load Visual Workflow Designer");
		}

	}

	public void designWorkflow() throws InterruptedException {

		int x = 300, y = 200, index = 0;

		waitAndSwitchIframe(WorkflowsTab.frm_Iframe);
		waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeTasks);
		clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeTasks,
				WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTask);
		clickElementAndWait(WorkflowsTab.ele_WorkflowVisualDesignerIframeEndEvent);
		Actions builder = new Actions(getWebDriver());
		List<WebElement> nodes = new ArrayList<WebElement>();
		nodes.add(findElement(WorkflowsTab.ele_WorkflowsVisualDesignerStartNode));

		for (index = 1; index <= 3; index++) {
			WebElement fromElement;

			if (index == 1) {
				builder.dragAndDrop(findElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTask),
						findElement(WorkflowsTab.ele_IframeWorkflowDesignArea)).build().perform();
				if (isDisplayed(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse))
					clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);

				fromElement = findElement(By
						.cssSelector("g[class='stencils'] g[class='children']  g[id*='svg-sid']:nth-child("
								+ (Integer.toString(index + 1)) + ") rect[stroke='black']"));
				nodes.add(fromElement);
			} else if (index == 3) {
				builder.dragAndDrop(findElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeEndTask),
						findElement(WorkflowsTab.ele_IframeWorkflowDesignArea)).build().perform();
				if (isDisplayed(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse))
					clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				fromElement = findElement(By
						.cssSelector("g[class='stencils'] g[class='children']  g[id*='svg-sid']:nth-child("
								+ (Integer.toString(index + 1)) + ") circle[stroke='black']"));
				nodes.add(fromElement);
			} else {
				builder.dragAndDrop(findElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTask),
						findElement(WorkflowsTab.ele_IframeWorkflowDesignArea)).build().perform();
				if (isDisplayed(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse))
					clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				fromElement = findElement(By
						.cssSelector("g[class='stencils'] g[class='children']  g[id*='svg-sid']:nth-child("
								+ (Integer.toString(index + 1)) + ") rect[stroke='black']"));
				nodes.add(fromElement);
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

			if (index == 0) {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
			}

			else if (index == 1) {
				nodes.get(index).click();
				String workflowDays = "3D";
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName,
						AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField,
						WorkflowsTab.txt_WorkflowVisualDesignerIframeUserTaskDescription);
				sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription,
						AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT + dateUtils.getEpoch());
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
				clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
				waitUntilElementIsClickable(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField,
						WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction);
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction,
						AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT);
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

			}

			else if (index == 2) {
				nodes.get(index).click();
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				WebElement source = findElement(WorkflowsTab.ele_WorkflowVisualDesignerNodeConnectingEdge);
				builder.dragAndDrop(source, nodes.get(index + 1)).build().perform();
				waitUtils.waitInterval(1);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField);
				clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskNameField,
						WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName);
				sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName,
						AdoddleCommonStringPool.LABEL_STATUS_CHANGE);
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
				sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeSystemTaskType,
						AdoddleCommonStringPool.LABEL_STATUS_CHANGE);
				waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedSystemTask);
				Assert.assertTrue(getElementText(WorkflowsTab.pop_WorkflowVisualDesignerIframeSystemTaskPopup)
						.contains(AdoddleCommonStringPool.WORKFLOW_POPUP_SYSTEM_TASK));
				if (!isSelected(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask)) {
					clickElement(WorkflowsTab.sel_WorkflowVisualDesignerIframeDistributeSystemTask);

				}

				clickElementAndWait(WorkflowsTab.btn_WorkflowVisualDesignerIframeSelectActionPopupOkay);

			}

		}

	}

	public void createWorkflowTrigger(String triggerName) throws InterruptedException {

		workflowTrigger = triggerName + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureTriggerName);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerName, workflowTrigger);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerDescription, workflowTrigger);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerField, AdoddleCommonStringPool.WORKFLOW_TRIGGER_FIELD_FOLDER);
		clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
		sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerValue, "Automation_WF_Folder");
		waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowsConfigureTriggerValueSearchResult);
		sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerValue, Keys.TAB);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerEvent,
				AdoddleCommonStringPool.TRIGGER_EVENT_UPLOAD_DOCUMENTS);
		if (!isSelected(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode))
			selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode,
					AdoddleCommonStringPool.TRIGGER_ACTION_MODE_POST);
		log.info("Workflow Name::::" + workflowName);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerAction, workflowName);
		clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void valdatedTriggerInListing() {
		log.info("Covered in <validateWorkflowTestdataInInheritedClonedProject>");

	}

	public void validateWorkflowTestdataInClonedProject() {
		log.info("Cloned Project::" + clonedProject);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
		clickElementWithText(clonedProject);
		workflowDefList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowDefs);
		log.info("workflowDefList size::" + workflowDefList.size());
		for (WebElement web : workflowDefList)
			Assert.assertTrue(!web.getText().contains(workflowName));
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		workflowTriggerList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowTriggers);
		for (WebElement web : workflowTriggerList)
			Assert.assertTrue(!web.getText().contains(workflowTrigger));

	}

	public void validateWorkflowTestdataInInheritedClonedProject() throws InterruptedException {

		Boolean flag = false;
		for (int index = 0; index < 10; index++) {
			try {
				clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
				waitForElementWithText(InheritedClonnedProject, 30);
				break;
			} catch (Throwable t) {
				log.error("ERROR: Inherited project not available on Workflow listing");
			}
		}
		clickElementWithText(InheritedClonnedProject);
		workflowDefList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowDefs);
		log.info("workflowDefList size::" + workflowDefList.size());
		for (WebElement web : workflowDefList) {
			if (web.getText().equalsIgnoreCase(workflowName)) {
				flag = true;
				Assert.assertTrue(web.getText().contains(workflowName));
				break;
			} else
				flag = false;
		}
		Assert.assertTrue(flag == true);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		workflowTriggerList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowTriggers);

		for (WebElement web : workflowTriggerList) {

			if (web.getText().equalsIgnoreCase(workflowTrigger)) {
				flag = true;
				Assert.assertTrue(web.getText().contains(workflowTrigger));
				break;
			} else
				flag = false;

		}
		Assert.assertTrue(flag == true);

	}

	public void updateExistingSystemTask(String existingSystemTask, String updatedSystemTask, String instanceName) {
		Boolean flag = false;
		log.info("Existing sys Name::" + existingSystemTask);
		log.info("Updated sys Name::" + updatedSystemTask);
		waitUntilElementIsClickable(WorkflowsTab.lnk_WorkflowsTriggersLHPanelSystemActionLink);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelSystemActionLink);
		systemActionList = findElements(WorkflowsTab.css_WorkflowsListingExistingSystemActions);
		for (int i = 0; i < systemActionList.size(); i++)
			log.info("systemAction name ::" + systemActionList.get(i).getText());
		for (WebElement web : systemActionList) {
			if (web.getText().contains(existingSystemTask)) {

				flag = true;
				waitUntilElementIsClickable(web);
				Assert.assertTrue(web.getText().contains(existingSystemTask));
				web.click();
				break;
			}

		}

		if (flag == true)
			updateSystemAction(updatedSystemTask, instanceName);

		else
			Assert.assertTrue(flag);
	}

	public void updateSystemAction(String sysTaskIndentifier, String instanceName) {

		editedSystemAction = sysTaskIndentifier + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

		if (instanceName.contains("Workspace_Template")) {

			sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionName, editedSystemAction);
			clickElement(WorkflowsTab.Rad_ClearSystemActionOptActiveVersion);
			actionList = findElements(WorkflowsTab.chk_ClearSystemActionsList);
			for (WebElement web : actionList)
				if (web.isSelected() && web.isDisplayed())
					web.click();

			clickElement(WorkflowsTab.chk_ClearSystemActionsForAction);
			clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureSystemActionCreateButton);
			waitForCompletePageLoad();

		}

		else {

			sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionName, editedSystemAction);
			clickElement(WorkflowsTab.Rad_ClearSystemActionOptActiveVersion);
			actionList = findElements(WorkflowsTab.chk_ClearSystemActionsList);
			for (WebElement web : actionList)
				if (web.isSelected() && web.isDisplayed())
					web.click();
			clickElement(WorkflowsTab.chk_ClearSystemActionsForInformation);
			clickElement(WorkflowsTab.chk_ClearSystemActionsForStatusChange);
			clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureSystemActionCreateButton);
			waitForCompletePageLoad();

		}

	}

	public void updateWorklfowDefinition(String existingWorkflow, String updatedworkflowDef) {

		Boolean flag = false;
		waitUntilElementIsClickable(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
		workflowDefList = findElements(WorkflowsTab.css_WorkflowsListingExistingPublishedModels);
		log.info("workflowDefList size::" + workflowDefList.size());
		for (WebElement web : workflowDefList) {

			String workflowModelName = web.findElement(WorkflowsTab.css_WorkflowsListingExistingPublishedModelName)
					.getText();
			log.info("Model:: " + workflowModelName);
			String workflowModelVersion = web.findElement(
					WorkflowsTab.css_WorkflowsListingExistingPublishedModelsVersion).getText();
			log.info("ModelVersion:: " + workflowModelVersion);
			String workflowModelRevision = web.findElement(
					WorkflowsTab.css_WorkflowsListingExistingPublishedModelsRevision).getText();
			log.info("ModelRevision:: " + workflowModelRevision);

			if (workflowModelName.contains(existingWorkflow)) {
				flag = true;
				Assert.assertTrue("Workflow Model Published Version or Revision not matched"
						+ "Version & Revision found:: " + workflowModelVersion + "Vs" + workflowModelRevision,
						Integer.parseInt(workflowModelVersion) == Integer.parseInt(workflowModelRevision));
				contextClickWebElement(web);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowsDefinitionsEditWorkflowDetail);
				clickElementAndWait(WorkflowsTab.ele_WorkflowsDefinitionsEditWorkflowDetail);
				break;

			}
		}
		if (flag == true)
			updateWorkflowDefination(updatedworkflowDef);
		else
			Assert.assertEquals(flag, true);

	}

	public void updateWorkflowDefination(String updatedWrkflowDefName) {

		waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsEditWorkflowDetailWorkflowName);
		editedWorkflowDefinition = updatedWrkflowDefName + dateUtils.getEpoch();
		sendKeys(WorkflowsTab.txt_WorkflowsEditWorkflowDetailWorkflowName, editedWorkflowDefinition);
		waitUntilElementIsClickable(WorkflowsTab.btn_WorkflowsEditWorkflowDetailUpdateButton);
		clickElement(WorkflowsTab.btn_WorkflowsEditWorkflowDetailUpdateButton);
		waitUntilElementIsInvisible(WorkflowsTab.btn_WorkflowsEditWorkflowDetailUpdateButton);
		waitForCompletePageLoad();

	}

	public void validateAndClickUpdatedWorkdefinition() {

		Boolean flag = false;
		workflowDefList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowDefs);
		log.info("workflowDefList size::" + workflowDefList.size());
		for (WebElement web : workflowDefList) {
			if (web.getText().contains(editedWorkflowDefinition)) {
				flag = true;
				waitUntilElementIsClickable(web);
				Assert.assertTrue(web.getText().contains(editedWorkflowDefinition));
				web.click();
				break;

			}
		}

		Assert.assertTrue(flag);

	}

	public void updateExistingWorkflowDefinition(String instanceName) throws InterruptedException {

		waitAndSwitchIframe(WorkflowsTab.frm_Iframe);
		waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeTasks);
		updatePrimaryUserTaskNode(instanceName);
		updateSecondryUserTaskNode(instanceName);
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

	public void clearUserToFieldBox() throws InterruptedException {

		List<WebElement> closeLink = new ArrayList<WebElement>();
		closeLink = findElements(WorkflowsTab.lnk_WorkflowVisualDesignerfieldBoxlist);
		for (WebElement web : closeLink)
			if (web.isDisplayed())
				web.click();

	}

	public void locateAndClickNode(String nodeInstance) {

		Boolean flag = false;
		String nodeActionName;
		userTaskList = findElements(WorkflowsTab.css_WorkflowVisualDesignerUserTasksList);
		log.info("Node size:: " + userTaskList.size());
		for (int nodeIndex = 0; nodeIndex < userTaskList.size(); nodeIndex++) {

			nodeActionName = userTaskList.get(nodeIndex)
					.findElement(By.cssSelector("text[id*='text_name'] :nth-child(1)")).getText();
			log.info(" userTask Name :: " + nodeActionName);
			WebElement userTask = userTaskList.get(nodeIndex).findElement(By.cssSelector("text[id*='text_name']"));
			if (nodeInstance.contains("PrimaryUserTask")) {
				if (nodeActionName.equalsIgnoreCase(AdoddleCommonStringPool.FOR_ACTION)) {
					flag = true;
					userTask.click();
					break;
				} else
					log.info("Failure in Desired Node");

			}

			else {
				if (nodeActionName.contains("For Status")) {
					flag = true;
					userTask.click();
					break;
				}

				else
					log.info("Failure in finding Desired Node");

			}

		}

		Assert.assertTrue(flag);
		userTaskList.clear();

	}

	public void updatePrimaryUserTaskNode(String wInstanceName) throws InterruptedException {

		String workflowDays = "5D";
		String nodeType = "PrimaryUserTask";

		locateAndClickNode(nodeType);
		if (wInstanceName.contains("Workspace_Template")) {

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
			waitUntilElementIsClickable(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField,
					WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction);
			sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction,
					AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT);
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo);
			clearUserToFieldBox();
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			waitUtils.waitInterval(1);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.rfi.bloggs.name"));
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
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);

		}

		else {

			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskName);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName,
					AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField,
					WorkflowsTab.txt_WorkflowVisualDesignerIframeUserTaskDescription);
			sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription,
					AdoddleCommonStringPool.ACTION_FOR_INFORMATION + dateUtils.getEpoch());
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
			waitUntilElementIsClickable(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField,
					WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction);
			sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction,
					AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo);
			clearUserToFieldBox();
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.auto.rfi.name"));
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			waitUtils.waitInterval(1);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.dc.bloggs.name"));
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
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);

		}

	}

	public void updateSecondryUserTaskNode(String wInstanceName) throws InterruptedException {

		String workflowDays = "10D3H30M10S";
		String nodeType = "SecondryUserTask";

		locateAndClickNode(nodeType);
		if (wInstanceName.contains("Workspace_Template")) {

			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo);
			clearUserToFieldBox();
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.rfi.builder.name"));
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			waitUtils.waitInterval(1);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.pa.builder.name"));
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDaysField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDaysField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDays);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDays, workflowDays);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);

		} else {

			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskNameField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskName);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeSystemTaskName,
					AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTaskDescriptionField,
					WorkflowsTab.txt_WorkflowVisualDesignerIframeUserTaskDescription);
			sendKeys(WorkflowsTab.txt_WorkflowVisualDesignerIframeSystemTaskDescription,
					AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION + dateUtils.getEpoch());
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolExpand);
			waitUntilElementIsClickable(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserActionField,
					WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction);
			sendKeys(WorkflowsTab.sel_WorkflowVisualDesignerIframeUserAction,
					AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION);
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTask);
			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserToField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo);
			clearUserToFieldBox();
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			waitUtils.waitInterval(1);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserTo,
					ResourceHandler.loadProperty("test.user.rfi.bloggs.name"));
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			clickElementAndWait(WorkflowsTab.opt_WorkflowVisualDesignerIframeSelectedUserTo);
			waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDaysField);
			clickElementAndWaitForElement(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDaysField,
					WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDays);
			sendKeys(WorkflowsTab.ele_WorkflowVisualDesignerIframeUserDueDays, workflowDays);
			clickElementAndWait(WorkflowsTab.ele_WorkFlowVisualDesignerXToolCollapse);

		}

	}

	public void updatedWorkflowTrigger(String existingTrigger, String updatedtriggerIndentifier, String instanceName)
			throws InterruptedException {

		Boolean flag = false;
		waitUntilElementIsClickable(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		workflowTriggerList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowTriggers);
		for (WebElement web : workflowTriggerList) {

			if (web.getText().contains(existingTrigger)) {
				flag = true;
				waitUntilListElementIsClickable(web);
				web.click();
				break;

			}

		}
		if (flag == true)
			updateExistingTrigger(updatedtriggerIndentifier, instanceName);
		else
			Assert.assertEquals(flag, true);
	}

	public void updateExistingTrigger(String triggerIdentifier, String wInstance) throws InterruptedException {
		editedWorkflowTrigger = triggerIdentifier + dateUtils.getEpoch();

		if (wInstance.contains("Workspace_Template")) {
			waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureTriggerName);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerName, editedWorkflowTrigger);
			selectByVisibleText(WorkflowsTab.txt_WorkflowsConfigureTriggerPriority,
					AdoddleCommonStringPool.TRIGGER_PRIORITY_HIGH);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerField,
					AdoddleCommonStringPool.WORKFLOW_TRIGGER_FIELD_FOLDER);
			clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
			sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerValue, "AutomationUploadFiles");
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowsConfigureTriggerValueSearchResult);
			sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerValue, Keys.TAB);
			clickElementAndWait(WorkflowsTab.lnk_WorkflowsConfigureTriggerAddLink);
			waitUntilElementIsDisplayed(WorkflowsTab.sel_WorkflowsConfigureTriggerLastField);
			sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerLastField,
					AdoddleCommonStringPool.WORKFLOW_TRIGGER_FIELD_DOCUMENT_STATUS);
			clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
			selectByVisibleText(WorkflowsTab.sel_WorkflowsConfigureTriggerSecondryValue,
					AdoddleCommonStringPool.FOR_APPROVAL);
			clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

		}

		else {

			waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureTriggerName);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerName, editedWorkflowTrigger);
			selectByVisibleText(WorkflowsTab.txt_WorkflowsConfigureTriggerPriority,
					AdoddleCommonStringPool.TRIGGER_PRIORITY_HIGH);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerField,
					AdoddleCommonStringPool.WORKFLOW_TRIGGER_FIELD_FOLDER);
			clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
			sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerValue, "PublicFolder");
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowsConfigureTriggerValueSearchResult);
			sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerValue, Keys.TAB);
			clickElementAndWait(WorkflowsTab.lnk_WorkflowsConfigureTriggerAddLink);
			waitUntilElementIsDisplayed(WorkflowsTab.sel_WorkflowsConfigureTriggerLastField);
			sendKeys(WorkflowsTab.sel_WorkflowsConfigureTriggerLastField,
					AdoddleCommonStringPool.WORKFLOW_TRIGGER_FIELD_PURPOSE_OF_ISSUE);
			clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
			selectByVisibleText(WorkflowsTab.sel_WorkflowsConfigureTriggerSecondryValue,
					AdoddleCommonStringPool.STATUS_FOR_INFORMATION);
			clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

		}

	}

	public void validateInheritedProjectWorkflowAttributes() {

		log.info("Cloned Project::" + InheritedClonnedProject);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelDefinitionsLink);
		clickElementWithText(InheritedClonnedProject);
		validateWorkflowDefinitionIsNotEditable();
		validateWorkflowTriggerIsNotEditable();
		validateSystemActionIsNotEditable();

	}

	public void validateWorkflowDefinitionIsNotEditable() {

		Boolean flag = false;
		workflowDefList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowDefs);
		log.info("workflowDefList size::" + workflowDefList.size());
		for (WebElement web : workflowDefList) {
			if (web.getText().contains(editedWorkflowDefinition)) {

				flag = true;
				contextClickWebElement(web);
				waitUntilElementIsDisplayed(WorkflowsTab.ele_WorkflowsDefinitionsEditWorkflowDetail);
				clickElementAndWait(WorkflowsTab.ele_WorkflowsDefinitionsEditWorkflowDetail);
				waitForCompletePageLoad();
				break;

			}
		}
		if (flag == true) {
			Assert.assertTrue(!isEnabled(WorkflowsTab.btn_WorkflowsEditWorkflowDetailUpdateButton));
			clickElementAndWait(WorkflowsTab.btn_WorkflowsEditWorkflowDetailCancel);

		} else
			Assert.assertTrue(flag);

	}

	public void validateWorkflowTriggerIsNotEditable() {

		Boolean flag = false;
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		workflowTriggerList = findElements(WorkflowsTab.css_WorkflowsListingExistingWorkflowTriggers);
		for (WebElement web : workflowTriggerList) {

			if (web.getText().contains(editedWorkflowTrigger)) {
				flag = true;
				waitUntilListElementIsClickable(web);
				web.click();
				waitForCompletePageLoad();
				break;

			}

		}

		if (flag == true) {
			Assert.assertTrue(!isEnabled(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate));
			clickElementAndWait(WorkflowsTab.btn_WorkflowsEditTriggerCancel);

		} else
			Assert.assertTrue(flag);
	}

	public void validateSystemActionIsNotEditable() {
		Boolean flag = false;
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelSystemActionLink);
		systemActionList = findElements(WorkflowsTab.css_WorkflowsListingExistingSystemActions);
		for (WebElement web : systemActionList) {

			if (web.getText().contains(editedSystemAction)) {
				flag = true;
				waitUntilListElementIsClickable(web);
				web.click();
				waitForCompletePageLoad();
				break;
			}

		}
		if (flag == true) {
			Assert.assertTrue(!isEnabled(WorkflowsTab.btn_WorkflowsConfigureSystemActionCreateButton));
			clickElementAndWait(WorkflowsTab.btn_WorkflowsEditActionConfigurationCancel);

		} else
			Assert.assertTrue(flag);

	}

	public void focusProjectAndFolder(String folder, String project) throws InterruptedException {
		navigateTab(LandingPage.lnk_Files);
		waitUntilElementIsDisplayed(FilesTab.css_FileLists);
		if (project.contains("InheritedCloned"))
			clickElementWithText(InheritedClonnedProject);
		else
			clickElementWithText(clonedProject);
		clickElementWithText(folder);
		waitForCompletePageLoad();

	}

	public void focusClonedProject() {
		clickElementWithText(clonedProject);
		waitForCompletePageLoad();
	}

	public void publishDocumentsInFolder(String fcount, String wFolder) throws InterruptedException, IOException {

		if (wFolder.equalsIgnoreCase("AutomationUploadFiles"))
			fileList1 = uploadDocuments(null, Integer.parseInt(fcount), null, null, false, 1,
					Arrays.asList(AdoddleCommonStringPool.STATUS_FOR_INFORMATION),
					Arrays.asList(AdoddleCommonStringPool.STATUS_FOR_APPROVAL), null, null);
		else if (wFolder.equalsIgnoreCase("Automation_WF_Folder"))
			fileList2 = uploadDocuments(null, Integer.parseInt(fcount), null, null, false, 1,
					Arrays.asList(AdoddleCommonStringPool.STATUS_FOR_INFORMATION), null, null, null);
		else
			fileList3 = uploadDocuments(null, Integer.parseInt(fcount), null, null, false, 1,
					Arrays.asList(AdoddleCommonStringPool.STATUS_FOR_INFORMATION), null, null, null);

		waitForCompletePageLoad();
		commonFileList = findElements(FilesTab.css_FileLists);
		log.info("CommonFileList:: " + commonFileList.size());
	}

	public void validateWorkflowAttributes(String fileType, String wStatus, String wStage) {

		if (fileType.equalsIgnoreCase("InheritedCloned_File's")
				&& wStage.contains(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT)) {

			String fileName = strUtils.extractFileNameString(fileList1.get(0));
			log.info("File Name:: " + fileName);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

			for (WebElement web : commonFileList) {
				try {

					String expectedWorkflowStatus = web.findElement(FilesTab.css_workflowStatus).getText();
					log.info("WorkflowStatus::" + expectedWorkflowStatus);
					String expectedWorkflowStage = web.findElement(FilesTab.css_workflowStage).getText();
					log.info("WorkflowStage::" + expectedWorkflowStage);
					Assert.assertTrue(expectedWorkflowStatus.contains(wStatus));
					Assert.assertTrue(expectedWorkflowStage.contains(wStage));
					log.info("Sucessfully Verified Workflow Attribute");

				} catch (Exception e) {

					log.info("Failure in validating Workflow Attributes");
				}

			}
			searchFiles(fileName);
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).contains(wStatus));
			if (getElementText(FilesTab.ele_workflowStage).contains(wStage))
				Assert.assertTrue("Expected Workflow Stage:: " + wStage + " But was "
						+ getElementText(FilesTab.ele_workflowStage), getElementText(FilesTab.ele_workflowStage)
						.contains(wStage));
			else
				Assert.assertTrue(
						"Expected Workflow Stage:: " + wStage + " But was "
								+ getElementText(FilesTab.ele_workflowStage),
						getElementText(FilesTab.ele_workflowStage).contains(
								AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + AdoddleCommonStringPool.COMMA_STRING
										+ AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));

			clickElementAndWait(FilesTab.lnk_workflowStatus);
			waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
			waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, wStatus);
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowStatus).equalsIgnoreCase(wStatus));
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowEndTime) != "--");
			switchDefault();
			clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);

		}

		else if (fileType.equalsIgnoreCase("Cloned_TestFile's") && wStage.contains(AdoddleCommonStringPool.FOR_ACTION)) {

			String fileName = strUtils.extractFileNameString(fileList2.get(0));
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			for (WebElement web : commonFileList) {
				try {
					String expectedWorkflowStatus = web.findElement(FilesTab.css_workflowStatus).getText();
					String expectedWorkflowStage = web.findElement(FilesTab.css_workflowStage).getText();
					Assert.assertTrue(expectedWorkflowStatus.contains(wStatus));
					Assert.assertTrue(expectedWorkflowStage.contains(wStage));
					log.info("Sucessfully Verified Workflow Attribute");
				} catch (Exception e) {

					log.info("Failure in validating Workflow Attribute");
				}

			}
			searchFiles(fileName);
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).contains(wStatus));
			if (getElementText(FilesTab.ele_workflowStage).contains(wStage))
				Assert.assertTrue("Expected Workflow Stage:: " + wStage + " But was "
						+ getElementText(FilesTab.ele_workflowStage), getElementText(FilesTab.ele_workflowStage)
						.contains(wStage));
			else
				Assert.assertTrue(
						"Expected Workflow Stage:: " + wStage + " But was "
								+ getElementText(FilesTab.ele_workflowStage),
						getElementText(FilesTab.ele_workflowStage).contains(
								AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE + AdoddleCommonStringPool.COMMA_STRING
										+ AdoddleCommonStringPool.ACTION_FOR_ACTION));
			clickElementAndWait(FilesTab.lnk_workflowStatus);
			waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
			waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, wStatus);
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowStatus).equalsIgnoreCase(wStatus));
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowEndTime) != "--");
			switchDefault();
			clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
		}

		else if (fileType.equalsIgnoreCase("Cloned_TestFile's") && wStage.contains("--")) {

			String fileName = strUtils.extractFileNameString(fileList2.get(0));
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			searchFiles(fileName);
			waitUntilElementContainsText(FilesTab.ele_workflowStage, wStage);
			Assert.assertTrue(!isDisplayed(FilesTab.lnk_workflowStatus));
			Assert.assertTrue(getElementText(FilesTab.ele_workflowStage).contains(wStage));
			clickElement(FilesTab.lnk_DocListingFirstFileName);
			waitForSwitchWindow(2);
			switchWindow();
			if (fileBetaViewFlag) {

				waitUntilElementIsDisplayed(FilesTab.btn_BetaViewViewFileMoreOptionsButton);
				waitUntilElementIsClickable(FilesTab.btn_BetaViewViewFileMoreOptionsButton);
				clickElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton);
				waitUntilElementIsDisplayed(FilesTab.btn_BetaViewViewFileWorkflowsButton);
				waitUntilElementIsClickable(FilesTab.btn_BetaViewViewFileWorkflowsButton);
				clickElement(FilesTab.btn_BetaViewViewFileWorkflowsButton);
				waitUntilElementContainsText(FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage,
						AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
				Assert.assertTrue(getElementText(FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage).contains(
						AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE));
			}

			else {

				waitUntilElementIsDisplayed(FilesTab.lnk_FileViewerWorkflow);
				waitUntilElementIsClickable(FilesTab.lnk_FileViewerWorkflow);
				clickElementAndWait(FilesTab.lnk_FileViewerWorkflow);
				waitUntilElementContainsText(FilesTab.css_FileViewerWorkflowNoRecordsMessage,
						AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
				Assert.assertTrue(getElementText(FilesTab.css_FileViewerWorkflowNoRecordsMessage).contains(
						AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE));
			}

			closeCurrentWindow();
			switchPreviousWindow(parentWindow);

		}

		else {

			String fileName = strUtils.extractFileNameString(fileList3.get(0));
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			for (WebElement web : commonFileList) {
				try {

					String expectedWorkflowStatus = web.findElement(FilesTab.css_workflowStatus).getText();
					String expectedWorkflowStage = web.findElement(FilesTab.css_workflowStage).getText();
					Assert.assertTrue(expectedWorkflowStatus + " does not contain " + wStatus,
							expectedWorkflowStatus.contains(wStatus));
					Assert.assertTrue(expectedWorkflowStage.contains(wStage));
					log.info("Sucessfully Verified Workflow Attribute");

				} catch (Exception e) {

					log.info("Failure in validating Workflow Attribute");
				}

			}
			searchFiles(fileName);
			Assert.assertTrue(getElementText(FilesTab.lnk_workflowStatus).contains(wStatus));
			if (getElementText(FilesTab.ele_workflowStage).contains(wStage))
				Assert.assertTrue("Expected Workflow Stage:: " + wStage + " But was "
						+ getElementText(FilesTab.ele_workflowStage), getElementText(FilesTab.ele_workflowStage)
						.contains(wStage));
			else
				Assert.assertTrue(
						"Expected Workflow Stage:: " + wStage + " But was "
								+ getElementText(FilesTab.ele_workflowStage),
						getElementText(FilesTab.ele_workflowStage).contains(
								AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION + AdoddleCommonStringPool.COMMA_STRING
										+ AdoddleCommonStringPool.ACTION_FOR_INFORMATION));
			clickElementAndWait(FilesTab.lnk_workflowStatus);
			waitAndSwitchIframe(FilesTab.frm_WorkflowIframe);
			waitUntilElementContainsText(FilesTab.lbl_WorkflowStatus, wStatus);
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowStatus).equalsIgnoreCase(wStatus));
			Assert.assertTrue(getElementText(FilesTab.lbl_WorkflowEndTime) != "--");
			switchDefault();
			clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);

		}

	}

	public void searchFileAndClickTitle(String fName) {
		parentWindow = getCurrentWindow();
		waitUntilElementIsClickable(FilesTab.lnk_DocListingFirstFileName);
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_HISTORY);
		clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		waitForSwitchWindow(2);
		switchWindow();

	}

	public void naviagteLHPanelAndHistory() {

		log.info("Covered in Above Defintion <searchFileAndClickTitle>");
	}

	public void validateDistributedActions(String fileName, String distributedAction) {

		Boolean flag = true;

		if (fileName.equalsIgnoreCase("InheritedCloned_File")
				&& distributedAction.contains(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT)) {

			if (fileBetaViewFlag) {

				waitUntilListOfElementIsDisplayed(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				userDistributionList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				AutomationAssert.assertTrue("Expected List Count :: 4" + "But was" + userDistributionList.size(),
						userDistributionList.size() == 4);

				for (WebElement web : userDistributionList) {

					String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
					String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();

					if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
							|| userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name")))
						Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));

					else if (userName.contains(ResourceHandler.loadProperty("test.user.auto.pa.builder.name"))
							|| userName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name")))
						Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

					else {
						log.info("Failure while validating Audit Trails for User:: " + userName);
						flag = false;
					}

				}

				Assert.assertTrue("Failure while validation userAction", flag);

			}

			else {
				waitUntilElementIsDisplayed(FilesTab.ele_clkFirstRecord);
				clickElementAndWait(FilesTab.ele_clkFirstRecord);
				log.info("UserName::"
						+ findElement(FilesTab.img_FileHistoryFirstRecordActionUserName).getAttribute("title"));

				if (findElement(FilesTab.img_FileHistoryFirstRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
				else
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

				if (findElement(FilesTab.img_FileHistorySecondRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));

				else
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

			}

		}

		else if (fileName.equalsIgnoreCase("Cloned_File")
				&& distributedAction.contains(AdoddleCommonStringPool.FOR_ACTION)) {

			if (fileBetaViewFlag) {

				waitUntilListOfElementIsDisplayed(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				userDistributionList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				String secondaryUser = AdoddleCommonStringPool.AUTOMATION + " " + dataCenter;
				log.info("Sceordary User:: " + secondaryUser);
				AutomationAssert.assertTrue("Expected List Count :: 4" + "But was" + userDistributionList.size(),
						userDistributionList.size() == 4);

				for (WebElement web : userDistributionList) {

					String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
					String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();

					if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))
							|| userName.contains(ResourceHandler.loadProperty("test.user.pa.builder.name")))
						Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_ACTION,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));

					else if (userName.contains(ResourceHandler.loadProperty("test.user.automation.primary.name"))
							|| userName.contains(secondaryUser))
						Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

					else {
						log.info("Failure while validating Audit Trails for User:: " + userName);
						flag = false;
					}

				}

				Assert.assertTrue("Failure while validation userAction", flag);

			} else {

				waitUntilElementIsDisplayed(FilesTab.ele_clkFirstRecord);
				clickElementAndWait(FilesTab.ele_clkFirstRecord);

				if (findElement(FilesTab.img_FileHistoryFirstRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.rfi.builder.name")))
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_ACTION));

				else
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

				if (findElement(FilesTab.img_FileHistorySecondRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.rfi.builder.name")))
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_ACTION));

				else
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

			}

		}

		else {

			if (fileBetaViewFlag) {

				waitUntilListOfElementIsDisplayed(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				userDistributionList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
				AutomationAssert.assertTrue("Expected List Count :: 4" + "But was" + userDistributionList.size(),
						userDistributionList.size() == 4);

				for (WebElement web : userDistributionList) {

					String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
					String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();

					if (userName.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))
							|| userName.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name")))
						Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_INFORMATION,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION));

					else if (userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))
							|| userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
						Assert.assertTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION,
								action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));

					else {
						log.info("Failure while validating Audit Trails for User:: " + userName);
						flag = false;
					}

				}

				Assert.assertTrue("Failure while validation userAction", flag);

			}

			else {

				waitUntilElementIsDisplayed(FilesTab.ele_clkFirstRecord);
				clickElementAndWait(FilesTab.ele_clkFirstRecord);

				if (findElement(FilesTab.img_FileHistoryFirstRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.auto.rfi.name")))
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_INFORMATION));

				else
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistoryFirstRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));

				if (findElement(FilesTab.img_FileHistorySecondRecordActionUserName).getAttribute("title").contains(
						ResourceHandler.loadProperty("test.user.auto.rfi.name")))
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_INFORMATION));

				else
					Assert.assertTrue(getElementText(FilesTab.lbl_FileHistorySecondRecordFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));

			}

		}

		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void searchTemplateName() {
		Boolean flag = false;
		List<WebElement> templateList = new ArrayList<WebElement>();
		templateList = findElements(ClassicLocators.existingTemplateList);
		for (WebElement web : templateList) {
			log.info("web text: " + web.getText());
			if (web.getText().contains(globalWorkspaceTemplateName)) {
				flag = true;
				Assert.assertTrue("Workspace Template missing", web.getText().contains(globalWorkspaceTemplateName));
				break;
			}

		}

		Assert.assertTrue("Template not found:: " + globalWorkspaceTemplateName, flag);
	}

	public void validateTemplateAndClickCloneIcon() {
		Boolean flag = false;
		List<WebElement> templateList = new ArrayList<WebElement>();
		templateList = findElements(ClassicLocators.existingTemplateList);
		for (WebElement web : templateList) {
			log.info("web text: " + web.getText());
			if (web.getText().contains(globalWorkspaceTemplateName)) {
				flag = true;
				web.findElement(ClassicLocators.templateClonedLink).click();
				break;
			}

		}

		Assert.assertTrue(flag);
	}

	public void deactivateClonedProject(String projectName) throws InterruptedException {

		if (projectName.equalsIgnoreCase("Cloned_Project")) {
			deactivateProject(clonedProject);
		} else {
			deactivateInheritanceProject(InheritedClonnedProject);
		}

	}

	public void validateAndDeactivateTemplate() throws InterruptedException {

		try {
			waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);
			waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceTemplatesTab);
			clickElementAndWait(ClassicLocators.lbl_WorkspaceTemplatesTab);
			waitUntilElementIsDisplayed(ClassicLocators.existingTemplateList);
			validateTemplateInListing();
			switchDefault();
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
			switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
			waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceHeaderName);
			Assert.assertTrue(getElementText(ClassicLocators.lbl_WorkspaceHeaderName).equalsIgnoreCase(
					globalWorkspaceTemplateName));
			selectdropDownOption(AdoddleCommonStringPool.EDIT_WORKSPACE);
			waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
			Assert.assertTrue(getElementText(GlobalPageElements.lbl_PageTitle).contains(
					AdoddleCommonStringPool.PAGE_TITLE_WORKSPACE_TEMPLATE));
			selectByVisibleText(ClassicLocators.lnk_CreateEditWorkspaceTemplateStatus,
					AdoddleCommonStringPool.STATUS_CLOSED);
			waitUntilElementIsDisplayed(ClassicLocators.lnk_CreateEditWorkspaceSave);
			clickElementAndWait(ClassicLocators.lnk_CreateEditWorkspaceSave);

		} catch (Throwable t) {
			log.error("Failure in deactivating Template");
		}

	}

	public void validateTemplateInListing() {
		Boolean flag = false;
		List<WebElement> templateList = new ArrayList<WebElement>();
		templateList = findElements(ClassicLocators.existingTemplateList);
		for (WebElement web : templateList) {
			log.info("web text: " + web.getText());
			if (web.getText().contains(globalWorkspaceTemplateName)) {
				flag = true;
				web.findElement(ClassicLocators.lnk_WorkspaceTemplateName).click();
				break;
			}

		}

		if (flag == true)
			log.info("Template found sucessfully");

		else
			Assert.assertTrue("Failure in validating Template", flag);

		waitForCompletePageLoad();

	}

}