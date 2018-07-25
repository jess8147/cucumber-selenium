package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.FilesLHTasksScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_FilesLHTasks
{
	/* ****** "Tests_FilesLHAction" is developed by "Vishal Modi" ****** */
	
	FilesLHTasksScripts actionScripts = new FilesLHTasksScripts();
	
	@Given("^I have atleast one \"(.*?)\" on Files tab$")
	public void i_have_atleast_one_on_Files_tab(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.verifyActionCount(actionType);	
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I drag mouse on \"(.*?)\" count on Files tab$")
	public void i_drag_mouse_on_count_on_Files_tab(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.dragMouseOnActions(actionType);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Total number of \"(.*?)\" should display on mouse over on Files tab$")
	public void total_number_of_should_display_on_mouse_over_on_Files_tab(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.verifyTotalActionCount(actionType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" on Files tab$")
	public void i_click_on_on_Files_tab(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.clickOnActionLink(actionType);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^My all \"(.*?)\" documents should be available in Files listing$")
	public void my_all_documents_should_be_available_in_Files_listing(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.verifyIncompleteActionCount();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" filter should applied$")
	public void filter_should_applied(String filterType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.verifyFilterType(filterType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform on any \"(.*?)\" from Files listing$")
	public void i_perform_on_any_action_from_Files_listing(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.performActionFromFileListing(actionType);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Total number of \"(.*?)\" action count should be decrease by one$")
	public void total_number_of_action_count_should_be_decrease_by_one(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			actionScripts.verifyTotalActionCountDecreased(actionType);
		}
		else
			Assume.assumeTrue(true);
	}
}
