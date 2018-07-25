package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreateIDPP2Scripts;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateIDPP2Form {
	
	CreateIDPP2Scripts scripts = new CreateIDPP2Scripts();
	
	@Given("^I focus on cloned project$")
	public void i_focus_on_cloned_project() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.getFocusToClonedProject();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I click on \"(.*?)\" form link$")
	public void i_click_on_form_link(String formLink) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest){
	    	scripts.clickIDPFormLink(formLink);
	    }
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I create IDP form by EPM user$")
	public void i_create_IDP_form_by_EPM_user() throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest){
	    	scripts.createIDPP2Form();
	    }
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I edit project details in IDP form by EPM user$")
	public void i_edit_project_details_in_IDP_form_by_EPM_user() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.editProjectDetails();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit stage details in IDP form by EPM user$")
	public void i_edit_stage_details_in_IDP_form_by_EPM_user() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.editStageDetails();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit stage deliverables details in IDP form by EPM user$")
	public void i_edit_stage_deliverables_details_in_IDP_form_by_EPM_user() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest){
			scripts.editStageDeliverables();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit deliverables details in IDP form by EPM user$")
	public void i_edit_deliverables_details_in_IDP_form_by_EPM_user() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editDeliverablesDetails();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I login with \"(.*?)\" user$")
	public void i_login_with_user(String role) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			if(role.equalsIgnoreCase("EPM"))
				scripts.login(ResourceHandler.loadProperty("idp.form.epm.user"), ResourceHandler.loadProperty("idp.form.epm.password"));
			else 
				scripts.login(ResourceHandler.loadProperty("idp.form.sim.uk.user"), ResourceHandler.loadProperty("idp.form.sim.uk.password"));
		}
		else
			Assume.assumeTrue(true);
	}	
	
	@When("^I share subdeliverables by SIM user$")
	public void i_share_subdeliverables_by_SIM_user() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.shareDeliverables();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I verify expected subdeliverables vs actual subdeliverables$")
	public void i_verify_expected_subdeliverables_vs_actual_subdeliverables() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileMaskKey();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I verify variuos filters picklist on IDP form$")
	public void i_verify_variuos_filters_picklist_on_IDP_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilters();
		else
			Assume.assumeTrue(true);
	}


}
