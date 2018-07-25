package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreateEditRoleScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateEditRole {
	CreateEditRoleScripts roleScripts = new CreateEditRoleScripts();

	@When("^I right click on ([^\"]*) And Drag mouse to \"(.*?)\" And Click on \"(.*?)\"$")
	public void i_right_click_on(String project, String edit, String userAccess)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnUserAccess(project, edit, userAccess);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on CreateNewRole button$")
	public void i_click_on_CreateNewRole_button() {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnCreateNewRole();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter RoleName$")
	public void i_enter_RoleName() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.createNewRole();
		else
			Assume.assumeTrue(true);
	}

	@When("^Click on Save button$")
	public void click_on_Save_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnSave();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New Role should be created$")
	public void new_Role_should_be_created() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyCreatedRole();
		else
			Assume.assumeTrue(true);
	}

	@When("^I edited RoleName$")
	public void i_edited_RoleName() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.editCreatedRole();
		else
			Assume.assumeTrue(true);
	}

	@Then("^updated Role should be displayed$")
	public void updated_Role_should_be_displayed() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyEditedRole();
		else
			Assume.assumeTrue(true);
	}

	@When("^I assign ([^\"]*) into created Role$")
	public void i_assign_into_created_Role(String proxyUser) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.assignUserInCreatedRole(proxyUser);
		else
			Assume.assumeTrue(true);
	}

	@Then("^assigned \"(.*?)\" should be displayed in Created Role panel$")
	public void assigned_should_be_displayed_in_Created_Role_panel(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyAssignedUser();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on assigned User$")
	public void i_click_on_assigned_User() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnAssignedUser();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should open for Proxy User$")
	public void popup_should_open_for_Proxy_User(String popUpText)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyManageUserDetailsPopup(popUpText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I entered \"(.*?)\" in \"(.*?)\" input text field$")
	public void i_entered_in_input_text_field(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.enterProxyUser();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" AND \"(.*?)\"$")
	public void i_select_AND(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.enterStartDateAndEndDate();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Save button And I click on Cancel button$")
	public void i_click_on_Save_button_And_I_click_on_Cancel_button()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnSaveAndCancel();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Save button And I click on Cancel button on Proxy Popup$")
	public void i_click_on_Save_button_And_I_click_on_Cancel_button_on_proxy_popup()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnSaveAndCancelProxyPopup();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\" into Header dropdown menu list$")
	public void i_Click_on_into_Header_dropdown_menu_list(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnSwitchUser();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should be displayed in \"(.*?)\" popup$")
	public void should_be_displayed_in_popup(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyAssignedUserInSwitchUserPopup();
		else
			Assume.assumeTrue(true);
	}

	@When("^I switch into Assigned User$")
	public void i_switch_into_Assigned_User() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.switchIntoAssignedUser();
		else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button And I select one Files to upload$")
	public void i_clicked_on_button_And_I_select_one_Files_to_upload(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnSelectFilesAndUpload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter all mandatory fields to upload$")
	public void i_enter_all_mandatory_fields_to_upload() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.enterMendatoryAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Then("^File should be uploaded successfully And \"(.*?)\" name should be displayed in \"(.*?)\" column$")
	public void file_should_be_uploaded_successfully_And_name_should_be_displayed_in_column(
			String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyUploadedFileAndPublisher();
		else
			Assume.assumeTrue(true);
	}

	@When("^I switch to Proxy User$")
	public void i_switch_to_Proxy_User() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.switchIntoProxyUser();
		else
			Assume.assumeTrue(true);
	}

	@When("^I remove User into Created Role panel$")
	public void i_remove_User_into_Created_Role_panel() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.removeAssignedUser();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should be open for remove user$")
	public void warning_popup_should_be_open(String popUpText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyWarningPopup(popUpText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Continue button$")
	public void i_click_on_Continue_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnContinue();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Assigned \"(.*?)\" should be removed into Created Role panel$")
	public void assigned_should_be_removed_into_Created_Role_panel(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyRemoveAssignedUser();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should be removed in \"(.*?)\" popup$")
	public void should_be_removed_in_popup(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.verifyRemoveAssignedUserInSwitchUserPopup();
		else
			Assume.assumeTrue(true);
	}
	
	/*** TestData Cleanup ***/
	
	@When("^I search ([^\"]*) project$")
	public void i_search_Automation_ManageRoles_project(String roleProject) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			roleScripts.searchProject(roleProject);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I right click on ([^\"]*) and click on \"(.*?)\" AND \"(.*?)\"$")
	public void i_right_click_on_Automation_ManageRoles_UK_Project_and_click_on_AND(String project, String edit, String userAccess) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			roleScripts.clickOnEditAndUserAccess(project, edit, userAccess);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I remove existing proxy user ([^\"]*)$")
	public void i_remove_existing_proxy_user(String existingUser) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			roleScripts.removeExistingProxyUserData(existingUser);
		else
			Assume.assumeTrue(true);
	}
}
