package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.DashboardLHActionScripts;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DashboardLHAction extends AdoddleCommonAppMethods {

	DashboardLHActionScripts scripts = new DashboardLHActionScripts();

	@Given("^I am on Dashboard$")
	public void i_am_on_Dashboard() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyDashboardTab();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have atleast one \"(.*?)\" on Dashboard$")
	public void i_have_atleast_one_on_Dashboard(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionCount(actionType);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have atleast one \"(.*?)\" on Dashboard for Form$")
	public void i_have_atleast_one_on_Dashboard_for_Form(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionCountForForm(actionType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I drag mouse on \"(.*?)\" count on Dashboard$")
	public void i_drag_mouse_on_count(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.dragMouseOnActions();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Total number of \"(.*?)\" should display on mouse over on Dashboard$")
	public void total_number_of_action_should_display_on_mouse_over_on_Dashboard(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyTotalActionCount(actionType);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click \"(.*?)\" on Dashboard$")
	public void i_click_on_on_Dashboard(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnActionLink(actionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I should re-directed to \"([^\"]*)\" Tab$")
	public void i_should_re_directed_to_Tab(String activeTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.navigateTabByText(activeTab);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I validated multiple pending tasks in \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_validated_multiple_pending_tasks_in_for(String task, String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			if (task.contains("Files"))
				scripts.verifyFilesActions(2, actionType);
			else
				scripts.verifyFormsActions(5, actionType);

		} else
			Assume.assumeTrue(true);
	}

	@When("^I perform multiple pending tasks in \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_perform_multiple_pending_tasks_in_for(String task, String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performPendingTasks(task, actionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Total number of \"([^\"]*)\" count should be decrease by \"([^\"]*)\"$")
	public void total_number_of_count_should_be_decrease_by(String actionType, String count) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			int expectedCount = Integer.parseInt(count);
			scripts.verifyTotalActionCountDecreasedOnDashboard(actionType, expectedCount);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Total count on \"([^\"]*)\" should be decrease by \"([^\"]*)\" on \"([^\"]*)\" page$")
	public void total_count_on_should_be_decrease_by_on_page(String module, String count, String actionType)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			int expectedCount = Integer.parseInt(count);
			if (module.contains("Files"))
				scripts.verifyActionCountDecreasedOnFilesTab(actionType, expectedCount);
			else
				scripts.verifyActionCountDecreasedOnApp(actionType, expectedCount);
		} else
			Assume.assumeTrue(true);
	}

	@When("^I perform on any action from Discussions listing in \"(.*?)\"$")
	public void i_perform_on_any_action_from_Discussions_listing_in(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.performActionFromDiscussionListing(actionType);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Total count on Discussions should be decrease by one on \"(.*?)\" page$")
	public void total_count_on_Discussions_should_be_decrease_by_one_on_page(String actionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyActionCountDecreasedOnDiscussion(actionType, 1);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have created new group \"([^\"]*)\" with two members as \"([^\"]*)\" AND \"([^\"]*)\" in ([^\"]*) successfully$")
	public void i_have_created_new_group_with_two_members_as_AND_in_AutomationTestProject_successfully(String group,
			String member1, String member2, String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createGroupAndMember(group, member1, member2, project);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have assigned multiple \"([^\"]*)\" tasks to group members \"([^\"]*)\" AND \"([^\"]*)\" successfully$")
	public void i_have_assigned_multiple_tasks_to_group_members_AND_successfully(String task, String asignee1,
			String asignee2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createAdhocTasks(task);
			scripts.assignAdhocTasks(asignee1, asignee2);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^I validated multiple pending \"([^\"]*)\" of group \"([^\"]*)\" successfully$")
	public void i_validated_multiple_pending_of_group_successfully(String arg1, String arg2) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.validatePendingAdhocTasks();
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated AND delegated tasks to group member \"([^\"]*)\"$")
	public void i_have_updated_AND_delegated_tasks_to_group_member(String member) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateAndDelegateMultipleTasks(member);
		else
			Assume.assumeTrue(true);
	}

	@When("^I logged in as ([^\"]*)$")
	public void i_logged_in_as_ActionsTest_User(String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.loggedInAsUser(user);
		else
			Assume.assumeTrue(true);
	}

	@Then("^I have updated multiple pending adhoc tasks successfully by user \"([^\"]*)\"$")
	public void i_have_updated_multiple_pending_adhoc_tasks_successfully_by_user(String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.updateMultipleTasksStatus(user);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have updated all pending tasks AND mark Group \"([^\"]*)\" inactive successfully$")
	public void i_have_updated_all_pending_tasks_AND_mark_Group_inactive_successfully(String group) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			try {
				scripts.applyTasksFilter();
				scripts.updateMultipleTasksStatus(group);
				scripts.deactivateTasksGroup();
			} catch (Throwable exp) {

				log.info("Failure while performing clean up opertation");
			}
		} else
			Assume.assumeTrue(true);
	}
}