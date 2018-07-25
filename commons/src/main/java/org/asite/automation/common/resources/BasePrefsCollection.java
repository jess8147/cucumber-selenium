package org.asite.automation.common.resources;

// TODO: Auto-generated Javadoc
/**
 * The Class BasePrefsCollection.
 * @author jasminprajapati
 */
public class BasePrefsCollection {

	/** The ff security enable java. */
	public static String	FF_SECURITY_ENABLE_JAVA					= "security.enable_java";

	/** The ff browser download use download dir. */
	public static String	FF_BROWSER_DOWNLOAD_USE_DOWNLOAD_DIR	= "browser.download.useDownloadDir";

	/** The ff plugin state java. */
	public static String	FF_PLUGIN_STATE_JAVA					= "plugin.state.java";
	
	
	public static class ChromePreferences { 

		/** The chrome prompt for download. */
		public static String	CHROME_PROMPT_FOR_DOWNLOAD				= "download.prompt_for_download";

		/** The chrome disable extensions. */
		public static String	CHROME_DISABLE_EXTENSIONS				= "--disable-extensions";
	
		/** The chrome preferences. */
		public static String	CHROME_PREFERENCES						= "prefs";
	}

}
