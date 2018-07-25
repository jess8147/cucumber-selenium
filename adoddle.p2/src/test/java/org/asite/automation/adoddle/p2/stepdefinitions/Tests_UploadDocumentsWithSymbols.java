package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.UploadDocumentsSymbols;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_UploadDocumentsWithSymbols extends AdoddleCommonAppMethods {

	private String validationInstance1 = "preActionValidation", validationInstance2 = "postActionValidation";

	UploadDocumentsSymbols scripts = new UploadDocumentsSymbols();

	@Given("^I have focus on folder \"(.*?)\" in workspace ([^\"]*)$")
	public void i_have_focus_on_folder_in_workspace_AutomationTest_$_UK_Workspace(String folder, String workspace)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusFolderAndWorkspace(workspace, folder);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have published Multiple documents with different docRef docStatus docRef And DocTitle$")
	public void i_have_published_Multiple_documents_with_different_docRef_docStatus_docRef_And_DocTitle()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectMultipleFilesAndUpload();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All documents should published with correct docStatus docPoi docRef And DocTitle successfully$")
	public void all_documents_should_published_with_correct_docStatus_docPoi_docRef_And_DocTitle_successfully()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocumentsWithAttiributes(validationInstance1);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have created comment with title as \"(.*?)\" successfully$")
	public void i_have_created_comment_with_title_as_successfully(String title) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createMultiDocComment(title);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search document AND click on file name to open document in file viewer successfully$")
	public void i_have_search_document_AND_click_on_file_name_to_open_document_in_file_viewer_successfully()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchDocumentAndClickOnTitle();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" link of file$")
	public void i_click_link_of_file(String buttonType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickCreateCommentLink(buttonType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have attached more than files having special characters successfully$")
	public void i_have_attached_more_than_files_having_special_characters_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSelectFilesAndAttachDocuments();
		else
			Assume.assumeTrue(true);
	}

	@Then("^aMessages form loaded successfully for comment creation$")
	public void amessages_form_loaded_successfully_for_comment_creation() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyaMessageForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter comment title as \"(.*?)\" AND all mandatory attributes for comment successfully$")
	public void i_have_enter_comment_title_as_AND_all_mandatory_attributes_for_comment_successfully(String comment)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterCommentMandatoryAttributes(comment);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on \"(.*?)\" button for file$")
	public void i_click_on_button_for_file(String buttonType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnCreateFormHeaderButtonOptions(buttonType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I associate more than one Files having special characters in \"(.*?)\" folder in workspace ([^\"]*)$")
	public void i_associate_more_than_one_Files_having_special_characters_in_folder_in_workspace_AutomationTest_$_UK_Workspace(
			String folder, String workspace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateCommentDocuments(folder, workspace);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button AND close file window successfully$")
	public void i_click_on_button_AND_close_file_window_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.saveCommentAndCloseWindow();
			scripts.verifySuccessMessage();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have validated comment \"(.*?)\" successfully$")
	public void i_have_validated_comment_successfully(String title) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateCommentInListing(title);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have created form \"(.*?)\" AND validated in listing successfully$")
	public void i_have_created_form_AND_validated_in_listing_successfully(String formTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createAppAndValidate(formTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have published Multiple documents revisions with different docRef docStatus docRef And DocTitle$")
	public void i_have_published_Multiple_documents_revisions_with_different_docRef_docStatus_docRef_And_DocTitle()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.publishDocumentRevisions();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have downloaded all published documents revisions in local directory successfully$")
	public void i_have_downloaded_all_published_documents_revisions_in_local_directory_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadPublishDocumentRevisions();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated published Documents with action as \"(.*?)\" successfully$")
	public void i_have_validated_published_Documents_with_action_as_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocumentsWithAttiributes(validationInstance2);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated published Documents with tasks as \"(.*?)\" successfully$")
	public void i_have_validated_published_Documents_with_tasks_as_successfully(String task) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocumentsWithAttiributes(validationInstance1);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Deactivated all Test files$")
	public void i_Deactivated_all_Test_files() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.deactivateAllFiles();
		Assume.assumeTrue(true);
	}

}