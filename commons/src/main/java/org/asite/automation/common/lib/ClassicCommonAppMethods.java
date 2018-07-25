package org.asite.automation.common.lib;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.DistributionPage;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicFormListingLocators.FormListingPage;
import org.asite.automation.CommonLocators.ClassicGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicLoginLocators.LoginPage;
import org.asite.automation.CommonLocators.ClassicMyHomeLocators.MyHomePage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.ClassicCommonStringPool;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassicCommonMethods.
 * 
 * @author jasminprajapati
 */
@SuppressWarnings("deprecation")
public class ClassicCommonAppMethods extends AutomationSeleniumExtendedLibrary {

	/** The in stream. */
	FileInputStream								inStream				= null;

	/** The log. */
	final public static Logger					log						= Logger.getLogger(ClassicCommonAppMethods.class.getName());

	/** The rnd. */
	final private static Random					rnd						= new Random();

	/** The epoch. */
	public String								epoch					= getCurrentDate();

	/** The print window name. */
	private static String						windowTitle, uploadWindowName, appletWindowName;

	/** The Constant charList. */
	public static final String					charList				= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	/** The node url. */
	public String								nodeURL					= null;

	/** The training flag. */
	public static boolean						trainingFlag			= false;

	/** The project title. */
	public static String						projectTitle;

	/** The data center. */
	public static String						dataCenter;

	/** The browser. */
	public static String						browser					= ResourceHandler.loadProperty("browser");

	/** The configfile. */
	public static String						configfile				= null;

	/** The Asession ID. */
	private static String						AsessionID				= "ASESSIONID";

	/** The cucumber report Map key. */
	protected static String						cucumberReportMapKey;

	/** The main window. */
	protected static String						superWindow				= null;

	/** The Cucumber Report Map. */
	public static HashMap<String, List<String>>	cucumberReportMap		= new HashMap<String, List<String>>();

	/** The Cucumber Report Data List. */
	protected static List<String>				cucumberReportData		= new ArrayList<String>();

	/** The report map keys list. */
	protected static List<String>				reportMapKeysList		= new ArrayList<String>();

	/** The Cucumber Report Headers. */
	public static List<String>					cucumberReportHeaders	= Arrays.asList(AdoddleCommonStringPool.REPORT_FEATURETITLE, AdoddleCommonStringPool.AUTO_TEST_SCENARIO, AdoddleCommonStringPool.DATACENTER, AdoddleCommonStringPool.REPORT_SCENARIO_STATUS, AdoddleCommonStringPool.REPORT_SCENARIO_EXECUTION_TIME);

