package org.asite.automation.common.lib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.asite.automation.CommonLocators.AdoddleAdminLocators.AdminTab;
import org.asite.automation.CommonLocators.AdoddleAdminLocators.ManageNotices;
import org.asite.automation.CommonLocators.AdoddleClassicLocators.ClassicLocators;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFieldLocators.FieldTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.Outlook365Locators;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.CommonLocators.AdoddleTasksLocators.TasksTab;
import org.asite.automation.CommonLocators.AdoddleWorkflowsLocators.WorkflowsTab;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.asite.automation.common.utils.JavaUtils;
import org.asite.automation.common.utils.PropertyUtils;
import org.asite.automation.common.utils.ResourceUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

// TODO: Auto-generated Javadoc

/**
 * The Class AdoddleCommonMethods.
 *
 * @author jasminprajapati
 */
public class AdoddleCommonAppMethods extends AutomationSeleniumExtendedLibrary {

	/** The Class EnumList.*/
	public static class EnumList {

		/** The Enum AsiteMenu.*/
		public enum AsiteMenu {
			
			/** The Dashboard. */
			Dashboard,
			
			/** The Projects. */
			Projects,
			
			/** The Files. */
			Files,
			
			/** The Project forms. */
			Project_Forms,
			
			/** The Discussions. */
			Discussions,
			
			/** The Reports. */
			Reports,
			
			/** The Models. */
			Models,
			
			/** The Procurement. */
			Procurement,
			
			/** The Contracts. */
			Contracts,
			
			/** The fm. */
			FM,
			
			/** The Finance. */
			Finance,
			
			/** The Project cost. */
			Project_Cost,
			
			/** The hr. */
			HR,
			
			/** The h s. */
			H_S,
			
			/** The Suppliers. */
			Suppliers,
			
			/** The App library. */
			AppLibrary,
			
			/** The Admin. */
			Admin,
			
			/** The Contacts. */
			Contacts,
			
			/** The Help. */
			Help,
			
			/** The Field. */
			Field
		}

		/** The Enum DashboardLeftPanel.*/
		public enum DashboardLeftPanel {
			
			/** The Welcome. */
			Welcome,
			
			/** The Incomplete. */
			Incomplete,
			
			/** The Overdue. */
			Overdue,
			
			/** The Due today. */
			Due_Today
		}

	}

	/** The data center.*/
	public static String						dataCenter					= null;

	/** The Asession ID. */
	private static String						AsessionID					= "ASESSIONID";

	/** The User Timezone ID.*/
	public static String						timezoneID					= null;

	/** The run test.*/
	public static boolean						runTest						= false;

	/** The file beta view flag.*/
	public static boolean						fileBetaViewFlag			= false;

	/** The run dependent.
	public static boolean						runDependent				= false; */

	/** The global project title.*/
	protected static String						globalProjectTitle;

	/** The global test project.*/
	protected static String						globalTestProject;

	/** The node flag. */
	final private static ThreadLocal<Boolean>	nFlag						= new ThreadLocal<Boolean>();

	/** The epoch Timestamp.*/
	public String								epoch						= dateUtils.getEpoch();

	/** The window title.*/
	public String								windowTitle;

	/**  The window names.
	public static String						uploadWindowName, downloadWindowName, appletWindowName; */

	/* The Application URL.
	private static String						appURL						= "\"" + ResourceHandler.getPropertyValue("application.url") + "\""; */

	/*
	 * The load Application URL Script.
	 protected static String					loadAppURLScript			= "location.assign(" + appURL + ")"; */

	/**
	 * The cloned project.
	 */
	protected static String						clonedProject;

	/**
	 * The browser.
	 */
	protected static String						browser;

	/**
	 * The index.
	 */
	public static int							index						= 0;

	/**
	 * The cucumber report Map key.
	 */
	protected static String						cucumberReportMapKey;

	/**
	 * The Cucumber Report Map.
	 */
	protected static HashMap<String, List<String>>	cucumberReportMap		= new HashMap<String, List<String>>();

	/**
	 * The Cucumber Report Data List.
	 */
	protected static List<String>				cucumberReportData			= new ArrayList<String>();

	/*
	 * The report map keys list.
	protected static List<String>				reportMapKeysList			= new ArrayList<String>(); */

	/**
	 * The Cucumber Report Headers.
	 */
	public static List<String>					cucumberReportHeaders		= Arrays.asList(AdoddleCommonStringPool.REPORT_FEATURETITLE, AdoddleCommonStringPool.AUTO_TEST_SCENARIO, AdoddleCommonStringPool.DATACENTER, AdoddleCommonStringPool.REPORT_SCENARIO_STATUS, AdoddleCommonStringPool.REPORT_SCENARIO_EXECUTION_TIME, AdoddleCommonStringPool.REPORT_SCENARIO_DOCUMENTATION);

	/**
	 * The Global Cloned Workflow Template.
	 */
	protected static String						globalWorkspaceTemplateName	= null;

	/*
	 * The global cloned workspace.

	protected static String						globalClonedWorkspace		= null; */

	/*
	 * The global inherited workspace.

	protected static String						globalInheritedWorkspace	= null;  */

	/**
	 * The script dependency flags.
	 */
	public static boolean						depedentScenario			= false;

	/**
	 * Form title *.
	 */
	public static String						formTitle					= null;

	/**
	 * List of Forms *.
	 */
	final public static List<String>			formList					= new ArrayList<String>();

	/**
	 * The adoddle home URL.
	 */
	public static String						adoddleHomeURL				= null;

	/**
	 * Instantiates a new adoddle common app methods.
	 */
	public AdoddleCommonAppMethods() {
		log = AutomationLogger.getInstance().getLogger(AdoddleCommonAppMethods.class);
	}

	/**
	 * Sets the a session ID.
	 *
	 * @param AsessionID the new a session ID
	 */
	@SuppressWarnings("static-access")
	private void setASessionID(String AsessionID) {
		this.AsessionID = AsessionID;
	}

	/**
	 * Gets the a session ID.
	 *
	 * @return the a session ID
	 */
	protected String getASessionID() {
		return AsessionID;
	}

	/**
	 * Sets the up.
	 *
	 * @param browser the new up
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setUp(String browser) throws IOException {
		TestDriverFactory.getInstance().setUp(browser);
	}

	/**
	 * The type Priviledge class.
	 */
	public class PriviledgeClass {

		/** The locator. */
		private By locator;

		/** The flag. */
		private boolean flag = true;

		/**
		 * Sets locator.
		 *
		 * @param locator the locator
		 */
		private void setLocator(By locator) {
			this.locator = locator;
		}

		/**
		 * Gets locator.
		 *
		 * @return the locator
		 */
		public By getLocator() {
			return this.locator;
		}

		/**
		 * Sets flag.
		 *
		 * @param flag the flag
		 */
		private void setFlag(boolean flag) {
			this.flag = flag;
		}

		/**
		 * Gets flag.
		 *
		 * @return the flag
		 */
		public boolean getFlag() {
			return this.flag;
		}
	}


	/**
	 * Sets the up selenium server.
	 *
	 * @param hub     the hub
	 * @param nodes   the nodes
	 * @param browser the browser
	 */
	public void setUpSeleniumServer(String hub, String nodes, String browser) {
		log.info("To be done");
	}

	/**
	 * Checks if is data center available.
	 *
	 * @param dc_center the dc_center
	 * @return true, if is data center available
	 */
	public boolean isDataCenterAvailable(String dc_center) {
		List<String> dataCenters = sysUtils.getFileList(ResourceHandler.getPropertyValue("application.test.dcs"));

		if (dc_center.equalsIgnoreCase(""))
			return false;

		return dataCenters.contains(dc_center) || dataCenters.contains(dc_center.substring(0, 2));
	}

	/**
	 * Gets the node flag.
	 *
	 * @return the node flag
	 */
	public static boolean getNodeFlag() {
		/* The node flag. */
		if (nFlag.get() == null)
			nFlag.set(false);

		return nFlag.get();
	}

