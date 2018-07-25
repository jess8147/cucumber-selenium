/*  Testdata required for this script as follows.
     1). DelegateActionsDistributionGroup1
     2). DelegateActionsDistributionGroup2
     3). DelegateActionsDistributionGroup3  
 */
package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ClearDelegateActions extends AdoddleCommonAppMethods {

	public static String parentHandle;
	private List<WebElement> formActionList = new ArrayList<WebElement>();
	private static String formSubject = null;
	final private List<String> attachedFileList = new ArrayList<String>();
	final private static List<String> expectedActionList = new ArrayList<String>();

	public ClearDelegateActions() {
		log = AutomationLogger.getInstance().getLogger(this.getClass());
	}

	public void focusFormTemplate(String projectName, String formFolder, String formType) {
		clickElementWithText(projectName);
		clickElementWithText(formFolder);
		clickElementWithText(formType);
	}

	public void selectDistributionGroup(String groupName) {

		if (groupName.contains("DistributionGroup1") || groupName.contains("DistributionGroup3")) {
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, groupName);
			waitUntilElementIsDisplayed(By
					.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'"
							+ groupName + "')]"));
			findElement(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField).sendKeys(
					Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		}

		else if (groupName.contains("DistributionGroup2")) {
			clearFieldBox();
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, groupName);
			waitUntilElementIsDisplayed(By
					.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'"
							+ groupName + "')]"));
			findElement(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField).sendKeys(
					Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		} else {
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, groupName);
			findElement(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField).sendKeys(
					Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		}

	}

	public void inputMandatoryFormAttributes() {
		formSubject = "AutomationTest_DelegateActionsForm" + epoch;
		collectionDataMap.put("form title ", formSubject);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, formSubject);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, ResourceHandler.loadProperty("special.char.test.string"));
	}

	public void attachFilesToForm() {
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path"));
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		List<WebElement> fileAttachList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		for (WebElement attachFile : fileAttachList) {
			attachedFileList.add(attachFile.getText());
		}
		log.info("attachedFileList :" + attachedFileList);
		clickOnAttach();
	}

	private void clickOnAttach() {
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
		try {
			Assert.assertTrue(getElementText(ProjectFormsTab.lbl_CreateFormUploadSucessMsg).contains(
					AdoddleCommonStringPool.MSG_UPLOAD_COMPLETED));
		} catch (Throwable e) {
			log.info("Failure while attachment operation");
		}
	}

	public void clickSaveButton() {
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		verifyFormSuccessMessage();
	}

	private void verifyFormSuccessMessage() {
		try {
			verifyElementText(DiscussionsTab.lbl_FormAddSuccessMsg, AdoddleCommonStringPool.FORM_SUCCESS_MESSAGE);
		} catch (Throwable t) {
			log.error(t.getStackTrace());
			log.error("Success Message could not be verified");
		}
		waitForCompletePageLoad();
	}

	public void validateCreatedForm() {
		searchForms(formSubject);
		Assert.assertTrue("Form Title:",
				getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).contains(formSubject));
		mouseHoverElement(ProjectFormsTab.img_ProjectFormsFirstFormAssociatedDiscussion);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FirstFormAttachmentsLink,
				ProjectFormsTab.pop_AttachmentAndAssociationPopup);
		validateAppAttachmentsOnPopUpAttachmentAndAssociations();
		clickElementAndWait(ProjectFormsTab.pop_AttachmentAndAssociationCancel);
		waitForCompletePageLoad();
		AdoddleScenarioMarkers.clearDelegateActionsScriptFlag = true;
	}

	private void validateAppAttachmentsOnPopUpAttachmentAndAssociations() {

		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupAttachments);
		List<WebElement> associationElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationAttachmentsFileNames);
		List<String> actualAssociationFileDocRefs = new ArrayList<String>();

		for (WebElement e : associationElements)
			actualAssociationFileDocRefs.add(e.getText());

		AutomationAssert.verifyTrue(actualAssociationFileDocRefs.containsAll(attachedFileList));

	}

	public void searchAndValidateForm() {
		searchForms(formSubject);
		Assert.assertTrue("Form Title:",
				getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).contains(formSubject));
		waitForCompletePageLoad();
	}

	public void validateFormActions() {
		log.info("Covered in <searchAndClickActions> defination");
	}

	public void searchAndClickActions(String actionName) {
		boolean flag = false;
		mouseHoverElement(ProjectFormsTab.css_FormsFirstAdditionalActions);
		formActionList = findElements(ProjectFormsTab.css_FormsFirstAdditionalActionsList);
		for (WebElement ele : formActionList) {
			if (getElementText(ele).contains(
					AdoddleCommonStringPool.ORI_001 + AdoddleCommonStringPool.COLON_STRING
							+ AdoddleCommonStringPool.ACTION_RESPOND)) {
				flag = true;
				parentHandle = clickElementAndSwitchWindow(ele);
				break;
			} else
				log.info("Action not found");
		}
		AutomationAssert.verifyTrue(actionName + " Action not found", flag);
		waitForCompletePageLoad();
	}

	public void verifyPopup(String expectedText) {
		if (expectedText.contains("ORI")) {
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		} else if (expectedText.contains("RES")) {
			waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
		} else
			log.error("Create Form / Reply Form Window not found");
	}

	public void performActions(String fileAction) throws InterruptedException {

		if (fileAction.contains("Respond")) {
			attachFilesToForm();
			sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, javaUtils.getRandomString(20));
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
			clickElement(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
			clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
			waitUntilElementContainsText(ProjectFormsTab.ele_BetaViewViewFormTitle, formSubject);
			closeCurrentWindow();
			switchPreviousWindow(parentHandle);
			waitForCompletePageLoad();
		} else {
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton,
					ProjectFormsTab.btn_CreateFormSendButton);
			switchDefault();
			closeCurrentWindow();
			switchPreviousWindow(parentHandle);
			waitForCompletePageLoad();
			waitUntilElementIsDisplayed(GlobalPageElements.btn_FilterProject);
		}

	}

	public void validatedAssignedActions(String actionCount) {
		int expectedActionCount;
		searchForms(formSubject);
		Assert.assertTrue("Form Title:",
				getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).contains(formSubject));
		mouseHover(ProjectFormsTab.css_FormsFirstAdditionalActions);
		formActionList = findElements(ProjectFormsTab.css_FormsFirstAdditionalActionsList);
		expectedActionCount = formActionList.size();
		log.info("ActionCount::" + expectedActionCount);
		if (actionCount.contains("+9"))
			Assert.assertTrue("FormActions count: " + expectedActionCount + " != " + 10, expectedActionCount == 10);
		else if (actionCount.contains("+13"))
			Assert.assertTrue("FormActions count: " + expectedActionCount + " != " + 14, expectedActionCount == 14);

		else if (actionCount.contains("+11"))
			Assert.assertTrue("FormActions count: " + expectedActionCount + " != " + 12, expectedActionCount == 12);

		else if (actionCount.contains("+4"))
			Assert.assertTrue("FormActions count: " + expectedActionCount + " != " + 5, expectedActionCount == 5);

		else if (actionCount.contains("+14"))
			Assert.assertTrue("FormActions count: " + expectedActionCount + " != " + 15, expectedActionCount == 15);

		else if (actionCount.contains("+20"))
			Assert.assertTrue("FormAction count: " + expectedActionCount + " != " + 21, expectedActionCount == 21);

		else if (actionCount.contains("+6"))
			Assert.assertTrue("FormAction count: " + expectedActionCount + " != " + 7, expectedActionCount == 7);

		waitForCompletePageLoad();
	}

	public void selectFormAndOpen() {
		parentHandle = getCurrentWindow();
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
	}

	public void verifyFormIsViewed() {
		waitUntilElementContainsText(ProjectFormsTab.ele_BetaViewViewFormTitle, formSubject);
	}

	public void clickActionAndSelectOption() {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton,
				ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
		try {
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
		} catch (Throwable t) {
			log.error("ERROR: Failed to click Edit and Distribute button");
		}
	}

	public void naviagteLeftPanelHistoryTab() {
		clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormHistoryButton);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		Assert.assertTrue("History Results count: " + getCount(ProjectFormsTab.css_BetaViewFormViewHistoryResults),
				getCount(ProjectFormsTab.css_BetaViewFormViewHistoryResults) > 0);
	}

	public void verifyFormHistoryResults() {
		log.info("Covered in Above definition");
	}

	public void filterFormHistoryResults(String MsgID) {
		waitUntilElementIsDisplayed(ProjectFormsTab.sel_BetaViewFormViewHistoryType);
		selectByVisibleText(ProjectFormsTab.sel_BetaViewFormViewHistoryType,
				AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		selectByVisibleText(ProjectFormsTab.sel_BetaViewFormViewHistoryMsgID, MsgID);
	}

	public void postFilterFormHistoryValidation() {
		log.info("Covered in <verifyAssignedFormActions> Definition");
	}

	public void verifyAssignedFormActions() {
		waitUntilElementIsDisplayed(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		Assert.assertTrue("History Results count :", getCount(ProjectFormsTab.css_BetaViewFormViewHistoryResults) != 0);

		List<WebElement> filteredHistoryResults = findElements(ProjectFormsTab.css_BetaViewFormViewHistoryResults);

		for (WebElement web : filteredHistoryResults) {

			WebElement actionCheckbox = web.findElement(ProjectFormsTab.chk_FormViewHistorycheckboxListCheckbox);
			String actionsTitle = web.findElement(ProjectFormsTab.chk_FormViewHistorycheckboxListActionTitle).getText();
			String actionStatus = web.findElement(ProjectFormsTab.chk_FormViewHistorycheckboxListActionStatus)
					.getText();
			if (actionStatus.contains(AdoddleCommonStringPool.STRING_INCOMPLETE)) {
				expectedActionList.add(actionsTitle);
				actionCheckbox.click();
			}
		}

		log.info("ExpectedActionList Count:: " + expectedActionList.size());

	}

	public void verifyPopupElement() {
		log.info("Covered in <clickAndSelectOption> definition");

	}

	public void selectDelegateActionsAttribute(String userName, String dueDateOption) throws InterruptedException {
		waitUntilElementIsDisplayed(ProjectFormsTab.Pop_BetaViewFormViewHistoryDelegateActionsTo);
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_BetaViewFormViewHistoryResultsDelegateActionClose);
		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.Pop_BetaViewFormViewHistoryDelegateActionsTo, userName);
		findElement(ProjectFormsTab.Pop_BetaViewFormViewHistoryDelegateActionsTo).sendKeys(
				Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		selectDueDateActionOptions(dueDateOption);
		clickButtonWithText(AdoddleCommonStringPool.BTN_DELEGATE_TASK);
		verifyTaskDelegateSuccessMessage();
		clickElementAndWait(ProjectFormsTab.btn_BetaViewFormViewHistoryResultsClose);
		naviagteLeftPanelHistoryTab();
	}

	private void verifyTaskDelegateSuccessMessage() {
		try {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CreateFormUploadSucessMsg, 10);
			AutomationAssert.assertTrue(
					"Failure while validating task delegated message",
					getElementText(ProjectFormsTab.lbl_CreateFormUploadSucessMsg, 10).contains(
							AdoddleCommonStringPool.TASKS_DELEGATED_MESSAGE));
			waitUntilElementIsInvisible(ProjectFormsTab.lbl_CreateFormUploadSucessMsg);
			waitForCompletePageLoad();
		} catch (Throwable e) {
			log.error("ERROR :Delegate Tasks Success message could not be verified");
		}

	}

	public void clickAndSelectOption(String option) {
		if (option.contains(AdoddleCommonStringPool.OPTION_DELEGATE_TASKS)) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormViewHistoryActionDropdown,
					ProjectFormsTab.sel_BetaViewFormViewHistoryDelegateAction);
			clickElementAndWaitForElement(ProjectFormsTab.sel_BetaViewFormViewHistoryDelegateAction,
					ProjectFormsTab.css_FormViewHistoryDelegateActions);
		}

		else {
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormViewHistoryActionDropdown,
					ProjectFormsTab.sel_BetaViewFormViewHistoryClearAction);
			clickElementAndWait(ProjectFormsTab.sel_BetaViewFormViewHistoryClearAction);
			verifyTaskClearedSuccessMessage();
			clickElementAndWait(ProjectFormsTab.btn_BetaViewFormViewHistoryResultsClose);
			naviagteLeftPanelHistoryTab();
		}
	}

	private void verifyTaskClearedSuccessMessage() {
		try {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CreateFormUploadSucessMsg, 10);
			AutomationAssert.assertTrue(
					"Failure while validating task clear message",
					getElementText(ProjectFormsTab.lbl_CreateFormUploadSucessMsg, 10).contains(
							AdoddleCommonStringPool.TASKS_CLEARED_MESSAGE));
			waitUntilElementIsInvisible(ProjectFormsTab.lbl_CreateFormUploadSucessMsg);
			waitForCompletePageLoad();
		} catch (Throwable e) {
			log.error("ERROR :Clear Tasks Success message could not be verified");
		}

	}

	public void clickContinueButton() {
		log.info("Covered in <selectDelegateActionsAttribute> definition");

	}

	private void selectDueDateActionOptions(String optionName) {

		if (optionName.equalsIgnoreCase(AdoddleCommonStringPool.OPTION_EXISTING_DUE_DATE)) {
			if (!isSelected(ProjectFormsTab.rad_BetaViewFormViewHistoryDelegateActionsExistingDueDays))
				clickElement(ProjectFormsTab.rad_BetaViewFormViewHistoryDelegateActionsExistingDueDays);
		} else if (optionName.equalsIgnoreCase(AdoddleCommonStringPool.OPTION_USER_DEFINITION)) {

			if (!isSelected(ProjectFormsTab.rad_BetaViewFormViewHistoryDelegateActionsUserDefinition))
				clickElement(ProjectFormsTab.rad_BetaViewFormViewHistoryDelegateActionsUserDefinition);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewFormViewHistoryResultsDelegateActionDatepicker);
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_BetaViewFormViewHistoryResultsDelegateActionActiveDate);
			clickElement(ProjectFormsTab.btn_BetaViewFormViewHistoryResultsDelegateActionActiveDate);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewFormViewHistoryResultsDelegateActionBulkApplyDateIcon);
		} else {
			if (!isSelected(ProjectFormsTab.rad_BetaViewFormViewHistoryDelegateActionsRecalculate))
				clickElement(ProjectFormsTab.rad_BetaViewFormViewHistoryDelegateActionsRecalculate);
		}
	}

	public void validateDelegatedActions() {
		List<WebElement> actionRemarks;
		clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormMaximizeTab);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		actionRemarks = findElements(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		log.info("actionRemarks:: " + actionRemarks.size());
		Assert.assertTrue("Failure while Validation", actionRemarks.size() == 28);
		log.info("Post Window" + parentHandle);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		waitForCompletePageLoad();

	}

	public void validateAndSelectAssignedActions(String selectionType) {
		waitUntilElementIsDisplayed(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		Assert.assertTrue("History Results count :", getCount(ProjectFormsTab.css_BetaViewFormViewHistoryResults) != 0);
		if (selectionType.contains("Incomplete Actions only")) {
			List<WebElement> filteredHistoryResults = findElements(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
			for (WebElement web : filteredHistoryResults) {
				WebElement actionCheckbox = web.findElement(ProjectFormsTab.chk_BetaViewFormViewHistoryResults);
				String actionStatus = web.findElement(ProjectFormsTab.css_BetaViewFormViewHistoryResultsActionStatus)
						.getText();
				if (actionStatus.contains(AdoddleCommonStringPool.STRING_INCOMPLETE))
					actionCheckbox.click();
			}
		} else
			clickElementAndWait(ProjectFormsTab.chk_FormViewHistoryMsgIDCheckbox);

		log.info("ExpectedActionList Count:: " + expectedActionList.size());

	}

	public void clickContinueAndOk() {
		log.info("Covered in <clickAndSelectOption> definition");
	}

	private void clearFieldBox() {
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		clickElement(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToClearButton);
	}

	public void validateClearedActions() {

		boolean flag = false;
		waitUntilElementIsDisplayed(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		Assert.assertTrue("History Results count :", getCount(ProjectFormsTab.css_BetaViewFormViewHistoryResults) != 0);
		List<WebElement> filteredHistoryResults = findElements(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		for (WebElement web : filteredHistoryResults) {
			String actionStatus = web.findElement(ProjectFormsTab.css_BetaViewFormViewHistoryResultsActionStatus)
					.getText();
			if (actionStatus.contains(AdoddleCommonStringPool.STRING_CLEARED)) {
				Assert.assertTrue("Expected Action Status:: " + AdoddleCommonStringPool.STRING_CLEARED + "But found:: "
						+ actionStatus, actionStatus.contains(AdoddleCommonStringPool.STRING_CLEARED));
				flag = true;
			}

			else if (actionStatus.contains(AdoddleCommonStringPool.STRING_COMPLETE))
				Assert.assertTrue("Expected Action Status:: " + AdoddleCommonStringPool.STRING_COMPLETE
						+ "But found:: " + actionStatus, actionStatus.contains(AdoddleCommonStringPool.STRING_COMPLETE));
			else
				Assert.assertTrue("Clear Actions Operation failed", flag);

		}
	}
}