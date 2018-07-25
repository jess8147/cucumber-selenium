package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.CreateSiteLocationScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateSiteLocation {
	
	CreateSiteLocationScripts scripts  = new CreateSiteLocationScripts();
	
	@When("^I create new field enabled project with ([^\"]*)$")
	public void i_create_new_field_enabled_project(String geoGraphy) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFieldEnabledProject(geoGraphy);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" app and click on \"(.*?)\" button$")
	public void i_select_app_and_click_on_button(String appName, String addToProject) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectAppAndAddToProject(appName,addToProject);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I search app template and right click$")
	public void i_search_app_template_and_right_click() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectAppTemplateAndContextClick();
		else
			Assume.assumeTrue(true);
	}

	@When("^I add Current User as \"(.*?)\" role and click on \"(.*?)\" button$")
	public void i_add_Current_User_as_role_and_click_on_button(String role, String btnText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addUserAsRole(role);
		else
			Assume.assumeTrue(true);
	}

	@When("^I context click on field enabled Project$")
	public void i_context_click_on_field_enabled_Project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickOnFieldEnabledProject();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" option should be enabled$")
	public void option_should_be_enabled(String addSiteLabel) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddSiteOptionEnabled(addSiteLabel);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" option to add site$")
	public void i_click_on_option_to_add_site(String addSiteLabel) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickAddSiteOption(addSiteLabel);
		else
			Assume.assumeTrue(true);
	}
	

	@Given("^I read data from excel and create site locations$")
	public void i_read_site_and_location_data_from_excel() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getSiteLocationExcelData();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter Site Name as \"(.*?)\"$")
	public void i_enter_Site_Name_as(String siteName) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterSiteName(siteName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" message appears$")
	public void message_appears(String successMsg) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddSiteLocationMessage(successMsg);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Site should be available in the project$")
	public void site_should_be_available_in_the_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySiteIsAvailable();
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^Site is available in the project$")
	public void site_is_available_in_the_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySiteIsAvailable();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I context click on Site$")
	public void i_context_click_on_Site() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickOnSite();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I click on \"(.*?)\" option to add location$")
	public void i_click_on_option_to_add_location(String locationLabel) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickAddLocationOption(locationLabel);
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter Location Name as \"(.*?)\"$")
	public void i_enter_Location_Name_as(String locationTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterLocationName(locationTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Location should be available in the project under the selected site$")
	public void location_should_be_available_in_the_project_under_the_selected_site() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyLocationIsAvailable();
		else
			Assume.assumeTrue(true);
	}


}
