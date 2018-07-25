package org.asite.automation.common.base;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * The Interface TestBaseClosure.
 * @author jasminprajapati
 */
public interface TestBaseClosure {

	/**
	 * Sets the report data row.
	 *
	 * @param s the s
	 * @return the list
	 */
	List<String> getAdoddleReportDataRow(Scenario s);
	
	
	/**
	 * Gets the classic report data row.
	 *
	 * @param s the Scenario
	 * @return the classic report data row
	 */
	List<String> getClassicReportDataRow(Scenario s);
	/**
	 * Gets the execution time.
	 *
	 * @return the execution time
	 */
	Long getExecutionTime();

	/**
	 * Gets the cucumber report map key.
	 *
	 * @param s the Scenario
	 * @return the cucumber report map key
	 */
	String getCucumberReportMapKey(Scenario s);

	/**
	 * Gets the scenario status.
	 *
	 * @return the scenario status
	 */
	String getScenarioStatus();
	
	/**
	 * Gets the scenario name.
	 *
	 * @param s the Scenario
	 * @return the scenario name
	 */
	String getScenarioName(Scenario s);

	/**
	 * Sets the map value.
	 *
	 * @param map the map
	 * @param key the key
	 * @param list the list
	 */
	void setMapValue(HashMap<String, List<String>> map, String key, List<String> list);
	
	/**
	 * Sets the scenario startup.
	 */
	void setScenarioStartup(Map<String, String> dataMap);
	
	/**
	 * Perform scenario closure.
	 *
	 * @param s the new scenario closure data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	void setScenarioClosureData(Scenario s, String nodeIP) throws IOException, InterruptedException;
	
	/**
	 * Execute cucumber report.
	 *
	 * @param cucumberReportMap the cucumber report map
	 * @param cucumberReportMapKey the cucumber report map key
	 * @param cucumberReportData the cucumber report data
	 */
	void executeCucumberExcelReport(HashMap<String, List<String>> cucumberReportMap, String cucumberReportMapKey, List<String> cucumberReportData, String dc);
}
