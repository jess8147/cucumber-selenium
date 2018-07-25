package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateMultiFunctionForm;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateMultiFunctionForm {
	
	CreateMultiFunctionForm scripts = new CreateMultiFunctionForm();

	@When("^I enter UserRef and Title for Automation Testing Form Title$")
	public void i_enter_UserRef_and_Title_for_Automation_Testing_Form_Title() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterUserRefAndTitle();
		else
			Assume.assumeTrue(true);
	}

	@When("^I attach single and multiple attachments in form$")
	public void i_attach_single_and_multiple_attachments_in_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.attachSingleAndMultipleDocuments();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter With Callback details$")
	public void i_enter_With_Callback_details() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterCallBackDetails();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter Without Callback details$")
	public void i_enter_Without_Callback_details() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterWithOutCallBackDetails();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter Auto Create and Auto Distribution details$")
	public void i_enter_Auto_Create_and_Auto_Distribution_details() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterAutoCreateAndDistributionDetails();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Automation Testing Master Form should get created$")
	public void automation_Testing_Master_Form_should_get_created() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMasterFormIsCreated();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Automation Testing Child Forms should be auto created$")
	public void automation_Testing_Child_Forms_should_be_auto_created() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyChildFormIsCreated();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Auto Distribution should distribute \"(.*?)\" action to selected user$")
	public void auto_Distribution_should_distribute_action_to_selected_user(String action) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDistributionAction(action);
		else
			Assume.assumeTrue(true);
	}

}
