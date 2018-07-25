package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.CatalogueViewScripts;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CatalogueView {

	CatalogueViewScripts viewScripts = new CatalogueViewScripts();

	@Given("^I logout and re-login with procurement user$")
	public void i_logout_and_relogin_with_procurement_user()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.logOut();
			viewScripts.login(
					ResourceHandler.loadProperty("catalogue.view.username")
							.trim(),
					ResourceHandler.loadProperty("catalogue.view.password")
							.trim());
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I clicked on \"(.*?)\" in LH panel$")
	public void i_clicked_on_in_LH_panel(String lhPanelMenu) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOnCatalogues(lhPanelMenu);
		} else
			Assume.assumeTrue(true);
	}

	@Given("^I select Catalogue contract value \"(.*?)\" Catalogues from catalouge listing$")
	public void i_select_Catalogue_contract_value_Catalogues_from_catalouge_listing(
			String contractValue) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.catalogueContractCheckboxSelect(contractValue);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button from catalouge listing$")
	public void i_click_on_button_from_catalouge_listing(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOnPublishCatalogue();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I have selected valid catalogue and valid zip image file from local$")
	public void i_have_selected_valid_catalogue_and_valid_zip_image_file_from_local()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.selectCatalogueAndImageIntoLocal();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on Submit button$")
	public void i_click_on_Submit_button() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOnSubmit();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Current popup window should be closed And Catalouge should be published successfully without any error$")
	public void current_popup_window_should_be_closed_And_Catalouge_should_be_published_successfully_without_any_error()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifyPublishedCatalogue();
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on \"(.*?)\" popup$")
	public void i_click_on_button_on_popup(String arg1, String arg2)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOnOk();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Catalogue listing should be refreshed And Latest published catalogue should be listed on top$")
	public void catalogue_listing_should_be_refreshed_And_Latest_published_catalogue_should_be_listed_on_top()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifyLatestCatalogueOnTop();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Status of the catalogue should be changed to \"(.*?)\"$")
	public void status_of_the_catalogue_should_be_changed_to(String status)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifyCatalogueStatus(status);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" Status of latest published catalogue$")
	public void i_click_on_Status_of_latest_published_catalogue(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOnCatalogueStatus();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should be opened$")
	public void popup_should_be_opened(String popup) {
		if (Tests_CommonStepDefinitions.runTest)
			viewScripts.verifyViewCataloguePopup(popup);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" icon on View catalogue popup$")
	public void i_click_on_icon_on_View_catalogue_popup(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOnChangeCatalogueStatus();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should be open$")
	public void popup_should_be_open(String popup) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifyChangeCatalogueStatusPopup(popup);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I select \"(.*?)\" in Catalogue Status dropdown AND Give \"(.*?)\" in Comments textarea \\(Optional\\)$")
	public void i_select_in_Catalogue_Status_dropdown_AND_Give_in_Comments_textarea_Optional(
			String catalogueStatus, String comment) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.CatalogueStatusDropdownSelect(catalogueStatus, comment);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" of recently published catalogue$")
	public void i_click_on_of_recently_published_catalogue(String arg1)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOncatalogueId();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Catalogue items should be listed on right hand panel$")
	public void catalogue_items_should_be_listed_on_right_hand_panel()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifyCatalogueItems();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Catalogue classification tree should be displayed on left hand side$")
	public void catalogue_classification_tree_should_be_displayed_on_left_hand_side()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifyCatalogueClassificationTree();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^different fields should be available in search panel like \"(.*?)\" AND \"(.*?)\" AND \"(.*?)\"$")
	public void different_fields_should_be_available_in_search_panel_like_AND_AND(String partNum, String description, String supplierName) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifySearchPanelFields(partNum, description, supplierName);
		} else
			Assume.assumeTrue(true);
	}

	@When("^Click on \"(.*?)\" button of any Product$")
	public void click_on_button_of_any_Product(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.clickOnViewDetails();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" popup should open into View Details$")
	public void popup_should_open_into_View_Details(String popup) {
		if (Tests_CommonStepDefinitions.runTest)
			viewScripts.verifyProductDetailsPopup(popup);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Details of product should be available on Product Details popup$")
	public void details_of_product_should_be_available_on_Product_Details_popup()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			viewScripts.verifyProductDetails();
		} else
			Assume.assumeTrue(true);
	}
}
