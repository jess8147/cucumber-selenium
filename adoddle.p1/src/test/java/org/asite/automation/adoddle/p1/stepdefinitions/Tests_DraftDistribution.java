package org.asite.automation.adoddle.p1.stepdefinitions;
//import com.asite.adoddle.scripts.DraftDistributionScripts;
import org.asite.automation.adoddle.p1.scripts.DraftDistributionTestScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DraftDistribution {

	DraftDistributionTestScripts scripts = new DraftDistributionTestScripts();
 
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
			scripts.verifyFormIsAvailable();
		else 
			Assume.assumeTrue(true);
	}

	@When("^I edit form AND assign \"(.*?)\" action to user ([^\"]*), ([^\"]*) and ([^\"]*)$")
	public void i_edit_form_AND_assign_action_to_users(String action, String user1, String user2, String user3) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editFormAndAssignAction(action, user1, user2, user3);
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" action should be assigned to user ([^\"]*)$")
	public void action_should_be_assigned_to_user_PA_Builder(String actionTitle, String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionAssigned(actionTitle);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I perform review draft and assign \"(.*?)\" action to user ([^\"]*)$")
	public void i_edit_response_And_assign_action_to_user(String actionTitle, String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editResponseAndAssignAction(actionTitle, user);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^User ([^\"]*) creates form from draft distribution$")
	public void user_creates_form_from_draft_distribution(String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFormFromDraft(user);
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" actions should get cleared$")
	public void actions_should_get_cleared_for_all_users(String actionTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionsCleared(actionTitle);
		else 
			Assume.assumeTrue(true);
	}

}
