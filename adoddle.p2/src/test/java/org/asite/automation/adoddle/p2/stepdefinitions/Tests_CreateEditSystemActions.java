package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreateEditSystemActionsScripts;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateEditSystemActions extends AdoddleCommonAppMethods {

	CreateEditSystemActionsScripts systemActionScript = new CreateEditSystemActionsScripts();

	@Given("^I have cloned project as ([^\"]*) from existing template ([^\"]*) for ([^\"]*)$")
	public void i_have_cloned_project_as_Cloned_AutomationTestProject_from_existing_template_AutomationTestProject_Template_for_UK(
			String workspace, String template, String dc) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			cloneProject(template, dc);
			systemActionScript.setUpWorkspaceCalenderInClonedWorkspace();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I click on \"(.*?)\" link$")
	public void i_click_on_link(String configureSystemAction) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.clickLinks(configureSystemAction);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I create System task Name as \"(.*?)\"$")
	public void i_create_System_task_Name_as(String firstAction) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.createEditSystemAction(firstAction);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Select Action Type as \"(.*?)\"$")
	public void i_Select_Action_Type_as(String aType) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.selectActionType(aType);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Select Users Distribution group Role AND Org as \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" AND \"(.*?)\"$")
	public void i_Select_Users_Distribution_group_Role_AND_Org_as_AND(String user1, String user2, String group,
			String role, String org) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			systemActionScript.selectDistributeToUser(user1, user2, group, role, org);
			systemActionScript.assignActionAndDueDays(role, org);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I put Subject as \"(.*?)\"$")
	public void i_put_Subject_as(String subject) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.putSubject();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on \"(.*?)\" button to create system task$")
	public void i_click_on_button_to_create_system_task(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.clickcreateButton();
		else
			Assume.assumeTrue(true);

	}

	@When("^I click again on System Action as \"(.*?)\"$")
	public void i_click_again_on_System_Action_as(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.editSystemAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^popup \"(.*?)\" should open$")
	public void popup_should_open(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.verifyEditpopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I edit Name as \"(.*?)\"$")
	public void i_edit_Name_as(String editName) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.changeSystemActionName();
		else
			Assume.assumeTrue(true);

	}

	@Then("^\"(.*?)\" action should Edited as \"(.*?)\" sucessfully for workflow$")
	public void action_should_Edited_as_sucessfully(String arg1, String editActionName) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.validateEditedSystemAction(editActionName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I put System task Name as \"(.*?)\"$")
	public void i_put_System_task_Name_as(String sysAction) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.createSystemAction(sysAction);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select Change Status from \"(.*?)\" To \"(.*?)\"$")
	public void i_Select_Change_Status_from_To(String fromStatus, String toStatus) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.performStatusChange(fromStatus, toStatus);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I put Reason for Status Change Notes as \"(.*?)\"$")
	public void i_put_Reason_for_Status_Change_Notes_as(String statusChangeReason) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.putStatusChangeReason(statusChangeReason);
		else
			Assume.assumeTrue(true);

	}

	@Given("^I Updated \"(.*?)\" system action as \"(.*?)\"$")
	public void i_Updated_system_action_as(String arg1, String fVersion) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.selectUpdateFilePrivacyAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" System Action should created sucessfully AND Available on System Actions List$")
	public void system_Action_should_created_sucessfully_AND_Available_on_System_Actions_List(String sysActionName)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.validateSystemAction(sysActionName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select Clear Actions For \"(.*?)\" AND Actions To Be Cleared for all Actions$")
	public void i_Select_Clear_Actions_For_AND_Actions_To_Be_Cleared_for_all_Actions(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.selectClearActionsAttributes();
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Select Mark File Version As:\"(.*?)\" AND Select Version as \"(.*?)\"$")
	public void i_Select_Mark_File_Version_As_AND_Select_Version_as(String fVersion, String arg2) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.selectUpdateFilePrivacyAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I put Workflow Name AND Description as \"(.*?)\" AND Workflow Days$")
	public void i_put_Workflow_Name_AND_Description_as_AND_Workflow_Days(String wrkName) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.createWorkflowDefinition(wrkName);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I click on \"(.*?)\" button for WorkflowDefinition$")
	public void i_click_on_button_for_WorkflowDefinition(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.saveWorkflowDefinition();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should Re-directed to \"(.*?)\"$")
	public void i_should_Re_directed_to(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.validateVisualWorkflowDesigner();
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Design Workflow with UserTask And SystemTasks$")
	public void i_Design_Workflow_with_UserTask_And_SystemTasks() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.designWorkflow();
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Saved Designed Workflow Definition$")
	public void i_Saved_Designed_Workflow_Definition() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.saveWorkflowDefiniton();
		else
			Assume.assumeTrue(true);

	}

	@When("^I Right click on designed Workflow Definiton Name$")
	public void i_Right_click_on_designed_Workflow_Definiton_Name() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.contextClickWorkflowDefiniton();
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Edit Workflow Definition Name as \"(.*?)\"$")
	public void i_Edit_Workflow_Definition_Name_as(String newDefName) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.editWorkflowDefiniton(newDefName);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Create Workflow Trigger as \"(.*?)\"$")
	public void i_Create_Workflow_Trigger_as(String trgName) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.createWorkflowTrigger(trgName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Edit Created Workflow Trigger as \"(.*?)\"$")
	public void i_Edit_Created_Workflow_Trigger_as(String newTrigger) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.editWorkflowTrigger(newTrigger);
		else
			Assume.assumeTrue(true);

	}

	@Given("^I have focus on \"(.*?)\" folder in my \"(.*?)\" Project$")
	public void i_have_focus_on_folder_in_my_Project(String folderName, String arg2) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.focusProjectAndFolder(folderName);
		else
			Assume.assumeTrue(true);

	}

	@When("^I have published multiple Document\\(s\\) in folder \"(.*?)\" successfully$")
	public void i_have_published_multiple_Document_s_in_folder_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.publishMultipleDocuments();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Documents Should Available in Document Listing having Status as \"(.*?)\"$")
	public void all_Documents_Should_Available_in_Document_Listing_having_Status_as(String fStatus) throws Throwable {
		String validationInstance1 = "preActionValidation";

		if (Tests_CommonStepDefinitions.runTest) {
			systemActionScript.validateActionAndDueDateInAuditTrail(validationInstance1);
			systemActionScript.checkFileStatus(fStatus);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I Search Cloned_TestFile having Status as \"(.*?)\" AND I click \"(.*?)\"$")
	public void i_Search_Cloned_TestFile_having_Status_as_AND_I_Perform(String arg1, String arg2) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.searchFileAndClickAction();
		else
			Assume.assumeTrue(true);

	}

	@Then("^I perform \"(.*?)\" Action successfully$")
	public void i_perform_Action_sucessfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.performMyAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Validate Cloned_TestFile file version as \"(.*?)\"$")
	public void i_Validate_Cloned_TestFile_file_version_as(String fVersion) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.validateFileVersion();

		else
			Assume.assumeTrue(true);
	}

	@Then("^Cloned_TestFile Workflow Status should be \"(.*?)\" AND Workflow Stage as \"(.*?)\"$")
	public void workflow_Status_should_be_AND_Workflow_Stage_as(String wStatus, String wStage) throws Throwable {
		String validationInstance2 = "postActionValidation";
		if (Tests_CommonStepDefinitions.runTest) {
			systemActionScript.validateWorkflowStatusAndStage(wStatus, wStage);
			systemActionScript.validateActionAndDueDateInAuditTrail(validationInstance2);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I modify docStatus as \"(.*?)\" AND poi as \"(.*?)\" AND I uncheck option \"(.*?)\"$")
	public void i_modify_docStatus_as_AND_poi_as_AND_I_uncheck_option(String status, String poi, String arg3)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.updateSystemActionAttributes(status, poi);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have validated upto file version \"(.*?)\" AND workflowStatus as \"(.*?)\" successfully$")
	public void i_have_validated_upto_file_version_AND_workflowStatus_as_successfully(String version, String wStatus)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.validateDocumentVersionAndWorkflowStatus(version);
		else
			Assume.assumeTrue(true);
	}

	@When("^I have validated Audit trial for Users Distribution group Role AND Org as \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" AND \"(.*?)\"$")
	public void i_have_validated_Audit_trial_for_Users_Distribution_group_Role_AND_Org_as_AND(String arg1, String arg2,
			String arg3, String arg4, String arg5) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.validateActionAndDueDateInHistory();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have download AND validate multiple files \"(.*?)\" successfully in Local$")
	public void i_have_download_AND_validate_multiple_files_successfully_in_Local(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.downloadMultipleDocumentsInLocal();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Search Cloned_Project AND Deactivate$")
	public void i_Search_Cloned_Project_AND_Deactivate() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			systemActionScript.deactivateClonedProject();
		else
			Assume.assumeTrue(true);

	}

}