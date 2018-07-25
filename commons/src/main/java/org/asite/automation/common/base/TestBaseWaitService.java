package org.asite.automation.common.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

// TODO: Auto-generated Javadoc

/**
 * The Interface TestBaseWaitService.
 *
 * @author jasminprajapati
 */
public interface TestBaseWaitService {

	/**
	 * Wait for complete page load.
	 *
	 * @return the web driver
	 */
	public WebDriver waitForCompletePageLoad();

	/**
	 * Wait for ready state.
	 *
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> waitForReadyState();

	/**
	 * Wait for ajax complete.
	 *
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> waitForAjaxComplete();

	/**
	 * Sets the web driver wait.
	 *
	 * @param webDriverWait the new web driver wait
	 */
	public void setWebDriverWait(WebDriverWait webDriverWait);

	/**
	 * Gets the web driver wait.
	 *
	 * @return the web driver wait
	 */
	public WebDriverWait getWebDriverWait();


	/**
	 * Wait interval.
	 *
	 * @param seconds the seconds
	 */
	public void waitInterval(float  seconds) throws InterruptedException;
}
