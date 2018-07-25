package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.MoveFilesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_MoveFiles
{
	MoveFilesScripts filesScripts = new MoveFilesScripts();
	
	@When("^I click on \"(.*?)\" of subfolder$")
	public void i_click_on_of_subfolder(String subfolder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			filesScripts.clickOnSubFolder(subfolder);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select multiple files$")
	public void i_multiple_files_select() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			filesScripts.selectMultipleFilesCheckboxSelect();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^right click on selected files And drag mouse to \"(.*?)\"$")
	public void right_click_on_selected_files_And_drag_mouse_to(String menuOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			filesScripts.clickOnMultipleFilesAndSelectOption(menuOption);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Selected documents should be moved to \"(.*?)\"$")
	public void selected_documents_should_be_moved_to(String folderName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			filesScripts.verifyMovedFilesIntoFolder(folderName);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^click on More Options$")
	public void click_on_More_Options() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			filesScripts.clickOnMoreOptions();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Move Files icon on Options popup$")
	public void i_click_on_Move_Files_icon_on_Options_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			filesScripts.clickOnMoveFiles();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^all Selected documents should be moved to \"(.*?)\" in \"(.*?)\"$")
	public void all_Selected_documents_should_be_moved_to_in(String folderName, String subFolderName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			filesScripts.verifyMovedFilesIntoSubFolder(folderName, subFolderName);
		}
		else
			Assume.assumeTrue(true);
	}
}
