package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleReportsLocators.ReportsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateReportScripts extends AdoddleCommonAppMethods {

	private List<WebElement>	arrowExpandList;
	private Actions				action;
	private static String		reportName		= null;
	private String				expressReport	= "Exp_Rpt_Adoddle_Comments";
	private String				standardReport	= "Std_Rpt_Adoddle_Files";
	private String				fileName		= null;
	private File				file2			= null;
	public static List<String>	layoutFields;
	private XSSFWorkbook		wbXLSX;
	private XSSFSheet			sheetXLSX;
	public static Logger		log				= AutomationLogger.getInstance().getLogger(CreateReportScripts.class);

	@SuppressWarnings("static-access")
	public String getReportName() {
		log.info("Report name for CreateReportScripts class : "+this.reportName);
		return this.reportName;
	}

	@SuppressWarnings("static-access")
	public void setReportName(String reportName) {
		this.reportName = reportName;
		log.info("Report Name Set to: "+this.reportName);
	}

	public void clickOnCreateNewReport() throws InterruptedException {
		waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
		waitAndSwitchIframe(ReportsTab.frm_WebReportIframe);
		//waitForFrameAndElement(ReportsTab.frm_WebReportIframe, ReportsTab.img_CreateReportButton);
		clickElementAndWait(ReportsTab.img_CreateReportButton);
	}

	public void verifyReportTypeOptionMenu() {
		waitUntilElementIsDisplayed(ReportsTab.ele_ReportTypeOptionMenu);
	}

	public void clickOnReportMenu(String reportType) {
		clickElementAndWait(By.xpath(".//div[@id='wrNewReportMenu']//span[text()='" + reportType.trim() + "']"));
	}

	public void verifyOpenedReportIntoPanel(String reportTab) {
		waitUntilElementIsDisplayed(By.xpath(".//*[@id='WebReportsCtrl_MainSplitter_MainTabCtrl_TabStripInnerContainer']//span[text()='" + reportTab.trim() + "']"));
	}

	public void enterReportNameAndSelectFolder(String reportType) {
		if (reportType.contains("Express"))
			setReportName(expressReport + dateUtils.getEpoch());
		else if (reportType.contains("Standard"))
			setReportName(standardReport + dateUtils.getEpoch());
		sendKeys(ReportsTab.txt_ReportNameInput, reportName);

		arrowExpandList = findElements(ReportsTab.css_ArrowExpandList);
		for (WebElement e : arrowExpandList) {
			if (!isDisplayed(ReportsTab.ele_ReportFolderSelection))
				clickElementAndWait(e);
			else {
				clickElementAndWait(ReportsTab.ele_ReportFolderSelection);
				break;
			}
		}
	}

	public void clickOnNext(String nextButton) {
		waitForCompletePageLoad();
		clickElementWithText(nextButton);
		acceptAlert(3);
		
		if(nextButton.equalsIgnoreCase("Finish")) {
			waitUntilElementIsDisplayed(ReportsTab.img_ExecuteReportMenuGaugeWizardButton);
			waitUntilElementIsClickable(ReportsTab.img_ExecuteReportMenuGaugeWizardButton);
		}
	}

	public void verifySelectedTab(String openTab) {
		waitUntilElementIsDisplayed(By.xpath(".//div[@class='wrTabStripInnerContainer']//div[contains(@class,'wrStaticTabItemSelected')]//span[text()='" + openTab.trim() + "']"));
		Assert.assertTrue(isDisplayed(By.xpath(".//div[@class='wrTabStripInnerContainer']//div[contains(@class,'wrStaticTabItemSelected')]//span[text()='" + openTab.trim() + "']")));
	}

	public void searchAndSelectCategories(String categories) {
		sendKeys(ReportsTab.txt_SearchCategoriesInput, categories);
		doubleClick(By.xpath(".//div[contains(@id,'ReportCategoriesSplitter') and @class='wrTreeContent']//div[not(contains(@style,'display: none'))]//span[text()='" + categories + "']"));
	}

	public void verifySelectedCategories(String categories) {
		waitUntilElementIsDisplayed(By.xpath(".//*[@id='wrGridTbl']//td[contains(text(),'" + categories.trim() + "')]"));
	}

	public void doubleClickOnSelectedField(String selectField) {
		waitUntilElementIsDisplayed(By.xpath(".//div[contains(@id,'ReportSortsSplitter')]//span[text()='" + selectField.trim() + "']"));
		doubleClick(By.xpath(".//div[@class='wrTabInnerContent' and not(contains(@style,'display: none'))]//div[contains(@id,'DataFieldsCtrl')]//span[text()='" + selectField.trim() + "']"));
	}

	public void selectMultipleColumns() {
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ReportsTab.ele_ExpressReportLayoutSubField);

		List<WebElement> Columns = findElements(ReportsTab.css_ExpressReportFieldList);
		layoutFields = Arrays.asList("Comment Code", "Comment Creator", "Comment Date", "Comment Details", "Comment Title");
		for (WebElement e : Columns) {
			for (String field : layoutFields) {
				if (e.getText().equalsIgnoreCase(field)) {
					action = new Actions(getWebDriver());
					action.moveToElement(e).doubleClick().perform();
				}
			}
		}
	}

	public void clickOnExecuteSelectedReport() {
		waitForCompletePageLoad();
		try { waitUtils.waitInterval(2); } catch (Throwable t) { log.info(": Waitinterval Failed :"); }
		waitUntilElementIsClickable(ReportsTab.btn_ExecuteReportMenu);
		clickElementAndWaitForElement(ReportsTab.btn_ExecuteReportMenu, ReportsTab.img_ExecuteExcelReportButton);
		clickElementAndWait(ReportsTab.img_ExecuteExcelReportButton);
	}

	public void verifyReportExecution() {
		waitUntilElementIsDisplayed(ReportsTab.pop_ReportsTabProcessDialog);
		waitUntilElementIsInvisible(ReportsTab.pop_ReportsTabProcessDialog);
	}

	public void verifyReportInLocalDirectory(String reportType) throws IOException, InterruptedException {
		fileName = nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path").trim() + reportName + ".xlsx";
		File file1 = new File(fileName);
		String renamedReportName = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + reportName + ".xlsx";
		file2 = new File(renamedReportName);
		//autoItUtils.downloadAutoIt(file.toString(), nodeIP);
		waitUntilFileIsDownloaded(file1);

		file1.renameTo(file2);
		waitUntilFileIsDownloaded(file2);
		sendKeys(ReportsTab.txt_SearchReportInput, reportName);
		try {
			waitUntilElementIsDisplayed(ReportsTab.ele_SelectedReport);
		}
		catch (Throwable t) {
			log.info(": WaitInterval Failed :");
		}
		if (reportType.contains("Standard Report")) {
			Assert.assertTrue(getElementText(ReportsTab.ele_SelectedReport).equalsIgnoreCase(reportName));
		}
		else {
			contextClick(ReportsTab.ele_SelectedReport);
			clickElementAndWait(ReportsTab.sel_ReportsTabDelete);
			waitUntilElementIsDisplayed(ReportsTab.pop_ReportsTabProcessDialog);
			clickElementAndWait(ReportsTab.btn_ReportsTabProcessDialogOkButton);
		}
	}

	public void verifyDownloadedReportContent(String reportType) throws IOException {
		if(reportType.contains("Standard"))
			layoutFields = ScheduleReportScripts.layoutFields;
		try {
			FileInputStream file = new FileInputStream(file2);
			wbXLSX = new XSSFWorkbook(file);
			sheetXLSX = wbXLSX.getSheetAt(0);
	
			if (sheetXLSX != null) {
				String reportTitle = sheetXLSX.getRow(0).getCell(0).getStringCellValue();
				log.info("reportTitle : " + reportTitle);
				Assert.assertTrue("Actual :" + reportTitle + " :: " + "Expected :" + reportName, reportName.equalsIgnoreCase(reportTitle));

				int j = 0;
				for (int i = 0; i < sheetXLSX.getRow(2).getLastCellNum(); i++) {
					String actualVal = sheetXLSX.getRow(2).getCell(i).getStringCellValue();
					String expectedVal = layoutFields.get(j);
					Assert.assertTrue("actualVal : " + actualVal + "expectedVal : " + expectedVal, actualVal.equalsIgnoreCase(expectedVal));
					j++;
				}
			}
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (reportType.contains("Express Report"))
			sysUtils.deleteFile(fileName);
	}

	public static String getEpoch() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
