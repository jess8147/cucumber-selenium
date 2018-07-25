package org.asite.automation.classic.p1.scripts;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.AssociateDocPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.AssociateFormPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.AttachDocPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.DistributionPage;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCommentClassicScripts extends ClassicCommonAppMethods{
	
	private int assocDocCount;
	private String commentTitle;
	private String attachFile1, attachFile2;
	private String assocFirstFormTitle, assocSecondFormTitle;
	private String parentHandle1, parentHandle2, parentHandle3;
	private String testFileDocRef = "CreateCommentAutomationTestData";
	private String associcationFilesDocRef = "BatchPrintAutomationTestDataFile";

	
	public void clickOnDocumentDistributionHistory(String toolTipText) throws InterruptedException
	{
		parentHandle1 = getCurrentWindow();
		searchFiles(testFileDocRef);
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingFirstFileDocRef);
		Assert.assertTrue(getCount(DocListingPage.css_DocListingDocRefList) > 0);
		Assert.assertTrue(getElementText(DocListingPage.lnk_DocListingFirstDocRef).equalsIgnoreCase(testFileDocRef));
		Assert.assertTrue(getToolTipText(DocListingPage.lnk_DocListingFirstDocDistributionHistory).equalsIgnoreCase(toolTipText));
		clickElementAndWait(DocListingPage.lnk_DocListingFirstDocDistributionHistory);
		waitForSwitchWindow(2);
		switchWindow();	
		handleAlertPopup();
		reloadPage();
		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		parentHandle2 = getCurrentWindow();
	}
	
	public void clickOnCommentAndAssociationsLink(String commentAssociationsLink)
	{
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocumentDistributionAccessLink);
		reloadPage();
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocumentDistributionAccessLink);
		clickLinkWithText(commentAssociationsLink);
	}
	
	public void verifyCreateCommentPage(String createCommentHeader)
	{
		waitForSwitchWindow(3);
		switchToThirdWindow(parentHandle1, parentHandle2);
		Assert.assertTrue(getElementText(DocListingPage.lbl_CreateNewCommentHeader).equalsIgnoreCase(createCommentHeader));
	}
	
	public void enterCommentTitle(String cTitle)
	{
		commentTitle = cTitle+epoch;
		waitUntilElementIsDisplayed(DocListingPage.txt_CreateCommentTitleInput);
		sendKeys(DocListingPage.txt_CreateCommentTitleInput, commentTitle);
		collectionDataMap.put("Comment Title", commentTitle);
	}
	
	public void enterCommentText(String commentText)
	{
		commentText = commentText+epoch;
		waitUntilElementIsDisplayed(DocListingPage.txt_CreateCommentCommentInput);
		sendKeys(DocListingPage.txt_CreateCommentCommentInput, commentText);
		
	}
	
	public void addUsersToDistribution(String user, String action)
	{
		parentHandle3 = getCurrentWindow();
		clickElementAndWait(DocListingPage.lnk_CreateCommentDocDistribution);
		switchToForthWindow(parentHandle1, parentHandle2, parentHandle3);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(DistributionPage.sel_DocDistributionselectIndividuals);
		selectGroupType();
		waitUntilElementIsDisplayed(DistributionPage.sel_DocDistributionselectIndividuals);
		selectByVisibleText(DistributionPage.sel_DocDistributionselectCompany, ResourceHandler.loadProperty("auto.test.classic.organization"));
		deselectAllInMultiSelect(DistributionPage.sel_DocDistributionselectIndividuals);
		selectByVisibleText(DistributionPage.sel_DocDistributionselectIndividuals, user);
		clickElementAndWait(DistributionPage.btn_DocDistributionAddToDistList);
		waitUntilElementIsDisplayed(DistributionPage.drp_DocDistributionAction);
		selectByVisibleText(DistributionPage.drp_DocDistributionAction, action);
		waitForCompletePageLoad();
		sendKeys(DistributionPage.txt_DocDistributionActionDate, getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST"));
		clickElement(DistributionPage.btn_DocDistributionDistribute);
		switchPreviousWindow(parentHandle3);
		waitUntilElementIsDisplayed(DocListingPage.lbl_CreateCommentDistributionSingleRecipient);
		Assert.assertTrue(getElementText(DocListingPage.lbl_CreateCommentDistributionSingleRecipient).equalsIgnoreCase(user));
	}
	
	public void attachMultipleDocuments() throws IOException, InterruptedException
	{
		clickElementAndWait(DocListingPage.lnk_CreateCommentAttachExternalDoc);
		switchToForthWindow(parentHandle1, parentHandle2,parentHandle3);
		handleAlertPopup();
		reloadPage();
		handleAlertPopup();
		getWebDriver().findElement(AttachDocPage.btn_AttachDocFirstBrowse).sendKeys(ResourceHandler.loadProperty("single.file.path"));
		attachFile1 = ResourceHandler.loadProperty("single.file.path").split("\\\\")[4];
		waitForCompletePageLoad();
		getWebDriver().findElement(AttachDocPage.btn_AttachDocSecondBrowse).sendKeys(ResourceHandler.loadProperty("form.mailbox.excel.file.path"));
		attachFile2 = ResourceHandler.loadProperty("form.mailbox.excel.file.path").split("\\\\")[4];
		waitForCompletePageLoad();
		clickElement(AttachDocPage.btn_AttachDocStartUpload);
		collectionDataMap.put("Attach File 1", attachFile1);
		collectionDataMap.put("Attach File 2", attachFile2);
		waitForSwitchWindow(3);
		switchPreviousWindow(parentHandle3);
		waitForCompletePageLoad();
		
	}
	
	public void associateMultipleDocuments() throws InterruptedException
	{
		clickElementAndWait(DocListingPage.lnk_CreateCommentAddDocFolder);
		switchToForthWindow(parentHandle1, parentHandle2, parentHandle3);
		handleAlertPopup();
		reloadPage();
		handleAlertPopup();
		waitForCompletePageLoad();
		waitAndSwitchIframe(AssociateDocPage.frm_AssociateDocNotesFrame);
		clickFolderWithTitle("PublicFolder");
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitForCompletePageLoad();
		waitUntilListOfElementIsDisplayed(AssociateDocPage.css_AssociateDocDocumentList);
		sendKeys(DocListingPage.txt_DocListingDocRefInput, associcationFilesDocRef);
		waitForCompletePageLoad();
		sendKeys(DocListingPage.txt_DocListingDocRefInput, Keys.ENTER);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(AssociateDocPage.chk_AssociateDocCheckAll);
		clickElementAndWait(AssociateDocPage.chk_AssociateDocCheckAll);
		assocDocCount = getCount(AssociateDocPage.css_AssociateDocDocumentList);
		clickElement(AssociateDocPage.btn_AssociateDocComplete);
		collectionDataMap.put("Associated files Doc Ref", associcationFilesDocRef);
		waitForSwitchWindow(3);
		switchPreviousWindow(parentHandle3);
		
	}
	
	public void associateMultipleForms() throws InterruptedException
	{
		clickElementAndWait(DocListingPage.lnk_CreateCommentAddForms);
		waitForSwitchWindow(4);
		switchToForthWindow(parentHandle1, parentHandle2, parentHandle3);
		handleAlertPopup();
		reloadPage();
		handleAlertPopup();
		clickElement(AssociateFormPage.chk_AssociateFormFirstForm);
		clickElement(AssociateFormPage.chk_AssociateFormSecondForm);
		assocFirstFormTitle = getElementText(AssociateFormPage.lbl_AssociateFormFirstFormTitle);
		assocSecondFormTitle = getElementText(AssociateFormPage.lbl_AssociateFormSecondFormTitle);
		clickElement(AssociateFormPage.btn_AssociateFormComplete);
		waitForSwitchWindow(3);
		collectionDataMap.put("Associated first form", assocFirstFormTitle);
		collectionDataMap.put("Associated second form ", assocSecondFormTitle);
		switchPreviousWindow(parentHandle3);
		
	}
	
	public void verifyCommentIsCreated()
	{
		
		log.info("Verification covered in next step definition");		
	}
	
	public void clickOnSendButton(String buttonText)
	{
		waitUntilElementIsDisplayed(DocListingPage.btn_CreateCommentSendTop);
		Assert.assertTrue(getValue(DocListingPage.btn_CreateCommentSendTop).equalsIgnoreCase(buttonText));
		clickElement(DocListingPage.btn_CreateCommentSendTop);
		waitForSwitchWindow(2);
		switchPreviousWindow(parentHandle2);
	}
	
	public void verifyCommentOnListingPage(String pageTitle)
	{
		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(pageTitle));
		waitUntilElementIsDisplayed(DocListingPage.lbl_CreateCommentListingTableFirstComment);
		Assert.assertTrue(getElementText(DocListingPage.lbl_CreateCommentListingTableFirstComment).contains(commentTitle));
		
	}
	
	public void clickOnViewCommentDetails(String linkText)
	{
		clickLinkWithText(linkText);
		waitForSwitchWindow(3);
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitForCompletePageLoad();
	}
	
	public void verifyCommentDetailWindow()
	{
		waitUntilElementIsDisplayed(DocListingPage.lbl_CreateCommentDetailsCommentTitle);
		Assert.assertTrue(getElementText(DocListingPage.lbl_CreateCommentDetailsCommentTitle).contains(commentTitle));
	}
	
	public void verifyCommentDocsAssociations()
	{
		List<WebElement> assocDocsRefLinks = findElements(DocListingPage.css_CreateCommentDetailsAssociatedDocs);
		Assert.assertTrue(assocDocsRefLinks.size() == assocDocCount);
		
		for(WebElement docRefLink : assocDocsRefLinks)
		{
			Assert.assertTrue(docRefLink.getText().contains(associcationFilesDocRef));
		}
		
	}
	
	public void verifyCommentFormsAssociations()
	{
		List<WebElement> assocFormsLabels = findElements(DocListingPage.css_CreateCommentDetailsAssociatedForms);
		Assert.assertTrue(assocFormsLabels.size() == 2);
		
		for(WebElement label : assocFormsLabels)
		{
			Assert.assertTrue(label.getText().equalsIgnoreCase(assocFirstFormTitle) || label.getText().equalsIgnoreCase(assocSecondFormTitle));
		}
	}
	
	public void verifyCommentDocsAttachments()
	{
		List<WebElement> attachedDocsLinks = findElements(DocListingPage.css_CreateCommentDetailsAttachedDocs);
		Assert.assertTrue(attachedDocsLinks.size() == 2);
		for(WebElement label : attachedDocsLinks)
			Assert.assertTrue(label.getText().equalsIgnoreCase(attachFile1) || label.getText().equalsIgnoreCase(attachFile2));
		closeCurrentWindow();
		switchPreviousWindow(parentHandle2);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
	}
	
	public void switchToForthWindow(String w1, String w2, String w3)
	{
		waitForSwitchWindow(4);
		Set<String> handles = getWindowHandles();
		for (String windowHandle : handles) 
		{
			if (!windowHandle.equals(w1) && !windowHandle.equals(w2) && !windowHandle.equals(w3)) 
			{
				getWebDriver().switchTo().window(windowHandle);
				break;
			}
		}
		
	}
}
