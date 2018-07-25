/**  Testdata required for this script as follows.
     1).  Automation_TTT_Comment_Form_WS  workspace is available.
     2).  Users with TTT Roles and have proxy added to Auto Test (Contractor PM).
          -  Technical Authoriser -> TC Bloggs
          -  Reviewer -> PA Builder
          -  Document Controller -> Automation UK
          -  ReviewCoordinator -> Auto RFI
          -  TIG -> PA Bloggs
          -  Contractor PM -> Auto Test
          -  Lead Reviewer -> RFI Builder
          -  TTT Project Managger -> RFI Bloggs
    3).  Apps Settings applied for below forms. 
         1. Contractor Data Form
         2. Task Information Delivery Plan
         3. Submission Package
         4. Commenting Form
         5. WBS and Task Definition
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CommentingFormScripts extends AdoddleCommonAppMethods {

	private String				formTitle, parentHandle1, parentHandle2, packageFormTitle, commentingTitle, fileUploadFolderTitle;
	private float				currentVersion;
	private List<WebElement>	docStatusDropdowns	= new LinkedList<WebElement>();
	private String				REVIEW_CO_GROUP		= "RC fisrt user with Respond action";
	private String				REVIEWER_GROUP		= "Reviewers Group1";
	static  String				docTitle			=  null;
	public static Logger		log					= AutomationLogger.getInstance().getLogger(CommentingFormScripts.class);

	public void searchCommentingFormWithAction(String actionTitle) {
		filterFormWithStatus(AdoddleCommonStringPool.STATUS_REVIEW_IN_PROGRESS);
		filterFormWithIncompleteActionToCurrentUser(actionTitle);
		formTitle = getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		searchForms(formTitle);
	}

	public void respondActionAndAssignTo(String user) {
		parentHandle1 = getCurrentWindow();
		searchForms(formTitle);
		checkAndPerformForInformation();
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction).contains(AdoddleCommonStringPool.ACTION_RESPOND));
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FormListingFirstFormAction, ProjectFormsTab.frm_createFormIframe);
		switchIframe(ProjectFormsTab.frm_createFormIframe);

		if (!isSelected(ProjectFormsTab.rad_RespondCommentingFormFirstPackageRecommendationPM)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormFirstPackageRecommendationPM);
			sendKeys(ProjectFormsTab.txt_RespondCommentingFormCommentBox, javaUtils.getRandomString(30));
			selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSeverity, 1);
		}
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormInternalRemarks, javaUtils.getRandomString(30));
		if (!user.equalsIgnoreCase("RFI Bloggs")) {
			executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormIssueToTA);
			selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSelectTA, 1);
		}
		else if (user.equalsIgnoreCase("RFI Bloggs")) {
			executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormIssueToPM);
			selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSendToPM2, 1);
		}

		waitForCompletePageLoad();
		docStatusDropdowns = findElements(ProjectFormsTab.css_RespondCommentingFormRequiredDocStatusDropdowns);
		for (WebElement e : docStatusDropdowns) {
			if (e.isEnabled())
				selectByVisibleText(e, AdoddleCommonStringPool.RECOMMENDATION_AWC);
		}
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
	}

	public void respondCommentingWithStatus(String user, String status) {
		searchForms(formTitle);
		checkAndPerformForInformation();
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction).contains(AdoddleCommonStringPool.ACTION_RESPOND));
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FormListingFirstFormAction, ProjectFormsTab.frm_createFormIframe);
		switchIframe(ProjectFormsTab.frm_createFormIframe);
		if (user.equalsIgnoreCase("TC Bloggs")) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormTAApproveNo);
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_RespondCommentingFormTARemarks);
			sendKeys(ProjectFormsTab.txt_RespondCommentingFormTARemarks, "Not Approved");
		}
		else if (user.equalsIgnoreCase("RFI Bloggs")) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsNo);
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_RespondCommentingFormPMReason);
			sendKeys(ProjectFormsTab.txt_RespondCommentingFormPMReason, "Not Approved");
		}

		docStatusDropdowns = findElements(ProjectFormsTab.css_RespondCommentingFormRequiredDocStatusDropdowns);
		for (WebElement e : docStatusDropdowns) {
			if (e.isEnabled())
				selectByVisibleText(e, "Not accepted");
		}

		clickElementAndWait(ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
	}

	public void enterMandatoryFields() throws InterruptedException {
		packageFormTitle = "AutoTest_Package" + dateUtils.getEpoch();

		sendKeys(ProjectFormsTab.txt_CreatePackageFormTitle, packageFormTitle);
		sendKeys(ProjectFormsTab.txt_CreatePackageFormTitle, Keys.TAB);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormProjectManager, 1);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormDocController, 1);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormGate, 2);
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.img_CreatePackageFormDateButton, ProjectFormsTab.ele_DatePickerTodayDate);
		clickElementAndWait(ProjectFormsTab.ele_DatePickerTodayDate);
		selectByVisibleText(ProjectFormsTab.drp_CreatePackagePOI, AdoddleCommonStringPool.FOR_ACCEPTANCE);
		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.txt_CreatePackageP6Number, javaUtils.getRandomString(4));
		clear(ProjectFormsTab.txt_CreatePackageTaskSearch);
		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.txt_CreatePackageTaskSearch, AdoddleCommonStringPool.AUTOMATION);
		clickElementAndWait(ProjectFormsTab.drp_CreatePackageTask);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageTask, 1);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackagePeriodOfReply, 1);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageConsentType, 2);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageConsentStage, 2);
		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.txt_CreatePackageConsentBody, javaUtils.getRandomString(10));
		sendKeys(ProjectFormsTab.txt_CreatePackageConsentBody, Keys.TAB);
		waitForCompletePageLoad();
		clear(ProjectFormsTab.txt_CreatePackageLocationSearch);
		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.txt_CreatePackageLocationSearch, "ALBEF");
		clickElementAndWait(ProjectFormsTab.drp_CreatePackageTask);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageLocation, 1);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageLifeCycle, 1);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageWorkType, 1);
		waitForCompletePageLoad();
		clickElementAndWait(ProjectFormsTab.chk_CreatePackageStatus);

	}

	public void verifyFormStatus(String formStatus) {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(formTitle);
		Assert.assertTrue(getElementText(ProjectFormsTab.lbl_FormListingFirstFormStatus).equalsIgnoreCase(formStatus));
	}

	public void checkAndPerformForInformation() {
		while (getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction).contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
			performForInformation();
		}
	}

	public void filterPackageFormWithStatus(String status, String qaState, String recipient) {
		filterFormWithStatus(status);
		filterFormWithQAStatus(qaState);
		filterFormWithRecipient(recipient);
	}

	public void verifyPackageForms(String status) {
		Assert.assertTrue(getElementText(ProjectFormsTab.lbl_FormListingFirstFormStatus).contains(status));
	}

	public void uploadMultipleDocuments(String folderTitle) throws IOException, InterruptedException {
		fileUploadFolderTitle = folderTitle;
		clickElementWithText(fileUploadFolderTitle);
		sysUtils.authenticateRemoteMachine(nodeIP);
		setDocumentTitle(javaUtils.getRandomString(10)+epoch);
		uploadDocuments(null, 5, null, Arrays.asList(docTitle), false, 1, Arrays.asList(AdoddleCommonStringPool.FOR_ACCEPTANCE), Arrays.asList(AdoddleCommonStringPool.TRIPLE_DASH), null, null);
	}

	public void submitPackageForm(String status) {
		parentHandle1 = getCurrentWindow();
		packageFormTitle = getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		openFormView();
		waitUntilElementIsDisplayed(ProjectFormsTab.lbl_FormDetailsPackageFormRevision);
		currentVersion = Float.parseFloat(getElementText(ProjectFormsTab.lbl_FormDetailsPackageFormRevision).trim());
		System.out.println("Current Package Revision# " + currentVersion);
		openFormInEditMode();
		clickElementAndWait(ProjectFormsTab.chk_EditCommentingFormStatusSubmitted);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
	}

	public void verifyPackageFormRevision() {
		Assert.assertTrue(Float.parseFloat(getElementText(ProjectFormsTab.lbl_FormDetailsPackageFormRevision).trim()) == (currentVersion + 1));
		closeWindowAndSwitchPreviousWindow();
	}

	public void changeQACheck(String qaStatus) {
		searchForms(packageFormTitle);
		openFormView();
		if (!isDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton)) {
			openFormInEditMode();
			selectByVisibleText(ProjectFormsTab.drp_EditPackageFormQACheck, qaStatus);
			selectByIndex(ProjectFormsTab.drp_EditPackageFormSendtoPM, 1);
			waitForCompletePageLoad();
			clickElementAndWait(ProjectFormsTab.btn_CreateFormSend);
		}
	}

	public void verifyLaunchCommentingButton() {
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton);
	}

	public void launchCommentingForm(String buttonText) {
		parentHandle2 = getCurrentWindow();
		clickElementAndWait(ProjectFormsTab.btn_LaunchCommentingFormButton);
		waitForSwitchWindow(3);
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitForCompletePageLoad();
	}

	public void selectValidReviewerGroups() {
		commentingTitle = "AutoTest_Commenting" + dateUtils.getEpoch();
		sendKeys(ProjectFormsTab.txt_CreateCommentingFormTitle, commentingTitle);
		selectByVisibleText(ProjectFormsTab.drp_CreateCommentingFormReviewCoGroup, REVIEW_CO_GROUP);
		waitForCompletePageLoad();
		selectByVisibleText(ProjectFormsTab.drp_CreateCommentingFormReviewerGroup, REVIEWER_GROUP);
		waitForCompletePageLoad();
	}

	public void verifyCommentFormRevision() {
		switchPreviousWindow(parentHandle2);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
		log.info("to be done");
	}

	public void respondCommentingForm() {
		openRespondPopup();
		clickElementAndWaitForElement(ProjectFormsTab.rad_RespondCommentingFormPackage, ProjectFormsTab.txt_RespondCommentingFormCommentBox);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormCommentBox, javaUtils.getRandomString(20));
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSeverity, 1);
		clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormIssueToTA);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormInternalRemarks, javaUtils.getRandomString(20));
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSelectTA, 1);
		waitForCompletePageLoad();
		docStatusDropdowns = findElements(ProjectFormsTab.css_RespondCommentingFormRequiredDocStatusDropdowns);
		for (WebElement e : docStatusDropdowns) {
			if (e.isEnabled())
				selectByVisibleText(e, "Accepted");
		}
		clickElement(ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
	}

	public void replyTheCommentingForm() {
		searchForms(commentingTitle);
		openFormView();
		openFormInEditMode();

	}

	public void verifyAlertMessage() {
		waitUntilElementIsDisplayed(ProjectFormsTab.lbl_RespondCommentingFormReviewerAlertMessage);
		Assert.assertTrue(getElementText(ProjectFormsTab.lbl_RespondCommentingFormReviewerAlertMessage).contains(AdoddleCommonStringPool.FORM_RESPOND_AUTHORIZATION_MESSAGE));
	}

	/*** Common Methods ***/

	public void filterFormWithQAStatus(String qaStatus) {
		clickElementWithText("POW");
		clickLinkWithTitle("02 Submission Package");
		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.FILTER_DC_RES_STATUS);
		waitForCompletePageLoad();
		clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
		sendKeys(ProjectFormsTab.txt_GlobalListingCreatedFilterDCResStatus, qaStatus);
		clickElementAndWait(ProjectFormsTab.txt_FormListingFormSearchInput);
	}

	public void filterFormWithRecipient(String recipient) {
		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.FILTER_RECIPIENT_NAME);
		waitForCompletePageLoad();
		clickElementAndWaitForElement(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox, ProjectFormsTab.drp_GlobalListingCreatedFilterRecipientName);
		clickElementAndWaitForElement(ProjectFormsTab.drp_GlobalListingCreatedFilterRecipientName, ProjectFormsTab.txt_GlobalListingCreatedFilterRecipientNameSearch);
		sendKeys(ProjectFormsTab.txt_GlobalListingCreatedFilterRecipientNameSearch, recipient);
		waitForCompletePageLoad();
		clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
		clickElementAndWait(ProjectFormsTab.txt_FormListingFormSearchInput);

	}

	public void performForInformation() {
			contextClick(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
			mouseHoverAndClickElement(ProjectFormsTab.opt_FormContextClickActions, ProjectFormsTab.opt_FormContextClickActionsForInformation);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_PopActionsForInformationOk, ProjectFormsTab.btn_PopActionsForInformationOk);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitUntilElementContainsText(ProjectFormsTab.lnk_FormsFirstAction, AdoddleCommonStringPool.ACTION_RESPOND);
	}

	public void openFormView() {
		clickElementAndWait(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(ProjectFormsTab.pop_CommentingFormViewPopover);
	}

	public void closeWindowAndSwitchPreviousWindow() {
		closeCurrentWindow();
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle1);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
	}

	public void openFormInEditMode() {
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute, ProjectFormsTab.frm_createFormIframe);
		switchIframe(ProjectFormsTab.frm_createFormIframe);
	}

	public void openRespondPopup() {
		searchForms(commentingTitle);
		checkAndPerformForInformation();
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FormsListingFirstAction, ProjectFormsTab.frm_createFormIframe);
		switchIframe(ProjectFormsTab.frm_createFormIframe);
	}
	
	@SuppressWarnings("static-access")
	public void setDocumentTitle(String title) {
		this.docTitle = title;
	}
	
	public String getDocumentTitle() {
		return docTitle;
	}
	
}
