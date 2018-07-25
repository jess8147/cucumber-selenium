package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.FormIncompleteActionsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_FormIncompleteActions
{
	FormIncompleteActionsScripts scripts = new FormIncompleteActionsScripts();
	
	@When("^I create new form in ([^\"]*) project app folder ([^\"]*) and apptype ([^\"]*) with \"(.*?)\" Distribution Group$")
	public void i_create_new_form_in_project_app_folder_and_apptype_with_Distribution_Group(String project, String appFolder, String appType, String actionGroup) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createNewForm(project, appFolder, appType, actionGroup);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should created successfully$")
	public void form_should_created_successfully() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Created Form should displayed with all Incomplete Actions$")
	public void created_Form_should_displayed_with_all_Incomplete_Actions() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithActions();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Form all Actions should displayed in Distribution \"(.*?)\" And Action status set as \"(.*?)\"$")
	public void form_all_Actions_should_displayed_in_Distribution_And_Action_status_set_as(String history, String actionStatus) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDistributionHistory(actionStatus);
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform all Incomplete Actions one by one$")
	public void i_perform_all_Incomplete_Actions_one_by_one() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.performAllIncompleteActions();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Forms all Actions should Completed And on \"(.*?)\" tab Action status set as \"(.*?)\"$")
	public void forms_all_Actions_should_Completed_And_on_tab_Action_status_set_as(String historyTab, String actionStatus) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormAllActionsCompleted();
		else
			Assume.assumeTrue(true);
	}
}
