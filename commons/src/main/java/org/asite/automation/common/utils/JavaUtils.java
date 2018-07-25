package org.asite.automation.common.utils;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;
import org.asite.automation.common.errors.AutomationAssert;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleJavaUtils.
 * @author jasminprajapati
 */
public class JavaUtils {

	/** The log. */
	public static Logger log = Logger.getLogger(JavaUtils.class.getName());
	
	/** The Constant charList. */
	private static final String charList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	/** The rnd. */
	private static Random rnd = new Random();
	

	
	/**
	 * Gets the random string.
	 * 
	 * @param charCount
	 *            the char count
	 * @return the random string
	 */
	public String getRandomString(int charCount) {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < charCount; i++) {
			int randomNumer = Integer.parseInt(getRandomNumber(1));
			char ch = charList.charAt(randomNumer);
			randStr.append(ch);
		}
		return randStr.toString();
	}
	
	/**
	 * Gets the random number.
	 * 
	 * @param digCount
	 *            the dig count
	 * @return the random number
	 */
	public static String getRandomNumber(int digCount) {
		StringBuilder sb = new StringBuilder(digCount);
		for (int i = 0; i < digCount; i++)
			sb.append((char) ('0' + (rnd.nextInt(9) + 1)));
		return sb.toString();
	}
	
	
	/**
	 * Compare list.
	 *
	 * @param list1 the list1
	 * @param list2 the list2
	 */
	public void compareUnorderedList(List<String> list1, List<String> list2)
	{
		boolean flag = false;
		
		if (list1.size() == list2.size())
		{
			for(String value : list1)
			{
				if(list2.contains(value))
					flag = true;
				else 
					flag = false;
				
				if(!flag)
					AutomationAssert.verifyTrue(list2.toString()+" does not contain "+value, false);
			}
		}
		else
			AutomationAssert.verifyTrue(list1.toString() +" does not equal\n "+list2.toString(), false);
	}
	
	
	/**
	 * Compare map list.
	 * 
	 * @param Map1
	 *            the map1
	 * @param Map2
	 *            the map2
	 */
	public void compareMapList(Map<String, String> Map1,
			Map<String, String> Map2) {
		for (String key : Map1.keySet()) {
			if (Map1.get(key).toString().trim().equalsIgnoreCase(Map2.get(key).toString().trim())) {
				log.info("key :" + key + " value :" + Map2.get(key));
			} else {
				AutomationAssert.verifyTrue(Map1+" should equal "+Map2, false);
			}
		}
	}
	
	
	/**
	 * Reset index.
	 *
	 * @param index the index
	 * @param resetValue the reset value
	 * @return the int
	 */
	public int resetIndex(int index, int resetValue) {
		if(index != resetValue) { 
			index = resetValue;
			return index;
		}
		else
			return index;
	}
	
	
	/**
	 * Compare list on index based.
	 *
	 * @param list1 the list1
	 * @param list2 the list2
	 */
	public void compareListOnIndexBased(List<String> list1, List<String> list2)
	{
		for (int i = 0; i < list1.size(); i++) {
			for (int j = i; j < list2.size(); j++) {
				if(list1.get(i).equalsIgnoreCase(list2.get(j))) {
					AutomationAssert.verifyTrue("file-1 -> "+list1.get(i)+ " :: "+"file-2 -> "+list2.get(j), true);
					break;
				} else {
					AutomationAssert.verifyTrue(list1.get(i)+" fails matching "+list2.get(j), list1.get(i).equalsIgnoreCase(list2.get(j)));
				}
			}
		}
	}
	
	public boolean validateFileSizeRange(double fileSizeDifference, double allowedDifference) {
		return fileSizeDifference <= allowedDifference && fileSizeDifference >= -(allowedDifference);
	}

}
