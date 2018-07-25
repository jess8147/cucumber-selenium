/*Test Date Required as :
 * BatchPrintAutomationTestDataFile's 
 * pdfFactory Pro Printer And should be selected as Default on Machine
 * handleBatchPrint.exe 
 */
package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.WebElement;

public class BatchPrintScripts extends AdoddleCommonAppMethods {

	private int				batchFileCount;
	private String			parentWindow, batchFilePath;
	final private List<String>	fileList	= new ArrayList<String>();
	final private List<String>	printList	= new ArrayList<String>();
	final private static Logger	log			= AutomationLogger.getInstance().getLogger(BatchPrintScripts.class);

	public void selectMultipleFiles() {
		String batchFileName = "BatchPrintAutoTestDataFile";
		parentWindow = getCurrentWindow();
		searchFiles(batchFileName);
		batchFileCount = getCount(FilesTab.css_DocListingFilesCount);
		List<WebElement> batchFiles = findElements(FilesTab.css_FilesDocRefList);
		for (WebElement e : batchFiles)
			fileList.add(e.getText());
		clickElementAndWait(FilesTab.chk_DocListingCheckAll);
	}

	public void clickMoreOptions() {
		clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, FilesTab.lnk_PopFileOptionsPrintFile);
	}

	public void clickPrintFileIcon() {
		clickElementAndWait(FilesTab.lnk_PopFileOptionsPrintFile);
	}

	public void contextClickOnSelectedDocs() {
		contextClick(FilesTab.lnk_DocListingFirstDocRef);

	}

	public void clickOnContextMenutOptionPrintFile(String elementText) {
		//clickContextMenuOptionWithText(elementText);
		clickContextMenuOption(elementText);
	}

	public void verifyAllSelectedBatchPrintFiles() {
		List<WebElement> batchPrintDocs = findElements(FilesTab.css_PopPrintDocumentDocRefList);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(batchFileCount, batchPrintDocs.size()), batchFileCount == batchPrintDocs.size());
		for (WebElement e : batchPrintDocs)
			printList.add(e.getText());
		AutomationAssert.assertEquals(fileList, printList);
	}

	public void selectPrintDocumentOptions() {
		clickElement(FilesTab.chk_PopPrintDocumentInclMarkup);
		clickElement(FilesTab.chk_PopPrintDocumentInclChangeMark);
		clickElement(FilesTab.chk_PopPrintDocumentFitToBanner);
	}
	
	public void selectPrintOptions() {
		clickElement(FilesTab.chk_PopPrintDocumentInclMarkup);
	}

	public void verifyNewWindowOpens() {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsPresent(FilesTab.ele_BatchPrintPDFViewerForm);
		String formAction = getElementAttributeValue(FilesTab.ele_BatchPrintPDFViewerForm, "action");
		String revisionId = getValue(FilesTab.ele_BatchPrintPDFViewerFormRevisionID);
		String viewerPref = getValue(FilesTab.ele_BatchPrintPDFViewerFormViewerPref);
		String projectId = getValue(FilesTab.ele_BatchPrintPDFViewerFormProjectId);
		String sel_docs = getValue(FilesTab.ele_BatchPrintPDFViewerFormSelectDocs);
		String doc_markup = getValue(FilesTab.ele_BatchPrintPDFViewerFormDocMarkUp);
		String change_mark = getValue(FilesTab.ele_BatchPrintPDFViewerFormChangeMark);
		String fit_to_banner = getValue(FilesTab.ele_BatchPrintPDFViewerFormFitToBanner);
		String action_id = getValue(FilesTab.ele_BatchPrintPDFViewerFormActionId);
		String fileUrl = formAction + "?revisionId=" + revisionId + "&viewerPref=" + viewerPref + "&projectId=" + projectId + "&sel_docs=" + sel_docs + "&change_mark=" + change_mark + "&doc_markup=" + doc_markup + "&fit_to_banner=" + fit_to_banner + "&action_id=" + action_id;
		log.info(fileUrl);
		/*navigateURL(fileUrl); new Actions(getWebDriver()).sendKeys(Keys.chord(Keys.CONTROL, "s")).perform();*/
	}

	public void createBatchFileInLocal(String fileName) throws IOException, InterruptedException {
		batchFilePath = handleBatchPrintDialog(fileName, parentWindow);	
	}

	public void compareFileSizeInLocal(String existingFileName) {
		double batchfileSizeDifference;
		double localBatchPrintFileSize;
		String testDataFile;
		sysUtils.authenticateRemoteMachine(nodeIP);

		if(TestDriverFactory.scenario.getName().contains("Universal Web Viewer")) {
			testDataFile = nodeIP + ResourceHandler.loadProperty("batch.print.UniversalWebViewer.testdata.filepath") + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION;
			localBatchPrintFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("batch.print.UniversalWebViewer.testdata.filepath") + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		}
		else {
			testDataFile = nodeIP + ResourceHandler.loadProperty("batch.print.testdata.filepath") + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION;
			localBatchPrintFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("batch.print.testdata.filepath") + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		}

		double savedBatchPrintFileSize = sysUtils.getFileSize(batchFilePath);
		collectionDataMap.put("TestData File", testDataFile);
		collectionDataMap.put("Downloaded File", batchFilePath);
		localBatchPrintFileSize = Math.round(localBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("LocalFileSize :" + localBatchPrintFileSize);
		savedBatchPrintFileSize = Math.round(savedBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("DowloadedFileSize: " + savedBatchPrintFileSize);
		batchfileSizeDifference = Math.round((savedBatchPrintFileSize - localBatchPrintFileSize) * 1000.0) / 1000.0;
		log.info("BatchFileSize Difference: " + batchfileSizeDifference);
		if (savedBatchPrintFileSize != 0)
			AutomationAssert.verifyTrue("BatchFileDifference not expected as:: " + batchfileSizeDifference, javaUtils.validateFileSizeRange(batchfileSizeDifference, 10.0));
		else
			AutomationAssert.verifyTrue("BatchAutoTestFile not saved successfully", false);
	}
	
	/*public void compareFileSizeInLocalForUniversalWebViewerWS(String existingFileName) {
		double batchfileSizeDifference;
		sysUtils.authenticateRemoteMachine(nodeIP);
		double localBatchPrintFileSize = sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("batch.print.UniversalWebViewer.testdata.filepath") + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		double savedBatchPrintFileSize = sysUtils.getFileSize(batchFilePath);
		collectionDataMap.put("TestData File", nodeIP + ResourceHandler.loadProperty("batch.print.UniversalWebViewer.testdata.filepath") + dataCenter + AdoddleCommonStringPool.PDF_EXTENSION);
		collectionDataMap.put("Downloaded File", batchFilePath);
		localBatchPrintFileSize = Math.round(localBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("LocalFileSize :" + localBatchPrintFileSize);
		savedBatchPrintFileSize = Math.round(savedBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("DowloadedFileSize: " + savedBatchPrintFileSize);
		batchfileSizeDifference = Math.round((savedBatchPrintFileSize - localBatchPrintFileSize) * 1000.0) / 1000.0;
		log.info("BatchFileSize Difference: " + batchfileSizeDifference);
		if (savedBatchPrintFileSize != 0)
			AutomationAssert.verifyTrue("BatchFileDifference not expected as:: " + batchfileSizeDifference, javaUtils.validateFileSizeRange(batchfileSizeDifference, 10.0));
		else
			AutomationAssert.verifyTrue("BatchAutoTestFile not saved successfully", false);
	}*/
	
	public void focusOnProject(String elementText) {
		
		clickElementWithText(elementText);
	}

}
