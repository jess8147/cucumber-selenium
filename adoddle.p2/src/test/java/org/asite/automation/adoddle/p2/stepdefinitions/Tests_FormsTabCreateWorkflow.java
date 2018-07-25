package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.FormsTabCreateWorkflowScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_FormsTabCreateWorkflow
{
	FormsTabCreateWorkflowScripts scripts = new FormsTabCreateWorkflowScripts();
	
	@When("^I select ([^\"]*) project$")
	public void i_select_project(String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickElementWithText(project);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select more than one Forms for Create Workflow$")
	public void i_select_more_than_one_Forms_for_Create_Workflow() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormsForWorkflow();
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on selected Forms and I select \"(.*?)\" and \"(.*?)\" context option$")
	public void i_right_click_on_selected_Forms_and_I_select_and_context_option(String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectContextOption(option1, option2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I attach a document And I click on \"(.*?)\" Button$")
	public void i_attach_a_document_And_I_click_on_Button(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.attachExternalDocInWorkflowForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^I create workflow form with \"(.*?)\" action to ([^\"]*) User$")
	public void i_create_workflow_form_with_action_to_Automation_UKP_User(String action, String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createNewWorkflowForm(action, user);
		else
			Assume.assumeTrue(true);
	}

	@Then("^workflow form should created successfully And \"(.*?)\" AND selected \"(.*?)\" should associated with it$")
	public void workflow_form_should_created_successfully_And_AND_selected_should_associated_with_it(String attachments, String apps) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedWorkflowFormAndAttachmentAssociation(attachments, apps);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link of More Option popup$")
	public void i_click_on_link_of_More_Option_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnMoreOptionsWorkflowFormLink();
		else
			Assume.assumeTrue(true);
	}
}
