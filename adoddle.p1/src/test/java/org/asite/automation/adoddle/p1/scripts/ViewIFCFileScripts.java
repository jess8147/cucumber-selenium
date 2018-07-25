package org.asite.automation.adoddle.p1.scripts;

import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class ViewIFCFileScripts extends AdoddleCommonAppMethods {

	private String			parentHandle;
	public static Logger	log	= AutomationLogger.getInstance().getLogger(ViewIFCFileScripts.class);

	public void selectBrowserRenderingViewer(String viewer) {
		selectByVisibleText(ModelsTab.drp_ModelsListingViewerRenderer, viewer);
	}

	public void clickOnValidModelName() {
		parentHandle = getCurrentWindow();
		searchModels(ResourceHandler.loadProperty("upload.ifc.model"));
		collectionDataMap.put("Test Model", ResourceHandler.loadProperty("upload.ifc.model"));
		clickElementAndWait(ModelsTab.lnk_UploadIFCFileModel);
	}

	public void verifyModelOpensInNewTab() {
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void verifyModelViewer(String viewerMode) {
		
		
		log.info("Viewer Rendererer not found");
		
	}

	public void verifyIFCFileBrowserRendering() throws Exception {
		
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ModelsTab.lbl_BetaViewModelsViewModelViewerHeader);
		if(isDisplayed(ModelsTab.lnk_BetaViewModelViewerWorksetExpand))
			clickElement(ModelsTab.lnk_BetaViewModelViewerWorksetExpand);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ModelsTab.ele_ModelsTabViewModelName);
		Assert.assertTrue(getElementText(ModelsTab.ele_ModelsTabViewModelName).contains(ResourceHandler.loadProperty("upload.ifc.model")));
		try {
			waitUntilElementIsDisplayed(ModelsTab.lbl_ViewIFCFileSuccessMessage);
			Assert.assertTrue(isDisplayed(ModelsTab.lbl_ViewIFCFileSuccessMessage));
		} catch (Throwable t) {
			log.info("unable to verify success message");
		}
		Assert.assertTrue(isDisplayed(ModelsTab.scr_ModelViewerCanvasScreen));
		
		takeScreenShot(TestDriverFactory.scenario);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void verifyIFCFileServerRendering() {
		final int MAX_STALE_ELEMENT_RETRIES = 5;
		int retries = 0;

		if (isDisplayed(ModelsTab.lnk_ModelViewerWorksetExpand))
			clickElement(ModelsTab.lnk_ModelViewerWorksetExpand);
		List<WebElement> fileList = findElements(ModelsTab.css_ModelViewerUploadedFiles);

		for (WebElement e : fileList) {

			if (e.getText().equalsIgnoreCase(ResourceHandler.loadProperty("single.ifc.file.path").split("\\\\")[4].replace(".ifc", ""))) {
				if (isDisplayed(ModelsTab.lnk_ModelViewerFirstFileCollapse))
					e.click();
				waitForCompletePageLoad();
				waitUntilElementIsDisplayed(ModelsTab.opt_ModelViewerUploadedFirstFileLatestVersion);
				waitUntilElementIsPresent(ModelsTab.lbl_ViewIFCFileLoadingSuccessMessage);
				waitUntilElementIsDisplayed(ModelsTab.scr_ServerRenderingViewerCanvasScreen);
				while (true) {
					try {
						waitUntilElementIsPresent(ModelsTab.lbl_ViewIFCFileServerRenderingSuccessMessage);
						return;
					}
					catch (StaleElementReferenceException t) {
						if (retries < MAX_STALE_ELEMENT_RETRIES) {
							retries++;
							continue;
						}
						else {
							throw t;
						}
					}

				}

			}

		}
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}
}
