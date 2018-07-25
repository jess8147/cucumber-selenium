package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.FileComparisonThinClientHtmlViewerClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_FileComparisonThinClientHtmlViewer
{
	FileComparisonThinClientHtmlViewerClassicScripts classicScripts = new FileComparisonThinClientHtmlViewerClassicScripts();
	
	@Given("^I have selected \"(.*?)\" from \"(.*?)\"$")
	public void i_have_selected_from_edit_workspace(String arg1, String adminDropdownList) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.selectEnableFileCompareCheckBox(adminDropdownList);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select two files$")
	public void i_select_two_files() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.selectTwoFilesCheckbox();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" icon$")
	public void click_on_icon(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.clickOnCompareDocuments();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^popup should open with both compared files in viewer$")
	public void popup_should_open_with_both_compared_files_in_viewer() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.verifyCompareFilesInViewer();
		}
		else
			Assume.assumeTrue(true);
	}
}
