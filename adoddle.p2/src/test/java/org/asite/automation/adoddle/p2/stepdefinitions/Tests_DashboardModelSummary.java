package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DashboardModelSummary;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Tests_DashboardModelSummary {

	DashboardModelSummary scripts = new DashboardModelSummary();

	@Given("^I get total count of document available in Model Summary via disciplines wise$")
	public void i_get_total_count_of_document_available_in_Model_Summary_via_disciplines_wise() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.getCurrentModelCount();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" type Model total count should increased And remain types Model count should not incresed$")
	public void type_Model_total_count_should_increased_And_remain_types_Model_count_should_not_incresed(String modelType) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validateUpdatedModelSummaryWidget(modelType);
		else
			Assume.assumeTrue(true);
	}
}