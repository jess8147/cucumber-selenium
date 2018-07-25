package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.MoveFilesClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_MoveFiles {

	MoveFilesClassicScripts scripts = new MoveFilesClassicScripts();

	@When("^I get document list for \"(.*?)\"$")
	public void i_get_document_list_for(String destinationFolder) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.getDocumentCountDetails(destinationFolder);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select multiple files from document listing$")
	public void i_select_multiple_files_from_document_listing() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.selectMultipleFilesOnDocListing();
		else
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" on documents dropdown options$")
	public void click_on_on_documents_dropdown_options(String arg1) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnDocumentDropdownOptions();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Move Files confirmation page should open$")
	public void move_files_confirmatrion_page_should_open() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyMoveFileConfirmation();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Selected documents should be moved to \"(.*?)\"$")
	public void all_Selected_documents_should_be_moved_to(String destinationFolder) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.verifyDocumentsMovedSuccess(destinationFolder);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I goto Audit History and select \"(.*?)\" And verify move files path$")
	public void i_goto_Audit_History_and_select_And_verify_move_files_path(String distributionHeaderLink) {
		if (CTests_CommonStepDefinitions.runTest) {
			scripts.gotoAuditHistoryAndClickOnAccessLink(distributionHeaderLink);
			scripts.verifyMoveFilesPathIntoHistory();
			scripts.afterFilesMoveDateAndTime();
		} else
			Assume.assumeTrue(true);
	}

}