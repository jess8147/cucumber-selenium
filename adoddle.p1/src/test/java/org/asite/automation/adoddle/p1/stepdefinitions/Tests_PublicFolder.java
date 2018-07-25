package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.PublicFolderScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_PublicFolder
{
	/* ****** "Tests_PublicFolder" is developed by "Vishal Modi" ****** */
	
	PublicFolderScripts publicScript = new PublicFolderScripts();
	
	@When("^right click on \"(.*?)\" AND Click on \"(.*?)\"$")
	public void right_click_on_AND_Click_on(String folderName, String menuOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.clickOnFolderAndSelectOption(folderName, menuOption);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on clip icon of direct link$")
	public void i_click_on_clip_icon_of_direct_link() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.clickOnPopupClipIcon();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I copy link of opened \"(.*?)\" window popup And click on ok button$")
	public void i_copy_link_of_opened_window_popup_And_click_on_ok_button(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.getclipboardText();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^link should be copied to clipboard AND \"(.*?)\" window popup should be closed$")
	public void link_should_be_copied_to_clipboard_AND_window_popup_should_be_closed(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.clickOnPopupCancel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^logout from Adoddle$")
	public void logout_from_Adoddle() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.logOut();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^paste copied link on URL AND hit the URL$")
	public void paste_copied_link_on_URL_AND_hit_the_URL() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.hitCopiedUrl();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Folder should be open as public with ([^\"]*) project$")
	public void folder_should_be_open_as_public(String projectName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.verifyOpenPublicFolder(projectName);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^Select multiple files from opened public folder$")
	public void select_multiple_files_from_opened_public_folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.selectMultipleFiles();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^click on Download button$")
	public void click_on_Download_button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.clickOnDownload();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Batch file should be created And Zip file should be downloaded into Local Directory folder$")
	public void batch_file_should_be_created_And_Zip_file_should_be_downloaded_into_Local_Directory_folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.createBatchFile();
			publicScript.downloadZipDocumentIntoLocal();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Unzip downloaded zip file Into Local$")
	public void i_Unzip_downloaded_zip_file_Into_Local()
	{
		publicScript.zipIntoUnZip();
	}

	@Then("^all selected documents files should be available into Local Directory$")
	public void all_selected_documents_files_should_be_available_into_Local_Directory() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			publicScript.getFileNamesFromLocalFolder();
			publicScript.verifyFilesNameIntoSystem();
			publicScript.deleteZipFileAndUnzipFolder();
		}
		else
			Assume.assumeTrue(true);
	}

}
