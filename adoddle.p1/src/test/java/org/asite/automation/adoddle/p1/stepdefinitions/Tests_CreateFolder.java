package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateFolderScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateFolder 
{
	CreateFolderScripts folderScript = new CreateFolderScripts();
	
	@When("^I Right Click on Project as ([^\"]*)$")
	public void i_Right_Click_on_Project_as(String projectName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.clickOnProjectName(projectName);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^drag mouse to \"(.*?)\" AND Click on \"(.*?)\"$")
	public void drag_mouse_to_AND_Click_on(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.clickOncreateNewFolder();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I Enter FolderName AND I Click on \"(.*?)\" button$")
	public void i_Enter_FolderName_AND_I_Click_on_button(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.createFolder();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Folder should be created AND Folder should be available in Folder Tree$")
	public void folder_should_be_created_AND_Folder_should_be_available_in_Folder_Tree() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.verifyFolderName();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I Right Click on Folder AND Click on \"(.*?)\"$")
	public void i_Right_Click_on_Folder_AND_Click_on(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.clickOnEditFolder();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I Change Folder Name AND Click on \"(.*?)\" button$")
	public void i_Change_Folder_Name_AND_Click_on_button(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.editFolder();
		folderScript.clickOnUpdate();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Updated Folder should be available in Folder Tree$")
	public void updated_Folder_should_be_available_in_Folder_Tree() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.verifyEditedFolderName();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I Right Click on Updated Folder AND Click on \"(.*?)\" AND Select \"(.*?)\" Checkbox AND Click on \"(.*?)\" button$")
	public void i_Right_Click_on_Updated_Folder_AND_Click_on_AND_Select_Checkbox_AND_Click_on_button(String arg1, String arg2, String arg3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.clickOnEditedFolder();
		folderScript.deactivateCheckboxSelect();
		folderScript.clickOnUpdate();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\" button of Clear Actions$")
	public void i_Click_on_button_of_Clear_Actions(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.clickOnOk();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Updated Folder should not be available in Folder Tree$")
	public void updated_Folder_should_not_be_available_in_Folder_Tree() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
		folderScript.verifyEditedFolderDeactivate();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" link option on More Options popup$")
	public void i_click_on_link_option_on_more_options_popup(String linkText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			folderScript.clickCreateFolderLink();
			}
			else
				Assume.assumeTrue(true);
	}
}
