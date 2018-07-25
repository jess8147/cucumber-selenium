package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.EmbededEmailResponseScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_EmbededEmailResponse {

	EmbededEmailResponseScripts scripts  = new EmbededEmailResponseScripts();
	
	@When("^I click \"(.*?)\" button for \"(.*?)\"$")
	public void i_click_button_for(String buttonText, String form) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickCreateFormButton(form);
		else
			Assume.assumeTrue(true);
	}

	@When("^I enter form title as \"(.*?)\"$")
	public void i_enter_form_title_as(String formTitle) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.enterFormTitle(formTitle);
		else
			Assume.assumeTrue(true);
	}

	@When("^I attach multiple files to the form$")
	public void i_attach_multiple_files_to_the_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.attachMultipleFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I associate multiple documents to the form$")
	public void i_associate_multiple_documents_to_the_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.associateMultipleFiles();
		else
			Assume.assumeTrue(true);
	}

	@When("^I distribute form to ([^\"]*) and ([^\"]*) users$")
	public void i_distribute_form_to_multiple_users(String primaryUser, String paperUser) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.distributeForm(primaryUser, paperUser);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button to create form$")
	public void i_click_on_button_to_create_form(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickSendButtonToCreateForm();
		else
			Assume.assumeTrue(true);
	}

	@Then("^Embeded Email Response form should get created$")
	public void embeded_email_response_form_should_get_created() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFormIsAvailableOnListing();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I login to ([^\"]*) email account with password ([^\"]*)$")
	public void i_login_to_distributed_users_email_account(String email, String pwd) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) {
			scripts.logOut();
			scripts.signInToOutLook365WebMail(email, pwd);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Email should be received to distributed users with embeded form$")
	public void email_should_be_received_to_distributed_users_with_embeded_form() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) { 
			scripts.verifyEmbededFormInEmail();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Form documents should get downloaded$")
	public void paper_user_can_download_attachment_without_login() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) { 
			scripts.verifyFormAttachmentsDownload();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I provide response to email for \"(.*?)\" user email$")
	public void i_provide_response_to_email_for_online_user_email(String userType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) 
			scripts.replyToOnlineUserEmail(userType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Email response should get created in the form$")
	public void email_response_should_get_created_in_the_form(String userType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) 
			scripts.verifyResponseIsCreated(userType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" link for \"(.*?)\" user email$")
	public void i_click_on_link_for_user_email(String linkText, String userType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) 
			scripts.clickRespondLink(linkText, userType);
		else
			Assume.assumeTrue(true);
	}

	@When("^Online user logs with Login credentials$")
	public void online_user_logs_with_Login_credentials() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) 
			scripts.verifyLoginPageIsNavigated();
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should be displayed and \"(.*?)\" user should be able to respond$")
	public void page_should_be_displayed(String pageTitle, String userType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) 
			scripts.verifyPageAndCreateResponse(userType);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Responded text should be available in the form for \"(.*?)\" user$")
	public void responded_text_should_be_available_in_the_form(String userType) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest) 
			scripts.verifyResponseIsCreated(userType);
		else
			Assume.assumeTrue(true);
	}
}
