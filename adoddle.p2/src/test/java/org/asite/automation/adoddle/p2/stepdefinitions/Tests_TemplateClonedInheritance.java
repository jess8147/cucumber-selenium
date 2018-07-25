package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.TemplateClonedInheritanceScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_TemplateClonedInheritance {

	TemplateClonedInheritanceScripts scripts = new TemplateClonedInheritanceScripts();

	@Given("^I Switch to \"(.*?)\" from Adoddle$")
	public void i_Switch_to_from_Adoddle(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.switchToClassicView();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I click on tab \"(.*?)\"$")
	public void i_click_on_tab(String arg1) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnWorkSpaceTab();
		else
			Assume.assumeTrue(true);
	}

	@Then("^([^\"]*) should Available in Workspace List$")
	public void automationtestproject_should_Available_in_Workspace_List(String wSpace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateWorkspaceInAllWorkspaceList(wSpace);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on ([^\"]*) project$")
	public void i_click_on_AutomationTestProject_project(String wSpace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnWorkspace(wSpace);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on ([^\"]*) home page$")
	public void i_should_redirect_on_AutomationTestProject_home_page(String wSpace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspacePage(wSpace);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" from Admin Dropdown$")
	public void i_select_from_Admin_Dropdown(String dropDownOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectdropDownOption(dropDownOption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should open$")
	public void page_should_open(String pageName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreateEditWorkspacePage(pageName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on link \"(.*?)\"$")
	public void i_click_on(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSaveAsTemplateLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on \"(.*?)\" Page$")
	public void i_should_redirect_on_Page(String tempTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreateWorkspaceTemplatePage(tempTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Edit Workspace Template name as \"(.*?)\"$")
	public void i_Edit_Workspace_Template_name_as(String tName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editWorkspaceTemplateName(tName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on \"(.*?)\" Link$")
	public void i_click_on_Link(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.saveWorkspaceTemplate();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should created successfully AND Available in workspace template list$")
	public void should_created_sucessfully_AND_Available_in_workspace_template_list(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceTemplate();
		else
			Assume.assumeTrue(true);

	}

	@Given("^I have focus on \"(.*?)\" tab AND I have validated template \"(.*?)\" in listing$")
	public void i_have_focus_on_tab_AND_I_have_validated_template_in_listing(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateTemplateAndClickClonnedIcon();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click clonned image link of \"(.*?)\"$")
	public void i_click_clonned_image_link_of(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickClonnedImageLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on \"(.*?)\" page$")
	public void i_should_redirect_on_page(String pageTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreateWorkspacePage(pageTitle);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter all mandatory attributes of ([^\"]*) scenario \"(.*?)\"$")
	public void i_have_enter_all_mandatory_attributes_of_Cloned_AutoTestProject_UK_scenario(String cProject,
			String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterswitchDefaultProjectMandatoryAttributes(cProject, scenario);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Break Inheritance for ([^\"]*)$")
	public void i_Break_Inheritance_for_Cloned_AutoTestProject_UK(String cProject) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.brkInheritance();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should created sucessfully AND Available in all workspace list$")
	public void should_created_sucessfully_AND_Available_in_all_workspace_list(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyClonnedProjectInAllWorkspacesList(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on \"(.*?)\" tab$")
	public void i_have_focus_on_tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusWorkflowTemplates();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link AND I switch to \"(.*?)\" from Settings dropdown list$")
	public void i_click_on_link_AND_I_switch_to_from_Settings_dropdown_list(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.switchIntoAdoddleView();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on Adoddle View \"(.*?)\"$")
	public void i_should_redirect_on_Adoddle_View(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAdoddleDashboard();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on \"(.*?)\"$")
	public void i_have_focus_on(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusWorkspaceTemplate();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I put Workflow Name as \"(.*?)\" AND Workflow Days$")
	public void i_put_Workflow_Name_as_AND_Workflow_Days(String wrkName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createWorkflowDefinition(wrkName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should Re-directed to \"(.*?)\" page$")
	public void i_should_Re_directed_to_page(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateVisualWorkflowDesigner();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Design Workflow with a UserTask AND SystemTasks$")
	public void i_Design_Workflow_with_a_UserTask_AND_SystemTasks() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.designWorkflow();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Create Workflow Trigger name as \"(.*?)\" AND i put all mandatory attributes$")
	public void i_Create_Workflow_Trigger_name_as_AND_i_put_all_mandatory_attributes(String trgName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createWorkflowTrigger(trgName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" created successfully AND available in trigger Listing$")
	public void created_successfully_AND_available_in_trigger_Listing(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdatedTriggerInListing();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on \"(.*?)\" AND I Validated \"(.*?)\" should not be available$")
	public void i_have_have_focus_on_AND_I_Validated_should_not_be_available(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateWorkflowTestdataInClonedProject();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on \"(.*?)\" AND I Validated \"(.*?)\" should available$")
	public void i_have_have_focus_on_AND_I_Validated_should_available(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateWorkflowTestdataInInheritedClonedProject();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have updated \"(.*?)\" as \"(.*?)\" sucessfully for \"(.*?)\"$")
	public void i_have_updated_as_sucessfully_for(String existingSysTask, String updatedSysTask, String wInstance)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateExistingSystemTask(existingSysTask, updatedSysTask, wInstance);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have Context click on \"(.*?)\" definition AND I updated as \"(.*?)\"$")
	public void i_have_Context_click_on_defination_AND_I_updated_as(String existingWrkflow, String updatedWorkflowDef)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateWorklfowDefinition(existingWrkflow, updatedWorkflowDef);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Validated updated \"(.*?)\" workflow Definition AND I click on it$")
	public void i_Validated_updated_workflow_Defination_AND_I_click_on_it(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAndClickUpdatedWorkdefinition();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I updated \"(.*?)\" workflow design succesfully for \"(.*?)\"$")
	public void i_updated_workflow_design_succesfully_for(String arg1, String wInstance) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateExistingWorkflowDefinition(wInstance);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated \"(.*?)\" trigger as \"(.*?)\" successfully for \"(.*?)\"$")
	public void i_have_updated_trigger_as_successfully_for(String existingTrigger, String updatedTrigger,
			String wInstance) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updatedWorkflowTrigger(existingTrigger, updatedTrigger, wInstance);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on \"(.*?)\" AND I Validated \"(.*?)\" should available AND It should not Editable$")
	public void i_have_focus_on_AND_I_Validated_should_available_AND_It_should_not_Editable(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateInheritedProjectWorkflowAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on folder \"(.*?)\" in project ([^\"]*)$")
	public void i_have_focus_on_folder_in_project_InheritedCloned_AutoTestProject_UK(String folder, String project)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusProjectAndFolder(folder, project);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have published \"(.*?)\" file in folder \"(.*?)\"$")
	public void i_have_published_file_in(String fcount, String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.publishDocumentsInFolder(fcount, folder);
		else
			Assume.assumeTrue(true);
	}

	@Then("^File \"(.*?)\" should published successfully with workflowStatus as \"(.*?)\" AND workflowStage as \"(.*?)\"$")
	public void file_should_published_successfully_with_workflowStatus_as_AND_workflowStage_as(String fName,
			String wStatus, String wStage) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateWorkflowAttributes(fName, wStatus, wStage);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on cloned project ([^\"]*)$")
	public void i_have_focus_on_Cloned_AutoTestProject_UK(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusClonedProject();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have Search \"(.*?)\" AND I Validated workflowStatus as \"(.*?)\" AND workflowStage as \"(.*?)\"$")
	public void i_have_Search_AND_I_Validated_workflowStatus_as_AND_workflowStage_as(String fName, String wStatus,
			String wStage) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateWorkflowAttributes(fName, wStatus, wStage);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Search file \"(.*?)\" AND I click on file title$")
	public void i_Search_file_AND_I_click_on_file_title(String fName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchFileAndClickTitle(fName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^file \"(.*?)\" should open in HTML Viewer successfully$")
	public void file_should_open_in_HTML_Viewer_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.naviagteLHPanelAndHistory();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I navigate LH panel AND I click on History$")
	public void i_navigate_LH_panel_AND_I_click_on_History() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.naviagteLHPanelAndHistory();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Validated \"(.*?)\" distributed actions as \"(.*?)\" AND \"(.*?)\"$")
	public void i_Validated_distributed_actions_as_AND(String fName, String aName, String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDistributedActions(fName, aName);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Context Click \"(.*?)\" AND Break Inheritance and I deactivated project$")
	public void i_Context_Click_Cloned_Project_AND_Break_Inheritance_and_I_deactivate_project(String pType)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.deactivateClonedProject(pType);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search AND deactivate template successfully$")
	public void i_have_search_AND_deactivate_template_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAndDeactivateTemplate();
		else
			Assume.assumeTrue(true);
	}

}
