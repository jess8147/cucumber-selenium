package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.adoddle.p2.scripts.ManageFormStatusScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ManageFormStatus {
	ManageFormStatusScripts formStatusScripts = new ManageFormStatusScripts();

	@When("^I right click on Project as ([^\"]*) And I clicked on \"(.*?)\" AND I clicked on \"(.*?)\" option into context menu List$")
	public void i_right_click_on_Project_as_And_I_clicked_on_AND_I_clicked_on_option_into_context_menu_List(
			String project, String arg2, String formStatuses) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnFormStatuses(project, formStatuses);
		else
			Assume.assumeTrue(true);
	}

	@When("^I create new all required Form Statuses$")
	public void i_create_new_all_required_Form_Statuses() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.createFormStatuses();
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit color and Font of \"(.*?)\" form status to \"(.*?)\" and \"(.*?)\" respectively with Cell Record \"(.*?)\"$")
	public void i_edit_color_and_Font_of_status_to_and_respectively_with_Cell_Record(String status, String color, String font, String applyTo) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.setFormStatusFontAndColor(status, color, font, applyTo);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" Fromtype \"(.*?)\" image link$")
	public void i_click_on_Fromtype_image_link(String formTitle, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnEditTemplateImageLink(formTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^New \"(.*?)\" popup should be opened$")
	public void new_popup_should_be_opened(String popupTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.verifyEditAppSettingsPopup(popupTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I checked Created \"(.*?)\" checkboxes And \"(.*?)\" checkbox disabled$")
	public void i_checked_Created_checkboxes(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.selectCreatedFormCheckboxes();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Save button of Edit App setting popup$")
	public void i_click_on_Save_button_of_Edit_App_setting_popup()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnSave();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Cancel button$")
	public void i_click_on_Cancel_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnCancel();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Project as ([^\"]*) AND I click on Folder \"(.*?)\" AND I click on \"(.*?)\" Form type$")
	public void i_click_on_Project_as_AND_I_click_on_Folder_AND_I_click_on_Form_type(
			String project, String formFolder, String formType)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnProjectAndFolderAndFormType(project,
					formFolder, formType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" button is enabled$")
	public void button_is_enabled(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.verifyCreateFormEnabled();
		else
			Assume.assumeTrue(true);
	}

	/*@When("^I click on \"(.*?)\" button on Project Forms page$")
	public void i_click_on_button_createform(String createFormLinkText)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickCreateForm(createFormLinkText);
		else
			Assume.assumeTrue(true);
	}
*/
	@Then("^New \"(.*?)\" popup should open$")
	public void popup_should_open(String createFormText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.verifyCreateFormPopup(createFormText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered \"(.*?)\" Name And I Cleared text of \"(.*?)\" And I also Cleared \"(.*?)\" text$")
	public void i_have_entered_Name_And_I_Cleared_text_of_And_I_also_Cleared_text(
			String arg1, String arg2, String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.enterFormTitleText();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" dropdown list$")
	public void i_click_on_dropdown_list(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnFormStatusDropdownList();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form Status dropdown list should not contains \"(.*?)\" accessibility$")
	public void form_Status_dropdown_list_should_not_contains_accessibility(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.verifyAccessableFormStatuses();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" accessibility And entered Current Date$")
	public void i_select_accessibility_And_entered_Current_Date(
			String formStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.selectFormStatusAndCurrentDate(formStatus);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Send button$")
	public void i_click_on_Send_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnSend();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Uploaded form status should be \"(.*?)\" and background color \"(.*?)\" and font \"(.*?)\" with Cell Record \"(.*?)\"$")
	public void uploaded_form_status_should_be_and_background_color_and_font_color_with_cell_record(
			String formStatus, String color, String font, String recordFlag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.verifyCreatedFormAndFormStatus(formStatus, color, font, recordFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click on Created Form And drag mouse to \"(.*?)\" And I click on \"(.*?)\" into context click menu list$")
	public void i_right_click_on_Created_Form_And_drag_mouse_to_And_I_click_on_into_context_click_menu_list(
			String edit, String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.clickOnFormAndEditAndStatus(edit, status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify Form Status dropdown list with created Form Status that should not contains \"(.*?)\" accessibility$")
	public void i_verify_Form_Status_dropdown_list_with_created_Form_Status_that_should_not_contains_accessibility(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.verifyChangeableFormStatus();
		else
			Assume.assumeTrue(true);
	}

	@When("^I change selected Form Status into \"(.*?)\" And I entered Status change Reason Note \"(.*?)\" And I click on Change Status button$")
	public void i_change_selected_Form_Status_into_And_I_entered_Status_change_Reason_Note_And_I_click_on_Change_Status_button(
			String formStatus, String formStatusChangeNote) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			formStatusScripts.changeFormStatusAndNoteAndClickOnChangeStatus(
					formStatus, formStatusChangeNote);
		else
			Assume.assumeTrue(true);
	}

	@When("^I deactivated created all Form Statuses using secondary user$")
	public void i_deactivated_created_all_Form_Statuses_using_secondary_user()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			formStatusScripts.logOut();
			formStatusScripts.login(System.getProperty("secondary.username"),
					System.getProperty("secondary.password"));
			formStatusScripts.navigateTab(LandingPage.lnk_Projects);
			formStatusScripts.deactivateFormStatuses();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^It should not be displayed in \"(.*?)\" dropdown list when New Form should be created in folder \"(.*?)\" of \"(.*?)\" Form type$")
	public void it_should_not_be_displayed_in_dropdown_list_when_New_Form_should_be_created_in_folder_of_Form_type(
			String arg1, String formFolder, String formType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			formStatusScripts.navigateTab(LandingPage.lnk_ProjectForms);
			formStatusScripts
					.verifyFormStatusesDeactivate(formFolder, formType);
		} else
			Assume.assumeTrue(true);
	}
}
