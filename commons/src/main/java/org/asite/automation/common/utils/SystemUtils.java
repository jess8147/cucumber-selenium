package org.asite.automation.common.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.errors.AutomationErrorStrings;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleSystemUtils.
 * @author jasminprajapati
 */
@SuppressWarnings("ALL")
public class SystemUtils {

	/** The log. */
	public static Logger			log				= Logger.getLogger(SystemUtils.class.getName());
	public AutomationErrorStrings	eStringUtils	= null;

	/**
	 * Gets the file list.
	 * 
	 * @param files
	 *            the files
	 * @return the file list
	 */
	public List<String> getFileList(String files) {
		List<String> fileList = new ArrayList<String>();
		String[] uploadFiles = files.split(",");

		for (int i = 0; i < uploadFiles.length; i++) {
			fileList.add(uploadFiles[i].trim());
		}

		return fileList;
	}

	/**
	 * Delete directory.
	 * 
	 * @param folderPath
	 *            the folder path
	 */
	public void deleteDirectory(String folderPath) {
		File directory = new File(folderPath);

		if (directory.exists()) {
			for (File f : directory.listFiles()) {
				if (f.isDirectory()) {
					deleteDirectory(f.toPath().toString());
					f.delete();
				}
				else {
					f.delete();
				}
			}
			directory.delete();
		}
	}

	/**
	 * Creates the pdf file.
	 * 
	 * @param filePath
	 *            the file path
	 * @param nodeIP
	 *            the node ip
	 * @return the string
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("JavaDoc")
	public String createRemotePdfFile(String filePath, String nodeIP) {
		String accessPath = filePath.replace("Users","c$\\Users");
		log.info("Creating PDF file as: "+ accessPath);
		/*String machineIP = filePath.replace("\\\\", "");
		authenticateRemoteMachine(machineIP.substring(0, machineIP.indexOf("\\")));*/
		createDirectory(accessPath.substring(0, accessPath.lastIndexOf("\\")));

