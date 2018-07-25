package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class LoginScript extends AdoddleCommonAppMethods {

	public void verifyLoginPage() {
		Assert.assertTrue(isDisplayed(LoginPage.frm_Iframe));
	}

	public void gotoLoginNegative() throws InterruptedException {
		login(ResourceHandler.loadProperty("primary.username.n"), ResourceHandler.loadProperty("primary.password.n"));
	}

	public void verifyErrorMsg() throws InterruptedException {
		switchIframe(LoginPage.frm_Iframe);
		verifyElementText(LoginPage.msg_ErrorMsg, AdoddleCommonStringPool.LOGIN_ERROR_MESSAGE);
	}

	public void gotoLoginPositive() throws InterruptedException {
		login(ResourceHandler.loadProperty("primary.username"), ResourceHandler.loadProperty("primary.password"));
	}

	public void verifyloginSuccess() throws InterruptedException {
		Assert.assertTrue(isDisplayed(DashboardTab.btn_HeaderUserLogin));
	}

	public void captureAPICalls() throws IOException, InterruptedException {
		String linkAttributes, processDefinitionId, processInstanceID, reqUserId, reqProjectId;
		TestDriverFactory.getInstance().setUp("CHROME");
		login("jasminprajapati@asite.com", "Test@12345");
		clickElementAndWait(LandingPage.lnk_Files);
		clickElementWithText("Automation_WF_Folder");
		Cookie cookie = getWebDriver().manage().getCookieNamed("ASessionID");

		System.out.println("Cookie: " + cookie.toString().split(";")[0].replace("ASessionID=", "").trim());
		waitForCompletePageLoad();

		reqUserId = (String) ((JavascriptExecutor) getWebDriver()).executeScript("return USP.userID");
		WebElement firstWorkFlowLink = findElement(By.cssSelector("div[class*='rows']:nth-child(1) div[class*='col-workflowStatus-fixed-width'] a[class*='multiobjcount']"));
		linkAttributes = firstWorkFlowLink.getAttribute("onclick").toString();
		processDefinitionId = linkAttributes.split(",")[0].replace("'", "").replace("viewProgress(", "").trim();
		processInstanceID = (linkAttributes.split(",")[1].replace("'", "")).split("\\$")[0];
		reqProjectId = linkAttributes.split(",")[3].replace("'", "");
		String URL = "https://workflow.qa.asite.com/AWOM/workflow/model/viewer?processDefinitionId=" + processDefinitionId + "&processInstanceId=" + processInstanceID + "&reqUserId=" + reqUserId + "&reqProjectId=" + reqProjectId;
		System.out.println("API URL: " + URL);
		System.out.println("Response Code: " + getResponseCode(URL, cookie));

	}

	public static String getResponseCode(String urlString, Cookie cookie) throws MalformedURLException, IOException {
		URL url = new URL(urlString);
		HttpsURLConnection huc = (HttpsURLConnection) url.openConnection();
		huc.setRequestMethod("GET");

		String username = "jasminprajapati@asite.com";
		String password = "Test@12345";
		String userpass = username + ":" + password;
		@SuppressWarnings("restriction")
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		huc.setRequestProperty("Authorization", basicAuth);
		huc.setRequestProperty("Content-Type", "application/json; charset=utf8");
		huc.setRequestProperty("Accept", "application/json");
		huc.setRequestProperty("x-auth-token", cookie.toString().split(";")[0].replace("ASessionID=", "").trim());
		huc.connect();
		System.out.println("Response Code: " + huc.getResponseCode());
		return huc.getResponseMessage();
	}
}