package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.SingleMultipleFormsStatusChangeScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_SingleMultipleFormsStatusChange
{
	SingleMultipleFormsStatusChangeScripts scripts = new SingleMultipleFormsStatusChangeScripts();
	
	@When("^I select ([^\"]*) project and ([^\"]*) group and ([^\"]*) app$")
	public void i_select_project_and_group_and_app(String project, String appGroup, String appType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormApp(project, appGroup, appType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new form And Distribute to Users ([^\"]*) with ([^\"]*) action$")
	public void i_create_new_form_And_Distribute_to_users_with_action(String distributeUsers, String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createCombinedAppTypeForm(distributeUsers, action, "Multiple");
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Form should created Successfully with No action$")
	public void form_should_created_Successfully_with_No_action() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithAction(null);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" context Option should disabled$")
	public void context_Option_should_disabled(String contextOption) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyContextOptionDisabled(contextOption);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" More Option should disabled$")
	public void more_Option_should_disabled(String option) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMoreOptionsOptionDisabled(option);
		else
			Assume.assumeTrue(true);
	}
	
	/*@Then("^both Form should displayed with ([^\"]*) action$")
	public void both_Form_should_displayed_action(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithAction(action);
		else
			Assume.assumeTrue(true);
	}*/
	
	@Then("^created all Forms should displayed with \"(.*?)\" action$")
	public void all_Forms_should_displayed_with_action(String action) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFormWithAction(action);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" And perform \"(.*?)\" and \"(.*?)\" for change status$")
	public void i_select_And_perform_and_for_change_status(String listOfForms, String option1, String option2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormAndSelectContextClickOption(listOfForms, option1, option2);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select \"(.*?)\" And click on \"(.*?)\" and select \"(.*?)\" Option$")
	public void i_select_And_click_on_and_select_Option(String listOfForms, String moreOptions, String option) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectFormAndSelectMoreOptionsOption(listOfForms, option);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I change \"(.*?)\" Status and entered Status Change notes And I click on \"(.*?)\" Button of popup$")
	public void i_change_Status_and_entered_Status_Change_notes_And_I_click_on_Button_of_popup(String listOfForms, String button) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeFormStatus(listOfForms, button, null);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" popup should not Opened And Status Change hyperlink \"(.*?)\" should \"(.*?)\" on View Form$")
	public void popup_should_not_Opened_And_Status_Change_hyperlink_should_on_View_Form(String popupText, String status, String disabledAttribute) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyChangeStatusHyperlinkDisabled(status);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" dropdown empty And \"(.*?)\" button should disabled$")
	public void dropdown_empty_And_button_should_disabled(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyChangeStatusDropdownAndButtonDisabledOnPopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Status should changed and displayed on View Form RH-panel$")
	public void status_should_changed_and_displayed_on_View_Form_RH_panel(String listOfForms) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusOnViewForm();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Status should changed and displayed on View Form RH-panel and status link disabled$")
	public void status_should_changed_and_displayed_on_View_Form_RH_panel_and_status_link_disabled(String listOfForms) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusOnViewFormWithDisabledLink();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Status should changed and displayed on View Form RH-panel And Button disabled$")
	public void status_should_changed_and_displayed_on_View_Form_RH_panel_And_Button_disabled(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			
//			if(ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true"))
				scripts.verifyUpdatedStatusOnViewFormAndDisabled();
//			else
//				scripts.verifyUpdatedStatusOnViewForm();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" updated Status should set on Forms tab And \"(.*?)\" action completed$")
	public void updated_Status_should_set_on_Forms_tab_And_action_completed(String listOfForms, String completeActions) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusAndAssignedAndCompletedActions(listOfForms, completeActions, null);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" updated Status should set on Forms tab$")
	public void updated_Status_should_set_on_Forms_tab(String listOfForms) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusAndAssignedAndCompletedActions(listOfForms, null, null);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" updated Status should set on Forms tab And \"(.*?)\" action cleared$")
	public void updated_Status_should_set_on_Forms_tab_And_action_cleared(String listOfForms, String clearedAction) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusAndAssignedAndCompletedActions(listOfForms, clearedAction, null);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" updated Status should set on Forms tab And \"(.*?)\" action cleared And \"(.*?)\" action assigned$")
	public void updated_Status_should_set_on_Forms_tab_And_action_cleared_And_action_assigned(String listOfForms, String completeActions, String assignActions) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusAndAssignedAndCompletedActions(listOfForms, completeActions, assignActions);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Status should changed And ([^\"]*) action completed$")
	public void status_should_changed_And_action_completed(String listOfForms, String completeActions) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusAndAssignedAndCompletedActions(listOfForms, completeActions, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should not Opened And Status Change hyperlink should \"(.*?)\" on View Form$")
	public void popup_should_not_Opened_And_Status_Change_hyperlink_should_on_View_Form(String popupText, String disabledAttribute) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyChangeStatusDisabled();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I change \"(.*?)\" Status as \"(.*?)\" and entered Status Change notes And I click on \"(.*?)\" Button of popup$")
	public void i_change_Status_as_and_entered_Status_Change_notes_And_I_click_on_Button_of_popup(String listOfForms, String status, String button) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeFormStatus(listOfForms, button, status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I verify both Form could not Change Status And \"(.*?)\" hyperlink \"(.*?)\" on View Form$")
	public void i_verify_both_Form_could_not_Change_Status_And_hyperlink_on_View_Form(String popupText, String disabledAttribute) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyListOfFormsStatusChangeDisabled();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I select \"(.*?)\" And View Form$")
	public void i_select_And_View_Form(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchAndViewSingleForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I \"(.*?)\" Form with Change Status$")
	public void i_Form_with_Change_Status(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAndDistributeFormWithChangeStatus();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^only ([^\"]*) app type form should displayed on popup$")
	public void only_app_type_form_should_displayed_on_popup(String appType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyChangeableStatusOnPopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^only ([^\"]*) form status should changed and \"(.*?)\" action completed But ([^\"]*) form status and \"(.*?)\" action as it is$")
	public void only_form_status_should_changed_and_action_completed_But_form_status_and_action_as_it_is(String appType1, String completeAction, String appType2, String IncompleteAction) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyStatusAndActionForRestrictFormYesAndNo(completeAction,IncompleteAction);
		else
			Assume.assumeTrue(true);
	}
}
