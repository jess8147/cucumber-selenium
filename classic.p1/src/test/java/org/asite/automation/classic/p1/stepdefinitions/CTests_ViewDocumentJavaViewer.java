package org.asite.automation.classic.p1.stepdefinitions;

import org.asite.automation.classic.p1.scripts.ViewDocumentJavaViewerClassicScripts;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CTests_ViewDocumentJavaViewer {
	ViewDocumentJavaViewerClassicScripts classicScripts = new ViewDocumentJavaViewerClassicScripts();

	@When("^I click on any file for file viewing$")
	public void i_click_on_any_file_for_file_viewing() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.gotoFileDocument();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^Popup should be open with Java Viewer And Attribute of file should be available on LH Panel$")
	public void popup_should_be_open_with_Java_Viewer_And_Attribute_of_file_should_be_available_on_LH_Panel()
			throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.gotoFileOpenWindow();
			classicScripts.verifyFileAttributes();
		} else
			Assume.assumeTrue(true);
	}

	@Then("^file should be open/view in Java Viewer$")
	public void file_should_be_open_view_in_Java_Viewer() throws Throwable {
		if (CTests_CommonStepDefinitions.runTest) {
			classicScripts.verifyFileOpened();
			classicScripts.closeFileOpenedWindow();
		} else
			Assume.assumeTrue(true);
	}
}
