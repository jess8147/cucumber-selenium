package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.FieldTabListViewMapViewScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Tests_FieldTabListViewMapView {

	FieldTabListViewMapViewScripts scripts = new FieldTabListViewMapViewScripts();
	
	@Given("^Site ([^\"]*) is available in the project ([^\"]*)$")
	public void site_is_available_in_the_project(String siteTitle, String projectTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyExistingProjectSite(siteTitle, projectTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^Location ([^\"]*) is available in the Site ([^\"]*)$")
	public void location_is_available_in_the_site(String location, String siteTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyExistingSiteLocation(location, siteTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Defect Listing page should be displayed$")
	public void defect_Listing_page_should_be_displayed() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDefectsAvailable();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I select \"(.*?)\" in Listing page$")
	public void i_select_in_Listing_page(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.switchToAppsDistribution();
		else
			Assume.assumeTrue(true);
	}
	

	}
