package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.SharedLinkScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_SharedLink {


	SharedLinkScripts scripts = new SharedLinkScripts();
	
	@When("^I right click on \"(.*?)\" document$")
	public void i_right_click_on_any_document(String document) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickDocument(document);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I drag mouse on \"(.*?)\"$")
	public void i_drag_mouse_on(String optionText) throws Throwable {
	   if(Tests_CommonStepDefinitions.runTest)
		   scripts.dragMouseOnShare();
	   else
		   Assume.assumeTrue(true);
	}

	@When("^Click on \"(.*?)\"$")
	public void click_on_Share_Adoddle_Link(String optionText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickOnShareAdoddleLink(optionText);
		else
			Assume.assumeTrue(true);
	}

	@When("^I set visibility to \"(.*?)\" and Expiry to \"(.*?)\"$")
	public void i_set_visibility_and_expiry(String visibility, String expiry) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.setVisibilityAndExpiry(visibility, expiry);
	    else
	    	Assume.assumeTrue(true);
	}
	
	@When("^I enter \"(.*?)\" into \"(.*?)\" textbox$")
	public void i_enter_into_textbox(String userEmail, String textBox) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.enterEmailAddress(userEmail);
	    else
	    	Assume.assumeTrue(true);
	}

	@Then("^Email should send to given \"(.*?)\" with Selected doucments link$")
	public void email_should_send_to_given_with_Selected_doucments_link(String emailID) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyReceivedEmail(emailID);
	    else
	    	Assume.assumeTrue(true);
	}
	
	
	@When("^click on \"(.*?)\" button$")
	public void click_on_button(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.clickOnSendButton();
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on link from received email$")
	public void i_click_on_link_from_received_email() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.clickOnReceivedEmailLink();
		else
			Assume.assumeTrue(true);
	}

	
	@Then("^Document \"(.*?)\" should open in Adoddle view and viewing should require credentials$")
	public void document_should_opened_in_Adoddle_view(String document) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyDocumentOpened(document);
		else
			Assume.assumeTrue(true);
	}
}
