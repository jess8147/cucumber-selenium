package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.RecentFormsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_RecentForms {
	RecentFormsScripts scripts = new RecentFormsScripts();

	/****** Common Steps ******/

	@When("^I create new form with \"(.*?)\" action to ([^\"]*) user in ([^\"]*) project \"(.*?)\" folder in \"(.*?)\" form type$")
	public void i_create_new_form_with_action_to_auto_multi_dc_user_atest_com_user_in_Automation_Last_Login_UK_Project_project_folder_in_form_type(
			String action, String user, String project, String folderType, String formType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createForm(action, user, project, folderType, formType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I create new form in folder \"(.*?)\" and form type \"(.*?)\" with \"(.*?)\" action for ([^\"]*) user$")
	public void i_create_new_form_in_folder_and_form_type_with_action_for_Automation_user(String folderType,
			String formType, String action, String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createNewForm(folderType, formType, action, user);
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click and select context click menu option \"(.*?)\"$")
	public void i_right_click_and_select_context_click_menu_option(String contextOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectContextOption(contextOption, "");
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click and select context click menu option \"(.*?)\" AND I click on \"(.*?)\" widget$")
	public void i_right_click_and_select_context_click_menu_option_AND_I_click_on_widget(String contextOption1,
			String contextOption2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectContextOption(contextOption1, contextOption2);
		else
			Assume.assumeTrue(true);
	}

	@When("^I deSelect selected Recent Forms checkboxes for \"(.*?)\" HighchartsAxis$")
	public void i_deSelect_selected_Recent_Forms_checkboxes_for_HighchartsAxis(String highChartAxis) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.deSelectCheckList(highChartAxis);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" highcharts of Recent Forms$")
	public void i_click_on_highcharts_of_Recent_Forms(String axis) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnHighchartsAppAxis(axis);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I cleared list and map values$")
	public void i_cleared_list_and_map_values() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clearedListMapValues();
		else
			Assume.assumeTrue(true);
	}

	/****** LAST LOGIN ******/

	@When("^I get \"(.*?)\", \"(.*?)\", \"(.*?)\" AND \"(.*?)\" total Recent Forms highcharts count$")
	public void i_get_AND_total_Recent_Forms_highcharts_count(String lastLogin, String today, String yesterday,
			String pastWeek) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.getTotalHighchartsAxisAppsCount(lastLogin, today, yesterday, pastWeek);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I create new form in folder \"(.*?)\" and form type \"(.*?)\" with \"(.*?)\" action$")
	public void i_create_new_form_in_folder_and_form_type_with_action(String folderType, String formType, String action)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createNewForm(folderType, formType, action);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form should created successfully on \"(.*?)\" tab And \"(.*?)\" action assigned to given form$")
	public void form_should_created_successfully_on_tab_And_action_assigned_to_given_form(String activeTab,
			String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCreatedFormAndAssignedAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^total \"(.*?)\", \"(.*?)\", AND \"(.*?)\" count of Recent Forms axis should increased And \"(.*?)\" count same as previous$")
	public void total_AND_count_of_Recent_Forms_axis_should_increased_And_count_same_as_previous(String lastLogin,
			String today, String pastWeek, String yesterday) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyTotalHighchartsAxisAppsCount(lastLogin, today, pastWeek, yesterday);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Total Forms listing and forms count same as \"(.*?)\" highcharts of Recent Forms Count$")
	public void total_Forms_listing_and_forms_count_same_as_highcharts_of_Recent_Forms_Count(String axis)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFileListingAndCountWithHighcharts(axis);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^total \"(.*?)\" count of Recent Forms should set as (\\d+)$")
	public void total_count_of_Recent_Forms_should_set_as(String arg1, int count) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyLastLoginAppsCountAfterLogin(count);
		} else
			Assume.assumeTrue(true);
	}

	/****** flag Widget ******/

	@When("^I select more then one Forms for \"(.*?)\" flag$")
	public void i_select_more_then_one_Forms_for_flag(String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMoreForms(flag);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^selected forms flag should set as \"(.*?)\" flag$")
	public void selected_forms_flag_should_set_as_flag(String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFlagWithForms(flag);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select selected Forms for \"(.*?)\" flag$")
	public void i_select_selected_Forms_for_flag(String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMoreForms(flag);
		} else
			Assume.assumeTrue(true);
	}

	/****** View & For-Information Actions Widget ******/

	@When("^I select More then one forms for \"(.*?)\" widget$")
	public void i_select_More_then_one_forms_for_widget(String widgetType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMoreForms(widgetType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select single form for perform \"(.*?)\" widget$")
	public void i_select_single_form_for_perform_widget(String widget) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformAction(widget);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form should viewed in opened new tab$")
	public void form_should_viewed_in_opened_new_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormViewed();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed for viewed form$")
	public void action_should_completed_for_viewed_form(String forInfo) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormActionCompleted(forInfo);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed successfully for viewed form$")
	public void action_should_completed_successfully_for_viewed_form(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyForInformationAction();

		else
			Assume.assumeTrue(true);
	}

	@When("^I select single form for perform For Information action widget with \"(.*?)\" action$")
	public void i_select_single_form_for_perform_For_Information_action_widget_with_action(String action)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^selected forms should displayed on \"(.*?)\" popup$")
	public void selected_forms_should_displayed_on_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifySelectedFormsOnActionPopup();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed to selected form$")
	public void action_should_completed_to_selected_form(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormActionCompleted(actionType);
		} else
			Assume.assumeTrue(true);
	}

	/*
	 * @When(
	 * "^I select more than one forms for perform For Information action widget with \"(.*?)\" action$"
	 * ) public void
	 * i_select_more_than_one_forms_for_perform_For_Information_action_widget_with_action
	 * (String action) throws Throwable {
	 * if(Tests_CommonStepDefinitions.runTest) {
	 * scripts.selectFormForPerformForInformationAction(action); } else
	 * Assume.assumeTrue(true); }
	 */

	/****** Status-Change & Status-History Widget ******/

	@When("^I select form for perform Status widget with \"(.*?)\" action$")
	public void i_select_form_for_perform_Status_widget_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should opened in new tab$")
	public void popup_should_opened_in_new_tab(String popTxt) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyChangeStatusPopup(popTxt);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form current status, form ID and form Title should displayed in \"(.*?)\" popup$")
	public void form_current_status_form_ID_and_form_Title_should_displayed_in_popup(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormCurrentStatusIDAndTitle();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I change Form Status from current status AND entered Status Change notes AND click on ChangeStatus Button$")
	public void i_change_Form_Status_from_current_status_AND_entered_Status_Change_notes() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormStatusAndEnteredNotes();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^updated Status should displayed on View Form RH-panel$")
	public void updated_Status_should_displayed_on_View_Form_RH_panel() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUpdatedStatusOnViewForm();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^updated Status should set on selected Recent form$")
	public void updated_Status_should_set_on_selected_Recent_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUpdatedStatusOnRecentForm();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form all \"(.*?)\" history should displayed as \"(.*?)\"$")
	public void form_all_history_should_displayed_as(String arg1, String historyStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAllHistoryWithSelectType(historyStatus);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^updated new status should displayed in \"(.*?)\" history page$")
	public void updated_new_status_should_displayed_in_history_page(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUpdatedStatusValueOnStatusHistoryPage();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should opened and Form \"(.*?)\" tab should activated$")
	public void new_tab_should_opened_and_Form_tab_should_activated(String activeTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyViewFormActivateTab(activeTab);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" dropdown selected in Form History tab$")
	public void dropdown_selected_in_Form_History_tab(String historyType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyViewFormSelectedHistoryDropdown(historyType);
		else
			Assume.assumeTrue(true);
	}

	/****** Share Distribute Widget ******/

	@When("^I select form for perform Distribution widget with \"(.*?)\" action$")
	public void i_select_form_for_perform_Distribution_widget_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on selected form hyperlink of \"(.*?)\" action$")
	public void i_click_on_selected_form_hyperlink_of_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnActionHyperLink(action);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I assign \"(.*?)\" action to ([^\"]*) users$")
	public void i_assign_action_to_Automation_users(String action, String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.assignActionToUser(action, user);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should completed AND \"(.*?)\" action should assigned to given form$")
	public void action_should_completed_AND_action_should_assigned_to_given_form(String completedAction,
			String assignedAction) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormActionCompleted(completedAction);
			scripts.verifyFormActionAssigned(assignedAction);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^from \"(.*?)\" status should displayed in Distribution history page$")
	public void from_status_should_displayed_in_Distribution_history_page(String distributionStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyDistributionOnHistoryPage();
		} else
			Assume.assumeTrue(true);
	}

	/****** For Acknowledgement Hyperlink ******/

	@When("^I select form for perform For Acknowledgement action widget with \"(.*?)\" action$")
	public void i_select_form_for_perform_For_Acknowledgement_action_widget_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select checkbox for complete \"(.*?)\" action$")
	public void i_select_checkbox_for_complete_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectCheckboxOfDashboardActionPopup();
		} else
			Assume.assumeTrue(true);
	}

	@When("^click on Submit button of \"(.*?)\" popup$")
	public void click_on_Submit_button_of_popup(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnSubmitButtonOfDashboardActionPopup();
		} else
			Assume.assumeTrue(true);
	}

	/****** For Action Hyperlink ******/

	@When("^I select form for perform For Action action widget with \"(.*?)\" action$")
	public void i_select_form_for_perform_For_Action_action_widget_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I entered Remarks text of \"(.*?)\" popup$")
	public void i_entered_Remarks_text_of_popup(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.enterTextareaOfDashboardActionPopup();
		} else
			Assume.assumeTrue(true);
	}

	/****** Attach Docs Hyperlink ******/

	@When("^I select form for perform Attach Docs action widget with \"(.*?)\" action$")
	public void i_select_form_for_perform_Attach_Docs_action_widget_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I replyed to selected form without attached external Document$")
	public void i_replyed_to_selected_form_without_attached_external_Document() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.formReplyWithoutAttachment();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" action should not completed to selected form$")
	public void action_should_not_completed_to_selected_form(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormActionAssigned(actionType);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Attached external Document to selected form$")
	public void i_Attached_external_Document_to_selected_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.formReplyWithAttachment();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^document attachment should displayed on \"(.*?)\" RH-panel$")
	public void document_attachment_should_displayed_on_RH_panel(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormAttachmentOnViewForm();
		} else
			Assume.assumeTrue(true);
	}

	/****** Respond Hyperlink ******/

	@When("^I create new form in folder\"(.*?)\" and form type \"(.*?)\" with \"(.*?)\" action to User ([^\"]*) User ([^\"]*) and User ([^\"]*)$")
	public void i_create_new_form_in_folder_and_form_type_with_action_to_User_Jasmin_Prajapati_User_Automation_UK_and_User_Auto_Test(
			String folderType, String formType, String action, String userA, String userB, String userC)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createRespondForm(folderType, formType, action, userA, userB, userC);
		else
			Assume.assumeTrue(true);
	}

	@When("^I create new form in \"(.*?)\" appfolder and \"(.*?)\" form type with \"(.*?)\" action to User ([^\"]*) and ([^\"]*)$")
	public void i_create_new_form_in_appfolder_and_form_type_with_action_to_User_Automation_UK_and_Auto_Test(
			String folderType, String formType, String action, String userB, String userC) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createRespondForm(folderType, formType, action, null, userB, userC);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select form for perform Respond action widget with \"(.*?)\" action$")
	public void i_select_form_for_perform_Respond_action_widget_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			// scripts.selectFormForPerformRespondAction();
			scripts.selectFormForPerformAction(action);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should opened AND User ([^\"]*) and User ([^\"]*) should pre-populated in Distributed field$")
	public void page_should_opened_AND_User_Automation_UK_and_User_Auto_Test_should_pre_populated_in_Distributed_field(
			String arg1, String userB, String userC) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyRespondFormPrePopulatedUsers(userB, userC, "");
		} else
			Assume.assumeTrue(true);
	}

	@When("^I create \"(.*?)\" and clear \"(.*?)\" and click on Send button$")
	public void i_create_and_clear_and_click_on_Send_button(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.enteredRespondFormDetails();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form reply should opened in new tab and User ([^\"]*) should pre-populated in Distributed field for single reply$")
	public void form_reply_should_opened_in_new_tab_and_User_Automation_UKP_should_pre_populated_in_Distributed_field_for_single_reply(
			String userA) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyRespondFormPrePopulatedUsers(userA, "", "");
		else
			Assume.assumeTrue(true);
	}

	@Then("^Message Status set as \"(.*?)\"$")
	public void message_Status_set_as(String msgStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyRespondFormCompleted(msgStatus);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form reply should opened in new tab and User ([^\"]*) and User ([^\"]*) both should pre-populated in Distributed field for multiple reply$")
	public void form_reply_should_opened_in_new_tab_and_User_Automation_UKP_and_User_Automation_UK_both_should_pre_populated_in_Distributed_field_for_multiple_reply(
			String userA, String userB) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyRespondFormPrePopulatedUsers(userA, userB, "");
		else
			Assume.assumeTrue(true);
	}

	/****** Customize Status Widget ******/

	@When("^I create new form in folder\"(.*?)\" and form type \"(.*?)\" with form status \"(.*?)\"$")
	public void i_create_new_form_in_folder_and_form_type_with_form_status(String folderType, String formType,
			String customizeStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createCustomizeStatusForm(folderType, formType, customizeStatus);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form should created successfully on \"(.*?)\" tab And form status should set as \"(.*?)\"$")
	public void form_should_created_successfully_on_tab_And_form_status_should_set_as(String arg1,
			String customizeStatus) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCustomizeStatusForm(customizeStatus);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select form for perform Customize Status widget$")
	public void i_select_form_for_perform_Customize_Status_widget() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformCustomizeStatusWidget();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I edit color and Font of selected form status to \"(.*?)\" and \"(.*?)\" respectively with Cell Record \"(.*?)\"$")
	public void i_edit_color_and_Font_of_selected_form_status_to_and_respectively_with_Cell_Record(String color,
			String font, String applyToRow) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.setFormStatusFontAndColor(color, font, applyToRow);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^selected form status background color \"(.*?)\" and font \"(.*?)\" with Cell Record \"(.*?)\"$")
	public void selected_form_status_background_color_and_font_with_Cell_Record(String statusColor, String statusFont,
			String cellRecordFlag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormStatusFontAndColor(statusColor, statusFont, cellRecordFlag);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^selected form status set as default$")
	public void selected_form_status_set_as_default() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCustomizedStatusSetAsDefault();
		} else
			Assume.assumeTrue(true);
	}

	/******* Create Form & Start Workflow widget *******/

	@When("^I create form using \"(.*?)\" widget$")
	public void i_create_form_using_widget(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFormForPerformNewFormWidget();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should open of Recent Forms$")
	public void popup_should_open_of_Recent_Forms(String createFormPopup) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCreateFormPopup();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^create rights \"(.*?)\" forms should displayed And create rights \"(.*?)\" forms should not displayed$")
	public void create_rights_forms_should_displayed_And_create_rights_forms_should_not_displayed(String arg1,
			String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyViewFormType();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I create new form with \"(.*?)\" action for ([^\"]*) user$")
	public void i_create_new_form_with_action_for_user(String action, String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createNewForm("", "", action, user);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^form should created successfully on Recent Forms listing page$")
	public void form_should_created_successfully_on_Recent_Forms_listing_page() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCreatedFormOnRecentFormsListing();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select created new form for \"(.*?)\" widget$")
	public void i_select_created_new_form_for_widget(String startWFWidget) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			// scripts.selectFormFormStartWorkflowWidget();
			scripts.selectFormForPerformAction(startWFWidget);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^only \"(.*?)\" form should displayed and other form should not displayed$")
	public void only_form_should_displayed_and_other_form_should_not_displayed(String accessForm) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyStartWorkflowFormType(accessForm);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I attach any one document And I click on \"(.*?)\" Button$")
	public void i_attach_any_one_document_And_I_click_on_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.attachExternalDocInForm();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I create workflow for form with \"(.*?)\" action for ([^\"]*) user$")
	public void i_create_workflow_for_form_with_action_for_Jasmin_Prajapati_user(String action, String user)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createNewForm("", "", action, user);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^forms all \"(.*?)\" and \"(.*?)\" should associated with workflow form$")
	public void forms_all_and_should_associated_with_workflow_form(String attachments, String forms) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAssociationAndAttachmentViaStartWorkflow(attachments, forms);
		} else
			Assume.assumeTrue(true);
	}

	/* *************************************************************************** */

	/******* Forms Tab Create Reply \ ReplyAll *******/

	@Then("^new tab should opened$")
	public void new_tab_should_opened() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.switchToSecondWindow();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should created successfully with No action$")
	public void form_should_created_successfully_with_No_action() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedRespondForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^created form should displayed with \"(.*?)\" action$")
	public void created_form_should_displayed_with_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithAction(action);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected form \"(.*?)\" action should completed$")
	public void selected_form_action_should_completed(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifySearchedFormActionCompleted(action);
		else
			Assume.assumeTrue(true);
	}
	
	
	/******* Added New *******/
	
	@When("^I externally assign \"(.*?)\" action to User ([^\"]*)$")
	public void i_externally_assign_action_to_User(String action, String user) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.distributeFormToMultipleUsers(action, user, null, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^verify number of \"(.*?)\" action count is (\\d+)$")
	public void verify_number_of_action_count_is(String action, int count) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySameActionsCount(action, count);
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected form \"(.*?)\" action should completed for \"(.*?)\" type form and should not completed for \"(.*?)\" type form$")
	public void selected_form_action_should_completed_for_type_form_and_should_not_completed_for_type_form(String action, String resType, String oriType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionCompletionIncompletion(resType, oriType, action);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" page should opened AND User ([^\"]*) User ([^\"]*) and User ([^\"]*) should pre-populated in Distributed field for multiple reply$")
	public void page_should_opened_AND_User_User_and_User_should_pre_populated_in_Distributed_field_for_multiple_reply(String arg1, String user1, String user2, String user3) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyRespondFormPrePopulatedUsers(user1, user2, user3);
		else
			Assume.assumeTrue(true);
	}
}