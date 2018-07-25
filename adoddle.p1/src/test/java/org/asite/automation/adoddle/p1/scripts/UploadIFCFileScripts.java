package org.asite.automation.adoddle.p1.scripts;

import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
public class UploadIFCFileScripts extends AdoddleCommonAppMethods {

	private String				modelTitle, modelDesc, modelMapFolderName, /*uploadTime*/ longiTude, latitude, elevation;
	private String				parentHandle;
	final private static Logger	log	= AutomationLogger.getInstance().getLogger(UploadIFCFileScripts.class);

	public void clickAddModelButton() {
		clickElementAndWaitForElement(ModelsTab.lnk_ModelsAddModel, ModelsTab.pop_ModelsAddModel);
	}

	public void verifyAddModelPopup(String textAddModel) {
		AutomationAssert.verifyTrue(getElementText(ModelsTab.lbl_PopAddModelHeader).equalsIgnoreCase(textAddModel));
	}

	public void createModel() {
		modelTitle = javaUtils.getRandomString(10) + dateUtils.getEpoch();
		collectionDataMap.put("Model Title", modelTitle);
		modelMapFolderName = ResourceHandler.loadProperty("test.map.folder") + dateUtils.getEpoch();
		sendKeys(ModelsTab.txt_PopAddModelTitle, modelTitle);
		modelDesc = javaUtils.getRandomString(20);
		sendKeys(ModelsTab.txt_PopAddModelDescription, modelDesc);
		selectByVisibleText(ModelsTab.drp_PopAddModelProject, System.getProperty("global.test.project"));
		selectByIndex(ModelsTab.drp_PopAddModelUnits, 1);
		clickElementAndWaitForElement(ModelsTab.btn_PopAddModelAddWorkSet, ModelsTab.pop_AddModelAddWorkSet);
		sendKeys(ModelsTab.txt_PopAddWorkSetName, javaUtils.getRandomString(10) + dateUtils.getEpoch());
		selectByIndex(ModelsTab.txt_PopAddWorkSetDiscipline, 1);
		clickElementAndWaitForElement(ModelsTab.lnk_PopAddWorkSetModelWorkSetTreeParent, ModelsTab.btn_PopAddWorkSetCreateFolder);
		clickElementAndWaitForElement(ModelsTab.btn_PopAddWorkSetCreateFolder, ModelsTab.txt_PopAddWorkSetCreateFolderInput);
		sendKeys(ModelsTab.txt_PopAddWorkSetCreateFolderInput, modelMapFolderName);
		clickElement(ModelsTab.btn_PopAddWorkSetSave);
		waitForCompletePageLoad();
		longiTude = JavaUtils.getRandomNumber(2);
		latitude = String.valueOf(Math.abs(Integer.parseInt(JavaUtils.getRandomNumber(2)) - 10));
		elevation = JavaUtils.getRandomNumber(3);
		sendKeys(ModelsTab.txt_PopAddModelLongitude, longiTude);
		sendKeys(ModelsTab.txt_PopAddModelLatitude, latitude);
		sendKeys(ModelsTab.txt_PopAddModelElevation, elevation);
		clickElementAndWaitForInvisibilityOfElement(ModelsTab.btn_PopAddModelSave, ModelsTab.btn_PopAddModelSave);
	}

	public void verifyModelCreateSuccess() {
		try{
			AutomationAssert.verifyTrue(getElementText(ModelsTab.lbl_ModelAddSuccessMsg).equalsIgnoreCase(AdoddleCommonStringPool.MODEL_ADDED_SUCCESS));
		} catch (Exception e) {
			
			log.info("Success Message could not be verified");
		}
		
	}

