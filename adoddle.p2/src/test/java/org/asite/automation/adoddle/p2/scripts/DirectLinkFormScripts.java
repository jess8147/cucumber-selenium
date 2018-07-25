package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.ClassicFormListingLocators.FormListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DirectLinkFormScripts extends AdoddleCommonAppMethods {

	private String 				directLink;
	private boolean				flag;
	private static String		parentWindow	= null;
	final private String		formTitle		= "AutoDirectLinkForm" + epoch;
	final private static Logger	log				= AutomationLogger.getInstance().getLogger(DirectLinkFormScripts.class);

	/*** Direct Link with Login ***/
	public void createNewForm(String action, String adoddleUserID, String classicUserID) {
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		distributeForm(action, adoddleUserID, classicUserID);
		enterFormNameAndDetails();
	}

	private void distributeForm(String action, String adoddleUserID, String classicUserID) {
		switchDefault();
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		List<String> userList = new ArrayList<String>();

		if (adoddleUserID != null)
			userList.add(adoddleUserID);
		if (classicUserID != null)
			userList.add(classicUserID);

		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton, ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);

		for (String userID : userList) {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, userID);
			waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + userID + "')]"));
			clickElementAndWaitForElement(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + userID + "')]//preceding::input[@type='checkbox']"), ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown);
			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		}
	}

	private void enterFormNameAndDetails() {
		switchDefault();
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		collectionDataMap.put("Direct Link FormTitle", formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateFormCalendarCurrentDate, ProjectFormsTab.btn_CreateFormSendButton);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void verifyCreatedForm() {
		searchForms(formTitle);
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(formTitle));
	}

	public void clickOnForm() {
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormTitle);
	}

	public void switchToSecondWindow() {
		parentWindow = getCurrentWindow();
		switchToWindow(2);
		waitForCompletePageLoad();
	}

	public void verifyViewForm() {
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_BetaViewViewFormTitle), formTitle), getElementText(ProjectFormsTab.ele_BetaViewViewFormTitle).contains(formTitle));
	}

	public void getDirectLinkOfViewedForm() {
		directLink = getElementAttributeValue(ProjectFormsTab.ele_BetaViewViewFormDirectLink,"title");
		collectionDataMap.put("Form Direct Link", directLink);
		log.info("directLink : " + directLink);
		closeSecondWindow();
	}

	public void closeSecondWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
		waitForCompletePageLoad();
	}

	public void openNewTabAndHitDirectLink() {
		executeJScript("window.open('"+directLink+"', '_blank')");
		waitForCompletePageLoad();
		switchToSecondWindow();
	}

	public void verifyFormNotGettingViewedForNoAccessAdoddleUser(String blankFormPageErrorMsg) {
		reloadPage();
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.msg_BetaViewDirectLinkNotAccessableMessage), blankFormPageErrorMsg), getElementText(ProjectFormsTab.msg_BetaViewDirectLinkNotAccessableMessage).contains(blankFormPageErrorMsg));
		if (!flag)
			closeSecondWindow();
	}

	public void verifyFormGettingViewedForAccessAdoddleUser(String action, String historyRemarks, String user) {
		waitForCompletePageLoad();
		verifyViewForm();
		reloadPage();
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormViewFormDetailsButton, ProjectFormsTab.ele_BetaViewViewFormSingleActionButton);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_BetaViewViewFormSingleActionButton), action), getElementText(ProjectFormsTab.ele_BetaViewViewFormSingleActionButton).contains(action));
		clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormHistoryButton);
		
		for (WebElement historyRow : findElements(By.xpath(".//div[@id='form-view-page']//main[@class='open']//div[@class='gbody']//div[@class='ng-scope'][@ng-if]"))) {
			
			if(historyRow.findElement(By.xpath(".//ul//li[@class='gpuser']//a[text()]")).getText().contains(user)) {
				Assert.assertTrue(historyRow.findElement(By.xpath(".//ul//li[@class='gstatus ng-binding'][@title=text()]")).getText().contains(action));
				Assert.assertTrue(historyRow.findElement(By.xpath(".//ul//li[@class='gstatus ng-binding'][@title!=text()]")).getText().contains(historyRemarks));
				break;
			}
		}
		
		if (!flag)
			closeSecondWindow();
	}

	public void verifyFormGettingViewedForAccessClassicUser(String historyRemarks) {
		waitForCompletePageLoad();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteDirectAccessContent);
		Assert.assertTrue(getElementText(FormListingPage.lbl_ViewFormFormTitleLabel).contains(formTitle));
		clickElementAndWaitForElement(FormListingPage.lnk_ViewFormAuditTrailLink, FormListingPage.ele_ViewFormAuditTrailFormActionStatus);
		Assert.assertTrue(getElementText(FormListingPage.ele_ViewFormAuditTrailFormActionStatus).contains(historyRemarks));
		clickElementAndWaitForElement(FormListingPage.lnk_ViewFormAuditTrailBackToFormLink, FormListingPage.lbl_ViewFormActionTimeLabel);
		Assert.assertTrue(getElementText(FormListingPage.lbl_ViewFormActionTimeLabel).contains(historyRemarks));
		if (!flag)
			closeSecondWindow();
	}

	public void verifyFormNotGettingViewedForNoAccessClassicUser(String classicUnauthorisedAccessErrorPage) {
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitUntilElementIsDisplayed(FormListingPage.ele_ViewFormUnauthorisedAccessPage);
		Assert.assertTrue(isDisplayed(By.xpath(".//table//p[@class='subtitle'][text()='" + classicUnauthorisedAccessErrorPage + "']")));
		if (!flag)
			closeSecondWindow();
	}

	public void logoutAndHitDirectLink() {
		flag = true;
		logOut();
		navigateURL(directLink);
	}

	public void verifyLoginPageAndDirectLinkValidationMsg(String directLinkValidationMsg) {
		waitForCompletePageLoad();
		waitAndSwitchIframe(LoginPage.frm_Iframe);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(LoginPage.msg_ErrorMsg),directLinkValidationMsg), getElementText(LoginPage.msg_ErrorMsg).contains(directLinkValidationMsg));
		switchDefault();
	}

	public void setUpBrowserAndHitDirectLink() throws IOException {
		tearDown();
		setUp(ResourceHandler.loadProperty("browser"));
		navigateURL(directLink);
	}

	public void loginInAdoddleWithDirectLink(String username, String password) {
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		switchIframe(LoginPage.frm_Iframe);
		sendKeys(LoginPage.txt_AsiteUsername, username);
		sendKeys(LoginPage.txt_AsitePassword, password);
		clickElement(LoginPage.btn_AsiteLogin);
	}
}
