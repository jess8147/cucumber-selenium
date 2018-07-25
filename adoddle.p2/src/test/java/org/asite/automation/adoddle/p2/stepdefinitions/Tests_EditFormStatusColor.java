package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.EditFormStatusColorScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_EditFormStatusColor {
	
	EditFormStatusColorScripts scripts = new EditFormStatusColorScripts();

	@Given("^I have project ([^\"]*) available$")
	public void i_have_Project_project_available(String projectTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyProjectIsAvailable(projectTitle);
		else
			Assume.assumeTrue(true);	
	}

	@When("^I search form status \"(.*?)\"$")
	public void i_search_form_status(String formStatus) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchFormStatus(formStatus);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set status color as \"(.*?)\" and save the form status$")
	public void i_set_status_color_as_and_save_the_form_status(String color) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeStatusColor(color);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form status should be saved$")
	public void form_status_should_be_saved() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySavedFormStatus();
		else
			Assume.assumeTrue(true);
	}

	@When("^I search defects with status \"(.*?)\"$")
	public void i_search_defects_with_status(String status) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchDefectWithStatus(status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^All defects should have status color as \"(.*?)\"$")
	public void all_defects_should_have_status_color_as(String color) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyExistingDefectsColor(color);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Defect color should be \"(.*?)\"$")
	public void defect_color_should_be(String color) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedDefectColor(color);
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit status color as \"(.*?)\"$")
	public void i_edit_status_color_as(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    Assume.assumeTrue(true);
	}

	@When("^I save the form status$")
	public void i_save_the_form_status() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    Assume.assumeTrue(true);
	}

	@When("^I search created defect with Status \"(.*?)\"$")
	public void i_search_created_defect_with_Status(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    Assume.assumeTrue(true);
	}

	@Then("^Defect listing page should show \"(.*?)\" color for status$")
	public void defect_listing_page_should_show_color_for_status(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    Assume.assumeTrue(true);
	}
	
}
