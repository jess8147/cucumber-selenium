package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.FormPermissionsScripts;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_FormPermissions
{
	FormPermissionsScripts formScripts = new FormPermissionsScripts();
	
	@When("^I enter RoleName for Form Permissions$")
	public void i_enter_RoleName_for_Form_Permissions() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			formScripts.createNewRole();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^New Role should be created for Form Permissions$")
	public void new_Role_should_be_created_for_Form_Permissions() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			formScripts.verifyCreatedRole();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Form Permissions LH-panel tab$")
	public void i_click_on_Form_Permissions_LH_panel_tab() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			formScripts.clickOnFormPermissionsLHpanelTab();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I assign \"(.*?)\" into created Role for Form Permissions$")
	public void i_assign_into_created_Role_for_Form_Permissions(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.assignUserToCreatedRole();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I assigned \"(.*?)\" Permission to Public Form And \"(.*?)\" Permission to Private Form$")
	public void i_assigned_Permission_to_Public_Form_And_Permission_to_Private_Form(String noAccess, String viewAllOrgs) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.assignedPermissionToForms(noAccess, viewAllOrgs);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^Click on Save button And I click on Cancel button of Role Form Permissions$")
	public void click_on_Save_button_And_I_click_on_Cancel_button_of_Role_Form_Permissions() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.clickOnSaveAndCancel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Public Form and Its all Forms Listing Should not displayed on \"(.*?)\" tab$")
	public void public_Form_and_Its_all_Forms_Listing_Should_not_displayed_on_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.verifyPublicFormDisabled();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Private Form and Its all Forms Listing Should displayed on \"(.*?)\" tab$")
	public void private_Form_and_Its_all_Forms_Listing_Should_displayed_on_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.verifyPrivateFormEnabled();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I removed \"(.*?)\" Permission to Public Form And \"(.*?)\" Permission to Private Form$")
	public void i_removed_Permission_to_Public_Form_And_Permission_to_Private_Form(String noAccess, String viewAllOrgs) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.unAssignedPermissionInToForms(noAccess, viewAllOrgs);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Private Form all Forms Listing Should not displayed on \"(.*?)\" tab$")
	public void private_Form_all_Forms_Listing_Should_not_displayed_on_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.verifyPrivateFormListingDisabled();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Public Form and Its all Forms Listing Should displayed on \"(.*?)\" tab$")
	public void public_Form_and_Its_all_Forms_Listing_Should_displayed_on_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.verifyPublicFormEnabled();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^I remove User into Created Role$")
	public void i_remove_User_into_Created_Role() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			formScripts.logOut();
			formScripts.login(System.getProperty("multi.project.username"), System.getProperty("multi.project.password"));
			formScripts.navigateTab(LandingPage.lnk_Projects);
			formScripts.deactivateUserIntoCreatedRole();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I am login with multi-project user$")
	public void i_am_login_with_multi_project_user() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			formScripts.tearDown();
			TestDriverFactory.getInstance().setUp(ResourceHandler.getPropertyValue("browser"));
			formScripts.login(System.getProperty("multi.project.username"), System.getProperty("multi.project.password"));
			formScripts.setProjectAll();
			formScripts.setMultiProjectUserAsPrimaryUser();
		} else
			Assume.assumeTrue(true);
	}
}
