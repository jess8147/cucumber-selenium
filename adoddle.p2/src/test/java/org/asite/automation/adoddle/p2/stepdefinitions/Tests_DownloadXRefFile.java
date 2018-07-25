package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DownloadXRefFileScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DownloadXRefFile {

	DownloadXRefFileScripts scripts = new DownloadXRefFileScripts();


	@Given("^I search document with XRef having title \"(.*?)\"$")
	public void i_search_document_with_XRef_having_title(String docTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchXRefDocument(docTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^XRef document should get filtered with blue icon$")
	public void xref_document_should_get_filtered_with_blue_icon() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentIcon();
		else
			Assume.assumeTrue(true);
	}

	@When("^I assign \"(.*?)\" action to current user$")
	public void i_assign_action_to_current_user(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.assignActionToCurrentUser(action);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on blue icon of file with XRef$")
	public void i_click_on_blue_icon_of_file_with_XRef() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnBlueXrefFileIcon();
		else
			Assume.assumeTrue(true);
	}

	@When("^I download the Xref file as \"(.*?)\" file$")
	public void i_download_the_xref_file(String extension) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadXRefFile(extension);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Parent file should get downloaded$")
	public void only_parent_file_should_get_downloaded() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyDownloadedFile();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Downloaded file size should match with actual file size$")
	public void downloaded_file_size_should_match_with_actual_file_size() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyDownloadedFileSize();
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^\"(.*?)\" action should get cleared$")
	public void action_should_get_cleared(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionIsCleared(action);
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I click on the XRef file docref$")
	public void i_click_on_the_file_docref() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickFileDocRef();
		else
			Assume.assumeTrue(true);
	}

	@Then("^XRef file should open in new window$")
	public void xref_file_should_open_in_new_window() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyXRefFileIsOpened();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" on LH Panel$")
	public void i_click_on_on_LH_Panel(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAssociationLHPanel();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get list of all the files associated and close the window$")
	public void i_get_list_of_all_the_files_associated_and_close_the_window() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.getXRefChildAssociationFilesList();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select the Xref file by clicking on file checkbox$")
	public void i_select_the_file_by_clicking_on_file_checkbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectXRefFileCheckbox();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Download button on file listing$")
	public void i_click_on_Download_button_on_file_listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickDownloadLinkButton();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" checkbox \"(.*?)\" and click on Download button$")
	public void i_select_checkbox_and_click_on_Download_button(String xRefOption, String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectXRefOptionAndClickDownload(flag);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I unzip all the Xref files from zip file$")
	public void i_unzip_all_the_xref_files_from_zip_file() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
				scripts.unzipDownloadedXRefZipFile();
		else
				Assume.assumeTrue(true);
	}


	@Then("^All files should be available at download location with correct file sizes$")
	public void all_files_should_be_available_at_download_location_with_correct_file_sizes() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllParentAndChildFiles();
		else
			Assume.assumeTrue(true);
	}
	
}
