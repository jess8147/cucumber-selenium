package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateCommentingFormScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateCommentingForm {

	CreateCommentingFormScripts scripts = new CreateCommentingFormScripts();

	@When("^I download Commenting form zip file from svn$")
	public void i_download_Commenting_form_zip_files_from_svn() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadCommentingFormFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I download package form xsn file from svn$")
	public void i_download_package_form_xsn_file_from_svn() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadPackageFormXSN();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Package form xsn file should get downloaded successfully$")
	public void package_form_xsn_file_should_get_downloaded_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyPackageXSNIsDownloaded();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Commenting zip file should get downloaded successfully$")
	public void commenting_zip_file_should_get_downloaded_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentingZipIsDownloaded();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have uploaded Commenting zip file to ([^\"]*) project with Admin user$")
	public void i_have_uploaded_commenting_zip_file_to_ttt_project_with_admin_user(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadTTTZipFile(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have uploaded Packaging xsn file to ([^\"]*) project with Admin user$")
	public void i_have_uploaded_packaging_xsn_file_to_ttt_project_with_admin_user(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadPackageFormXSN();
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

	@When("^I enter all mandatory fields for package form$")
	public void i_enter_all_mandatory_fields_for_package_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterMandatoryFields();
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

	@When("^I search created \"(.*?)\" form$")
	public void i_search_created_package_form(String formType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchForm(formType);
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
	public void document_controller_accepts_form_and_changes_status_to(String qaAccepted) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.acceptFormAndChangeStatus(qaAccepted);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" button should be available to create Commenting form$")
	public void button_should_be_available_to_create_Commenting_form(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyLaunchButtonIsAvailable();
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

	@When("^I input all mandatory fields and click Send button to create commenting form$")
	public void i_input_all_mandatory_fields_to_create_commenting_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.inputAllMandatoryFieldsAndCreateForm();
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

	@Then("^User should be able to input Comment and select severity$")
	public void user_should_be_able_to_input_Comment_and_select_severity() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentAndSeverifyFields();
		else
			Assume.assumeTrue(true);
	}

	@Then("^User should be able to opt Package Recommendation options \"(.*?)\" or \"(.*?)\"$")
	public void user_should_be_able_to_opt_Package_Recommendation_options_or(String radioValue1, String radioValue2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyPackageRecommendation(radioValue1, radioValue2);
		else
			Assume.assumeTrue(true);
	}

	@When("^User should be able to select document to comment on$")
	public void user_should_be_able_to_select_document_to_comment_on() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentSelection();
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

	@Then("^\"(.*?)\" action should get cleared for ([^\"]*)$")
	public void action_should_get_cleared_for_PA_Builder(String action, String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionIsClearedForUser(action);
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


	/*@When("^User opts package Recommendation option as \"(.*?)\"$")
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
	} */

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
			scripts.verifyLeadReviewerCommentsInEditableSection();
		else
			Assume.assumeTrue(true);
	}

	/*@When("^User Insert Comment to existing comment$")
	public void user_Insert_Comment_to_existing_comment() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.insertNewComment();
		else
			Assume.assumeTrue(true);
	}

	@When("^User externally adds new comment on another document$")
	public void user_externally_adds_new_comment_on_another_document() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.addAdditionalDocumentComment();
		else
			Assume.assumeTrue(true);
	}*/

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

	@When("^I open commenting form details in new window$")
	public void i_open_commenting_form_details_in_new_window() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.openCommentingForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" button should be available$")
	public void button_should_be_available(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyExportCommentsButton();
		else
			Assume.assumeTrue(true);
	}

	@When("^I export excel report by clicking \"(.*?)\" button$")
	public void i_export_excel_report_by_clicking_button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.exportCommentsIntoExcel();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Excel Report should contains all comments$")
	public void excel_Report_should_contains_all_comments() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyExcelReport();
		else
			Assume.assumeTrue(true);
	}

}
