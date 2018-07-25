package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleHomeLocators {
	
	public static class LandingPage {
		public static By	lnk_Dashboard						= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navdashboard']");
		public static By	lnk_Files							= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navfiles']");
		public static By	lnk_Models							= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navmodels']");
		public static By	lnk_Projects						= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navprojects']");
		public static By	lnk_ProjectForms					= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navcommunications']");
		public static By	lnk_Discussion						= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navdiscussions']");
		public static By	lnk_Field							= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navfield']");
		public static By	lnk_Workflows						= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navworkflows']");
		public static By	lnk_Tasks							= By.cssSelector("div[id='wrap'] div[id='menuNav'] a[id='navtasks']");
		public static By	btn_More							= By.cssSelector("div[id='wrap'] div[id='menuNav'] button[id='header_moreNav'][filterkey='moreNav']");
		public static By	btn_UserProfile						= By.cssSelector("div[id='user-loggedin'] a[data-toggle='dropdown'] img[id='userProfImg'][class='circle']");
		public static By	lnk_Logout							= By.cssSelector("ul[class*='dropdown-menu'] a[id='logout']");
		public static By	lnk_ClassicView						= By.cssSelector("div[id='header'] div[id='user-loggedin'] a[id='appOldView']");
		public static By	ele_WidgetNewFormsPublishedHeader	= By.cssSelector("div[id='playground'] span[title='Recent Apps']");
		/*public static By	ele_WidgetNewFilesPublishedHeader	= By.xpath(".//span[@id='widget-title'][@title='New Files Uploaded']");*/

		/* Create Edit Roles */
		
		public static By	ele_LoggedInUserProfile				= By.cssSelector("div[id='user-loggedin'][title]");
		public static By	lnk_SwitchUser						= By.xpath(".//div[@class='btn-group open']//a[@id='switchUser']");
		public static By	lnk_Preferences						= By.xpath(".//div[@class='btn-group open']//a[text()='Preferences']");
		public static By	lnk_FilterPreferences				= By.xpath(".//a[contains(@id,'filter-preference')]");
		public static By	chk_RememberAppliedFilterCheckbox	= By.xpath(".//div[contains(@style,'display: block')]//input[@type='checkbox'][@name='applyfilterCheckbox']");
		public static By	btn_PopUserPreferencesSubmitButton	= By.xpath(".//div[contains(@style,'display: block')]//button[@id='filter-pref-submit'][text()='Submit']");
		public static By	txt_SwitchUserSearchFilter			= By.xpath(".//input[@id='proxy-user-search-input']");
		public static By	ele_FirstSwitchUserBox				= By.xpath(".//div[@id='proxy-user-list-container']/div[not(contains(@class,'filterTd2'))]");
		public static By	ele_FirstSwitchUserBoxName			= By.xpath(".//div[@id='proxy-user-list-container']/div[not(contains(@class,'filterTd2'))]//div[@class='boxUserName' and text()]");
		public static By	ele_LoggedInUser					= By.cssSelector("div[class='header-right-side'] div[id='user-loggedin']");
		/*public static By	img_SwitchUserSearchImage			= By.xpath(".//a[@id='proxy-user-search-img']");*/
	}
	
}