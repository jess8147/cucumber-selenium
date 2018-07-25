package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreateMultipleRepliesFormScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateMultipleRepliesForm {

	CreateMultipleRepliesFormScripts scripts = new CreateMultipleRepliesFormScripts();

	@Given("^I have focus on folder \"(.*?)\"$")
	public void i_have_focus_on_folder(String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.focusFolder(folder);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^More than one file should Available in File Listing$")
	public void more_than_one_file_should_Available_in_File_Listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateFileCountInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have posted comment on multiple files Available in listing$")
	public void i_have_posted_comment_on_first_two_files_Available_in_listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.postCommentOnFile();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have selected User as \"(.*?)\" into \"(.*?)\" Field$")
	public void i_have_selected_User_as_into_Field(String user, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectUserInToField(user);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have selected User as \"(.*?)\" into \"(.*?)\" AND I put Response as \"(.*?)\"$")
	public void i_have_selected_User_as_into_AND_I_put_Response_as(String user, String arg2, String arg3)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clearFieldBox();
			scripts.selectUserAndDescription(user);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" clip icon$")
	public void i_click_on_clip_icon(String strType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnCreateFormButtonOptions(strType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" associate icon$")
	public void i_click_on_associate_icon(String strType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnCreateFormButtonOptions(strType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link from dropDown menuList$")
	public void i_click_on_link_from_dropDown_menuList(String strType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOndropDownListOptions(strType);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have Attached more than one documents$")
	public void i_have_Attached_more_than_one_documents() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSelectFilesAndAttachDocuments();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have Associated atleast one documents for \"(.*?)\"$")
	public void i_have_Associated_atleast_one_documents_for(String msgType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateFilesAndClickOnSave();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Associated more than one Discussions for \"(.*?)\"$")
	public void i_Associated_more_than_one_Discussions_for(String msgType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateDiscussionsAndClickOnSave(msgType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have Associated more than one Forms for \"(.*?)\"$")
	public void i_have_Associated_more_than_one_Forms_for(String msgType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateFormAndClickOnSave(msgType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Send button AND close form successfully$")
	public void i_click_on_Send_button_AND_close_form_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickSaveButtonOnForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" form should created sucessfully AND I validated all  Attached AND Associated documents$")
	public void form_should_created_sucessfully_AND_I_validated_all_Attached_AND_Associated_documents(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateFormAttachmentsAndAssociations();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search form \"(.*?)\" AND I validated assigned actions$")
	public void i_have_search_form_AND_I_validated_assigned_actions(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateFormAndAssignedAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" form action should completed sucessfully AND I validated completed form actions$")
	public void form_action_should_completed_sucessfully_AND_I_validated_completed_form_actions(String formAction)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateCompletedActions();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search form \"(.*?)\"$")
	public void i_have_search_created_form(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchAndValidateForm();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I validated all assigned actions$")
	public void i_validated_all_assigned_actions() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateUserAssignedActions();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Click form \"(.*?)\" title$")
	public void i_Click_form_title(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.openFormInViewer();
		else
			Assume.assumeTrue(true);
	}

	@Then("^form \"(.*?)\" should open in Viewer$")
	public void form_should_open_in_Viewer(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsViewed();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click \"(.*?)\" AND I Select option \"(.*?)\"$")
	public void i_click_AND_I_Select_option(String arg1, String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickActionAndSelectOption(option);
		else
			Assume.assumeTrue(true);
	}

	@When("^I validated all Attached AND Associated documents for Message \"(.*?)\"$")
	public void i_validated_all_Attached_AND_Associated_documents_for_Message(String msgID) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllAttachmentAndAssociations(msgID);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Validated \"(.*?)\" of form sucessfully$")
	public void i_Validated_of_form_sucessfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAllFormReplies();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I navigate left panel AND I click on Attachments$")
	public void i_navigate_left_panel_AND_I_click_on_Attachments() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.naviagteLeftPanelAttachmentTab();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Atleast one record should Available in Listing$")
	public void atleast_one_record_should_Available_in_Listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAttachmentsLHPanelRecord();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Select Attachments filter type as \"(.*?)\" AND Message id as \"(.*?)\"$")
	public void i_Select_Attachments_filter_type_as_AND_Message_id_as(String aType, String msgID) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.filterFormAttachmentResult(aType, msgID);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Validated Record count as \"(.*?)\" for Message id as \"(.*?)\"$")
	public void i_Validated_Record_count_as_for_Message_id_as(String rCount, String msgID) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdiateAttachmentFilteredResultCount(rCount, msgID);
		else
			Assume.assumeTrue(true);
	}

	@When("^I mouse hover \"(.*?)\" link I select all files AND click on Download$")
	public void i_mouse_hover_link_I_select_all_files_AND_click_on_Download(String lnkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesAndClickOnDownload(lnkTab);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I am on \"(.*?)\" tab AND I select all files AND click on Download$")
	public void i_am_on_tab_AND_I_select_all_files_AND_click_on_Download(String lnkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesAndClickOnDownload(lnkTab);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select all files AND click on Download Icon$")
	public void i_select_all_files_AND_click_on_Download_Icon() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectAssociationsAndAttachmentsClickOnDownload();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Batch file should be created AND \"(.*?)\" Zip file should be downloaded into Local Directory$")
	public void batch_file_should_be_created_AND_Zip_file_should_be_downloaded_into_Local_Directory(String linkTab)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.downloadZipDocumentIntoLocal(linkTab);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I Unzipped downloaded \"(.*?)\" zip file$")
	public void i_Unzipped_downloaded_zip_file(String linkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.zipIntoUnZip(linkTab);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I validated all \"(.*?)\" in Local Directory$")
	public void i_validated_all_in_Local_Directory(String linkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.getFileNamesFromLocalFolder(linkTab);
			scripts.verifyFilesNameIntoSystem(linkTab);
		} else
			Assume.assumeTrue(true);
	}

}