		try {
			OutputStream file = new FileOutputStream(new File(accessPath));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();

			Image img = Image.getInstance(nodeIP + ResourceHandler.loadProperty("automation.pdf.attach.img.filepath").trim());
			document.add(img);
			document.add(new Paragraph(new JavaUtils().getRandomString(30).trim()));
			document.add(new Paragraph(new Date().toString()));
			Thread.sleep(1000);
			document.close();
			file.close();
		}
		catch (Exception e) {
			log.info(e.getLocalizedMessage());
		}
		waitUntilFileExists(new File(accessPath));
		return accessPath;
	}

	/**
	 * Get System PDF file's total Pages Count.
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */

	public int getPDFFilePagesCount(String filePath) throws IOException {
		PdfReader reader = new PdfReader(filePath);
		return reader.getNumberOfPages();
	}

	/**
	 * Copy the ifc file.
	 * 
	 * @param sourceFilePath
	 *            the source file path
	 * @param destFilePath
	 *            the dest file path
	 * @return the string
	 * @throws IOException 
	 */

	@SuppressWarnings("resource")
	public String copyIFCfile(String sourceFilePath, String destFilePath) throws IOException {
		/*InputStream inStream = null;
		OutputStream outStream = null;
		destFilePath = destFilePath + ".ifc";

		try {
			File source = new File(sourceFilePath);
			File target = new File(destFilePath);
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			inStream.close();
			outStream.close();

			log.info("IFC File Copied successfully!");
		}
		catch (IOException e) {
			log.info("failure in copying ifc file");
		}
		return destFilePath;*/
		
		FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
		destFilePath = destFilePath + ".ifc";

	    try {
	    	File source = new File(sourceFilePath);
			File target = new File(destFilePath);
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(target).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	       }
		return destFilePath;
	}
	

	/**
	 * Creates the file.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public String createFile(String filePath) throws IOException, InterruptedException {
		String accessPath = filePath.replace("Users","c$\\Users");
		log.info("Creating PDF file as: "+ accessPath);
		/*String machineIP = filePath.replace("\\\\", "");
		authenticateRemoteMachine(machineIP.substring(0, machineIP.indexOf("\\")));*/
		createDirectory(accessPath.substring(0, accessPath.lastIndexOf("\\")));
		BufferedWriter output = null;
		try {
			File file = new File(accessPath);
			output = new BufferedWriter(new FileWriter(file));
			Thread.sleep(1000);
			output.write(new JavaUtils().getRandomString(1000));
		}
		catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}
		finally {
			if (output != null)
				output.close();
		}

		return accessPath;
	}

	/**
	 * Delete file.
	 * 
	 * @param path
	 *            the path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void deleteFile(String path) throws IOException {
		File file = new File(path);
		try {

			file.delete();
		}
		catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

	}

	/**
	 * Creates the directory.
	 * 
	 * @param folderPath
	 *            the folder path
	 * @return the string
	 */
	public String createDirectory(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				log.info("Directory is created!");
			}
			else {
				eStringUtils = new ErrorStringUtils();
				log.error(eStringUtils.getDirCreationError(file.toString()));
			}
		}
		return folderPath;
	}

	/**
	 * Un zip file.
	 * 
	 * @param zipFile
	 *            the zip file
	 * @param outputFolder
	 *            the output folder
	 */
	public void unZipFile(String zipFile, String outputFolder) {
		try {

			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			ZipInputStream zipStream = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry zEntry;

			while ((zEntry = zipStream.getNextEntry()) != null) {
				log.info("Unzipping " + zEntry.getName() + " at " + outputFolder);

				if (zEntry.isDirectory())
					hanldeDirectory(zEntry.getName());
				else {
					FileOutputStream fout = new FileOutputStream(outputFolder + "/" + zEntry.getName());
					BufferedOutputStream bufout = new BufferedOutputStream(fout);
					byte[] buffer = new byte[1024];
					int read = 0;
					while ((read = zipStream.read(buffer)) != -1) {
						bufout.write(buffer, 0, read);
					}

					zipStream.closeEntry();
					bufout.close();
					fout.close();
				}
			}
			zipStream.close();
			log.info("Unzipping complete. path :  " + outputFolder);
		}
		catch (Exception e) {
			log.error("Unzipping failed");
			log.error(e.getLocalizedMessage());
		}

	}

	public void hanldeDirectory(String dir) {
		File f = new File(dir);
		if (!f.isDirectory()) {
			f.mkdirs();
		}
	}

	/**
	 * Gets the file list of system folder.
	 * 
	 * @param dir
	 *            the dir
	 * @return the file list of system folder
	 */
	public List<String> getFileListOfSystemFolder(String dir) {

		File systemFolder = new File(dir);
		List<File> listOfFiles = Arrays.asList(systemFolder.listFiles());
		List<String> directoryFileNames = new ArrayList<String>();

		for (File file : listOfFiles)
			directoryFileNames.add(file.toString().substring(file.toString().lastIndexOf("\\") + 1));

		return directoryFileNames;

	}

	/**
	 * Authenticate remote machine.
	 * 
	 * @param remoteMachineIP
	 *            the remote machine ip
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void authenticateRemoteMachine(String remoteMachineIP) {
		String netExecPath = new File("./src/test/resources/net.exe").toString();
		String remoteDomain = ResourceHandler.getPropertyValue("remote.domain");
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");

		if (ResourceHandler.getPropertyValue("selenium.server.hosts").contains(remoteMachineIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
			remotePassword = (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.nodes.user");
			remotePassword = (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}
		/*
		 * Example --> net use \\192.168.102.95 /user:ASITE\jasminprajapati asite987
		 */
		String cmd = netExecPath + " use " + remoteMachineIP + " /user:" + remoteDomain + "\\" + remoteUser + " " + remotePassword;
		log.info("Remote net connect call: " + cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		}
		catch(Throwable t) {
			log.error("Error while executing remote exe: "+t.getLocalizedMessage());
		}
	}

	/**
	 * Gets the file size.
	 * 
	 * @param fileLocation
	 *            the file location
	 * @return the file size
	 */
	public double getFileSize(String fileLocation) {
		File file = new File(fileLocation);
		if (file.exists()) {
			double bytes = file.length();
			double kbytes = bytes / 1024;
			return kbytes;
		}
		return 0;

	}

	/**
	 * Wait until file is downloaded.
	 * 
	 * @param file
	 *            the file
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitUntilFileIsDownloaded(File file) throws InterruptedException {
		int index = 0;
		while (!file.exists()) {
			log.info("Waiting for file to be downloaded: " + file.toString());
			Thread.sleep(2000);
			index++;
			if (index > 120) {
				eStringUtils = new ErrorStringUtils();
				AutomationAssert.verifyTrue(eStringUtils.getFileDownloadError(file.toString()), false);
				break;
			}
		}
	}

	/**
	 * Wait until file is downloaded.
	 * 
	 * @param file
	 *            the file
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitUntilFileExists(File file) {
		int index = 0;
		while (!file.exists()) {
			log.info("Waiting for file to exist: " + file.toString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				log.error(e.getLocalizedMessage());
			}
			index++;
			if (index > 120) {
				eStringUtils = new ErrorStringUtils();
				AutomationAssert.verifyTrue(eStringUtils.getFileExistsError(file.toString()), false);
				break;
			}
		}
	}
	
	/**
	 * Clear java cache.
	 * 
	 * @param nodeIP
	 *            the node ip
	 */
	public void clearJavaCache(String nodeIP) {
		String cacheDir = nodeIP + "\\Users\\jasminprajapati\\AppData\\LocalLow\\Sun\\Java\\Deployment\\cache";
		
		try {
			deleteDirectory(cacheDir);
		}
		catch (Throwable t) {
			eStringUtils = new ErrorStringUtils();
			log.error(eStringUtils.getFileExistsError(cacheDir));
		}

	}

	/**
	 * Execute vbs call.
	 * 
	 * @param filePath
	 *            the file path
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void executeVBSCall(String filePath, String nodeIP) throws InterruptedException, IOException {
		String psExecPath = new File("./src/test/resources/PsExec.exe").toString();
		String remoteDomain = ResourceHandler.loadProperty("remote.domain");
		String remoteUser = ResourceHandler.loadProperty("remote.user");
		String remotePassword = ResourceHandler.loadProperty("remote.password");
		String vbsFilePath = ResourceHandler.loadProperty("vbs.file.download.path");
		String cmd = psExecPath + " -i " + nodeIP + " -u " + remoteDomain + "\\" + remoteUser + " -p " + remotePassword + " wscript.exe " + vbsFilePath + " " + filePath + " " + filePath + "";

		log.info("Remote AutoIt call:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();

	}

	/**
	 * Wait for multi download file exists file.
	 *
	 * @param nodeIP         the node ip
	 * @param fileName		 the file name
	 * @param targetFilePath the target file path
	 * @return the filepath
	 * @throws InterruptedException the interrupted exception
	 */
	public String waitForSingleDirectFileDownload(String nodeIP, String fileName, String targetFilePath) throws InterruptedException {
		boolean fileFound = false;
		String defaultDownloadPath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path");
		File defaultDownloadFile = new File (defaultDownloadPath + fileName);
		waitUntilFileIsDownloaded(defaultDownloadFile);
		File targetFile = new File(targetFilePath);
		defaultDownloadFile.renameTo(targetFile);
		waitUntilFileIsDownloaded(targetFile);
		return targetFilePath;
	}

	/**
	 * Wait for multi download file exists file.
	 *
	 * @param nodeIP         the node ip
	 * @param targetFilePath the target file path
	 * @param dataCenter     the data center
	 * @return the file
	 * @throws InterruptedException the interrupted exception
	 */
	public String waitForMultipleFileDownload(String nodeIP, String targetFilePath, String...optionalParam) throws InterruptedException {
		boolean fileFound = false;
		String downloadLogicalFileName = null;
		String defaultDownloadPath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path");

		if(optionalParam != null && optionalParam.length > 0)
			downloadLogicalFileName = optionalParam[0].substring(0,8);
		else {
			downloadLogicalFileName = new ResourceUtils().getDownloadFileProperties() + new DateUtils().getCurrentDateTimeWithZone("dd-MM-yyyy-HH-mm", "UTC");
		}

		log.info("Logical File Name: "+downloadLogicalFileName);
		for(int counter = 0; counter < new ResourceUtils().getGlobalWaitTimeout(); counter++) {
			Thread.sleep(1000);
			for (String s : getFileListOfSystemFolder(defaultDownloadPath)) {
				log.info("File in default path: "+s);
				if(s.contains(downloadLogicalFileName) && !(s.contains(".crdownload"))) {
					log.info("File found successfully");
					downloadLogicalFileName = s;
					log.info("Physical File Name: "+downloadLogicalFileName);
					fileFound= true;
					break;
				}
				else
					continue;
			}
			if(fileFound)
				break;
		}

		File downloadedFile = new File (defaultDownloadPath + downloadLogicalFileName);
		File targetFile = new File(targetFilePath);
		downloadedFile.renameTo(targetFile);
		waitUntilFileIsDownloaded(targetFile);

		return targetFilePath;
	}
}