	/**
	 * Sets the up.
	 * 
	 * @param browser
	 *            the new up
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void setUp(String browser) throws IOException {
		/*
		 * DesiredCapabilities capabilities = new DesiredCapabilities(); nodeURL = ClassicCommonStringPool.NODE_URL_PREFIX + System.getProperty("selenium.server.hub.ip") + ":" + System.getProperty("selenium.server.hub.port") + ClassicCommonStringPool.NODE_URL_SUFFIX; if (browser != null && driver != null) { try { tearDown(); } catch (Throwable e) { log.info("driver setup is initiated; killing any open instances"); } }
		 * 
		 * if (browser.equalsIgnoreCase("IE")) { capabilities = DesiredCapabilities.internetExplorer(); capabilities.setBrowserName("internet explorer"); capabilities.setPlatform(Platform.WINDOWS); capabilities.setCapability("nativeEvents", false); capabilities.setCapability("enablePersistentHover", true); capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); if (nodeFlag) capabilities.setCapability("applicationName", "autoIt"); else capabilities.setCapability("applicationName", "webApp"); System.setProperty("webdriver.ie.driver", new File("./src/test/resources/internetexplorer.exe") .getCanonicalPath().toString()); driver = new RemoteWebDriver(new URL(nodeURL), capabilities); ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector()); }
		 * 
		 * else if (browser.equalsIgnoreCase("FF")) { log.info("Opening firefox browser"); capabilities = DesiredCapabilities.firefox(); FirefoxProfile ffProfile = new FirefoxProfile(); ffProfile.setPreference("security.enable_java", false); ffProfile.setAcceptUntrustedCertificates(true); ffProfile.setPreference("browser.download.useDownloadDir", false); ffProfile.setPreference("plugin.state.java", 2); ffProfile.setAcceptUntrustedCertificates(true); capabilities.setCapability(FirefoxDriver.PROFILE, ffProfile); if (nodeFlag) capabilities.setCapability("applicationName", "autoIt"); else capabilities.setCapability("applicationName", "webApp"); log.info(capabilities.getCapability("applicationName")); driver = new RemoteWebDriver(new URL(nodeURL), capabilities); ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector()); driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 * 
		 * }
		 * 
		 * else if (browser.equalsIgnoreCase("CHROME")) { log.info("Opening chrome broser"); capabilities = DesiredCapabilities.chrome(); capabilities.setBrowserName("chrome"); capabilities.setPlatform(Platform.WINDOWS); if (nodeFlag) capabilities.setCapability("applicationName", "autoIt"); else capabilities.setCapability("applicationName", "webApp"); Map<String, Object> prefs = new HashMap<String, Object>(); prefs.put("download.prompt_for_download", true); ChromeOptions options = new ChromeOptions(); options.setExperimentalOption("prefs", prefs); options.addArguments("--disable-extensions"); options.addArguments("--disable-bundled-ppapi-flash"); options.addArguments("--disable-internal-flash"); // options.addArguments("--disable-plugins-discovery"); capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors")); log.info(capabilities.getCapability("applicationName")); capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		 * System.setProperty("webdriver.chrome.driver", new File("./src/test/resources/chromedriver.exe") .getCanonicalPath().toString()); driver = new RemoteWebDriver(new URL(nodeURL), capabilities); ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector()); driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 * 
		 * } initWebDriverWait(); maximizeWindow(); navigateURL(System.getProperty("application.url")); log.info("Waiting for loading login page"); waitForCompletePageLoad(); setCurrentNodeInternetProtocol(System.getProperty ("selenium.server.hub.ip"), Integer.parseInt(System.getProperty("selenium.server.hub.port"))); sysUtils.clearJavaCache(nodeIP);
		 */

