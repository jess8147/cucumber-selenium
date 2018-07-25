package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.CreateModelFormScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateModelForm
{
	CreateModelFormScripts scripts = new CreateModelFormScripts();
	
	@When("^I right click on ModelView And select \"(.*?)\"$")
	public void i_right_click_on_ModelView_And_select(String menuOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.contextClickOnModelViewAndSelectOption(menuOption);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" form type should displayed AND other form type should not displayed$")
	public void associated_View_with_Create_Form_should_displayed_AND_other_from_should_not_displayed(String viewForm) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyViewFormType(viewForm);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Secondary User in Distribute text area$")
	public void i_select_Secondary_User_in_Distribute_text_area() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.enterFormDistributionTextField();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button for Form$")
	public void i_click_on_button_for_Form(String buttonType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnCreateFormHeaderButtonOptions(buttonType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I attach more than one documents And I click on \"(.*?)\" Button for Form$")
	public void i_attach_more_than_one_documents_And_I_click_on_Button_for_Form(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnSelectFilesAndAttachDocuments();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should opend for form association$")
	public void popup_should_opend_for_form_association(String popHeaderText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyOptionsPopup();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I associate more than one Files And I click on \"(.*?)\" Button for Form of ([^\"]*) project of \"(.*?)\" folder$")
	public void i_associate_more_than_one_Files_And_I_click_on_Button_for_Form_of_AutomationTestProject_project_of_folder(String arg1, String project, String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.associateFilesAndClickOnSave(project, folder);
		else
			Assume.assumeTrue(true);
	}

	@When("^I associate more than one Discussions And I click on \"(.*?)\" Button for Form$")
	public void i_associate_more_than_one_Discussions_And_I_click_on_Button_for_Form(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.associateDiscussionsAndClickOnSave();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I associate more than one Forms And I click on \"(.*?)\" Button for Form$")
	public void i_associate_more_than_one_Forms_And_I_click_on_Button_for_Form(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.associateFormAndClickOnSave();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered \"(.*?)\" And I Cleared \"(.*?)\" text And entered Current Date$")
	public void i_have_entered_And_I_Cleared_text_And_entered_Current_Date(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.enterFormDetails();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be successfully created And Should be displayed in \"(.*?)\" LH-panel top list$")
	public void form_should_be_successfully_created_And_Should_be_displayed_in_LHpanel_top_list(String lhTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyModelFormOnFormsTab(lhTab);
		}
		else
			Assume.assumeTrue(true);
		
	}

	@When("^I click on \"(.*?)\" and I click on RH-panel attachment clip Icon And I click on Associate Header link$")
	public void i_click_on_and_I_click_on_RH_panel_attachment_clip_Icon_And_I_click_on_Associate_Header_link(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.gotoFormDetails();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Attached AND Associated \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" AND \"(.*?)\" should be successfully done with selected All fields for \"(.*?)\"$")
	public void all_Attached_AND_Associated_AND_should_be_successfully_done_with_selected_All_fields_for(String attachments, String files, String forms, String discussions, String views, String viewType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAttachmentsAndAssociations(viewType, attachments, files, forms, discussions, views);
		else
			Assume.assumeTrue(true);
	}

	@When("^I login using secondary user And I goto ProjectForms tab$")
	public void i_login_using_secondary_user_And_I_goto_ProjectForms_tab() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.logOut();
			scripts.login(System.getProperty("secondary.username"),System.getProperty("secondary.password"));
			scripts.navigateTab(LandingPage.lnk_ProjectForms);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I nevigate to \"(.*?)\" folder in \"(.*?)\" form type$")
	public void i_nevigate_to_folder_in_form_type(String folder, String formType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickElementWithText(System.getProperty("global.test.project"));
			scripts.clickElementWithText(folder);
			scripts.clickElementWithText(formType);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^created Form should displayed in ProjectForms listing And assigned action should displayed$")
	public void created_Form_should_displayed_in_ProjectForms_listing_And_assigned_action_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyModelFormAndActionOnProjectFormsTab();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Associated all views$")
	public void i_click_on_Associated_all_views() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickAndVerifyAssociatedAllViews();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^It should be opened and viewed in new window$")
	public void it_should_be_opened_and_viewed_in_new_window() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyAssociatedViews();
		}
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Form From LH-Panel ******/
	
	@Then("New \"(.*?)\" popup should open for Model Form")
	public void new_popup_should_open_for_Model_Form(String expectedText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyCreateFormPopup();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I associate all existing Views And I click on \"(.*?)\" Button for Form$")
	public void i_associate_all_existing_Views_And_I_click_on_Button_for_Form(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.associateViewAndClickOnSave();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" link should \"(.*?)\" for view associate$")
	public void link_should_for_view_associate(String viewLink, String displayText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyAssociateViewsLink(viewLink, displayText);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I close Create Form popup$")
	public void i_close_Create_Form_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.closeFormPopup();
		}
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Download Attachments & Files ******/
	
	@When("^I goto \"(.*?)\" link I select all files AND click on Download for \"(.*?)\"$")
	public void i_goto_link_I_select_all_files_AND_click_on_Download(String linkTab, String viewType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.selectFilesAndClickOnDownload(linkTab, viewType);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all checkboxes AND click on download button of Download popup for \"(.*?)\"$")
	public void i_select_all_checkboxes_AND_click_on_download_button_of_Download_popup_for(String linkTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.selectCheckListAndClickOnDownload(linkTab);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Batch file should be created And \"(.*?)\" Zip file should be downloaded into Local Directory$")
	public void batch_file_should_be_created_And_Zip_file_should_be_downloaded_into_Local_Directory(String linkTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.downloadZipDocumentIntoLocal(linkTab);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Unzip downloaded \"(.*?)\" zip file$")
	public void i_Unzip_downloaded_zip_file(String linkTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.zipIntoUnZip(linkTab);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^all \"(.*?)\" should be available into Local Directory$")
	public void all_should_be_available_into_Local_Directory(String linkTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.getFileNamesFromLocalFolder(linkTab);
			scripts.verifyFilesNameIntoSystem(linkTab);
		}
		else
			Assume.assumeTrue(true);
	}
	
	/***************************** NEW SCRIPT *******************************/
	
	@When("^I click on \"(.*?)\" Button for file associate for \"(.*?)\"$")
	public void i_click_on_Button_for_file_associate(String arg1, String formComment) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAssociateFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" Button for comment associate for \"(.*?)\"$")
	public void i_click_on_Button_for_comment_associate(String arg1, String formComment) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAssociateDiscussions(formComment);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" Button for form associate for \"(.*?)\"$")
	public void i_click_on_Button_for_form_associate(String arg1, String formComment) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAssociateForms();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" Button for Model View for \"(.*?)\"$")
	public void i_click_on_Button_for_Model_View(String arg1, String formComment) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAssociateViews();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I opened Views Manager for current model$")
	public void i_opened_Views_Manager_for_current_model() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.navigateViewManager();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select modelView And \"(.*?)\" on selected View Manager$")
	public void i_select_modelView_And_on_selected_View_Manager(String commentForm) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectModelViewAndClickDiscussion(commentForm);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" dropdown popup should open$")
	public void dropdown_popup_should_open(String popup) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyDropdownPopup(popup);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I search and click \"(.*?)\" form type$")
	public void i_search_and_click_form_type(String formType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchAndSelectFormType(formType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Form should created successfully on selected View Manager$")
	public void form_should_created_successfully_on_selected_View_Manager() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormOnViewer();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Attachment clip Icon of View Form$")
	public void i_click_on_Attachment_clip_Icon_of_View_Form() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnViewFormAttachmentClipIcon();
		else
			Assume.assumeTrue(true);
	}
	
	@When("I close View Model comment window")
	public void i_close_View_Model_comment_window() {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.closeViewModelWindow();
		else
			Assume.assumeTrue(true);
	}
}
