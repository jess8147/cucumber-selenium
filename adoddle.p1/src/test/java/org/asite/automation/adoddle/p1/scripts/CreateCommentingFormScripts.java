package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateCommentingFormScripts extends AdoddleCommonAppMethods {

	private static File				commentingZip, packageFormXSN, commentReportFile;
	private static String			commentingFormTitle		= "AutoTest_Commenting20180509184234", parentHandle1, parentHandle2;
	private static String			packageFormTitle		= "AutoTest_Package20170811153601";
	final private List<String>		fileArrayList			= new ArrayList<String>();
	final private List<String>		associatedFileNames		= new ArrayList<String>();
	final private static Logger		log						= AutomationLogger.getInstance().getLogger(CreateCommentingFormScripts.class);
	final private static String		docTitle				= new JavaUtils().getRandomString(15);

	public void downloadCommentingFormFiles() throws IOException, InterruptedException {
		clickElement(ProjectFormsTab.lnk_svnHTMLAppsCommentingZipFile);
		commentingZip = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + "CommentingZip" + epoch + ".zip");
		autoItUtils.downloadAutoIt(commentingZip.toString(), nodeIP);
		sysUtils.waitUntilFileIsDownloaded(commentingZip);
	}

	public void downloadPackageFormXSN() throws IOException, InterruptedException {
		navigateURL(ResourceHandler.loadProperty("adoddle.svn.html.ttt.xsn.url"));
		clickElement(ProjectFormsTab.lnk_svnHTMLAppsPackageFormXSN);
		packageFormXSN = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + "PackagingXSN_" + epoch + AdoddleCommonStringPool.XSN_EXTENSION);
		autoItUtils.downloadAutoIt(packageFormXSN.toString(), nodeIP);
		sysUtils.waitUntilFileIsDownloaded(packageFormXSN);
	}

	public void verifyCommentingZipIsDownloaded() {
		AutomationAssert.verifyTrue(commentingZip.exists());
	}

	public void verifyPackageXSNIsDownloaded() {
		AutomationAssert.verifyTrue(packageFormXSN.exists());
		tearDown();
	}

	public void uploadTTTZipFile(String project) {
		navigateTab(LandingPage.lnk_Projects);
		searchProjects(project);
		contextClick(ProjectsTab.lnk_ProjectsFirstProject);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditAppSettings);
		waitUntilElementIsDisplayed(ProjectsTab.txt_PopManageAppsModalSearchInput);
		sendKeys(ProjectsTab.txt_PopManageAppsModalSearchInput, "03 Commenting");
		sendKeys(ProjectsTab.txt_PopManageAppsModalSearchInput, Keys.ENTER);
		clickElementAndWaitForElement(ProjectsTab.img_PopManageAppsModelFilteredAppEditImage, ProjectsTab.pop_EditAppSettings);
		findElement(ProjectsTab.btn_PopEditAppSettingsXsnBrowseButton).sendKeys(commentingZip.toString());
		waitUntilElementIsDisplayed(ProjectsTab.btn_PopEditAppSettingsXsnUpload);
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditAppSettingsXsnUpload, ProjectsTab.Pop_TemplateUploadWaitPopup);
		AutomationAssert.verifyTrue("Expected #" + commentingZip.toString().split("\\\\")[6] + "\n Actual :" + getElementText(ProjectsTab.lnk_PopEditAppSettingsDownloadFormZip), getElementText(ProjectsTab.lnk_PopEditAppSettingsDownloadFormZip).equalsIgnoreCase(commentingZip.toString().split("\\\\")[6]));
		clickElementAndWait(ProjectsTab.btn_PopEditAppSettingsSave);
	}

	public void uploadPackageFormXSN() {
		waitUntilElementIsDisplayed(ProjectsTab.txt_PopManageAppsModalSearchInput);
		sendKeys(ProjectsTab.txt_PopManageAppsModalSearchInput, "02 Submission Package");
		clickElementAndWaitForElement(ProjectsTab.img_PopManageAppsModelFilteredAppEditImage, ProjectsTab.pop_EditAppSettings);
		findElement(ProjectsTab.btn_PopEditAppSettingsXsnBrowseButton).sendKeys(packageFormXSN.toString());
		waitUntilElementIsDisplayed(ProjectsTab.btn_PopEditAppSettingsXsnUpload);
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditAppSettingsXsnUpload, ProjectsTab.Pop_TemplateUploadWaitPopup);
		AutomationAssert.verifyTrue("Expected #" + packageFormXSN.toString().split("\\\\")[6] + "\n Actual :" + getElementText(ProjectsTab.lnk_PopEditAppSettingsDownloadFormXSN), getElementText(ProjectsTab.lnk_PopEditAppSettingsDownloadFormXSN).equalsIgnoreCase(packageFormXSN.toString().split("\\\\")[6]));
		clickElementAndWait(ProjectsTab.btn_PopEditAppSettingsSave);
	}

	public void uploadMultipleDocuments(String folderTitle) throws InterruptedException {
		clickElementWithText(folderTitle);
		clickElementAndWait(FilesTab.btn_AddFiles);
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (int index = 0; index < 5; index++) {
			String fileName = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);
			fileArrayList.add(fileName);
			waitUtils.waitInterval(1);
		}
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileArrayList);
		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		sendKeys(FilesTab.txt_PopUploadHeaderDocTitle, docTitle);
		selectByVisibleText(FilesTab.drp_PopUploadHeaderPurposeOfIssue, AdoddleCommonStringPool.FOR_ACCEPTANCE);
		selectByVisibleText(FilesTab.drp_PopUploadHeaderStatus, AdoddleCommonStringPool.TRIPLE_DASH);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWaitForElement(FilesTab.btn_PopUploadApplytoAll, FilesTab.btn_PopUploadFileDistributeUpload);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopUploadFileDistributeUpload, GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void verifyCreateFormPopup(String popupTitle) {
		/*AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.pop_CreateFormWindow));*/
		if(! popupTitle.equalsIgnoreCase("Respond Form"))
			switchIframe(ProjectFormsTab.frm_createFormIframe);

		/*if(!appBetaViewFlag)
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_CreatePackageFormHeader), popupTitle), getElementText(ProjectFormsTab.lbl_CreatePackageFormHeader).contains(popupTitle));
		else {*/
		if(popupTitle.equalsIgnoreCase("Respond Form"))
			popupTitle = AdoddleCommonStringPool.OPTION_REPLY;
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_BetaViewCreateFormWindowHeader), popupTitle), getElementText(ProjectFormsTab.lbl_BetaViewCreateFormWindowHeader).contains(popupTitle));
		//}
	}

	public void clickFormLinkAndCreateButton(String formLink) {
		clickElementWithText(formLink);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
	}

	public void enterMandatoryFields() throws InterruptedException {
		String	projectTitle = ResourceHandler.loadProperty("commenting.form.test.project");
		packageFormTitle = "AutoTest_Package" + dateUtils.getEpoch();
		collectionDataMap.put("Package Form", packageFormTitle);
		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_CreatePackageMoreOption, ProjectFormsTab.lnk_CreatePackageMoreOptionAssociateDocs);
			clickElementAndWaitForElement(ProjectFormsTab.lnk_CreatePackageMoreOptionAssociateDocs, ProjectFormsTab.pop_CreatePackageAssocFileModal);
			clickElementWithText(projectTitle);
			sendKeys(ProjectFormsTab.txt_PopCreateFormAssocFileSearch, docTitle);
			sendKeys(ProjectFormsTab.txt_PopCreateFormAssocFileSearch, Keys.ENTER);
			List<WebElement> associateFilesCheckboxes = findElements(FilesTab.css_PopAssociateFilesCheckboxes);
			for(WebElement e: associateFilesCheckboxes)
				e.click();
			clickElementAndWaitForElement(FilesTab.btn_CreateFormAssociateFileSave, FilesTab.btn_AssociateDocListModelSave);
			clickElementAndWait(FilesTab.btn_AssociateDocListModelSave);

		}
		else {*/
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateFormMoreOptions, ProjectFormsTab.lnk_BetaViewCreateFormMoreOptionsAssociateDocs);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewCreateFormMoreOptionsAssociateDocs, ProjectFormsTab.txt_BetaViewCreateFormAssociateDocsSearch);
		clickElementWithText(projectTitle);
		sendKeys(ProjectFormsTab.txt_BetaViewCreateFormAssociateDocsSearch, docTitle);
		sendKeys(ProjectFormsTab.txt_BetaViewCreateFormAssociateDocsSearch, Keys.ENTER);
		waitUntilElementCountToBe(ProjectFormsTab.css_BetaViewCreateFormAssociateDocsListingCheckBoxes, 5);
		for(WebElement e: findElements(ProjectFormsTab.css_BetaViewCreateFormAssociateDocsListingCheckBoxes))
			e.click();
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BetaViewPopCreateFormAssociateDocsAssociate, ProjectFormsTab.btn_BetaViewPopCreateFormAssociateDocsAssociate);

		//}
		sendKeys(ProjectFormsTab.txt_CreatePackageFormTitle, packageFormTitle);
		sendKeys(ProjectFormsTab.txt_CreatePackageFormTitle, Keys.TAB);
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormProjectManager, 1);
		acceptAlertAndWait();
		waitUtils.waitInterval(5);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormDocController, 1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_CreatePackageFormGate, 2);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		/*if(!appBetaViewFlag)
			clickElementAndWaitForElement(ProjectFormsTab.img_CreatePackageFormDateButton, ProjectFormsTab.ele_DatePickerTodayDate);
		else*/
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreatePackageFormDateButton, ProjectFormsTab.ele_DatePickerTodayDate);
		clickElementAndWait(ProjectFormsTab.ele_DatePickerTodayDate);
		selectByVisibleText(ProjectFormsTab.drp_CreatePackagePOI, AdoddleCommonStringPool.FOR_ACCEPTANCE);
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
		clear(ProjectFormsTab.txt_CreatePackageLocationSearch);
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
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		clickElementAndWait(ProjectFormsTab.chk_CreatePackageStatus);
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
			if(isDisplayed(ProjectFormsTab.btn_CreateFormSave))
				clickElementAndWait(ProjectFormsTab.btn_CreateFormSave);
		}
	}

	public void verifyPackageCreation() {
		switchDefault();
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(packageFormTitle);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle) + " should equal to " + packageFormTitle, getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle).equalsIgnoreCase(packageFormTitle));
	}

	public void searchForm(String formType) {
		if (formType.equalsIgnoreCase(AdoddleCommonStringPool.FORMTYPE_PACKAGE))
			searchForms(packageFormTitle);
		else if (formType.equalsIgnoreCase(AdoddleCommonStringPool.FORMTYPE_COMMENTING))
			searchForms(commentingFormTitle);
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

	public void acceptFormAndChangeStatus(String qaAcceptedStatus) {
		parentHandle1 = getCurrentWindow();
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		/*if(!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_FormDetailsActionDropdownButton, ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute);
			clickElementAndWaitForElement(ProjectFormsTab.lnk_FormDetailsActionEditAndDistribute, ProjectFormsTab.pop_CreateFormWindow);
			switchIframe(ProjectFormsTab.frm_createFormIframe);

		}
		else {*/
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute, ProjectFormsTab.btn_BetaViewCreateFormSave);
		//}
		selectByVisibleText(ProjectFormsTab.drp_EditPackageFormQACheck, qaAcceptedStatus);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByIndex(ProjectFormsTab.drp_EditPackageFormSendtoPM, 1);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSave);
		switchDefault();
	}

	public void verifyLaunchButtonIsAvailable() {
		try {
			waitUntilElementIsPresent(ProjectFormsTab.btn_LaunchCommentingFormButton, 30);
		}
		catch (AutomationErrors e) {
			reloadPage();
			waitUntilElementIsPresent(ProjectFormsTab.btn_LaunchCommentingFormButton);
		}
		mouseHover(ProjectFormsTab.btn_LaunchCommentingFormButton);
		AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton));
		AdoddleScenarioMarkers.createCommentingFormP1Flag = true;
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
	}

	public void launchCommentingForm() {
		parentHandle1 = getCurrentWindow();
		System.out.println("First Window Title# " + getWebDriver().getTitle());
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		parentHandle2 = getCurrentWindow();
		reloadPage();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_LaunchCommentingFormButton);
		clickElement(ProjectFormsTab.btn_LaunchCommentingFormButton);
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitForCompletePageLoad();
	}

	public void inputAllMandatoryFieldsAndCreateForm() {
		String	RCGroup1 = ResourceHandler.loadProperty("commenting.form.rc.group1");
		String	RCGroup2 = ResourceHandler.loadProperty("commenting.form.rc.group2");
		String	RGroup1	 = ResourceHandler.loadProperty("commenting.form.reviewers.group1");

		commentingFormTitle = "AutoTest_Commenting" + dateUtils.getEpoch();
		collectionDataMap.put("Commenting Form", commentingFormTitle);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_CreateCommentingFormTitle);
		sendKeys(ProjectFormsTab.txt_CreateCommentingFormTitle, commentingFormTitle);
		selectByVisibleText(ProjectFormsTab.drp_CreateCommentingFormReviewCoGroup, RCGroup1);
		waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CreateCommentingFormReviewCoGroupError);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormReviewCoGroupError).equalsIgnoreCase(AdoddleCommonStringPool.COMMENTING_FORM_EMPTY_REVIEW_COGROUP_ERROR));
		selectByVisibleText(ProjectFormsTab.drp_CreateCommentingFormReviewCoGroup, RCGroup2);
		waitUntilElementIsInvisible(ProjectFormsTab.lbl_CreateCommentingFormReviewCoGroupError);
		AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.lbl_CreateCommentingFormReviewCoGroupError));
		selectByVisibleText(ProjectFormsTab.drp_CreateCommentingFormReviewerGroup, RGroup1);
		waitForCompletePageLoad();

		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormReviewerActionDays).trim().equalsIgnoreCase(AdoddleCommonStringPool.LABEL_REVIEWER_ACTION_DAYS));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormReviewerActionDaysValue).trim().equalsIgnoreCase("20"));

		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormRCActionDays).trim().equalsIgnoreCase(AdoddleCommonStringPool.LABEL_RC_ACTION_DAYS));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormRCActionDaysValue).trim().equalsIgnoreCase("23"));

		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormTAActionDays).trim().equalsIgnoreCase(AdoddleCommonStringPool.LABEL_TA_ACTION_DAYS));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormTAActionDaysValue).trim().equalsIgnoreCase("1"));

		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormPMActionDays).trim().equalsIgnoreCase(AdoddleCommonStringPool.LABEL_PM_ACTION_DAYS));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateCommentingFormPMActionDaysValue).trim().equalsIgnoreCase("3"));

		if(!isSelected(ProjectFormsTab.chk_DisplayFormPrintViewAfterSaving))
			clickElementAndWait(ProjectFormsTab.chk_DisplayFormPrintViewAfterSaving);

		clickElementAndWait(ProjectFormsTab.btn_CreateFormSave);
		waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CreateCommentingFormViewHolderFormTitle);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lbl_CreateCommentingFormViewHolderFormTitle), commentingFormTitle), getElementText(ProjectFormsTab.lbl_CreateCommentingFormViewHolderFormTitle).equalsIgnoreCase(commentingFormTitle));
		closeCurrentWindow();
		waitForSwitchWindow(2);
		switchPreviousWindow(parentHandle2);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
	}

	public void verifyCommentingFormStatus(String formStatus) {
		navigateTab(LandingPage.lnk_ProjectForms);
		for (int index = 0; index < 3; index++) {
			searchForm(commentingFormTitle);
			try {
				waitUntilElementContainsText(ProjectFormsTab.lbl_FormsListingFirstStatus, formStatus, 10);
				break;
			}
			catch (Throwable t) {
				log.info("INFO: Retrying for commenting form status change to " + formStatus);
			}
		}
	}

	public void verifyAssociatedFilesStatus(String fileStatus) {
		navigateTab(LandingPage.lnk_Files);
		for (String fileTitle : associatedFileNames) {
			searchFiles(fileTitle);
			AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_DocListingFirstStatus).equalsIgnoreCase(fileStatus));
		}
	}

	public void performRespondAction() {
		parentHandle1 = getCurrentWindow();
		if (getElementText(ProjectFormsTab.lnk_FormsFirstAction).contains(AdoddleCommonStringPool.FOR_INFORMATION)) {
			contextClickWithLink(commentingFormTitle);
			mouseHoverAndClickElement(ProjectFormsTab.opt_FormContextClickActions, ProjectFormsTab.opt_FormContextClickActionsForInformation);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_PopActionsForInformationOk, ProjectFormsTab.btn_PopActionsForInformationOk);
		}
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementContainsText(ProjectFormsTab.lnk_FormsFirstAction, AdoddleCommonStringPool.ACTION_RESPOND);
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormsFirstAction);
		waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
	}

	public void clickRadioButton(String radio) {

		if (radio.equalsIgnoreCase(AdoddleCommonStringPool.COMMENTTYPE_PACKAGE)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackage);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormPackage))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackage);
		}
		else if (radio.equalsIgnoreCase(AdoddleCommonStringPool.COMMENTTYPE_DOCUMENT)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocument);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormDocument))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocument);
		}
		else if (radio.equalsIgnoreCase(AdoddleCommonStringPool.COMMENTTYPE_NOCOMMENTS)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormNoComment);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormNoComment))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormNoComment);
		}

	}

	public void verifyCommentAndSeverifyFields() {
		AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.txt_RespondCommentingFormCommentBox));
		AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.drp_RespondCommentingFormSeverity));
		selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormSeverity, AdoddleCommonStringPool.SEVERITY_MINOR);
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSeverity).equalsIgnoreCase(AdoddleCommonStringPool.SEVERITY_MINOR));
		selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormSeverity, AdoddleCommonStringPool.SEVERITY_MODERATE);
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSeverity).equalsIgnoreCase(AdoddleCommonStringPool.SEVERITY_MODERATE));
		selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormSeverity, AdoddleCommonStringPool.SEVERITY_MAJOR);
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSeverity).equalsIgnoreCase(AdoddleCommonStringPool.SEVERITY_MAJOR));
	}

	public void verifyPackageRecommendation(String rValue1, String rValue2) {
		AutomationAssert.verifyTrue(getValue(ProjectFormsTab.rad_RespondCommentingFormFirstPackageRecommendation).equalsIgnoreCase(rValue1));
		AutomationAssert.verifyTrue(getValue(ProjectFormsTab.rad_RespondCommentingFormSecondPackageRecommendation).equalsIgnoreCase(rValue2));
	}

	public void verifyDocumentSelection() {
		AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.drp_RespondCommentingFormDocument));
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormDocument, 1);
		AutomationAssert.verifyTrue(! getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormDocument).equals("") || getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormDocument) != null);

	}

	public void clickOnSendButtonToCreateComment() {
		mouseHoverAndClickElement(ProjectFormsTab.btn_CreateFormSave, ProjectFormsTab.btn_CreateFormSave);
		try {
			waitUntilElementIsInvisible(ProjectFormsTab.btn_CreateFormSave, 30);
		}
		catch(Throwable t) {
			log.info("ERROR: Clicking send button not successful, reclicking the button");
		}
		waitForCompletePageLoad();
		if(isDisplayed(ProjectFormsTab.btn_CreateFormSave))
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSave, ProjectFormsTab.btn_CreateFormSave);
		switchDefault();
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
	}

	public void verifyActionIsClearedForUser(String action) {
		searchForms(commentingFormTitle);
		try {
			waitUntilElementIsInvisible(ProjectFormsTab.lnk_FormsFirstAction, 60);
			AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.lnk_FormsFirstAction));
		}
		catch (AutomationErrors t) {
			AutomationAssert.verifyTrue(!getElementText(ProjectFormsTab.lnk_FormsFirstAction).equalsIgnoreCase(action));
		}

		try {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_FormsFirstAction), action), getElementText(ProjectFormsTab.lbl_FormsFirstAction).contains(action));
		}
		catch (AutomationErrors a) {
			mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(GlobalPageElements.pop_firstActionsPopOverContent), action), getElementText(GlobalPageElements.pop_firstActionsPopOverContent).contains(action));
		}
	}

	public void selectDocumentToComment(String rSeverity) {
		String reviewerComment = javaUtils.getRandomString(20);
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormDocument, 1);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormCommentBox, reviewerComment);
		selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormSeverity, rSeverity);
	}

	public void optDocumentRecommendationOption(String dRecommendation) {
		if (dRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_AWC))
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocumentAcceptanceWithComment);
		else if (dRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_NA))
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocumentNotAccepted);
		else if(dRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_AC)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocumentAccepted);
			if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormDocumentAccepted))
				clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocumentAccepted);
		}
	}

	/*public void optPackageRecommendationOption(String pRecommendation) {
		if (pRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_AWC)) {
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackageAcceptanceWithComment);
			sendKeys(ProjectFormsTab.txt_RespondCommentingFormPackageAcceptanceComment, javaUtils.getRandomString(20));
		}
		else if (pRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_NA))
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPackageNotAccepted);
		else if (pRecommendation.equalsIgnoreCase(AdoddleCommonStringPool.RECOMMENDATION_AC))
			clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormDocumentAccepted);
	}

	public void specifyCommentAndSeverity(String lSeverity) {
		leadReviewerComment = javaUtils.getRandomString(20);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormCommentBox, leadReviewerComment);
		selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormSeverity, lSeverity);
	}*/

	public void verifyAllCommentsInReadOnlySection() {
		if(isDisplayed(ProjectFormsTab.lnk_RespondCommentingFormReadOnlyExpand)) {
			clickElementAndWait(ProjectFormsTab.lnk_RespondCommentingFormReadOnlyExpand);
			try {
				for(WebElement e: findElements(By.cssSelector("div[id='reviewerCoordinatorGrp'] div[ng-show='showReadOnlyComments'] div[ng-repeat] div[class*='RCReadOnlyHeader']")))
					AutomationAssert.verifyTrue(e.getText().contains("Reviewer"));
				for(WebElement e: findElements(By.cssSelector("div[id='reviewerCoordinatorGrp'] div[ng-show='showReadOnlyComments'] div[ng-repeat] table[class*='cmntngFrmDetails'] tr:nth-child(1)"))) {
					AutomationAssert.verifyTrue(e.getText()+" != "+"Package Recommendation" , e.getText().contains("Package Recommendation"));
					AutomationAssert.verifyTrue(e.getText()+" != "+"Accepted" , e.getText().contains("Accepted"));
				}
			}
			catch(Throwable t) {
				log.info("Label verification failed for Package Recommendation");
			}
		}

	}

	public void verifyLeadReviewerCommentsInEditableSection() {
		System.out.println("INFO: This implementation is not available");
	}

	/*public void insertNewComment() {
		clickElementAndWaitForElement(ProjectFormsTab.lnk_RespondCommentingFormInsertComment, ProjectFormsTab.txt_RespondCommentingFormInsertedCommentInput);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormInsertedCommentInput, javaUtils.getRandomString(20));
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormInsertedCommentSeverity, 1);

	}

	public void addAdditionalDocumentComment() {
		clickElementAndWaitForElement(ProjectFormsTab.img_RespondCommentingFormCommentAdditional, ProjectFormsTab.rad_RespondCommentingFormCommentAdditionalDocument);
		clickElementAndWaitForElement(ProjectFormsTab.rad_RespondCommentingFormCommentAdditionalDocument, ProjectFormsTab.drp_RespondCommentingFormAdditionalDocument);
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormAdditionalDocument, 3);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormCommentAdditionalDocTextArea, javaUtils.getRandomString(20));
		selectByIndex(ProjectFormsTab.drp_RespondCommentingFormCommentAdditionalDocSeverity, 1);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormInternalRemarks, javaUtils.getRandomString(30));
	}*/

	public void issueFormToTA(String user, String userRole) {
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormInternalRemarks, javaUtils.getRandomString(30));
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
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		mouseHover(ProjectFormsTab.rad_RespondCommentingFormTAApproveYes);
		clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormTAApproveYes);
		AutomationAssert.verifyTrue(getValue(ProjectFormsTab.rad_RespondCommentingFormTAApproveYes).equalsIgnoreCase(approveFlag));
	}

	public void sendCommentingFormToUser(String toUser) {
		if (toUser.equalsIgnoreCase(ResourceHandler.loadProperty("commenting.form.project.manager").trim())) {
			selectByIndex(ProjectFormsTab.drp_RespondCommentingFormSendToPM, 1);
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSendToPM).contains(toUser));
		}
		else if (toUser.equalsIgnoreCase(ResourceHandler.loadProperty("commenting.form.contractor.pm").trim())) {
			clickElementAndWait(ProjectFormsTab.drp_RespondCommentingFormSendToContractor);
			List<WebElement> optionElements = findElement(ProjectFormsTab.drp_RespondCommentingFormSendToContractor).findElements(By.tagName("option"));
			for (WebElement option : optionElements) {
				if (option.getText().contains(toUser)) {
					System.out.println("Option Text: " + option.getText());
					option.click();
					break;
				}
			}
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectFormsTab.drp_RespondCommentingFormSendToContractor).contains(toUser));
		}
	}

	public void pmAcceptsFormComments(String flag) {
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		clickElementAndWait(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsYes);
		if(!isSelected(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsYes))
			clickElementAndWaitForElement(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsYes, ProjectFormsTab.txt_RespondCommentingFormPMComments);
		if (isDisplayed(ProjectFormsTab.drp_RespondCommentingFormPMPackageStatus))
			selectByVisibleText(ProjectFormsTab.drp_RespondCommentingFormPMPackageStatus, AdoddleCommonStringPool.ACCEPTED);
		sendKeys(ProjectFormsTab.txt_RespondCommentingFormPMComments, AdoddleCommonStringPool.ACCEPTED);
		AutomationAssert.verifyTrue(getValue(ProjectFormsTab.rad_RespondCommentingFormPMAcceptsYes).equalsIgnoreCase(flag));
	}

	public void openCommentingForm() {
		parentHandle1 = clickElementAndSwitchWindow(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		parentHandle2 = getCurrentWindow();
	}

	public void verifyExportCommentsButton() {
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_ExportCommentingFormComments);
		waitUntilElementIsClickable(ProjectFormsTab.btn_ExportCommentingFormComments);
	}

	public void exportCommentsIntoExcel() throws IOException, InterruptedException {
		clickElementAndWait(ProjectFormsTab.btn_ExportCommentingFormComments);
		waitForSwitchWindow(3);
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_DownloadCommentingFormReport);
		clickElementAndWait(ProjectFormsTab.btn_DownloadCommentingFormReport);
		commentReportFile = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + "AutomationReport" + JavaUtils.getRandomNumber(10) + ".xls");
		autoItUtils.downloadAutoIt(commentReportFile.toString(), nodeIP);
		waitUntilFileIsDownloaded(commentReportFile);
		sysUtils.executeVBSCall(commentReportFile.toString(), nodeIP);
	}

	public void verifyExcelReport() throws IOException {
		FileInputStream	fs = new FileInputStream(commentReportFile.toString());
		System.out.println("Commenting Form Report File path: " + commentReportFile.toString());
		String expectedTitle = excelUtils.getCell(excelUtils.getSheetAt(fs, 0), 4, 1).getStringCellValue();
		AutomationAssert.verifyTrue(expectedTitle.equalsIgnoreCase(commentingFormTitle));
	}
}