/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ModelsACLCheckScripts extends AdoddleCommonAppMethods {
	private String			parentWindow;
	private String			modelName		= "AutoTestACLModel" + epoch;
	private String			workSet1		= "workSet1", workSet2 = "workSet2", workSet3 = "workSet3";
	private String			folderMap1		= "AutoACLFolder" + dateUtils.getEpoch() + 1, folderMap2 = "AutoACLFolder" + dateUtils.getEpoch() + 2, folderMap3 = "AutoACLFolder" + dateUtils.getEpoch() + 3;
	private List<String>	worksetList		= new ArrayList<String>();
	private List<String>	folderMapList	= new ArrayList<String>();
	public static Logger	log				= AutomationLogger.getInstance().getLogger(ModelsACLCheckScripts.class);

	public void clickOnAddModelButton() {
		setListingStyle("ListView");
		clickElementAndWait(ModelsTab.lnk_AddModelsTab);
	}

	public void createNewModel() {
		int index = 0;
		sendKeys(ModelsTab.txt_PopAddModelTitle, modelName);
		collectionDataMap.put("ModelName", modelName);
		sendKeys(ModelsTab.txt_PopAddModelDescription, javaUtils.getRandomString(10));
		selectByVisibleText(ModelsTab.drp_PopAddModelProject, System.getProperty("global.test.project"));
		selectByIndex(ModelsTab.drp_PopAddModelUnits, 1);

		worksetList.add(workSet1);
		worksetList.add(workSet2);
		worksetList.add(workSet3);
		collectionDataMap.put("WorksetList", worksetList.toString());

		folderMapList.add(folderMap1);
		folderMapList.add(folderMap2);
		folderMapList.add(folderMap3);
		collectionDataMap.put("FolderMapList", folderMapList.toString());

		for (String workset : worksetList) {
			clickElementAndWait(ModelsTab.btn_PopAddModelAddWorkSet);
			waitUntilElementIsDisplayed(ModelsTab.pop_AddModelAddWorkSet);

			sendKeys(ModelsTab.txt_PopAddWorkSetName, workset);
			selectByIndex(ModelsTab.txt_PopAddWorkSetDiscipline, 1);

			clickElementAndWait(ModelsTab.lnk_PopAddWorkSetModelWorkSetTreeChild);

			waitUntilElementIsClickable(ModelsTab.btn_PopAddWorkSetCreateFolder);
			clickElementAndWaitForElement(ModelsTab.btn_PopAddWorkSetCreateFolder, ModelsTab.btn_PopAddWorkSetCreateFolderLeftView);
			sendKeys(ModelsTab.txt_PopAddWorkSetCreateFolderInput, folderMapList.get(index));

			clickElementAndWait(ModelsTab.btn_PopAddWorkSetSave);
			waitUntilElementIsInvisible(ModelsTab.pop_AddModelAddWorkSet);
			index++;
		}
		sendKeys(ModelsTab.txt_PopAddModelLongitude, JavaUtils.getRandomNumber(2));
		sendKeys(ModelsTab.txt_PopAddModelLatitude, String.valueOf(Math.abs(Integer.parseInt(JavaUtils.getRandomNumber(2)) - 10)));
		sendKeys(ModelsTab.txt_PopAddModelElevation, JavaUtils.getRandomNumber(2));

		clickElementAndWait(ProjectsTab.btn_POIAndStatusSaveButton);
		waitUntilElementIsInvisible(ProjectsTab.btn_POIAndStatusSaveButton);
	}

	public void verifyCreatedModel() {
		waitUntilElementIsClickable(ModelsTab.lnk_AllModelsTab);
		
		setListingStyle("ListView");
		/*if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink))
			clickElementAndWait(ProjectsTab.lnk_ListView);*/

		searchModels(modelName);
		Assert.assertTrue(isDisplayed(ModelsTab.lnk_ModelsTabFirstModel));
	}

	public void contextClickOnModelAndSelectOption(String upload) {
		navigateTabByText(AsiteMenu.Models.toString());
		searchModels(modelName);
		Assert.assertTrue(isDisplayed(ModelsTab.lnk_ModelsTabFirstModel));
		contextClickWithLink(modelName);
		clickContextMenuOption(upload);
	}

	public void uploadIFCFile(String user) {
		List<String> fileList;
		if (user.contains("primary")) {
			selectByVisibleText(ModelsTab.drp_PopUploadModelFilesWorkset, workSet1);
			fileList = sysUtils.getFileList(ResourceHandler.loadProperty("ifc.model.acl.file1.path"));
			collectionDataMap.put("Primary IFC File", fileList.toString());

		}
		else if (user.contains("secondary")) {
			selectByVisibleText(ModelsTab.drp_PopUploadModelFilesWorkset, workSet2);
			fileList = sysUtils.getFileList(ResourceHandler.loadProperty("ifc.model.acl.file2.path"));
			collectionDataMap.put("Secondary IFC File", fileList.toString());
		}
		else {
			selectByVisibleText(ModelsTab.drp_PopUploadModelFilesWorkset, workSet3);
			fileList = sysUtils.getFileList(ResourceHandler.loadProperty("ifc.model.acl.file3.path"));
			collectionDataMap.put("Third IFC File", fileList.toString());
		}
		uploadFileUsingKeys(ModelsTab.btn_ModelUploadFileSelectFiles, fileList);
		clickElement(ModelsTab.btn_ModelUploadfileUpload);

		if (isDisplayed(ModelsTab.lnk_PopModelUploadDashButton))
			clickElement(ModelsTab.lnk_PopModelUploadDashButton);
		if (isDisplayed(ModelsTab.lnk_PopModelUploadPlusButton))
			clickElement(ModelsTab.lnk_PopModelUploadPlusButton);
	}

	public void setFolderRightsForUserAccess(String noAccess) throws InterruptedException {
		for (String folderMap : folderMapList) {
			if (folderMap.endsWith("1")) {
				contextClickWithText(folderMap);
				clickContextMenuOptionWithText("Edit Folder");
				waitUntilElementIsDisplayed(FilesTab.chk_PopEditFolderDeactivate);
				sendKeys(FilesTab.txt_PopEditFolderSecurityInput, System.getProperty("secondary.username"));
				sendKeys(FilesTab.txt_PopEditFolderSecurityInput, Keys.ENTER);
			}
			else if (folderMap.endsWith("2")) {
				contextClickWithText(folderMap);
				clickContextMenuOptionWithText("Edit Folder");
				waitUntilElementIsDisplayed(FilesTab.chk_PopEditFolderDeactivate);
				sendKeys(FilesTab.txt_PopEditFolderSecurityInput, System.getProperty("multi.project.username"));
				sendKeys(FilesTab.txt_PopEditFolderSecurityInput, Keys.ENTER);
			}
			else {
				contextClickWithText(folderMap);
				clickContextMenuOptionWithText("Edit Folder");
				waitUntilElementIsDisplayed(FilesTab.chk_PopEditFolderDeactivate);
			}
			sendKeys(FilesTab.txt_PopEditFolderSecurityInput, ResourceHandler.loadProperty("forth.user.username"));
			sendKeys(FilesTab.txt_PopEditFolderSecurityInput, Keys.ENTER);

			List<WebElement> folderSecurityAccessList = findElements(FilesTab.css_PopEditFolderUnlockUserList);
			for (WebElement folderAccess : folderSecurityAccessList) {
				folderAccess.click();
				waitUntilElementIsDisplayed(FilesTab.drp_PopEditFolderUserFolderPermissionDropdown);
				selectByVisibleText(FilesTab.drp_PopEditFolderUserFolderPermissionDropdown, noAccess);
				mouseHover(FilesTab.chk_PopEditFolderDeactivate);
				waitUtils.waitInterval(1);
			}
			clickElementAndWait(FilesTab.btn_PopEditFolderUpdate);
		}
	}

	public void userLogin(String user) throws InterruptedException {
		if (user.contains("primary")) {
			login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		}
		else if (user.contains("secondary")) {
			login(System.getProperty("multi.project.username"), System.getProperty("multi.project.password"));
		}
		else {
			login(ResourceHandler.loadProperty("forth.user.username"), ResourceHandler.loadProperty("forth.user.password"));
		}
	}

	public void clickOnModel() {
		setListingStyle("ListView");
		/*if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink))
			clickElementAndWait(ProjectsTab.lnk_ListView);*/

		/*if (!new Select(findElement(ModelsTab.drp_ModelsTabViewer)).getFirstSelectedOption().getText().contains("Browser-Rendering"))
			selectByValue(ModelsTab.drp_ModelsTabViewer, "3");*/

		searchModels(modelName);
		try {
			clickElementAndWait(ModelsTab.lnk_ModelsTabFirstModel);
			parentWindow = getCurrentWindow();
			waitForSwitchWindow(2);
		}
		catch (Throwable t) {
			expandModelNameColumn();
			clickElementAndWait(ModelsTab.lnk_ModelsTabFirstModel);
			parentWindow = getCurrentWindow();
			waitForSwitchWindow(2);
		}
		switchWindow();
	}

	public void viewModel(String user) throws Exception {
		waitUntilElementIsDisplayed(ModelsTab.ele_ModelsTabViewModelName);
		Assert.assertTrue(getElementText(ModelsTab.ele_ModelsTabViewModelName).contains(modelName));

		/*if (!new Select(findElement(ModelsTab.drp_ModelsViewModelViewerRenderer)).getFirstSelectedOption().getText().contains("Browser-Rendering"))
			selectByVisibleText(ModelsTab.drp_ModelsViewModelViewerRenderer, "Browser-Rendering");*/

		if (!user.contains("third")) {
			try {
				waitUntilElementIsDisplayed(ModelsTab.lbl_ViewIFCFileSuccessMessage);
			}
			catch (Throwable t) {
				log.error("unable to verify model loading success message");
			}
		}
		else {
//			waitUntilElementIsDisplayed(ModelsTab.scr_browserRenderingViewerCanvasScreen);
			waitUntilElementIsDisplayed(ModelsTab.scr_ModelViewerCanvasScreen);
		}
		takeScreenShot(TestDriverFactory.scenario);
//		Assert.assertTrue(isDisplayed(ModelsTab.scr_browserRenderingViewerCanvasScreen));
		Assert.assertTrue(isDisplayed(ModelsTab.scr_ModelViewerCanvasScreen));
	}

	public void closeViewModelWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
		waitForCompletePageLoad();
	}

	public void expandModelNameColumn() {
		executeJScript(AdoddleCommonJQueries.expandFirstModelNameJQuery);
	}
}
