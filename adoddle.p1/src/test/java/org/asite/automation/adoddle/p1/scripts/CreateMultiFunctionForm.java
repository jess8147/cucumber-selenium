package org.asite.automation.adoddle.p1.scripts;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.Keys;

public class CreateMultiFunctionForm extends AdoddleCommonAppMethods {

	private String	parentHandle, masterFormTitle, childFormTitle;

	public void enterUserRefAndTitle() throws InterruptedException {
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormUserRef, javaUtils.getRandomString(5).toUpperCase());
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormUserRef, Keys.TAB);
		clickElementAndWait(ProjectFormsTab.btn_MultiFunctionFormSetFormTitle);
		waitForElementValueToBe(ProjectFormsTab.txt_MultiFunctionFormTitle, AdoddleCommonStringPool.MASTER_FORM_TITLE.trim(), 30);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(ProjectFormsTab.txt_MultiFunctionFormTitle), AdoddleCommonStringPool.MASTER_FORM_TITLE), getValue(ProjectFormsTab.txt_MultiFunctionFormTitle).trim().equalsIgnoreCase(AdoddleCommonStringPool.MASTER_FORM_TITLE.trim()));
		masterFormTitle = "MasterForm_" + dateUtils.getEpoch();
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormTitle, masterFormTitle);
		collectionDataMap.put("Master Form", masterFormTitle);

	}

	public void attachSingleAndMultipleDocuments() {
		parentHandle = clickElementAndSwitchWindow(ProjectFormsTab.lnk_MultiFunctionFormAttachSingleFile);
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormAttachFileUpload, ResourceHandler.loadProperty("single.file.path"));
		clickElement(ProjectFormsTab.btn_MultiFunctionFormAttachFileOK);
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		attachSingleFileForMultipleAttachments();
		clickElementAndWaitForElement(ProjectFormsTab.img_MultiFunctionFormInsertItem, ProjectFormsTab.lnk_MultiFunctionFormAttachMultipleFiles);
		attachSingleFileForMultipleAttachments();
	}

	public void enterCallBackDetails() throws InterruptedException {
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormYourSalary, JavaUtils.getRandomNumber(5));
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormYourSalary, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormPartnerSalary, JavaUtils.getRandomNumber(5));
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormPartnerSalary, Keys.TAB);

	}

	public void enterWithOutCallBackDetails() throws InterruptedException {
		childFormTitle = "ChildForm_" + dateUtils.getEpoch();
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormChildFormTitle, childFormTitle);
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormChildFormTitle, Keys.TAB);
		collectionDataMap.put("Child Form", childFormTitle);
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormNumber1, JavaUtils.getRandomNumber(5));
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormNumber1, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormNumber2, JavaUtils.getRandomNumber(5));
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormNumber2, Keys.TAB);
		clickElementAndWait(ProjectFormsTab.txt_MultiFunctionFormChildFormTitle);
		int sum2 = Integer.parseInt(getValue(ProjectFormsTab.txt_MultiFunctionFormNumber1)) + Integer.parseInt(getValue(ProjectFormsTab.txt_MultiFunctionFormNumber2));
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(Integer.parseInt(getValue(ProjectFormsTab.txt_MultiFunctionFormTotalOfNumber)), sum2), Integer.parseInt(getValue(ProjectFormsTab.txt_MultiFunctionFormTotalOfNumber).replace(",", "")) == sum2);
		int sum1 = Integer.parseInt(getValue(ProjectFormsTab.txt_MultiFunctionFormYourSalary).replace(",", "")) + Integer.parseInt(getValue(ProjectFormsTab.txt_MultiFunctionFormPartnerSalary).replace(",", ""));
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(Integer.parseInt(getElementText(ProjectFormsTab.txt_MultiFunctionFormTotalNumber).replace(",", "")), sum1), Integer.parseInt(getElementText(ProjectFormsTab.txt_MultiFunctionFormTotalNumber).replace(",", "")) == sum1);
	}

	public void enterAutoCreateAndDistributionDetails() throws InterruptedException {
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		selectByVisibleText(ProjectFormsTab.drp_MultiFunctionFormDistributeUser, resourceUtils.getSecondaryTestAutomationUser(dataCenter)+AdoddleCommonStringPool.COMMA_STRING+AdoddleCommonStringPool.SPACE_STRING+resourceUtils.getSecondaryUserOrg());
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
	}

	public void verifyMasterFormIsCreated() {
		switchDefault();
		searchForms(masterFormTitle);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle), masterFormTitle), getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle).equalsIgnoreCase(masterFormTitle));
	}

	public void verifyChildFormIsCreated() {
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(System.getProperty("global.test.project"));
		searchForms(childFormTitle);
		
		try{
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle), masterFormTitle), getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle).equalsIgnoreCase(masterFormTitle));
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lnk_FormListingSecondFormTitle), childFormTitle), getElementText(ProjectFormsTab.lnk_FormListingSecondFormTitle).equalsIgnoreCase(childFormTitle));
		}
		catch(Throwable t) {
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle), childFormTitle), getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle).equalsIgnoreCase(childFormTitle));
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lnk_FormListingSecondFormTitle), masterFormTitle), getElementText(ProjectFormsTab.lnk_FormListingSecondFormTitle).equalsIgnoreCase(masterFormTitle));
		}
		
	}

	public void verifyDistributionAction(String action) throws InterruptedException {
//		switchToUser(resourceUtils.getSecondaryTestAutomationUser(dataCenter));
		logOut();
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		navigateTab(LandingPage.lnk_ProjectForms);
		clickElementWithText(System.getProperty("global.test.project"));
		searchForms(masterFormTitle);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lnk_FormsListingFirstAction), action), getElementText(ProjectFormsTab.lnk_FormsListingFirstAction).contains(action));
		searchForms(childFormTitle);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lnk_FormsListingFirstAction), action), getElementText(ProjectFormsTab.lnk_FormsListingFirstAction).contains(action));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lnk_FormsListingSecondAction), action), getElementText(ProjectFormsTab.lnk_FormsListingSecondAction).contains(action));
	}

	/*** Common Methods ***/

	public void attachSingleFileForMultipleAttachments() {
		try {
			sysUtils.authenticateRemoteMachine(nodeIP);
		}
		catch (Throwable t) {
			log.info("ERROR: authentication to remote machine failed..");
		}
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_MultiFunctionFormAttachMultipleFiles);
		String filePath1 = nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION;
		sysUtils.authenticateRemoteMachine(nodeIP);
		sendKeys(ProjectFormsTab.txt_MultiFunctionFormAttachFileUpload, sysUtils.createRemotePdfFile(filePath1, nodeIP));
		clickElement(ProjectFormsTab.btn_MultiFunctionFormAttachFileOK);
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
	}

}
