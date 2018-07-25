package org.asite.automation.common.base;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

// TODO: Auto-generated Javadoc
/**
 * The Class SeleniumWrappers.
 * @author jasminprajapati
 */
public interface TestBaseLibrary {

	
	void setConfigFile(String config);

	/**
	 * Initialize web driver wait.
	 *
	 * @return the config file
	 */
	String getConfigFile();

	/**
	 * Gets the config timeout value.
	 *
	 * @return the config timeout value
	 */
	String getConfigTimeoutValue();
	/**
	 * Navigate url.
	 * 
	 * @param url
	 *            the url
	 */
	void navigateURL(String url);

	/**
	 * Maximize window.
	 */
	void maximizeWindow();

	/**
	 * Find element.
	 * 
	 * @param locator
	 *            the locator
	 * @return the web element
	 */
	WebElement findElement(By locator);
	/**
	 * Find elements.
	 * 
	 * @param locator
	 *            the locator
	 * @return the list
	 */
	List<WebElement> findElements(By locator);

	/**
	 * Wait interval.
	 * 
	 * @param f
	 *            the f
	 */
	void waitInterval(float f) ;

	/**
	 * Switch_ iframe.
	 * 
	 * @param locator
	 *            the locator
	 */
	void switchIframe(By locator);

	/**
	 * Drag and drop element.
	 *
	 * @param dragElement the drag element
	 * @param dropElement the drop element
	 */
	void dragAndDropElement(WebElement dragElement, WebElement dropElement);
	
	/**
	 * Switch_default.
	 */
	void switchDefault();

	/**
	 * Tear down.
	 */
	void tearDown();

	/**
	 * Clear.
	 * 
	 * @param locator
	 *            the locator
	 */
	void clear(By locator) ;

	/**
	 * Context click.
	 * 
	 * @param locator
	 *            the locator
	 */
	void contextClick(By locator);
	/**
	 * Context click.
	 *
	 * @param e
	 *            the e
	 */
	void contextClickWebElement(WebElement e);

	/**
	 * Gets the element text.
	 * 
	 * @param locator
	 *            the locator
	 * @return the element text
	 */
	String getElementText(By locator);

	/**
	 * Gets the current window.
	 * 
	 * @return the current window
	 */
	String getCurrentWindow();

	/**
	 * Gets the current Window URL.
	 * 
	 * @return the current window URL
	 */
	String getCurrentWindowURL();

	/**
	 * Switch window.
	 */
	void switchWindow() ;

	/**
	 * Switch window.
	 * 
	 * @param handle
	 *            the handle
	 */
	void switchWindow(String handle);

	/**
	 * Gets the window handles.
	 * 
	 * @return the window handles
	 */
	Set<String> getWindowHandles();

	/**
	 * Switch previous window.
	 * 
	 * @param parentHandle
	 *            the parent handle
	 */
	void switchPreviousWindow(String parentHandle);

	/**
	 * Switch Third window.
	 * 
	 * @param parentHandle1
	 *            the parent Window
	 * @param parentHandle2
	 *            the Child Window
	 */
	void switchToThirdWindow(String parentHandle1, String parentHandle2);
	
	
	/**
	 * Switch to Blank tab like use Control + t.
	 */
	void openedNewTab();

	/**
	 * Wait for i frame load.
	 * 
	 * @return the expected condition
	 */
	ExpectedCondition<Boolean> waitForIFrameLoad();

	/**
	 * Wait for i frame load.
	 * 
	 * @param locator
	 *            the locator
	 */
	void waitForIFrameLoad(By locator);

	/*
	 * public static ExpectedCondition<Boolean>
	 * waitForSwitchWindowCondition(final int numberOfWindows) { return new
	 * ExpectedCondition<Boolean>() { boolean apply(WebDriver driver) {
	 * driver.getWindowHandles(); return driver.getWindowHandles().size() ==
	 * numberOfWindows; } }; }
	 */

	/**
	 * Click element and wait.
	 * 
	 * @param locator
	 *            the locator
	 */
	void clickElementAndWait(By locator);

	/**
	 * Click element if enabled.
	 *
	 * @param locator
	 *            the locator
	 */
	void clickElementIfEnabled(By locator);

	/**
	 * Click element and wait Until Element Is Invisible.
	 *
	 * @param clickElement the click element
	 * @param visibleElement the visible element
	 */
	void clickElementAndWaitUntilElementInvisible(By clickElement, By visibleElement);

	/**
	 * Click element and wait.
	 *
	 * @param e the e
	 */
	void clickElementAndWait(WebElement e);

	/**
	 * Click element and switch window.
	 *
	 * @param locator the locator
	 * @return the string
	 */
	String clickElementAndSwitchWindow(By locator);
	
	
	/**
	 * Reload page.
	 */
	void reloadPage();

