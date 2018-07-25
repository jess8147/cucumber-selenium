package org.asite.automation.adoddle.p1.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CreateQueryFormScripts extends AdoddleCommonAppMethods {

	private String			formTitle	= "Automation_QueryForm";
	public static Logger	log			= AutomationLogger.getInstance().getLogger(CreateQueryFormScripts.class);

	public void setAllMandatoryFields() throws InterruptedException {
		formTitle = formTitle + dateUtils.getEpoch();
		sendKeys(ProjectFormsTab.txt_CreateControllerFormTitle, formTitle);
		collectionDataMap.put("API Form", formTitle);
		sendKeys(ProjectFormsTab.txt_CreateControllerFormTitle, Keys.TAB);
		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.txt_EOFFormRichTestBox, javaUtils.getRandomString(30));
		sendKeys(ProjectFormsTab.txt_EOFFormRichTestBox, Keys.TAB);
		waitForCompletePageLoad();
	}

	public void attachMultipleDocuments() {
		//if(appBetaViewFlag){
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCERFormAttachments, ProjectFormsTab.frm_BetaViewFilesAttachmentsFrame);
			uploadMultipleFilesUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path")));
			waitUntilElementCountToBe(ProjectFormsTab.lbl_BetaViewPopAttachFileControllerFormAttachedFile, 2);
			waitForCompletePageLoad();
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave, ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave);
			clickElementAndWait(By.cssSelector("input[id*='my:Query_Confidential,1']"));
			waitForCompletePageLoad();
		/*}
		else{
			clickElementAndWaitForElement(ProjectFormsTab.btn_CERFormAttachments, By.cssSelector("div[id='myModal-Attachment']"));
			uploadMultipleFilesUsingKeys(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles, sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path")));
			waitUntilElementCountToBe(ProjectFormsTab.lbl_PopAttachFileControllerFormAttachedFile, 2);
			waitForCompletePageLoad();
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateControllerFormAttachFileSave, ProjectFormsTab.btn_CreateControllerFormAttachFileSave);
			clickElementAndWait(By.cssSelector("form[id='myform'] div[id='xdoc_view'] input[id*='Query_Confidential,1'][class='xdBehavior_Boolean']"));
			waitForCompletePageLoad();
			
		}*/
}
	public void searchAndVerifyQueryForm() {
		searchForms(formTitle);
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).equalsIgnoreCase(formTitle));
	}
}
