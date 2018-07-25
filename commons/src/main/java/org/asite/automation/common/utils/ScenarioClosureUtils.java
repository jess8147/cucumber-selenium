package org.asite.automation.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.asite.automation.common.base.TestBaseClosure;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import cucumber.api.Scenario;

/**
 * The Class ScenarioClosureUtils.
 * @author jasminprajapati
 */
public class ScenarioClosureUtils implements TestBaseClosure {

	/** The status flag. */
	private boolean				statusFlag		= true;

	/** The start time. */
	private Long				startTime		= 12345678910L;

	/** The screen shot util. */
	private ScreenshotUtils		screenShotUtil	= null;

	/** The date utils. */
	private DateUtils			dateUtils		= null;

	/** The string utils. */
	private StringUtils			stringUtils		= null;

	/** The excel utils. */
	private ExcelUtils			excelUtils		= null;

	/** The Email utils. */
	private EmailUtils			emailUtils		= null;
	
	/** The Email utils. */
	private TestdataCheckUtils	dataCheckUtils	= null;

	/**
	 * Instantiates a new scenario closure utils.
	 */
	public ScenarioClosureUtils() {
		screenShotUtil = new ScreenshotUtils();
		dateUtils = new DateUtils();
		excelUtils = new ExcelUtils();
		stringUtils = new StringUtils();
		emailUtils = new EmailUtils();
		dataCheckUtils = new TestdataCheckUtils();
	}

	/**
	 * Set Scenario Startup Time.
	 */
	public void setScenarioStartup(Map<String, String> collectionDataMap) {
		startTime = dateUtils.getCurrentTimeInMilliSeconds();
		dataCheckUtils.setDataCollectionMap(collectionDataMap);
	}

	/**
	 * Sets the report data row.
	 * 
	 * @param scenario
	 *            the scenario
	 * @return the list
	 */
	public List<String> getReportDataRow(Scenario scenario) {
		return Arrays.asList(getScenarioName(scenario), getAdoddleDataCenter(), getScenarioStatus(), String.valueOf(getExecutionTime()));
	}

	/**
	 * Gets the data center.
	 * 
	 * @return the data center
	 */
	public String getAdoddleDataCenter() {
		return AdoddleCommonAppMethods.dataCenter;
	}

	/**
	 * Gets the classic data center.
	 * 
	 * @return the classic data center
	 */
	public String getClassicDataCenter() {
		return ClassicCommonAppMethods.dataCenter;
	}

	/**
	 * Gets the execution time.
	 * 
	 * @return the execution time
	 */
	public Long getExecutionTime() {
		return TimeUnit.MILLISECONDS.toSeconds(dateUtils.getCurrentTimeInMilliSeconds() - startTime);
	}

	/**
	 * Gets the cucumber report map key.
	 * 
	 * @param scenario
	 *            the scenario
	 * @return the cucumber report map key
	 */
	public String getCucumberReportMapKey(Scenario scenario) {
		return stringUtils.toTitleCase(scenario.getId().split(";")[0].replace("-", " "));
	}

	/**
	 * Gets the scenario status.
	 * 
	 * @return the scenario status
	 */
	public String getScenarioStatus() {
		return statusFlag ? AdoddleCommonStringPool.RESULT_PASSED : AdoddleCommonStringPool.RESULT_FAILED;
	}

	/**
	 * Gets the scenario name.
	 * 
	 * @param scenario
	 *            the scenario
	 * @return the scenario name
	 */
	public String getScenarioName(Scenario scenario) {
		return scenario.getName();
	}

	/**
	 * Sets the map value.
	 * 
	 * @param map
	 *            the map
	 * @param key
	 *            the key
	 * @param list
	 *            the list
	 */
	public synchronized void setMapValue(HashMap<String, List<String>> map, String key, List<String> list) {
		map.put(key, list);
	}