	/**
	 * Click element and wait for element.
	 * 
	 * @param clickLocator
	 *            the click locator
	 * @param waitLocator
	 *            the wait locator
	 */
	void clickElementAndWaitForElement(By clickLocator, By waitLocator);

	/**
	 * Click element and wait for invisibility of element.
	 *
	 * @param clickLocator
	 *            the click locator
	 * @param invisibilityLocator
	 *            the invisibility locator
	 */
	void clickElementAndWaitForInvisibilityOfElement(By clickLocator, By invisibilityLocator);
	
	/**
	 * Click element and wait for invisibility of element.
	 *
	 * @param clickLocator            the click locator
	 * @param invisibilityLocator            the invisibility locator
	 * @param timeout the timeout
	 */
	void clickElementAndWaitForInvisibilityOfElement(By clickLocator, By invisibilityLocator, int timeout) ;

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
	void retryClickElementAndWaitForElement(By clickLocator, By waitLocator, int retries) ;

	/**
	 * Click element.
	 * 
	 * @param locator
	 *            the locator
	 */
	void clickElement(By locator);

	/**
	 * Wait until list of all elements are displayed.
	 * 
	 * @param locator
	 *            the locator
	 */
	void waitUntilListOfElementIsDisplayed(By locator);

	/**
	 * Wait until element is displayed.
	 * 
	 * @param locator
	 *            the locator
	 */
	void waitUntilElementIsDisplayed(By locator);

	/**
	 * Wait until element is displayed.
	 *
	 * @param locator            the locator
	 * @param timeOut the time out
	 */
	void waitUntilElementIsDisplayed(By locator, int timeOut);

	/**
	 * Wait until element is displayed.
	 *
	 * @param e the e
	 */
	void waitUntilElementIsDisplayed(WebElement e) ;

	/**
	 * Wait until element is present.
	 *
	 * @param locator            the locator
	 * @param timeout the timeout
	 */
	void waitUntilElementIsPresent(By locator, int timeout) ;

	/**
	 * Wait until element is present.
	 * 
	 * @param locator
	 *            the locator
	 */
	void waitUntilElementIsPresent(By locator);
	/**
	 * Wait until element is invisible.
	 * 
	 * @param locator
	 *            the locator
	 */
	void waitUntilElementIsInvisible(By locator) ;

	/**
	 * Wait until element is invisible.
	 *
	 * @param locator            the locator
	 * @param timeout the timeout
	 */
	void waitUntilElementIsInvisible(By locator, int timeout) ;

	/**
	 * Wait until element is Selected.
	 * 
	 * @param locator
	 *            the locator
	 */
	void waitUntilElementIsSelected(By locator) ;
	/**
	 * Checks if is displayed.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is displayed
	 */
	boolean isDisplayed(By locator) ;

	/**
	 * Checks if is displayed.
	 *
	 * @param e the e
	 * @return true, if is displayed
	 */
	boolean isDisplayed(WebElement e);

	/**
	 * Checks if is displayed.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is displayed
	 */
	boolean isElementPresent(By locator) ;

	/**
	 * Checks if is selected.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is selected
	 */
	boolean isSelected(By locator);
	/**
	 * Checks if is selected.
	 * 
	 * @param locator
	 *            the locator
	 * @return true, if is selected
	 */
	boolean isEnabled(By locator) ;
	/**
	 * Close current window.
	 */
	void closeCurrentWindow() ;
	/**
	 * Mouse hover.
	 * 
	 * @param locator
	 *            the locator
	 */
	void mouseHover(By locator) ;

	/**
	 * Verify element text.
	 * 
	 * @param locator
	 *            the locator
	 * @param text
	 *            the text
	 */
	void verifyElementText(By locator, String text);

	/**
	 * Mouse hover.
	 *
	 * @param ele the ele
	 */
	void mouseHoverWebElement(WebElement ele);
	/**
	 * Verify element text contains. f
	 * 
	 * @param locator
	 *            the locator
	 * @param text
	 *            the text
	 */
	void verifyElementTextContains(By locator, String text);

	/**
	 * Gets the count.
	 * 
	 * @param locator
	 *            the locator
	 * @return the count
	 */
	int getCount(By locator) ;

	/**
	 * Send keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param keys
	 *            the keys
	 */
	void sendKeys(By locator, String keys);

	/**
	 * Send keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param keys
	 *            the keys
	 */
	void sendKeys(By locator, Keys keys) ;

	/**
	 * Send keys.
	 *
	 * @param element the element
	 * @param keys            the keys
	 */
	void sendKeys(WebElement element, Keys keys) ;

