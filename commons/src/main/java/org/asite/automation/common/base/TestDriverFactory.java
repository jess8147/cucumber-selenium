package org.asite.automation.common.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationErrors;
import org.asite.automation.common.impl.ImplChromeCapabilities;
import org.asite.automation.common.impl.ImplFFCapabilities;
import org.asite.automation.common.impl.ImplIECapabilities;
import org.asite.automation.common.lib.AutomationSeleniumExtendedLibrary;
import org.asite.automation.common.resources.BaseCapabilitiesCollection.ChromeCapabilities;
import org.asite.automation.common.resources.BaseCapabilitiesCollection.IECapabilities;
import org.asite.automation.common.utils.DateUtils;
import org.asite.automation.common.utils.ResourceUtils;
import org.asite.automation.common.utils.StringUtils;
import org.asite.automation.common.utils.SystemUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating TestDriver objects.
 * @author jasminprajapati
 */
@SuppressWarnings({ "deprecation" })
public class TestDriverFactory {

	/** The desired capabilities. */
	static DesiredCapabilities			desiredCapabilities	= new DesiredCapabilities();

	/** The base capabilities. */
	static TestBaseCapabilities			baseCaps			= null;

	/** The str utils. */
	StringUtils							strUtils			= new StringUtils();

	/** The sys utils. */
	SystemUtils							sysUtils			= new SystemUtils();

	/** The instance. */
	private static TestDriverFactory	instance			= null;

	/** The mutex. */
	private static Object				mutex				= new Object();

	/** The scenario start time. */
	public static String				scenarioStartTime	= "";

	/** The node flag. */
	private  static boolean 					nodeFlag			= false;

	/** The node flag. */
	public static ThreadLocal<Boolean>			nFlag				= new ThreadLocal<Boolean>();

	/** The scenario. */
	public static Scenario				scenario;


	/**
	 * Instantiates a new test driver factory.
	 */
	public TestDriverFactory() {}

	/**
	 * The Enum Browser.
	 */
	private enum Browser {

		/** The ff. */
		FF("Firefox"),
		/** The chrome. */
		CHROME("chrome"),
		/** The ie. */
		IE("internet explorer"),
		/** The headless. */
		HEADLESS("headless");
		
		/** The title. */
		private final String	title;

		/**
		 * Instantiates a new scenarios.
		 * 
		 * @param title
		 *            the title
		 */
		private Browser(String title) {
			this.title = title;
		}

		/**
		 * Gets the enum from string.
		 * 
		 * @param str
		 *            the str
		 * @return the enum from string
		 */
		@SuppressWarnings("unused")
		public static Browser getEnumFromString(String str) {
			for (Browser b : Browser.values()) {
				if (b.title.equalsIgnoreCase(str)) {
					return b;
				}
			}
			return null;
		}
	}

	/** The browser. */
	Browser					browser	= null;

	/** The driver. */
	public static WebDriver	driver	= null;

	/**
	 * Gets the node url.
	 * 
	 * @return the node url
	 */
	private static String getNodeURL() {
		/** The node url. */
		return "http://" + ResourceUtils.getSeleniumServer() + ":" + ResourceUtils.getSeleniumServerPort() + "/wd/hub";
	}

	/**
	 * Instantiates a new test driver factory.
	 * 
	 * @param b
	 *            the b
	 */
	public TestDriverFactory(String b) {
		browser = Browser.valueOf(b);

		switch (browser) {
			case FF:
				baseCaps = new ImplFFCapabilities();
			case CHROME:
				baseCaps = new ImplChromeCapabilities();
			case IE:
				baseCaps = new ImplIECapabilities();
			default:
				break;
		}
	}

	/**
	 * Gets the single instance of TestDriverFactory.
	 * 
	 * @return single instance of TestDriverFactory
	 */
	public static TestDriverFactory getInstance() {
		if (instance == null)
			synchronized (mutex) {
				if (instance == null)
					instance = new TestDriverFactory();
			}
		return instance;
	}

