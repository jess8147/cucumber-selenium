package org.asite.automation.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.asite.automation.common.config.ResourceHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class AutoItUtils.
 * @author jasminprajapati
 */
public class AutoItUtils {

	/** The applet window name. */
	public static String uploadWindowName, downloadWindowName, appletWindowName;
	
	/** The log. */
	public static Logger log = Logger.getLogger(AutoItUtils.class.getName());
	
	/** The resource utils. */
	ResourceUtils resourceUtils = new ResourceUtils();
	
	/**
	 * Upload file auto it.
	 *
	 * @param filePath            the file path
	 * @param nodeIP the node IP
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws InterruptedException             the interrupted exception
	 */
	public void uploadFileAutoIt(String filePath, String nodeIP) throws IOException,
			InterruptedException {
		
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");

		if(ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
		    remotePassword =  (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
		    remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}

		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("CHROME"))
			uploadWindowName = "Open";
		else if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("FF"))
			uploadWindowName = "File Upload";
		else if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("IE"))
			uploadWindowName = "Choose File to Upload";
		String cmd = getPsExecPath() + " -i " + nodeIP + " -u " + resourceUtils.getRemoteDomain()
				+ "\\" + remoteUser + " -p " + remotePassword + " "
				+ resourceUtils.getUploadAutoItPath() + " " + resourceUtils.getBrowser() + " " + filePath + " "
				+ uploadWindowName + "";
		log.info("Remote AutoIt call: " + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor(900, TimeUnit.SECONDS);

	}
	
	
	/**
	 * Download auto it.
	 *
	 * @param filePath            the file path
	 * @param nodeIP the node IP
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws InterruptedException             the interrupted exception
	 */
	public void downloadAutoIt(String filePath, String nodeIP) throws IOException,
			InterruptedException {
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");
		String remoteDomain = "";
		
		if(ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
		    remotePassword =  (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
		    remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}
		 
		if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("CHROME"))
			downloadWindowName = "Save As";
		else if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("FF"))
			downloadWindowName = "Enter name of file to save to...";
		else if (ResourceHandler.loadProperty("browser").equalsIgnoreCase("IE"))
			downloadWindowName = "Save As";
		
		remoteDomain = remoteUser.equalsIgnoreCase("admin") ? "" : resourceUtils.getRemoteDomain()+"\\";
				
		String cmd = getPsExecPath() + " -i " + nodeIP + " -u " + remoteDomain + remoteUser + " -p " + remotePassword + " "
				+ resourceUtils.getDownloadAutoItPath() + " " + resourceUtils.getBrowser() + " " + "\"" + filePath
				+ "\" +downloadWindowName+";
		log.info("Remote AutoIt call:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor(900, TimeUnit.SECONDS);
	}
	
	
	/**
	 * Handle alert window popup.
	 *
	 * @param function the function
	 * @param nodeIP the node IP
	 */
	public void handleAlertWindowPopup(String function, String nodeIP) {
		try {
			String remoteUser = System.getProperty("remote.user");
			String remotePassword = System.getProperty("remote.password");
			String remoteDomain = "";
			
			
			if(ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
				remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
			    remotePassword =  (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
			}
			else {
				remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
			    remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
			}
			remoteDomain = remoteUser.equalsIgnoreCase("admin") ? "" : resourceUtils.getRemoteDomain()+"\\";
			
			String cmd = getPsExecPath() + " -i " + nodeIP + " -u " + remoteDomain + remoteUser + " -p " + remotePassword + " " + resourceUtils.getPublicFolderAutoItPath() + " " + function + "";
			log.info("Command for Remote AutoIt execution:" + cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		}
		catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	
	/**
	 * Verify window popup with text.
	 *
	 * @param popupText            the popup text
	 * @param nodeIP the node IP
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws InterruptedException             the interrupted exception
	 */
	public void verifyWindowPopupWithText(String popupText, String nodeIP) throws IOException,
			InterruptedException {
		String windowCheck = "true";
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");
		
		if(ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
		    remotePassword =  (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
		    remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}

		String cmd = getPsExecPath() + " -i " + nodeIP + " -u " + resourceUtils.getRemoteDomain()
				+ "\\" + remoteUser + " -p " + remotePassword + " "
				+ resourceUtils.getDownloadAutoItPath() + " " + resourceUtils.getBrowser() + " " + windowCheck + " "
				+ popupText + "";
		log.info("Remote AutoIt call:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor(600, TimeUnit.SECONDS);
	}
	

	/**
	 * Handle applet upload.
	 *
	 * @param filePath the separate file path for Adoddle and Classic 
	 * @param nodeIP the node IP
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws InterruptedException             the interrupted exception
	 */
	public void handleAppletUpload(String filePath, String appletWindowURL, String nodeIP) throws IOException, InterruptedException {
		String fileName = new StringUtils().extractFileNameString(filePath).replace(".txt", "");
		log.info("Applet Upload file name::" + fileName);
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");	
		
		if(ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
		    remotePassword =  (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
		    remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}

		String cmd = getPsExecPath() + " -i " + nodeIP + " -u " + resourceUtils.getRemoteDomain()
				+ "\\" + remoteUser + " -p " + remotePassword + " "
				+ resourceUtils.getAppletUploadAutoItPath() + " " + resourceUtils.getBrowser() + " " + filePath + " " + fileName + " " + appletWindowURL
				+ "";
		log.info("Command for Remote AutoIt execution:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor(900, TimeUnit.SECONDS);
	}	

	/**
	 * Handle Batch Print Dialog.
	 *
	 * @param fileName            the file name
	 * @param projectType the project type
	 * @param nodeIP the node IP
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws InterruptedException             the interrupted exception
	 */
	public void handleBatchPrint(String fileName, String projectType, String batchPrintDocCount, String nodeIP) throws IOException,
			InterruptedException {
		
		log.info("BatchFile name: " + fileName);
		
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");	
		
		if(ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
		    remotePassword =  (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
		    remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}
		
		String cmd = getPsExecPath() + " -i " + nodeIP + " -u " + resourceUtils.getRemoteDomain() + "\\" + remoteUser + " -p " + remotePassword
				+ " " + resourceUtils.getBatchPrintAutoItPath() + " " + resourceUtils.getBrowser() + " " + fileName + " " + projectType + " " + batchPrintDocCount + "";
		log.info("Command for Remote AutoIt execution:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor(900, TimeUnit.SECONDS);
	}
	
	/**
	 * Handle X-Ref Applet upload.
	 *
	 * @param filePath the file path
	 * @param fileRev the file rev
	 * @param uploadType the upload type
	 * @param nodeIP the node IP
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws InterruptedException             the interrupted exception
	 */
	public void XRef_AppletUpload(String filePath, String fileRev, String uploadType, String nodeIP)
			throws IOException, InterruptedException {
		String fileName = filePath.split("\\\\")[5].replace(".dwg", "");
		log.info("X-Ref Upload file name: " + fileName);
		
		
		String remoteUser = ResourceHandler.loadProperty("remote.user");
		String remotePassword = ResourceHandler.loadProperty("remote.password");
		
		if(ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
		    remotePassword =  (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
		    remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}
		
		String cmd = getPsExecPath() + " -i " + nodeIP + " -u " + resourceUtils.getRemoteDomain() + "\\" + remoteUser + " -p " + remotePassword
				+ " " + resourceUtils.getXRefUploadAutoItPath() + " " + resourceUtils.getBrowser() + " " + filePath + " " + fileName + " " + fileRev + " " + uploadType
				+ "";
		System.out.println("Command for Remote AutoIt execution:" + cmd);
		log.info("Command for Remote AutoIt execution:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor(900, TimeUnit.SECONDS);
	}
	
	/**
	 * Gets the ps exec path.
	 *
	 * @return the ps exec path
	 */
	public String getPsExecPath() {
		return	new File("./src/test/resources/PsExec.exe").toString();
	}
}