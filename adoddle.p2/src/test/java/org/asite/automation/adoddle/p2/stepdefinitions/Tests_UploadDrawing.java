package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.UploadDrawingScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_UploadDrawing {

	UploadDrawingScripts scripts  = new UploadDrawingScripts();
	
	@Given("^Field enabled project ([^\"]*) is available$")
	public void field_enabled_project_is_available(String projectTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFieldEnabledProject(projectTitle);
		else
			Assume.assumeTrue(true);
	}

	
	@Given("^Site \"(.*?)\" is created in the project ([^\"]*)$")
	public void site_is_created_in_the_project(String siteTitle, String projectTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySiteIsCreated(siteTitle, projectTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I context click on existing Site \"(.*?)\"$")
	public void i_context_click_on_existing_Site(String siteTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickExistingSite(siteTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" option to add drawing$")
	public void i_click_on_option_to_add_drawing(String option) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnContextOption(option);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select drawing file and upload it$")
	public void i_select_drawing_file_and_upload_it() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDrawingFileAndUpload();
		else
			Assume.assumeTrue(true);
	}

	@Given("^Location \"(.*?)\" is created in the site \"(.*?)\"$")
	public void location_is_created_in_the_project(String locationTitle, String siteTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyLocationIsCreated(locationTitle, siteTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I context click on existing Location \"(.*?)\"$")
	public void i_context_click_on_existing_Location(String locationTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickExistingLocation(locationTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^PlanView Icon should be enabled$")
	public void planview_Icon_should_be_enabled() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPlanViewIconEnabled();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Viewer should display the drawing file$")
	public void viewer_should_display_the_drawing_file() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDrawingFileIsVisible();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I download the uploaded drawing file$")
	public void i_download_the_uploaded_drawing_file() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) {
			/*scripts.downloadUploadedDrawingFile();*/
			System.out.println("INFO: Depricated functionality");
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Downloaded file size should match with uploaded file$")
	public void downloaded_file_size_should_match_with_uploaded_file() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) {
			/*scripts.verifyDrawingFileSize();*/
			System.out.println("INFO: Depricated functionality");
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I remove created site from project$")
	public void i_remove_created_site_from_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeSiteFromProject();
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^Site should be removed from project$")
	public void site_should_be_removed_from_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySiteIsRemoved();
		else
			Assume.assumeTrue(true);
	}
}
