package org.asite.automation.cukesxls;

import gherkin.formatter.model.Result;


// TODO: Auto-generated Javadoc
/**
 * The Class CucumberElement.
 * @author jasminprajapati
 */
public class CucumberElement {
	
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The type. */
	private Type type;
	
	/** The start time. */
	private long startTime;
	
	/** The result. */
	private Result result;
	
	/** The costtime. */
	private long costtime;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime            the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result            the result to set
	 */
	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * Gets the costtime.
	 *
	 * @return the costtime
	 */
	public long getCosttime() {
		return costtime;
	}

	/**
	 * Sets the costtime.
	 *
	 * @param costtime            the costtime to set
	 */
	public void setCosttime(long costtime) {
		this.costtime = costtime;
	}

	/**
	 * The Enum Type.
	 */
	static enum Type {
		
		/** The feature. */
		FEATURE, 
 /** The scenario. */
 SCENARIO, 
 /** The step. */
 STEP;
	}
}
