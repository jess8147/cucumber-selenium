package org.asite.automation.classic.p1.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asite.automation.CommonLocators.ClassicAssociateLocators.AssociateDocPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.AssociateFormPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.AttachDocPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.DistributionPage;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicFormListingLocators.FormListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicMyHomeLocators.MyHomePage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateFormClassicScripts extends ClassicCommonAppMethods {

	private String parentHandle1, parentHandle2, assocFirstFormTitle, assocSecondFormTitle;
	private String associcationFilesDocRef = "BatchPrintAutomationTestDataFile";
	private String formTitleText = "Automation_Form_Title" + getCurrentDate();
	private List<String> getDataList = new ArrayList<String>();
	private List<String> verifyDataList = new ArrayList<String>();
	private String dueDate;
	private String formLinkName;

	public void clickOnFormLink(String formName) {
		formLinkName = formName;
		switchMultiFrames();
		clickLinkWithText(formName);
		waitForCompletePageLoad();
	}

	public void clickCreateFormIcon(String formName) {
		dueDate = getDueDate();
		getDataList.add(dueDate);
		switchMultiFrames();
		waitUntilElementIsDisplayed(FormListingPage.img_ProjectFormCreateForm);
		clickElementAndWait(FormListingPage.img_ProjectFormCreateForm);

	}

	public void verifyCreateFormWindowOpens(String formTitle) {
		parentHandle1 = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		if (formTitle.equalsIgnoreCase("Create PNCF"))
			waitUntilElementIsDisplayed(FormListingPage.txt_CreateFormPNFCTitle);
		else if (formTitle.equalsIgnoreCase("Create Form"))
			waitUntilElementIsDisplayed(FormListingPage.txt_CreateFormSubject);
	}

	public void addUsersToDistribution(String user, String action) {
		parentHandle2 = getCurrentWindow();
		clickElementAndWait(FormListingPage.lnk_CreateFormDistribution);
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(DistributionPage.sel_DocDistributionselectIndividuals);
		selectGroupType();
		waitUntilElementIsDisplayed(DistributionPage.sel_DocDistributionselectIndividuals);
		selectByVisibleText(DistributionPage.sel_DocDistributionselectCompany,
				ResourceHandler.loadProperty("auto.test.classic.organization"));
		deselectAllInMultiSelect(DistributionPage.sel_DocDistributionselectIndividuals);
		selectByVisibleText(DistributionPage.sel_DocDistributionselectIndividuals, user);
		clickElementAndWait(DistributionPage.btn_DocDistributionAddToDistList);
		waitUntilElementIsDisplayed(DistributionPage.drp_DocDistributionAction);
		getDataList.add(action);
		selectByVisibleText(DistributionPage.drp_DocDistributionAction, action);
		waitForCompletePageLoad();
		sendKeys(DistributionPage.txt_DocDistributionActionDate, dueDate);

		clickElement(DistributionPage.btn_DocDistributionDistribute);
		switchPreviousWindow(parentHandle2);
		waitUntilElementIsDisplayed(FormListingPage.lbl_CreateFormDistributionFirstRecipient);
		Assert.assertTrue(getElementText(FormListingPage.lbl_CreateFormDistributionFirstRecipient).equalsIgnoreCase(
				user));
	}

	public void fillMandatoryFields(String form) {
		if (form.contains("RFI")) {
			waitUntilElementIsDisplayed(FormListingPage.txt_CreateFormSubject);
			sendKeys(FormListingPage.txt_CreateFormSubject, formTitleText);
			collectionDataMap.put("Form Title :", formTitleText);
			
			log.info("Form Title :");
			
			sendKeys(FormListingPage.txt_CreateFormDescription, javaUtils.getRandomString(20));
		} else if (form.contains("PNCF")) {
			waitUntilElementIsDisplayed(FormListingPage.txt_CreateFormPNFCTitle);
			sendKeys(FormListingPage.txt_CreateFormPNFCTitle, formTitleText);
			collectionDataMap.put("Form Title", formTitleText);
			
			log.info("Form Title :");
			
			sendKeys(FormListingPage.txt_CreateFormPNFCUSerRef, "UserRef" + epoch);
			sendKeys(FormListingPage.txt_CreateFormPNFCDescription, javaUtils.getRandomString(20));
			sendKeys(FormListingPage.txt_CreateFormPNFCHyperLink, getCurrentWindowURL());
			sendKeys(FormListingPage.txt_CreateFormPNFCHyperLink, getCurrentWindowURL());
			clickElementAndWaitForElement(FormListingPage.lnk_CreateFormPNFCRespondDatePickerImg,
					FormListingPage.img_CreateFormPNFCRespondDatePickerCurrentDate);
			clickElementAndWait(FormListingPage.img_CreateFormPNFCRespondDatePickerCurrentDate);

		}

	}

	public void attachMultipleDocuments(String buttonText) throws IOException, InterruptedException {
		clickElementAndWait(FormListingPage.img_CreateFormAttachExternalDoc);
		switchToThirdWindow(parentHandle1, parentHandle2);
		reloadPage();
		waitForCompletePageLoad();
		getWebDriver().findElement(AttachDocPage.btn_AttachDocFirstBrowse).sendKeys(ResourceHandler.loadProperty("single.file.path"));
		waitForCompletePageLoad();
		collectionDataMap.put("First attachment File :", ResourceHandler.loadProperty("single.file.path"));
		getWebDriver().findElement(AttachDocPage.btn_AttachDocSecondBrowse).sendKeys(ResourceHandler.loadProperty("form.mailbox.excel.file.path"));
		waitForCompletePageLoad();
		collectionDataMap.put("Second attachment File :", ResourceHandler.loadProperty("form.mailbox.excel.file.path"));
		clickElement(AttachDocPage.btn_AttachDocStartUpload);
		waitForSwitchWindow(2);
		switchPreviousWindow(parentHandle2);
		waitForCompletePageLoad();

	}

	public void associateMultipleDocuments() throws InterruptedException {
		clickElementAndWait(FormListingPage.lnk_CreateFormAddDocFolder);
		switchToThirdWindow(parentHandle1, parentHandle2);
		waitForCompletePageLoad();
		waitAndSwitchIframe(By.cssSelector("frameset[resize='yes'] frame[name='notes']"));
		clickFolderWithTitle("PublicFolder");
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(DocListingPage.chk_DocListingCheckAllChekbox);
		waitUntilElementIsDisplayed(DocListingPage.txt_DocListingDocRefInput);
		sendKeys(DocListingPage.txt_DocListingDocRefInput, associcationFilesDocRef);
		waitForCompletePageLoad();
		collectionDataMap.put("Associate Files", associcationFilesDocRef);
		sendKeys(DocListingPage.txt_DocListingDocRefInput, Keys.ENTER);
		waitForCompletePageLoad();
		waitUntilElementIsClickable(AssociateDocPage.chk_AssociateDocCheckAll);
		clickElement(AssociateDocPage.chk_AssociateDocCheckAll);
		clickElement(AssociateDocPage.btn_AssociateDocComplete);
		waitForSwitchWindow(2);
		switchPreviousWindow(parentHandle2);

	}

	public void associateMultipleForms() throws InterruptedException {
		clickElementAndWait(FormListingPage.lnk_CreateFormAddForms);
		waitForSwitchWindow(3);
		switchToThirdWindow(parentHandle1, parentHandle2);
		handleAlertPopup();
		reloadPage();
		waitUntilElementIsDisplayed(AssociateFormPage.chk_AssociateFormFirstForm);
		clickElement(AssociateFormPage.chk_AssociateFormFirstForm);
		clickElement(AssociateFormPage.chk_AssociateFormSecondForm);
		assocFirstFormTitle = getElementText(AssociateFormPage.lbl_AssociateFormFirstFormTitle);
		assocSecondFormTitle = getElementText(AssociateFormPage.lbl_AssociateFormSecondFormTitle);
		clickElement(AssociateFormPage.btn_AssociateFormComplete);
		collectionDataMap.put("Associate Forms : ", assocFirstFormTitle);
		collectionDataMap.put("Associate Forms : ", assocSecondFormTitle);
		waitForSwitchWindow(2);
		switchPreviousWindow(parentHandle2);

	}

	public void clickCreateButton() {
		waitUntilElementIsDisplayed(FormListingPage.btn_CreateFormCreate);
		mouseHover(FormListingPage.btn_CreateFormCreate);
		waitUntilElementIsClickable(FormListingPage.btn_CreateFormCreate);
		clickElement(FormListingPage.btn_CreateFormCreate);
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle1);
	}

	public void verifyFormIsCreated() {

		log.info("Verification covered in next step definition");
	}

	public void verifyFormIsAvailableOnListing() throws InterruptedException {
		switchMultiFrames();
		searchForms(formTitleText);
		Assert.assertTrue(getElementText(FormListingPage.lbl_ProjectFormListingFirstFormTitle).equalsIgnoreCase(
				formTitleText));
	}

	public void verifyAttachedDocuments() {
		clickElementAndWait(FormListingPage.lnk_ProjectFormListingFirstFormID);
		waitForSwitchWindow(2);
		switchWindow();

		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(FormListingPage.ele_ProjectFormDetailsAttachedDocList);

		Assert.assertTrue(getElementText(FormListingPage.ele_ProjectFormDetailsAttachedDocList).contains(
				ResourceHandler.loadProperty("single.file.path").split("\\\\")[4]));
		Assert.assertTrue(getElementText(FormListingPage.ele_ProjectFormDetailsAttachedDocList).contains(
				ResourceHandler.loadProperty("form.mailbox.excel.file.path").split("\\\\")[4]));
	}

	public void verifyFormAssociations() {

		Assert.assertTrue(getCount(FormListingPage.css_ProjectFormDetailsAssociatedFormTitleList) > 0);
		List<WebElement> titleElements = findElements(FormListingPage.css_ProjectFormDetailsAssociatedFormTitleList);

		for (WebElement t : titleElements) {
			Assert.assertTrue(t.getText().equalsIgnoreCase(assocFirstFormTitle)
					|| t.getText().equalsIgnoreCase(assocSecondFormTitle));
		}

	}

	public void switchMultiFrames() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

	public String getDueDate() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		return getElementText(LandingPage.lbl_AsiteServerCurrentDate).split(" ")[0];
	}

	public void gotoDistributedUser() throws InterruptedException, IOException {
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		signOut();
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
	}

	public void verifyDistributionActionAndDate(String projectName) throws InterruptedException, IOException {
		gotoDistributedUser();
		if (trainingFlag) {
			clickElementAndWait(MyHomePage.tab_Training);
			switchDefault();
			waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
			waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
		}
		navigateToWorkSpace(projectName);
		clickOnFormLink(formLinkName);
		verifyFormIsAvailableOnListing();
		clickElementAndSwitchWindow(FormListingPage.lnk_ProjectFormListingFirstFormID);
		getActionAndDueDateIntoAuditTrail();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
	}

	public void getActionAndDueDateIntoAuditTrail() {
		waitUntilElementIsDisplayed(FormListingPage.img_CreateFormAuditHistory);
		clickElementAndWait(FormListingPage.img_CreateFormAuditHistory);

		verifyDataList.add(getElementText(FormListingPage.lbl_CreateFormAuditHistoryRecipientAction));
		verifyDataList.add(getElementText(FormListingPage.lbl_CreateFormAuditHistoryActionDueDate));

		log.info("getDataList : " + getDataList);
		log.info("verifyDataList : " + verifyDataList);
		compareList(getDataList, verifyDataList);
	}
}
