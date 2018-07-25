package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.ViewDocumentThinClientViewerClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_ViewDocumentThinClientViewer
{
	ViewDocumentThinClientViewerClassicScripts classicScripts = new ViewDocumentThinClientViewerClassicScripts();
	
	@Then("^I have atleast one file in Files listing documents$")
	public void i_have_atleast_one_file_in_Files_listing_documents() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.verifyFileListing();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on any file$")
	public void i_click_on_any_file() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.gotoFileDocument();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Popup should be open with Thin Client Viewer And Attribute of file should be available on LH Panel$")
	public void popup_should_be_open_with_Thin_Client_Viewer_And_Attribute_of_file_should_be_available_on_LH_Panel() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.gotoFileOpenWindow();
			classicScripts.verifyFileAttributes();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^file should be open/view in viewer$")
	public void file_should_be_open_view_in_viewer() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.verifyFileOpened();
			classicScripts.closeFileOpenedWindow();
		}
		else
			Assume.assumeTrue(true);
	}
}
