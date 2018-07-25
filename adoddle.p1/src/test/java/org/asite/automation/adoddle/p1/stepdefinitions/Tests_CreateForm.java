package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CreateFormScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateForm {

	CreateFormScripts	scripts	= new CreateFormScripts();

	@Given("^I am on Project Forms tab$")
	public void i_am_on_Project_Forms_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.navigateProjectFormsTab();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^New \"(.*?)\" popup should open$")
	public void popup_should_open(String createFormText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCreateFormPopup(createFormText);
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have opened \"(.*?)\" popup$")
	public void i_have_opened_popup(String createFormText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyCreateFormPopupIsOpen();
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have selected Form Users into \"(.*?)\"$")
	public void i_have_selected_Users_into(String toFieldText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormUsersSelected(toFieldText);
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have attached atleast one document for form$")
	public void i_have_attached_atleast_one_document() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.attachFilesToForm();
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have associated atleast one document for form$")
	public void i_have_associated_atleast_one_document() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.associateSingleDocument(3);
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I Click on \"(.*?)\" button on create form window$")
	public void i_click_on_save_button_on_create_form_window(String saveButtonText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.clickSaveButton();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should be successfully created$")
	public void form_should_be_successfully_created() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormSuccessMessage();
		}
		else
			Assume.assumeTrue(true);

	}

	@Then("^Form should be available in \"(.*?)\" listing$")
	public void form_shold_be_available_on_form_listing(String formText) {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyFormIsCreated();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Attached documents should be uploaded And available in form$")
	public void attached_documents_should_be_uploaded_And_available_in_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAttachedDocumentOfForm();
		}
		else
			Assume.assumeTrue(true);

	}

	@Then("^Form Association should be successfully done with selected documents$")
	public void form_association_should_be_successfully_done() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAssociatedDocumentOfForm();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form Associations should have reference of this form$")
	public void form_associations_should_have_reference_of_this_form() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyAssociationReferences();
		}
		else
			Assume.assumeTrue(true);
	}

}
