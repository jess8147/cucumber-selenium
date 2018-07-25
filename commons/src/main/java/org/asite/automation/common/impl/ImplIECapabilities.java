package org.asite.automation.common.impl;

import java.util.List;

import org.asite.automation.common.base.TestBaseCapabilities;
import org.asite.automation.common.base.TestDriverFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


// TODO: Auto-generated Javadoc
/**
 * The Class ImplIECapabilities.
 * @author jasminprajapati
 */
public class ImplIECapabilities implements TestBaseCapabilities {

	/** The IE caps. */
	static DesiredCapabilities IECaps = null;
	
	/**
	 * Instantiates a new impl IE capabilities.
	 */
	public ImplIECapabilities() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.asite.automation.common.base.TestBaseCapabilities#setCapability(java.util.List, java.lang.String)
	 */
	public DesiredCapabilities setCapability(List<String> capList, String b, TestDriverFactory t) {
		IECaps = DesiredCapabilities.internetExplorer();
		IECaps.setCapability(capList.get(0), true);
		IECaps.setCapability(capList.get(1), false);
		IECaps.setCapability(capList.get(2), true);
		IECaps.setCapability(capList.get(3), t.getNodeApplicationName(t.getNodeFlag()));
		IECaps.setBrowserName(b);
		IECaps.setPlatform(Platform.WINDOWS);
		IECaps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		return IECaps;
	}

}
