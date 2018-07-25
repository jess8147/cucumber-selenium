package org.asite.automation.common.utils;

import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

import javax.annotation.Resource;

// TODO: Auto-generated Javadoc

/**
 * The Class ResourceUtils.
 *
 * @author jasminprajapati
 */
public class ResourceUtils {

	/**
	 * Gets the common user password.
	 *
	 * @return the common user password
	 */
	public String getCommonUserPassword() {
		return ResourceHandler.loadProperty("test.users.common.password");
	}

	/**
	 * Gets the secondary user.
	 *
	 * @param dc the dc
	 * @return the secondary user
	 */
	public String getSecondaryUser(String dc) {
		if(dc.equalsIgnoreCase("uk"))
			return ResourceHandler.loadProperty("secondary.uk.username");
		else if(dc.equalsIgnoreCase("us"))
			return ResourceHandler.loadProperty("secondary.us.username");
		else 
			return ResourceHandler.loadProperty("secondary.aus.username");
	}

	/**
	 * Gets the secondary password.
	 *
	 * @param dc the dc
	 * @return the secondary password
	 */
	public String getSecondaryPassword(String dc) {
		if(dc.equalsIgnoreCase("uk"))
			return ResourceHandler.loadProperty("secondary.uk.password");
		else if(dc.equalsIgnoreCase("us"))
			return ResourceHandler.loadProperty("secondary.us.password");
		else 
			return ResourceHandler.loadProperty("secondary.aus.password");
	}

	/**
	 * Gets the selenium server.
	 *
	 * @return the selenium server
	 */
	public static String getSeleniumServer() {
		return ResourceHandler.loadProperty("selenium.server.hub.ip");
	}

	/**
	 * Gets the application url.
	 *
	 * @return the application url
	 */
	public static String getApplicationURL() {
		return ResourceHandler.getPropertyValue("application.url");
	}

	/**
	 * Gets the Email Flag.
	 *
	 * @return the email flag
	 */
	public static String getEmailTriggerFlag() {
		return ResourceHandler.getPropertyValue("mail.trigger.flag");
	}

	/**
	 * Gets the Execution Environment.
	 *
	 * @return the Execution Environment
	 */
	public static String getExecutionEnvironment() {
		if(ResourceHandler.getPropertyValue("application.url").contains(AdoddleCommonStringPool.ENV_QA1_HOST))
			return AdoddleCommonStringPool.ENV_QA1;
		else
			return ResourceHandler.getPropertyValue("application.url").contains(AdoddleCommonStringPool.ENV_QA2_HOST) ? AdoddleCommonStringPool.ENV_QA2:AdoddleCommonStringPool.ENV_LIVE;
	}

	/**
	 * Gets the application Data Centers.
	 *
	 * @return the application Data Centers
	 */
	public static String getApplicationDataCenters() {
		return ResourceHandler.getPropertyValue("application.test.dcs");
	}

	/**
	 * Gets the selenium server port.
	 *
	 * @return the selenium server port
	 */
	public static String getSeleniumServerPort() {
		return ResourceHandler.loadProperty("selenium.server.hub.port");
	}

	/**
	 * Gets the remote domain.
	 *
	 * @return the remote domain
	 */
	public String getRemoteDomain() {
		return ResourceHandler.getPropertyValue("remote.domain");
	}

	/**
	 * Gets the browser.
	 *
	 * @return the browser
	 */
	public String getBrowser() {
		return ResourceHandler.getPropertyValue("browser");
	}

	/**
	 * Gets the upload auto it path.
	 *
	 * @return the upload auto it path
	 */
	public String getUploadAutoItPath() {
		return ResourceHandler.loadProperty("autoit.upload.executable.path");
	}

	/**
	 * Gets the download auto it path.
	 *
	 * @return the download auto it path
	 */
	public String getDownloadAutoItPath() {
		return ResourceHandler.loadProperty("autoit.download.path");
	}

	/**
	 * Gets the download auto it path.
	 *
	 * @return the download auto it path
	 */
	public String getPublicFolderAutoItPath() {
		return ResourceHandler.loadProperty("autoit.public.folder.pop.path");
	}

