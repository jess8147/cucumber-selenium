package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.UpdateFileAttributeViaWorkflow;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_UpdateFileAttributeViaWorkflow {

	private String workspace = null, scenariofolder = null;
	UpdateFileAttributeViaWorkflow scripts = new UpdateFileAttributeViaWorkflow();

	@Given("^I have focus on \"(.*?)\" folder in ([^\"]*)$")
	public void i_have_focus_on_folder_in_AutomationTestProject(String folder, String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			workspace = project;
			scenariofolder = folder;
			scripts.focusProjectAndFolder(folder, project);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I Search \"(.*?)\" AND I validate all oldRevisions access modifiers for scenario \"(.*?)\"$")
	public void i_Search_AND_I_validate_all_oldRevisions_access_modifiers_for_scenario(String fileName, String scenario)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateOldRevisions(workspace, scenariofolder, fileName, scenario);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Search \"(.*?)\" AND I get current revision$")
	public void i_Search_Test_File_AND_I_get_current_revision(String fDocRef) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchFileAndGetActiveRevsion(fDocRef);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I click on Add Files button$")
	public void i_click_on_Add_Files_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAddFilesButton();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button AND I have selected \"(.*?)\" file revisions from Local$")
	public void i_click_on_button_AND_I_have_selected_file_revisions_from_Local(String arg1, String revType)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectFileRevisonsAndUpload(revType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Click Upload Button$")
	public void i_Click_Upload_Button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickOnUpload();
			scripts.waitUntilUploadPopInvisible();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Enter Mandatory Attributes of TestFile for scenario \"(.*?)\"$")
	public void i_Enter_Mandatory_Attributes_of_TestFile_for_scenario(String fAcess) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enteraMandatoryAttributes(fAcess);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Should Available in Document Listing having Workflow Status as \"(.*?)\" AND Workflow Stage as \"(.*?)\" for scenario \"(.*?)\"$")
	public void should_Available_in_Document_Listing_having_Workflow_Status_as_AND_Workflow_Stage_as_for_scenario(
			String fName, String wStatus, String wStage, String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.checkFileStatus(fName, wStatus, wStage, scenario);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Search \"(.*?)\" having MyActions as \"(.*?)\" AND I click \"(.*?)\" for scenario \"(.*?)\"$")
	public void i_Search_having_MyActions_as_AND_I_click_for_scenario(String fName, String fAction, String arg3,
			String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateFileMyAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Popup \"(.*?)\" should open$")
	public void popup_should_open(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateActionPopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I perform \"(.*?)\" Action sucessfully for scenario \"(.*?)\"$")
	public void i_perform_Action_sucessfully_for_scenario(String fAction, String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performMyActions(fAction, scenario);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Validate \"(.*?)\" file version for scenario \"(.*?)\"$")
	public void i_Validate_file_version_for_scenario(String fName, String wScenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateFileVersion(wScenario);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Workflow Status should be \"(.*?)\" AND Workflow Stage as \"(.*?)\"$")
	public void workflow_Status_should_be_AND_Workflow_Stage_as(String fname, String wStatus, String wStage)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateWorkflowStatusAndStage(wStatus);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have deactivated \"(.*?)\" of files$")
	public void i_have_deactivated_of_files(String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.deactivateFileRevisions();
		else
			Assume.assumeTrue(true);
	}

}