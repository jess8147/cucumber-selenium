package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.PublicPrivateFoldersWithFilesScripts;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_PublicPrivateFoldersWithFiles
{
	PublicPrivateFoldersWithFilesScripts scripts = new PublicPrivateFoldersWithFilesScripts();
	
	@When("^I \"(.*?)\" and Verify ([^\"]*) folders as public and private$")
	public void i_and_Verify_folders_as_public_and_private(String setReset, String folderList) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setAndVerifyPublicPrivateFolder(setReset, folderList);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total Files name and Count of ([^\"]*) folders$")
	public void i_get_total_Files_name_and_Count_of_Public_Folder_folders(String publicFolderList) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getPublicFoldersFilesAndCount(publicFolderList);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^right click on \"(.*?)\" AND Click on \"(.*?)\"$")
	public void right_click_on_AND_Click_on(String folderType, String menuOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFolderAndSelectOption(menuOption, folderType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I copied public link of \"(.*?)\" And I closed \"(.*?)\" popup$")
	public void i_copied_public_link_of_And_I_closed_popup(String folderType, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.copyPublicLink();
		else
			Assume.assumeTrue(true);
	}
	

	@When("^logout from Adoddle$")
	public void logout_from_Adoddle() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.logOut();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I opened new tab I hit Asite Login URL$")
	public void i_opened_new_tab_I_hit_Asite_Login_URL() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.openNewTabAndHitLoginUrl();
		else
			Assume.assumeTrue(true);
	}

	@When("^paste copied link on URL AND hit the URL$")
	public void paste_copied_link_on_URL_AND_hit_the_URL() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.hitCopiedUrl();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Folder should be open as public with ([^\"]*) project$")
	public void folder_should_be_open_as_public_with_project(String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyOpenPublicFolder(project);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Only FolderA, FolderC and FolderE files should displayed And FolderB and FolderD files should not displayed$")
	public void only_FolderA_FolderC_and_FolderE_files_should_displayed_And_FolderB_and_FolderD_files_should_not_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPublicFolderFilesAndCount();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Only FolderC and FolderE files should displayed And FolderA, FolderB and FolderD files should not displayed$")
	public void only_FolderC_and_FolderE_files_should_displayed_And_FolderA_FolderB_and_FolderD_files_should_not_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPublicFolderFilesAndCount();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^Select multiple files of opened public folders And click on \"(.*?)\" button$")
	public void select_multiple_files_of_opened_public_folders_And_click_on_button(String downloadAllButton) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.clickLinkWithText(downloadAllButton);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link of Public Folder$")
	public void i_click_on_link_of_Public_Folder(String loginButton) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickLinkWithText(loginButton);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Login for ([^\"]*) User$")
	public void i_Login_for_User(String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.login(userID, ResourceHandler.loadProperty("test.users.common.password"));
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I login using loggedIn User$")
	public void i_login_using_loggedIn_User() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.tearDown();
			TestDriverFactory.getInstance().setUp(ResourceHandler.getPropertyValue("browser"));
			scripts.login(System.getProperty("primary.username"), System.getProperty("primary.password"));
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^ParentFolder selected as default And All Folders only \"(.*?)\" Files should displayed And \"(.*?)\" Files should not displayed$")
	public void parentfolder_selected_as_default_And_All_Folders_only_Files_should_displayed_And_Files_should_not_displayed(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySelectedFolderAndAccessibleFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^SubPublicFolder selected as default And Folder and its child All Folders only \"(.*?)\" Files should displayed And \"(.*?)\" Files should not displayed$")
	public void subpublicfolder_selected_as_default_And_Folder_and_its_child_All_Folders_only_Files_should_displayed_And_Files_should_not_displayed(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySelectedFolderAndAccessibleFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" page should displayed for NoAccess User$")
	public void page_should_displayed_for_NoAccess(String unauthorisedMsg) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUnauthorisedAccessPage(unauthorisedMsg);
		else
			Assume.assumeTrue(true);
	}

	@Then("^ParentFolder selected as default And only ParentFolder all public and private Files should displayed$")
	public void parentfolder_selected_as_default_And_only_ParentFolder_all_public_and_private_Files_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAdminFolderAccessibleFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I opened new \"(.*?)\" (\\d+) tab$")
	public void i_opened_new_tab(String arg1, int numberofWindows) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.openedNewWindow(numberofWindows);
		else
			Assume.assumeTrue(true);
	}

	@When("^I closed \"(.*?)\" (\\d+) tab$")
	public void i_closed_tab(String arg1, int numberofWindows) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.closedOpenedWindow(numberofWindows);
			else
				Assume.assumeTrue(true);
	}
}
