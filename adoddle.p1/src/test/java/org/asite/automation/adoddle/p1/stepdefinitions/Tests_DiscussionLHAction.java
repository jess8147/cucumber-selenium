package org.asite.automation.adoddle.p1.stepdefinitions;
import org.asite.automation.adoddle.p1.scripts.DiscussionLHActionScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DiscussionLHAction {
	
	DiscussionLHActionScripts scripts = new DiscussionLHActionScripts();
	
	@Given("^I am on Discussions tab$")
	public void i_am_on_Discussions_tab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.navigateToDiscussionTab();
		else
			Assume.assumeTrue(true);
	   
	}

	@Given("^I have atleast one \"(.*?)\"$")
	public void i_have_atleast_one(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUnreadComment();
		else
			Assume.assumeTrue(true);
	}

	@When("^I drag mouse on \"(.*?)\" count$")
	public void i_drag_mouse_on_count(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.dragMouseOnUnreadDiscussions();
		else
			Assume.assumeTrue(true);
	   
	}

	@Then("^Total number of action should display on mouse over$")
	public void total_number_of_action_should_display_on_mouse_over() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUnreadCommentTooltipCount();
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I click on \"(.*?)\"$")
	public void i_click_on(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnUnreadDiscussionsLink();
		else
			Assume.assumeTrue(true);

	}

	@Then("^My all \"(.*?)\" documents should be available in Discussions listing$")
	public void my_all_documents_should_be_available_in_Discussions_listing(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUnreadDocuments();
		else
			Assume.assumeTrue(true);
	   
	}

	@Then("^\"(.*?)\" filter should be applied for Action Status$")
	public void filter_should_applied_action_status(String filterText) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionStatusFilter(filterText);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" filter should be applied for Recipient Name$")
	public void filter_should_applied_recipient_name(String filterText) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyRecipientNameFilter(filterText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform on any action from Discussions listing$")
	public void i_perform_on_any_action_from_Discussions_listing() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.completeDiscussionAction();
		else
			Assume.assumeTrue(true);
	
	}

	@Then("^Total number of action count should \"(.*?)\" be decrease by one$")
	public void total_number_of_action_count_should_be_decrease_by_one(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionCountDecreased();
		else
			Assume.assumeTrue(true);
	}

}
