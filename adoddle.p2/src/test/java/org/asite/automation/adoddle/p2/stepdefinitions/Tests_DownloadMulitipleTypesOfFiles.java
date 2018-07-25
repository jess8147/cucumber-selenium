package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DownloadMulitipleTypesOfFilesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DownloadMulitipleTypesOfFiles
{
	DownloadMulitipleTypesOfFilesScripts scripts = new DownloadMulitipleTypesOfFilesScripts();
	
	@When("^I search ([^\"]*) files with linked Files$")
	public void i_search_files_with_linked_Files(String searchFileList) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchDownloadAndVerifyFileWithFileSize(searchFileList);
		else
			Assume.assumeTrue(true);
	}

	@When("^Download Files using Type icon$")
	public void download_Files_using_Type_icon() throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.log.info("Covered in Previous Step Defination");
		else
			Assume.assumeTrue(true);
	}

	@Then("^Files should downloaded in local And Verify with files size$")
	public void files_should_downloaded_in_local_And_Verify_with_files_size() throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.log.info("Covered in Previous Step Defination");
		else
			Assume.assumeTrue(true);
	}
}
