package org.asite.automation.common.base;

import java.util.Map;
import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * The Interface TestBaseDataCheck.
 */
public interface TestBaseDataCheck {
	
	/**
	 * Checks if is test data available.
	 *
	 * @param s the s
	 * @return true, if is test data available
	 */
	boolean isTestDataAvailable(Scenario s);
	
	
	void setDataCollectionMap(Map<String, String> map);
	
	/**
	 * Gets the data collection map.
	 *
	 * @return the data collection map
	 */
	Map<String, String> getDataCollectionMap();
	
}
