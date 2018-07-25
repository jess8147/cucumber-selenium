package org.asite.automation.classic.p1.stepdefinitions;
import org.asite.automation.classic.p1.scripts.FormMailBoxClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_FormMailBox {

	FormMailBoxClassicScripts scripts = new FormMailBoxClassicScripts();
	
	@Given("^I have sent mail to ([^\"]*) With Subject \"(.*?)\"\\+SYSDATE for Form Mailbox$")
	public void i_have_sent_mail_to_With_Subject_SYSDATE(String sendToUser, String subject) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.sendMailToMailBoxUser(sendToUser, subject);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^entered test mail template AND have attachment excel sheet$")
	public void entered_test_mail_template_AND_have_attachment() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.attachFormExcelToEmail();
		else
			Assume.assumeTrue(true);
	}


	@When("^I click on \"(.*?)\" Formtype$")
	public void i_click_on_folder(String folderName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnFormLink(folderName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Form with \"(.*?)\"\\+SYSDATE value in Form title should be available in Form listing$")
	public void form_with_SYSDATE_value_in_Form_title_should_be_available_in_Form_listing(String formTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsCreated();
		else
			Assume.assumeTrue(true);
		
	}
		
	@Then("^User should be assigned actions as per mailbox configuration$")
	public void user_should_be_assigned_actions_per_mailbox_configuration() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyActionAssignedToUser();
		else
			Assume.assumeTrue(true);
	}
}
