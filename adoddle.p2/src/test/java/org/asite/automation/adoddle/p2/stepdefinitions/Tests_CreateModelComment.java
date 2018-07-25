package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreateModelCommentScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateModelComment {
	CreateModelCommentScripts scripts = new CreateModelCommentScripts();

	@When("^I search Model And click on Model$")
	public void i_search_Model_And_click_on_Model() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnModel();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should be opened AND Model should be View$")
	public void new_tab_should_be_opened_AND_Model_should_be_View() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyViewModel();
		else
			Assume.assumeTrue(true);
	}

	/***************************** NEW SCRIPT *******************************/

	/*@When("^I select modelView And create Discussion on Viewer$")
	public void i_select_modelView_And_create_Discussion_on_Viewer() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectModelViewAndClickDiscussion();
		else
			Assume.assumeTrue(true);
	}*/

	/*@Then("^\"(.*?)\" dropdown popup should open$")
	public void dropdown_popup_should_open(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDiscussionDropdown();
		else
			Assume.assumeTrue(true);
	}*/

	@When("^I select user into \"(.*?)\" text field And I entered comment \"(.*?)\" AND comment \"(.*?)\"$")
	public void i_select_user_into_text_field_And_I_entered_comment_AND_comment(String arg1, String arg2, String arg3)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectUserAndEnterCommentDetails();
		else
			Assume.assumeTrue(true);
	}

	/*@When("^I click on \"(.*?)\" Button and select \"(.*?)\" for files associate$")
	public void i_click_on_Button_and_select_for_files_associate(String arg1, String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickAssociateAndSelectOptions(option);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I click on \"(.*?)\" Button and select \"(.*?)\" for Discussions associate$")
	public void i_click_on_Button_and_select_for_Discussions_associate(String arg1, String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickAssociateAndSelectOptions(option);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I click on \"(.*?)\" Button and select \"(.*?)\" for Apps associate$")
	public void i_click_on_Button_and_select_for_Apps_associate(String arg1, String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickAssociateAndSelectOptions(option);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I click on \"(.*?)\" Button and select \"(.*?)\" for Views associate$")
	public void i_click_on_Button_and_select_for_Views_associate(String arg1, String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickAssociateAndSelectOptions(option);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I associate more than one Apps And I click on \"(.*?)\" Button$")
	public void i_associate_more_than_one_Apps_And_I_click_on_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateFormAndClickOnSave();
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I associate more than one Views And I click on \"(.*?)\" Button$")
	public void i_associate_more_than_one_Views_And_I_click_on_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateViewAndClickOnSave();
		else
			Assume.assumeTrue(true);
	}*/

	@When("^I Click on \"(.*?)\" button of Create Discussion page$")
	public void i_Click_on_button_of_Create_Discussion_page(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickSubmitDisscussionLink();
		else
			Assume.assumeTrue(true);
	}

	/*@Then("^Comment should be created successfully and displayed on \"(.*?)\" dropdown popup$")
	public void comment_should_be_created_successfully_and_displayed_on_dropdown_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateCommentWithAttachmentAndAssociations();
		else
			Assume.assumeTrue(true);
	}*/

	/******************************* END **************************/

	/*@When("^I click on \"(.*?)\" LH-panel Model link$")
	public void i_click_on_LH_panel_Model_link(String newModelCommentLink) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickLinkWithText(newModelCommentLink);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I click on \"(.*?)\" link of New Popup$")
	public void i_click_on_link_of_New_Popup(String newPopupOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDiscussionOrForm(newPopupOption);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I select user into \"(.*?)\" text field And I entered comment \"(.*?)\" AND comment \"(.*?)\" for \"(.*?)\"$")
	public void i_select_user_into_text_field_And_I_entered_comment_AND_comment(String arg1, String arg2, String arg3,
			String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterCommentDetails(option);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I click on \"(.*?)\" Button for attachments$")
	public void i_click_on_Button_for_attachments(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAttachments();
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I attach more than one documents And I click on \"(.*?)\" Button$")
	public void i_attach_more_than_one_documents(String attach) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSelectFilesAndAttachDocuments();
		else
			Assume.assumeTrue(true);
	}*/

	/*@Then("^\"(.*?)\" popup should opened for comment association$")
	public void popup_should_opend_for_comment_association(String popHeaderText) {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyOptionsPopup(popHeaderText);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I associate more than one Files And I click on \"(.*?)\" Button of ([^\"]*) project of \"(.*?)\" folder$")
	public void i_associate_more_than_one_Files_And_I_click_on_Button_of_AutomationTestProject_project_of_folder(
			String arg1, String project, String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateFilesAndClickOnSave(project, folder);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I associate more than one Discussions And I click on \"(.*?)\" Button$")
	public void i_associate_more_than_one_Discussions(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateDiscussionsAndClickOnSave();
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I associate more than one Forms And I click on \"(.*?)\" Button$")
	public void i_associate_more_than_one_Forms(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateFormAndClickOnSave();
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I associate all existing Views And I click on \"(.*?)\" Button$")
	public void i_associate_all_existing_Views_And_I_click_on_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.associateViewAndClickOnSave();
		else
			Assume.assumeTrue(true);
	}*/

	/*@Then("^Should be displayed in \"(.*?)\" LH-panel top list for \"(.*?)\"$")
	public void should_be_displayed_in_LH_panel_top_list(String lhTab, String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedCommentOnViewModelTab(lhTab, option);
		else
			Assume.assumeTrue(true);
	}*/

	/*@When("^I click on attachment clip Icon$")
	public void i_click_on_attachment_clip_Icon() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAttachAssociateClipIcon();
		else
			Assume.assumeTrue(true);
	}*/

	/*@Then("^All Attached AND Associated \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" AND \"(.*?)\" should be successfully done with selected All fields$")
	public void all_Attached_AND_Associated_AND_should_be_successfully_done_with_selected_All_fields(
			String attachments, String files, String forms, String discussions, String views) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAttachmentsAndAssociations(attachments, files, forms, discussions, views);
		else
			Assume.assumeTrue(true);
	}*/

	@When("^I login using secondary user And I goto \"(.*?)\" tab$")
	public void i_login_using_secondary_user_And_I_goto_tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
			scripts.navigateToDiscussionTab();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^created comment should available and displayed as \"(.*?)\" on \"(.*?)\" tab$")
	public void created_comment_should_available_and_displayed_as_on_tab(String arg1, String arg2) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyUnReadModelCommentOnDiscussionTab();
		else
			Assume.assumeTrue(true);
	}

	/****** Comment Form Right Click ******/

	/*@When("^I right Click on modelView And select \"(.*?)\" context menu option$")
	public void i_right_Click_on_modelView_And_select_context_menu_option(String menuOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextClickOnModelViewAndSelectOption(menuOption);
		else
			Assume.assumeTrue(true);
	}*/

	/*@Then("^after click on comment \"(.*?)\" link View should displayed on R-H panel and I take screenshot of opend view$")
	public void after_click_on_comment_link_View_should_displayed_on_R_H_panel_and_I_take_screenshot_of_opend_view(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnMarkupsAndVerifyView();
		else
			Assume.assumeTrue(true);
	}*/
	
	/***************************** NEW SCRIPT *******************************/
	
	@Then("^Comment should successfully created and displayed in \"(.*?)\" dropdown top list$")
	public void comment_should_successfully_created_and_displayed_in_dropdown_top_list(String drpdownPopup) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedCommentOnViewModelTab(drpdownPopup);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Attach Associate hyperlink of created comment$")
	public void i_click_on_Attach_Associate_hyperlink_of_created_comment() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFirstAttachAssociateHyperLink();
		else
			Assume.assumeTrue(true);
	}

}