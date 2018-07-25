package org.asite.automation.common.utils;

import org.asite.automation.common.errors.AutomationErrorStrings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleErrorStringUtils.
 * @author jasminprajapati
 */
public class ErrorStringUtils implements AutomationErrorStrings{

	/**
	 * Gets the string.
	 *
	 * @param o the o
	 * @return the string
	 */
	public String getString(Object o) {
		return o.toString();
	}
	
	/**
	 * Gets the time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(By by, int t) {
		return "\nERROR: Timeout after "+t+"s for "+getString(by);
	}


	/* (non-Javadoc)
	 * @see org.asite.automation.CommonErrors.AutomationErrorStrings#getSendKeysTimeOutError(org.openqa.selenium.By, java.lang.String)
	 */
	public String getSendKeysTimeOutError(By by, String t) {
		return "\nERROR: Timeout after "+t+"s for clearing/sending text to "+getString(by);
	}
	
	/**
	 * Gets the time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(By by, String t) {
		return "\nERROR: Timeout after "+t+"s for "+getString(by);
	}
	
	/**
	 * Gets the time out error.
	 *
	 * @param e the e
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(WebElement e, int t) {
		return "\nERROR: Timeout after "+t+"s for "+getString(e);
	}

	/**
	 * Gets the time out error.
	 *
	 * @param e the e
	 * @param t the t
	 * @return the time out error
	 */
	public String getTimeOutError(WebElement e, String t) {
		return "\nERROR: Timeout after "+t+"s for "+getString(e);
	}
	
	/**
	 * Gets the time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @param expectedCount the expected count
	 * @param actualCount the actual count
	 * @return the time out error
	 */
	public String getTimeOutError(By by, String t, int expectedCount, int actualCount) {
		return "\nERROR: Timeout after "+t+"s for count to be "+expectedCount+" for "+getString(by)+"\n Actual count: "+actualCount;
	}
	
	/**
	 * Gets the click failure error.
	 *
	 * @param by the by
	 * @return the click failure error
	 */
	public String getClickFailureError(By by) {
		return "\nERROR: Click failure for "+getString(by);
	}
	
	/**
	 * Gets the click failure error.
	 *
	 * @param e the e
	 * @return the click failure error
	 */
	public String getClickFailureError(WebElement e) {
		return "\nERROR: Click failure for "+getString(e);
	}
	
	
	/**
	 * Gets the tear down error.
	 *
	 * @return the tear down error
	 */
	public String getTearDownError() {
		return "\nERROR: Unable to close browser properly";
	}
	
	/**
	 * Gets the page load error.
	 *
	 * @param t the t
	 * @return the page load error
	 */
	public String getPageLoadError(String t) {
		return "\nERROR: Page load failure after waiting for "+t+"s";
	}
	
	/**
	 * Gets the ajax load error.
	 *
	 * @param t the t
	 * @return the ajax load error
	 */
	public String getAjaxLoadError(String t) {
		return "\nERROR: Ajax Stuck failure after waiting for "+t+"s";
	}
	
	/**
	 * Gets the find failure error.
	 *
	 * @param by the by
	 * @return the find failure error
	 */
	public String getFindFailureError(By by) {
		return "\nERROR: Element(s) not found with locator :"+getString(by);
	}
	
	/**
	 * Gets the new window failure error.
	 *
	 * @param windows the windows
	 * @return the new window failure error
	 */
	public String getNewWindowFailureError(int windows) {
		return "\nERROR: Unable to open/close window(s) to be "+windows;
	}
	
	/**
	 * Gets the invisibility time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the invisibility time out error
	 */
	public String getInvisibilityTimeOutError(By by, String t) {
		return "\nERROR: Timeout after "+t+"s for invisibility of "+getString(by);
	}
	
	/**
	 * Gets the text visibility timeout error.
	 *
	 * @param by the by
	 * @param t the t
	 * @param text the text
	 * @return the text visibility timeout error
	 */
	public String getTextVisibilityTimeoutError(By by, String t, String text) {
		return "\nERROR: Timeout after "+t+"s for text '"+text+"' to be available in "+getString(by);
	}
	
	/**
	 * Gets the presence time out error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the presence time out error
	 */
	public String getPresenceTimeOutError(By by, String t) {
		return "\nERROR: Timeout after "+t+"s for presence of "+getString(by);
	}
	
	/**
	 * Gets the clickable failure error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the clickable failure error
	 */
	public String getClickableFailureError(By by, String t) {
		return "\nERROR: Element was not clickable after timeout of \"+t+\"s for element "+getString(by);
	}

	/**
	 * Gets the clickable failure error.
	 *
	 * @param by the by
	 * @param t the t
	 * @return the enable failure error
	 */
	public String getEnableFailureError(By by, String t) {
		return "\nERROR: Element was not enabled after timeout of "+t+"s for element "+getString(by);
	}

	/**
	 * Gets the clear element failure error.
	 *
	 * @param by the by
	 * @return the clear element failure error
	 */
	public String getClearElementFailureError(By by) {
		return "\nERROR: Failed to clear for element located by "+getString(by);
	}
	
	/**
	 * Gets the reload time out error.
	 *
	 * @return the reload time out error
	 */
	public String getReloadTimeOutError() {
		return "\nERROR: Timeout in reloading the page";
	}
	
	/**
	 * Gets the file download error.
	 *
	 * @return the file download error
	 */
	public String getFileDownloadError(String file) {
		return "\nERROR: File was not downloaded :"+file;
	}

	
	public String getFileExistsError(String file) {
		return "\nERROR: File does not exist :"+file;
	}
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the in equality error string
	 */
	public String getInEqualityStringError(String comparator1,
			String comparator2) {
		return "\nERROR: "+comparator1+" != "+comparator2+" (comparison failure; should be equal)";
	}
	
