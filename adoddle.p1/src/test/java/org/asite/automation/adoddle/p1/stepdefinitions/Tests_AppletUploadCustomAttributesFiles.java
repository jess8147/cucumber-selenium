package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.AppletUploadCustomAttributesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_AppletUploadCustomAttributesFiles {

	AppletUploadCustomAttributesScripts scripts = new AppletUploadCustomAttributesScripts();

	@Given("^I have focus on \"(.*?)\" folder in ([^\"]*) Project$")
	public void i_have_focus_on_folder_in_Project(String folderName,
			String projectName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnProjectFolder(projectName, folderName);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have Add Files button enabled$")
	public void i_have_Add_Files_button_enabled() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddFilesButton();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Add Files button$")
	public void i_click_on_Add_Files_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAddFilesButton();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Click here for Applet Upload link$")
	public void i_click_on_Click_here_for_Applet_Upload_link() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAppletUploadLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Applet upload popup should open$")
	public void applet_upload_popup_should_open() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAppletUploadPopup();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Add Files button on Applet upload popup$")
	public void i_click_on_Add_Files_button_on_Applet_upload_popup()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performAppletUpload();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Local file system popup should open$")
	public void local_file_system_popup_should_open() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have selected multiple files from local system AND I have clicked on Enter Document Details button$")
	public void i_have_selected_multiple_files_from_local_system_AND_I_have_clicked_on_Enter_Document_Details_button()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attributes should be load for all uploading documents$")
	public void attributes_should_be_load_for_all_uploading_documents()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Copy FileName button$")
	public void i_click_on_Copy_FileName_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^all documents File Name Without extension should be copied in Doc Title$")
	public void all_documents_File_Name_Without_extension_should_be_copied_in_Doc_Title()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter <Values> into header for <Attributes> AND Select Overwrite$")
	public void i_enter_Values_into_header_for_Attributes_AND_Select_Overwrite()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@When("^click on Apply To All button$")
	public void click_on_Apply_To_All_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^all attributes values should be filled with Values AND \"(.*?)\" should be filled with combine custom attributes$")
	public void all_attributes_values_should_be_filled_with_Values_AND_should_be_filled_with_combine_custom_attributes(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" in Distribute dropdown$")
	public void i_select_in_Distribute_dropdown(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@When("^click on Start Upload button$")
	public void click_on_Start_Upload_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Uploaded document should be available in document listing$")
	public void uploaded_document_should_be_available_in_document_listing()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAppletUploadDocuments();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Uploaded document should be distributed to distribution list$")
	public void uploaded_document_should_be_distributed_to_distribution_list() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAppletUploadDistribution();
		else
			Assume.assumeTrue(true);
	}
}
