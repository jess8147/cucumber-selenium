package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.EditORISaveDraftUpdateScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_EditORISaveDraftUpdate {

	EditORISaveDraftUpdateScripts scripts  = new EditORISaveDraftUpdateScripts();
	
	@When("^I create form of type Edit ORI$")
	public void i_create_form_of_type_Edit_ORI() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createORIForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be created successfully$")
	public void form_should_be_created_successfully() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsCreated();
		else
			Assume.assumeTrue(true);
	}

	@When("^I search created Edit ORI Form$")
	public void i_search_created_edit_ori_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchCreatedForm();
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I context click on \"(.*?)\" Edit ORI form$")
	public void i_context_click_on_Edit_ORI_form(String formCount) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickORIForms(formCount);
		else
			Assume.assumeTrue(true);
	}

	@When("^Edit Message option should be \"(.*?)\"$")
	public void edit_message_option_should_be_enabled(String option) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyEditMessageOption(option);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on form to view the form$")
	public void i_click_on_form_to_view_the_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFormToViewTheForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" button should be disabled$")
	public void button_should_be_disabled(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyEditORIButtonIsDisabled();
		else
			Assume.assumeTrue(true);
	}

	@When("^I search \"(.*?)\" form of type Edit ORI$")
	public void i_search_form_of_type_Edit_ORI(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchClosedEditORIForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^User edits form of type EDIT ORI$")
	public void user_edits_form_of_type_EDIT_ORI() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editORIForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^User saves EDIT ORI form as Draft$")
	public void user_saves_EDIT_ORI_form_as_Draft() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.saveORIFormAsDraft();
		else
			Assume.assumeTrue(true);
	}

	@When("^User again tries to edit form of type EDIT ORI$")
	public void user_again_tries_to_edit_form_of_type_EDIT_ORI() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.reEditORIForm();
		else
			Assume.assumeTrue(true);
	}

	
	@Then("^\"(.*?)\" popup should be displayed$")
	public void popup_should_be_displayed(String popupTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPopupWithTitle(popupTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^User opts \"(.*?)\" option and clicks it$")
	public void user_opts_option(String btnText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickButtonWithText(btnText);
		else
			Assume.assumeTrue(true);
	}

	@When("^User modifies form details and clicks on \"(.*?)\" button$")
	public void user_modifies_form_details_and_clicks_on_cancel_button(String btnText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.modifyFormDetailsClickButton(btnText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form Draft should get updated to Form$")
	public void form_draft_should_get_updated_to_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDraftIsSavedToForm();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^User clicks \"(.*?)\" button for form of type EDIT ORI$")
	public void user_clicks_button_for_form_of_type_edit_ori(String btnText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickDiscardDraftButton(btnText);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Saved ORI Draft should get discarded$")
	public void saved_ori_draft_should_get_discarded() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDraftIsDiscarded();
		else
			Assume.assumeTrue(true);
	}
	
	
}
