package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import org.asite.automation.CommonLocators.AdoddleClassicLocators.ClassicLocators;
import org.asite.automation.CommonLocators.AdoddleReportsLocators.ReportsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class AdoddleSharedReportACLCheck extends AdoddleCommonAppMethods {

	private String automationTestReportName = "AutomationTest_ExpressSharedReport";
	private String preViewFile, parentHandel;
	private double localFileSize;

	public void clickLinkEditSchedule() {
		clickElementAndWait(ReportsTab.lnk_EditAndScheduleReports);
	}

	public void searchAndValidateReport(String reportName) throws InterruptedException {
		sendKeys(ReportsTab.txt_SearchLegacyReportInput, reportName);
		collectionDataMap.put("Report:: ", reportName);
		sendKeys(ReportsTab.txt_SearchLegacyReportInput, Keys.ENTER);
	}

	public void validateReportIsNotAvailable() {
		Assert.assertTrue("Reports Listing not available", !isDisplayed(ReportsTab.lnk_FirstLegacyReport));
	}

	public void validateReportCountInListing() {
		Assert.assertTrue("Reports Listing not available", isDisplayed(ReportsTab.lnk_FirstLegacyReport));
	}

	public void reportACLValidation(String aclType, String userName) throws IOException, InterruptedException {

		if (userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))
				&& aclType.contains(AdoddleCommonStringPool.REPORT_NO_ACCESS))
			Assert.assertTrue(!isDisplayed(ReportsTab.lnk_FirstLegacyReport));

		else if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
				&& aclType.contains(AdoddleCommonStringPool.REPORT_VIEW)) {
			Assert.assertTrue(isDisplayed(ReportsTab.lnk_FirstLegacyReport));
			Assert.assertTrue(getElementText(ReportsTab.lnk_FirstLegacyReport).contains(automationTestReportName));
			Assert.assertTrue("Failure in validation", isDisplayed(ReportsTab.lnk_FirstLegacyEditReportDisabled));
			downloadReportAndValidate();

		}

		else if (userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))
				&& aclType.contains(AdoddleCommonStringPool.REPORT_SCHEDULE_REPORT)) {
			Assert.assertTrue(isDisplayed(ReportsTab.lnk_FirstLegacyReport));
			Assert.assertTrue(getElementText(ReportsTab.lnk_FirstLegacyReport).contains(automationTestReportName));
			Assert.assertTrue("Failure in validation", isEnabled(ReportsTab.lnk_FirstLegacyEditReport));
			clickElementAndWait(ReportsTab.lnk_FirstLegacyEditReport);
			switchToSecondWindow();
			waitUntilElementIsDisplayed(ClassicLocators.drp_EditReportFrequencyDropdown);
			Assert.assertTrue("Failure in validation", isEnabled(ClassicLocators.drp_EditReportFrequencyDropdown));
			Assert.assertTrue("Failure in validation", isEnabled(ClassicLocators.sel_FirstDateOfMonthRadioButton));
			closeCurrentWindow();
			switchPreviousWindow(parentHandel);

		}

		else if (userName.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name"))
				&& aclType.contains(AdoddleCommonStringPool.REPORT_DELEGATE_PERMISSION)) {

			Assert.assertTrue(isDisplayed(ReportsTab.lnk_FirstLegacyReport));
			Assert.assertTrue(getElementText(ReportsTab.lnk_FirstLegacyReport).contains(automationTestReportName));
			Assert.assertTrue("Failure in validation", isEnabled(ReportsTab.lnk_FirstLegacyEditReport));
			clickElementAndWait(ReportsTab.lnk_FirstLegacyEditReport);
			switchToSecondWindow();
			waitAndSwitchIframe(ReportsTab.frm_ReportACLIframe);
			Assert.assertTrue(isDisplayed(ReportsTab.lbl_EditReportAccessUserGroups));
			Assert.assertTrue(isEnabled(ReportsTab.btn_EditReportAccessScheduleReportAddSelected));
			switchDefault();
			closeCurrentWindow();
			switchPreviousWindow(parentHandel);

		}

		else {

			Assert.assertTrue(isDisplayed(ReportsTab.lnk_FirstLegacyReport));
			Assert.assertTrue(getElementText(ReportsTab.lnk_FirstLegacyReport).contains(automationTestReportName));
			Assert.assertTrue("Failure while validating user Previlege ",
					!isDisplayed(ReportsTab.lnk_FirstLegacyEditReportDisabled));
			clickElementAndWait(ReportsTab.lnk_FirstLegacyEditReport);
			switchToSecondWindow();
			waitUntilElementIsDisplayed(ClassicLocators.drp_EditReportFrequencyDropdown);
			Assert.assertTrue("Failure in validation", isEnabled(ClassicLocators.drp_EditReportFrequencyDropdown));
			Assert.assertTrue("Failure in validation", isEnabled(ClassicLocators.sel_FirstDateOfMonthRadioButton));
			waitAndSwitchIframe(ReportsTab.frm_ReportACLIframe);
			Assert.assertTrue(isDisplayed(ReportsTab.lbl_EditReportAccessUserGroups));
			Assert.assertTrue(isEnabled(ReportsTab.btn_EditReportAccessScheduleReportAddSelected));
			switchDefault();
			closeCurrentWindow();
			switchPreviousWindow(parentHandel);
			downloadReportAndValidate();

		}

	}

	public void switchToSecondWindow() {
		parentHandel = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
	}

	public void downloadReportAndValidate() throws IOException, InterruptedException {

		int counter = 0;

		for (int index = 0; index < 3; index++) {
			waitUntilElementIsDisplayed(ReportsTab.txt_SearchLegacyReportInput);
			sendKeys(ReportsTab.txt_SearchLegacyReportInput, automationTestReportName);
			sendKeys(ReportsTab.txt_SearchLegacyReportInput, Keys.ENTER);

			if (isDisplayed(ReportsTab.lnk_FirstLegacyReport))
				break;
			else {
				sendKeys(ReportsTab.txt_SearchLegacyReportInput, "");
				sendKeys(ReportsTab.txt_SearchLegacyReportInput, Keys.ENTER);
			}
		}
		Assert.assertTrue(getElementText(ReportsTab.lnk_FirstLegacyReport).contains(automationTestReportName));

		clickElementAndWaitForElement(ReportsTab.lnk_FirstLegacyReport,
				ReportsTab.btn_PopEditAndScheduleReportDownloadButton);
		waitForCompletePageLoad();
		while (isDisplayed(ReportsTab.btn_PopEditAndScheduleReportDownloadButton)) {
			waitUtils.waitInterval(1);
			try {
				clickElement(ReportsTab.btn_PopEditAndScheduleReportDownloadButton);
				waitUntilElementIsInvisible(ReportsTab.btn_PopEditAndScheduleReportDownloadButton, 3);
			} catch (Throwable t) {
				counter++;
				if (counter == 5)
					break;
			}

		}

		verifyDownloadedReport(AdoddleCommonStringPool.XLS_EXTENSION);
	}

	public void verifyDownloadedReport(String reportExtension) throws IOException, InterruptedException {
		File downloadFile =null;
		sysUtils.authenticateRemoteMachine(nodeIP);
		int counter = 0;

		for(String s: sysUtils.getFileListOfSystemFolder(nodeIP+ ResourceHandler.loadProperty("default.browser.download.file.path"))) {
			if(s.contains(automationTestReportName+"_"+dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST")) || counter > 120) {
				downloadFile = new File(nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path") + s);
				break;
			}
			else
				waitUtils.waitInterval(1);

			counter++;
		}

		String previewFolder = sysUtils.createDirectory(nodeIP
				+ ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutoTestReportACL" + "Folder"
				+ dateUtils.getEpoch());
		preViewFile = previewFolder + "\\PreviewReport" + epoch + reportExtension;

		File report = new File(preViewFile);
		downloadFile.renameTo(report);
		log.info("Download report name: " + report);
		sysUtils.authenticateRemoteMachine(nodeIP);
		/*autoItUtils.downloadAutoIt(report.toString(), nodeIP);*/
		waitUntilFileIsDownloaded(report);
		localFileSize = sysUtils.getFileSize(preViewFile);
		log.info("localFileSize :" + localFileSize);
		Assert.assertTrue(localFileSize > 1);
	}

}