	/**
	 * Gets the contains error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the contains error string
	 */
	public String getContainsStringError(String comparator1,
			String comparator2) {
		return "\nERROR: "+comparator1+" should contain "+comparator2;
	}

	/**
	 * Gets the contains error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the contains error string
	 */
	public String getNotContainsStringError(String comparator1,
			String comparator2) {
		return "\nERROR: "+comparator1+" should not contain "+comparator2;
	}
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the in equality error string
	 */
	public String getInEqualityStringError(int comparator1, int comparator2) {
		return "\nERROR: "+comparator1+" != "+comparator2;
	}
	
	/**
	 * Gets the in equality error string.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the in equality error string
	 */
	public String getInEqualityStringError(double comparator1, double comparator2) {
		return "\nERROR: "+comparator1+" != "+comparator2;
	}
	
	/**
	 * Gets the java cache cleanup error.
	 *
	 * @return the java cache cleanup error
	 */
	public String getJavaCacheCleanupError() {
		return "\nERROR: Java cache clean up operation failed";
	}
	
	/**
	 * Gets the dir creation error.
	 *
	 * @param dir the dir
	 * @return the dir creation error
	 */
	public String getDirCreationError(String dir) {
		return "\nERROR: Failed to create directory "+dir;
	}

	/**
	 * Gets the element selection failure error.
	 *
	 * @param by the by
	 * @param flag the flag
	 * @return the element selection failure error
	 */
	public String getElementSelectionError(By by, boolean flag) {
		if(flag)
			return "\nERROR: Element is not selected with locator :"+by.toString();
		else
			return "\nERROR: Element should not be selected with locator :"+by.toString();
	}

	/**
	 * Gets the element selection failure error.
	 *
	 * @param e the e
	 * @param flag the flag
	 * @return the element selection failure error
	 */
	public String getElementSelectionError(WebElement e, boolean flag) {
		if(flag)
			return "\nERROR: Element is not selected with element :"+e.toString();
		else
			return "\nERROR: Element should not be selected with element :"+e.toString();
	}
	
	/**
	 * Gets the element count error.
	 *
	 * @param by the by
	 * @param flag the flag
	 * @param count the count
	 * @return the element count error
	 */
	public String getElementCountError(By by, int flag, int count) {
		if(flag == 0)
				return "\nERROR: Element count should be "+count+" for :"+by.toString();
		else if(flag == 1)
				return "\nERROR: Element count should be greater than "+count+" for :"+by.toString();
		else if(flag == -1)
				return "\nERROR: Element count should be lesser than "+count+" for :"+by.toString();
		return null;
	}
	
	/**
	 * Gets Action Completion error.
	 *
	 * @param action the action
	 * @return the actoin completion error
	 */
	public String getActionCompletionError(String action) {
		return "\nERROR: Action "+action+" was not completed";
	}
	
	
	/**
	 * Gets the attribute contains error.
	 *
	 * @param by the by
	 * @param attribute the attribute
	 * @param value the value
	 * @param t the t
	 * @return the attribute contains error
	 */
	public String getAttributeContainsError(By by, String attribute, String value, String t) {
		return "\nERROR: Timeout after "+t+" seconds waiting for "+attribute+" to contain "+value;
	}

	
	/**
	 * Gets the text visibility timeout error.
	 *
	 * @param e the e
	 * @param t the t
	 * @param text the text
	 * @return the text visibility timeout error
	 */
	public String getTextVisibilityTimeoutError(WebElement e, String t,
			String text) {
		return "\nERROR: Timeout after "+t+"s for text '"+text+"' to be available in "+getString(e);
	}
	
	/**
	 * Gets the equality string error.
	 *
	 * @param comparator1 the comparator1
	 * @param comparator2 the comparator2
	 * @return the equality string error
	 */
	public String getEqualityStringError(String comparator1, String comparator2) {
		return "\nERROR: "+comparator1+" == "+comparator2+" (comparison failure; should not be equal)";
	}

	/* (non-Javadoc)
	 * @see org.asite.automation.CommonErrors.AutomationErrorStrings#getVisibilityStringError(org.openqa.selenium.By)
	 */
	public String getVisibilityStringError(By by, boolean b) {
		if(b)
			return "\nERROR: Element with Locator "+by.toString()+" should be displayed";
		else
			return "\nERROR: Element with Locator "+by.toString()+" should not be displayed";
	}
	
	
	/* (non-Javadoc)
	 * @see org.asite.automation.CommonErrors.AutomationErrorStrings#getVisibilityStringError(org.openqa.selenium.By)
	 */
	public String getVisibilityStringError(WebElement e, boolean b) {
		if(b)
			return "\nERROR: Element with Locator "+e.toString()+" should be displayed";
		else
			return "\nERROR: Element with Locator "+e.toString()+" should not be displayed";
	}
	
	/* (non-Javadoc)
	 * @see org.asite.automation.common.errors.AutomationErrorStrings#getInEqualityStringError(java.lang.String, java.lang.String, boolean)
	 */
	public String getInEqualityStringError(String comparator1, String comparator2, boolean b) {
		// TODO Auto-generated method stub
		if(b)
			return "\nERROR: "+comparator1+" != "+comparator2+" (comparison failure; should be equal)";
		else
			return "\nERROR: "+comparator1+" == "+comparator2+" (comparison failure; should not be equal)";
	}

	
	/**
	 * Gets the dropdown value error.
	 *
	 * @param by the by
	 * @param val the val
	 * @param t the t
	 * @return the dropdown value error
	 */
	public String getDropdownValueError(By by, String val, String t) {
		return "\nERROR: Timeout after "+t+"s for value '"+val+"' to be available in "+getString(by); 
	}
}
