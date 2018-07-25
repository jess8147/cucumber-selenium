package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.DirectLinkFormScripts;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DirectLinkForm
{
	DirectLinkFormScripts scripts = new DirectLinkFormScripts();
	ClassicCommonAppMethods classicScripts = new ClassicCommonAppMethods();
	
	
	/*** Direct Link with Login ***/
	
	@When("^I create New Form with \"(.*?)\" action to ([^\"]*) Adoddle Form AccessUser and ([^\"]*) Classic Form AccessUser$")
	public void i_create_New_Form_with_action_to_pa_tctestorg_tctestorg_com_Adoddle_Form_AccessUser_and_auto_classic_atest_com_Classic_Form_AccessUser(String action, String adoddleUserID, String classicUserID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createNewForm(action, adoddleUserID, classicUserID);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should created successfully on Forms tab listing page$")
	public void form_should_created_successfully_on_Forms_tab_listing_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCreatedForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on created Form$")
	public void i_click_on_created_Form() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^New tab should opened And Form should viewed$")
	public void new_tab_should_opened_And_Form_should_viewed() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.switchToSecondWindow();
			scripts.verifyViewForm();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I get Direct Link of viewed Form$")
	public void i_get_Direct_Link_of_viewed_Form() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getDirectLinkOfViewedForm();
		else
			Assume.assumeTrue(true);
	}

	@When("^I logOut and re-login using ([^\"]*) Adoddle Form \"(.*?)\"$")
	public void i_logOut_and_re_login_using_pa_builder_auto_com_Adoddle_Form(String adoddleUserID, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.login(adoddleUserID, ResourceHandler.loadProperty("test.users.common.password"));
		} else
			Assume.assumeTrue(true);
	}

	@When("^I opened new tab I hit Direct Link on URL$")
	public void i_opened_new_tab_I_hit_Direct_Link_on_URL() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.openNewTabAndHitDirectLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^It should displayed blank page and Form not getting Viewed And getting Message like \"(.*?)\"$")
	public void it_should_displayed_blank_page_and_Form_not_getting_Viewed(String blankFormPageErrorMsg) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormNotGettingViewedForNoAccessAdoddleUser(blankFormPageErrorMsg);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should viewed and \"(.*?)\" action should completed for ([^\"]*) user And \"(.*?)\" Remarks should listed in History tab$")
	public void form_should_viewed_and_action_should_completed_for_user_And_Remarks_should_listed_in_History_tab(String action, String user, String historyRemarks) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormGettingViewedForAccessAdoddleUser(action, historyRemarks, user);
		else
			Assume.assumeTrue(true);
	}

	@When("^I logOut and re-login using ([^\"]*) Classic Form \"(.*?)\"$")
	public void i_logOut_and_re_login_using_auto_classic_atest_com_Classic_Form(String classicUserID, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			classicScripts.login(classicUserID, ResourceHandler.loadProperty("test.users.common.password"));
		}else
			Assume.assumeTrue(true);
	}
	
	@Then("^Form should viewed and \"(.*?)\" action should completed And Action Status \"(.*?)\" should listed in Audit Trail$")
	public void form_should_viewed_and_action_should_completed_And_Action_Status_should_listed_in_Audit_Trail(String action, String historyRemarks) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormGettingViewedForAccessClassicUser(historyRemarks);
		else
			Assume.assumeTrue(true);
	}
	
	
	/*** Direct Link Before Login ***/
	
	@When("^I logOut and hit Direct Link on URL$")
	public void i_logOut_and_hit_Direct_Link_on_URL() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.logoutAndHitDirectLink();
		else
			Assume.assumeTrue(true);
	}

	@Then("^login page should redirect and \"(.*?)\" validation message should displayed$")
	public void login_page_should_redirect_and_validation_message_should_displayed(String directLinkValidationMsg) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyLoginPageAndDirectLinkValidationMsg(directLinkValidationMsg);
		else
			Assume.assumeTrue(true);
	}

	@When("^I login using ([^\"]*) Adoddle Form \"(.*?)\"$")
	public void i_login_using_pa_builder_auto_com_Adoddle_Form(String adoddleUserID, String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.loginInAdoddleWithDirectLink(adoddleUserID, ResourceHandler.loadProperty("test.users.common.password"));
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I login using ([^\"]*) Classic Form \"(.*?)\"$")
	public void i_login_using_auto_classic_atest_com_Classic_Form(String classicUserID, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			classicScripts.login(classicUserID, ResourceHandler.loadProperty("test.users.common.password"));
		else
			Assume.assumeTrue(true);
	}

	@When("^I open new browser and hit Direct Link on URL$")
	public void i_open_new_browser_and_hit_Direct_Link_on_URL() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.setUpBrowserAndHitDirectLink();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create New Form with \"(.*?)\" action to ([^\"]*) Adoddle Form AccessUser$")
	public void i_create_New_Form_with_action_to_pa_tctestorg_tctestorg_com_Adoddle_Form_AccessUser(String action, String adoddleUserID) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createNewForm(action, adoddleUserID, null);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form should viewed in Existing session$")
	public void form_should_viewed_in_Existing_session() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.verifyViewForm();
			scripts.closeSecondWindow();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should displayed and Form not getting Viewed$")
	public void page_should_displayed_and_Form_not_getting_Viewed(String classicUnauthorisedAccessErrorPage) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormNotGettingViewedForNoAccessClassicUser(classicUnauthorisedAccessErrorPage);
		else
			Assume.assumeTrue(true);
	}
}
