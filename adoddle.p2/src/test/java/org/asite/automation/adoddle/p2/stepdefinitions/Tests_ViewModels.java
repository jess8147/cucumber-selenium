package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.ViewModelsScripts;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ViewModels {
	ViewModelsScripts	scripts	= new ViewModelsScripts();

	/******* ListView ThumbView *******/

	@When("^I click on Models tab$")
	public void i_click_on_Models_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.navigateTabByText(AsiteMenu.Models.toString());
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model listing should be displayed as ThumbView in style$")
	public void model_listing_should_be_displayed_as_ThumbView_in_style() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyThumbViewListing();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model listing should be displayed as ListView in style$")
	public void model_listing_should_be_displayed_as_ListView_in_style() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyListViewListing();
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Favourite models *******/

	@When("^I get total \"(.*?)\" count$")
	public void i_get_total_count(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.beforeFavouriteModelsCount();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I search any one model and I set model \"(.*?)\" Favourite$")
	public void i_search_any_one_model_and_I_set_model(String addAsFavourite) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectAddAsFavouriteModel(addAsFavourite);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on LH-Panel Of Favorite Models tab$")
	public void i_click_on_LH_Panel_Of_Favorite_Models_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnFavouriteModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected Model should be displayed on Favorite Models tabs$")
	public void selected_Model_should_be_displayed_on_Favorite_Models_tabs() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelOnFavouriteModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^It should be displayed as \"(.*?)\" And total Favorite Models Count should be increased$")
	public void it_should_be_displayed_as_And_total_Favorite_Models_Count_should_be_increased(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAddAsFavouriteModelAndCountIncreased();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" Models tab$")
	public void i_click_on_Models_tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnAllModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I remove selected Model \"(.*?)\" Favourite into \"(.*?)\" Favourite$")
	public void i_remove_selected_Model_into(String arg1, String removeAsFavourite) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectRemoveAsFavouriteModel(removeAsFavourite);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected Model should not displayed into Favorite Models tab And total Favorite Models Count should be decreased$")
	public void selected_Model_should_not_displayed_into_Favorite_Models_tab_And_total_Favorite_Models_Count_should_be_decreased() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyRemovedModelOnFavouriteModelsTab();
			scripts.verifyFavouriteModelsTabCountDecreased();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected Model should displayed into All Models tab And It should be displayed as \"(.*?)\"$")
	public void selected_Model_should_displayed_into_All_Models_tab_And_It_should_be_displayed_as(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelRemoveAsFavouriteModel();
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Recent models *******/

	@When("^I get All Models Name List$")
	public void i_get_All_Models_Name_List() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.getAllModelsNameList();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Recent Models tab$")
	public void i_click_on_Recent_Models_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnRecentModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I get All Recent Models Name List And I search any one Model in \"(.*?)\" Models tab$")
	public void i_get_All_Recent_Models_Name_List_And_I_search_any_one_Model_in_Models_tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.getAllRecentModelsNameList();
			scripts.searchModelInRescentModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^searched Model should not displayed in Recent Models tab$")
	public void searched_Model_should_not_displayed_in_Recent_Models_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelNotListedInRecentModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I search searched Model in \"(.*?)\" Models tab$")
	public void i_search_searched_Model_in_Models_tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchModelInAllModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should be opened and searched Model should be View in new opened tab$")
	public void new_tab_should_be_opened_and_searched_Model_should_be_View_in_new_opened_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyOpenedModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I close New opened tab$")
	public void i_close_New_opened_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.closeViewModelWindow();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^searched Model should be displayed in \"(.*?)\" Models tab$")
	public void searched_Model_should_be_displayed_in_Models_tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelInRecentModelsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Single Model *******/

	@When("^I clicked on \"(.*?)\" button And I select IFC File to upload$")
	public void i_clicked_on_button_And_I_select_IFC_File_to_upload(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnSelectFilesAndUploadIFC();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter all mandatory fields for IFC upload And set Revision as \"(.*?)\"$")
	public void i_enter_all_mandatory_fields_for_IFC_upload_And_set_Revision_as(String rev) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.enterMendatoryAttributes(rev);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should open for IFC upload$")
	public void popup_should_open_for_IFC_upload(String activityCentre) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyActivityCentrePopup(activityCentre);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" AND \"(.*?)\" progress should be available on \"(.*?)\" popup$")
	public void and_progress_should_be_available_on_popup(String arg1, String arg2, String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyMergingAndLoadingProcessOnActivityCentrePopup();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button of Activity Centre popup$")
	public void i_click_on_button_of_Activity_Centre_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnOK();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^IFC File should be uploaded successfully And Revison should be set as \"(.*?)\" And Issue number also set as \"(.*?)\"$")
	public void ifc_File_should_be_uploaded_successfully_And_Revison_should_be_set_as_And_Issue_number_also_set_as(String rev, String issueNumber) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUploadedIFCFileWithRevisionAndIssueNum(rev, issueNumber);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^File type icon displayed as \"(.*?)\"$")
	public void file_type_icon_displayed_as(String ifcFileType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyIFCFileTypeIcon(ifcFileType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button And I upload same IFC File second issue for content change$")
	public void i_clicked_on_button_And_I_upload_same_IFC_File_second_issue_for_content_change(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnSelectFilesAndUploadIFCForContentChange();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I search uploaded IFC file as Single Model in Models tab$")
	public void i_search_uploaded_IFC_file_as_Single_Model_in_Models_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchSingleModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on IFC file$")
	public void i_click_on_IFC_file() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnIFCFile();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^IFC file as \"(.*?)\" should be displayed in Models tab$")
	public void ifc_file_as_Single_Model_should_be_displayed_in_Models_tab(String modelType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifySingleModel(modelType);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^IFC file should be viewed in new window$")
	public void ifc_file_should_be_viewed_in_new_window() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyIFCFileAsSingleModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^uploaded both version should be displayed in left panel$")
	public void uploaded_both_version_should_be_displayed_in_left_panel() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUploadedAllVersionOfSingleModel();
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Models ListView Hyperlinks *******/

	@When("^I search any one model into Models listing$")
	public void i_search_any_one_model_into_Models_listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchRandomModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I check Model \"(.*?)\" value on \"(.*?)\" hyperlink$")
	public void i_check_Model_value_on_hyperlink(String arg1, String hyperLinkType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.getHyperLinkValue(hyperLinkType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" hyperlink of Model list View$")
	public void i_click_on_hyperlink_of_Model_list_View(String hyperLinkType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnHyperLink(hyperLinkType);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" name should displayed on \"(.*?)\" popup$")
	public void name_should_displayed_on_popup(String hyperLinkType, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyHperLinkUserOnContactDetailsPopup(hyperLinkType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I closed opened \"(.*?)\" popup$")
	public void i_closed_opened_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.closePopup();
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Models tab ListView *******/

	@When("^I Click on \"(.*?)\" button From LH Panel for \"(.*?)\" listing$")
	public void i_Click_on_button_From_LH_Panel(String arg1, String listingStyle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnAddModelButton(listingStyle);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I filled all mendatory fields And click on \"(.*?)\" button$")
	public void i_filled_all_mendatory_fields_And_click_on_button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createNewModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^loggedIn UserName should be set to \"(.*?)\", \"(.*?)\" AND \"(.*?)\" hyperlink of model And CurrentDate should be set to \"(.*?)\", \"(.*?)\" AND \"(.*?)\"$")
	public void loggedIn_UserName_should_be_set_to_AND_hyperlink_of_model_And_CurrentDate_should_be_set_to_AND(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelHyperLinkAndCurrentDateForModelCreater();
			scripts.verifyModelTimeAfterCreatingModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on \"(.*?)\" Model And click on \"(.*?)\"$")
	public void i_right_click_on_Model_And_click_on(String model, String menuOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.contextClickOnModelAndSelectOption(model, menuOption);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Wrokset And select \"(.*?)\" from local And click on \"(.*?)\" button$")
	public void i_select_Wrokset_And_select_from_local_And_click_on_button(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.uploadIFCFile();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^loggedIn UserName should be set to \"(.*?)\" AND \"(.*?)\" hyperlink And \"(.*?)\" AND \"(.*?)\" also should be changed but \"(.*?)\" hyperlink and \"(.*?)\" coloum should not changed$")
	public void loggedIn_UserName_should_be_set_to_AND_hyperkink_And_AND_also_should_be_changed_but_hyperlink_and_coloum_should_not_changed(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelHyperLinkAndCurrentDateAfterFileUpload();
			scripts.verifyModelTimeAfterFileUpload();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I login with third user$")
	public void i_login_with_third_user() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.login(System.getProperty("multi.project.username"), System.getProperty("multi.project.password"));
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model All coloums value should be same as Secondary User all coloums value$")
	public void model_All_coloums_value_should_be_same_as_Secondary_User_all_coloums_value() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelHyperLinkAndCurrentDateTimeBeforeView();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Model Name$")
	public void i_click_on_Model_Name() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should be opened and Model should be View in new opened tab$")
	public void new_tab_should_be_opened_and_Model_should_be_View_in_new_opened_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyViewModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^loggedIn UserName should be set to \"(.*?)\" hyperlink AND \"(.*?)\" should be changed but \"(.*?)\" AND \"(.*?)\" hyperlink and \"(.*?)\" AND \"(.*?)\" should not changed$")
	public void and_should_be_changed_And_Model_details(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelHyperLinkAndCurrentDateAfterView();
			scripts.verifyModelTimeAfterView();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I login with forth user$")
	public void i_login_with_forth_user() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.login(ResourceHandler.loadProperty("forth.user.username"), ResourceHandler.loadProperty("forth.user.password"));
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model All coloums value should be same as Third User all coloums value$")
	public void model_All_coloums_value_should_be_same_as_Third_User_all_coloums_value() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelHyperLinkAndCurrentDateTimeBeforeEditModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit Model Name And click on \"(.*?)\" button$")
	public void i_edit_Model_Name_And_click_on_button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.editCreatedModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^loggedIn User should be set to \"(.*?)\" AND \"(.*?)\" hyperlink and \"(.*?)\" AND \"(.*?)\" should be changed but \"(.*?)\" AND \"(.*?)\" should not changed$")
	public void i_verify_Model_details_for(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelHyperLinkAndCurrentDateTimeAfterEditModel();
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Models tab ThumbView *******/

	@Then("^Model should be created And should be available in Models \"(.*?)\" listing$")
	public void model_should_be_created_And_should_be_available_in_Models_listing(String listingStyle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelOnModelListing(listingStyle);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I mousehover on Model and click on \"(.*?)\"$")
	public void i_mousehover_on_Model_and_click_on(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.mouseHoverAndUploadFileInModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model All coloums value should be same as First User all coloums value for \"(.*?)\" listing$")
	public void model_All_coloums_value_should_be_same_as_First_User_all_coloums_value_for_listing(String listingStyle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyModelHyperLinkAndCurrentDateTimeBeforeFileUpload();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I mousehover on Model and click on \"(.*?)\" link of Model$")
	public void i_mousehover_on_Model_and_click_on_link_of_Model(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.mouseHoverAndClickOnViewModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I mousehover on Model and click on \"(.*?)\" link$")
	public void i_mousehover_on_Model_and_click_on_link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.mouseHoverAndClickOnProperties();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Edited Model should be displayed in Model \"(.*?)\" listing$")
	public void edited_Model_should_be_displayed_in_Model_listing(String listingStyle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyEditedModelOnModelListing(listingStyle);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^I deactivate Mapping Folder$")
	public void i_deactivate_Mapping_Folder() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.navigateTab(LandingPage.lnk_Files);
			scripts.deactivateMappingFolder();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model edited all fields should displayed in \"(.*?)\" popup$")
	public void model_edited_all_fields_should_displayed_in_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyEditedModel();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I close Properties popup$")
	public void i_close_Properties_popup() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.closePropertiesPopup();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I mousehover on edited Model and click on \"(.*?)\" link$")
	public void i_mousehover_on_edited_Model_and_click_on_link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.mouseHoverOnEditModelAndSelectProperties();
		}
		else
			Assume.assumeTrue(true);
	}
}
