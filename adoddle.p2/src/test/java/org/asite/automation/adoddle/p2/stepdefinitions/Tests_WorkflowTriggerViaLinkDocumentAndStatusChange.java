package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.WorkflowTriggerViaLinkDocumentAndStatusChangeScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_WorkflowTriggerViaLinkDocumentAndStatusChange {

	WorkflowTriggerViaLinkDocumentAndStatusChangeScripts workflowScripts = new WorkflowTriggerViaLinkDocumentAndStatusChangeScripts();

	@When("^I Right Click on Project as ([^\"]*)$")
	public void i_Right_Click_on_Project_as_Project_Name(String project) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.clickOnProjectName(project);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I Again Right Click on Project as ([^\"]*)$")
	public void i_Again_Right_Click_on_Project_as_AutomationTestProject(String project) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.clickOnProjectName(project);
		} else
			Assume.assumeTrue(true);
	}

	@When("^drag mouse to \"(.*?)\" AND Click on \"(.*?)\"$")
	public void drag_mouse_to_AND_Click_on(String arg1, String arg2) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.clickOncreateNewFolder();
		} else
			Assume.assumeTrue(true);

	}

	@Then("^I Enter FolderName as \"(.*?)\" AND I Click on Create button$")
	public void i_Enter_FolderName_as_AND_I_Click_on_button(String folderName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.createFolder(folderName);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should be created AND available in Folder Tree$")
	public void should_be_created_AND_available_in_Folder_Tree(String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.verifyFolderName(folder);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I clicked on \"(.*?)\" button AND I have selected Multiple Files from Local for Scenario \"(.*?)\"$")
	public void i_clicked_on_button_AND_I_have_selected_Multiple_Files_from_Local_for_Scenario(String arg1,
			String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.selectFilesAndUpload(scenario);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All Workflow Files Should Available in Document Listing AND I edit Attributes of First Three Files for Scenario \"(.*?)\"$")
	public void all_Workflow_Files_Should_Available_in_Document_Listing_AND_I_edit_Attributes_of_First_Three_Files_for_Scenario(
			String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.editFilesAttributes(scenario);
		} else
			Assume.assumeTrue(true);

	}

	@Given("^I have focus on  project ([^\"]*) AND I click Link Workflow Triggers$")
	public void i_have_focus_on_project_AND_I_click_Link_Workflow_Triggers(String project) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.focusOnProject(project);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I Click on Existing Trigger \"(.*?)\"$")
	public void i_Click_on_Existing_Trigger(String trigger) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.clickExistingTrigger(trigger);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I Edit Folder Value in Trigger Conditions as \"(.*?)\"$")
	public void i_Edit_Folder_Value_in_Trigger_Conditions_as(String triggerCondition) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			workflowScripts.editExistingTrigger(triggerCondition);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on Project ([^\"]*) AND folder \"(.*?)\" folder$")
	public void i_have_focus_on_AutomationTestProject_Project_AND_folder_folder(String projectName, String folderName)
			throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.clickOnProjectFolder(projectName, folderName);
		else
			Assume.assumeTrue(true);

	}

	@Given("^I Search AND Right Click on File$")
	public void i_Search_AND_Right_Click_on_File() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.searchLinkedDocumentFile();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Select Link Files\\(s\\) Option$")
	public void i_Select_Link_Files_s_Option() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.linkFile();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Popup Should Open$")
	public void popup_Should_Open(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.validatePopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Select Target Folder AND Click Button \"(.*?)\"$")
	public void i_Select_Target_Folder_AND_Click_Button(String btnText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.selectTargetFolder(btnText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Link Document With User \"(.*?)\" AND Link \"(.*?)\" in Target Folder$")
	public void i_Link_Document_With_User_AND_Link_in_Target_Folder(String user, String lnkType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.linkFileToTargetFolder();
		else
			Assume.assumeTrue(true);

	}

	@Then("^Link should be created sucessfully$")
	public void link_should_be_successfully_created() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.verifyCretaedLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on Folder \"(.*?)\"$")
	public void i_have_focus_on_Folder(String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.TargetFolder(folder);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on Project ([^\"]*) AND Target Folder$")
	public void i_have_focus_on_AutomationTestProject_Project_AND_Target_Folder(String project) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.focusProjectAndTagetFolder(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Validate All Files Workflow status and MyActions for Scenario \"(.*?)\"$")
	public void i_Validate_All_Files_Workflow_status_and_My_Actions(String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.checkWorkflowStatusAndStage(scenario);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Search All Files having Status as \"(.*?)\"$")
	public void i_Search_File_having_Status_as(String action) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.verifyFileAction(action);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I perform \"(.*?)\" action successfully for All files's$")
	public void i_perform_action_sucessfully_for_All_files(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.performActionStatusChange();

		else
			Assume.assumeTrue(true);
	}

	@Given("^I Click on Files having My Actions as \"(.*?)\"$")
	public void i_Click_on_My_Actions_having_Status_as_for_file(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.clickOnMyAction();
		else
			Assume.assumeTrue(true);

	}

	@Then("^I Deactivated All Test_Files$")
	public void i_Deactivated_File() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.deactivateTestFiles();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Deactivated Created Folder ([^\"]*) in project ([^\"]*)$")
	public void i_Deactivated_Created_Folder_WF_LinkDocumentPrimaryFolder_in_project_AutomationTestProject(
			String fName, String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			workflowScripts.deactivateFolder(fName, project);
		else
			Assume.assumeTrue(true);
	}

}