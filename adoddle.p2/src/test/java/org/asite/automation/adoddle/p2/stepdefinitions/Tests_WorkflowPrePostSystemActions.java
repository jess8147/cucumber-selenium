package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.WorkflowPrePostSystemActionsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_WorkflowPrePostSystemActions {

	WorkflowPrePostSystemActionsScripts scripts = new WorkflowPrePostSystemActionsScripts();
	
	@Given("^I have created new workflow folder under project ([^\"]*)$")
	public void i_have_created_new_workflow_folder_under_project(String project) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.createWorkflowTestFolder(project);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have created new workflow folder under cloned project$")
	public void i_have_created_new_workflow_folder_under_cloned_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.createWorkflowTestFolder(null);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I have groovy script available with Pre conditions \"(.*?)\" and \"(.*?)\" and \"(.*?)\"$")
	public void i_have_groovy_script_available_with_Pre_conditions_and_and(String xC, String yC, String zC) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.verifyGroovyScript();
		else
			Assume.assumeTrue(true);
	}

	@When("^I update the folder for existing Pre-Post Groovy Workflow trigger$")
	public void i_update_the_folder_for_existing_Pre_Post_Groovy_Workflow_trigger() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.updatePrePostWorkflowTrigger();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have enabled \"(.*?)\" on the folder$")
	public void i_have_enabled_on_the_folder(String uploadType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.enableSimpleUpload();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have enabled \"(.*?)\" on file upload dialogue$")
	public void i_have_enabled_on_upload_dialogiue(String uploadType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.enableUploadAttibutes();
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I try to upload \"(.*?)\" documents$")
	public void i_try_to_upload_documents(String count) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.openUploadDialog(count);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Groovy script should fail \"(.*?)\" conditions for \"(.*?)\" file$")
	public void groovy_script_should_fail_conditions_for_files(String conditions, String files) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.validateGroovyScriptConditions(conditions, files);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I have satisfied \"(.*?)\" groovy script conditions for \"(.*?)\" file$")
	public void i_have_satisfied_groovy_script_conditions_for_file(String conditions, String files) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.applyFileUploadConditions(conditions, files);
		else
			Assume.assumeTrue(true);
	}

	@Then("^User should be able to upload file successfully$")
	public void user_should_be_able_to_upload_file_successfully() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.verifyUploadIsSuccessful();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I deactivate the workflow groovy folder for ([^\"]*)$")
	public void i_deactivate_the_workflow_groovy_folder(String project) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.deactivateGroovyFolder(project);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I verify \"(.*?)\" and \"(.*?)\" system actions$")
	public void i_verify_and_system_actions(String preActions, String postActions) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.verifyPrePostActions(preActions, postActions);
		else
			Assume.assumeTrue(true);
	}

	@When("^I create System task of type \"(.*?)\"$")
	public void i_create_System_task_of_type(String type) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.createSystemTaskOfType(type);
		else
			Assume.assumeTrue(true);
	}

	@When("^I create trigger with \"(.*?)\" and \"(.*?)\" and \"(.*?)\" conditions on \"(.*?)\" event with action mode \"(.*?)\"$")
	public void i_create_trigger_with_and_and_conditions_on_event_with_action_mode(String folderOption, String poiOption, String statusOption, String event, String actionMode) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.createSystemTrigger(folderOption, poiOption, statusOption, event, actionMode);
		else
			Assume.assumeTrue(true);
	}

	@When("^I publish \"(.*?)\" documents with trigger \"(.*?)\" conditions$")
	public void i_publish_documents_with_trigger_conditions(String count, String condition) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.publishDocuments(count, condition);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Actions should be distributed to only \"(.*?)\" documents$")
	public void actions_should_be_distributed_to_only_documents(String flag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.verifyActionsDistribution(true);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Actions should not be distributed to \"(.*?)\" documents$")
	public void actions_should_not_be_distributed_to_documents(String flag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.verifyActionsDistribution(false);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I deactivate cloned project$")
	public void i_deactivate_cloned_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
		    scripts.deactivateClonedProject();
		else
			Assume.assumeTrue(true);
	}

}
