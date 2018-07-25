package org.asite.automation.adoddle.p1.stepdefinitions;
import org.asite.automation.adoddle.p1.scripts.DocumentMailboxScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_DocumentMailbox {

	DocumentMailboxScripts scripts = new DocumentMailboxScripts();
	
	@Given("^I have sent mail to ([^\"]*) With Subject \"(.*?)\"\\+SYSDATE$")
	public void i_have_sent_mail_to_With_Subject_SYSDATE(String sendToUser, String subject) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sendMailToMailBoxUser(sendToUser, subject);
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on Project$")
	public void i_have_focus_on_Project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnProject();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" folder$")
	public void i_click_on_folder(String folderName) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnMailBoxFolder(folderName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Document with \"(.*?)\"\\+SYSDATE value in Doc Ref should be available in document listing$")
	public void document_with_SYSDATE_value_in_Doc_Ref_should_be_available_in_document_listing(String docRef) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentIsAvailable(docRef);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on clip icon of document with \"(.*?)\"\\+SYSDATE value in Doc Ref$")
	public void i_click_on_clip_icon_of_document_with_SYSDATE_value_in_Doc_Ref(String docRef) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnClipIconOfDocument();
		else
			Assume.assumeTrue(true);
	}

	@Then("^attached document in sent mail should download$")
	public void attached_document_in_sent_mail_should_download() throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.downloadAndVerifyDocument();
	    else
	    	Assume.assumeTrue(true);
	}
}