		TestDriverFactory.getInstance().setUp(browser);

	}

	/**
	 * Sets the current node internet protocol.
	 * 
	 * @param hubIP
	 *            the hub ip
	 * @param hubPort
	 *            the hub port
	 * @throws JSONException
	 *             the JSON exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings({ "resource" })
	public void setCurrentNodeInternetProtocol(String hubIP, int hubPort) throws JSONException, ParseException, IOException {
		final String	ipAccessSlash			= "\\\\";
		HttpHost host = new HttpHost(hubIP, hubPort);
		DefaultHttpClient client = new DefaultHttpClient();
		URL testSessionApi = new URL("http://" + hubIP + ":" + hubPort + "/grid/api/testsession?session=" + ((RemoteWebDriver) getWebDriver()).getSessionId());
		BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm());
		HttpResponse response = client.execute(host, r);
		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		String proxyID = object.get("proxyId").toString();
		log.info(proxyID.split("//")[1].split(":")[0]);
		System.setProperty("selenium.execution.node.ip", ipAccessSlash + proxyID.split("//")[1].split(":")[0]);
		nodeIP = System.getProperty("selenium.execution.node.ip");
	}

	/**
	 * Retry click element and wait for element.
	 * 
	 * @param clickLocator
	 *            the click locator
	 * @param waitLocator
	 *            the wait locator
	 * @param retries
	 *            the retries
	 */
	public void retryClickElementAndWaitForElement(By clickLocator, By waitLocator, int retries) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), Integer.parseInt(ResourceHandler.loadProperty("wait.timeout")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickLocator));

		for (int index = 0; index < retries; index++) {
			try {
				findElement(clickLocator).click();
				waitUntilElementIsDisplayed(waitLocator);
				if (isDisplayed(waitLocator))
					break;
			}
			catch (Exception e) {
				log.info("failure in retry click and wait for element: " + index);
			}
		}
	}

	/**
	 * Login.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	/*
	 * Classic Implementation of loging into Application which is same as Adoddle.
	 */
	public void login(String username, String password) {
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		switchIframe(LoginPage.frm_Iframe);
		waitUntilElementIsDisplayed(LoginPage.txt_AsiteUsername);
		clear(LoginPage.txt_AsiteUsername);
		findElement(LoginPage.txt_AsiteUsername).sendKeys(username);
		clear(LoginPage.txt_AsitePassword);
		findElement(LoginPage.txt_AsitePassword).sendKeys(password);
		clickElementAndWait(LoginPage.btn_AsiteLogin);
		switchDefault();
		if (getCurrentWindowURL().contains("announcement")) {
			clickElementAndWait(GlobalPageElements.btn_AsiteAnnounceDismiss);
		}

		if (username.contains("aparikh")) {
			try {
				Assert.assertTrue(isDisplayed(LandingPage.btn_UserProfile));
			}
			catch (Throwable t) {
				reloadPage();
				waitForCompletePageLoad();
			}
			trainingFlag = true;
			switchToClassicView();
		}
		waitForCompletePageLoad();
		setDriverCookies(getDriverCookies());
		superWindow = getCurrentWindow();
	}

	/**
	 * Sets the driver cookies.
	 * 
	 * @param c
	 *            the new driver cookies
	 */
	private void setDriverCookies(Set<Cookie> c) {
		for (Cookie cookie : c) {
			if (AsessionID.toLowerCase().equalsIgnoreCase(cookie.getName().toLowerCase())) {
				setASessionID(cookie.getValue());
			}
		}
	}

	/**
	 * Sets the a session ID.
	 * 
	 * @param AsessionID
	 *            the new a session ID
	 */
	@SuppressWarnings("static-access")
	private void setASessionID(String AsessionID) {
		this.AsessionID = AsessionID;
	}

	/**
	 * Swith To Classic View.
	 */
	private void switchToClassicView() {
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_ClassicView);
		clickElementAndWait(LandingPage.lnk_ClassicView);
		waitForCompletePageLoad();
		clickElementAndWait(MyHomePage.tab_Training);
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);

	}

	/**
	 * Verify login.
	 */
	/* Classic Implementation of verifying Login is Successful */
	protected void verifyLogin() {
		try {
			Assert.assertTrue(isDisplayed(By.cssSelector("div[id='topNavLinks'] a[class='topMenu'][title='My Home']")));
		}
		catch (Throwable t) {
			log.error("Exception caught in Login");
		}

	}

	/**
	 * Sets the up selenium server.
	 * 
	 * @param hub
	 *            the hub
	 * @param nodes
	 *            the nodes
	 * @param browser
	 *            the browser
	 */
	public void setUpSeleniumServer(String hub, String nodes, String browser) {
		log.info("To be done");
	}

	/**
	 * Checks if is data center available.
	 * 
	 * @param dc_center
	 *            the dc_center
	 * @return true, if is data center available
	 */
	protected boolean isDataCenterAvailable(String dc_center) {
		dataCenter = dc_center;
		List<String> dataCenters = new ArrayList<String>();
		dataCenters = getFileList(ResourceHandler.getPropertyValue("application.test.dcs"));
		return dataCenters.contains(dc_center) || dataCenters.contains(dc_center.substring(0, 2));
	}

	/**
	 * Sets up Environment System Properties.
	 */

	private void setEnvironmentProperty() {
		if (System.getProperty("application.url") == null)
			System.setProperty("application.url", ResourceHandler.getPropertyValue("application.url"));
		if (System.getProperty("selenium.server.hub.ip") == null)
			System.setProperty("selenium.server.hub.ip", ResourceHandler.getPropertyValue("selenium.server.hub.ip"));
		if (System.getProperty("selenium.server.hub.port") == null)
			System.setProperty("selenium.server.hub.port", ResourceHandler.loadProperty("selenium.server.hub.port"));

		if (System.getProperty("application.url").contains(AdoddleCommonStringPool.ENV_QA1_HOST))
			setConfigFile(ResourceHandler.getPropertyValue("qa1.environment.properties"));
		else if (System.getProperty("application.url").contains(AdoddleCommonStringPool.ENV_QA2_HOST))
			setConfigFile(ResourceHandler.getPropertyValue("qa2.environment.properties"));
		else if (System.getProperty("application.url").contains(AdoddleCommonStringPool.ENV_LIVE_HOST))
			setConfigFile(ResourceHandler.getPropertyValue("production.environment.properties"));
	}

	/**
	 * Sets the up environment test project.
	 * 
	 * @param dc
	 *            the new up environment test project
	 */
	/* [Classic Implementation of setting Test Project.] */
	public void setSystemProperties(String dc) {
		setEnvironmentProperty();

		if (System.getProperty("primary.username") == null)
			System.setProperty("primary.username", ResourceHandler.loadProperty("primary.username"));
		if (System.getProperty("primary.password") == null)
			System.setProperty("primary.password", ResourceHandler.loadProperty("primary.password"));
		if (System.getProperty("secondary.username") == null)
			System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.username"));
		if (System.getProperty("secondary.password") == null)
			System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.password"));

		if (dc.equalsIgnoreCase("UK")) {
			if (trainingFlag)
				System.setProperty("global.test.project", ResourceHandler.loadProperty("test.training.project"));
			else
				System.setProperty("global.test.project", ResourceHandler.loadProperty("test.uk.project"));
		}
		else if (dc.equalsIgnoreCase("US"))
			System.setProperty("global.test.project", ResourceHandler.loadProperty("test.us.project"));
		else if (dc.equalsIgnoreCase("AUS"))
			System.setProperty("global.test.project", ResourceHandler.loadProperty("test.aus.project"));

	}

	/**
	 * Sets the project.
	 * 
	 * @param project
	 *            the new project
	 */
	public void setProject(String project) {
		clickLinkWithText(project);
	}

	/**
	 * Sign out.
	 */
	public void signOut() {
		if(getWebDriver() != null) {
			if (getWindowHandles().size() > 1) {
				if (!getCurrentWindow().equalsIgnoreCase(superWindow))
					closeCurrentWindow();
			}
			switchWindow(superWindow);
		

			try {
				switchDefault();
				waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
				waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
				clickElement(LandingPage.lnk_SignOut);
				acceptAlert();
				switchDefault();
				waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
			}
			catch (Throwable t) {
				log.info("LOGOUT: Operation was not completed; closing browser with no logging out");
			}
		}
	}

	/**
	 * Search files.
	 * 
	 * @param fileName
	 *            the file name
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void searchFiles(String fileName) {
		waitUntilElementIsDisplayed(DocListingPage.txt_DocListingDocRefInput);
		findElement(DocListingPage.txt_DocListingDocRefInput).clear();
		sendKeys(DocListingPage.txt_DocListingDocRefInput, fileName);
		log.info("Searching classic document matching with title/docRef/fileName :" + fileName);
		waitForCompletePageLoad();
		sendKeys(DocListingPage.txt_DocListingDocRefInput, Keys.ENTER);
		waitForCompletePageLoad();
		log.info("Number of file(s) found after document search :" + getCount(DocListingPage.css_DocListingDocRefList));

	}

	/**
	 * Search files using title.
	 * 
	 * @param docTitle
	 *            the doc title
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void searchFilesUsingTitle(String docTitle) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
		waitUntilElementIsDisplayed(DocListingPage.txt_DocListingTitleInput);
		findElement(DocListingPage.txt_DocListingTitleInput).clear();
		sendKeys(DocListingPage.txt_DocListingTitleInput, docTitle);
		log.info("Searching classic document matching with title :" + docTitle);
		waitForCompletePageLoad();
		sendKeys(DocListingPage.txt_DocListingTitleInput, Keys.TAB);
		waitForCompletePageLoad();
		clickElementAndWait(DocListingPage.btn_DocListingSearch);
		log.info("Number of file(s) found after document title search :" + getCount(DocListingPage.css_DocListingDocRefList));

	}

	/**
	 * Navigate to work space.
	 * 
	 * @param projectName
	 *            the project name
	 */
	public void navigateToWorkSpace(String projectName) {
		clickLinkWithText(projectName);
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

	/**
	 * Search projects.
	 * 
	 * @param projectName
	 *            the project name
	 */
	public void searchProjects(String projectName) { }

	/**
	 * Search discussions.
	 * 
	 * @param commentTitle
	 *            the comment title
	 */
	public void searchDiscussions(String commentTitle) { }

	/**
	 * Search forms.
	 * 
	 * @param formName
	 *            the form name
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void searchForms(String formName) {
		waitUntilElementIsDisplayed(FormListingPage.txt_FormListingFormTitle);
		findElement(FormListingPage.txt_FormListingFormTitle).clear();
		sendKeys(FormListingPage.txt_FormListingFormTitle, formName);
		log.info("Searching classic form matching with title/formName :" + formName);
		waitForCompletePageLoad();
		sendKeys(FormListingPage.txt_FormListingFormTitle, Keys.ENTER);
		waitForCompletePageLoad();
		log.info("Number of form(s) found after form search :" + getCount(FormListingPage.css_ProjectFormListingCount));

	}

	/**
	 * Search models.
	 * 
	 * @param modelName
	 *            the model name
	 */
	public void searchModels(String modelName) { }

	/**
	 * Perform file action.
	 * 
	 * @param actionString
	 *            the action string
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void performFileAction(String actionString) throws InterruptedException, IOException {

		if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.status.change")))
			performStatusChange();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.action")))
			performSimpleAction();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.comment.coordination")))
			performCommentCoordination();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.comment")))
			performSimpleComment();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.acknowledgement")))
			performAcknowledgement();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.distribution")))
			performDistribution(ResourceHandler.loadProperty("distribution.user"));
		else if (actionString.equalsIgnoreCase(ClassicCommonStringPool.FOR_INFORMATION))
			performInformation();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.publishing")))
			performPublish(resourceUtils.getTestDataFilePath() + getCurrentDate() + ClassicCommonStringPool.TXT_EXTENSION);
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("file.action.for.comment.incorporation")))
			performCommentIncorporation();
	}

	/**
	 * Perform form action.
	 * 
	 * @param actionString
	 *            the action string
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
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
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("form.action.for.information")))
			performFormForInformation();
		else if (actionString.equalsIgnoreCase(ResourceHandler.loadProperty("for.comment.incorporation")))
			performCommentIncorporation();
	}

	/**
	 * Perform form assign status.
	 */
	private void performFormAssignStatus() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form attach docs.
	 */
	private void performFormAttachDocs() {
		log.info("TESTDATA: TestData to be created");

	}

	/**
	 * Perform form action.
	 */
	private void performFormAction() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form distribution.
	 */
	private void performFormDistribution() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form respond.
	 */
	private void performFormRespond() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form for information.
	 */
	private void performFormForInformation() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform form acknowledgement.
	 */
	private void performFormAcknowledgement() {
		log.info("TESTDATA: TestData to be created");
	}

	/**
	 * Perform publish.
	 * 
	 * @param path
	 *            the path
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void performPublish(String path) { }

	/**
	 * Perform status change.
	 */
	private void performStatusChange() { }

	/**
	 * Perform simple action.
	 */
	private void performSimpleAction() { }

	/**
	 * Perform comment incorporation.
	 */
	private void performCommentIncorporation() { }

	/**
	 * Perform comment coordination.
	 */
	private void performCommentCoordination() {	}

	/**
	 * Perform simple comment.
	 */
	private void performSimpleComment() { }

	/**
	 * Perform acknowledgement.
	 */
	private void performAcknowledgement() {	}

	/**
	 * Perform distribution.
	 * 
	 * @param distributeTo
	 *            the distribute to
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void performDistribution(String distributeTo) { }

	/**
	 * Perform information.
	 */
	private void performInformation() { }

	/**
	 * Sets the clipboard data.
	 * 
	 * @param string
	 *            the new clipboard data
	 * @throws UnsupportedFlavorException
	 *             the unsupported flavor exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void setClipboardData(String string) {
		StringSelection stringselection = new StringSelection(string);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringselection, null);
	}

	/**
	 * Upload file robot.
	 * 
	 * @param fileLocation
	 *            the file location
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void uploadFileRobot(String fileLocation) {

		try {
			setClipboardData(fileLocation);

			Robot robot = new Robot();
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(100);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_V);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(100);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_ENTER);

		}
		catch (Exception exp) {
			log.info("failure in uploading file using robot");
		}
	}

	/**
	 * Gets the file list.
	 * 
	 * @param files
	 *            the files
	 * @return the file list
	 */
	private List<String> getFileList(String files) {
		List<String> fileList = new ArrayList<String>();
		String[] uploadFiles = files.split(",");

		for (String uploadFile : uploadFiles) {
			fileList.add(uploadFile.trim());
		}

		return fileList;
	}

	/**
	 * Gets the current date.
	 * 
	 * @return the current date
	 */
	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Gets the current date time with zone.
	 * 
	 * @param format
	 *            the format
	 * @param timeZone
	 *            the time zone
	 * @return the current date time with zone
	 */
	public String getCurrentDateTimeWithZone(String format, String timeZone) {
		Date today = new Date();
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		df.format(today);
		return df.format(today);
	}

	/**
	 * Gets the file size.
	 * 
	 * @param fileLocation
	 *            the file location
	 * @return the file size
	 */
	public double getFileSize(String fileLocation) {
		File file = new File(fileLocation);
		if (file.exists()) {
			double bytes = file.length();
			return bytes / 1024;
		}
		return 0;

	}

	/**
	 * Verify pop up with text.
	 * 
	 * @param headerText
	 *            the header text
	 */
	public void verifyPopUpWithText(String headerText) {
		Assert.assertTrue(isDisplayed(GlobalPageElements.pop_PopUpElement));
		try {
			Assert.assertTrue(getElementText(GlobalPageElements.pop_PopUpHeader1).contains(headerText));
		}
		catch (Throwable t) {
			Assert.assertTrue(getElementText(GlobalPageElements.pop_PopUpHeader2).contains(headerText));
		}
	}

	/**
	 * Deactivate file.
	 * 
	 * @param fileName
	 *            the file name
	 */
	public void deactivateFile(String fileName) {

	}

	/**
	 * Verify asite menu list.
	 * 
	 * @param linkMenu
	 *            the link menu
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void verifyAsiteMenuList(String linkMenu) {
		Assert.assertEquals(getElementText(GlobalPageElements.lnk_ActiveTab), linkMenu.trim());
		waitForCompletePageLoad();
	}

	/**
	 * Compare map list.
	 * 
	 * @param Map1
	 *            the map1
	 * @param Map2
	 *            the map2
	 */
	public void compareMapList(Map<String, String> Map1, Map<String, String> Map2) {
		for (String key : Map1.keySet()) {
			if (Map1.get(key).equals(Map2.get(key))) {
				log.info("key :" + key + " value :" + Map2.get(key));
			}
			else {
				Assert.assertTrue("Map \"Key-Value\" not matched",false);
			}
		}
	}

	/**
	 * Gets the random number.
	 * 
	 * @param digCount
	 *            the dig count
	 * @return the random number
	 */
	public static String getRandomNumber(int digCount) {
		StringBuilder sb = new StringBuilder(digCount);
		for (int i = 0; i < digCount; i++)
			sb.append((char) ('0' + (rnd.nextInt(9) + 1)));
		return sb.toString();
	}

	/**
	 * Handle Batch Print Dialog.
	 * 
	 * @param filePath1
	 *            the file path1
	 * @param filePath2
	 *            the file path2
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 * 
	 * 
	 *             public void handleBatchPrintDialog(String filepath, String projectType, String nodeIP) throws IOException, InterruptedException { String psExecPath = new File("./src/test/resources/PsExec.exe").toString(); log.info("BatchFile path::" + filepath); String remoteDomain = ResourceHandler.getPropertyValue("remote.domain"); String browser = ResourceHandler.getPropertyValue("browser"); String autoITPath = ResourceHandler.loadProperty( "autoit.batch.print.executable.path"); String remoteUser = System.getProperty("remote.user"); String remotePassword = System.getProperty("remote.password"); remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("remote.user"); remotePassword = (remotePassword != null) ? remotePassword : ResourceHandler .getPropertyValue("remote.password"); String cmd = psExecPath + " -i " + nodeIP + " -u " + remoteDomain + "\\" + remoteUser + " -p " + remotePassword + " " + autoITPath + " " + browser + " " + filepath + " " +
	 *             projectType + ""; System.out.println("Command for Remote AutoIt execution:" + cmd); Process p = Runtime.getRuntime().exec(cmd); p.waitFor(); }
	 */

	/**
	 * Download Batch Printing files.
	 * 
	 * @param filePath1
	 *            the file1 path
	 * @param filePath2
	 *            the file2 path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void handleBatchPrintWindows(String filePath1, String filePath2) throws IOException, InterruptedException {

		String psExecPath = new File("./src/test/resources/PsExec.exe").toString();
		String remoteDomain = ResourceHandler.getPropertyValue("remote.domain");
		String autoITPath = ResourceHandler.loadProperty("autoit.batch.print.executable.path");
		String remoteUser = ResourceHandler.getPropertyValue("remote.user");
		String remotePassword = ResourceHandler.getPropertyValue("remote.password");
		String printWindowName = "Print Setup";
		String downloadWindowName = "Save the file as";
		String cmd = psExecPath + " -i " + nodeIP + " -u " + remoteDomain + "\\" + remoteUser + " -p " + remotePassword + " " + autoITPath + " " + "\"" + filePath1 + "\" " + "\"" + filePath2 + "\" " + "\"" + printWindowName + "\"";
		log.info("Command for Remote AutoIt execution:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
		InputStream inputStream = p.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}

	}

	/**
	 * Un zip file.
	 * 
	 * @param zipFile
	 *            the zip file
	 * @param outputFolder
	 *            the output folder
	 */
	public void unZipFile(String zipFile, String outputFolder) {
		try {

			File folder = new File(outputFolder);
			if (!folder.exists()) {
				AutomationAssert.verifyTrue("Failed to create folder :"+folder.toString(), folder.mkdir());
			}

			ZipInputStream zipStream = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry zEntry;

			while ((zEntry = zipStream.getNextEntry()) != null) {
				log.info("Unzipping " + zEntry.getName() + " at " + outputFolder);

				if (zEntry.isDirectory())
					hanldeDirectory(zEntry.getName());
				else {
					FileOutputStream fout = new FileOutputStream(outputFolder + "/" + zEntry.getName());
					BufferedOutputStream bufout = new BufferedOutputStream(fout);
					byte[] buffer = new byte[1024];
					int read = 0;
					while ((read = zipStream.read(buffer)) != -1) {
						bufout.write(buffer, 0, read);
					}

					zipStream.closeEntry();
					bufout.close();
					fout.close();
				}
			}
			zipStream.close();
			log.info("Unzipping complete. path :  " + outputFolder);
		}
		catch (Exception e) {
			log.error("Unzipping failed");
			e.printStackTrace();
		}

	}

	/**
	 * Hanlde directory.
	 * 
	 * @param dir
	 *            the dir
	 */
	private void hanldeDirectory(String dir) {
		File f = new File(dir);
		if (!f.isDirectory()) {
			AutomationAssert.verifyTrue("Failed to create directories : "+f.toString(), f.mkdirs());
		}
	}

	/**
	 * Creates the directory.
	 * 
	 * @param folderPath
	 *            the folder path
	 * @return the string
	 */
	public String createDirectory(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				log.info("Directory is created!");
			}
			else {
				log.error("Failed to create directory!");
			}
		}
		return folderPath;
	}

	/**
	 * Navigate tab by text.
	 * 
	 * @param tabName
	 *            the tab name
	 */
	public void navigateTabByText(String tabName) {
		clickLinkWithText(tabName);
		waitForCompletePageLoad();
	}

	/**
	 * Verify file context menu opened.
	 */
	public void verifyFileContextMenuOpened() {

	}

	/**
	 * Verify window popup with text.
	 * 
	 * @param popupText
	 *            the popup text
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void verifyWindowPopupWithText(String popupText) throws IOException, InterruptedException {
		String windowCheck = "true";
		String psExecPath = new File("./src/test/resources/PsExec.exe").toString();
		String remoteDomain = ResourceHandler.getPropertyValue("remote.domain");
		String browser = ResourceHandler.loadProperty("browser");
		String autoITPath = ResourceHandler.loadProperty("autoit.download.executable.path");
		String remoteUser = ResourceHandler.getPropertyValue("remote.user");
		String remotePassword = ResourceHandler.getPropertyValue("remote.password");
		String cmd = psExecPath + " -i " + nodeIP + " -u " + remoteDomain + "\\" + remoteUser + " -p " + remotePassword + " " + autoITPath + " " + browser + " " + windowCheck + " " + popupText + "";
		log.info("Command for Remote AutoIt execution:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
	}

	/**
	 * Compare list.
	 * 
	 * @param list1
	 *            the list1
	 * @param list2
	 *            the list2
	 */
	public void compareList(List<String> list1, List<String> list2) {
		if (list1.size() == list2.size()) {
			boolean flag = false;
			for (String valueList1 : list1) {
				for (String valueList2 : list2) {
					if (valueList2.equalsIgnoreCase(valueList1)) {
						log.error("verified valueList2 :" + valueList2);
						log.error("verified valueList1 :" + valueList1);
						flag = true;
						break;
					}
					else {
						log.error("not Verified");
						flag = false;
					}
				}
				if (!flag) {
					Assert.assertTrue("List Value not Matched",false);
				}
			}
		}
		else {
			Assert.assertTrue("List size not Matched", false);
		}
	}

	/**
	 * Handle applet upload.
	 * 
	 * @param winTitle
	 *            the win title
	 */

	/**
	 * Verify page title.
	 * 
	 * @param winTitle
	 *            the win title
	 */
	public void verifyPageTitle(String winTitle) {
		handleAlertPopup();
		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		log.info("Page title Actual::" + getElementText(GlobalPageElements.lbl_PageTitle));
		log.info("Page title Expected::" + winTitle);
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(winTitle));
	}

	/**
	 * Switch to third window.
	 * 
	 * @param parentHandle1
	 *            the parent handle1
	 * @param parentHandle2
	 *            the parent handle2
	 */
	public void switchToThirdWindow(String parentHandle1, String parentHandle2) {
		waitForSwitchWindow(3);
		Set<String> handles = getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentHandle1) && !windowHandle.equals(parentHandle2)) {
				switchWindow(windowHandle);
				break;
			}
		}

	}

	/**
	 * Verify doc listing page.
	 * 
	 * @param folderTitle
	 *            the folder title
	 */
	public void verifyDocListingPage(String folderTitle) {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lbl_DocListingFolderPath);
		waitUntilElementContainsText(DocListingPage.lbl_DocListingFolderPath, folderTitle);

	}

	/**
	 * Mouse hover and click element.
	 * 
	 * @param hoverLocator
	 *            the hover locator
	 * @param clickLocator
	 *            the click locator
	 */
	public void mouseHoverAndClickElement(By hoverLocator, By clickLocator) {
		String javaScript = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";
		waitUntilElementIsDisplayed(hoverLocator);
		((JavascriptExecutor) getWebDriver()).executeScript(javaScript, findElement(hoverLocator));
		waitUntilElementIsDisplayed(clickLocator);
		clickElement(clickLocator);

	}

	/**
	 * Swith multiple frames.
	 */
	public void switchMultiFrames() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

	/**
	 * Select group type.
	 */
	public void selectGroupType() {
		clickElement(DistributionPage.drp_DocDistributionGroupType);
		selectByVisibleText(DistributionPage.drp_DocDistributionGroupType, "Companies");
		waitUntilElementIsDisplayed(DistributionPage.sel_DocDistributionGroupCompaniesFirstOption);
		waitUntilElementIsDisplayed(DistributionPage.sel_DocDistributionGroupCompanies);
		waitUntilDropdownContainsValue(DistributionPage.sel_DocDistributionGroupCompanies, ResourceHandler.loadProperty("auto.test.classic.organization"), 60);
		selectByVisibleText(DistributionPage.sel_DocDistributionGroupCompanies, ResourceHandler.loadProperty("auto.test.classic.organization"));
		waitUntilElementIsDisplayed(DistributionPage.sel_DocDistributionUsersFirstOption);
	}

	/**
	 * Click on all documents.
	 * 
	 * @param wsDocs
	 *            the ws docs
	 */
	public void clickOnAllDocuments(String wsDocs) {
		switchMultiFrames();
		clickLinkWithText(wsDocs);
		waitForCompletePageLoad();
	}

	/**
	 * Verify file listing.
	 */
	public void verifyFileListing() {
		switchDefault();
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingFirstFileDocRef);
		Assert.assertTrue(getCount(DocListingPage.css_NumberOfFiles) > 1);
	}

}