package org.asite.automation.classic.p1.scripts;

import java.util.HashMap;
import java.util.Map;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;

public class ViewDocumentJavaViewerClassicScripts extends ClassicCommonAppMethods {
	private String				parentWindow, switchWindowURL;
	private String				viewFileDocRef		= ResourceHandler.loadProperty("file.view.test");
	private Map<String, String>	beforeViewMapList	= new HashMap<String, String>();
	private Map<String, String>	AfterViewMapList	= new HashMap<String, String>();
	private String[]			keyList				= { "DocRef", "DocTitle" };

	public void gotoFileDocument() throws InterruptedException {
		searchFiles(viewFileDocRef);
		parentWindow = getCurrentWindow();
		switchWindowURL = getCurrentWindowURL().split("/dmsa/")[0];
		log.info("Window Title:: " + switchWindowURL);
		beforeViewMapList.put(keyList[0], getElementText(DocListingPage.lnk_DocListingFirstFileDocRef).trim());
		beforeViewMapList.put(keyList[1], getElementText(DocListingPage.lbl_DocListingFirstFileDocTitle).trim());
		log.info("beforeViewMapList :" + beforeViewMapList);
		clickElementAndWait(DocListingPage.lnk_DocListingFirstFileDocRef);
	}

	public void verifyFileAttributes() {
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(DocListingPage.frm_ViewDocumentLeftPanel);
		if (!isDisplayed(DocListingPage.frm_ViewDocumentLeftPanel)) {
			waitAndSwitchIframe(DocListingPage.frm_ViewDocumentBravaLeftPanel);
			if (!isDisplayed(DocListingPage.frm_ViewDocumentBravaLeftPanel)) {
				AfterViewMapList.put(keyList[0], getElementText(DocListingPage.lbl_ViewDocumentDocRef).trim());
				AfterViewMapList.put(keyList[1], getElementText(DocListingPage.lbl_ViewDocumentDocTitle).trim());
				log.info("AfterViewMapList :" + AfterViewMapList);
				compareMapList(beforeViewMapList, AfterViewMapList);
			}
		}
		else {
			AutomationAssert.verifyTrue("Document attributes are not viewed successfully", false);
		}
	}

	public void verifyFileOpened() throws Exception {
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(DocListingPage.frm_AsiteViewDocument);
		if (!isDisplayed(DocListingPage.frm_AsiteViewDocument)) {
			waitUntilElementIsInvisible(DocListingPage.frm_ViewDocumentDummyFrame);
			if (!isDisplayed(DocListingPage.frm_ViewDocumentDummyFrame)) {
				AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(DocListingPage.frm_ViewDocumentBravaApplet, true), isDisplayed(DocListingPage.frm_ViewDocumentBravaApplet));
				waitUtils.waitInterval(5);
				takeScreenShot(TestDriverFactory.scenario);
			}
		}
		else {
			takeScreenShot(TestDriverFactory.scenario);
			AutomationAssert.verifyTrue("Advanced Viewer not loaded successfully", false);
		}
	}

	public void closeFileOpenedWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void insertIntoMultipleFrame() {
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

	public void gotoFileOpenWindow() throws Exception {
		switchWindow(DocListingPage.frm_ViewDocumentLeftPanel, switchWindowURL);
	}

}