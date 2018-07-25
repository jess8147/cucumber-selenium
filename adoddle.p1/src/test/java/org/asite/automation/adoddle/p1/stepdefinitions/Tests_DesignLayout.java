package org.asite.automation.adoddle.p1.stepdefinitions;
import org.asite.automation.adoddle.p1.scripts.DesignLayoutScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DesignLayout {

	DesignLayoutScripts scripts = new DesignLayoutScripts();
	
	@Given("^([^\"]*) is available for Design Layout$")
	public void org_is_available_for_Design_Layout(String orgTitle) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyLayoutForOrg(orgTitle);
	    else
	    	Assume.assumeTrue(true);
	}
	
	@When("^I click on edit icon of ([^\"]*) design layout$")
	public void i_click_on_edit_icon_of_Automation_Bill_To_Org_design_layout(String orgTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.clickOrgLayoutEditIcon(orgTitle);
	    else
	    	Assume.assumeTrue(true);
	}

	@Then("^I collect all layout configurations for ([^\"]*) organization$")
	public void i_collect_all_layout_configurations_for_organization(String orgTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.collectLayoutConfigurations(orgTitle);
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I set editable \"(.*?)\" to ([^\"]*)$")
	public void i_set_editable_option_to_Org(String editOption, String org) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.setEditableOptionToLayout(editOption, org);
	    else
	    	Assume.assumeTrue(true);
	}

	@Then("^([^\"]*) should follow layout of ([^\"]*) organization$")
	public void layoutuser_atest_com_should_follow_layout_of_Automation_Bill_To_Org_organization(String user, String org) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyUserLayout(org);
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I set no changes to ([^\"]*)$")
	public void i_set_no_changes_to_Org(String org) throws Throwable {
	    System.out.println("to be done");
	}

	@Then("^([^\"]*) should follow customized options \"(.*?)\"$")
	public void layoutuser_atest_com_should_follow_customized_options(String user, String flag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyCustomizedOptions(flag);
	    else
	    	Assume.assumeTrue(true);
	}

	@When("^I edit ([^\"]*) and set ApplyTo option to \"(.*?)\"$")
	public void i_edit_Automation_User_Org_and_set_ApplyTo_option_as(String org, String applyTo) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.editApplyToOptionInOrgLayout(org, applyTo);
	    else
	    	Assume.assumeTrue(true);
	}

}
