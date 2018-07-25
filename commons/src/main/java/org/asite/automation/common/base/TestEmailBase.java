package org.asite.automation.common.base;

// TODO: Auto-generated Javadoc
/**
 * The Interface TestEmailBase.
 * @author jasminprajapati
 */
public interface TestEmailBase {
	
	/**
	 * Send failure email.
	 *
	 * @param screenShotPath the screen shot path
	 * @param scenarioTitle the scenario title
	 */
	public void sendFailureEmail(String screenShotPath, String scenarioTitle);
	
	/**
	 * Send initiation email.
	 */
	public void sendInitiationEmail();
}