	/**
	 * Wait for switch window.
	 * 
	 * @param numberofWindows
	 *            the numberof windows
	 */
	void waitForSwitchWindow(int numberofWindows) ;
	/**
	 * Drag mouse.
	 * 
	 * @param locator
	 *            the locator
	 */
	void dragMouse(By locator) ;

	/**
	 * Gets the tool tip text.
	 * 
	 * @param locator
	 *            the locator
	 * @return the tool tip text
	 */
	String getToolTipText(By locator);

	/**
	 * Gets the value.
	 * 
	 * @param locator
	 *            the locator
	 * @return the value
	 */
	String getValue(By locator);

	/**
	 * Gets the selected dropdown label.
	 * 
	 * @param locator
	 *            the locator
	 * @return the selected dropdown label
	 */
	String getSelectedDropdownLabel(By locator);

	/**
	 * Gets the selected dropdown label.
	 *
	 * @param e the e
	 * @return the selected dropdown label
	 */
	String getSelectedDropdownLabel(WebElement e);
	/**
	 * Click and hold element.
	 * 
	 * @param locator
	 *            the locator
	 */
	void clickAndHoldElement(By locator) ;

	/**
	 * Release element click.
	 * 
	 * @param locator
	 *            the locator
	 */
	void releaseElementClick(By locator) ;
	/**
	 * Upload file using keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param fileLocation
	 *            the file location
	 */
	void uploadFileUsingKeys(By locator, List<String> fileLocation);

	/**
	 * Upload file using keys.
	 * 
	 * @param locator
	 *            the locator
	 * @param fileLocation
	 *            the file location
	 */
	void uploadMultipleFilesUsingKeys(By locator, List<String> fileLocation) ;

	/**
	 * Wait for ready state.
	 * 
	 * @return the expected condition
	 */
	ExpectedCondition<Boolean> waitForReadyState() ;

	/**
	 * Wait for ajax complete.
	 * 
	 * @return the expected condition
	 */
	ExpectedCondition<Boolean> waitForAjaxComplete() ;

	/**
	 * Wait for complete page load.
	 * 
	 * @return the web driver
	 */
	WebDriver waitForCompletePageLoad() ;
	
	/**
	 * Wait until element count to be.
	 *
	 * @param locator            the locator
	 * @param expectedCount the expected count
	 */
	void waitUntilElementCountToBe(By locator, int expectedCount);

	/**
	 * Wait until alert is present.
	 */
	void waitUntilAlertIsPresent() ;

	/**
	 * Wait until alert is present with TimeOut.
	 *
	 * @param timeOutInSeconds the time out in seconds
	 */
	void waitUntilAlertIsPresent(int timeOutInSeconds);

	/**
	 * Select by visible text.
	 * 
	 * @param locator
	 *            the locator
	 * @param visibleText
	 *            the visible text
	 */
	void selectByVisibleText(By locator, String visibleText);

	/**
	 * Select by visible text.
	 *
	 * @param e the e
	 * @param visibleText            the visible text
	 */
	void selectByVisibleText(WebElement e, String visibleText);

	/**
	 * Select by value.
	 * 
	 * @param locator
	 *            the locator
	 * @param value
	 *            the value
	 */
	void selectByValue(By locator, String value);

	/**
	 * Select by index.
	 * 
	 * @param locator
	 *            the locator
	 * @param index
	 *            the index
	 */
	void selectByIndex(By locator, int index);

	/**
	 * Select by index.
	 *
	 * @param e the e
	 * @param index            the index
	 */
	void selectByIndex(WebElement e, int index);
	/**
	 * Context click with text.
	 * 
	 * @param elementText
	 *            the element text
	 */
	void contextClickWithText(String elementText);
	/**
	 * Context click with link.
	 * 
	 * @param elementLinkText
	 *            the element link text.
	 */
	void contextClickWithLink(String elementLinkText);
	/**
	 * Click context menu option with text.
	 * 
	 * @param elementText
	 *            the element text
	 */
	void clickContextMenuOptionWithText(String elementText) ;

	/**
	 * Click link with text.
	 * 
	 * @param linkText
	 *            the link text
	 */
	void clickLinkWithText(String linkText) ;

	/**
	 * Click link with title.
	 * 
	 * @param linkTitle
	 *            the link title
	 */
	void clickLinkWithTitle(String linkTitle) ;

	/**
	 * Click button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 */
	void clickButtonWithText(String buttonText) ;

	/**
	 * Click popup button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 */
	void clickPopupButtonWithText(String buttonText) ;

	/**
	 * Click element with text.
	 * 
	 * @param elementText
	 *            the element text
	 */
	void clickElementWithText(String elementText) ;

