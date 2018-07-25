package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateCommentScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateComment {

	CreateCommentScripts scripts = new CreateCommentScripts();

	@Given("^I am already logged in And I am on Files tab And I have atleast one document in document listing$")
	public void i_am_already_logged_in_i_am_on_files_tab_having_atleast_one_doc() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUserIsLoggedIn();
			scripts.navigateFilesTab();
			scripts.verifyDocumentAvailable();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Right click on any document$")
	public void i_Right_click_on_any_document() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.contextClickDocument();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should open on files page$")
	public void popup_should_open(String newPopupText) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyNewPopup(newPopupText);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have opened \"(.*?)\" popup to create comment$")
	public void i_have_opened_popup_to_create_comment(String newPopupText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyNewPopup(newPopupText);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have selected secondary User into \"(.*?)\"$")
	public void i_have_selected_Users_into(String toFieldText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUserExistsInToField(toFieldText);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have entered \"(.*?)\"$")
	public void i_have_entered(String titleLabelText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.enterTitleText();
			scripts.verifyTitleLabel(titleLabelText);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have attached atleast one documents$")
	public void i_have_attached_atleast_one_documents() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.attachAtLeastOneDocument();
		} else
			Assume.assumeTrue(true);

	}

	@Given("^I have associated atleast one document$")
	public void i_have_associated_atleast_one_document() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.associateAtleastOneDocument(2);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have associated atleast one discussion$")
	public void i_have_associated_atleast_one_discussion() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.associateAtleastOneDiscussion(2);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have associated atleast one form$")
	public void i_have_associated_atleast_one_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.associateAtleastOneForm(3);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\" button$")
	public void i_Click_on_button(String submitBtnText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickSubmitButton(submitBtnText);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Comment should be successfully created$")
	public void comment_should_besuccessfully_created() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifySuccessMessage();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Comment should be available in \"(.*?)\" tab$")
	public void comment_should_be_available_in_tab(String aMessagesTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCommentOnDiscussionTab(aMessagesTab);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Comment should be available in Files detail$")
	public void comment_should_be_available_in_Files_detail() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.navigateFilesTab();
			scripts.verifyCommentOnFilesDetails();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Association should be successfully done with selected documents$")
	public void doc_association_should_be_successfully_done_with_selected_documents() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFileAssociations();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Form Association should be successfully done with selected forms$")
	public void form_association_should_be_successfully_done_with_selected_forms() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAssociationForms();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Discussion Association should be successfully done with selected discussions$")
	public void discussion_association_should_be_successfully_done_with_selected_discussions() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAssociationDiscussions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Attached documents should be uploaded AND available in comment$")
	public void attached_documents_should_be_uploaded_AND_available_in_comment() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAttachments();
		} else
			Assume.assumeTrue(true);

	}

	@Given("^I have Search \"(.*?)\"$")
	public void i_have_Search(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchCreatedComment();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Comment Should open sucessfully AND I mouse hover association clip icon And I click \"(.*?)\"$")
	public void comment_Should_open_sucessfully_AND_I_mouse_hover_association_clip_icon_And_I_click(String arg1)
			throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			scripts.mouseHoverAndClickAssociationLink();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Popup should open$")
	public void Popup_should_open(String header) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyPopupHeader(header);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I goto \"(.*?)\" link I select all files AND click on Download$")
	public void i_goto_link_I_select_all_files_AND_click_on_Download(String linkTabText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFilesAndClickOnDownload(linkTabText);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select all checkboxes AND click on download button of Download popup for \"(.*?)\"$")
	public void i_select_all_checkboxes_AND_click_on_download_button_of_Download_popup_for(String linkTab)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectCheckListAndClickOnDownload(linkTab);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Batch file should be created And \"(.*?)\" Zip file should be downloaded into Local Directory$")
	public void batch_file_should_be_created_And_Zip_file_should_be_downloaded_into_Local_Directory(String linkTab)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.downloadZipDocumentIntoLocal(linkTab);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Unzip downloaded \"(.*?)\" zip file$")
	public void i_Unzip_downloaded_zip_file(String linkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.zipIntoUnZip(linkTab);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^all \"(.*?)\" should be available into Local Directory$")
	public void all_should_be_available_into_Local_Directory(String linkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.getFileNamesFromLocalFolder(linkTab);
			scripts.verifyFilesNameIntoSystem(linkTab);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I login using secondary user into application$")
	public void i_login_using_secondary_user_into_application() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.loginWithSecondaryUser();
		else
			Assume.assumeTrue(true);
	}

	@Then("^File should be available on listing with assigned action$")
	public void file_should_be_available_on_listing_with_assigned_action() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionIsAssigned();
		else
			Assume.assumeTrue(true);
	}

}