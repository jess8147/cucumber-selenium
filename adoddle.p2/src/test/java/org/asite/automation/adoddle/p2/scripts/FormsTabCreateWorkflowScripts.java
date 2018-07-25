package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FormsTabCreateWorkflowScripts extends AdoddleCommonAppMethods {
	private String workflowFormTitle, attachDoc;
	private List<String> selectedFormList = new ArrayList<String>();
	public static Logger log = AutomationLogger.getInstance().getLogger(FormsTabCreateWorkflowScripts.class);

	public void selectFormsForWorkflow() {
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		waitForCompletePageLoad();
		List<WebElement> formList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
		List<WebElement> formCheckList = findElements(GlobalPageElements.css_ActiveTabListingPageCheckboxList);

		int i = 0;
		for (WebElement checkbox : formCheckList) {
			checkbox.click();
			selectedFormList.add(formList.get(i).getText());
			if (i == 2)
				break;
			i++;
		}
		collectionDataMap.put("Associated Form List", selectedFormList.toString());
	}

	public void attachExternalDocInWorkflowForm() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		attachDoc = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("file created as: " + attachDoc);
		collectionDataMap.put("Attached External Document", attachDoc);

		List<String> fileList = sysUtils.getFileList(attachDoc);
		//if (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
			uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		/*else
			uploadFileUsingKeys(ModelsTab.btn_PopModelAttachmentsSelectFiles, fileList);*/

		waitUntilElementIsDisplayed(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
	}

	public void createNewWorkflowForm(String action, String user) throws InterruptedException {
		enteredFormTitleAndDetails(action, user);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void enteredFormTitleAndDetails(String action, String user) throws InterruptedException {
		switchDefault();
		workflowFormTitle = "AutoForm" + dateUtils.getEpoch();
		log.info("workflowFormTitle :" + workflowFormTitle);
		collectionDataMap.put("Workflow Form Title", workflowFormTitle);

		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		distributeForm(action, user);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_CreateFormTitle);
		clear(ProjectFormsTab.txt_CreateFormTitle);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, workflowFormTitle);

		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();

		//if(ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
			waitUntilElementIsClickable(ProjectFormsTab.img_BetaViewCreateFormCalendar);
			clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		/*}
		else
			clickElementAndWait(ProjectFormsTab.img_CreateFormCalendar);*/
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
	}

	public void distributeForm(String action, String user) throws InterruptedException {
		log.info("Distributed User :" + user);
		
		//if(ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			
			clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, user);
			waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + user + "')]"));
			clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'"+user+"')]//preceding::input[@type='checkbox']"));
			waitUntilElementIsDisplayed(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown);
			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		/*}
		else {
			clickElementAndWait(ProjectFormsTab.btn_PopCreateFormDistributeFormButton);
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_PopCreateFormDistributeTo);
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, user);
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, Keys.ENTER);
			clickElementAndWait(ModelsTab.btn_FirstDistributeUserDropdownToggleButton);
			selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
			waitUntilElementIsDisplayed(FilesTab.lnk_PopDatePickerAssignedCurrentDate);
			clickElementAndWait(FilesTab.lnk_PopDatePickerAssignedCurrentDate);
		}*/
	}

	public void verifyCreatedWorkflowFormAndAttachmentAssociation(String attachments, String apps) {
		verifyCreatedForm(workflowFormTitle);
		verifyAssociationAndAttachmentViaStartWorkflow(attachments, apps);
	}

	public void verifyCreatedForm(String formTitle) {
		waitForCompletePageLoad();
		searchForms(formTitle);
		Assert.assertTrue(isDisplayed(ProjectFormsTab.lnk_FirstFormTitle));
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).equalsIgnoreCase(formTitle));
	}

	public void verifyAssociationAndAttachmentViaStartWorkflow(String attachments, String apps) {
		Assert.assertTrue(isDisplayed(ProjectFormsTab.img_FormsTabFirstFormAssociationImage));
		mouseHover(ProjectFormsTab.img_FormsTabFirstFormAssociationImage);
		waitUntilElementIsDisplayed(FilesTab.pop_AppsPopOverContent);
		clickElementAndWait(FilesTab.pop_AppsPopOverContent);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);

		for (String headerLink : Arrays.asList(attachments, apps)) {
			clickElementAndWait(By.xpath(".//div[@id='pageLayoutHeader']//div[@id]//a[contains(text(),'" + headerLink
					+ "')]"));

			if (getElementText(ModelsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains(attachments)) {
				Assert.assertTrue(findElements(ModelsTab.css_PopAttachmentsAndAssociationsAttachmentsTabFileNameList)
						.size() == 1);
				for (WebElement attachment : findElements(ModelsTab.css_PopAttachmentsAndAssociationsAttachmentsTabFileNameList)) {
					Assert.assertTrue(attachment.getText().contains(strUtils.extractFileNameString(attachDoc)));
				}
			} else if (getElementText(ModelsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains(apps)) {
				Assert.assertTrue(findElements(ModelsTab.css_PopAssociateFormsFormTitleList).size() == selectedFormList
						.size());
				List<String> formList = new ArrayList<String>();
				for (WebElement form : findElements(ModelsTab.css_PopAssociateFormsFormTitleList)) {
					formList.add(form.getText());
				}
				Assert.assertTrue(formList.containsAll(selectedFormList));
			}
		}
	}

	public void contextClickAndSelectContextOption(String option1, String option2) {
		contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		/*clickContextMenuOptionWithText(option1);
		clickContextMenuOptionWithText(option2);*/
		clickContextMenuOption(option1, option2);
	}

	public void clickOnMoreOptionsWorkflowFormLink() {
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FormListingMoreOptions, ProjectFormsTab.lnk_PopMoreOptionsProjectFormLink);
		clickElementAndWait(ProjectFormsTab.lnk_PopMoreOptionsProjectFormLink);
	}
}
