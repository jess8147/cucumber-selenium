package org.asite.automation.adoddle.p2.stepdefinitions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.asite.automation.common.base.TestBaseClosure;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.utils.ScenarioClosureUtils;
import org.asite.automation.common.utils.ScreenshotUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

// TODO: Auto-generated Javadoc
/**
 * The Class Tests_SetupTearDown.
 */
public class Tests_SetupTearDown extends AdoddleCommonAppMethods {

	/** The screen shot util. */
	ScreenshotUtils screenShotUtil = null;
	
	/** The testbase closure. */
	TestBaseClosure testbaseClosure = null;

	/**
	 * Instantiates a new tests_ setup tear down.
	 */
	public Tests_SetupTearDown() {
		screenShotUtil = new ScreenshotUtils();
		testbaseClosure = new ScenarioClosureUtils();
	}
	
	/**
	 * Inits the.
	 *
	 * @param scenario the scenario
	 * @throws Throwable the throwable
	 */
	@Before
	public void init(Scenario scenario) throws Throwable {
		TestDriverFactory.scenario = scenario;
		collectionDataMap = new HashMap<String, String>();
		testbaseClosure.setScenarioStartup(collectionDataMap);
	}

	/**
	 * Close browser.
	 *
	 * @throws Throwable the throwable
	 */
	@After
	public void closeBrowser() throws Throwable {
		new Tests_SetupTearDown();
		dataCheckUtils.setDataCollectionMap(collectionDataMap);
		testbaseClosure.setScenarioClosureData(TestDriverFactory.scenario, nodeIP);
		cucumberReportData = getCucumberReportData();
		cucumberReportMapKey = getCucumberReportMapKey(TestDriverFactory.scenario);
		Collections.synchronizedMap(cucumberReportMap);
		testbaseClosure.executeCucumberExcelReport(cucumberReportMap, cucumberReportMapKey, cucumberReportData, dataCenter);
		System.gc();
	}
	
	/**
	 * Gets the cucumber report data.
	 *
	 * @return the cucumber report data
	 */
	public List<String> getCucumberReportData() {
		return testbaseClosure.getAdoddleReportDataRow(TestDriverFactory.scenario);
	}
	
	/**
	 * Gets the cucumber report map key.
	 *
	 * @return the cucumber report map key
	 */
	public String getCucumberReportMapKey(Scenario scenario) {
		return testbaseClosure.getCucumberReportMapKey(scenario);
	}
}
