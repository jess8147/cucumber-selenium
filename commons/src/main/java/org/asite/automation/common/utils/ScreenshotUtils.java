package org.asite.automation.common.utils;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleScreenshotUtils.
 * @author jasminprajapati
 */
public class ScreenshotUtils extends AdoddleCommonAppMethods {

	/** The date utils. */
	DateUtils				dateUtils	= new DateUtils();

	/** The logger. */
	public static Logger	log			= Logger.getLogger(ScreenshotUtils.class.getName());

	/**
	 * Capture screenshot.
	 * 
	 * @param scenario
	 *            the scenario
	 * @param ip
	 *            the ip
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public String captureScreenshot(Scenario scenario, String ip) throws IOException, InterruptedException {
		String screenshotPath = null;
		
		try {
			if(isAlertPresent()) {
				System.out.println("INFO: Alert is present on screenshot capture");
				scenario.write("Unexpected alert text: "+getAlertText());
				acceptAlert();
			}
			final byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			File screenshotFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
			screenshotPath = ip + ResourceHandler.loadProperty("test.failure.download.screenshot.path") + epoch + ".jpg";
			sysUtils.authenticateRemoteMachine(ip);
			FileUtils.copyFile(screenshotFile, new File(screenshotPath));
		}
		catch (Throwable t) {
			log.error("ERROR: screenshot was not captured");
		}
		return screenshotPath;
	}

	/**
	 * Capture failure url.
	 * 
	 * @param scenario
	 *            the scenario
	 */
	public void captureFailureURL(Scenario scenario) {
		try {
			scenario.write("URL at failure: " + getWebDriver().getCurrentUrl());
		}
		catch(Throwable t) {
			log.info("ERROR: URL capture failed on script failure");
		}
	}

	/**
	 * Capture failure time.
	 * 
	 * @param scenario
	 *            the scenario
	 */
	public void captureFailureTime(Scenario scenario) {
		scenario.write("\nTime of failure (WET): " + dateUtils.getCurrentDateTimeWithZone(getFailureTimeFormatString(), getFailureTimeZoneString())+"\t");
	}
	
	
	/**
	 * Capture test data collection.
	 *
	 * @param scenario the scenario
	 */
	public void captureTestDataCollection(Scenario scenario) {
		scenario.write("\nTestData Info: " + dataCheckUtils.getDataCollectionMap());
	}
	
	/**
	 * Capture Start time.
	 * 
	 * @param scenario
	 *            the scenario
	 */
	public void captureScenarioTimeframe(Scenario scenario) {
		try {
			scenario.write("\nStart Time (WET): " + TestDriverFactory.scenarioStartTime+"\nClose Time (WET): " + dateUtils.getCurrentDateTimeWithZone(ResourceUtils.getScenarioTimeFormat(), ResourceUtils.getScenarioTimeZone())+"\t");
		}
		catch(Throwable t) {
			log.info("ERROR: Scenario timeframe capture failed");
		}
	}
	
	/**
	 * Capture failure test machine.
	 * 
	 * @param scenario
	 *            the scenario
	 * @param ip
	 *            the ip
	 */
	public void captureFailureTestMachine(Scenario scenario, String ip) {
		try {
		if(ip.length() > 1)
			scenario.write("\nTest Machine: " + ip.replace("\\", "")+"\t");
		}
		catch(Throwable t) {
			log.info("ERROR: Testmachine capture failed on script failure");
		}
	}

	public void captureASessionID(Scenario scenario) {
		scenario.write("\nASession ID: " + getASessionID());
	}
	
	/**
	 * Gets the failure time format string.
	 * 
	 * @return the failure time format string
	 */
	public String getFailureTimeFormatString() {
		return resourceUtils.getFailureTimeFormat();
	}

	/**
	 * Gets the failure time zone string.
	 * 
	 * @return the failure time zone string
	 */
	public String getFailureTimeZoneString() {
		return resourceUtils.getFailureTimeZone();
	}
	
	
}
