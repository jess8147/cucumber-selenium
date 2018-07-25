package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class XREFAppletUploadScripts extends AdoddleCommonAppMethods {

	private static String		WorkspaceName, XRef_filePath, XRef_fileName;
	private String				currentX_RefChildRevision, updatedX_RefChildRevision, newX_RefChildRevision = "1";
	private String				currentX_RefVer, updatedX_RefRev, newX_RefVer = "1";
	private String				firstWindowHandle, secondWindowHandle;
	private List<String>		systemFileList		= new ArrayList<String>();
	private List<String>		XRefChildFilesList	= new ArrayList<String>();
	private List<WebElement>	XRefChildElements	= new ArrayList<WebElement>();
	private int					updatedX_RefVer, updatedX_RefChildVersion;
	public static String		folderName;
	public static Logger		log					= AutomationLogger.getInstance().getLogger(XREFAppletUploadScripts.class);

	public void createTestFolders(String directoyName) {
		if (directoyName.contains("XRef_AutoFolder")) {
			folderName = directoyName + dateUtils.getEpoch();
			waitUntilElementIsDisplayed(FilesTab.txt_PopCreateFolderFolderName);
			sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName);
			clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			Assert.assertTrue(isDisplayedElementWithText(folderName));
		}

		else {
			log.info("Directory name::" + directoyName);
			waitUntilElementIsDisplayed(FilesTab.txt_PopCreateFolderFolderName);
			sendKeys(FilesTab.txt_PopCreateFolderFolderName, directoyName);
			clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			Assert.assertTrue(isDisplayedElementWithText(directoyName));

		}

	}

	public void validateFoldersInFolderTree() {
		log.info("<createTestFolders> Covered in Above Definition");
	}

	public void focusOnFolder(String directoryName) {
		log.info("Folder Name:: " + directoryName);
		if (directoryName.contains("XRef_AutoFolder"))
			clickElementWithText(folderName);
		else
			clickElementWithText(directoryName);
	}

	public void searchParentFileAndGetVersion(String parentFileName) throws IOException, InterruptedException {

		systemFileList = sysUtils.getFileListOfSystemFolder(ResourceHandler.loadProperty("multi.X-REF.filespath"));
		XRef_fileName = parentFileName.split("-")[0];
		firstWindowHandle = getCurrentWindow();
		searchFiles(XRef_fileName);
		currentX_RefVer = getElementText(FilesTab.lnk_FileListingFirstVersion);
		log.info("CurrentX-RefVerion:: " + currentX_RefVer);
		updatedX_RefVer = Integer.parseInt(currentX_RefVer) + 1;
		updatedX_RefRev = Integer.toString(updatedX_RefVer);
		log.info("Updated X-Ref Revision" + updatedX_RefRev);
		clickElementAndWait(FilesTab.lnk_DocListingFirstDocRef);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		secondWindowHandle = getCurrentWindow();
		System.out.println("Switched Window Title: " + getWebDriver().getTitle());
		waitForCompletePageLoad();
		clickElementAndWait(FilesTab.lnk_FileViewLHAssociationBlock);
		XRefChildElements = findElements(FilesTab.css_FileViewAssociationsXRefChilds);
		for (WebElement e : XRefChildElements)
			XRefChildFilesList.add(e.getText());
		System.out.println("X-Ref ChildElements:: " + XRefChildFilesList.size());
		Assert.assertTrue("Compare X_Ref Child List", systemFileList.containsAll(XRefChildFilesList));

	}

	public void getXRefCurrentChildRevision() throws IOException, InterruptedException {

		for (WebElement ele : XRefChildElements) {

			if (ele.getText().contains("001_Cover Sheet.pdf")) {
				ele.click();
				waitForSwitchWindow(3);
				switchToThirdWindow(firstWindowHandle, secondWindowHandle);
				break;
			}

			else
				log.info("Child not Found");

		}

		waitForCompletePageLoad();
		System.out.println("Switched Window Title: " + getWebDriver().getTitle());
		waitUntilElementIsDisplayed(By.cssSelector("div[id='fileAllHistorySection'] select[id='xrefTypeDDL']"));
		selectByVisibleText(By.cssSelector("div[id='fileAllHistorySection'] select[id='xrefTypeDDL']"), "Revision Uploaded");
		selectByIndex(By.cssSelector("div[id='fileAllHistorySection'] select[id='xrefhistoryRevDDl']"), 1);
		waitForCompletePageLoad();
		currentX_RefChildRevision = getElementText(By.cssSelector("div[class*='historyRow'] :nth-child(2) span[title*='Revision']"));
		System.out.println("Current X-Ref Child Revision::" + currentX_RefChildRevision);
		log.info("Current X-Ref Child Revision:: " + currentX_RefChildRevision);
		updatedX_RefChildVersion = Integer.parseInt(currentX_RefChildRevision) + 1;
		updatedX_RefChildRevision = Integer.toString(updatedX_RefChildVersion);
		log.info("Updated X-Ref Revision" + updatedX_RefChildRevision);

	}

	public void clickOnAppletUploadLink() {

		doubleClickAndWait(FilesTab.lnk_PopUploadAppletUpload);

	}

	public void verifyAppletUploadPopup() {
		firstWindowHandle = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void performAppletUpload(String uploadType) throws IOException, InterruptedException {

		XRef_filePath = ResourceHandler.loadProperty("single.X-Ref.filepath");
		sysUtils.authenticateRemoteMachine(nodeIP);
		System.out.println("XRef_filePath:: " + XRef_filePath);
		try {

			if (uploadType.equalsIgnoreCase("XRefRevisionUploadWithCustomAttributes") || uploadType.equalsIgnoreCase("XRefUploadWithoutCustomAttributes"))
				autoItUtils.XRef_AppletUpload(XRef_filePath, updatedX_RefRev, uploadType, nodeIP);

			else
				autoItUtils.XRef_AppletUpload(XRef_filePath, newX_RefVer, uploadType, nodeIP);

		}
		catch (IOException e) {
			e.printStackTrace();
		}

		XRef_fileName = XRef_filePath.split("\\\\")[5];
		log.info("XRef_filePath:: " + XRef_fileName);

	}

	public void logInfo() {
		log.info("Test Automation step covered in function: HandleXRefAppletUpload.exe");

	}

	public void resetCurrentBrowser(String browserName, String dataCentre, String scenarioName) throws IOException, InterruptedException {

		tearDown();
		if (browserName.contains("IE") && scenarioName.contains("XRefRevisionUploadWithCustomAttributes")) {
			TestDriverFactory.getInstance().setUp(browserName);
			propertyUtils.setupEnvironmentTestProperties(dataCentre, System.getProperty("primary.username"));
			login(System.getProperty("primary.username"), System.getProperty("primary.password"));
			verifyLogin();

		}

		else if (browserName.contains("CHROME") && scenarioName.contains("XRefRevisionUploadWithCustomAttributes")) {
			TestDriverFactory.getInstance().setUp(ResourceHandler.loadProperty("browser"));
			propertyUtils.setupEnvironmentTestProperties(dataCentre, System.getProperty("primary.username"));
			login(System.getProperty("primary.username"), System.getProperty("primary.password"));
			verifyLogin();

		}

		else if (browserName.contains("IE") && scenarioName.contains("XRefUploadWithoutCustomAttributes")) {

			TestDriverFactory.getInstance().setUp(browserName);
			propertyUtils.setupEnvironmentTestProperties(dataCentre, System.getProperty("primary.username"));
			login(ResourceHandler.loadProperty("multi.project.user.username"), ResourceHandler.loadProperty("multi.project.user.password"));
			setProjectAll();

		}

		else {

			TestDriverFactory.getInstance().setUp(ResourceHandler.loadProperty("browser"));
			propertyUtils.setupEnvironmentTestProperties(dataCentre, System.getProperty("primary.username"));
			login(ResourceHandler.loadProperty("multi.project.user.username"), ResourceHandler.loadProperty("multi.project.user.password"));
			setProjectAll();

		}

	}

	public void validateXRefDocumentVersion(String versionInstance, String childInstance) throws IOException, InterruptedException {

		searchFiles(XRef_fileName);
		if (versionInstance.contains("UpdatedVersion"))
			Assert.assertTrue("Expected X-ReF Revision::" + updatedX_RefRev, getElementText(FilesTab.lnk_FileListingFirstVersion).contains(updatedX_RefRev));
		else
			Assert.assertTrue("Expected X-ReF Revision::" + updatedX_RefRev, getElementText(FilesTab.lnk_FileListingFirstVersion).contains(newX_RefVer));

		firstWindowHandle = getCurrentWindow();
		clickElementAndWait(FilesTab.lnk_DocListingFirstDocRef);
		waitForSwitchWindow(2);
		switchWindow();
		System.out.println("Switched Window Title: " + getWebDriver().getTitle());
		secondWindowHandle = getCurrentWindow();
		reloadPage();
		waitForCompletePageLoad();
		waitUntilElementIsClickable(FilesTab.lnk_FileViewLHAssociationBlock);
		clickElementAndWait(FilesTab.lnk_FileViewLHAssociationBlock);

	}

	public void validateXRefChildUpdatedRevision(String childRevision) {

		XRefChildElements = findElements(FilesTab.css_FileViewAssociationsXRefChilds);
		for (WebElement e : XRefChildElements)
			XRefChildFilesList.add(e.getText());
		System.out.println("X-Ref ChildElements:: " + XRefChildFilesList.size());
		systemFileList = sysUtils.getFileListOfSystemFolder(ResourceHandler.loadProperty("multi.X-REF.filespath"));
		Assert.assertTrue("CompareX_Ref Child List", systemFileList.containsAll(XRefChildFilesList));

		for (WebElement ele : XRefChildElements) {

			if (ele.getText().contains("001_Cover Sheet.pdf")) {
				ele.click();
				waitForSwitchWindow(3);
				switchToThirdWindow(firstWindowHandle, secondWindowHandle);
				waitForCompletePageLoad();
				ApplyFilterAndValidateChildXRefRevisions(childRevision);
				break;
			}

			else if (ele.getText().contains("2015-03-18 22_59_30-View Project Models.jpg")) {

				log.info("Pending Immplementation..");
				/*
				 * ele.click(); waitForSwitchWindow(3); switchToThirdWindow(firstWindowHandle, secondWindowHandle); waitForCompletePageLoad(); ApplyFilterAndValidateChildXRefRevisions("oldRevision"); break;
				 */
			}

			else
				log.info("Child not Found");

		}

	}

	public void ApplyFilterAndValidateChildXRefRevisions(String childRevision) {

		System.out.println("Switched Window Title: " + getWebDriver().getTitle());
		waitUntilElementIsDisplayed(By.cssSelector("div[id='fileAllHistorySection'] select[id='xrefTypeDDL']"));
		selectByVisibleText(By.cssSelector("div[id='fileAllHistorySection'] select[id='xrefTypeDDL']"), "Revision Uploaded");
		selectByIndex(By.cssSelector("div[id='fileAllHistorySection'] select[id='xrefhistoryRevDDl']"), 1);
		waitForCompletePageLoad();
		System.out.println("Child Revision: " + getElementText(By.cssSelector("div[class*='historyRow'] :nth-child(2) span[title*='Revision']")));

		if (childRevision.contains("UpdatedRevision")) {
			Assert.assertTrue(getElementText(By.cssSelector("div[class*='historyRow'] :nth-child(2) span[title*='Revision']")).contains(updatedX_RefChildRevision));

			// closeCurrentWindow();
			// switchPreviousWindow(secondWindowHandle);

		}

		else if (childRevision.contains("newRevision"))
			Assert.assertTrue(getElementText(By.cssSelector("div[class*='historyRow'] :nth-child(2) span[title*='Revision']")).contains(newX_RefChildRevision));

		else
			Assert.assertTrue(getElementText(By.cssSelector("div[class*='historyRow'] :nth-child(2) span[title*='Revision']")).contains(currentX_RefChildRevision));

	}

	public void createProjectDetails(String projectName) throws InterruptedException {

		WorkspaceName = projectName + dateUtils.getEpoch();
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, WorkspaceName);

		if (getCount(ProjectsTab.txt_PopCreateProjectClientInput) > 0) {
			log.info("found client input");

			if (ResourceHandler.loadProperty("application.url").contains("www.asite.com") || ResourceHandler.loadProperty("application.url").contains("wwwqa.asite.com"))
				findElement(ProjectsTab.txt_PopCreateProjectClientInput).sendKeys(System.getProperty("primary.user.org").replace(".", ""));
			else
				findElement(ProjectsTab.txt_PopCreateProjectClientInput).sendKeys(System.getProperty("primary.user.org"));

			waitForCompletePageLoad();
			clickElementAndWait(ProjectsTab.sel_CloneProjectClientNameFirstResult);
		}
	}

	public void geographyDropdownSelect(String DC) throws InterruptedException {
		selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, DC);
	}

	public void breakXRefInstancesSharing() {

		clickElementAndWait(ProjectsTab.lnk_PopCreateProjectSettings);
		if (!isSelected(By.cssSelector("div[id='project-setting'] input[id='noXref']")))
			clickElement(By.cssSelector("div[id='project-setting'] input[id='noXref']"));

	}

	public void clickOnCreate() throws InterruptedException {
		clickElementAndWait(ProjectsTab.btn_PopCreateProjectSave);
	}

	public void verifyProjectName() throws InterruptedException {
		searchProjects(WorkspaceName);
		Assert.assertEquals(getElementText(ProjectsTab.lnk_ProjectsFirstProject), WorkspaceName);
	}

	public void contextclickOnProjectName() {

		log.info("Project Name" + WorkspaceName);
		waitForCompletePageLoad();
		contextClickWithText(WorkspaceName);
		waitUntilElementIsDisplayed(FilesTab.opt_ProjectContextClickNew);

	}

	public void focusOnWorkspace() {

		log.info("ProjectName::" + WorkspaceName);
		clickElementWithText(WorkspaceName);

	}
}
