package org.asite.automation.classic.p1.scripts;

import java.io.File;
import java.io.IOException;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.ClassicCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.Alert;

public class BatchPrintClassicScripts extends ClassicCommonAppMethods {

	private String batchFiles = "BatchPrintAutoTestDataFile",
			projectType = "Collab";
	private String actualMsg, parentWindow, batchFilePath;
	private String batchPrintDocCount = null;
	private Alert alert;

	public void selectMultipleDocs() throws InterruptedException {

		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		searchFiles(batchFiles);
		clickElementAndWait(DocListingPage.chk_DocListingDirecAccessCheckAll);

	}

	public void clickOnAddBasketIcon() {

		try {
			clickElementAndWait(DocListingPage.img_DocListingAddToBasket);
		} catch (Exception e) {
			log.error("ERROR: Failure while attempt to click element.");

		}

	}

	public void verifySuccessMessage(String expectedMsg) {
		try {
			waitUntilAlertIsPresent(5);
			alert = getWebDriver().switchTo().alert();
			actualMsg = alert.getText();
			Assert.assertTrue(expectedMsg.equalsIgnoreCase(actualMsg));
			alert.accept();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	public void acceptAlertMessage() {

		log.info("Covered in <verifySuccessMessage> definition");
	}

	public void viewBasketDocuments(String viewBasketToolTip) {
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingViewBasket);
		try {
			clickElementAndWait(DocListingPage.img_DocListingViewBasket);
		} catch (Exception e) {

			log.error("ERROR: Failure to click element");
		}
	}

	public void verifyPageTitle(String viewBasketTitle) {
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteWorkspaceNameFrame);
		waitUntilElementIsDisplayed(DocListingPage.lbl_DocListingBatchPrintBasketPageTitle);
		Assert.assertTrue(getElementText(
				DocListingPage.lbl_DocListingBatchPrintBasketPageTitle)
				.equalsIgnoreCase(viewBasketTitle));
		batchPrintDocCount = getElementText(
				DocListingPage.lbl_DocListingBatchPrintDocCount).split("-")[1]
				.trim();
		log.info("BatchPrintDocCount:: " + batchPrintDocCount);
	}

	public void selectPrintAction(String printActionLabel) {
		waitUntilElementIsDisplayed(DocListingPage.drp_DocListingBasketDocActions);
		selectByVisibleText(DocListingPage.drp_DocListingBasketDocActions,
				printActionLabel);
	}

	public void verifyPrintDocumentPage(String pageTitle) {

		waitUntilElementIsDisplayed(DocListingPage.lbl_DocListingBatchPrintDocPageTitle);
		Assert.assertTrue(getElementText(
				DocListingPage.lbl_DocListingBatchPrintDocPageTitle)
				.equalsIgnoreCase(pageTitle));
	}

	public void selectBatchPrintMarkUps() {

		clickElementAndWait(DocListingPage.chk_DocListingBatchPrintFitToBanner);
	}

	public void clickPrintButton(String btnText) {
		waitUntilElementIsDisplayed(DocListingPage.btn_DocListingBatchPrintDocPrintButton);
		Assert.assertTrue(getValue(
				DocListingPage.btn_DocListingBatchPrintDocPrintButton)
				.equalsIgnoreCase(btnText));
		try {
			clickElementAndWait(DocListingPage.btn_DocListingBatchPrintDocPrintButton);
		} catch (Exception e) {

			log.error("ERROR: Failure to click element");
		}

	}

	public void verifyNewWindowOpens() {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();

	}

	public void logInfo() {
		log.info("Test Automation step covered in function <HandleBatchPrintDialogs.exe>");
	}

	public void createBatchFileInLocal(String fileName) throws IOException,
			InterruptedException {

		batchFilePath = nodeIP
				+ ResourceHandler.loadProperty("remote.download.file.path")
				+ fileName + dateUtils.getEpoch()
				+ AdoddleCommonStringPool.PDF_EXTENSION;
		try {
			waitForCompletePageLoad();
			autoItUtils.handleBatchPrint(batchFilePath, projectType,
					batchPrintDocCount, nodeIP);
		} catch (IOException e) {
			log.error("I/O exeption while handling Print Documents Dialog");
		} catch (InterruptedException e) {
			log.error("Interrupted exeption while handling Print Documents Dialog");
		}
		collectionDataMap.put("Saved File Name :", batchFilePath);
		waitUntilFileIsDownloaded(new File(batchFilePath));
		switchPreviousWindow(parentWindow);

	}

	public void validateFileInLocal() {

		log.info("Covered in <compareFileSizeInLocal> Definition");

	}

	public void compareFileSizeInLocal(String existingFileName,
			String projectTitle) throws IOException, InterruptedException {

		boolean flag = false;
		double batchfileSizeDifference, localBatchPrintFileSize;
		sysUtils.authenticateRemoteMachine(nodeIP);
		if (projectTitle.contains("AutomationClassic"))
			localBatchPrintFileSize = getFileSize(nodeIP
					+ ResourceHandler
							.loadProperty("batch.print.testdata.thinclient.filepath")
					+ dataCenter + ClassicCommonStringPool.PDF_EXTENSION);

		else
			localBatchPrintFileSize = getFileSize(nodeIP
					+ ResourceHandler
							.loadProperty("batch.print.testdata.javaviewer.filepath")
					+ dataCenter + ClassicCommonStringPool.PDF_EXTENSION);

		double savedBatchPrintFileSize = sysUtils.getFileSize(batchFilePath);
		localBatchPrintFileSize = Math.round(localBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("LocalFileSize :" + localBatchPrintFileSize);

		savedBatchPrintFileSize = Math.round(savedBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("DowloadedFileSize: " + savedBatchPrintFileSize);

		batchfileSizeDifference = Math
				.round((savedBatchPrintFileSize - localBatchPrintFileSize) * 1000.0) / 1000.0;
		log.info("BatchFileSize Difference: " + batchfileSizeDifference);
		if (savedBatchPrintFileSize != 0) {
			flag = true;
			Assert.assertTrue("BatchFileDifference not expected as:: "
					+ batchfileSizeDifference,
					validateFileSizeRange(batchfileSizeDifference));
		} else
			Assert.assertTrue("BatchAutoTestFile not saved successfully", flag);

	}

	public boolean validateFileSizeRange(double fileSizeDifference) {

		return fileSizeDifference <= 10.0 && fileSizeDifference >= -10.0;

	}

}