	/**
	 * Sets Scenario Closire Data.
	 * 
	 * @param s
	 *            the new scenario closure data
	 * @param ip
	 *            the ip
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void setScenarioClosureData(Scenario s, String ip) throws IOException, InterruptedException {
		int executionTime = getExecutionTime().intValue();
		if (s.isFailed()) {
			screenShotUtil.captureScreenshot(s, ip);
			screenShotUtil.captureFailureTime(s);
			screenShotUtil.captureFailureURL(s);
			screenShotUtil.captureASessionID(s);
			if(Boolean.valueOf(ResourceUtils.getEmailTriggerFlag()) && !(s.getName().equalsIgnoreCase("A Message Load Testing"))) {
				try {
					emailUtils.sendFailureEmail(screenShotUtil.captureScreenshot(s, ip), s.getName());
				}
			catch(Throwable t) {
					System.out.println("ERROR: Failure sending notification email, Please check SMTP settings.");
				}
			}
			
			if(s.getName().equalsIgnoreCase("A Message Load Testing"))
				emailUtils.sendFailureEmail(screenShotUtil.captureScreenshot(s, ip), s.getName());
			statusFlag = false;
		}

		if (executionTime == 0)
			statusFlag = false;

		screenShotUtil.captureFailureTestMachine(s, ip);
		screenShotUtil.captureScenarioTimeframe(s);
		screenShotUtil.captureTestDataCollection(s);
		/* s.write("Execution Time: " + executionTime + "s"); */
		if(new ResourceUtils().getApplicationModule().contains(AdoddleCommonStringPool.ADODDLE_APP)) {
			new AdoddleCommonAppMethods().tearLogOut();	
			new AdoddleCommonAppMethods().tearDown();
		}
		else {
			new ClassicCommonAppMethods().signOut();	
			new ClassicCommonAppMethods().tearDown();
		}
		/*analyzeLog();*/
	}

	public void analyzeLog() {
        LogEntries logEntries = new AdoddleCommonAppMethods().getWebDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
    }
	
	/**
	 * Executes Cucumber Excel Report.
	 * 
	 * @param map
	 *            the map
	 * @param key
	 *            the key
	 * @param data
	 *            the data
	 * @param dc
	 *            the dc
	 */
	public void executeCucumberExcelReport(HashMap<String, List<String>> map, String key, List<String> data, String dc) {
		if (dc != null) {
			if (!dc.equalsIgnoreCase("")) {
				if (map.containsKey(key)) {
					List<String> keyList = new ArrayList<String>(map.keySet());
					Collections.sort(keyList);
					for (String k : keyList)
						key = k.contains(key) ? key + " " : key;
				}
				setMapValue(map, key, data);
				excelUtils.generateCucumberReport(map, key);
			}
		}
	}

	/**
	 * gets Classic Report Data Rows.
	 * 
	 * @param s
	 *            the s
	 * @return the classic report data row
	 */
	public List<String> getClassicReportDataRow(Scenario s) {
		return Arrays.asList(getScenarioTitle(s), getClassicDataCenter(), getScenarioStatus(), String.valueOf(getExecutionTime()), getDefectNumber(s));
	}

	/**
	 * gets Adoddle Report Data Rows.
	 * 
	 * @param s
	 *            the s
	 * @return the adoddle report data row
	 */
	public List<String> getAdoddleReportDataRow(Scenario s) {
		return Arrays.asList(getScenarioTitle(s), getAdoddleDataCenter(), getScenarioStatus(), String.valueOf(getExecutionTime()), getDefectNumber(s));
	}

	/**
	 * Gets the scenario title.
	 * 
	 * @param s
	 *            the s
	 * @return the scenario title
	 */
	public String getScenarioTitle(Scenario s) {
		return getScenarioName(s).contains("[") ? getScenarioName(s).split("\\[")[0] : getScenarioName(s);
	}

	/**
	 * Gets the defect number.
	 * 
	 * @param s
	 *            the s
	 * @return the defect number
	 */
	public String getDefectNumber(Scenario s) {
		return getScenarioName(s).contains("[") ? getScenarioName(s).split("\\[")[1].replace("]", "") : "";
	}
}
