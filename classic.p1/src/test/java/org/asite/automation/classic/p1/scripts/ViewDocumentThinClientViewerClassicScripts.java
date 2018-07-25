package org.asite.automation.classic.p1.scripts;

import java.util.HashMap;
import java.util.Map;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;

public class ViewDocumentThinClientViewerClassicScripts extends ClassicCommonAppMethods
{
	private String parentWindow;
	private String viewFileDocRef = "viewTest";
	private Map<String, String> beforeViewMapList = new HashMap<String, String>();
	private Map<String, String> AfterViewMapList = new HashMap<String, String>();
	private String[]keyList = {"DocRef", "DocTitle"};
	
	public void verifyFileListing()
	{
		switchDefault();
		insertIntoMultipleFrame();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingFirstFileDocRef);
		Assert.assertTrue(getCount(DocListingPage.css_NumberOfFiles) > 0);
	}
	
	public void gotoFileDocument()
	{
		sendKeys(DocListingPage.txt_DocListingDocRefInput, viewFileDocRef.trim());
		clickElementAndWait(DocListingPage.btn_DocListingSearch);
		
		beforeViewMapList.put(keyList[0], getElementText(DocListingPage.lnk_DocListingFirstFileDocRef).trim());
		beforeViewMapList.put(keyList[1], getElementText(DocListingPage.lbl_DocListingFirstFileDocTitle).trim());
		log.info("beforeViewMapList :"+beforeViewMapList);
		
		clickElementAndWait(DocListingPage.lnk_DocListingFirstFileDocRef);
	}
	
	public void verifyFileOpened() throws Exception
	{
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(DocListingPage.frm_AsiteViewDocument);
		if(! isDisplayed(DocListingPage.frm_AsiteViewDocument)) {
			waitAndSwitchIframe(DocListingPage.frm_BravaObjectFrame);
			if(! isDisplayed(DocListingPage.frm_BravaObjectFrame)) {
				waitUntilElementIsDisplayed(DocListingPage.frm_OpenFileIframe);
				Assert.assertTrue(isDisplayed(DocListingPage.frm_OpenFileIframe));
				takeScreenShot(TestDriverFactory.scenario);
			}
		} else {
			Assert.assertTrue(false);
		}
	}
	
	public void verifyFileAttributes()
	{
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(DocListingPage.frm_ViewDocumentLeftPanel);
		if(! isDisplayed(DocListingPage.frm_ViewDocumentLeftPanel))
		{
			waitAndSwitchIframe(DocListingPage.frm_ViewDocumentBravaLeftPanel);
			if(! isDisplayed(DocListingPage.frm_ViewDocumentBravaLeftPanel))
			{
				AfterViewMapList.put(keyList[0], getElementText(DocListingPage.lbl_ViewDocumentDocRef).trim());
				AfterViewMapList.put(keyList[1], getElementText(DocListingPage.lbl_ViewDocumentDocTitle).trim());
				log.info("AfterViewMapList :"+AfterViewMapList);
				compareMapList(beforeViewMapList, AfterViewMapList);
			}
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	public void gotoFileOpenWindow()
	{
		parentWindow= getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}
	
	public void closeFileOpenedWindow()
	{
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}
	
	public void insertIntoMultipleFrame()
	{
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}
}
