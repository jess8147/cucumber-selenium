package org.asite.automation.classic.p1.scripts;

import java.io.IOException;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.ClassicCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;

public class AppletUploadWithCustomAttributeFilesScripts extends ClassicCommonAppMethods {
	private String parentWindow;
	private String fileName, appletWindowURL;

	public void clickOnCustomAttributes(String folderName) throws InterruptedException {
		waitForCompletePageLoad();
		switchMultipleFrames();
		clickFolderWithTitle(folderName);
	}

	public void clickOnPublicStandardDocument() throws Exception {
		switchMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingPublishDocMenu);
		try {
			mouseHoverAndClickElement(DocListingPage.img_DocListingPublishDocMenu,
					DocListingPage.img_DocListingMenuPublishStandardDoc);
		} catch (WebDriverException e) {
			mouseHover(DocListingPage.img_DocListingPublishDocMenu);
			waitUtils.waitInterval(5);
			waitUntilElementIsDisplayed(DocListingPage.img_DocListingMenuPublishStandardDoc);
			clickElement(DocListingPage.img_DocListingMenuPublishStandardDoc);
		}
	}

	public void verifyPublishDocumentPage(String pageTitle) {
		log.info("Covered in <clickOnAppletUploadLink> defination..");
	}

	public void clickOnAppletUploadLink() throws Exception {
		switchWindow(DocListingPage.lnk_PublishDocumentPublishPageAppletUploadLink);
		waitUntilElementIsDisplayed(DocListingPage.lnk_PublishDocumentPublishPageAppletUploadLink);
		clickElementAndWait(DocListingPage.lnk_PublishDocumentPublishPageAppletUploadLink);
		waitForCompletePageLoad();
		maximizeWindow();
	}

	public void performAppletUpload() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		fileName = sysUtils
				.createFile(
						nodeIP + resourceUtils.getTestDataFilePath() + getCurrentDate()
								+ ClassicCommonStringPool.TXT_EXTENSION).trim();
		log.info("fileName:: " + fileName);
		appletWindowURL = getCurrentWindowURL().split("/dmsa/")[0];
		log.info("AppletWindow URL:: " + appletWindowURL);
		try {
			waitForCompletePageLoad();
			autoItUtils.handleAppletUpload(fileName, appletWindowURL, nodeIP);
		} catch (IOException e) {
			log.error("I/O exeption while performing applet upload");
		} catch (InterruptedException e) {
			log.error("Interrupted exeption while performing applet upload");
		}
		collectionDataMap.put("Uploaded File Name", fileName);
	}

	public void logInfo() {
		log.info("Test Automation step covered in function: handleAppletUpload(\"\")");
	}

	public void verifyUploadedDocumentIntoDocListing() throws IOException, InterruptedException {
		String file = strUtils.extractFileNameString(fileName).replace(ClassicCommonStringPool.TXT_EXTENSION, "");
		log.info("file :" + file);
		tearDown();
		setUp(ResourceHandler.loadProperty("browser"));
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		navigateToWorkSpace(projectTitle);
		switchMultipleFrames();
		clickElementAndWait(DocListingPage.lnk_CustomAttributesFolder);
		waitForCompletePageLoad();
		switchMultipleFrames();
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		searchFilesUsingTitle(file);
		waitUntilElementIsDisplayed(DocListingPage.lbl_DocListingFirstFileDocTitle);
		Assert.assertTrue(isDisplayed(DocListingPage.lbl_DocListingFirstFileDocTitle));
		Assert.assertTrue(getElementText(DocListingPage.lbl_DocListingFirstFileDocTitle).equalsIgnoreCase(file));
	}

	public void switchToPublishWindow() {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void closeFileOpenedWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void switchMultipleFrames() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

}