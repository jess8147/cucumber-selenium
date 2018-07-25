package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.UploadFilesBulkApplyScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_UploadFilesBulkApply {

	UploadFilesBulkApplyScripts scripts = new UploadFilesBulkApplyScripts();
	
	@When("^I click on \"(.*?)\" button on file Listing$")
	public void i_click_on_button_of_file_Listing(String buttonText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickAddFilesButton(buttonText);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I have current revision of existing files$")
	public void i_have_current_revision_of_existing_files() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.getCurrentRevision();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select multiple revision files \"(.*?)\" and \"(.*?)\"$")
	public void i_select_multiple_revision_files_and_new_files_to_upload(String file1, String file2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectMultipleRevisionFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select multiple newly created files$")
	public void i_select_multiple_newly_created_files() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectMultipleNewFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I enter all Values into header and apply all after selecting Overwrite$")
	public void i_enter_all_Values_into_header_and_apply_all_after_selecting_overwrite() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterHeaderValuesAndApplyAll();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Distribution User in textbox and click on Upload button$")
	public void i_select_Distribution_User_in_textbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectDistributionUser();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New files should get uploaded with new attributes$")
	public void new_files_should_get_uploaded_with_new_attributes() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNewFilesAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Revision files should get updated with new attributes$")
	public void revision_files_should_get_updated_with_new_attributes() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyRevisionFilesAttributes();
		else
			Assume.assumeTrue(true);
	}
}
