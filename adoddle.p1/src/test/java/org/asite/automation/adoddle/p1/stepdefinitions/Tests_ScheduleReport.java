package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.ScheduleReportScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ScheduleReport {
	ScheduleReportScripts scheduleScripts = new ScheduleReportScripts();

	@When("^check the Incomplete Action counts$")
	public void check_the_Incomplete_Action_counts() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.getTotalActionCount();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I goto \"(.*?)\" from Adoddle$")
	public void i_goto_from_Adoddle(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.switchIntoClassicView();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Reporting tab in classic$")
	public void i_click_on_Reporting_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickClassicReportingTab();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on \"(.*?)\" page$")
	public void i_should_redirect_on_page(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyReportsPage();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" img icon of Reports page$")
	public void i_click_on_img_icon_of_Reports_page(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnCreateReport();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should be opened$")
	public void page_should_be_opened(String pageTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifySearchReportsPage(pageTitle);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" Report And I click on \"(.*?)\" button$")
	public void i_select_Report_And_I_click_on_button(String reportType,
			String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectReportAndClickOnCreateReport(reportType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I entered \"(.*?)\" And I select Workspace of ([^\"]*)$")
	public void i_entered_And_I_select_Workspace_of(String reportType, String project)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.enterReportNameAndSelectWorkspace(reportType, project);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" into \"(.*?)\" dropdown list And select \"(.*?)\" radio Button$")
	public void i_select_into_dropdown_list(String frequencyType, String arg2,
			String firstMonthRadioButton) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectFrequency(frequencyType,
					firstMonthRadioButton);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" for Hour AND \"(.*?)\" for Minute into \"(.*?)\" dropdown list$")
	public void i_select_for_Hour_AND_for_Minute_into_dropdown_list(
			String hour, String mins, String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectTime(hour, mins);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link of \"(.*?)\" Area$")
	public void i_click_on_link_of_Area(String folder, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnFolderLink(folder);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select project as ([^\"]*) \\+ \"(.*?)\" into select Workspace dorpdown list$")
	public void i_select_project_as_into_select_Workspace_dorpdown_list(
			String project, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectWorkspace(project);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^it should display the folder tree structure$")
	public void it_should_display_the_folder_tree_structure() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyFolderTreeStructure();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select the folder named \"(.*?)\" AND click on \"(.*?)\" Button$")
	public void i_select_the_folder_named_AND_click_on_Button(String arg1,
			String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnReportsFolderAndSelectFolder();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^it should display \"(.*?)\" area$")
	public void it_should_display_area(String documentDetailsArea)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyDocumentDetailsArea(documentDetailsArea);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select POI AND Status$")
	public void i_select_POI_AND_Status() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectPoiAndStatus();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Next button$")
	public void i_click_on_Next_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnNext();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^it should display the \"(.*?)\" area$")
	public void it_should_display_the_area(String distributeArea)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyDistributeArea(distributeArea);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select organization AND select any one user$")
	public void i_select_organization_AND_select_any_one_user()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectOrgAndUser();
		} else
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" Button$")
	public void click_on_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnAddToDistributionList();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" in \"(.*?)\" dropdown AND select \"(.*?)\" in \"(.*?)\" dropdown$")
	public void i_select_in_dropdown_AND_select_in_dropdown(String actionType,
			String arg2, String days, String arg4) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectActionAndNumberOfDays(actionType, days);
		} else
			Assume.assumeTrue(true);
	}

	@When("^click on Distribute Button$")
	public void click_on_Distribute_Button() {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnDistribute();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^it should add the user in \"(.*?)\" list$")
	public void it_should_add_the_user_in_list(String reportRecipientsList)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyReportRecipientsList(reportRecipientsList);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" Button$")
	public void i_click_on_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnSave();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^It should generate the report And the report should get published in the selected target folder And I close opened window$")
	public void it_should_generate_the_report_And_the_report_should_get_published_in_the_selected_target_folder_And_I_close_opened_window()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.gotoPreviousWindow();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I verify generated report on \"(.*?)\" page in \"(.*?)\"$")
	public void i_verify_generated_report_on_page(String arg1, String view) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyCreatedReportInReportsPage(view);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I goto project as ([^\"]*) AND click on \"(.*?)\" folder$")
	public void i_goto_project_as_AND_click_on_folder(String project,
			String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.gotoReportsFolder(project, folder);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^created Report should be available in Report Listing page$")
	public void created_Report_should_be_available_in_Report_Listing_page()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyCreatedReportInClassic();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" into Settings dropdown list$")
	public void i_click_on_into_Settings_dropdown_list(String arg1) {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.switchIntoswitchDefaultView();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on Adoddle View on \"(.*?)\" tab$")
	public void i_should_redirect_on_Adoddle_View(String linkTab)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyAsiteMenuList(linkTab);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^generated report should be available in Reports Folder And \"(.*?)\" Action should be assigned to it$")
	public void generated_report_should_be_available_in_Reports_Folder_And_Action_should_be_assigned_to_it(
			String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyCreatedReportInAdoddle(actionType);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Incomplete Actions counts should be increased By (\\d+)$")
	public void incomplete_Actions_counts_should_be_increased_By(int arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyIncompleteActionCount();
		} else
			Assume.assumeTrue(true);
	}

	/************************************************************************************************************************/

	@When("^I click on Save&Close Button$")
	public void i_click_on_Save_Close_Button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnSaveAndClose();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link AND I switch to \"(.*?)\" from Settings dropdown list$")
	public void i_click_on_link_AND_I_switch_to_from_Settings_dropdown_list(
			String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.gotoHomeAndSwitchIntoAdoddleView();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" LH Button$")
	public void i_click_on_LH_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnEditAndScheduleLHButton();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I should display the \"(.*?)\" listing page$")
	public void i_should_display_the_listing_page(String EditScheduleReportsPage)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyEditAndScheduleReportsPage(EditScheduleReportsPage);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I search created \"(.*?)\" Report And I click on \"(.*?)\" link$")
	public void i_search_created_Report_And_I_click_on_link(String type, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.searchReportAndClickOnEditReport(type);
		} else
			Assume.assumeTrue(true);
	}

	/************************************************************************************************************************/

	@When("^I click on workspace of ([^\"]*)$")
	public void i_click_on_workspace_of(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnWorkspace(project);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" option into REPORTS dropdown list$")
	public void i_select_option_into_REPORTS_dropdown_list(String newReport)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectReportsDropdownOption(newReport);
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I entered the \"(.*?)\" Report name AND select folder for \"(.*?)\" report$")
	public void i_Entered_the_Report_name_AND_select_folder_for_report(String rtype, String asiteView) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.enterReportNameAndSelectFolder(rtype, asiteView);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I double click on Multiple columns of Layout tab$")
	public void i_double_click_on_Multiple_columns_of_Layout_tab()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.selectMultipleColumns();
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^Adhoc Report should be executed$")
	public void adhoc_Report_should_be_executed() {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyReportExecution();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Report should be saved And Report downloaded in XLS format And I Close the opened Window$")
	public void report_should_be_saved_And_Report_downloaded_in_XLS_format_And_I_Close_the_opened_Window(String type)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.verifyReportInLocalDirectory(type);
			scheduleScripts.closeCreateReportPage();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link AND I click on Reporting tab$")
	public void i_click_on_link_AND_I_click_on_Reporting_tab(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnMyHomeAndgotoReportingTab();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I search created Report And I click on \"(.*?)\" img icon$")
	public void i_search_created_Report_And_I_click_on_img_icon(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnEditAndScheduleReport();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I checked \"(.*?)\" CheckBox AND click on \"(.*?)\" Button of \"(.*?)\" page$")
	public void i_checked_CheckBox_AND_click_on_Button_of_page(String arg1,
			String arg2, String reportType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.checkSendNowAndclickOnSaveAndClose(reportType);
		} else
			Assume.assumeTrue(true);
	}
	
	
	/********************************************* Preview Report *********************************************/
	
	@Then("^\"(.*?)\" report for \"(.*?)\" Preview verify on \"(.*?)\"$")
	public void report_for_Preview_verify_on(String asiteView, String reportExtension, String previewType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.verifyReportPreview(asiteView, reportExtension, previewType);
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^I verify downloaded Adhoc Report Contect$")
	public void i_verify_downloaded_Adhoc_Report_Contect() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.verifyDownloadedReportContent();
		else
			Assume.assumeTrue(true);
	}
	
	
	/********************************************* Standard Report Adoddle *********************************************/
	
	@Given("^I get loggedIn User and Org$")
	public void i_get_loggedIn_User_and_Org() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.getLoggedInUserAndOrg();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" filter With both Current Set and Superseded$")
	public void i_set_filter_With_both_Current_Set_and_Superseded(String filter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.createFilter("more", filter);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total files count of ([^\"]*) project and ([^\"]*) project$")
	public void i_get_total_files_count_of_project_and_project(String project1, String project2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.getTotalFilesCountOfReportCriteriaProjects(project1, project2);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I double click on Multiple \"(.*?)\" columns of Layout field$")
	public void i_double_click_on_Multiple_columns_of_Layout_field(String files) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.selectMultipleColumns(files);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" Radio button into Report Criteria And I select ([^\"]*) and ([^\"]*) into dropdown$")
	public void i_select_Radio_button_into_Report_Criteria_And_I_select_and_into_dropdown(String arg1, String project1, String project2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.selectProjectsInReportCriteriaField(project1, project2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select ([^\"]*) project and select \"(.*?)\" folder for scheduling$")
	public void i_select_project_and_select_folder_for_scheduling(String project, String folder) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnReportsFolder(project, folder);
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^\"(.*?)\" Standard report for \"(.*?)\" preview downloaded and verify total Files count of both Selected Projects$")
	public void standard_report_for_preview_downloaded_and_verify_total_Files_count_of_both_Selected_Projects(String asiteView, String reportExtension) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.downloadAndVerifyPreviewOfSelectedProjectCriteria(asiteView, reportExtension);
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on SaveAndClose Button$")
	public void i_click_on_SaveAndClose_Button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scheduleScripts.clickOnSaveAndClose();
			scheduleScripts.gotoPreviousWindow();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I \"(.*?)\" Report from \"(.*?)\" page$")
	public void i_Report_from_page(String inactive, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scheduleScripts.deactivateScheduleReport(inactive);
		else
			Assume.assumeTrue(true);
	}
}