	/**
	 * Gets the applet upload auto it path.
	 *
	 * @return the applet upload auto it path
	 */
	public String getAppletUploadAutoItPath() {
		return ResourceHandler.loadProperty("autoit.handle.applet.path");
	}

	/**
	 * Gets the application test module.
	 *
	 * @return the test module
	 */
	public String getApplicationModule() {
		return ResourceHandler.getPropertyValue("application.test.module");
	}

	/**
	 * Gets the batch print auto it path.
	 *
	 * @return the batch print auto it path
	 */
	public String getBatchPrintAutoItPath() {
		return ResourceHandler.loadProperty("autoit.batch.print.executable.path");
	}

	/**
	 * Gets the x ref upload auto it path.
	 *
	 * @return the x ref upload auto it path
	 */
	public String getXRefUploadAutoItPath() {
		return ResourceHandler.loadProperty("autoit.XRef.applet.path");
	}

	/**
	 * Gets the dynamic link test data folder.
	 *
	 * @return the dynamic link test data folder
	 */
	public String getDynamicLinkTestDataFolder() {
		return ResourceHandler.loadProperty("dynamic.link.target.folder");
	}

	/**
	 * Gets the static link test data folder.
	 *
	 * @return the static link test data folder
	 */
	public String getStaticLinkTestDataFolder() {
		return ResourceHandler.loadProperty("static.link.target.folder");
	}

	/**
	 * Gets the link doc test data file.
	 *
	 * @return the link doc test data file
	 */
	public String getLinkDocTestDataFile() {
		return ResourceHandler.loadProperty("link.document.testdata.filename");
	}

	/**
	 * Gets the test data file path.
	 *
	 * @return the test data file path
	 */
	public String getTestDataFilePath() {
		return ResourceHandler.loadProperty("auto.create.testdata.filepath");
	}

	/**
	 * Gets the remote link doc test data file.
	 *
	 * @return the remote link doc test data file
	 */
	public String getRemoteLinkDocTestDataFile() {
		return ResourceHandler.loadProperty("remote.link.doc.testdata.file");
	}

	/**
	 * Gets the remote file download path.
	 *
	 * @return the remote file download path
	 */
	public String getRemoteFileDownloadPath() {
		return ResourceHandler.loadProperty("remote.download.file.path");
	}

	/**
	 * Gets the Default file download path.
	 *
	 * @return the Default file download path
	 */
	public String getDefaultDownloadDir() {	return ResourceHandler.loadProperty("default.browser.download.file.path"); }

	/**
	 * Gets the secondary test automation user.
	 *
	 * @param dc the dc
	 * @return the secondary test automation user
	 */
	public String getSecondaryTestAutomationUser(String dc) {
		if(dc.equalsIgnoreCase("uk"))
			return	ResourceHandler.loadProperty("test.user.automation.uk.name");
		else if(dc.equalsIgnoreCase("us"))
			return	ResourceHandler.loadProperty("test.user.automation.us.name");
		else if(dc.equalsIgnoreCase("aus"))
			return	ResourceHandler.loadProperty("test.user.automation.aus.name");
		return null;
	}

	/**
	 * Gets the failure time format.
	 *
	 * @return the failure time format
	 */
	public String getFailureTimeFormat() {
		return ResourceHandler.loadProperty("test.failure.time.format");
	}


	/**
	 * Gets the failure time zone.
	 *
	 * @return the failure time zone
	 */
	public String getFailureTimeZone() {
		return ResourceHandler.loadProperty("test.failure.time.zone");
	}

	/**
	 * Gets the failure time format.
	 *
	 * @return the failure time format
	 */
	public static String getScenarioTimeFormat() {
		return ResourceHandler.getPropertyValue("test.scenario.time.format");
	}


	/**
	 * Gets the failure time zone.
	 *
	 * @return the failure time zone
	 */
	public static String getScenarioTimeZone() {
		return ResourceHandler.getPropertyValue("test.scenario.time.zone");
	}

	/**
	 * Gets the automation classic org.
	 *
	 * @return the automation classic org
	 */
	public String getAutomationClassicOrg() {
		return ResourceHandler.loadProperty("automation.testing.classic.org");
	}

