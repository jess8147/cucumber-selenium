package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateReportScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateReport
{

	CreateReportScripts reportScripts = new CreateReportScripts();
	
	
	@When("^I click on \"(.*?)\" icon$")
	public void i_click_on_icon(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.clickOnCreateNewReport();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Report Types options should open in dropdown$")
	public void report_Types_options_should_open_in_dropdown() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.verifyReportTypeOptionMenu();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Create New \"(.*?)\"$")
	public void i_click_on_Create_New(String reportType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.clickOnReportMenu(reportType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should open in \"(.*?)\" panel$")
	public void should_open_in_panel(String reportTab, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.verifyOpenedReportIntoPanel(reportTab.trim());
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter the report name \"(.*?)\" AND selected folder for report$")
	public void i_Entered_the_report_name_AND_selected_folder_for_report(String reportType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.enterReportNameAndSelectFolder(reportType);
		else
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" button of Manage Report popup$")
	public void click_on_button_of_Manage_Report_popup(String nextButton) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.clickOnNext(nextButton);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" tab should open$")
	public void tab_should_open(String openTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.verifySelectedTab(openTab);
		else
			Assume.assumeTrue(true);
	}

	@When("^I double click on \"(.*?)\"$")
	public void i_double_click_on(String categories) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.searchAndSelectCategories(categories);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should be selected$")
	public void should_be_selected(String categories) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.verifySelectedCategories(categories);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I double click on \"(.*?)\" into Sorts tab$")
	public void I_double_click_on_into_Sorts_tab(String selectField)
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.doubleClickOnSelectedField(selectField);
		else
			Assume.assumeTrue(true);
	}

	@When("^I double click on Multiple columns$")
	public void i_double_click_on_Multiple_columns() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.selectMultipleColumns();
		else
			Assume.assumeTrue(true);
	}

	@When("^Click on \"(.*?)\" button$")
	public void click_on_button(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.clickOnExecuteSelectedReport();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Report should be executed$")
	public void report_should_be_executed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.verifyReportExecution();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should saved And downloaded in XLSX format$")
	public void should_saved_And_downloaded_in_XLSX_format(String reportType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.verifyReportInLocalDirectory(reportType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I verify downloaded \"(.*?)\" Contect$")
	public void i_verify_downloaded_Contect(String reportType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			reportScripts.verifyDownloadedReportContent(reportType);
		else
			Assume.assumeTrue(true);
	}
}
