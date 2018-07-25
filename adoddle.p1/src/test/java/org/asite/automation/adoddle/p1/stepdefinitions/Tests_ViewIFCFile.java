package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.ViewIFCFileScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ViewIFCFile {

ViewIFCFileScripts scripts = new ViewIFCFileScripts();
	
	@Given("^I have selected \"(.*?)\" viewer on Models Page$")
	public void i_have_selected_viewer_on_Models_Page(String viewer) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectBrowserRenderingViewer(viewer);
		else 
  			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\"$")
	public void i_Click_on(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnValidModelName();
		else 
  			Assume.assumeTrue(true);
	}

	@Then("^Selected model should be open in new tab$")
	public void selected_model_should_be_open_in_new_tab() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyModelOpensInNewTab();
		else 
  			Assume.assumeTrue(true);
	}

	@Then("^Model should be open in \"(.*?)\" viewer$")
	public void model_should_be_open_in_viewer(String viewerMode) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyModelViewer(viewerMode);
		else 
  			Assume.assumeTrue(true);
	}

	@Then("^IFC files should be available to view in Browser-Rendering Viewer$")
	public void ifc_files_should_be_available_to_view_in_browser_rendering_Viewer() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyIFCFileBrowserRendering();
		else 
  			Assume.assumeTrue(true);
	}
	
	@Then("^IFC files should be available to view in Server-Rendering Viewer$")
	public void ifc_files_should_be_available_to_view_in_server_rendering_Viewer() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyIFCFileBrowserRendering();
		else 
  			Assume.assumeTrue(true);
	}
}
