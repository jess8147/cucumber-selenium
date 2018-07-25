package org.asite.automation.common.impl;

import org.asite.automation.common.base.TestBasePrefs;
import org.asite.automation.common.resources.BasePrefsCollection;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class ImplFFPrefs.
 * @author jasminprajapati
 */
public class ImplFFPrefs implements TestBasePrefs {
	
	/** The ff profile. */
	public FirefoxProfile ffProfile = null;
	
	/**
	 * Instantiates a new impl ff prefs.
	 */
	public ImplFFPrefs() {}
	
	/**
	 * Instantiates a new impl ff prefs.
	 *
	 * @param ffProfile the ff profile
	 */
	public ImplFFPrefs(FirefoxProfile ffProfile) {
		this.ffProfile = ffProfile;
	}
	
	public void setPreference(String key, String value) {
		this.ffProfile.setPreference(key, value);
	}

	public void setPreference(String key, boolean value) {
		this.ffProfile.setPreference(key, value);
	}

	public void setPreference(String key, int value) {
		this.ffProfile.setPreference(key, value);
	}
	
	public void setAcceptUntrustedCertificates(boolean value) {
		this.ffProfile.setAcceptUntrustedCertificates(value);
	}
	
	public String getFFSecurityEnabledJavaPref() {
		return BasePrefsCollection.FF_SECURITY_ENABLE_JAVA;
	}
	
	public String getFFBrowserDownloadDIRPref() {
		return BasePrefsCollection.FF_BROWSER_DOWNLOAD_USE_DOWNLOAD_DIR;
	}
	
	public String getFFPluginStateJavaPref() {
		return BasePrefsCollection.FF_PLUGIN_STATE_JAVA;
	}

	public Object getChromePrefs() {
		return null;
	}

	public ChromeOptions getChromeOptions() {
		return null;
	}
}