package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.EditDocumentAttributesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_EditDocumentAttributes {

		EditDocumentAttributesScripts editDocAttribScripts = new EditDocumentAttributesScripts();
		
		@Given("^I have created new test folder \"(.*?)\" for ([^\"]*)$")
		public void i_have_created_new_test_folder(String folder, String project) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.createTestDataFolder(folder, project);
			else
				Assume.assumeTrue(true);
		}
	
		@When("^I upload \"(.*?)\" documents in \"(.*?)\" folder$")
		public void i_upload_documents_in_folder(String count, String folderType) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.uploadFilesInFolder(count, folderType);
			else
				Assume.assumeTrue(true);
		}
		
		@When("^I search all the documents uploaded at project level$")
		public void i_search_all_the_documents_uploaded_at_project_level() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.searchDocuments();
			else
				Assume.assumeTrue(true);
		}
		
		@Then("^\"(.*?)\" documents should be displayed on listing$")
		public void all_document_should_be_displayed_on_listing(String docCount) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.verifyDocumentsCount(docCount);
			else
				Assume.assumeTrue(true);
		}
		
		@Given("^I have focus on new test folder$")
		public void i_have_focus_on_newly_created_folder() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.clickOnTestDataFolder();
			else
				Assume.assumeTrue(true);
		}
		
		@When("^I select all document and right click on them$")
		public void i_select_all_document_and_right_click_on_them() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.selectDocumentAndContextClick();
			else
				Assume.assumeTrue(true);
		}
		
		@When("^I click on \"(.*?)\" option in context options$")
		public void i_click_on_option_in_context_options(String arg1) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.selectEditAttributesOption();
			else
				Assume.assumeTrue(true);
		}
		
		@When("^I edit attributes of uploaded custom attributes specific files$")
		public void i_edit_attributes_of_uploaded_custom_attributes_specific_files() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.editCustomAttributesAndGetUpdatedData();
			else
				Assume.assumeTrue(true);
		}
		
		@When("^I edit attributes of uploaded simple attributes specific files$")
		public void i_edit_attributes_of_uploaded_simple_attributes_specific_files() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.editSimpleAttributes();
			else
				Assume.assumeTrue(true);		
		}
		
		@Then("^DocRef and custom attributes of custom attributes specific files should be edited$")
		public void docref_and_custom_attributes_of_custom_attributes_specific_files_should_be_edited() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.verifyDocRefsForCustomAttributeFiles();
			else
				Assume.assumeTrue(true);		
		}
		
		@Then("^Attributes of simple attributes specific files should be edited$")
		public void attributes_of_simple_attributes_specific_files_should_be_edited() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.verifySimpleAttributeEdit();
			else
				Assume.assumeTrue(true);
		}
		
		@Given("^I deactivate the edit attributes folder for ([^\"]*)$")
		public void i_deactivate_the_edit_attributes_folder(String project) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.deactivateEditAttributeFolder(project);
			else
				Assume.assumeTrue(true);
		}
		
		@Given("^\"(.*?)\" privilege is available for ([^\"]*) on ([^\"]*)$")
		public void privilege_is_available_for_user(String privilege, String userRole, String project) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.verifyUserPrivilegeIsAvailable(privilege, userRole, project);
			else
				Assume.assumeTrue(true);
		}
		
		@When("^I \"(.*?)\" checkbox for \"(.*?)\" privilege and save$")
		public void i_uncheck_privilege_for_user(String flag, String privilege) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.uncheckPrivilegeAndSave(flag);
			else
				Assume.assumeTrue(true);
		}
		
		
		@Given("^I open edit folder dialogue for created folder$")
		public void i_open_edit_folder_dialogue_for_created_folder() throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.openEditFolderDialogue();
			else
				Assume.assumeTrue(true);
		}

		@Then("^User should be able to edit (\\d+) documents$")
		public void user_should_be_able_to_edit_only_documents(int documentCount) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.verifyEditAttributeDocuments(documentCount);
			else
				Assume.assumeTrue(true);
		}
		
		@Then("^Document counter should change for \"(.*?)\" folder \"(.*?)\"$")
		public void document_counter_should_change_for_folder(String type, String flag) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				editDocAttribScripts.verifyAttributesInFolders(type, flag);
			else
				Assume.assumeTrue(true);
		}
}
