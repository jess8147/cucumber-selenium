package org.asite.automation.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asite.automation.common.base.TestBaseCapabilities;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.resources.BaseCapabilitiesCollection;
import org.asite.automation.common.resources.BasePrefsCollection.ChromePreferences;
import org.asite.automation.common.utils.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


//TODO: Auto-generated Javadoc
/**
* The Class ImplChromeCapabilities.
* @author jasminprajapati
*/
public class ImplChromeCapabilities implements TestBaseCapabilities{

	/** The chrome caps. */
	static DesiredCapabilities chromeCaps = null;
	
	/** The str utils. */
	StringUtils  strUtils = null;
	
	/**
	 * Instantiates a new impl chrome capabilities.
	 */
	public ImplChromeCapabilities() {
		chromeCaps = new DesiredCapabilities();
		strUtils = new StringUtils();
	}
		
	/**
	 * Sets Desired Capability
	 * 
	 * @return the chrome Capabilities
	 */
	public DesiredCapabilities setCapability(List<String> capList, String browser, TestDriverFactory t) {
		chromeCaps = DesiredCapabilities.chrome();
		if(!browser.equalsIgnoreCase("headless"))
			chromeCaps.setCapability(capList.get(0), strUtils.getLowerCaseString(browser));
		else
			chromeCaps.setCapability(capList.get(0), "chrome");
		chromeCaps.setCapability(capList.get(1), Platform.WINDOWS);
		chromeCaps.setCapability(capList.get(2), t.getNodeApplicationName(t.getNodeFlag()));
		chromeCaps.setCapability(capList.get(3), BaseCapabilitiesCollection.CAP_IGNORE_CERT_ERR);
		/*LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeCaps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);*/
		chromeCaps.setCapability(ChromeOptions.CAPABILITY, getChromeOptions(browser, t));
		return chromeCaps;
	}
	
	/**
	 * Gets the chrome options.
	 *
	 * @return the chrome options
	 */
	public ChromeOptions getChromeOptions(String browser, TestDriverFactory tdf) {
		Map<String, Object> prefs = new HashMap<String, Object>();
		if(tdf.getNodeFlag())
			prefs.put(ChromePreferences.CHROME_PROMPT_FOR_DOWNLOAD, true);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption(ChromePreferences.CHROME_PREFERENCES, prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-bundled-ppapi-flash");
		options.addArguments("--disable-internal-flash");
		options.addArguments("enable-automation");
		prefs.put("plugins.plugins_disabled", new String[] {"Adobe Flash Player","Chrome PDF Viewer"});
		prefs.put("plugins.always_open_pdf_externally", true);
		options.addArguments(ChromePreferences.CHROME_DISABLE_EXTENSIONS);
        /*Map<String, Object> profile = new HashMap<String, Object>();
		Map<String, Object> contentSettings = new HashMap<String, Object>();
        contentSettings.put("notifications", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile); */
		prefs.put("profile.default_content_setting_values.notifications", 2);
       
		if(browser.equalsIgnoreCase("headless")) {
			options.addArguments("--headless");
			options.addArguments("window-size=1920,1080");
		}
		return options;
	}

}