	/**
	 * Sets the project.
	 *
	 * @param project    the project
	 * @param projectURL the project url
	 */
	public void setProject(String project, String projectURL) {
		navigateURL(projectURL);
		clickElementAndWaitForElement(GlobalPageElements.btn_FilterProject, GlobalPageElements.lnk_FilterProjectShowAll);
		clickElementAndWait(GlobalPageElements.lnk_FilterProjectShowAll);

		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll)) {
			clickElementAndWait(GlobalPageElements.lnk_FilterProjectClearAll);
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
		}
		else {
			clickElementAndWait(LandingPage.lnk_Dashboard);
		}

		clickElementAndWaitForElement(GlobalPageElements.btn_FilterProject, GlobalPageElements.lnk_FilterProjectShowAll);
		waitForCompletePageLoad();
		sendKeys(GlobalPageElements.txt_FilterProjectInput, project);
		waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterProjectFirstProject);
		if (!(getCount(GlobalPageElements.chk_FilterProjectFirstProjectSelected) > 0)) {
			clickElementAndWait(GlobalPageElements.chk_FilterProjectFirstProject);
			if (isDisplayed(GlobalPageElements.lnk_FilterClose))
				clickElementAndWait(GlobalPageElements.lnk_FilterClose);
			else
				clickElementAndWait(LandingPage.lnk_Dashboard);
		}
		else
			clickElementAndWait(LandingPage.lnk_Dashboard);
		waitForCompletePageLoad();

	}

	/**
	 * Sets the project to All.
	 */
	public void setProjectAll() {

		while (isDisplayed(ManageNotices.btn_NoticeInfoClose)) {
			log.info("Adoddle admin notice found, closing the notice" + "\t<THID: " + Thread.currentThread().getId() + ">");
			clickElementAndWait(ManageNotices.btn_NoticeInfoClose);
		}

		waitUntilElementIsDisplayed(GlobalPageElements.lbl_FilterProject);
		if (!getElementText(GlobalPageElements.lbl_FilterProject).contains(AdoddleCommonStringPool.LABEL_FILTER_PROJECT_ALL)) {
			log.info("Project filter not set to All, current project: " + getElementText(GlobalPageElements.lbl_FilterProject).split(":")[1] + "\t<THID: " + Thread.currentThread().getId() + ">");
			clickElementAndWaitForElement(GlobalPageElements.btn_FilterProject, GlobalPageElements.lnk_FilterProjectShowAll);
			try {
				mouseHover(GlobalPageElements.chk_FilterProjectFirstProject);
				clickElement(GlobalPageElements.lnk_FilterProjectShowAll);
			}
			catch (Throwable t) {
				log.error("ERROR: selenium could not click Project Show All link");
			}
			waitForCompletePageLoad();
			if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll)) {
				clickElementAndWait(GlobalPageElements.lnk_FilterProjectClearAll);
				clickElementAndWait(GlobalPageElements.lnk_FilterClose);
			}
			else {
				clickElementAndWait(LandingPage.lnk_Dashboard);
			}

		}
	}

	/**
	 * Sets the multi project user as primary user.
	 */
	public void setMultiProjectUserAsPrimaryUser() {
		System.setProperty("primary.username", System.getProperty("multi.project.username"));
		System.setProperty("primary.password", System.getProperty("multi.project.password"));
	}

	/**
	 * Login.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public void login(String username, String password) {
		log.info("Login Operation Started for: " + username + "\tTHID :" + Thread.currentThread().getId());
		try {
			waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
			switchIframe(LoginPage.frm_Iframe);
		}
		catch (Throwable t) {
			reloadPage();
			waitForCompletePageLoad();
			clickElementAndWaitForElement(LoginPage.lnk_login, LoginPage.frm_Iframe);
			switchIframe(LoginPage.frm_Iframe);
		}
		waitUntilElementIsDisplayed(LoginPage.txt_AsiteUsername);
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).clear();
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).sendKeys(username);
		getWebDriver().findElement(LoginPage.txt_AsitePassword).clear();
		getWebDriver().findElement(LoginPage.txt_AsitePassword).sendKeys(password);
		clickElementAndWait(LoginPage.btn_AsiteLogin);
		//getWebDriver().findElement(LoginPage.btn_AsiteLogin).click();
		waitForCompletePageLoad();
		switchDefault();
		try {
			waitUntilElementIsDisplayed(LandingPage.ele_WidgetNewFormsPublishedHeader, 10);
		}
		catch (Throwable t) {
			log.info("Recent forms header not available for " + username);
		}
		waitForCompletePageLoad();
		int counter =0;
		
		while (isDisplayed(GlobalPageElements.btn_DynamicModelDisMiss)) {
			clickElementAndWait(GlobalPageElements.btn_DynamicModelDisMiss);
			counter ++;
			if(counter == 5)
				break;
		}
		
		while(isDisplayed(By.cssSelector("div[id='myModal-annoucement'] div[class='modal-header'] button[class='close']"))) {
			clickElement(By.cssSelector("div[id='myModal-annoucement'] div[class='modal-header'] button[class='close']"));
			reloadPage();
			try {
				waitUntilElementIsDisplayed(By.cssSelector("div[id='dismis_button'] button"), 10);
			}
			catch(Throwable t) {
				log.info("All notifications are dismissed.");
			}
			
				
		}

		if (PropertyUtils.trainingFlag) {
			switchToClassicViewTraining();
			navigateURL(ResourceHandler.loadProperty("adoddle.live.training.url"));
		}
		log.info("Login Operation Completed for: " + username + "\tTHID :" + Thread.currentThread().getId());
		setDriverCookies(getDriverCookies());
		timezoneID = getUserTimezoneID();
		adoddleHomeURL = getCurrentWindowURL();
	}

	/**
	 * Sets the driver cookies.
	 *
	 * @param c the new driver cookies
	 */
	private void setDriverCookies(Set<Cookie> c) {
		for (Cookie cookie : c) {
			if (AsessionID.toLowerCase().equalsIgnoreCase(cookie.getName().toLowerCase())) {
				setASessionID(cookie.getValue());
			}
		}
	}

	/**
	 * Get User's Timezone ID.
	 *
	 * @return the user timezone id
	 */
	private String getUserTimezoneID() {
		waitForCompletePageLoad();
		return ((JavascriptExecutor) getWebDriver()).executeScript("return USP.timezoneId").toString();
	}

	/**
	 * Select date from calendar.
	 *
	 * @param days the days
	 */
	protected void selectDateFromCalendar(int...days) {
		String selectedMonth, selectedDate;
		int daysAdded = days.length > 0 ? days[0] : 2;
		selectedMonth = DateUtils.addDaysToDate("MMMM", timezoneID, daysAdded);
		log.info("selectDateFromCalendar() - selected month after adding 2 days: " + selectedMonth);
		selectedDate = DateUtils.addDaysToDate("d", timezoneID, daysAdded);
		log.info("selectDateFromCalendar() - selected date after adding 2 days: " + selectedDate);
		waitForCompletePageLoad();
		if (!getElementText(FilesTab.lbl_PopDatePickerCurrentMonthLabel).contains(selectedMonth))
			clickElementAndWait(FilesTab.lnk_PopDatePickerNextMonthLink);
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//td[@data-handler='selectDay']//a[text()='" + selectedDate + "']"));
	}

	/**
	 * select Date with added Days.
	 *
	 * @param addedDays the added days
	 */
	protected void selectCustomizedDateFromCalendar(int addedDays) {
		String selectedMonth, selectedDate;
		selectedMonth = DateUtils.addDaysToDate("MMMM", timezoneID, addedDays);
		log.info("selectDateFromCalendar() - selected month after adding " + addedDays + " days: " + selectedMonth);
		selectedDate = DateUtils.addDaysToDate("d", timezoneID, addedDays);
		log.info("selectDateFromCalendar() - selected date after adding " + addedDays + " days: " + selectedDate);
		waitForCompletePageLoad();
		if (!getElementText(FilesTab.lbl_PopDatePickerCurrentMonthLabel).contains(selectedMonth))
			clickElementAndWait(FilesTab.lnk_PopDatePickerNextMonthLink);
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//td[@data-handler='selectDay']//a[text()='" + selectedDate + "']"));
	}

	/**
	 * Swith To Classic View Training.
	 */
	private void switchToClassicViewTraining() {
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_ClassicView);
		clickElementAndWait(LandingPage.lnk_ClassicView);
		waitForCompletePageLoad();
		clickElementAndWait(ClassicLocators.tab_Training);
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);

	}

	/**
	 * Swith To Classic View.
	 */
	public void switchToClassicView() {
		while (isDisplayed(GlobalPageElements.lnk_HeaderNotificationClose))
			clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_ClassicView);
		clickElementAndWait(LandingPage.lnk_ClassicView);
		waitForCompletePageLoad();
	}

	/**
	 * Verify login.
	 */
	public void verifyLogin() {
		if (!strUtils.splitString(getElementText(GlobalPageElements.lbl_FilterProject), ":").get(1).trim().equalsIgnoreCase(System.getProperty("global.test.project"))) {
			clickElementAndWaitForElement(GlobalPageElements.btn_FilterProject, GlobalPageElements.lnk_FilterProjectShowAll);
			clickElementAndWait(GlobalPageElements.lnk_FilterProjectShowAll);
			if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
				clickElementAndWait(GlobalPageElements.lnk_FilterProjectClearAll);
			sendKeys(GlobalPageElements.txt_FilterProjectInput, System.getProperty("global.test.project"));
			waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterProjectFirstProject);
			if (!isSelected(GlobalPageElements.chk_FilterProjectFirstProject))
				clickElementAndWait(GlobalPageElements.chk_FilterProjectFirstProject);
			clickElementAndWait(LandingPage.lnk_Dashboard);
			Assert.assertTrue(isDisplayed(LandingPage.lnk_Dashboard));
		}
	}

	/**
	 * Log out.
	 */
	public void tearLogOut() {
		if (!(getWebDriver() == null) && !(getWebDriver().toString().contains("null"))) {
			while (isDisplayed(GlobalPageElements.lnk_HeaderNotificationClose)) {
				clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
			}
			reloadPage();
			waitForCompletePageLoad();
			if (isDisplayed(LandingPage.btn_UserProfile)) {
				log.info("Logging out...Please wait...");
				clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_Logout);
				clickElementAndWaitForElement(LandingPage.lnk_Logout, LoginPage.frm_Iframe);
			}
		}
	}

	/**
	 * Log out.
	 */
	public void logOut() {
		while (isDisplayed(GlobalPageElements.lnk_HeaderNotificationClose)) {
			clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
		}
		if (!isDisplayed(LandingPage.btn_UserProfile))
			reloadPage();
		waitUntilElementIsDisplayed(LandingPage.btn_UserProfile, 30);
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_Logout);
		clickElementAndWaitForElement(LandingPage.lnk_Logout, LoginPage.frm_Iframe);
		waitForCompletePageLoad();
	}

	/**
	 * Navigate tab.
	 *
	 * @param locator the locator
	 */
	public void navigateTab(By locator) {
		clickElementAndWait(locator);
	}

	/**
	 * Goto asite menu list.
	 *
	 * @param asiteMenu the asite menu
	 */
	public void gotoAsiteMenuList(EnumList.AsiteMenu asiteMenu) {
		String menu = asiteMenu.toString().replace('_', ' ');
		String asiteMenuXpath = ".//a[starts-with(@id,'nav') and text()='" + menu.trim() + "']";
		if (isDisplayed(By.xpath(asiteMenuXpath.trim()))) {
			clickElement(By.xpath(asiteMenuXpath.trim()));
		}
		else {
			clickElementAndWait(LandingPage.btn_More);
			clickElement(By.xpath(asiteMenuXpath.trim()));
		}

		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();

	}

	/**
	 * Search files.
	 *
	 * @param fileName the file name
	 */
	public void searchFiles(String fileName) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(FilesTab.txt_FilesFilterInput);
		sendKeys(FilesTab.txt_FilesFilterInput, fileName);
		log.info("Searching Adoddle file(s) with docRef/fileName/Content/docTitle matching with: " + fileName);
		/* clickElementAndWait(FilesTab.btn_FilesFilterSearch); */
		sendKeys(FilesTab.txt_FilesFilterInput, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		log.info("Searched Adoddle file(s) with docRef/fileName/Content/docTitle matching with: " + getValue(FilesTab.txt_FilesFilterInput));
		log.info("Number of File(s) found after document search :" + getCount(FilesTab.css_DocListingFilesCount));
	}

	/**
	 * Search Tasks.
	 *
	 * @param taskContent the task content
	 */
	public void searchTasks(String taskContent) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
		waitUntilElementIsDisplayed(TasksTab.txt_TasksFilterInput);
		sendKeys(TasksTab.txt_TasksFilterInput, taskContent);
		log.info("Searching Adoddle Task(s) with task/file DocRef/workspace matching with: " + taskContent);
		try {
			//sendKeys(TasksTab.txt_TasksFilterInput, Keys.ENTER);
//			findElement(By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] :nth-child(1) button[class*='dropdown-toggle']")).click();
			clickElementAndWait(By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] :nth-child(1) button[class*='dropdown-toggle']"));
			waitUtils.waitInterval(2);
			if(isDisplayed(By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] :nth-child(1) div[class*='dropdown-menu'] :nth-child(1)")))
				clickElementAndWait(By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] :nth-child(1) div[class*='dropdown-menu'] :nth-child(1)"));
//			waitUntilElementIsDisplayed(By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] :nth-child(1) div[class*='dropdown-menu'] :nth-child(1)"));
//			clickElementAndWait(By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] :nth-child(1) div[class*='dropdown-menu'] :nth-child(1)"));
		}
		catch (Throwable e) {
			log.error("ERROR: InterruptedException hit while searching Task(s)");
		}
		waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
		waitForCompletePageLoad();
		log.info("Searched Adoddle Task(s) with task/file DocRef/workspace matching with: " + getValue(TasksTab.txt_TasksFilterInput));
		log.info("Number of Task(s) found after task search :" + getCount(TasksTab.css_TaskListingTasksCount));
	}

	/**
	 * Search Defects.
	 *
	 * @param defectTitle the defect title
	 */
	public void searchDefects(String defectTitle) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(FieldTab.txt_SearchDefectInput);
		sendKeys(FieldTab.txt_SearchDefectInput, defectTitle);
		log.info("Searching Adoddle defect(s) title matching with: " + defectTitle);
		/* clickElementAndWait(FieldTab.btn_SearchDefectButton); */
		sendKeys(FieldTab.txt_SearchDefectInput, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		log.info("Searched Adoddle defect(s) title matching with: " + getValue(FieldTab.txt_SearchDefectInput));
		log.info("Number of Defect(s) found after defect search :" + getCount(FieldTab.css_FieldListingDefectsCount));

	}

	/**
	 * Search file DocRef.
	 *
	 * @param fileDocRef the file doc ref
	 */
	public void searchFilesUsingDocRefInClassic(String fileDocRef) {
		waitUntilElementIsDisplayed(DocListingPage.txt_DocListingDocRefInput);
		clear(DocListingPage.txt_DocListingDocRefInput);
		sendKeys(DocListingPage.txt_DocListingDocRefInput, fileDocRef);
		log.info("Searching file in classic docRef matching with: " + fileDocRef);
		clickElementAndWait(DocListingPage.btn_DocListingSearch);
	}

	/**
	 * Search projects.
	 *
	 * @param projectName the project name
	 */
	public void searchProjects(String projectName) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(ProjectsTab.txt_ProjectsFilterInput);
		findElement(ProjectsTab.txt_ProjectsFilterInput).clear();
		sendKeys(ProjectsTab.txt_ProjectsFilterInput, projectName);
		log.info("Searching project name matching with: " + projectName);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		/* clickElementAndWait(ProjectsTab.btn_ProjectsFilterSearch); */
		sendKeys(ProjectsTab.txt_ProjectsFilterInput, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		/*
		 * if (isDisplayed(ProjectsTab.lnk_ListView)) clickElementAndWait(ProjectsTab.lnk_ListView);
		 */
		setListingStyle("ListView");
		log.info("Searched project name matching with: " + getValue(ProjectsTab.txt_ProjectsFilterInput));
		log.info("Number of Project(s) found after project search :" + getCount(ProjectsTab.css_ProjectRowsCount));
	}

	/**
	 * Verify pop up with text.
	 *
	 * @param headerText the header text
	 */
	public void verifyPopUpWithText(String headerText) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		try {
			waitUntilElementContainsText(GlobalPageElements.lbl_PopUpHeader, headerText, 5);
		}
		catch (Throwable t) {
			waitUntilElementContainsText(GlobalPageElements.pop_PopUpElement, headerText, 5);
		}
	}

	/**
	 * Verify asite menu list.
	 *
	 * @param linkMenu the link menu
	 */
	public void verifyAsiteMenuList(String linkMenu) {
		if (!linkMenu.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
			waitUntilElementContainsText(GlobalPageElements.lnk_ActiveTab, linkMenu.trim());
			Assert.assertEquals(getElementText(GlobalPageElements.lnk_ActiveTab), linkMenu.trim());
		}
		else {
			Assert.assertEquals(new Select(findElement(FilesTab.drp_TabTypeDropdown)).getFirstSelectedOption().getText(), AdoddleCommonStringPool.FILE_DISCUSSIONS);
			waitUntilElementIsDisplayed(FilesTab.txt_DiscussionsFilterInput);
		}
		waitForCompletePageLoad();
	}

	
	/**
	 * Click on popup link.
	 *
	 * @param linkName the link name
	 */
	public void clickOnPopupLink(String linkName) {
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//a[text()='" + linkName + "']"));
	}

	
	/**
	 * Navigate tab by text.
	 *
	 * @param tabName the tab name
	 */
	public void navigateTabByText(String tabName) {
		if (isDisplayedLinkWithText(tabName)) {
			clickLinkWithText(tabName);
			waitForCompletePageLoad();
		}
		else if (tabName.equalsIgnoreCase(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
			navigateToDiscussionTab();
		}
		else if (isDisplayed(By.xpath("//a[contains(@id, 'nav') and text()='" + tabName + "']"))) {
			clickElementAndWait(By.xpath("//a[contains(@id, 'nav') and text()='" + tabName + "']"));
		}
		else {
			clickElementAndWaitForElement(LandingPage.btn_More, By.xpath("//a[contains(@id, 'nav') and contains(text(),'" + tabName + "')]"));
			clickElementAndWait(By.xpath("//a[contains(@id, 'nav') and contains(text(),'" + tabName + "')]"));
		}
	}

	/**
	 * Navigate to discussion tab.
	 */
	public void navigateToDiscussionTab() {
		navigateTab(LandingPage.lnk_Files);
		waitUntilElementIsDisplayed(FilesTab.chk_DocListingCheckAll);
		clickElementAndWaitForElement(FilesTab.lnk_FilesTabSubTabDiscussions, FilesTab.txt_DiscussionsFilterInput);
	}

	/**
	 * Verify file context menu opened.
	 */
	protected void verifyFileContextMenuOpened() {
		Assert.assertTrue(isDisplayed(FilesTab.opt_FileContextClickMenu));
	}

	/**
	 * Verify file Documents count on files tab.
	 */
	public void verifyDocumentsCountOnFilesTab() {
	    waitUntilElementCountToBeMoreThan(FilesTab.css_DocListingFilesCount, 1);
		Assert.assertTrue(getCount(FilesTab.css_DocListingFilesCount) > 1);
	}

	/**
	 * Search discussions.
	 *
	 * @param commentTitle the comment title
	 */
	public void searchDiscussions(String commentTitle) {
		waitUntilElementIsDisplayed(FilesTab.txt_DiscussionsFilterInput);
		findElement(FilesTab.txt_DiscussionsFilterInput).clear();
		sendKeys(FilesTab.txt_DiscussionsFilterInput, commentTitle);
		log.info("Searching Adoddle comment(s) title matchig with: " + commentTitle);
		/* clickElementAndWait(DiscussionsTab.btn_DiscussionsFilterSearch); */
		sendKeys(FilesTab.txt_DiscussionsFilterInput, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		log.info("Searched Adoddle comment(s) title matchig with: " + getValue(FilesTab.txt_DiscussionsFilterInput));
		log.info("Number of Discussion(s) found after discussion search :" + getCount(DiscussionsTab.css_DiscussionListingDiscussionsCount));
	}

	/**
	 * Search forms.
	 *
	 * @param formName the form name
	 */
	public void searchForms(String formName) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_FormListingFormSearchInput);
		sendKeys(ProjectFormsTab.txt_FormListingFormSearchInput, formName);
		log.info("Searching Adoddle form(s) title matching with: " + formName);
		/* clickElementAndWait(ProjectFormsTab.btn_filterProjectForm); */
		sendKeys(ProjectFormsTab.txt_FormListingFormSearchInput, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		log.info("Searched Adoddle form(s) title matching with: " + getValue(ProjectFormsTab.txt_FormListingFormSearchInput));
		log.info("Number of Form(s) found after form search :" + getCount(ProjectFormsTab.css_ProjectFormListingCount));
	}

	/**
	 * Search models.
	 *
	 * @param modelName the model name
	 */
	public void searchModels(String modelName) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(ModelsTab.txt_filterModelInput);
		findElement(ModelsTab.txt_filterModelInput).clear();
		sendKeys(ModelsTab.txt_filterModelInput, modelName);
		log.info("Searching Adoddle models(s) title matching with: " + modelName);
		/* clickElementAndWait(ModelsTab.btn_filterModel); */
		sendKeys(ModelsTab.txt_filterModelInput, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		log.info("Searched Adoddle models(s) title matching with: " + getValue(ModelsTab.txt_filterModelInput));
		log.info("Number of model(s) found after model search :" + getCount(ModelsTab.css_ModelsListingModels));
	}

	/**
	 * Search discussions.
	 *
	 * @param commentTitle the comment title
	 */
	protected void searchComments(String commentTitle) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(FilesTab.txt_DiscussionsFilterInput);
		findElement(FilesTab.txt_DiscussionsFilterInput).clear();
		findElement(FilesTab.txt_DiscussionsFilterInput).sendKeys(commentTitle);
		log.info("Searching Adoddle comment(s) title matching with: " + commentTitle);
		/* clickElementAndWait(DiscussionsTab.lnk_DiscussionSearchFilterLink); */
		sendKeys(FilesTab.txt_DiscussionsFilterInput, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		log.info("Searched Adoddle comment(s) title matching with: " + getValue(FilesTab.txt_DiscussionsFilterInput));
		log.info("Number of Comment(s) found after comment search :" + getCount(DiscussionsTab.css_DiscussionListingDiscussionsCount));

	}

	/**
	 * Swith multiple frames.
	 */
	public void switchMultiFrames() {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);
	}

	/**
	 * Sort by and verify.
	 *
	 * @param columnName the column name
	 * @param order      the order
	 */
	public void sortByAndVerify(String columnName, String order) {
		int columnCounter = 1;
		List<String> columnData = new ArrayList<String>();
		List<WebElement> columnHeaders = findElements(GlobalPageElements.css_GlobalListingHeaderColumns);

		for (WebElement wE : columnHeaders) {

			Actions action = new Actions(getWebDriver());
			action.moveToElement(wE).build().perform();
			log.info("Printing Column name: " + wE.getText());

			columnCounter++;

			if (wE.getText().equalsIgnoreCase(columnName)) {
				log.info("Required Column name: " + wE.getText());
				wE.click();
				waitForCompletePageLoad();

				columnData = getColumnDefaultData(columnCounter);
				log.info("Unsorted columndata: " + columnData);

				if (order.equalsIgnoreCase("ascending")) {
					if (isDisplayed(GlobalPageElements.ele_GlobalListingHeaderAscendingIcon))
						break;
					else if (isDisplayed(GlobalPageElements.ele_GlobalListingHeaderDescendingIcon))
						clickElementAndWait(GlobalPageElements.ele_GlobalListingHeaderDescendingIcon);

				}
				else if (order.equalsIgnoreCase("descending")) {
					if (isDisplayed(GlobalPageElements.ele_GlobalListingHeaderDescendingIcon))
						break;
					else if (isDisplayed(GlobalPageElements.ele_GlobalListingHeaderAscendingIcon))
						clickElementAndWait(GlobalPageElements.ele_GlobalListingHeaderAscendingIcon);
				}

				break;
			}
		}

		List<String> sortedColumnData = getSortedColumnData(columnData, order);
		log.info("Sorted ColumnData: " + sortedColumnData);

		columnData = getColumnDefaultData(columnCounter);
		log.info("Sorted ColumnData after sorting on App: " + columnData);

		AutomationAssert.verifyTrue(columnData.toString() + "\n does not match \n" + sortedColumnData, columnData.equals(sortedColumnData));
	}

	/**
	 * Perform file action.
	 *
	 * @param actionString the action string
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException          Signals that an I/O exception has occurred.
	 */
	public void performFileAction(String actionString) throws InterruptedException, IOException {

		if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_STATUS_CHANGE))
			performStatusChange();
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_ACTION))
			performSimpleAction();
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_COMMENT_COORDINATION))
			performCommentCoordination();
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_COMMENT))
			performSimpleComment();
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_ACKNOWLEDGEMENT))
			performAcknowledgement();
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_DISTRIBUTION))
			performDefaultDistribution(Arrays.asList(System.getProperty("primary.username")));
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_INFORMATION))
			performInformation();
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_PUBLISHING))
			performPublish(resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION);
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_COMMENT_INCORP))
			performCommentIncorporation();
	}

	/**
	 * Perform information.
	 */
	public void performInformation() {
		waitForCompletePageLoad();
		String parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(FilesTab.frm_BravaObjectFrame);
		switchIframe(FilesTab.frm_BravaObjectFrame);
		waitUntilElementIsDisplayed(FilesTab.frm_OpenFileIframe);
		switchDefault();
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	/**
	 * Perform form action.
	 *
	 * @param actionString the action string
	 */
	public void performFormAction(String actionString) {

		if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("form.action.asssign.status")))
			performFormAssignStatus();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("form.action.attach.docs")))
			performFormAttachDocs();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("form.action.distribute")))
			performFormDistribution();

		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("form.action.for.action")))
			performFormAction();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("form.action.for.acknowledgement")))
			performFormAcknowledgement();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("form.action.respond")))
			performFormRespond();
		else if (actionString.equalsIgnoreCase(AdoddleCommonStringPool.FOR_INFORMATION))
			performFormForInformation();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("for.comment.incorporation")))
			performCommentIncorporation();
	}

	/**
	 * Perform form assign status.
	 */
	public void performFormAssignStatus() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form attach docs.
	 */
	public void performFormAttachDocs() {
		log.info("TESTDATA: TestData to be created");

	}

	/**
	 * Perform form action.
	 */
	public void performFormAction() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form distribution.
	 */
	public void performFormDistribution() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form respond.
	 */
	public void performFormRespond() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form for information.
	 */
	public void performFormForInformation() {
		waitForCompletePageLoad();
		String parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	/**
	 * Perform form acknowledgement.
	 */
	public void performFormAcknowledgement() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform publish.
	 *
	 * @param path the path
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException          Signals that an I/O exception has occurred.
	 */
	public void performPublish(String path) throws InterruptedException, IOException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		sysUtils.createFile(path);
		String distributionSubject = javaUtils.getRandomString(10) + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(FilesTab.pop_UploadFileDialog);
		List<String> fileList = sysUtils.getFileList(path);
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		try {
			Assert.assertTrue(isDisplayed(FilesTab.txt_PopUploadFileDistributeTo));
		}
		catch (Throwable t) {
			clickElementAndWait(FilesTab.btn_PopUploadFileDistributeFiles);
			waitUntilElementIsDisplayed(FilesTab.txt_PopUploadFileDistributeTo);
		}
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, System.getProperty("secondary.username"));
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.TAB);
		sendKeys(FilesTab.txt_PopUploadFileDistributeSubject, distributionSubject);
		sendKeys(FilesTab.txt_PopUploadFileDistributeRev, String.valueOf(1));
		/*
		 * selectByValue(FilesTab.drp_PopUploadFileDistributePurpose, "1"); selectByValue(FilesTab.drp_PopUploadFileDistributeStatus, "1");
		 */
		selectByVisibleText(FilesTab.drp_PopUploadFileDistributePurpose, AdoddleCommonStringPool.STATUS_FOR_REVIEW);
		selectByVisibleText(FilesTab.drp_PopUploadFileDistributeStatus, AdoddleCommonStringPool.STATUS_FOR_REVIEW);
		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeUpload);
		waitForCompletePageLoad();
		try {
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		}
		catch (Throwable t) {
			log.info("error while waiting for overlay process");
		}

		sysUtils.deleteFile(path);
	}

	/**
	 * Perform status change.
	 */
	public void performStatusChange() {
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionChangeStatus);
		verifyElementText(FilesTab.lbl_PopFilesActionChangeStatusHeader, AdoddleCommonStringPool.LABEL_STATUS_CHANGE);
		List<WebElement> statusList = findElements(FilesTab.css_PopFileActionChangeStatusToOptions);

		for (WebElement e : statusList) {
			if (! (e.getText().trim().equalsIgnoreCase(getElementText(FilesTab.lbl_FilesActionChangeStatusFrom).trim()) || e.getText().trim().equalsIgnoreCase(AdoddleCommonStringPool.PLEASE_SELECT))) {
				selectByVisibleText(FilesTab.drp_PopFileActionChangeStatusTo, e.getText());
				break;
			}
		}
		sendKeys(FilesTab.txt_PopFileActionReasonForStatusChange, javaUtils.getRandomString(30));
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopFileActionChangeStatus, FilesTab.pop_FilesActionChangeStatus);
		waitForCompletePageLoad();
	}

	/**
	 * Perform simple action.
	 */
	public void performSimpleAction() {
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionForAction);
		verifyElementText(FilesTab.lbl_PopFilesActionForActionHeader, ResourceHandler.loadProperty("for.action"));
		clickElementAndWait(FilesTab.chk_PopFileActionForAction);
		sendKeys(FilesTab.txt_PopFileActionForActionRemarks, javaUtils.getRandomString(30));
		clickElement(FilesTab.btn_PopFileActionForActionSubmit);
	}

	/**
	 * Perform comment incorporation.
	 */
	public void performCommentIncorporation() {
		clickElementAndWaitForElement(FilesTab.chk_PopForCommentIncorporationAcknowledge, FilesTab.txt_PopForCommentIncorporationDate);
		clickElementAndWaitForElement(FilesTab.txt_PopForCommentIncorporationDate, FilesTab.ele_PopForCommentIncorporationDatePicker);
		clickElementAndWaitForElement(FilesTab.btn_PopForCommentIncorporationDatePickerToday, FilesTab.btn_PopForCommentIncorporationSubmit);
		clickElementAndWait(FilesTab.btn_PopForCommentIncorporationSubmit);
	}

	/**
	 * Perform comment coordination.
	 */
	public void performCommentCoordination() {
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionForCommentCoordination);
		clickElementAndWait(FilesTab.btn_PopFilesActionForCommentCoordinationSubmit);
	}

	/**
	 * Perform simple comment.
	 */
	public void performSimpleComment() {
		String parentHandle = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		if (!fileBetaViewFlag) {
			waitUntilElementIsDisplayed(FilesTab.pop_StartNewDiscussion);
			sendKeys(FilesTab.txt_NewDiscussionTitleInput, javaUtils.getRandomString(10) + epoch);
			sendKeys(FilesTab.txt_NewDiscussionDescInput, javaUtils.getRandomString(10) + epoch);
			clickElement(FilesTab.btn_NewDiscussionSubmit);
			try {
				waitUntilElementIsDisplayed(FilesTab.lbl_FileAddCommentSuccessMsg);
			}
			catch (Throwable t) {
				log.error("Create comment success message verification failed");
			}
		}
		else {
			waitUntilElementIsDisplayed(FilesTab.txt_BetaFileViewNewDiscussionTitle);
			String commentTitle = javaUtils.getRandomString(10) + epoch;
			sendKeys(FilesTab.txt_BetaFileViewNewDiscussionTitle, commentTitle);
			sendKeys(FilesTab.btn_BetaFileViewNewDiscussionDescBody, javaUtils.getRandomString(10) + epoch);
			clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_BetaFileViewNewDiscussionSubmit, FilesTab.btn_BetaFileViewNewDiscussionSubmit);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion), commentTitle), getElementText(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion).contains(commentTitle));
		}
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	/**
	 * Perform acknowledgement.
	 */
	public void performAcknowledgement() {
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionForAcknowledgement);
		verifyElementText(FilesTab.lbl_PopFilesActionForAcknowledgementHeader, "For Acknowledgement");
		clickElementAndWait(FilesTab.btn_PopFilesActionForAcknowledgementReceipt);
	}

	/**
	 * Perform distribution.
	 *
	 * @param distributeToList the distribute to list
	 */
	public void performDefaultDistribution(List<String> distributeToList) {
		String testSubjectForDistribution = javaUtils.getRandomString(10) + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionForDistribute);
		for (String distributeTo : distributeToList) {
			sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, distributeTo);
			sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.TAB);
		}
		sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject, testSubjectForDistribution);
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		try {
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		}
		catch (Throwable t) {
			log.info("error while waiting for overlay process");
		}
	}

	/**
	 * Gets the column default data.
	 *
	 * @param counter the counter
	 * @return the column default data
	 */
	public List<String> getColumnDefaultData(int counter) {
		log.info("Counter in getColumnData(): " + counter);
		List<String> data = new ArrayList<String>();
		List<WebElement> paginationList = findElements(GlobalPageElements.css_GlobalListingPaginationCount);

		if (paginationList.size() != 0) {
			for (int index = 0; index < paginationList.size(); index++) {
				List<WebElement> columnElements = findElements(By.cssSelector("div[id='listing'] div[id='adTableBody'] div[class*='rows'] div[class*='divtd']:nth-child(" + counter + ")"));

				for (WebElement cE : columnElements) {
					log.info(cE.getText());
					data.add(cE.getText().toLowerCase());
				}

				if (index != paginationList.size() - 1) {
					clickElementAndWait(GlobalPageElements.lnk_GlobalListingPaginationNext);
					waitForCompletePageLoad();
				}

			}
		}
		else {
			List<WebElement> columnElements = findElements(By.cssSelector("div[id='listing'] div[id='adTableBody'] div[class*='rows'] div[class*='divtd']:nth-child(" + counter + ")"));

			for (WebElement cE : columnElements) {
				log.info(cE.getText());
				data.add(cE.getText().toLowerCase());
			}
		}

		return data;
	}

	/**
	 * Gets the sorted column data.
	 *
	 * @param dataList the data list
	 * @param order    the order
	 * @return the sorted column data
	 */
	public List<String> getSortedColumnData(List<String> dataList, String order) {

		if (order.equalsIgnoreCase("ascending"))
			Collections.sort(dataList);
		else if (order.equalsIgnoreCase("descending"))
			Collections.sort(dataList, Collections.reverseOrder());

		return dataList;
	}

	/**
	 * Execute svn login vbs call.
	 *
	 * @param user     the user
	 * @param password the password
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException          Signals that an I/O exception has occurred.
	 */
	public void executeSVNLoginVBSCall(String user, String password) throws InterruptedException, IOException {
		String psExecPath = new File("./src/test/resources/PsExec.exe").toString();
		String remoteDomain = ResourceHandler.getPropertyValue("remote.domain");
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");

		String vbsFilePath = ResourceHandler.loadProperty("vbs.file.svn.login.path");
		String cmd = psExecPath + " -i " + nodeIP + " -u " + remoteDomain + "\\" + remoteUser + " -p " + remotePassword + " wscript.exe " + vbsFilePath + " " + user + " " + password + "";

		log.info("Started remote VBS call:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
		log.info("Completed remote VBS call:" + cmd);

	}

	/**
	 * Verify Popup is closed.
	 */
	public void verifyPopupIsClosed() {
		Assert.assertTrue(!(isDisplayed(GlobalPageElements.pop_PopUpElement)));
	}

	/**
	 * Verify Project is Available.
	 */
	public void verifyProjectIsAvailable() {
		Assert.assertTrue(getCount(ProjectsTab.css_NumberOfProjects) > 0);
	}

	/**
	 * Select Permission Checkbox based on Role and Click Save.
	 *
	 * @param permission the permission
	 * @param role       the role
	 */
	public void selectPermissionCheckboxAndSave(String permission, String role) {
		String classID = "";
		int counter = 0;
		String permissionID = "";

		List<WebElement> roleHeaders = findElements(ProjectsTab.css_PopRoleFormPermissionRolesHeaders);
		for(WebElement e: roleHeaders) {
			if(permission.equalsIgnoreCase(getElementText(e)))
				permissionID = e.getAttribute("class").split(" ")[0];
		}
		log.info("Permission ID for " + permission + " :"+permissionID);
		List<WebElement> roleRows = findElements(ProjectsTab.css_PopRoleFormPermissionRolesLabelRows);
		List<WebElement> roleNames = findElements(ProjectsTab.css_PopRoleFormPermissionRolesLabels);

		while (counter != roleRows.size()) {

			log.info("Expected Role while selecting permission checkbox: " + role);
			log.info("Actual Role while selecting permission checkbox: " + roleNames.get(counter).getAttribute("value"));

			if (roleNames.get(counter).getAttribute("value").equalsIgnoreCase(role)) {
				classID = roleRows.get(counter).getAttribute("class");
				break;
			}

			counter++;
		}

		By chk_RoleFormPermission = By.cssSelector("div[id='form-permission-table'] div[class*='fixedTable-body'] ul[class='" + classID + "'] li[title='" + permission + "']");

		clickElementAndWait(chk_RoleFormPermission);
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopRoleFormPermissionSave, By.cssSelector("div[class='loading'] div[class='loader_new']"));
		if (isDisplayedButtonWithText("OK"))
			clickButtonWithText("OK");

	}

	/**
	 * Deactivate file.
	 *
	 * @param fileName the file name
	 */
	public void deactivateFile(String fileName) {
		navigateTab(LandingPage.lnk_Files);
		searchFiles(fileName);
		log.info("Searching adoddle file(s) docRef/Title/Name for deactivation: " + fileName);
		if (isDisplayed(FilesTab.css_fileSearchFailureMsg)) {
			AutomationAssert.verifyTrue("Server Error while deactivating file", false);
			/* closeCurrentWindow(); */
		}
		else {
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, GlobalPageElements.pop_MoreOptionsPopUpElement);
			clickElementAndWaitForElement(FilesTab.lnk_PopFileOptionsDeactivateFiles, FilesTab.chk_PopDeactivateFilesEntireFile);
			clickElementAndWait(FilesTab.chk_PopDeactivateFilesEntireFile);
			clickElementAndWaitForElement(FilesTab.btn_PopDeactivateFilesDeactivate, FilesTab.lbl_PopDeactivatedFileSuccessMsg);
			clickElementAndWait(FilesTab.btn_PopDeactivatedFileSuccessOk);
		}

	}

	/**
	 * Swith to user.
	 *
	 * @param userName the user name
	 */
	public void switchToUser(String userName) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		while (isDisplayed(GlobalPageElements.lnk_HeaderNotificationClose))
			clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
		String currentURL = getCurrentWindowURL();

		if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_LIVE)) {
			if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleak"))
				currentURL = currentURL.contains("adoddleb") ? currentURL.replace("adoddleb", "adoddleak") : currentURL.replace("adoddled", "adoddleak");
			else if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleb"))
				currentURL = currentURL.contains("adoddleak") ? currentURL.replace("adoddleak", "adoddleb") : currentURL.replace("adoddled", "adoddleb");
			else if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.AUS_DC) && !currentURL.contains("adoddled"))
				currentURL = currentURL.contains("adoddleak") ? currentURL.replace("adoddleak", "adoddled") : currentURL.replace("adoddleb", "adoddled");
		}
		else if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_QA2)) {
			if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleqa2ak"))
				currentURL = currentURL.contains("adoddleqa2b") ? currentURL.replace("adoddleqa2b", "adoddleqa2ak") : currentURL;
			else if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleqa2b"))
				currentURL = currentURL.contains("adoddleqa2ak") ? currentURL.replace("adoddleqa2ak", "adoddleqa2b") : currentURL;
		}
		else if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_QA1)) {
			if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleqaak"))
				currentURL = currentURL.contains("adoddleqab") ? currentURL.replace("adoddleqab", "adoddleqaak") : currentURL;
			else if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleqab"))
				currentURL = currentURL.contains("adoddleqaak") ? currentURL.replace("adoddleqa2ak", "adoddleqab") : currentURL;
		}
		navigateURL(currentURL);
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_SwitchUser);
		clickElementAndWaitForElement(LandingPage.lnk_SwitchUser, GlobalPageElements.txt_PopSwitchProxyUserInput);
		waitForCompletePageLoad();
		sendKeys(GlobalPageElements.txt_PopSwitchProxyUserInput, userName);
		waitForCompletePageLoad();
		List<WebElement> switchUserOptions = findElements(GlobalPageElements.css_PopSwitchUserUserList);
		for (WebElement e : switchUserOptions) {
			if (e.getText().equalsIgnoreCase(userName)) {
				clickElementAndWait(e);
				waitUntilElementIsDisplayed(LandingPage.lnk_Dashboard);
				break;
			}
		}
		AutomationAssert.verifyTrue(getElementAttributeValue(LandingPage.ele_LoggedInUser, "title") + " does not contain " + (userName + AdoddleCommonStringPool.COMMA_STRING), getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(userName + AdoddleCommonStringPool.COMMA_STRING));
		timezoneID = getUserTimezoneID();
		log.info("Switched successfully to User :"+userName);
	}

	/**
	 * Select folder on popup window.
	 *
	 * @param folderName the folder name
	 */
	public void selectFolderOnPopupWindow(String folderName) {
		clickElementAndWait(By.xpath(".//*[@id='selectTargetFolderModal']//span[text()='" + folderName + "']"));
	}

	/**
	 * Click plan view icon.
	 */
	public void clickPlanViewIcon() {
		clickElement(FieldTab.img_SiteListingPlanView);
	}

	/**
	 * Click list view icon.
	 */
	public void clickListViewIcon() {
		clickElement(FieldTab.lnk_SiteListingListView);
	}

	/**
	 * Select project context menu option.
	 *
	 * @param menuOption the menu option
	 */
	public void selectProjectContextMenuOption(String menuOption) {

		contextClick(ProjectsTab.lnk_ProjectsListFirstProject);
		waitUntilElementIsDisplayed(ProjectsTab.opt_ProjectContextClickEdit);

		if (menuOption.equalsIgnoreCase("App Settings"))
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditAppSettings);

		else if (menuOption.equalsIgnoreCase("Apps"))
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditApps);

		else if (menuOption.contains("Purpose of Issue"))
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditPoi);

		else if (menuOption.equalsIgnoreCase("User Access"))
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditUserAccess);

		else if (menuOption.equalsIgnoreCase("Form Statuses"))
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditFormStatues);

		else if (menuOption.equalsIgnoreCase("File Statuses"))
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditFileStatues);

		waitForCompletePageLoad();
	}

	/**
	 * Click existing site location.
	 *
	 * @param title the title
	 */
	public void clickExistingSiteLocation(String title) {
		clickElementWithText(title);
	}

	/**
	 * Deactivate Existing Project With Prefix.
	 *
	 * @param projectText the project text
	 */
	public void deactivateProjectsWithText(String projectText) {
		try {
			navigateTab(LandingPage.lnk_Projects);
			int totalProjects = Integer.parseInt(getElementText(ProjectsTab.lbl_ProjectsTabProjectsCount).split("of")[1].replace(")", "").trim());
			log.info("Total Projects :" + totalProjects);
			int counter = 0;
			while (getCount(ProjectsTab.css_ProjectRowsCount) != totalProjects) {
				executeJScript("$('div.innerContainer').scrollTop(5000)");
				waitForCompletePageLoad();
				counter++;
				if (counter > 10)
					break;
			}
			searchProjects(projectText);
			List<String> projectTitles = new ArrayList<String>();
			List<WebElement> projects = findElements(ProjectsTab.css_NumberOfProjects);
			for (WebElement e : projects) {
				projectTitles.add(e.getText());

			}
			log.info("List of projects to deactivate: " + projectTitles.toString());
			for (String s : projectTitles)
				deactivateInheritanceProject(s);

			collectionDataMap.put("Deactivated Projects: ", projectTitles.toString());
		}
		catch (Throwable t) {
			log.error("failure in deactivating project");
		}

	}

	/**
	 * Deactivate Existing Project.
	 *
	 * @param projectTitle the project title
	 */
	public void deactivateProject(String projectTitle) {
		try {
			navigateTab(LandingPage.lnk_Projects);
			searchProjects(projectTitle);
			contextClickWithLink(projectTitle);
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditProject);
			waitForCompletePageLoad();
			waitUntilElementIsDisplayed(ProjectsTab.drp_PopEditProjectStatus);
			selectByIndex(ProjectsTab.drp_PopEditProjectStatus, 1);
			clickElementAndWait(ProjectsTab.btn_PopEditProjectEdit);
			waitUntilElementIsInvisible(ProjectsTab.btn_PopEditProjectEdit);
			waitForCompletePageLoad();
		}
		catch (Throwable t) {
			log.error("failure in deactivating project");
		}

	}

	/**
	 * Deactivate Inheritance Project.
	 *
	 * @param projectTitle the project title
	 */
	public void deactivateInheritanceProject(String projectTitle) {
		String inheritanceJQuery = "$('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').click()";
		String countJQuery = "return $('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').length";
		try {
			navigateTab(LandingPage.lnk_Projects);
			searchProjects(projectTitle);
			contextClick(ProjectsTab.lnk_ProjectsListFirstProject);
			mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditProject);
			waitForCompletePageLoad();
			waitUntilElementIsDisplayed(ProjectsTab.drp_PopEditProjectStatus);
			selectByIndex(ProjectsTab.drp_PopEditProjectStatus, 1);
			if(isDisplayed(ProjectsTab.lnk_PopEditProjectInheritanceOption))
				clickElementAndWait(ProjectsTab.lnk_PopEditProjectInheritanceOption);
			int counter = 0;
			while (!((JavascriptExecutor) getWebDriver()).executeScript(countJQuery).toString().equalsIgnoreCase("0")) {
				System.out.println("Number of Inheritance checks: " + ((JavascriptExecutor) getWebDriver()).executeScript(countJQuery));
				executeJScript(inheritanceJQuery);
				counter++;
				if (counter > 10)
					break;
			}
			/* breakAdoddleProjectInheritance(); */
			clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditProjectEdit, ProjectsTab.btn_PopEditProjectEdit);
			waitForCompletePageLoad();
		}
		catch (Throwable t) {
			log.error("Failure in Deactivating Project: " + projectTitle);
		}
	}

	/**
	 * Break adoddle project inheritance and save.
	 *
	 * @param project the project
	 */
	public void breakAdoddleProjectInheritanceAndSave(String project) {
		String inheritanceJQuery = "$('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').click()";
		String countJQuery = "return $('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').length";
		navigateTab(LandingPage.lnk_Projects);
		searchProjects(project);
		contextClick(ProjectsTab.lnk_ProjectsListFirstProject);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditProject);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectsTab.drp_PopEditProjectStatus);
		clickElementAndWait(ProjectsTab.lnk_PopEditProjectInheritanceOption);
		int counter = 0;
		while (!((JavascriptExecutor) getWebDriver()).executeScript(countJQuery).toString().equalsIgnoreCase("0")) {
			System.out.println("Number of Inheritance checks: " + ((JavascriptExecutor) getWebDriver()).executeScript(countJQuery));
			executeJScript(inheritanceJQuery);
			counter++;
			if (counter > 10)
				break;
		}
		 
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditProjectEdit, ProjectsTab.btn_PopEditProjectEdit);
		waitForCompletePageLoad();
	}
	
	/**
	 * Break adoddle project inheritance partially and save.
	 *
	 * @param project the project
	 */
	public void breakPartialAdoddleProjectInheritanceAndSave(String project) {
		String inheritanceJQuery = "$('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').click()";
		String countJQuery = "return $('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').length";
		navigateTab(LandingPage.lnk_Projects);
		searchProjects(project);
		contextClick(ProjectsTab.lnk_ProjectsListFirstProject);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditProject);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectsTab.drp_PopEditProjectStatus);
		clickElementAndWait(ProjectsTab.lnk_PopEditProjectInheritanceOption);
		int counter = 0;
		while (!((JavascriptExecutor) getWebDriver()).executeScript(countJQuery).toString().equalsIgnoreCase("0")) {
			System.out.println("Number of Inheritance checks: " + ((JavascriptExecutor) getWebDriver()).executeScript(countJQuery));
			executeJScript(inheritanceJQuery);
			counter++;
			if (counter > 10)
				break;
		}
		
		String checkBoxElements [] = {"All Purpose of Issues","All Document Status","All Form Statuses","All Distribution Group Names","All Attributes","Folders Names And Structure","All Document Numbering Schemes","All Configurable Attributes, Attributes Set","All Workflow Rules"};
		
		for (String checkBox : checkBoxElements) {
			if(!isSelected(By.xpath(".//div[@id='inheritanceTable']//tr//td[label[text()='"+ checkBox +"']]//following-sibling::td//input[contains(@id,'chkInheritObject')]"))){
				clickElementAndWait(By.xpath(".//div[@id='inheritanceTable']//tr//td[label[text()='"+ checkBox +"']]//following-sibling::td//input[contains(@id,'chkInheritObject')]"));
			}
			log.info("Checbox selected for " +checkBox);
		}
		
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditProjectEdit, ProjectsTab.btn_PopEditProjectEdit);
		waitForCompletePageLoad();
	}

	/**
	 * Break inheritance.
	 *
	 */
	public void breakAdoddleProjectInheritance() {
		boolean flag = false;
		List<WebElement> inheritChecboxList = findElements(ProjectsTab.chk_PopEditProjetInheritanceOptioncheckboxlist);
		log.info("Checbox List Size" + inheritChecboxList.size());
		for (WebElement i : inheritChecboxList) {
			if (i.isSelected() && i.isEnabled()) {
				flag = true;
				i.click();
			}
		}
		if (flag)
			breakAdoddleProjectInheritance();
	}

	/**
	 * Perform status change.
	 *
	 * @param changeStatusTo the change status to
	 */
	public void performStatusChange(String changeStatusTo) {
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionChangeStatus);
		selectByVisibleText(FilesTab.drp_PopFileActionChangeStatusTo, changeStatusTo);
		sendKeys(FilesTab.txt_PopFileActionReasonForStatusChange, javaUtils.getRandomString(10));
		clickElementAndWait(FilesTab.btn_PopFileActionChangeStatus);
		waitUntilElementIsInvisible(FilesTab.pop_FilesActionChangeStatus);
		waitForCompletePageLoad();
	}

	/**
	 * Perform status change.
	 *
	 * @param changeStatusTo      the change status to
	 * @param markPrivate         the mark private
	 * @param markactionsComplete the markactions complete
	 * @param updateStaticLinks   the update static links
	 * @return the string
	 */
	public String performStatusChangeWithOpts(String changeStatusTo, boolean markPrivate, boolean markactionsComplete, boolean updateStaticLinks) {
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionChangeStatus);
		String updatedStatus = null;
		String existingStatus = getElementText(FilesTab.lbl_PopFileActionChangeStatusExistingStatus);

		if (changeStatusTo != null) {
			selectByVisibleText(FilesTab.drp_PopFileActionChangeStatusTo, changeStatusTo);
			updatedStatus = changeStatusTo;
		}
		else {
			for (int index = 1; index <= 3; index++) {
				selectByIndex(FilesTab.drp_PopFileActionChangeStatusTo, index);
				if (! getSelectedDropdownLabel(FilesTab.drp_PopFileActionChangeStatusTo).trim().equalsIgnoreCase(existingStatus.trim())) {
					updatedStatus = getSelectedDropdownLabel(FilesTab.drp_PopFileActionChangeStatusTo);
					break;
				}
			}
		}

		sendKeys(FilesTab.txt_PopFileActionReasonForStatusChange, javaUtils.getRandomString(10));
		if (markPrivate)
			clickElementAndWait(By.cssSelector("form[id='changeStatusForm'] div[id='row_markPrivate'] input[name='markPrivate_single']"));
		if (markactionsComplete)
			clickElementAndWait(By.cssSelector("form[id='changeStatusForm'] div[id='row_markall_user'] input[name='markall_user_Action_completed']"));
		if (updateStaticLinks)
			clickElementAndWait(By.cssSelector("form[id='changeStatusForm'] div[id='row_update_link'] input[name='hasProjectLink']"));
		clickElementAndWait(FilesTab.btn_PopFileActionChangeStatus);
		waitUntilElementIsInvisible(FilesTab.pop_FilesActionChangeStatus);
		waitForCompletePageLoad();

		return updatedStatus;
	}

	/**
	 * Perform batch status change with opts.
	 *
	 * @param changeStatusTo      the change status to
	 * @param markPrivate         the mark private
	 * @param markactionsComplete the markactions complete
	 * @param updateStaticLinks   the update static links
	 * @param existingStatus      the existing status
	 * @return the string
	 */
	public String performBatchStatusChangeWithOpts(String changeStatusTo, boolean markPrivate, boolean markactionsComplete, boolean updateStaticLinks, String existingStatus) {
		String updatedStatus = null;
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionBatchChangeStatus);

		if (changeStatusTo != null) {
			selectByVisibleText(FilesTab.drp_PopBatchFileActionChangeStatusTo, changeStatusTo);
			updatedStatus = changeStatusTo;
		}
		else {
			for (int index = 1; index <= 3; index++) {
				selectByIndex(FilesTab.drp_PopBatchFileActionChangeStatusTo, index);
				if (! getSelectedDropdownLabel(FilesTab.drp_PopBatchFileActionChangeStatusTo).trim().equalsIgnoreCase(existingStatus.trim())) {
					updatedStatus = getSelectedDropdownLabel(FilesTab.drp_PopBatchFileActionChangeStatusTo);
					break;
				}
			}
		}

		sendKeys(FilesTab.txt_PopBatchFileActionReasonForStatusChange, javaUtils.getRandomString(10));
		if (markPrivate)
			clickElementAndWait(By.cssSelector("div[id='batchChangeStatusModal'][style*='margin-top'] div[class='markOwn'] input[id='is_clear_my_actions_all_user']"));
		if (markactionsComplete)
			clickElementAndWait(By.cssSelector("div[id='batchChangeStatusModal'][style*='margin-top'] div[class='markAll'] input[id='is_clear_all_actions_all_user']"));
		if (updateStaticLinks)
			clickElementAndWait(By.cssSelector("div[id='batchChangeStatusModal'][style*='margin-top'] div[class='updatelink'] input[id='hasProjectLink']"));
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopBatchFileActionChangeStatus, FilesTab.pop_FilesActionBatchChangeStatus);
		waitForCompletePageLoad();
		return updatedStatus;
	}

	/**
	 * Perform single document edit poi.
	 *
	 * @param cPoi                  the c poi
	 * @param uPoi                  the u poi
	 * @param updateStatusLinksFlag the update status links flag
	 * @return the string
	 */
	public String performSingleDocumentEditPOI(String cPoi, String uPoi, boolean updateStatusLinksFlag) {
		String returnVal = null;
		if (!(uPoi == null))
			selectByVisibleText(FilesTab.css_PopEditAttributesPOI, uPoi);
		else {
			for (int index = 1; index <= 3; index++) {
				selectByIndex(FilesTab.css_PopEditAttributesPOI, index);
				if (!getSelectedDropdownLabel(FilesTab.css_PopEditAttributesPOI).trim().equalsIgnoreCase(cPoi.trim())) {
					returnVal = getSelectedDropdownLabel(FilesTab.css_PopEditAttributesPOI);
					break;
				}
			}
			if (!isSelected(FilesTab.chk_PopEditAttributesUpdateStatusLinks) && (updateStatusLinksFlag))
				clickElementAndWait(FilesTab.chk_PopEditAttributesUpdateStatusLinks);
			clickElementAndWaitForElement(FilesTab.btn_PopEditAttributesAssignAttributes, FilesTab.btn_PopupConfirmUIContinue);
			clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopupConfirmUIContinue, GlobalPageElements.pop_PopUpElement);
		}
		return returnVal;
	}

	/**
	 * Perform single document edit poi.
	 *
	 * @param cPoi                  the c poi
	 * @param uPoi                  the u poi
	 * @param updateStatusLinksFlag the update status links flag
	 * @return the string
	 */
	public String performMultiDocumentEditPOI(String cPoi, String uPoi, boolean updateStatusLinksFlag) {
		String returnVal = null;
		clickElementAndWaitForElement(FilesTab.chk_PopEditAttributesHeaderBulkApply, FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue);
		selectByVisibleText(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, strUtils.toTitleCase(AdoddleCommonStringPool.PLEASE_SELECT));
		clickElementAndWait(FilesTab.img_PopEditAttributesHeaderPurposeOfIssueCaret);
		waitUntilDropdownContainsValue(findElements(FilesTab.css_PopEditAttributesPOI).get(findElements(FilesTab.css_PopEditAttributesPOI).size() - 1), strUtils.toTitleCase(AdoddleCommonStringPool.PLEASE_SELECT));
		if (!(uPoi == null)) {
			selectByVisibleText(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, uPoi);
		}
		else {
			for (int index = 1; index <= 3; index++) {
				selectByIndex(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, index);
				if (!getSelectedDropdownLabel(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue).trim().equalsIgnoreCase(cPoi.trim())) {
					returnVal = getSelectedDropdownLabel(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue);
					break;
				}
			}
		}
		clickElementAndWait(FilesTab.img_PopEditAttributesHeaderPurposeOfIssueCaret);
		if (!isSelected(FilesTab.chk_PopEditAttributesUpdateStatusLinks) && (updateStatusLinksFlag))
			clickElementAndWait(FilesTab.chk_PopEditAttributesUpdateStatusLinks);
		else if (!updateStatusLinksFlag && isSelected(FilesTab.chk_PopEditAttributesUpdateStatusLinks))
			clickElementAndWait(FilesTab.chk_PopEditAttributesUpdateStatusLinks);
		clickElementAndWaitForElement(FilesTab.btn_PopEditAttributesAssignAttributes, FilesTab.btn_PopupConfirmUIContinue);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopupConfirmUIContinue, GlobalPageElements.pop_PopUpElement);
		return returnVal;
	}

	/**
	 * Creates Global Test project.
	 *
	 * @param projectTitle the project title
	 */
	public void createGlobalTestProject(String projectTitle) {
		navigateTab(LandingPage.lnk_Projects);
		clickElementAndWait(ProjectsTab.lnk_ProjectsTabLHAddProject);
		globalTestProject = projectTitle + epoch;
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, projectTitle + epoch);

		if (dataCenter.equalsIgnoreCase("UK"))
			selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, "United Kingdom (EU01)");
		else if (dataCenter.equalsIgnoreCase("US"))
			selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, "North America (US01)");
		else if (dataCenter.equalsIgnoreCase("AUS"))
			selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, "Australia (AU01)");

		waitForCompletePageLoad();

		if (getCount(ProjectsTab.txt_PopCreateProjectClientInput) > 0) {
			getWebDriver().findElement(ProjectsTab.txt_PopCreateProjectClientInput).sendKeys("Automation Testing");
			waitForCompletePageLoad();
			clickElementAndWait(ProjectsTab.sel_CloneProjectClientNameFirstResult);
		}

		clickElementAndWait(ProjectsTab.btn_PopCreateProjectSave);
		searchProjects(globalTestProject);
		verifyElementText(ProjectsTab.lnk_ProjectsListFirstProject, globalTestProject);
	}

	/**
	 * Comapres Unordered Maps.
	 *
	 * @param mapA MapB
	 * @param mapB the map b
	 * @return true, if successful
	 */
	public boolean compareUnorderedMaps(Map<String, String> mapA, Map<String, String> mapB) {

		log.info("comparing maps");

		try {
			for (String x : mapB.keySet()) {
				if (!mapA.get(x).equalsIgnoreCase(mapB.get(x))) {
					log.info("key: " + x);
					log.info("Map A value: " + mapA.get(x));
					log.info("Map B value: " + mapB.get(x));
					return false;
				}
			}

			for (String y : mapA.keySet()) {
				if (!mapB.containsKey(y)) {
					log.error("map B does not contain key of map A");
					return false;
				}
			}
		}
		catch (NullPointerException np) {
			log.info("catch nullpointer exception");
			return false;
		}
		return true;
	}

	/**
	 * Re login into adoddle.
	 */
	public void reLoginIntoAdoddle() {
		try {
			logOut();
		}
		catch (Throwable t) {
			log.error("error while logging out, probably already logged out");
		}

		login(System.getProperty("primary.username"), System.getProperty("primary.password"));
		waitUntilElementIsDisplayed(LandingPage.lnk_Dashboard);
	}

	/**
	 * Login into adoddle with user.
	 *
	 * @param user     the user
	 * @param password the password
	 */
	public void loginIntoAdoddleWithUser(String user, String password) {
		try {
			tearDown();
			TestDriverFactory.getInstance().setUp(ResourceHandler.getPropertyValue("browser"));
		}
		catch (Throwable t) {
			log.error("error while logging out, probably already logged out");
		}

		if (user.equalsIgnoreCase(ResourceHandler.loadProperty("secondary.uk.username")))
			login(user, ResourceHandler.loadProperty("secondary.uk.password"));
		else if (user.equalsIgnoreCase(ResourceHandler.loadProperty("secondary.us.username")))
			login(user, ResourceHandler.loadProperty("secondary.us.password"));
		else if (user.equalsIgnoreCase(ResourceHandler.loadProperty("secondary.aus.username")))
			login(user, ResourceHandler.loadProperty("secondary.aus.password"));
		else
			login(user, password);
		waitUntilElementIsDisplayed(LandingPage.lnk_Dashboard);
	}

	/**
	 * Login to svn.
	 *
	 * @param user     the user
	 * @param password the password
	 * @throws InterruptedException the interrupted exception
	 */
	public void loginToSVN(String user, String password) throws InterruptedException {
		String authenticatedURL = "\"" + ResourceHandler.loadProperty("asite.svn.url").replace("svn.asite.com", user + ":" + password + "@svn.asite.com") + "\"";
		log.info("Authentication URL# " + authenticatedURL);
		String authAppURLScript = "location.assign(" + authenticatedURL + ")";
		waitUtils.waitInterval(2);
		executeJScript(authAppURLScript);
		waitUtils.waitInterval(2);

	}

	/*
	 * Login to web mail light version.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.

	public void loginToWebMailLightVersion() {
		navigateURL(ResourceHandler.getPropertyValue("asite.web.mail.url"));
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("ie"))
			overrideSecurityLink();
		clickElement(GlobalPageElements.chk_WebMailLighVersion);
		sendKeys(GlobalPageElements.txt_WebMailUserNameInput, ResourceHandler.getPropertyValue("asite.web.mail.user"));
		sendKeys(GlobalPageElements.txt_WebMailPasswordInput, ResourceHandler.getPropertyValue("asite.web.mail.password"));
		clickElementAndWaitForElement(GlobalPageElements.btn_WebMailSignIn, GlobalPageElements.lnk_WebMailLVNew);
	} */

	/*
	 * Login to web mail.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	public void loginToWebMail() throws IOException {
		navigateURL(ResourceHandler.getPropertyValue("asite.web.mail.url"));
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("ie"))
			overrideSecurityLink();
		sendKeys(GlobalPageElements.txt_WebMailUserNameInput, ResourceHandler.getPropertyValue("asite.web.mail.user"));
		sendKeys(GlobalPageElements.txt_WebMailPasswordInput, ResourceHandler.getPropertyValue("asite.web.mail.password"));
		clickElementAndWaitForElement(GlobalPageElements.btn_WebMailSignIn, GlobalPageElements.lnk_WebMailExpandInbox);
	}	 */


	/*
	 * Login to web mail.
	 *
	 * @param email    the email
	 * @param password the password
	 * @throws IOException Signals that an I/O exception has occurred.

	public void loginToWebMailLightVersionWithCreds(String email, String password) throws IOException {
		navigateURL(ResourceHandler.getPropertyValue("asite.web.mail.url"));
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("ie"))
			overrideSecurityLink();
		clickElement(GlobalPageElements.chk_WebMailLighVersion);
		sendKeys(GlobalPageElements.txt_WebMailUserNameInput, email);
		sendKeys(GlobalPageElements.txt_WebMailPasswordInput, password);
		clickElementAndWaitForElement(GlobalPageElements.btn_WebMailSignIn, GlobalPageElements.lnk_WebMailLVNew);
	} */

	/*
	 * Login to web mail.
	 *
	 * @param email    the email
	 * @param password the password
	 * @throws IOException Signals that an I/O exception has occurred.

	public void loginToWebMailWithCreds(String email, String password) throws IOException {
		navigateURL(ResourceHandler.getPropertyValue("asite.web.mail.url"));
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("ie"))
			overrideSecurityLink();
		sendKeys(GlobalPageElements.txt_WebMailUserNameInput, email);
		sendKeys(GlobalPageElements.txt_WebMailPasswordInput, password);
		clickElementAndWaitForElement(GlobalPageElements.btn_WebMailSignIn, GlobalPageElements.lnk_WebMailExpandInbox);
	}*/

	/**
	 * Sign in to out look 365 web mail light.
	 */
	public void signInToOutLook365WebMailLight() {
		signInToOutLook365WebMailLight(ResourceHandler.getPropertyValue("asite.web.mail.user"), ResourceHandler.getPropertyValue("asite.web.mail.password"));
	}

	/**
	 * Sign in to out look 365 web mail light.
	 *
	 * @param user     the user
	 * @param password the password
	 */
	public void signInToOutLook365WebMailLight(String user, String password) {
		navigateURL(ResourceHandler.getPropertyValue("asite.outlook.365.web.url"));
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("ie"))
			overrideSecurityLink();
		clickElementAndWaitForElement(Outlook365Locators.lnk_Office365SignInLink, Outlook365Locators.txt_Office365EmailInput);
		sendKeys(Outlook365Locators.txt_Office365EmailInput, user);
		clickElementAndWaitForElement(Outlook365Locators.btn_Office365NextButton, Outlook365Locators.txt_Office365PasswordInput);
		sendKeys(Outlook365Locators.txt_Office365PasswordInput, password);
		clickElementAndWaitForElement(Outlook365Locators.btn_Office365SignInButton, Outlook365Locators.btn_Office365StaySingedInNo);
		clickElementAndWaitForElement(Outlook365Locators.btn_Office365StaySingedInNo, Outlook365Locators.lnk_Office365OutLook);
		navigateURL(resourceUtils.getOutLookNavigateEmailURL());
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_WebMailLVNew);
		waitForCompletePageLoad();
	}

	/**
	 * Send email via outlook 365 light.
	 *
	 * @param recipient  the recipient
	 * @param subject    the subject
	 * @param attachment the attachment
	 */
	protected void sendEmailViaOutlook365Light(String recipient, String subject, String...attachment) {
		signInToOutLook365WebMailLight();
		clickElementAndWaitForElement(GlobalPageElements.lnk_WebMailLVNew, GlobalPageElements.txt_WebMailLVNewToField);
		sendKeys(GlobalPageElements.txt_WebMailLVNewToField, recipient);
		sendKeys(GlobalPageElements.txt_WebMailLVNewSubject, subject);
		sendKeys(GlobalPageElements.txt_WebMailLVContent, javaUtils.getRandomString(20));
		clickElementAndWaitForElement(GlobalPageElements.img_WebMailLVAttachFileLink, GlobalPageElements.ele_WebMailLVAttachFileBrowse);
		findElement(GlobalPageElements.ele_WebMailLVAttachFileBrowse).sendKeys(attachment);
		clickElementAndWaitForElement(GlobalPageElements.btn_WebMailLVAttachButton, GlobalPageElements.lnk_WebMailLVAttachMentDone);
		clickElementAndWaitForElement(GlobalPageElements.lnk_WebMailLVAttachMentDone, GlobalPageElements.lnk_WebMailLVSend);
		clickElementAndWaitForElement(GlobalPageElements.lnk_WebMailLVSend, GlobalPageElements.lnk_WebMailLVSignout);
		clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_WebMailLVSignout, GlobalPageElements.lnk_WebMailLVSignout);
	}

	/**
	 * Sign In to OutLook 365 Web Mail.
	 */
	public void signInToOutLook365WebMail() {
		try {
			signInToOutLook365WebMail(ResourceHandler.getPropertyValue("asite.web.mail.user"), ResourceHandler.getPropertyValue("asite.web.mail.password"));
		}
		catch(IOException e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Sign In to OutLook 365 Web Mail.
	 *
	 * @param email    the email
	 * @param password the password
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void signInToOutLook365WebMail(String email, String password) throws IOException {
		navigateURL(ResourceHandler.getPropertyValue("asite.outlook.365.web.url"));
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("ie"))
			overrideSecurityLink();
		clickElementAndWaitForElement(Outlook365Locators.lnk_Office365SignInLink, Outlook365Locators.txt_Office365EmailInput);
		sendKeys(Outlook365Locators.txt_Office365EmailInput, email);
		clickElementAndWaitForElement(Outlook365Locators.btn_Office365NextButton, Outlook365Locators.txt_Office365PasswordInput);
		sendKeys(Outlook365Locators.txt_Office365PasswordInput, password);
		clickElementAndWaitForElement(Outlook365Locators.btn_Office365SignInButton, Outlook365Locators.btn_Office365StaySingedInNo);
		clickElementAndWaitForElement(Outlook365Locators.btn_Office365StaySingedInNo, Outlook365Locators.lnk_Office365OutLook);
		/*String outlookParentWindow = clickElementAndSwitchWindow(Outlook365Locators.lnk_Office365OutLook);*/
		navigateURL(resourceUtils.getOutLookNavigateEmailURL());
		waitUntilElementIsDisplayed(Outlook365Locators.btn_Office365UserProfile);
		waitForCompletePageLoad();
	}

	/**
	 * Sign Out From OutLook 365 Web Mail.
	 */
	public void signOutFromOutLook365WebMail() {
		clickElementAndWaitForElement(Outlook365Locators.btn_Office365UserProfile, Outlook365Locators.lnk_Office365SignOutLink);
		clickElementAndWaitForElement(Outlook365Locators.lnk_Office365SignOutLink, Outlook365Locators.lbl_Office365SingedOutLabel);
		waitUntilElementContainsText(Outlook365Locators.lbl_Office365SingedOutLabel, AdoddleCommonStringPool.WEBMAIL_SIGN_OUT_MSG);
		waitForCompletePageLoad();
	}

	/**
	 * Override security link.
	 */
	public void overrideSecurityLink() {
		if (getWebDriver().getTitle().contains("Certificate Error"))
			executeJScript(AdoddleCommonJQueries.overrideCertificationErrorJQuery);

		waitForCompletePageLoad();
	}

	/**
	 * Filter form with status.
	 *
	 * @param status the status
	 */
	public void filterFormWithStatus(String status) {
		clickElementAndWaitForElement(ProjectFormsTab.btn_FormListingStatusFilter, ProjectFormsTab.txt_FormListingStatusFilterSearch);
		sendKeys(ProjectFormsTab.txt_FormListingStatusFilterSearch, status);
		waitForCompletePageLoad();
		selectFormFilterFromDrodown(status, true);
	}

	/**
	 * Select filter from drodown.
	 *
	 * @param filterTitle    the filter title
	 * @param exactMatchFlag the exact match flag
	 */
	public void selectFormFilterFromDrodown(String filterTitle, boolean exactMatchFlag) {
		List<WebElement> filteredLabels = findElements(GlobalPageElements.css_GlobalListingCreateFilterFilteredLabels);
		List<WebElement> filteredCheckBoxes = findElements(GlobalPageElements.css_GlobalListingCreateFilterFilteredLabels);

		index = javaUtils.resetIndex(index, 0);

		for (WebElement e : filteredLabels) {
			log.info("Chebox while filtering form : " + e.getText());
			if (exactMatchFlag) {
				if (e.getText().equalsIgnoreCase(filterTitle)) {
					filteredCheckBoxes.get(index).click();
					break;
				}
			}
			else {
				if (e.getText().contains(filterTitle)) {
					filteredCheckBoxes.get(index).click();
					break;
				}
			}
			index++;
		}
		clickElementAndWait(ProjectFormsTab.txt_FormListingFormSearchInput);
	}

	/**
	 * Filter form with incomplete action to current user.
	 *
	 * @param actionTitle the action title
	 */
	public void filterFormWithIncompleteActionToCurrentUser(String actionTitle) {

		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.FILTER_ACTION_NAME);
		waitForCompletePageLoad();
		selectFormFilterFromDrodown(AdoddleCommonStringPool.FILTER_ACTION_NAME, true);
		clickElementAndWaitForElement(ProjectFormsTab.drp_FormListingActionNameFilterPanelLabel, ProjectFormsTab.txt_FormListingActionNameFilterInput);
		sendKeys(ProjectFormsTab.txt_FormListingActionNameFilterInput, actionTitle);
		selectFormFilterFromDrodown(actionTitle, true);

		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.FILTER_ACTION_STATUS);
		waitForCompletePageLoad();
		selectFormFilterFromDrodown(AdoddleCommonStringPool.FILTER_ACTION_STATUS, true);
		clickElementAndWaitForElement(ProjectFormsTab.drp_FormListingActionStatusFilterPanelLabel, ProjectFormsTab.txt_FormListingActionStatusFilterInput);
		sendKeys(ProjectFormsTab.txt_FormListingActionStatusFilterInput, "Incomplete");
		selectFormFilterFromDrodown("Incomplete", true);

		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.FILTER_RECIPIENT_NAME);
		waitForCompletePageLoad();
		selectFormFilterFromDrodown(AdoddleCommonStringPool.FILTER_RECIPIENT_NAME, true);
		clickElementAndWaitForElement(ProjectFormsTab.drp_FormListingRecipientNameFilterPanelLabel, ProjectFormsTab.txt_FormListingRecipientNameFilterInput);
		String userTitle = getLoggedInUserTitle();
		sendKeys(ProjectFormsTab.txt_FormListingRecipientNameFilterInput, getLoggedInUserTitle());
		selectFormFilterFromDrodown(userTitle, false);

	}

	/**
	 * Gets the logged in user title.
	 *
	 * @return the logged in user title
	 */
	public String getLoggedInUserTitle() {
		log.info("Logged in user: " + getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").split("\\R")[1]);
		return getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").split("\\R")[1].split(",")[0].trim();
	}

	/**
	 * Upload documents.
	 *
	 * @param fileList the file list
	 * @return the list
	 */
	public List<String> simpleUploadDocuments(List<String> fileList) {
		List<String> globalUploadFileList = new ArrayList<String>();
		if (!(fileList == null)) {
			globalUploadFileList = fileList;
		}
		log.info("Upload Document(s) List:" + globalUploadFileList.toString());
		clickElementAndWaitForElement(FilesTab.btn_AddFiles, FilesTab.pop_UploadFile);
		if (globalUploadFileList.size() == 1)
			uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, globalUploadFileList);
		else
			uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, globalUploadFileList);
		waitUntilElementCountToBe(By.cssSelector("div[id='tblbodyAttributesSection'] tbody[id='tblbodySelectedFiles'] tr"), !(fileList == null) ? fileList.size():0);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopUploadFileUpload, GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		log.info("Upload Process Completed for Number of Documents :" + globalUploadFileList.size() + "\tTHID :" + Thread.currentThread().getId());
		log.info("Document(s) Upload list: " + globalUploadFileList);
		return globalUploadFileList;
	}

	/**
	 * Upload documents.
	 *
	 * @param fileList                the file list
	 * @param count                   the count
	 * @param docRefStrings           the doc ref strings
	 * @param docTitleStrings         the doc title strings
	 * @param privateFlag             the private flag
	 * @param revision                the revision
	 * @param poi                     the poi
	 * @param status                  the status
	 * @param distributionUserStrings the distribution user strings
	 * @param usersActionStrings      the users action strings
	 * @return the list
	 * @throws InterruptedException the interrupted exception
	 */
	public List<String> uploadDocuments(List<String> fileList, int count, List<String> docRefStrings, List<String> docTitleStrings, boolean privateFlag, int revision, List<String> poi, List<String> status, List<String> distributionUserStrings, List<String> usersActionStrings) throws InterruptedException {

		boolean docRefFlag = false, docTitleFlag = false, distUsersFlag = false;

		if (docRefStrings != null)
			docRefFlag = docRefStrings.size() != 0;
		if (docTitleStrings != null)
			docTitleFlag = docTitleStrings.size() != 0;
		if (distributionUserStrings != null)
			distUsersFlag = distributionUserStrings.size() != 0;

		List<String> globalUploadFileList = new ArrayList<String>();
		List<WebElement> revisionList, poiList, docRefList, statusList, userActionsList, privateCheckboxes;
		List<WebElement> docTitleList = new ArrayList<WebElement>();

		sysUtils.authenticateRemoteMachine(nodeIP);

		if (!(fileList == null)) {
			globalUploadFileList = fileList;
		}
		else {
			for (int index = 0; index < count; index++) {
				globalUploadFileList.add(sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP));
				waitUtils.waitInterval(1);
			}
		}
		log.info("Upload Document(s) List:" + globalUploadFileList.toString());
		clickElementAndWaitForElement(FilesTab.btn_AddFiles, FilesTab.pop_UploadFile);
		if (globalUploadFileList.size() == 1)
			uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, globalUploadFileList);
		else
			uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, globalUploadFileList);

		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(getCount(FilesTab.css_PopUploadFilesDocRefsInput) == globalUploadFileList.size());

		if (!docTitleFlag)
			clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		else
			docTitleList = findElements(FilesTab.css_PopUploadFilesDocTitlesInput);

		revisionList = findElements(FilesTab.css_PopUploadFilesRevisionsInput);
		poiList = findElements(FilesTab.css_PopUploadFilesPurposeOfIssuesDropDowns);
		statusList = findElements(FilesTab.css_PopUploadFilesStatusDropDowns);
		docRefList = findElements(FilesTab.css_PopUploadFilesDocRefsInput);

		index = javaUtils.resetIndex(index, 0);

		for (WebElement e : revisionList) {

			e.sendKeys(String.valueOf(revision));

			if (poi == null)
				selectByIndex(poiList.get(index), 1);
			else if (poi.size() == 1)
				selectByVisibleText(poiList.get(index), poi.get(0));
			else if (poi.size() > 1)
				selectByVisibleText(poiList.get(index), poi.get(index));

			if (status == null)
				selectByIndex(statusList.get(index), 1);
			else if (status.size() == 1)
				selectByVisibleText(statusList.get(index), status.get(0));
			else if (status.size() > 1)
				selectByVisibleText(statusList.get(index), status.get(index));

			if (docRefFlag) {
				docRefList.get(index).clear();
				docRefList.get(index).sendKeys(docRefStrings.get(index));
			}

			if (docTitleFlag) {
				docTitleList.get(index).clear();
				if (docTitleStrings.size() != 1)
					docTitleList.get(index).sendKeys(docTitleStrings.get(index));
				else
					docTitleList.get(index).sendKeys(docTitleStrings.get(0));
			}

			index++;
		}

		if (privateFlag) {
			privateCheckboxes = findElements(FilesTab.css_PopUploadFilesPrivateCheckboxes);
			for (WebElement e : privateCheckboxes)
				e.click();
		}

		if (distUsersFlag) {

			index = javaUtils.resetIndex(index, 0);
			clickElementAndWait(FilesTab.btn_PopUploadFileDistributeFiles);
			for (String user : distributionUserStrings) {
				sendKeys(FilesTab.txt_PopUploadFileDistributeTo, user);
				sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
				if (!isDisplayed(FilesTab.lnk_PopDatePickerCurrentDate) && !isDisplayed(FilesTab.lnk_PopDistributeUserSearchExpandIcon))
					clickElementAndWait(FilesTab.btn_PopDistributeUserToggleButton);

				if (usersActionStrings == null && !isDisplayed(FilesTab.lnk_PopDistributeUserSearchExpandIcon)) {
					selectByIndex(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, 1);
					// clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate);
					selectDateFromCalendar();
				}

				else {

					if (!isDisplayed(FilesTab.lnk_PopDistributeUserSearchExpandIcon)) {
						waitUntilListOfElementIsDisplayed(ProjectsTab.css_DistributionGroupContextMenuActionsDropdownOptions);
						userActionsList = findElements(ProjectsTab.css_DistributionGroupContextMenuActionsDropdownOptions);
						Assert.assertTrue("userList should equal to actionList", distributionUserStrings.size() == usersActionStrings.size());
						for (WebElement action : userActionsList) {

							if (action.getText().contains(usersActionStrings.get(index))) {
								selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, usersActionStrings.get(index));
								// clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate);
								selectDateFromCalendar();
								break;
							}
						}
						userActionsList.clear();
					}

				}
				index++;
			}
			sendKeys(FilesTab.txt_PopUploadFileDistributeSubject, javaUtils.getRandomString(20));
		}

		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		log.info("Upload Process Started for Number of Documents :" + globalUploadFileList.size() + "\tTHID :" + Thread.currentThread().getId());
		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		log.info("Upload Process Completed for Number of Documents :" + globalUploadFileList.size() + "\tTHID :" + Thread.currentThread().getId());
		log.info("Document(s) Upload list: " + globalUploadFileList);
		return globalUploadFileList;
	}

	/**
	 * Upload documents with custom attributes.
	 *
	 * @param fileList            the file list
	 * @param count               the count
	 * @param docRefStrings       the doc ref strings
	 * @param docTitleStrings     the doc title strings
	 * @param privateFlag         the private flag
	 * @param revision            the revision
	 * @param pois                the pois
	 * @param statuses            the statuses
	 * @param radio               the radio
	 * @param states              the states
	 * @param cities              the cities
	 * @param attachExternalFiles the attach external files
	 * @param decimal             the decimal
	 * @param lettersNumbers      the letters numbers
	 * @param multiSelection      the multi selection
	 * @param distributionUsers   the distribution users
	 * @return the list
	 * @throws InterruptedException the interrupted exception
	 */
	public List<String> uploadDocumentsWithCustomAttributes(List<String> fileList, int count, List<String> docRefStrings, List<String> docTitleStrings, boolean privateFlag, int revision, List<String> pois, List<String> statuses, boolean radio, List<String> states, List<String> cities, List<String> attachExternalFiles, float decimal, String lettersNumbers, List<String> multiSelection, List<String> distributionUsers) throws InterruptedException {

		boolean docRefFlag = false, docTitleFlag = false;
		if (docRefStrings != null)
			docRefFlag = docRefStrings.size() != 0;

		if (docTitleStrings != null)
			docTitleFlag = docTitleStrings.size() != 0;

		List<String> globalUploadFileList = new ArrayList<String>();
		List<WebElement> revisionList, poiList, docRefList, statusList, privateCheckboxes, docTitleTextBoxes;
		List<WebElement> integerBoxElements, emailBoxElements, lettersElements, radioNoElements, radioYesElements;
		List<WebElement> coordinatesXList, coordinatesXXList, coordinatesYYList, coordinatesXXXList, coordinatesYYYList, coordinatesZZZList, statesDropdowns, citiesDropdowns, attachExternalFilesElements;
		List<WebElement> decimalTexBoxes, lettersNumbersTexBoxes, multiSelectOptions, datePickerList;

		sysUtils.authenticateRemoteMachine(nodeIP);

		if (!(fileList == null)) {
			globalUploadFileList = fileList;
		}
		else {
			for (int index = 0; index < count; index++) {
				globalUploadFileList.add(sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP));
				waitUtils.waitInterval(1);
			}
		}

		log.info("Upload Document(s) list with custom attributes: " + globalUploadFileList.toString());
		clickElementAndWaitForElement(FilesTab.btn_AddFiles, FilesTab.pop_UploadFile);
		if (globalUploadFileList.size() == 1)
			uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, globalUploadFileList);
		else
			uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, globalUploadFileList);

		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		revisionList = findElements(FilesTab.css_PopUploadFilesRevisionsInput);
		poiList = findElements(FilesTab.css_PopUploadFilesPurposeOfIssuesDropDowns);
		statusList = findElements(FilesTab.css_PopUploadFilesStatusDropDowns);
		docRefList = findElements(FilesTab.css_PopUploadFilesDocRefsInput);
		docTitleTextBoxes = findElements(FilesTab.css_PopUploadFilesDocTitlesInput);

		index = javaUtils.resetIndex(index, 0);
		for (WebElement e : revisionList) {

			/* e.sendKeys(); */
			sendKeys(e, String.valueOf(revision));

			if (pois == null)
				selectByIndex(poiList.get(index), 1);
			else if (pois.size() == 1)
				selectByVisibleText(poiList.get(index), pois.get(0));
			else if (pois.size() > 1)
				selectByVisibleText(poiList.get(index), pois.get(index));

			if (statuses == null)
				selectByIndex(statusList.get(index), 1);
			else if (statuses.size() == 1)
				selectByVisibleText(statusList.get(index), statuses.get(0));
			else if (statuses.size() > 1)
				selectByVisibleText(statusList.get(index), statuses.get(index));

			if (docRefFlag) {
				docRefList.get(index).clear();
				docRefList.get(index).sendKeys(docRefStrings.get(index));
			}

			if (docTitleFlag) {
				docTitleTextBoxes.get(index).clear();
				docTitleTextBoxes.get(index).sendKeys(docTitleStrings.get(index));
			}
			index++;

		}

		if (privateFlag) {
			privateCheckboxes = findElements(FilesTab.css_PopUploadFilesPrivateCheckboxes);
			for (WebElement e : privateCheckboxes)
				e.click();
		}

		integerBoxElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesIntegerTextBoxes);
		for (WebElement e : integerBoxElements) {
			if (e.isEnabled())
				sendKeys(e, JavaUtils.getRandomNumber(3));
		}

		emailBoxElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesEmailTextBoxes);
		for (WebElement e : emailBoxElements) {
			e.clear();
			waitForCompletePageLoad();
			e.sendKeys(javaUtils.getRandomString(4) + AdoddleCommonStringPool.AT_STRING + javaUtils.getRandomString(4) + AdoddleCommonStringPool.DOT_STRING + javaUtils.getRandomString(3));
		}

		lettersElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesLetters);
		for (WebElement e : lettersElements)
			sendKeys(e, javaUtils.getRandomString(4));

		if (radio) {
			radioYesElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesRadioYes);
			for (WebElement e : radioYesElements)
				e.click();
		}
		else {
			radioNoElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesRadioNo);
			for (WebElement e : radioNoElements)
				e.click();
		}

		coordinatesXList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesX);
		for (WebElement e : coordinatesXList)
			sendKeys(e, JavaUtils.getRandomNumber(1));

		coordinatesXXXList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesXXX);
		for (WebElement e : coordinatesXXXList)
			sendKeys(e, JavaUtils.getRandomNumber(3));

		coordinatesYYYList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesYYY);
		for (WebElement e : coordinatesYYYList)
			sendKeys(e, JavaUtils.getRandomNumber(3));

		coordinatesZZZList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesZZZ);
		for (WebElement e : coordinatesZZZList)
			sendKeys(e, JavaUtils.getRandomNumber(3));

		index = javaUtils.resetIndex(index, 0);
		statesDropdowns = findElements(FilesTab.css_PopUploadCustomAttributesFilesStates);
		for (WebElement e : statesDropdowns) {
			if (states == null)
				selectByIndex(e, 1);
			else if (states.size() == 1)
				selectByVisibleText(statesDropdowns.get(index), states.get(0));
			else if (states.size() > 1)
				selectByVisibleText(statesDropdowns.get(index), states.get(index));
			index++;
		}

		index = javaUtils.resetIndex(index, 0);
		citiesDropdowns = findElements(FilesTab.css_PopUploadCustomAttributesFilesCities);
		for (WebElement e : citiesDropdowns) {
			if (cities == null)
				selectByIndex(e, 1);
			else if (cities.size() == 1)
				selectByVisibleText(citiesDropdowns.get(index), cities.get(0));
			else if (cities.size() > 1)
				selectByVisibleText(citiesDropdowns.get(index), cities.get(index));
			index++;
		}

		index = javaUtils.resetIndex(index, 0);
		if (attachExternalFiles != null) {
			attachExternalFilesElements = findElements(FilesTab.css_PopUploadCustomAttributesFilesAttachExternalFiles);
			for (WebElement wE : attachExternalFilesElements) {
				wE.sendKeys(attachExternalFiles.get(index));
				waitUtils.waitInterval(1);
				index++;
			}
		}

		decimalTexBoxes = findElements(FilesTab.css_PopUploadCustomAttributesFilesDecimalBoxes);
		for (WebElement e : decimalTexBoxes) {
			sendKeys(e, JavaUtils.getRandomNumber(3) + AdoddleCommonStringPool.DOT_STRING + JavaUtils.getRandomNumber(2));
		}

		lettersNumbersTexBoxes = findElements(FilesTab.css_PopUploadCustomAttributesFilesLettersNumbersBoxes);
		for (WebElement e : lettersNumbersTexBoxes) {
			if (e.isEnabled())
				sendKeys(e, javaUtils.getRandomString(4) + JavaUtils.getRandomNumber(3));
		}

		if (multiSelection != null) {
			/* This section implementation is pending */
			multiSelectOptions = findElements(FilesTab.css_PopUploadCustomAttributesFilesMultiSelections);
			for (WebElement e : multiSelectOptions) {
				e.click();
				for (String opt : multiSelection)
					e.findElement(By.cssSelector("option[value='" + opt + "']")).click();
				sendKeys(e, Keys.ENTER);
				waitUntilElementIsInvisible(FilesTab.sel_PopUploadCustomAttributesFilesOptionBox);
			}
		}

		coordinatesXXList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesXX);
		for (WebElement e : coordinatesXXList)
			sendKeys(e, JavaUtils.getRandomNumber(2));

		coordinatesYYList = findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesYY);
		for (WebElement e : coordinatesYYList)
			sendKeys(e, JavaUtils.getRandomNumber(2));

		datePickerList = findElements(FilesTab.css_PopUploadCustomAttributesFilesDatePickers);
		for (WebElement e : datePickerList) {
			clickElementAndWait(e);
			waitUntilElementIsDisplayed(GlobalPageElements.lnk_DatePickerCalenderToday);
			clickElementAndWait(GlobalPageElements.lnk_DatePickerCalenderToday);
			waitUntilElementIsInvisible(GlobalPageElements.lnk_DatePickerCalenderToday);
		}

		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);

		if (distributionUsers != null) {
			clickElementAndWaitForElement(FilesTab.btn_PopUploadFileDistributeFiles, FilesTab.txt_PopUploadFileDistributeTo);
			for (String s : distributionUsers) {
				sendKeys(FilesTab.txt_PopUploadFileDistributeTo, s);
				sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.TAB);
			}
		}
		log.info("Upload Operation Started for number of Documents :" + globalUploadFileList.size() + "\tTHID :" + Thread.currentThread().getId());
		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		log.info("Upload Operation Completed for number of Documents :" + globalUploadFileList.size() + "\tTHID :" + Thread.currentThread().getId());
		log.info("Document(s) with Custom Attributes Upload list: " + globalUploadFileList);
		return globalUploadFileList;
	}

	/**
	 * Creates the test project.
	 *
	 * @param project the project
	 * @param dc      the dc
	 */
	public void createTestProject(String project, String dc) {

		clickElementAndWait(ProjectsTab.lnk_ProjectsTabLHAddProject);
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, project);
		log.info("Creating automation test project with Title: " + project + " and data center :" + dc);
		if (dc.equalsIgnoreCase("UK"))
			selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, AdoddleCommonStringPool.UK_GEOGRAPHY);
		else if (dc.equalsIgnoreCase("US"))
			selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, AdoddleCommonStringPool.US_GEOGRAPHY);
		else if (dc.equalsIgnoreCase("AUS"))
			selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, AdoddleCommonStringPool.AUS_GEOGRAPHY);
		waitForCompletePageLoad();
		if (getCount(ProjectsTab.txt_PopCreateProjectClientInput) > 0) {
			getWebDriver().findElement(ProjectsTab.txt_PopCreateProjectClientInput).sendKeys(ResourceHandler.loadProperty("secondary.user.org"));
			waitForCompletePageLoad();
			clickElementAndWait(ProjectsTab.sel_CloneProjectClientNameFirstResult);
		}
		clickElementAndWait(ProjectsTab.btn_PopCreateProjectSave);
		reloadPage();
		waitForCompletePageLoad();
	}

	/**
	 * Creates the project folder.
	 *
	 * @param project     the project
	 * @param folderTitle the folder title
	 */
	public void createProjectFolder(String project, String folderTitle) {
		clickElementAndWait(LandingPage.lnk_Files);
		contextClickWithText(project);
		waitUntilElementIsDisplayed(FilesTab.opt_ProjectContextClickNew);
		/*
		 * clickElement(FilesTab.opt_ProjectContextClickNew); clickElementAndWait(FilesTab.opt_ProjectContextClickFolder);
		 */
		mouseHoverAndClickElement(FilesTab.opt_ProjectContextClickNew, FilesTab.opt_ProjectContextClickFolder);
		sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderTitle);
		log.info("Creating automation test folder with Title: " + folderTitle + " in project :" + project);
		waitForCompletePageLoad();
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopCreateFolderCreate, GlobalPageElements.pop_PopUpElement);

	}

	/**
	 * Creates the project folder.
	 *
	 * @param project     the project
	 * @param folderTitle the folder title
	 */
	public void createProjectFolderMoreOptions(String project, String folderTitle) {
		clickElementAndWait(LandingPage.lnk_Files);
		clickElementWithText(project);
		clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, FilesTab.lnk_PopMoreOptionsNewFolder);
		clickElementAndWaitForElement(FilesTab.lnk_PopMoreOptionsNewFolder, FilesTab.txt_PopCreateFolderFolderName);
		sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderTitle);
		log.info("Creating automation test folder with Title: " + folderTitle + " in project :" + project);
		waitForCompletePageLoad();
		clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

	}

	/**
	 * Deactivate project folder.
	 *
	 * @param project     the project
	 * @param folderTitle the folder title
	 */
	public void deactivateProjectFolder(String project, String folderTitle) {
		clickElementAndWait(LandingPage.lnk_Files);
		log.info("Deactivating automation test folder with Title: " + folderTitle + " in project :" + project);
		clickElementWithText(project);
		contextClickWithText(folderTitle);
		clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.chk_PopEditFolderDeactivate);
		clickElementAndWaitForElement(FilesTab.chk_PopEditFolderDeactivate, FilesTab.btn_PopEditFolderUpdate);
		clickElementAndWait(FilesTab.btn_PopEditFolderUpdate);
		waitForCompletePageLoad();
		try {
			waitUntilElementIsDisplayed(FilesTab.btn_PopClearActionsContinue, 5);
			if (isDisplayed(FilesTab.btn_PopClearActionsContinue))
				clickElementAndWaitForElement(FilesTab.btn_PopClearActionsContinue, FilesTab.btn_PopupOK);
		}
		catch (Throwable t) {
			log.info("Clear actions popup was not found");
		}
		clickElement(FilesTab.btn_PopupOK);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

	}

	/**
	 * Deactivate project folder.
	 *
	 * @param folderTitle the folder title
	 */
	public void deactivateFolder(String folderTitle) {
		clickElementAndWait(LandingPage.lnk_Files);
		contextClickWithText(folderTitle);
		clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.chk_PopEditFolderDeactivate);
		clickElementAndWaitForElement(FilesTab.chk_PopEditFolderDeactivate, FilesTab.btn_PopEditFolderUpdate);
		clickElementAndWait(FilesTab.btn_PopEditFolderUpdate);
		waitForCompletePageLoad();
		try {
			waitUntilElementIsDisplayed(FilesTab.btn_PopClearActionsContinue, 5);
			if (isDisplayed(FilesTab.btn_PopClearActionsContinue))
				clickElementAndWaitForElement(FilesTab.btn_PopClearActionsContinue, FilesTab.btn_PopupOK);
		}
		catch (Throwable t) {
			log.info("Clear actions popup was not found");
		}
		clickElement(FilesTab.btn_PopupOK);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

	}

	/**
	 * Deactivate folders with text.
	 *
	 * @param text the text
	 */
	public void deactivateFoldersWithText(String text) {
		List<WebElement> folderElementList = findElements(By.cssSelector("div[class*='tree-row'][style*='padding-left: 30px;'] a[class='docList']"));
		List<String> folderList = new ArrayList<String>();
		for (WebElement e : folderElementList) {
			if (getElementText(e).contains(text))
				folderList.add(getElementText(e));
		}
		log.info("List of folders to be deactivated: " + folderList.toString());
		for (String folder : folderList) {
			contextClickWithText(folder);
			clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.chk_PopEditFolderDeactivate);
			clickElementAndWaitForElement(FilesTab.chk_PopEditFolderDeactivate, FilesTab.btn_PopEditFolderUpdate);
			clickElementAndWait(FilesTab.btn_PopEditFolderUpdate);
			waitForCompletePageLoad();
			try {
				waitUntilElementIsDisplayed(FilesTab.btn_PopClearActionsContinue, 5);
				if (isDisplayed(FilesTab.btn_PopClearActionsContinue))
					clickElementAndWaitForElement(FilesTab.btn_PopClearActionsContinue, FilesTab.btn_PopupOK);
			}
			catch (Throwable t) {
				log.info("Clear actions popup was not found");
			}
			clickElement(FilesTab.btn_PopupOK);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		}
	}

	/**
	 * Deactivate dashboard with text.
	 *
	 * @param text the text
	 */
	public void deactivateDashboardWithText(String text) {
		clickElementAndWaitForElement(By.cssSelector("div[id='tools'] button[title='Tools']"), By.cssSelector("a[id='manageDashboard'][title='Manage Dashboards']"));
		clickElementAndWaitForElement(By.cssSelector("a[id='manageDashboard'][title='Manage Dashboards']"), By.cssSelector("div[id='dashborad-listing'] thead"));
		int size = findElements(By.cssSelector("div[id='dashborad-listing'] tbody tr")).size();

		for (int index = 0; index <= size; index++) {
			List<WebElement> dashboardRows = findElements(By.cssSelector("div[id='dashborad-listing'] tbody tr"));

			try {
				for (WebElement e : dashboardRows) {
					if (getElementText(e).contains(text)) {
						log.info("Deleting Dashboard with title :" + e.findElement(By.cssSelector("tbody td[title]")).getText());
						clickElementAndWaitForElement(e.findElement(By.className("icon-trash")), findElement(By.id("continueButton")));
						clickElementAndWaitForInvisibilityOfElement(By.id("continueButton"), By.id("continueButton"));
					}
				}
			}
			catch (StaleElementReferenceException e) {
				log.info("Iterating for next dashboard");
			}
		}
	}

	/**
	 * Deactivate users with text.
	 *
	 * @param text the text
	 */
	public void deactivateUsersWithText(String text) {
		searchUsers(text);
		List<String> userEmails = new ArrayList<String>();
		executeJScript("$('div.innerContainer').scroll(5000)");
		waitForCompletePageLoad();
		List<WebElement> userRows = findElements(AdminTab.css_ManageUsersListingUserRows);
		log.info("Total Users count: " + userRows.size());
		for (WebElement e : userRows) {
			if (e.findElement(AdminTab.css_ManageUsersListingUserStatus).getText().equals(AdoddleCommonStringPool.USER_STATUS_ACTIVE))
				userEmails.add(getElementText(e.findElement(AdminTab.css_ManageUsersListingUserEmails)));
		}
		log.info("Users to be deactivated :" + userEmails.toString());
		for (String s : userEmails) {
			searchUsers(s);
			waitUntilElementContainsText(AdminTab.lbl_ManageUsersListingFirstEmail, s);
			clickElementAndWaitForElement(AdminTab.img_ManageUsersListingFirstUserEdit, AdminTab.drp_PopCreateUserStatus);
			selectByVisibleText(AdminTab.drp_PopCreateUserStatus, AdoddleCommonStringPool.USER_STATUS_INACTIVE);
			clickElementAndWaitForElement(By.cssSelector("button[id='okButton']"), AdminTab.btn_PopCreateUserUpdateButton);
			sendKeys(AdminTab.txt_PopCreateUserBillToOrg, Keys.TAB);
			clickElementAndWaitForInvisibilityOfElement(AdminTab.btn_PopCreateUserUpdateButton, AdminTab.btn_PopCreateUserUpdateButton);
			log.info("Successfully deactivated user with email:" + s);
		}
	}

	/**
	 * Search users.
	 *
	 * @param userEmail the user email
	 */
	public void searchUsers(String userEmail) {
		sendKeys(AdminTab.txt_ManageUsersUserSearchInput, userEmail);
		sendKeys(AdminTab.txt_ManageUsersUserSearchInput, Keys.ENTER);
		waitForCompletePageLoad();
	}

    /*
	 * public void verifyFileInHTMLViewer() { openFileInViewerWindow(); waitForCompletePageLoad(); if (!isDisplayed(FilesTab.lbl_UnsupportedFile)) { waitUntilElementIsDisplayed(FilesTab.frm_BravaObjectFrame); switchIframe(FilesTab.frm_BravaObjectFrame);
	 * 
	 * if (!isDisplayed(FilesTab.frm_ErrorFileIframe)) { waitUntilElementIsDisplayed(FilesTab.frm_OpenFileIframe); Assert.assertTrue(isDisplayed(FilesTab.frm_OpenFileIframe)); log.info("Expected Iframe: Expected Iframe Detected"); switchDefault();
	 * 
	 * } else { log.error("Error Iframe: Error Iframe Detected"); Assert.assertTrue(false); } } else { log.info("Unsupported File : File not supported"); } }
	 * 
	 * @throws InterruptedException
	 *             the interrupted exception
	 */

	/**
	 * Verify file in html viewer.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public void verifyFileInHTMLViewer() throws InterruptedException {
		openFileInViewerWindow();
		waitForCompletePageLoad();
		waitForHTMLFileView();
	}

	/**
	 * Wait for html file view.
	 *
	 * @param externalFilename the external filename
	 * @throws InterruptedException the interrupted exception
	 */
	public void waitForHTMLFileView(String... externalFilename) throws InterruptedException {
		Long count = 0L;
		if (!isDisplayed(FilesTab.lbl_UnsupportedFile)) {
			waitUntilElementIsDisplayed(FilesTab.frm_BravaObjectFrame);
			String objectID = getElementAttributeValue(FilesTab.frm_BravaObjectFrame, "id");
			for (int counter = 0; counter < 60; counter++) {
				if (((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit__WidgetBase_0')") != null)
					count = (Long) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit__WidgetBase_0').getElementsByTagName('iframe').length");

				if (count.intValue() == 1) {
					log.info("SUCCESS: File View Successful");
					if (externalFilename != null && externalFilename.length > 0) {
						if (fileBetaViewFlag) {
							log.info("Actual external file name on HTML Viewer: " + ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent"));
							log.info("Expected external file name : " + listUtils.getArrayList(externalFilename).get(0));
							AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(listUtils.getArrayList(externalFilename).get(0), ((String) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent"))), listUtils.getArrayList(externalFilename).get(0).equalsIgnoreCase((String) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent")));
						}
						else {
							log.info("Actual external file name on HTML Viewer: " + ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent"));
							log.info("Expected external file name : " + listUtils.getArrayList(externalFilename).get(0));
							AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(listUtils.getArrayList(externalFilename).get(0), ((String) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('app_widget_ToolPropertiesMenuBar_0').getElementsByClassName('titleText')[0].textContent"))), listUtils.getArrayList(externalFilename).get(0).equalsIgnoreCase((String) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('app_widget_ToolPropertiesMenuBar_0').getElementsByClassName('titleText')[0].textContent")));
						}
					}
					switchDefault();
					try {
						takeScreenShot(TestDriverFactory.scenario);
					}
					catch (Throwable t) {
						log.info(": Failed to Fetch Screenshot :");
					}
					break;
				}
				else
					waitUtils.waitInterval(2);
			}
			if (count.intValue() == 0)
				AutomationAssert.verifyTrue("ERROR: Unable to view the file", false);
		}
		else {
			log.info("Unsupported File : File not supported");
		}
	}
	
	/**
	 * Verify file comparison in html viewer.
	 *
	 * @param file1 the file 1
	 * @param file2 the file 2
	 * @throws InterruptedException the interrupted exception
	 */
	public void verifyCompareFilesView(String file1, String file2) throws InterruptedException {
		Long count = 0L;
		String firstFileName,secondFileName ;
		if (!isDisplayed(FilesTab.lbl_UnsupportedFile)) {
			waitUntilElementIsDisplayed(FilesTab.frm_BravaObjectFrame);
			String objectID = getElementAttributeValue(FilesTab.frm_BravaObjectFrame, "id");
			for (int counter = 0; counter < 60; counter++) {
				if (((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit__WidgetBase_0')") != null)
					count = (Long) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit__WidgetBase_0').getElementsByTagName('iframe').length");

				if (count.intValue() == 1) {
					log.info("SUCCESS: File View Successful");
					{
						firstFileName =  (String) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_5').textContent");
						log.info("First file name on HTML Viewer: " + firstFileName);
						secondFileName =  (String) ((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_9').textContent");
						log.info("Second file name on HTML Viewer: " +secondFileName);
						AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(firstFileName, file1), firstFileName.equalsIgnoreCase(file1));
						AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(secondFileName, file2), secondFileName.equalsIgnoreCase(file2));
					}
					switchDefault();
					try {
						takeScreenShot(TestDriverFactory.scenario);
					}
					catch (Throwable t) {
						log.info(": Failed to Fetch Screenshot :");
					}
					break;
				}
				else
					waitUtils.waitInterval(2);
			}
			if (count.intValue() == 0)
				AutomationAssert.verifyTrue("ERROR: Unable to view the file", false);
		}
		else {
			log.info("Unsupported File : File not supported");
		}
	}

	/**
	 * Assign File actions to multiple users.
	 *
	 * @param users   the users
	 * @param actions the actions
	 */
	protected void assignFileActionsToMultipleUsers(List<String> users, List<String> actions) {

		for (String user : users) {
			for (int index = 0; index < actions.size(); index++) {
				sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, user);
				sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.TAB);
			}
		}

		index = javaUtils.resetIndex(index, 0);

		List<WebElement> dropdownToggles = findElements(FilesTab.css_PopDistributeToggleDropdowns);

		for (WebElement e : dropdownToggles) {
			clickElementAndWait(e);
			waitUntilElementIsDisplayed(FilesTab.drp_PopDistributeToggleActionDropDown);
			selectByVisibleText(FilesTab.drp_PopDistributeToggleActionDropDown, actions.get(index));
			if (isDisplayed(GlobalPageElements.lnk_DatePickerCalenderToday)) {
				clickElementAndWaitUntilElementInvisible(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
			}
			else {
				clickElementAndWaitForElement(GlobalPageElements.lnk_DatePickerMonthPrevious, GlobalPageElements.lnk_DatePickerCalenderToday);
				clickElementAndWaitUntilElementInvisible(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
			}
			index++;
			if (index == (actions.size()))
				index = javaUtils.resetIndex(index, 0);
		}

	}

	/**
	 * Assign Form actions to multiple users.
	 *
	 * @param users   the users
	 * @param actions the actions
	 */
	protected void assignFormActionsToMultipleUsers(List<String> users, List<String> actions) {
		for (String user : users) {
			for (int index = 0; index < actions.size(); index++) {
				/*if (!appBetaViewFlag) {
					sendKeys(ProjectFormsTab.txt_CERFormDistributeTo, user);
					sendKeys(ProjectFormsTab.txt_CERFormDistributeTo, Keys.TAB);
				}
				else {*/
					sendKeys(ProjectFormsTab.txt_BetaViewCERFormDistributeTo, user);
					List<WebElement> userListRows = findElements(ProjectFormsTab.css_BetaViewFormDistributionToList);
					for (WebElement e : userListRows) {
						if (e.findElement(ProjectFormsTab.lbl_BetaViewFormDistributionUserSearchList).getText().contains(user + AdoddleCommonStringPool.COMMA_STRING) || e.findElement(ProjectFormsTab.lbl_BetaViewFormDistributionUserSearchList).getText().contains("\\(" + user + "\\)")) {
							if (!isSelected(findElements(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox).get(0))) {
								log.info("Checking if checkbox checked: " + e.findElement(ProjectFormsTab.lbl_BetaViewFormDistributionUserSearchList).getText());
								clickElementAndWait(findElements(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox).get(0));
							}
							else {
								log.info("Clicking cloning icon :" + e.findElement(ProjectFormsTab.lbl_BetaViewFormDistributionUserSearchList).getText());
								clickElementAndWait(e.findElement(ProjectFormsTab.img_BetaViewFormDistributionCloneIcon));
							}
							break;
						}
					}
				//}
			}
		}

		index = javaUtils.resetIndex(index, 0);

		List<WebElement> dropdownToggles = (findElements(/*(!appBetaViewFlag) ? ProjectFormsTab.css_PopFormDistributeToggleDropdowns :*/ProjectFormsTab.css_BetaViewCreateFormToggleActions));

		for (WebElement e : dropdownToggles) {
			clickElementAndWait(e);
			selectByVisibleText((/*!appBetaViewFlag ? ProjectFormsTab.drp_PopFormDistributeToggleActionDropDown :*/ ProjectFormsTab.drp_BetaViewCreateFormAssignActionToUser), actions.get(index));
			if (isDisplayed(GlobalPageElements.lnk_DatePickerCalenderToday))
				selectDateFromCalendar(); //clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
			else {
				if (isDisplayed(ProjectFormsTab.pop_CreateFormTogglePopOverContent)) {
					mouseHover(ProjectFormsTab.ele_CreateFormToggleActionsClose);
					waitUntilElementIsInvisible(ProjectFormsTab.pop_CreateFormTogglePopOverContent);
				}
				/*if (!appBetaViewFlag) {
					clickElementAndWaitForElement(GlobalPageElements.lnk_DatePickerMonthPrevious, GlobalPageElements.lnk_DatePickerCalenderToday);
					clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
				}*/
			}
			index++;
			if (index == (actions.size()))
				index = javaUtils.resetIndex(index, 0);
		}

	}

	/**
	 * Clone project.
	 *
	 * @param cloneSourceWS the clone source ws
	 * @param dc            the dc
	 */
	public synchronized void cloneProject(String cloneSourceWS, String dc) {

		boolean flag = false;
		String currentURL = getCurrentWindowURL();

		if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_LIVE)) {
			if (dc.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleak"))
				currentURL = currentURL.contains("adoddleb") ? currentURL.replace("adoddleb", "adoddleak") : currentURL.replace("adoddled", "adoddleak");
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleb"))
				currentURL = currentURL.contains("adoddleak") ? currentURL.replace("adoddleak", "adoddleb") : currentURL.replace("adoddled", "adoddleb");
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.AUS_DC) && !currentURL.contains("adoddled"))
				currentURL = currentURL.contains("adoddleak") ? currentURL.replace("adoddleak", "adoddled") : currentURL.replace("adoddleb", "adoddled");
		}
		else if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_QA2)) {
			if (dc.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleqa2ak"))
				currentURL = currentURL.contains("adoddleqa2b") ? currentURL.replace("adoddleqa2b", "adoddleqa2ak") : currentURL;
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleqa2b"))
				currentURL = currentURL.contains("adoddleqa2ak") ? currentURL.replace("adoddleqa2ak", "adoddleqa2b") : currentURL;
		}
		else if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_QA1)) {
			if (dc.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleqaak"))
				currentURL = currentURL.contains("adoddleqab") ? currentURL.replace("adoddleqab", "adoddleqaak") : currentURL;
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleqab"))
				currentURL = currentURL.contains("adoddleqaak") ? currentURL.replace("adoddleqa2ak", "adoddleqab") : currentURL;
		}

		navigateURL(currentURL);
		clickElementAndWaitForElement(LandingPage.lnk_Projects, ProjectsTab.ele_ProjectsTemplatesName);
		searchProjects(cloneSourceWS);

		waitUntilElementCountToBeMoreThan(ProjectsTab.ele_ProjectsTemplatesName, 0);
		List<WebElement> projectTemplates = findElements(ProjectsTab.ele_ProjectsTemplatesName);

		for (WebElement e : projectTemplates) {
			if (e.getAttribute("title").equalsIgnoreCase(cloneSourceWS)) {
				clonedProject = "Cloned_" + dc + "_" + dateUtils.getEpoch();
				collectionDataMap.put("Cloned Project", clonedProject);
				Actions action = new Actions(getWebDriver());
				action.moveToElement(e);
				action.contextClick(e).build().perform();
				waitForCompletePageLoad();
				clickElementAndWait(ProjectsTab.sel_CloneProject);
				Assert.assertTrue(getElementText(ProjectsTab.lbl_CloneProject).contains(AdoddleCommonStringPool.POPUP_CLONE_PROJECT));
				sendKeys(ProjectsTab.txt_CloneProjectName, clonedProject);
				if (findElement(ProjectsTab.txt_CloneProjectClientName).isEnabled()) {
					getWebDriver().findElement(ProjectsTab.txt_CloneProjectClientName).sendKeys(AdoddleCommonStringPool.QA2_ORGANIZATION_AUTOMATION_TESTING);
					clickElementAndWait(ProjectsTab.sel_CloneProjectClientNameFirstResult);
				}

				clickElementAndWait(ProjectsTab.txt_CloneProjectSubmit);
				waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();
				flag = true;
				break;
			}

			else
				log.info("Iterating search in listing for template: " + cloneSourceWS);
		}

		Assert.assertTrue("Template not found::" + cloneSourceWS, flag);
	}

	/**
	 * Sets the landing url.
	 *
	 * @param dc the new landing url
	 */
	public void setLandingURL(String dc) {
		String currentURL = getCurrentWindowURL();
		if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_LIVE)) {
			if (dc.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleak"))
				currentURL = currentURL.contains("adoddleb") ? currentURL.replace("adoddleb", "adoddleak") : currentURL.replace("adoddled", "adoddleak");
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleb"))
				currentURL = currentURL.contains("adoddleak") ? currentURL.replace("adoddleak", "adoddleb") : currentURL.replace("adoddled", "adoddleb");
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.AUS_DC) && !currentURL.contains("adoddled"))
				currentURL = currentURL.contains("adoddleak") ? currentURL.replace("adoddleak", "adoddled") : currentURL.replace("adoddleb", "adoddled");
		}
		else if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_QA2)) {
			if (dc.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleqa2ak"))
				currentURL = currentURL.contains("adoddleqa2b") ? currentURL.replace("adoddleqa2b", "adoddleqa2ak") : currentURL;
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleqa2b"))
				currentURL = currentURL.contains("adoddleqa2ak") ? currentURL.replace("adoddleqa2ak", "adoddleqa2b") : currentURL;
		}
		else if (ResourceUtils.getExecutionEnvironment().equalsIgnoreCase(AdoddleCommonStringPool.ENV_QA1)) {
			if (dc.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC) && !currentURL.contains("adoddleqaak"))
				currentURL = currentURL.contains("adoddleqab") ? currentURL.replace("adoddleqab", "adoddleqaak") : currentURL;
			else if (dc.equalsIgnoreCase(AdoddleCommonStringPool.US_DC) && !currentURL.contains("adoddleqab"))
				currentURL = currentURL.contains("adoddleqaak") ? currentURL.replace("adoddleqa2ak", "adoddleqab") : currentURL;
		}
		navigateURL(currentURL);
		waitForCompletePageLoad();
	}

	/**
	 * Creates the project system trigger.
	 *
	 * @param workSpace       the work space
	 * @param systemTaskTitle the system task title
	 * @param triggerValues   the trigger values
	 * @param e               the e
	 * @param actionMode      the action mode
	 * @return the string
	 */
	protected String createProjectSystemTrigger(String workSpace, String systemTaskTitle, Map<String, String> triggerValues, String e, String actionMode) {

		String triggerTitle = "Auto_Trigger" + dateUtils.getEpoch();

		navigateTab(LandingPage.lnk_Workflows);
		clickElementWithText(workSpace);
		clickElementAndWaitForElement(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink, WorkflowsTab.lnk__WorkflowsCreateTriggerLink);
		clickElementAndWaitForElement(WorkflowsTab.lnk__WorkflowsCreateTriggerLink, WorkflowsTab.txt_WorkflowsConfigureTriggerName);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerName, triggerTitle);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerDescription, javaUtils.getRandomString(30));

		for (Map.Entry<String, String> entry : triggerValues.entrySet()) {
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerField, entry.getKey());
			waitUntilElementIsDisplayed(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);
			clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerFieldSearchResult);

			if (entry.getKey().equalsIgnoreCase("Folder")) {
				sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, entry.getValue());
				clickElementAndWait(WorkflowsTab.opt_WorkflowsConfigureTriggerValueFirstSearchResult);
			}
			else if (entry.getKey().equalsIgnoreCase("Purpose of issue") || entry.getKey().equalsIgnoreCase("Document Status"))
				selectByVisibleText(By.cssSelector("form[id='workflow-rules-form'] div[class='controls'] li[class*='last-row'] select[class='required']"), entry.getValue());

			/* Add code for other fields as and when required */
			clickElementAndWaitForElement(WorkflowsTab.lnk_WorkflowsConfigureTriggerAddLink, WorkflowsTab.lnk_WorkflowsConfigureTriggerRemoveink);

		}

		clickElementAndWait(WorkflowsTab.lnk_WorkflowsConfigureTriggerRemoveink);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerEvent, e);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode, actionMode);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerAction, systemTaskTitle);
		clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

		return triggerTitle;
	}

	/**
	 * Creates the project system task.
	 *
	 * @param project  the project
	 * @param type     the type
	 * @param userList the user list
	 * @return the string
	 */
	protected String createProjectSystemTask(String project, String type, List<String> userList) {
		String systemTaskTitle = "Auto_SystemTask" + dateUtils.getEpoch();
		navigateTab(LandingPage.lnk_Workflows);
		clickElementWithText(project);
		clickElementAndWaitForElement(WorkflowsTab.lnk_WorkflowsTriggersLHPanelSystemActionLink, WorkflowsTab.lnk__WorkflowsConfigureSystemActionLink);
		clickElementAndWaitForElement(WorkflowsTab.lnk__WorkflowsConfigureSystemActionLink, WorkflowsTab.txt_WorkflowsConfigureSystemActionName);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionName, systemTaskTitle);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureSystemActionTypeDropdown, type);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown);
		for (String user : userList) {
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown, user);
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureSystemActionTODropdown, Keys.TAB);
		}
		clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureSystemActionCreateButton);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

		return systemTaskTitle;
	}

	/**
	 * Focus cloned project.
	 */
	public synchronized void focusClonedProject() {
		log.info("Cloned project::" + clonedProject);
		clickElementWithText(clonedProject);
	}

	/**
	 * Open file in viewer window.
	 */
	protected void openFileInViewerWindow() {
		clickElementAndWait(FilesTab.lnk_DocListingFirstFileName);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
	}

	/**
	 * Mouse hover first action pop over.
	 */
	protected void mouseHoverFirstActionPopOver() {

		if (isDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver)) {
			mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitForCompletePageLoad();
		}
		else {
			executeJScript(AdoddleCommonJQueries.actionColWidthExpandQuery);
			mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
			waitForCompletePageLoad();
		}
	}

	/**
	 * Checks for user privilege.
	 *
	 * @param privilege the privilege
	 * @param userRole  the user role
	 * @param project   the project
	 * @return true, if successful
	 */
	protected PriviledgeClass hasUserPrivilege(String privilege, String userRole, String project) {
		searchProjects(project);
		contextClickWithLink(project);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditUserAccess);
		clickElementAndWaitForElement(ProjectsTab.lnk_PopManageRolesRolePrivileges, ProjectsTab.btn_PopManageRolesRolePrivilegesFilter);
		clickElementAndWaitForElement(ProjectsTab.btn_PopManageRolesRolePrivilegesFilter, ProjectsTab.txt_PopManageRolesRolePrivilegesSearchInput);
		sendKeys(ProjectsTab.txt_PopManageRolesRolePrivilegesSearchInput, privilege);
		waitUntilElementContainsText(GlobalPageElements.lbl_PopFilterListScrollFirstSearchResult, privilege);
		clickElementAndWaitForElement(GlobalPageElements.chk_PopFilterListScrollFirstSearchResult, GlobalPageElements.lnk_FilterClose);
		clickElementAndWaitForElement(GlobalPageElements.lnk_FilterClose, ProjectsTab.btn_PopManageRolesRolePrivilegesRoleNameFilter);
		clickElementAndWaitForElement(ProjectsTab.btn_PopManageRolesRolePrivilegesRoleNameFilter, ProjectsTab.txt_PopManageRolesRolePrivilegesSearchInput);
		sendKeys(ProjectsTab.txt_PopManageRolesRolePrivilegesSearchInput, userRole);
		waitUntilElementContainsText(GlobalPageElements.lbl_PopFilterListScrollFirstSearchResult, userRole);
		clickElementAndWaitForElement(GlobalPageElements.chk_PopFilterListScrollFirstSearchResult, GlobalPageElements.lnk_FilterClose);
		clickElementAndWait(GlobalPageElements.lnk_FilterClose);

		String permissionID = "";
		String roleClassID = "";
		waitUntilElementCountToBeMoreThan(By.cssSelector("div[id='rolePrivilegesMainDiv'] div[id='roles-table'] header[class='fixedTable-header'] li"), 0);
		List<WebElement> roleHeaders = findElements(By.cssSelector("div[id='rolePrivilegesMainDiv'] div[id='roles-table'] header[class='fixedTable-header'] li"));
		log.info("Role Headers count :"+roleHeaders.size());
		for(WebElement e: roleHeaders) {
			if(privilege.equalsIgnoreCase(e.getText())) {
				permissionID = e.getAttribute("class").split(" ")[0];
				log.info("Permission ID for Assign Document Metadata Role :"+permissionID);
				break;
			}
		}

		List<WebElement> roleRows = findElements(By.cssSelector("div[id='roles-table'] div[class*='side-wrapper'] li"));
		log.info("Role Rows Size: "+roleRows.size());
		int counter = 0;

		while (counter != roleRows.size()) {

			if (roleRows.get(counter).findElement(By.tagName("input")).getAttribute("value").equalsIgnoreCase(userRole)) {
				log.info("Expected Role: "+roleRows.get(counter).getAttribute("value"));
				log.info("Actual Role: "+userRole);
				roleClassID = roleRows.get(counter).getAttribute("class").split(" ")[0];
				log.info("Role ID: "+roleClassID);
				break;
			}

			counter++;
		}

		By chk_RoleFormPermissionChecked = By.cssSelector("div[id='roles-table'] div[class='fixedTable-body'] ul[class*='"+roleClassID+"'] li[class='"+permissionID+" "+"checked']");
		By chk_RoleFormPermissionUnChecked = By.cssSelector("div[id='roles-table'] div[class='fixedTable-body'] ul[class*='"+roleClassID+"'] li[class='"+permissionID+"']");


		PriviledgeClass pclass = new PriviledgeClass();

		if(isDisplayed(chk_RoleFormPermissionChecked)) {
			log.info("Element Attribute : "+getElementAttributeValue(chk_RoleFormPermissionChecked, "class"));
			pclass.setLocator(chk_RoleFormPermissionChecked);
			pclass.setFlag(true);
		}
		else if(isDisplayed(chk_RoleFormPermissionUnChecked)){
			log.info("Element Attribute : "+getElementAttributeValue(chk_RoleFormPermissionUnChecked, "class"));
			pclass.setLocator(chk_RoleFormPermissionUnChecked);
			pclass.setFlag(false);
		}

		return pclass;

	}

	/**
	 * Scroll window bottom.
	 */
	public void scrollWindowBottom() {
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		waitForCompletePageLoad();
	}

	/**
	 * Assign actions to user.
	 *
	 * @param distributeTo the distribute to
	 */
	protected void assignActionsToUser(String distributeTo) {
		String actionArray[] = { "For Acknowledgement", "For Action", "For Comment", "For Comment Coord.", "For Distribution", "For Information", "For Status Change" };
		String testSubjectForDistribution = javaUtils.getRandomString(10) + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(FilesTab.pop_FilesActionForDistribute);

		int j = 0;
		for (int i = 1; i <= actionArray.length; i++) {
			sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, distributeTo);
			sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.ENTER);
			clickElementAndWait(By.xpath(".//div[@id='s2id_distTo']//ul/li[" + i + "]"));
			selectByVisibleText(FilesTab.sel_Action, actionArray[j]);
			clickElementAndWait(FilesTab.lnk_DistributePopupCurrentDate);
			j++;
		}
		sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject, testSubjectForDistribution);
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	/**
	 * Click more options and select option.
	 *
	 * @param optionType the option type
	 */
	protected void clickMoreOptionsAndSelectOption(String optionType) {
		if (optionType.contains(AdoddleCommonStringPool.OPTION_DISTRIBUTE_FILES)) {
			clickElementAndWaitForElement(FilesTab.lnk_FilesMoreOptions, FilesTab.lnk_OptionsDistributeFiles);
			clickElementAndWait(FilesTab.lnk_OptionsDistributeFiles);
		}
		else if (optionType.contains(AdoddleCommonStringPool.OPTION_PROJECT_FORM)) {
			clickElementAndWaitForElement(FilesTab.lnk_FilesMoreOptions, FilesTab.lnk_OptionsStartWorkflow);
			clickElementAndWait(FilesTab.lnk_OptionsStartWorkflow);
		}
	}

	/**
	 * Click on More Options Button on Any Tab.
	 */
	public void clickOnMoreOptions() {
		clickElementAndWait(GlobalPageElements.lnk_MoreOptions);
	}

	/**
	 * Verify link success.
	 */
	protected void verifyLinkSuccess() {
		try {
			waitUntilElementIsDisplayed(GlobalPageElements.lbl_SuccessMessage, 10);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(GlobalPageElements.lbl_SuccessMessage), AdoddleCommonStringPool.LINK_FILE_SUCCESS_MESSAGE), getElementText(GlobalPageElements.lbl_SuccessMessage).contains(AdoddleCommonStringPool.LINK_FILE_SUCCESS_MESSAGE));
		}
		catch (Throwable t) {
			log.error("Link Success Message could not be verified sucessfully: " + AdoddleCommonStringPool.LINK_FILE_SUCCESS_MESSAGE);
		}
	}

	/**
	 * Select project edit option.
	 *
	 * @param project the project
	 * @param option  the option
	 */
	protected void selectProjectEditOption(String project, String option) {
		searchProjects(project);
		contextClickWithLink(project);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_EDIT, option);
	}

	/**
	 * Gets the document custom attributes.
	 *
	 * @return the document custom attributes
	 */
	protected HashMap<String, String> getDocumentCustomAttributes() {
		HashMap<String, String> attributesMap = new HashMap<String, String>();
		waitForCompletePageLoad();

		if (!fileBetaViewFlag)
			clickElementAndWait(FilesTab.lnk_FileViewerHeaderShowMore);
		else {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileDetails);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileDetails, FilesTab.ele_BetaViewFileCustomAtrributesTable);
		}

		List<WebElement> customAttributeElements = findElements(!fileBetaViewFlag ? FilesTab.css_FileViewerFileAttributes : FilesTab.css_BetaFileViewerFileAttributes);

		if (!fileBetaViewFlag) {
			for (WebElement e : customAttributeElements)
				attributesMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		else {
			for (WebElement e : customAttributeElements)
				attributesMap.put(getElementText(e.findElement(By.tagName("th"))), getElementText(e.findElement(By.tagName("th"))).equalsIgnoreCase("DatePicker") ? getElementText(e.findElement(By.tagName("td"))).trim() : getElementText(e.findElement(By.tagName("td"))).trim().split(" ")[0]);
		}
		return attributesMap;

	}

	/**
	 * Edits the custom attributes.
	 *
	 * @param docPrefix             the doc prefix
	 * @param currentRevision       the current revision
	 * @param currentPOI            the current poi
	 * @param updateStatusLinksFlag the update status links flag
	 * @return the map
	 */
	protected Map<String, String> editCustomAttributes(String docPrefix, int currentRevision, String currentPOI, boolean updateStatusLinksFlag) {
		String updatedDocTitles = null;
		String updatedPOI = null;
		String updatedRevisionNotes = javaUtils.getRandomString(10);
		String updatedIntegerValue = JavaUtils.getRandomNumber(3);
		String updatedLetterNumbers = javaUtils.getRandomString(4) + JavaUtils.getRandomNumber(3);

		Map<String, String> attributeMap = new HashMap<String, String>();
		if (docPrefix != null)
			updatedDocTitles = docPrefix + dateUtils.getEpoch();

		List<WebElement> editAttributesIntegerValues = findElements(FilesTab.css_PopEditAttributesIntegerTextBoxes);
		List<WebElement> editAttributesLettersNumbers = findElements(FilesTab.css_PopEditAttributesLettersNumbersTextBoxes);
		List<WebElement> revElementList = findElements(FilesTab.css_PopEditAttributesRevisions);
		List<WebElement> docTitleElementList = findElements(FilesTab.css_PopEditAttributesDocTitles);
		List<WebElement> stateElementList = findElements(FilesTab.css_PopEditAttributesStatesDropdowns);
		List<WebElement> cityElementList = findElements(FilesTab.css_PopEditAttributesCitiesDropdowns);
		List<WebElement> revisionNotesElementList = findElements(FilesTab.css_PopEditAttributesRevisionNotes);

		for (WebElement rev : revElementList) {
			rev.clear();
			rev.sendKeys(String.valueOf(currentRevision + 1));
		}

		for (WebElement docTitle : docTitleElementList) {
			if (updatedDocTitles != null) {
				docTitle.clear();
				docTitle.sendKeys(updatedDocTitles);
			}
		}

		for (WebElement revNote : revisionNotesElementList)
			revNote.sendKeys(updatedRevisionNotes);

		index = javaUtils.resetIndex(index, 0);

		for (WebElement e : editAttributesIntegerValues) {
			e.clear();
			e.sendKeys(updatedIntegerValue);
			editAttributesLettersNumbers.get(index).clear();
			editAttributesLettersNumbers.get(index).sendKeys(updatedLetterNumbers);
			index++;
		}

		for (WebElement e : stateElementList)
			selectByIndex(e, 3);

		for (WebElement e : cityElementList)
			selectByIndex(e, 2);

		index = javaUtils.resetIndex(index, 1);
		clickElementAndWaitForElement(FilesTab.chk_PopUploadFilesBulkApply, FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue);
		while (index <= 2) {
			selectByIndex(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, index);
			updatedPOI = getSelectedDropdownLabel(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue);
			updatedPOI = (!updatedPOI.equalsIgnoreCase(currentPOI)) ? updatedPOI : null;
			if (updatedPOI != null) {
				log.info("Edit Attributes selected Purpose of issue# " + updatedPOI);
				break;
			}
			index++;
		}

		selectByVisibleText(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, AdoddleCommonStringPool.LABEL_PLEASE_SELECT);
		clickElementAndWait(FilesTab.img_PopEditAttributesHeaderPurposeOfIssueCaret);
		selectByVisibleText(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue, updatedPOI);
		clickElementAndWait(FilesTab.img_PopEditAttributesHeaderPurposeOfIssueCaret);

		if (!isSelected(FilesTab.chk_PopEditAttributesUpdateStatusLinks) && updateStatusLinksFlag)
			clickElementAndWait(FilesTab.chk_PopEditAttributesUpdateStatusLinks);
		else if (!updateStatusLinksFlag && isSelected(FilesTab.chk_PopEditAttributesUpdateStatusLinks))
			clickElementAndWait(FilesTab.chk_PopEditAttributesUpdateStatusLinks);

		clickElementAndWaitForElement(FilesTab.btn_PopEditAttributesAssignAttributes, FilesTab.btn_PopupConfirmUIContinue);
		log.info("Edit Attribute Operation started" + "\t<THID: " + Thread.currentThread().getId() + ">");
		clickElementAndWaitUntilElementInvisible(FilesTab.btn_PopupConfirmUIContinue, GlobalPageElements.pop_PopUpElement);
		log.info("Edit Attribute Operation completed" + "\t<THID: " + Thread.currentThread().getId() + ">");
		attributeMap.put("revision", updatedRevisionNotes);
		attributeMap.put("integer", updatedIntegerValue);
		attributeMap.put("letternumbers", updatedLetterNumbers);
		attributeMap.put("doctitles", updatedDocTitles);
		attributeMap.put("poi", updatedPOI);

		return attributeMap;
	}

	/**
	 * Create combined Apptype Form.
	 *
	 * @param distributeUsers               the distribute users
	 * @param actions                       the actions
	 * @param selectSingleORMultipleActions the select single or multiple actions
	 */
	public void createCombinedAppTypeForm(String distributeUsers, String actions, String selectSingleORMultipleActions) {
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		switchDefault();

		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		if (distributeUsers != null) {
			if (selectSingleORMultipleActions.contains("Single"))
				distributeUsersWithSingleAction(distributeUsers, actions);
			else
				distributeMultipleUsersWithMultipleActions(distributeUsers, actions);
		}
		formTitle = "AutoForm" + epoch + index;
		log.info("formTitle :" + formTitle);
		formList.add(formTitle);
		index++;

		waitUntilElementIsDisplayed(ProjectFormsTab.txt_CreateFormTitle);
		clear(ProjectFormsTab.txt_CreateFormTitle);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		waitUntilElementIsClickable(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		log.info("formList : " + formList);
	}

	/**
	 * Distribute Form With Multiple Users with Multiple Actions Parallelly.
	 *
	 * @param distributeUsers the distribute users
	 * @param actions         the actions
	 */
	protected void distributeMultipleUsersWithMultipleActions(String distributeUsers, String actions) {
			clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
			int i = 0;
			for (String user : distributeUsers.split(",")) {
				sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, user);
				waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + user + "')]"));
				clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + user + "')]//preceding::input[@type='checkbox']"));
				waitUntilElementIsDisplayed(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown);
				selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, actions.split(",")[i]);
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
				i++;
			}
	}

	/**
	 * Distribute Form With Single\Multiple Users with Single Action.
	 *
	 * @param distributeUsers the distribute users
	 * @param action          the action
	 */
	protected void distributeUsersWithSingleAction(String distributeUsers, String action) {
			clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
			for (String user : distributeUsers.split(",")) {
				sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, user);
				waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + user + "')]"));
				clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + user + "')]//preceding::input[@type='checkbox']"));
				waitUntilElementIsDisplayed(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown);
				selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
			}
	}

	/**
	 * Verify action assigned.
	 *
	 * @param actionIndex the action index
	 * @param action      the action
	 */
	protected void verifyActionAssigned(int actionIndex, String action) {
		List<String> actionList1 = new ArrayList<String>();
		List<String> actionList2 = new ArrayList<String>();

		waitUntilElementIsDisplayed(By.xpath(".//div[@index='" + actionIndex + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		mouseHoverElement(By.xpath(".//div[@index='" + actionIndex + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		for (WebElement actualAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
			if (actualAction.getText().contains(":"))
				actionList1.add(actualAction.getText().split(":")[1].trim());
			else
				actionList1.add(actualAction.getText().trim());
		}
		for (String expectedAction : action.split(","))
			actionList2.add(expectedAction.trim());
		log.info("actual action List : " + actionList1);
		log.info("expected action List : " + actionList2);
		Assert.assertTrue(actionList1.containsAll(actionList2));
	}

	/**
	 * Verify action completed.
	 *
	 * @param actionIndex the action index
	 * @param action      the action
	 */
	public void verifyActionCompleted(int actionIndex, String action) {
		List<String> actualActionList = new ArrayList<String>();
		List<String> actualIncompleteActionList = new ArrayList<String>();
		List<String> expectedActionList = new ArrayList<String>();
		Collections.addAll(expectedActionList, action.split(","));
		waitForCompletePageLoad();
		mouseHoverElement(By.xpath(".//div[@index='" + actionIndex + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
			if (popCompleteAction.getText().contains(":"))
				actualActionList.add(popCompleteAction.getText().split(":")[1]);
			else
				actualActionList.add(popCompleteAction.getText());
		}

		if (findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0) {
			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				if (popHoverAction.getText().contains(":"))
					actualIncompleteActionList.add(popHoverAction.getText().split(":")[1]);
				else
					actualIncompleteActionList.add(popHoverAction.getText());
			}
			Assert.assertTrue(! actualIncompleteActionList.containsAll(expectedActionList));
		}
		else
			Assert.assertTrue(true);

		//Assert.assertTrue("expectedActionList :" + expectedActionList.size() + " != actualActionList : " + actualActionList.size(), actualActionList.size() == expectedActionList.size());
		Assert.assertTrue(actualActionList.containsAll(expectedActionList));
	}

	/**
	 * Sets the projet form view.
	 *
	 * @param viewFlag the view flag
	 * @param user     the user
	 */
	protected void setProjetFormView(String viewFlag, String user) {
		boolean betaViewFlag = Boolean.parseBoolean(viewFlag);
		boolean classicViewFlag = !betaViewFlag;

		navigateTab(LandingPage.lnk_ProjectForms);

		if (getCount(ProjectFormsTab.css_FormListingFormTitles) > 0) {
			clickElementAndSwitchWindow(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
			waitForCompletePageLoad();

			waitUntilElementIsInvisible(By.cssSelector("div[class='loading']"));

			if (classicViewFlag && isDisplayed(By.cssSelector("div[id='form-holder']"))) {
				waitForCompletePageLoad();
				log.info("Switching to Project form old view for user: " + user);
				clickElementAndWaitForElement(By.cssSelector("a[class*='old-view-btn']"), By.cssSelector("a[class*='beta-view']"));
				log.info("Switched to Project form old view for user: " + user);
			}

			if (betaViewFlag && isDisplayed(By.cssSelector("div[id='viewFormContain'] div[id='viewFormMainSection'] a[class='beta-view close']"))) {
				waitForCompletePageLoad();
				log.info("Switching to Project form Beta view for user:" + user);
				clickElementAndWaitForElement(By.cssSelector("div[id='viewFormContain'] div[id='viewFormMainSection'] a[class='beta-view close']"), By.cssSelector("a[class*='old-view-btn']"));
				log.info("Switched to Project form Beta view for user:" + user);
			}

		}
	}

	/**
	 * Sets the files view.
	 *
	 * @param viewFlag the view flag
	 * @param user     the user
	 */
	protected void setFilesView(String viewFlag, String user) {

		boolean betaViewFlag = Boolean.parseBoolean(viewFlag);
		boolean classicViewFlag = !betaViewFlag;

		navigateTab(LandingPage.lnk_Files);

		if (getCount(FilesTab.css_FilesListingRowList) > 0) {
			clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstFileName);
			waitForCompletePageLoad();

			waitUntilElementIsInvisible(By.cssSelector("div[class='loading']"));

			if (classicViewFlag && isDisplayed(By.cssSelector("div[id='file-view-page'] a[class*='old-view-btn']"))) {
				waitForCompletePageLoad();
				log.info("Switching to Project form old view for user: " + user);
				clickElementAndWaitForElement(By.cssSelector("a[class*='old-view-btn']"), By.cssSelector("a[class*='beta-view']"));
				log.info("Switched to Project form old view for user: " + user);
			}

			if (betaViewFlag && isDisplayed(By.cssSelector("div[id='fileViewer'] a[class*='beta-view']"))) {
				waitForCompletePageLoad();
				log.info("Switching to Project form Beta view for user:" + user);
				clickElementAndWaitForElement(By.cssSelector("div[id='fileViewer'] a[class*='beta-view']"), By.cssSelector("a[class*='old-view-btn']"));
				log.info("Switched to Project form Beta view for user:" + user);
			}

		}
	}

	/**
	 * Handle batch print dialog.
	 *
	 * @param fileName      the file name
	 * @param parentWString the parent W string
	 * @return the string
	 */
	protected String handleBatchPrintDialog(String fileName, String parentWString) throws InterruptedException {
		String defaultPath;

		if(getWebDriver().getCurrentUrl().contains("apps"))
			defaultPath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path") + "communications" + AdoddleCommonStringPool.PDF_EXTENSION;
		else
			defaultPath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path") + "batchPrint" + AdoddleCommonStringPool.PDF_EXTENSION;

		String renamedPath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + fileName + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION;
		log.info("FileName:: " + renamedPath);
		File file1 = new File(defaultPath);
        File file2 = new File(renamedPath);
        waitUntilFileIsDownloaded(file1);
        AutomationAssert.verifyTrue("Downloaded file was not moved successfully" , file1.renameTo(file2));
        waitUntilFileIsDownloaded(file2);
		closeCurrentWindow();
		switchPreviousWindow(parentWString);
		return renamedPath;

	}

	/**
	 * Sets the listing style.
	 *
	 * @param listingStyle the new listing style
	 */
	protected void setListingStyle(String listingStyle) {
		clickElementAndWaitForElement(GlobalPageElements.btn_ActiveTabSettingDropdownButton, GlobalPageElements.drp_ShowNumberOfListDropdown);
		if (!listingStyle.contains("ThumbView")) {
			if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink))
				clickElementAndWait(ProjectsTab.lnk_ListView);
		}
		else {
			if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink))
				clickElementAndWait(ProjectsTab.lnk_ThumbView);
		}
		if (isDisplayed(GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton))
			clickElementAndWait(GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton);
	}

	/**
	 * Gets the unread comment count.
	 *
	 * @param locator the locator
	 * @return the unread comment count
	 */
	protected String getUnreadCommentCount(By locator) {
		return findElement(locator).getAttribute("title").split(": ")[1].trim();
	}

	/**
	 * Click on admin box with text.
	 *
	 * @param boxText the box text
	 */
	protected void clickOnAdminBoxWithText(String boxText) {

		if (boxText.equalsIgnoreCase(AdoddleCommonStringPool.AUTO_FETCH_ATTRIBUTES))
			clickElementAndWait(AdminTab.img_AutoFetchAttributesBox);
		else if (boxText.equalsIgnoreCase(AdoddleCommonStringPool.MANAGE_USERS))
			clickElementAndWait(AdminTab.img_ManageUsersBox);
		else if (boxText.equalsIgnoreCase(AdoddleCommonStringPool.DESIGN_LAYOUT))
			clickElementAndWait(AdminTab.img_DesignLayoutBox);
		else if (boxText.equalsIgnoreCase(AdoddleCommonStringPool.MANAGE_NOTICE))
			clickElementAndWait(AdminTab.img_DesignLayoutBox);
	}

	/**
	 * Click create form.
	 *
	 * @param createFormLinkText the create form link text
	 */
	public void clickCreateForm(String createFormLinkText) {
		verifyElementText(ProjectFormsTab.btn_CreateForm, createFormLinkText);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
	}

	/**
	 * Input form title and description rfi form string.
	 *
	 * @param subject the subject
	 * @return the string
	 */
	public String inputFormTitleAndDescriptionRFIForm(String subject) {
		String epochTime = dateUtils.getEpoch();
		String formTitle = subject + epochTime;
		collectionDataMap.put("ORI Message:: ", formTitle);
		String formDescription = javaUtils.getRandomString(30);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, formTitle);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, formDescription);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		return formTitle;
	}

	/**
	 * Click first element on file listing.
	 *
	 * @param field the field
	 */
	public void clickFirstElementOnFileListing(String field) {
		if(field.equalsIgnoreCase("File Name"))
			clickElementAndWait(FilesTab.lnk_DocListingFirstFileName);
		else if(field.equalsIgnoreCase("Doc Ref"))
			clickElementAndWait(FilesTab.lnk_DocListingFirstDocRef);
	}


	/**
	 * Search user group entity.
	 *
	 * @param entityTitle the entity title
	 */
	public void searchUserGroupEntity(String entityTitle) {
		clickElementAndWaitForElement(DiscussionsTab.img_AMessageTabSearchOption, DiscussionsTab.txt_AMessageTabSearch);
		sendKeys(DiscussionsTab.txt_AMessageTabSearch, entityTitle);
		waitUntilElementContainsText(DiscussionsTab.lbl_AMessageTabSearchFirstRecord, entityTitle, 10);
		clickElementAndWaitForElement(DiscussionsTab.lbl_AMessageTabSearchFirstRecord, DiscussionsTab.lbl_AmessageTabChatPanelHeader);
		waitUntilElementContainsText(DiscussionsTab.lbl_AmessageTabChatPanelHeader, entityTitle);
	}
}