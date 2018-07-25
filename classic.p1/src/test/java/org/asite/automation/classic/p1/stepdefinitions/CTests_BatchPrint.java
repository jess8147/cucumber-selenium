package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.BatchPrintClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_BatchPrint {

	BatchPrintClassicScripts scripts = new BatchPrintClassicScripts();

	@Given("^I have selected multiple files on document listing$")
	public void i_have_selected_multiple_files_on_document_listing()
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.selectMultipleDocs();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Add to Basket icon$")
	public void i_click_on_Add_to_Basket_icon() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickOnAddBasketIcon();
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected documents should be added to basket with message \"(.*?)\"$")
	public void selected_documents_should_be_added_to_basket_with_message(
			String successMsg) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifySuccessMessage(successMsg);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on ok button of confirmation popup$")
	public void i_click_on_ok_button_of_confirmation_popup() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.acceptAlertMessage();
		else
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" Icon$")
	public void click_on_view_basket_icon(String viewBasketText)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.viewBasketDocuments(viewBasketText);
		else
			Assume.assumeTrue(true);
	}

	@When("^Page with \"(.*?)\" header should open$")
	public void page_with_header_should_open(String viewBasketTitle)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyPageTitle(viewBasketTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" value from \"(.*?)\" dropdown$")
	public void i_select_value_from_dropdown(String printAction,
			String actionDropdown) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.selectPrintAction(printAction);
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should get opened$")
	public void print_doc_page_should_openp(String printDocTitle)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyPrintDocumentPage(printDocTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select Incl\\. Markups AND Incl\\. Changemarks AND Fit Inside Banners$")
	public void i_select_Incl_Markups_AND_Incl_Changemarks_AND_Fit_Inside_Banners()
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.selectBatchPrintMarkUps();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on right side$")
	public void i_click_on_print_button_on_right_side(String printButtonText)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.clickPrintButton(printButtonText);
		else
			Assume.assumeTrue(true);
	}

	@Then("^New Window should open with title as \"(.*?)\" AND I have selected default printer as \"(.*?)\"$")
	public void new_Window_should_open_with_title_as_AND_I_have_selected_default_printer_as(
			String arg1, String arg2) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.verifyNewWindowOpens();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have redirected to new window with title as \"(.*?)\" AND I have click on \"(.*?)\" button on top Panel$")
	public void i_have_redirected_to_new_window_with_title_as_AND_I_have_click_on_button_on_top_Panel(
			String arg1, String arg2) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Popup should open AND I have destination directory as \"(.*?)\"$")
	public void popup_should_open_AND_I_have_destination_directory_as(
			String arg1, String arg2) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.logInfo();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have enter file name as \"(.*?)\" AND I click on \"(.*?)\"$")
	public void i_have_enter_file_name_as_AND_I_click_on(String fileName,
			String arg2) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.createBatchFileInLocal(fileName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^file \"(.*?)\" should saved successfully in Local$")
	public void file_should_saved_successfully_in_Local(String arg1)
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.validateFileInLocal();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have validated \"(.*?)\" file size with local file in ([^\"]*) successfully$")
	public void i_have_validated_file_size_with_local_file_in_successfully(
			String existingFileName, String projectTitle) throws Throwable {
		if (CTests_CommonStepDefinitions.runTest)
			scripts.compareFileSizeInLocal(existingFileName, projectTitle);
		else
			Assume.assumeTrue(true);
	}

}