package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicMyHomeLocators {
	
	public static class MyHomePage
	{
		public static By tab_WorkSpace = By.cssSelector("div[id='wrapper'] div[id='navigation'] div[id='tabsBar'] ul[id='ulPageTabs'] a[onclick*='action_id=3']");
		public static By tab_Training = By.cssSelector("div[id='wrapper'] div[id='navigation'] div[id='tabsBar'] ul[id='ulPageTabs'] a[onclick*='dmstrainingak.asite.com']");
		public static By btn_AnnouncementsPageContinueButton = By.xpath(".//form[@name='fm']//input[@type='button'][@value='Continue']");
	}

}
