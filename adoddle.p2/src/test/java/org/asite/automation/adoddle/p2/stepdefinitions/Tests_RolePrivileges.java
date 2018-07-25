package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.RolePrivilegesScripts;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_RolePrivileges
{
	RolePrivilegesScripts roleScript = new RolePrivilegesScripts();
	
	@When("^I enter RoleName for Role Privileges$")
	public void i_enter_RoleName_for_Role_Privileges() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.createNewRole();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New Role should be created for Role Privileges$")
	public void new_Role_should_be_created_for_Role_Privileges() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.verifyCreatedRole();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I assigned other Org ([^\"]*) UserID \"(.*?)\" into created Role$")
	public void i_assigned_other_Org_Bag_Bad_Automation_Test_Org_UserID_pa_builder_auto_com_into_created_Role(String orgName, String orgUserID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			roleScript.assignUserToCreatedRole(ResourceHandler.loadProperty("test.user.rs.bloggs.id"));
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^Click on Save button And I get current Date and Time$")
	public void click_on_Save_button_And_I_get_current_Date_and_Time() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.clickOnSaveAndGetCurrentDateAndTime();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" LH-panel tab$")
	public void i_click_on_LH_panel_tab(String lhPanelTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.clickLinkWithText(lhPanelTab);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Assigned User ([^\"]*) And Current Date and Time should be displayed on \"(.*?)\" tab$")
	public void assigned_User_PA_Automation_Builder_And_Current_Date_and_Time_should_be_displayed_on_tab(String orgUserName, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			roleScript.verifyAssignedUserAndDateTimeOnHistoryTab(orgUserName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^fixed assigned \"(.*?)\" should be checked to Created new Role$")
	public void fixed_assigned_should_be_checked_to_Created_new_Role(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.verifyAssignedUnAssignedRolePrivileges();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("I click on Cancel button of \"(.*?)\" tab")
	public void i_click_on_Cancel_button_of_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.clickOnCancel();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Login to ([^\"]*) User$")
	public void i_Login_to_User(String orgUserID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			roleScript.tearDown();
			TestDriverFactory.getInstance().setUp(ResourceHandler.getPropertyValue("browser"));
			roleScript.login(orgUserID, ResourceHandler.loadProperty("test.users.common.password"));
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I login to NewOrg UserID$")
	public void i_login_to_NewOrg_UserID() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			roleScript.tearDown();
			TestDriverFactory.getInstance().setUp(ResourceHandler.getPropertyValue("browser"));
			roleScript.login(ResourceHandler.loadProperty("test.user.rs.bloggs.id"), ResourceHandler.loadProperty("test.users.common.password"));
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on ([^\"]*) project and I click on \"(.*?)\" folder for PA builder$")
	public void i_click_on_project_and_I_click_on_folder_for_PA_builder(String project, String folder)
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.clickElementWithText(project);
			roleScript.clickElementWithText(folder);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Right Click on file and click on \"(.*?)\" and \"(.*?)\" context click button$")
	public void i_Right_Click_on_file_and_click_on_and_context_click_button(String share, String distributeFiles) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.contextClickOnShareAndDistributeFiles(share, distributeFiles);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^In \"(.*?)\" text field List Current Org ([^\"]*) and Its All Users like \"(.*?)\" user only Displayed$")
	public void in_text_field_List_Current_Org_Bag_Bad_Automation_Test_Org_and_Its_All_Users_like_pa_builder_auto_com_user_only_Displayed(String arg1, String orgName, String orgUserID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			roleScript.verifyDistributeUsersAndOrgs(orgName, ResourceHandler.loadProperty("test.user.rs.bloggs.id"));
		else
			Assume.assumeTrue(true);
	}

	@When("^I Assigned \"(.*?)\" Permissions to Created new Role$")
	public void i_Assigned_Permissions_to_Created_new_Role(String rolePrivilege) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.assignedRolePrivilegesToCreatedRole(rolePrivilege);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^Click on Save button And I click on Cancel button of Role Privileges$")
	public void click_on_Save_button_And_I_click_on_Cancel_button_of_Role_Privileges() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.clickOnSaveAndCancel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^In \"(.*?)\" text field List All Orgs and All Users Displayed$")
	public void in_text_field_List_All_Orgs_and_All_Users_Displayed(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.verifyDistributeAllUsersAndAllOrgs();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I deactivate User into Created Role$")
	public void i_deactivate_User_into_Created_Role() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.logOut();
			roleScript.login(System.getProperty("multi.project.username"), System.getProperty("multi.project.password"));
			roleScript.navigateTab(LandingPage.lnk_Projects);
			roleScript.deactivateUserIntoCreatedRole();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Assigned User \"(.*?)\" should be listed on \"(.*?)\" tab$")
	public void assigned_User_should_be_listed_on_tab(String removeRole, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			roleScript.verifyRemovedUserOnHistoryTab(removeRole);
		}
		else
			Assume.assumeTrue(true);
	}
}
