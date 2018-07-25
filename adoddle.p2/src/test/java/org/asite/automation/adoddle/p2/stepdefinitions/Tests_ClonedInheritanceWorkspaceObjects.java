package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ClonedInheritanceWorkspaceObjects;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ClonedInheritanceWorkspaceObjects {

	ClonedInheritanceWorkspaceObjects scripts = new ClonedInheritanceWorkspaceObjects();

	@Then("^I Edit Workspace Template name as \"(.*?)\" successfully$")
	public void i_Edit_Workspace_Template_name_as_successfully(String template) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateWorkspaceTemplate(template);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should created successfully AND should Available in workspace Template list$")
	public void should_created_successfully_AND_should_Available_in_workspace_Template_list(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateWorkflowInstances();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter all mandatory fields of ([^\"]*) scenario \"(.*?)\"$")
	public void i_have_enter_all_mandatory_fields_of_Cloned_AutoTestProject_UK_scenario(String workspace,
			String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterWorkspaceMandatoryAttributes(workspace, scenario);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should created successfully AND Available in all workspace list$")
	public void should_created_successfully_AND_Available_in_all_workspace_list(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceInListing(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on \"(.*?)\" Tab$")
	public void i_have_focus_on_Tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusWorkflowTemplates();
		else
			Assume.assumeTrue(true);
	}

	@Then("^([^\"]*) should Available in Template List$")
	public void automationtest_workspace_uk_should_Available_in_Template_List(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateTemplateInTemplateList();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on ([^\"]*) in template List$")
	public void i_click_on_AutomationTest_Workspace_UK_in_template_List(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.naviagteAndClickTemplate();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on ([^\"]*) home Page$")
	public void i_should_redirect_on_AutomationTest_Workspace_UK_home_Page(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyWorkspaceTemplateHomePage();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have created new userRole as \"(.*?)\" with users as \"(.*?)\" \"(.*?)\"$")
	public void i_have_created_new_userRole_as_with_users_as(String role, String user1, String user2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createUserRoleInTemplate(role);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated existing role \"(.*?)\" as \"(.*?)\" AND users as \"(.*?)\" \"(.*?)\"$")
	public void i_have_updated_existing_role_as_AND_users_as(String existingRole, String newRole, String user2,
			String user3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateUserRoleInTemplate(existingRole, newRole);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" Link on top Panel$")
	public void i_click_Link_on_top_Panel(String link) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnWorkspaceHome();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" link$")
	public void i_click_link(String wsDocs) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnWorkspaceDocuments(wsDocs);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should redirect on \"(.*?)\" Listing$")
	public void i_should_redirect_on_Listing(String folderPath) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentListing();
		else
			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\" img icon$")
	public void i_Click_on_img_icon(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnCreateNewParentFolder();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should be open$")
	public void page_should_be_open(String pageTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyFolderPage(pageTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Enter FolderName \"(.*?)\" AND I Click on \"(.*?)\" button$")
	public void i_Enter_FolderName_AND_I_Click_on_button(String folder, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterFolderNameAndCreateFolder(folder);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Folder should be created AND Folder should be available in Folder Tree$")
	public void folder_should_be_created_AND_Folder_should_be_available_in_Folder_Tree() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedFolderIntoFolderTree();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated existing directory \"(.*?)\" as \"(.*?)\" successfully in listing$")
	public void i_have_updated_existing_directory_as_successfully_in_listing(String folder1, String folder2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateExistingDirectoryInLisitng(folder1, folder2);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on \"(.*?)\" AND I have validated created AND updated Directories successfully in Listing$")
	public void i_have_focus_on_AND_I_have_validated_created_AND_updated_Directories_successfully_in_Listing(
			String workspace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusWorkspaceAndValidateDirectories(workspace);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have focus on \"(.*?)\" AND I have validated All Directories remain intact$")
	public void i_have_focus_on_AND_I_have_validated_All_Directories_remain_intact(String workspace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusWorkspaceAndValidateDirectories(workspace);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select option \"(.*?)\" from Admin Dropdown$")
	public void i_select_option_from_Admin_Dropdown(String dropDownOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectOptionfromAdminDropdown(dropDownOption);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" icon$")
	public void i_click_icon(String imgType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickCreateStatusIcon(imgType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter docStatus name as \"(.*?)\" AND its mandatory attributes successfully$")
	public void i_have_enter_docStatus_name_as_AND_its_mandatory_attributes_successfully(String status)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterDocStatusAndPoiMandatoryAttributes(status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on \"(.*?)\" Button on pop up \"(.*?)\"$")
	public void i_click_on_Button_on_pop_up(String arg1, String popUpText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickButtonOnPopup(popUpText);
			scripts.waitUntilStatusPoiPageLoad();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^docStatus \"(.*?)\" should created successfully AND Available in Manage Status list$")
	public void docstatus_should_created_successfully_AND_Available_in_Manage_Status_list(String status)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateStatusAndPoiInListing(status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter POI name as \"(.*?)\" AND its mandatory attributes successfully$")
	public void i_have_enter_POI_name_as_AND_its_mandatory_attributes_successfully(String poi) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterDocStatusAndPoiMandatoryAttributes(poi);
		else
			Assume.assumeTrue(true);
	}

	@Then("^POI \"(.*?)\" should created successfully AND Available in Manage POI list$")
	public void poi_should_created_successfully_AND_Available_in_Manage_POI_list(String poi) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateStatusAndPoiInListing(poi);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated existing POI \"(.*?)\" as \"(.*?)\" successfully in Manage POI list$")
	public void i_have_updated_existing_POI_as_successfully_in_Manage_POI_list(String existingPoi, String updatedPoi)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateExistingPoiInTemplate(existingPoi, updatedPoi);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" link AND I Switch to \"(.*?)\" from Settings Dropdown list$")
	public void i_click_on_link_AND_I_Switch_to_from_Settings_Dropdown_list(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.switchIntoAdoddleView();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have search \"(.*?)\" successfully And I context click$")
	public void i_have_search_successfully_And_I_context_click(String project) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchWorkspaceAndContextClick(project);
		else
			Assume.assumeTrue(true);

	}

	@Then("^I mouse hover \"(.*?)\" AND I have select context click menuOption as \"(.*?)\"$")
	public void i_mouse_hover_AND_I_have_select_context_click_menuOption_as(String arg1, String contextClickoption)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectProjectContextMenuOption(contextClickoption);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated docStatus \"(.*?)\" should available AND Grayed out in \"(.*?)\"$")
	public void i_have_validated_docStatus_should_available_AND_Grayed_out_in(String docStatus, String workspace)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocStatusAndPoiInClonedAndInheritedWorkspaces(docStatus, workspace);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated docStatus \"(.*?)\" should not available in \"(.*?)\"$")
	public void i_have_validated_docStatus_should_not_available_in(String docStatus, String workspace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocStatusAndPoiInClonedAndInheritedWorkspaces(docStatus, workspace);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated POI \"(.*?)\" should available AND Grayed out in \"(.*?)\"$")
	public void i_have_validated_POI_should_available_AND_Grayed_out_in(String poi, String workspace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocStatusAndPoiInClonedAndInheritedWorkspaces(poi, workspace);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated POI \"(.*?)\" should not available in \"(.*?)\"$")
	public void i_have_validated_POI_should_not_available_in(String poi, String workspace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDocStatusAndPoiInClonedAndInheritedWorkspaces(poi, workspace);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on workspace \"(.*?)\" AND I have created Directory as \"(.*?)\"$")
	public void i_have_focus_on_workspace_AND_I_have_created_Directory_as(String arg1, String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusWorkspaceAndCreateDirectory(folder);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have published Document with created docStatus AND updated existing POI Successfully$")
	public void i_have_published_Document_with_created_docStatus_AND_updated_existing_POI_Successfully()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.publishMultipleDocuments();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated userRoles \"(.*?)\" AND \"(.*?)\" should available AND Grayed out$")
	public void i_have_validated_userRoles_AND_should_available_AND_Grayed_out(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdiateUserRolesInInheritedWorkspace();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated userRoles \"(.*?)\" AND \"(.*?)\" should not available$")
	public void i_have_validated_userRoles_AND_should_not_available(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdiateUserRolesInClonedWorkspace();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have copied new appTemplate as \"(.*?)\" successfully$")
	public void i_have_copied_new_appTemplate_as_successfully(String appName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.addAppTemplateInWorkspaceTemplate();
			scripts.activateAvailableAppTemplates();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated appTemplate \"(.*?)\" name AND XSN as \"(.*?)\" successfully$")
	public void i_have_updated_appTemplate_name_AND_XSN_as_successfully(String appTemplate, String updatedAppName)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.switchToMultipleFrames();
			scripts.updateAppTemplateInWorkspaceTemplate(appTemplate, updatedAppName);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated existing appTemplate \"(.*?)\" name AND XSN as \"(.*?)\" successfully$")
	public void i_have_updated_existing_appTemplate_name_AND_XSN_as_successfully(String appTemplate,
			String updatedAppName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateAppTemplateInWorkspaceTemplate(appTemplate, updatedAppName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated appTemplate \"(.*?)\" AND \"(.*?)\" should available AND Grayed out$")
	public void i_have_validated_appTemplate_AND_should_available_AND_Grayed_out(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdiateAppTemplateInInheritedWorkspace();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated appTemplate \"(.*?)\" AND \"(.*?)\" should not available$")
	public void i_have_validated_appTemplate_AND_should_not_available(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.valdiateAppTemplateClonedWorkspace();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I navigate tab \"(.*?)\"$")
	public void i_navigate_tab(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.navigateFormPermissionTab();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have assigned \"(.*?)\" AND \"(.*?)\" Permission to All AppTemplates successfully$")
	public void i_have_assigned_AND_Permission_to_All_AppTemplates_successfully(String permissionCreate,
			String permissionView) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.assignedRoleFormPermission(permissionCreate, permissionView);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on workspace \"(.*?)\" AND AppTemplate \"(.*?)\"$")
	public void i_have_focus_on_workspace_AND_AppTemplate(String arg1, String appTemplate) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusWorksapceAndAppTemplate(appTemplate);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have created an app as \"(.*?)\" with appTemplate \"(.*?)\" successfully$")
	public void i_have_created_an_app_as_with_appTemplate_successfully(String form, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createFormWithAppTemplate(form);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" form should created successfully AND I validated App XSN \"(.*?)\" successfully$")
	public void form_should_created_sucessfully_AND_I_validated_App_XSN_successfully(String form, String appTitle)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAppInListing(form);
		else
			Assume.assumeTrue(true);
	}

	@When("^I Context click on \"(.*?)\" I mouse hover on \"(.*?)\" AND I select option \"(.*?)\"$")
	public void i_Context_click_on_I_mouse_hover_on_AND_I_select_option(String project, String arg2, String arg3)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextClickAndSelectMenuOption(project);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated \"(.*?)\" Action should not available on popUp \"(.*?)\" AND in File Viewer successfully$")
	public void i_have_validated_Action_should_not_available_on_popUp_AND_in_File_Viewer_successfully(String arg1,
			String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateActionInApp();
			scripts.validateAppActionInFileViewer();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I Distributed form \"(.*?)\" with Respond action to User \"(.*?)\"$")
	public void i_Distributed_form_with_Respond_action_to_User(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.distributeAppAction();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search form \"(.*?)\" And I validated \"(.*?)\" action successfully$")
	public void i_have_search_form_And_I_validated_action_successfully(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchAndValidatedAppAction();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have completed \"(.*?)\" action successfully$")
	public void i_have_completed_action_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performAppAssignedAction();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have deactivated Cloned AND Inherited workspaces successfully$")
	public void i_have_deactivated_Cloned_AND_Inherited_workspaces_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.cleanUpOperation();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have search AND deactivated template successfully$")
	public void i_have_search_AND_deactivated_template_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAndDeactivateTemplate();
		else
			Assume.assumeTrue(true);
	}

}