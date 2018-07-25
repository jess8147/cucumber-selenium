package org.asite.automation.common.utils;

import java.lang.Thread.State;

// TODO: Auto-generated Javadoc
/**
 * The Class ThreadUtils.
 * @author jasminprajapati
 */
public class ThreadUtils {
	
	/**
	 * Gets the current thread id.
	 *
	 * @return the current thread id
	 */
	public long getCurrentThreadID() {
		return Thread.currentThread().getId();
	}
	
	/**
	 * Gets the current thread state.
	 *
	 * @return the current thread state
	 */
	public State getCurrentThreadState() {
		return Thread.currentThread().getState();
	}

	/**
	 * Gets the current thread name.
	 *
	 * @return the current thread name
	 */
	public String getCurrentThreadName() {
		return Thread.currentThread().getName();
	}
}
