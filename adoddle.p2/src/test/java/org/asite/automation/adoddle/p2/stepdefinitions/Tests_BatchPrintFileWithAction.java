package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.BatchPrintFileWithActionScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_BatchPrintFileWithAction
{
	BatchPrintFileWithActionScripts scripts = new BatchPrintFileWithActionScripts();
	
	@When("^I clicked on \"(.*?)\" button And I upload more than one Files$")
	public void i_clicked_on_button_And_I_upload_more_than_one_Files(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnSelectFilesAndUpload();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I fill all mendatory fields And assign \"(.*?)\" action$")
	public void i_fill_all_mendatory_fields_And_assign_action(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.enterMendatoryAttributes(action);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^files should uploaded successfully And \"(.*?)\" action should assigned to uploaded documents$")
	public void files_should_uploaded_successfully_And_action_should_assigned_to_uploaded_documents(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyUploadedFilesAndActions(action);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select all files and perform right click and select context menu option \"(.*?)\"$")
	public void i_select_all_files_and_perform_right_click_and_select_context_menu_option(String contextOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.contextClickAndSelectOption(contextOption);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected all files should displayed on popup$")
	public void selected_all_files_should_displayed_on_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifySelectedFilesOnPopup();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should opened And \"(.*?)\" popup should open for BatchPrint files$")
	public void new_tab_should_opened_And_popup_should_open_for_BatchPrint_files(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyFilesOpenedForBatchPrint();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed for selected all BatchPrint documents$")
	public void action_should_completed_for_selected_all_BatchPrint_documents(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyBatchPrintFilesActionCompleted(action);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all files AND click on \"(.*?)\" and select \"(.*?)\" from Options popup list$")
	public void i_select_all_files_AND_click_on_and_select_from_Options_popup_list(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickMoreOptionsAndSelectOption();
		}
		else
			Assume.assumeTrue(true);
	}
}
