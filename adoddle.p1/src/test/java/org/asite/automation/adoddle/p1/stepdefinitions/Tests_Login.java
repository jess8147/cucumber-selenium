package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.LoginScript;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_Login 
{
	LoginScript login = new LoginScript();
	
	@Given("^I am on Login Page$")
	public void i_am_on_login_page()
	{
		login.verifyLoginPage();
	}
	
	@When("^I give Username as \"(.*?)\" and Password \"(.*?)\" and click on Login button$")
	public void i_give_Username_as_and_Password_and_click_on_Login_button_for_N(String arg1, String arg2) throws Throwable 
	{
		login.gotoLoginNegative();
	}

	@Then("^same page should be displayed with \"(.*?)\" message$")
	public void same_page_should_be_displayed_with_message(String arg1) throws Throwable 
	{
		login.verifyErrorMsg();
	}
	
		
	@When("^I give Username and Password and click on Login button$")
	public void i_give_Username_and_Password_and_click_on_Login_button() throws Throwable 
	{
		login.gotoLoginPositive();
	}

	@Then("^I should logged in successfully and redirecting on Adoddle Dashboard$")
	public void i_should_logged_in_successfully_and_redirecting_on_Adoddle_Dashboard() throws Throwable 
	{
		login.verifyloginSuccess();
	}
	
	@Given("^I click Add Files and capture API calls$")
	public void i_click_add_files_and_capture_api_calls() throws Throwable 
	{
		login.captureAPICalls();
	}
}
