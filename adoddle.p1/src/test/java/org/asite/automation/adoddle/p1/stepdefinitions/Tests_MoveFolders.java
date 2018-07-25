package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.MoveFoldersScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_MoveFolders {
	/* ****** "Tests_MoveFolders" is developed by "Vishal Modi" ****** */

	MoveFoldersScripts foldersScripts = new MoveFoldersScripts();

	@When("^I have validated folders \"(.*?)\" AND \"(.*?)\" should available in Folder Listing$")
	public void i_have_validated_folders_AND_should_available_in_Folder_Listing(String folder1, String folder2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.setUpTestData(folder1, folder2);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on ([^\"]*) project$")
	public void i_click_on_AutomationTestProject_project(String projectName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.clickOnProject(projectName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I right click on \"(.*?)\" And drag mouse to \"(.*?)\"$")
	public void i_right_click_on_And_drag_mouse_to(String folderName, String menuOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.clickOnFolderAndSelectOption(folderName, menuOption);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" Folder$")
	public void i_select(String folderName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.selectFolderIntoTargetPopup(folderName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^click on submit button$")
	public void click_on_submit_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.clickOnSubmit();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" folder should be moved into \"(.*?)\" with its child folder and all documents$")
	public void folder_should_be_moved_into_with_its_child_folder_and_all_documents(String movedFolder,
			String parentFolder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.verifyMovedFolderIntoFolder(movedFolder, parentFolder);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" into ([^\"]*) project$")
	public void i_click_on_into_project(String folderName, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.clickOnFolder(folderName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" And click on More Options$")
	public void i_select_And_click_on_More_Options(String folderName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.clickOnFolderAndMoreOptions(folderName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Move Folder icon on Options popup$")
	public void i_click_on_Move_Folder_icon_on_Options_popup() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.clickOnMoveFolder();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select ([^\"]*) Project$")
	public void i_select_AutomationTestProject(String projectName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.selectFolderIntoTargetPopup(projectName);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" folder should be moved into ([^\"]*) with its child folder and all documents$")
	public void folder_should_be_moved_into__with_its_child_folder_and_all_documents(String movedFolder,
			String projectName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			foldersScripts.verifyMovedFolderIntoProject(movedFolder, projectName);
		} else
			Assume.assumeTrue(true);
	}
}
