package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.HighMediumLowFlagsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_HighMediumLowFlags
{
	HighMediumLowFlagsScripts scripts = new HighMediumLowFlagsScripts();

	@When("^I select ([^\"]*) project AND select ([^\"]*) App folder AND select ([^\"]*) Apptype AND I get total forms title$")
	public void i_select_project_AND_select_App_folder_AND_select_Apptype_AND_I_get_total_forms_title(String project, String appFolder, String appType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getFormNameList(project, appFolder, appType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select ([^\"]*) project AND select ([^\"]*) App folder AND select ([^\"]*) Apptype AND select more then one Forms for \"(.*?)\" flag$")
	public void i_select_project_AND_select_App_folder_AND_select_Apptype_AND_select_more_then_one_Forms_for_flag(String project, String appFolder, String appType, String flag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectMoreFormsForFlag(project, appFolder, appType, flag);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I right click on selected Forms and select \"(.*?)\" AND I click on \"(.*?)\" widget$")
	public void i_right_click_on_selected_Forms_and_select_AND_I_click_on_widget(String flag, String flagType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectFlagType(flag, flagType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^only \"(.*?)\" flag Forms should displayed$")
	public void only_flag_Forms_should_displayed(String flag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFlagTypeAndForms(flag);
		else
			Assume.assumeTrue(true);
	}

	@Then("^all active flags Forms should displayed$")
	public void all_active_flags_Forms_should_displayed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllFlagFilterForms();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select all Forms AND right click on selected Forms And drag mouse to \"(.*?)\" And I click on \"(.*?)\"$")
	public void i_select_all_Forms_AND_right_click_on_selected_Forms_And_drag_mouse_to_And_I_click_on(String flag, String flagType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectFlagType(flag, flagType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^all active flags Forms should set as \"(.*?)\" Forms$")
	public void all_active_flags_Forms_should_set_as_Forms(String flag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFlagTypeAndForms(flag);
		else
			Assume.assumeTrue(true);
	}
	
	
	/*** Set Flags on Multiple Apptypes ***/

	@When("^I set more than one Forms \"(.*?)\" as \"(.*?)\", \"(.*?)\" and \"(.*?)\" flags$")
	public void i_set_more_than_one_Forms_as_and_flags(String flag, String highFlag, String midFlag, String lowFlag) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setFlagsOnMultipleApptypes(flag, highFlag, midFlag, lowFlag);
		else
			Assume.assumeTrue(true);
	}
}
