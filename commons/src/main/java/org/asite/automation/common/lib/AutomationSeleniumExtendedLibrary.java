package org.asite.automation.common.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.base.TestBaseDataCheck;
import org.asite.automation.common.base.TestBaseWaitService;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.base.TestScenarioCheckBase;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.errors.AutomationErrorStrings;
import org.asite.automation.common.errors.AutomationErrors;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * The Class SeleniumWrappers.
 * @author jasminprajapati
 */
public class AutomationSeleniumExtendedLibrary {

	/** The getWebDriver(). */
	private static WebDriver		driver		= null;

	/** The log. */
	public Logger				log;

	/** The configfile. */
	public static String					configfile		= null;

	/**
	 * Initialize web driver wait.
	 */
	public void initWebDriverWait() {
		initiateWaitServiceUtilsInstance();
	}

	/** The utils Objects. */
	protected DateUtils						dateUtils		= null;

	/** The sys utils. */
	protected SystemUtils					sysUtils		= null;

	/** The auto it utils. */
	protected AutoItUtils					autoItUtils		= null;

	/** The java utils. */
	protected JavaUtils						javaUtils		= null;

	/** The Excel utils. */
	protected ExcelUtils					excelUtils		= null;

	/** The String utils. */
	protected AutomationErrorStrings		eStringUtils	= null;

	/** The String utils. */
	protected static TestBaseWaitService	waitUtils		= null;

	/** The list utils. */
	protected ListUtils						listUtils		= null;

	/** The str utils. */
	protected StringUtils					strUtils		= null;

	/** The email utils. */
	protected EmailUtils					emailUtils		= null;

	/** The resource utils. */
	protected ResourceUtils					resourceUtils	= null;

	/** The TestDataCheck utils. */
	protected TestBaseDataCheck				dataCheckUtils	= null;

	/** The pdf utils. */
	protected PdfUtils 						pdfUtils		= null;

	/** The Property utils. */
	protected PropertyUtils 				propertyUtils		= null;
	
	/** The scenario check base instance. */
	public static TestScenarioCheckBase		scb				= null;

	/** The node ip. */
	public static String					nodeIP			= AdoddleCommonStringPool.EMPTY_STRING;
	
	/** The collection data map. */
	public static Map<String, String>		collectionDataMap 	= null;

	/**
	 * Instantiates a new automation selenium extended library.
	 */
	public AutomationSeleniumExtendedLibrary() {
		AutomationLogger	logInstance	= AutomationLogger.getInstance();
		PropertyConfigurator.configure(ResourceHandler.getPropertyValue("logger.properties"));
		log = logInstance.getLogger(this.getClass());
		initializeUtils();
	}

	/**
	 * Instantiates a new automation selenium extended library.
	 * 
	 * @param driver
	 *            the driver
	 */
	public AutomationSeleniumExtendedLibrary(WebDriver driver) {
		setWebDriver(driver);
	}

