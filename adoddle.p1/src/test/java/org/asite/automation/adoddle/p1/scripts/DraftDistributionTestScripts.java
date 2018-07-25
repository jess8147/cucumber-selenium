package org.asite.automation.adoddle.p1.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DraftDistributionTestScripts extends AdoddleCommonAppMethods {

	private String					parentHandle, testFormTitle;
	private Boolean					isListPresent;
	final private List<String>		popHoverActionList	= new ArrayList<String>();
	final private static Logger		log					= AutomationLogger.getInstance().getLogger(DraftDistributionTestScripts.class);

	public void selectFormAndOpen() {
		testFormTitle = createTestDataForm();
		collectionDataMap.put("Draft Form Title", testFormTitle);
		log.info("form data :" + testFormTitle);
		searchForms(testFormTitle);
		parentHandle = clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormID);
	}

	public void verifyFormIsViewed() {
			waitForCompletePageLoad();
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewFormDetailsFormName, testFormTitle);
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsFormName), (testFormTitle)), getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsFormName).equalsIgnoreCase(testFormTitle));
	}

	public void distributeSelectedForm(String distributionUser) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormActionDistribute);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormActionDistribute);
			sendKeys(ProjectFormsTab.txt_BetaViewDistributeToInput, distributionUser);
			if (!isSelected(ProjectFormsTab.chk_BetaViewSelectFirstDistributeUser1)) {

				clickElementAndWait(ProjectFormsTab.chk_BetaViewSelectFirstDistributeUser1);
			}

			clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
			sendKeys(ProjectFormsTab.txt_BetaViewDistributeSubjectInput, "Distribute Form to " + distributionUser);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewPopDistributeButtonDistribute);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			closeCurrentWindow();
			switchPreviousWindow(parentHandle);
	}

	public void verifyFormIsAvailable() {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		AutomationAssert.verifyTrue(getCount(ProjectFormsTab.css_ProjectFormListingCount) == 1);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).equalsIgnoreCase(testFormTitle));

		AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains("For Information"));
	}

	public void editFormAndAssignAction(String action, String user1, String user2, String user3) throws InterruptedException {
			clickElement(ProjectFormsTab.lnk_FormListingFirstFormID);
			waitForSwitchWindow(2);
			switchWindow();
			waitForCompletePageLoad();
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			waitAndSwitchIframe(ProjectFormsTab.frm_replyFormIframe);
			sendKeys(ProjectFormsTab.txt_BetaViewDistributeToInput, user1);
			waitUntilElementCountToBeMoreThan(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox, 0);
			if (!isSelected(findElements(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox).get(0)))
				clickElementAndWait(findElements(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox).get(0));

			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
			clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
			sendKeys(ProjectFormsTab.txt_BetaViewDistributeToInput, user2);
			if (!isSelected(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox))
				clickElementAndWait(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox);

			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
			clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
			sendKeys(ProjectFormsTab.txt_BetaViewDistributeToInput, user3);
			if (!isSelected(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox))
				clickElementAndWait(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox);

			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
			clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
			List<WebElement> toggleActionElements = findElements(ProjectFormsTab.css_CreateFormToggleActions);
			for (WebElement e : toggleActionElements) {
				waitUtils.waitInterval(1);
				clickElementAndWait(e);
				selectByVisibleText(ProjectFormsTab.drp_CreateFormAssignActionToUser, action);
				clickElementAndWait(ProjectFormsTab.ele_CreateFormToggleActionsClose);
				waitUntilElementIsInvisible(ProjectFormsTab.ele_CreateFormToggleActions);
			}
			waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
			sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, "Edit " + testFormTitle + " and Assign " + action + " to " + user1 + ", " + user2 + ", " + user3);
			sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, Keys.TAB);
			waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
			waitForCompletePageLoad();
			clickElement(ProjectFormsTab.btn_CreateFormSaveDraft);
			waitForCompletePageLoad();
			try {
				clickElementAndWait(ProjectFormsTab.btn_CreateFormSaveDraft);
			}
			catch (Throwable t) {
				log.error("by passing error");
			}
			acceptAlertAndWait();
			waitUntilElementIsInvisible(ProjectFormsTab.btn_CreateFormSaveDraft);
			switchDefault();
			closeCurrentWindow();
			waitForSwitchWindow(1);
			switchPreviousWindow(parentHandle);
			waitForCompletePageLoad();

	}

	public void verifyActionAssigned(String action) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		waitUntilElementIsDisplayed(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList);
		mouseHover(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
		if (isListPresent) {
			popHoverActionList.clear();
			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				popHoverActionList.add(popHoverAction.getText().split(":")[1]);
			}
			log.info("Assigned Action List :" + popHoverActionList);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(popHoverActionList.toString(), action), popHoverActionList.contains(action));
		}
		else {
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ProjectFormsTab.lnk_FormListingFirstFormAction, true), isDisplayed(ProjectFormsTab.lnk_FormListingFirstFormAction));
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction), action), getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction).contains(action));
		}
	}

	public void editResponseAndAssignAction(String actionTitle, String user) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		clickElementAndWait(ProjectFormsTab.lnk_FormsFirstAction);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.ele_CreateFormYourResponse);
		waitUntilElementCountToBeMoreThan(ProjectFormsTab.txt_BetaViewCreateFormYourEditableResponse, 0);
		List<WebElement> editableResponses = findElements(ProjectFormsTab.txt_BetaViewCreateFormYourEditableResponse);
		if (editableResponses.size() > 1)
			sendKeys(editableResponses.get(editableResponses.size() - 1), "Draft Reply to ??" + testFormTitle);
		else
			sendKeys(ProjectFormsTab.txt_BetaViewCreateFormYourEditableResponse, "Draft Reply to " + testFormTitle);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormYourResponse);
		sendKeys(ProjectFormsTab.txt_BetaViewDistributeToInput, user);
		if (!isSelected(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox))
			clickElementAndWait(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox);
			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, actionTitle);
		clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
		List<WebElement> toggleActionElements = findElements(ProjectFormsTab.css_CreateFormToggleActions);
		for (WebElement e : toggleActionElements) {
			e.click();
			waitForCompletePageLoad();
			selectByVisibleText(ProjectFormsTab.drp_CreateFormAssignActionToUser, actionTitle);
			clickElement(ProjectFormsTab.ele_CreateFormToggleActionsClose);
		}
		clickElement(ProjectFormsTab.btn_CreateFormSaveDraft);
		acceptAlertAndWait();
		switchDefault();
		closeCurrentWindow();
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
	}

	public void createFormFromDraft(String user) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		clickElementAndWait(ProjectFormsTab.lnk_FormListingFirstFormID);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionEditDraft);
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditDraft);
		List<WebElement> editableResponseList = findElements(ProjectFormsTab.txt_BetaViewCreateFormYourEditableResponse);
		if (editableResponseList.size() > 1)
			sendKeys(editableResponseList.get(editableResponseList.size() - 1), user + " Perform Review Draft");
		else if (editableResponseList.size() == 1)
			sendKeys(ProjectFormsTab.txt_BetaViewCreateFormYourEditableResponse, user + " Perform Review Draft");
		sendKeys(ProjectFormsTab.txt_BetaViewCreateFormYourEditableResponse, Keys.TAB);
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		waitForCompletePageLoad();
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormSave);
		switchDefault();
		closeCurrentWindow();
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle);
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(globalProjectTitle);
		searchForms(testFormTitle);
		AutomationAssert.verifyTrue(!isDisplayed(FilesTab.lnk_FilesFirstAction));
		AutomationAssert.verifyTrue(getElementText(FilesTab.ele_FilesFirstActionCompleted).contains(AdoddleCommonStringPool.ACTION_REVIEW_DRAFT));
	}

	public void verifyActionsCleared(String actionTitle) {
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(globalProjectTitle);
		searchForms(testFormTitle);
		waitUntilElementIsDisplayed(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList);
		mouseHover(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		isListPresent = findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList).size() > 0;
		if (isListPresent) {
			popHoverActionList.clear();
			for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
				popHoverActionList.add(popCompleteAction.getText().split(":")[1]);
			}
			log.info("Cleared Action List :" + popHoverActionList);
			AutomationAssert.verifyTrue(popHoverActionList.contains(actionTitle));
		}
		else if (isDisplayed(ProjectFormsTab.lnk_FormListingFirstFormAction))
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction).contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION));
		else {
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ProjectFormsTab.lnk_FormListingFirstFormAction, false), (!isDisplayed(ProjectFormsTab.lnk_FormListingFirstFormAction)));
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_FormListingFirstFormActionDiv), actionTitle), getElementText(ProjectFormsTab.ele_FormListingFirstFormActionDiv).contains(actionTitle));
		}
	}

	private String createTestDataForm() {
		String formSubject;
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_EmbeddedEmailCreateForm);
		clickElementAndWait(ProjectFormsTab.lnk_EmbeddedEmailCreateForm);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_BetaViewDistributeToInput);
		sendKeys(ProjectFormsTab.txt_BetaViewDistributeToInput, System.getProperty("primary.username"));
		if (!isSelected(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox)) {
				clickElementAndWait(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox);
		}
		clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
		formSubject = "AutoReviewDraftForm" + dateUtils.getEpoch();
		sendKeys(ProjectFormsTab.txt_CreateFormSubject, formSubject);
		sendKeys(ProjectFormsTab.txt_CreateFormSubject, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_CreateFormDescription, "The ORI Message created for" + formSubject);
		sendKeys(ProjectFormsTab.txt_CreateFormDescription, Keys.TAB);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		waitForCompletePageLoad();
		switchDefault();
		waitForCompletePageLoad();
		return formSubject;
	}
}
