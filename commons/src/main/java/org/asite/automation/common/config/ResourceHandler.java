package org.asite.automation.common.config;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AutomationSeleniumExtendedLibrary;
import org.asite.automation.common.logger.AutomationLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class ResourceHandler.
 * @author jasminprajapati
 */
@SuppressWarnings("serial")
public class ResourceHandler extends Properties{

	/** The prop. */
	final private static Properties prop = new Properties();
	
	/** The in stream. */
	private static FileInputStream inStream = null;

	public static Logger log			= AutomationLogger.getInstance().getLogger(ResourceHandler.class);

	/**
	 * Gets the property value.
	 *
	 * @param key the key
	 * @return the property value
	 */
	public static String getPropertyValue(String key)
	{
		/*StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement ste = stacktrace[2];
		System.out.println("Caller function: "+ste.getMethodName());*/
		try	{
			if(System.getProperty(key) == null || System.getProperty(key).length() == 0 ) {
				inStream = new FileInputStream("config.properties");
				prop.load(inStream);
			}
			else
				return System.getProperty(key);
		}
		catch(Exception e) {
			System.out.print(e.toString());
		}

		System.setProperty(key, prop.getProperty(key).toString());
		return System.getProperty(key);
	}
	
	
	/**
	 * Load property.
	 *
	 * @param key the key
	 * @return the string
	 */
	public static String loadProperty(String key)
	{
		try {
			if(System.getProperty(key) == null) {
				inStream = new FileInputStream(new AutomationSeleniumExtendedLibrary().getConfigFile());
				prop.load(inStream);
			}
			else
				return System.getProperty(key);
		}
		catch(Exception e) {
			System.out.print(e.toString());
		}
		if(null != prop.getProperty(key))
			System.setProperty(key, prop.getProperty(key));
		else
			log.info("Property missing in config file :"+key);
		return System.getProperty(key);
	}
	
		
}
