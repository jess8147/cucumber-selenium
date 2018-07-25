package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.ViewDocumentScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ViewDocument {
	ViewDocumentScripts	documentScripts	= new ViewDocumentScripts();

	@When("^I click on any file$")
	public void i_click_on_any_file() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			documentScripts.gotoFileDocument();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I view any file of Universal Web Viewer project$")
	public void i_view_any_file_of_Universal_Web_Viewer_project() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			documentScripts.gotoFileDocumentInUniversalWebViewer();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New Tab should be open with HTML viewer AND file should be open/view in viewer$")
	public void new_Tab_should_be_open_with_HTML_viewer_AND_file_should_be_open_view_in_viewer() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			documentScripts.verifyFileInHTMLViewer();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^New Tab should be open with PDFTron viewer AND file should be open in viewer$")
	public void new_Tab_should_be_open_with_PDFTron_viewer_AND_file_should_be_open_in_viewer() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			documentScripts.verifyFileInUniversalWebViewer();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attribute of file should be available on top$")
	public void attribute_of_file_should_be_available_on_top() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			documentScripts.verifyFileAttributeOnTop();
			documentScripts.closeFileOpenedWindow();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New Tab should be open with Advanced Viewer AND file should be open/view in viewer$")
	public void new_Tab_should_be_open_with_Advanced_Viewer_AND_file_should_be_open_view_in_viewer() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			documentScripts.gotoFileOpenWindow();
			documentScripts.verifyFileOpenedInAdvancedViewer();
			documentScripts.closeFileOpenedWindow();
		}
		else
			Assume.assumeTrue(true);
	}
}
