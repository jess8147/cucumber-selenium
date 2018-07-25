package org.asite.automation.adoddle.p2.scripts;

import java.io.File;

import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.openqa.selenium.By;

public class CreateIDPP2Scripts extends AdoddleCommonAppMethods {
	
	private String parentHandle, subDeliverableFileMask, itemTitle;
//	private String clonedProject = "Cloned_United Kingdom (EU01)_20180719175646";
	private String fileMask, expectedFileMask ;
	private String stageItemUploadDocumentStatus = "S2 - Issued for Information";
	private String stageItemUploadDocumentRevision = "1";
	
	
	public void getFocusToClonedProject() {
		clickElementWithText(clonedProject);
	}
	
	public void clickIDPFormLink(String formLink){
		clickElementAndWait(ProjectFormsTab.lnk_IDPFormLink);
	}
	
	public void createIDPP2Form(){
		parentHandle = getCurrentWindow();
		waitUntilElementIsClickable(ProjectFormsTab.btn_CreateForm);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		waitForCompletePageLoad();
	}
	
	public void editProjectDetails(){
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormPTitleTable, ProjectFormsTab.txt_CreateIDPFormProjectDetailsFacility);
		clickElementAndWaitForElement(ProjectFormsTab.drp_CreateIDPFormProjectDetailsRef, ProjectFormsTab.drp_CreateIDPFormProjectDetailsRefFirstSelect);
		clickElementAndWaitForElement(ProjectFormsTab.drp_CreateIDPFormProjectDetailsRefFirstSelect, ProjectFormsTab.txt_CreateIDPFormProjectDetailsFacility);
		sendKeys(ProjectFormsTab.txt_CreateIDPFormProjectDetailsFacility, javaUtils.getRandomString(10));
		selectByIndex(ProjectFormsTab.drp_CreateIDPFormProjectDetailsPlanStatus, 1);
		selectByIndex(ProjectFormsTab.drp_CreateIDPFormProjectDetailsRegion, 1);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateIDPFormProjectDetailsUpdate, ProjectFormsTab.btn_CreateIDPFormProjectDetailsUpdate);
	}
	
	public void editStageDetails(){
		
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

	/* Common Function */
	public void openIDPFormInEditMode() {
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(clonedProject);
		clickElementWithText("1.0 Information Delivery Plan");
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormID);
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_CreateIDPFormLastDeliverable);
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormViewDetailsActionEditDraft);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewFormViewDetailsActionEditDraft, ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
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
		fileMask = getElementText(By.cssSelector("div[id='idpDropArea'] table tbody tr:nth-child(18) td:last-child div"));
		log.info("File Mask: " +fileMask );
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_PopStageItemUpdate, ProjectFormsTab.btn_PopStageItemUpdate);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_CreateIDPFormLastDeliverable);
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_EditIDPFormFirstStageVerify);
		getWebDriver().findElement(ProjectFormsTab.btn_EditIDPFormFirstStageVerify).click();
		waitForCompletePageLoad();
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
	
	public void shareDeliverables() {
		openIDPFormInEditMode();
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateIDPFormFirstStageSubDeliverable, ProjectFormsTab.ele_StageItemPopUp);
		File idpSubDeliverableFile = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + subDeliverableFileMask + AdoddleCommonStringPool.PDF_EXTENSION);
		sysUtils.authenticateRemoteMachine(nodeIP);
		sysUtils.createRemotePdfFile(idpSubDeliverableFile.toString(), nodeIP);
		getWebDriver().findElement(ProjectFormsTab.btn_StageItemPopUpSelectFileButton).sendKeys(idpSubDeliverableFile.toString());
		waitUntilElementIsDisplayed(ProjectFormsTab.drp_StageItemUploadDocumentStatus);
		waitUntilDropdownContainsValue(ProjectFormsTab.drp_StageItemUploadDocumentStatus, stageItemUploadDocumentStatus, 30);
		selectByVisibleText(ProjectFormsTab.drp_StageItemUploadDocumentStatus, stageItemUploadDocumentStatus);
		sendKeys(ProjectFormsTab.ele_StageItemUploadDocumentRevision, stageItemUploadDocumentRevision);
		expectedFileMask = getElementText(ProjectFormsTab.ele_IDPFormFileMask);
		log.info("Expected File Mask: " + expectedFileMask);
		expectedFileMask = expectedFileMask.replace("Status", "S2").replace("Rev", "1");
		log.info("Expected File Mask replaced: " + expectedFileMask);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_StageItemUploadDocumentUpdateButton, ProjectFormsTab.btn_StageItemUploadDocumentUpdateButton);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_SendButtonCreateForm, ProjectFormsTab.btn_SendButtonCreateForm);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		logOut();
	}
	
	public void verifyFileMaskKey(){
		
		login(ResourceHandler.loadProperty("idp.form.epm.user"), ResourceHandler.loadProperty("idp.form.epm.password"));
		navigateTabByText("Files");
		clickElementWithText(clonedProject);
		AutomationAssert.assertTrue(eStringUtils.getEqualityStringError(getElementText(FilesTab.css_DocListingPurposeOfIssuesList), stageItemUploadDocumentStatus), getElementText(FilesTab.css_DocListingPurposeOfIssuesList).equals(stageItemUploadDocumentStatus));
		AutomationAssert.assertTrue(eStringUtils.getEqualityStringError(getElementText(FilesTab.lnk_UploadedFileRev), stageItemUploadDocumentRevision), getElementText(FilesTab.lnk_UploadedFileRev).equals(stageItemUploadDocumentRevision));
	}
	
	public void verifyFilters(){
		openIDPFormInEditMode();
		clickElementAndWaitForElement(ProjectFormsTab.ele_IDPFormHideShow, ProjectFormsTab.chk_IDPFormHideShowHeaderCheckbox);
		if(!isSelected(ProjectFormsTab.chk_IDPFormHideShowHeaderCheckbox))
			clickElement(ProjectFormsTab.chk_IDPFormHideShowHeaderCheckbox);
		AutomationAssert.verifyTrue(eStringUtils.getFindFailureError(ProjectFormsTab.ele_IDPFormHideShowPriority), isDisplayed(ProjectFormsTab.ele_IDPFormHideShowPriority));
		clickElementAndWaitForElement(ProjectFormsTab.ele_IDPFormHideShow, ProjectFormsTab.chk_IDPFormHideShowFileMaskCheckBox);
		if(!isSelected(ProjectFormsTab.chk_IDPFormHideShowFileMaskCheckBox))
			clickElement(ProjectFormsTab.chk_IDPFormHideShowFileMaskCheckBox);
		AutomationAssert.verifyTrue(eStringUtils.getFindFailureError(ProjectFormsTab.ele_IDPFormHideShowFileMask), isDisplayed(ProjectFormsTab.ele_IDPFormHideShowFileMask));
		clickElementAndWaitForElement(ProjectFormsTab.ele_IDPFormHideShowSection, ProjectFormsTab.chk_IDPFormHideShowSectionDeliverableFirst);
		if(isSelected(ProjectFormsTab.chk_IDPFormHideShowSectionDeliverableFirst))
			AutomationAssert.verifyTrue(eStringUtils.getFindFailureError(ProjectFormsTab.ele_IDPFormHideShowSectionDeliverableFirstListing), isDisplayed(ProjectFormsTab.ele_IDPFormHideShowSectionDeliverableFirstListing));
		else
			AutomationAssert.verifyTrue(eStringUtils.getFindFailureError(ProjectFormsTab.ele_IDPFormHideShowSectionDeliverableFirstListing), false);
		clickElementAndWaitForElement(ProjectFormsTab.ele_IDPFormHideShowStages, ProjectFormsTab.chk_IDPFormHideShowStagesIdentificationCheckbox);
		if(isSelected(ProjectFormsTab.chk_IDPFormHideShowStagesIdentificationCheckbox))
			AutomationAssert.verifyTrue(eStringUtils.getFindFailureError(ProjectFormsTab.ele_IDPFormHighlightedStageDeliverable), isDisplayed(ProjectFormsTab.ele_IDPFormHighlightedStageDeliverable));
		else
			AutomationAssert.verifyTrue(eStringUtils.getFindFailureError(ProjectFormsTab.ele_IDPFormHighlightedStageDeliverable), false);
			
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
