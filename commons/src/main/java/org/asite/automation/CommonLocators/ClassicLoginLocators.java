package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicLoginLocators {

	public static class LoginPage
	{
		public static By lnk_login = By.xpath(".//div[contains(@id,'top')]//a[contains(@id,'login')]");
//		public static By lnk_login = By.xpath(".//a[@class='loginBtn' and text()='Log in']");
		public static By frm_Iframe = By.cssSelector("iframe[id='iFrameAsite']");
		public static By txt_AsiteUsername = By.xpath(".//div[@id='portlet-wrapper-58']//input[contains(@name,'login')]");
//		public static By txt_AsiteUsername = By.xpath(".//input[contains(@name,'login')]");
		public static By txt_AsitePassword = By.xpath(".//div[@id='portlet-wrapper-58']//input[contains(@name,'password')]");
//		public static By txt_AsitePassword = By.xpath(".//input[contains(@name,'password')]");
		public static By btn_AsiteLogin = By.xpath(".//div[@id='portlet-wrapper-58']//input[contains(@class,'submit')]");
//		public static By btn_AsiteLogin = By.xpath(".//button[text()='Log In']");
		public static By msg_ErrorMsg = By.xpath(".//*[@id='portlet-wrapper-58']//form/span[1]");
	}
}
