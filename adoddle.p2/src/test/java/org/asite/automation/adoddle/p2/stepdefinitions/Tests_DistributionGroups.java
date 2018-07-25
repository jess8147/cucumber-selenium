package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.DistributionGroupsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DistributionGroups {
	DistributionGroupsScripts groupsScripts = new DistributionGroupsScripts();

	@When("^I right click on ProjectName as ([^\"]*) Drag mouse to \"(.*?)\" And Click on \"(.*?)\"$")
	public void i_right_click_on_ProjectName_as_Drag_mouse_to_And_Click_on(String project, String edit, String distGrps)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnDistributionGroups(project, edit, distGrps);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on AddDistributionGroup button And I create \"(.*?)\" type Distribution Group for \"(.*?)\" AND \"(.*?)\" AND \"(.*?)\"$")
	public void i_click_on_AddDistributionGroup_button_And_I_create_type_Distribution_Group_for_AND_AND(
			String groupType, String admin, String accessToUse, String noAccess) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.createDocumentDistributionGroups(groupType, admin, accessToUse, noAccess);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Save All button$")
	public void i_click_on_Save_All_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnSaveAll();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" and \"(.*?)\" groups should be displayed and \"(.*?)\" group should not displayed$")
	public void and_groups_should_be_displayed_and_group_should_not_displayed(String admin, String accessToUse,
			String noAccess) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyDocumentDistributionGroups(admin, accessToUse, noAccess);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button of upload popup$")
	public void i_click_on_button_of_upload_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnDistributeFiles();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" AND \"(.*?)\" textboxes should be available on upload popup$")
	public void and_textboxes_should_be_available_on_upload_popup(String To, String Subject) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyDistributeTextboxes(To, Subject);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select Admin Distribution Group in textbox$")
	public void i_select_Admin_Distribution_Group_in_textbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.enterAdminDocDistributionGroup();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^File should be uploaded successfully And assigned all actions should displayed in \"(.*?)\" column popup$")
	public void file_should_be_uploaded_successfully_And_assigned_all_actions_should_displayed_in_column_popup(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyUploadedFileAndAdminGroupActions();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select AccessToUse Distribution Group in textbox$")
	public void i_select_AccessToUse_Distribution_Group_in_textbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.enterAccessToUseDocDistributionGroup();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^File should be uploaded successfully And \"(.*?)\" action should be assigned to uploaded file without \"(.*?)\"$")
	public void file_should_be_uploaded_successfully_And_action_should_be_assigned_to_uploaded_file_without(
			String actionType, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyUploadedFileAndAccessToUseGroupActions(actionType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I deactivate created all groups$")
	public void i_deactivate_created_all_groups() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.logOut();
			groupsScripts.login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
			groupsScripts.navigateTab(LandingPage.lnk_Projects);
			groupsScripts.deactivateDistributionGroup();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Groups should not displayed in \"(.*?)\" popup$")
	public void groups_should_not_displayed_in_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyDistributionGroupsDeactivated();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^It should not displayed in Distribution List when files should be upload on folderName \"(.*?)\" with Distribution$")
	public void it_should_not_displayed_in_Distribution_List_when_files_should_be_upload_on_folderName_with_Distribution(
			String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.navigateTab(LandingPage.lnk_Files);
			groupsScripts.verifyDistributionGroupsDeactivatedOnFileUpload(folder);
		} else
			Assume.assumeTrue(true);
	}

	/******* Form Distribution Groups *******/

	@When("^I click on AddDistributionGroup button And I create \"(.*?)\" type Distribution Group for \"(.*?)\" AND \"(.*?)\" AND \"(.*?)\" for FormDistributionGroups$")
	public void i_click_on_AddDistributionGroup_button_And_I_create_type_Distribution_Group_for_AND_AND_for_FormDistributionGroups(
			String groupType, String admin, String accessToUse, String noAccess) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.createFormDistributionGroups(groupType, admin, accessToUse, noAccess);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" and \"(.*?)\" groups should be displayed and \"(.*?)\" group should not displayed for FormDistributionGroups$")
	public void and_groups_should_be_displayed_and_group_should_not_displayed_for_FormDistributionGroups(String admin,
			String accessToUse, String noAccess) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyFormDistributionGroups(admin, accessToUse, noAccess);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Distribute button of Create Form popup$")
	public void i_click_on_Distribute_button_of_Create_Form_popup() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnDistributeForm();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Entered groups \"(.*?)\" text field should be displayed$")
	public void entered_groups_text_field_should_be_displayed(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyDistributeFormToField();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select Admin Form Distribution Group in textbox$")
	public void i_select_Admin_Form_Distribution_Group_in_textbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.enterAdminFormDistributionGroup();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I have entered \"(.*?)\" Name And I Cleared \"(.*?)\" text And entered Current Date$")
	public void i_have_entered_Name_And_I_Cleared_text_And_entered_Current_Date(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.enterFormTitleAndClearGropCodeText();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Send button of CreateForm popup$")
	public void i_click_on_Send_button_of_CreateForm_popup() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnSend();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be Created successfully And assigned all actions should displayed in \"(.*?)\" column popup$")
	public void form_should_be_Created_successfully_And_assigned_all_actions_should_displayed_in_column_popup(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyCreatedFormAndAdminFormGroupActions();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Create \"(.*?)\" Form link$")
	public void i_click_on_Create_Form_link(String newFormLink) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnCreateNewFormLink(newFormLink);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I search \"(.*?)\" Form Type And I click on searched formType$")
	public void i_search_Form_Type_And_I_click_on_searched_formType(String formType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.searchFormTypeAndSelectForm(formType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I search \"(.*?)\" Form Type And I click on searched formType ADDED$")
	public void i_search_Form_Type_And_I_click_on_searched_formType_ADDED(String formType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.searchFormTypeAndSelectForm2(formType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button And handle alert popup$")
	public void i_click_on_SaveDraft_button_And_handle_alert_popup(String saveDraft) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnSaveDraftAndHandlePopup(saveDraft);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be Created successfully And \"(.*?)\" Action should be assigned to created Form with assigned \"(.*?)\"$")
	public void form_should_be_Created_successfully_And_Action_should_be_assigned_to_created_Form_with_assigned(
			String actionType, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyCreatedFormDraftAndAction(actionType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select AccessToUse Form Distribution Group in textbox$")
	public void i_select_AccessToUse_Form_Distribution_Group_in_textbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.enterAccessToUseFormDistributionGroup();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be Created successfully And \"(.*?)\" Action should be assigned to uploaded file without \"(.*?)\"$")
	public void form_should_be_Created_successfully_And_Action_should_be_assigned_to_uploaded_file_without(
			String actionType, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyCreatedFormAndAccessToUseFormGroupActions(actionType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I search NoAccess Form Distribution Group in textbox$")
	public void i_search_NoAccess_Form_Distribution_Group_in_textbox() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.enterNoAccessFormDistributionGroup();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^It should not displayed in Form Distribution List$")
	public void it_should_not_displayed_in_Form_Distribution_List() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.verifyNoAccessFormGroupDeactived();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I close Form$")
	public void i_close_Form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.clickOnCloseForm();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^It should not displayed in Distribution List when I create Form in FolderName \"(.*?)\" in \"(.*?)\" Form type$")
	public void it_should_not_displayed_in_Distribution_List_when_I_create_Form_in_FolderName_in_Form_type(
			String formFolder, String formType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			groupsScripts.navigateTab(LandingPage.lnk_ProjectForms);
			groupsScripts.verifyDistributionGroupsDeactivatedOnProjectForms(formFolder, formType);
		} else
			Assume.assumeTrue(true);
	}
}