package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.NewFilesPublishedScripts;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_NewFilesPublished
{
	NewFilesPublishedScripts scripts = new NewFilesPublishedScripts();
	
	/****** LAST LOGIN ******/
	
	@When("^I \"(.*?)\" ([^\"]*) project as Default$")
	public void i_Automation_Last_Login_UK_Project_project_as_Default(String setReset, String defaultProject) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setResetDefaultProject(setReset, defaultProject);
		else
			Assume.assumeTrue(true);
	}

	@When("^I \"(.*?)\" ([^\"]*) project from Default$")
	public void i_Automation_Last_Login_UK_Project_project_from_Default(String setReset, String defaultProject) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setResetDefaultProject(setReset, defaultProject);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I clear all flags for \"(.*?)\"$")
	public void i_clear_all_flags_for(String activeTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clearFlag(activeTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get \"(.*?)\", \"(.*?)\", AND \"(.*?)\" total highcharts count$")
	public void i_get_AND_total_highcharts_count(String lastLogin, String today, String pastWeek) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalHighchartsAxisCount(lastLogin, today, pastWeek);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I fill all mendatory fields for \"(.*?)\"$")
	public void i_fill_all_mendatory_fields_for(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterMendatoryAttributes();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Files should be uploaded successfully in DocListing page$")
	public void files_should_be_uploaded_successfully_in_DocListing_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I logOut and again Login using \"(.*?)\" User$")
	public void i_logOut_and_again_Login_using_User(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.logOutAndLogin(user);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I logOut and re login using ([^\"]*) User$")
	public void i_logOut_and_re_login_using_auto_nfpw_uk_atest_com_User(String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.login(userID, ResourceHandler.loadProperty("test.users.common.password"));
		} else
			Assume.assumeTrue(true);
	}

	@Then("^total \"(.*?)\", \"(.*?)\", AND \"(.*?)\" count should increased$")
	public void total_AND_count_should_increased(String lastLogin, String today, String pastWeek) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalHighchartsAxisCount(lastLogin, today, pastWeek);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" highcharts$")
	public void i_click_on_highcharts(String axis) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnHighchartsAxis(axis);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Total Files listing and files count same as \"(.*?)\" highcharts of Dashboard Count$")
	public void total_Files_listing_and_files_count_same_as_highcharts_of_Dashboard_Count(String axis) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileListingAndCountWithHighcharts(axis);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^total \"(.*?)\" count should set as (\\d+)$")
	public void total_count_should_set_as(String arg1, int count) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyLastLoginCountAfterLogin(count);
		else
			Assume.assumeTrue(true);
	}
	
	/****** flag Widget ******/
	
	@When("^I get \"(.*?)\" count and perform Operation$")
	public void i_get_count_and_perform_Operation(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.performFileUploadOperationForAxisCount();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Project Name ([^\"]*) AND I click on Folder Name \"(.*?)\" on Files tab And I get total files name$")
	public void i_click_on_Project_Name_AND_I_click_on_Folder_Name_on_Files_tab_And_I_get_total_files_name(String project, String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFileNameList(project, folder);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select more then one Files for \"(.*?)\" flag$")
	public void i_select_more_then_one_Files_for_flag(String flagType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForFlag(flagType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("I right click on selected files And drag mouse to \"(.*?)\" And I click on \"(.*?)\" flag in context menu options")
	public void i_right_click_on_selected_files_And_drag_mouse_to_And_I_click_on_flag_in_context_menu_options(String flag, String flagType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectFlagType(flag, flagType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create \"(.*?)\" filter$")
	public void i_create_filter(String flag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setSearchFilter(flag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set \"(.*?)\" flag into filter$")
	public void i_set_flag_into_filter(String flag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setSearchFilter(flag);
		else
			Assume.assumeTrue(true);
	}

	@Then("^only \"(.*?)\" flag files should displayed$")
	public void only_flag_files_should_displayed(String flag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFlagTypeAndFiles(flag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set \"(.*?)\", \"(.*?)\" AND \"(.*?)\" flag in Flag filter$")
	public void i_set_AND_flag_in_Flag_filter(String high, String medium, String low) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setAllFlagFilter(high, medium, low);
		else
			Assume.assumeTrue(true);
	}

	@Then("^all flag filter files should displayed$")
	public void all_flag_filter_files_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllFlagFilterFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select all files AND right click on selected files And drag mouse to \"(.*?)\" And I click on \"(.*?)\"$")
	public void i_select_all_files_AND_right_click_on_selected_files_And_drag_mouse_to_And_I_click_on(String flag, String flagType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectFlagType(flag, flagType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^all flag filter files should set as \"(.*?)\" files$")
	public void all_flag_filter_files_should_set_as_files(String flag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFlagTypeAndFiles(flag);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Link File(s) ******/
	
	@When("^I right click on selected files And drag mouse to \"(.*?)\" context click Option$")
	public void i_right_click_on_selected_files_And_drag_mouse_to_context_click_Option(String contextOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectOption(contextOption);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I have atleast two folders in project$")
	public void i_have_atleast_two_folders_in_project() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFolderCountInProject();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" file for perform \"(.*?)\"$")
	public void i_select_file_for_perform(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectIFCFile();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select destination Folder And click on \"(.*?)\" button$")
	public void i_select_destination_Folder_And_click_on_button(String btnText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDestinationFolderAndClickSubmitButton(btnText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select ([^\"]*) User in \"(.*?)\" field And select \"(.*?)\" type And Click on \"(.*?)\" button$")
	public void i_select_Auto_FWidget_US_User_in_field_And_select_type_And_Click_on_button(String user, String arg2, String linkType, String btnText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectUserAndClickButton(user, btnText, linkType);
		else
			Assume.assumeTrue(true);
	}

	@Given("I have focus on Destination folder")
	public void i_have_focus_on_destination_folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickTargetFolder();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Link document should be available in destination folder$")
	public void link_document_should_be_available_in_destination_folder() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyLinkDocumentIsAvailableInTargetFolder();
		else
			Assume.assumeTrue(true);
	}

	@Then("^For Information action should assign to selected User$")
	public void for_Information_action_should_assign_to_selected_User() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionAssignedToSelectedUser();
		else
			Assume.assumeTrue(true);
	}

	/****** Secondary Files ******/
	
	@When("^I select More then one files for \"(.*?)\" widget$")
	public void i_select_More_then_one_files_for_widget(String widgetType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectMoreFiles(widgetType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I right click and select context menu option \"(.*?)\"$")
	public void i_right_click_and_select_context_menu_option(String contextOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectContextOption(contextOption, "");
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I right click and select context menu option \"(.*?)\" AND I click on \"(.*?)\" widget$")
	public void i_right_click_and_select_context_menu_option_AND_I_click_on_widget(String contextOption1, String contextOption2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectContextOption(contextOption1, contextOption2);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" context menu Option should displayed as disabled$")
	public void context_menu_Option_should_displayed_as_disabled(String widgetType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyContextMenuOptionDisabled(widgetType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I deSelect selected checkboxes for \"(.*?)\" HighchartsAxis$")
	public void i_deSelect_selected_checkboxes(String highChartAxis) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deSelectCheckList(highChartAxis);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" for \"(.*?)\" Attachment$")
	public void i_select_for_Attachment(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectLinkFileForAttachment();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select any one file for \"(.*?)\" widget$")
	public void i_select_any_one_file_for_widget(String widget) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectSingleFile(widget);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I clicked on \"(.*?)\" button And I click on \"(.*?)\" Button for Attachment$")
	public void i_clicked_on_button_And_I_click_on_Button_for_Attachment(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSelectFileAndAttachFile();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Image should displayed And after click on \"(.*?)\" Image New tab should opened And Attached file should be open/view in viewer$")
	public void image_should_displayed_And_after_click_on_Image_New_tab_should_opened_And_Attached_file_should_be_open_view_in_viewer(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileAttachment();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Download Image$")
	public void i_click_on_Download_Image() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnDownloadImage();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" File should download in local directory$")
	public void file_should_download_in_local_directory(String type) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDownloadedFile(type);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Start Workflow ******/
	
	@When("^I entered form \"(.*?)\" AND form details$")
	public void i_entered_form_AND_form_details(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterFormDetails();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" image should displayed in selected \"(.*?)\" file$")
	public void image_should_displayed_in_selected_file(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAssociationsOnFile();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Associations image$")
	public void i_click_on_Associations_image() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAssociationsImage();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^created Form should displayed on \"(.*?)\" popup$")
	public void created_Form_should_displayed_on_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormOnAttachmentAssociationPopup();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Form \"(.*?)\"$")
	public void i_click_on_Form(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnFormID();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^New tab should opened And File should displayed as Associations on \"(.*?)\" popup$")
	public void new_tab_should_opened_And_File_should_displayed_as_Associations_on_popup(String popupText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileOnAttachmentAssociationPopup(popupText);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Distribute Files ******/
	
	@When("^I get total \"(.*?)\" count of LoggedIn User Dashboard$")
	public void i_get_total_count_of_LoggedIn_User_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getDashboardIncompleteActionsCountOfPUser();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total \"(.*?)\" count of LoggedIn User Files tab$")
	public void i_get_total_count_of_LoggedIn_User_Files_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFilesIncompleteActionsCountOfPUser();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total \"(.*?)\" count of Secondary User Dashboard$")
	public void i_get_total_count_of_Secondary_User_Dashboard(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getDashboardIncompleteActionsCountOfSUser();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total \"(.*?)\" count of Secondary User Files tab$")
	public void i_get_total_count_of_Secondary_User_Files_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFilesIncompleteActionsCountOfSUser();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select files for perform Distribution widget$")
	public void i_select_files_for_perform_widget() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformDistributionWidget();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I assign \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" AND \"(.*?)\" actions to ([^\"]*) and ([^\"]*) User$")
	public void i_assign_AND_actions_to_Auto_FWidget_UK_and_Auto_FWidget_UK_User(String forInfo, String forComIncrp, String forAction, String forACK, String forComCoord, String primary, String secondary) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignFilesActionsToUsers(forInfo, forComIncrp, forACK, forAction, forComCoord, primary, secondary); 
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" AND \"(.*?)\" actions should assigned to given documents$")
	public void and_actions_should_assigned_to_given_documents(String forInfo, String forComIncrp, String forAction, String forACK, String forComCoord) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAssignedFileActionsToUsers(forInfo, forComIncrp, forACK, forAction, forComCoord);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Files tab \"(.*?)\" count of LoggedIn User should increased$")
	public void files_tab_count_of_LoggedIn_User_should_increased(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilesIncompleteActionsCountOfPUserIncreased();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Dashboard \"(.*?)\" count of LoggedIn User should increased$")
	public void dashboard_count_of_LoggedIn_User_should_increased(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardIncompleteActionsCountOfPUserIncreased();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Dashboard \"(.*?)\" count of LoggedIn User should decreased$")
	public void dashboard_count_of_LoggedIn_User_should_decreased(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardIncompleteActionsCountOfPUserDecreased();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Dashboard \"(.*?)\" count of Secondary User should increased$")
	public void dashboard_count_of_Secondary_User_should_increased(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardIncompleteActionsCountOfSUserIncreased();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Files tab \"(.*?)\" count of Secondary User should increased$")
	public void files_tab_count_of_Secondary_User_should_increased(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilesIncompleteActionsCountOfSUserIncreased();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** View Files ******/
	
	@When("^I select file for perform View widget$")
	public void i_select_file_for_perform_View_widget() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFileForPerformViewFileWidget();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^New tab should opened And file should be open/view in viewer$")
	public void new_tab_should_opened_And_file_should_be_open_view_in_viewer() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileOpened();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Attribute of file should be available on top$")
	public void attribute_of_file_should_be_available_on_top() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileAttributeOnTop();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I closed opened new window$")
	public void i_closed_opened_new_window() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.closeSecondWindow();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" action should completed for viewed document$")
	public void action_should_completed_for_viewed_document(String forInfo) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentActionCompleted(forInfo);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Download Files ******/
	
	@When("^I upload files in \"(.*?)\" folder of ([^\"]*) Project with \"(.*?)\" action for User ([^\"]*)$")
	public void i_upload_files_in_folder_of_Project_Name_Project_with_action_for_User_auto_nfpw_uk_atest_com(String folder, String project, String action, String userID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFilesWithAction(folder, project, action, userID, null);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" for \"(.*?)\"$")
	public void i_select_for(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectPlaceholder();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select files for perform Download Files widget$")
	public void i_select_files_for_perform_Download_Files_widget() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformDownloadFilesWidget();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all checkboxes AND click on download button of \"(.*?)\" popup$")
	public void i_select_all_checkboxes_AND_click_on_download_button_of_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectCheckListAndClickOnDownload("");
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Batch file should be created And Zip file should be downloaded into Local Directory$")
	public void batch_file_should_be_created_And_Zip_file_should_be_downloaded_into_Local_Directory(String widget) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.downloadZipDocumentIntoLocal(widget);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I Unzip \"(.*?)\" zip file$")
	public void i_Unzip_zip_file(String widget) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.zipIntoUnZip(widget);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" files should available in Local Directory$")
	public void files_should_available_in_Local_Directory(String widget) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.getFileNamesFromLocalFolder(widget);
			scripts.verifyFilesNameIntoSystem(widget);
			scripts.fileRename(widget);
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" action should completed for downloaded documents$")
	public void action_should_completed_for_downloaded_documents(String forInfo) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentActionCompleted(forInfo);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Checkout & Undo Checkout ******/
	
	@When("^I upload files in \"(.*?)\" folder of ([^\"]*) Project with \"(.*?)\" actions to User ([^\"]*) and User ([^\"]*)$")
	public void i_upload_files_in_folder_of_AutomationTestProject_Project_with_actions_to_User_Auto_FWidget_UK_and_User_Auto_FWidget_UK(String folder, String project, String action, String userA, String userB) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFilesWithAction(folder, project, action, userA, userB);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select files for perform Checkout widget$")
	public void i_select_files_for_perform_Checkout_widget() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformCheckoutFilesWidget();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" image should displayed And \"(.*?)\" action should completed for selected Checkout files$")
	public void image_should_displayed_And_action_should_completed_for_selected_Checkout_files(String arg1, String forComInrp) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentActionCompleted(forComInrp);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" image should displayed And \"(.*?)\" action should completed for selected Checkout files of user B$")
	public void image_should_displayed_And_action_should_completed_for_selected_Checkout_files_of_user_B(String arg1, String forComInrp) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentActionCompleted(forComInrp);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on Project And select folder And click on \"(.*?)\" button$")
	public void i_click_on_Project_And_select_folder_And_click_on_button(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectProjectAndFolderAndClickOnAddFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I clicked on \"(.*?)\" button And I select Checkout files from Local to upload$")
	public void i_clicked_on_button_And_I_select_Checkout_files_from_Local_to_upload(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSelectFileAndUploadCheckoutFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Checkout validation popup should opened$")
	public void checkout_validation_popup_should_opened(String popupText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCheckoutValidationPopup(popupText);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" validation message should displayed$")
	public void validation_message_should_displayed(String validationMsg) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCheckoutValidationMessage(validationMsg);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I close \"(.*?)\" popup of Checkout$")
	public void i_close_popup_of_Checkout(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.closePublishDocumentsPopup();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select files for perform Undo Checkout widget$")
	public void i_select_files_for_perform_Undo_Checkout_widget() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformUndoCheckoutFilesWidget();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button of \"(.*?)\" popup$")
	public void i_click_on_button_of_popup(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnContinue();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^checkout files should set as \"(.*?)\" files$")
	public void checkout_files_should_set_as_files(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUndoCheckoutFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I fill all mendatory fields with other \"(.*?)\" of Checkout files$")
	public void i_fill_all_mendatory_fields_with_other_of_Checkout_files(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterMendatoryAttributesForCheckoutFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^removable Checkout files should uploaded successfully With different Revision in DocListing page$")
	public void removable_Checkout_files_should_uploaded_successfully_With_different_Revision_in_DocListing_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUndoCheckoutUploadedFilesAndRevision();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** No Comment ******/
	
	@When("^I upload \"(.*?)\" files in \"(.*?)\" folder$")
	public void i_upload_few_files_in_folder(String fileCount, String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFilesWithCount(fileCount, folder);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I assign \"(.*?)\" and \"(.*?)\" actions to User ([^\"]*), ([^\"]*) AND ([^\"]*)$")
	public void i_assign_and_actions_to_User_Automation_UK_Auto_Test_AND_Auto_RFI(String forCom, String forDist, String userB, String userC, String userD) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignActionsToUsers(forCom, forDist, userB, userC, userD);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total Incomplete Actions count of User ([^\"]*)$")
	public void i_get_total_Incomplete_Actions_count_of_User(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalIncompleteActionCountOfUser(user);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get \"(.*?)\" tab total Unread count of User ([^\"]*)$")
	public void i_get_tab_total_Unread_count_of_User(String menuTab, String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getTotalUnReadActionCountOfUser(menuTab, user);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I assign \"(.*?)\" action to User ([^\"]*)$")
	public void i_assign_action_to_User(String forCom, String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.assignSingleActionToSingleUser(forCom, user);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^User ([^\"]*) and User ([^\"]*) should pre-populated on \"(.*?)\" field$")
	public void user_and_User_should_pre_populated_on_field(String user1, String user2, String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPrePopulatedUsersOnCommentPopup(user1, user2);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Only User ([^\"]*) should pre-populated on \"(.*?)\" field$")
	public void only_User_should_pre_populated_on_field(String user, String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPrePopulatedUsersOnCommentPopup(user, "");
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^No Users should pre-populated on \"(.*?)\" field$")
	public void no_Users_should_pre_populated_on_field(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPrePopulatedUsersOnCommentPopup();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Comment \"(.*?)\" AND \"(.*?)\" area should displayed as disabled$")
	public void comment_AND_area_should_displayed_as_disabled(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentTitleAndDescription();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Total Incomplete Actions count of User ([^\"]*) should incresed$")
	public void total_Incomplete_Actions_count_of_User_should_incresed(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalIncompleteActionCountOfUser(user);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" tab total Unread count of User ([^\"]*) should incresed$")
	public void tab_total_Unread_count_of_User_should_incresed(String menuTab, String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUnReadActionCountOfUser(menuTab, user);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Total Incomplete Actions count of User ([^\"]*) should not incresed$")
	public void total_Incomplete_Actions_count_of_User_should_not_incresed(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalIncompleteActionCountOfUser(user);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" tab total Unread count of User ([^\"]*) should not incresed$")
	public void tab_total_Unread_count_of_User_should_not_incresed(String menuTab, String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUnReadActionCountOfUser(menuTab, user);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select files for perform \"(.*?)\" Distribution widget$")
	public void i_select_files_for_perform_Distribution_widget(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformNoCommentDistributionWidget();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select Distributed files for User ([^\"]*)$")
	public void i_select_Distributed_files_for_User(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDistributedFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" action should completed for distributed documents$")
	public void action_should_completed_for_distributed_documents(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentActionCompleted(actionType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select single Distributed file for User ([^\"]*)$")
	public void i_select_single_Distributed_file_for_User_Auto_Test(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectSingleDistributedFile();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" action should completed for selected \"(.*?)\" document$")
	public void action_should_completed_for_selected_document(String actionType, String numOfDoc) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoCommentDocumentsActionCompleted(actionType, numOfDoc);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" action should completed for selected all \"(.*?)\" documents$")
	public void action_should_completed_for_selected_all_documents(String actionType, String numOfDoc) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoCommentDocumentsActionCompleted(actionType, numOfDoc);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select multiple Distributed files for User ([^\"]*)$")
	public void i_select_multiple_Distributed_files_for_User_Auto_Test(String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectMultipleDistributedFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I right click on selected Comment Files AND drag mouse to \"(.*?)\" AND I click on \"(.*?)\"$")
	public void i_right_click_on_selected_Comment_Files_AND_drag_mouse_to_AND_I_click_on(String newOption, String noCom) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectNoComment(newOption, noCom);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I right click on selected Comment Files AND drag mouse to \"(.*?)\" AND I click on \"(.*?)\" for Multiple files$")
	public void i_right_click_on_selected_Comment_Files_AND_drag_mouse_to_AND_I_click_on_for_Multiple_files(String newOption, String noCom) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectNoCommentForMultipleFiles(newOption, noCom);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Save as PDF ******/
	
	@When("^I click on Project And select \"(.*?)\" folder$")
	public void i_click_on_Project_And_select_folder(String savePDFFolder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnProjectAndFolder(savePDFFolder);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" with file size$")
	public void i_select_with_file_size(String docRef) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFileAndGetFileSize(docRef);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" file should downloaded with \"(.*?)\" extension And I verify file size$")
	public void file_should_downloaded_with_extension_And_I_verify_file_size(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			System.out.println("Need Check verification Part Using AutoIT");
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^file should downloaded as \"(.*?)\" File in local directory And I verify file size$")
	public void file_should_downloaded_as_File_in_local_directory_And_I_verify_file_size(String type) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.verifyDownloadedFile(type);
			scripts.verifyLocalFileSize();
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I get files name from doclisting page$")
	public void i_get_files_name_form_doclisting_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getPDFFileNameList();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" checkbox AND click on download button of \"(.*?)\" popup$")
	public void i_select_checkbox_AND_click_on_download_button_of_popup(String markup, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectCheckListAndClickOnDownload(markup);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" files should available in Local Directory And files size match with local files size$")
	public void files_should_available_in_Local_Directory_And_files_size_match_with_local_files_size(String widget) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.getFileNamesFromLocalFolder(widget);
			scripts.verifyFilesNameIntoSystem(widget);
		} else
			Assume.assumeTrue(true);
	}
	
	
	/****** Actions ******/
	
	@When("^I click on \"(.*?)\" Button of popup$")
	public void i_click_on_Button_of_popup(String buttonName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnPopupButton(buttonName);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get total \"(.*?)\" of files tab$")
	public void i_get_total_of_files_tab(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getIncompleteActionCountOfFilesTab();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select files for perform \"(.*?)\" Actions widget$")
	public void i_select_files_for_perform_Actions_widget(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformActionsWidget(actionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected files should displayed on \"(.*?)\" popup$")
	public void selected_files_should_displayed_on_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySelectedFilesOnActionPopup();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" actions should completed for selected Actions documents$")
	public void actions_should_completed_for_selected_Actions_documents(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentActionCompleted(actionType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^total \"(.*?)\" of files tab should decreased$")
	public void total_of_files_tab_should_decreased(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyIncompleteActionCountOfFilesTabDecreased();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" checkbox AND I entered Remarks input text \"(.*?)\"$")
	public void i_select_checkbox_AND_I_entered_Remarks_input_text(String arg1, String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectCheckboxAndEnterActionPopupFields(actionType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select single file for perform \"(.*?)\" Action widget$")
	public void i_select_single_file_for_perform_Action_widget(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformSingleActionsWidget(actionType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" checkbox for Action \"(.*?)\"$")
	public void i_select_checkbox_for_Action(String arg1, String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectCheckboxAndEnterActionPopupFields(actionType);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** For Comment Incorp. Action ******/
	
	@When("^I click on Project And select \"(.*?)\" folder And select \"(.*?)\" sub folder$")
	public void i_click_on_Project_And_select_folder_And_select_sub_folder(String folder, String subFolder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSubFolder(folder, subFolder);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I perform Custom Attributes upload Operation using ([^\"]*) user for \"(.*?)\" action type$")
	public void i_perform_Custom_Attributes_upload_Operation_using_auto_nfpw_us_atest_com_user_for_action_type(String user, String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.performCustomAttributesWithUploadFiles(user, actionType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I search selected \"(.*?)\" Action file And I get all Custom Attributes value of selected action file$")
	public void i_search_selected_Action_file_And_I_get_all_Custom_Attributes_value_of_selected_action_file(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchActionFileAndGetAllAttributesValue();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" checkbox AND select estimated publication date for Action \"(.*?)\"$")
	public void i_select_checkbox_AND_select_estimated_publication_date_for_Action(String arg1, String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectCheckboxAndEnterActionPopupFields(actionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected file should create Placeholder And \"(.*?)\" action assigned to Placeholder And only status value should set as \"(.*?)\"$")
	public void selected_file_should_create_Placeholder_And_action_assigned_to_Placeholder_And_only_status_value_should_set_as(String actionType, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedPlaceholderDetails(actionType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I search selected Placeholder file and click on \"(.*?)\" action type$")
	public void i_search_selected_Placeholder_file_and_click_on_action_type(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchPlaceholderAndClickOnAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Attributes value should match to selected file Attributes value$")
	public void all_Attributes_value_should_match_to_selected_file_Attributes_value() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyPlaceholderAttributesForFileUpload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Status for file upload And I get all attributes values of File upload$")
	public void i_select_Status_for_file_upload_And_I_get_all_attributes_values_of_File_upload() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectStatusAndGetUploadFileAttributesValue();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select ([^\"]*) User for Distribute File And \"(.*?)\" action With Current Date$")
	public void i_select_Auto_FWidget_UK_User_for_Distribute_File_And_action_With_Current_Date(String user, String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterDistributeUserField(user, action);
		else
			Assume.assumeTrue(true);
	}

	@Then("^File should uploaded successfully in Placeholder And \"(.*?)\" action should completed$")
	public void file_should_uploaded_successfully_in_Placeholder_And_action_should_completed(String actionType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadedFileInPlaceholderWithAttributesAndAction(actionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^all attributes values of uploaded File should verified with previous file attributes values$")
	public void all_attributes_values_of_uploaded_File_should_verified_with_previous_file_attributes_values() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			System.out.println("Coverd in Previous Method");
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I deactivate Action files$")
	public void i_deactivate_Action_files() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deactivateActionFiles();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Compare Files Html Viewer ******/
	
	@When("^I select more then two files for \"(.*?)\" widget$")
	public void i_select_more_then_two_files_for_widget(String widget) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectMoreThanTwoFiles(widget);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select files for perform Compare Files widget$")
	public void i_select_files_for_perform_Compare_Files_widget() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformCompareFilesWidget();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should open with both compared files in HTML viewer$")
	public void new_tab_should_open_with_both_compared_files_in_HTML_viewer() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCompareFilesInHtmlViewer();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Batch Print Files ******/
	
	@When("^I select files for perform Print File widget$")
	public void i_select_files_for_perform_Print_File_widget() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFilesForPerformBatchPrint();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select all checkboxes of Print Document popup$")
	public void i_select_all_checkboxes_of_Print_Document_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectPrintDocumentPopupCheckList();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should open with BatchPrint files AND Window \"(.*?)\" popup should open$")
	public void new_tab_should_open_with_BatchPrint_files_AND_Window_popup_should_open(String windowName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilesOpenedForBatchPrint();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Associations ******/
	
	@Then("^New tab should opened and LH-panel \"(.*?)\" tab of \"(.*?)\" should activated$")
	public void new_tab_should_opened_and_LH_panel_tab_of_should_activated(String activeTab, String globalTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyViewFileActivateTab(activeTab);
		else
			Assume.assumeTrue(true);
	}

	@Then("^file should open/view in RH-panel viewer$")
	public void file_should_open_view_in_RH_panel_viewer() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAssociatedFileInRHPanelTab();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Deactivate and Reactivate files ******/
	
	@When("^I select all checkboxes of \"(.*?)\" popup$")
	public void i_select_all_checkboxes_of_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectAllCheckboxesOfDeactivateFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" deactivated file message should displayed$")
	public void deactivated_file_message_should_displayed(String reactiveDeactiveMsg) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyReactivatedDeactivatedFilesValidationMessage(reactiveDeactiveMsg);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^selected \"(.*?)\" should not displayed in \"(.*?)\" listing page$")
	public void selected_should_not_displayed_in_listing_page(String file, String fileListType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileListing(file, fileListType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set \"(.*?)\" search filter type$")
	public void i_set_search_filter_type(String filterType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setSearchFilter(filterType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected \"(.*?)\" should displayed in \"(.*?)\" listing page$")
	public void selected_should_displayed_in_listing_page(String file, String fileListType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileListing(file, fileListType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I search selected file$")
	public void i_search_selected_file() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchSelectedFile();
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected file current status should set as \"(.*?)\"$")
	public void selected_file_current_status_should_set_as(String fileCurrentStatus) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileCurrentStatusOnHistoryTab(fileCurrentStatus);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" reactivated file message should displayed$")
	public void reactivated_file_message_should_displayed(String reactiveDeactiveMsg) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyReactivatedDeactivatedFilesValidationMessage(reactiveDeactiveMsg);
		else
			Assume.assumeTrue(true);
	}

	@When("^I remove \"(.*?)\" filter$")
	public void i_remove_filter(String filterType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.removeSearchFilter(filterType);
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Customize Status ******/
	
	@When("^I edit color and Font of selected status to \"(.*?)\" and \"(.*?)\" respectively with Cell Record \"(.*?)\"$")
	public void i_edit_color_and_Font_of_selected_status_to_and_respectively_with_Cell_Record(String color, String font, String applyToRow) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setDocStatusFontAndColor(color, font, applyToRow);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Save button of popup$")
	public void i_click_on_Save_button_of_popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnSave();
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^selected file status background color \"(.*?)\" and font \"(.*?)\" with Cell Record \"(.*?)\"$")
	public void selected_file_status_background_color_and_font_with_Cell_Record(String statusColor, String statusFont, String cellRecordFlag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocStatusFontAndColor(statusColor, statusFont, cellRecordFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I reset \"(.*?)\" Customize Status$")
	public void i_reset_Customize_Status(String defaultSetButton) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setCustomizedStatusAsDefault(defaultSetButton);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected file status set as default$")
	public void selected_file_status_set_as_default() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCustomizedStatusSetAsDefault();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** History ******/
	
	@When("^I select file for perform History widget in \"(.*?)\" type$")
	public void i_select_file_for_perform_History_widget_in_type(String historyType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFileForPerformHistoryWidget(historyType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" type selected in dropdown list of History tab$")
	public void type_selected_in_dropdown_list_of_History_tab(String historyType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyHistoryDropdownListType(historyType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^file all \"(.*?)\" history should displayed as \"(.*?)\"$")
	public void file_all_history_should_displayed_as(String historyType, String historyStatus) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllHistoryWithSelectType(historyStatus);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I close History tab page$")
	public void i_close_History_tab_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.closeHistoryPage();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I change status And I entered Status change Reason \"(.*?)\" And I click on \"(.*?)\" button of popup$")
	public void i_change_status_And_I_entered_Status_change_Reason_And_I_click_on_button_of_popup(String statusChangeNote, String changeStatusBtn) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeStatusAndNoteAndClickOnChangeStatus(statusChangeNote, changeStatusBtn);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^updated status should set to selected document$")
	public void updated_status_should_set_to_selected_document() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusOnFilesDocument();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Old Status value and New Status value should displayed on \"(.*?)\" history page$")
	public void old_Status_value_and_New_Status_value_should_displayed_on_history_page(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusValueOnStatusHistoryPage();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^file Latest Revision should displayed on top row$")
	public void file_Latest_Revision_should_displayed_on_top_row() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileLatestRevisionOnHistoryTab();
		else
			Assume.assumeTrue(true);
	}
	
	
	/****** Upload Files with Action ******/
	
	@When("^I upload files for ([^\"]*) user with \"(.*?)\" action in ([^\"]*) project \"(.*?)\" folder$")
	public void i_upload_files_for_user_with_action_in_project_folder(String userID, String action, String project, String folder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadFilesWithAction(userID, action, project, folder);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set compare Files Flag$")
	public void i_set_compare_Files_Flag() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.compareFilesFlag();
		else
			Assume.assumeTrue(true);
	}
}