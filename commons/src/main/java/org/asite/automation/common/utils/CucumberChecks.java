package org.asite.automation.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;


// TODO: Auto-generated Javadoc
/**
 * The Class CucumberChecks.
 * @author jasminprajapati
 */
public class CucumberChecks {

	/**
	 * Checks if is data center available.
	 * 
	 * @param dc_center
	 *            the dc_center
	 * @return true, if is data center available
	 */
	public boolean isDataCenterAvailable(String dc_center) {
		boolean flag = false;
		List<String> dataCenters = new ArrayList<String>();
		dataCenters = Arrays.asList((ResourceHandler.getPropertyValue("application.test.dcs")).split(","));

		if (dc_center.equalsIgnoreCase(""))
			return flag;

		if (dataCenters.contains(dc_center) || dataCenters.contains(dc_center.substring(0, 2))) {
			AdoddleCommonAppMethods.dataCenter = dc_center;
			flag = true;
		} else
			flag = false;
		return flag;
	}
}
