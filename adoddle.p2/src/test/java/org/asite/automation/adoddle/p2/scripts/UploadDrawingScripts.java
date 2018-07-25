/**  Testdata required for this script as follows.
     1). Workspace Automation_FieldEnabled_UK_Project should be available. 
     2). Above workspace should have all field related settings applied.  
 */

package org.asite.automation.adoddle.p2.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFieldLocators.FieldTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

public class UploadDrawingScripts extends AdoddleCommonAppMethods {

	private String			siteName, projectName;
	public static Logger	log	= AutomationLogger.getInstance().getLogger(UploadDrawingScripts.class);

	public void verifyFieldEnabledProject(String projectTitle) {
		projectName = projectTitle;
		AutomationAssert.verifyTrue("Project with Title: " + projectTitle + " is not visible", isDisplayedElementWithText(projectTitle));
		clickElementWithText(projectTitle);

	}

	public void verifySiteIsCreated(String siteTitle, String projectTitle) {
		siteName = siteTitle + epoch;
		contextClickWithText(projectTitle);
		waitUntilElementIsDisplayed(FieldTab.opt_ContextClickProjectAddSite);
		clickElementAndWaitForElement(FieldTab.opt_ContextClickProjectAddSite, FieldTab.txt_PopAddSiteSiteName);
		sendKeys(FieldTab.txt_PopAddSiteSiteName, siteName);
		collectionDataMap.put("site", siteName);
		clickElementAndWait(FieldTab.btn_PopAddSiteCreate);
		reloadPage();
		waitForCompletePageLoad();
		navigateTabByText(AdoddleCommonStringPool.TAB_FIELD);
		clickElementWithText(projectTitle);
		AutomationAssert.verifyTrue(isDisplayedElementWithText(siteName));
		clickElementWithText(siteName);
	}

	public void verifyLocationIsCreated(String location, String siteTitle) {
		switchDefault();
		reloadPage();
		waitForCompletePageLoad();
		navigateTabByText(AdoddleCommonStringPool.TAB_FIELD);
		clickElementWithText(projectName);
		contextClickWithText(siteName);
		waitUntilElementIsDisplayed(FieldTab.opt_ContextClickSiteAddLocation);
		clickElementAndWaitForElement(FieldTab.opt_ContextClickSiteAddLocation, FieldTab.txt_PopAddLocationLocationName);
		sendKeys(FieldTab.txt_PopAddLocationLocationName, location + epoch);
		collectionDataMap.put("location", location + epoch);
		clickElementAndWait(FieldTab.btn_PopAddLocationCreate);
		AutomationAssert.verifyTrue("ERROR:Location " + location + epoch + " is not displayed", isDisplayedElementWithText(location + epoch));
		clickElementWithText(location + epoch);
		waitForCompletePageLoad();

	}

	public void contextClickExistingSite(String siteTitle) {
		contextClickWithText(siteTitle + epoch);
	}

	public void clickOnContextOption(String option) {
		waitUntilElementIsDisplayed(FieldTab.opt_ContextClickSiteAddDrawing);
		AutomationAssert.verifyTrue(getElementText(FieldTab.opt_ContextClickSiteAddDrawing).equalsIgnoreCase(option));
		clickElementAndWait(FieldTab.opt_ContextClickSiteAddDrawing);
	}

	public void selectDrawingFileAndUpload() {
		findElement(FieldTab.btn_PopUploadDrawingSelectFiles).sendKeys(ResourceHandler.loadProperty("snagit.drawing.file1.path"));
		collectionDataMap.put("drawing file", ResourceHandler.loadProperty("snagit.drawing.file1.path"));
		waitUntilElementIsDisplayed(FieldTab.btn_PopUploadDrawingSelectedFileSize);
		clickElementAndWait(FieldTab.btn_PopUploadDrawingUpload);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void verifyPlanViewIconEnabled() {
		AutomationAssert.verifyTrue(isEnabled(FieldTab.img_SiteListingPlanView));
	}

	public void verifyDrawingFileIsVisible() {
		waitAndSwitchIframe(FieldTab.frm_PlanViewerIframe);
		waitUntilElementIsDisplayed(FieldTab.ele_DrawingDocumentViewerCanvas);
		switchDefault();
	}

	public void contextClickExistingLocation(String locationTitle) {
		contextClickWithText(locationTitle + epoch);
	}

	public void removeSiteFromProject() {
		switchDefault();
		reloadPage();
		waitForCompletePageLoad();
		navigateTabByText(AdoddleCommonStringPool.TAB_FIELD);
		clickElementWithText(projectName);
		contextClickWithText(siteName);
		waitUntilElementIsDisplayed(FieldTab.opt_ContextClickSiteRemove);
		mouseHoverAndClickElement(FieldTab.opt_ContextClickSiteRemove, FieldTab.opt_ContextClickSiteRemoveSite);
		clickElementAndWait(FieldTab.btn_PopRemoveSiteAlertOK);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void verifySiteIsRemoved() {
		AutomationAssert.verifyTrue(!isDisplayedElementWithText(siteName));
	}

}
