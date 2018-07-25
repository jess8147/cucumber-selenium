package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ClonedInheritanceWorkflowScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ClonedInheritanceWorkflow {

	ClonedInheritanceWorkflowScripts Scripts = new ClonedInheritanceWorkflowScripts();

	@When("^I logged in as ([^\"]*)$")
	public void i_logged_in_as_TC_Bloggs(String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.loggedInAsUser(user);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should re-directed to Dashboard of user \"(.*?)\"$")
	public void i_should_re_directed_to_Dashboard_of_user(String dashboardUser) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.validateUsersDashboard(dashboardUser);
		else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button AND I have selected multiple Files from Local$")
	public void i_clicked_on_button_AND_I_have_selected_multiple_Files_from_Local(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.selectMultipleFilesAndUpload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered all mandatory attributes for multiple files$")
	public void i_have_entered_all_mandatory_attributes_for_multiple_files() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.enterMandatoryAttributes();
		else
			Assume.assumeTrue(true);

	}

	@Then("^All Workflow Files should be uploaded successfully AND I should redirected on Document listing$")
	public void all_Workflow_Files_should_be_uploaded_successfully_AND_I_should_redirected_on_Document_listing()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			Scripts.waitAndRefreshWorkflowAttributes();
			Scripts.verifyWorkflowFiles();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Workflow status should get changed to \"(.*?)\" state AND Workflow Stage should not equal to  \"(.*?)\"$")
	public void workflow_status_should_get_changed_to_state_AND_Workflow_Stage_should_not_equal_to(String arg1,
			String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.workflowStatusAndStage();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on All Workflow files having status as \"(.*?)\"$")
	public void i_click_on_All_Workflow_files_having_status_as(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.checkWorkflowStatus();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All  Workflow files should have status as \"(.*?)\" AND correct start time AND End time on \"(.*?)\" popup$")
	public void all_Workflow_files_should_have_status_as_AND_correct_start_time_AND_End_time_on_popup(String arg1,
			String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.checkWorkflowFileAttributes();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Right click on All files in Document Listing having workflow status as \"(.*?)\"$")
	public void i_Right_click_on_All_files_in_Document_Listing_having_workflow_status_as(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.rightClickFirstFileWithStatusWorkflowRuning();
		else
			Assume.assumeTrue(true);
	}

	@When("^drag mouse on History AND click on Distribution$")
	public void drag_mouse_on_History_AND_click_on_Distribution() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.dragMouseOnHistoryAndOptDistribution();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New Tab should open with HTML viewer AND file should be open/view in viewer$")
	public void new_Tab_should_open_with_HTML_viewer_AND_file_should_be_open_view_in_viewer() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.verifyHTMLViewer();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on first record of history$")
	public void i_click_on_first_record_of_history() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.clickFirstHistoryRecord();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Records should get expanded$")
	public void records_should_get_expanded() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.recordExpand();

		else
			Assume.assumeTrue(true);
	}

	@Then("^Records should have correct \"(.*?)\" and \"(.*?)\"$")
	public void records_should_have_correct_and(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.checkFilesRecordAttributes();

		else
			Assume.assumeTrue(true);
	}

	@Then("^Workflow file\\(s\\) should have \"(.*?)\" action for \"(.*?)\"$")
	public void workflow_file_s_should_have_action_for(String fname, String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.checkFileStatus(fname, scenario);
		else
			Assume.assumeTrue(true);
	}

	@When("^i click on First File having  My action as \"(.*?)\" for \"(.*?)\"$")
	public void i_click_on_First_File_having_My_action_as_for(String status, String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.clickMyActions();
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform For Action action in any workflow file\\(s\\)$")
	public void i_perform_For_Action_action_in_any_workflow_file_s() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.performForAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Action \"(.*?)\" should be completed sucessfully$")
	public void action_should_be_completed_sucessfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.validateAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Workflow Status should be \"(.*?)\" AND Workflow Stage as \"(.*?)\" for \"(.*?)\"$")
	public void workflow_Status_should_be_AND_Workflow_Stage_as_for(String status, String stage, String wscenario)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.checkWorkflowStatusAndStage(status, stage, wscenario);
		else
			Assume.assumeTrue(true);
	}

	@When("^I perform \"(.*?)\" to \"(.*?)\" action in workflow file\\(s\\) for \"(.*?)\"$")
	public void i_perform_to_action_in_workflow_file_s_for(String action, String cStatus, String scenario)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.performForStatusChange(cStatus);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Workflow file should receive \"(.*?)\" action$")
	public void workflow_file_should_receive_action(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.checkfileStatusAfterStatusChange(action);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have context click on File AND I have completed My action as \"(.*?)\" successfully$")
	public void i_have_context_click_on_File_AND_I_have_completed_My_action_as_successfully(String action)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.performForInformationAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New Tab should be open with HTML viewer AND file should be open in viewer$")
	public void new_Tab_should_be_open_with_HTML_viewer_AND_file_should_be_open_view_in_viewer() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			Scripts.validateFileInHTMLViewer();
		else
			Assume.assumeTrue(true);
	}

}