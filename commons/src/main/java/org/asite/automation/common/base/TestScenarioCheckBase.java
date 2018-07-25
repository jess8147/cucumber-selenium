package org.asite.automation.common.base;

import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * The Interface TestScenarioCheckBase.
 * @author jasminprajapati
 */
public interface TestScenarioCheckBase {

	/**
	 * Checks if is scenario dependent.
	 *
	 * @param scenario the scenario
	 * @return true, if is scenario dependent
	 */
	public boolean isScenarioDependent(Scenario scenario);
}
