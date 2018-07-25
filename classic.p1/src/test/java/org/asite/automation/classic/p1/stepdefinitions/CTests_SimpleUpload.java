package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.SimpleUploadClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_SimpleUpload
{
	SimpleUploadClassicScripts scripts = new SimpleUploadClassicScripts();
	
	@When("^I select \"(.*?)\" from ADMIN dropdown list$")
	public void i_select_from_ADMIN_dropdown_list(String adminDropdownList) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectEditWorkspace(adminDropdownList);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have selected \"(.*?)\" from \"(.*?)\" table$")
	public void i_have_selected_from_table(String arg1, String arg2) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			scripts.selectEnableSimpleUploadCheckbox();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" folder into \"(.*?)\" Listing$")
	public void i_select_folder_into_Listing(String folderName, String arg2) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnFolder(folderName);
		else
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" img$")
	public void click_on_img(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnPublishStandardDocument();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Start Upload button for Simple upload$")
	public void i_click_on_Start_Upload_button() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnStartUpload();
		else
			Assume.assumeTrue(true);
	}


	@Then("^All Documents should be uploaded successfully AND I should redirected on Document listing page$")
	public void all_Documents_should_be_uploaded_successfully_AND_I_should_redirected_on_Document_listing_page() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentUploadSuccess();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Uploaded documents should be available into document listing page$")
	public void uploaded_documents_should_be_available_into_document_listing_page() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentIsAvailable();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I de-selected \"(.*?)\" from \"(.*?)\" table$")
	public void i_de_selected_from_table(String arg1, String arg2) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			scripts.revertSimpleUploadChanges();
		else
			Assume.assumeTrue(true);
	}
}
