package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.FilesStartWorkflowScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_FilesStartWorkflow
{
	FilesStartWorkflowScripts scripts = new FilesStartWorkflowScripts();
	
	@When("^I select \"(.*?)\" folder AND I click on \"(.*?)\" button$")
	public void i_select_folder_AND_I_click_on_button(String folder, String addFiles) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.selectFolderAndClickOnAddFiles(folder, addFiles);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^file should uploaded successfully for Start Workflow$")
	public void file_should_uploaded_successfully_for_Start_Workflow() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyUploadedFile();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I create new form on selected document using Start Workflow$")
	public void i_create_new_form_on_selected_document_using_Start_Workflow() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.createForm();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" image should displayed in selected document$")
	public void image_should_displayed_in_selected_document(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyAssociationsOnFile();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^created new Form should displayed on popup$")
	public void created_new_Form_should_displayed_on_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyFormOnAttachmentAssociationPopup();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should opened And selected Document should displayed as Associations on popup$")
	public void new_tab_should_opened_And_selected_Document_should_displayed_as_Associations_on_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyFileOnAttachmentAssociationPopup();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select file AND click on \"(.*?)\" and select \"(.*?)\" from Options popup list$")
	public void i_select_file_AND_click_on_and_select_from_Options_popup_list(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickMoreOptionsAndSelectOption();
		}
		else
			Assume.assumeTrue(true);
	}
}