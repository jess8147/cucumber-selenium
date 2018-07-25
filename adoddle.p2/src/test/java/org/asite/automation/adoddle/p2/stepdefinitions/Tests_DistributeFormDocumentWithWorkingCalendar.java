package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DistributeFormDocumentWithWorkingCalendarScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DistributeFormDocumentWithWorkingCalendar
{
	DistributeFormDocumentWithWorkingCalendarScripts scripts = new DistributeFormDocumentWithWorkingCalendarScripts();
	
	@When("^I set \"(.*?)\", \"(.*?)\" days as Holiday And click on Save Button$")
	public void i_set_days_as_Holiday_And_click_on_Save_Button(String saturday, String sunday) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.setHolidayInWorkspaceWorkingCalendar(saturday, sunday);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select ([^\"]*) project AND \"(.*?)\" Folder And I click on \"(.*?)\" button$")
	public void i_select_project_AND_Folder_And_I_click_on_button(String project, String folder, String addFilesButton) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectProjectFolderAndAddFiles(project, folder);
		else
			Assume.assumeTrue(true);
	}

	@When("^I distribute ([^\"]*) Distribution Group$")
	public void i_distribute_Distribution_Group(String distributeGroup) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterDistributeGroup(distributeGroup);
		else
			Assume.assumeTrue(true);
	}

	@Then("^file should uploaded Successfully on \"(.*?)\" tab And I verify File Distribution History with Working Calendar$")
	public void file_should_uploaded_Successfully_on_tab_And_I_verify_File_Distribution_History_with_Working_Calendar(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedFileWithWorkingCalendar(activeTab);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should uploaded Successfully on \"(.*?)\" tab And I verify Form Distribution History with Working Calendar$")
	public void form_should_uploaded_Successfully_on_tab_And_I_verify_Form_Distribution_History_with_Working_Calendar(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithWorkingCalendar(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Form should created Successfully on \"(.*?)\" tab And I verify Form Distribution History with All Working Days$")
	public void form_should_created_Successfully_on_tab_And_I_verify_Form_Distribution_History_with_All_Working_Days(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithAllWorkingDays(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	
	/*** With Additional Holiday ***/
	
	@When("^I set \"(.*?)\", \"(.*?)\" and one extra day \"(.*?)\" days as Holiday$")
	public void i_set_and_one_extra_day_days_as_Holiday(String saturday, String sunday, String tuesday) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setWorkspaceWorkingDays(saturday, sunday, tuesday, null, null, null, null);
		else
			Assume.assumeTrue(true);
	}

	@When("^I add Additional Holiday in Working Calendar And click on Save Button$")
	public void i_add_Additional_Holiday_in_Working_Calendar_And_click_on_Save_Button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addAdditionalHolidayInWorkingCalendar();
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on ([^\"]*) project And click on \"(.*?)\"$")
	public void i_right_click_on_project_And_click_on(String project, String contextOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectOption(project, contextOption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Working Days should be displayed and Holidays should not be displayed And Additional Holiday should be displayed in Holiday List$")
	public void working_Days_should_displayed_and_Holiday_days_should_not_displayed_And_Additional_Holiday_should_displayed_in_Holiday_List() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyWorkingDaysAndHolidayListOnWorkingCalendar();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I perform Working Calendar Holidays Cleanup$")
	public void i_perform_Working_Calendar_TestData_Cleanup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clearHolidaysFromProject();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I distribute ([^\"]*) AND I select \"(.*?)\" action with adding (\\d+) days in Role AND \"(.*?)\" action with (\\d+) days in Org$")
	public void i_distribute_AND_I_select_action_with_adding_days_in_Role_AND_action_with_days_in_Org(String distributeRoleOrg, String roleAction, int roleAddDays, String orgAction, int orgAddDays) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectRoleAndOrgWithAddingDueDays(distributeRoleOrg, roleAction, roleAddDays, orgAction, orgAddDays);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new Form with distribute ([^\"]*) AND I select \"(.*?)\" action with adding (\\d+) days in Role AND \"(.*?)\" action with (\\d+) days in Org AND \"(.*?)\" Distribution Group$")
	public void i_create_new_Form_with_distribute_Working_Cal_Role_Automation_Testing_AND_I_select_action_with_adding_days_in_Role_AND_action_with_days_in_Org_AND_Distribution_Group(String distributeRoleOrg, String roleAction, int roleAddDays, String orgAction, int orgAddDays, String distributeGroup) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFormWithOrgRoleAndGroup(distributeRoleOrg, roleAction, roleAddDays, orgAction, orgAddDays, distributeGroup, null);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new Form with distribute ([^\"]*) AND I select \"(.*?)\" action with adding (\\d+) days in Role AND \"(.*?)\" action with (\\d+) days in Org AND \"(.*?)\" Distribution Group into \"(.*?)\"$")
	public void i_create_new_Form_with_distribute_Working_Cal_Role_Automation_Testing_AND_I_select_action_with_adding_days_in_Role_AND_action_with_days_in_Org_AND_Distribution_Group_into(String distributeRoleOrg, String roleAction, int roleAddDays, String orgAction, int orgAddDays, String distributeGroup, String dropdown) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFormWithOrgRoleAndGroup(distributeRoleOrg, roleAction, roleAddDays, orgAction, orgAddDays, distributeGroup, dropdown);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get active tab$")
	public void i_get_active_tab() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getActiveTabText();
		else
			Assume.assumeTrue(true);
	}
}
