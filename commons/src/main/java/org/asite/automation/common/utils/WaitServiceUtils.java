package org.asite.automation.common.utils;
import org.asite.automation.common.base.TestBaseWaitService;
import org.asite.automation.common.lib.AutomationSeleniumExtendedLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleWaitServiceUtils.
 * @author jasminprajapati
 */
public class WaitServiceUtils implements TestBaseWaitService {
	
	
	/**
	 * Instantiates a new adoddle wait service utils.
	 */
	public WaitServiceUtils() {}
	
	/** The wait. */
	public static WebDriverWait wait = null;
	
	public WaitServiceUtils(WebDriver driver, int t) {
		setWebDriverWait(new WebDriverWait(driver, t));
	}
	
	/**
	 * Sets the web driver wait.
	 *
	 * @param webDriverWait the new web driver wait
	 */
	public void setWebDriverWait(WebDriverWait webDriverWait) {
		wait = webDriverWait;
	}
	
	/**
	 * Gets the web driver wait.
	 *
	 * @return the web driver wait
	 */
	public WebDriverWait getWebDriverWait() {
		return wait;
	}

	/**
	 * Wait for complete page load.
	 *
	 * @return the web driver
	 */
	
	public WebDriver waitForCompletePageLoad() {
		return new AutomationSeleniumExtendedLibrary().waitForCompletePageLoad();
	}

	/**
	 * Wait for ready state.
	 *
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> waitForReadyState() {
		return new AutomationSeleniumExtendedLibrary().waitForReadyState();
	}

	/**
	 * Wait for ajax complete.
	 *
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> waitForAjaxComplete() {
		return new AutomationSeleniumExtendedLibrary().waitForAjaxComplete();
	}

	/**
	 * Wait interval.
	 *
	 * @param f
	 *            the f
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitInterval(float f) throws InterruptedException {
		Thread.sleep((long) (f * 1000));
	}

}
