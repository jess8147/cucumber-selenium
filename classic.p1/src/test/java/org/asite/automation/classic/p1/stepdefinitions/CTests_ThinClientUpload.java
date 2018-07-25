package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.ThinClientUploadClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_ThinClientUpload {

	ThinClientUploadClassicScripts scripts = new ThinClientUploadClassicScripts();

	@Given("^I have focus on \"(.*?)\" folder$")
	public void i_have_focus_on_folder(String testFolder) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnTestFolder(testFolder);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have disabled Simple Upload$")
	public void i_have_selected_from_table() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.uncheckSimpleUploadCheckbox();
		else
			Assume.assumeTrue(true);
	}

	/*@Given("^I have Public Standard Document option enabled$")
	public void i_have_Public_Standard_Dcoument_option_enabled() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyPublicStandardDocOption();
		else
			Assume.assumeTrue(true);
	}*/

	@When("^I click on Upload Standard Document$")
	public void i_click_on_Public_Standard_Dcoument() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickPublicStandardDocOption();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" window should open for upload$")
	public void window_should_open_for_upload(String publishWindow) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyPublicStandardDocWindow(publishWindow);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" AND I have selected more then one Files from Local for \"(.*?)\"$")
	public void i_click_on_AND_I_have_selected_more_then_one_Files_from_Local(String addFilesText, String uploadType)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickAddFilesAndSelectFromLocal(uploadType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" input button$")
	public void i_click_on_enter_doc_detail_input_button(String btnText) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnEnterDocDetailButton(btnText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attributes should open for selected files$")
	public void attributes_should_open_for_selected_files() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyAttributesSection();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered all mandatory attributes$")
	public void i_have_entered_all_mendatory_attributes() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.enterAttributesDetails();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" on Publish window$")
	public void i_click_on(String btnText) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnDocumentDistribution();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup window should open$")
	public void publish_documents_Distribute_popup_should_open(String windowTitle) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyPublishDocumentWindowOpens(windowTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" user with \"(.*?)\" Action AND Due dates$")
	public void i_select_some_users_with_Action_Required_AND_Action_Due_dates(String user, String action)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.selectUsersAndAssignAction(user, action);
		else
			Assume.assumeTrue(true);
	}

	@When("^Click on Distribute button$")
	public void click_on_Distribute_button() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickDistibuteButton();
		else
			Assume.assumeTrue(true);

	}

	@Then("^selected users should be available on Publish Documents - Publish popup with Actions$")
	public void selected_users_should_be_available_on_Publish_Documents_Publish_popup_with_Actions() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifySelectedUser();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Start Upload button$")
	public void i_click_on_Start_Upload_button() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnStartUpload();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Selected documents should be uploaded AND Upload summary popup should open$")
	public void selected_documents_should_be_uploaded_AND_Upload_summary_popup_should_open() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentsOnUploadSummaryPopup();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on OK button on upload summary popup$")
	public void i_click_on_ok_button_on_upload_summary_popup() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOKButtonUploadSummaryPopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Uploaded documents should be available on document listing$")
	public void uploadede_documents_should_be_available_on_document_listing() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedDocuments();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Actions should be assigned to secondary user$")
	public void actions_should_be_assigned_to_secondary_users() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyActionAssignedToUser();
		else
			Assume.assumeTrue(true);
	}

	/* Without Distribution */

	@Given("^I have focus on \"(.*?)\" folder with attributes assigned$")
	public void i_have_focus_on_folder_with_attributes_assigned(String testFolder) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnCustomAttributeTestFolder(testFolder);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Copy FileName button$")
	public void i_click_on_Copy_FileName_button() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnCopyFileNameButton();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All documents File Name Without extension should be copied in Doc Title$")
	public void all_documents_File_Name_Without_extension_should_be_copied_in_Doc_Title() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyDocFileNameIsCopied();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter Values into header for Attributes AND Select Overwrite$")
	public void i_enter_Values_into_header_for_Attributes_AND_Select_Overwrite() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.enterHeaderValuesAndOverwrite();
		else
			Assume.assumeTrue(true);
	}

	@When("^click on Apply To All button$")
	public void click_on_Apply_To_All_button() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickApplyAll();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All attributes values should be filled with Values AND Doc Ref should be filled with combine custom attributes$")
	public void all_attributes_values_should_be_filled_with_Values_AND_Doc_Ref_should_be_filled_with_combine_custom_attributes()
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyAttributesCopied();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" in Distribute dropdown$")
	public void i_select_in_Distribute_dropdown(String distribution) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.selectDoNotDistributeOption(distribution);
		else
			Assume.assumeTrue(true);
	}
}
