package org.asite.automation.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.mockito.internal.util.StringJoiner;

// TODO: Auto-generated Javadoc
/**
 * The Class DateUtils.
 * @author jasminprajapati
 */
public class DateUtils {

	/** The log. */
	public static Logger log = Logger.getLogger(DateUtils.class.getName());

	/**
	 * Gets the current date.
	 * 
	 * @return the current date
	 */
	public String getEpoch() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Gets the current date time with zone.
	 * 
	 * @param format
	 *            the format
	 * @param timeZone
	 *            the time zone
	 * @return the current date time with zone
	 */
	public String getCurrentDateTimeWithZone(String format, String timeZone) {
		Date today = new Date();
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		df.format(today);
		return df.format(today);
	}

	/**
	 * Adds the no. of days to date passed in params.
	 *
	 * @param dateFormat the date format
	 * @param timeZone the time zone
	 * @param days            no. of days to add
	 * @return the date time with added days
	 */

	public static String addDaysToDate(String dateFormat, String timeZone, int days) {

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		format.setTimeZone(TimeZone.getTimeZone(timeZone));

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		cal.add(Calendar.DATE, days); // minus number would decrement the days

		log.info("Custom date: " + format.format(cal.getTime()));

		return format.format(cal.getTime());
	}

	/**
	 * Adds the no. of days to date passed in params as per Workspace Calender.
	 *
	 * @param holidayList            The list of Holidays
	 * @param additionalDay the additional day
	 * @param dateFormat            Format of Date
	 * @param timeZoneID the time zone ID
	 * @param numDays the num days
	 * @return the date time with added days
	 */

	public static String addDaysInWorkspaceWorkingCalendar(List<String> holidayList, String additionalDay,
			String dateFormat, String timeZoneID, int numDays) {

		List<String> workingDayList = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
		String workingDays = null;

		if (holidayList.contains("sunday"))
			Collections.replaceAll(holidayList, "sunday", "1");
		if (holidayList.contains("monday"))
			Collections.replaceAll(holidayList, "monday", "2");
		if (holidayList.contains("tuesday"))
			Collections.replaceAll(holidayList, "tuesday", "3");
		if (holidayList.contains("wednesday"))
			Collections.replaceAll(holidayList, "wednesday", "4");
		if (holidayList.contains("thursday"))
			Collections.replaceAll(holidayList, "thursday", "5");
		if (holidayList.contains("friday"))
			Collections.replaceAll(holidayList, "friday", "6");
		if (holidayList.contains("saturday"))
			Collections.replaceAll(holidayList, "saturday", "7");

		workingDayList.removeAll(holidayList);
		workingDays = StringJoiner.join(workingDayList).toString().replace("[", "").replace("]", "");
		log.info("workingDays : " + workingDays);

		int offDays = 0, totalNumDays = 0;

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		format.setTimeZone(TimeZone.getTimeZone(timeZoneID));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZoneID));
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		List<String> additionalDayList = new ArrayList<String>(Arrays.asList(additionalDay));

		for (int i = 1; i <= numDays; i++) {
			cal.add(Calendar.DATE, 1);

			if (!workingDays.contains(String.valueOf(cal.get(Calendar.DAY_OF_WEEK)))) {
				offDays = offDays + 1;
				i = i - 1;
			} else if (additionalDayList.contains(format.format(cal.getTime()))) {
				offDays = offDays + 1;
				i = i - 1;
			} else
				log.info("Not Found working Day or Holidays");
		}

		log.info("Working Days::" + numDays);
		log.info("Off Days:: " + offDays);

		cal.setTime(new Date(System.currentTimeMillis()));
		totalNumDays = numDays + offDays;
		cal.add(Calendar.DATE, (totalNumDays));
		log.info("Custom date: " + format.format(cal.getTime()));

		return format.format(cal.getTime()).split(" ")[0];
	}

	/**
	 * Removes n days from current date time with zone.
	 *
	 * @param format
	 *            the format
	 * @param timeZone
	 *            the time zone
	 * @param days
	 *            the days
	 * @return the current date time with zone
	 */
	public String substractDaysFromCurrentDate(String format, String timeZone, int days) {
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));

		/*
		 * Calendar cal = Calendar.getInstance(); cal.add(Calendar.DATE,
		 * -(days)); Date dateBefore1 = cal.getTime();
		 * log.info("Date before: "+df.format(dateBefore1));
		 */

		Date today = new Date();
		Date dateBefore = new Date(today.getTime() - (days * 24 * 3600 * 1000));
		log.info("Date before gettime(): " + df.format(dateBefore));

		return df.format(dateBefore);
	}

	/**
	 * Gets First Date of Current Month.
	 *
	 * @return the first date of current month
	 */
	public String getFirstDateOfCurrentMonth() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.DATE, 1);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * Gets Last Date of Current Month.
	 *
	 * @return the last date of current month
	 */
	public String getLastDateOfCurrentMonth() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(cal.getTime());
	}

	/**
	 * Gets the current time in milli seconds.
	 *
	 * @return the current time in milli seconds
	 */
	public Long getCurrentTimeInMilliSeconds() {
		return System.currentTimeMillis();
	}
	
	
	/**
	 * Gets the current date time with User timezoneId.
	 * 
	 * @param format
	 *            the format
	 * @return loggedIn User's current date
	 */
	
	public String getUserCurrentDate(String format)
	{
		DateFormat df = new SimpleDateFormat(format);
		if(null != AdoddleCommonAppMethods.timezoneID)
			df.setTimeZone(TimeZone.getTimeZone(AdoddleCommonAppMethods.timezoneID));
		String userDate = df.format(new Date());
		log.info("UserDate : "+userDate);
		return userDate;
	}
}
