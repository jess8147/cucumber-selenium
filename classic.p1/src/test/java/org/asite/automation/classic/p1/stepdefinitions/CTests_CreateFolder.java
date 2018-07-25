package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.CreateFolderClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_CreateFolder
{
	CreateFolderClassicScripts classicScripts = new CreateFolderClassicScripts();
	
	@When("^I click \"(.*?)\"$")
	public void i_click(String wsDocs) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.clickOnWorkspaceDocuments(wsDocs);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on \"(.*?)\" Listing$")
	public void i_should_redirect_on_Document_Listing(String folderPath) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.verifyDocumentListing(folderPath);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\" img icon$")
	public void i_Click_on_img_icon(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.clickOnCreateNewParentFolder();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" page should be open$")
	public void page_should_be_open(String pageTitle)
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.verifyFolderPage(pageTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Enter FolderName AND I Click on \"(.*?)\" button$")
	public void i_Enter_FolderName_AND_I_Click_on_button(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.enterFolderNameAndCreateFolder();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Folder should be created AND Folder should be available in Folder Tree$")
	public void folder_should_be_created_AND_Folder_should_be_available_in_Folder_Tree() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.verifyCreatedFolderIntoFolderTree();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Click on created Folder AND Click on \"(.*?)\" from Folder dropdown options$")
	public void i_Click_on_created_Folder_AND_Click_on_from_Folder_dropdown_options(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.clickOnCreatedFolderAndEditFolder();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Change Folder Name AND Click on \"(.*?)\" button$")
	public void i_Change_Folder_Name_AND_Click_on_button(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.enterEditFolderNameAndUpdateChanges();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Updated Folder should be available in Folder Tree$")
	public void updated_Folder_should_be_available_in_Folder_Tree() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.verifyUpdatedFolderIntoFolderTree();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Click on Updated Folder AND Click on \"(.*?)\" from Folder dropdown options$")
	public void i_Click_on_Updated_Folder_AND_Click_on_from_Folder_dropdown_options(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.clickOnUpdatedFolderAndEditFolder();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" AND Click on \"(.*?)\" button$")
	public void i_select_AND_Click_on_button(String arg1, String arg2) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.selectDeactivateCheckboxAndUpdateChanges();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Confirmation page should open$")
	public void confirmation_page_should_open(String pageTitle) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.verifyDeactivateFolderPage(pageTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Continue button$")
	public void i_click_on_Continue_button()
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.clickOnContinue();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Updated Folder should be deactivated AND not be available in Folder Tree$")
	public void updated_Folder_should_be_deactivated_AND_not_be_available_in_Folder_Tree() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
			classicScripts.verifyFolderDeactivated();
		else
			Assume.assumeTrue(true);
	}
}
