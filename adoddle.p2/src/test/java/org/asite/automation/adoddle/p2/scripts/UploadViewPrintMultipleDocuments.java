package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class UploadViewPrintMultipleDocuments extends AdoddleCommonAppMethods {

	private List<String>	systemMultiDocumentsList	= new ArrayList<String>();
	private List<String>	uploadMultiDocumentsList	= new ArrayList<String>();
	private List<String>	fileList					= new ArrayList<String>();
	private List<String>	printList					= new ArrayList<String>();
	private String			BatchPrintCommonFile		= "BatchPrintAutoTestDataFile", parentHandle;
	private String			batchFilePath, folderName, projectType = "Adoddle";
	private int				batchFileCount;
	public static Logger	log							= AutomationLogger.getInstance().getLogger(UploadViewPrintMultipleDocuments.class);

	public void setUpBrowser(String browserName, String dataCentre) throws IOException, InterruptedException {

		if (browser.contains("IE"))
			tearDown();
		TestDriverFactory.getInstance().setUp(browserName);
		propertyUtils.setupEnvironmentTestProperties(dataCentre, ResourceHandler.loadProperty("test.user.ravisingh.id"));
		login(ResourceHandler.loadProperty("test.user.ravisingh.id"), ResourceHandler.loadProperty("test.user.ravisingh.password"));
		verifyLogin();

	}

	public void createAndfocusWorkspaceDirectory(String workspace) throws InterruptedException {

		folderName = "BatchPrintAutoTestFolder" + JavaUtils.getRandomNumber(15);
		navigateTab(LandingPage.lnk_Files);
		contextClickWithText(workspace);
		waitUntilElementIsDisplayed(FilesTab.opt_ProjectContextClickNew);
		mouseHoverAndClickElement(FilesTab.opt_ProjectContextClickNew, FilesTab.opt_ProjectContextClickFolder);
		waitUntilElementIsDisplayed(FilesTab.txt_PopCreateFolderFolderName);
		sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopCreateFolderCreate, FilesTab.btn_PopCreateFolderCreate);
		waitForElementWithText(folderName);
		clickElementWithText(folderName);
	}

	public void publishMultipleDocuments() throws IOException, InterruptedException {

		String fileName, fileExtension;

		clickElementAndWaitForElement(FilesTab.btn_AddFiles, GlobalPageElements.pop_PopUpElement);

		sysUtils.authenticateRemoteMachine(nodeIP);

		systemMultiDocumentsList = sysUtils.getFileListOfSystemFolder(nodeIP + ResourceHandler.loadProperty("print.multi.document.testdata.filepath"));

		System.out.println("Documents Name" + systemMultiDocumentsList);
		for (String str : systemMultiDocumentsList) {

			fileExtension = "." + str.split("\\.")[1];
			sysUtils.authenticateRemoteMachine(nodeIP);
			System.out.println("String " + str);
			fileName = copyAndRenameMultipleDocuments(nodeIP + ResourceHandler.loadProperty("print.multi.document.testdata.filepath") + str, nodeIP + ResourceHandler.loadProperty("remote.copy.file.folderpath"), fileExtension);

			System.out.println("String " + fileName);
			uploadMultiDocumentsList.add(fileName);
		}

		System.out.println("Documents Name" + uploadMultiDocumentsList);
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, uploadMultiDocumentsList);
		enterHeaderValuesAndApplyAll();
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

	}

	public void enterHeaderValuesAndApplyAll() {

		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void viewMultipleBatchDocuments() {

		String parentWindow = getCurrentWindow();

		for (String file : uploadMultiDocumentsList) {
			String fileName = file.split("\\\\")[6];
			parentWindow = getCurrentWindow();
			searchFiles(fileName);
			clickElementAndWait(FilesTab.lnk_DocListingFirstFileName);
			waitForSwitchWindow(2);
			switchWindow();
			verifyFileInSimpleViewer(fileName);
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);

		}

	}

	public void PrintMultipleBatchDocuments() throws IOException, InterruptedException {

		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(folderName);
		// searchFiles(BatchPrintCommonFile);
		batchFileCount = getCount(FilesTab.css_DocListingFilesCount);
		List<WebElement> batchFiles = findElements(FilesTab.css_FilesDocRefList);
		for (WebElement e : batchFiles)
			fileList.add(e.getText());
		clickElementAndWait(FilesTab.chk_DocListingCheckAll);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		clickContextMenuOptionWithText("Print File");
		verifyAllSelectedBatchPrintFiles();
		clickElement(FilesTab.chk_PopPrintDocumentInclMarkup);
		clickElement(FilesTab.chk_PopPrintDocumentInclChangeMark);
		clickElement(FilesTab.chk_PopPrintDocumentFitToBanner);
		clickLinkWithText("Print");
		verifyNewWindowOpens();
		createBatchFileInLocal(BatchPrintCommonFile);

	}

	public void verifyAllSelectedBatchPrintFiles() {
		List<WebElement> batchPrintDocs = findElements(FilesTab.css_PopPrintDocumentDocRefList);
		Assert.assertTrue(batchFileCount == batchPrintDocs.size());
		for (WebElement e : batchPrintDocs)
			printList.add(e.getText());
		Assert.assertEquals(fileList, printList);
	}

	public void verifyNewWindowOpens() {
		parentHandle = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();

	}

	public void createBatchFileInLocal(String fileName) throws IOException, InterruptedException {

		batchFilePath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + fileName + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION;
		log.info("FileName:: " + batchFilePath);
		sysUtils.authenticateRemoteMachine(nodeIP);
		try {
			waitForCompletePageLoad();
			handleBatchPrint(batchFilePath, projectType, nodeIP);
		}
		catch (IOException e) {
			log.error("I/O exeption while handling Print Documents Dialog");
		}
		catch (InterruptedException e) {
			log.error("Interrupted exeption while handling Print Documents Dialog");
		}

		switchPreviousWindow(parentHandle);

	}

	public void compareFileSizeInLocal() throws IOException, InterruptedException {

		boolean flag = false;
		double batchfileSizeDifference;
		sysUtils.authenticateRemoteMachine(nodeIP);
		double localBatchPrintFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("bulk.batch.print.testdata.filepath") + AdoddleCommonStringPool.PDF_EXTENSION);
		double savedBatchPrintFileSize = sysUtils.getFileSize(batchFilePath);
		localBatchPrintFileSize = Math.round(localBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("LocalFileSize :" + localBatchPrintFileSize);

		savedBatchPrintFileSize = Math.round(savedBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("DowloadedFileSize: " + savedBatchPrintFileSize);

		batchfileSizeDifference = Math.round((savedBatchPrintFileSize - localBatchPrintFileSize) * 1000.0) / 1000.0;
		log.info("BatchFileSize Difference: " + batchfileSizeDifference);
		if (savedBatchPrintFileSize != 0) {
			flag = true;
			log.info("BatchFileDifference :: " + batchfileSizeDifference);

		}
		else
			Assert.assertTrue("BatchAutoTestFile not saved successfully", flag);

	}

	public boolean validateFileSizeRange(double fileSizeDifference) {

		return fileSizeDifference <= 100.0 && fileSizeDifference >= -100.0;

	}

	public String copyAndRenameMultipleDocuments(String sourceFilePath, String destFilePath, String fileExtension) {
		InputStream inStream = null;
		OutputStream outStream = null;
		destFilePath = destFilePath + epoch + JavaUtils.getRandomNumber(5) + fileExtension;

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

			log.info("File Copied successfully!");
		}
		catch (IOException e) {
			log.info("failure in copying file");
		}
		return destFilePath;
	}

	public void verifyFileInSimpleViewer(String fileName) {
		waitForCompletePageLoad();

		if (!isDisplayed(FilesTab.lbl_UnsupportedFile)) {
			waitAndSwitchIframe(FilesTab.frm_BravaObjectFrame);

			if (!isDisplayed(FilesTab.frm_ErrorFileIframe)) {
				waitUntilElementIsDisplayed(FilesTab.frm_OpenFileIframe);
				Assert.assertTrue(isDisplayed(FilesTab.frm_OpenFileIframe));
				log.info("Expected Iframe: Expected Iframe Detected");

				log.info("Attachment :" + getElementText(FilesTab.ele_ViewAttachFileName));
				Assert.assertTrue(getElementText(FilesTab.ele_ViewAttachFileName).contains(fileName));
				switchDefault();

			}
			else {
				log.error("Error Iframe: Error Iframe Detected");
				Assert.assertTrue(false);
			}
		}
		else {
			log.info("Not supported File : File not supported");
		}
		switchDefault();
	}

	public void handleBatchPrint(String fileName, String projectType, String nodeIP) throws IOException, InterruptedException {
		String psExecPath = new File("./src/test/resources/PsExec.exe").toString();
		log.info("BatchFile name::" + fileName);
		String remoteDomain = ResourceHandler.getPropertyValue("remote.domain");
		String browser = ResourceHandler.getPropertyValue("browser");
		String autoITPath = ResourceHandler.loadProperty("autoit.Bulk.print.executable.path");
		String remoteUser = System.getProperty("remote.user");
		String remotePassword = System.getProperty("remote.password");

		if (ResourceHandler.getPropertyValue("selenium.server.hosts").contains(nodeIP.replace("\\\\", ""))) {
			remoteUser = (remoteUser != null) ? remoteUser : ResourceHandler.getPropertyValue("selenium.server.hosts.user");
			remotePassword = (remotePassword != null) ? remotePassword : ResourceHandler.getPropertyValue("selenium.server.hosts.password");
		}
		else {
			remoteUser = ResourceHandler.getPropertyValue("selenium.server.nodes.user");
			remotePassword = ResourceHandler.getPropertyValue("selenium.server.nodes.password");
		}

		String cmd = psExecPath + " -i " + nodeIP + " -u " + remoteDomain + "\\" + remoteUser + " -p " + remotePassword + " " + autoITPath + " " + browser + " " + fileName + " " + projectType + "";
		System.out.println("Command for Remote AutoIt execution:" + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
	}

	public void cleanUpOperations(String workspace) {

		deactivateProjectFolder(workspace, folderName);
		folderName = null;
		uploadMultiDocumentsList.clear();
		systemMultiDocumentsList.clear();

	}

}