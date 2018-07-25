package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormIncompleteActionsScripts extends AdoddleCommonAppMethods {
	public static String formTitle;
	private String formUpdatedStatus;
	final private List<String> actionList = Arrays.asList(AdoddleCommonStringPool.ACTION_FOR_INFORMATION,
			AdoddleCommonStringPool.ACTION_ASSIGN_STATUS, AdoddleCommonStringPool.ACTION_DISTRIBUTE,
			AdoddleCommonStringPool.ACTION_RESPOND, AdoddleCommonStringPool.ACTION_ATTACH_DOCS,
			AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT, AdoddleCommonStringPool.ACTION_FOR_ACTION);
	final private static Logger log = AutomationLogger.getInstance().getLogger(FormIncompleteActionsScripts.class);

	public void createNewForm(String project, String appFolder, String appType, String actionGroup)
	{
		clickElementWithText(project);
		clickElementWithText(appFolder);
		clickElementWithText(appType);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		if (appType.equalsIgnoreCase("Automation_Working_Cal_Distribute_App"))
			enteredDistributeFormTitleAndDetails(actionGroup);
		else
			enteredFormTitleAndDetails("", "", actionGroup);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		waitForCompletePageLoad();
	}

	private void enteredDistributeFormTitleAndDetails(String actionGroup) {
		switchDefault();
		formTitle = "AutoForm" + dateUtils.getEpoch();
		collectionDataMap.put("Form Title", formTitle);
		log.info("formTitle :" + formTitle);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		clear(ProjectFormsTab.txt_CreateFormTitle);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		waitForCompletePageLoad();
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAssociationIcon);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAssociationIcon);
		clear(ProjectFormsTab.txt_PopCreateDistributeGroupFormCode);
		waitForCompletePageLoad();
		selectByVisibleText(ProjectFormsTab.drp_PopCreateDistributeGroupFormDistributionGroupDropdown, actionGroup);
		collectionDataMap.put("Distribution Group", actionGroup);
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
	}

	private void enteredFormTitleAndDetails(String action, String user, String actionGroup) {
		switchDefault();
		formTitle = "AutoForm" + dateUtils.getEpoch();
		collectionDataMap.put("Form Title", formTitle);
		log.info("formTitle :" + formTitle);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		collectionDataMap.put("Distribution Group", actionGroup);
		distributeForm(action, user, actionGroup);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
	}

	private void distributeForm(String action, String user, String actionGroup) {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton, ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		if (actionGroup != null) {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, actionGroup);
			clickElementAndWaitForElement(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'"+actionGroup+"')]//preceding::input[@type='checkbox']"), ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);

		} else {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, user);
			clickElementAndWait(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + user + "')]"));
			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		}
	}

	public void verifyCreatedForm() {
		searchForms(formTitle);
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).equalsIgnoreCase(formTitle));
	}

	public void verifyCreatedFormWithActions() {
		verifyCreatedForm();
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		List<String> popHoverActionList = new ArrayList<String>();
		if (findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0) {

			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks))
				popHoverActionList.add(popHoverAction.getText().split(":")[1]);

			log.info("popHoverActionList : " + popHoverActionList);
			log.info("actionList : " + actionList);
		} else
			Assert.assertTrue(false);
		Assert.assertTrue(actionList.containsAll(popHoverActionList));
	}

	public void verifyDistributionHistory(String actionStatus) {
		searchForms(formTitle);
		contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_HISTORY, AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		verifyViewFormActivateTab();
		verifyViewFormSelectedHistoryDropdown(AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		verifyDistributedActionsAndStatusOnDistributionHistory(actionStatus);
		closeSecondWindow();
	}

	private void verifyDistributedActionsAndStatusOnDistributionHistory(String distributionStatus) {
		List<String> incompleteActionList = new ArrayList<String>();
		List<WebElement> incompleteActionStatusList = findElements(ProjectFormsTab.css_BetaViewFormsDistributeHistoryIncompleteActionStatusList);
		int i = 0;
		for (WebElement action : findElements(ProjectFormsTab.css_BetaViewFormsDistributeHistoryIncompleteActionsList)) {

			if(distributionStatus.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE)) {
				incompleteActionList.add(action.getText());
				Assert.assertTrue(incompleteActionStatusList.get(i).getText().contains(distributionStatus));
			}
			else {
				if(i != 0 && i != 1) {
					incompleteActionList.add(action.getText());
					Assert.assertTrue(incompleteActionStatusList.get(i).getText().contains(distributionStatus));
				}
			}
			i++;
		}
		log.info("incompleteActionList : " + incompleteActionList);
		Assert.assertTrue(actionList.size() == incompleteActionList.size());
		Assert.assertTrue(actionList.containsAll(incompleteActionList));
	}

	private void verifyFormActionCompleted(String action) {
		List<String> popHoverActionList = new ArrayList<String>();
		reloadPage();
		navigateTabByText(AdoddleCommonStringPool.TAB_PROJECT_FORMS);
		waitForCompletePageLoad();
		verifyCreatedForm();
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		popHoverActionList.clear();
		for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList))
			popHoverActionList.add(popCompleteAction.getText().split(":")[1]);

		Assert.assertTrue(popHoverActionList.contains(action));

		if (findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0) {
			popHoverActionList.clear();
			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks))
				popHoverActionList.add(popHoverAction.getText().split(":")[1]);
			Assert.assertTrue(!popHoverActionList.contains(action));
		} else
			Assert.assertTrue(true);
	}

	public void performAllIncompleteActions() {
		boolean flag = false;
		String missingAction = null;
		for (String expectedAction : actionList) {

			waitForCompletePageLoad();
			searchForms(formTitle);
			mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				if (expectedAction.equalsIgnoreCase(popHoverAction.getText().split(":")[1])) {

					flag = true;
					log.info("expectedAction : " + expectedAction);
					popHoverAction.click();

					if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_ASSIGN_STATUS))
						performAssignStatusAction();
					else if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_ATTACH_DOCS))
						performAttachDocsAction();
					else if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_DISTRIBUTE))
						performDistributeAction();
					else if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT))
						performForAcknowledgementAction();
					else if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_FOR_ACTION))
						performForActionAction();
					else if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_FOR_INFORMATION))
						performForInformationAction();
					else if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_RESPOND))
						performRespondAction();


					verifyFormActionCompleted(expectedAction);

					if (expectedAction.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_ASSIGN_STATUS))
						verifyStatusHistory();
					break;
				} else {
					missingAction = expectedAction;
					log.info("missingAction : " + missingAction);
					flag = false;
				}
			}
		}
		if (!flag)
			Assert.assertTrue("Missing Action not performed : " + missingAction, false);
	}

	public void verifyFormAllActionsCompleted() {
		verifyCreatedForm();
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		if (findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);

		List<String> popHoverActionList = new ArrayList<String>();
		for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList))
			popHoverActionList.add(popCompleteAction.getText().split(":")[1]);
		Assert.assertTrue(popHoverActionList.containsAll(actionList));
		verifyDistributionHistory(AdoddleCommonStringPool.ACTION_STATUS_COMPLETE);
	}

	/*public void verifyAllHistory() {
		contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickHistory, FilesTab.opt_FileContextClickHistoryAll);
		verifyActivatedLHPanelTab(AdoddleCommonStringPool.OPTION_HISTORY);
		verifySelectedHistoryDropdown(AdoddleCommonStringPool.OPTION_HISTORY_ALL);
		verifyActionsAndActionStatusOnAllHistory(AdoddleCommonStringPool.ACTION_STATUS_COMPLETE);
		closeSecondWindow();
	}*/

	/*private void verifyActionsAndActionStatusOnAllHistory(String actionStatus) {
		waitForCompletePageLoad();
		for (WebElement history : findElements(ProjectFormsTab.css_FormAllHistoryList)) {
			if (history.getText().startsWith("Automation Distributed")) {
				history.click();
				waitForCompletePageLoad();

				List<String> completeActionList = new ArrayList<String>();
				List<WebElement> completeActionStatusList = findElements(ProjectFormsTab.css_FormHistorySelectedRowCompleteActionStatusList);
				int i = 0;
				for (WebElement action : findElements(ProjectFormsTab.css_FormHistorySelectedRowCompleteActionsList)) {
					completeActionList.add(action.getText().split(": ")[1]);
					Assert.assertTrue(completeActionStatusList.get(i).getText().contains(actionStatus));
					i++;
				}
				log.info("completeActionList : " + completeActionList);
				Assert.assertTrue(actionList.size() == completeActionList.size());
				Assert.assertTrue(actionList.containsAll(completeActionList));
				break;
			}
		}
	}*/

	/*** Perform "Attach Docs" Action ***/

	private void performAttachDocsAction() {
		verifyFormViewed();
		editAndDistributeForm();
		sysUtils.authenticateRemoteMachine(nodeIP);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		CreateEditPOIScripts.createFile1 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("created file as: " + CreateEditPOIScripts.createFile1);
		List<String> fileList = sysUtils.getFileList(CreateEditPOIScripts.createFile1);
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		collectionDataMap.put("Attached Doc", CreateEditPOIScripts.createFile1);
		clickElementAndWaitForElement(ModelsTab.btn_PopModelCommentAttachmentsAttachButton, ProjectFormsTab.btn_CreateFormSendButton);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		verifyFormAttachmentOnViewForm();
	}

	private void editAndDistributeForm() {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
	}

	private void verifyFormAttachmentOnViewForm() {
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewViewFormAttachmentAssociationButton, ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList);
		Assert.assertTrue(getElementText(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList).contains(strUtils.extractFileNameString(CreateEditPOIScripts.createFile1)));
		closeSecondWindow();
	}

	/*** Perform "Assign Status" Action ***/

	private void performAssignStatusAction() {
		selectFormStatusAndEnteredNotes();
		verifyUpdatedStatusOnViewForm();
		navigateTabByText(AdoddleCommonStringPool.TAB_PROJECT_FORMS);
		verifyCreatedForm();
		waitForCompletePageLoad();
		try {
			waitUntilElementContainsText(ProjectFormsTab.ele_FirstFormStatus, formUpdatedStatus, 10);
		} catch (Throwable t) {
			log.info(": Failed to Update Status :");
		}
		Assert.assertTrue(getElementText(ProjectFormsTab.ele_FirstFormStatus).equalsIgnoreCase(formUpdatedStatus));
	}

	private void selectFormStatusAndEnteredNotes() {
		switchToSecondWindow();
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel).contains(AdoddleCommonStringPool.LABEL_STATUS_CHANGE));
		selectByIndex(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, 1);
		if (new Select(findElement(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown)).getFirstSelectedOption()
				.getText().equalsIgnoreCase(AdoddleCommonStringPool.STATUS_CLOSED))
			selectByIndex(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, 2);
		formUpdatedStatus = new Select(findElement(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown))
				.getFirstSelectedOption().getText();
		log.info("formUpdatedStatus :" + formUpdatedStatus);
		sendKeys(ProjectFormsTab.txt_BetaViewFormStatusChangeReasonNote, javaUtils.getRandomString(20));
		clickElementAndWait(ProjectFormsTab.btn_BetaViewPopFormStatusChangeChangeStatus);
		collectionDataMap.put("Assigned Status", formUpdatedStatus);
	}

	private void verifyUpdatedStatusOnViewForm() {
		waitForCompletePageLoad();
		waitUntilElementContainsText(ProjectFormsTab.ele_BetaViewViewFormUpdatedStatus, formUpdatedStatus);
		waitForCompletePageLoad();
		closeSecondWindow();
	}

	private void verifyStatusHistory() {
		waitForCompletePageLoad();
		contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_HISTORY);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickHistoryStatus, FilesTab.opt_FileContextClickHistoryStatus);
		verifyViewFormActivateTab();
		verifyViewFormSelectedHistoryDropdown(AdoddleCommonStringPool.STATUS);
		verifyUpdatedStatusValueOnStatusHistoryPage();
	}

	private void verifyViewFormActivateTab() {
		switchToSecondWindow();
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel).contains(AdoddleCommonStringPool.OPTION_HISTORY));
	}

	private void verifyViewFormSelectedHistoryDropdown(String historyType) {
		String selectedHistotyDropdown = new Select(
				findElement(ProjectFormsTab.drp_BetaViewHistoryTabHistoryTypeDropdown)).getFirstSelectedOption()
				.getText();
		log.info("selectedHistotyDropdown :" + selectedHistotyDropdown);
		Assert.assertTrue(selectedHistotyDropdown.contains(historyType));
	}

	private void verifyUpdatedStatusValueOnStatusHistoryPage() {
		log.info("Actual history Status :" + getElementText(ProjectFormsTab.ele_BetaViewViewFormHistoryTabFirstRowStatusRemarks));
		log.info("Expected history Status :" + formUpdatedStatus);
		Assert.assertTrue(getElementText(ProjectFormsTab.ele_BetaViewViewFormHistoryTabFirstRowStatusRemarks).contains(formUpdatedStatus));
		closeSecondWindow();
	}

	/*** Perform "Distribute" Action ***/

	private void performDistributeAction() {
		waitForCompletePageLoad();
		verifyPopUpWithText(AdoddleCommonStringPool.LABEL_DISTRIBUTE);
		assignActionToUser(System.getProperty("primary.username"));
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//button[text()='Distribute']"));
	}

	private void assignActionToUser(String user) {
		sendKeys(DashboardTab.txt_RecentFormsDistributeFormToField, user);
		sendKeys(DashboardTab.txt_RecentFormsDistributeFormToField, Keys.ENTER);
		clickElementAndWait(FilesTab.btn_DistributePopSingleActionToggleButton);
		selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
		clickElementAndWait(FilesTab.lnk_PopDatePickerAssignedCurrentDate);
	}

	/*** Perform "For Acknowledgement" Action ***/

	private void performForAcknowledgementAction() {
		waitForCompletePageLoad();
		verifyPopUpWithText(AdoddleCommonStringPool.LABEL_ACKNOWLEDGEMENT);
		selectPopupCheckbox();
		clickElementAndWait(DashboardTab.btn_PopDashboardActionsSubmitButton);
	}

	/*** Perform "For Action" Action ***/

	private void performForActionAction() {
		waitForCompletePageLoad();
		verifyPopUpWithText(AdoddleCommonStringPool.LABEL_ACTION);
		selectPopupCheckbox();
		sendKeys(DashboardTab.txt_PopDashboardActionsTextareaInput,
				javaUtils.getRandomString(20) + JavaUtils.getRandomNumber(5));
		clickElementAndWait(DashboardTab.btn_PopDashboardActionsSubmitButton);
	}

	/*** Perform "For Information" Action ***/

	public void performForInformationAction() {
		verifyFormViewed();
		closeSecondWindow();
	}

	/*** Perform "Respond" Action ***/

	public void performRespondAction() {
		String parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
		String respondMsg = "RespondMessage" + dateUtils.getEpoch();
		sendKeys(ProjectFormsTab.txt_RespondFormRespondMessageInput, respondMsg);
		clear(DashboardTab.txt_RespondFormGroupCodeInput);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateFormSendButton);
		clickAndHoldElement(ProjectFormsTab.btn_CreateFormSendButton);
		try {waitUtils.waitInterval(1);} catch(Throwable t) {log.info(": WaitInterval Failed :");}
		releaseElementClick(ProjectFormsTab.btn_CreateFormSendButton);
		waitForCompletePageLoad();
		switchDefault();
		waitForCompletePageLoad();
		waitUntilElementContainsValue(ProjectFormsTab.txt_RespondFormRespondMessageInput, respondMsg);
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	/* Common Methods */

	private void switchToSecondWindow() {
		CreateModelCommentScripts.parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void closeSecondWindow() {
		closeCurrentWindow();
		switchPreviousWindow(CreateModelCommentScripts.parentWindow);
		waitForCompletePageLoad();
	}

	public void verifyFormViewed() {
		switchToSecondWindow();
		waitForCompletePageLoad();
		String viewForm;
		viewForm = getElementText(ProjectFormsTab.ele_BetaViewViewFormTitle);
		log.info("viewForm :" + viewForm);
		Assert.assertTrue(viewForm.contains(formTitle));
		try{waitUtils.waitInterval(5);} catch(Throwable t){}
	}

	private void selectPopupCheckbox() {
		if (!isSelected(DashboardTab.chk_PopDashboardActionsCheckbox))
			clickElementAndWait(DashboardTab.chk_PopDashboardActionsCheckbox);
	}
}
