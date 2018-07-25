package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.CreateFormClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_CreateForm {
	
	CreateFormClassicScripts scripts = new CreateFormClassicScripts();
	
	
	@When("^I click on \"(.*?)\" link of All Apps$")
	public void i_click_on_form_link_of_all_pps(String formName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnFormLink(formName);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" of any form of Apps$")
	public void i_click_on_of_any_form_of_Apps(String createFormTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickCreateFormIcon(createFormTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^New \"(.*?)\" popup should open$")
	public void new_popup_should_open(String createFormTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyCreateFormWindowOpens(createFormTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have selected Form User \"(.*?)\" into Distribution With action \"(.*?)\"$")
	public void i_have_selected_Form_Users_into(String user, String action) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.addUsersToDistribution(user, action);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have filled all mandatory fields for \"(.*?)\"$")
	public void i_have_filled_all_mendatory_fileds(String form) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.fillMandatoryFields(form);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have attached atleast one document for form and click \"(.*?)\"$")
	public void i_have_attached_atleast_one_document_for_form(String buttonText) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.attachMultipleDocuments(buttonText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have associated atleast one document for form$")
	public void i_have_associated_atleast_one_document_for_form() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.associateMultipleDocuments();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have associated atleast one form for form$")
	public void i_have_associated_atleast_one_form_for_form() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.associateMultipleForms();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Click on \"(.*?)\" button on create form window$")
	public void i_Click_on_button_on_create_form_window(String arg1) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickCreateButton();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be successfully created$")
	public void form_should_be_successfully_created() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsCreated();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be available in \"(.*?)\" listing$")
	public void form_should_be_available_in_listing(String arg1) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsAvailableOnListing();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attached documents should be uploaded And available in form$")
	public void attached_documents_should_be_uploaded_And_available_in_form() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyAttachedDocuments();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form Association should be successfully done with selected documents$")
	public void form_Association_should_be_successfully_done_with_selected_documents() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyFormAssociations();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Distribution action and due date should be valid for Distributed User of workspace ([^\"]*)$")
	public void distribution_action_and_due_date_should_be_valid_for_Distributed_User_of_workspace(String project) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			scripts.verifyDistributionActionAndDate(project);
		}
		else
			Assume.assumeTrue(true);
	}
}
