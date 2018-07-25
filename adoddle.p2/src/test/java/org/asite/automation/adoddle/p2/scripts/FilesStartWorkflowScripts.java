package org.asite.automation.adoddle.p2.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.junit.Assert;

public class FilesStartWorkflowScripts extends AdoddleCommonAppMethods {
	private String			fileDocRef, formTitle;
	public static Logger	log	= AutomationLogger.getInstance().getLogger(FilesStartWorkflowScripts.class);

	public void selectFolderAndClickOnAddFiles(String folder, String addFiles) throws InterruptedException {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(folder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);
	}

	public void verifyUploadedFile() {
		searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
		Assert.assertTrue(getElementText(FilesTab.lnk_FileName).contains(strUtils.extractFileNameString(CreateEditRoleScripts.createFile)));
		fileDocRef = getElementText(FilesTab.lnk_FilesListingRowDocRef);
		log.info("fileDocRef :" + fileDocRef);
	}

	public void createForm() throws InterruptedException {
		switchDefault();
		formTitle = "AutomationRecentForm" + dateUtils.getEpoch();
		log.info("formTitle :" + formTitle);
		collectionDataMap.put("Form Title", formTitle);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		clear(ProjectFormsTab.txt_CreateFormTitle);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true"))
			clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		/*else
			clickElementAndWait(ProjectFormsTab.img_CreateFormCalendar);*/
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void verifyAssociationsOnFile() {
		searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
		waitForCompletePageLoad();
		Assert.assertTrue(isDisplayed(FilesTab.img_FilesTabFirstFileAssociationImage));
		clickOnAssociationsImage();
	}

	public void clickOnAssociationsImage() {
		mouseHover(FilesTab.img_FilesTabFirstFileAssociationImage);
		clickElementAndWait(FilesTab.pop_AppsPopOverContent);
	}

	public void verifyFormOnAttachmentAssociationPopup() {
		Assert.assertTrue(getElementText(ModelsTab.css_PopAssociateFormsFormTitleList).contains(formTitle));
	}

	public void verifyFileOnAttachmentAssociationPopup() {
		
		//if(ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			waitUntilElementIsInvisible(GlobalPageElements.img_LoadingProcess);
			clickElementAndWaitForElement(FilesTab.lnk_BetaViewViewFormFilesAssociation, ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel);
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel, "Attachments & Associations");
			Assert.assertTrue(fileDocRef.contains(getElementText(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAssociateFileList)));
		/*}
		else {
			clickElementAndWait(FilesTab.lnk_ViewFormFilesAssociation);
			Assert.assertTrue(fileDocRef.contains(getElementText(FilesTab.lnk_PopAttachmentsAssociationsDocRef)));
		}*/
	}

	public void clickMoreOptionsAndSelectOption() {
		if (!isSelected(FilesTab.chk_FilesListingRowchecbox))
			clickElementAndWait(FilesTab.chk_FilesListingRowchecbox);
		clickElementAndWaitForElement(FilesTab.lnk_FilesMoreOptions, FilesTab.lnk_OptionsStartWorkflow);
		clickElementAndWait(FilesTab.lnk_OptionsStartWorkflow);
	}
}
