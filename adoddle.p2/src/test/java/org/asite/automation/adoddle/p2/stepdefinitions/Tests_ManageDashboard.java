package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ManageDashboardScripts;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ManageDashboard
{
	ManageDashboardScripts scripts = new ManageDashboardScripts();
	
	/*** Create Edit Dashboard ***/
	
	@When("^I set DC vise URL$")
	public void i_set_DC_vise_URL() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setLandingURL(AdoddleCommonAppMethods.dataCenter);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" dropdown Button and select \"(.*?)\"$")
	public void i_click_on_dropdown_Button_and_select(String toolsDropdown, String toolsOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnToolsAndSelectOption(toolsDropdown, toolsOption);
		else
			Assume.assumeTrue(true);
	}

	@When("^I entered Dashboard \"(.*?)\" and \"(.*?)\" for create \"(.*?)\"$")
	public void i_entered_Dashboard_and_for_create(String arg1, String arg2, String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enteredDashboardCreationDetails(dashboardType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Created New Dashboard should displayed in \"(.*?)\" list$")
	public void created_New_Dashboard_should_displayed_in_list(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedDashboard();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" of created Dashboard$")
	public void i_click_on_of_created_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit Dashboard \"(.*?)\" and \"(.*?)\" for edit \"(.*?)\"$")
	public void i_edit_Dashboard_and_for_edit(String arg1, String arg2, String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enteredDashboardCreationDetails(dashboardType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Updated Dashboard should displayed in \"(.*?)\" list$")
	public void updated_Dashboard_should_displayed_in_list(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	
	/*** Customised Dashboard ***/
	
	@When("^I add \"(.*?)\" gadget in Created Dashboard$")
	public void i_add_gadget_in_Created_Dashboard(String gadgetCount) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addSingleMultipleGadgetOnDashboard(gadgetCount);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I entered Dashboard \"(.*?)\" and \"(.*?)\" and select existing Dashboard in dropdown for \"(.*?)\"$")
	public void i_entered_Dashboard_and_and_select_existing_Dashboard_in_dropdown_for(String arg1, String arg2, String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enteredDashboardCreationDetails(dashboardType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" dropdown Button and select created new Dashboard$")
	public void i_click_on_dropdown_Button_and_select_created_new_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDashboardOnSwitchDashboardDropdown();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Created \"(.*?)\" page should set$")
	public void created_Dashboard_page_should_set(String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardPage(dashboardType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" to add gadgets to your dashboard hyperlink$")
	public void i_click_on_to_add_gadgets_to_your_dashboard_hyperlink(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAddGadgets();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I add all Gadgets of Dashboard$")
	public void i_add_all_Gadgets_of_Dashboard() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addGadgetsOnDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^all Gadgets should listed on Created Dashboard$")
	public void all_Gadgets_should_listed_on_Created_Dashboard() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyGadgetsAddedOnDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set created Dashboard as \"(.*?)\"$")
	public void i_set_created_Dashboard_as(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setDashboardAsDefault();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^created Dashboard set as \"(.*?)\"$")
	public void created_Dashboard_set_as(String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardSetAsDefaultDashboard(dashboardType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^added Gadgets should displayed as previously on Created Dashboard$")
	public void added_Gadgets_should_displayed_as_previously_on_Created_Dashboard() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyGadgetsAddedOnDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Created Dashboard all Gadgets should displayed in Created Clone Dashboard$")
	public void created_Dashboard_all_Gadgets_should_displayed_in_Created_Clone_Dashboard() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyGadgetsAddedOnDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I delete \"(.*?)\" Dashboard$")
	public void i_delete_created_Dashboard(String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deleteDashboard(dashboardType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" should set as default Dashboard$")
	public void should_set_as_default_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDefaultDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I logOut and re-login for default Dashboard using ([^\"]*) in Adoddle$")
	public void i_logOut_and_re_login_for_default_Dashboard_using_auto_testuser_atest_com_in_Adoddle(String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.loginInAdoddle(userID, ResourceHandler.loadProperty("test.users.common.password"));
		} else
			Assume.assumeTrue(true);
	}
	
	
	/*** "Shared Dashboard" with Filter ***/
	
	@When("^I create \"(.*?)\" filter with any three \"(.*?)\"$")
	public void i_create_filter_with_any_three(String filterName, String filter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFilter(filterName, filter);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" plus Icon$")
	public void i_click_on_plus_Icon(String createDashboard) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickLinkWithTitle(createDashboard);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I shared dashboard with ([^\"]*), ([^\"]*) and ([^\"]*)$")
	public void i_shared_dashboard_with_auto_nfpw_user_atest_com_auto_nfpw_user_atest_com_and_auto_nfpw_user_atest_com(String user1, String user2, String user3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sharedDashboardToUsers(user1, user2, user3);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Created Dashboard of \"(.*?)\" page$")
	public void i_click_on_Created_Dashboard_of_page(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDashboardFromManageDashboardPage();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I add \"(.*?)\" Gadget and Save as \"(.*?)\" to Dashboard$")
	public void i_add_Gadget_and_Save_as_to_Dashboard(String gadgetHeader, String filterChartGadgetName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addFilterChartGadget(gadgetHeader, filterChartGadgetName);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Gadget should added Successfully on Dashboard$")
	public void gadget_should_added_Successfully_on_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilterChartGadgetOnDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" into Filter dropdown And \"(.*?)\" into Statistics Type dropdown And select \"(.*?)\" checkbox of gadget$")
	public void i_select_into_Filter_dropdown_And_into_Statistics_Type_dropdown_And_select_checkbox_of_gadget(String filterDropdownOption, String statisticsDropdownOption, String legendsCheckbox) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDropdownAndCheckboxOFGadget(statisticsDropdownOption);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button of gadget$")
	public void i_click_on_button_of_gadget(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFilterGadgetUpdateButton();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" should displayed on Filter gadgets$")
	public void should_displayed_on_Filter_gadgets(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyHighchartsTrackerOfGadget();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^created three all \"(.*?)\" should displayed on paichart$")
	public void created_three_all_should_displayed_on_paichart(String filter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilterTypesOnPaichart();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I get and verify \"(.*?)\" filter and filtertype with Dashboard and \"(.*?)\" tab and set saved \"(.*?)\" filter$")
	public void i_get_and_verify_filter_and_filtertype_with_Dashboard_and_tab_and_set_saved_filter(String filter, String activeTab, String arg3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.getAndVerifySelectedFilterTypeOnTrackerAndActiveTab(filter, activeTab);
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^I get and verify \"(.*?)\" filter and Its selected types on Dashboard with \"(.*?)\" tab And selected saved \"(.*?)\" filter$")
	public void i_get_and_verify_filter_and_Its_selected_types_on_Dashboard_with_tab_And_selected_saved_filter(String filter, String activeTab, String savedFilter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getAndVerifyFilterAllTypeCountOnTrackerAndActiveTab(filter, activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Gadget should displayed on Dashboard$")
	public void gadget_should_displayed_on_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilterChartGadgetOnDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" in search Project filter$")
	public void i_set_in_search_Project_filter(String selectedProject) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.setProjectInSearchProjectFilter(selectedProject);
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total counts of selected \"(.*?)\"$")
	public void i_get_total_counts_of_selected(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalCountOfSetFilterTypes();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set ([^\"]*) project in search Project Filter$")
	public void i_set_AutomationTestProject_project_in_search_Project_Filter(String selectedProject) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.setProjectInSearchProjectFilter(selectedProject);
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^total counts of selected \"(.*?)\" miss-match with Previous counts of \"(.*?)\"$")
	public void total_counts_of_selected_miss_match_with_Previous_counts_of(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalSetFilterTypeCountMissMatch();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I delete created customized Dashboard$")
	public void i_delete_created_customized_Dashboard() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deleteCustomizedDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^created \"(.*?)\" should not displayed on \"(.*?)\" page$")
	public void created_should_not_displayed_on_page(String dashboardType, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardDeleted(dashboardType);
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^\"(.*?)\" should \"(.*?)\" on Saved Filter list$")
	public void should_displayed_on_Saved_Filter_list(String arg1, String isDisplay) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySavedFilterOnActiveTab(isDisplay);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I delete \"(.*?)\" Saved Filter$")
	public void i_delete_Saved_Filter(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deleteSavedFilter();
		else
			Assume.assumeTrue(true);
	}
	
	
	/*** Shared Dashboard With Filter's All type ***/
	
	@When("^I create \"(.*?)\" filter for All \"(.*?)\"$")
	public void i_create_filter_for_All(String filterName, String filter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFilter(filterName, filter);
		else
			Assume.assumeTrue(true);
	}
	
	/*@When("^I create \"(.*?)\" filter for All status types \"(.*?)\"$")
	public void i_create_filter_for_All_status_types(String filterName, String filter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFilterWithAllTypes(filterName, filter);
		else
			Assume.assumeTrue(true);
	}*/
	
	@When("^I create filter \"(.*?)\" with filtername \"(.*?)\" with sub filter \"(.*?)\"$")
	public void i_create_filter_with_filtername_with_sub_filter(String filter, String filterName, String subFilter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFilterWithAllTypes(filterName, subFilter);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I shared dashboard to user ([^\"]*) with filter$")
	public void i_shared_dashboard_to_user_auto_nfpw_user_atest_com_with_filter(String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sharedDashboardToUsers(userID, null, null);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I get and verify \"(.*?)\" filter and Its all active types on Dashboard with \"(.*?)\" tab AND selected saved \"(.*?)\" filter$")
	public void i_get_and_verify_filter_and_Its_all_active_types_on_Dashboard_with_tab_AND_selected_saved_filter(String filter, String activeTab, String savedFilter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getAndVerifyFilterAllTypeCountOnTrackerAndActiveTab(filter, activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	
	/*** Status-POI & Recipient Org With Action Due Date***/
	
	@Then("^I verify \"(.*?)\" hide and show using \"(.*?)\" click$")
	public void i_verify_hide_and_show_using_click(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnLegendAndVerifyTrackerHideAndShow();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create \"(.*?)\" filter with \"(.*?)\" type And \"(.*?)\" filter And \"(.*?)\" filter within (\\d+) week And save with filtername \"(.*?)\"$")
	public void i_create_filter_with_type_And_filter_And_filter_within_week_And_save_with_filtername(String statusFilter, String statusFilterType, String recipientOrgFilter, String actionDueDateFilter, int actionTotalWeek, String filterName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createMultipleFiltersForActiveTab(statusFilter, statusFilterType, recipientOrgFilter, actionDueDateFilter, actionTotalWeek, filterName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I get and verify \"(.*?)\" filter with \"(.*?)\" selected type And \"(.*?)\" filter and filtertype on Dashboard with \"(.*?)\" tab And \"(.*?)\" filter within (\\d+) week And set saved \"(.*?)\" filter$")
	public void i_get_and_verify_filter_with_selected_type_And_filter_and_filtertype_on_Dashboard_with_tab_And_filter_within_week_And_set_saved_filter(String statusFilter, String statusFilterType, String recipientOrgFilter, String activeTab, String actionDueDateFilter, int actionTotalWeek, String arg7) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getAndVerifySelectedMultiFilterOrgsAndCountAndActiveTab(statusFilter, statusFilterType, recipientOrgFilter, activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I get and Verify \"(.*?)\" filter and filtertype and total count with Dashboard and \"(.*?)\" tab and set saved \"(.*?)\" filter$")
	public void i_get_and_Verify_filter_and_filtertype_and_total_count_with_Dashboard_and_tab_and_set_saved_filter(String filter, String activeTab, String savedFilter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.getAndVerifyFilterSelectedTypesAndCountOnTrackerWithActiveTab(filter, activeTab);
		}else
			Assume.assumeTrue(true);
	}
	
	
	/*** Manage Dashboard with Project ***/
	
	@When("^I Edit created Dashboard$")
	public void i_Edit_created_Dashboard() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnEditDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set Project as \"(.*?)\" access rights$")
	public void i_set_Project_as_access_rights(String accessRights) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignPrevilegesToProjectUsers(accessRights, null);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" for Project AND \"(.*?)\" for User access rights$")
	public void i_set_for_Project_AND_for_User_access_rights(String projectAccessRights, String userAccessRights) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignPrevilegesToProjectUsers(projectAccessRights, userAccessRights);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I remove \"(.*?)\" gadget from Created Dashboard$")
	public void i_remove_gadget_from_Created_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeSingleGadgetOnDashboard();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" as default Dashboard$")
	public void i_set_as_default_Dashboard(String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.performTestDataCleanUp(dashboardType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I shared dashboard with ([^\"]*) project to other users$")
	public void i_shared_dashboard_with_project_to_other_users(String sharedProject) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sharedDashboardToUsers(sharedProject, null, null);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I shared dashboard with Project to ([^\"]*) users$")
	public void i_shared_dashboard_with_Project_to_auto_nfpw_user_atest_com_users(String sharedUser) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sharedDashboardToUsers(sharedUser, null, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^created Dashboard should not set as \"(.*?)\" for Other Users$")
	public void created_Dashboard_should_not_set_as_for_Other_Users(String dashboardType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardNotSetAsDefault();
		else
			Assume.assumeTrue(true);
	}

	@When("^I set Project as \"(.*?)\" And assign ([^\"]*) as \"(.*?)\" and ([^\"]*) as \"(.*?)\"$")
	public void i_set_Project_as_And_assign_auto_nfpw_user_atest_com_as_and_auto_nfpw_user_atest_com_as(String adminRole, String userB, String adminRole2, String userC, String viewRole) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignPrevilegesToProjectAndUsers(userB, userC, adminRole, viewRole);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Button should \"(.*?)\" on Dashboard page$")
	public void button_should_on_Dashboard_page(String arg1, String isDisplay) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddGadgetButtonOnDashboard(isDisplay);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I am logged in using ([^\"]*) user and set Default Dashboard as \"(.*?)\"$")
	public void i_am_logged_in_using_user_and_set_Default_Dashboard_as(String userID, String systemDashboard) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.loginAndSetDefaultDashboard(userID, ResourceHandler.loadProperty("test.users.common.password"), systemDashboard);
		else
			Assume.assumeTrue(true);
	}
}
