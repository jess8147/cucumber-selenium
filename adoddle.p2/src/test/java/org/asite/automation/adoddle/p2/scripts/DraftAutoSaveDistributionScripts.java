/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DraftAutoSaveDistributionScripts extends AdoddleCommonAppMethods {

	private String			parentHandle, testFormTitle, responseText, removedFileTitle;
	private String			file1, file2, file3;
	public static Logger	log	= AutomationLogger.getInstance().getLogger(DraftAutoSaveDistributionScripts.class);

	public void selectFormAndOpen() throws InterruptedException {
		parentHandle = getCurrentWindow();
		testFormTitle = createTestDataForm();
		collectionDataMap.put("form title", testFormTitle);
		log.info("form data :" + testFormTitle);
		searchForms(testFormTitle);
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormTitle);
	}

	public void verifyFormIsViewed() {
		waitForCompletePageLoad();
		/*if(!appBetaViewFlag) {
			waitUntilElementContainsText(ProjectFormsTab.lbl_FormDetailsHeader, testFormTitle);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormDetailsFormName).equalsIgnoreCase(testFormTitle));
		}
		else {*/
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader, testFormTitle);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_BetaViewFormDetailsFormName).equalsIgnoreCase(testFormTitle));
		//}
	}

	public void distributeSelectedForm(String distributionUser) throws InterruptedException {

		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionDistribute);
			clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionDistribute);
			sendKeys(ProjectFormsTab.txt_PopDistributeToInput, distributionUser);
			sendKeys(ProjectFormsTab.txt_PopDistributeToInput, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_PopDistributeSubjectInput, javaUtils.getRandomString(20));
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_PopDistributeButtonDistribute, ProjectFormsTab.btn_PopDistributeButtonDistribute);
		}
		else {*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionDistribute);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormActionDistribute);
			sendKeys(ProjectFormsTab.txt_BetaViewPopDistributeToInput, distributionUser);
			sendKeys(ProjectFormsTab.txt_BetaViewPopDistributeToInput, Keys.TAB);
			clickElementAndWait(ProjectFormsTab.chk_BetaViewPopDistributeToUserCheckbox);
			sendKeys(ProjectFormsTab.txt_BetaViewPopDistributeSubjectInput, javaUtils.getRandomString(20));
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BetaViewPopDistributeButtonDistribute, ProjectFormsTab.pop_BetaViewDistribution);
		//}
		
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void verifyFormIsAvailable(String user) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		AutomationAssert.verifyTrue(getCount(ProjectFormsTab.css_ProjectFormListingCount) == 1);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).equalsIgnoreCase(testFormTitle));
	}

	public void editFormAndAssignActionWithAutoSave(String action, String user1, String user2, String user3) throws InterruptedException {
		clickElementAndWait(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionReply);
			clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionReply);
			switchIframe(ProjectFormsTab.frm_createFormIframe);
		}
		else {*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			waitAndSwitchIframe(ProjectFormsTab.frm_replyFormIframe);
		//}
		
		/*if(!appBetaViewFlag) {
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, user1);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, user2);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, user3);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, Keys.TAB);
		}
		else {*/
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, user1);
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, Keys.TAB);
			clickElementAndWait(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox);
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, user2);
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, Keys.TAB);
			clickElementAndWait(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox);
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, user3);
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, Keys.TAB);
			clickElementAndWait(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox);
		//}
		
		List<WebElement> toggleActionElements = null;
		
		/*if(!appBetaViewFlag)
			toggleActionElements = findElements(ProjectFormsTab.css_CreateFormToggleActions);
		else*/
			toggleActionElements = findElements(ProjectFormsTab.css_BetaViewCreateFormToggleActions);
		
		responseText = javaUtils.getRandomString(30);
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, responseText);
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, Keys.TAB);
		waitForCompletePageLoad();

		for (WebElement e : toggleActionElements) {
			waitUtils.waitInterval(1);
			mouseHoverWebElement(e);
			e.click();
			selectByVisibleText(ProjectFormsTab.drp_BetaViewCreateFormAssignActionToUser, action);
			e.click();
			mouseHover(ProjectFormsTab.txt_CreateFormYourEditableResponse);
			waitUntilElementIsInvisible(ProjectFormsTab.drp_CreateFormAssignActionToUser);
		}

		waitUtils.waitInterval(130); /* This wait is to create auto saved draft after 2 minutes */

	}

	public void editFormAndAttachFiles(String fileName) throws InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		file1 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);
		file2 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);

		clickElementAndWait(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionReply);
			clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionReply);
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		}
		else {*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			waitAndSwitchIframe(ProjectFormsTab.frm_replyFormIframe);
		//}
		responseText = javaUtils.getRandomString(30);
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, responseText);
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, Keys.TAB);
		/*if(!appBetaViewFlag)
			clickElementAndWait(ProjectFormsTab.btn_EditDraftFormDistributeAttachment);
		else*/
			clickElementAndWait(ProjectFormsTab.btn_BetaViewEditDraftFormDistributeAttachment);
		
		getWebDriver().findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(file1.toString());
		getWebDriver().findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(file2.toString());
		/*if(!appBetaViewFlag)
			clickElementAndWait(ProjectFormsTab.btn_CreateRFIFormAttachFileSave);
		else*/
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsAttach);
		waitUtils.waitInterval(130); /* This wait is to create auto saved draft after 2 minutes */

	}

	public void editFormRemoveAndAttachFiles(String fileName) throws InterruptedException {
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, javaUtils.getRandomString(20));
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, Keys.TAB);
		sysUtils.authenticateRemoteMachine(nodeIP);
		file3 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);
		removedFileTitle = getElementText(By.cssSelector("div[id='create-form-page'] div[class='gbody'] div:nth-child(2) ul[class='grow'] li[class*='gfname']"));
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		clickElementAndWait(By.cssSelector("div[id='create-form-page'] div[class='gbody'] div:nth-child(2) ul[class='grow'] li:nth-child(1) a[title='Remove']"));
		mouseHover(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		getWebDriver().findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(file3.toString());
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave);
		waitUtils.waitInterval(125); /* This wait is to create auto saved draft after 2 minutes */
	}

	public void clickCancelButtonAndCloseWinow(String buttonText) {
		verifyElementText(ProjectFormsTab.btn_CreateFormSaveCancel, buttonText);
		clickElement(ProjectFormsTab.btn_CreateFormSaveCancel);
		switchDefault();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void clickSaveDraftButton(String buttonText) {
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateFormSaveDraft);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSaveDraft);
		acceptAlert();
		waitForCompletePageLoad();
		switchDefault();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void searchAndReOpenFormDraft(String editDraft) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		try {
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FirstFormDraftID).contains("(2)"));
		}
		catch (Throwable t) {
			log.error("draft not created");
		}
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(/*!appBetaViewFlag ? ProjectFormsTab.lbl_FormDetailsFormName:*/ProjectFormsTab.lbl_BetaViewFormDetailsFormName);
		
		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionEditDraft);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FormDetailsActionEditDraft).equalsIgnoreCase(editDraft));
			clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionEditDraft);
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		}
		else {*/
			/*clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsViewPageFormDetails, ProjectFormsTab.ele_BetaViewFormDetailsViewPageFormReply);
			clickElementAndWaitForElement(ProjectFormsTab.ele_BetaViewFormDetailsViewPageFormReply, ProjectFormsTab.lbl_BetaViewFormDetailsFormName);*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionEditDraft);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditDraft).equalsIgnoreCase(editDraft));
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditDraft);
		//}
		
		if (isDisplayed(ProjectFormsTab.txt_CreateFormYourEditableResponse))
			sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, javaUtils.getRandomString(20));

	}

	public void verifyDraftIsSaved() {
		try {
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.txt_EditDraftFormFirstResponse) + " does not equal " + responseText, getElementText(ProjectFormsTab.txt_EditDraftFormFirstResponse).equalsIgnoreCase(responseText));
		}
		catch (Throwable t) {
			AutomationAssert.verifyTrue(getValue(ProjectFormsTab.txt_EditDraftFormFirstResponse) + " does not equal " + responseText, getValue(ProjectFormsTab.txt_EditDraftFormFirstResponse).equalsIgnoreCase(responseText));
		}
	}

	public void verifyDiscardDraftButton(String buttonText) {
		AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.btn_CreateFormDiscardDraft));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.btn_CreateFormDiscardDraft).equalsIgnoreCase(buttonText));
	}

	public void clickCancelDraftButtonAndCloseWindow(String buttonText) {
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateFormSaveCancel);
		AutomationAssert.verifyTrue(buttonText.equalsIgnoreCase(getElementText(ProjectFormsTab.btn_CreateFormSaveCancel)));
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSaveCancel);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void clickDiscardDraftButton() {
		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_CreateFormDiscardDraft, ProjectFormsTab.btn_CreateFormDiscrardDraftYes);
			clickElement(ProjectFormsTab.btn_CreateFormDiscrardDraftYes);
		}
		else*/
			clickElement(ProjectFormsTab.btn_CreateFormDiscardDraft);
		acceptAlert();
	}

	public void verifyDraftIsDiscarded() {
		switchPreviousWindow(parentHandle);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		try {
			searchForms(testFormTitle);
			AutomationAssert.verifyTrue(!getElementText(ProjectFormsTab.lnk_FirstFormDraftID).contains("(2)"));
		}
		catch (Throwable t) {
			reloadPage();
			waitForCompletePageLoad();
			navigateTab(LandingPage.lnk_ProjectForms);
			searchForms(testFormTitle);
			AutomationAssert.verifyTrue(!getElementText(ProjectFormsTab.lnk_FirstFormDraftID).contains("(2)"));
		}
		clickElementAndWait(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(getCount(ProjectFormsTab.css_CreateFormResponsesTextAreas) == 0);
		/*if(!appBetaViewFlag) {
			clickElementAndWait(ProjectFormsTab.btn_FormDetailsActionDropdownButton);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FormDetailsActionEditDraft).equalsIgnoreCase("Distribute"));
		} 
		else {*/
			clickElementAndWait(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton);
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditDraft, false), !isDisplayed(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditDraft));
		//}
	}

	public void verifyReplyToFormIsDiscarded() {
		log.info("covered in previous definition");
	}

	public void verifyActionNotAssigned(String action, String user) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		AutomationAssert.verifyTrue(getCount(ProjectFormsTab.lnk_FormsListingFirstAction) == 0);
	}

	public void verifyActionAssigned(String action, String user) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FormsListingFirstAction).contains(action));
	}

	public void editResponseAndAssignAction(String actionTitle, String user) throws InterruptedException {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		clickElementAndWait(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionEditDraft);
		clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionEditDraft);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, user);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, Keys.TAB);

		List<WebElement> toggleActionElements = findElements(ProjectFormsTab.css_CreateFormToggleActions);

		for (WebElement e : toggleActionElements) {
			e.click();
			waitForCompletePageLoad();
			selectByVisibleText(ProjectFormsTab.drp_CreateFormAssignActionToUser, actionTitle);
			clickElement(ProjectFormsTab.ele_CreateFormToggleActionsClose);
		}

		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, javaUtils.getRandomString(20));
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, Keys.TAB);
		waitForCompletePageLoad();
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSaveDraft);
		acceptAlert();
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);

	}

	public void editResponseAndAssignActionWithAutoSave(String actionTitle, String user) throws InterruptedException {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		clickElementAndWait(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionEditDraft);
		clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionEditDraft);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, user);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, Keys.TAB);

		List<WebElement> toggleActionElements = findElements(ProjectFormsTab.css_CreateFormToggleActions);

		for (WebElement e : toggleActionElements) {
			e.click();
			waitForCompletePageLoad();
			selectByVisibleText(ProjectFormsTab.drp_CreateFormAssignActionToUser, actionTitle);
			clickElement(ProjectFormsTab.ele_CreateFormToggleActionsClose);
		}

		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, javaUtils.getRandomString(20));
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, Keys.TAB);
		waitForCompletePageLoad();
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSaveDraft);
		acceptAlert();
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);

	}

	public void createFormFromDraft(String user) throws InterruptedException {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		clickElementAndWait(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionEditDraft);
		clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionEditDraft);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, javaUtils.getRandomString(20));
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse, Keys.TAB);
		waitForCompletePageLoad();
		clickElementAndWait(ProjectFormsTab.btn_CreateRFIFormSave);
		switchDefault();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(globalProjectTitle);
		searchForms(testFormTitle);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormsListingFirstAction).contains("Review Draft"));
	}

	public void verifyActionsCleared(String actionTitle) {
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(globalProjectTitle);
		searchForms(testFormTitle);
		AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.lnk_FormsListingFirstAction));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormsListingFirstAction).contains(actionTitle));
	}

	public String getORIFormData() {
		List<WebElement> formCodes = findElements(ProjectFormsTab.css_FormListingFormIDs);
		List<WebElement> formTitles = findElements(ProjectFormsTab.css_FormListingFormTitles);

		Iterator<WebElement> it1 = formCodes.iterator();
		Iterator<WebElement> it2 = formTitles.iterator();

		while (it1.hasNext() && it2.hasNext()) {

			log.info("form code :" + it1.next().getText());
			log.info("form title :" + it2.next().getText());

			if (!it1.next().getText().contains("("))
				return it2.next().getText();
		}

		return null;
	}

	public String createTestDataForm() throws InterruptedException {
		String formSubject;
		/*navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(globalProjectTitle);*/
		clickElementWithText("Communications");
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_EmbeddedEmailCreateForm);
		clickElementAndWait(ProjectFormsTab.lnk_EmbeddedEmailCreateForm);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		/*if(!appBetaViewFlag) {
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_CreateRFIFormInputToField);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, System.getProperty("primary.username").toString());
			sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, Keys.TAB);
		}
		else
		{*/
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField);
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, System.getProperty("primary.username").toString());
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, Keys.TAB);
			waitUntilElementIsDisplayed(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox);
			clickElementAndWait(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox);
		//}
		
		formSubject = "AutomationTest_Form_Subject" + dateUtils.getEpoch();;
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("IE")) {
			sendKeys(ProjectFormsTab.txt_CreateFormField, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_CreateFormField, formSubject);
			sendKeys(ProjectFormsTab.txt_CreateFormField, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_CreateFormField, javaUtils.getRandomString(30));
		}
		else {
			sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, formSubject);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, javaUtils.getRandomString(20));
			sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, Keys.TAB);
			waitForCompletePageLoad();
			sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, Keys.TAB);

		}

		/*if(!appBetaViewFlag)
			clickElementAndWait(ProjectFormsTab.btn_CreateRFIFormAttachment);
		else*/
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateRFIFormAttachment);
		
		List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path"));
		/*if(!appBetaViewFlag) {
			uploadFileUsingKeys(ProjectFormsTab.btn_CreateRFIFormAttachmentsSelectFiles, fileList);
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateRFIFormAttachFileSave);
			clickElementAndWait(ProjectFormsTab.btn_CreateFormAttachFileSave);
			clickElementAndWait(ProjectFormsTab.btn_CreateRFIFormSave);
		}
		else {*/
			uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsSelectFiles, fileList);
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsAttach, ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
		//}
		
		switchDefault();
		waitForCompletePageLoad();
		return formSubject;
	}

	public void verifyFormAttachments() {
		List<WebElement> fileAttachments = new ArrayList<WebElement>();
		switchDefault();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(testFormTitle);
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		/*if(!appBetaViewFlag) {
			clickElementAndWait(ProjectFormsTab.lnk_FormDetailsAttachments);
			fileAttachments = findElements(ProjectFormsTab.css_PopAttachmentsFormAttachments);
		}
		else {*/
			waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
			clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewFormDetailsAttachments, ProjectFormsTab.pop_BetaViewFormAttachmentsAndAssociations);
			waitUntilElementCountToBe(ProjectFormsTab.css_BetaViewPopAttachmentsFormAttachments, 2);
			fileAttachments = findElements(ProjectFormsTab.css_BetaViewPopAttachmentsFormAttachments);
		//}

		for (WebElement e : fileAttachments) {
			AutomationAssert.verifyTrue("Current File: " + e.getText() + "File not expected: " + removedFileTitle, !e.getText().trim().equalsIgnoreCase(removedFileTitle.trim()));
		}

	}
}
