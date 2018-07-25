package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DistributeFilesViaCopyingVersion;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DistributeFilesViaCopyingVersion {

	DistributeFilesViaCopyingVersion scripts = new DistributeFilesViaCopyingVersion();

	@Then("^I click on \"(.*?)\" button AND I have selected multiple file from Local$")
	public void i_click_on_button_AND_I_have_selected_multiple_file_from_Local(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMultipleFileFromLocal();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter all mandatory attributes for all files successfully$")
	public void i_have_enter_all_mandatory_attributes_for_all_files_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.enterMandatoryFields();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All files should uploaded successfully AND Available in file listing$")
	public void all_files_should_uploaded_successfully_AND_Available_in_file_listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateFileInDocumentListing();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I have Search files with name as \"(.*?)\"$")
	public void i_have_Search_files_with_name_as(String file) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchFileInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All files with docRef as \"(.*?)\" should Available in listing$")
	public void all_files_with_docRef_as_should_Available_in_listing(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchFileInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Selected multiple files AND I Context click on doc title$")
	public void i_have_Selected_multiple_files_AND_I_Context_click_on_doc_title() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMultipleFileInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Distributed files to users \"(.*?)\" \"(.*?)\" AND \"(.*?)\" with different actions AND Due Date$")
	public void i_have_Distributed_files_to_users_AND_with_different_actions_AND_Due_Date(String user1, String user2,
			String user3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.distributeUsersAndAction(user1, user2, user3);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^all selected files should distributed successfully to respective users$")
	public void all_selected_files_should_distributed_successfully_to_respective_users() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifiedSuccessMessage();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have selected different file AND I Context click on doc title$")
	public void i_have_selected_different_file_AND_I_Context_click_on_doc_title() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectfileAndContextClick();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have selected users as \"(.*?)\" AND \"(.*?)\" with different actions AND Due Date$")
	public void i_have_selected_users_as_AND_with_different_actions_AND_Due_Date(String user1, String user2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.distributeUsersAndAction(user1, user2, null);
			;
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button AND I have selected uploaded file revisions from Local$")
	public void i_click_on_button_AND_I_have_selected_uploaded_file_revisions_from_Local(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.uploadFileRevisions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Users as \"(.*?)\" \"(.*?)\" AND \"(.*?)\" with respective actions AND Due Date should copied successfully$")
	public void users_as_AND_with_respective_actions_AND_Due_Date_should_copied_successfully(String arg1, String arg2,
			String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateCopiedUserAndAction();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Users \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" AND \"(.*?)\" with respective actions AND Due Date should copied from previous version successfully$")
	public void users_AND_with_respective_actions_AND_Due_Date_should_copied_from_previous_version_successfully(
			String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateCopiedUserAndAction();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I have Search file with title as \"(.*?)\"$")
	public void i_have_Search_file_with_title_as(String fileName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchFileWithTitle(fileName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click icon for copying user from previous revision on Popup \"(.*?)\"$")
	public void i_click_icon_for_copying_user_from_previous_revision_on_Popup(String popUpText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickIconPreviousRevision(popUpText);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^User \"(.*?)\" with action \"(.*?)\" should copied sucessfully$")
	public void user_with_action_should_copied_sucessfully(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateCopiedUserAndAction();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^User \"(.*?)\" \"(.*?)\" AND ([^\"]*) with respective action AND Time Duration should copied successfully$")
	public void user_AND_Automation_UK_with_respective_action_AND_Time_Duration_should_copied_successfully(String arg1,
			String arg2, String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateCopiedUserAndAction();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I enter all mandatory attributes for all revisions sucessfully$")
	public void i_enter_all_mandatory_attributes_for_all_revisions_sucessfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.enterMandatoryAttributes();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All Revisions of file \"(.*?)\" should uploaded successfully$")
	public void all_Revisions_of_file_should_uploaded_successfully(String agr1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateFileRevisionsInListing();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All latest Revisions of \"(.*?)\" should Available in listing$")
	public void all_latest_Revisions_of_should_Available_in_listing(String file) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateUpdateFileRevisions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I Select All file revisions AND I Context click on doc title$")
	public void i_Select_All_file_revisions_AND_I_Context_click_on_doc_title() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectAllRevisionsAndContextClick();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select context click menu option \"(.*?)\" AND \"(.*?)\"$")
	public void i_select_context_click_menu_option_AND(String option1, String option2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickContextClickMenuOptions(option1);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^User \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" AND ([^\"]*) with actions AND Time Duration should copied successfully$")
	public void user_AND_Automation_UK_with_actions_AND_Time_Duration_should_copied_successfully(String arg1,
			String arg2, String arg3, String arg4, String arg5) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateCopiedUserAndAction();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All Revisions of file \"(.*?)\" should distributed successfully to respective user$")
	public void all_Revisions_of_file_should_distributed_successfully_to_respective_user(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifiedSuccessMessage();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Search \"(.*?)\" file AND Go to Audit Trail AND Validated due date for actions successfully$")
	public void i_have_Search_file_AND_Go_to_Audit_Trail_AND_Validated_due_date_for_actions_sucessfully(String file)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateFileActionsInAuditTrail();
			scripts.validateUserDueDateAndActions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Deactivated All files name with title \"(.*?)\"$")
	public void i_have_Deactivated_All_files_name_with_title(String fileName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.deactivateFile(fileName);

		} else
			Assume.assumeTrue(true);
	}

}
