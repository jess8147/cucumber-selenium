package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.CommentingFormScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CommentingFormP2 {

	CommentingFormScripts  scripts = new CommentingFormScripts();
	
	@When("^I search commenting form with \"(.*?)\" action$")
	public void i_search_commenting_for_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchCommentingFormWithAction(action);
		else
			Assume.assumeTrue(true);
	}

	@When("^I respond the form and assign it to ([^\"]*)$")
	public void i_respond_the_form_and_assign_it_to_user(String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.respondActionAndAssignTo(user);
		else
			Assume.assumeTrue(true);
	}

	@When("^([^\"]*) User responds commenting for with Accepted \"(.*?)\"$")
	public void user_responds_commenting_for_with_Accepted(String user, String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.respondCommentingWithStatus(user, status);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I enter all mandatory fields for package form$")
	public void i_enter_all_mandatory_fields_for_package_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterMandatoryFields();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form status should be \"(.*?)\"$")
	public void form_status_should_be(String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormStatus(status);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I filter package form with status \"(.*?)\" and QA status as \"(.*?)\" and recipient as ([^\"]*)$")
	public void i_filter_package_form_with_status_and_QA_status_as(String status, String qaStatus, String recipient) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.filterPackageFormWithStatus(status, qaStatus, recipient);
		else
			Assume.assumeTrue(true);
	}

	@Then("^All the package forms with \"(.*?)\" status should be displayed$")
	public void all_the_package_forms_with_status_should_be_displayed(String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyPackageForms(status);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select one package form and distribute with \"(.*?)\" status$")
	public void i_select_one_package_form_and_distribute_with_status(String submittedStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.submitPackageForm(submittedStatus);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I upload multiple documents in \"(.*?)\" folder$")
	public void i_upload_multiple_documents_in_folder(String folderTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadMultipleDocuments(folderTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Package form revision should be updated$")
	public void package_form_revision_should_be_updated() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyPackageFormRevision();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I change QA Check status of package form to \"(.*?)\"$")
	public void i_change_QA_Check_status_of_package_form_to(String qaCheckStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.changeQACheck(qaCheckStatus);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Launch Commenting Form button should be displayed$")
	public void launch_commenting_form_button_should_be_displayed() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyLaunchCommentingButton();
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I launch commenting form by clicking \"(.*?)\" button$")
	public void i_launch_commenting_form_by_clicking_button(String btnText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.launchCommentingForm(btnText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Review Coordinator and Reviewer group$")
	public void i_select_Review_Coordinator_and_Reviewer_group() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectValidReviewerGroups();
		else
			Assume.assumeTrue(true);
	}

	@When("^I create Commenting form by clicking \"(.*?)\" button$")
	public void i_create_Commenting_form_by_clicking_button(String buttonText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickButtonWithText(buttonText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Comment form with updated Revision should be created$")
	public void comment_form_with_updated_Revision_should_be_created() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentFormRevision();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I open respond popup for commenting form$")
	public void i_open_respond_popup_for_commenting_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.openRespondPopup();
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform respond action for commenting form$")
	public void i_perform_respond_action_for_commenting_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.respondCommentingForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^I try to respond the commenting form$")
	public void i_try_to_respond_the_commenting_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.replyTheCommentingForm();
		else
			Assume.assumeTrue(true);
	}

	
	@Then("^Alert should be displayed with valid authorization message$")
	public void alert_should_be_displayed_with_valid_authorization_message() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAlertMessage();
		else
			Assume.assumeTrue(true);
	}
	

}
