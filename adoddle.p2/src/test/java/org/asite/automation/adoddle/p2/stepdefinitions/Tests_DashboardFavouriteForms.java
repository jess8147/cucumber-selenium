package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DashboardFavouriteFormsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DashboardFavouriteForms
{
	DashboardFavouriteFormsScripts scripts = new DashboardFavouriteFormsScripts();
	
	/******* Set As Favourite Forms *******/
	
	@When("^I click on ([^\"]*) Project for Favourite Form Widget$")
	public void i_click_on_AutomationTestProject_Project_for_Favourite_Form_Widget(String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickElementWithText(project);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" Favourite Forms Widget hyperlink$")
	public void i_click_on_Favourite_Forms_Widget_hyperlink(String favFormWidgetLink) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDashboardWidgetHyperLink(favFormWidgetLink);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on ([^\"]*) form type of \"(.*?)\" form folder$")
	public void i_click_on_Create_Yes_Ass_Yes_Form_form_type_of_form_folder(String formType, String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFormType(formType, folder);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" button should \"(.*?)\"$")
	public void button_should(String arg1, String activeInactiveButton) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFromCreationRights(activeInactiveButton);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set Create rights \"(.*?)\" form type ([^\"]*) to \"(.*?)\"$")
	public void i_set_Create_rights_form_type_Create_Yes_Ass_Yes_Form_to(String arg1, String form, String addRemoveFavourite) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
			scripts.addRemoveFavouriteForm(form, addRemoveFavourite);
		}else
			Assume.assumeTrue(true);
	}

	@Then("^([^\"]*) form \"(.*?)\" Favourite form$")
	public void create_Yes_Ass_Yes_Form_form_Favourite_form(String form, String setRemove) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFavouriteFormOnProjectFormsTab(form, setRemove);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^([^\"]*) form should \"(.*?)\" in Favourite Forms Widgets list And form should \"(.*?)\" Add Form icon$")
	public void create_No_Ass_Yes_Form_form_should_in_Favourite_Forms_Widgets_list_And_form_should_Add_Form_icon(String form, String isDisplay, String addIcon) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFavouriteFormOnDashboardWidget(form, isDisplay, addIcon);
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Favourite Forms with Color Code "Green & Amber" *******/
	
	@When("^I click on AddForm icon of ([^\"]*) form type$")
	public void i_click_on_AddForm_icon_of_Create_Yes_Ass_Yes_Form_form_type(String form) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDashboardAddFormIcon(form);
		else
			Assume.assumeTrue(true);
	}

	@When("^I create new form in ([^\"]*) formtype with all mandatory attributes and click on Send button$")
	public void i_create_new_form_in_Create_Yes_Ass_Yes_Form_formtype_with_all_mandatory_attributes_and_click_on_Send_button(String form) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createForm(form);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should created and listed in \"(.*?)\" tab$")
	public void form_should_created_and_listed_in_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on ([^\"]*) dashboard Favourite Forms Widgets$")
	public void i_click_on_Create_Yes_Ass_Yes_Form_dashboard_Favourite_Forms_Widgets(String form) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDashboardFavouriteForm(form);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select created form and right click and select \"(.*?)\" and \"(.*?)\"$")
	public void i_select_created_form_and_right_click_and_select_and(String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormForDistribute(option1, option2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I distribute \"(.*?)\", \"(.*?)\" AND \"(.*?)\" actions with \"(.*?)\" due days to ([^\"]*) user$")
	public void i_distribute_AND_actions_with_due_days_to_auto_us_atest_com_user(String action1, String action2, String action3, String dueDays, String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignFormActionsToUsers(action1, action2, action3, dueDays, userID);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set \"(.*?)\" as \"(.*?)\" AND \"(.*?)\" as ([^\"]*) filter$")
	public void i_set_as_AND_as_Automation_UK_filter(String filter1, String subFilter1, String filter2, String subFilter2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setFilterWithSubFilter(filter1, subFilter1, filter2, subFilter2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total Incomplete actions count of \"(.*?)\" tab Favourite formtype$")
	public void i_get_total_Incomplete_actions_count_of_tab_Favourite_formtype(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalIncompleteActionCountOfFormsTabFavouriteForm(activeTab);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify Dashboard Favourite Form ([^\"]*) Count with \"(.*?)\" tab Favourite form Count And Color of Incomplete actions of Favourite form set as \"(.*?)\"$")
	public void i_verify_Dashboard_Favourite_Form_Create_Yes_Ass_Yes_Form_Count_with_tab_Favourite_form_Count_And_Color_of_Incomplete_actions_of_Favourite_form_set_as(String form, String tab, String color) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardFavouriteFormActionsCountAndColorCode(form, color);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I distribute with \"(.*?)\" action for \"(.*?)\" due days to ([^\"]*) user$")
	public void i_distribute_with_action_for_due_days_to_auto_uk_atest_com_user(String action, String dueDays, String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignFormActionsToUsers(action, null, null, dueDays, userID);
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Perform Actions And verify Before And After Color *******/
	
	@When("^I get Dashboard Favourite Form ([^\"]*) Incomplete Actions count and Color \"(.*?)\" And click on Actions count$")
	public void i_get_Dashboard_Favourite_Form_Create_Yes_Ass_Yes_Form_Incomplete_Actions_count_and_Color_And_click_on_Actions_count(String form, String color) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getDashboardFavouriteFormActionsCountWithColorAndClickOnActionsCount(form, color);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with ([^\"]*) filters should set$")
	public void filter_with_AND_filter_with_Automation_UK_filters_should_set(String filter1, String subFilter1, String filter2, String subFilter2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySetFiltersAndFilterSelection(filter1, subFilter1, filter2, subFilter2, null, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^total Incomplete Actions count of \"(.*?)\" tab should match with Dashboard Favourite Form Incomplete Actions count$")
	public void total_Incomplete_Actions_count_of_tab_should_match_with_Dashboard_Favourite_Form_Incomplete_Actions_count(String tab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalIncompleteActionsCountOfFormsTabFavouriteForm(tab);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select and get total Incomplete actions count of \"(.*?)\" tab ([^\"]*) favourite formtype$")
	public void i_select_and_get_total_Incomplete_actions_count_of_tab_Create_Yes_Ass_Yes_Form_favourite_formtype(String tab, String form) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getIncompleteActionsCountOfFormsTabFavouriteForm(tab, form);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Dashboard Favourite Form ([^\"]*) Incomplete Actions count should decreased And match with \"(.*?)\" tab Incomplete Actions Count And Color of Incomplete actions should set as \"(.*?)\"$")
	public void dashboard_Favourite_Form_Create_Yes_Ass_Yes_Form_Incomplete_Actions_count_should_decreased_And_match_with_tab_Incomplete_Actions_Count_And_Color_of_Incomplete_actions_should_set_as(String form, String tab, String color) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardFavouriteFormActionsCountAndColorCode(form, color);
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Filter Preferences & Favourite Forms *******/
	
	@When("^I create New Form in ([^\"]*) project of ([^\"]*) formtype of \"(.*?)\" formgroup with \"(.*?)\" action to User ([^\"]*) without due days$")
	public void i_create_New_Form_in_AutomationTestProject_project_of_Create_Yes_Ass_Yes_Form_formtype_of_formgroup_with_action_to_User_auto_uk_atest_com_without_due_days(String project, String formtype, String formGroup, String action, String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFormWithDistribution(project, formtype, formGroup, userID, action, null);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I search created new form that have Incomplete Actions$")
	public void i_search_created_new_form_that_have_Incomplete_Actions() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" filter with \"(.*?)\" sub filter should set$")
	public void filter_with_sub_filter_should_set(String filter, String subFilter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySetFiltersAndFilterSelection(filter, subFilter, null, null, null, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^previous searched form should displayed$")
	public void previous_searched_form_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPreviousFormAndSearchFormInput();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Dashboard Favourite Form ([^\"]*) Incomplete Actions count$")
	public void i_click_on_Dashboard_Favourite_Form_Create_Yes_Ass_Yes_Form_Incomplete_Actions_count(String formtype) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDashboardFavouriteFormsIncompleteActionsCount(formtype);
		else
			Assume.assumeTrue(true);
	}

	@Then("^previous search forms filter should reset and all incomplete forms should displayed$")
	public void previous_search_forms_filter_should_reset_and_all_incomplete_forms_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyResetSearchFormInputAndFormListing();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify Previous all filters \"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with ([^\"]*) filters should set$")
	public void i_verify_Previous_all_filters_filter_with_AND_filter_with_Automation_UK_filters_should_set(String filter1, String subFilter1, String filter2, String subFilter2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySetFiltersAndFilterSelection(filter1, subFilter1, filter2, subFilter2, null, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Previous Reset search forms filter and all incomplete forms should displayed$")
	public void previous_Reset_search_forms_filter_and_all_incomplete_forms_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyResetSearchFormInputAndFormListing();
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Perform Actions Remove From Favourite *******/
	
	@Then("^Dashboard Favourite Form ([^\"]*) Incomplete Actions count should removed from Form$")
	public void dashboard_Favourite_Form_Recent_FM_Form_Incomplete_Actions_count_should_removed_from_Form(String form) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionsCountRemovedFromDashboardFavouriteForm(form);
		else
			Assume.assumeTrue(true);
	}

	@When("^I remove ([^\"]*) form \"(.*?)\"$")
	public void i_remove_Create_Yes_Ass_Yes_Form_form(String form, String addRemoveFavourite) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addRemoveFavouriteForm(form, addRemoveFavourite);
		else
			Assume.assumeTrue(true);
	}

	@Then("^([^\"]*) should \"(.*?)\" in Favourite Forms Widgets list$")
	public void create_Yes_Ass_Yes_Form_should_in_Favourite_Forms_Widgets_list(String form, String isDisplay) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFavouriteFormOnDashboardWidget(form, isDisplay, null);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all forms and perform right click and select \"(.*?)\" with \"(.*?)\"$")
	public void i_select_all_forms_and_perform_right_click_and_select_with(String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormsForPerformForInfo(option1, option2);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I change status as \"(.*?)\" of all forms AND I click on \"(.*?)\" Button of popup$")
	public void i_change_status_as_of_all_forms_AND_I_click_on_Button_of_popup(String statusVal, String button) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeAllFormsStatus(statusVal, button);
		else
			Assume.assumeTrue(true);
	}
	
}