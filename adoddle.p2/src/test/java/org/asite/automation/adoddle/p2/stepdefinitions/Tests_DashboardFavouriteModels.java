package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DashboardFavouriteModels;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DashboardFavouriteModels extends AdoddleCommonAppMethods {

	DashboardFavouriteModels scripts = new DashboardFavouriteModels();

	@Given("^I have validated favourite Models widget is Blank successfully$")
	public void i_have_validated_favourite_Models_widget_is_Blank_successfully() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateDasboardModelWidget();
		else
			Assume.assumeTrue(true);
	}

	@When("^I get total \"(.*?)\" count available in Model Listing$")
	public void i_get_total_count_available_in_Model_Listing(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.beforeFavouriteModelsCount();
		else
			Assume.assumeTrue(true);
	}

	@When("^I search first available model AND I mark as Favourite$")
	public void i_search_first_available_model_AND_I_mark_as_Favourite() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchAndMarkAsFavourite();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Marked Favourite Model should displayed in Favorite Models tabs$")
	public void marked_Favourite_Model_should_displayed_in_Favorite_Models_tabs() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyModelOnFavouriteModelsTab();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" count should increased by one$")
	public void count_should_increased_by_one(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAddAsFavouriteModelAndCountIncreased();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Navigate to Dasboard AND validated Marked \"(.*?)\" model should available in Favourite model widget$")
	public void i_Navigate_to_Dasboard_AND_validated_Marked_model_should_available_in_Favourite_model_widget(
			String validationType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateFavouriteModelWidgetOnDashBoard(validationType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Context click AND unmarked Favourite model as unfavourite$")
	public void i_Context_click_AND_unmarked_Favourite_model_as_unfavourite() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.markModelAsUnfavourite();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" count should decreased by one$")
	public void count_should_decreased_by_one(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyFavouriteModelsTabCountDecreased();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I Navigate to Dasboard AND validated Marked \"(.*?)\" model should not available in Favourite model widget$")
	public void i_Navigate_to_Dasboard_AND_validated_Marked_model_should_not_available_in_Favourite_model_widget(
			String validationType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateFavouriteModelWidgetOnDashBoard(validationType);
		else
			Assume.assumeTrue(true);
	}

}