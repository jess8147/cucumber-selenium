package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.CreateMultiDocCommentScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_CreateMultiDocComment {

	
	CreateMultiDocCommentScripts scripts = new CreateMultiDocCommentScripts();

	@When("^I Right click on multiple documents$")
	public void i_Right_click_on_multiple_documents() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.contextClickMultipleDocuments();
		else 
  			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" popup should open on files page$")
	public void popup_should_open(String newPopupText) throws Throwable {
	
		if(Tests_CommonStepDefinitions.runTest){
		scripts.verifyNewPopup(newPopupText);
		}
		else 
  			Assume.assumeTrue(true);
	}
	
	@Given("^I have opened \"(.*?)\" popup to create comment$")
	public void i_have_opened_popup_to_create_comment(String newPopupText) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyNewPopup(newPopupText);
		else 
  			Assume.assumeTrue(true);
	}

	@Given("^I have selected Users into \"(.*?)\"$")
	public void i_have_selected_Users_into(String toFieldText) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyUserExistsInToField(toFieldText);
		else 
  			Assume.assumeTrue(true);
	}

	@Given("^I have entered \"(.*?)\"$")
	public void i_have_entered(String titleLabelText) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest){
		scripts.enterTitleText();
		scripts.verifyTitleLabel(titleLabelText);
		}
		else 
  			Assume.assumeTrue(true);
	}

	@Given("^I have associated atleast one document$")
	public void i_have_associated_atleast_one_document() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.associateAtleastOneDocument(3);
		else 
  			Assume.assumeTrue(true);
	}
	
	@Given("^I have associated atleast one discussion$")
	public void i_have_associated_atleast_one_discussion() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.associateAtleastOneDiscussion(3);
		else 
  			Assume.assumeTrue(true);
	}
	
	@Given("^I have associated atleast one form$")
	public void i_have_associated_atleast_one_form() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.associateAtleastOneForm(3);
		else 
  			Assume.assumeTrue(true);
	}

	@When("^I Click on \"(.*?)\" button$")
	public void i_Click_on_button(String submitBtnText) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clickSubmitButton(submitBtnText);
		else 
  			Assume.assumeTrue(true);
	}

	@Then("^Comment should be successfully created$")
	public void comment_should_besuccessfully_created() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifySuccessMessage();
		else 
  			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Comment should be available in \"(.*?)\" tab$")
	public void comment_should_be_available_in_tab(String flag, String discussionTabText) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentOnDiscussionTab(flag, discussionTabText);
		else 
  			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Comment should be available in Files detail$")
	public void comment_should_be_available_in_Files_detail(String multiflag) throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest){
		scripts.navigateFilesTab();
		scripts.verifyCommentOnFilesDetails(multiflag);
		}
		else 
  			Assume.assumeTrue(true);
	}

	
	@Then("^Association should be successfully done with selected documents$")
	public void doc_association_should_be_successfully_done_with_selected_documents() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMultiCommentAssociation();
		else 
  			Assume.assumeTrue(true);
	}
	
	@Then("^Form Association should be successfully done with selected forms$")
	public void form_association_should_be_successfully_done_with_selected_forms() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMultiCommentAssociationForms();
		else 
  			Assume.assumeTrue(true);
	}
	
	@Then("^Discussion Association should be successfully done with selected discussions$")
	public void discussion_association_should_be_successfully_done_with_selected_discussions() throws Throwable 
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyMultiCommentAssociationDiscussions();
		else 
  			Assume.assumeTrue(true);
	}
	
	@Then("^Comment should not be available on Discussions listing$")
	public void comment_should_not_be_available_on_discussions_listing() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			scripts.verifyCommentIsNotAvailable();
		else 
	  		Assume.assumeTrue(true);
	}

}
