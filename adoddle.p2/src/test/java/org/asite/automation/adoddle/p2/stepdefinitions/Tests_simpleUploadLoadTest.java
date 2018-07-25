package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.SimpleUploadLoadTest;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.When;


public class Tests_simpleUploadLoadTest
{

	SimpleUploadLoadTest scripts = new SimpleUploadLoadTest();
	
	@When("^I publish \"(.*?)\" files using simple upload for ([^\"]*) times$")
	public void i_send_test_messages_to_channel_every_seconds(String documentCount, String counter) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.simpleUploadNDocuments(documentCount, counter);
		else
			Assume.assumeTrue(true);
	}
}
