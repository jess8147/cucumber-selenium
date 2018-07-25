package org.asite.automation.classic.p1.stepdefinitions;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.utils.ResourceUtils;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SuppressWarnings("static-access")
public class CTests_CommonStepDefinitions extends ClassicCommonAppMethods{

public static boolean runTest = true;
	
	@Given("^Script is Node specific$")
	public void script_verification_is_Node_specific() throws Throwable {
		new TestDriverFactory().setNodeFlag(true);
	}

	@Given("^Project DC ([^\"]*) is available")
	public void setup_project_dc_center(String dc_center) throws Throwable { 
		runTest = isDataCenterAvailable(dc_center);
		if(runTest)
			setSystemProperties(dc_center);
		else
			Assume.assumeTrue(true);
	}

	@Given("^Script is \"(.*?)\" browser specific$")
	public void script_is_browser_specific(String browser) throws Throwable {
		if(runTest) {
			if(ResourceHandler.getPropertyValue("browser")!= browser)
				this.browser = browser;
			else
				this.browser = ResourceHandler.getPropertyValue("browser");
			
			setUp(browser);
			login(System.getProperty("primary.username"), System.getProperty("primary.password"));
	    	verifyLogin();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I setup default browser$")
	public void i_setup_default_browser() throws Throwable {
	    if(runTest) {
			setUp(ResourceHandler.getPropertyValue("browser"));
	    }
	    else
	    	Assume.assumeTrue(true);
	}
	
	@Given("^I am already logged in$")
	public void i_am_already_logged_in() throws Throwable {
	    if(runTest) {
			this.browser = ResourceHandler.getPropertyValue("browser");
			setUp(browser);
			propertyUtils.setupEnvironmentTestProperties(dataCenter, System.getProperty("primary.username"));
			login(System.getProperty("primary.username"), System.getProperty("primary.password"));
	    	verifyLogin();
	    }
	    else
	    	Assume.assumeTrue(true);
	}
	
	@Given("^I am on Workspace home page of workspace ([^\"]*)$")
	public void i_have_on_Workspace_home_page_of_workspace(String project) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest){
			projectTitle = project;
			navigateToWorkSpace(projectTitle);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" popup should open$")
	public void popup_should_open(String popUpText) throws Throwable 
	{
		if(runTest)
			verifyPopUpWithText(popUpText);
		else
			Assume.assumeTrue(true);
	}
	
	
	@Given("^I click on \"(.*?)\" button$")
	public void given_click_on_button_with_text(String buttonText) throws Throwable {
		if(runTest){
							
				clickClassicButtonWithText(buttonText);
			}
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^Context menu popup should open$")
	public void context_menu_popup_should_open() throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			verifyFileContextMenuOpened();
		else
			Assume.assumeTrue(true);
	}
	
	
	@Then("^\"(.*?)\" window page should open$")
	public void window_page_should_open(String windowTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			verifyPageTitle(windowTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on link with text \"(.*?)\"$")
	public void i_click_on_link_with_text(String linkText) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			clickLinkWithText(linkText);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on link with title \"(.*?)\"$")
	public void i_click_on_link_with_title(String linkTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			clickLinkWithTitle(linkTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on icon with title \"(.*?)\"$")
	public void i_click_on_icon_with_title(String iconTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			clickIconWithTitle(iconTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on folder with title \"(.*?)\"$")
	public void i_click_on_folder_with_title(String folderTitle) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			clickFolderWithTitle(folderTitle);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Document listing of \"(.*?)\" folder should open$")
	public void document_listing_of_selected_folder_should_open(String folderName) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			verifyDocListingPage(folderName);
		else
			Assume.assumeTrue(true);
	}
	
	@Given("^I have clicked on \"(.*?)\"$")
	public void i_have_clicked_on(String wsDoc) throws Throwable {
		if(CTests_CommonStepDefinitions.runTest)
			clickOnAllDocuments(wsDoc);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Document listing should open with all documents of workspace$")
	public void document_listing_should_open_with_all_documents_of_workspace() throws Throwable
	{
		if(CTests_CommonStepDefinitions.runTest)
		{
			verifyFileListing();
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I send reporting email to end users$")
	public void i_send_reporting_email_to_end_users() throws Throwable {
		if (runTest && 	Boolean.valueOf(ResourceUtils.getEmailTriggerFlag())) {
			emailUtils.sendReportingEmail();
		}
		else {
			log.info("Either email flag or runTest parameter not set to true");
			Assume.assumeTrue(true);
		}
	}

}
