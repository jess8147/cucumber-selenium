package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateLinkDocument;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateLinkDocument {

	CreateLinkDocument scripts = new CreateLinkDocument(); 
			
	@Given("^I am on Files tab$")
	public void i_am_on_Files_tab() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
		scripts.navigateToFilesTab();
		}
		else 
  			Assume.assumeTrue(true);
	}

	@Given("^I have atleast two folders for \"(.*?)\" link in project$")
	public void i_have_atleast_two_folders_in_project(String linkType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
		scripts.verifyFolderCountInProject(linkType);
		}
		else 
			Assume.assumeTrue(true);
		
	}

	@Given("^I have \"(.*?)\" link document in document listing to link$")
	public void i_have_link_document_in_Files_listing(String docName) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
		scripts.verifyFilesCountOnFilesListing(docName);
		}
		else 
			Assume.assumeTrue(true);
	}

	@When("^I select any document to link$")
	public void i_select_any_document() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.selectLinkDocument();
			}
			else 
				Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" from more options$")
	public void click_on_from_more_options(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.selectLinkOptionFromMoreOption();
			}
			else 
				Assume.assumeTrue(true);
	}

	
	@When("^I select \"(.*?)\" link destination Folder And click on \"(.*?)\" button$")
	public void i_select_destination_Folder_And_click_on_button(String linkType, String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
		scripts.selectDestinationFolderAndClickSubmitButton(linkType, buttonText);
		}
		else 
			Assume.assumeTrue(true);
	}

	@When("^I select User in \"(.*?)\" field And select \"(.*?)\" type And Click on \"(.*?)\" button$")
	public void i_select_User_in_field_And_Click_on_button(String arg1, String linkType, String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
		scripts.selectUserAndClickButton(buttonText, linkType);
		}
		else 
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Link document should be available in destination folder$")
	public void link_document_should_be_available_in_destination_folder(String linkType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
		scripts.verifyLinkDocumentIsAvailableInTargetFolder(linkType);
		}
		else 
			Assume.assumeTrue(true);
	}

	@Then("^For Information action should assign to selected User$")
	public void for_Information_action_should_assign_to_selected_User() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
		scripts.verifyActionAssignedToSelectedUser();
		}
		else 
			Assume.assumeTrue(true);
	}

	@When("^I upload revision of document$")
	public void i_upload_revision_of_document_in_folder() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.uploadRevision();
			}
			else 
				Assume.assumeTrue(true);
	}

	@Then("^Revision for document should get reflected in linked folder$")
	public void revision_for_document_should_get_reflected_in_linked_folder() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.verifyUploadedRevision();
			}
			else 
				Assume.assumeTrue(true);
	}

	@Then("^I deactivate test folder$")
	public void i_deactivate_test_folder() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.deactivateTestDataFolder();
			}
			else 
				Assume.assumeTrue(true);
	}
	
	@Then("^Secondary file attachment should get updated$")
	public void secondary_file_attachment_should_get_updated() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifySecondaryFileAttachment();
		else
			Assume.assumeTrue(true);
	}
	
	
}
