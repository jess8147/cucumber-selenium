package org.asite.automation.common.impl;
import org.asite.automation.common.base.TestBasePrefs;
import org.asite.automation.common.resources.BasePrefsCollection.ChromePreferences;
import org.openqa.selenium.chrome.ChromeOptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ImplChromePrefs.
 * @author jasminprajapati
 */
public class ImplChromePrefs implements TestBasePrefs {

	
	public void setPreference(String key, String value) {
		// TODO Auto-generated method stub
	}

	public void setPreference(String key, boolean value) {
		System.out.println("set chrome prefs");
		prefs.put(key, value);
	}

	public void setPreference(String key, int value) {
		// TODO Auto-generated method stub
	}

	public void setAcceptUntrustedCertificates(boolean value) {
		// TODO Auto-generated method stub
	}

	public String getFFSecurityEnabledJavaPref() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFFBrowserDownloadDIRPref() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFFPluginStateJavaPref() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getChromePrefs() {
		return prefs;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption(ChromePreferences.CHROME_PREFERENCES, getChromePrefs());
		options.addArguments(ChromePreferences.CHROME_DISABLE_EXTENSIONS);
		return options;
	}
	
}
