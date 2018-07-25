/*  Testdata required for this script as follows.
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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.errors.AutomationErrors;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreatePackageFormP2Scripts extends AdoddleCommonAppMethods {

	private String	parentHandle1, parentHandle2;
	private String			commentingFormTitle 	= "AutoTest_Commenting20180504122505";
	private static String	packageFormTitle		= "AutoTest_Package20171017145637";
	final private static String projectTitle 		= "Automation_TTT_Comment_Form_WS";
	final private List<String>	associatedFileNames	= new ArrayList<String>();
	final private static 	Logger	log				= AutomationLogger.getInstance().getLogger(CreatePackageFormP2Scripts.class);

	public void verifyCreateFormPopup(String popupTitle) {

		/*if (!appBetaViewFlag)
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_CreatePackageFormHeader), popupTitle), getElementText(ProjectFormsTab.lbl_CreatePackageFormHeader).contains(popupTitle));
		else {*/
			if ("Respond Form".equalsIgnoreCase(popupTitle)) {
				/*waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);*/
				popupTitle = AdoddleCommonStringPool.OPTION_REPLY;
			}
			else {
				AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.pop_CreateFormWindow));
				waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
			}
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_BetaViewCreateFormWindowHeader), popupTitle), getElementText(ProjectFormsTab.lbl_BetaViewCreateFormWindowHeader).contains(popupTitle));
		//}
	}

	public void clickFormLinkAndCreateButton(String formLink) {
		clickElementWithText(formLink);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
	}

	public void enterMandatoryFields(String flag) throws InterruptedException {
		packageFormTitle = "AutoTest_Package" + dateUtils.getEpoch();
		collectionDataMap.put("Package Form", packageFormTitle);
		sendKeys(ProjectFormsTab.txt_CreatePackageFormTitle, packageFormTitle);
		sendKeys(ProjectFormsTab.txt_CreatePackageFormTitle, Keys.TAB);
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormProjectManager, 1);
		acceptAlertAndWait();
		waitUtils.waitInterval(1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormDocController, 1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormGate, 2);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		clickElementAndWaitForElement(ProjectFormsTab.img_CreatePackageFormDateButton, ProjectFormsTab.ele_DatePickerTodayDate);
		clickElementAndWait(ProjectFormsTab.ele_DatePickerTodayDate);
		selectByVisibleText(ProjectFormsTab.drp_CreatePackagePOI, AdoddleCommonStringPool.ACTION_FOR_ACCEPTANCE);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		sendKeys(ProjectFormsTab.txt_CreatePackageP6Number, javaUtils.getRandomString(4));
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		clear(ProjectFormsTab.txt_CreatePackageTaskSearch);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		sendKeys(ProjectFormsTab.txt_CreatePackageTaskSearch, AdoddleCommonStringPool.AUTOMATION);
		clickElementAndWait(ProjectFormsTab.drp_CreatePackageTask);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageTask, 1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackagePeriodOfReply, 1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageConsentType, 2);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageConsentStage, 2);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		sendKeys(ProjectFormsTab.txt_CreatePackageLocationSearch, "ALBEF");
		clickElementAndWait(ProjectFormsTab.drp_CreatePackageTask);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageLocation, 1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageLifeCycle, 1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageWorkType, 1);
		waitForCompletePageLoad();
		if (flag.equalsIgnoreCase("except"))
			log.info("status field going unsubmitted");
		else {
			waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
			mouseHoverAndClickElement(ProjectFormsTab.chk_CreatePackageStatus, ProjectFormsTab.chk_CreatePackageStatus);
			waitUntilElementIsSelected(ProjectFormsTab.chk_CreatePackageStatus);
		}
		sendKeys(ProjectFormsTab.txt_CreatePackageConsentBody, javaUtils.getRandomString(10));
		sendKeys(ProjectFormsTab.txt_CreatePackageConsentBody, Keys.TAB);
		waitForCompletePageLoad();

	}

	public void clickButtonAndCreatePackage() {
		try {
			mouseHover(ProjectFormsTab.btn_CreateFormSave);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSave, ProjectFormsTab.btn_CreateFormSave, 5);
		}
		catch (Throwable t) {
			clickElementAndWait(ProjectFormsTab.btn_CreateFormSave);
		}
	}

	public void verifyPackageCreation() {
		switchDefault();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(packageFormTitle);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle) + " should equal to " + packageFormTitle, getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle).equalsIgnoreCase(packageFormTitle));
		AdoddleScenarioMarkers.createPackageFormScriptP2Flag = true;
	}

	public void verifyPackageFormStatus(String status) {
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormListingFirstFormStatus).equalsIgnoreCase(status));
		if (status.equalsIgnoreCase(AdoddleCommonStringPool.STATUS_SUBMITTED))
			AdoddleScenarioMarkers.commentingExternalDistributionFlag = true;
	}

	public void searchForm(String formType) {
		if (formType.equalsIgnoreCase(AdoddleCommonStringPool.FORMTYPE_PACKAGE))
			searchForms(packageFormTitle);
		else if (formType.equalsIgnoreCase(AdoddleCommonStringPool.FORMTYPE_COMMENTING))
			searchForms(commentingFormTitle);
	}

	public void updatePackageFormStatus() {
		parentHandle1 = clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormID);
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute);
		clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute);
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		clickElementAndWait(ProjectFormsTab.chk_CreatePackageStatus);
		clickElement(ProjectFormsTab.btn_CreateFormSave);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
		AdoddleScenarioMarkers.packageFormSubmittedStatusFlag = true;
	}

	public void verifyFormIsNotAvailable() {
		AutomationAssert.verifyTrue(getCount(ProjectFormsTab.css_FormListingFormTitles) == 0);
	}

	public void verifyAssignedAction(String action) {

		try {
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FormsFirstAction).contains(action));
		}
		catch (Throwable t) {
			mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
			AutomationAssert.verifyTrue(getElementText(GlobalPageElements.pop_firstActionsPopOverContent) + " should contain " + action, getElementText(GlobalPageElements.pop_firstActionsPopOverContent).contains(action));
		}

		clickElementAndWait(ProjectFormsTab.txt_filterProjectForm);
		waitUntilElementIsInvisible(GlobalPageElements.pop_firstActionsPopOverContent);
	}

	public void acceptFormAndChangeStatus(String qaStatus) throws InterruptedException {
		parentHandle1 = getCurrentWindow();
		openEditFormPopup();
		selectByVisibleText(ProjectFormsTab.drp_EditPackageFormQACheck, qaStatus);
		waitForCompletePageLoad();

		if (qaStatus.equalsIgnoreCase(AdoddleCommonStringPool.STATUS_QA_NOT_ACCEPTED)) {
			sendKeys(ProjectFormsTab.txt_EditPackageFormDocControllerComment, javaUtils.getRandomString(20));
			sendKeys(ProjectFormsTab.txt_EditPackageFormDocControllerComment, Keys.TAB);
		}
		else if (qaStatus.equalsIgnoreCase(AdoddleCommonStringPool.STATUS_QA_ACCEPTED)) {
			selectByIndex(ProjectFormsTab.drp_EditPackageFormSendtoPM, 1);
		}

		waitForCompletePageLoad();
		mouseHoverAndClickElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend);
		try {
			clickElement(ProjectFormsTab.btn_CreateFormSend, 10);
		}
		catch (Throwable t) {
			log.info("click operation was successful with mousehover");
		}
		switchDefault();
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_FormDetailsActionDropdownButton);
	}

	public void verifyLaunchButtonIsAvailable(String flag) {
		if (flag.equalsIgnoreCase("yes")) {
			waitUntilElementIsPresent(ProjectFormsTab.btn_LaunchCommentingFormButton);
			reloadPage();
			waitForCompletePageLoad();
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton);
			mouseHover(ProjectFormsTab.btn_LaunchCommentingFormButton);
			AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton));
			closeCurrentWindow();
			switchPreviousWindow(parentHandle1);
		}
		else if (flag.equalsIgnoreCase("no")) {
			waitForCompletePageLoad();
			reloadPage();
			waitForCompletePageLoad();
			AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton));
			closeCurrentWindow();
			switchPreviousWindow(parentHandle1);
		}
	}

	public void verifyAndCreateRevision(String revision) throws InterruptedException {
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreatePackageMoreOption, ProjectFormsTab.lnk_CreatePackageMoreOptionAssociateDocs);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_CreatePackageMoreOptionAssociateDocs, ProjectFormsTab.pop_CreatePackageAssocFileModal);
		clickElementWithText(projectTitle);
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_BetaViewCreateFormAssociateDocsPaginationNext);
		waitUntilElementContainsText(ProjectFormsTab.lnk_BetaViewCreateFormAssociateDocsPaginationRecordCounter, "Showing");
		sendKeys(ProjectFormsTab.txt_BetaViewCreateFormAssociateDocsSearch, new CommentingFormScripts().getDocumentTitle());
		sendKeys(ProjectFormsTab.txt_BetaViewCreateFormAssociateDocsSearch, Keys.ENTER);
		waitForCompletePageLoad();
		waitUntilElementCountToBe(FilesTab.css_PopAssociateFilesCheckboxes, 5);
		log.info("Association files count: " + getCount(FilesTab.css_PopAssociateFilesCheckboxes));
		waitForCompletePageLoad();
		List<WebElement> associateFilesCheckboxes = findElements(FilesTab.css_PopAssociateFilesCheckboxes);
		for (int index = 0; index < 5; index++) {
			associateFilesCheckboxes.get(index).click();
		}
		clickElementAndWaitForElement(FilesTab.btn_CreateFormAssociateFileSave, ProjectFormsTab.chk_CreatePackageStatus);
		waitUntilElementIsDisplayed(ProjectFormsTab.lbl_EditFormDetailsPackageFormRevision);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_EditFormDetailsPackageFormRevision).equalsIgnoreCase(revision));
		/*clickElementAndWaitForElement(ProjectFormsTab.btn_CreatePackageMoreOption, ProjectFormsTab.lnk_CreatePackageMoreOptionAssociateDocs);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_CreatePackageMoreOptionAssociateDocs, ProjectFormsTab.pop_CreatePackageAssocFileModal);
		clickElementWithText(projectTitle);
		waitForCompletePageLoad();
		List<WebElement> associateFilesCheckboxes = findElements(FilesTab.css_PopAssociateFilesCheckboxes);
		waitUntilElementCountToBeMoreThan(FilesTab.css_PopAssociateFilesCheckboxes, 15);
		log.info("Association files count: " + getCount(FilesTab.css_PopAssociateFilesCheckboxes));
		waitForCompletePageLoad();
		associateFilesCheckboxes = findElements(FilesTab.css_PopAssociateFilesCheckboxes);
		for (int index = 0; index < 5; index++) {
			associateFilesCheckboxes.get(index).click();
		}*/
		mouseHover(ProjectFormsTab.chk_CreatePackageStatus);
		clickElementAndWait(ProjectFormsTab.chk_CreatePackageStatus);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
		waitForCompletePageLoad();
		reloadPage();
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(projectTitle);
		searchForms(packageFormTitle);
		verifyElementText(ProjectFormsTab.lbl_FormListingFirstFormStatus, AdoddleCommonStringPool.STATUS_SUBMITTED);
		AdoddleScenarioMarkers.packageQANotAccepedFlag = true;
	}

	public void launchCommentingForm() {
		parentHandle1 = getCurrentWindow();
		openNewFormWindow();
		parentHandle2 = getCurrentWindow();
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton);
		clickElement(ProjectFormsTab.btn_LaunchCommentingFormButton);
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitForCompletePageLoad();
	}

	public void inputAllMandatoryFieldsAndCreateForm(String reviewerFlag) {
		String			REVIEW_CO_GROUP		= ResourceHandler.loadProperty("commenting.form.rc.group2");
		String			REVIEWER_GROUP		= ResourceHandler.loadProperty("commenting.form.reviewers.group1");

		commentingFormTitle = "AutoTest_Commenting" + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_CreateCommentingFormTitle);
		sendKeys(ProjectFormsTab.txt_CreateCommentingFormTitle, commentingFormTitle);
		selectByVisibleText(ProjectFormsTab.drp_CreateCommentingFormReviewCoGroup, REVIEW_CO_GROUP);
		waitUntilElementIsInvisible(ProjectFormsTab.lbl_CreateCommentingFormReviewCoGroupError);
		AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.lbl_CreateCommentingFormReviewCoGroupError));

		if (reviewerFlag.equalsIgnoreCase("with"))
			selectByVisibleText(ProjectFormsTab.drp_CreateCommentingFormReviewerGroup, REVIEWER_GROUP);
		else
			clickElementAndWait(ProjectFormsTab.img_CreateCommentingFormRemoveReviewerGroup);

		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateFormSave);
		clickElement(ProjectFormsTab.btn_CreateFormSave);
		waitForSwitchWindow(2);
		switchPreviousWindow(parentHandle2);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
		collectionDataMap.put("Commenting Form", commentingFormTitle);
	}

	public void distributeCommentingForm(String tigUser, String taUser, String pmUser) throws InterruptedException {
		reloadPage();
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(projectTitle);
		log.info("Commenting Form Title: " + commentingFormTitle);
		searchForms(commentingFormTitle);
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		waitUntilElementIsDisplayed(By.cssSelector("div[class*='darkGreenBg']"));
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionDistribute);
		clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionDistribute);

		sendKeys(ProjectFormsTab.txt_PopDistributeToInput, tigUser);
		if (!isSelected(ProjectFormsTab.chk_SelectFirstDistributeUser))
			clickElementAndWait(ProjectFormsTab.chk_SelectFirstDistributeUser);
		selectByVisibleText(ProjectFormsTab.dd_BetaViewFormActionDistribute, AdoddleCommonStringPool.ACTION_RESPOND);
		clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
		sendKeys(ProjectFormsTab.txt_PopDistributeToInput, Keys.TAB);

		sendKeys(ProjectFormsTab.txt_PopDistributeToInput, taUser);
		if (!isSelected(ProjectFormsTab.chk_SelectFirstDistributeUser))
			clickElementAndWait(ProjectFormsTab.chk_SelectFirstDistributeUser);
		selectByVisibleText(ProjectFormsTab.dd_BetaViewFormActionDistribute, "Respond");
		clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
		sendKeys(ProjectFormsTab.txt_PopDistributeToInput, Keys.TAB);

		sendKeys(ProjectFormsTab.txt_PopDistributeToInput, pmUser);
		if (!isSelected(ProjectFormsTab.chk_SelectFirstDistributeUser))
			clickElementAndWait(ProjectFormsTab.chk_SelectFirstDistributeUser);
		selectByVisibleText(ProjectFormsTab.dd_BetaViewFormActionDistribute, "Respond");
		clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
		sendKeys(ProjectFormsTab.txt_PopDistributeToInput, Keys.TAB);

		List<WebElement> dropdownToggles = findElements(ProjectFormsTab.css_PopDistributeToggleDropdowns);

		for (WebElement e : dropdownToggles) {
			waitUtils.waitInterval(1);
			e.click();
			selectByVisibleText(ProjectFormsTab.drp_CreateFormAssignActionToUser, "Respond");
			clickElementAndWait(ProjectsTab.ele_DistributionGroupContextMenuCloseButton);
			waitUntilElementIsInvisible(ProjectsTab.ele_DistributionGroupContextMenuCloseButton);
			waitUntilElementIsInvisible(ProjectFormsTab.ele_CreateFormToggleActionsPopMenu);

		}

		clickElementAndWait(ProjectFormsTab.btn_PopDistributeButtonDistribute);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
		waitForCompletePageLoad();
	}

	public void verifyCommentingFormStatus(String formStatus) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		try {
			waitUntilElementContainsText(ProjectFormsTab.lbl_FormListingFirstFormStatus, formStatus);
		}
		catch (Throwable t) {
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_FormListingFirstFormStatus) + " should contain " + formStatus, getElementText(ProjectFormsTab.lbl_FormListingFirstFormStatus).contains(formStatus));
		}
	}

	public void verifyAssociatedFilesStatus(String fileStatus) {
		navigateTab(LandingPage.lnk_Files);

		for (String fileTitle : associatedFileNames) {
			searchFiles(fileTitle);
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FilesTabFirstFileStatus).equalsIgnoreCase(fileStatus));
		}
	}

	public void performRespondAction() {

		if (getElementText(ProjectFormsTab.lnk_FormsFirstAction).contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
			contextClick(ProjectFormsTab.lnk_FormsFirstAction);
			mouseHoverAndClickElement(ProjectFormsTab.opt_FormContextClickActions, ProjectFormsTab.opt_FormContextClickActionsForInformation);
			clickElementAndWait(By.cssSelector("div[id='modalFormsbatchforinfo'] div[class*='modal-'] a[id='btnSubmitFormsForInfo']"));
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitUntilElementContainsText(ProjectFormsTab.lnk_FormsFirstAction, AdoddleCommonStringPool.ACTION_RESPOND);
		}

		parentHandle1 = clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormsFirstAction);
		waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
		waitForCompletePageLoad();
	}

	public void clickRadioButton(String radio) throws InterruptedException {
		if (radio.equalsIgnoreCase(AdoddleCommonStringPool.COMMENTTYPE_PACKAGE)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackage);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormPackage))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackage);
		}
		else if (radio.equalsIgnoreCase(AdoddleCommonStringPool.COMMENTTYPE_DOCUMENT)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocument);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormPackage))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocument);
		}
		else if (radio.equalsIgnoreCase(AdoddleCommonStringPool.COMMENTTYPE_NOCOMMENTS)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormNoComment);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormPackage))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormNoComment);
		}
	}

	public void selectDocumentToComment(String rSeverity) {
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormDocument, 1);
		String reviewerComment = javaUtils.getRandomString(20);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormCommentBox, reviewerComment);
		selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormSeverity, rSeverity);

	}

	public void optDocumentRecommendationOption(String dRecommendation) {
		/*executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);*/
		if (dRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_AWC))
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocumentAcceptanceWithComment);
		else if (dRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_NA))
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocumentNotAccepted);
	}

	public void optPackageRecommendationOption(String pRecommendation) {
		//executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		if (pRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_AWC)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackageAcceptanceWithComment);
			sendKeys(ProjectFormsTab.txt_RespondCommentingFormPackageAcceptanceComment, javaUtils.getRandomString(20));
		}
		else if (pRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_NA)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackageNotAccepted);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormPackageNotAccepted))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackageNotAccepted);
		}
	}

	public void clickOnSendButtonToCreateComment() {
		for(int counter=0; counter < 5; counter++) {
			executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
			if(isDisplayed(ProjectFormsTab.btn_CreateFormSave))
				break;
		}
		mouseHoverAndClickElement(ProjectFormsTab.btn_CreateFormSave, ProjectFormsTab.btn_CreateFormSave);
		try{
			waitUntilElementIsInvisible(ProjectFormsTab.btn_CreateFormSave, 30);
		}
		catch(AutomationErrors t) {
			mouseHover(ProjectFormsTab.btn_CreateFormSave);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSave, ProjectFormsTab.btn_CreateFormSave);
			
		}
		waitForCompletePageLoad();
		switchDefault();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
	}

	public void verifyAllCommentsInReadOnlySection() {
		clickElementAndWait(ProjectFormsTab.lnk_RespondCommentingFormReadOnlyExpand);
	}

	public void verifyLeadReviewerCommentsInEditableSection(String leadReviewer) {
		log.info("Lead Reviewer is : "+leadReviewer);
	}

	public void verifyCommentsInReadOnlySection(String user, String userType) {
		clickElementAndWait(ProjectFormsTab.lnk_RespondCommentingFormReadOnlyExpand);
		List<WebElement> userCommentReadOnlySections = findElements(ProjectFormsTab.css_RespondCommentingFormReadOnlyUserSections);

		for (WebElement e : userCommentReadOnlySections) {
			if (e.getText().contains(user) && userType.equalsIgnoreCase("TIG Reviewer")) {
				AutomationAssert.verifyTrue(e.findElement(ProjectFormsTab.lbl_RespondCommentingFormReadOnlyHeaders).getText().contains("TIG : " + user));
			}
			else if (e.getText().contains(user) && !userType.equalsIgnoreCase("TIG Reviewer")) {
				AutomationAssert.verifyTrue(e.findElement(ProjectFormsTab.lbl_RespondCommentingFormReadOnlyHeaders).getText().contains("Reviewer : " + user));
			}

		}
	}

	public void insertNewComment() {
		clickElementAndWait(ProjectFormsTab.lnk_RespondCommentingFormReadOnlyExpand);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_RespondCommentingFormInsertComment, ProjectFormsTab.txt_RespondCommentingFormInsertedCommentInput);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormInsertedCommentInput, javaUtils.getRandomString(20));
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormInsertedCommentSeverity, 1);

	}

	public void issueFormToTA(String user, String userRole) {
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormInternalRemarks, javaUtils.getRandomString(20));
		clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormIssueToTA);
		if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormIssueToTA))
			clickElementAndWaitForElement(ProjectFormsTab.rad_RespondCommentingFormIssueToTA, ProjectFormsTab.drp_RespondCommentingFormSelectTA);
		AutomationAssert.verifyTrue(getValue(ProjectFormsTab.rad_RespondCommentingFormIssueToTA).equalsIgnoreCase(userRole));
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSelectTA, 1);
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSelectTA).contains(user));
	}

	public void selectDocumentStatuses(String status) {
		List<WebElement> docStatusDropdowns = findElements(ProjectFormsTab.css_RespondCommentingFormRequiredDocStatusDropdowns);
		for (WebElement e : docStatusDropdowns) {
			if (e.isEnabled())
				selectByVisibleText(e, status);
		}

	}

	public void userTAApprovesCommentingForm(String approveFlag) {
		//executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		mouseHover(ProjectFormsTab.rad_RespondCommentingFormTAApproveYes);
		clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormTAApproveYes);
		AutomationAssert.verifyTrue(getValue(ProjectFormsTab.rad_RespondCommentingFormTAApproveYes).equalsIgnoreCase(approveFlag));
	}

	public void sendCommentingFormToUser(String toUser) {
		if (toUser.equalsIgnoreCase("RFI Bloggs")) {
			selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSendToPM, 1);
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSendToPM).contains(toUser));
		}
		else if (toUser.equalsIgnoreCase("Auto Test")) {
			clickElementAndWait(ProjectFormsTab.drp_RespondCommentingFormSendToContractor);
			List<WebElement> optionElements = findElement(ProjectFormsTab.drp_RespondCommentingFormSendToContractor).findElements(By.tagName("option"));
			for (WebElement option : optionElements) {
				if (option.getText().contains(toUser)) {
					log.info("Option Text: " + option.getText());
					option.click();
					break;
				}
			}

			/*
			 * selectByIndex( ProjectFormsTab.drp_RespondCommentingFormSendToContractor, 1);
			 */
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSendToContractor).contains(toUser));
		}
	}

	public void pmAcceptsFormComments(String flag) {
		//executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		mouseHover(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsYes);
		clickElementAndWaitForElement(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsYes, ProjectFormsTab.txt_RespondCommentingFormPMComments);
		if (isDisplayed(ProjectFormsTab.drp_RespondCommentingFormPMPackageStatus))
			selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormPMPackageStatus, AdoddleCommonStringPool.STATUS_ACCEPTED_WITH_COMMENTS);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormPMComments, AdoddleCommonStringPool.STATUS_ACCEPTED_WITH_COMMENTS);
		AutomationAssert.verifyTrue(getValue(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsYes).equalsIgnoreCase(flag));

	}

	public void specifyCommentAndSeverity(String lSeverity) {
		String leadReviewerComment = javaUtils.getRandomString(20);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormCommentBox, leadReviewerComment);
		selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormSeverity, lSeverity);
	}

	/*** Common Methods ***/

	public void openEditFormPopup() {
		openNewFormWindow();
		clickElementAndWait(ProjectFormsTab.btn_FormDetailsActionDropdownButton);
		if (isDisplayed(ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute))
			clickElementAndWait(ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute);
		else if (isDisplayed(ProjectFormsTab.lnk_FormDetailsActionDistribute))
			clickElementAndWaitForElement(ProjectFormsTab.lnk_FormDetailsActionDistribute, ProjectFormsTab.pop_CreateFormWindow);
		// switchIframe(ProjectFormsTab.frm_createFormIframe);

	}

	private void openNewFormWindow() {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormID);
		waitForCompletePageLoad();

	}
}
