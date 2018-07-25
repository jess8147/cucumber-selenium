package org.asite.automation.common.errors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

// TODO: Auto-generated Javadoc
/**
 * The Interface AutomationErrorStrings.
 * @author jasminprajapati
 */
public interface AutomationErrorStrings {

	/**
	 * Gets the string.
	 *
	 * @param o the o
	 * @return the string
	 */
	public String getString(Object o);
	
	/**
	 * Gets the time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(By by, int t) ;
	
	/**
	 * Gets the time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(By by, String t);
	
	/**
	 * Gets the time out error.
	 *
	 * @param e the e
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(WebElement e, int t);

	/**
	 * Gets the time out error.
	 *
	 * @param e the e
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(WebElement e, String t);
	
	/**
	 * Gets the time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @param expectedCount the expected count
	 * @param actualCount the actual count
	 * @return the time out error
	 */
	public String getTimeOutError(By by, String t, int expectedCount, int actualCount);
	
	/**
	 * Gets the click failure error.
	 *
	 * @param by the by
	 * @return the click failure error
	 */
	public String getClickFailureError(By by);
	
	/**
	 * Gets the click failure error.
	 *
	 * @param e the e
	 * @return the click failure error
	 */
	public String getClickFailureError(WebElement e);
	
	
	/**
	 * Gets the tear down error.
	 *
	 * @return the tear down error
	 */
	public String getTearDownError();
	
	/**
	 * Gets the page load error.
	 *
	 * @param t the t
	 * @return the page load error
	 */
	public String getPageLoadError(String t) ;
	
	/**
	 * Gets the ajax load error.
	 *
	 * @param t the t
	 * @return the ajax load error
	 */
	public String getAjaxLoadError(String t) ;
	
	/**
	 * Gets the find failure error.
	 *
	 * @param by the by
	 * @return the find failure error
	 */
	public String getFindFailureError(By by) ;
	
	/**
	 * Gets the new window failure error.
	 *
	 * @param windows the windows
	 * @return the new window failure error
	 */
	public String getNewWindowFailureError(int windows);
	
	/**
	 * Gets the invisibility time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the invisibility time out error
	 */
	public String getInvisibilityTimeOutError(By by, String t);
	
	/**
	 * Gets the text visibility timeout error.
	 *
	 * @param by the by
	 * @param t the t
	 * @param text the text
	 * @return the text visibility timeout error
	 */
	public String getTextVisibilityTimeoutError(By by, String t, String text);
	
	/**
	 * Gets the text visibility timeout error.
	 *
	 * @param e the e
	 * @param t the t
	 * @param text the text
	 * @return the text visibility timeout error
	 */
	public String getTextVisibilityTimeoutError(WebElement e, String t, String text);
	
	
	/**
	 * Gets the presence time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the presence time out error
	 */
	public String getPresenceTimeOutError(By by, String t);
	
	/**
	 * Gets the clickable failure error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the clickable failure error
	 */
	public String getClickableFailureError(By by, String t);

	/**
	 * Gets the clickable failure error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the clickable failure error
	 */
	public String getEnableFailureError(By by, String t);


	/**
	 * Gets the clear element failure error.
	 *
	 * @param by the by
	 * @return the clear element failure error
	 */
	public String getClearElementFailureError(By by);
	
	/**
	 * Gets the reload time out error.
	 *
	 * @return the reload time out error
	 */
	public String getReloadTimeOutError();

	/**
	 * Gets the send keys time out error.
	 *
	 * @param locator the locator
	 * @param string the string
	 * @return the send keys time out error
	 */
	public String getSendKeysTimeOutError(By locator, String string);
	
	/**
	 * Gets the file download error.
	 *
	 * @return the file download error
	 */
	public String getFileDownloadError(String file);
	
	/**
	 * Gets the file exists error.
	 *
	 * @return the file exists error
	 */
	public String getFileExistsError(String file);
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the in equality error string
	 */
	public String getInEqualityStringError(String comparator1, String comparator2);
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @param b the b
	 * @return the in equality error string
	 */
	public String getInEqualityStringError(String comparator1, String comparator2, boolean b);
	
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the in equality error string
	 */
	public String getInEqualityStringError(int comparator1, int comparator2);
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param by the by
	 * @param b the b
	 * @return the in equality error string
	 */
	public String getVisibilityStringError(By by, boolean b);
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param e the e
	 * @param b the b
	 * @return the in equality error string
	 */
	public String getVisibilityStringError(WebElement e, boolean b);
	
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the in equality error string
	 */
	public String getInEqualityStringError(double comparator1, double comparator2);
	
	
	/**
	 * Gets the contains error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the contains error string
	 */
	public String getContainsStringError(String comparator1, String comparator2);
	
	/**
	 * Gets the contains error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the contains error string
	 */
	public String getNotContainsStringError(String comparator1, String comparator2);
	
	
	/**
	 * Gets the java cache cleanup error.
	 *
	 * @return the java cache cleanup error
	 */
	public String getJavaCacheCleanupError();

	
	/**
	 * Gets the dir creation error.
	 *
	 * @param string the string
	 * @return the dir creation error
	 */
	public String getDirCreationError(String string);
	
	/**
	 * Gets the element selection failure error.
	 *
	 * @param by the by
	 * @param b the b
	 * @return the element selection failure error
	 */
	public String getElementSelectionError(By by, boolean b);
	
	/**
	 * Gets the element selection error.
	 *
	 * @param e the e
	 * @param b the b
	 * @return the element selection error
	 */
	public String getElementSelectionError(WebElement e, boolean b);
	
	/**
	 * Gets the element count error.
	 *
	 * @param by the by
	 * @param flag the flag
	 * @param count the count
	 * @return the element count error
	 */
	public String getElementCountError(By by, int flag , int count);
	
	/**
	 * Gets the action completion error.
	 *
	 * @param action the action
	 * @return the action completion error
	 */
	public String getActionCompletionError(String action);
	
	/**
	 * Gets the attribute contains error.
	 *
	 * @param by the by
	 * @param attribute the attribute
	 * @param value the value
	 * @param t the t
	 * @return the attribute contains error
	 */
	public String getAttributeContainsError(By by, String attribute, String value, String t);
	
	/**
	 * Gets the equality string error.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the equality string error
	 */
	public String getEqualityStringError(String comparator1, String comparator2);
	
	/**
	 * Gets the Dropdown value missing Error.
	 *
	 * @param by the Dropdown Locator
	 * @param val the Value
	 * @param time the time
	 * @return the equality string error
	 */
	public String getDropdownValueError(By by, String val, String time);
}
