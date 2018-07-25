package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.CreateEditWorkSpaceClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_CreateEditWorkspace {
	
	CreateEditWorkSpaceClassicScripts scripts = new CreateEditWorkSpaceClassicScripts();
	
	@Given("^I am on Workspaces tab$")
	public void i_am_on_Workspaces_tab() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnWorkSpaceTab();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link on Workspaces tab$")
	public void i_click_on(String arg1) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnAddNewWorkspace();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should open$")
	public void page_should_open(String pageName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyCreateEditWorkspacePage(pageName);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered WorkspaceName$")
	public void i_have_enetered_WorkspaceName() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.inputWorkSpaceName();
		else
			Assume.assumeTrue(true);
	}


	@When("^Selected Installed Application$")
	public void selected_Installed_Application() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectInstalledApplication();
		else
			Assume.assumeTrue(true);
	}

	@When("^Select Viewer as \"(.*?)\"$")
	public void select_Viewer(String viewer) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectViewer(viewer);
		else
			Assume.assumeTrue(true);
	}

	@When("^selected Primary Geography as ([^\"]*)$")
	public void selected_Primary_Geography_as_United_Kingdom_EU(String geography) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectGeaography(geography);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on popup$")
	public void i_click_on_button_on_popup(String saveLinkText) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickSaveLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Workspace should be Saved/Created$")
	public void workspace_should_be_Saved_Created() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceIsSaved();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" Image$")
	public void i_click_on_image(String imgText) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickBackImage();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I should redirect on Workspaces tab AND created workspace should be available in Favourite Workspaces$")
	public void i_should_redirect_on_Workspaces_tab_AND_created_workspace_should_be_available_in_Favourite_Workspaces() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceInFavorite();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click created workspace$")
	public void i_click_created_workspace() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnCreatedWorkspace();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Workspace home page should open$")
	public void workspace_home_page_should_open() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceHomePage();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on ADMIN dropdown AND select \"(.*?)\"$")
	public void i_click_on_dropdown_AND_select(String dropDownOption) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectEditWorkspace(dropDownOption);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Change the WorkspaceName AND I click on save all button$")
	public void i_Change_the_WorkspaceName_AND_I_click_on_save_all_button() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.editWorkspaceAndSave();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Workspace should be updated with new WorkspaceName$")
	public void workspace_should_be_updated_with_new_WorkspaceName() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceIsEdited();
		else
			Assume.assumeTrue(true);
	}

	@When("^I selected \"(.*?)\" status in \"(.*?)\" dropdown AND click on save button$")
	public void i_selected_status_in_dropdown_AND_click_on_save_button(String closeOption, String status) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.closeUpdatedWorkSpace(closeOption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^WorkspaceName workspace should be closed AND WorkspaceName workspace should not be available in workspace listing$")
	public void workspacename_workspace_should_be_closed_AND_WorkspaceName_workspace_should_not_be_available_in_workspace_listing() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceIsClosed();
		else
			Assume.assumeTrue(true);
	}

}
