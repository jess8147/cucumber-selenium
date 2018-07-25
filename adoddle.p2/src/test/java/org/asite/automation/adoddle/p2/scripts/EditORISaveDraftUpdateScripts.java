/**  Testdata required for this script as follows.
     1). ORI Type form is available in Project
     2). WS Admin users have right to create ORI Form
 */

package org.asite.automation.adoddle.p2.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.Keys;

public class EditORISaveDraftUpdateScripts extends AdoddleCommonAppMethods {
	private String			formTitle, formTitleDraft, richText, parentHandle, richTextValue;
	public static Logger	log	= AutomationLogger.getInstance().getLogger(EditORISaveDraftUpdateScripts.class);

	public void createORIForm() throws InterruptedException {
		formTitle = "Automation_EditORI" + AdoddleCommonStringPool.UNDERSCORE_STRING + dateUtils.getEpoch();
		collectionDataMap.put("form title", formTitle);
		clickLinkWithTitle(AdoddleCommonStringPool.CREATE_LINK_EDITORIFORM);
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateForm, ProjectFormsTab.frm_createFormIframe);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_CERFormTitle, formTitle);
		sendKeys(ProjectFormsTab.txt_CERFormTitle, Keys.TAB);
		richTextValue = javaUtils.getRandomString(50);
		sendKeys(ProjectFormsTab.txt_CERFormRichTextBox, richTextValue);
		clickElementAndWait(ProjectFormsTab.txt_CERFormTitle);
		clickElementAndWait(ProjectFormsTab.txt_CERFormRichTextBox);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		waitUtils.waitInterval(1);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.chord(Keys.CONTROL, "c"));
		waitUtils.waitInterval(1);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.chord(Keys.CONTROL, "v"));
		waitUtils.waitInterval(1);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.ENTER);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.ENTER);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.ENTER);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.chord(Keys.CONTROL, "v"));
		waitUtils.waitInterval(1);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.ENTER);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.ENTER);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.ENTER);
		findElement(ProjectFormsTab.txt_CERFormRichTextBox).sendKeys(Keys.chord(Keys.CONTROL, "v"));
		waitUtils.waitInterval(1);

		for (int index = 0; index < 3; index++) {
			sendKeys(ProjectFormsTab.txt_EOFFormGroupCode, javaUtils.getRandomString(4));
			sendKeys(ProjectFormsTab.txt_EOFFormGroupCode, Keys.TAB);
			waitForCompletePageLoad();
		}

		clear(ProjectFormsTab.txt_EOFFormGroupCode);
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		clickElementAndWaitForElement(ProjectFormsTab.txt_EOFFormDueDatePicker, ProjectFormsTab.ele_CERFormDatePickerSelectedDate);
		clickElementAndWait(ProjectFormsTab.ele_CERFormDatePickerSelectedDate);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend);
	}

	public void verifyFormIsCreated() {
		switchDefault();
		/*
		 * waitForCompletePageLoad(); reloadPage(); waitForCompletePageLoad();
		 */
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(formTitle);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle) + " != " + formTitle, getElementText(ProjectFormsTab.lnk_FirstFormTitle).equalsIgnoreCase(formTitle));
		parentHandle = getCurrentWindow();
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_FormViewRichTextView);
		AutomationAssert.verifyTrue((richTextValue + richTextValue + richTextValue).equalsIgnoreCase(getElementText(ProjectFormsTab.lnk_FormViewRichTextView).replaceAll("\\s", "")));
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);

	}

	public void searchCreatedForm() {
		navigateEditORIForms();
		searchForms(formTitle);
	}

	public void verifyFormPermissionForUser() {
		log.info("Pre-requisites for this test; already satisfied");
	}

	public void contextClickORIForms(String formCount) {
		if (!getElementText(ProjectFormsTab.btn_FormListingStatusFilter).contains(AdoddleCommonStringPool.STATUS_CLOSED))
			searchCreatedForm();
		clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
		if (formCount.equalsIgnoreCase("single")) {
			try {
				contextClick(ProjectFormsTab.lnk_FirstFormTitle);
				waitUntilElementIsDisplayed(ProjectFormsTab.opt_FormListingContextClickEdit);
			}
			catch (Throwable t) {
				searchCreatedForm();
				contextClick(ProjectFormsTab.lnk_FirstFormTitle);
				waitUntilElementIsDisplayed(ProjectFormsTab.opt_FormListingContextClickEdit);

			}
			mouseHover(ProjectFormsTab.opt_FormListingContextClickEdit);
			waitUntilElementIsDisplayed(ProjectFormsTab.opt_FormListingContectClickEditMessage);

		}
		else if (formCount.equalsIgnoreCase("multiple")) {
			navigateEditORIForms();
			clickElementAndWait(ProjectFormsTab.chk_FormListingFirstCheckbox);
			clickElementAndWait(ProjectFormsTab.chk_FormListingSecondCheckbox);
			contextClick(ProjectFormsTab.lnk_FirstFormTitle);
			waitUntilElementIsDisplayed(ProjectFormsTab.opt_FormListingContextClickEdit);
			mouseHoverClick(ProjectFormsTab.opt_FormListingContextClickEdit);
			waitUntilElementIsDisplayed(ProjectFormsTab.opt_FormListingContectClickEditMessage);
		}

	}

	public void navigateEditORIForms() {
		reloadPage();
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(System.getProperty("global.test.project"));
	}

	public void verifyEditMessageOption(String option) {

		if (option.equalsIgnoreCase("enabled"))
			AutomationAssert.verifyTrue(isEnabled(ProjectFormsTab.opt_FormListingContectClickEditMessage));
		else
			AutomationAssert.verifyTrue("Disabled Class# " + findElement(ProjectFormsTab.opt_FormListingContectClickEditMessage).getAttribute("class"), findElement(ProjectFormsTab.opt_FormListingContectClickEditMessage).getAttribute("class").contains(option));

		reloadPage();
		waitForCompletePageLoad();

	}

	public void clickOnFormToViewTheForm() {
		parentHandle = getCurrentWindow();
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(formTitle);
		clickElementAndWait(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();

		try {
			/*if (!appBetaViewFlag) {
				waitUntilElementIsDisplayed(ProjectFormsTab.btn_FormDetailsActionDropdownButton);
				AutomationAssert.verifyTrue(!findElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton).isEnabled());
			}
			else {*/
				AutomationAssert.verifyTrue(findElements(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton).size() == 0);
			//}
		}
		catch (Throwable t) {
			log.info("Action Dropdown button disablity check in catch block");
			AutomationAssert.verifyTrue(/*!appBetaViewFlag ? findElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton).getAttribute("class").contains("disabled") :*/ findElements(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton).size() == 0);

		}
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);

	}

	public void verifyEditORIButtonIsDisabled() {
		log.info("verified in previous definition");
	}

	public void searchClosedEditORIForm() {

		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(AdoddleCommonStringPool.FOLDER_EDITORIMESSAGE);
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormListingStatusFilter, ProjectFormsTab.txt_FormListingStatusFilterSearch);
		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.txt_FormListingStatusFilterSearch, "Closed");
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.chk_FormListingStatusFilterClosed);
		clickElementAndWait(ProjectFormsTab.chk_FormListingStatusFilterClosed);
		waitUntilElementIsSelected(ProjectFormsTab.chk_FormListingStatusFilterClosed);
		clickElementAndWait(ProjectFormsTab.txt_FormListingFormSearchInput);
	}

	public void editORIForm() {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(formTitle);
		parentHandle = getCurrentWindow();
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		/*if (!appBetaViewFlag) {
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_FormDetailsActionDropdownButton);
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.btn_ControllerFormViewActionsEditORI);
			clickElementAndWaitForElement(ProjectFormsTab.btn_ControllerFormViewActionsEditORI, ProjectFormsTab.frm_createFormIframe);
			switchIframe(ProjectFormsTab.frm_createFormIframe);
		}
		else {*/
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton);
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.btn_BetaViewControllerFormViewActionsEditORI);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewControllerFormViewActionsEditORI);
		//}
	}

	public void saveORIFormAsDraft() throws InterruptedException {
		formTitleDraft = "Draft" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle;
		sendKeys(ProjectFormsTab.txt_CERFormTitle, formTitleDraft);
		sendKeys(ProjectFormsTab.txt_CERFormTitle, Keys.TAB);
		waitForCompletePageLoad();
		clickElement(findElements(ProjectFormsTab.btn_CreateFormSaveDraft).get(0));
		switchDefault();
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_LoadingProcess);
		/*if (!appBetaViewFlag) {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_FormDetailsFormName);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormDetailsFormName).contains(formTitle));
			AutomationAssert.verifyTrue(!getElementText(ProjectFormsTab.lbl_FormDetailsFormName).contains(formTitleDraft));
		}
		else {*/
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_BetaViewFormDetailsHeader);
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader, formTitle);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader), formTitle), getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader).contains(formTitle));
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader), formTitleDraft), !getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader).contains(formTitleDraft));
		//}

	}

	public void reEditORIForm() {
		/*if (!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.btn_ControllerFormViewActionsEditORI);
			clickElementAndWaitForElement(ProjectFormsTab.btn_ControllerFormViewActionsEditORI, ProjectFormsTab.btn_PopEditORIEditUsingSavedDraft);
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ProjectFormsTab.btn_PopEditORICreateNewEdit, true), isDisplayed(ProjectFormsTab.btn_PopEditORICreateNewEdit));
		}
		else {*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.btn_BetaViewControllerFormViewActionsEditORI);
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewControllerFormViewActionsEditORI, ProjectFormsTab.btn_BetaViewPopEditORIEditUsingSavedDraft);
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ProjectFormsTab.btn_BetaViewPopEditORICreateNewEdit, true), isDisplayed(ProjectFormsTab.btn_BetaViewPopEditORICreateNewEdit));
		//}
	}

	public void clickDiscardDraftButton(String buttonText) {
		/*if(!appBetaViewFlag) {
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
			clickElementAndWaitForElement(ProjectFormsTab.btn_CreateFormDiscardDraft, ProjectFormsTab.btn_CreateFormDiscrardDraftYes);
			clickElementAndWait(ProjectFormsTab.btn_CreateFormDiscrardDraftYes);
		}
		else {*/
			clickElement(ProjectFormsTab.btn_CreateFormDiscardDraft);
			acceptAlert(5);
		//}
		
		switchPreviousWindow(parentHandle);
		reloadPage();
	}

	public void verifyDraftIsDiscarded() {

		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(formTitle);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).equalsIgnoreCase(formTitle));
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.btn_ControllerFormViewActionsEditORI);
			clickElementAndWait(ProjectFormsTab.btn_ControllerFormViewActionsEditORI);
			AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.btn_PopEditORICreateNewEdit) && !isDisplayed(ProjectFormsTab.lbl_PopEditORIHeader));
		}
		else {*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.btn_BetaViewControllerFormViewActionsEditORI);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewControllerFormViewActionsEditORI);
			AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.btn_BetaViewPopEditORICreateNewEdit) && !isDisplayed(ProjectFormsTab.lbl_BetaViewPopEditORIHeader));
		//}

	}

	public void verifyPopupWithTitle(String popupTitle) {
		AutomationAssert.verifyTrue(/*!appBetaViewFlag ? isDisplayed(ProjectFormsTab.lbl_PopEditORIHeader) && getElementText(ProjectFormsTab.lbl_PopEditORIHeader).equalsIgnoreCase(popupTitle):*/isDisplayed(ProjectFormsTab.lbl_BetaViewPopEditORIHeader) && getElementText(ProjectFormsTab.lbl_BetaViewPopEditORIHeader).equalsIgnoreCase(popupTitle));
	}

	public void modifyFormDetailsClickButton(String buttonText) throws InterruptedException {
		/*if (!appBetaViewFlag)
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);*/
		richText = javaUtils.getRandomString(20);
		sendKeys(ProjectFormsTab.txt_EOFFormRichTestBox2, richText);
		sendKeys(ProjectFormsTab.txt_CERFormTitle, Keys.TAB);

		waitForCompletePageLoad();
		if (buttonText.equalsIgnoreCase("cancel")) {
			AutomationAssert.verifyTrue(getValue(ProjectFormsTab.txt_CERFormTitle).trim() + " should equal " + formTitle, getValue(ProjectFormsTab.txt_CERFormTitle).trim().equalsIgnoreCase(formTitle));
			sendKeys(ProjectFormsTab.txt_CERFormTitle, "NewEdit" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle);
			sendKeys(ProjectFormsTab.txt_CERFormTitle, Keys.TAB);
			waitForCompletePageLoad();
			clickElementAndWait(ProjectFormsTab.btn_CreateFormSaveCancel);
		}
		else if (buttonText.equalsIgnoreCase("update")) {
			AutomationAssert.verifyTrue(getValue(ProjectFormsTab.txt_CERFormTitle).trim() + " should equal " + formTitleDraft, getValue(ProjectFormsTab.txt_CERFormTitle).trim().equalsIgnoreCase(formTitleDraft));
			sendKeys(ProjectFormsTab.txt_CERFormTitle, "DraftSave" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle);
			sendKeys(ProjectFormsTab.txt_CERFormTitle, Keys.TAB);
			waitForCompletePageLoad();
			clear(ProjectFormsTab.txt_EOFFormGroupCode);
			sendKeys(ProjectFormsTab.txt_EOFFormGroupCode, Keys.TAB);
			clickElementAndWait(/*!appBetaViewFlag ? ProjectFormsTab.btn_CreateControllerFormSendButton : */ProjectFormsTab.btn_BetaViewCreateControllerFormSendButton);
		}
		switchDefault();
	}

	public void verifyDraftIsSavedToForm() {
		waitUntilElementIsDisplayed(/*!appBetaViewFlag ? ProjectFormsTab.lbl_FormDetailsFormName:*/ ProjectFormsTab.lbl_BetaViewFormDetailsHeader);
		/*if(!appBetaViewFlag)
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormDetailsFormName) + " != DraftSave" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle, getElementText(ProjectFormsTab.lbl_FormDetailsFormName).equalsIgnoreCase("DraftSave" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle));
		else {*/
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader, "DraftSave" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader) + " != contains DraftSave" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle, getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader).contains("DraftSave" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle));
		//}
		
		waitUntilElementIsDisplayed(ProjectFormsTab.lbl_FormDetailsEOFFormRichTestBox);
		waitUntilElementContainsText(ProjectFormsTab.lbl_FormDetailsEOFFormRichTestBox, richText);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormDetailsEOFFormRichTestBox) + " != " + richText, getElementText(ProjectFormsTab.lbl_FormDetailsEOFFormRichTestBox).equalsIgnoreCase(richText));
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		waitForCompletePageLoad();
		formTitle = "DraftSave" + AdoddleCommonStringPool.UNDERSCORE_STRING + formTitle;
	}

}
