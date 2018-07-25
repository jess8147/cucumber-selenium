/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.Outlook365Locators;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class EmbededEmailResponseScripts extends AdoddleCommonAppMethods {

	private String				formTitle					= null, onlineUserResponse, paperUserResponse, parentWindow1;
	private static String		emailUser, emailPassword;
	private static final Logger		log							= AutomationLogger.getInstance().getLogger(EmbededEmailResponseScripts.class);

	public void clickCreateFormButton(String form) {
		parentWindow1 = getCurrentWindow();
		clickElementWithText(form);
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateForm, ProjectFormsTab.frm_createFormIframe);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
	}

	public void enterFormTitle(String title) {
		formTitle = title + epoch;
		sendKeys(ProjectFormsTab.txt_CERFormTitle, formTitle);
		collectionDataMap.put("form title", formTitle);
	}

	public void attachMultipleFiles() {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon, ProjectFormsTab.pop_BetaViewCreateControllerFormAttachments);
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsSelectFiles, sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path")));
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsAttach, ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsAttach);
	}

	public void associateMultipleFiles() {
		List<WebElement>	associatedFileCheckboxes;
		List<WebElement>	associatedFileNameElements;
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateFormAssociationIcon, ProjectFormsTab.lnk_BetaViewCreateFormMoreOptionsAssociateDocs);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewCreateFormMoreOptionsAssociateDocs, ProjectFormsTab.lnk_BetaViewCreateFormAssociateDocsPaginationNext);
		waitUntilElementCountToBeMoreThan(ProjectFormsTab.css_BetaViewPopAssociateFilesCheckboxes, 15);
		associatedFileCheckboxes = findElements(ProjectFormsTab.css_BetaViewPopAssociateFilesCheckboxes);
		associatedFileNameElements = findElements(ProjectFormsTab.css_BetaViewCERPopAssociateFilesFileNames);
		log.info("Associations Checkbox Elements Size:" + associatedFileCheckboxes.size());
		log.info("Associations Name Elements Size:" + associatedFileNameElements.size());

		for (int index = 0; index < 3; index++)
			associatedFileCheckboxes.get(index).click();
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BetaViewPopCreateFormAssociateDocsAssociate, ProjectFormsTab.btn_BetaViewPopCreateFormAssociateDocsAssociate);
	}


	public void distributeForm(String primaryUser, String paperUser) throws InterruptedException {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton, ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, primaryUser);
		waitUntilElementCountToBe(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox, 1);
		clickElementAndWait(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox);
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, paperUser);
		waitUntilElementCountToBe(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox, 1);
		clickElementAndWait(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox);
		clear(ProjectFormsTab.txt_CERFormGroupCode);
	}

	public void clickSendButtonToCreateForm() {
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.txt_CERFormDueDatePicker, ProjectFormsTab.ele_CERFormDatePickerSelectedDate);
		clickElementAndWait(ProjectFormsTab.ele_CERFormDatePickerSelectedDate);
		mouseHoverAndClickElement(ProjectFormsTab.btn_BetaViewCreateRFIFormSave, ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
		waitUntilElementIsInvisible(ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
		waitForSwitchWindow(1);
		switchPreviousWindow(parentWindow1);
	}

	public void verifyFormIsAvailableOnListing() {
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		searchForms(formTitle);
		AutomationAssert.verifyTrue(getCount(ProjectFormsTab.css_ProjectFormListingCount) == 1);
	}

	public void loginWebMailWithDCUser(String user, String password) throws IOException {
		emailUser = user;
		emailPassword = password;
		signInToOutLook365WebMail(emailUser, emailPassword);
	}

	public void verifyEmbededFormInEmail() throws InterruptedException {

		if(isDisplayed(Outlook365Locators.img_Office365InboxChevronDown))
			clickElementAndWaitForElement(Outlook365Locators.img_Office365InboxChevronDown, Outlook365Locators.img_Office365InboxChevronUp);
		
		if(formTitle.contains("Download"))
			clickElementWithText(ResourceHandler.loadProperty("embeded.email.form.download.folder"));
		else 
			clickElementWithText(ResourceHandler.loadProperty("embeded.email.form.response.folder"));


		for (int index = 0; index < 30; index++) {
			if (isDisplayed(Outlook365Locators.ele_Office365FirstEmailUnreadSubject)
					&& isDisplayed(Outlook365Locators.ele_Office365SecondEmailUnreadSubject) 
					&& getElementText(Outlook365Locators.ele_Office365FirstEmailUnreadSubject).contains(formTitle) 
					&& getElementText(Outlook365Locators.ele_Office365SecondEmailUnreadSubject).contains(formTitle)) {
				clickElementAndWait(Outlook365Locators.ele_Office365FirstEmailUnreadSubject);
				if (getElementText(Outlook365Locators.ele_Office365MailContent).contains("Jasmin,") || getElementText(Outlook365Locators.ele_Office365MailContent).contains("Vishal,"))
					break;
				else {
					clickElementAndWait(Outlook365Locators.ele_Office365SecondEmailUnreadSubject);
					if (getElementText(Outlook365Locators.ele_Office365MailContent).contains("Jasmin,") || getElementText(Outlook365Locators.ele_Office365MailContent).contains("Vishal,"))
						break;
				}
			}
			else {
				if(isDisplayed(Outlook365Locators.ele_Office365FirstEmailSubject)) {
					clickElementAndWait(Outlook365Locators.ele_Office365FirstEmailSubject);
				}
				waitUtils.waitInterval(15);
			}
		}
		
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(Outlook365Locators.lbl_Office365EmbededMailContentTableFormTitle), formTitle), getElementText(Outlook365Locators.lbl_Office365EmbededMailContentTableFormTitle).contains(formTitle));
		
	}

	public void verifyFormAttachmentsDownload() throws IOException, InterruptedException {
		String defaultDownloadPath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path");
		File defaultDownloadFile = null;
		int counter = 0;


		String filePath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch + AdoddleCommonStringPool.ZIP_EXTENSION;
		parentWindow1 = getCurrentWindow();
		clickLinkWithText(AdoddleCommonStringPool.DOWNLOAD_ATTACHMENT_LINK_TEXT);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		closeCurrentWindow();
		switchPreviousWindow(parentWindow1);
		clickElementAndWait(Outlook365Locators.ele_Office365SecondEmailUnreadSubject);
		if (getElementText(Outlook365Locators.ele_Office365SecondEmailUnreadSubject).contains(formTitle) && getElementText(Outlook365Locators.ele_Office365MailContent).contains("DC (Paper),")) {
			clickLinkWithText(AdoddleCommonStringPool.DOWNLOAD_ATTACHMENT_LINK_TEXT);
			waitForSwitchWindow(2);
			switchWindow();
			waitForCompletePageLoad();
			clickElementAndWait(GlobalPageElements.btn_DownloadDocAssocClickHereToSaveFile);
			waitUtils.waitInterval(1);
			for(String file: sysUtils.getFileListOfSystemFolder(defaultDownloadPath)) {
				if((file.contains("MessageDocAssociations") || counter > 120)) {
					defaultDownloadFile = new File(defaultDownloadPath + file);
					log.info("Default download file name : "+defaultDownloadFile.toString());
					break;
				}
				else {
					waitUtils.waitInterval(1);
					counter++;
				}
			}

			waitUntilFileIsDownloaded(new File(defaultDownloadFile.toString().replace(".crdownload", "")));
			closeCurrentWindow();
			switchPreviousWindow(parentWindow1);
		}
		defaultDownloadFile.renameTo(new File(filePath));

	}

	public void replyToOnlineUserEmail(String userType) throws IOException {

		tearDown();
		TestDriverFactory.getInstance().setUp(ResourceHandler.loadProperty("browser"));
		signInToOutLook365WebMail(emailUser, emailPassword);
		clickElementWithText(ResourceHandler.loadProperty("embeded.email.form.download.folder"));

		if (userType.equalsIgnoreCase("online")) {
			clickElementAndWaitForElement(Outlook365Locators.ele_Office365FirstEmailSubject, Outlook365Locators.btn_Office365EmailReplyAll);
			clickElementAndWaitForElement(Outlook365Locators.btn_Office365EmailReplyAll, Outlook365Locators.txt_Office365EmailReplyTextArea);
			clickElementAndWait(Outlook365Locators.txt_Office365EmailReplyTextArea);
			onlineUserResponse = javaUtils.getRandomString(20);
			getWebDriver().findElement(Outlook365Locators.txt_Office365EmailReplyTextArea).sendKeys(onlineUserResponse);
			clickElementAndWaitForInvisibilityOfElement(Outlook365Locators.btn_Office365EmailReplySend, Outlook365Locators.btn_Office365EmailReplySend);
			waitUntilElementContainsText(Outlook365Locators.ele_Office365EmailRepliedContent, onlineUserResponse);
		}
		else if (userType.equalsIgnoreCase("paper")) {
			clickElementAndWaitForElement(Outlook365Locators.ele_Office365SecondEmailSubject, Outlook365Locators.btn_Office365EmailReplyAll);
			clickElementAndWaitForElement(Outlook365Locators.btn_Office365EmailReplyAll, Outlook365Locators.txt_Office365EmailReplyTextArea);
			clickElementAndWait(Outlook365Locators.txt_Office365EmailReplyTextArea);
			paperUserResponse = javaUtils.getRandomString(20);
			getWebDriver().findElement(Outlook365Locators.txt_Office365EmailReplyTextArea).sendKeys(paperUserResponse);
			clickElementAndWaitForInvisibilityOfElement(Outlook365Locators.btn_Office365EmailReplySend, Outlook365Locators.btn_Office365EmailReplySend);
			waitUntilElementContainsText(Outlook365Locators.ele_Office365EmailRepliedContent, paperUserResponse);
		}
	}

	public void verifyResponseIsCreated(String type) throws IOException, InterruptedException {

		tearDown();
		TestDriverFactory.getInstance().setUp(ResourceHandler.loadProperty("browser"));
		super.login(System.getProperty("primary.username"), System.getProperty("primary.password"));
		parentWindow1 = getCurrentWindow();

		for (int index = 0; index < 30; index++) {
			navigateTab(LandingPage.lnk_ProjectForms);
			searchForms(formTitle);
			if (type.equalsIgnoreCase("online") && getElementText(ProjectFormsTab.lnk_FormListingFirstFormID).split("\\(2").length == 2) {
				System.out.println("Form Listing ID# " + getElementText(ProjectFormsTab.lnk_FormListingFirstFormID).split("\\(2").length);
				break;
			}
			else if (type.equalsIgnoreCase("paper") && getElementText(ProjectFormsTab.lnk_FormListingFirstFormID).split("\\(3").length == 2)
				break;
			else
				waitUtils.waitInterval(10);
		}

		clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsViewPageFormDetails, ProjectFormsTab.ele_BetaViewFormDetailsViewPageFormReply);

		if (type.equalsIgnoreCase("online")) {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CERFormViewEmailResponse);
			AutomationAssert.verifyTrue("Expected# " + getElementText(ProjectFormsTab.lbl_CERFormViewEmailResponse) + "\nActual #" + onlineUserResponse, getElementText(ProjectFormsTab.lbl_CERFormViewEmailResponse).contains(onlineUserResponse));
		}
		else if (type.equalsIgnoreCase("paper")) {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CERFormViewEmailResponse);
			waitUntilElementIsInvisible(ProjectFormsTab.pop_CommentingFormViewPopover);
			AutomationAssert.verifyTrue("Expected# " + getElementText(ProjectFormsTab.lbl_CERFormViewEmailResponse) + "\nActual #" + paperUserResponse, getElementText(ProjectFormsTab.lbl_CERFormViewEmailResponse).contains(paperUserResponse));
		}
		closeCurrentWindow();
		switchPreviousWindow(parentWindow1);

	}

	public void clickRespondLink(String respondLink, String userType) {
		parentWindow1 = getCurrentWindow();

		if(userType.equalsIgnoreCase("paper")) {
			clickElementAndWaitForElement(Outlook365Locators.ele_Office365FirstEmailSubject, Outlook365Locators.ele_Office365MailContent);
			log.info("Mail content: "+getElementText(Outlook365Locators.ele_Office365MailContent));
			if(! getElementText(Outlook365Locators.ele_Office365MailContent).contains("DC (Paper),"))
				clickElementAndWait(Outlook365Locators.ele_Office365SecondEmailSubject);
		}
		else if (userType.equalsIgnoreCase("online")) {
			log.info("User is of type :"+userType);
			clickElementAndWait(Outlook365Locators.ele_Office365FirstEmailSubject);
			log.info("Mail content: "+getElementText(Outlook365Locators.ele_Office365MailContent));
			if((!getElementText(Outlook365Locators.ele_Office365MailContent).contains("Jasmin,")) && (!getElementText(Outlook365Locators.ele_Office365MailContent).contains("Vishal,")))
				clickElementAndWait(Outlook365Locators.ele_Office365SecondEmailSubject);
		}
		clickLinkWithText(respondLink);
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void verifyLoginPageIsNavigated() {
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		login(System.getProperty("primary.username"), System.getProperty("primary.password"));
	}

	public void verifyPageAndCreateResponse(String uType) throws InterruptedException {
		String parentWindow2 = getCurrentWindow();
		waitForCompletePageLoad();
		if (uType.equalsIgnoreCase("online")) {
			waitAndSwitchIframe(ProjectFormsTab.frm_replyFormIframe);
			onlineUserResponse = javaUtils.getRandomString(20);
			sendKeys(ProjectFormsTab.txt_FormViewViaEmailResponseInput, onlineUserResponse);
		}
		else if (uType.equalsIgnoreCase("paper")) {
			waitAndSwitchIframe(By.cssSelector("frame[id='AsiWorkingFrame']"));
			waitUntilElementIsDisplayed(ProjectFormsTab.img_FormViewViaEmailReplyButton);
			AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.img_FormViewViaEmailReplyButton));
			clickElementAndWait(ProjectFormsTab.img_FormViewViaEmailReplyAllButton);
			waitForSwitchWindow(3);
			switchToThirdWindow(parentWindow1, parentWindow2);
			waitForCompletePageLoad();
			paperUserResponse = javaUtils.getRandomString(20);
			sendKeys(ProjectFormsTab.txt_FormViewViaEmailResponseInput, paperUserResponse);
		}

		sendKeys(ProjectFormsTab.txt_FormViewViaEmailResponseInput, Keys.TAB);
		clear(ProjectFormsTab.txt_FormViewViaEmailFormGroupCode);
		sendKeys(ProjectFormsTab.txt_FormViewViaEmailFormGroupCode, Keys.TAB);

		if (!uType.equalsIgnoreCase("online")) {
			clickElement(ProjectFormsTab.btn_FormViewViaEmailResponseCreate);
			waitForSwitchWindow(2);
			switchPreviousWindow(parentWindow2);
		}
		else {
			executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
			try {
				clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend, 30);
			}
			catch (Throwable t) {
				clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSend, ProjectFormsTab.btn_CreateFormSend, 30);
			}
		waitUntilElementIsDisplayed(ProjectFormsTab.ele_BetaViewFormDetailsViewPageFormReply);
			waitForCompletePageLoad();
			closeCurrentWindow();
			switchPreviousWindow(parentWindow1);
		}
	}

	public void signInToOutLook365WebMail(String email, String password) throws IOException {
		emailUser = email;
		emailPassword = password;
		super.signInToOutLook365WebMail(emailUser,emailPassword);
	}
	
	@Override
	public void login(String username, String password) {
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		switchIframe(LoginPage.frm_Iframe);
		waitUntilElementIsDisplayed(LoginPage.txt_AsiteUsername);
		findElement(LoginPage.txt_AsiteUsername).clear();
		findElement(LoginPage.txt_AsiteUsername).sendKeys(username);
		findElement(LoginPage.txt_AsitePassword).clear();
		findElement(LoginPage.txt_AsitePassword).sendKeys(password);
		findElement(LoginPage.btn_AsiteLogin).click();
		waitForCompletePageLoad();
	}
}