	/**
	 * Sets the up.
	 * 
	 * @param browserName
	 *            the browser name
	 * @return the web driver
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void 	setUp(String browserName) throws IOException {

		browser = Browser.valueOf(browserName);

		switch (browser) {
			case FF:
				System.out.println("INFO: Initiating firefox driver");
			case CHROME:
				System.setProperty("webdriver.chrome.driver", new File(getDriverPath(browserName)).getCanonicalPath().toString());
			case IE:
				System.setProperty("webdriver.ie.driver", new File(getDriverPath(browserName)).getCanonicalPath().toString());
			case HEADLESS:
				System.setProperty("webdriver.chrome.driver", new File(getDriverPath(browserName)).getCanonicalPath().toString());
			default:
				break;			
		}
		try {
			setWebDriver(new RemoteWebDriver(new URL(getNodeURL()), getBrowserCapabilities(browserName)));
		}
		catch (WebDriverException e) {
			e.printStackTrace();
			throw new AutomationErrors("ERROR: Unable to start browser, please check server configuration on :"+AutomationSeleniumExtendedLibrary.nodeIP);
		}
		getWebDriver().manage().window().maximize();
		scenarioStartTime = new DateUtils().getCurrentDateTimeWithZone(ResourceUtils.getScenarioTimeFormat(), ResourceUtils.getScenarioTimeZone());
		getWebDriver().get(getApplicationURL());
		setCurrentNodeInternetProtocol(ResourceUtils.getSeleniumServer(), Integer.parseInt(ResourceUtils.getSeleniumServerPort()), getWebDriver());
		new AutomationSeleniumExtendedLibrary(getWebDriver()).initWebDriverWait();
		sysUtils.clearJavaCache(AutomationSeleniumExtendedLibrary.nodeIP);
	}

	/**
	 * Sets the web driver.
	 *
	 * @param driver the new web driver
	 */
	@SuppressWarnings("static-access")
	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
		((RemoteWebDriver) this.driver).setFileDetector(new LocalFileDetector());

	}

	/**
	 * Gets the web driver.
	 *
	 * @return the web driver
	 */
	public WebDriver getWebDriver() {
		return driver;
	}

	/**
	 * Gets the driver path.
	 * 
	 * @param browserName
	 *            the browser name
	 * @return the driver path
	 */
	public String getDriverPath(String browserName) {

		browser = Browser.valueOf(browserName);

		switch (browser) {
			case FF:
				return null;
			case CHROME:
				return ResourceHandler.getPropertyValue("automation.chrome.driver.path");
			case IE:
				return ResourceHandler.getPropertyValue("automation.ie.driver.path");
			case HEADLESS:
				return ResourceHandler.getPropertyValue("automation.chrome.driver.path");
			default:
				break;
		}
		return null;
	}

	/**
	 * Gets the browser capabilities.
	 * 
	 * @param browserName
	 *            the browser name
	 * @return the browser capabilities
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DesiredCapabilities getBrowserCapabilities(String browserName) throws IOException {

		browser = Browser.valueOf(browserName);

		if (browserName != null) {
			switch (browser) {
				case FF:
					return initFireFoxDriver(browserName);
				case CHROME:
					return initChromeDriver(browserName);
				case IE:
					return initInternetExplorerDriver(browserName);
				case HEADLESS:
					return initHTMLUnitDriver(browserName);
				default:
					System.out.println("launching chrome as default browser");
					return initChromeDriver(browserName);
			}
		}
		return desiredCapabilities;
	}

	/**
	 * Inits the fire fox driver.
	 *
	 * @param b            the b
	 * @return the desired capabilities
	 */
	private DesiredCapabilities initFireFoxDriver(String b) {
		new TestDriverFactory(b);
		desiredCapabilities = DesiredCapabilities.firefox();
		FirefoxProfile ffProfile = new FirefoxProfile();
		ffProfile.setPreference("security.enable_java", false);
		ffProfile.setAcceptUntrustedCertificates(true);
		ffProfile.setPreference("browser.download.useDownloadDir", false);
		ffProfile.setPreference("plugin.state.java", 2);
		ffProfile.setAcceptUntrustedCertificates(true);
		desiredCapabilities.setCapability(FirefoxDriver.PROFILE, ffProfile);
		desiredCapabilities.setCapability("applicationName", getNodeApplicationName(getNodeFlag()));
		return desiredCapabilities;

	}

	/**
	 * Inits the chrome driver.
	 *
	 * @param b            the b
	 * @return the desired capabilities
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	private DesiredCapabilities initChromeDriver(String b) throws IOException {
		new TestDriverFactory(b);
		baseCaps = new ImplChromeCapabilities();
		List<String> capabilities = new ArrayList<String>();
		Field[] fields = ChromeCapabilities.class.getFields();
		Field.setAccessible(fields, true);
		for (Field f : fields) {
			try {
				capabilities.add(f.get(f).toString());
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.setProperty("webdriver.chrome.driver", new File(getDriverPath(b)).getCanonicalPath().toString());
		return baseCaps.setCapability(capabilities, b, TestDriverFactory.getInstance());

	}

	/**
	 * Inits the internet explorer driver.
	 *
	 * @param b            the b
	 * @return the desired capabilities
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	private DesiredCapabilities initInternetExplorerDriver(String b) throws IOException {
		new TestDriverFactory(b);
		baseCaps = new ImplIECapabilities();
		List<String> capabilities = new ArrayList<String>();
		Field[] fields = IECapabilities.class.getFields();
		Field.setAccessible(fields, true);
		for (Field f : fields) {
			try {
				capabilities.add(f.get(f).toString());
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.setProperty("webdriver.ie.driver", new File(getDriverPath(b)).getCanonicalPath().toString());
		return baseCaps.setCapability(capabilities, Browser.valueOf(b).title, TestDriverFactory.getInstance());

	}

	/**
	 * Inits the html unit driver.
	 *
	 * @param b the b
	 * @return the desired capabilities
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private DesiredCapabilities initHTMLUnitDriver(String b) throws IOException {
		/*desiredCapabilities = DesiredCapabilities.htmlUnit();
		desiredCapabilities.setBrowserName("Html Unit");
		desiredCapabilities.setPlatform(Platform.ANY);
		desiredCapabilities.setJavascriptEnabled(true);
		return desiredCapabilities;*/
		
		baseCaps = new ImplChromeCapabilities();
		List<String> capabilities = new ArrayList<String>();
		Field[] fields = ChromeCapabilities.class.getFields();
		Field.setAccessible(fields, true);
		for (Field f : fields) {
			try {
				capabilities.add(f.get(f).toString());
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.setProperty("webdriver.chrome.driver", new File(getDriverPath(b)).getCanonicalPath().toString());
		return baseCaps.setCapability(capabilities, b, TestDriverFactory.getInstance());
		
	}
	
	
	/**
	 * Gets the application url.
	 * 
	 * @return the application url
	 */
	private String getApplicationURL() {
		return ResourceHandler.getPropertyValue("application.url");
	}

	/**
	 * Sets the current node internet protocol.
	 * 
	 * @param hubIP
	 *            the hub ip
	 * @param hubPort
	 *            the hub port
	 * @param d
	 *            the d
	 * @throws JSONException
	 *             the JSON exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void setCurrentNodeInternetProtocol(String hubIP, int hubPort, WebDriver d) throws JSONException, ParseException, IOException {
		HttpHost host = new HttpHost(hubIP, hubPort);
		@SuppressWarnings("resource")
		DefaultHttpClient client = new DefaultHttpClient();
		URL testSessionApi = new URL("http://" + hubIP + ":" + hubPort + "/grid/api/testsession?session=" + ((RemoteWebDriver) d).getSessionId());
		BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm());
		HttpResponse response = client.execute(host, r);
		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		String proxyID = object.get("proxyId").toString();
		printline("*", 100);
		System.out.println("Node ip-address :" + proxyID.split("//")[1].split(":")[0]);
		System.out.println("Feature Scenario :"+scenario.getName());
		System.out.println("Execution Node :"+getNodeApplicationName(getNodeFlag()));
		printline("*", 100);
		System.setProperty("selenium.execution.node.ip", "\\\\" + proxyID.split("//")[1].split(":")[0]);
		AutomationSeleniumExtendedLibrary.nodeIP = System.getProperty("selenium.execution.node.ip");
	}
	
	/**
	 * Printline.
	 *
	 * @param c the c
	 * @param count the count
	 */
	public void printline(String c, int count) {
		System.out.println("\n");
		for(int i=0; i < count; i++)
			System.out.print(String.join(c, c));
		System.out.println("\n");
	}

	/**
	 * Gets the node application name.
	 * 
	 * @param nodeFlag
	 *            the nflag
	 * @return the node application name
	 */
	public synchronized String getNodeApplicationName(boolean nodeFlag) {
		if (nodeFlag)
			return ResourceHandler.loadProperty("selenium.config.application.autoit");
		else
			return ResourceHandler.loadProperty("selenium.config.application.webapp");
	}

	/**
	 * Gets the node flag.
	 *
	 * @return the node flag
	 */
	public synchronized boolean getNodeFlag() {
		if (nFlag.get() == null)
			nFlag.set(nodeFlag);

		return nFlag.get();
	}
	
	
	/**
	 * Sets the node flag.
	 *
	 * @param nodeFlag the new node flag
	 */
	@SuppressWarnings("static-access")
	public void setNodeFlag(boolean nodeFlag) {
		this.nodeFlag = nodeFlag;		
	}
}
