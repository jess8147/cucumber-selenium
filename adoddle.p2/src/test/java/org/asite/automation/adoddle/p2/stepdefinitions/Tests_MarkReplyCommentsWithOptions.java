package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.MarkReplyCommentsWithOptions;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_MarkReplyCommentsWithOptions {

	MarkReplyCommentsWithOptions scripts = new MarkReplyCommentsWithOptions();

	@When("^I Navigate to \"(.*?)\" Link AND I get total Unread Discussions Count$")
	public void i_Navigate_to_Link_AND_I_get_total_Unread_Discussions_Count(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.navigateUnreadDiscussion();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have created testdata for mark reply discussions$")
	public void i_have_created_testdata_for_mark_reply_discussions() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createTestData();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Atleast one unread comment should available in comment Listing$")
	public void atleast_one_unread_comment_should_available_in_comment_Listing() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateUnreadCommentList();
		else
			Assume.assumeTrue(true);

	}

	@When("^I search a comment with name as \"(.*?)\"$")
	public void i_Search_a_comment_with_name_as(String comment) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchComment(comment);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Context Click on first available comment in file Listing AND I Select option \"(.*?)\"$")
	public void i_Context_Click_on_first_available_comment_in_file_Listing_AND_I_Select_option(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectComment();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Click Link \"(.*?)\"$")
	public void i_Click_Link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performReadAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Validate \"(.*?)\" Count AND Comment$")
	public void i_Validate_Count_AND_Comment(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.navigateToDiscussionTab();
			scripts.validatePostCommentCount();

		} else
			Assume.assumeTrue(true);
	}

	@Then("^I Select first available comment in listing AND I click link \"(.*?)\"$")
	public void i_Select_first_available_comment_in_listing_and_click_link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectFirstCommentAndLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select Link \"(.*?)\"$")
	public void i_Select_Link(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Context Click AND I Select option \"(.*?)\"$")
	public void i_Context_Click_AND_I_Select_option(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectCommentAndReply();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should Redirected to new window with popup having comment title as \"(.*?)\"$")
	public void i_Should_Redirected_to_new_window_with_popup_having_comment_title_as(String cTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdiateWindowAndPopup(cTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click link \"(.*?)\"$")
	public void i_click_link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Popup should open$")
	public void popup_should_open(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validatePopUpText();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I edit comment title AND description$")
	public void i_edit_comment_title_AND_description() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.commentSubjectAndDescription();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have select option \"(.*?)\"$")
	public void i_have_select_option(String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectAvailableOptions(option);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have associated first two \"(.*?)\" available in Listing$")
	public void i_have_associated_first_two_available_in_Listing(String attachment) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateAttachments(attachment);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Again I Click Link \"(.*?)\"$")
	public void again_I_Click_Link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I \"(.*?)\" Comment Reply$")
	public void i_Comment_Reply(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.submitCommentReply();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Reply should created sucessfully AND I validated all associated documents$")
	public void reply_should_created_sucessfully_AND_I_validated_all_associated_documents() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateCommentReply();
		else
			Assume.assumeTrue(true);
	}

}