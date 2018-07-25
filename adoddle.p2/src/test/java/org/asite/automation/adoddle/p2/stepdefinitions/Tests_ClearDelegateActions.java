package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ClearDelegateActions;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ClearDelegateActions {

	ClearDelegateActions scripts = new ClearDelegateActions();

	@Given("^I have focus on Project as ([^\"]*) AND I click on Folder \"(.*?)\" AND I click on \"(.*?)\" Form type$")
	public void i_have_focus_on_Project_as_Project_Name_AND_I_click_on_Folder_AND_I_click_on_Form_type(String project,
			String fFolder, String fType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.focusFormTemplate(project, fFolder, fType);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have selected distribution group as \"(.*?)\" into \"(.*?)\"$")
	public void i_have_selected_distribution_group_as_into(String gName, String formInstance) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectDistributionGroup(gName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have filled all mandatory form fields$")
	public void i_have_filled_all_mandatory_form_fields() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			scripts.inputMandatoryFormAttributes();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have attached atleast one document for form$")
	public void i_have_attached_atleast_one_document_for_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.attachFilesToForm();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\" button on create form window$")
	public void i_Click_on_button_on_create_form_window(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickSaveButton();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Form should created sucessfully AND Available in \"(.*?)\" listing$")
	public void form_should_created_sucessfully_AND_Available_in_listing(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateCreatedForm();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have search created form \"(.*?)\" AND I hover actions link$")
	public void i_have_search_created_form_AND_I_hover_actions_link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchAndValidateForm();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I Validate all actions with due days$")
	public void i_Validate_all_actions_with_due_days() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateFormActions();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I mouse hover form action AND I click action \"(.*?)\"$")
	public void i_mouse_hover_form_action_AND_I_click_action(String fAction) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchAndClickActions(fAction);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Pop-up \"(.*?)\" should open$")
	public void pop_up_should_open(String eleText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyPopup(eleText);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I perform \"(.*?)\" form action sucessfully$")
	public void i_perform_form_action_sucessfully(String fAction) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.performActions(fAction);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have search created form \"(.*?)\" AND I validated assigned actions count as \"(.*?)\"$")
	public void i_have_search_created_form_AND_I_validated_assigned_actions_count_as(String fName, String aCount)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validatedAssignedActions(aCount);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I click form title$")
	public void i_click_form_title() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormAndOpen();
		} else
			Assume.assumeTrue(true);

	}

	@Then("^form \"(.*?)\" should open in HTML Viewer$")
	public void form_should_open_in_HTML_Viewer(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormIsViewed();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I click \"(.*?)\" AND I select option \"(.*?)\"$")
	public void i_click_AND_I_select_option(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickActionAndSelectOption();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I navigate left panel AND I click on History$")
	public void i_navigate_left_panel_AND_I_click_on_History() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.naviagteLeftPanelHistoryTab();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form history should load sucessfully$")
	public void form_history_should_load_sucessfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormHistoryResults();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Select filter type as \"(.*?)\" AND Message id as \"(.*?)\"$")
	public void i_Select_filter_type_as_AND_Message_id_as(String fType, String MsgID) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.filterFormHistoryResults(MsgID);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Atleast one result should available in list sucessfully$")
	public void atleast_one_result_should_available_in_list_sucessfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.postFilterFormHistoryValidation();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I validated form actions sucessfully AND I selected all incomplete actions$")
	public void i_validated_form_actions_sucessfully_AND_I_selected_all_incomplete_actions() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAssignedFormActions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Popup Should open$")
	public void popup_Should_open(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyPopupElement();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I click link AND I select option as \"(.*?)\"$")
	public void i_click_delegate_link_AND_I_select_option_as(String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickAndSelectOption(option);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I select user as ([^\"]*) into To field AND Assign action due date as \"(.*?)\"$")
	public void i_select_user_as_RFI_Bloggs_into_To_field_AND_Assign_action_due_date_as(String User, String dueDate)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectDelegateActionsAttribute(User, dueDate);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I click button \"(.*?)\"$")
	public void i_click_button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickContinueButton();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All incomplete actions delegated sucessfully$")
	public void all_incomplete_actions_delegated_sucessfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateDelegatedActions();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I validated all form assigned actions to ([^\"]*) AND I selected all incomplete actions selectionType \"(.*?)\"$")
	public void i_validated_all_form_assigned_actions_to_RFI_Bloggs_AND_I_selected_all_incomplete_actions_selectionType(
			String arg1, String selectionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAndSelectAssignedActions(selectionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click buttons \"(.*?)\" AND \"(.*?)\"$")
	public void i_Click_buttons_AND(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickContinueAndOk();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All incomplete actions cleared sucessfully$")
	public void all_incomplete_actions_cleared_sucessfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateClearedActions();
		} else
			Assume.assumeTrue(true);
	}

}