package org.asite.automation.common.resources;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseCapabilitiesCollection.
 * @author jasminprajapati
 */
public class BaseCapabilitiesCollection {

	/** The Constant SUPPORTS_JAVASCRIPT. */
	public static final String	SUPPORTS_JAVASCRIPT				= "javascriptEnabled";

	/** The Constant TAKES_SCREENSHOT. */
	public static final String	TAKES_SCREENSHOT				= "takesScreenshot";

	/** The Constant VERSION. */
	public static final String	VERSION							= "version";

	/** The Constant SUPPORTS_ALERTS. */
	public static final String	SUPPORTS_ALERTS					= "handlesAlerts";

	/** The Constant SUPPORTS_SQL_DATABASE. */
	public static final String	SUPPORTS_SQL_DATABASE			= "databaseEnabled";

	/** The Constant SUPPORTS_LOCATION_CONTEXT. */
	public static final String	SUPPORTS_LOCATION_CONTEXT		= "locationContextEnabled";

	/** The Constant SUPPORTS_APPLICATION_CACHE. */
	public static final String	SUPPORTS_APPLICATION_CACHE		= "applicationCacheEnabled";

	/** The Constant SUPPORTS_NETWORK_CONNECTION. */
	public static final String	SUPPORTS_NETWORK_CONNECTION		= "networkConnectionEnabled";

	/** The Constant SUPPORTS_FINDING_BY_CSS. */
	public static final String	SUPPORTS_FINDING_BY_CSS			= "cssSelectorsEnabled";

	/** The Constant PROXY. */
	public static final String	PROXY							= "proxy";

	/** The Constant SUPPORTS_WEB_STORAGE. */
	public static final String	SUPPORTS_WEB_STORAGE			= "webStorageEnabled";

	/** The Constant ROTATABLE. */
	public static final String	ROTATABLE						= "rotatable";
	
	/** The Constant HAS_NATIVE_EVENTS. */
	public static final String	HAS_NATIVE_EVENTS				= "nativeEvents";

	/** The Constant UNEXPECTED_ALERT_BEHAVIOUR. */
	public static final String	UNEXPECTED_ALERT_BEHAVIOUR		= "unexpectedAlertBehaviour";

	/** The Constant ELEMENT_SCROLL_BEHAVIOR. */
	public static final String	ELEMENT_SCROLL_BEHAVIOR			= "elementScrollBehavior";

	/** The Constant HAS_TOUCHSCREEN. */
	public static final String	HAS_TOUCHSCREEN					= "hasTouchScreen";

	/** The Constant OVERLAPPING_CHECK_DISABLED. */
	public static final String	OVERLAPPING_CHECK_DISABLED		= "overlappingCheckDisabled";

	/** The Constant LOGGING_PREFS. */
	public static final String	LOGGING_PREFS					= "loggingPrefs";

	/** The Constant ENABLE_PROFILING_CAPABILITY. */
	public static final String	ENABLE_PROFILING_CAPABILITY		= "webdriver.logging.profiler.enabled";

	/** The Constant SECURITY_ENABLE_JAVA. */
	public static final String	SECURITY_ENABLE_JAVA			= "security.enable_java";
	
	/** The Constant CAP_IGNORE_CERT_ERR. */
	public static final String	CAP_IGNORE_CERT_ERR				= "--ignore-certificate-errors";
	
	
	/**
	 * The Class ChromeCapabilities.
	 */
	public static class ChromeCapabilities {
		/** The Constant BROWSER_NAME. */
		public static final String	BROWSER_NAME					= "browserName";

		/** The Constant PLATFORM. */
		public static final String	PLATFORM						= "platform";

		/** The Constant NODE_APP_CAPABILITY. */
		public static final String	NODE_APP_CAPABILITY				= "applicationName";

		/** The Constant CAP_CHROME_SWITCHES. */
		public static final String	CAP_CHROME_SWITCHES				= "chrome.switches";

	}
	
	/**
	 * The Class IECapabilities.
	 */
	public static class IECapabilities {
		/** The Constant ACCEPT_SSL_CERTS. */
		public static final String	ACCEPT_SSL_CERTS				= "acceptSslCerts";
		
		/** The Constant BROWSER_NATIVE_EVENTS. */
		public final static String	BROWSER_NATIVE_EVENTS			= "nativeEvents";

		/** The Constant BROWSER_ENABLE_PERSISTENT_HOVER. */
		public final static String	BROWSER_ENABLE_PERSISTENT_HOVER	= "enablePersistentHover";

		/** The Constant NODE_APP_CAPABILITY. */
		public static final String	NODE_APP_CAPABILITY				= "applicationName";
		
	}

}
