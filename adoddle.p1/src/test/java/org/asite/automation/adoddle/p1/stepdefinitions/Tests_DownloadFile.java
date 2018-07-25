package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.DownloadFileScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DownloadFile
{
	/* ****** "Tests_DownloadFile" is developed by "Vishal Modi" ****** */
	
	DownloadFileScripts fileScripts = new DownloadFileScripts();

	@When("^I Click file icon in \"(.*?)\" column$")
	public void i_Click_file_icon_in_column(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.clickOnType();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Document should be downloaded in local$")
	public void document_should_be_downloaded_in_local() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.verifyDocumentInLocalDirectory();
		}
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I click on file link$")
	public void i_click_on_file_link() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.clickOnFileName();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^new tab should be opened$")
	public void new_tab_should_be_opened() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.gotoFileOpenWindow();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on LH-panel in Discussions link$")
	public void i_click_on_LH_panel_in_Discussions_link() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.gotoDiscussionsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^I get the all attached Files Name AND close the new opened tab$")
	public void i_get_the_all_attached_Files_Name_AND_close_the_new_opened_tab() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.gotoAttachmentAndAssociations();
			fileScripts.closeFileOpenedWindow();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I checked opened file checkbox AND click on Download button$")
	public void i_checked_opened_file_checkbox_AND_click_on_Download_button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.gotoDownload();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select all checkboxes AND click on download button of Download popup$")
	public void i_select_all_checkboxes_AND_click_on_download_button_of_Download_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.downloadDocumentsCheckboxSelect();
			fileScripts.clickOnDownload();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Batch file should be created And Zip file should be downloaded into Local Directory$")
	public void batch_file_should_be_created_And_Zip_file_should_be_downloaded_into_Local_Directory() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.createBatchFile();
			fileScripts.downloadZipDocumentIntoLocal();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I Unzip downloaded zip file$")
	public void i_Unzip_downloaded_zip_file() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.zipIntoUnZip();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^all attached files and Parent Document and attachment to parent document should be available into Local Directory$")
	public void all_attached_files_and_Parent_Document_and_attachment_to_parent_document_should_be_available_into_Local_Directory() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			fileScripts.getFileNamesFromLocalFolder();
			fileScripts.verifyFilesNameIntoSystem();
			fileScripts.deleteZipFileAndUnzipFolder();
		}
		else
			Assume.assumeTrue(true);
	}
}
