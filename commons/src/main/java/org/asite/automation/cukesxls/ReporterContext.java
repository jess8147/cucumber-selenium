package org.asite.automation.cukesxls;

import gherkin.formatter.model.BasicStatement;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.TagStatement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporterContext.
 * @author jasminprajapati
 */
public class ReporterContext {

	/** The total features. */
	private static int totalFeatures = 0;
	
	/** The fail features. */
	private static int failFeatures = 0;
	
	/** The total scenarios. */
	private static int totalScenarios = 0;
	
	/** The fail scenarios. */
	private static int failScenarios = 0;
	
	/** The total steps. */
	private static int totalSteps = 0;
	
	/** The fail steps. */
	private static int failSteps = 0;

	/** The cucumber eles map. */
	private static Map<String, CucumberElement> cucumberElesMap = new LinkedHashMap<String, CucumberElement>();
	
	/** The scenario hook result list. */
	private static List<Result> scenarioHookResultList = new ArrayList<Result>();
	
	/** The temp id list. */
	private static List<String> tempIdList = new ArrayList<String>();
	
	/** The current scenario. */
	public static CucumberElement currentScenario = null;
	
	/** The current feature. */
	public static CucumberElement currentFeature = null;

	/**
	 * Adds the ele.
	 *
	 * @param ele the ele
	 * @param type the type
	 */
	public static void addEle(BasicStatement ele, CucumberElement.Type type) {
		CucumberElement cucumberEle = new CucumberElement();
		cucumberEle.setResult(Result.UNDEFINED);
		cucumberEle.setName(ele.getName());
		cucumberEle.setStartTime(System.currentTimeMillis());
		cucumberEle.setType(type);

		String id = "";

		if (type == CucumberElement.Type.STEP) {
			totalSteps++;
			id = cucumberEle.getId() + "_" + ele.getLine();
		} else {
			id = ((TagStatement) ele).getId();
			if (type == CucumberElement.Type.SCENARIO) {
				currentScenario = cucumberEle;
				totalScenarios++;
			} else {
				currentFeature = cucumberEle;
				totalFeatures++;
			}
		}
		cucumberEle.setId(id);
		cucumberElesMap.put(id, cucumberEle);

		// step and scenario will callback result,so add it to tempIdList to
		// process callback
		if (type == CucumberElement.Type.STEP
				|| type == CucumberElement.Type.SCENARIO) {
			tempIdList.add(id);
		}
	}

	/**
	 * Callback result.
	 *
	 * @param result the result
	 * @param type the type
	 */
	public static void callbackResult(Result result, CucumberElement.Type type) {
		if (type == CucumberElement.Type.STEP) {
			scenarioHookResultList.add(result);
			CucumberElement callbackEle = cucumberElesMap.get(tempIdList
					.get(scenarioHookResultList.size()));
			callbackEle
					.setCosttime(Math.round(1.0 * result.getDuration() / 1000000));

			if (Result.FAILED.equals(result.getStatus())) {
				failSteps++;
			}
		} else {// scenario result
			scenarioHookResultList.add(0, result);
			currentScenario.setCosttime(System.currentTimeMillis()
					- currentScenario.getStartTime());

			// if one scenario failed,the feature failed too.
			if (Result.FAILED.equals(result.getStatus())) {
				if (!Result.FAILED.equals(currentFeature.getResult()
						.getStatus())) {
					currentFeature.setResult(new Result(Result.FAILED, null,
							null));
					failFeatures++;
				}
				failScenarios++;
			}

			// add scenario execute info to map
			for (int i = 0; i < tempIdList.size(); i++) {
				CucumberElement ele = cucumberElesMap.get(tempIdList.get(i));
				ele.setResult(scenarioHookResultList.get(i));
			}

			// clear temp list
			tempIdList.clear();
			scenarioHookResultList.clear();
		}
	}

	/**
	 * Clear.
	 */
	public static void clear() {
		cucumberElesMap.clear();
	}

	/**
	 * Gets the feature eles.
	 *
	 * @return the feature eles
	 */
	public static Map<String, CucumberElement> getFeatureEles() {
		return cucumberElesMap;
	}

	/**
	 * Gets the total features.
	 *
	 * @return the totalFeatures
	 */
	public static int getTotalFeatures() {
		return totalFeatures;
	}

	/**
	 * Gets the fail features.
	 *
	 * @return the failFeatures
	 */
	public static int getFailFeatures() {
		return failFeatures;
	}

	/**
	 * Gets the total scenarios.
	 *
	 * @return the totalScenarios
	 */
	public static int getTotalScenarios() {
		return totalScenarios;
	}

	/**
	 * Gets the fail scenarios.
	 *
	 * @return the failScenarios
	 */
	public static int getFailScenarios() {
		return failScenarios;
	}

	/**
	 * Gets the total steps.
	 *
	 * @return the totalSteps
	 */
	public static int getTotalSteps() {
		return totalSteps;
	}

	/**
	 * Gets the fail steps.
	 *
	 * @return the failSteps
	 */
	public static int getFailSteps() {
		return failSteps;
	}

}
