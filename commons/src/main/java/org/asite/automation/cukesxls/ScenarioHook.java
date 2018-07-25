package org.asite.automation.cukesxls;

import gherkin.formatter.model.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;

// TODO: Auto-generated Javadoc
/**
 * The Class ScenarioHook.
 * @author jasminprajapati
 */
public class ScenarioHook {

	/** The Constant RESULT_PASS. */
	private static final Result RESULT_PASS = new Result(Result.PASSED, 0l, "");
	
	/** The Constant RESULT_FAIL. */
	private static final Result RESULT_FAIL = new Result(Result.FAILED, 0l, "");

	/**
	 * After.
	 *
	 * @param scenario the scenario
	 */
	@After
	public void after(Scenario scenario) {
		ReporterContext.callbackResult(scenario.isFailed() ? RESULT_FAIL
				: RESULT_PASS, CucumberElement.Type.SCENARIO);
	}

}
