package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicLandingLocators {
	
	public static class LandingPage
	{
		public static By btn_UserProfile = By.cssSelector("div[id='user-loggedin'] button[id='header_userlogin']");
		public static By lnk_ClassicView = By.cssSelector("div[id='header'] div[id='user-loggedin'] a[id='appOldView']");
		public static By lnk_MyHome = By.cssSelector("div[id='topBarLinks'] div[id='topNavLinks'] a[title='My Home']");
		public static By lnk_Settings = By.cssSelector("div[id='topMenu'] div[class='topMenuLinks'] a[id='lnkSettings']");
		public static By lnk_SettingsPrefs = By.cssSelector("div[id='topDropMenu'] a[title='Edit User Preferences']");
		public static By lnk_SignOut = By.cssSelector("div[id='topMenu'] div[class='topMenuLinks'] a[title='Return to Asite']");
		public static By frm_AsiteWorkingFrame = By.cssSelector("frameset[id='defaultFramesetApp'] frame[id='AsiWorkingFrame']");
		public static By frm_AsiteMainFrame = By.cssSelector("frame[name='asite_main']");
		public static By frm_AsiteHeaderFrame = By.cssSelector("frame[name='header']");
		public static By frm_AsiteNoResize = By.cssSelector("frame[noresize='noresize']");
		public static By frm_PmFrame = By.cssSelector("frame[id='pmfrm']");
		public static By frm_AsiteWorkspaceFrame = By.cssSelector("frame[id='workspace']");
		public static By frm_AsiteWorkspaceNameFrame = By.cssSelector("frame[name='workspace']");
		public static By frm_AsiteDirectAccessContent = By.cssSelector("frame[name='direct_access_content']");
		public static By lbl_AsiteServerCurrentDate = By.xpath(".//font[@id='liveclock' and text()]");
	}
	
}
