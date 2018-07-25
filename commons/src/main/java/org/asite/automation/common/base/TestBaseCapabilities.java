package org.asite.automation.common.base;

import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;

// TODO: Auto-generated Javadoc
/**
 * The Interface TestBaseCapabilities.
 * @author jasminprajapati
 */
public interface TestBaseCapabilities {

	/**
	 * Sets the capability.
	 *
	 * @param capList the cap List
	 * @param b the cap Value
	 * @param t the TestDriverFactory Object
	 * @return the desired capabilities
	 */
	DesiredCapabilities setCapability(List<String> capList, String b, TestDriverFactory t);

}
