package org.asite.automation.classic.p1.stepdefinitions;
import org.asite.automation.classic.p1.scripts.LinkDocumentClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CTests_LinkDocument {
	
	LinkDocumentClassicScripts scripts = new LinkDocumentClassicScripts();
	
	@When("^I click on \"(.*?)\" Folder for \"(.*?)\" link$")
	public void i_click_on_any_Folder(String folderTitle, String lType) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickTestDataFolder(folderTitle, lType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" file$")
	public void i_select_multiple_files(String linkTestFile) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectFileToLink(linkTestFile);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" link type from dropdown$")
	public void i_select_link_type_from_dropdown(String linkType) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectLinkType(linkType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^click on \"(.*?)\" from document dropdown options$")
	public void click_on_from_document_dropdown_options(String arg1) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectLinkDocument();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select destination Folder$")
	public void i_select_destination_Folder() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectDestinationFolder();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Create Link button$")
	public void i_click_on_create_link_button() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickCreateLinkButton();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on any \"(.*?)\" button$")
	public void click_on_continue_button(String continueBtn) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickContinueButton();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" company and \"(.*?)\" user from Select Individuals list$")
	public void i_select_user_from_Select_Individuals_list(String company, String user) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectUserFromList(company, user);
		else
			Assume.assumeTrue(true);
	}


	@Then("^selected user should be added into list$")
	public void selected_user_should_be_added_into_list() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyUserIsAddedToDistList();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" action And Current Date for selected user$")
	public void i_select_action_And_date_for_selected_user(String action) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectActionAndDate(action);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Link should be successfully created$")
	public void link_should_be_successfully_created() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyOperationSuccess();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Link document should be available in destination folder$")
	public void link_document_should_be_available_in_destination_folder() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentIsLinked();
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected action should assign to selected User$")
	public void selected_action_should_assign_to_selected_User() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyValidActionIsAssigned();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I upload revision of \"(.*?)\" in \"(.*?)\" folder$")
	public void i_upload_revision_of_in_folder(String revisionFile, String folderTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.uploadRevision(revisionFile, folderTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Revision for \"(.*?)\" should get reflected in linked folder$")
	public void revision_for_should_get_reflected_in_linked_folder(String revisedFile) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyRevisionInLinkedFolder(revisedFile);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I deactivate the testdata folder$")
	public void i_deactivate_the_testdata_folder() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.deactivateTestdataFolder();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" window page should open for Link Document$")
	public void window_page_should_open(String windowTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyPageTitle(windowTitle);
		else
			Assume.assumeTrue(true);
	}
}
