package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.CreateCommentClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_CreateComment {

	CreateCommentClassicScripts scripts = new CreateCommentClassicScripts();

	@When("^I click on \"(.*?)\" of any document$")
	public void i_click_on_of_any_document(String auditTrailTooltip) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnDocumentDistributionHistory(auditTrailTooltip);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link text$")
	public void i_click_on_link_text(String commentAssociationsLink) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnCommentAndAssociationsLink(commentAssociationsLink);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should open in new tab$")
	public void page_should_open_in_new_tab(String createComment) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyCreateCommentPage(createComment);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have entered \"(.*?)\" in Title$")
	public void i_have_entered(String createCommentTitle) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.enterCommentTitle(createCommentTitle);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have entered \"(.*?)\" into Comment$")
	public void i_have_entered_into(String createCommentText) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.enterCommentText(createCommentText);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have selected User \"(.*?)\" into Document Distribution with Action \"(.*?)\"$")
	public void i_have_selected_Users_into(String user, String action) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.addUsersToDistribution(user, action);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have associated atleast one document to comment$")
	public void i_have_associated_atleast_one_document() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.associateMultipleDocuments();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have attached atleast one external document to comment$")
	public void i_have_associated_atleast_one_discussion() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.attachMultipleDocuments();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have associated atleast one form to comment$")
	public void i_have_associated_atleast_one_form() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.associateMultipleForms();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on create comment window$")
	public void i_click_on_send_button_on_create_comment_window(String buttonText) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnSendButton(buttonText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Comment should be successfully created$")
	public void comment_should_besuccessfully_created() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyCommentIsCreated();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Comment should be available in comments tab into \"(.*?)\" page$")
	public void comment_should_be_available_in_comments_tab_into_page(String pageTitle) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyCommentOnListingPage(pageTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" of comment from \"(.*?)\" tab$")
	public void i_click_on_of_comment_from_tab(String linkText, String tabText) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnViewCommentDetails(linkText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^comment detail window should open$")
	public void comment_detail_should_open() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyCommentDetailWindow();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Associated documents AND discussions should be available on comment details$")
	public void associated_documents_AND_discuaaions_should_be_available_on_comment_details() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyCommentDocsAssociations();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Associated forms should be available on comment details$")
	public void associated_forms_should_be_available_on_comment_details() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyCommentFormsAssociations();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attached docs should be uploaded and available on comment details$")
	public void attached_docs_should_be_uploaded_and_available_on_comment_details() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyCommentDocsAttachments();
		else
			Assume.assumeTrue(true);
	}

}
