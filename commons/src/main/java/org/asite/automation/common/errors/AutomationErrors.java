package org.asite.automation.common.errors;

import org.openqa.selenium.WebDriverException;

// TODO: Auto-generated Javadoc
/**
 * The Class AutomationErrors.
 * @author jasminprajapati
 */
public class AutomationErrors extends WebDriverException   {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4848803637856022136L;

	/**
	 * Instantiates a new automation errors.
	 */
	public AutomationErrors() {}
	
	/**
	 * Instantiates a new automation errors.
	 *
	 * @param message the message
	 */
	public AutomationErrors(String message) { super(message);  }
	
	/**
	 * Instantiates a new automation errors.
	 *
	 * @param cause the cause
	 */
	public AutomationErrors(Throwable cause) { super(cause); }
	
	/**
	 * Instantiates a new automation errors.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public AutomationErrors(String message, Throwable cause) { super(message, cause); }
	
	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriverException#getMessage()
	 */
	public String getMessage() {
		return createMessage(super.getMessage());
	}
	
	/**
	 * Creates the message.
	 *
	 * @param originalMessageString the original message string
	 * @return the string
	 */
	private String createMessage(String originalMessageString) {
		/*String supportMessage = (getSupportUrl() == null) ? ""
				: new StringBuilder()
						.append("For documentation on this error, please visit: ")
						.append(getSupportUrl()).append("\n").toString();*/

		return new StringBuilder()
				.append((originalMessageString == null) ? ""
						: new StringBuilder().append(originalMessageString)
								.append("\n").toString()).toString();
				/*.append(supportMessage).append(getBuildInformation())
				.append("\n").append(getSystemInformation())
				.append(getAdditionalInformation()).toString();*/
	}
	
}
