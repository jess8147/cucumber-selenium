package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DeactivateReactivateDocumentActions;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DeactivateReactivateDocActions {

	DeactivateReactivateDocumentActions scripts = new DeactivateReactivateDocumentActions();
	
	@Given("^I create automation folder in project ([^\"]*)$")
	public void i_create_automation_folder_in_project(String projectTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createAutomationFolder(projectTitle);
		else
			Assume.assumeTrue(true);
	}

	@Given("I open folder edit dialog to provide user access")
	public void i_open_folder_edit_dialog_to_provide_user_access() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.openFolderEditDialog();
		else
			Assume.assumeTrue(true);
	}

	
	@Given("^I provided \"(.*?)\" access to ([^\"]*)$")
	public void i_provided_access_to_users(String access, String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.editAccessToAutomationFolder(access, user);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I saved updated folder$")
	public void i_saved_updated_folder() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.saveUpdatedFolder();
		else
			Assume.assumeTrue(true);
	}

	@When("^I publish private document and distribute to ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_publish_private_document_and_distribute_to_users(String user1, String user2, String user3, String user4) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.publishDocumentAndDistribute(user1, user2, user3, user4);
		else
			Assume.assumeTrue(true);
	}

	@When("^I upload revision of private document and distribute to ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
		public void i_upload_revision_of_private_document_and_distribute_to(String user1, String user2, String user3, String user4) throws Throwable {
			if(Tests_CommonStepDefinitions.runTest)
				scripts.uploadDocumentRevisionAndDistribute(user1, user2, user3, user4);
			else
				Assume.assumeTrue(true);
		}
	
	
	@When("^I search created private document$")
	public void i_search_private_document() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchDocument();
		else
			Assume.assumeTrue(true);
	}

	@When("^I \"(.*?)\" actions of ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) for revision \"(.*?)\"$")
	public void i_deactivate_actions_of_users(String action, String user1, String user2, String user3, String user4, String revision) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deactivateActions(action, user1, user2,user3,user4, revision);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Document actions count will be \"(.*?)\" and document will be \"(.*?)\" for ([^\"]*)$")
	public void document_actions_count_will_reduce_and_document_will_be(String countFlag, String visibilityFlag, String user) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentVisibilityAndCounts(countFlag, visibilityFlag, user);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Document actions count will be \"(.*?)\" and \"(.*?)\" Revision should be displayed$")
	public void document_actions_count_will_reduce_and_previous_revision_should_be_displayed_as_current(String countFlag, String revisionFlag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentRevision(countFlag, revisionFlag);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I deactivate folder in project ([^\"]*)$")
	public void i_deactivate_folder_in_project(String projectTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deactivateFolder(projectTitle);
		else
			Assume.assumeTrue(true);
	}
	
}
