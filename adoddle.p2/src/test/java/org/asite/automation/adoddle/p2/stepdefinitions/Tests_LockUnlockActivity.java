package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.LockUnlockActivity;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_LockUnlockActivity {

	LockUnlockActivity scripts = new LockUnlockActivity();

	@Then("^I Right Click on created directory \"(.*?)\"$")
	public void i_Right_Click_on_created_directory(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.contextClickParentDirectory();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I Enter directoryName as \"(.*?)\" AND I Reset Folder Permissions$")
	public void i_Enter_directoryName_as_AND_I_Reset_Folder_Permissions(String subfolderName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createSubDirectoryAndRestFolderPermission(subfolderName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Click on Create button$")
	public void i_Click_on_Create_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickCreateButton();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should be created successfully AND available in Folder Tree$")
	public void should_be_created_successfully_AND_available_in_Folder_Tree(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.valdiateFolderInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on directory name as \"(.*?)\"$")
	public void i_have_focus_on_directory_name_as(String folderName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.focusFolderInListing(folderName);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^multiple document\\(s\\) should available in directory$")
	public void multiple_document_s_should_available_in_directory() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateDocumentsInDirectory();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have published multiple Documents from Local in directory \"(.*?)\" successfully$")
	public void i_have_published_multiple_Documents_from_Local_in_directory_successfully(String folderName)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.publishMultipleDocumentsInDirectories(folderName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I have search file\\(s\\) having docRefs suffix as \"(.*?)\"$")
	public void i_have_search_file_s_having_docRefs_suffix_as(String docSuffix) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchDocumentsWithEpochs(docSuffix);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Locked Activity \"(.*?)\" successfully$")
	public void i_have_Locked_Activity_successfully(String activityName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.lockedMultipleBatchActivity(activityName);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated Locked Activity on multiple Documents successfully$")
	public void i_have_validated_Locked_Activity_on_multiple_Documents_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateLockedActivitiesOnDocuments();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^multiple files\\(s\\) having docRefs suffix as \"(.*?)\" should available in document listing$")
	public void multiple_files_s_having_docRefs_suffix_as_should_available_in_document_listing(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateDocumentsInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have Selected all documents AND I Context click$")
	public void i_have_Selected_all_documents_AND_I_Context_click() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMultipleDocumentsAndContextClick();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Selected documents having docRefs as \"(.*?)\" AND \"(.*?)\" AND I Context Click$")
	public void i_have_Selected_documents_having_docRefs_as_AND_AND_I_Context_Click(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectDocumentsAndContextClick();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I have published Documents Revisions from Local in folder \"(.*?)\"$")
	public void i_have_published_Documents_Revisions_from_Local_in_folder_AND(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.publishMultipleDocumentsRevisions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I validated restricted files AND webPage Message as \"(.*?)\"$")
	public void i_validated_restricted_files_AND_webPage_Message_as(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateRestrictedFileListAndWebPageAlert();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Performed \"(.*?)\" action on all document successfully$")
	public void i_have_Performed_action_on_all_document_successfully(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.performFileAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click menu option as \"(.*?)\"$")
	public void i_click_menu_option_as(String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickContextClickMenuOption(option);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Pop up should open$")
	public void pop_up_should_open(String headerText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validatePopElementMessage(headerText);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I click button as \"(.*?)\" AND then \"(.*?)\"$")
	public void i_click_button_as_AND_then(String btntext, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.lockBatchDocuments(btntext);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated Locked Activities on multiple Documents successfully$")
	public void i_have_validated_Locked_Activities_on_multiple_Documents_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateLockedActivitiesOnDocuments();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Selected multiple locked AND Unlocked Documents AND I Context click$")
	public void i_have_Selected_multiple_locked_AND_Unlocked_Documents_AND_I_Context_click() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMultipleDocumentAndContextClick();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select context click menu options as \"(.*?)\" AND \"(.*?)\"$")
	public void i_select_context_click_menu_options_as_AND(String option1, String option2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.contextClickMenuOptions(option1, option2);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should Open$")
	public void popup_should_Open(String headertxt) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validatePopElementMessage(headertxt);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated all locked file\\(s\\) should filtered successfully scenario \"(.*?)\"$")
	public void i_have_validated_all_locked_file_s_should_filtered_successfully_scenario(String scenario)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateLockedAndUnlockedDocuments(scenario);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Distributed Unlocked files with action \"(.*?)\" to user \"(.*?)\"$")
	public void i_have_Distributed_all_files_with_action_to_user(String arg1, String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.distributeUnlockedDocuments(user);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have successfully validated webPage message as \"(.*?)\"$")
	public void i_have_successfully_validated_webPage_message_as(String msgTxt) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateWebPageMessage(msgTxt);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated \"(.*?)\" published documents list successfully AND I click on continue button$")
	public void i_have_validated_published_documents_list_successfully_AND_I_click_on_continue_button(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateSubDirectoryDocumentListAndClickContinue();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All Published Document of folder \"(.*?)\" should UnLocked successfully$")
	public void all_Published_Document_of_folder_should_UnLocked_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateUnLockedDocumentsInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^multiple files\\(s\\) having docRefs suffix as \"(.*?)\" should available with completed action as \"(.*?)\"$")
	public void multiple_files_s_having_docRefs_suffix_as_should_available_with_completed_action_as(String arg1,
			String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateMultipleDocumentsAndAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Reset filter to Default folder Level search$")
	public void i_have_Reset_filter_to_Default_folder_Level_search() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.resetFilterSearch();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Updated Status for Unlocked files to \"(.*?)\" successfully$")
	public void i_have_Updated_Status_for_Unlocked_files_to_successfully(String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.updateMultiDocumentsStatus(status);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have created comment with title as \"(.*?)\" AND I have distributed to user \"(.*?)\"$")
	public void i_have_created_comment_with_title_as_AND_I_have_distributed_to_user(String ctitle, String user)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createCommentAndDistribute(ctitle, user);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Selected Documents Revisions from Local in folder \"(.*?)\"$")
	public void i_have_Selected_Documents_Revisions_from_Local_in_folder(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.publishMultipleDocumentsRevisions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated all locked file\\(s\\) for activity \"(.*?)\" should filtered successfully$")
	public void i_have_validated_all_locked_file_s_for_activity_should_filtered_successfully(String scenario)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validatedWebFileFilteredList(scenario);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have published AND validated Documents Revisions from Local in folder \"(.*?)\"$")
	public void i_have_published_AND_validated_Documents_Revisions_from_Local_in_folder(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.publishedAndValidateDocumentRevisions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have edited AND validated poi as \"(.*?)\" AND private flag as \"(.*?)\" successfully$")
	public void i_have_edited_AND_validated_poi_as_AND_private_flag_as_successfully(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.editMultipleFileAttributes();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have search AND Distribute multiple\\(s\\) documents with different action to user \"(.*?)\" successfully$")
	public void i_have_search_AND_Distribute_multiple_s_documents_with_different_action_to_user_successfully(
			String docSuffix) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchDocumentsAndDistribute();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have locked multiple\\(s\\) documents with different activities successfully$")
	public void i_have_locked_multiple_s_documents_with_different_activities_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchDocumentsAndLockedActivity();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I deactivate lock unlock activity folder$")
	public void i_deactivate_lock_unlock_activity_folder() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.deactivateLockUnlockActivityFolder();
		} else
			Assume.assumeTrue(true);
	}
}
