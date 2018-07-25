package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.EditInsertSecondaryFileScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Tests_EditInsertSecondaryFile {

	EditInsertSecondaryFileScripts scripts = new EditInsertSecondaryFileScripts();
	
	@When("^I select \"(.*?)\" files on file listing$")
	public void i_select_multiple_files_on_file_listing(String multiFlag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesOnListing(multiFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on file and select Edit option$")
	public void i_right_click_on_file_and_select_Edit_option() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectEditOption();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" option should be disabled$")
	public void option_should_be_disabled(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifySecondaryFileOption();
		else
			Assume.assumeTrue(true);
	}

	@When("^I search linked file with \"(.*?)\" link$")
	public void i_search_linked_file_with_link(String linkType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchFileWithLink(linkType);
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I select \"(.*?)\" option from context menu$")
	public void i_select_option_from_context_menu(String secondaryFileOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectContextMenuOption(secondaryFileOption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^User should be able to upload only single file$")
	public void user_should_be_able_to_upload_only_single_file() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifySingleFileUploadEnabled();
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I upload file with \"(.*?)\" as secondary file$")
	public void i_upload_file_with_as_secondary_file(String fileType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadFileWithSecondaryFile(fileType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I search uploaded file to edit secondary file$")
	public void i_search_uploaded_file_to_edit_secondary_files() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchUploadedFile();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^User attaches \"(.*?)\" file as secondary file$")
	public void user_attaches_file_as_secondary_file(String fileType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editAttachSecondaryFile(fileType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Secondary file should get replaced for uploaded doc$")
	public void secondary_file_should_get_replaced_for_uploaded_doc() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifySecondaryFileIsEdited();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Audit trail history should show correct data$")
	public void audit_trail_history_should_show_correct_data() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAuditHistoryForSecondaryFile();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I download primary file with all associations$")
	public void i_download_primary_file_with_all_associations() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadPrimaryFileWithAssociations();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Secondary file should get downloaded as \"(.*?)\" file$")
	public void secondary_file_should_get_downloaded_as_file(String extension) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyReplacedSecondaryFileIsDownloaded(extension);
		else
			Assume.assumeTrue(true);
	}
}
