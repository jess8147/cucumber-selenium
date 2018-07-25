package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.PublishPDFviaWorkflow;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_PublishPDFviaWorkflow {

	PublishPDFviaWorkflow scripts = new PublishPDFviaWorkflow();

	@Given("^I have focus subFolder \"(.*?)\" in folder \"(.*?)\"$")
	public void i_have_focus_subFolder_in_folder(String folder1, String folder2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusSubFolderInFolder(folder1, folder2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button AND I have selected file \"(.*?)\" from Local$")
	public void i_clicked_on_button_AND_I_have_selected_file_from_Local(String arg1, String file) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadDocument(file);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered all mandatory attributes AND i have attached secondary file as \"(.*?)\"$")
	public void i_have_entered_all_mandatory_attributes_AND_i_have_attached_secondary_file_as(String secondaryFile)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterMandatoryAttributesAndUploadSecondaryFile(secondaryFile);
		else
			Assume.assumeTrue(true);
	}

	@Then("^file \"(.*?)\" is uploaded successfully AND available in listing$")
	public void file_is_uploaded_successfully_AND_available_in_listing(String file) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdiateDocumentInListing(file);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have Distribute file \"(.*?)\" to users \"(.*?)\" AND \"(.*?)\" with action as \"(.*?)\" successfully$")
	public void i_have_Distribute_file_to_users_AND_with_action_as_successfully(String arg1, String user1,
			String user2, String arg4) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.distributeDocument(user1, user2);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search document \"(.*?)\" successfully AND I edit attributes poi as \"(.*?)\" successfully$")
	public void i_have_search_document_successfully_AND_I_edit_attributes_poi_as_successfully(String arg1, String status)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchDocumentAndEditAttributes(status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have created multiple comments successfully$")
	public void i_have_created_multiple_comments_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createMultipleDiscussionsOnDocument();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search file \"(.*?)\" with action as \"(.*?)\" successfully$")
	public void i_have_search_file_with_action_as_successfully(String arg1, String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchDocumentAndValidateAction(action);
		else
			Assume.assumeTrue(true);
	}

	@When("^I performed \"(.*?)\" action successfully$")
	public void i_performed_action_successfully(String dueAction) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performDueAction(dueAction);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" file version should updated AND Published as PDF document in listing$")
	public void file_version_should_updated_AND_Published_as_PDF_document_in_listing(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocumentInListing();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have download file \"(.*?)\" successfully in Local$")
	public void i_have_download_file_successfully_in_Local(String file) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadAndValidateDcoument(file);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated file size with created test document successfully$")
	public void i_have_validated_file_size_with_created_test_document_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.compareFileSizeInLocal();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have apply filter as \"(.*?)\" AND \"(.*?)\" on file listing$")
	public void i_have_apply_filter_as_AND_on_file_listing(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.applyDcoumentFilter();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Current AND Superseded Versions of published documents should available in file Listing$")
	public void all_Current_AND_Superseded_Versions_of_published_documents_should_available_in_file_Listing()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validatePublishedDocumentIssue();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated Workflow Status as \"(.*?)\" AND workflow Stage as \"(.*?)\" on previous Issue of published document successfully$")
	public void i_have_validated_Workflow_Status_as_AND_workflow_Stage_as_on_previous_Issue_of_published_document_successfully(
			String wStatus, String wStage) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocumentWorkflowStatus(wStatus, wStage);
		else
			Assume.assumeTrue(true);
	}

}