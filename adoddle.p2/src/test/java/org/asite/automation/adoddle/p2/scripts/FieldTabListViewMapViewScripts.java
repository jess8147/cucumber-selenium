package org.asite.automation.adoddle.p2.scripts;

import org.asite.automation.CommonLocators.AdoddleFieldLocators.FieldTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.junit.Assert;

public class FieldTabListViewMapViewScripts extends AdoddleCommonAppMethods {

	public void verifyExistingProjectSite(String siteTitle, String projectTitle) {
		Assert.assertTrue(isDisplayedElementWithText(siteTitle));
	}

	public void verifyExistingSiteLocation(String location, String siteTitle) {
		clickElementWithText(siteTitle);
		collectionDataMap.put("site", siteTitle);
		collectionDataMap.put("location", location);
		AutomationAssert.verifyTrue(isDisplayedElementWithText(location));

	}

	public void verifyDefectsAvailable() {
		waitUntilElementCountToBeMoreThan(FieldTab.css_FieldListingDefectsCount, 0);
	}

	public void switchToAppsDistribution() {
		/*if (!getSelectedDropdownLabel(FilesTab.drp_TabTypeDropdown).equalsIgnoreCase("Apps Distribution"))
			selectByVisibleText(FilesTab.drp_TabTypeDropdown, "Apps Distribution");*/
		clickElementAndWait(GlobalPageElements.lnk_ActiveTabDistributionTab);
		waitForCompletePageLoad();
	}

}
