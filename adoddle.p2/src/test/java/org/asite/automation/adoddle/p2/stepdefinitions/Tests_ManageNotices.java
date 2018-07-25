package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.ManageNoticesScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_ManageNotices {

	ManageNoticesScripts scripts = new ManageNoticesScripts();

	@Then("^\"(.*?)\" button should be displayed$")
	public void button_should_be_displayed(String createNewNotice)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreateNoticeButton(createNewNotice);
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter values in notice fields$")
	public void i_enter_values_in_notice_fields() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterValuesInNoticeFields();
		else
			Assume.assumeTrue(true);
	}

	@When("^I set notice start date and end date as active values$")
	public void i_set_notice_start_date_and_end_date_as_active_values()
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.enterValuesInDateFields();
		else
			Assume.assumeTrue(true);
	}

	@When("^I apply notice to current project$")
	public void i_apply_notice_to_current_project() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.setNoticeToCurrentDCProject();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on Add Notice Popup$")
	public void i_click_on_button_on_Add_Notice_Popup(String buttonText)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickSaveButton(buttonText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I sort notice in \"(.*?)\" order of \"(.*?)\"$")
	public void i_sort_notice_in_descending_order_of(String order,
			String createdOn) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.sortNoticeByCreatedOn(order);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice should be available on top of Listing$")
	public void notice_should_be_available_on_top_of_Listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticeIsOnTopListing();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice should appear on top of the page \"(.*?)\"$")
	public void notice_should_appear_on_top_of_the_page(String flag)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticeIsDisplayedOnPage(flag);
		else
			Assume.assumeTrue(true);

	}

	@When("^I click on Notice \"(.*?)\" link and read it$")
	public void i_click_on_Notice_and_read_it(String readMoreLink)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickReadMoreLinkOnNotice(readMoreLink);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice should have correct data available$")
	public void notice_should_have_correct_data_available() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticeDataOnReadMorePopup();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice popup should get closed$")
	public void notice_popup_should_get_closed() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticePopupIsClosed();
		else
			Assume.assumeTrue(true);
	}

	@When("^I dismiss the Notice by clicking \"(.*?)\" link$")
	public void i_dismiss_the_Notice_by_clicking_link(String dismissLink)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.dismissNotice(dismissLink);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice should get dismissed$")
	public void notice_should_get_dismissed() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticeIsDismissed();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" listing should be displayed$")
	public void manage_notices_listing_should_be_displayed(String header)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticesAreAvailable(header);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select any active notice and make it \"(.*?)\"$")
	public void i_select_any_notice_and_make_it_ongoing(String onGoing) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectActiveNoticeAndSetCurrent(onGoing);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Active Notice should popup on the page \"(.*?)\"$")
	public void active_notice_should_popup_on_the_page(String flag) {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyActiveNoticePopup(flag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select active notice and deactivate it$")
	public void i_select_notice_and_deactivate_it() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectActiveNoticeAndDeactivate();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice Status should be \"(.*?)\"$")
	public void notice_status_should_be(String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticeStatus(status);
		else
			Assume.assumeTrue(true);
	}

	@Then("^User should be able to edit notice \"(.*?)\"$")
	public void user_should_be_able_to_edit_notice(String flag)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.isNoticeEditable(flag);
		else
			Assume.assumeTrue(true);
	}

	@When("^I select inactive notice and activate it$")
	public void i_select_any_notice_and_activate_it() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.selectInActiveNoticeAndActivate();
		else
			Assume.assumeTrue(true);
	}

	@When("^I edit existing Notice Details$")
	public void i_edit_existing_notice_details() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editExistingNoticeDetails();
		else
			Assume.assumeTrue(true);
	}

	@Then("^All notice details should get updated on Listing$")
	public void notice_details_should_get_updated_on_listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticeIsEdited();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on Update Notice popup$")
	public void click_on_save_button_on_update_notice_popup(String button)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.clickOnSaveButton();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice Scheduled Status should be \"(.*?)\"$")
	public void notice_scheduled_status_should_be(String schedStatus)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyScheduleStatus(schedStatus);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set notice end time as past value$")
	public void i_set_notice_end_time_as_past_value() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.editNoticeSetTimeAsPast();
		else
			Assume.assumeTrue(true);
	}

	@When("^I create \"(.*?)\" notice with \"(.*?)\" priority and having \"(.*?)\" due time$")
	public void i_create_notice_with_priority_and_having_due_time(
			String noticeTitle, String priority, String dueTime)
			throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.createNoticeWithPriorityAndDueTime(noticeTitle, priority,
					dueTime);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notice should get created and available on listing$")
	public void notice_should_get_created_and_available_on_listing() {
		if (Tests_CommonStepDefinitions.runTest)
			System.out.println("covered in previous definition");
		else
			Assume.assumeTrue(true);
	}

	@Then("^All created Notices should be seen as per their priorities$")
	public void all_created_notices_should_be_seen_as_per_their_priorities() {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticePriorities();
		else
			Assume.assumeTrue(true);
	}

	@Given("^I deactivate global test notice project$")
	public void i_deactivat_global_test_notice_project() {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.deactivateNoticeProject();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Notices should not be displayed on notice listing$")
	public void notices_should_not_be_displayed_in_listing() {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.verifyNoticesRemovedFromListing();
		else
			Assume.assumeTrue(true);
	}
	
	
	@When("^I give project access to ([^\"]*)$")
	public void i_give_project_access_to(String user) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			scripts.setProjectAccessToUser(user);
		else
			Assume.assumeTrue(true);
	}
	
}
