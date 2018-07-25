package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CommentOnStatusChangeScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CommentOnStatusChange {
	
	CommentOnStatusChangeScripts scripts = new CommentOnStatusChangeScripts();

	@When("^I change status of uploaded document via \"(.*?)\"$")
	public void i_change_status_of_uploaded_document_via(String action) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.changeDocumentStatus(action);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Comment should get created for status change$")
	public void comment_should_get_created_for_status_change() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentIsCreated();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have uploaded \"(.*?)\" documents$")
	public void i_have_uploaded_document_s(String count) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadSingleDocument(count);
		else 
			Assume.assumeTrue(true);
	}
	
}
