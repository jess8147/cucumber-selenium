package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.HtmlUploadWithCustomAttributeFilesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_HtmlUploadWithCustomAttributeFiles {

	HtmlUploadWithCustomAttributeFilesScripts	customFilesScripts	= new HtmlUploadWithCustomAttributeFilesScripts();

	@Given("^I drag mouse on \"(.*?)\" count on Files tab for upload$")
	public void i_drag_mouse_on_count_on_Files_tab_for_upload(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			customFilesScripts.getFilesTabIncompleteActionCount();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button of file Listing$")
	public void i_click_on_button_of_file_Listing(String addFiles) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.clickOnAddFiles();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button AND I have selected more then one Files into Local$")
	public void i_clicked_on_button_AND_I_have_selected_more_then_one_Files_into_Local(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.clickOnSelectFilesAndUpload();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Bulk Apply checkbox$")
	public void i_click_on_Bulk_Apply_checkbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.bulkApplyCheckboxSelect();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Header Panel should be displayed$")
	public void header_Panel_should_be_displayed() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.verifyHeaderPanel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter all Values into header for Attributes AND Select Overwrite$")
	public void i_enter_all_Values_into_header_for_Attributes_AND_Select_Overwrite() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.enterCustomAttributes();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^click on Apply to All button$")
	public void click_on_Apply_to_All_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.clickOnApplytoAll();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^all attributes values should be filled with Values$")
	public void all_attributes_values_should_be_filled_with_Values() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.getCustomFilesAttributesValue();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button of upload popup$")
	public void i_Click_on_button_of_upload_popup(String DistributeFiles) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.clickOnDistributeFiles(DistributeFiles);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" AND \"(.*?)\" textboxes should be available on upload popup$")
	public void and_textboxes_should_be_available_on_upload_popup(String To, String Subject) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.verifyDistributeTextboxes(To, Subject);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Distribution Group in textbox$")
	public void i_select_Distribution_Group_in_textbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			customFilesScripts.performDistributionAction(System.getProperty("primary.username"));
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" in \"(.*?)\" Textbox$")
	public void i_select_in_Textbox(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.enterDistributeGroupAndAction();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Total number of \"(.*?)\" action count should be increase for upload$")
	public void total_number_of_action_count_should_be_increase_for_upload(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			customFilesScripts.verifyIncompleteActionCount();
	}

	@Then("^\"(.*?)\" Action should be assigned to \"(.*?)\" AND All Files should be uploaded successfully AND It should be available in document listing$")
	public void action_should_be_assigned_to_AND_all_Files_should_be_uploaded_successfully_AND_it_should_be_available_in_document_listing(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			customFilesScripts.verifyUploadFileActionAndAttributesValue();
			customFilesScripts.verifyFilesAndAttributes();
			customFilesScripts.deleteCreatedFiles();
		}
		else
			Assume.assumeTrue(true);
	}
}
