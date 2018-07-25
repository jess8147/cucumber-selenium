package org.asite.automation.adoddle.p1.stepdefinitions;
import org.asite.automation.adoddle.p1.scripts.AutoFetchAttributesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_AutoFeatchAttributes {

	AutoFetchAttributesScripts scripts = new AutoFetchAttributesScripts();
	
	@Then("^\"(.*?)\" page should be displayed$")
	public void page_should_be_displayed(String pageTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyPageHeader(pageTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Edit icon of existing rule")
	public void i_click_on_Edit_icon_of_rule_Auto_Fetch_Rule() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.clickAutoFetchRuleEditIcon();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get valid file attributes with dropdown \"(.*?)\"$")
	public void i_get_file_attributes_as_per_order(String attribType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.getValidFileAttributes(attribType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I get invalid file attributes with dropdown \"(.*?)\"$")
	public void i_get_invalid_file_attributes_as_per_order(String attribType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.getInValidFileAttributes(attribType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I edit existing rule title$")
	public void i_edit_rule_title_with_epoch() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.editExistingRuleTitle();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Rule should get saved successfully$")
	public void rule_should_get_saved() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyRuleIsSaved();
		else
			Assume.assumeTrue(true);
	}

	@When("^I create file with conjuction of all attribute values$")
	public void i_create_file_with_conjuction_of_all_attribute_values() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.createUploadTestDataFile();
		else
			Assume.assumeTrue(true);
	}

	@When("^I upload file with conjuction of all attribute values$")
	public void i_upload_file_with_conjuction_of_all_attribute_values() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.uploadFileWithAttributesConjuction();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attributes should get auto fetched in upload popup \"(.*?)\"$")
	public void all_custom_attributes_should_get_auto_fetched_in_upload_popup(String flag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyAutoFetchAttributes(flag);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I upload all above files all together$")
	public void i_upload_all_above_files_all_together() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.uploadAllFilesAllTogether();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Respective attributes should be seen for each file$")
	public void respective_attributes_should_be_seen_for_each_file() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyAllAttributesForAllFiles();
		else
			Assume.assumeTrue(true);
	}
	
}
