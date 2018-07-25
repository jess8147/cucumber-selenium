package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.PublicFolderClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_PublicFolder {
	
	PublicFolderClassicScripts scripts = new PublicFolderClassicScripts();
	
	@When("^I click on ([^\"]*) workspace$")
	public void i_click_on_workspace(String workspaceTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnWorkspace();
		else
			  Assume.assumeTrue(true);
	}
	
	@When("^I click on folder named \"(.*?)\"$")
	public void i_click_on_folder(String folderName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnFolderWithTitle(folderName);
		else
			  Assume.assumeTrue(true);
	}
	
	@Then("^Document listing should open for \"(.*?)\"$")
	public void document_listing_should_open_for(String folderName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentListingPage(folderName);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" from Folder dropdown options$")
	public void i_Click_on_from_Folder_dropdown_options(String editFolderOption) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectEditFolderFromFolderMenu(editFolderOption);
		else	    
			Assume.assumeTrue(true);
		
	}

	@When("^I copy Direct Access URL$")
	public void i_copy_to_clipboard() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.copyDirectAccessURL();
		else
			Assume.assumeTrue(true);
	}

	@When("^logout from Classic$")
	public void logout_from_Classic() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.logoutFromClassic();
		else
			Assume.assumeTrue(true);
	}

	@When("^paste copied link on URL AND hit the URL$")
	public void paste_copied_link_on_URL_AND_hit_the_URL() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.openPageWithCopiedURL();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Folder should be open as public with ([^\"]*) Workspace$")
	public void folder_should_be_open_as_public_with_Workspace(String projectName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyPublicFolder(projectName);
		else
			Assume.assumeTrue(true);
	}

	@When("^Select multiple files from opened public folder$")
	public void select_multiple_files_from_opened_public_folder() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectMultipleFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" button$")
	public void click_on_button(String downloadSelected) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickDownloadSelectedButton(downloadSelected);
		else
			Assume.assumeTrue(true);
	}

	@When("^\"(.*?)\" window should open$")
	public void download_window_should_open(String downloadWindowTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyDownloadWindowOpens(downloadWindowTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all checkboxes except \"(.*?)\" AND click on download button of Download popup$")
	public void i_select_all_checkboxes_except_AND_click_on_download_button_of_Download_popup(String arg1) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectAllCheckboxAndDownload();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Batch file should be created And Zip file should be downloaded into Local Directory folder$")
	public void batch_file_should_be_created_And_Zip_file_should_be_downloaded_into_Local_Directory_folder() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyZipDownload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Unzip downloaded zip file Into Local$")
	public void i_Unzip_downloaded_zip_file_Into_Local() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.zipIntoUnZip();
		else
			Assume.assumeTrue(true);
	}

	@Then("^all selected documents files should be available into Local Directory$")
	public void all_selected_documents_files_should_be_available_into_Local_Directory() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyFilesNameIntoSystem();
		else
			Assume.assumeTrue(true);
	}


}
