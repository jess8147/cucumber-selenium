package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.AMesssageLoadTestScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;
import cucumber.api.java.en.When;

public class Tests_AMessageLoadTest
{
	AMesssageLoadTestScripts scripts = new AMesssageLoadTestScripts();
	
	@When("^I send test messages to channel ([^\"]*) every ([^\"]*) seconds for ([^\"]*) duration$")
	public void i_send_test_messages_to_channel_every_seconds(String channelTitle, String time, String duration) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sendTestMessageToChanel(channelTitle, time, duration);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I send test messages to user ([^\"]*) every ([^\"]*) seconds for ([^\"]*) duration$")
	public void i_send_test_messages_to_user_every_seconds(String user, String time, String duration) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.sendTestMessageToUser(user, time, duration);
		else
			Assume.assumeTrue(true);
	}

}
