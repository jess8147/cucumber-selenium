package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.WorkflowBoundaryTimerEmailScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_BoundaryTimerEmailNotification {

	WorkflowBoundaryTimerEmailScripts scripts = new WorkflowBoundaryTimerEmailScripts();

	@Given("^I have focus on subFolder \"(.*?)\" in folder \"(.*?)\"$")
	public void i_have_focus_on_subFolder_in_folder(String subDirectory, String parentFolder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.focusSubDirectory(subDirectory, parentFolder);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I have published multiple Documents successfully in subFolder \"(.*?)\"$")
	public void i_have_published_multiple_Documents_successfully_in_subFolder(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.publishMultipleDocumentsInSubDirectory();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All published documents should have workStatus as \"(.*?)\" AND workflowStage as \"(.*?)\"$")
	public void all_published_documents_should_have_workStatus_as_AND_workflowStage_as(String wStatus, String wStage)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateDocumentsWorkflowAttributes(wStage, wStage);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have search file with docRef as \"(.*?)\" AND I have validated action as \"(.*?)\" successfully$")
	public void i_have_search_file_with_docRef_as_AND_I_have_validated_action_as_successfully(String file, String action)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateDocumentAction(file, action);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I perform action \"(.*?)\" successfully$")
	public void i_perform_action_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.performDocumentAction();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^published document \"(.*?)\" should have workStatus as \"(.*?)\" AND workflowStage as \"(.*?)\"$")
	public void published_document_should_have_workStatus_as_AND_workflowStage_as(String file, String wStatus,
			String wStage) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validatefileWorkflowAttributes(file, wStatus, wStage);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated Email Occurrence as \"(.*?)\" successfully on Pop up \"(.*?)\"$")
	public void i_have_validated_Email_Occurrence_as_successfully_on_Pop_up(String emailOccurence, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateEmailOccurenceOnPopUp(emailOccurence);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Email should be received to distributed users with subject as \"(.*?)\"$")
	public void email_should_be_received_to_distributed_users_with_subject_as(String emailSubject) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateEmailContentsUserMailbox(emailSubject);
		} else
			Assume.assumeTrue(true);
	}

}
