package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreatePlaceholdersScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreatePlaceholders
{
	CreatePlaceholdersScripts scripts = new CreatePlaceholdersScripts();
	
	@Given("^I drag mouse on \"(.*?)\" and \"(.*?)\" count of Dashboard$")
	public void i_drag_mouse_on_and_count_of_Dashboard(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getDashboardIncompleteAndDueTodayActionsCount();
		else
			Assume.assumeTrue(true);
	}

	@When("^I drag mouse on \"(.*?)\" and \"(.*?)\" count of Files tab$")
	public void i_drag_mouse_on_and_count_of_Files_tab(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFilesIncompleteAndDueTodayActionsCount();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have focus on ([^\"]*) projects \"(.*?)\" folder$")
	public void i_have_focus_on_folder(String project, String folderName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.focusOnFolder(project, folderName);
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on \"(.*?)\" folder And drag mouse to \"(.*?)\" And click on \"(.*?)\" into context menu options$")
	public void i_right_click_on_folder_And_drag_mouse_to_And_click_on_into_context_menu_options(String folderName, String newContextClickOption, String arg3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickOnPlaceholder(folderName, newContextClickOption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should open for publishing Placeholders$")
	public void popup_should_open_for_publishing_Placeholders(String popupText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatePlaceholderPopup(popupText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Distribution User for Distribute \"(.*?)\" And \"(.*?)\" action With Current Date$")
	public void i_select_Distribution_User_for_Distribute_And_action_With_Current_Date(String filePlaceholder, String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterDistributeUserField(filePlaceholder, actionType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" And I entered all mendatory attributes for Creating both Placeholders$")
	public void i_click_on_And_I_entered_all_mendatory_attributes_for_Creating_both_Placeholders(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterPlaceholderMendatoryAttributes();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get all attributes values of first Placeholder$")
	public void i_get_all_attributes_values_of_first_Placeholder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFirstPlaceholderAttributesValue();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button for for Creating Placeholder$")
	public void i_click_on_button_for_for_Creating_Placeholder(String assignPlaceholderAction) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickLinkWithText(assignPlaceholderAction);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Placeholders Created Successfully and should be listed on Files listing page And \"(.*?)\" action should be displayed AND all attributes of Placeholders should be listed on Created Placeholders$")
	public void placeholders_Created_Successfully_and_should_be_listed_on_Files_listing_page_And_action_should_be_displayed_AND_all_attributes_of_Placeholders_should_be_listed_on_Created_Placeholders(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCreatedPlaceholderAndAction(actionType);
			scripts.verifyCreatedFirstPlaceholderAttributesValue();
			scripts.verifyPlaceholderAttributes();
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^Total number of \"(.*?)\" and \"(.*?)\" count of Dashboard should be increased$")
	public void total_number_of_and_count_of_Dashboard_should_be_increased(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardIncompleteActionsCountIncreased();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Total number of \"(.*?)\" and \"(.*?)\" count of Files tab should be increased$")
	public void total_number_of_and_count_of_Files_tab_should_be_increased(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilesIncompleteActionsCountIncreased();
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on \"(.*?)\" folder And drag mouse to \"(.*?)\" of context click$")
	public void i_right_click_on_folder_And_drag_mouse_to_of_context_click(String nonPlaceholderFolder, String newContextClickOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickOnNonPlaceHolder(nonPlaceholderFolder, newContextClickOption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^create \"(.*?)\" option should be \"(.*?)\" on context menu options$")
	public void create_option_should_be_on_context_menu_options(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyNonPlaceHolderDisabled();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I search Created First Placeholder and click on \"(.*?)\" action type$")
	public void i_search_Created_First_Placeholder_and_click_on_action_type(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchFirstPlaceholderAndClickOnAction();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Only Status dropdown value should be blanked$")
	public void only_Status_dropdown_value_should_be_blanked() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyStatusBlankValue();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^All Attributes should be match to previous created Placeholder$")
	public void all_Attributes_should_be_match_to_previous_created_Placeholder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPlaceholderAttributesForFileUpload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Status into Status dropdown list$")
	public void i_select_Status_into_Status_dropdown_list() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectStatusForFileUpload();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I attach external file in created Placeholder$")
	public void i_attach_external_file_in_created_Placeholder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.attachExternalFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^after click on attachment attached file should viewed in viewer$")
	public void after_click_on_attachment_attached_file_should_viewed_in_viewer() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAttachmentInListingAndViewer();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get all attributes values of File$")
	public void i_get_all_attributes_values_of_File() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFileAttributesValue();
		else
			Assume.assumeTrue(true);
	}

	@Then("^File should be uploaded successfully into Created Placeholder And \"(.*?)\" action should be completed$")
	public void file_should_be_uploaded_successfully_into_Created_Placeholder_And_action_should_be_completed(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedFileAndActionCompleted();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^All Attributes should be listed on Files tab listing page$")
	public void all_Attributes_should_be_listed_on_Files_tab_listing_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCustomAttributesOnFileListingPage();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Attributes of Placeholder should be set into Uploaded file And \"(.*?)\" value should be changed And File Name should be set$")
	public void all_Attributes_of_Placeholder_should_be_set_into_Uploaded_file_And_value_should_be_changed_And_File_Name_should_be_set(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUploadedFileAndAttributesValue();
			scripts.verifyFileAttributes();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Total number of \"(.*?)\" and \"(.*?)\" count of Dashboard should be decreased$")
	public void total_number_of_and_count_of_Dashboard_should_be_decreased(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardIncompleteActionsCountDecreased();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Total number of \"(.*?)\" and \"(.*?)\" count of Files tab should be decreased$")
	public void total_number_of_and_count_of_Files_tab_should_be_decreased(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilesIncompleteActionsCountDecreased();
		else
			Assume.assumeTrue(true);
	}
	
	/******* More Options *******/
	
	@When("^click on More Options$")
	public void click_on_More_Options() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnMoreOptions();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" icon on Options popup$")
	public void i_click_on_icon_on_Options_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnPublishFilesPlaceholder();
		else
			Assume.assumeTrue(true);
	}
	
	/******* Excel Import *******/
	
	@When("^I click on \"(.*?)\" for download template$")
	public void i_click_on_for_download_template(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnPlaceholderTemplateIcon();
		else
			Assume.assumeTrue(true);
	}

	@Then("^template should be downloaded into Local Directory$")
	public void template_should_be_downloaded_into_Local_Directory() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDownloadedTemplate();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter all mendatory fields into downloaded template$")
	public void i_enter_all_mendatory_fields_into_downloaded_template() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterFieldsIntoExcelTemplate();
		else
			Assume.assumeTrue(true);
	}

	@When("^I import \"(.*?)\" sheet$")
	public void i_import_sheet(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.importPlaceholderFromExcel();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should be open for Upload XslPlaceholder$")
	public void popup_should_be_open_for_Upload_XslPlaceholder(String popupText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyImportPlaceholdersPopup(popupText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button link$")
	public void i_click_on_button_link(String linkText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickLinkWithText(linkText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should be opened for XslPlaceholder verifications$")
	public void popup_should_be_opened_for_XslPlaceholder_verifications(String popupText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyConfirmPopup(popupText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button of Confirm popup$")
	public void i_click_on_button_of_Confirm_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnOK();
		else
			Assume.assumeTrue(true);
	}

	@Then("^XslPlaceholder template All fields should be set into \"(.*?)\" popup list And Status default value should be set as \"(.*?)\"$")
	public void xslplaceholder_template_All_fields_should_be_set_into_popup_list_And_Status_default_value_should_be_set_as(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedPlaceholderValues();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Xsl template Placeholder Created Successfully and should be listed on Files listing page And All Fields should be displayed in Files Listing page$")
	public void all_Xsl_template_Placeholder_Created_Successfully_and_should_be_listed_on_Files_listing_page_And_All_Fields_should_be_displayed_in_Files_Listing_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedPlaceholders();
		else
			Assume.assumeTrue(true);
	}

	@When("^I login with secondary user$")
	public void i_login_with_secondary_user() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" placeholder Excel Import folder$")
	public void i_click_on_placeholder_Excel_Import_folder(String excelImportFolder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickElementWithText(excelImportFolder);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should be displayed on created all PlaceHolders$")
	public void action_should_be_displayed_on_created_all_PlaceHolders(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionOnPlaceholder(actionType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I upload files into created placeholders using \"(.*?)\" action And I check only \"(.*?)\" dropdown value should be blanked$")
	public void i_upload_files_into_created_placeholders_using_action_And_I_check_only_dropdown_value_should_be_blanked(String actionType, String statusValue) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFileIntoPlaceholder();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^File should be uploaded successfully and placeholder All attributes value should be set to uploaded file And \"(.*?)\" action should be completed And File Version should be same \"(.*?)\"$")
	public void file_should_be_uploaded_successfully_and_placeholder_All_attributes_value_should_be_set_to_uploaded_file_And_action_should_be_completed_And_File_Version_should_be_same(String actionType, String issueNumber) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedFileAttributesAndIssueNumber(issueNumber);
		else
			Assume.assumeTrue(true);
	}
}
