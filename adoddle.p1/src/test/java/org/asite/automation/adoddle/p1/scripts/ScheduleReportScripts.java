package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.asite.automation.CommonLocators.AdoddleClassicLocators.ClassicLocators;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleReportsLocators.ReportsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScheduleReportScripts extends AdoddleCommonAppMethods {
	private String				parentHandle1;
	private int					BeforeFilesIncompleteActionCount, totalCount;
	private String				projectName, layout, actionName, actionDays, loggedInUser, userOrg;
	private Actions				action;
	private double				localFileSize;
	private String				preViewFile;
	private static String		epoch			= new DateUtils().getEpoch();
	private static String		reportType		= null;
	public static String		reportName		= null;
	private String				fileName		= null;
	private HSSFWorkbook		wbXLS;
	public static List<String>	layoutFields;
	private HSSFSheet			sheetXLS;
	private XSSFSheet			sheetXLSX;
	private XSSFWorkbook		wbXLSX;
	private List<WebElement>	arrowExpandList;
	public static Logger		log				= AutomationLogger.getInstance().getLogger(ScheduleReportScripts.class);
	private String				adhocReport		= "Adhoc_Rpt_Classic_Comments";
	private String				classicReport	= "Std_Rpt_Classic_Actions";

	public void getTotalActionCount() {
		mouseHover(FilesTab.lbl_FilesIncompleteActionsCount);
		BeforeFilesIncompleteActionCount = Integer.parseInt(findElement(FilesTab.lbl_FilesIncompleteActionsCount).getAttribute("title").split(": ")[1]);
		log.info("BeforeFilesIncompleteActionCount :" + BeforeFilesIncompleteActionCount);
	}

	public void switchIntoClassicView() {
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_ClassicView);
		clickElementAndWait(LandingPage.lnk_ClassicView);
		waitForCompletePageLoad();
	}

	public void clickClassicReportingTab() {
		clickElementAndWait(ClassicLocators.tab_Reporting);
	}

	public void verifyReportsPage() {
		switchIntoReportsTab();
		waitUntilElementIsDisplayed(ClassicLocators.lbl_ReportsPage);
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ClassicLocators.lbl_ReportsPage, false), isDisplayed(ClassicLocators.lbl_ReportsPage));
	}

	public void clickOnCreateReport() {
		waitForCompletePageLoad();
		clickElementAndWait(ClassicLocators.img_ReportsPageCreateReportImageIcon);
		switchToSecondWindow();
	}

	public void verifySearchReportsPage(String pageTitle) {
		maximizeWindow();
		acceptAlert(5);
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(ClassicLocators.lbl_CreateReportPageTitle).contains(pageTitle));
	}

	public void selectReportAndClickOnCreateReport(String reportType) {
		acceptAlert(5);
		reloadPage();
		sendKeys(ClassicLocators.txt_SearchReportTemplate, reportType);
		clickElementAndWaitForElement(ClassicLocators.btn_ReportsPageSearchButton, ClassicLocators.sel_ReportTempleteRadioButton);

		if (!isSelected(ClassicLocators.sel_ReportTempleteRadioButton)) {
			clickElementAndWait(ClassicLocators.sel_ReportTempleteRadioButton);
		}
		clickElementAndWait(ClassicLocators.btn_ReportsPageCreateReportButton);
		waitForCompletePageLoad();
	}

	public void enterReportNameAndSelectWorkspace(String reportType, String project) throws InterruptedException {
		projectName = project;

		if (reportType.contains("Classic_Actions"))
			reportName = "Std_Rpt_Classic_Actions" + epoch;
		else if (reportType.contains("Adoddle_Actions"))
			reportName = "Std_Rpt_Adoddle_Actions" + epoch;
		else
			reportName = "Auto_Report" + dateUtils.getEpoch();

		clear(ClassicLocators.txt_ReportNameInput);
		if (reportType.equalsIgnoreCase("classic report"))
			reportName = classicReport + epoch;
		else if (reportType.equalsIgnoreCase("standard report"))
			reportName = new CreateReportScripts().getReportName();
		sendKeys(ClassicLocators.txt_ReportNameInput, reportName);
		collectionDataMap.put("Report Title", reportName);
		if (!reportType.contains("Standard Report")) {
			clickElementAndWaitForElement(ClassicLocators.drp_EditReportSelectWorkspaceDropdown, ClassicLocators.drp_EditReportSelectWorkspaceDropdownListOption);
			selectByVisibleText(ClassicLocators.drp_EditReportSelectWorkspaceDropdown, project);
		}
	}

	public void selectFrequency(String frequencyType, String firstMonthRadioButton) {
		clickElementAndWait(ClassicLocators.drp_EditReportFrequencyDropdown);
		selectByVisibleText(ClassicLocators.drp_EditReportFrequencyDropdown, frequencyType);
		waitUntilElementIsDisplayed(ClassicLocators.sel_FirstDateOfMonthRadioButton);
		if (!isSelected(ClassicLocators.sel_FirstDateOfMonthRadioButton))
			clickElementAndWait(ClassicLocators.sel_FirstDateOfMonthRadioButton);
	}

	public void selectTime(String hour, String mins) {
		clickElementAndWait(ClassicLocators.drp_EditReportFrequencyHourDropdown);
		selectByVisibleText(ClassicLocators.drp_EditReportFrequencyHourDropdown, hour);
		clickElementAndWait(ClassicLocators.drp_EditReportFrequencyMinuteDropdown);
		selectByVisibleText(ClassicLocators.drp_EditReportFrequencyMinuteDropdown, mins);
	}

	public void clickOnFolderLink(String folder) {
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		clickLinkWithText(folder);
		waitUntilElementIsDisplayed(ClassicLocators.drp_DeliveryScheduleAreaSelectWorkspaceDropdown);
	}

	public void selectWorkspace(String project) {
		clickElementAndWaitForElement(ClassicLocators.drp_DeliveryScheduleAreaSelectWorkspaceDropdown, ClassicLocators.drp_DeliveryScheduleAreaSelectWorkspaceDropdownListOption);
		selectByVisibleText(ClassicLocators.drp_DeliveryScheduleAreaSelectWorkspaceDropdown, project + " " + AdoddleCommonStringPool.STRING_DOCUMENT_WORKFLOW_FM);
	}

	public void verifyFolderTreeStructure() {
		waitUntilElementIsDisplayed(ClassicLocators.ele_EditReportFolderTreeStructure);
		Assert.assertTrue(isDisplayed(ClassicLocators.ele_EditReportFolderTreeStructure));
	}

	public void clickOnReportsFolderAndSelectFolder() {
		clickElementAndWaitForElement(ClassicLocators.lnk_FolderTreeStructureReportsFolder, ClassicLocators.btn_FolderTreeStructureSelectFolderButton);
		clickElementAndWaitForElement(ClassicLocators.btn_FolderTreeStructureSelectFolderButton, ClassicLocators.lbl_EditReportDocumentDetails);
	}

	public void verifyDocumentDetailsArea(String documentDetailsArea) {
		Assert.assertTrue(getElementText(ClassicLocators.lbl_EditReportDocumentDetails).contains(documentDetailsArea));
	}

	public void selectPoiAndStatus() {
		clickElementAndWait(ClassicLocators.drp_EditReportPOIdropdown);
		selectByIndex(ClassicLocators.drp_EditReportPOIdropdown, 1);
		clickElementAndWait(ClassicLocators.drp_EditReportStatusDropdown);
		selectByIndex(ClassicLocators.drp_EditReportStatusDropdown, 1);
	}

	public void clickOnNext() {
		clickElementAndWait(ClassicLocators.btn_EditReportNextButton);
	}

	public void verifyDistributeArea(String distributeArea) {
		waitUntilElementIsDisplayed(ClassicLocators.lbl_EditReportDistribute);
		Assert.assertTrue(getElementText(ClassicLocators.lbl_EditReportDistribute).contains(distributeArea));
	}

	public void clickOnAddToDistributionList() {
		clickElementAndWait(ClassicLocators.btn_AddToDistributionListButton);
	}

	public void selectActionAndNumberOfDays(String actionType, String days) {
		actionName = actionType;
		actionDays = days;
		clickElementAndWait(ClassicLocators.drp_EditReportActionRequiredDropdown);
		selectByVisibleText(ClassicLocators.drp_EditReportActionRequiredDropdown, actionType);

		clickElementAndWait(ClassicLocators.drp_EditReportActionDaysDropdown);
		selectByVisibleText(ClassicLocators.drp_EditReportActionDaysDropdown, days);
	}

	public void clickOnDistribute() {
		clickElementAndWait(ClassicLocators.btn_EditReportDistributeButton);
	}

	public void verifyReportRecipientsList(String reportRecipientsList) {
		Assert.assertTrue(getElementText(ClassicLocators.lbl_EditReportRecipientsArea).contains(reportRecipientsList));
	}

	public void clickOnSave() {
		waitForCompletePageLoad();
		if (isDisplayed(ClassicLocators.btn_EditReportSaveButton)) {
			log.info("Classic Button of Save");
			clickElement(ClassicLocators.btn_EditReportSaveButton);
			acceptAlert(5);
		}
		else if (isDisplayed(ReportsTab.btn_EditReportSaveButton)) {
			log.info("Adoddle Button of Save");
			clickElementAndWait(ReportsTab.btn_EditReportSaveButton);
		}
		else {
			log.error("Save Button Not Displayed");
			Assert.assertTrue(false);
		}
	}

	public void checkSendNowAndclickOnSaveAndClose(String reportType) {
		acceptAlert(5);
		waitUntilElementIsClickable(ClassicLocators.chk_EditReportSendNowCheckbox);
		clickElementAndWait(ClassicLocators.chk_EditReportSendNowCheckbox);
		if (reportType.contains("Schedule Report")) {
			waitUntilElementIsDisplayed(ClassicLocators.drp_EditReportSelectWorkspaceDropdownListOption);
			selectByVisibleText(ClassicLocators.drp_EditReportSelectWorkspaceDropdown, projectName);
		}
		clickOnSaveAndClose();
	}

	public void gotoPreviousWindow() {
		switchPreviousWindow(parentHandle1);
	}

	public void verifyCreatedReportInReportsPage(String appView) throws InterruptedException {
		if (appView.equalsIgnoreCase("classic")) {
			reloadPage();
			switchIntoReportsTab();
			for (int index = 0; index < 3; index++) {
				sendKeys(ClassicLocators.txt_ReportsNameInput, reportName);
				clickElementAndWait(ClassicLocators.btn_ReportsPageSearchButton);
				waitUtils.waitInterval(2);
				if (isDisplayed(ClassicLocators.lnk_ReportsPageFirstReport))
					break;
				else
					waitUtils.waitInterval(5);
			}
			Assert.assertTrue(getElementText(ClassicLocators.lnk_ReportsPageFirstReport).contains(reportName));
			log.info("Report name verified successfully on classic listing");
		}
		else {
			log.info("Verification in adoddle pending...");
			waitUntilElementIsInvisible(GlobalPageElements.ele_Loader);
			clickElementAndWaitForElement(ReportsTab.lnk_EditAndScheduleReports, ReportsTab.txt_SearchLegacyReportInput);
			sendKeys(ReportsTab.txt_SearchLegacyReportInput, reportName);
			sendKeys(ReportsTab.txt_SearchLegacyReportInput, Keys.ENTER);
			AutomationAssert.verifyTrue(getElementText(ReportsTab.lnk_FirstLegacyReport).equalsIgnoreCase(reportName));
		}
	}

	public void gotoReportsFolder(String project, String folder) {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_WorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteNoResize);
		clickElementAndWait(ClassicLocators.lnk_MyHome);

		clickLinkWithText(project);
		switchMultiFrames();
		clickLinkWithTitle(folder);
	}

	public void verifyCreatedReportInClassic() throws InterruptedException {
		switchMultiFrames();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkspaceFrame);

		for (int index = 0; index < 60; index++) {
			try {
				searchFilesUsingDocRefInClassic(reportName);
				waitUtils.waitInterval(2);
				if ((getCount(ClassicLocators.lnk_DocListingFirstFileDocRef) > 0) && (getElementText(ClassicLocators.lnk_DocListingFirstFileAction).contains(actionName)))
					break;
				else
					waitUtils.waitInterval(10);
			}
			catch (Throwable t) {
				log.info("Waiting for 1 min for Report publishing in Classic");
			}
		}

		Assert.assertTrue(getElementText(ClassicLocators.lnk_DocListingFirstFileDocRef).contains(reportName));
		Assert.assertTrue(getElementText(ClassicLocators.lnk_DocListingFirstFileAction).contains(actionName));
		Assert.assertTrue(getElementText(ClassicLocators.ele_DocListingFirstFileActionDays).contains(actionDays));
	}

	public void switchIntoswitchDefaultView() {
		/*switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		mouseHoverAndClickElement(ClassicLocators.lnk_SettingsMenu, ClassicLocators.lnk_AdoddleView);
		waitForCompletePageLoad();
		reloadPage();
		waitForCompletePageLoad();*/
		navigateURL(adoddleHomeURL);
	}

	public void clickOnReportsFolder(String project, String folder) {
		clickElementWithText(project);
		clickElementAndWait(By.xpath(".//div[contains(@class,'selected')]//following::div[@style='display: block;']//span[text()='" + folder + "']"));
	}

	public void verifyCreatedReportInAdoddle(String actionType) {
		for (int index = 0; index < 60; index++) {
			try {
				searchFiles(reportName);
				if (getCount(FilesTab.css_NumberOfFiles) > 0)
					break;
				else
					waitUtils.waitInterval(10);
				searchFiles("");
			}
			catch (Throwable t) {
				log.info("Waiting for 1 min for Report publishing in Adoddle");
			}
		}
		AutomationAssert.verifyTrue("Report was not scheduled successfully", getElementText(FilesTab.lnk_FileName).contains(reportName));
		AutomationAssert.verifyTrue("User did not recieve valid report action", getElementText(FilesTab.lnk_FilesFirstAction).contains(actionName));
		AutomationAssert.verifyTrue("User did not receive valid action days", getElementText(FilesTab.ele_FilesFirstActionDays).contains(actionDays));
	}

	public void verifyIncompleteActionCount() throws InterruptedException {
		navigateTab(LandingPage.lnk_Files);
		mouseHover(FilesTab.lbl_FilesIncompleteActionsCount);
		log.info("Count after Scheduling Report: " + Integer.parseInt(findElement(FilesTab.lbl_FilesIncompleteActionsCount).getAttribute("title").split(": ")[1]));
	}

	public void clickOnSaveAndClose() {
		waitForCompletePageLoad();
		if (isDisplayed(ClassicLocators.btn_EditReportSaveAndCloseButton)) {
			log.info("Classic Button of Save & Close");
			clickElementAndWait(ClassicLocators.btn_EditReportSaveAndCloseButton);
		}
		else if (isDisplayed(ReportsTab.btn_EditReportSaveAndCloseButton)) {
			log.info("Adoddle Button of Save & Close");
			clickElementAndWait(ReportsTab.btn_EditReportSaveAndCloseButton);
		}
		else {
			log.info("Save & Close Button not Displayed");
			Assert.assertTrue(false);
		}
		acceptAlert(5);
		try {
			waitUtils.waitInterval(2);
		}
		catch (Throwable t) {
			log.info("WaitInterval Failed");
		}
	}

	public void gotoHomeAndSwitchIntoAdoddleView() {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_WorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteNoResize);
		clickElementAndWait(ClassicLocators.lnk_MyHome);
		switchDefault();
		waitForCompletePageLoad();
		mouseHoverAndClickElement(ClassicLocators.lnk_SettingsMenu, ClassicLocators.lnk_AdoddleView);
		waitForCompletePageLoad();
		reloadPage();
		waitForCompletePageLoad();
	}

	public void clickOnEditAndScheduleLHButton() {
		switchDefault();
		waitUntilElementIsDisplayed(ReportsTab.lnk_EditAndScheduleReports);
		waitUntilElementIsClickable(ReportsTab.lnk_EditAndScheduleReports);
		clickElementAndWait(ReportsTab.lnk_EditAndScheduleReports);
	}

	public void verifyEditAndScheduleReportsPage(String EditScheduleReportsPage) {
		Assert.assertTrue(getElementText(ReportsTab.lbl_EditAndSchedulePage).contains(EditScheduleReportsPage));
	}

	@SuppressWarnings("static-access")
	public void searchReportAndClickOnEditReport(String reportType) throws InterruptedException {
		this.reportType = reportType;
		waitUntilElementIsDisplayed(ReportsTab.txt_SearchLegacyReportInput);
		if (reportType.equalsIgnoreCase("standard"))
			sendKeys(ReportsTab.txt_SearchLegacyReportInput, new CreateReportScripts().getReportName());
		sendKeys(ReportsTab.txt_SearchLegacyReportInput, Keys.ENTER);
		log.info("Searching Report :" + new CreateReportScripts().getReportName());
		Assert.assertTrue(isDisplayed(ReportsTab.lnk_FirstLegacyReport));
		waitUntilElementIsClickable(ReportsTab.lnk_FirstLegacyEditReport);
		clickElementAndWait(ReportsTab.lnk_FirstLegacyEditReport);
		switchToSecondWindow();
	}

	/***************************************** Classic Adhoc Report ************************************************/

	public void clickOnWorkspace(String project) {
		projectName = project;
		clickLinkWithText(project);
	}

	public void selectReportsDropdownOption(String newReport) {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);

		clickElementAndWait(ClassicLocators.drp_ReportsDropdown);
		selectByVisibleText(ClassicLocators.drp_ReportsDropdown, newReport);

		switchToSecondWindow();
	}

	public void enterReportNameAndSelectFolder(String reportType, String asiteView) {
		if (reportType.equalsIgnoreCase("adhoc"))
			reportName = adhocReport + epoch;

		sendKeys(ReportsTab.txt_ReportNameInput, reportName);
		if (!asiteView.contains("Adoddle")) {
			clickElementAndWait(ClassicLocators.ele_ExpressReportFolder);
		}
		else {

			arrowExpandList = findElements(ReportsTab.css_ArrowExpandList);
			for (WebElement e : arrowExpandList) {
				if (!isDisplayed(ReportsTab.ele_ReportFolderSelection))
					e.click();
				else {
					clickElementAndWait(ReportsTab.ele_ReportFolderSelection);
					break;
				}
			}
		}
	}

	public void selectMultipleColumns() {
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ClassicLocators.ele_ExpressReportLayoutField);

		List<WebElement> Columns = findElements(ReportsTab.css_ExpressReportFieldList);
		layout = "LastLogin,OrganisationName,Telephone,User_FName,User_LName,WorkspaceName";
		String layoutField[] = layout.split(",");

		for (WebElement e : Columns) {
			for (String field : layoutField) {
				if (e.getText().equalsIgnoreCase(field)) {
					action = new Actions(getWebDriver());
					action.moveToElement(e).doubleClick().perform();
				}
			}
		}
	}

	public void selectMultipleColumns(String reportType) {
		waitForCompletePageLoad();
		if (reportType.contains("Files"))
			waitUntilElementIsDisplayed(ReportsTab.ele_StandardReportLayoutField);

		List<WebElement> Columns = findElements(ReportsTab.css_ExpressReportFieldList);
		layoutFields = Arrays.asList("Doc Ref", "Document Status", "File Name", "Purpose Of Issue", "Title");

		for (WebElement e : Columns) {
			for (String field : layoutFields) {
				if (e.getText().equalsIgnoreCase(field)) {
					action = new Actions(getWebDriver());
					action.moveToElement(e).doubleClick().perform();
				}
			}
		}
	}

	public void verifyReportExecution() {
		waitUntilElementIsDisplayed(ReportsTab.pop_ReportsTabProcessDialog);
		waitUntilElementIsInvisible(ReportsTab.pop_ReportsTabProcessDialog);
	}

	public void verifyReportInLocalDirectory(String reportType) throws IOException, InterruptedException {
		if (reportType.equalsIgnoreCase("adhoc"))
			reportName = adhocReport + epoch;

		fileName = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path").trim() + reportName + ".xls";
		File report = new File(fileName);
		log.info("Download report name: " + report);
		sysUtils.authenticateRemoteMachine(nodeIP);
		String renamedFileName = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + reportName + ".xls";
		File renamedReport = new File(renamedFileName);
		report.renameTo(renamedReport);
		waitUntilFileIsDownloaded(renamedReport);
		collectionDataMap.put("Downloaded Report", renamedReport.toString());
		Assert.assertTrue(renamedReport.exists());
	}

	public void closeCreateReportPage() {
		waitForCompletePageLoad();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
	}

	public void clickOnMyHomeAndgotoReportingTab() {
		reloadPage();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		clickElementAndWait(ClassicLocators.lnk_MyHome);
		switchDefault();
		clickClassicReportingTab();
	}

	public void clickOnEditAndScheduleReport() throws InterruptedException {
		clickElementAndWait(ClassicLocators.img_ReportsTabFirstReportEditAndSchedule);
		switchToSecondWindow();
	}

	public void switchIntoReportsTab() {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_WorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_FramesetListingController);
	}

	public void switchToSecondWindow() {
		parentHandle1 = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	/********************************************* Preview Report *********************************************/

	public void verifyReportPreview(String asiteView, String reportExtension, String previewType) throws InterruptedException, IOException {
		if (asiteView.contains("Adoddle")) {

			if (previewType.contains("Edit Page")) {
				editPagePreviewReport(asiteView, reportExtension);
			}
			else {
				listingPagePreviewReport(asiteView, reportExtension);
			}
		}
		else {
			if (previewType.contains("Edit Page")) {
				editPagePreviewReport(asiteView, reportExtension);
				closeCreateReportPage();
			}
			else {
				listingPagePreviewReport(asiteView, reportExtension);
			}
		}
	}

	public void editPagePreviewReport(String asiteView, String reportExtension) throws IOException, InterruptedException {
		if (asiteView.contains("Adoddle")) {
			clickElementAndWait(ReportsTab.btn_EditAndScheduleReportPreviewButton);
		}
		else {
			clickOnEditAndScheduleReport();
			verifySearchReportsPage("Edit And Schedule");
			clickElementAndWait(ClassicLocators.btn_EditAndScheduleReportPreviewButton);
		}
		verifyDownloadedReport(reportExtension, asiteView);
		clickElementAndWaitForInvisibilityOfElement(ReportsTab.btn_PreviewToReturnPreviousScreenButton, ReportsTab.btn_PreviewToReturnPreviousScreenButton);
	}

	public void listingPagePreviewReport(String asiteView, String reportExtension) throws InterruptedException, IOException {
		int counter = 0;
		if (asiteView.contains("Adoddle")) {
			for (int index = 0; index < 3; index++) {
				waitUntilElementIsDisplayed(ReportsTab.txt_SearchLegacyReportInput);
				sendKeys(ReportsTab.txt_SearchLegacyReportInput, reportName);
				sendKeys(ReportsTab.txt_SearchLegacyReportInput, Keys.ENTER);

				if (isDisplayed(ReportsTab.lnk_FirstLegacyReport))
					break;
				else {
					sendKeys(ReportsTab.txt_SearchLegacyReportInput, "");
					sendKeys(ReportsTab.txt_SearchLegacyReportInput, Keys.ENTER);
					waitUtils.waitInterval(5);
				}
			}
			Assert.assertTrue(getElementText(ReportsTab.lnk_FirstLegacyReport).contains(reportName));

			clickElementAndWaitForElement(ReportsTab.lnk_FirstLegacyReport, ReportsTab.btn_PopEditAndScheduleReportDownloadButton);
			waitForCompletePageLoad();
			waitUtils.waitInterval(5);

			while (isDisplayed(ReportsTab.btn_PopEditAndScheduleReportDownloadButton)) {
				waitUtils.waitInterval(1);
				try {
					clickElementAndWaitForInvisibilityOfElement(ReportsTab.btn_PopEditAndScheduleReportDownloadButton, ReportsTab.btn_PopEditAndScheduleReportDownloadButton, 3);
				}
				catch (Throwable t) {
					counter++;
					if (counter == 5)
						break;
				}

			}
		}
		else {
			classicReport = getElementText(ClassicLocators.lnk_ReportsPageFirstReport);
			clickElementAndWait(ClassicLocators.lnk_ReportsPageFirstReport);
		}
		verifyDownloadedReport(reportExtension, asiteView);
	}

	private List<String>	previewReportExpectedList	= Arrays.asList("TC", "Test", "Big", "Bad");

	public void verifyDownloadedReport(String reportExtension, String view) throws IOException, InterruptedException {
		File file1 = null;
		sysUtils.authenticateRemoteMachine(nodeIP);
		String defaultDownloadPath = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path").trim();
		if(view.equalsIgnoreCase("adoddle")) {
			log.info("Other script Report name : "+new CreateReportScripts().getReportName());
			waitForFileWithPartialName(new CreateReportScripts().getReportName(), defaultDownloadPath);
			for (String s : sysUtils.getFileListOfSystemFolder(defaultDownloadPath)) {
				if (s.contains(new CreateReportScripts().getReportName())) {
					log.info("Default downloaded report file found : " + s);
					file1 = new File(defaultDownloadPath + s);
					break;
				} else {
					log.info("Searching downloaded report file in default download folder :" + s);
				}
			}
		}
		else {
			log.info("Classic Report name :"+reportName);
			waitForFileWithPartialName(reportName, defaultDownloadPath);
			for (String s : sysUtils.getFileListOfSystemFolder(defaultDownloadPath)) {
				if (s.contains(reportName)) {
					file1 = new File(defaultDownloadPath + s.toString().replace(".crdownload", ""));
					waitUntilFileIsDownloaded(file1);
					log.info("Default downloaded report file found : " + file1.toString());
					break;
				} else {
					log.info("Searching downloaded report file in default download folder :" + s);
				}
			}
		}

		AutomationAssert.verifyTrue("Report file was not successfully downloaded in default location",file1 != null);

		String previewFolder = sysUtils.createDirectory(nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "PreviewReportFolder" + dateUtils.getEpoch());
		preViewFile = previewFolder + "\\PreviewReport" + epoch + AdoddleCommonStringPool.DOT_STRING + reportExtension;
		File report = new File(preViewFile);


		Files.move(file1, report);
		log.info("Download report name: " + report);
		//autoItUtils.downloadAutoIt(report.toString(), nodeIP);
		waitUntilFileIsDownloaded(report);
		Assert.assertTrue("Report was not found :" + report.toString(), report.exists());

		localFileSize = sysUtils.getFileSize(preViewFile);
		log.info("localFileSize :" + localFileSize);
		Assert.assertTrue("localFileSize :" + localFileSize, localFileSize > 1);

		if (reportExtension.equalsIgnoreCase("PDF")) {
			List<String> previewReportActualList = new ArrayList<String>();
			for (String pdfContent : pdfUtils.extractPDFText(report.toString()).get(0).replace("\n", ",").split(" ")) {
				if (pdfContent.contains(",")) {
					for (String subVal : pdfContent.split(","))
						previewReportActualList.add(subVal.toString().trim());
				}
				else
					previewReportActualList.add(pdfContent.toString().trim());
			}
			previewReportActualList.remove(0);
			Assert.assertTrue(previewReportActualList.containsAll(previewReportExpectedList));
		}
	}

	/******************************* Standard Report with Project Criteria *******************************/

	public void verifyDownloadedReportContent() throws IOException {
		try {
			POIFSFileSystem file = new POIFSFileSystem(new FileInputStream(fileName));
			wbXLS = new HSSFWorkbook(file);
			sheetXLS = wbXLS.getSheetAt(0);
			String layoutField[] = layout.split(",");
			if (sheetXLS != null) {
				String xlsreportName = sheetXLS.getRow(0).getCell(0).getStringCellValue();
				log.info("xlsreportName :" + xlsreportName);
				Assert.assertTrue("Actual :" + xlsreportName + " :: " + "Expected :" + reportName, reportName.equalsIgnoreCase(xlsreportName));

				int j = 0;
				for (int i = 0; i < sheetXLS.getRow(2).getLastCellNum(); i++) {

					String actualVal = sheetXLS.getRow(2).getCell(i).getStringCellValue();
					String expectedVal = layoutField[j];
					log.info("Actual Report Cell Value : " + actualVal);
					log.info("Expected Report Cell Value : " + expectedVal);
					Assert.assertTrue(actualVal.equalsIgnoreCase(expectedVal));
					j++;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getLoggedInUserAndOrg() {
		waitForCompletePageLoad();
		loggedInUser = findElement(LandingPage.ele_LoggedInUserProfile).getAttribute("title").split(", ")[0];
		log.info("loggedInUser :" + loggedInUser);
		userOrg = findElement(LandingPage.ele_LoggedInUserProfile).getAttribute("title").split(", ")[1];
		log.info("User organization :" + userOrg);
	}

	public void selectProjectsInReportCriteriaField(String project1, String project2) {
		clickElementAndWaitForElement(ReportsTab.sel_PopEditAndScheduleReportSelectProjectRadioButton, ReportsTab.drp_PopEditAndScheduleReportCriteriaDropdown);
		clickElementAndWaitForElement(ReportsTab.drp_PopEditAndScheduleReportCriteriaDropdown, ReportsTab.ele_PopEditAndScheduleReportCriteriaListFrame);
		for (String selectedProject : Arrays.asList(project1, project2)) {
			for (WebElement projectCheckbox : findElements(ReportsTab.css_PopEditAndScheduleReportCriteriaDropdownList)) {
				if (projectCheckbox.getText().equalsIgnoreCase(selectedProject)) {
					clickElementAndWait(projectCheckbox);
					break;
				}
			}
		}
		clickElementAndWait(ReportsTab.drp_PopEditAndScheduleReportCriteriaDropdown);
	}

	public void selectOrgAndUser() {
		waitUntilElementIsDisplayed(ClassicLocators.lbl_SelectOrganisationDropdownFirstOption);
		waitUntilDropdownContainsValue(ClassicLocators.drp_SelectOrganisationDropdown, userOrg, 60);
		selectByVisibleText(ClassicLocators.drp_SelectOrganisationDropdown, userOrg);
		waitUntilElementIsDisplayed(ClassicLocators.lbl_SelectUserDropdownFirstOption);
		waitUntilDropdownContainsValue(ClassicLocators.drp_SelectUserDropdown, loggedInUser, 60);
		selectByVisibleText(ClassicLocators.drp_SelectUserDropdown, loggedInUser);
	}

	public void createFilter(String filter, String subFilter) {
		waitForCompletePageLoad();
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
		waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);

		for (WebElement filterVal : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
			if (filterVal.getAttribute("title").equalsIgnoreCase(subFilter)) {
				if (!filterVal.isSelected())
					filterVal.click();
				break;
			}
			else
				continue;
		}
		waitForCompletePageLoad();
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + subFilter + "']//button[@filterkey='" + subFilter + "']"));
		waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);

		for (WebElement filterVal : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
			if (!filterVal.findElement(By.cssSelector("input")).isSelected())
				filterVal.findElement(By.cssSelector("input")).click();
		}
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + subFilter + "']//button[@filterkey='" + subFilter + "']"));
	}

	public void getTotalFilesCountOfReportCriteriaProjects(String project1, String project2) {
		totalCount = 0;
		for (String project : Arrays.asList(project1, project2)) {
			clickElementWithText(project);
			totalCount += Integer.parseInt(getTotalFileCount());
		}
		log.info("Total report file count :" + totalCount);
	}

	public String getTotalFileCount() {
		String folderFiles;
		waitUntilElementIsDisplayed(FilesTab.lbl_FilesTabFilesCount);
		String fileCount[] = getElementText(FilesTab.lbl_FilesTabFilesCount).split("of ");
		folderFiles = fileCount[1].replace(")", "");
		log.info("folderFiles :" + folderFiles);
		return folderFiles;
	}

	public void downloadAndVerifyPreviewOfSelectedProjectCriteria(String asiteView, String reportExtension) throws InterruptedException, IOException {
		acceptAlert(5);
		waitForCompletePageLoad();
		waitUntilElementIsClickable(ClassicLocators.chk_EditReportSendNowCheckbox);
		waitForCompletePageLoad();
		waitUtils.waitInterval(5);
		mouseHover(ReportsTab.btn_EditAndScheduleReportPreviewButton);
		waitUtils.waitInterval(2);
		verifyReportPreview(asiteView, reportExtension, "Edit Page");

		int downloadedListingCount = getFileListingCount(preViewFile);
		log.info("downloadedListingCount : " + downloadedListingCount);
		log.info("totalCount : " + totalCount);
		log.info("Result : " + (downloadedListingCount - 2));
		Assert.assertTrue((totalCount) == (downloadedListingCount - 2));
	}

	public int getFileListingCount(String filePath) throws IOException {
		FileInputStream file = new FileInputStream(new File(filePath));
		wbXLSX = new XSSFWorkbook(file);
		sheetXLSX = wbXLSX.getSheetAt(0);
		log.info("Report row count : " + sheetXLSX.getLastRowNum());
		return sheetXLSX.getLastRowNum();
	}

	public void selectDeliveryScheduleDropdown(String statusVal) {
		maximizeWindow();
		waitForCompletePageLoad();
		try {
			waitUntilElementIsDisplayed(ReportsTab.drp_DeliveryScheduleDropdown);
			selectByVisibleText(ReportsTab.drp_DeliveryScheduleDropdown, statusVal);
		}
		catch (Throwable t) {
			log.info(": Report Not Scheduled :");
		}
		waitForCompletePageLoad();
	}

	@SuppressWarnings("static-access")
	public void deactivateScheduleReport(String statusVal) {
		try {
			clickOnEditAndScheduleLHButton();
			searchReportAndClickOnEditReport(this.reportType);
			selectDeliveryScheduleDropdown(statusVal);
			clickOnSaveAndClose();
			gotoPreviousWindow();
		}
		catch (Throwable t) {
			log.info(": Scheduled Report Deactivation Failed :");
		}
	}
}
