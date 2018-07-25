package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateProjectScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Tests_CreateProject
{
	
	CreateProjectScripts projectScript = new CreateProjectScripts();
	
	@Then("^\"(.*?)\" button should be displayed$")
	public void button_should_be_displayed(String addProjectText) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			projectScript.verifyAddProject(addProjectText);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" in LH panel$")
	public void i_click_on_in_LH_panel(String arg1) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			projectScript.clickOnAddProject();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I have entered ProjectName AND Description AND I have selected Geography as ([^\"]*)$")
	public void i_have_entered_ProjectName_AND_Description_AND_I_have_selected_Geography_as(String dcCenter) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest){
			projectScript.createProjectDetails();
			projectScript.geographyDropdownSelect(dcCenter);
		}
		else
			Assume.assumeTrue(true);
	}

	
	@When("^I click on \"(.*?)\" button on popup$")
	public void i_click_on_button(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			projectScript.clickOnCreate();
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Project should be created AND I should be redirected on \"(.*?)\" tab AND Project with ProjectName should be available in project listing$")
	public void project_should_be_created_AND_I_should_be_redirected_on_tab_AND_Project_with_ProjectName_should_be_available_in_project_listing(String linkTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
			projectScript.verifyAsiteMenuList(linkTab);
			projectScript.verifyProjectName();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click again Project Tab AND I right click on ProjectName project AND I clicked on Edit Project$")
	public void i_click_again_Project_Tab_AND_I_right_click_on_ProjectName_project_AND_I_clicked_on_Edit_Project() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			projectScript.clickOnEditProject();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Change the ProjectName AND I click on save button$")
	public void i_Change_the_ProjectName_AND_I_click_on_save_button() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
			projectScript.editProject();
			projectScript.clickOnEditSave();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New ProjectName project should be available in project listing$")
	public void new_ProjectName_project_should_be_available_in_project_listing() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			projectScript.verifyChangedProjectName();
		else
			Assume.assumeTrue(true);
	}

	@When("^I right click again on ProjectName project AND I clicked on Edit Project AND I click on \"(.*?)\" dropdown ANd selected \"(.*?)\" AND click on save button$")
	public void i_right_click_again_on_ProjectName_project_AND_I_clicked_on_Edit_Project_AND_I_click_on_dropdown_ANd_selected_AND_click_on_save_button(String arg1, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest){
			projectScript.clickOnEditedProject();
			projectScript.statusDropdownSelect();
			projectScript.clickOnEditSave();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^ProjectName project should be closed AND ProjectName project should not be available in project listing$")
	public void projectname_project_should_be_closed_AND_ProjectName_project_should_not_be_available_in_project_listing() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			projectScript.verifyProjectClosed();
		else
			Assume.assumeTrue(true);
	}
}
