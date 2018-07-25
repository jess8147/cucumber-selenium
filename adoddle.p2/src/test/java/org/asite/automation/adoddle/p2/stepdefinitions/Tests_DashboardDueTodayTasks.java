package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.DashboardDueTodayTasksScripts;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DashboardDueTodayTasks {

	DashboardDueTodayTasksScripts scripts = new DashboardDueTodayTasksScripts();

	@Then("^\"(.*?)\" widget should be displayed$")
	public void widget_should_be_displayed(String widgetTitle) {
		scripts.verifyWidgetTitle(widgetTitle);
	}
	
	@When("I distribute multiple actions to ([^\"]*)$")
	public void and_i_distribute_multiple_actions_to(String user) {
		scripts.distributeActionsTo(user);
	}
	
	@Then("^\"(.*?)\" widget should have multiple actions$")
	public void widget_should_have_multiple_actions(String widgetTitle) {
		scripts.verifyWidgetActions(widgetTitle);
	}
}
