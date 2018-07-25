package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.DashboardFavouriteFoldersScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DashboardFavouriteFolders
{
	DashboardFavouriteFoldersScripts scripts = new DashboardFavouriteFoldersScripts();
	
	/******* Favourite Folder With Incomplete Actions Count *******/
	
	@When("^I click on \"(.*?)\" Favourite Folders Widget hyperlink$")
	public void i_click_on_Favourite_Folders_Widget_hyperlink(String favFolderWidgetLink) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDashboardWidgetHyperLink(favFolderWidgetLink);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" folder \"(.*?)\"$")
	public void i_set_folder(String folder, String addAsFavourite) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addRemoveFavouriteFolder(folder, addAsFavourite);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" folder \"(.*?)\" Favourite folder$")
	public void folder_Favourite_folder(String folder, String setRemove) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyFavouriteFolderOnFilesTab(folder, setRemove);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" should \"(.*?)\" in Favourite Folders Widgets list$")
	public void should_in_Favourite_Folders_Widgets_list(String folder, String isDisplay) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFavouriteFolderOnDashboardWidget(folder, isDisplay);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" dashboard Favourite Folders Widgets$")
	public void i_click_on_dashboard_Favourite_Folders_Widgets(String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDashboardFavouriteFolderWidget(folder);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" filter as \"(.*?)\" actions And \"(.*?)\" filter as ([^\"]*)$")
	public void i_set_filter_as_actions_And_filter_as(String filter1, String subFilter1, String filter2, String subFilter2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.createFilter(filter1, subFilter1);
			scripts.createFilter(filter2, subFilter2);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I get total Incomplete Actions count of Files tab Favourite Folder$")
	public void i_get_total_Incomplete_Actions_count_of_Files_tab_Favourite_Folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.filesTabIncompleteActionsCount();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Files tab incomplete actions count should verify with Dashboard Favourite Folder incomplete action counts$")
	public void files_tab_incomplete_actions_count_should_verify_with_Dashboard_Favourite_Folder_incomplete_action_counts() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyIncompleteActionsCountOfDashboardFolderWidget();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Incomplete Actions count of Dashboard Favourite Folder$")
	public void i_click_on_Incomplete_Actions_count_of_Dashboard_Favourite_Folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFavouriteFolderIncompleteCounts();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Dashboard Favourite Folder incomplete actions count should verify with Files tab favourite folder incomplete actions count$")
	public void dashboard_Favourite_Folder_incomplete_actions_count_should_verify_with_Files_tab_favourite_folder_incomplete_actions_count() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyWidgetFromFilesFavouriteFolderActionsCount();
		else
			Assume.assumeTrue(true);
	}

	@When("^I remove \"(.*?)\" folder \"(.*?)\"$")
	public void i_remove_folder(String folder, String removeAsFavourite) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addRemoveFavouriteFolder(folder, removeAsFavourite);
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Favourite Folder Actions Count with Color code *******/
	
	@When("^I create Folder and folder with Sub-Folder And Set parent folder as \"(.*?)\"$")
	public void i_create_Folder_and_folder_with_Sub_Folder_And_Set_parent_folder_as(String addAsFavourite) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createParentAndSubFolderAndSetAsFavourite(addAsFavourite);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Parent folder \"(.*?)\" Favourite folder$")
	public void parent_folder_Favourite_folder(String setRemove) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyParentFavouriteFolderOnFilesTab(setRemove);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Parent folder should \"(.*?)\" in Favourite Folders Widgets list$")
	public void parent_folder_should_in_Favourite_Folders_Widgets_list(String isDisplay) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyParentFavouriteFolderOnDashboardWidget(isDisplay);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Parent folder of dashboard Favourite Folders Widgets$")
	public void i_click_on_Parent_folder_of_dashboard_Favourite_Folders_Widgets() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDashboardFavouriteFolderWidget();
		else
			Assume.assumeTrue(true);
	}

	@When("^I upload more then one files in \"(.*?)\"$")
	public void i_upload_more_then_one_files_in(String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFilesInFavouriteFolder(folder);
		else
			Assume.assumeTrue(true);
	}

	@Then("^files should uploaded successfully in \"(.*?)\"$")
	public void files_should_uploaded_successfully_in(String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" two files for \"(.*?)\" and right click and select \"(.*?)\" and \"(.*?)\"$")
	public void i_select_two_files_for_and_right_click_and_select_and(String fileSelection, String folder, String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForDistribute(fileSelection, folder, option1, option2);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I distribute \"(.*?)\" and \"(.*?)\" action with \"(.*?)\" due days And \"(.*?)\" action without due date to ([^\"]*) user$")
	public void i_distribute_and_action_with_due_days_And_action_without_due_date_to_auto_pa_atest_com_user(String forCom, String forStatusChange, String dueDays, String forInfo, String userId) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignFilesActionsToUsers(forCom, forStatusChange, forInfo, dueDays, userId);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total Incomplete actions count of Files tab Favourite folder$")
	public void i_get_total_Incomplete_actions_count_of_Files_tab_Favourite_folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getIncompleteActionsCountOfFilesTabFavouriteFolder();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total incomplete actions count \"(.*?)\" of Favourite folder of Files tab$")
	public void i_get_total_incomplete_actions_count_of_Favourite_folder_of_Files_tab(String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getIncompleteActionsCount(folder);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I verify Dashboard Favourite folder Count with Files tab Favourite folder Count And Color of Incomplete actions of Favourite folder set as \"(.*?)\"$")
	public void i_verify_Dashboard_Favourite_folder_Count_with_Files_tab_Favourite_folder_Count_And_Color_of_Incomplete_actions_of_Favourite_folder_set_as(String color) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardFavouriteFolderActionsCountAndColorCode(color);
		else
			Assume.assumeTrue(true);
	}

	@When("^I distribute \"(.*?)\" and \"(.*?)\" action with \"(.*?)\" due days to ([^\"]*) user$")
	public void i_distribute_and_action_with_due_days_to_auto_pa_atest_com_user(String forAction, String forAck, String dueDays, String userId) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignFilesActionsToUsers(forAction, forAck, null, dueDays, userId);
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total Incomplete Actions count of Files tab Favourite Folder and Sub folder$")
	public void i_get_total_Incomplete_Actions_count_of_Files_tab_Favourite_Folder_and_Sub_folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalIncompleteActionsCountOfFilesTabFavouriteFolder();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Dashboard Incomplete Actions count should decreased And match with Files Incomplete Actions Count And Color of Incomplete actions of Favourite folder set as \"(.*?)\"$")
	public void dashboard_Incomplete_Actions_count_should_decreased_And_match_with_Files_Incomplete_Actions_Count_And_Color_of_Incomplete_actions_of_Favourite_folder_set_as(String color) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardFavouriteFolderActionsCountAndColorCode(color);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get first file Name and Revision and Upload using next Revision with ([^\"]*) and \"(.*?)\" action$")
	public void i_get_first_file_Name_and_Revision_and_Upload_using_next_Revision_with_auto_uk_atest_com_and_action(String userID, String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFileUsingNextRevision(userID, action);
		else
			Assume.assumeTrue(true);
	}

	@Then("^File should uploaded Successfully And both Revision files should displayed and \"(.*?)\" action assign to latest Revision file$")
	public void file_should_uploaded_Successfully_And_both_Revision_files_should_displayed_and_action_assign_to_latest_Revision_file(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedFileWithBothRevision(action);
		else
				Assume.assumeTrue(true);
	}

	@Then("^Dashboard Incomplete Actions count should increased$")
	public void dashboard_Incomplete_Actions_count_should_increased() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardIncompleteActionsCountAfterRevisionUpload();
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Filter Preferences & Favourite Folders *******/
	
	@When("^I \"(.*?)\" Filter Preferences$")
	public void i_Filter_Preferences(String setRemove) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setFilterPreferences(setRemove);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I remove all selected filters$")
	public void i_remove_all_selected_filters() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeAllSetFilters();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I upload more then one files using \"(.*?)\" Status$")
	public void i_upload_more_then_one_files_using_Status(String status) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFilesUsingDifferentStatus(status);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" status all files should displayed$")
	public void status_all_files_should_displayed(String status) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyListFilesStatusValue(status);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I search any one uploaded file that have \"(.*?)\" status$")
	public void i_search_any_one_uploaded_file_that_have_status(String status) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchStatusFile(status);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I only \"(.*?)\" filter with \"(.*?)\" filter type should selected$")
	public void i_only_filter_with_filter_type_should_selected(String includeSubFoldersFilter, String yesFilterOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySetFiltersAndFilterSelection(includeSubFoldersFilter, yesFilterOption, null, null, null, null, null, null);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^previous searched file should displayed$")
	public void previous_searched_file_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPreviousFileAndSearchFileInput();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with ([^\"]*) AND \"(.*?)\" filter with \"(.*?)\" filters should set$")
	public void filter_with_AND_filter_with_AND_filter_with_Shyamal_Patel_AND_filter_with_filters_should_set(String filter1, String option1, String filter2, String option2, String filter3, String option3, String filter4, String option4) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySetFiltersAndFilterSelection(filter1, option1, filter2, option2, filter3, option3, filter4, option4);
			else
				Assume.assumeTrue(true);
	}
	
	
	@Then("^previous search files filter should reset and all incomplete files should displayed$")
	public void previous_search_files_filter_should_reset_and_all_incomplete_files_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyResetSearchFileInputAndFileListing();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I verify Previous all filters \"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with ([^\"]*) AND \"(.*?)\" filter with \"(.*?)\" filters should set$")
	public void i_verify_Previous_all_filters_filter_with_AND_filter_with_AND_filter_with_Praveen_Mourya_AND_filter_with_filters_should_set(String filter1, String subFilter1, String filter2, String subFilter2, String filter3, String subFilter3, String filter4, String subFilter4) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySetFiltersAndFilterSelection(filter1, subFilter1, filter2, subFilter2, filter3, subFilter3, filter4, subFilter4);
			else
				Assume.assumeTrue(true);
	}
	
	@Then("^Previous Reset search files filter and all incomplete files should displayed$")
	public void previous_Reset_search_files_filter_and_all_incomplete_files_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyResetSearchFileInputAndFileListing();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" page should selected on \"(.*?)\" tab dropdown$")
	public void page_should_selected_on_tab_dropdown(String filesDropdown, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilesTabDropdown(filesDropdown);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I add \"(.*?)\" filter with \"(.*?)\" subfilter type$")
	public void i_add_filter_with_subfilter_type(String filter, String subFilter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFilter(filter, subFilter);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all \"(.*?)\" and perform right click And select \"(.*?)\"$")
	public void i_select_all_and_perform_right_click_And_select(String selectedTab, String clearActionOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clearActions(selectedTab, clearActionOption);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I remove \"(.*?)\" filter type$")
	public void i_remove_filter_type(String filterList) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeFilter(filterList);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set \"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with \"(.*?)\" AND \"(.*?)\" filter with ([^\"]*) AND \"(.*?)\" filter with \"(.*?)\"$")
	public void i_set_filter_with_AND_filter_with_AND_filter_with_AND_filter_with(String filter1, String subFilter1, String filter2, String subFilter2, String filter3, String subFilter3, String filter4, String subFilter4) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createFilterWithSubFilter(filter1, subFilter1, filter2, subFilter2, filter3, subFilter3, filter4, subFilter4);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I perform \"(.*?)\" action on \"(.*?)\" page$")
	public void i_perform_action_on_page(String action, String distributionPage) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.performActionOnDistributionPage(action);
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total incomplete action count of File Distribution page$")
	public void i_get_total_incomplete_action_count_of_File_Distribution_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getIncompleteActionsCountOfDistributionPage();
		else
			Assume.assumeTrue(true);
	}

	@Then("^total incomplete action count of File Distribution page should decrease$")
	public void total_incomplete_action_count_of_File_Distribution_page_should_decrease() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyIncompleteActionsCountOfDistributionPage();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all files and perform right click And select \"(.*?)\" and \"(.*?)\"$")
	public void i_select_all_files_and_perform_right_click_And_select_and(String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformActions(option1, option2);
		else
			Assume.assumeTrue(true);
	}
	
	/******* TestData Cleanup *******/
	
	@When("^I remove TestData \"(.*?)\" from Favourite Folder$")
	public void i_remove_TestData_from_Favourite_Folder(String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeTestDataFavouriteFolder();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I removed TestData Favourite Folder$")
	public void i_removed_TestData_Favourite_Folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deactivateTestDataFolder();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I removed ([^\"]*) TestData Favourite Forms$")
	public void i_removed_TestData_Favourite_Forms(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeTestDataFavouriteForms(user);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I clear previous actions count from testdata form$")
	public void i_clear_previous_actions_count_from_testdata_form() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeActionsCountFromTestDataForm();
		else
			Assume.assumeTrue(true);
	}
}