	/**
	 * Checks if is displayed link with text.
	 * 
	 * @param linkText
	 *            the link text
	 * @return true, if is displayed link with text
	 */
	boolean isDisplayedLinkWithText(String linkText);

	/**
	 * Checks if is displayed button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 * @return true, if is displayed button with text
	 */
	boolean isDisplayedButtonWithText(String buttonText);

	/**
	 * Checks if is displayed element with text.
	 * 
	 * @param elementText
	 *            the element text
	 * @return true, if is displayed element with text
	 */
	boolean isDisplayedElementWithText(String elementText);

	/**
	 * Checks if is displayed element with text.
	 * 
	 * @param elementText
	 *            the element text
	 * @return true, if is displayed element with text
	 */
	void waitForElementWithText(String elementText) ;

	/**
	 * Checks if is displayed element with text.
	 *
	 * @param elementText            the element text
	 * @param timeout the timeout
	 * @return true, if is displayed element with text
	 */
	void waitForElementWithText(String elementText, int timeout) ;
	
	/**
	 * Double click.
	 * 
	 * @param locator
	 *            the locator
	 */
	void doubleClick(By locator);
	/**
	 * Wait and switch iframe.
	 * 
	 * @param locator
	 *            the locator
	 */
	/*
	 * void waitAndSwitchIframe(By locator) {
	 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	 * }
	 */

	void waitAndSwitchIframe(By locator);
	/**
	 * Click Checkbox with Value.
	 * 
	 * @param checkBoxValue
	 *            the checkBox value
	 */
	void clickCheckBoxWithValue(String checkBoxValue);
	/**
	 * Wait until element is clickable.
	 * 
	 * @param locator
	 *            the locator
	 */
	void waitUntilElementIsClickable(By locator);
	
	/**
	 * Wait until element is clickable.
	 *
	 * @param element the element
	 */
	void waitUntilElementIsClickable(WebElement element);
	
	/**
	 * Wait until List of element is clickable.
	 * 
	 * @param element
	 *            the element
	 */
	void waitUntilListElementIsClickable(WebElement element);
	
	/**
	 * Double click Element And Wait.
	 *
	 * @param locator            the locator
	 */
	void doubleClickAndWait(By locator);

	/**
	 * Wait until element contains text.
	 * 
	 * @param locator
	 *            the locator
	 * @param text
	 *            the text
	 */
	void waitUntilElementContainsText(By locator, String text);
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
	void waitUntilAttributeContainsValue(By locator, String attribute, String value);
	/**
	 * Accept Alert.
	 */
	void acceptAlert() ;

	/**
	 * Mouse hover and click element.
	 * 
	 * @param hoverLocator
	 *            the hover locator
	 * @param clickLocator
	 *            the click locator
	 */
	void mouseHoverAndClickElement(By hoverLocator, By clickLocator) ;

	/**
	 * Accept Alert And Wait For Page Load.
	 */
	void acceptAlertAndWait();

	/**
	 * Deselect all in multi select.
	 * 
	 * @param locator
	 *            the locator
	 */
	void deselectAllInMultiSelect(By locator) ;

	/**
	 * Checks if is displayed folder with title.
	 * 
	 * @param folderTitle
	 *            the folder title
	 * @return true, if is displayed folder with title
	 */
	boolean isDisplayedFolderWithTitle(String folderTitle) ;

	/**
	 * Click folder with title.
	 * 
	 * @param folderText
	 *            the folder text
	 */
	void clickFolderWithTitle(String folderText);
	/**
	 * Click icon with title.
	 * 
	 * @param iconTitle
	 *            the icon title
	 */
	void clickIconWithTitle(String iconTitle) ;
	
	/**
	 * execute JScript.
	 *
	 * @param script            the jQuery or Javascript
	 */
	void executeJScript(String script);
	
	/**
	 * Initialize utils.
	 */
	void initializeUtils() ;

	/**
	 * Click button with text.
	 * 
	 * @param buttonText
	 *            the button text
	 */
	void clickClassicButtonWithText(String buttonText) ;

	/**
	 * Initiate java util instance.
	 */
	void initiateJavaUtilInstance();

	/**
	 * Initiate date utils instance.
	 */
	void initiateDateUtilsInstance();

	/**
	 * Initiate scenario util instance.
	 */
	void initiateScenarioUtilInstance();

	/**
	 * Initiate error string util instance.
	 */
	void initiateErrorStringUtilInstance();

	/**
	 * Initiate excel utils instance.
	 */
	void initiateExcelUtilsInstance();

	/**
	 * Initiate auto it utils instance.
	 */
	void initiateAutoItUtilsInstance();

	/**
	 * Initiate sys utils instance.
	 */
	void initiateSysUtilsInstance();

}
