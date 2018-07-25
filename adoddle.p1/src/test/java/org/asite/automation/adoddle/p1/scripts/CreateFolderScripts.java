package org.asite.automation.adoddle.p1.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;

public class CreateFolderScripts extends AdoddleCommonAppMethods {
	private String			folderTitle	= "AutomationTest_Folder", editSuffix = "_Edited";
	public static Logger	log			= AutomationLogger.getInstance().getLogger(CreateFolderScripts.class);

	public void clickOnProjectName(String dc) throws InterruptedException {
		contextClickWithText(dc.trim());
		waitUntilElementIsDisplayed(FilesTab.opt_ProjectContextClickNew);
	}

	public void clickOncreateNewFolder() {
		mouseHoverAndClickElement(FilesTab.opt_ProjectContextClickNew, FilesTab.opt_ProjectContextClickFolder);
	}

	public void createFolder() throws InterruptedException {
		waitForCompletePageLoad();
		sendKeys(FilesTab.txt_FolderName, folderTitle + epoch);
		collectionDataMap.put("Folder Title", folderTitle);
		clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
	}

	public void verifyFolderName() throws InterruptedException {
		waitForElementWithText(folderTitle + epoch.trim());
	}

	public void clickOnEditFolder() throws InterruptedException {
		contextClickWithText(folderTitle + epoch.trim());
		mouseHoverAndClickElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.opt_ProjectFolderContextClickEditFolder);

	}

	public void editFolder() throws InterruptedException {
		waitForCompletePageLoad();
		sendKeys(FilesTab.txt_ChangeFolderName, folderTitle + editSuffix + epoch);
		collectionDataMap.put("Edited Folder Title", folderTitle + editSuffix + epoch);

	}

	public void clickOnUpdate() throws InterruptedException {
		clickElementAndWait(FilesTab.btn_PopEditFolderDeactivateUpdate);
	}

	public void verifyEditedFolderName() throws InterruptedException {
		Assert.assertTrue(isDisplayedElementWithText(folderTitle + editSuffix + epoch.trim()));
	}

	public void clickOnEditedFolder() throws InterruptedException {
		contextClickWithText(folderTitle + editSuffix + epoch.trim());
		mouseHoverAndClickElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.opt_ProjectFolderContextClickEditFolder);
		waitUntilElementIsDisplayed(FilesTab.chk_PopEditFolderDeactivate);
	}

	public void deactivateCheckboxSelect() throws InterruptedException {
		if (!isSelected(FilesTab.chk_PopEditFolderDeactivate)) {
			clickElementAndWait(FilesTab.chk_PopEditFolderDeactivate);
		}
	}

	public void clickOnOk() throws InterruptedException {
		clickElementAndWait(FilesTab.btn_PopupOK);
	}

	public void verifyEditedFolderDeactivate() throws InterruptedException {
		Assert.assertTrue(!isDisplayedElementWithText(folderTitle + editSuffix + epoch.trim()));
	}

	public void clickCreateFolderLink() {
		clickElementAndWait(FilesTab.lnk_PopMoreOptionsNewFolder);
	}
}
