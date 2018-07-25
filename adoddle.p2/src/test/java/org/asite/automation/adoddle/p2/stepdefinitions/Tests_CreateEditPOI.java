package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.CreateEditPOIScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateEditPOI {
	CreateEditPOIScripts poiScripts = new CreateEditPOIScripts();

	@Given("^I create new testdata folder for project ([^\"]*)$")
	public void i_create_new_testdata_folder_for_project(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.createPOIFolder(project);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I set project as ([^\"]*) and poi as \"(.*?)\"$")
	public void i_set_project_as_poi_as(String project, String poi) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.setScriptData(project, poi);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new all required Purpose of Issue$")
	public void i_create_new_all_required_Purpose_of_Issue() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.createPurposeOfIssue();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Save button$")
	public void i_click_on_Save_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			poiScripts.clickOnSave();
			poiScripts.navigateTab(LandingPage.lnk_Projects);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Project Name ([^\"]*) AND I click on Folder Name \"(.*?)\" AND I click on \"(.*?)\" button$")
	public void i_click_on_Project_Name_AND_I_click_on_Folder_Name_AND_I_click_on_button(
			String projectName, String folderName, String addFiles)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.clickOnProjectAndFolderAndAddFiles(projectName, folderName);
		else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button And I select more then one Files from Local to upload$")
	public void i_clicked_on_button_And_I_select_more_then_one_Files_from_Local_to_upload(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.clickOnSelectFilesAndUpload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Bulk Apply checkbox$")
	public void i_click_on_Bulk_Apply_checkbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.bulkApplyCheckboxSelect();
		else
			Assume.assumeTrue(true);
	}

	
	
	@When("^I click on Bulk Apply checkbox on Edit Attributes popup$")
	public void i_click_on_Bulk_Apply_checkbox_on_edit_attributes() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.bulkApplyCheckboxEditAttributes();
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^PurposeOfIssue dropdown list should not contains \"(.*?)\" AND \"(.*?)\" accessibility$")
	public void purposeOfIssue_dropdown_list_should_not_contains_AND_accessibility(
			String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.verifyAccessablePOI();
		else
			Assume.assumeTrue(true);
	}

	@When("^I fill all mendatory fields$")
	public void i_fill_all_mendatory_fields() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.enterMendatoryAttributes();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Upload button$")
	public void i_click_on_Upload_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.clickOnUpload();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Files should be uploaded successfully And assign POI \"(.*?)\" should be available with uploaded files$")
	public void files_should_be_uploaded_successfully_And_assign_POI_should_be_available_with_uploaded_files(
			String poi) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.verifyUploadedFilesAndPOI(poi);
		else
			Assume.assumeTrue(true);
	}

	@When("^I multiple files select$")
	public void i_multiple_files_select() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.selectMultipleFilesCheckboxSelect();
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on selected files And drag mouse to \"(.*?)\" And I click on \"(.*?)\"$")
	public void i_right_click_on_selected_files_And_drag_mouse_to_And_I_click_on(
			String edit, String attributes) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.clickOnFilesAndEditAndAttributes(edit, attributes);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify PurposeOfIssue dropdown list with created PurposeOfIssue that should not contains \"(.*?)\" accessibility$")
	public void i_verify_dropdown_list_with_created_that_should_not_contains_accessibility(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.verifyChangeablePOI();
		else
			Assume.assumeTrue(true);
	}

	@When("^I change selected \"(.*?)\" into \"(.*?)\" And I click on \"(.*?)\" button$")
	public void i_change_selected_into_And_I_click_on_button(String arg1,
			String poi, String assignAttributes) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.changePOIAndClickOnAssignAttributes(poi,
					assignAttributes);
		else
			Assume.assumeTrue(true);
	}

	@Then("^changed POI \"(.*?)\" should be available with uploaded files$")
	public void changed_POI_should_be_available_with_uploaded_files(String poi)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			poiScripts.verifyUploadedFilesAndPOI(poi);
		else
			Assume.assumeTrue(true);
	}

	@When("^I deactivate created all \"(.*?)\"$")
	public void i_deactivate_created_all(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			poiScripts.logOut();
			poiScripts.login(System.getProperty("secondary.username"),
					System.getProperty("secondary.password"));
			poiScripts.navigateTab(LandingPage.lnk_Projects);
			poiScripts.deactivatePOI();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^It should not be displayed in \"(.*?)\" list when files should be uploaded in folderName \"(.*?)\"$")
	public void i_deactivate_created_using_Secondary_User(String arg1,
			String poiStatusFolder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			poiScripts.navigateTab(LandingPage.lnk_Files);
			poiScripts.verifyPurposeOfIssueDeactivate(poiStatusFolder);
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I deactivate all uploaded poi documents in folder$")
	public void i_deactivate_all_uploaded_poi_docs() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			poiScripts.deactivateAllDocuments();
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^POI documents should get deactivated$")
	public void poi_documents_should_get_deactivated() throws Throwable {
		System.out.println("documents deactivated in previous definition");
	}
	
}
