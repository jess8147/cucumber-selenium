package org.asite.automation.adoddle.p2.stepdefinitions;
import org.asite.automation.adoddle.p2.scripts.LinkDocumentOptions;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_LinkDocumentOptions extends AdoddleCommonAppMethods {
	LinkDocumentOptions scripts = new LinkDocumentOptions();

	@Given("^I create testdata folder for \"(.*?)\" link$")
	public void i_create_testdata_folder_for_link(String linkType) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.createStaticLinkTestDataFolder(linkType);
	    else
	    	Assume.assumeTrue(true);
	}

	@Given("^I create conditional testdata folder for \"(.*?)\" linking document$")
	public void i_create_conditional_testdata_folder_for_link(String pType) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.createConditionalLinkTestDataFolder(pType);
	    else
	    	Assume.assumeTrue(true);
	}
	
	@Given("^I create testdata folder in cloned test project$")
	public void i_create_testdata_folder_for_in_global_test_project() throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.createStaticLinkTestDataFolder(clonedProject);
	    else
	    	Assume.assumeTrue(true);
	}

	@Given("^I upload multiple documents for \"(.*?)\" link operation$")
	public void i_upload_multiple_documents_for_link_operarion(String prefix) throws Throwable {
	    if(Tests_CommonStepDefinitions.runTest)
	    	scripts.uploadMultipleDocuments(prefix);
	    else
	    	Assume.assumeTrue(true);
	}
	
	@Given("^I have \"(.*?)\" document in document listing to link$")
	public void i_have_link_document_in_Files_listing(String docName) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyFilesCountOnFilesListing(docName);
		else 
			Assume.assumeTrue(true);
	}

	@When("^I select any document to link$")
	public void i_select_any_document() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectLinkDocument();
		else 
			Assume.assumeTrue(true);
	}

	@When("^click on \"(.*?)\" from more options$")
	public void click_on_from_more_options(String arg1) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectLinkOptionFromMoreOption();
		else 
			Assume.assumeTrue(true);
	}

	@Given("^I select cloned test project on popup$")
	public void i_select_global_test_project_on_popup() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickClonedTestProjectOnPopup();
		else 
			Assume.assumeTrue(true);
	}
	
	@Given("^I click on cloned test workspace$")
	public void i_click_on_cloned_test_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.getClonedTestProjectClick();
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I select link destination \"(.*?)\" folder \"(.*?)\" project And click on \"(.*?)\" button$")
	public void i_select_destination_Folder_And_click_on_button(String linkType, String projectType, String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectDestinationFolderAndClickSubmitButton(linkType, projectType, buttonText);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I select User in \"(.*?)\" field And select \"(.*?)\" type And Click on \"(.*?)\" button$")
	public void i_select_User_in_field_And_Click_on_button(String arg1, String linkType, String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectUserAndClickButton(linkType);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I select ([^\"]*) and ([^\"]*) Users in \"(.*?)\" field And select \"(.*?)\" type And Click on \"(.*?)\" button$")
	public void i_select_User_in_field_And_Click_on_button(String user1, String user2, String arg1, String linkType, String buttonText) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectUserAndClickButton(user1, user2, linkType);
		else 
			Assume.assumeTrue(true);
	}
	
	@When("^I change status of source file with \"(.*?)\" as checked to \"(.*?)\"$")
	public void i_change_status_of_source_file_with_as_checked(String option, String flag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeSourceFileStatus(Boolean.parseBoolean(flag));
		else 
			Assume.assumeTrue(true);
	}

	@When("^I update poi of linked source files with \"(.*?)\" as checked to \"(.*?)\"$")
	public void i_update_poi_of_linked_source_file(String option, String flag) {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.changeSourceFilePOI(Boolean.parseBoolean(flag));
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^Target file \"(.*?)\" project with \"(.*?)\" link should have updated status as source file to \"(.*?)\"$")
	public void target_file_should_have_updated_status_as_source_file(String projectType, String linkType, String flag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedStatusInTargetFile(projectType, linkType, Boolean.parseBoolean(flag));
		else 
			Assume.assumeTrue(true);
	}

	@Then("^Target file \"(.*?)\" project with \"(.*?)\" link should have updated poi as source file to \"(.*?)\"$")
	public void target_file_should_have_updated_poi_as_source_file_to(String projectType, String linkType, String flag) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUpdatedPoiInTargetFile(linkType, projectType, Boolean.parseBoolean(flag));
		else 
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" Link document should be available in target folder \"(.*?)\" project$")
	public void link_document_should_be_available_in_destination_folder(String linkType, String projectType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.hasTargetFolderLinkDocument(linkType, projectType);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I deactivate the \"(.*?)\" link testdata folder$")
	public void i_deactivate_the_static_link_testdata_folder(String linkType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.deactivateTestDataFolder(linkType);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I create new single document status$")
	public void i_create_new_single_document_status() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.createDocumentTestStatus();
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I select dynamic link condition \"(.*?)\" for status \"(.*?)\"$")
	public void i_select_dynamic_link_condition(String condition, String status) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.selectDynamicLinkCondition(condition, status);
		} else
			Assume.assumeTrue(true);
	}
	
	@When("^I upload revisions of the source link documents with external attachments \"(.*?)\"$")
	public void i_upload_revisions_of_the_source_link_documents(String flag) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.uploadRevisionDocuments(Boolean.parseBoolean(flag));
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^Documents matching with condition should get \"(.*?)\" updated \"(.*?)\" project$")
	public void documents_matching_with_condition_should_get_updated_revisions(String criteria, String projectType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyUpdatedRevisionDocuments(criteria, projectType);
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^Documents matching with condition should get distributed to correct users \"(.*?)\" project$")
	public void documents_matching_with_condition_should_get_distributed_to_correct_users(String  projectType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyDistributionUsers(projectType);
		} else
			Assume.assumeTrue(true);
	}
	
	@Then("^Documents not matching with condition should not get \"(.*?)\" updated \"(.*?)\" project$")
	public void documents_not_matching_with_condition_should_not_get_updated_revision(String criteria, String projectType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.verifyNotUpdatedRevisionDocuments(criteria, projectType);
		} else
			Assume.assumeTrue(true);
	}
	
	@Given("^I deactivate cloned test project$")
	public void i_deactivate_cloned_test_project() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			scripts.deactivateClonedProject();
		} else
			Assume.assumeTrue(true);
	}
}