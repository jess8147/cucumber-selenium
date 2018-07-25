package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.UploadViewPrintMultipleDocuments;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;

public class Tests_UploadViewPrintMultipleDocuments {

	private String browserIE = "IE", browserCrome = "CHROME";
	UploadViewPrintMultipleDocuments scripts = new UploadViewPrintMultipleDocuments();

	@Given("^I have published View AND Print Multiple Documents on workspace ([^\"]*) AND ([^\"]*) successfully$")
	public void i_have_published_View_AND_Print_Multiple_Documents_on_workspace_AutomationTestProject_successfully(
			String workspace, String dc) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.setUpBrowser(browserCrome, dc);
			scripts.createAndfocusWorkspaceDirectory(workspace);
			scripts.publishMultipleDocuments();
			scripts.viewMultipleBatchDocuments();
			scripts.setUpBrowser(browserIE, dc);
			scripts.PrintMultipleBatchDocuments();
			scripts.compareFileSizeInLocal();
			scripts.setUpBrowser(browserCrome, dc);
			scripts.cleanUpOperations(workspace);

		}

		else
			Assume.assumeTrue(true);
	}

}