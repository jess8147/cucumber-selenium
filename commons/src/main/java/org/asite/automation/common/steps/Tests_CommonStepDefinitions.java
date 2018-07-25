package org.asite.automation.common.steps;

import cucumber.api.PendingException;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.utils.ResourceUtils;
import org.junit.Assume;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

// TODO: Auto-generated Javadoc
/**
 * The Class Tests_CommonStepDefinitions.
 * @author jasminprajapati
 */
public class Tests_CommonStepDefinitions extends AdoddleCommonAppMethods {

	/** The run test. */
	public static boolean	runTest	= true;
	
	/** The test data checked. */
	public static boolean   testDataChecked = false;
	
	/** The log. */
	final private static Logger	log			= AutomationLogger.getInstance().getLogger(Tests_CommonStepDefinitions.class);

	public static String formTitle 		= null;

	/**
	 * Sets the up project dc center.
	 *
	 * @param dc_center the new up project dc center
	 * @throws Throwable the throwable
	 */
	@Given("^Project DC ([^\"]*) is available")
	public void setup_project_dc_center(String dc_center) {
		runTest = isDataCenterAvailable(dc_center) && dataCheckUtils.isTestDataAvailable(TestDriverFactory.scenario);
		if(runTest) {
			dataCenter = dc_center;
			propertyUtils.setEnvironmentProperties();
			propertyUtils.setConfigSytemProperties(dataCenter);
			browser = ResourceHandler.getPropertyValue("browser");
			runTest = scb.isScenarioDependent(TestDriverFactory.scenario);
		}
		else {
			//Assume.assumeTrue("Data center OR Testdata not available", true);
			throw new PendingException("Data Center not available");
		}
	}

	/**
	 * Script verification is node specific.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^Script is Node specific$")
	public void script_verification_is_Node_specific() {
		new TestDriverFactory().setNodeFlag(true);
	}

	/**
	 * Script is browser specific.
	 *
	 * @param testBrowser the test browser
	 * @throws Throwable the throwable
	 */
	@Given("^Script is \"(.*?)\" browser specific$")
	public void script_is_browser_specific(String testBrowser) throws Throwable {
		if (runTest) {
			if (resourceUtils.getBrowser() != testBrowser) {
				System.out.println("setting browser to non-default: " + TestDriverFactory.scenario.getName());
				browser = testBrowser;
			}
			else
				Assume.assumeTrue(true);
		}
	}

