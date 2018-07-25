package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ManageFormTemplateScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ManageFormTemplate {

	ManageFormTemplateScripts scripts  = new ManageFormTemplateScripts();
	
	@When("^I create new project \"(.*?)\"$")
	public void i_create_new_project(String projectTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createNewTestProject(projectTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select form template and click on \"(.*?)\" button$")
	public void i_select_form_template_and_click_on_button(String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.addFormTemplateToProject(buttonText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I search form template and right click$")
	public void i_search_form_template_and_right_click() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchFormTemplateAndContextClick();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" option on context menu$")
	public void i_select_option(String activeText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.activateForm(activeText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Status should change to \"(.*?)\"$")
	public void status_should_change_to(String status) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyStatusChange(status);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I search created Project$")
	public void i_search_created_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchCreatedProject();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" option on LH menu$")
	public void i_click_on_option_on_LH_menu(String optionText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickLHOptionMenu(optionText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" dropdown and search Form$")
	public void i_click_on_dropdown_and_search(String dropdownTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickFormNameDropdownAndSearchForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select correct form and click on \"(.*?)\" Link$")
	public void i_select_correct_form_and_click_on_Link(String closeLink) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormAndClickClose(closeLink);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should get filtered for selected form$")
	public void should_get_filtered_for_selected_form(String rolePermissions) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsFiltered();
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I clik on above created project$")
	public void i_have_focus_on_created_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectCreatedProject();
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I click on \"(.*?)\" option in LH panel$")
	public void i_click_on_New_option_in_LH_panel(String newOption) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickNewOptionOnLHPanel(newOption);
		else
			Assume.assumeTrue(true);
	}

	@When("^I search form  on \"(.*?)\" window$")
	public void i_search_form_on_window(String windowTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchFormOnCreateFormWindow();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Form should get filtered on Create Form window$")
	public void form_should_get_filtered_on_create_form_window() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsFilteredOnCreateFormWindow();
		else
			Assume.assumeTrue(true);
	}

	/***********************************************************************************************************************************/
	
	@When("^I search form template and click \"(.*?)\" icon$")
	public void i_search_form_template_and_click_icon(String editIcon) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchFormAndClickEditIcon();
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit Form Name Form Group Code and Form Group Name$")
	public void i_edit_formname_formgroupcode_and_formgroupname() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editFormNameGroupAndCodeGroupName();
		else
			Assume.assumeTrue(true);
	}

	@When("^I download xsn file rename it and re-upload it$")
	public void i_download_xsn_file_rename_it_and_re_upload_it() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.downloadXsnAndUpload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit all Form Template Settings to \"(.*?)\" for ([^\"]*)$")
	public void i_edit_all_form_template_settings_for_project(String radioFlag, String project) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllFormTemplateSettings(radioFlag, project);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit all Controller Settings to \"(.*?)\"$")
	public void i_edit_all_Controller_Settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllControllerSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit all Response Settings to \"(.*?)\"$")
	public void i_edit_all_Response_Settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllResponseSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I edit Actions Required to \"(.*?)\" for all actions$")
	public void i_edit_Actions_Required_to_for_all_actions(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllActionRequiredSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit all Distribution settings to \"(.*?)\"$")
	public void i_edit_all_Distribution_settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllDistributionSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit all Edit and Forward settings to \"(.*?)\"$")
	public void i_edit_all_Edit_and_Forward_settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAndForwardSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit all Attachments settings to \"(.*?)\"$")
	public void i_edit_all_Attachments_settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllAttachmentSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit all Associations settings to \"(.*?)\"$")
	public void i_edit_all_associations_settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllAssociationSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit all Form Statuses settings to \"(.*?)\"$")
	public void i_edit_all_Form_Statuses_settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllFormStatusSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit all Additional Form Settings to \"(.*?)\"$")
	public void i_edit_all_Additional_Form_Settings_to(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAllAdditionalFormSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I save Form Template Settings changes$")
	public void i_save_form_template_settings_changes() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.saveTheFormTemplateSettings();
		else
			Assume.assumeTrue(true);
	}

	@Then("^App Settings should get saved successfully to \"(.*?)\"$")
	public void app_Settings_should_get_saved_successfully(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySavedFormTemplateSettings(radioFlag);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on updated Form Name$")
	public void i_click_on_updated_form_name() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnUpdatedFormName();
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I search updated form  on \"(.*?)\" window$")
	public void i_search_updated_form_on_window(String windowTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchUpdatedFormOnCreateFormWindow();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button of App Settings window$")
	public void i_click_on_x_button_of_app_settings_window(String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.closeAppSettingsWindow();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Controller dropdown should be available \"(.*?)\"$")
	public void controller_dropdown_should_be_available_on_create_form_window(String radioFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyControllerOptionAvailability(radioFlag);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select valid user from Controller dropdown$")
	public void i_select_valid_user_from_Controller_dropdown() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectValidUserFromControllerDropdown();
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter mandatory values to create form$")
	public void i_enter_mandatory_values_to_create_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterMandatoryValuesToCreateForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^I attach external file to the form and click on Send button with Controller \"(.*?)\"$")
	public void i_attach_external_file_to_the_form(String controlFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.attachExternalFileToControllerForm();
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^Form with Controller \"(.*?)\" should be created successfully$")
	public void form_should_be_created_successfully(String controlFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyControllerFormIsCreated(controlFlag);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I search created form and click on it$")
	public void i_search_created_form_and_click_on_it() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchCreatedFormAndClick();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Created Form should be viewed$")
	public void created_Form_should_be_viewed() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsViewed();
		else
			Assume.assumeTrue(true);
	}

	@Then("^User should be able to edit the form$")
	public void user_should_be_able_to_edit_the_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editORIForm();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^User should be able to close project$")
	public void user_should_be_able_to_close_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.closeTestProject();
		else
			Assume.assumeTrue(true);
	}

}
