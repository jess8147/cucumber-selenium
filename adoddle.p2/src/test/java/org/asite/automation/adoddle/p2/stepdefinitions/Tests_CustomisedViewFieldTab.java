package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CustomisedViewFieldTabScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CustomisedViewFieldTab {
	
	CustomisedViewFieldTabScripts scripts = new CustomisedViewFieldTabScripts();

	@Then("^I should redirect to \"(.*?)\" Tab$")
	public void i_should_redirecting_on_Tab(String linkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyAsiteMenuList(linkTab);
		else
			Assume.assumeTrue(true);
	}

	@When("^I \"(.*?)\" any two Coloums of \"(.*?)\" Tab from Customize \"(.*?)\"$")
	public void i_any_two_Coloums_of_Tab_from_Customize(String arg1, String arg2, String arg3) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest){
			scripts.getRemovableColumnName();
			scripts.removeAttributesFromSelectedFields();
		}
		else
	    	Assume.assumeTrue(true);
	}

	@Then("^selected both Coloums should \"(.*?)\" on \"(.*?)\" Tab listing page$")
	public void selected_both_Coloums_should_on_Tab_listing_page(String arg1, String arg2) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCustomizedAttributes();
		else
			Assume.assumeTrue(true);
	}

	@Then("^selected both Coloums should \"(.*?)\" on \"(.*?)\" Tab listing page on last Position$")
	public void selected_both_Coloums_should_on_Tab_listing_page_on_last_Position(String arg1, String arg2) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyAttributesAtLastPosition();
		else
			Assume.assumeTrue(true);
	}

	@When("^I moved Selected both \"(.*?)\" Tab Coloums of Customize \"(.*?)\" into Top Position$")
	public void i_moved_Selected_both_Tab_Coloums_of_Customize_into_Top_Position(String arg1, String arg2) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.movedColoumsUsingCustomizedView();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Selected both Coloums should displayed on \"(.*?)\" Tab listing page on First Position$")
	public void selected_both_Coloums_should_displayed_on_Tab_listing_page_on_First_Position(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I get Moved coloum width of \"(.*?)\" Tab And I customize its width$")
	public void i_get_Moved_coloum_width_of_Tab_And_I_customize_its_width(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Moved cutomized column width change with its previous width on \"(.*?)\" Tab$")
	public void moved_cutomized_column_width_change_with_its_previous_width_on_Tab(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I get Moved cutomized column new width on \"(.*?)\" Tab And I again customized its width with previous width$")
	public void i_get_Moved_cutomized_column_new_width_on_Tab_And_I_again_customized_its_width_with_previous_width(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^newly moved cutomized column width of \"(.*?)\" Tab should changed with its original width$")
	public void newly_moved_cutomized_column_width_of_Tab_should_changed_with_its_original_width(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
