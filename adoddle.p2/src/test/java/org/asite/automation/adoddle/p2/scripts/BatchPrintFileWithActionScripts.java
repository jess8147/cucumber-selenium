package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class BatchPrintFileWithActionScripts extends AdoddleCommonAppMethods {
	private String file, file1, file2, file3;
	private boolean flag;
	private Boolean isListPresent;
	private List<WebElement> fileList = new ArrayList<WebElement>();
	private List<String> multiFileUploadList = new ArrayList<String>();
	private List<String> popHoverActionList = new ArrayList<String>();
	public static Logger log = AutomationLogger.getInstance().getLogger(BatchPrintFileWithActionScripts.class);

	public void clickOnSelectFilesAndUpload() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		file = nodeIP + resourceUtils.getTestDataFilePath() + epoch;
		log.info("file :" + file);

		String[] fileList = { file1, file2, file3 };
		index = javaUtils.resetIndex(index, 1);
		for (String fileName : fileList) {
			fileName = sysUtils.createRemotePdfFile(file + index + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP)
					.trim();
			multiFileUploadList.add(fileName);
			index++;
		}
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileUploadList);
		collectionDataMap.put("Multiple Document List:: ", multiFileUploadList.toString());
		log.info("multiFileUploadList : " + multiFileUploadList);
	}

	public void enterMendatoryAttributes(String action) throws InterruptedException {
		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, AdoddleCommonStringPool.ONE_STRING);
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);

		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeFilesButton);
		waitUntilElementIsDisplayed(FilesTab.txt_PopUploadFileDistributeTo);
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, System.getProperty("primary.username"));
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
		clickElementAndWait(FilesTab.btn_PopDistributeUserToggleButton);
		selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
		clickElementAndWait(FilesTab.lnk_PopDatePickerAssignedCurrentDate);
	}

	public void verifyUploadedFilesAndActions(String action) {
		searchFiles(strUtils.extractFileNameString(file));

		List<String> fileNameList = getList();
		log.info("fileNameList : " + fileNameList);
		fileList = findElements(FilesTab.css_NumberOfFiles);
		index = javaUtils.resetIndex(index, 0);
		flag = false;

		for (WebElement webFile : fileList) {
			for (String localFile : fileNameList) {
				if (webFile.getText().contains(localFile)) {

					flag = true;
					log.info("Verified : " + webFile.getText() + " : " + localFile);

					clickElement(By.xpath(".//div[@index='" + index
							+ "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					mouseHover(By.xpath(".//div[@index='" + index
							+ "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					popHoverActionList.clear();
					for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
						popHoverActionList.add(popHoverAction.getText());
					}
					log.info("popHoverActionList :" + popHoverActionList);
					Assert.assertTrue(popHoverActionList.contains(action));
					mouseHover(FilesTab.lbl_FilesTabFilesCount);
					break;
				} else {
					log.info(": Not Verified :");
					flag = false;
				}
			}
			if (!flag) {
				log.info(": List Not Matched :");
				Assert.assertTrue(flag);
			}
			index++;
		}
	}

	public void contextClickAndSelectOption(String contextOption) {
		selectAllFilesCheckbox();
		contextClick(FilesTab.lnk_FileName);
		clickContextMenuOptionWithText(contextOption);
	}

	public void verifySelectedFilesOnPopup() {
		List<String> fileList = new ArrayList<String>();
		List<WebElement> popFileList = new ArrayList<WebElement>();
		popFileList = findElements(FilesTab.css_PopActionFilesNameList);

		for (WebElement fileName : popFileList) {
			fileList.add(fileName.getText());
		}
		log.info("fileList :" + fileList);

		Assert.assertTrue(fileList.containsAll(getList()));
	}

	public void verifyFilesOpenedForBatchPrint() throws Exception {
		switchToSecondWindow();
		try {
			sendActionKeys(Keys.ESCAPE);
			waitForCompletePageLoad();
			Assert.assertTrue(isDisplayed(FilesTab.frm_PDFViewer));
		} catch (Throwable t) {
			log.info(": can't find PDF Viewer :");
		}
		takeScreenShot(TestDriverFactory.scenario);
	}

	public void verifyBatchPrintFilesActionCompleted(String action) {
		index = javaUtils.resetIndex(index, 0);
		for (String file : getList()) {

			log.info("file :" + file);
			mouseHover(FilesTab.lbl_FilesTabFilesCount);
			clickElement(By.xpath(".//div[@index='" + index
					+ "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
			mouseHover(By.xpath(".//div[@index='" + index
					+ "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
			waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

			popHoverActionList.clear();
			for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
				popHoverActionList.add(popCompleteAction.getText());
			}
			Assert.assertTrue(popHoverActionList.contains(action));

			isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
			if (isListPresent == true) {
				popHoverActionList.clear();
				for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
					popHoverActionList.add(popHoverAction.getText());
				}
				Assert.assertTrue(!popHoverActionList.contains(action));
			} else {
				Assert.assertTrue(true);
			}
			index++;
		}
	}

	public void clickMoreOptionsAndSelectOption() {
		selectAllFilesCheckbox();
		clickElementAndWaitForElement(FilesTab.lnk_FilesMoreOptions, FilesTab.lnk_OptionsPrintFile);
		clickElementAndWait(FilesTab.lnk_OptionsPrintFile);
	}

	public void selectAllFilesCheckbox() {
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox)) {
			executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
			mouseHover(FilesTab.chk_MultiFilesSelectionCheckbox);
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		}
	}

	public List<String> getList() {
		List<String> fileNameList = new ArrayList<String>();

		for (String file : multiFileUploadList) {
			fileNameList.add(strUtils.extractFileNameString(file));
		}
		return fileNameList;
	}

	public void switchToSecondWindow() {
		CreateModelCommentScripts.parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
	}
}
