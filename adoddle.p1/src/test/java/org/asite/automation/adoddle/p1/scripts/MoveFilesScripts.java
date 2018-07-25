package org.asite.automation.adoddle.p1.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.openqa.selenium.WebElement;

import cucumber.api.PendingException;

public class MoveFilesScripts extends AdoddleCommonAppMethods {

	private List<String> movedFileList1 = new ArrayList<String>();
	private List<String> movedFileList2 = new ArrayList<String>();
	private List<String> storedFileList1 = new ArrayList<String>();
	private List<String> storedFileList2 = new ArrayList<String>();
	public static Logger log = AutomationLogger.getInstance().getLogger(MoveFilesScripts.class);

	public void clickOnSubFolder(String subfolder) {
		clickElementWithText(subfolder);
	}

	public void selectMultipleFilesCheckboxSelect() {
		try {
			if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		} catch (Throwable t) {
			log.error("Testdata files not found; hence skipping steps");
			throw new PendingException();
		}
	}

	public void clickOnMultipleFilesAndSelectOption(String menuOption) {
		getListOfFiles(movedFileList1);
		log.info("movedFileList1 :" + movedFileList1);

		contextClick(FilesTab.lnk_FileName);
		clickContextMenuOptionWithText(menuOption);
	}

	public void verifyMovedFilesIntoFolder(String folderName) {
		clickElementWithText(folderName);

		getListOfFiles(storedFileList1);
		log.info("storedFileList1 :" + storedFileList1);

		javaUtils.compareUnorderedList(movedFileList1, storedFileList1);
	}

	public void clickOnMoreOptions() {
		getListOfFiles(movedFileList2);
		log.info("movedFileList2 :" + movedFileList2);

		clickElementAndWait(FilesTab.lnk_FilesTabMoreOptions);
	}

	public void clickOnMoveFiles() {
		clickElementAndWait(FilesTab.lnk_PopOptionsMoveFiles);
	}

	public void verifyMovedFilesIntoSubFolder(String folderName, String subFolderName) {
		clickElementWithText(folderName);
		clickElementWithText(subFolderName);

		getListOfFiles(storedFileList2);
		log.info("storedFileList2 :" + storedFileList2);

		javaUtils.compareUnorderedList(movedFileList2, storedFileList2);
	}

	public void getListOfFiles(List<String> fileList) {
		executeJScript(AdoddleCommonJQueries.scrollDownJquery);
		waitForCompletePageLoad();
		List<WebElement> subFiles = findElements(FilesTab.css_NumberOfFiles);
		for (WebElement e : subFiles) {
			fileList.add(e.getText());
		}
	}
}