package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.PropertyUtils;
import org.asite.automation.common.utils.ResourceUtils;
import org.junit.Assert;
import com.google.common.io.Files;


public class DocumentMailboxScripts extends AdoddleCommonAppMethods {

	final private String			filePath		= ResourceHandler.loadProperty("single.file.path");
	final private String			epoch			= dateUtils.getEpoch();
	private String					downloadFileTitle;
	private String 					actualFileName, mailboxFolder;
	private String					docRef;
	private static double			expectedFileSize;
	private String					projectTitle;
	final private static Logger		log				= AutomationLogger.getInstance().getLogger(DocumentMailboxScripts.class);

	public void sendMailToMailBoxUser(String recipient, String mailSubject) {
		List<String> receiver = new ArrayList<String>();
		log.info("Recipient: "+recipient);
		if (ResourceUtils.getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA1_HOST))
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace(".asite.com", "1.qa.asite.com") : recipient.replace("b.asite.com", "1b.qa.asite.com"));
		else if (ResourceUtils.getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA2_HOST))
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace(".asite.com", "qa2.asite.com") : recipient.replace("b.asite.com", "qa2b.asite.com"));
		else
			receiver.add(recipient);

		if (PropertyUtils.trainingFlag)
			receiver.add(recipient.replace("@mail", "@mailtraining"));

		expectedFileSize = sysUtils.getFileSize(filePath);
		File file = new File(filePath);
		actualFileName = file.getName();
		emailUtils.sendEmail(ResourceHandler.getPropertyValue("asite.web.mail.user"), receiver, mailSubject + epoch, javaUtils.getRandomString(20), filePath);

	}

	public void clickOnMailBoxFolder(String folderName) {
		mailboxFolder = folderName;
		clickElementWithText(mailboxFolder);
	}

	public void verifyDocumentIsAvailable(String docRefParam) {
		docRef = docRefParam;
		for (int index = 0; index < Integer.parseInt(ResourceHandler.getPropertyValue("mailbox.wait.time.out")); index++) {
			try {
				navigateTab(LandingPage.lnk_Files);
				clickElementWithText(projectTitle);
				clickElementWithText(mailboxFolder);
				searchFiles(docRef + epoch);
				collectionDataMap.put("Mailbox DocRef", docRef + epoch);
				if (getCount(FilesTab.css_FilesDocRefList) > 0 && getElementText(FilesTab.lnk_DocListingFirstDocRef).equalsIgnoreCase(docRef + epoch))
					break;
				else
					waitUtils.waitInterval(60);
			}
			catch (Throwable t) {
				log.info("Waiting for 1 minute for doc publish");
			}
		}

		Assert.assertTrue(getElementText(FilesTab.lnk_DocListingFirstDocRef).equalsIgnoreCase(docRef + epoch));
	}

	public void clickOnProject() {
		projectTitle = System.getProperty("global.test.project");
		clickElementWithText(projectTitle);
	}

	public void clickOnClipIconOfDocument() {
		downloadFileTitle = getElementText(FilesTab.lnk_DocListingFirstDocRef);
		clickElementAndWait(FilesTab.img_FileListingFirstAttachmentImage);
	}

	public void downloadAndVerifyDocument() throws IOException {
		String unzippedFileLocation = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + epoch;
		String remoteDownloadFilePath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path") + downloadFileTitle + "_zip_Ver1" + AdoddleCommonStringPool.ZIP_EXTENSION;
		File file1 = new File(remoteDownloadFilePath);
		sysUtils.waitUntilFileExists(file1);
		String remoteMoveFilePath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + downloadFileTitle + "_zip_Ver1" + AdoddleCommonStringPool.ZIP_EXTENSION;
		File file2 = new File(remoteMoveFilePath);
		Files.move(file1, file2);
		sysUtils.waitUntilFileExists(file2);
		sysUtils.unZipFile(remoteMoveFilePath, unzippedFileLocation);
		File unzippedFile = new File(unzippedFileLocation + "\\" + actualFileName);
		sysUtils.waitUntilFileExists(unzippedFile);
		log.info("Actual File Size:::" + sysUtils.getFileSize(unzippedFile.toString()));
		log.info("Expected File Size:::" + expectedFileSize);
		try {
			deactivateFile(docRef + epoch);
			sysUtils.deleteFile(remoteDownloadFilePath);
			sysUtils.deleteFile(unzippedFileLocation + actualFileName);
		}
		catch (Throwable t) {
			log.info(": File deactivation failed :");
		}
	}

}
