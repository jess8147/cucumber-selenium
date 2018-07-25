package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.DraftAutoSaveDistributionScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DraftAutoSaveDistribution {

	DraftAutoSaveDistributionScripts scripts = new DraftAutoSaveDistributionScripts();
 
	@When("^I select any form and open it$")
	public void i_select_any_form_and_open_it() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormAndOpen();
		else 
			Assume.assumeTrue(true);;
	}
	
	@Then("^Form should be viewed in new window$")
	public void form_should_be_viewed_in_new_window() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsViewed();
		else 
			Assume.assumeTrue(true);;
	}
	
	@When("^I distribute selected form to ([^\"]*)$")
	public void i_distribute_selected_form_to_auto_us__atest_com(String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.distributeSelectedForm(user);
		else 
			Assume.assumeTrue(true);;
	}


	@Then("^Form should be available to ([^\"]*)$")
	public void form_should_be_available_to_auto_uk_atest_com(String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsAvailable(user);
		else 
			Assume.assumeTrue(true);
	}

	@When("^I edit form AND assign \"(.*?)\" action to user ([^\"]*), ([^\"]*) and ([^\"]*) using AutoSave$")
	public void i_edit_form_AND_assign_action_to_users(String action, String user1, String user2, String user3) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editFormAndAssignActionWithAutoSave(action, user1, user2, user3);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I edit form and attach \"(.*?)\" file with AutoSave$")
	public void i_edit_form_and_attach_multiple_files_with_autosave(String fileTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editFormAndAttachFiles(fileTitle);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I edit form by removing one file and attaching \"(.*?)\" file with AutoSave$")
	public void i_edit_form_by_removing_one_file_and_attaching_another_file_with_autosave(String filePrefix) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editFormRemoveAndAttachFiles(filePrefix);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I search and re-open above form and select \"(.*?)\"$")
	public void i_search_and_reopen_form(String editDraft) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchAndReOpenFormDraft(editDraft);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button and close current window$")
	public void i_click_on_button_and_close_current_window(String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickCancelButtonAndCloseWinow(buttonText);
		else 
			Assume.assumeTrue(true);
	}
	
	
	@When("^I edit response and assign \"(.*?)\" action to user ([^\"]*)$")
	public void i_edit_response_And_assign_action_to_user(String actionTitle, String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editResponseAndAssignAction(actionTitle, user);
		else 
			Assume.assumeTrue(true);
	}
	
	
	@When("^I click on \"(.*?)\" button for auto saved form$")
	public void i_click_on_button_for_auto_saved_form(String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickSaveDraftButton(buttonText);
		else 
			Assume.assumeTrue(true);
	}
	
	
	@Then("^Draft should have been auto saved$")
	public void draft_should_have_been_auto_saved() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDraftIsSaved();
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" button should be visible$")
	public void button_should_be_visible(String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDiscardDraftButton(buttonText);
		else 
			Assume.assumeTrue(true);
	}
	
	
	@When("^I click on \"(.*?)\" button and switch to previous window$")
	public void i_click_on_calcel_button_and_switch_to_previous_window(String btnText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickCancelDraftButtonAndCloseWindow(btnText);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button to discard the form$")
	public void i_click_on_discard_draft_button_to_discard_the_form(String discardButton) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickDiscardDraftButton();
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" action should not be assigned to user ([^\"]*)$")
	public void action_should_not_be_assigned_to_user(String actionTitle, String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionNotAssigned(actionTitle, user);
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^Auto saved Draft should get discarded$")
	public void auto_saved_draft_should_get_discarded() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDraftIsDiscarded();
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^Reply to form should get discarded$")
	public void reply_to_form_should_get_discarded() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyReplyToFormIsDiscarded();
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^Form should get created with valid attachments$")
	public void form_should_get_created_with_valid_attachments() throws Throwable {
	
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormAttachments();
		else 
			Assume.assumeTrue(true);
	}
	
	
}
