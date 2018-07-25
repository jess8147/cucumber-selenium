package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.XREFAppletUploadScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_XREFAppletUpload {

	XREFAppletUploadScripts scripts = new XREFAppletUploadScripts();

	@Then("^I put folderName as \"(.*?)\" AND I Click on Create button$")
	public void i_put_folderName_as_AND_I_Click_on_Create_button(String folderName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createTestFolders(folderName);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should be created AND I validate in Folder Tree$")
	public void should_be_created_AND_I_validate_in_Folder_Tree(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateFoldersInFolderTree();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus folder \"(.*?)\"$")
	public void i_have_focus_folder(String folder) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusOnFolder(folder);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have Search file \"(.*?)\" file AND get Current version AND Current \"(.*?)\" revision$")
	public void i_have_Search_file_file_AND_get_Current_version_AND_Updated_revision(String fName, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.searchParentFileAndGetVersion(fName);
			scripts.getXRefCurrentChildRevision();
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I Reset current Browser to \"(.*?)\" in ([^\"]*) scenario \"(.*?)\"$")
	public void i_Reset_current_Browser_to_in_UK_scenario(String browser, String dc, String scenario) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.resetCurrentBrowser(browser, dc, scenario);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click link for \"(.*?)\"$")
	public void i_click_link_for(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnAppletUploadLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Applet upload popup should open$")
	public void applet_upload_popup_should_open() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAppletUploadPopup();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Add Files button on Applet upload popup AND uploadType as \"(.*?)\"$")
	public void i_click_on_Add_Files_button_on_Applet_upload_popup_AND_uploadType_as(String uploadType)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performAppletUpload(uploadType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Popup \"(.*?)\" of Local file System should Open$")
	public void popup_of_Local_file_System_should_Open(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have selected \"(.*?)\" X-Ref With Multiple File$")
	public void i_have_selected_X_Ref_With_Multiple_File(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have clicked on \"(.*?)\"  button$")
	public void i_have_clicked_on_button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have unchecked one of the child of \"(.*?)\" X-Ref File$")
	public void i_have_unchecked_one_of_the_child_of_X_Ref_File(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click \"(.*?)\" Button$")
	public void i_click_Button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Attributes of \"(.*?)\" X-Ref File should load successfully$")
	public void all_Attributes_of_X_Ref_File_should_load_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Entered All mandatory Attributes of \"(.*?)\" X-Ref file$")
	public void i_Entered_All_mandatory_Attributes_of_X_Ref_file(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I click on Apply To All button$")
	public void i_click_on_Apply_To_All_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Attributes should be filled with values AND \"(.*?)\" should be filled with combine custom attributes$")
	public void all_Attributes_should_be_filled_with_values_AND_should_be_filled_with_combine_custom_attributes(
			String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I select \"(.*?)\" in Distribute dropdown$")
	public void i_select_in_Distribute_dropdown(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^click on \"(.*?)\" button$")
	public void click_on_button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I validated \"(.*?)\" as \"(.*?)\" AND \"(.*?)\" as \"(.*?)\" successfully in Listing AND in Viewer$")
	public void i_validated_as_AND_as_successfully_in_Listing_AND_in_Viewer(String arg1, String versionInstance,
			String arg3, String childRevision) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.validateXRefDocumentVersion(versionInstance, childRevision);
			scripts.validateXRefChildUpdatedRevision(childRevision);

		} else
			Assume.assumeTrue(true);
	}

	@When("^I have entered ([^\"]*) AND Description AND I have selected Geography as ([^\"]*)$")
	public void i_have_entered_ProjectName_AND_Description_AND_I_have_selected_Geography_as(String pIdentifier,
			String dcCenter) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createProjectDetails(pIdentifier);
			scripts.geographyDropdownSelect(dcCenter);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I turned off \"(.*?)\" successfully$")
	public void i_turned_off_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.breakXRefInstancesSharing();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on popup$")
	public void i_click_on_button(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnCreate();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Project should be created AND I should be redirected on \"(.*?)\" tab AND Project with ProjectName should be available in project listing$")
	public void project_should_be_created_AND_I_should_be_redirected_on_tab_AND_Project_with_ProjectName_should_be_available_in_project_listing(
			String linkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAsiteMenuList(linkTab);
			scripts.verifyProjectName();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I Context Click on ([^\"]*)$")
	public void i_Context_Click_on_AutoTestProject(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextclickOnProjectName();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Again I Right Click on Project as ([^\"]*)$")
	public void again_I_Right_Click_on_Project_as_AutoTestProject(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextclickOnProjectName();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus Project ([^\"]*)$")
	public void i_have_focus_Project_AutoTestProject(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusOnWorkspace();
		else
			Assume.assumeTrue(true);
	}

}
