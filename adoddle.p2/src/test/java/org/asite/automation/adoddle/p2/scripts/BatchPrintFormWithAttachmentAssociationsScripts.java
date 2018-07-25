package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;

public class BatchPrintFormWithAttachmentAssociationsScripts extends AdoddleCommonAppMethods {
	private String			formTitle	= "BatchPrintAutoTestDataForm";
	private String			parentWindow;
	private String			batchFilePath, parentWindow2;
	public static Logger	log			= AutomationLogger.getInstance().getLogger(BatchPrintFormWithAttachmentAssociationsScripts.class);

	public void searchPrintForm() {
		searchForms(formTitle);
		collectionDataMap.put("BatchPrint Form Title", formTitle);
	}

	public void verifyCreatedForm() {
		Assert.assertTrue(isDisplayed(ProjectFormsTab.lnk_FirstFormTitle));
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(formTitle));
	}

	public void contextClickAndSelectPrintAll(String printAllContextOption) {
		contextClickWithLink(formTitle);
//		clickContextMenuOptionWithText(printAllContextOption);
		clickContextMenuOption(printAllContextOption);
	}

	public void switchToSecondWindow() throws IOException, InterruptedException {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
	}
	
	public void switchToThirdWindow() throws IOException, InterruptedException {
		parentWindow2 = getCurrentWindow();
		switchToThirdWindow(parentWindow, parentWindow2);
		waitForCompletePageLoad();
	}

	public void createBatchFileInLocal(String fileName) throws IOException, InterruptedException {
		if(fileName.equalsIgnoreCase("PrintViewFormTestFile") || fileName.equalsIgnoreCase("BatchPrintTestFile"))
			batchFilePath = handleBatchPrintDialog(fileName, parentWindow);
		else
			batchFilePath = handleBatchPrintDialog(fileName, parentWindow2);
		collectionDataMap.put("batchFilePath", batchFilePath);
	}

	public void compareFileSizeInLocal(String fileName) throws IOException, InterruptedException {
		double localBatchPrintFileSize = 0;
		int localFilePages = 0;
		int savedFilePages = 0;
		if (fileName.contains("ORI"))
			fileName = "ORI";
		else if (fileName.contains("FWD"))
			fileName = "FWD";
		else if (fileName.contains("RESF"))
			fileName = "RES_FWD";
		else if (fileName.contains("RESO"))
			fileName = "RES_ORI";
		else
			log.info("fileName : " + fileName);

		sysUtils.authenticateRemoteMachine(nodeIP);
		
		if (fileName.contains("BatchPrintTestFile"))
			localFilePages = sysUtils.getPDFFilePagesCount(nodeIP + ResourceHandler.loadProperty("printall.form.testdata.filepath").trim() + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		else if (fileName.contains("PrintViewFormTestFile"))
			localFilePages = sysUtils.getPDFFilePagesCount(nodeIP + ResourceHandler.loadProperty("printall.viewform.testdata.filepath").trim() + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		else
			localFilePages = sysUtils.getPDFFilePagesCount(nodeIP + ResourceHandler.loadProperty("current.form.testdata.filepath").trim() + fileName + AdoddleCommonStringPool.UNDERSCORE_STRING + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);

		collectionDataMap.put("localFilePages", Integer.toString(localFilePages));
		log.info("localFilePages : " + localFilePages);
		
		waitUntilFileIsDownloaded(new File(batchFilePath));
		savedFilePages = sysUtils.getPDFFilePagesCount(batchFilePath);
		
		collectionDataMap.put("savedFilePages", Integer.toString(savedFilePages));
		log.info("savedFilePages : " + savedFilePages);
		Assert.assertTrue("Local file page count " + localFilePages + " != saved file page count " + savedFilePages, localFilePages == savedFilePages);

		if (fileName.contains("BatchPrintTestFile"))
			localBatchPrintFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("printall.form.testdata.filepath").trim() + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		else if (fileName.contains("PrintViewFormTestFile"))
			localBatchPrintFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("printall.viewform.testdata.filepath").trim() + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		else
			localBatchPrintFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("current.form.testdata.filepath").trim() + fileName + AdoddleCommonStringPool.UNDERSCORE_STRING + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);

		double savedBatchPrintFileSize = sysUtils.getFileSize(batchFilePath);

		savedBatchPrintFileSize = Math.round(savedBatchPrintFileSize * 1000.0) / 1000.0;
		localBatchPrintFileSize = Math.round(localBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("localBatchPrintFileSize :: " + localBatchPrintFileSize);
		log.info("savedBatchPrintFileSize :: " + savedBatchPrintFileSize);
		double batchfileSizeDifference = Math.round((savedBatchPrintFileSize - localBatchPrintFileSize) * 1000.0) / 1000.0;
		
		collectionDataMap.put("batchfileSizeDifference", Double.toString(batchfileSizeDifference));
		log.info("batchfileSizeDifference :: " + batchfileSizeDifference);
		
		if (savedBatchPrintFileSize != 0)
			Assert.assertTrue("BatchFileDifference not expected as:: " + batchfileSizeDifference, javaUtils.validateFileSizeRange(batchfileSizeDifference, 5.0));
		else
			Assert.assertTrue("BatchPrintFormFile not saved successfully", false);

	}

	/******* All Messages to PDF *******/

	public void clickOnPrintForm() {
		waitUntilElementIsClickable(ProjectFormsTab.lnk_FirstFormTitle);
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormTitle);
	}

	public void clickAndSelectExportMenuOption(String exportSelectionType) {
		/*if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {*/

			waitUntilElementIsDisplayed(ProjectFormsTab.btn_BetaViewFormDetailsExportButton);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewFormDetailsExportButton);
			clickElementAndWait(By.xpath(".//div[@id='form-view-page']//div[@id='form-holder']//ul//li//a[contains(text(),'" + exportSelectionType + "')]"));
		//}
		/*else {
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_FormDetailsExportButton);
			clickElementAndWait(ProjectFormsTab.btn_FormDetailsExportButton);
			clickElementAndWait(By.xpath(".//ul//li[contains(@id,'print')]//a//span[text()='" + exportSelectionType + "']"));
		}*/
	}

	/******* Current Message to PDF *******/

	public void clickFormDetailsAndSelectFormType(String lhOption, String formType) {
		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			waitUntilElementIsDisplayed(By.xpath(".//div[contains(@class,'component-wrapper')]//button[@title='" + lhOption + "']"));
			clickElementAndWait(By.xpath(".//div[contains(@class,'component-wrapper')]//button[@title='" + lhOption + "']"));
			
			if(formType.equalsIgnoreCase("ori"))
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewViewFormHistoryORI);
			else if(formType.equalsIgnoreCase("fwd"))
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewViewFormHistoryORIFWD);
			else if(formType.equalsIgnoreCase("res001"))
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewViewFormHistoryFWDRES1);
			else if(formType.equalsIgnoreCase("res002"))
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewViewFormHistoryORIRES1);
			
			waitUntilElementIsInvisible(ProjectFormsTab.ele_BetaViewFormViewLoading);
		/*}
		else {
			clickLinkWithText(lhOption);
			clickElementAndWait(By.xpath(".//div[@class='table-comms']//table[@class='tblCommentsTree']//span[contains(text(),'" + formType + "')]"));
		}*/
	}
}
