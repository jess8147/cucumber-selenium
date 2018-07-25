package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.DownloadFilesClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_DownloadFiles {
	DownloadFilesClassicScripts classicScripts = new DownloadFilesClassicScripts();

	@When("^I Click file icon in \"(.*?)\" column of any file$")
	public void i_Click_file_icon_in_column_of_any_file(String arg1) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.clickOnType();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Document should be downloaded in local$")
	public void document_should_be_downloaded_in_local() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.verifyDocumentInLocalDirectory();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I checked file checkbox AND click on file link Distribution history$")
	public void i_checked_file_checkbox_AND_click_on_file_link_Distribution_history() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.clickOnFileCheckboxAndDocumentDistributionHistory();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^new tab should be opened$")
	public void new_tab_should_be_opened() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.verifyNewTabWindow();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" link AND I click \"(.*?)\" link$")
	public void i_click_link_AND_I_click_link(String commentsAssociations, String viewCommentDetails) {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.clickOnCommentsAssociationsAndViewCommentDetails(commentsAssociations, viewCommentDetails);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^new tab should open for comment details$")
	public void new_tab_should_open() {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.verifyCommentDetailsWindow();

		} else
			Assume.assumeTrue(true);
	}

	@Then("^I get the all attached AND Associated Files Name And close the new opened tab$")
	public void i_get_the_all_attached_AND_Associated_Files_Name_And_close_the_new_opened_tab() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.getFileNamesFromApplication();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" img icon$")
	public void i_click_on_img_icon(String arg1) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.clickOnDownloadSelectedDocuments();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select all checkboxes AND click on download button of Download popup$")
	public void i_select_all_checkboxes_AND_click_on_download_button_of_Download_popup() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.selectAllCheckboxAndDownload();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Batch file should be created And Zip file should be downloaded into Local Directory$")
	public void batch_file_should_be_created_And_Zip_file_should_be_downloaded_into_Local_Directory() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.downloadZipDocumentIntoLocal();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Unzip downloaded zip file$")
	public void i_Unzip_downloaded_zip_file() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.zipIntoUnZip();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^all attached files and Parent Document and attachment to parent document should be available into Local Directory$")
	public void all_attached_files_and_Parent_Document_and_attachment_to_parent_document_should_be_available_into_Local_Directory()
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.getFileNamesFromLocalFolder();
			classicScripts.verifyDownloadedFiles();
			classicScripts.deleteZipFileAndUnzipFolder();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select all checkboxes with \"(.*?)\" checkbox$")
	public void i_select_all_checkboxes_with_checkbox(String arg1) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.selectAllCheckboxWithExtractFiles();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select download files path AND click on download button of Download popup$")
	public void i_select_download_files_path_AND_click_on_download_button_of_Download_popup() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.selectPathAndClickOnDownload();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Batch file should be created$")
	public void batch_file_should_be_created() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.waitForCreatingBatchFile();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^all files should be available into Local Directory$")
	public void all_files_should_be_available_into_Local_Directory() {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.getFilesNameIntoLocalDirectoryFolder();
			classicScripts.verifyDownloadedFiles();
		} else
			Assume.assumeTrue(true);
	}
}
