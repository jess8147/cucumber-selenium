package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.UploadFilesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_UploadFiles
{
	/* ****** "Tests_UploadFiles" is developed by "Vishal Modi" ****** */
	
	UploadFilesScripts scripts = new UploadFilesScripts();
	
	@When("^I click on Project Name ([^\"]*) AND I click on Folder Name \"(.*?)\" AND I click on \"(.*?)\" button$")
	public void i_click_on_Project_Name_AND_I_click_on_Folder_Name_AND_I_click_on_button(String projectName, String folderName, String addFiles) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnProjectAndFolderAndAddFiles(projectName, folderName, addFiles);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button AND I have selected more then one Files from Local$")
	public void i_clicked_on_button_AND_I_have_selected_more_then_one_Files_from_Local(String selectFile) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnSelectFilesAndUpload();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered all mendatory attributes$")
	public void i_have_entered_all_mendatory_attributes() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.enterMendatoryAttributes();
			scripts.getUploadFilesAttributesValue();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button AND I distribute to Distribution Group$")
	public void i_click_on_button_AND_I_distribute_to_Distribution_Group(String DistributeFiles) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnDistributeFiles(DistributeFiles);
			scripts.enterDistributeGroupText();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Upload button$")
	public void i_click_on_Upload_button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnUpload();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Files should be uploaded successfully AND I should redirected on Document listing$")
	public void all_Files_should_be_uploaded_successfully_AND_I_should_redirected_on_Document_listing() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyUploadedFilesAttributesValue();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Uploaded documents should be available in document listing$")
	public void uploaded_documents_should_be_available_in_document_listing() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyUploadFilesAndAttributes();
			scripts.downloadAndVerifySize();
			scripts.deleteCreatedFiles();
		}
		else
			Assume.assumeTrue(true);
	}
}
