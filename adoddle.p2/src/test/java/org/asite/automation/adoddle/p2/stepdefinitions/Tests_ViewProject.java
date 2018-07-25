package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ViewProjectScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ViewProject
{
	ViewProjectScripts scripts = new ViewProjectScripts();
	
	/***** Search Project Filter *****/
	
	@When("^I clear search \"(.*?)\"$")
	public void i_clear_search(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clearSearchProjectFilter();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select searched project ([^\"]*)$")
	public void i_select_searched_project(String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectProject(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected project ([^\"]*) should be set into Search \"(.*?)\"$")
	public void selected_project_should_be_set_into_Search(String project, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySearchProjectFilter(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected project ([^\"]*) should be displayed into Files Tab$")
	public void selected_project_should_be_displayed_into_Files_Tab(String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySelectedProjectInFilesTab(project);
		else
			Assume.assumeTrue(true);
	}
	
	/***** "OwnerOrg" HyperLink *****/
	
	@Then("^I check \"(.*?)\" of selected project$")
	public void i_check_of_selected_project(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getOwnerOrgName();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Hyperlink of \"(.*?)\" of Projects tab$")
	public void i_click_on_Hyperlink_of_of_Projects_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnOwnerOrgHyperLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify \"(.*?)\" name on \"(.*?)\" block area$")
	public void i_verify_name_on_block_area(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyOwnerOrgName();
		else
			Assume.assumeTrue(true);
	}
	
	/***** "Files" HyperLink *****/
	
	@When("^I select \"(.*?)\" into \"(.*?)\" dropdown list$")
	public void i_select_into_dropdown_list(String revisions, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectRevisionIntoCreateFilterDropdown(revisions);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" filter dropdown set into file listing page$")
	public void filter_dropdown_set_into_file_listing_page(String revisions) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyRevisionFilterDropdown(revisions);
		else
			Assume.assumeTrue(true);
	}

	@When("^I checked \"(.*?)\" and \"(.*?)\" into Revision filter dropdown$")
	public void i_checked_and_into_Revision_filter_dropdown(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.checkRevisionFilterList();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total Active Files count$")
	public void i_get_total_Active_Files_count() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalActiveFilesCount();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I get total Files count on \"(.*?)\" hyperlink$")
	public void i_get_total_Files_count_on_hyperlink(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalActiveFilesCountOnFilesHyperLink();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" hyperlink$")
	public void i_click_on_hyperlink(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFilesHyperLink();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^current project ([^\"]*) should be set into Search \"(.*?)\"$")
	public void current_project_should_be_set_into_Search(String project, String arg2)
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySearchProjectFilter(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify \"(.*?)\" and \"(.*?)\" checked into \"(.*?)\" filter dropdown$")
	public void i_verify_and_checked_into_filter_dropdown(String arg1, String arg2, String arg3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyRevisionFilterCheckedValue();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify total Active files count$")
	public void i_verify_total_Active_files_count() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalFilesCount();
		else
			Assume.assumeTrue(true);
	}
	
	/***** "Last File Uploaded" HyperLink *****/
	
	@Then("^I get last uploaded file date into \"(.*?)\" Hyperlink$")
	public void i_get_last_uploaded_file_date_into_Hyperlink(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getLastFileUploadedHyperLinkDate();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" into Create filter dropdown list$")
	public void i_select_into_Create_filter_dropdown_list(String date) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDateIntoCreateFilterDropdown(date);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" filter dropdown set into Files tab$")
	public void filter_dropdown_set_into_Files_tab(String date)
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDateFilterDropdown(date);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" Hyperlink date into \"(.*?)\" filter$")
	public void i_set_Hyperlink_date_into_filter(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setFileHyperLinkDateIntoDateFilter();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total Last Uploaded Files Count$")
	public void i_get_total_Last_Uploaded_Files_Count() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getLastUploadedFilesCount();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" hyperlink of Projects tab$")
	public void i_click_on_hyperlink_of_Projects_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnLastFileUploadedHyperLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify \"(.*?)\" selected date with \"(.*?)\" Hyperlink date$")
	public void i_verify_selected_date_with_Hyperlink_date(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDateFilterSelectedDate();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify all Uploaded Files date with \"(.*?)\" Hyperlink date And Total Files Count$")
	public void i_verify_all_Uploaded_Files_date_with_Hyperlink_date_And_Total_Files_Count(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllLastUploadedFilesDateAndCount();
		else
			Assume.assumeTrue(true);
	}
	
	/***** "Favourite Projects" filter *****/
	
	@Given("^I remove some projects into Favorite Projects List$")
	public void i_remove_some_projects_into_Favorite_Projects_List() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.favouriteProjectsPreCondition();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select more than one Projects into Search \"(.*?)\"$")
	public void i_select_more_than_one_Projects_into_Search(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectProjectsIntoSearchProjectFilter();
		else
			Assume.assumeTrue(true);
	}

	@When("^I set all selected project \"(.*?)\"$")
	public void i_set_all_selected_project(String markAsFavourite) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectMarkAsFavouriteProject(markAsFavourite);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on LH-Panel Of Favorite Projects tab$")
	public void i_click_on_LH_Panel_Of_Favorite_Projects_tab() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFavouriteProjectsTab();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^selected all Projects should be displayed on Favorite Projects tab$")
	public void selected_all_Projects_should_be_displayed_on_Favorite_Projects_tab() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyProjectsOnFavouriteProjectsTab();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^all Projects should be displayed as \"(.*?)\" And I get Total Favorite Projects Count$")
	public void all_Projects_should_be_displayed_as_And_I_get_Total_Favorite_Projects_Count(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMarkAsFavouriteProjectsAndProjectsCount();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" Projects tab$")
	public void i_click_on_Projects_tab(String allProjects) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAllProjectsTab(allProjects);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I remove all Favourite Projects \"(.*?)\" into \"(.*?)\"$")
	public void i_remove_all_Favourite_Projects_into(String arg1, String removeAsFavourite) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectRemoveAsFavouriteProject(removeAsFavourite);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^No projects should displayed into Projects tab And Total Favorite Projects Count Should be (\\d+)$")
	public void no_projects_should_displayed_into_Projects_tab_And_Total_Favorite_Projects_Count_Should_be(int projectsCount) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoProjectsInFavouriteProjectsTab();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^selected all projects should displayed into Projects tab And I verify selected all projects displayed as \"(.*?)\"$")
	public void selected_all_projects_should_displayed_into_Projects_tab_And_I_verify_selected_all_projects_displayed_as(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyRemoveAsFavouriteProjects();
		else
			Assume.assumeTrue(true);
	}
	
	/***** "Last Form Activity" for Project Forms *****/
	
	@When("^I goto \"(.*?)\" tab$")
	public void i_goto_tab(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.gotoActiveFormTab(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get last uploaded form date from \"(.*?)\" coloum$")
	public void i_get_last_uploaded_form_date_from_coloum(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getLastFormActivityDate();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total Forms Count from \"(.*?)\" coloum$")
	public void i_get_total_Forms_Count_from_coloum(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getAppsCount();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify last uploaded form date with \"(.*?)\" coloum date And all Forms Count$")
	public void i_verify_last_uploaded_form_date_with_coloum_date_And_all_Forms_Count(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyLastFormActivityDateAndFormsCount();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify total Forms count with \"(.*?)\" coloum count$")
	public void i_verify_total_Forms_count_with_coloum_count(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalFormsCount();
		else
			Assume.assumeTrue(true);
	}
	
	/***** "Status" HyperLink *****/
	
	@Then("^I check Project \"(.*?)\" value on \"(.*?)\" hyperlink$")
	public void i_check_Project_value_on_hyperlink(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getProjectStatusHyperLinkValue();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" hyperlink of Project list View$")
	public void i_click_on_hyperlink_of_Project_list_View(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnProjectStatusHyperLink();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I verify Status dropdown selected value with \"(.*?)\" hyperlink value$")
	public void i_verify_Status_dropdown_selected_value_with_hyperlink_value(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyProjectStatusValue();
		else
			Assume.assumeTrue(true);
	}
	
	/***** "ListView" & "ThumbView" *****/
	
	@When("^I click on ThumbView button$")
	public void i_click_on_ThumbView_button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnThumbView();
		else
			Assume.assumeTrue(true);
	}

	@Then("^project listing should be displayed as ThumbView in style$")
	public void project_listing_should_be_displayed_as_ThumbView_in_style() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyThumbViewListing();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on ListView button$")
	public void i_click_on_ListView_button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnListView();
		else
			Assume.assumeTrue(true);
	}

	@Then("^project listing should be displayed as ListView in style$")
	public void project_listing_should_be_displayed_as_ListView_in_style() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyListViewListing();
		else
			Assume.assumeTrue(true);
	}
	
	/***** Shorting of "Project Name" *****/
	
	@When("^I Sort By ([^\"]*) in \"(.*?)\" order$")
	public void i_Sort_By_Project_Name_in_order(String column, String order) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sortByAndVerify(column, order);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Valid Sorting should be applied$")
	public void valid_Sorting_should_be_applied() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyShorting();
		else
			Assume.assumeTrue(true);
	}
	
	/***** Change Columns Position *****/
	
	@Then("^First column position changed with its previous position$")
	public void first_column_position_changed_with_its_previous_position() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMovableColumnPosition();
		else
			Assume.assumeTrue(true);
	}

	@Then("^First column position changed and it set on its original position$")
	public void first_column_position_changed_and_it_set_on_its_original_position() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMovedColumnPosition();
		else
			Assume.assumeTrue(true);
	}
	
	/***** Customize Columns Width  *****/
	
	@When("^I get any one column width And I customize its width$")
	public void i_get_any_one_column_width_And_I_customize_its_width() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getColumnWidthAndCustomizeColumn();
		else
			Assume.assumeTrue(true);
	}

	@Then("^cutomized column width change with its previous width$")
	public void cutomized_column_width_change_with_its_previous_width() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCustomizeColumnWidth();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get cutomized column new width And I again customize its width with previous width$")
	public void i_get_cutomized_column_new_width_And_I_again_customize_its_width_with_previous_width() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getCustomizedColumnWidthAndCustomizeColumn();
		else
			Assume.assumeTrue(true);
	}

	@Then("^newly cutomized column width changed with its original width$")
	public void newly_cutomized_column_width_changed_with_its_original_width() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCustomizedColumnWidth();
		else
			Assume.assumeTrue(true);
	}
}