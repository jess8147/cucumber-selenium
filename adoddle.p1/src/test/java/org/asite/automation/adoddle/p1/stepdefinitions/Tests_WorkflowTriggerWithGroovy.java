package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.WorkflowTriggerWithGroovy;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_WorkflowTriggerWithGroovy {

	WorkflowTriggerWithGroovy scripts = new WorkflowTriggerWithGroovy();

	@When("^I clicked on \"(.*?)\" button AND I have uploaded multiple Files from Local$")
	public void i_clicked_on_button_AND_I_have_uploaded_multiple_Files_from_Local(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadMultipleFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have entered all mandatory files attributes for multiple files$")
	public void i_have_entered_all_mandatory_files_attributes_for_multiple_files() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.mandatoryFilesAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Workflow Files should be uploaded successfully with workflow status as \"(.*?)\" AND stage as \"(.*?)\"$")
	public void all_Workflow_Files_should_be_uploaded_successfully_with_workflow_status_as_AND_stage_as(String wStatus,
			String wStage) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.waitAndRefreshWorkflowAttributes();
			scripts.postUploadFileValidation(wStatus, wStage);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Search \"(.*?)\" having MyActions as \"(.*?)\" AND I click \"(.*?)\" action$")
	public void i_Search_having_MyActions_as_AND_I_click(String arg1, String fAction, String cAction) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateActionAndClick(fAction, cAction);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Popup \"(.*?)\" should open$")
	public void popup_should_open(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validatePopUp();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I perform \"(.*?)\"  action on file sucessfully$")
	public void i_perform_action_on_file_sucessfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performFileAction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Validated all \"(.*?)\" files attributes updated with groovy script$")
	public void i_Validated_all_files_attributes_updated_with_groovy_script(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.postFileAttributesValidation();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search files AND deactivated$")
	public void i_have_search_files_AND_deactivated() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.cleanUpOperation();
		else
			Assume.assumeTrue(true);
	}

}