	public void verifyModelOnListing() {
		navigateTab(LandingPage.lnk_Models);
		if(!isDisplayed(ModelsTab.img_ViewGridActiveView) && isDisplayed(ModelsTab.img_ViewGridInActiveView))
			clickElementAndWaitForElement(ModelsTab.img_ViewGridInActiveView, ModelsTab.img_ViewGridActiveView);
		searchModels(modelTitle);
		AutomationAssert.verifyTrue(getElementText(ModelsTab.lnk_firstModelName).equalsIgnoreCase(modelTitle));
		contextClick(ModelsTab.lnk_firstModelName);
		clickElementAndWaitForElement(ModelsTab.opt_ModelProperties, ModelsTab.pop_ModelPropertiesEdit);
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(getValue(ModelsTab.txt_popModelPropertiesTitle).equalsIgnoreCase(modelTitle));
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(ModelsTab.drp_popModelPropertiesProject).equalsIgnoreCase(System.getProperty("global.test.project")));
		AutomationAssert.verifyTrue(getValue(ModelsTab.txt_popModelPropertiesDesc).equalsIgnoreCase(modelDesc));
		AutomationAssert.verifyTrue(getValue(ModelsTab.txt_ModelPropertiesLongitude).equalsIgnoreCase(longiTude));
		AutomationAssert.verifyTrue(getValue(ModelsTab.txt_ModelPropertiesLatitude).equalsIgnoreCase(latitude));
		AutomationAssert.verifyTrue(getValue(ModelsTab.txt_ModelPropertiesElevation).equalsIgnoreCase(elevation));
		clickElementAndWait(ModelsTab.btn_ModelPropertiesClose);
		try {
			deactivateProjectFolder(System.getProperty("global.test.project"), modelMapFolderName);
			logOut();
		}
		catch (Throwable t) {
			log.info(": Failed To Deactive Folder :");
		}
	}

	public void verifyModelExists() {
		AutomationAssert.verifyTrue(getCount(ModelsTab.css_ModelsListingModels) > 0);
	}

	public void selectListViewOnModels() {
		clickElementAndWait(GlobalPageElements.btn_ActiveTabSettingDropdownButton);
		if (!isDisplayed(ModelsTab.lnk_ModelsListViewActive)) {
			clickElementAndWaitForElement(ModelsTab.lnk_ModelsListView, ModelsTab.lnk_ModelsListViewActive);
		}
	}

	public void contextClickModelUpload() {
		searchModels(ResourceHandler.loadProperty("upload.ifc.model"));
		AutomationAssert.verifyTrue(isDisplayed(ModelsTab.lnk_UploadIFCFileModel));
		contextClick(ModelsTab.lnk_UploadIFCFileModel);
		clickElementAndWaitForElement(ModelsTab.opt_ModelContextClickUpload, FilesTab.pop_UploadFileDialog);
		waitForCompletePageLoad();
	}

	public void verifyUploadDialog(String headerText) {
		AutomationAssert.verifyTrue(getElementText(FilesTab.lbl_PopUploadFileDialog).equalsIgnoreCase(headerText));
	}

	public void uploadIFCFile() {
		selectByVisibleText(ModelsTab.drp_PopUploadModelFilesWorkset, ResourceHandler.loadProperty("upload.ifc.model.workset"));
		List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("single.ifc.file.path"));
		uploadFileUsingKeys(ModelsTab.btn_ModelUploadFileSelectFiles, fileList);
		clickElementAndWaitForElement(ModelsTab.btn_ModelUploadfileUpload, ModelsTab.pop_ModelFileUploadActivityCentre);
	}

	public void verifyUploadProgress() {
		AutomationAssert.verifyTrue(isDisplayed(ModelsTab.pop_ModelFileUploadActivityCentre));
	}

	public void verifyActivityCentrePopupText(String activityCentre) {
		if("uk".equalsIgnoreCase(dataCenter) || "aus".equalsIgnoreCase(dataCenter))
			AutomationAssert.verifyTrue(getElementText(ModelsTab.lbl_popActivityCentreHeader).equalsIgnoreCase(activityCentre));
		else if("us".equalsIgnoreCase(dataCenter))
			AutomationAssert.verifyTrue(getElementText(ModelsTab.lbl_popActivityCentreHeader).equalsIgnoreCase(activityCentre.replace("Centre", "Center")));


	}

	public void verifyFileUploadProcessBar() {
		parentHandle = getCurrentWindow();
		waitUntilElementIsDisplayed(FilesTab.img_popActivityCentreLoadingIcon);
	}

	public void verifyLoadingImageForMerging() {
		waitUntilElementIsDisplayed(FilesTab.img_popActivityCentreLoadingIcon);
		waitUntilElementIsDisplayed(FilesTab.img_popActivityCentreMergingIcon);
		//uploadTime = dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy HH:mm", "WET");
		waitUntilElementIsDisplayed(FilesTab.btn_popActivityCentreOk);
		clickElementAndWait(FilesTab.btn_popActivityCentreOk);
		verifyUploadedIFCFile();
	}

	private void verifyUploadedIFCFile() {
		navigateTab(LandingPage.lnk_Models);
		searchModels(ResourceHandler.loadProperty("upload.ifc.model"));
		clickElementAndSwitchWindow(ModelsTab.lnk_UploadIFCFileModel);
		waitUntilElementIsDisplayed(ModelsTab.lbl_BetaViewModelsViewModelViewerHeader);
		if(isDisplayed(ModelsTab.lnk_BetaViewModelViewerWorksetExpand))
			clickElement(ModelsTab.lnk_BetaViewModelViewerWorksetExpand);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ModelsTab.ele_ModelsTabViewModelName);
		AutomationAssert.verifyTrue(getElementText(ModelsTab.ele_ModelsTabViewModelName).contains(ResourceHandler.loadProperty("upload.ifc.model")));
		try {
			waitUntilElementIsDisplayed(ModelsTab.lbl_ViewIFCFileSuccessMessage);
		} catch (Throwable t) {
			log.info("unable to verify success message");
		}

		AutomationAssert.verifyTrue(isDisplayed(ModelsTab.scr_ModelViewerCanvasScreen));
		takeScreenShot(TestDriverFactory.scenario);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}
	
}
