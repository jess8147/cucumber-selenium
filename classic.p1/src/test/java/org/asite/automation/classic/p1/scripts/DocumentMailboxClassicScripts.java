package org.asite.automation.classic.p1.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicWSLandingLocators.WSLandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.ClassicCommonStringPool;
import org.asite.automation.common.utils.ResourceUtils;

import com.google.common.io.Files;

public class DocumentMailboxClassicScripts extends ClassicCommonAppMethods {
	private String			actualFileName, docRef;
	private String			parentHandle	= null;

	public void sendMailToMailBoxUser(String recipient, String mailSubject) throws IOException, InterruptedException {
		List<String> 	receiver 		= new ArrayList<String>();
		String			filePath		= ResourceHandler.loadProperty("single.file.path");

		if (getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA1_HOST))
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace("mail.asite.com", "mail1.qa.asite.com"): recipient.replace("mailb.asite.com", "mail1b.qa.asite.com"));
		else if (getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA2_HOST))
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace(".asite.com", "qa2.asite.com") : recipient.replace("b.asite.com", "qa2b.asite.com"));
		else
			receiver.add(recipient);

		if (trainingFlag)
			receiver.add(recipient.replace("@mail", "@mailtraining"));

		log.info("Actual File Size:::" + (getFileSize(filePath)));
		log.info("Actual File Name:::" + (actualFileName = new File(filePath).getName()));
		emailUtils.sendEmail(getWebMailUser(), receiver, mailSubject + epoch, javaUtils.getRandomString(20), filePath);
		login(System.getProperty("primary.username"), System.getProperty("primary.password"));
	}

	public void attachDocToEmail() {
		log.info("Covered in previous definition collectively");
	}

	public void clickOnMailBoxFolder(String folderName) {
		clickElementWithText(folderName);
	}

	public void clickOnMailboxFolder(String mailboxFolder) {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(WSLandingPage.lnk_MailBoxFolder);
		AutomationAssert.verifyTrue(getElementText(WSLandingPage.lnk_MailBoxFolder).trim().equalsIgnoreCase(mailboxFolder.trim()));
		clickElementAndWait(WSLandingPage.lnk_MailBoxFolder);
	}

	public void verifyDocumentIsAvailable(String docRefParam) {
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		docRef = docRefParam;
		
		for (int index = 0; index < 30; index++) {
			try {
				searchFiles(docRef + epoch);
				if (getElementText(DocListingPage.lnk_DocListingFirstDocRef).equalsIgnoreCase(docRef + epoch))
					break;
				else
					waitUtils.waitInterval(10);
			}
			catch (Throwable t) {
				log.info("Waiting for 1 minute for doc publish");
			}
		}

		log.info("Element text::" + getElementText(DocListingPage.lnk_DocListingFirstDocRef));
		log.info("Epoch text::" + docRef + epoch);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstDocRef).equalsIgnoreCase(docRef + epoch));
	}

	public void clickOnClipIconOfDocument(String docRef) {
		parentHandle = getCurrentWindow();
		clickElementAndSwitchWindow(DocListingPage.img_DocListingFirstAttachmentIcon);
		waitUntilElementIsDisplayed(DocListingPage.btn_DownloadDocumentsDownload);
		clickElement(DocListingPage.btn_DownloadDocumentsDownload);
	}

	public void downloadAndVerifyDocument() throws IOException, InterruptedException {
		int zCounter = 0;
		String unzippedFileLocation = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch;
		String	mailboxFile	= "AutomationTestDocumentMailbox" + epoch + "_zip_Ver1" + ClassicCommonStringPool.ZIP_EXTENSION;
		String remoteMoveFilePath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + mailboxFile;
		log.info("Remote Move File Path :" + remoteMoveFilePath);
		sysUtils.waitForSingleDirectFileDownload(nodeIP, mailboxFile, remoteMoveFilePath);
			
		unZipFile(remoteMoveFilePath, unzippedFileLocation);
		File unzippedFile = new File(unzippedFileLocation + "\\" + actualFileName);
		while (!unzippedFile.exists()) {
			log.info("Waiting for file to be unzipped");
			waitUtils.waitInterval(1);
			zCounter++;
			if (zCounter > 180) {
				AutomationAssert.verifyTrue(false);
				break;                     
			}
		}
		AutomationAssert.verifyTrue(unzippedFile.exists());
		deactivateFile(docRef + epoch);
		sysUtils.deleteFile(remoteMoveFilePath);
		sysUtils.deleteFile(unzippedFileLocation + "\\"  + actualFileName);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void loginToWebMailLightVersion() {
		getWebDriver().get(ResourceHandler.getPropertyValue("asite.web.mail.url"));
		clickElement(GlobalPageElements.chk_WebMailLighVersion);
		sendKeys(GlobalPageElements.txt_WebMailUserNameInput, ResourceHandler.getPropertyValue("asite.web.mail.user"));
		sendKeys(GlobalPageElements.txt_WebMailPasswordInput, ResourceHandler.getPropertyValue("asite.web.mail.password"));
		clickElementAndWaitForElement(GlobalPageElements.btn_WebMailSignIn, GlobalPageElements.lnk_WebMailLVNew);
	}

	private String getApplicationURL() {
		return  ResourceUtils.getApplicationURL();
	}

	private String getWebMailUser() {
		return resourceUtils.getWebMailUser();
	}

}
