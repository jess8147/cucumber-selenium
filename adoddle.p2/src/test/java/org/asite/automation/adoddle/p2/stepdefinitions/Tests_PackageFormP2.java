package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.CreatePackageFormP2Scripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_PackageFormP2 {

	CreatePackageFormP2Scripts scripts = new CreatePackageFormP2Scripts();
	
	@When("^I click \"(.*?)\" form link and click on \"(.*?)\" button$") 
	public void i_click_on_form_link_and_click_button(String formLink, String btnText) throws Throwable{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickFormLinkAndCreateButton(formLink);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" form popup should open$")
	public void form_popup_should_open(String popTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreateFormPopup(popTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter all mandatory fields for package form \"(.*?)\" status$")
	public void i_enter_all_mandatory_fields_for_package_form_except_status(String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterMandatoryFields(flag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button to create package form$")
	public void i_click_on_button_to_create_package_form(String buttonText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickButtonAndCreatePackage();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Package form should get created successfully$")
	public void package_form_should_get_created_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyPackageCreation();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Package form status should be \"(.*?)\"$")
	public void package_form_status_should_be(String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyPackageFormStatus(status);
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I search created \"(.*?)\" form$")
	public void i_search_created_package_form(String formType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchForm(formType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I update package form status to \"(.*?)\"$")
	public void update_package_form_status(String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updatePackageFormStatus();
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^Form should not be available to ([^\"]*)$")
		public void form_should_not_be_available_to_doc_controller(String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsNotAvailable();
		else
			Assume.assumeTrue(true);
	}

	 
	@Then("^\"(.*?)\" action should be available for form$")
	public void action_should_be_available_for_form(String actionTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAssignedAction(actionTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^Document controller accepts form and changes status to \"(.*?)\"$")
	public void document_controller_accepts_form_and_changes_status_to(String qaStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.acceptFormAndChangeStatus(qaStatus);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Launch button should be available to create Commenting form \"(.*?)\"$")
	public void button_should_be_available_to_create_Commenting_form(String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyLaunchButtonIsAvailable(flag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I try to edit and distribute package form$")
	public void i_try_to_edit_and_distribute_package_form() {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.openEditFormPopup();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^New package version \"(.*?)\" should be created$")
	public void new_version_should_be_available_to_created(String revision) throws InterruptedException {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAndCreateRevision(revision);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I view the package form and click on \"(.*?)\" button$")
	public void i_view_the_package_form_and_click_on_button(String buttonText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.launchCommentingForm();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I input all mandatory fields \"(.*?)\" reviewer group and create commenting form$")
	public void i_input_all_mandatory_fields_to_create_commenting_form(String reviewerFlag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.inputAllMandatoryFieldsAndCreateForm(reviewerFlag);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I distribute commenting form to ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_distribute_commenting_form_to_PA_Bloggs_and_TC_Bloggs_and_RFI_Bloggs(String tigUser, String taUser, String pmUser) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.distributeCommentingForm(tigUser, taUser, pmUser);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" status should be available for commenting form$")
	public void status_should_be_available_for_commenting_form(String statusText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentingFormStatus(statusText);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" status should be available for associated files$")
	public void status_should_be_available_for_associated_files(String statusText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAssociatedFilesStatus(statusText);
		else
			Assume.assumeTrue(true);
	}

	@When("^User responds to the commenting form$")
	public void user_responds_to_the_commenting_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performRespondAction();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^User selects \"(.*?)\" radio button on comment section$")
	public void user_selects_radio_button_on_comment_section(String radioType) throws Throwable {
	if (Tests_CommonStepDefinitions.runTest)
		scripts.clickRadioButton(radioType);
	else
		Assume.assumeTrue(true);
	}
	
	@When("^User clicks on \"(.*?)\" button on commenting form popup$")
	public void user_clicks_on_button_to_create_comment(String btnText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSendButtonToCreateComment();
		else
			Assume.assumeTrue(true);
	}
		
	@When("^User selects document from dropdown and provides comment and selects severity as \"(.*?)\"$")
	public void user_selects_document_from_dropdown_to_comment_on(String severity) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectDocumentToComment(severity);
		else
			Assume.assumeTrue(true);
	}

	@When("^User opts Document Acceptance Recommendation option as \"(.*?)\"$")
	public void user_opts_Document_Recommendation_option_as(String recommendation) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.optDocumentRecommendationOption(recommendation);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^User opts package Recommendation option as \"(.*?)\"$")
	public void user_opts_package_Recommendation_option_as(String recommendation) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.optPackageRecommendationOption(recommendation);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^User input Comment and select severity as \"(.*?)\"$")
	public void user_input_comment_and_select_severity_as(String severity) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.specifyCommentAndSeverity(severity);
		else
			Assume.assumeTrue(true);
	}
		
	@Then("^Respond Form should contain all comments in Read-Only section$")
	public void respond_Form_should_contain_all_comments_in_Read_Only_section() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllCommentsInReadOnlySection();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Respond Form should contain ([^\"]*) comment in editable section$")
	public void respond_Form_should_contain_RFI_Builder_comment_in_editable_section(String leadReviewer) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyLeadReviewerCommentsInEditableSection(leadReviewer);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Respond Form should contain ([^\"]*) comment for \"(.*?)\" in Read-Only section$")
	public void respond_Form_should_contain_RFI_Builder_comment_in_readonly_section(String user, String type) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentsInReadOnlySection(user, type);
		else
			Assume.assumeTrue(true);
	}

	@When("^User Insert Comment to existing comment$")
	public void user_Insert_Comment_to_existing_comment() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.insertNewComment();
		else
			Assume.assumeTrue(true);
	}

	@When("^User issues form to ([^\"]*) as \"(.*?)\"$")
	public void user_issues_form_to_TC_Bloggs_as(String user, String userRole) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.issueFormToTA(user, userRole);
		else
			Assume.assumeTrue(true);
	}

	@When("^User selects status as \"(.*?)\" for all documents$")
	public void user_selects_status_as_for_all_documents(String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectDocumentStatuses(status);
		else
			Assume.assumeTrue(true);
	}

	@When("^User approves commenting form as \"(.*?)\"$")
	public void user_approves_commenting_form_as(String approveFlag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.userTAApprovesCommentingForm(approveFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^User sends commenting form to ([^\"]*)$")
	public void user_sends_commenting_form_to_user(String toUser) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.sendCommentingFormToUser(toUser);
		else
			Assume.assumeTrue(true);
	}

	@When("^User accepts form comments as \"(.*?)\" with comments$")
	public void user_accepts_form_comments_as_with_comments(String acceptanceFlag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.pmAcceptsFormComments(acceptanceFlag);
		else
			Assume.assumeTrue(true);
	}

}
