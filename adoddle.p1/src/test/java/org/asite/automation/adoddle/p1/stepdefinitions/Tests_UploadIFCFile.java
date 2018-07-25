package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.UploadIFCFileScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_UploadIFCFile {
	
	UploadIFCFileScripts scripts = new UploadIFCFileScripts();
	
	@When("^I Click on \"(.*?)\" button From LH Panel$")
	public void i_Click_on_button_From_LH_Panel(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickAddModelButton();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" popup should open on Models Page$")
	public void add_model_popup_should_open(String textAddModel) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddModelPopup(textAddModel);
		else
			Assume.assumeTrue(true);
	}

	@When("^I filled all mendatory fields And click on \"(.*?)\" button$")
	public void i_filled_all_mendatory_fields_And_click_on_button(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createModel();
		else
			Assume.assumeTrue(true);
		
	}

	@Then("^Model should be created$")
	public void model_should_be_created() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyModelCreateSuccess();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Model should be available in Models listing$")
	public void model_should_be_available_in_Models_listing() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyModelOnListing();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have atleast one model on Models listing$")
	public void i_have_atleast_one_model_on_Models_listing() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyModelExists();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have \"(.*?)\" on models listing$")
	public void i_have_on_models_listing(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectListViewOnModels();
		else
			Assume.assumeTrue(true);
	    
	}

	@When("^I right click on \"(.*?)\" Model And click on \"(.*?)\"$")
	public void i_right_click_on_Model_And_click_on(String arg1, String arg2) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickModelUpload();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" popup should open for IFC Upload$")
	public void popup_should_open_for_IFC_Upload(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadDialog(arg1);
	}

	@When("^I select Wrokset And select \"(.*?)\" from local And click on \"(.*?)\" button$")
	public void i_select_Wrokset_And_select_from_local_And_click_on_button(String arg1, String arg2) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.uploadIFCFile();
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^Upload IFC file should be started$")
	public void upload_IFC_file_should_be_started() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUploadProgress();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should open for IFC upload$")
	public void popup_should_open(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyActivityCentrePopupText(arg1);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" progress should be available on \"(.*?)\" popup$")
	public void progress_should_be_available_on_popup(String arg1, String arg2) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFileUploadProcessBar();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Loading image for \"(.*?)\" should be available on \"(.*?)\" popup$")
	public void loading_image_for_should_be_available_on_popup(String arg1, String arg2) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyLoadingImageForMerging();
		else
			Assume.assumeTrue(true);
	}


}
