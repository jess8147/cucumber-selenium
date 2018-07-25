package org.asite.automation.cukesxls;

import java.text.NumberFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 * @author jasminprajapati
 */
public class Util {

	/** The Constant NUMBER_FORMAT. */
	public static final NumberFormat NUMBER_FORMAT = NumberFormat
			.getPercentInstance();

	/**
	 * Format num.
	 *
	 * @param number the number
	 * @return the string
	 */
	public static String formatNum(double number) {
		return NUMBER_FORMAT.format(number);
	}

}
