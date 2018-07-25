package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ViewFilesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ViewFiles
{
	ViewFilesScripts scripts = new ViewFilesScripts();
	
	/***** Customized View *****/
	
	@When("^I \"(.*?)\" any two Coloums of \"(.*?)\" from Customize \"(.*?)\"$")
	public void i_any_two_Coloums_of_from_Customize(String addRemove, String activeTab, String arg3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addRemoveColoumsFromTabListingPage(addRemove, activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^click on Save button of Customise View$")
	public void click_on_Save_button_of_Customise_View() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSaveButtonOfCustomizedView();
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected both Coloums should \"(.*?)\" on \"(.*?)\" listing page$")
	public void selected_both_Coloums_should_on_listing_page(String isDisplay, String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddRemoveColoums(isDisplay, activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I \"(.*?)\" selected both Coloums in Customize \"(.*?)\"$")
	public void i_selected_both_Coloums_in_Customize(String addRemove, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addRemoveColoumsFromTabListingPage(addRemove, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected both Coloums should \"(.*?)\" on \"(.*?)\" listing page on last Position$")
	public void selected_both_Coloums_should_on_listing_page_on_last_Position(String isDisplay, String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddRemoveColoums(isDisplay, activeTab);
		else
			Assume.assumeTrue(true);
	}

	@When("^I moved Selected both \"(.*?)\" Coloums of Customize \"(.*?)\" into Top Position$")
	public void i_moved_Selected_both_Coloums_of_Customize_into_Top_Position(String activeTab, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.movedColoumsUsingCustomizedView(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Selected both Coloums should displayed on \"(.*?)\" listing page on First Position$")
	public void selected_both_Coloums_should_displayed_on_listing_page_on_First_Position(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySelectedColoumOnTop(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get Moved coloum width of \"(.*?)\" And I customize its width$")
	public void i_get_Moved_coloum_width_of_And_I_customize_its_width(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getMovedColumnWidthAndCustomizeColumn(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Moved cutomized column width change with its previous width on \"(.*?)\"$")
	public void moved_cutomized_column_width_change_with_its_previous_width_on(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCustomizeMovedColumnWidth(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get Moved cutomized column new width on \"(.*?)\" And I again customized its width with previous width$")
	public void i_get_Moved_cutomized_column_new_width_on_And_I_again_customized_its_width_with_previous_width(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getCustomizedMovedColumnWidthAndCustomizeColumn(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^newly moved cutomized column width of \"(.*?)\" should changed with its original width$")
	public void newly_moved_cutomized_column_width_of_should_changed_with_its_original_width(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCustomizedMovedColumnWidth(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I add all fields in Customize Selected Fields$")
	public void i_add_all_fields_in_Customize_Selected_Fields() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.CustomizedViewTestDataCleanUp();
		else
			Assume.assumeTrue(true);
	}
	
	/*** Sorting Scenario ***/
	
	@When("^I go to \"(.*?)\" folder of ([^\"]*) project$")
	public void i_go_to_folder_of_project(String folder, String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.clickElementWithText(project);
			scripts.clickElementWithText(folder);
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I get Last page files \"(.*?)\" Column Count$")
	public void i_get_Last_page_files_Column_Count(String columnName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getLastPageFilesCount(columnName);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Sorted \"(.*?)\" Column in \"(.*?)\" order list$")
	public void i_Sorted_Column_in_order_list(String columnName, String order) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sortByColumnNameInSpecifiedOrder(columnName, order);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get \"(.*?)\" page files \"(.*?)\" Column Name$")
	public void i_get_page_files_Column_Name(String filesOrder, String columnName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFilesNameWithRespectToOrder(filesOrder, columnName);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I go to Last page of selected Projects$")
	public void i_go_to_Last_page_of_selected_Projects() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.gotoLastPage();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" files \"(.*?)\" Column in \"(.*?)\" order list$")
	public void files_Column_in_order_list(String filesOrder, String columnName, String order) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyListingOrder(filesOrder, order, columnName);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I verify \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" AND \"(.*?)\" Columns sorting should disabled$")
	public void i_verify_AND_Columns_sorting_should_disabled(String column1, String column2, String column3, String column4, String column5, String column6, String column7) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyColumnSortingDisabled(column1, column2, column3, column4, column5, column6, column7);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" group and click on \"(.*?)\" formtype$")
	public void i_click_on_group_and_click_on_formtype(String formGroup, String formType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.clickElementWithText(formGroup);
			scripts.clickElementWithText(formType);
		} else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I scrolldown window for \"(.*?)\" Column until last Records not displyed$")
	public void i_scrolldown_window_for_Column_until_last_Records_not_displyed(String columnName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.scrollDownDistributionPage(columnName);
		else
			Assume.assumeTrue(true);
	}

	@When("^I navigate to ([^\"]*) project with ([^\"]*) folder in ([^\"]*) sub folder$")
	public void i_navigate_to_project_with_folder_in_sub_folder(String project, String folder, String subFolder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.clickElementWithText(project);
			scripts.clickElementWithText(folder);
			scripts.clickElementWithText(subFolder);
		} else
			Assume.assumeTrue(true);
	}
}
