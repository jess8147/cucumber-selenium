package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.GlobalFederatedSearchScripts;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_GlobalFederatedSearch
{
	GlobalFederatedSearchScripts scripts = new GlobalFederatedSearchScripts();
	
	@Given("^I have already created TestData for global Content search text$")
	public void i_have_already_created_TestData_for_global_Content_search_text() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getContentSearchTextTestData();
		else
			Assume.assumeTrue(true);
	}

	@When("^I globally search Content search text$")
	public void i_globally_search_Content_search_text() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.globallySearchContentSearchText();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Content search text contain All \"(.*?)\" should displayed$")
	public void content_search_text_contain_All_should_displayed(String tabWiseData) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyContentSearchTextData(tabWiseData);
		else
			Assume.assumeTrue(true);
	}

	@When("^I search Content search text on \"(.*?)\" tab$")
	public void i_search_Content_search_text_on_tab(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchContentSearchTextOnActiveTab(activeTab);
		else
			Assume.assumeTrue(true);
	}

	@When("^I upload three files in one contains Content search text, second one contains Content search text in Doc Title and other one contains Content search text in Revision notes And distribute to ([^\"]*) user with \"(.*?)\" actions$")
	public void i_upload_three_files_in_one_contains_Content_search_text_second_one_contains_Content_search_text_in_Doc_Title_and_other_one_contains_Content_search_text_in_Revision_notes_And_distribute_to_user_with_actions(String secondaryUser, String actionList) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadMultiFilesForContentSearchTextData(secondaryUser, actionList);
		else
			Assume.assumeTrue(true);
		
	}
	
	@When("^I upload new file for Discussion$")
	public void i_upload_new_file_for_Discussion() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFileForContentSearchDiscussion();
		else
			Assume.assumeTrue(true);
	}

	@When("^I create Content search text contain discussion on file and distribute to ([^\"]*) user$")
	public void i_create_Content_search_text_contain_discussion_on_file_and_distribute_to_user(String distributedUser) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createContentSearchTextDataComment(distributedUser);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select Cloned project AND go in \"(.*?)\" group AND select \"(.*?)\" app type$")
	public void i_select_Cloned_project_AND_go_in_group_AND_select_app_type(String appGroup, String appType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormApp(appGroup, appType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I create Content search text contain \"(.*?)\" Form And distribute to ([^\"]*) user with \"(.*?)\" actions$")
	public void i_create_Content_search_text_contain_Form_And_distribute_to_user_with_actions(String stringContentType, String distributeUser, String actionList) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createContentSearchTextDataForm(stringContentType, distributeUser, actionList);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create Cloned project from existing template ([^\"]*) for ([^\"]*)$")
	public void i_create_Cloned_project_from_existing_template_for(String template, String dc) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.cloneProject(template, dc);
		else
			Assume.assumeTrue(true);
	}

	@When("^I search created Cloned project And I give admin access to ([^\"]*) and ([^\"]*) users$")
	public void i_search_created_Cloned_project_And_I_give_admin_access_to_and_users(String fSearchUser, String distributeUser) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchProjectAndAddAccessToUser(fSearchUser, distributeUser);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Cloned project AND I click on Folder Name \"(.*?)\" AND I click on \"(.*?)\" button$")
	public void i_click_on_Cloned_project_AND_I_click_on_Folder_Name_AND_I_click_on_button(String folderName, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnProjectAndFolderAndAddFiles(folderName);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I deactivated all Cloned projects only excepts ([^\"]*) project$")
	public void i_deactivated_all_Cloned_projects_only_excepts_project(String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deactivateAllClonedProjects(project);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Global Search \"(.*?)\" LH tab$")
	public void i_click_on_Global_Search_LH_tab(String lhTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnGlobalSearchLhTab(lhTab);
		else
			Assume.assumeTrue(true);
	}

	@Then("^distributed all files with assigned \"(.*?)\" actions should displayed$")
	public void distributed_all_files_with_assigned_actions_should_displayed(String actionList) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDistributedFilesWithActions(actionList);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Single file and perform \"(.*?)\" action with hyperlink$")
	public void i_select_file_and_perform_action(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.selectSingleFileAndPerformForInformation();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed on selected \"(.*?)\" file$")
	public void action_should_completed_on_selected_file(String action, String listOfFiles) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileActionCompletion(action, listOfFiles);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select all files and perform right click and select \"(.*?)\" and \"(.*?)\"$")
	public void i_select_all_files_and_perform_right_click_and_select_and(String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectAllAndPerformContextMenuOption(option1, option2);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed for all files$")
	public void action_should_completed_for_all_files(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileActionCompletion(action, "all");
		else
			Assume.assumeTrue(true);
	}

	@When("^I create Comment for selected all files$")
	public void i_create_Comment_for_selected_all_files() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createComment();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Change Status for selected all files$")
	public void i_Change_Status_for_selected_all_files() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeStatus(AdoddleCommonStringPool.STATUS_FOR_REVIEW);
		else
			Assume.assumeTrue(true);
	}

	@Then("^distributed Un Read Discussion should displayed$")
	public void distributed_Un_Read_Discussion_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDistributedUnReadDiscussion();
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform distributed Un Read discussion$")
	public void i_perform_distributed_Un_Read_discussion() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.performDistributedUnReadDiscussion();
		else
			Assume.assumeTrue(true);
	}

	@Then("^distributed discussion should \"(.*?)\"$")
	public void distributed_discussion_should(String readDiscussion) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionCompleted(0, readDiscussion);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^distributed all forms with assigned \"(.*?)\" actions should displayed$")
	public void distributed_all_forms_with_assigned_actions_should_displayed(String actionList) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDistributedFormsWithActions(actionList);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Single form and perform \"(.*?)\" action with hyperlink$")
	public void i_select_Single_form_and_perform_action_with_hyperlink(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectSingleFormAndPerformAction(action);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed on selected \"(.*?)\" form$")
	public void action_should_completed_on_selected_form(String action, String listOfForms) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormActionCompletion(action, listOfForms);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select all forms and perform right click and select \"(.*?)\" and \"(.*?)\"$")
	public void i_select_all_forms_and_perform_right_click_and_select_and(String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectAllAndPerformContextMenuOption(option1, option2);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed for all forms$")
	public void action_should_completed_for_all_forms(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormActionCompletion(action, "all");
		else
			Assume.assumeTrue(true);
	}

	@When("^I Change Status for selected all forms$")
	public void i_Change_Status_for_selected_all_forms() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.changeStatus("RecentForm_Customize_Status");
		} else
			Assume.assumeTrue(true);
	}
	
	
	
	@When("^I create Content search text contain \"(.*?)\" Model in created Cloned project$")
	public void i_create_Content_search_text_contain_Model_in_created_Cloned_project(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createContentSearchTextDataModel();
		else
			Assume.assumeTrue(true);
	}

	@When("^I upload IFC file in created \"(.*?)\" Model$")
	public void i_upload_IFC_file_in_created_Model(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadIFCFile();
		else
			Assume.assumeTrue(true);
	}

	@Then("^created Content search text contain Model should displayed$")
	public void create_new_Content_search_text_contain_Model_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedContentSearchTextModel();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I mousehover on Model and click on View Model$")
	public void i_mousehover_on_Model_and_click_on_View_Model() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.mouseHoverAndClickOnViewModel();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^New tab should be opened and Model should Viewed$")
	public void new_tab_should_be_opened_and_Model_should_Viewed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyModelViewed();
		else
			Assume.assumeTrue(true);
	}
}