package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.CreateEditDocStatusScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateEditDocStatus {
	CreateEditDocStatusScripts statusScripts = new CreateEditDocStatusScripts();

	@When("^I set project as ([^\"]*) and status as \"(.*?)\"$")
	public void i_set_project_as_poi_as(String project, String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.setScriptData(project);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new all required Statuses$")
	public void i_create_new_all_required_Statuses() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.createDocumentStatuses();
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit color and Font of \"(.*?)\" status to \"(.*?)\" and \"(.*?)\" respectively with Cell Record \"(.*?)\"$")
	public void i_edit_color_and_Font_of_status_to_and_respectively_with_Cell_Record(String status, String color, String font, String applyTo) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.setDocStatusFontAndColor(status, color, font, applyTo);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I clicked on \"(.*?)\" button And I select more then one Files to upload$")
	public void i_clicked_on_button_And_I_select_more_then_one_Files_to_upload(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.clickOnSelectFilesAndUpload();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Status dropdown list should not contains \"(.*?)\" AND \"(.*?)\" accessibility$")
	public void status_dropdown_list_should_not_contains_AND_accessibility(
			String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.verifyAccessableStatuses();
		else
			Assume.assumeTrue(true);
	}

	@When("^I fill all mendatory fields for Status$")
	public void i_fill_all_mendatory_fields_for_Status() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.enterMendatoryAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Uploaded files status should be \"(.*?)\" and background color \"(.*?)\" and font \"(.*?)\" with Cell Record \"(.*?)\"$")
	public void uploaded_files_status_should_be_and_backgroud_color_and_font_with_cell_record(
			String status, String bgColor, String font, String rowFlag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.verifyUploadedFilesAndStatus(status, bgColor, font, rowFlag);
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I right click on selected files And drag mouse to \"(.*?)\" And I click on \"(.*?)\" into context click menu list$")
	public void i_right_click_on_selected_files_And_drag_mouse_to_And_I_click_on_into_context_click_menu_list(
			String edit, String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.clickOnFilesAndEditAndStatus(edit, status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify Status dropdown list with created Status that should not contains \"(.*?)\" accessibility$")
	public void i_verify_Status_dropdown_list_with_created_Status_that_should_not_contains_accessibility(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.verifyChangeableStatus();
		else
			Assume.assumeTrue(true);
	}

	@When("^I change selected Status into \"(.*?)\" And I entered Status change Reason \"(.*?)\" And I click on Change Status button$")
	public void i_change_selected_Status_into_And_I_click_on_Change_Status_button(
			String status, String statusChangeNote) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			statusScripts.changeStatusAndNoteAndClickOnChangeStatus(status,
					statusChangeNote);
		else
			Assume.assumeTrue(true);
	}


	@When("^I deactivate created all \"(.*?)\" using secondary user$")
	public void i_deactivate_created_all_using_secondary_user(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			statusScripts.logOut();
			statusScripts.login(System.getProperty("secondary.username"),
					System.getProperty("secondary.password"));
			statusScripts.navigateTab(LandingPage.lnk_Projects);
			statusScripts.deactivateStatuses();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^It should not be displayed in \"(.*?)\" dropdown list when files should be upload in folder \"(.*?)\"$")
	public void it_should_not_be_displayed_in_dropdown_list_when_files_should_be_upload_in_folder(
			String arg1, String poiStatusFolder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			statusScripts.navigateTab(LandingPage.lnk_Files);
			statusScripts.verifyStatusesDeactivate();
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I deactivate all uploaded status documents in folder$")
	public void i_deactivate_all_uploaded_poi_docs() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			statusScripts.deactivateAllDocuments();
		} else
			Assume.assumeTrue(true);
	}

}
