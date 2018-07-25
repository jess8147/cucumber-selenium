package org.asite.automation.adoddle.p1.stepdefinitions;
import org.asite.automation.adoddle.p1.scripts.CreateIDPFormScripts;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateIDPForm {
	
	CreateIDPFormScripts scripts  = new CreateIDPFormScripts();
	
	@Given("^I have cloned existing project ([^\"]*) with grography ([^\"]*)$")
	public void i_have_cloned_existing_project_AutomationTestProject(String existingPorjectName, String geoGraphy) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			scripts.cloneProject(existingPorjectName, geoGraphy);
		else
			Assume.assumeTrue(true);

	}
	
	@Given("^I have uploaded IDP files to idp template with Admin user$")
	public void i_have_uploaded_idp_files_to_idp_template() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadIDPFilesToTemplate();
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I focus on cloned project$")
	public void i_focus_on_cloned_project() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.getFocusToClonedProject();
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I click on \"(.*?)\" form link$")
	public void i_click_on_idpt_form_link(String formLink) throws Throwable {
			if (Tests_CommonStepDefinitions.runTest)
				scripts.clickIDPTFormLink(formLink);
			else
				Assume.assumeTrue(true);
	}
	
	/*@When("^I create IDP form template$")
	public void setup_project_dc_center() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createIDPTemplateForm();
		else
			Assume.assumeTrue(true);
	}*/
	
	@When("^I create IDP form by EPM user$")
	public void i_create_idp_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createIDPForm();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit project details in IDP form by EPM user$")
	public void i_edit_project_details_in_IDP_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editProjectDetails();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit stage details in IDP form by EPM user$")
	public void i_edit_stage_details_in_IDP_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editStageDetails();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit deliverables details in IDP form by EPM user$")
	public void i_edit_deliverables_details_in_IDP_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editDeliverablesDetails();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit stage deliverables details in IDP form by EPM user$")
	public void i_edit_stage_deliverables_details_in_IDP_form_by_sim_user() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editStageDeliverables();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Stage status should be \"(.*?)\" for Deliverable and \"(.*?)\" for subdeliverable1 and \"(.*?)\" for subdeliverable2$")
	public void verifyStageDeliverablesStatus(String color1, String color2, String color3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyIDPFormStageStatus(color1, color2);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit stage deliverables date to \"(.*?)\" by EPM user$")
	public void i_edit_stage_deliverables_date_by_EPM_user(String date) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editStageDeliverablesDate(date);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I upload subdeliverables by Supplier user$")
	public void i_upload_subdeliverables_by_supplier_user() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.uploadDeliverablesSupplierUser();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I login with \"(.*?)\" user$")
	public void i_login_with_user(String role) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			if(role.equalsIgnoreCase("EPM"))
				scripts.login(ResourceHandler.loadProperty("idp.form.epm.user"), ResourceHandler.loadProperty("idp.form.epm.password"));
			else if(role.equalsIgnoreCase("Supplier"))
				scripts.login(ResourceHandler.loadProperty("idp.form.supplier.user1"), ResourceHandler.loadProperty("idp.form.supplier.password1"));
			else if(role.equalsIgnoreCase("SIM") && Tests_CommonStepDefinitions.dataCenter.equalsIgnoreCase("uk"))
				scripts.login(ResourceHandler.loadProperty("idp.form.sim.uk.user"), ResourceHandler.loadProperty("idp.form.sim.uk.password"));
			else if(role.equalsIgnoreCase("SIM") && Tests_CommonStepDefinitions.dataCenter.equalsIgnoreCase("us"))
				scripts.login(ResourceHandler.loadProperty("idp.form.sim.us.user"), ResourceHandler.loadProperty("idp.form.sim.us.password"));
			else if(role.equalsIgnoreCase("SIM") && Tests_CommonStepDefinitions.dataCenter.equalsIgnoreCase("aus"))
				scripts.login(ResourceHandler.loadProperty("idp.form.sim.aus.user"), ResourceHandler.loadProperty("idp.form.sim.aus.password"));
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
	
	@When("^I edit status for \"(.*?)\" to \"(.*?)\" by EPM user$")
	public void i_edit_status_for_subdeliverable_to_for_publishing_by_epm_user(String file, String statusChangeTo) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.changeStatusToForPublishing(file, statusChangeTo);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I navigate to HTML apps download url$")
	public void i_navigate_to_html_apps_download_url() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.navigateToHTMLAppsDownload();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I download IDP files from svn$")
	public void i_download_idp_files_from_svn() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadIDPFiles();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^IDP files should get downloaded successfully$")
	public void idp_files_should_get_downloaded_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyIDPFileDownload();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I download COBieIR from IDP form$")
	public void i_download_cobieir_from_idp_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.downloadCoBieIR();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^File should get downloaded successfully$")
	public void file_should_get_downloaded_successfully() throws Throwable {
	    System.out.println("Covered in previous definition");
	}

	@Then("^File should contain uploaded Deliverables$")
	public void file_should_contain_uploaded_Deliverables() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCoBieIRFile();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^User should be able to close project$")
	public void user_should_be_able_to_close_project() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.closeTheProject();
		else
			Assume.assumeTrue(true);
	}
}
