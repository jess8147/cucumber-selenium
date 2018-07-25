package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.CreateDefectLHPanelScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateDefectLHPanel {
	
	CreateDefectLHPanelScripts scripts  = new CreateDefectLHPanelScripts();
	
	@Given("^I search \"(.*?)\" form  on \"(.*?)\" window$")
	public void i_search_form_on_window(String formTitle, String windowTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchFormOnCreateFormWindow(formTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Form should get filtered on Create Form window$")
	public void form_should_get_filtered_on_Create_Form_window(String formTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsFilteredOnCreateFormWindow(formTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on filtered \"(.*?)\" form$")
	public void i_click_on_filtered_form(String formTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFilteredForm(formTitle);
		else
			Assume.assumeTrue(true);
	}
			
	@When("^I enter values in mandatory fields to create ([^\"]*) form$")
	public void i_enter_values_in_mandatory_fields_in_create_form(String siteLocation) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterMandatoryFields(siteLocation);
		else
			Assume.assumeTrue(true);
	}

	@When("^I attach external files to Defect$")
	public void i_attach_external_files_to_Defect() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.attachExternalFilesToDefect();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button to create Defect$")
	public void i_click_send_button_to_create_defect(String btnTxt) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSendButton(btnTxt);
		else
			Assume.assumeTrue(true);
	}

	
	@Then("^Created defects should be available in listing$")
	public void created_defects_should_be_available_in_listing() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDefectIsCreated();
		else
			Assume.assumeTrue(true);
	}

	
	@Then("^Status \"(.*?)\" should be reflected in Listing with \"(.*?)\" color$")
	public void status_should_be_reflected_in_Listing_with_color(String status, String color) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyStatusOnListing(status, color);
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I close current window and switch to previous window$")
	public void i_switch_to_previous_window() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.closeCurrentWindowAndSwitchPrevious();
		}
		else
			Assume.assumeTrue(true);
	}
	

	@Given("^Defect should have action \"(.*?)\"$")
	public void defect_should_have_action(String action) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUserAction(action);
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform assigned action to defect form$")
	public void i_perform_assign_status_action() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.performAssignStatusAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New window should open and defect should be viewed$")
	public void new_window_should_open_and_defect_should_be_viewed() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormViewWindowOpens();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Defect should have status as \"(.*?)\" on Form view$")
	public void defect_should_have_status_as_on_form_view(String status) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDefectStatusOnFormView(status);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Defect should have status as \"(.*?)\" on listing$")
	public void defect_should_have_status_as_on_listing(String status) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDefectStatusOnListing(status);
		else
			Assume.assumeTrue(true);
	}

	@When("^\"(.*?)\" changes status to \"(.*?)\"$")
	public void contractor_changes_status_to_with_attachment(String user, String newStatus) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeDefectStatus(newStatus);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get existing site defect counts$")
	public void i_get_existing_site_defect_count() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.collectSiteDefectCounts();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get existing location defect counts$")
	public void i_have_existing_location_defect_counts() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.collectLocationDefectCounts();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Defect count should increase for \"(.*?)\" defect for \"(.*?)\"$")
	public void defect_count_should_increase_for_status_for_location(String status, String siteLocation) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDefectCountManagement(status, siteLocation);
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I click on \"(.*?)\" Icon on PlanView$")
	public void i_click_on_create_defect_icon_on_planview(String iconText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnCreateDefectIcon();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on defect Icon on Map$")
	public void i_click_on_defect_icon_on_map() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnCreatedDefectMapIcon();
		else
			Assume.assumeTrue(true);
	}
	
}
