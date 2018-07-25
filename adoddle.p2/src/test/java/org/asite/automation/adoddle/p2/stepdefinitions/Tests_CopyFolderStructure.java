package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CopyFolderStructure;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CopyFolderStructure {

	CopyFolderStructure scripts = new CopyFolderStructure();

	@Given("^I context click on ([^\"]*) AND I mouse hover context click option \"(.*?)\"$")
	public void i_context_click_on_AutomationTestProject_AND_I_mouse_hover_context_click_option(String workspace,
			String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.focusWorkspaceAndSelectOption(workspace);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have created Test folder as \"(.*?)\" for copy folder structure successfully$")
	public void i_have_created_Test_folder_as_for_copy_folder_structure_successfully(String parentFolder)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createTestFolder(parentFolder);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I context click on Parent Folder \"(.*?)\" AND I mouse hover \"(.*?)\"$")
	public void i_context_click_on_Parent_Folder_AND_I_mouse_hover(String folder, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.contextClickAndSelectOption(folder);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select context click menu option as \"(.*?)\"$")
	public void i_select_context_click_menu_option_as(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectMenuOption();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I selected third child folder \"(.*?)\"$")
	public void i_selected_third_child_folder(String childFolder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectHierarchyFolderChild(childFolder);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^All Superseded Parent AND Childs folder also get selected$")
	public void all_Superseded_Parent_AND_Childs_folder_also_get_selected() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validatedCopyFolderStrutureHierarchy();
		else
			Assume.assumeTrue(true);
	}

	@When("^I selected last child folder \"(.*?)\"$")
	public void i_selected_last_child_folder(String childFolder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectHierarchyFolderChild(childFolder);
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Superseded Parent AND Childs folder also get selected successfully$")
	public void all_Superseded_Parent_AND_Childs_folder_also_get_selected_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validatedCopyFolderStrutureHierarchy();
		else
			Assume.assumeTrue(true);
	}

	@When("^I selected parent folder \"(.*?)\"$")
	public void i_selected_parent_folder(String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectHierarchyFolderChild(folder);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have selected folder permissions as \"(.*?)\" \"(.*?)\" AND \"(.*?)\"$")
	public void i_have_selected_folder_permissions_as_AND(String arg1, String arg2, String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectFolderPermission();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Reset All folder permissions as \"(.*?)\" \"(.*?)\" AND \"(.*?)\" successfully$")
	public void i_have_Reset_All_folder_permissions_as_AND_successfully(String arg1, String arg2, String arg3)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.resetAllFolderPermission();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I have Reset folder permissions as \"(.*?)\" successfully$")
	public void i_have_Reset_folder_permissions_as_successfully(String permission) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.resetFolderPermission(permission);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" button AND I have selected destination folder as \"(.*?)\"$")
	public void i_click_button_AND_I_have_selected_destination_folder_as(String arg1, String destinationFolder)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.copyFolderStructureInDirectory(destinationFolder);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^created folder structure should copied successfully in destination folder \"(.*?)\"$")
	public void created_folder_structure_should_copied_successfully_in_destination_folder(String folder)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateCopiedFolderStructure();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have selected child folder \"(.*?)\"$")
	public void i_have_selected_child_folder(String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateAndSelectChildFolder(folder);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" button AND I have validated workspace ([^\"]*) should Grayed out$")
	public void i_click_button_AND_I_have_validated_workspace_AutomationTestProject_should_Grayed_out(String arg1,
			String workspace) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickButtonAndValidateWorkspace(workspace);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" button AND Dismiss popup \"(.*?)\"$")
	public void i_click_button_AND_Dismiss_popup(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.dismissPopupCopyFolderStructure();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Clean up All Test Data folders successfully$")
	public void i_Clean_up_All_Test_Data_folders_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.cleanUpOperation();
		else
			Assume.assumeTrue(true);
	}

}