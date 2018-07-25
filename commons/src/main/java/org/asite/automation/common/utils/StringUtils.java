package org.asite.automation.common.utils;
import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleErrorStringUtils.
 * @author jasminprajapati
 */
public class StringUtils {
	
	/** The list utils. */
	ListUtils listUtils = new ListUtils();
	
	/**
	 * Concatenate strings.
	 *
	 * @param stringLst the string lst
	 * @return the string
	 */
	public String concatenateStrings(List<String> stringLst) {
		return null;
	}

	/**
	 * Split string.
	 *
	 * @param string the string
	 * @param splitter the splitter
	 * @return the list
	 */
	public List<String> splitString(String string, String splitter) {
		return listUtils.getArrayList(string.split(splitter));
	}

	/**
	 * To title case.
	 *
	 * @param input the input
	 * @return the string
	 */
	public String toTitleCase(String input) {
	    StringBuilder titleCase = new StringBuilder();
	    boolean nextTitleCase = true;

	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toTitleCase(c);
	            nextTitleCase = false;
	        }

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}
	
	
	/**
	 * To title case.
	 *
	 * @param str the str
	 * @return the string
	 */
	public String getLowerCaseString(String str) {
		if(str != null)
			return str.toLowerCase();
		else
			return str;
	}
	
	
	/**
	 * Extract file name string.
	 *
	 * @param path the path
	 * @return the string
	 */
	public String extractFileNameString(String path) {
		int lastIndex = Arrays.asList(path.split("\\\\")).size() - 1;
		return Arrays.asList(path.split("\\\\")).get(lastIndex);
	}
	
	
	/**
	 * Gets the index of occurance.
	 *
	 * @param str the str
	 * @param ch the ch
	 * @param index the index
	 * @return the index of occurance
	 */
	public int getIndexOfOccurance(String str, String ch, int index) {
		return str.indexOf(ch, str.indexOf(ch) + index);
	}
	
	/**
	 * Gets the string.
	 *
	 * @param o the o
	 * @return the string
	 */
	public String getString(Object o) {
		return String.valueOf(o);
	}
	
	
	/**
	 * Bracket string.
	 *
	 * @param s the s
	 * @return the string
	 */
	public static String getEnclosedString(String s) {
		return "("+s+")";
	}
}
