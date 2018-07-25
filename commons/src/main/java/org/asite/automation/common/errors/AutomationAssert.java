package org.asite.automation.common.errors;
import org.junit.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class AutomationAssert.
 * @author jasminprajapati
 */
public class AutomationAssert extends Assert {

	 /**
     * Asserts that a condition is true. If it isn't it throws message
     * @param condition condition to be checked
     */
    static public void verifyTrue(boolean condition) {
    	verifyTrue(null, condition);
    }
    
    /**
     * Verify true.
     *
     * @param message the message
     * @param condition the condition
     */
    static public void verifyTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    /**
     * Verify true.
     *
     * @param message the message
     * @param condition the condition
     */
    static public void verifyFalse(String message, boolean condition) {
        if (condition) {
            fail(message);
        }
    }

    /**
     * Fails a test with the given message.
     * @param message the identifying message 
     * @see AssertionError
     */
    static public void fail(String message) {
        if (message == null) {
            throw new AutomationErrors();
        }
        throw new AutomationErrors(message);
    }


}
