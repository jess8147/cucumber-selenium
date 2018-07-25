package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.FileComparisonScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_FileComparison
{
	FileComparisonScripts comparisonScripts = new FileComparisonScripts();
	
	@When("^I select two files$")
	public void i_select_two_files() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			comparisonScripts.selectTwoFilesCheckbox();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^right click on selected files$")
	public void right_click_on_selected_files() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			comparisonScripts.clickOnSelectedFiles();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Compare Files for file comparison$")
	public void i_click_on_for_file_comparison() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			comparisonScripts.clickOnCompareFiles();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should open with both compared files in viewer$")
	public void new_tab_should_open_with_both_compared_files_in_viewer() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			comparisonScripts.gotoFileOpenWindow();
			comparisonScripts.verifyCompareFilesInViewer();
			comparisonScripts.closeFileOpenedWindow();
		}
		else
			Assume.assumeTrue(true);
	}
}