	/**
	 * I am already logged in.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I am already logged in$")
	public void i_am_already_logged_in() throws Throwable {
		if (runTest) {
			propertyUtils.setConfigSytemProperties(dataCenter);
			TestDriverFactory.getInstance().setUp(browser);
			propertyUtils.setupEnvironmentTestProperties(dataCenter, System.getProperty("primary.username"));
			if (! getWebDriver().toString().contains("null"))
				login(System.getProperty("primary.username"), System.getProperty("primary.password"));
			else
				AutomationAssert.verifyTrue("Page load timeout in loading home page", false);
			verifyLogin();
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I am already logged in with multi project user.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I am already logged in with multi-Project user$")
	public void i_am_already_logged_in_with_multi_project_user() throws Throwable {
		if (runTest) {
			TestDriverFactory.getInstance().setUp(browser);
			propertyUtils.setupEnvironmentTestProperties(dataCenter, System.getProperty("multi.project.username"));
			login(System.getProperty("multi.project.username"), System.getProperty("multi.project.password"));
			setProjectAll();
			setMultiProjectUserAsPrimaryUser();
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I should redirect to tab.
	 *
	 * @param linkTab the link tab
	 * @throws Throwable the throwable
	 */
	@Then("^I should redirect to \"(.*?)\" tab$")
	public void i_should_redirect_to_tab(String linkTab) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			verifyAsiteMenuList(linkTab);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" link of popup$")
	public void i_click_on_link_of_popup(String linkName) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			clickOnPopupLink(linkName);
		else
			Assume.assumeTrue(true);
	}


	/**
	 * Context menu popup should open.
	 *
	 * @throws Throwable the throwable
	 */
	@Then("^Context menu popup should open$")
	public void context_menu_popup_should_open() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			verifyFileContextMenuOpened();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I have atleast one document in document listing.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I have atleast one document in document listing$")
	public void i_have_atleast_one_document_in_document_listing() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			verifyDocumentsCountOnFilesTab();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * Popup should close.
	 *
	 * @throws Throwable the throwable
	 */
	@Then("Popup window should close$")
	public void popup_should_close() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			verifyPopupIsClosed();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I create global test project as.
	 *
	 * @param projectTitle the project title
	 * @throws Throwable the throwable
	 */
	@Given("^I create global test project as \"(.*?)\"$")
	public void i_create_global_test_project_as(String projectTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			createGlobalTestProject(projectTitle);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I have atleast one project available.
	 *
	 * @throws Throwable the throwable
	 */
	@When("^I have atleast one project available$")
	public void i_have_atleast_one_project_available() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			verifyProjectIsAvailable();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I select permission checkbox and click on save for.
	 *
	 * @param permission the permission
	 * @param wsAdmin the ws admin
	 * @throws Throwable the throwable
	 */
	@When("^I select \"(.*?)\" form role permission checkbox and click on Save for \"(.*?)\"$")
	public void i_select_permission_checkbox_and_click_on_Save_for(String permission, String wsAdmin) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			selectPermissionCheckboxAndSave(permission, wsAdmin);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I switch to default content window.
	 *
	 * @throws Throwable the throwable
	 */
	@When("^I switch to default content window$")
	public void i_switch_to_default_content_window() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			switchDefault();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I switch to user.
	 *
	 * @param usrName the usr name
	 * @throws Throwable the throwable
	 */
	@When("^I switch to ([^\"]*) user$")
	public void i_switch_to_user(String usrName) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			switchToUser(usrName);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I click on plan view icon for site.
	 *
	 * @throws Throwable the throwable
	 */
	@When("^I click on PlanView Icon for Site$")
	public void i_click_on_PlanView_Icon_for_Site() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickPlanViewIcon();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I click on list view icon for site.
	 *
	 * @throws Throwable the throwable
	 */
	@When("^I click on ListView Icon for Site$")
	public void i_click_on_ListView_Icon_for_Site() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickListViewIcon();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I right click on project and i click on edit and i click on context menu list option.
	 *
	 * @param contextMenuOption the context menu option
	 * @throws Throwable the throwable
	 */
	@When("^I right click on Project And I click on Edit And I click on \"(.*?)\" option into context menu List$")
	public void i_right_click_on_project_and_i_click_on_edit_and_i_click_on_context_menu_list_option(String contextMenuOption) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			selectProjectContextMenuOption(contextMenuOption);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I click on existing site.
	 *
	 * @param siteTitle the site title
	 * @throws Throwable the throwable
	 */
	@When("^I click on existing Site ([^\"]*)$")
	public void i_click_on_existing_Site(String siteTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickExistingSiteLocation(siteTitle);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I click on existing location.
	 *
	 * @param locationTitle the location title
	 * @throws Throwable the throwable
	 */
	@When("^I click on existing Location ([^\"]*)$")
	public void i_click_on_existing_Location(String locationTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickExistingSiteLocation(locationTitle);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I have focus on any folder.
	 *
	 * @param folderTitle the folder title
	 * @throws Throwable the throwable
	 */
	@Given("^I have focus on \"(.*?)\" folder$")
	public void i_have_focus_on_any_folder(String folderTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			clickElementWithText(folderTitle);
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I relogin into adoddle.
	 *
	 * @throws Throwable the throwable
	 */
	@When("^I re-login into adoddle$")
	public void i_relogin_into_adoddle() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			reLoginIntoAdoddle();
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I login to adoddle with user.
	 *
	 * @param user the user
	 * @param password the password
	 * @throws Throwable the throwable
	 */
	@When("^I login to adoddle with ([^\"]*) and ([^\"]*)$")
	public void i_login_to_adoddle_with_user(String user, String password) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			loginIntoAdoddleWithUser(user, password);
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * Wait for interval.
	 *
	 * @param timeOut the time out
	 * @throws NumberFormatException the number format exception
	 * @throws InterruptedException the interrupted exception
	 */
	@Given("^I wait for \"(.*?)\" seconds$")
	public void waitForInterval(String timeOut) throws NumberFormatException, InterruptedException {
		if (Tests_CommonStepDefinitions.runTest) {
			waitUtils.waitInterval(Integer.parseInt(timeOut));
		}
		else
			Assume.assumeTrue(true);
	}
	
	/**
	 * I have cloned existing project automation test project.
	 *
	 * @param existingPorjectName the existing porject name
	 * @param dc the dc
	 * @throws Throwable the throwable
	 */
	@Given("^I have cloned existing project ([^\"]*) for ([^\"]*)$")
	public void i_have_cloned_existing_project(String existingPorjectName, String dc) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			cloneProject(existingPorjectName, dc);
		else
			Assume.assumeTrue(true);

	}

	/**
	 * I navigate on workflow tab.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("I navigate on workflow tab")
	public void i_navigate_on_workflow_tab() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickElementAndWait(LandingPage.lnk_Workflows);
		else
			Assume.assumeTrue(true);

	}

	/**
	 * I have clicked cloned project.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I have clicked cloned project$")
	public void i_have_clicked_cloned_project() throws Throwable {

		if (Tests_CommonStepDefinitions.runTest)
			focusClonedProject();
		else
			Assume.assumeTrue(true);

	}

	/**
	 * I click on AN D select from options popup list.
	 *
	 * @param arg1 the arg 1
	 * @param optionType the option type
	 * @throws Throwable the throwable
	 */
	@When("^I click on \"(.*?)\" AND select \"(.*?)\" from Options popup list$")
	public void i_click_on_AND_select_from_Options_popup_list(String arg1, String optionType) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickMoreOptionsAndSelectOption(optionType);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I am already logged in using user.
	 *
	 * @param userID the user ID
	 * @throws Throwable the throwable
	 */
	@Given("^I am already logged in using ([^\"]*) user$")
	public void i_am_already_logged_in_using_user(String userID) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			propertyUtils.setConfigSytemProperties(dataCenter);
			TestDriverFactory.getInstance().setUp(Tests_CommonStepDefinitions.browser);
			propertyUtils.setupEnvironmentTestProperties(AdoddleCommonAppMethods.dataCenter, userID);
			login(userID, ResourceHandler.loadProperty("test.users.common.password"));
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I click on more options.
	 *
	 * @throws Throwable the throwable
	 */
	@When("^I click on More Options$")
	public void i_click_on_More_Options() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickOnMoreOptions();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * Link should be successfully created.
	 *
	 * @throws Throwable the throwable
	 */
	@Then("^Link should be successfully created$")
	public void link_should_be_successfully_created() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			verifyLinkSuccess();
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I deactivate the cloned test project.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I deactivate the cloned test project$")
	public void i_deactivate_the_cloned_test_project() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			deactivateProject(clonedProject);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I search project on projects tab.
	 *
	 * @param project the project
	 * @throws Throwable the throwable
	 */
	@Given("^I search ([^\"]*) project on projects tab$")
	public void i_search_project_on_projects_tab(String project) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			searchProjects(project);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I right click on project automation test project and I select in project edit options.
	 *
	 * @param project the project
	 * @param option the option
	 * @throws Throwable the throwable
	 */
	@When("^I right click on project ([^\"]*) And I select \"(.*?)\" in project edit options$")
	public void i_right_click_on_project_AutomationTestProject_And_I_select_in_project_edit_options(String project, String option) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			selectProjectEditOption(project, option);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I reaload the page.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I reload the page$")
	public void i_reaload_the_page() throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			reloadPage();
			waitForCompletePageLoad();
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * Script has dependent scenario.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^Script has dependent scenarios$")
	public void script_has_dependent_scenario() throws Throwable {
		System.out.println("Setting Dependent scenario flag to true: " + TestDriverFactory.scenario.getName());
		depedentScenario = true;
	}

	/**
	 * Popup should open.
	 *
	 * @param popUpText the pop up text
	 * @throws Throwable the throwable
	 */
	@Then("^\"(.*?)\" popup should open$")
	public void popup_should_open(String popUpText) throws Throwable {
		if (runTest) {
			verifyPopUpWithText(popUpText);
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I am on models tab.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I am on Models tab$")
	public void i_am_on_Models_tab() throws Throwable {
		if (runTest)
			navigateTabByText("Models");
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I am on tab.
	 *
	 * @param tabName the tab name
	 * @throws Throwable the throwable
	 */
	@Given("^I am on \"(.*?)\" tab$")
	public void i_am_on_tab(String tabName) throws Throwable {
		if (runTest)
			navigateTabByText(tabName);
		else
			Assume.assumeTrue(true);
	}

	/**
	 * Given click on button with text.
	 *
	 * @param buttonText the button text
	 * @throws Throwable the throwable
	 */
	@Given("^I click on \"(.*?)\" button$")
	public void given_click_on_button_with_text(String buttonText) throws Throwable {
		if (runTest) {
			try {
				clickPopupButtonWithText(buttonText);
			}
			catch (Throwable t) {

				clickButtonWithText(buttonText);
			}
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I have sent an initiation email.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I have sent an initiation email$")
	public void i_have_sent_an_initiation_email() throws Throwable {
		if (runTest && 	Boolean.valueOf(ResourceUtils.getEmailTriggerFlag())) {
			emailUtils.sendInitiationEmail();
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I send reporting email to end users$")
	public void i_send_reporting_email_to_end_users() throws Throwable {
		if (runTest && 	Boolean.valueOf(ResourceUtils.getEmailTriggerFlag())) {
			emailUtils.sendReportingEmail();
		}
		else
			Assume.assumeTrue(true);
	}


	/**
	 * I have focus on project.
	 *
	 * @param projectTitle the project title
	 * @throws Throwable the throwable
	 */
	@Given("^I have focus on ([^\"]*) project$")
	public void i_have_focus_on_project(String projectTitle) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			globalProjectTitle = projectTitle;
			clickElementWithText(globalProjectTitle);
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I login using user.
	 *
	 * @param usrName the usr name
	 * @throws Throwable the throwable
	 */
	@When("^I login using ([^\"]*) user$")
	public void i_login_using_user(String usrName) throws Throwable {

		if (Tests_CommonStepDefinitions.runTest) {
			TestDriverFactory.getInstance().setUp(browser);
			propertyUtils.setConfigSytemProperties(dataCenter);
			login(usrName, System.getProperty("primary.password"));
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I logged in to svn with user and password.
	 *
	 * @param svnUser the svn user
	 * @param svnPassword the svn password
	 * @throws Throwable the throwable
	 */
	@Given("^I logged in to svn with ([^\"]*) and ([^\"]*)$")
	public void i_logged_in_to_svn_with_user_and_password(String svnUser, String svnPassword) throws Throwable {
		if (runTest) {
			TestDriverFactory.getInstance().setUp(browser);
			loginToSVN(svnUser, svnPassword);
		}
		else
			Assume.assumeTrue(true);
	}

	/**
	 * I set form view as defined in config.
	 *
	 * @param user the user
	 */
	@When("^I set form view as defined in config for ([^\"]*)$")
	public void i_set_form_view_as_defined_in_config(String user) {
		setProjetFormView(ResourceHandler.loadProperty("app.view.beta.flag"), user);
	}
	
	/**
	 * I set form view as defined in config.
	 *
	 * @param user the user
	 */
	@When("^I set file view as defined in config for ([^\"]*)$")
	public void i_set_file_view_as_defined_in_config(String user) {
		try {
			setFilesView(ResourceHandler.loadProperty("file.view.beta.flag"), user);
		}
		catch(Throwable t) {
			log.error("ERROR: Failed to set beta/classic view for "+user);
		}
			
	}
	
	
	/**
	 * I_set_ landing_ url.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I set Landing URL$")
	public void i_set_Landing_URL() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
	    	setLandingURL(AdoddleCommonAppMethods.dataCenter);
	    else
	    	Assume.assumeTrue(true);
	}
	
	
	/**
	 * I_deactivate_all_projects_with_text.
	 *
	 * @param text the text
	 * @throws Throwable the throwable
	 */
	@Given("^I deactivate all projects with text ([^\"]*)$")
	public void i_deactivate_all_projects_with_text(String text) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			deactivateProjectsWithText(text);
	    else
	    	Assume.assumeTrue(true);
	}
	
	/**
	 * I_deactivate_all_folders_with_text.
	 *
	 * @param text the text
	 * @throws Throwable the throwable
	 */
	@Given("^I deactivate all folders with text ([^\"]*)$")
	public void i_deactivate_all_folders_with_text(String text) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			deactivateFoldersWithText(text);
	    else
	    	Assume.assumeTrue(true);
	}
	
	/**
	 * I_deactivate_all_dashboard_with_text.
	 *
	 * @param text the text
	 * @throws Throwable the throwable
	 */
	@Given("^I deactivate all dashboards with text ([^\"]*)$")
	public void i_deactivate_all_dashboard_with_text(String text) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			deactivateDashboardWithText(text);
	    else
	    	Assume.assumeTrue(true);
	}
	
	/**
	 * I_deactivate_all_users_with_text.
	 *
	 * @param text the text
	 * @throws Throwable the throwable
	 */
	@Given("^I deactivate all users with text ([^\"]*)$")
	public void i_deactivate_all_users_with_text(String text) throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			deactivateUsersWithText(text);
	    else
	    	Assume.assumeTrue(true);
	}
	
	/**
	 * I_break_inheritance_of_the_project.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I break inheritance of the cloned project$")
	public void i_break_inheritance_of_the_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			breakAdoddleProjectInheritanceAndSave(clonedProject);
	    else
	    	Assume.assumeTrue(true);
	}
	
	/**
	 * I_break_partial_inheritance_of_the_project.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I break partial inheritance of the cloned project$")
	public void i_break_partial_inheritance_of_the_project() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			breakPartialAdoddleProjectInheritanceAndSave(clonedProject);
	    else
	    	Assume.assumeTrue(true);
	}
	
	/**
	 * I_logout_from_adoddle.
	 *
	 * @throws Throwable the throwable
	 */
	@When("^I logout from adoddle$")
	public void i_logout_from_adoddle() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			logOut();
		else
			Assume.assumeTrue(true);
		
	}
	
	/**
	 * I_select_context_menu_option_and_click_on.
	 *
	 * @param option1 the option1
	 * @param option2 the option2
	 */
	@When("^I select context menu option \"(.*?)\" and click on \"(.*?)\"$")
	public void i_select_context_menu_option_and_click_on(String option1, String option2) {
		if(Tests_CommonStepDefinitions.runTest)
			clickContextMenuOption(option1, option2);
		else
			Assume.assumeTrue(true);
	}
	
	/**
	 * I_click_on_box.
	 *
	 * @param arg1 the arg1
	 * @throws Throwable the throwable
	 */
	@When("^I click on \"(.*?)\" box on Admin tab$")
	public void i_click_on_box(String arg1) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest)
			clickOnAdminBoxWithText(arg1);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" button on Project Forms page$")
	public void i_click_on_button_createform(String createFormLinkText) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			clickCreateForm(createFormLinkText);
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I have focus on form template \"(.*?)\"$")
	public void i_have_focus_on_form_template_in(String formTemplate) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
			clickLinkWithTitle(formTemplate);
		}
		else
			Assume.assumeTrue(true);
	}

	@Given("^I input title \"(.*?)\" and description for RFI form$")
	public void i_have_filled_all_mandatory_form_fileds(String title) throws Throwable {
		if (Tests_CommonStepDefinitions.runTest) {
				formTitle = inputFormTitleAndDescriptionRFIForm(title);
		} else
			Assume.assumeTrue(true);
	}

	@Then("^User should be able to view file successfully$")
	public void file_should_get_viewed_successfully() throws Throwable {
		if(Tests_CommonStepDefinitions.runTest)
			verifyFileInHTMLViewer();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I search project as ([^\"]*)$")
	public void i_search_project_as(String project) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			searchProjects(project);
		else
			Assume.assumeTrue(true);
	}
	

}
