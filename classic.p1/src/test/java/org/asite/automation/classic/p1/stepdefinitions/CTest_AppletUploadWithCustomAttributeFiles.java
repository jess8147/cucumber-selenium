package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.AppletUploadWithCustomAttributeFilesScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTest_AppletUploadWithCustomAttributeFiles
{
	AppletUploadWithCustomAttributeFilesScripts classicScripts = new AppletUploadWithCustomAttributeFilesScripts();
	
	@Given("^I have focus on \"(.*?)\" folder in ([^\"]*) Project$")
	public void i_have_focus_on_folder_in_Project(String folderName, String arg2) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.clickOnCustomAttributes(folderName);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Public Standard Dcoument$")
	public void i_click_on_Public_Standard_Dcoument() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.clickOnPublicStandardDocument();
			classicScripts.switchToPublishWindow();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should be opened$")
	public void page_should_be_opened(String pageTitle) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.verifyPublishDocumentPage(pageTitle);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Click here to use the Advanced Upload$")
	public void i_click_on_Click_here_to_use_the_Advanced_Upload() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.clickOnAppletUploadLink();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Applet upload popup should open$")
	public void applet_upload_popup_should_open() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Add Files button on Applet upload popup$")
	public void i_click_on_Add_Files_button_on_Applet_upload_popup() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.performAppletUpload();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Local file system popup should open$")
	public void local_file_system_popup_should_open() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I have selected multiple files from local system AND I have clicked on Enter Document Details button$")
	public void i_have_selected_multiple_files_from_local_system_AND_I_have_clicked_on_Enter_Document_Details_button() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attributes should be load for all uploading documents$")
	public void attributes_should_be_load_for_all_uploading_documents() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Copy FileName button of Applet Upload$")
	public void i_click_on_Copy_FileName_button() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^all documents File Name Without extension should be copied in Doc Title$")
	public void all_documents_File_Name_Without_extension_should_be_copied_in_Doc_Title() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter <Values> into header for <Attributes> AND Select Overwrite$")
	public void i_enter_Values_into_header_for_Attributes_AND_Select_Overwrite() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^click on Apply To All button of Applet Upload$")
	public void click_on_Apply_To_All_button() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^all attributes values should be filled with Values AND \"(.*?)\" should be filled with combine custom attributes$")
	public void all_attributes_values_should_be_filled_with_Values_AND_should_be_filled_with_combine_custom_attributes(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" in Distribute dropdown of Applet Upload$")
	public void i_select_in_Distribute_dropdown(String arg1) throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^click on Start Upload button$")
	public void click_on_Start_Upload_button() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.logInfo();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Uploaded document should be available in document listing$")
	public void uploaded_document_should_be_available_in_document_listing() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			classicScripts.verifyUploadedDocumentIntoDocListing();
		}
		else
			Assume.assumeTrue(true);
	}
}
