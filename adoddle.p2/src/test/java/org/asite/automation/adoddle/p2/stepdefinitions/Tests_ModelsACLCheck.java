package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ModelsACLCheckScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ModelsACLCheck
{
	ModelsACLCheckScripts aclScripts = new ModelsACLCheckScripts();
	
	@When("^I Click on \"(.*?)\" button From LH Panel$")
	public void i_Click_on_button_From_LH_Panel(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.clickOnAddModelButton();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I filled all mendatory fields And I create (\\d+) different Worksets on Model And click on \"(.*?)\" button$")
	public void i_filled_all_mendatory_fields_And_I_create_different_Worksets_on_Model_And_click_on_button(int arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.createNewModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model should be created And should be available in Model listing$")
	public void model_should_be_created_And_should_be_available_in_Model_listing() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.verifyCreatedModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on Model And click on \"(.*?)\"$")
	public void i_right_click_on_Model_And_click_on(String upload) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.contextClickOnModelAndSelectOption(upload);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Wrokset for \"(.*?)\" User And select \"(.*?)\" from local And click on \"(.*?)\" button$")
	public void i_select_Wrokset_for_User_And_select_from_local_And_click_on_button(String user, String arg2, String arg3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.uploadIFCFile(user);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on ([^\"]*) Project AND I click on \"(.*?)\" Folder$")
	public void i_click_on_Project_AND_I_click_on_Folder(String project, String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.clickElementWithText(project);
			aclScripts.clickElementWithText(folder);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I give \"(.*?)\" folder for \"(.*?)\" user AND \"(.*?)\" folder for \"(.*?)\" user AND \"(.*?)\" folder for \"(.*?)\" user total \"(.*?)\" rights$")
	public void i_give_folder_for_user_AND_folder_for_user_AND_folder_for_user_total_rights(String folder1, String arg2, String folder2, String arg4, String folder3, String arg6, String noAccess) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.setFolderRightsForUserAccess(noAccess);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I login using \"(.*?)\" User for ACL check$")
	public void i_login_using_User_for_ACL_check(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.logOut();
			aclScripts.userLogin(user);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on created Model$")
	public void i_click_on_created_Model() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.clickOnModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should be opened AND Model should be View in new opened tab And I take screenshot of \"(.*?)\" User ACL Model$")
	public void new_tab_should_be_opened_AND_Model_should_be_View_in_new_opened_tab_And_I_take_screenshot_of_User_ACL_Model(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.viewModel(user);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I close View Model opened tab$")
	public void i_close_View_Model_opened_tab() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			aclScripts.closeViewModelWindow();
		}
		else
			Assume.assumeTrue(true);
	}
}