	/**
	 * Sets the web driver.
	 * 
	 * @param dri
	 *            the new web driver
	 */
	@SuppressWarnings("static-access")
	private void setWebDriver(WebDriver dri) {
		this.driver = dri;
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
	 * Initialize web driver wait.
	 * 
	 * @param config
	 *            the new config file
	 */
	public void setConfigFile(String config) {
		configfile = config;
	}

	/**
	 * Initialize web driver wait.
	 * 
	 * @return the config file
	 */
	public String getConfigFile() {
		return configfile;
	}

	/**
	 * Gets the config timeout value.
	 * 
	 * @return the config timeout value
	 */
	private String getConfigTimeoutValue() {
		return ResourceHandler.getPropertyValue("wait.timeout");
	}

	/**
	 * Initiate scenario util instance.
	 */
	private void initiateScenarioUtilInstance() {
		scb = new ScenarioCheckUtils();
	}

	/**
	 * Initiate error string util instance.
	 */
	private void initiateErrorStringUtilInstance() {
		eStringUtils = new ErrorStringUtils();
	}

	/**
	 * Initiate java util instance.
	 */
	private void initiateJavaUtilInstance() {
		javaUtils = new JavaUtils();
	}

	/**
	 * Initiate date utils instance.
	 */
	private void initiateDateUtilsInstance() {
		dateUtils = new DateUtils();
	}

	/**
	 * Initiate sys utils instance.
	 */
	private void initiateSysUtilsInstance() {
		sysUtils = new SystemUtils();
	}

	/**
	 * Initiate auto it utils instance.
	 */
	private void initiateAutoItUtilsInstance() {
		autoItUtils = new AutoItUtils();
	}

	/**
	 * Initiate excel utils instance.
	 */
	private void initiateExcelUtilsInstance() {
		excelUtils = new ExcelUtils();
	}

	/**
	 * Initiate wait service utils instance.
	 */
	private void initiateWaitServiceUtilsInstance() {
		waitUtils = new WaitServiceUtils(driver, Integer.parseInt(getConfigTimeoutValue()));
	}

	/**
	 * Initiate wait service utils instance.
	 */
	private void initiateListUtilsInstance() {
		listUtils = new ListUtils();
	}

	/**
	 * Initiate wait service utils instance.
	 */
	private void initiateEmailUtilsInstance() {
		emailUtils = new EmailUtils();
	}

	/**
	 * Initiate resource utils instance.
	 */
	private void initiateResourceUtilsInstance() {
		resourceUtils = new ResourceUtils();
	}

	/**
	 * Initiate resource utils instance.
	 */
	private void initiatePdfUtilsInstance() {
		pdfUtils = new PdfUtils();
	}
	
	/**
	 * Initiate string utils instance.
	 */
	private void initiateStringUtilsInstance() {
		strUtils = new StringUtils();
	}

	/**
	 * Initiate data check utils instance.
	 */
	private void initiateDataCheckUtilsInstance() {
		dataCheckUtils = new TestdataCheckUtils();
	}

	/**
	 * Initiate property utils instance.
	 */
	private void initiatePropertyUtilsInstance() { propertyUtils = new PropertyUtils();	}

	/**
	 * Navigate url.
	 * 
	 * @param url
	 *            the url
	 */
	public void navigateURL(String url) {
		getWebDriver().get(url);
	}

	/**
	 * Maximize window.
	 */
	protected void maximizeWindow() {
		getWebDriver().manage().window().maximize();
	}

	
	/**
	 * Gets the driver cookies.
	 *
	 * @return the driver cookies
	 */
	protected Set<Cookie> getDriverCookies() {
		return getWebDriver().manage().getCookies();
	}
	
	/**
	 * Find element.
	 * 
	 * @param locator
	 *            the locator
	 * @return the web element
	 */
	public WebElement findElement(By locator) {
		try {
			waitUtils.waitInterval(Float.parseFloat(ResourceHandler.loadProperty("default.wait")));
			return getWebDriver().findElement(locator);
		}
		catch (Throwable e) {
			throw new AutomationErrors(eStringUtils.getFindFailureError(locator));
		}
	}

	/**
	 * Find elements.
	 * 
	 * @param locator
	 *            the locator
	 * @return the list
	 */
	public List<WebElement> findElements(By locator) {
		try {
			waitUtils.waitInterval(Float.parseFloat(ResourceHandler.loadProperty("default.wait")));
			return getWebDriver().findElements(locator);
		}
		catch (InterruptedException e) {
			throw new AutomationErrors(eStringUtils.getFindFailureError(locator));
		}
	}

	/**
	 * Switch_ iframe.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void switchIframe(By locator) {
		waitUntilElementIsDisplayed(locator);
		WebElement frame = findElement(locator);
		/*try {
			waitInterval(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		getWebDriver().switchTo().frame(frame);
	}

	/**
	 * Drag and drop element.
	 * 
	 * @param dragElement
	 *            the drag element
	 * @param dropElement
	 *            the drop element
	 */
	protected void dragAndDropElement(WebElement dragElement, WebElement dropElement) {
		Actions actions = new Actions(driver);
		Action dragAndDrop;
		dragAndDrop = actions.clickAndHold(dragElement).moveToElement(dropElement, dropElement.getLocation().getX(), dropElement.getLocation().getY()).release(dropElement).build();
		dragAndDrop.perform();
		waitForCompletePageLoad();
	}

	/**
	 * Switch_default.
	 */
	public void switchDefault() {
		getWebDriver().switchTo().defaultContent();
	}

	/**
	 * Tear down.
	 */
	public void tearDown() {
		if (!(driver == null) && !(getWebDriver().toString().contains("null"))) {
			try {
				getWebDriver().close();
				if (!getWebDriver().toString().contains("null"))
					getWebDriver().quit();
			}
			catch (Throwable t) {
				log.info(eStringUtils.getTearDownError());
			}
		}
	}

	/**
	 * Clear.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void clear(By locator) {
		try {
			waitUntilElementIsDisplayed(locator);
			findElement(locator).clear();
		}
		catch (Throwable t) {
			throw new AutomationErrors(eStringUtils.getClearElementFailureError(locator));
		}
	}

	/**
	 * Context click.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void contextClick(By locator) {
		Actions action = new Actions(driver);
		WebElement element = findElement(locator);
		action.moveToElement(element);
		action.contextClick(element).build().perform();
		waitUtils.waitForCompletePageLoad();

	}

	/**
	 * Context click.
	 * 
	 * @param e
	 *            the e
	 */
	protected void contextClickWebElement(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e);
		action.contextClick(e).build().perform();
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Gets the element text.
	 * 
	 * @param locator
	 *            the locator
	 * @return the element text
	 */
	public String getElementText(By locator) {
		waitUntilElementIsDisplayed(locator);
		return findElement(locator).getText();
	}

	/**
	 * Gets the element text.
	 * 
	 * @param locator
	 *            the locator
	 * @param timeOut
	 *            the time out
	 * @return the element text
	 */
	public String getElementText(By locator, int timeOut) {
		waitUntilElementIsDisplayed(locator, timeOut);
		return findElement(locator).getText();
	}

	/**
	 * Gets the element text.
	 * 
	 * @param e
	 *            the e
	 * @return the element text
	 */
	public String getElementText(WebElement e) {
		waitUntilElementIsDisplayed(e);
		return e.getText();
	}

	/**
	 * Gets the current window.
	 * 
	 * @return the current window
	 */
	public String getCurrentWindow() {
		return getWebDriver().getWindowHandle();
	}

	/**
	 * Gets the current Window URL.
	 * 
	 * @return the current window URL
	 */
	protected String getCurrentWindowURL() {
		return getWebDriver().getCurrentUrl();
	}

	/**
	 * Switch window.
	 */
	public void switchWindow() {
		ArrayList<String> handles = new ArrayList<String>(getWebDriver().getWindowHandles());
		log.info("Number of windows found: "+handles.size());
		log.info("First Window handle before switch Window: "+handles.get(0));
		log.info("Second Window handle before switch window: "+handles.get(1));
		log.info("First Window Title before switch Window: "+getWebDriver().getTitle());
		getWebDriver().switchTo().window(handles.get(1));
		log.info("After switch window active window handle :"+ getCurrentWindow());
		log.info("After switch window active window Title : "+getWebDriver().getTitle());
	}

	/**
	 * Switch window.
	 * 
	 * @param handle
	 *            the handle
	 */
	public void switchWindow(String handle) {
		getWebDriver().switchTo().window(handle);
	}

	public void switchToSecondWindow(String currentWindowTitle) {
		ArrayList<String> handles = new ArrayList<String>(getWebDriver().getWindowHandles());
		log.info("Number of windows found: "+handles.size());
		log.info("First Window Title before switch Window: "+getWebDriver().getTitle());
		AutomationAssert.verifyTrue(currentWindowTitle.equalsIgnoreCase(getWebDriver().getTitle()));
		for(String handle: handles) {
			getWebDriver().switchTo().window(handle);
			if(! getWebDriver().getTitle().equalsIgnoreCase(currentWindowTitle))
				break;
		}
		log.info("After switch window active window Title : "+getWebDriver().getTitle());
	}

	/**
	 * Gets the window handles.
	 * 
	 * @return the window handles
	 */
	protected Set<String> getWindowHandles() {
		return getWebDriver().getWindowHandles();
	}

	/**
	 * Switch previous window.
	 * 
	 * @param parentHandle
	 *            the parent handle
	 */
	public void switchPreviousWindow(String parentHandle) {
		getWebDriver().switchTo().window(parentHandle);
	}

	/**
	 * Switch Third window.
	 * 
	 * @param parentHandle1
	 *            the parent Window
	 * @param parentHandle2
	 *            the Child Window
	 */
	public void switchToThirdWindow(String parentHandle1, String parentHandle2) {
		waitForSwitchWindow(3);
		Set<String> handles = getWebDriver().getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentHandle1) && !windowHandle.equals(parentHandle2)) {
				getWebDriver().switchTo().window(windowHandle);
				break;
			}
		}
	}

	/**
	 * Switch to Blank tab like use Control + t.
	 */
	protected void openNewTab() {
//		findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		executeJScript("window.open('', '_blank')");
		waitForCompletePageLoad();
	}

	/**
	 * Wait for i frame load.
	 * 
	 * @return the expected condition
	 */
	private ExpectedCondition<Boolean> waitForIFrameLoad() {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver).executeScript("\"$('iframe').load();\"");
			}
		};
	}

	/**
	 * Wait for i frame load.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void waitForIFrameLoad(By locator) {
		waitUntilElementIsDisplayed(locator);
		switchIframe(locator);
		waitUtils.getWebDriverWait().until(waitForIFrameLoad());
	}

	/*
	 * public static ExpectedCondition<Boolean> waitForSwitchWindowCondition(final int numberOfWindows) { return new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver driver) { getWebDriver().getWindowHandles(); return getWebDriver().getWindowHandles().size() == numberOfWindows; } }; }
	 */

	/**
	 * Click element and wait.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void clickElementAndWait(By locator) {
		waitUntilElementIsDisplayed(locator);
		waitUntilElementIsClickable(locator);
		waitForCompletePageLoad();
		try {
			findElement(locator).click();
			waitUtils.waitForCompletePageLoad();
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getPageLoadError(getConfigTimeoutValue()));
		}
		catch (WebDriverException e) {
			e.printStackTrace();
			throw new AutomationErrors(e.getLocalizedMessage());
		}

	}

	/**
	 * Click element if enabled.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void clickElementIfEnabled(By locator) {
		if (isEnabled(locator)) {
			clickElement(locator);
		}
	}

	/**
	 * Click element and wait Until Element Is Invisible.
	 * 
	 * @param clickElement
	 *            the click element
	 * @param visibleElement
	 *            the visible element
	 */
	public void clickElementAndWaitUntilElementInvisible(By clickElement, By visibleElement) {
		waitUntilElementIsDisplayed(clickElement);
		waitUntilElementIsClickable(clickElement);
		try {
			findElement(clickElement).click();
			waitUtils.waitForCompletePageLoad();
			waitUntilElementIsInvisible(visibleElement);
		}
		catch (Exception e) {
			log.error("failure while clicking and waiting: " + clickElement.toString());
		}

	}

	/**
	 * Click element and wait.
	 * 
	 * @param e
	 *            the e
	 */
	public void clickElementAndWait(WebElement e) {
		waitUntilElementIsDisplayed(e);
		waitUntilElementIsClickable(e);
		try {
			e.click();
			waitUtils.waitForCompletePageLoad();
		}
		catch (Exception ex) {
			log.error("failure while clicking and waiting: " + e.toString());
		}

	}

	/**
	 * Click element and switch window.
	 * 
	 * @param locator
	 *            the locator
	 * @return the string
	 */
	public String clickElementAndSwitchWindow(By locator) {
		String currentWindowHandle = getCurrentWindow();
		clickElementAndWait(locator);
		waitForSwitchWindow(2);
		switchWindow();
		waitUtils.waitForCompletePageLoad();
		return currentWindowHandle;
	}

	/**
	 * Click element and switch window.
	 *
	 * @param e the e
	 * @return the string
	 */
	public String clickElementAndSwitchWindow(WebElement e) {
		String currentWindowHandle = getCurrentWindow();
		clickElementAndWait(e);
		waitForSwitchWindow(2);
		switchWindow();
		waitUtils.waitForCompletePageLoad();
		return currentWindowHandle;
	}

	
	/**
	 * Reload page.
	 */
	public void reloadPage() {
		try {
			getWebDriver().navigate().refresh();
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getReloadTimeOutError());
		}
	}

	/**
	 * Click element and wait for element.
	 * 
	 * @param clickLocator
	 *            the click locator
	 * @param waitLocator
	 *            the wait locator
	 */
	public void clickElementAndWaitForElement(By clickLocator, By waitLocator) {
		waitUntilElementIsDisplayed(clickLocator);
		waitUntilElementIsClickable(clickLocator);
		waitUtils.waitForCompletePageLoad();
		findElement(clickLocator).click();
		waitUntilElementIsDisplayed(waitLocator);
		waitUtils.waitForCompletePageLoad();

	}

	/**
	 * Click element and wait for element.
	 * 
	 * @param clickElement
	 *            the click Element
	 * @param waitElement
	 *            the wait Element
	 */
	public void clickElementAndWaitForElement(WebElement clickElement, WebElement waitElement) {
		waitUntilElementIsDisplayed(clickElement);
		waitUntilElementIsClickable(clickElement);
		waitUtils.waitForCompletePageLoad();
		clickElement.click();
		waitUntilElementIsDisplayed(waitElement);
		waitUtils.waitForCompletePageLoad();

	}

	
	/**
	 * Click element and wait for invisibility of element.
	 * 
	 * @param clickLocator
	 *            the click locator
	 * @param invisibilityLocator
	 *            the invisibility locator
	 */
	public void clickElementAndWaitForInvisibilityOfElement(By clickLocator, By invisibilityLocator) {
		waitUntilElementIsDisplayed(clickLocator);
		clickElementAndWait(clickLocator);
		waitUntilElementIsInvisible(invisibilityLocator);
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Click element and wait for invisibility of element.
	 *
	 * @param clickElement
	 *            the click Element
	 * @param invisibilityElement
	 *            the invisibility Element
	 */
	public void clickElementAndWaitForInvisibilityOfElement(WebElement clickElement, WebElement invisibilityElement) {
		waitUntilElementIsDisplayed(clickElement);
		clickElementAndWait(clickElement);
		waitUntilElementIsInvisible(invisibilityElement);
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Click element and wait for invisibility of element.
	 * 
	 * @param clickLocator
	 *            the click locator
	 * @param invisibilityLocator
	 *            the invisibility locator
	 * @param timeout
	 *            the timeout
	 */
	public void clickElementAndWaitForInvisibilityOfElement(By clickLocator, By invisibilityLocator, int timeout) {
		waitUntilElementIsDisplayed(clickLocator);
		clickElementAndWait(clickLocator);
		waitUntilElementIsInvisible(invisibilityLocator, timeout);
		waitUtils.waitForCompletePageLoad();
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
		waitUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(clickLocator));

		for (int index = 0; index < retries; index++) {
			try {
				findElement(clickLocator).click();
				waitUntilElementIsDisplayed(waitLocator);
				if (isDisplayed(waitLocator))
					break;
			}
			catch (Exception e) {
				log.error("failure while retrying clicking and waiting for element: \n" + clickLocator.toString());
				log.error("\n" + waitLocator.toString());
			}
		}
	}

	/**
	 * Click element.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void clickElement(By locator) {
		waitUntilElementIsDisplayed(locator);
		waitUntilElementIsClickable(locator);
		
		try {
			findElement(locator).click();
		}
		catch (WebDriverException e) {
			throw new AutomationErrors(eStringUtils.getClickFailureError(locator));
		}

	}

	/**
	 * Click element.
	 *
	 * @param locator            the locator
	 * @param timeout the timeout
	 */
	public void clickElement(By locator, int timeout) {
		waitUntilElementIsDisplayed(locator, timeout);
		try {
			findElement(locator).click();
		}
		catch (WebDriverException e) {
			throw new AutomationErrors(eStringUtils.getClickFailureError(locator));
		}

	}
	
	/**
	 * Click element.
	 * 
	 * @param e
	 *            the e
	 */
	public void clickElement(WebElement e) {
		waitUntilElementIsDisplayed(e);
		try {
			e.click();
		}
		catch (WebDriverException we) {
			throw new AutomationErrors(eStringUtils.getClickFailureError(e));
		}

	}

	/**
	 * Click element with out wait.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void clickElementWithOutWait(By locator) {
		try {
			findElement(locator).click();
		}
		catch (WebDriverException e) {
			throw new AutomationErrors(eStringUtils.getClickFailureError(locator));
		}
	}

	/**
	 * Wait until list of all elements are displayed.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void waitUntilListOfElementIsDisplayed(By locator) {
		waitUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		waitUtils.getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * Wait until element is displayed.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void waitUntilElementIsDisplayed(By locator) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
			waitUtils.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTimeOutError(locator, getConfigTimeoutValue()));
		}
		catch (StaleElementReferenceException se) {
			for(int t = 0; ; t++) {
				if (t > Integer.parseInt(getConfigTimeoutValue()))
						AutomationAssert.verifyTrue("Timedout after waiting for Stale element", false);
				try {
					if(isDisplayed(locator))
						break;
				}
				catch(Throwable th) {}		
				
				try {
					waitUtils.waitInterval(1);
				}
				catch (InterruptedException e) {
					log.error(e.getLocalizedMessage());
				}
			}
		}

	}

	/**
	 * Wait until element is displayed.
	 * 
	 * @param locator
	 *            the locator
	 * @param timeOut
	 *            the time out
	 */
	public void waitUntilElementIsDisplayed(By locator, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTimeOutError(locator, timeOut));
		}
		catch (StaleElementReferenceException se) {
			for(int t = 0; ; t++) {
				if (t > Integer.parseInt(getConfigTimeoutValue()))
						AutomationAssert.verifyTrue("Timedout after waiting for Stale element", false);
				try {
					if(isDisplayed(locator))
						break;
				}
				catch(Throwable th) {}		
				
				try {
					waitUtils.waitInterval(1);
				}
				catch (InterruptedException e) {
					log.error(e.getLocalizedMessage());
				}
			}
		}


	}

	/**
	 * Wait until element is displayed.
	 * 
	 * @param e
	 *            the e
	 */
	public void waitUntilElementIsDisplayed(WebElement e) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.visibilityOf(e));
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getTimeOutError(e, getConfigTimeoutValue()));
		}
		catch (StaleElementReferenceException se) {
			for(int t = 0; ; t++) {
				if (t > Integer.parseInt(getConfigTimeoutValue()))
						AutomationAssert.verifyTrue("Timedout after waiting for Stale element", false);
				try {
					if(isDisplayed(e))
						break;
				}
				catch(Throwable th) {
					
				}
				try {
					waitUtils.waitInterval(1);
				}
				catch (InterruptedException ie) {
					log.error(ie.getLocalizedMessage());
				}
			}
		}
	}

	/**
	 * Wait until element is displayed.
	 * 
	 * @param e
	 *            the e
	 * @param timeOut
	 *            the time out
	 */
	private void waitUntilElementIsDisplayed(WebElement e, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(e));
			wait.until(ExpectedConditions.visibilityOf(e));
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getTimeOutError(e, getConfigTimeoutValue()));
		}
		
		catch (StaleElementReferenceException se) {
			for(int t = 0; ; t++) {
				if (t > Integer.parseInt(getConfigTimeoutValue()))
						AutomationAssert.verifyTrue("Timedout after waiting for Stale element", false);
				try {
					if(isDisplayed(e))
						break;
				}
				catch(Throwable th) {}		
				
				try {
					waitUtils.waitInterval(1);
				}
				catch (InterruptedException ie) {
					log.error(ie.getLocalizedMessage());
				}
			}
		}

	}

	/**
	 * Wait until element is present.
	 * 
	 * @param locator
	 *            the locator
	 * @param timeout
	 *            the timeout
	 */
	protected void waitUntilElementIsPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getPresenceTimeOutError(locator, String.valueOf(timeout)));
		}
	}

	/**
	 * Wait until element is present.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void waitUntilElementIsPresent(By locator) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getPresenceTimeOutError(locator, getConfigTimeoutValue()));
		}
	}

	/**
	 * Wait until element is invisible.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void waitUntilElementIsInvisible(By locator) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
			waitUtils.waitForCompletePageLoad();
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getInvisibilityTimeOutError(locator, getConfigTimeoutValue()));
		}
	}
	
	/**
	 * Wait until element is invisible.
	 * 
	 * @param locator
	 *            the locator
	 * @param timeout
	 *            the timeout
	 */
	public void waitUntilElementIsInvisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getInvisibilityTimeOutError(locator, String.valueOf(timeout)));
		}

	}

	/**
	 * Wait until element is invisible.
	 *
	 * @param e the e
	 */
	public void waitUntilElementIsInvisible(WebElement e) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(e)));
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getVisibilityStringError(e, false));
		}
	}
	
	/**
	 * Wait until Link with text is visible.
	 *
	 * @param linkText the linkText
	 */
	public void waitUntilLinkWithTextIsVisible(String linkText) {
		By linkXPath = By.xpath(".//a[text()='" + linkText + "']");
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(linkXPath));
			waitUtils.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(linkXPath));
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getTimeOutError(linkXPath, getConfigTimeoutValue()));
		}
	}

	/**
	 * Wait until element is invisible.
	 *
	 * @param e the e
	 * @param timeout the timeout
	 */
	public void waitUntilElementIsInvisible(WebElement e, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);

		try {
			wait.until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(e)));
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getVisibilityStringError(e, false));
		}
	}
	
	/**
	 * Wait until element is Selected.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void waitUntilElementIsSelected(By locator) {
		waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeSelected(locator));
	}

	/**
	 * Checks if is displayed.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is displayed
	 */
	public boolean isDisplayed(By locator) {
		try {
			return getWebDriver().findElement(locator).isDisplayed();
		}
		catch (Throwable t) {
			return false;
		}
	}

	/**
	 * Checks if is displayed.
	 * 
	 * @param e
	 *            the e
	 * @return true, if is displayed
	 */
	public boolean isDisplayed(WebElement e) {
		try {
			return e.isDisplayed();
		}
		catch (Throwable t) {
			return false;
		}
	}

	/**
	 * Checks if is displayed.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is displayed
	 */
	public boolean isElementPresent(By locator) {
		try {
			waitUntilElementIsPresent(locator, 1);
			return true;
		}
		catch (Throwable t) {
			return false;
		}
	}

	/**
	 * Checks if is selected.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is selected
	 */
	public boolean isSelected(By locator) {
		return findElement(locator).isSelected();
	}

	/**
	 * Checks if is selected.
	 * 
	 * @param e
	 *            the e
	 * @return true, if is selected
	 */
	public boolean isSelected(WebElement e) {
		return e.isSelected();
	}

	/**
	 * Checks if is selected.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is selected
	 */
	public boolean isEnabled(By locator) {
		return findElement(locator).isEnabled();
	}

	/**
	 * Close current window.
	 */
	public void closeCurrentWindow() {
		getWebDriver().close();
	}

	/**
	 * Mouse hover.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void mouseHover(By locator) {
		try {
			waitUntilElementIsDisplayed(locator, 30);
		}
		catch (Throwable t) {
			log.info("Visibility check failed");
		}
		Actions action = new Actions(driver);
		action.moveToElement(findElement(locator)).build().perform();
	}

	/**
	 * Mouse hover.
	 *
	 * @param e
	 *            the e
	 */
	public void mouseHover(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).build().perform();
	}

	/**
	 * Mouse hover.
	 *
	 * @param hoverLocator the hover locator
	 */
	public void mouseHoverElement(By hoverLocator) {
		try {
			waitUntilElementIsDisplayed(hoverLocator, 30);
		}
		catch (Throwable t) {
			log.info("Visibility check failed");
		} 
		String javaScript = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, findElement(hoverLocator));
	}

	/**
	 * Mouse hover.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void mouseHoverClick(By locator) {
		try {
			waitUntilElementIsDisplayed(locator, 30);
		}
		catch (Throwable t) {
			log.info("Visibility check failed");
		}
		Actions action = new Actions(driver);
		action.moveToElement(findElement(locator)).click().build().perform();
	}

	/**
	 * Mouse hover.
	 *
	 * @param locator
	 *            the locator
	 */
	public void mouseHoverClick(By locator, int x, int y) {
		try {
			waitUntilElementIsDisplayed(locator, 30);
		}
		catch (Throwable t) {
			log.info("Visibility check failed");
		}
		Actions action = new Actions(driver);
		action.moveToElement(findElement(locator), x, y).click().build().perform();
	}

	/**
	 * Verify element text.
	 *
	 * @param locator            the locator
	 * @param text            the text
	 * @param timeout the timeout
	 */
	public void verifyElementText(By locator, String text, int...timeout) {
		try {
			if(timeout.length > 0)
				waitUntilElementIsDisplayed(locator, timeout[0]);
			else
				waitUntilElementIsDisplayed(locator);
			Assert.assertEquals(text, findElement(locator).getText());
		}
		catch (Throwable t) {
			log.error("Element text verification failed");
		}

	}

	/**
	 * Mouse hover.
	 * 
	 * @param ele
	 *            the ele
	 */
	public void mouseHoverWebElement(WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
	}

	/**
	 * Verify element text contains. f
	 * 
	 * @param locator
	 *            the locator
	 * @param text
	 *            the text
	 */
	public void verifyElementTextContains(By locator, String text) {
		waitUntilElementIsDisplayed(locator);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(findElement(locator).getText(), text), findElement(locator).getText().contains(text));
	}

	/**
	 * Gets the count.
	 * 
	 * @param locator
	 *            the locator
	 * @return the count
	 */
	public int getCount(By locator) {
		return getWebDriver().findElements(locator).size();
	}

	/**
	 * Send keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param keys
	 *            the keys
	 */
	public void sendKeys(By locator, String keys) {
		waitUntilElementIsClickable(locator);
		waitUtils.waitForCompletePageLoad();
		try {
			clear(locator);
		}
		catch (Throwable t) {
			log.info("Clear operation failure");
		}
		findElement(locator).sendKeys(keys);
		waitUtils.waitForCompletePageLoad();
		try {
			if(!keys.contains("C:\\"))
				AutomationAssert.verifyTrue(getValue(locator).trim().equalsIgnoreCase(keys));
		}
		catch(Throwable t) {
			log.error("SendKey(s) FAILURE:  Expected string input '"+keys+"'  VS  Actual string input '"+getValue(locator)+"'");
			/*try {
				clear(locator);
			}
			catch (Throwable e) {
				log.info("Clear operation re-failed");
			}
			findElement(locator).clear();
			findElement(locator).sendKeys(keys);
			waitUtils.waitForCompletePageLoad();*/
		}
	}

	/**
	 * Send keys.
	 * 
	 * @param e
	 *            the e
	 * @param keys
	 *            the keys
	 */
	public void sendKeys(WebElement e, String keys) {
		waitUtils.waitForCompletePageLoad();
		try {
			waitUntilElementIsDisplayed(e, 60);
			e.clear();
		}
		catch (Throwable t) {
			log.info("Clear operation failure");
		}
		e.sendKeys(keys);
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Send keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param keys
	 *            the keys
	 */
	public void sendKeys(By locator, Keys keys) {
		findElement(locator).sendKeys(keys);
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Send keys.
	 * 
	 * @param element
	 *            the element
	 * @param keys
	 *            the keys
	 */
	public void sendKeys(WebElement element, Keys keys) {
		element.sendKeys(keys);
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Wait for switch window.
	 * 
	 * @param numberofWindows
	 *            the numberof windows
	 */
	public void waitForSwitchWindow(int numberofWindows) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(numberofWindows));
			log.info("Number of windows after waiting for waitForSwitchWindow("+numberofWindows+") :"+getWebDriver().getWindowHandles().size());
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getNewWindowFailureError(numberofWindows));
		}
	}

	/**
	 * Wait for switch window.
	 * 
	 * @param numberofWindows
	 *            the numberof windows
	 * @param timeout
	 *            the timeout
	 */
	public void waitForSwitchWindow(int numberofWindows, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.numberOfWindowsToBe(numberofWindows));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getNewWindowFailureError(numberofWindows));
		}
	}

	public void switchToWindow(int switchedWinIndex)
	{
		waitForSwitchWindow(switchedWinIndex);
		getWebDriver().switchTo().window(new ArrayList<String>(getWebDriver().getWindowHandles()).get(switchedWinIndex-1));
		waitForCompletePageLoad();
	}
	
	/**
	 * Drag mouse.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void dragMouse(By locator) {

		Actions action = new Actions(driver);
		action.moveToElement(findElement(locator)).build().perform();
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Gets the tool tip text.
	 * 
	 * @param locator
	 *            the locator
	 * @return the tool tip text
	 */
	public String getToolTipText(By locator) {

		String tooltipText;
		clickAndHoldElement(locator);
		tooltipText = findElement(locator).getAttribute("title");
		waitUtils.waitForCompletePageLoad();
		releaseElementClick(locator);
		return tooltipText;
	}

	/**
	 * Gets the element attribute value.
	 * 
	 * @param locator
	 *            the locator
	 * @param attribute
	 *            the attribute
	 * @return the element attribute value
	 */
	public String getElementAttributeValue(By locator, String attribute) {
		waitUntilElementIsDisplayed(locator);
		return findElement(locator).getAttribute(attribute);
	}

	/**
	 * Gets the value.
	 * 
	 * @param locator
	 *            the locator
	 * @return the value
	 */
	public String getValue(By locator) {
		String value;
		value = findElement(locator).getAttribute("value");
		return value;
	}

	/**
	 * Gets the value.
	 * 
	 * @param e
	 *            the e
	 * @return the value
	 */
	public String getValue(WebElement e) {
		String value;
		value = e.getAttribute("value");
		return value;
	}

	/**
	 * Gets the selected dropdown label.
	 * 
	 * @param locator
	 *            the locator
	 * @return the selected dropdown label
	 */
	public String getSelectedDropdownLabel(By locator) {
		Select dropDown = new Select(findElement(locator));
		return (dropDown.getFirstSelectedOption().getText());
	}

	/**
	 * Gets the selected dropdown label.
	 * 
	 * @param e
	 *            the e
	 * @return the selected dropdown label
	 */
	public String getSelectedDropdownLabel(WebElement e) {
		Select dropDown = new Select(e);
		return (dropDown.getFirstSelectedOption().getText());
	}

	/**
	 * Click and hold element.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void clickAndHoldElement(By locator) {
		Actions action = new Actions(driver);
		action.clickAndHold(findElement(locator)).perform();
	}

	/**
	 * Release element click.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void releaseElementClick(By locator) {
		Actions action = new Actions(driver);
		action.release(findElement(locator)).perform();
	}

	/**
	 * Upload file using keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param fileLocation
	 *            the file location
	 */
	public void uploadFileUsingKeys(By locator, List<String> fileLocation) {
		for (String file : fileLocation) {
			findElement(locator).sendKeys(file);
			waitUtils.waitForCompletePageLoad();
		}
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Upload file using keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param fileLocation
	 *            the file location
	 */
	public void uploadMultipleFilesUsingKeys(By locator, List<String> fileLocation) {
		String multiFiles = "";
		StringBuilder strB = new StringBuilder();

		if (AdoddleCommonAppMethods.browser.equalsIgnoreCase("chrome") || AdoddleCommonAppMethods.browser.equalsIgnoreCase("headless"))
			findElement(locator).sendKeys(fileLocation.toString().replace("[", "").replace("]", "").replace(",", "\n"));
		else if (AdoddleCommonAppMethods.browser.equalsIgnoreCase("ie")) {
			for (String file : fileLocation) {
				strB.append("\"");
				strB.append(file);
				strB.append("\"");
			}
			multiFiles = multiFiles + strB.toString();
			findElement(locator).sendKeys(multiFiles);
		}
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Wait for ready state.
	 * 
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> waitForReadyState() {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState == 'complete'");
			}
		};
	}

	/**
	 * Wait for ajax complete.
	 * 
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> waitForAjaxComplete() {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver).executeScript("return window.jQuery.active == '0'");
			}
		};
	}
	
	
	/**
	 * Wait until dropdown contains value.
	 *
	 * @param locator the locator
	 * @param value the value
	 * @param timeout the timeout
	 */
	public void waitUntilDropdownContainsValue(By locator, String value, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(dropDownContainsValueTobe(locator, value));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getDropdownValueError(locator, value, String.valueOf(timeout)));
		}
	}
	
	/**
	 * Drop down contains value tobe.
	 *
	 * @param locator the locator
	 * @param value the value
	 * @return the expected condition
	 */
	private ExpectedCondition<Boolean> dropDownContainsValueTobe(By locator, final String value) {
		
		final Select droplist = new Select(findElement(locator));
		
		return new ExpectedCondition<Boolean>() {
			boolean flag = false;
	            public Boolean apply(WebDriver d) {
	            	for(WebElement e: droplist.getOptions()) {
	            		if(e.getText().equalsIgnoreCase(value)) 
	            			flag = true;
	            	}
	                return (!droplist.getOptions().isEmpty() && flag);
	            }
	        };
	}
	
	/**
	 * Wait for J script element.
	 *
	 * @param javaScript the java script
	 * @return true, if successful
	 * @throws InterruptedException the interrupted exception
	 */
	public boolean waitForJScriptElement(String javaScript) throws InterruptedException {
		int counter = 0;
		
		while(((JavascriptExecutor) driver).executeScript(javaScript).toString().equalsIgnoreCase("0")) {
			System.out.println(((JavascriptExecutor) driver).executeScript(javaScript).toString()+ " vs 0");
			if(counter == resourceUtils.getGlobalWaitTimeout()) {
				break;
			}
			log.info("Waiting for JScript Element..."+resourceUtils.getGlobalWaitTimeout()+" vs "+counter);
			waitUtils.waitInterval(1);
			counter++;
		}
		return true; 
	}

	/**
	 * Wait for complete page load.
	 * 
	 * @return the web driver
	 */
	public WebDriver waitForCompletePageLoad() {
		try {
			waitUtils.getWebDriverWait().until(waitUtils.waitForReadyState());
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getPageLoadError(ResourceHandler.getPropertyValue("wait.timeout")));
		}

		try {
			if ((Boolean) ((JavascriptExecutor) driver).executeScript(AdoddleCommonJQueries.ajaxCallTypeJQuery)) {
				waitUtils.getWebDriverWait().until(waitUtils.waitForAjaxComplete());
			}
		}
		catch (TimeoutException t) {
			throw new AutomationErrors(eStringUtils.getAjaxLoadError(ResourceHandler.getPropertyValue("wait.timeout")));
		}
		catch (WebDriverException e) {
			log.info("Page load wait is completed...");
		}

		return driver;
	}

	/**
	 * Wait until element count to be.
	 * 
	 * @param locator
	 *            the locator
	 * @param expectedCount
	 *            the expected count
	 */
	public void waitUntilElementCountToBe(By locator, int expectedCount) {
		waitUtils.waitForCompletePageLoad();
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTimeOutError(locator, getConfigTimeoutValue(), expectedCount, getCount(locator)));
		}
	}

	/**
	 * Wait until element count to be.
	 * 
	 * @param locator
	 *            the locator
	 * @param expectedCount
	 *            the expected count
	 */
	public void waitUntilElementCountToBeMoreThan(By locator, int expectedCount) {
		waitUtils.waitForCompletePageLoad();
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, expectedCount));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTimeOutError(locator, getConfigTimeoutValue(), expectedCount, getCount(locator)));
		}
	}

	/**
	 * Wait until alert is present.
	 */
	public void waitUntilAlertIsPresent() {
		waitUtils.getWebDriverWait().until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Wait until alert is present with TimeOut.
	 * 
	 * @param timeOutInSeconds
	 *            the time out in seconds
	 */
	public void waitUntilAlertIsPresent(int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Select by visible text.
	 * 
	 * @param locator
	 *            the locator
	 * @param visibleText
	 *            the visible text
	 */
	public void selectByVisibleText(By locator, String visibleText) {
		waitUntilElementIsDisplayed(locator);
		/*waitUntilElementIsClickable(locator);*/
		try {
			Select select = new Select(findElement(locator));
			select.selectByVisibleText(visibleText);
			waitUtils.waitForCompletePageLoad();
		}
		catch (Throwable t) {
			throw new AutomationErrors(eStringUtils.getTextVisibilityTimeoutError(locator, ResourceHandler.getPropertyValue("wait.timeout"), visibleText));
		}
	}

	/**
	 * Select by visible text.
	 * 
	 * @param e
	 *            the e
	 * @param visibleText
	 *            the visible text
	 */
	public void selectByVisibleText(WebElement e, String visibleText) {
		try {
			Select select = new Select(e);
			select.selectByVisibleText(visibleText);
			waitUtils.waitForCompletePageLoad();
		}
		catch(Throwable t) {
			log.error("Failure while selecting element from dropdown list with text :"+visibleText);
		}
	}

	/**
	 * Select by value.
	 * 
	 * @param locator
	 *            the locator
	 * @param value
	 *            the value
	 */
	public void selectByValue(By locator, String value) {
		waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
		Select select = new Select(findElement(locator));
		select.selectByValue(value);
	}

	/**
	 * Select by index.
	 * 
	 * @param locator
	 *            the locator
	 * @param index
	 *            the index
	 */
	public void selectByIndex(By locator, int index) {
		//waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
		waitUntilElementIsDisplayed(locator);
		Select select = new Select(findElement(locator));
		select.selectByIndex(index);
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Select by index.
	 * 
	 * @param e
	 *            the e
	 * @param index
	 *            the index
	 */
	public void selectByIndex(WebElement e, int index) {
		waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(e));
		Select select = new Select(e);
		select.selectByIndex(index);
	}

	/**
	 * Context click with text.
	 * 
	 * @param elementText
	 *            the element text
	 */
	public void contextClickWithText(String elementText) {
		waitForElementWithText(elementText);
		contextClick(By.xpath(".//span[text()='" + elementText.trim() + "']"));
	}

	/**
	 * Context click with link.
	 * 
	 * @param elementLinkText
	 *            the element link text.
	 */
	public void contextClickWithLink(String elementLinkText) {
		contextClick(By.linkText(elementLinkText));
		waitForCompletePageLoad();
	}

	/**
	 * Click context menu option with text.
	 * 
	 * @param elementText
	 *            the element text
	 */
	public void clickContextMenuOptionWithText(String elementText) {
		String elementXPath1 = "//ul[not(contains(@style,'display: none'))]//span[text()='" + elementText + "']";
		String elementXPath2 = "//li[not(contains(@style,'display: none'))]//span[text()='" + elementText + "']";
		String elementXPath3 = "//ul[contains(@style,'display: block')]//li[not(contains(@style,'display: none'))]//span[text()='" + elementText + "']";
		String elementXPath4 = "//ul[not(contains(@style,'display: none'))]//li[contains(@class,'hover')]//span[text()='" + elementText + "']";
		try {
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(By.xpath(elementXPath1), true), isDisplayed(By.xpath(elementXPath1)));
			clickElementAndWait(By.xpath(elementXPath1));
		}
		catch (Throwable t) {
			try {
				AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(By.xpath(elementXPath3), true), isDisplayed(By.xpath(elementXPath3)));
				clickElementAndWait(By.xpath(elementXPath3));
			}
			catch (Throwable tx) {
				try {
					AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(By.xpath(elementXPath4), true), isDisplayed(By.xpath(elementXPath4)));
					clickElementAndWait(By.xpath(elementXPath4));
				}
				catch (Throwable ty) {
					AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(By.xpath(elementXPath2), true), isDisplayed(By.xpath(elementXPath2)));
					clickElementAndWait(By.xpath(elementXPath2));
				}
			}

		}

	}

	/**
	 * Click link with text.
	 * 
	 * @param linkText
	 *            the link text
	 */
	public void clickLinkWithText(String linkText) {
		waitUtils.waitForCompletePageLoad();
		clickElementAndWait(By.linkText(linkText));
	}

	/**
	 * Click link with title.
	 * 
	 * @param linkTitle
	 *            the link title
	 */
	public void clickLinkWithTitle(String linkTitle) {
		String linkCSS = "a[title='" + linkTitle + "']";
		waitUntilElementIsDisplayed(By.cssSelector(linkCSS));
		clickElementAndWait(By.cssSelector(linkCSS));
	}

	/**
	 * Click button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 */
	public void clickButtonWithText(String buttonText) {
		String buttonXPath = "//button[text()='" + buttonText + "']";
		clickElementAndWait(By.xpath(buttonXPath));
	}

	/**
	 * Click popup button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 */
	public void clickPopupButtonWithText(String buttonText) {
		if (isDisplayed(GlobalPageElements.pop_PopUpElement)) {
			String buttonXPath = ".//div[@class='modal-scrollable'][contains(@style, 'z-index')]//button[text()='" + buttonText + "']";
			clickElementAndWait(By.xpath(buttonXPath));
		}
		else
			throw new ElementNotVisibleException("element not found");
	}

	/**
	 * Click popup button with text.
	 * 
	 * @param elementText
	 *            the element text
	 */
	public void clickPopupElementWithText(String elementText) {
		if (isDisplayed(GlobalPageElements.pop_PopUpElement)) {
			String elementXPath = ".//div[@class='modal-scrollable'][contains(@style, 'z-index')]//span[text()='" + elementText + "']";
			clickElementAndWait(By.xpath(elementXPath));
		}
		else
			throw new ElementNotVisibleException("element not found");
	}

	/**
	 * Click element with text.
	 * 
	 * @param elementText
	 *            the element text
	 */
	public void clickElementWithText(String elementText) {
		/*
		 * String jQuery = "$(\"span\").filter(function() { return $(this).text() === " +elementText+";}).click()"; ((JavascriptExecutor) driver).executeScript(jQuery); waitUtils.waitForCompletePageLoad();
		 */
		waitForElementWithText(elementText);
		String elementXPath = ".//span[text()='" + elementText + "']";
		waitUntilElementIsDisplayed(By.xpath(elementXPath));
		waitUntilElementIsClickable(By.xpath(elementXPath));
		clickElementAndWait(By.xpath(elementXPath));
	}

	
	/**
	 * Checks if is displayed link with text.
	 * 
	 * @param linkText
	 *            the link text
	 * @return true, if is displayed link with text
	 */
	public boolean isDisplayedLinkWithText(String linkText) {
		String linkXPath = "//a[text()='" + linkText + "']";
		try {
			return isDisplayed(By.xpath(linkXPath)) && (getCount(By.xpath(linkXPath)) == 1);
		}
		catch (Throwable e) {
			return false;
		}

	}

	/**
	 * Checks if is displayed button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 * @return true, if is displayed button with text
	 */
	public boolean isDisplayedButtonWithText(String buttonText) {
		String buttonXPath = "//button[text()='" + buttonText + "']";
		try {
			return isDisplayed(By.xpath(buttonXPath));
		}
		catch (Throwable e) {
			return false;
		}

	}

	/**
	 * Checks if is displayed element with text.
	 * 
	 * @param elementText
	 *            the element text
	 * @return true, if is displayed element with text
	 */
	public boolean isDisplayedElementWithText(String elementText) {
		String elementXPath = "//span[text()='" + elementText + "']";

		try {
			return isDisplayed(By.xpath(elementXPath));
		}
		catch (Throwable t) {
			return false;
		}

	}

	/**
	 * Checks if is displayed element with text.
	 * 
	 * @param elementText
	 *            the element text
	 * @return true, if is displayed element with text
	 */
	public void waitForElementWithText(String elementText) {
		String elementXPath = "//span[text()='" + elementText + "']";

		try {
			waitUntilElementIsDisplayed(By.xpath(elementXPath));
		}
		catch (Throwable t) {
			AutomationAssert.verifyTrue("ERROR: Timeout - Element with text '" + elementText + "' not displayed", false);
		}

	}

	/**
	 * Checks if is displayed element with text.
	 * 
	 * @param elementText
	 *            the element text
	 * @param timeout
	 *            the timeout
	 * @return true, if is displayed element with text
	 */
	public void waitForElementWithText(String elementText, int timeout) {
		String elementXPath = "//span[text()='" + elementText + "']";

		try {
			waitUntilElementIsDisplayed(By.xpath(elementXPath), timeout);
		}
		catch (Throwable t) {
			AutomationAssert.verifyTrue("ERROR: Timeout - Element with text :" + elementText + " not displayed", false);
		}

	}

	/**
	 * Double click.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void doubleClick(By locator) {
		waitUntilElementIsDisplayed(locator);
		waitUntilElementIsClickable(locator);
		
		try {
			Actions action = new Actions(driver);
			action.moveToElement(findElement(locator)).doubleClick().perform();
		}
		catch (Exception e) {
			log.error("failure while double clicking element: \n" + locator.toString());
		}
	}

	/**
	 * Double click.
	 *
	 * @param e the e
	 */
	public void doubleClick(WebElement e) {
		waitUntilElementIsDisplayed(e);
		waitUntilElementIsClickable(e);
		
		try {
			Actions action = new Actions(driver);
			action.moveToElement(e).doubleClick().perform();
		}
		catch (Exception t) {
			log.error("failure while double clicking element: \n" + e.toString());
		}
	}
	
	/**
	 * Wait and switch iframe.
	 * 
	 * @param locator
	 *            the locator
	 */
	/*
	 * public void waitAndSwitchIframe(By locator) { waitUtils.getWebDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator)); }
	 */

	public void waitAndSwitchIframe(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ResourceHandler.getPropertyValue("wait.timeout")));
		/*try {
			waitInterval(3);
		} catch (InterruptedException e) {
			log.info(e.getLocalizedMessage());
		}*/
		waitUntilElementIsDisplayed(locator);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	
	/**
	 * Wait for frame and element.
	 *
	 * @param locator1 the locator 1
	 * @param locator2 the locator 2
	 * @throws InterruptedException the interrupted exception
	 */
	public void waitForFrameAndElement(By locator1, By locator2) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ResourceHandler.getPropertyValue("wait.timeout")));
		for(int counter=0; counter < 30 ; counter++) {
			waitUntilElementIsDisplayed(locator1);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator1));
			if(isDisplayed(locator2))
				break;
			else {
				switchDefault();
				waitUtils.waitInterval(1);
			}
		}
	}
	
	
	/**
	 * Click Checkbox with Value.
	 * 
	 * @param checkBoxValue
	 *            the checkBox value
	 */
	public void clickCheckBoxWithValue(String checkBoxValue) {
		String chkCSS;
		chkCSS = "input[type='checkbox'][value='" + checkBoxValue + "']";
		waitUntilElementIsDisplayed(By.cssSelector(chkCSS));
		waitUntilElementIsClickable(By.cssSelector(chkCSS));
		clickElement(By.cssSelector(chkCSS));

	}

	/**
	 * Wait until element is clickable.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void waitUntilElementIsClickable(By locator) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getClickableFailureError(locator, ResourceHandler.getPropertyValue("wait.timeout")));
		}
	}

	/**
	 * Wait until element is clickable.
	 * 
	 * @param element
	 *            the element
	 */
	public void waitUntilElementIsClickable(WebElement element) {
		waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait until List of element is clickable.
	 * 
	 * @param element
	 *            the element
	 */
	public void waitUntilListElementIsClickable(WebElement element) {
		waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait until element is clickable.
	 *
	 * @param locator
	 *            the locator
	 */
	public void waitUntilElementIsEnabled(By locator) {
		try {
			for(int index = 0; index < Integer.parseInt(ResourceHandler.getPropertyValue("wait.timeout")); index++) {
				if(isEnabled(locator)) {
					waitUtils.waitInterval(1);
					break;
				}
				else
					waitUtils.waitInterval(1);
			}
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getClickableFailureError(locator, ResourceHandler.getPropertyValue("wait.timeout")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Double click Element And Wait.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void doubleClickAndWait(By locator) {
		Actions action = new Actions(driver).doubleClick(findElement(locator));
		action.build().perform();
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Wait until element contains text.
	 * 
	 * @param locator
	 *            the locator
	 * @param text
	 *            the text
	 */
	public void waitUntilElementContainsText(By locator, String text) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTextVisibilityTimeoutError(locator, ResourceHandler.getPropertyValue("wait.timeout"), text));
		}
	}

	/**
	 * Wait until element contains text.
	 * 
	 * @param locator
	 *            the locator
	 * @param text
	 *            the text
	 * @param timeOut
	 *            the timeOut
	 */
	public void waitUntilElementContainsText(By locator, String text, int timeOut) {
		try {
			new WebDriverWait(driver, timeOut).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTextVisibilityTimeoutError(locator, String.valueOf(timeOut), text));
		}
	}

	/**
	 * Wait until dropdown contains value.
	 * 
	 * @param webElement
	 *            the web element
	 * @param text
	 *            the text
	 */
	public void waitUntilDropdownContainsValue(WebElement webElement, String text) {
		try {
			for (int index = 0; index < Integer.parseInt(getConfigTimeoutValue()); index++) {
				if (getSelectedDropdownLabel(webElement).equalsIgnoreCase(text))
					break;
				else
					waitUtils.waitInterval(1);
			}
		}
		catch (InterruptedException e) {
			log.error("ERROR: Interrupted - Wait until dropdown contains value");
		}
	}

	/**
	 * Wait until element contains text.
	 * 
	 * @param element
	 *            the element
	 * @param text
	 *            the text
	 */
	public void waitUntilElementContainsText(WebElement element, String text) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.textToBePresentInElement(element, text));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTextVisibilityTimeoutError(element, ResourceHandler.getPropertyValue("wait.timeout"), text));
		}
	}
	
	/**
	 * Wait until element contains value.
	 * 
	 * @param value
	 *            the value
	 */
	protected void waitUntilElementContainsValue(By Locator, String value) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.textToBePresentInElementValue(Locator, value));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getTextVisibilityTimeoutError(Locator, ResourceHandler.getPropertyValue("wait.timeout"), value));
		}
	}

	/**
	 * Wait until attribute contains value.
	 * 
	 * @param locator
	 *            the locator
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 */
	public void waitUntilAttributeContainsValue(By locator, String attribute, String value) {
		try {
			waitUtils.getWebDriverWait().until(ExpectedConditions.attributeContains(locator, attribute, value));
		}
		catch (TimeoutException e) {
			throw new AutomationErrors(eStringUtils.getAttributeContainsError(locator, attribute, value, ResourceHandler.getPropertyValue("wait.timeout")));
		}
	}


	/**
	 * Get Alert text.
	 *
	 * @return the alert text
	 */
	public String getAlertText() {
		try {
			waitUntilAlertIsPresent();
			Alert alert = getWebDriver().switchTo().alert();
			return alert.getText();
		}
		catch (Throwable t) {
			log.info("alert was not found");
		}
		return null;
	}
	
	/**
	 * Accept Alert.
	 */
	public void acceptAlert() {
		try {
			waitUntilAlertIsPresent();
			Alert alert = getWebDriver().switchTo().alert();
			alert.accept();
		}
		catch (Throwable t) {
			log.info("alert was not found");
		}
	}

	/**
	 * Accept Alert.
	 *
	 * @param timeout the timeout
	 */
	public void acceptAlert(int timeout) {
		try {
			waitUntilAlertIsPresent(timeout);
			Alert alert = getWebDriver().switchTo().alert();
			alert.accept();
		}
		catch (Throwable t) {
			log.info("alert was not found");
		}
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
		waitUntilElementIsDisplayed(hoverLocator);
		String javaScript = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";

		((JavascriptExecutor) driver).executeScript(javaScript, findElement(hoverLocator));
		waitUntilElementIsDisplayed(clickLocator);
		clickElement(clickLocator);

	}
	
	/**
	 * Mouse hover and click element.
	 *
	 * @param hoverE the hover e
	 * @param clickE the click e
	 */
	public void mouseHoverAndClickElement(WebElement hoverE, WebElement clickE) {
		waitUntilElementIsDisplayed(hoverE);
		String javaScript = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";

		((JavascriptExecutor) driver).executeScript(javaScript, hoverE);
		waitUntilElementIsDisplayed(clickE);
		waitUntilElementIsClickable(hoverE);
		clickElement(clickE);
	}
	
	
	/**
	 * Right click menu option text.
	 *
	 * @param option
	 */
	
	public void waitUntilContextMenuOptionIsDisplayed(String option)
	{
		waitUntilElementIsDisplayed(By.xpath(".//ul[@class='context-menu-list context-menu-root'][contains(@style,'top')]//li//span[text()='"+option+"']"));
	}
	
	/**
	 * Mouse hover and click element Text.
	 *
	 * @param option1 the option1
	 * @param option2 the option2
	 */
	
	public void clickContextMenuOption(String option1, String...option2)
	{
		By hoverOption = By.xpath(".//ul[@class='context-menu-list context-menu-root'][contains(@style,'top') and not(contains(@style,'display: none'))]//li//span[text()='"+option1+"']");
		if(option2 != null && option2.length > 0) {
			By selectedOption = By.xpath(".//ul[@class='context-menu-list context-menu-root'][contains(@style,'top') and not(contains(@style,'display: none'))]//li//span[text()='"+Arrays.asList(option2).get(0)+"']");
			mouseHoverAndClickElement(hoverOption, selectedOption);
		} else
			mouseHoverAndClickElement(hoverOption, hoverOption);
	}

	/**
	 * Accept Alert And Wait For Page Load.
	 */
	public void acceptAlertAndWait() {

		try {
			waitUntilAlertIsPresent(10);
			Alert alert = getWebDriver().switchTo().alert();
			alert.accept();
		}
		catch (Throwable t) {
			log.info("alert was not found");
		}
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Accept Alert And Wait For Page Load.
	 *
	 * @return true, if is alert present
	 */
	public boolean isAlertPresent() {
		Alert alert;
		try {
			alert = getWebDriver().switchTo().alert();
			if(alert != null)
				return true;
		}
		catch (Throwable t) {
			log.info("alert was not found");
			return false;
		}
		
		return false;
	}
	
	/**
	 * Deselect all in multi select.
	 * 
	 * @param locator
	 *            the locator
	 */
	public void deselectAllInMultiSelect(By locator) {
		waitUtils.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
		Select select = new Select(findElement(locator));
		select.deselectAll();
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Checks if is displayed folder with title.
	 * 
	 * @param folderTitle
	 *            the folder title
	 * @return true, if is displayed folder with title
	 */
	public boolean isDisplayedFolderWithTitle(String folderTitle) {
		String linkCSS = "a[title=\"" + folderTitle + "\"]";
		return isDisplayed(By.cssSelector(linkCSS));

	}

	/**
	 * Click folder with title.
	 * 
	 * @param folderText
	 *            the folder text
	 */
	public void clickFolderWithTitle(String folderText) {
		String linkCSS = "a[title=\"" + folderText + "\"]";
		WebDriverWait wait = new WebDriverWait(driver, 3);
		waitUntilElementIsDisplayed(By.cssSelector(linkCSS));
		waitUntilElementIsClickable(By.cssSelector(linkCSS));
		clickElementAndWait(By.cssSelector(linkCSS));
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
			log.error("Accepted the alert successfully.");
		} catch (Throwable e) {
			log.info("Alert not found." + e.getMessage());
		}
	}

	/**
	 * Click icon with title.
	 * 
	 * @param iconTitle
	 *            the icon title
	 */
	public void clickIconWithTitle(String iconTitle) {
		String linkCSS = "a img[title='" + iconTitle + "']";
		waitUntilElementIsDisplayed(By.cssSelector(linkCSS));
		clickElementAndWait(By.cssSelector(linkCSS));
	}

	/**
	 * execute JScript.
	 * 
	 * @param script
	 *            the jQuery or Javascript
	 */
	public void executeJScript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
		waitUtils.waitForCompletePageLoad();
	}

	/**
	 * Initialize utils.
	 */
	private void initializeUtils() {

		/** Initialize Java Util */
		initiateJavaUtilInstance();

		/** Initialize Date Util */
		initiateDateUtilsInstance();

		/** Initialize Scenario Util */
		initiateScenarioUtilInstance();

		/** Initialize Error String Util */
		initiateErrorStringUtilInstance();

		/** Initialize System Util */
		initiateSysUtilsInstance();

		/** Initialize AutoIt Util */
		initiateAutoItUtilsInstance();

		/** Initialize Excel Util */
		initiateExcelUtilsInstance();

		/** Initialize List Util */
		initiateListUtilsInstance();

		/** Initialize List Util */
		initiateStringUtilsInstance();

		/** Initialize Email Util */
		initiateEmailUtilsInstance();

		/** Initialize Resource Util */
		initiateResourceUtilsInstance();

		/** Initialize Data Check Util */
		initiateDataCheckUtilsInstance();
		
		/** Initialize PDF Util */
		initiatePdfUtilsInstance();

		/** Initialize Property Util */
		initiatePropertyUtilsInstance();
	}

	/**
	 * Click button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 */
	public void clickClassicButtonWithText(String buttonText) {
		String buttonCSS;
		try {
			buttonCSS = "input[type='button'][value='" + buttonText + "']";
			waitUntilElementIsDisplayed(By.cssSelector(buttonCSS));
			waitUntilElementIsClickable(By.cssSelector(buttonCSS));
			clickElement(By.cssSelector(buttonCSS));
		}
		catch (Throwable t) {
			buttonCSS = "input[type='submit'][value='" + buttonText + "']";
			waitUntilElementIsDisplayed(By.cssSelector(buttonCSS));
			waitUntilElementIsClickable(By.cssSelector(buttonCSS));
			clickElement(By.cssSelector(buttonCSS));
		}
	}

	/**
	 * Wait until file is downloaded.
	 * 
	 * @param file
	 *            the file
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitUntilFileIsDownloaded(File file) throws InterruptedException {

		int counter = 1;
		while (!file.exists()) {
			log.info("Waiting for file to be downloaded: " + file.toString());
			waitUtils.waitInterval(2);
			counter++;
			if (counter > 120) {
				AutomationAssert.verifyTrue(eStringUtils.getFileDownloadError(file.toString()), false);
				break;
			}
		}
	}

	/**
	 * Wait until file is downloaded With Text.
	 *
	 * @param partialFileName
	 *            the partialFileName
	 * @param folderPath
	 *            the folderPath
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitForFileWithPartialName(String partialFileName, String folderPath) throws InterruptedException {
		boolean fileFound = false;
		int counter = 1;
		while (! fileFound) {
			for(String s: sysUtils.getFileListOfSystemFolder(folderPath)) {
				if(s.contains(partialFileName)) {
					log.info("Default downloaded report file found : " + s);
					fileFound = true;
					break;
				}
				else {
					log.info("Searching downloaded report file in default download folder :"+s);
					waitUtils.waitInterval(1);
				}

				if (counter > 120) {
					AutomationAssert.verifyTrue("File with partial file name '"+partialFileName+"' not found", false);
					break;
				}
			}
		}
	}


	
	/**
	 * Switch window.
	 *
	 * @param locator the locator
	 * @throws Exception the exception
	 */
	public void switchWindow(By locator) {
		ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		log.info("Number of windows found: " + tabs.size());
		for (String tab : getWebDriver().getWindowHandles()) {
			log.info("Before switch window active window Title: " + getWebDriver().getTitle());
			log.info("Before switch window active window URL : " + getCurrentWindowURL());
			switchWindow(tab);
			handleAlertPopup();
			log.info("After switch window active window Title : " + getWebDriver().getTitle());
			log.info("After switch window active window URL : " + getCurrentWindowURL());
			waitForCompletePageLoad();
			if (isDisplayed(locator)) {
				collectionDataMap.put("Current window title:: ", getCurrentWindowURL());
				takeScreenShot(TestDriverFactory.scenario);
				break;
			}
		}

	}

	
	/**
	 * Switch window.
	 *
	 * @param locator the locator
	 * @param windowTitle the window title
	 * @throws Exception the exception
	 */
	public void switchWindow(By locator, String windowTitle) {
		ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		log.info("Number of windows found: " + tabs.size());
		for (String tab : getWebDriver().getWindowHandles()) {
			log.info("Before switch window active window Title: " + getWebDriver().getTitle());
			log.info("Before switch window active window URL : " + getCurrentWindowURL());
			switchWindow(tab);
			handleAlertPopup();
			log.info("After switch window active window Title : " + getWebDriver().getTitle());
			log.info("After switch window active window URL : " + getCurrentWindowURL());
			waitForCompletePageLoad();
			if (isDisplayed(locator) || getWebDriver().getTitle().contains(windowTitle)) {
				takeScreenShot(TestDriverFactory.scenario);
				break;
			}
		}
	}
	
	/**
	 * Take screen shot.
	 * 
	 * @param scenario
	 *            the scenario
	 */
	public void takeScreenShot(Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}
	
	/**
	 * Handle alert popup.
	 */
	public void handleAlertPopup() {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
			log.error("Accepted the alert successfully.");
		}
		catch (Throwable e) {
			log.info("Error came while waiting for the alert popup. " + e.getMessage());
		}
	}

	
	/**
	 * Wait for element value to be.
	 *
	 * @param locator the locator
	 * @param value the value
	 * @param timeout the timeout
	 */
	public void waitForElementValueToBe(By locator, String value, int...timeout) {
		long waitFor;
		if(timeout.length > 0)
			waitFor = timeout[0];
		else
			waitFor = Integer.parseInt(getConfigTimeoutValue());
		WebDriverWait wait = new WebDriverWait(getWebDriver(), waitFor);
		wait.until(ExpectedConditions.textToBePresentInElementValue(locator, value));
	}

	protected void sendActionKeys(Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
	}
}