	/**
	 * Gets the Primary User org.
	 *
	 * @return the Primary User org
	 */
	public String getPrimaryUserOrg() {
		return ResourceHandler.loadProperty("primary.user.org");
	}

	/**
	 * Gets the Secondary User org.
	 *
	 * @return the Secondary User org
	 */
	public String getSecondaryUserOrg() {
		return ResourceHandler.loadProperty("secondary.user.org");
	}

	/**
	 * Gets the test data check xml path.
	 *
	 * @return the test data check xml path
	 */
	public static String getTestDataCheckXMLPath() {
		return ResourceHandler.loadProperty("automation.testdata.check.xml.path");
	}


	/**
	 * Gets the global wait timeout.
	 *
	 * @return the global wait timeout
	 */
	public int getGlobalWaitTimeout() {
		return Integer.parseInt(ResourceHandler.getPropertyValue("wait.timeout"));
	}


	/**
	 * Gets the out look navigate email URL.
	 *
	 * @return the out look navigate email URL
	 */
	public String getOutLookNavigateEmailURL() {
		return 	ResourceHandler.getPropertyValue("asite.outlook.navigate.email.url");
	}

	/**
	 * Gets web mail user.
	 *
	 * @return the web mail user
	 */
	public String getWebMailUser() {
		return ResourceHandler.getPropertyValue("asite.web.mail.user");
	}


	/**
	 * Gets date format.
	 *
	 * @param dc the dc
	 * @return the date format
	 */
	public String getDateFormat(String dc) {
		if(dc.equalsIgnoreCase("aus"))
			return ResourceHandler.getPropertyValue("aus.dc.date.format");
		return dc.equalsIgnoreCase("uk") ? ResourceHandler.getPropertyValue("uk.dc.date.format"):ResourceHandler.getPropertyValue("us.dc.date.format");
	}


	/**
	 * Gets download file properties.
	 *
	 * @return the download file properties
	 */
	public String getDownloadFileProperties() {
			return System.getProperty("global.org.id") + "-" + System.getProperty("global.test.project.id")+"-"
					+ System.getProperty("primary.username.id")+"-";
	}

	/**
	 * Gets special char string.
	 *
	 * @return the special char string
	 */
	public String getSpecialCharString() {
		return ResourceHandler.getPropertyValue("special.char.test.string");
	}

	/**
	 * Gets logged in user id.
	 *
	 * @param user the user
	 * @param dc   the dc
	 * @return the logged in user id
	 */
	public static String getLoggedInUserID(String user, String dc) {
		if(ResourceHandler.getPropertyValue("application.test.module").contains("Classic")) {
			return ResourceHandler.loadProperty("classic.primary.user.id");
		}

		if(user.equalsIgnoreCase(ResourceHandler.loadProperty("multi.project.user.username")))
			return ResourceHandler.loadProperty("multi.project.user.id");

		if("uk".equalsIgnoreCase(dc)) {
			if(user.equalsIgnoreCase(ResourceHandler.loadProperty("primary.uk.username")))
				return ResourceHandler.loadProperty("primary.uk.user.id");
			else if(user.equalsIgnoreCase(ResourceHandler.loadProperty("new.file.publish.uk.username")))
				return ResourceHandler.loadProperty("new.file.publish.uk.user.id");
		}
		else if("us".equalsIgnoreCase(dc)) {
			if(user.equalsIgnoreCase(ResourceHandler.loadProperty("primary.us.username")))
				return ResourceHandler.loadProperty("primary.us.user.id");
			else if(user.equalsIgnoreCase(ResourceHandler.loadProperty("new.file.publish.us.username")))
				return ResourceHandler.loadProperty("new.file.publish.us.user.id");
		}
		else if("aus".equalsIgnoreCase(dc)) {
			if(user.equalsIgnoreCase(ResourceHandler.loadProperty("primary.aus.username")))
				return ResourceHandler.loadProperty("primary.aus.user.id");
			else if(user.equalsIgnoreCase(ResourceHandler.loadProperty("new.file.publish.aus.username")))
				return ResourceHandler.loadProperty("new.file.publish.aus.user.id");
		}
		return "000000";
	}
}

