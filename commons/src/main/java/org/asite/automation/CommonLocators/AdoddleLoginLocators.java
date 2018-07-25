package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleLoginLocators {
	
	public static class LoginPage {
		
		public static By	frm_Iframe				= By.cssSelector("iframe[id='iFrameAsite']");
		public static By	lnk_login				= By.cssSelector("div[class='loginBtnSection'] a[class='loginBtn']");
		public static By	msg_ErrorMsg			= By.xpath(".//*[@id='portlet-wrapper-58']//form/span[1]");
		public static By	txt_AsiteUsername		= By.cssSelector("div[class='inp-login'] input[name*='login']");
		public static By	txt_AsitePassword		= By.cssSelector("div[class='inp-login'] input[name*='password']");
		public static By	btn_AsiteLogin			= By.cssSelector("input[class*='btn-submit']");
		/*public static By	img_AsiteMainLogoImage	= By.xpath(".//a[@id='main-logo']//img[contains(@src,'logo')]");*/
		
	}
	
}
