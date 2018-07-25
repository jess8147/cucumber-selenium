package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

public class AppletUploadCustomAttributesScripts extends AdoddleCommonAppMethods {

	private String				parentHandle, fileName;
	final private static Logger	log	= AutomationLogger.getInstance().getLogger(AppletUploadCustomAttributesScripts.class);

	public void clickOnProjectFolder(String project, String projectFolder) {
		clickElementWithText(project);
		clickElementWithText(projectFolder);
	}

	public void verifyAddFilesButton() {
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.btn_AddFiles, true), isDisplayed(FilesTab.btn_AddFiles));
	}

	public void clickOnAddFilesButton() {
		clickElementAndWaitForElement(FilesTab.btn_AddFiles, GlobalPageElements.pop_PopUpElement);
	}

	public void clickOnAppletUploadLink() {
		parentHandle = clickElementAndSwitchWindow(FilesTab.lnk_PopUploadAppletUpload);
	}

	public void verifyAppletUploadPopup() {
		log.info("Common method for click and Switch Window used");
	}

	public void performAppletUpload() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		fileName = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION).trim();
		collectionDataMap.put("AppletUpload filePath:: ", fileName);
		String appletWindowURL = getCurrentWindowURL().split("/adoddle/")[0];
		log.info("AppletWindow URL:: " + appletWindowURL);

		try {
			autoItUtils.handleAppletUpload(fileName, appletWindowURL, nodeIP);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}

		fileName = strUtils.extractFileNameString(fileName);
		collectionDataMap.put("File", fileName);
	}

	public void logInfo() {
		log.info("Test Automation step covered in function: handleAppletUpload()");
	}

	public void verifyAppletUploadDocuments() {
		switchPreviousWindow(parentHandle);
		navigateTab(LandingPage.lnk_Files);
		waitForCompletePageLoad();
		searchFiles(fileName);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.lnk_DocListingFirstFileName), fileName), getCount(FilesTab.css_DocListingFilesCount) > 0);
		AutomationAssert.verifyTrue(! getElementText(FilesTab.ele_DocListingFirstFile3DimensionCoordinates).equalsIgnoreCase(AdoddleCommonStringPool.DOUBLE_DASH));
		AutomationAssert.verifyTrue(!getElementText(FilesTab.ele_DocListingFirstFileEmailTextbox).equalsIgnoreCase(AdoddleCommonStringPool.DOUBLE_DASH));
		AutomationAssert.verifyTrue(!getElementText(FilesTab.lnk_DocListingFirstFileCitiesAttribute).equalsIgnoreCase(AdoddleCommonStringPool.DOUBLE_DASH));
	}

	public void verifyAppletUploadDistribution() throws InterruptedException {
		logOut();
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		clickElementAndWaitForElement(LandingPage.lnk_Files, FilesTab.txt_FilesFilterInput);
		searchFiles(fileName);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(eStringUtils.getElementCountError(GlobalPageElements.css_firstActionsPopoverContentLinks, 0, 7), findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() == 7);
	}
}