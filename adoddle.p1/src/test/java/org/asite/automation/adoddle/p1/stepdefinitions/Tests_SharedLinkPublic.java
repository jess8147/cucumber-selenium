package org.asite.automation.adoddle.p1.stepdefinitions;

import org.asite.automation.adoddle.p1.scripts.SharedLinkPublicScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;

public class Tests_SharedLinkPublic {


	SharedLinkPublicScripts scripts = new SharedLinkPublicScripts();
	
	@Then("^Document \"(.*?)\" should open in Adoddle view and viewing should not require credentials$")
	public void document_should_opened_in_Adoddle_view(String document) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	scripts.verifyDocumentHasPublicAccess(document);
		else
			Assume.assumeTrue(true);
	}
}
