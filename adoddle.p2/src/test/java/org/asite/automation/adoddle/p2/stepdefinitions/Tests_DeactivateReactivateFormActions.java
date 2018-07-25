package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DeactivateReactivateFormActions;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DeactivateReactivateFormActions {

	DeactivateReactivateFormActions  drfscripts = new DeactivateReactivateFormActions();
	
	@When("^I create \"(.*?)\" and distribute to ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_create_private_form_and_distribute_to_multiple_users(String ftype, String pUser, String sUser, String tUser, String fUser) throws Throwable {
	   if(Tests_CommonStepDefinitions.runTest)
		   drfscripts.createPrivateFormAndDistribute(ftype, pUser, sUser, tUser, fUser);
	   else
		   Assume.assumeTrue(true);
	}

	@When("^I search created private form$")
	public void i_search_created_private_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			   drfscripts.searchCreatedPrivateForm();
		   else
			   Assume.assumeTrue(true);
	}

	@When("^I \"(.*?)\" actions of ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_deactivate_reactivate_actions_of_multiple_users(String actionType, String pUser, String sUser, String tUser, String fUser) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			drfscripts.deactivateReactivateActions(actionType, pUser, sUser, tUser, fUser);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form actions count will be \"(.*?)\" and form will be \"(.*?)\" for ([^\"]*)$")
	public void form_actions_count_will_be_and_form_will_be_for_Automation_UK(String countFlag, String visibilityFlag, String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			drfscripts.verifyFormVisibilityAndCounts(countFlag, visibilityFlag, user);
		else
			Assume.assumeTrue(true);
	}

}
