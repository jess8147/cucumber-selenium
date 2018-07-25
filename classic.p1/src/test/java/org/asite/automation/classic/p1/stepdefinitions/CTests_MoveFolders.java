package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.MoveFoldersClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_MoveFolders {

	MoveFoldersClassicScripts scripts = new MoveFoldersClassicScripts();

	@When("^I have validated folders \"(.*?)\" AND \"(.*?)\" should available in Folder Listing$")
	public void i_have_validated_folders_AND_should_available_in_Folder_Listing(String folder1, String folder2)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.validateTestFoldersInListing(folder1, folder2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" from folders dropdown options for \"(.*?)\"$")
	public void i_click_on_from_folders_dropdown_options(String moveFolder, String folderTitle) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnMoveFolderOption(folderTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" Folder in \"(.*?)\" tree$")
	public void i_select_Folder_in_tree(String folderTitle, String selectDestinationLabel) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.clickFolderHavingTitle(folderTitle);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on move folders \"(.*?)\" button$")
	public void i_click_on_move_folders_continue_button(String continueBtnText) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.clickContinueButton(continueBtnText);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on move files \"(.*?)\" button$")
	public void i_click_on_move_files_continue_button(String continueBtnText) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.clickMoveFilesContinueButton(continueBtnText);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Move child folders confirmation page should open$")
	public void move_child_folders_confirmation_page_should_open() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyMoveFolderConfirmation();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" folder should be moved into child \"(.*?)\" with its child folder and all documents$")
	public void folder_should_be_moved_into_child_with_its_child_folder_and_all_documents(String folder1, String folder2)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyFoldersMovedChildSuccess(folder1, folder2);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I goto Audit History and select \"(.*?)\" link And verify move folders path$")
	public void i_goto_Audit_History_and_select_link_And_verify_move_folders_path(String distributionHeaderLink) {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.gotoAuditHistoryAndClickOnAccessLink();
			scripts.verifyMoveFolderPathIntoHistory();
			scripts.afterFolderMoveDateAndTime();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" folder should be moved into parent \"(.*?)\" with its child folder and all documents$")
	public void folder_should_be_moved_into_parent_with_its_child_folder_and_all_documents(String folder1,
			String folder2) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyFoldersMovedParentSuccess(folder1, folder2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" on LH folder tree$")
	public void i_click_on_folder_with_title(String folderTitle) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickFolderWithTitleOnLHTree(folderTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^In Document listing \"(.*?)\" folder should be open$")
	public void in_Document_listing_folder_should_be_open(String folderTitle) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.verifyDocListingPageTitle(folderTitle);
		} else
			Assume.assumeTrue(true);
	}

	@When("I click on OK confirmation button")
	public void i_click_on_OK_confirmation_button() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.clickOkConfirmationButton();
		} else
			Assume.assumeTrue(true);
	}
}