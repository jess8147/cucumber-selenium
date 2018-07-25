package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreateFieldEnabledProjectScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateFieldEnabledProject {
	
	CreateFieldEnabledProjectScripts scripts  = new CreateFieldEnabledProjectScripts();
	
	@Then("^\"(.*?)\" button should be displayed in LH panel$")
	public void button_should_be_displayed(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddProjectButton();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" in LH panel$")
	public void i_click_on_in_LH_panel(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAddProjectButton();
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I have entered ProjectName AND Description AND I have selected Geography as ([^\"]*)$")
	public void i_have_entered_ProjectName_AND_Description_AND_I_have_selected_Geography_as(String dropDownValue) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterProjectDetails(dropDownValue);
		else
			Assume.assumeTrue(true);
	}

	@When("^I checked \"(.*?)\" checkbox$")
	public void i_checked_checkbox(String checkBoxText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectCheckboxFieldEnabled(checkBoxText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on Create Project popup$")
	public void i_click_on_button_on_popup(String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickCreateProjectButton(buttonText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Field enabled project should be created$")
	public void project_should_be_created() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyProjectIsCreated();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I search and right click on created project$")
	public void i_search_and_right_click_on_created_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickOnCreatedProject();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I clicked on \"(.*?)\" option$")
	public void i_clicked_on_option(String editProjectOption) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectEditProjectOption();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" checkbox should be checked$")
	public void checkbox_should_be_checked(String fieldEnabled) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFieldEnabledCheckbox(fieldEnabled);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Field tab should display the project$")
	public void field_tab_should_be_displayed() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFieldTabIsDisplayingProject();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I close the Field Project$")
	public void i_close_the_field_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.closeFieldProject();
		else
			Assume.assumeTrue(true);
	}
}
