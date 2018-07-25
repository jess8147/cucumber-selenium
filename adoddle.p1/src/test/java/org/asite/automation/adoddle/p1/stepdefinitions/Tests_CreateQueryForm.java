package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateQueryFormScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Tests_CreateQueryForm {

	CreateQueryFormScripts scripts  = new CreateQueryFormScripts();
	
	@Given("^I have filled all mandatory query form fields$")
	public void i_have_filled_all_mandatory_query_form_fields() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setAllMandatoryFields();
		else	
			Assume.assumeTrue(true);
	}

	@Given("^I have attached multiple documents for query form$")
	public void i_have_attached_multiple_documents_for_query_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.attachMultipleDocuments();
		else	
			Assume.assumeTrue(true);
	}
	
	@Then("^User should be able to search Query Form on listing$")
	public void user_should_be_able_to_search_query_form_on_listing() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.searchAndVerifyQueryForm();
		else	
			Assume.assumeTrue(true);
	}
}
