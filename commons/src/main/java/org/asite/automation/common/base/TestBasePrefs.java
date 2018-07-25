package org.asite.automation.common.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

// TODO: Auto-generated Javadoc
/**
 * The Interface TestBasePrefs.
 * @author jasminprajapati
 */
public interface TestBasePrefs {
	
	/** The ff profile. */
	FirefoxProfile ffProfile = null;
	
	/** The prefs. */
	Map<String, Object> prefs = new HashMap<String, Object>();
	
	/**
	 * Sets the preference.
	 *
	 * @param key the key
	 * @param value the value
	 */
	void setPreference(String key, String value);
	
	/**
	 * Sets the preference.
	 *
	 * @param key the key
	 * @param value the value
	 */
	void setPreference(String key, boolean value);
	
	/**
	 * Sets the preference.
	 *
	 * @param key the key
	 * @param value the value
	 */
	void setPreference(String key, int value);
	
	/**
	 * Sets the accept untrusted certificates.
	 *
	 * @param value the new accept untrusted certificates
	 */
	void setAcceptUntrustedCertificates(boolean value);
	
	
	/**
	 * Gets the FF security enabled java pref.
	 *
	 * @return the FF security enabled java pref
	 */
	String getFFSecurityEnabledJavaPref();
	
	/**
	 * Gets the FF browser download dir pref.
	 *
	 * @return the FF browser download dir pref
	 */
	String getFFBrowserDownloadDIRPref();
	
	/**
	 * Gets the FF plugin state java pref.
	 *
	 * @return the FF plugin state java pref
	 */
	String getFFPluginStateJavaPref();

	
	/**
	 * Gets the chrome prefs.
	 *
	 * @return the chrome prefs
	 */
	Object getChromePrefs();
	
	
	/**
	 * Gets the chrome options.
	 *
	 * @return the chrome options
	 */
	ChromeOptions getChromeOptions();
}
