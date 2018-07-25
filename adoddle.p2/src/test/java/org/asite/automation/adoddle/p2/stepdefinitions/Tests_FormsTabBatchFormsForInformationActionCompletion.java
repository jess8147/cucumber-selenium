package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.FormsTabBatchFormsForInformationActionCompletionScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_FormsTabBatchFormsForInformationActionCompletion
{
	FormsTabBatchFormsForInformationActionCompletionScripts scripts = new FormsTabBatchFormsForInformationActionCompletionScripts();
	
	@Then("^I cleared formTitle and formList$")
	public void i_cleared_formTitle_and_formList() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clearFormList();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new form and Distribute to User ([^\"]*) with ([^\"]*) action$")
	public void i_create_new_form_and_Distribute_to_User_with_action(String distributeUser, String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createForm(distributeUser, action);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new form with No Distribute Action$")
	public void i_create_new_form_with_No_Distribute_Action() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createForm(null, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^created two Forms should displayed with \"(.*?)\" action And one Form should displayed with No action$")
	public void created_two_Forms_should_displayed_with_action_And_one_Form_should_displayed_with_No_action(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithAction(action);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Batch Forms And select \"(.*?)\" and \"(.*?)\" context menu option$")
	public void i_select_Batch_Forms_And_select_and_context_menu_option(String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectBatchFormsAndSelectContextClickOption(option1, option2);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select Batch Forms And click on \"(.*?)\" and select \"(.*?)\" Option$")
	public void i_select_Batch_Forms_And_click_on_and_select_Option(String arg1, String option) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormAndSelectMoreOptionsOption();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Forms that have \"(.*?)\" actions should displayed and other forms should not displayed in popup$")
	public void forms_that_have_actions_should_displayed_and_other_forms_should_not_displayed_in_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyForInformationActionFormsOnPopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Forms that have \"(.*?)\" actions should completed$")
	public void forms_that_have_actions_should_completed(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormsActionCompleted(action);
		else
			Assume.assumeTrue(true);
	}
}