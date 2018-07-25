package org.asite.automation.adoddle.p1.stepdefinitions;
import org.asite.automation.adoddle.p1.scripts.CreateUserScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Tests_CreateUser {

	CreateUserScripts scripts = new CreateUserScripts();
	
	@When("^I add Email Address as mandatory field$")
	public void i_add_Email_Address_as_mandatory_field() throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.enterUserEmailAddress();
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I select Organization as ([^\"]*)$")
	public void i_select_Organization_as_Organization(String org) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectValidOrganization(org);
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I enter all fields to create new user and click \"(.*?)\" button$")
	public void i_enter_all_mandatory_fields_to_create_new_user(String btnText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterMandatoryUserFields();
	    else
	    	Assume.assumeTrue(true);
	}

	@Then("^User should get created successfully$")
	public void user_should_get_created_successfully() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUserIsCreated();
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I search user on Manage Users & Subscription page$")
	public void i_search_user_on_Manage_Users_Subscription_page() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchUserOnListing();
	    else
	    	Assume.assumeTrue(true);
	}

	@Then("^User should be available$")
	public void user_should_be_available() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchUserOnListing();
	    else
	    	Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button on Users Listing page$")
	public void i_click_on_add_new_user_button_on_users_listing_page(String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickAddNewUserButton(buttonText);
	    else
	    	Assume.assumeTrue(true);
	}
	
	@When("^I create ([^\"]*) number of users ([^\"]*)$")
	public void i_create_bulk_users(String userCount, String org) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.createNumberofUsers(userCount, org);
	    else
	    	Assume.assumeTrue(true);
	}
}
