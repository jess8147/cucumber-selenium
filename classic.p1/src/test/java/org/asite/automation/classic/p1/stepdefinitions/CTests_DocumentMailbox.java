package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.DocumentMailboxClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_DocumentMailbox extends DocumentMailboxClassicScripts{

	DocumentMailboxClassicScripts scripts = new DocumentMailboxClassicScripts();
	
	@Given("^I have sent mail to ([^\"]*) With Subject \"(.*?)\"\\+SYSDATE$")
	public void i_have_sent_mail_to_With_Subject_SYSDATE(String sendToUser, String subject) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.sendMailToMailBoxUser(sendToUser, subject);
		else
			Assume.assumeTrue(true);
	}

	@Given("^entered test mail template AND have attachment$")
	public void entered_test_mail_template_AND_have_attachment() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.attachDocToEmail();
		else
			Assume.assumeTrue(true);
	}
 
	@When("^I click on \"(.*?)\" folder$")
	public void i_click_on_folder(String foldernName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnMailboxFolder(foldernName);
		else
			Assume.assumeTrue(true);
	}

	@Then("^Document with \"(.*?)\"\\+SYSDATE value in Doc Ref should be available in document listing$")
	public void document_with_SYSDATE_value_in_Doc_Ref_should_be_available_in_document_listing(String docRef) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.verifyDocumentIsAvailable(docRef);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Type icon of document with \"(.*?)\"\\+SYSDATE value in Doc Ref$")
	public void i_click_on_Type_icon_of_document_with_SYSDATE_value_in_Doc_Ref(String docRef) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.clickOnClipIconOfDocument(docRef);
		else
			Assume.assumeTrue(true);
	}

	@Then("^attached document in sent mail should download$")
	public void attached_document_in_sent_mail_should_download() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			scripts.downloadAndVerifyDocument();
		else
			Assume.assumeTrue(true);
	}

}
