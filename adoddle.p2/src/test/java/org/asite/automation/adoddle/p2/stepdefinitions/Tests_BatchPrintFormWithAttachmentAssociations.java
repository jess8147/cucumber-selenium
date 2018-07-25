package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.BatchPrintFormWithAttachmentAssociationsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_BatchPrintFormWithAttachmentAssociations
{
	BatchPrintFormWithAttachmentAssociationsScripts scripts = new BatchPrintFormWithAttachmentAssociationsScripts();
	
	@When("^I search BatchPrint TestData form for Print$")
	public void i_search_BatchPrint_TestData_form_for_Print() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchPrintForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should displayed on Forms tab listing page$")
	public void form_should_displayed_on_Forms_tab_listing_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedForm();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I right click on Form and select \"(.*?)\" on Context click menu options$")
	public void i_right_click_on_Form_and_select_on_Context_click_menu_options(String printAllContextOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectPrintAll(printAllContextOption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should opened with title as \"(.*?)\"$")
	public void new_tab_should_opened_with_title_as(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.switchToSecondWindow();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter file name as \"(.*?)\" AND I click on \"(.*?)\"$")
	public void i_have_enter_file_name_as_AND_I_click_on(String fileName, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createBatchFileInLocal(fileName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated \"(.*?)\" file size successfully with local file$")
	public void i_have_validated_file_size_successfully_with_local_file(String fileName) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.compareFileSizeInLocal(fileName);
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* All Messages to PDF *******/
	
	@When("^I click on searched Print Form$")
	public void i_click_on_searched_Print_Form() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnPrintForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should opened for View Form$")
	public void new_tab_should_opened_for_View_Form() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.switchToSecondWindow();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" Button of \"(.*?)\" menu$")
	public void i_click_on_Button_of_menu(String exportSelectionType, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickAndSelectExportMenuOption(exportSelectionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^New third tab should opened with title as \"(.*?)\"$")
	public void new_third_tab_should_opened_with_title_as(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.switchToThirdWindow();
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Current Message to PDF *******/
	
	@When("^I click on \"(.*?)\" LH-panel tab And I select \"(.*?)\" formtype$")
	public void i_click_on_LH_panel_tab_And_I_select_formtype(String lhOption, String formType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickFormDetailsAndSelectFormType(lhOption, formType);
		else
			Assume.assumeTrue(true);
	}
}
