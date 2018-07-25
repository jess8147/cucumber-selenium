package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.BatchPrintScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_BatchPrint {

	BatchPrintScripts scripts = new BatchPrintScripts();

	@Given("^I have selected multiple files on document listing$")
	public void i_have_selected_multiple_files_on_document_listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectMultipleFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on More Options on files tab$")
	public void i_click_on_More_Options() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickMoreOptions();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Print File icon$")
	public void i_click_on_Print_File_icon() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickPrintFileIcon();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All Selected files should be avialable on Print Document popup$")
	public void All_Selected_files_should_be_avialable_on_Print_Document_popup() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAllSelectedBatchPrintFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Incl\\. Markups AND Incl\\. Changemarks AND Fit Inside Banners$")
	public void i_select_Incl_Markups_AND_Incl_Changemarks_AND_Fit_Inside_Banners() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectPrintDocumentOptions();
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Incl\\. Markups AND Fit Inside Banners$")
	public void i_select_Incl_Markups_AND_Fit_Inside_Banners() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectPrintOptions();
		else
			Assume.assumeTrue(true);
	}

	@Given("^click on \"(.*?)\" link$")
	public void given_click_on_link_with_text(String linkText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickLinkWithText(linkText);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^New Window should open with title as \"(.*?)\"$")
	public void new_Window_should_open_with_title(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNewWindowOpens();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter file name as \"(.*?)\" AND I click on \"(.*?)\"$")
	public void i_have_enter_file_name_as_AND_I_click_on(String fileName, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createBatchFileInLocal(fileName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated \"(.*?)\" file size successfully with local file$")
	public void i_have_validated_file_size_successfully_with_local_file(String existingFileName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.compareFileSizeInLocal(existingFileName);
		else
			Assume.assumeTrue(true);
	}
	
	/*@Then("^I have validated \"([^\"]*)\" file size successfully with local saved file$")
	public void i_have_validated_file_size_successfully_with_local_saved_file(String existingFileName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.compareFileSizeInLocalForUniversalWebViewerWS(existingFileName);
		else
			Assume.assumeTrue(true);
	}*/

	@When("^I right click on selected documents$")
	public void i_right_click_on_selected_documents() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.contextClickOnSelectedDocs();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" on Context menu popup$")
	public void i_click_on_Print_File_on_Context_menu_popup(String printFileText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnContextMenutOptionPrintFile(printFileText);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I focus on Universal Web Viewer ([^\"]*)$")
	public void i_focus_on_Universal_Web_Viewer_Automation_project(String elementText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.focusOnProject(elementText);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select Incl\\. Markups in Printing options$")
	public void i_select_Incl_Markups_in_Printing_options() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectPrintOptions();
		else
			Assume.assumeTrue(true);
	}
	
	/*@Then("^I validate \"([^\"]*)\" file size successfully with local file$")
	public void i_validate_file_size_successfully_with_local_file(String existingFileName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.compareFileSizeInLocalForUniversalWebViewerWS(existingFileName);
		else
			Assume.assumeTrue(true);
	}*/
}
