package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.Outlook365Locators;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

public class SharedLinkScripts extends AdoddleCommonAppMethods {

	private String			emailMessage, sharedFileName, epoch = dateUtils.getEpoch();
	public static Logger	log	= AutomationLogger.getInstance().getLogger(SharedLinkScripts.class);

	public void contextClickDocument(String documentName) {
		sharedFileName = documentName;
		searchFiles(documentName);
		contextClickWithLink(documentName);
		waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickMenu);
	}

	public void dragMouseOnShare() {
		dragMouse(FilesTab.opt_FileContextClickShare);
	}

	public void clickOnShareAdoddleLink(String elementText) {
		clickElementWithText(elementText);
	}

	public void setVisibilityAndExpiry(String visibility, String expiryDetails) {
		String[] expiryValues = expiryDetails.split(" ");
		clickElementAndWaitForElement(FilesTab.lnk_PopShareLinkSetVisibilityExpiry, FilesTab.rad_PopShareLinkVisibilityAnyoneWithLink);
		if (visibility.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_FILE_SHARE_PERMISSION_ANYONE)) {
			clickElementAndWait(FilesTab.rad_PopShareLinkVisibilityAnyoneWithLink);
			clickElementAndWait(FilesTab.rad_PopShareLinkExpiresIn);
			selectByVisibleText(FilesTab.sel_PopShareLinkTimeHours, expiryValues[0]);
			selectByVisibleText(FilesTab.sel_PopShareLinkTimeUnit, expiryValues[1]);
		}
		else
			clickElementAndWait(FilesTab.rad_PopShareLinkVisibilityWithAccess);
		clickElementAndWait(FilesTab.btn_PopShareLinkPermissionSettingSave);
	}

	public void enterEmailAddress(String userEmail) {
		sendKeys(FilesTab.txt_PopShareLinkEmail, userEmail);
		emailMessage = javaUtils.getRandomString(20) + " - " + epoch;
		sendKeys(FilesTab.txt_PopShareLinkEmailMessage, emailMessage);
		collectionDataMap.put("Email message body", emailMessage);
	}

	public void clickOnSendButton() {
		clickElementAndWait(FilesTab.btn_PopShareAdoddleLinkSend);
	}

	public void verifyReceivedEmail(String emailID) throws IOException {
		logOut();
		tearDown();
		setUp(ResourceHandler.loadProperty("browser"));
		signInToOutLook365WebMail(emailID, ResourceHandler.getPropertyValue("asite.web.mail.password"));
		/*sendKeys(GlobalPageElements.txt_WebMailUserNameInput, emailID);
		sendKeys(GlobalPageElements.txt_WebMailPasswordInput, ResourceHandler.getPropertyValue("asite.web.mail.password"));
		clickElementAndWait(GlobalPageElements.btn_WebMailSignIn);*/
	}

	public void clickOnReceivedEmailLink() throws InterruptedException {
		if(isDisplayed(Outlook365Locators.img_Office365InboxChevronDown))
			clickElementAndWaitForElement(Outlook365Locators.img_Office365InboxChevronDown, Outlook365Locators.img_Office365InboxChevronUp);
		if (sharedFileName.contains("Private"))
			clickElementWithText(ResourceHandler.loadProperty("shared.link.private.email.folder"));
		else
			clickElementWithText(ResourceHandler.loadProperty("shared.link.public.email.folder"));

		for (int index = 0; index < Integer.parseInt(ResourceHandler.loadProperty("wait.timeout")); index++) {
			if (isDisplayed(Outlook365Locators.ele_Office365FirstEmailUnreadSubject)) {
				clickElementAndWait(Outlook365Locators.ele_Office365FirstEmailUnreadSubject);
				if (getElementText(Outlook365Locators.ele_Office365FirstEmailUnreadSubject).contains(sharedFileName) && getElementText(Outlook365Locators.ele_Office365MailContentMessage).contains(epoch))
					break;
				else
					waitUtils.waitInterval(2);
			}
			else
				waitUtils.waitInterval(2);
		}
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(Outlook365Locators.ele_Office365MailContentMessage), (emailMessage)), getElementText(Outlook365Locators.ele_Office365MailContentMessage).contains(emailMessage));
		clickElementAndWait(Outlook365Locators.lnk_Office365ShareLinkEmailClickHere);

		/*clickElementAndWait(GlobalPageElements.img_WebMailInboxExpander);
		if (sharedFileName.contains("Private"))
			clickElementWithText(ResourceHandler.loadProperty("shared.link.private.email.folder"));
		else
			clickElementWithText(ResourceHandler.loadProperty("shared.link.public.email.folder"));
		for (int index = 0; index < Integer.parseInt(ResourceHandler.loadProperty("wait.timeout")); index++) {
			if (isDisplayed(GlobalPageElements.ele_WebMailFirstEmailUnreadSubject)) {
				clickElementAndWait(GlobalPageElements.ele_WebMailFirstEmailUnreadSubject);
				if (getElementText(GlobalPageElements.ele_WebMailFirstEmailUnreadSubject).contains(sharedFileName) && getElementText(GlobalPageElements.ele_WebMailContent).contains(epoch))
					break;
				else
					waitUtils.waitInterval(2);
			}
			else
				waitUtils.waitInterval(2);
		}
		Assert.assertTrue(eStringUtils.getContainsStringError(getElementText(GlobalPageElements.ele_WebMailContent), (emailMessage)), getElementText(GlobalPageElements.ele_WebMailContent).contains(emailMessage));
		clickElementAndWait(GlobalPageElements.lnk_WebMailEmailClickHere);*/
	}

	public void verifyDocumentOpened(String sharedFileName) throws InterruptedException {
		waitForSwitchWindow(2);
		switchWindow();
		/*clickElementAndWait(LoginPage.lnk_login);*/
		login(System.getProperty("primary.username"), System.getProperty("primary.password"));
		waitUntilElementIsDisplayed(FilesTab.frm_BravaObjectFrame);
		waitForHTMLFileView(sharedFileName);

	}

	@Override
	public void login(String username, String password) {
		log.info("Login Operation Started for: " + username + "\tTHID :" + Thread.currentThread().getId());
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		switchIframe(LoginPage.frm_Iframe);
		waitUntilElementIsDisplayed(LoginPage.txt_AsiteUsername);
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).clear();
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).sendKeys(username);
		getWebDriver().findElement(LoginPage.txt_AsitePassword).clear();
		getWebDriver().findElement(LoginPage.txt_AsitePassword).sendKeys(password);
		getWebDriver().findElement(LoginPage.btn_AsiteLogin).click();
	}
}
