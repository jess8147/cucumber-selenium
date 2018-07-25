package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.AdoddleSharedReportACLCheck;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_AdoddleSharedReportACLCheck extends AdoddleCommonAppMethods {

	AdoddleSharedReportACLCheck scripts = new AdoddleSharedReportACLCheck();

	@When("^I click link Edit AND Schedule in LH Panel$")
	public void i_click_link_Edit_AND_Schedule_in_LH_Panel() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickLinkEditSchedule();
		else
			Assume.assumeTrue(true);
	}

	@When("^I have search Report with name as \"(.*?)\"$")
	public void i_have_search_Report_with_name_as(String report) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.searchAndValidateReport(report);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Report \"(.*?)\" should not available in Report listing successfully$")
	public void report_should_not_available_in_Report_listing_successfully(String report) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateReportIsNotAvailable();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Report \"(.*?)\" should available in Report listing successfully$")
	public void report_should_available_in_Report_listing_successfully(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateReportCountInListing();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I validated set ACL \"(.*?)\" for user \"(.*?)\" successfully$")
	public void i_validated_set_ACL_for_user_successfully(String aclType, String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.reportACLValidation(aclType, user);
		else
			Assume.assumeTrue(true);
	}

}