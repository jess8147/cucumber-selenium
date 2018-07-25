package org.asite.automation.adoddle.p1.scripts;

import java.util.ArrayList;
import java.util.List;

import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MoveFoldersScripts extends AdoddleCommonAppMethods {

	private String			fileCountOfMovedFolder1;
	private String			fileCountOfStoredFolder1;
	private String			fileCountOfMovedFolder2;
	private String			fileCountOfStoredFolder2;
	private List<String>	movedFolderList1	= new ArrayList<String>();
	private List<String>	storedFolderList1	= new ArrayList<String>();
	private List<String>	movedFolderList2	= new ArrayList<String>();
	private List<String>	storedFolderList2	= new ArrayList<String>();
	private String			folderPath;
	private String			folderPathVerify;
	private String			project, folderTitle1, folderTitle2;

	public MoveFoldersScripts() {
		log = AutomationLogger.getInstance().getLogger(this.getClass());
	}

	public void setUpTestData(String folder1, String folder2) {

		if (isDisplayedElementWithText(folder1)) {
			log.info("Test Folder " + folder1 + " available in listing");
			if (isDisplayedElementWithText(folder2))
				log.info("Test Folder " + folder2 + " available in listing");
			else
				moveFolderInFolderListing(folder2);
		}

		else {
			log.info("Test Folder " + folder2 + " available in listing");
			if (isDisplayedElementWithText(folder1))
				log.info("Test Folder " + folder1 + " available in listing");
			else
				moveFolderInFolderListing(folder1);
		}

		Assert.assertTrue("Folder " + folder1 + " not Available in workspace", isDisplayedElementWithText(folder1));
		Assert.assertTrue("Folder " + folder2 + " not Available in workspace", isDisplayedElementWithText(folder2));
	}

	public void clickOnProject(String projectName) throws InterruptedException {
		project = projectName;
		clickElementWithText(projectName.trim());
	}

	public void clickOnFolderAndSelectOption(String folderName, String menuOption) {
		folderTitle1 = folderName;
		movedFolderList1.add(folderName.trim());
		clickElementWithText(folderName.trim());

		getListOfSubFolders(movedFolderList1);
		fileCountOfMovedFolder1 = getTotalFileCount();
		log.info("fileCountOfMovedFolder1 :" + fileCountOfMovedFolder1);

		Assert.assertEquals(folderPath, folderPathVerify);

		contextClickWithText(folderName.trim());
		clickContextMenuOptionWithText(menuOption);
	}

	public void selectFolderIntoTargetPopup(String folderOrProject) {
		if (!folderOrProject.equalsIgnoreCase(project)) {
			folderTitle2 = folderOrProject;
		}
		clickElementAndWait(By.xpath(".//div[@aria-hidden='false']//span[text()='" + folderOrProject.trim() + "']"));
	}

	public void clickOnSubmit() {
		clickElementAndWait(FilesTab.btn_PopSubmit);
		if (isDisplayed(GlobalPageElements.pop_PopUpElement)) {
			clickElementAndWait(FilesTab.btn_PopSubmit);
		}
	}

	public void verifyMovedFolderIntoFolder(String movedFolder, String parentFolder) {
		clickElementWithText(parentFolder.trim());
		clickElementWithText(movedFolder.trim());

		getListOfSubFolders(storedFolderList1);

		javaUtils.compareUnorderedList(movedFolderList1, storedFolderList1);
		fileCountOfStoredFolder1 = getTotalFileCount();
		log.info("file count of moved first folder :" + fileCountOfStoredFolder1);

		Assert.assertEquals(fileCountOfMovedFolder1, fileCountOfStoredFolder1);
	}

	public void clickOnFolder(String folderName) {
		folderTitle2 = folderName;
		clickElementWithText(folderTitle2.trim());
	}

	public void clickOnFolderAndMoreOptions(String folderName) {
		folderTitle1 = folderName;
		clickElementWithText(folderTitle1.trim());

		getListOfSubFolders(movedFolderList2);
		fileCountOfMovedFolder2 = getTotalFileCount();
		log.info("file count of moved second folder :" + fileCountOfMovedFolder2);

		clickElementAndWait(FilesTab.lnk_FilesTabMoreOptions);
	}

	public void clickOnMoveFolder() {
		clickElementAndWait(FilesTab.lnk_PopOptionsMoveFolder);
	}

	public void verifyMovedFolderIntoProject(String movedFolder, String projectName) {
		clickElementWithText(projectName.trim());
		clickElementWithText(movedFolder.trim());

		storedFolderList2.add(movedFolder);
		getListOfSubFolders(storedFolderList2);
		javaUtils.compareUnorderedList(movedFolderList2, storedFolderList2);
		fileCountOfStoredFolder2 = getTotalFileCount();
		log.info("file count of stored second folder :" + fileCountOfStoredFolder2);

		Assert.assertEquals(folderPath, folderPathVerify);
		Assert.assertEquals(fileCountOfMovedFolder2, fileCountOfStoredFolder2);
	}

	public void getListOfSubFolders(List<String> folderList) {
		List<WebElement> subFiles = findElements(FilesTab.css_FilesTabSubFolders);
		for (WebElement e : subFiles) {
			folderList.add(e.getText());
		}
		log.info("folderList :" + folderList);
	}

	public String getTotalFileCount() {
		String folderFiles;
		String fileCount[] = getElementText(FilesTab.lbl_FilesTabFilesCount).split("of ");
		folderFiles = fileCount[1].replace(")", "");
		log.info("folderFiles :" + folderFiles);
		return folderFiles;
	}

	public void moveFolderInFolderListing(String folder) {

		if (folder.contains("AutoMoveTestFolder2")) {
			clickElementWithText("AutoMoveTestFolder1");
			Assert.assertTrue("Folder AutoMoveTestFolder2 not Available", isDisplayedElementWithText(folder));
			contextClickWithText(folder);
		}

		else {

			clickElementWithText("AutoMoveTestFolder2");
			Assert.assertTrue("Folder AutoMoveTestFolder2 not Available", isDisplayedElementWithText(folder));
			contextClickWithText(folder);
		}

		clickContextMenuOptionWithText("Move Folder");
		clickOnSubmit();
	}

}