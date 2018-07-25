package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleClassicLocators.ClassicLocators;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CreateIDPFormScripts extends AdoddleCommonAppMethods {

	private static File			idpFileZip;
	private String				parentHandle;
	private String				subDeliverableFileMask, DeliverableFileMask;
	final private String		epoch					= dateUtils.getEpoch();
	private String 				itemTitle 				= null;
	final private static Logger	log						= AutomationLogger.getInstance().getLogger(CreateIDPFormScripts.class);

	public void uploadIDPFilesToTemplate() {
		String parentHandle = getCurrentWindow();
		switchToClassicView();
		clickElementAndWait(ProjectFormsTab.lnk_ClassicWorkspaceTemplate);
		clickLinkWithText(ResourceHandler.loadProperty("idp.project.template.title"));
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		selectByVisibleText(ClassicLocators.drp_AdminDropdown, AdoddleCommonStringPool.LABEL_APPS_MANAGE_WS_SETTINGS);
		switchToMultipleFrames();
		clickElementAndWaitForElement(ProjectFormsTab.lnk_ClassicManageAppsFirstEdit, ProjectFormsTab.frm_FormUploadTemplate);
		waitAndSwitchIframe(ProjectFormsTab.frm_FormUploadTemplate);
		getWebDriver().findElement(ProjectFormsTab.btn_ClassicEditFormSettingsBrowse).sendKeys(idpFileZip.toString());
		clickElementAndSwitchWindow(ProjectFormsTab.btn_ClassicEditFormSettingsUpload);
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_UploadFormTemplateSuccessFullOK);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		switchToMultipleFrames();
		mouseHover(ProjectFormsTab.btn_ClassicEditFormSettingsSubmit);
		clickElementAndWaitForElement(ProjectFormsTab.btn_ClassicEditFormSettingsSubmit, ProjectFormsTab.lnk_ClassicManageAppsFirstEdit);
	}
		
	private void switchToMultipleFrames() {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);
	}

	public void getFocusToClonedProject() {
		clickElementWithText(clonedProject);
	}

	public void clickIDPTFormLink(String formLinkText) {
		if (formLinkText.equalsIgnoreCase("IDPT"))
			clickElementAndWait(ProjectFormsTab.lnk_IDPFormTemplateFormLink);
		else if (formLinkText.equalsIgnoreCase("1.0 Information Delivery Plan"))
			clickElementAndWait(ProjectFormsTab.lnk_IDPFormLink);
	}

	public void createIDPForm() {
		parentHandle = getCurrentWindow();
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateForm, ProjectFormsTab.frm_createFormIframe);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		waitForCompletePageLoad();
	}

	public void editProjectDetails() {
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormPTitleTable, ProjectFormsTab.txt_CreateIDPFormProjectDetailsFacility);
		clickElementAndWaitForElement(ProjectFormsTab.drp_CreateIDPFormProjectDetailsRef, ProjectFormsTab.drp_CreateIDPFormProjectDetailsRefFirstSelect);
		clickElementAndWaitForElement(ProjectFormsTab.drp_CreateIDPFormProjectDetailsRefFirstSelect, ProjectFormsTab.txt_CreateIDPFormProjectDetailsFacility);
		sendKeys(ProjectFormsTab.txt_CreateIDPFormProjectDetailsFacility, javaUtils.getRandomString(10));
		/* NOODLE-
		sendKeys(ProjectFormsTab.txt_CreateIDPFormProjectDetailsFacility, resourceUtils.getSpecialCharString());*/
		selectByIndex(ProjectFormsTab.drp_CreateIDPFormProjectDetailsPlanStatus, 1);
		selectByIndex(ProjectFormsTab.drp_CreateIDPFormProjectDetailsRegion, 1);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateIDPFormProjectDetailsUpdate, ProjectFormsTab.btn_CreateIDPFormProjectDetailsUpdate);
	}

	public void uploadDeliverablesSupplierUser() {
		uploadDeliverables(subDeliverableFileMask);
		logOut();
	}

	public void editStageDetails() {
		String	stageManageMentStatus	= "CR:Current";
		switchPreviousWindow(parentHandle);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		if(isDisplayed(GlobalPageElements.lbl_SuccessMessage))
			waitUntilElementIsInvisible(GlobalPageElements.lbl_SuccessMessage);
		openIDPFormInEditMode();
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormStageManagementFirstStage, ProjectFormsTab.ele_CreateIDPFormStageManagementInformationDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateIDPFormStageManagementInformationDate);
		selectDateFromCalendar();
		clickElementAndWaitForElement(ProjectFormsTab.drp_PopStageManageMentSIM, ProjectFormsTab.drp_PopStageManageMentSupplierOrg);
		clickElementAndWaitForElement(ProjectFormsTab.drp_PopStageManageMentSupplierOrgValue, ProjectFormsTab.drp_PopStageManageMentStatus);
		selectByVisibleText(ProjectFormsTab.drp_PopStageManageMentStatus, stageManageMentStatus);
		selectByIndex(ProjectFormsTab.drp_PopStageManageMentLOD, 1);
		clickElementAndWaitForElement(ProjectFormsTab.btn_PopStageDeliverablesUpdate, ProjectFormsTab.btn_CreateFormSend);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void editDeliverablesDetails() throws InterruptedException {
		openIDPFormInEditMode();
		itemTitle = javaUtils.getRandomString(10);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_CreateIDPFormFirstDeliverable, ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemTitle);
		sendKeys(ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemTitle, itemTitle);
		/* NOODLE-83798
		sendKeys(ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemTitle, resourceUtils.getSpecialCharString());*/
		clickElementAndWaitForElement(ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemVolumeEdit, ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemAdd);
		sendKeys(ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemAddCode, javaUtils.getRandomString(4).toUpperCase());
		sendKeys(ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemAddName, javaUtils.getRandomString(10));
		/* NOODLE-83798
		sendKeys(ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemAddName, resourceUtils.getSpecialCharString());*/
		clickElementAndWaitForElement(ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemAdd, ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemClose);
        waitUntilElementIsEnabled(ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemClose);
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemClose, ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemVolumeDropDown);
		waitUntilElementIsEnabled(ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemVolumeDropDown);
		clickElementAndWaitForElement(ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemVolumeDropDown, ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemVolumeSelect);
		clickElement(ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemVolumeSelect);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemLocationEdit, ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemAdd);
		sendKeys(ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemAddCode, javaUtils.getRandomString(4).toUpperCase());
		/* NOODLE-83798
		sendKeys(ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemAddName, resourceUtils.getSpecialCharString()); */
		sendKeys(ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemAddName, javaUtils.getRandomString(10));
		clickElementAndWaitForElement(ProjectFormsTab.lnk_CreateIDPFormFirstDeliverableItemAdd, ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemClose);
		waitUntilElementIsEnabled(ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemClose);
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemClose , ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemLocationDropDown);
		clickElementAndWaitForElement(ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemLocationDropDown, ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemLocationSelect);
		clickElement(ProjectFormsTab.drp_CreateIDPFormFirstDeliverableItemLocationSelect);
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemFileType, ProjectFormsTab.ele_CreateIDPFormFirstDeliverableItemFileTypeSeletion);
		clickElement(ProjectFormsTab.ele_CreateIDPFormFirstDeliverableItemFileTypeSeletion);
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateIDPFormFirstDeliverableItemRole, ProjectFormsTab.ele_CreateIDPFormFirstDeliverableItemRoleSeletion);
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormFirstDeliverableItemRoleSeletion, ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemNumber);
		sendKeys(ProjectFormsTab.txt_CreateIDPFormFirstDeliverableItemNumber, javaUtils.getRandomString(5));
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_PopStageDeliverablesUpdate, ProjectFormsTab.btn_PopStageDeliverablesUpdate);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void editStageDeliverables() {
		openIDPFormInEditMode();
		waitUntilElementIsDisplayed(ProjectFormsTab.ele_CreateIDPFormFirstStageDeliverable);
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormFirstStageDeliverable, ProjectFormsTab.btn_PopStageDeliverablesSupplierRole);
		String deliverableFileMask = getElementText(ProjectFormsTab.lbl_PopStageDeliverablesFileMask);
		log.info("Deliverable Mask: " + deliverableFileMask);
		sendKeys(ProjectFormsTab.txt_PopStageDeliverablesDeliveryNote, "Stage01-Note");
		clickElementAndWait(ProjectFormsTab.btn_PopStageDeliverablesUpdate);
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormFirstStageSubDeliverable, ProjectFormsTab.btn_PopStageItemUpdate);
		subDeliverableFileMask = "subDeliverableFileMask";
		waitUntilElementIsEnabled(ProjectFormsTab.img_NotifyToUserDueDateCalender);
		clickElementAndWaitForElement(ProjectFormsTab.img_NotifyToUserDueDateCalender, ProjectFormsTab.lnk_NotifyToUserDueDate);
		selectDateFromCalendar();
		selectByVisibleText(ProjectFormsTab.drp_CreateIDPFormPopStageItemNotifyTo, ResourceHandler.loadProperty("idp.project.notify.to.user"));
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_PopStageItemUpdate, ProjectFormsTab.btn_PopStageItemUpdate);
		waitForCompletePageLoad();
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend);
		switchDefault();
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void editStageDeliverablesDate(String date) {

		if (isDisplayed(LoginPage.frm_Iframe))
			login(ResourceHandler.loadProperty("idp.form.epm.user"), ResourceHandler.loadProperty("idp.form.epm.password"));
		openIDPFormInEditMode();
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormStageManagementFirstStage, ProjectFormsTab.drp_PopStageManageMentSIM);
		clickElementAndWaitForElement(ProjectFormsTab.txt_PopStageManageMentInfoByDate, ProjectFormsTab.lnk_PopStageManageMentDatePickerFooterToday);

		if (date.equalsIgnoreCase("current")) {
			clickElementAndWaitForElement(ProjectFormsTab.lnk_PopStageManageMentDatePickerFooterToday, ProjectFormsTab.btn_PopStageManageMentUpdate);
		}
		else if (date.equalsIgnoreCase("past")) {
			clickElementAndWaitForElement(ProjectFormsTab.lnk_PopStageManageMentDatePickerPrevious, ProjectFormsTab.ele_PopStageManageMentDatePickerRandomDate);
			clickElementAndWait(ProjectFormsTab.ele_PopStageManageMentDatePickerRandomDate);
		}

		clickElementAndWaitForElement(ProjectFormsTab.btn_PopStageManageMentUpdate, ProjectFormsTab.btn_CreateFormSend);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		waitForCompletePageLoad();
	}

	private void uploadDeliverables(String fileMask1) {
		String	deliverableReference	= "A0100";
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(clonedProject);
		clickElementWithText("CDE");
		clickElementAndWait(FilesTab.btn_AddFiles);
		fileMask1 = fileMask1.replaceFirst("\\?", "A01");
		subDeliverableFileMask = fileMask1 = fileMask1.replaceFirst("\\?", "1");
		log.info("Replaced file masks: " + subDeliverableFileMask + " \n");
		File idpSubDeliverableFile1 = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + fileMask1 + AdoddleCommonStringPool.PDF_EXTENSION);
		sysUtils.authenticateRemoteMachine(nodeIP);
		sysUtils.createRemotePdfFile(idpSubDeliverableFile1.toString(), nodeIP);
		getWebDriver().findElement(FilesTab.btn_PopUploadFileDistributeSelectFiles).sendKeys(idpSubDeliverableFile1.toString());
		clickElementAndWaitForElement(FilesTab.chk_PopUploadFilesBulkApply, FilesTab.txt_PopUploadHeaderRevInput);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);

		for (WebElement e : findElements(FilesTab.css_PopUploadFilesProjectNumbers))
			selectByIndex(e, 1);

		index = javaUtils.resetIndex(index, 1);
		for (WebElement e : findElements(FilesTab.css_PopUploadFilesFileNumber)) {
			e.sendKeys(deliverableReference + AdoddleCommonStringPool.UNDERSCORE_STRING + index);
			index++;
		}

		for (WebElement e : findElements(FilesTab.css_PopUploadFilesDeliverableReference))
			e.sendKeys(deliverableReference);

		for (WebElement e : findElements(FilesTab.css_PopUploadFilesPOWStage))
			selectByIndex(e, 1);

		for (WebElement e : findElements(FilesTab.css_PopUploadFilesPOWLOD))
			selectByIndex(e, 1);

		for (WebElement e : findElements(FilesTab.css_PopUploadFilesOrganization)) {
			log.info("IDP Stage Organization: " + strUtils.splitString(ResourceHandler.loadProperty("idp.project.stage.org"), ":").get(1));
			selectByVisibleText(e, strUtils.splitString(ResourceHandler.loadProperty("idp.project.stage.org"), ":").get(1));
		}

		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopUploadFileDistributeUpload, GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void shareDeliverables() {
		openIDPFormInEditMode();
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormFirstStageSubDeliverable, ProjectFormsTab.ele_StageItemPopUp);
		File idpSubDeliverableFile1 = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + subDeliverableFileMask + AdoddleCommonStringPool.PDF_EXTENSION);
		sysUtils.authenticateRemoteMachine(nodeIP);
		sysUtils.createRemotePdfFile(idpSubDeliverableFile1.toString(), nodeIP);
		getWebDriver().findElement(ProjectFormsTab.btn_StageItemPopUpSelectFileButton).sendKeys(idpSubDeliverableFile1.toString());
		waitUntilElementIsDisplayed(ProjectFormsTab.drp_StageItemUploadDocumentStatus);
		waitUntilDropdownContainsValue(ProjectFormsTab.drp_StageItemUploadDocumentStatus, "S2 - Issued for Information", 30);
		selectByVisibleText(ProjectFormsTab.drp_StageItemUploadDocumentStatus, "S2 - Issued for Information");
		sendKeys(ProjectFormsTab.ele_StageItemUploadDocumentRevision, "1");
		DeliverableFileMask = getElementText(ProjectFormsTab.ele_IDPFormFileMask);
		log.info("Deliverable File Mask: " + DeliverableFileMask);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_StageItemUploadDocumentUpdateButton, ProjectFormsTab.btn_StageItemUploadDocumentUpdateButton);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_SendButtonCreateForm, ProjectFormsTab.btn_SendButtonCreateForm);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		logOut();
	}

	public void changeStatusToForPublishing(String fileMask, String status) {
		if (fileMask.equalsIgnoreCase("subdeliverable1"))
			fileMask = subDeliverableFileMask;

		navigateTabByText(AdoddleCommonStringPool.TAB_FILES);
		clickElementWithText(clonedProject);
		searchFiles("subDeliverableFileMask");
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.css_DocListingDocTitleList).trim(), itemTitle), getElementText(FilesTab.css_DocListingDocTitleList).trim().equalsIgnoreCase(itemTitle));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.css_FilesDocRefList).trim(), DeliverableFileMask), DeliverableFileMask.contains(getElementText(FilesTab.css_FilesDocRefList).trim()));
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.img_DocListingFirstPrivateImage, true), isDisplayed(FilesTab.img_DocListingFirstPrivateImage));
		if (!isSelected(ProjectFormsTab.chk_SelectAllCheckbox))
			clickElement(ProjectFormsTab.chk_SelectAllCheckbox);
		contextClick(ProjectFormsTab.opt_ContextClickOnSelectedFiles);
		clickContextMenuOption("Edit", "Status");
		waitUntilElementIsDisplayed(ProjectFormsTab.drp_BatchChangeStatusDropDown);
		selectByVisibleText(ProjectFormsTab.drp_BatchChangeStatusDropDown, status);
		sendKeys(ProjectFormsTab.ele_BatchChangeStatusReason, "Status changed to Published by EPM user");
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BatchChangeStatusSubmit, ProjectFormsTab.btn_BatchChangeStatusSubmit);
		waitForCompletePageLoad();
		waitUntilElementContainsText(FilesTab.lnk_FilesTabFirstFileStatus, status);
	}

	public void verifyIDPFormStageStatus(String deliverableColor, String firstSubDeliverableColor) {
		openIDPFormInEditMode();
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_CreateIDPFormLastDeliverable);
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_EditIDPFormFirstStageVerify);
		getWebDriver().findElement(ProjectFormsTab.btn_EditIDPFormFirstStageVerify).click();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.img_EditIDPFormFirstStageSubDeliverableColorIcon);
		waitUntilAttributeContainsValue(ProjectFormsTab.img_EditIDPFormFirstStageDeliverableColorIcon, "src", deliverableColor + ".png");
		waitUntilAttributeContainsValue(ProjectFormsTab.img_EditIDPFormFirstStageSubDeliverableColorIcon, "src", firstSubDeliverableColor + ".png");
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void navigateToHTMLAppsDownload() {
		getWebDriver().get(ResourceHandler.loadProperty("adoddle.svn.html.apps.url"));
	}

	public void downloadIDPFiles() throws InterruptedException {
		clickElement(ProjectFormsTab.lnk_svnHTMLAppsIDPZipFile);
		idpFileZip = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + "IDPFile" + epoch + ".zip");
		sysUtils.waitForSingleDirectFileDownload(nodeIP, "PIDP.zip", idpFileZip.toString());
	}

	public void verifyIDPFileDownload() {
		log.info("covered in previous definition");
	}

	public void downloadCoBieIR() throws InterruptedException {
		if (isDisplayed(LoginPage.frm_Iframe)) {
			try {
				login(ResourceHandler.loadProperty("idp.form.epm.user"), ResourceHandler.loadProperty("idp.form.epm.password"));
			}
			catch (Throwable t) {
				log.info("failure while login");
			}
		}
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormID);
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_FormDetailsCoBieIR);
		clickElement(ProjectFormsTab.lnk_FormDetailsCoBieIR);
		String cobieFile = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + "CoBieIR" + epoch + ".xlsm";
		sysUtils.waitForSingleDirectFileDownload(nodeIP, ResourceHandler.loadProperty("idp.cobier.default.filename"), cobieFile);
	}

	public void verifyCoBieIRFile() {
		log.info("P2 scenario");
	}

	public void closeTheProject() {
		deactivateInheritanceProject(clonedProject);
	}

	/* Common Function */
	private void openIDPFormInEditMode() {
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(clonedProject);
		clickElementWithText("1.0 Information Delivery Plan");
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormID);
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_CreateIDPFormLastDeliverable);
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormViewDetailsActionEditDraft);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewFormViewDetailsActionEditDraft, ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
	}

	@Override
	public void login(String username, String password) {
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		switchIframe(LoginPage.frm_Iframe);
		waitUntilElementIsDisplayed(LoginPage.txt_AsiteUsername);
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).clear();
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).sendKeys(username);
		getWebDriver().findElement(LoginPage.txt_AsitePassword).clear();
		getWebDriver().findElement(LoginPage.txt_AsitePassword).sendKeys(password);
		getWebDriver().findElement(LoginPage.btn_AsiteLogin).click();

		if (isDisplayed(GlobalPageElements.btn_DynamicModelDisMiss))
			clickElementAndWait(GlobalPageElements.btn_DynamicModelDisMiss);

		try {
			waitUntilElementIsDisplayed(LandingPage.ele_WidgetNewFormsPublishedHeader);
			waitForCompletePageLoad();
		}
		catch (Throwable t) {
			log.error("Reloading page for dashboard loading error");
			reloadPage();
			waitForCompletePageLoad();
		}
	}

	private void selectDateFromCalendar() {
		String selectedMonth = null;
		String selectedDate = null;
		selectedMonth = DateUtils.addDaysToDate("MMMM", timezoneID, 7);
		log.info("selectedMonth : " + selectedMonth);
		selectedDate = DateUtils.addDaysToDate("d", timezoneID, 7);
		log.info("selectedDate : " + selectedDate);
		waitForCompletePageLoad();
		if (!getElementText(ProjectFormsTab.lbl_PopDatePickerCurrentMonthLabel).contains(selectedMonth))
			clickElementAndWait(ProjectFormsTab.lnk_PopDatePickerNextMonthLink);
		clickElementAndWait(By.xpath(".//div[contains(@class,'datepicker-calendar')]//div[contains(@class,'calendar-body')]//a[not(contains(@class,'datepicker-disabled'))][text()='"+selectedDate+"']"));
	}
}