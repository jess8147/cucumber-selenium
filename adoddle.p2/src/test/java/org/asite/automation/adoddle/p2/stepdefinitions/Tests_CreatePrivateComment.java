package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.CreatePrivateCommentScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreatePrivateComment {

	CreatePrivateCommentScripts scripts = new CreatePrivateCommentScripts();

	@Given("^I have atleast one document to create comment on listing$")
	public void i_have_atleast_one_document_to_create_comment() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyDocumentAvailable();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Right click on any document$")
	public void i_Right_click_on_any_document() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.contextClickDocument();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Right click on private discussion$")
	public void i_Right_click_on_private_discussion() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.contextClickFirstDiscussion();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have selected users into \"(.*?)\" as ([^\"]*) and ([^\"]*)$")
	public void i_have_selected_Users_into(String toFieldText, String user1, String user2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUserExistsInToField(toFieldText, user1, user2);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have attached atleast one documents$")
	public void i_have_attached_atleast_one_documents() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.attachAtLeastOneDocument();
		} else
			Assume.assumeTrue(true);

	}

	@Given("^I have checked private checkbox$")
	public void i_have_checked_private_checkbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.checkPrivateCheckbox();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^User should be switched to new window$")
	public void user_should_switch_to_new_window() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.getWindowHandle();
			scripts.waitForSwitchWindow(2);
			scripts.switchWindow();
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^User creates reply to ([^\"]*) user from To list$")
	public void user_creates_reply_and_removes_user(String replyUser) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performReplyAction(replyUser);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Reply should get created sucessfully$")
	public void reply_should_get_created_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyReplyIsSuccessful();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^User should be able to view private discussion reply \"(.*?)\"$")
	public void user_should_be_able_to_view_private_discussion_reply(String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyPrivateDiscussionReply(flag);
		else
			Assume.assumeTrue(true);
	}

	@Then("^([^\"]*) should get comment reply as aMessage from ([^\"]*)$")
	public void user_should_get_comment_reply_as_amessage_from_responder(String user, String responder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyReplyIsAvailableOnAMessages(user, responder);
		else
			Assume.assumeTrue(true);
	}


	
}