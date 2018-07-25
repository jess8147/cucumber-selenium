package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CommentAssociationsForm;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Tests_CommentAssociationForm {

	CommentAssociationsForm scripts = new CommentAssociationsForm();

	@Then("^Atleast one unread comment should available in comment Listing with name as \"(.*?)\"$")
	public void atleast_one_unread_comment_should_available_in_comment_Listing_with_name_as(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateUnreadCommentListing();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select multiple comments available in Listing AND I Context Click AND I Select option \"(.*?)\"$")
	public void i_Select_multiple_comments_available_in_Listing_AND_I_Context_Click_AND_I_Select_option(String option1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectMultipleComment(option1);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select Option \"(.*?)\"$")
	public void i_Select_Option(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectAvailableOption();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New \"(.*?)\" Popup should open$")
	public void new_Popup_should_open(String text) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreateFormPopup(text);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Search Form as \"(.*?)\"$")
	public void i_Search_Form_as(String form) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createForm(form);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Form Should Load sucessfully$")
	public void form_Should_Load_sucessfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateNewForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select user as ([^\"]*) AND subject as \"(.*?)\"$")
	public void i_Select_user_as_Automation_UK_AND_subject_as(String user, String arg2) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectUserAndSubject(user);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Click on \"(.*?)\" Button$")
	public void i_Click_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickSaveButton();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Search form AND Validated associated discussions$")
	public void i_Search_form_AND_Validated_associated_discussions() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateFormAndDiscussions();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select multiple comments available in Listing AND I Click Link \"(.*?)\"$")
	public void i_Select_multiple_comments_available_in_Listing_AND_I_Click_Link(String option2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectMultipleComment(option2);
		else
			Assume.assumeTrue(true);
	}
}
