package org.asite.automation.adoddle.p2.scripts;

import java.util.List;

import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardFavouriteModels extends AdoddleCommonAppMethods {

	private String beforeFavouriteModelsCount, afterFavouriteModelsCount;
	private String favouriteModel, parentHandel;
	boolean flag;

	public void validateDasboardModelWidget() throws InterruptedException {

		if (isDisplayed(DashboardTab.lnk_DashboardFavouriteModelsBlankMsg))
			log.info("Favourite Model Widget is Blank Already");

		else {

			navigateTabByText("Models");
			clickElementAndWait(ModelsTab.lnk_FavoriteModelsTab);
			waitUntilListOfElementIsDisplayed(FilesTab.css_FileLists);
			List<WebElement> availableModelList = findElements(ModelsTab.css_ModelLists);
			for (WebElement file : availableModelList) {
				contextClickWebElement(file.findElement(ModelsTab.lnk_listModelName));
				clickContextMenuOptionWithText("Remove as " + favouriteString());
				waitForCompletePageLoad();

			}

			Assert.assertTrue("Failure while Test data cleanUp", Integer.parseInt(getTotalFavouriteModelsCount()) == 0);

		}

	}

	public void beforeFavouriteModelsCount() {
		beforeFavouriteModelsCount = getTotalFavouriteModelsCount();
		log.info("beforeFavouriteModelsCount :" + beforeFavouriteModelsCount);
		collectionDataMap.put("beforeFavouriteModelsCount:: ", beforeFavouriteModelsCount);

	}

	public String getTotalFavouriteModelsCount() {
		mouseHoverElement(ModelsTab.ele_ModelsTabFavoriteModelsCount);
		return getElementText(ModelsTab.ele_ModelsTabFavoriteModelsCount);
	}

	public void searchAndMarkAsFavourite() {
		favouriteModel = getElementText(By.xpath(".//div[@index='" + JavaUtils.getRandomNumber(1)
				+ "']//div[contains(@class,'userModelName')]//a"));
		log.info("favouriteModel :" + favouriteModel);
		collectionDataMap.put("Model:: ", favouriteModel);
		searchModels(favouriteModel);
		if (!isDisplayed(ProjectsTab.css_MarkAsFavouriteProjectsImageList)) {
			contextClickWithLink(favouriteModel);
			clickContextMenuOptionWithText("Add as " + favouriteString());
			flag = true;
		}
	}

	public void verifyModelOnFavouriteModelsTab() {
		searchModels(favouriteModel);
		Assert.assertTrue(getElementText(ModelsTab.lnk_ModelsTabFirstModel).contains(favouriteModel));

	}

	public void verifyAddAsFavouriteModelAndCountIncreased() {
		Assert.assertTrue(isDisplayed(ProjectsTab.css_MarkAsFavouriteProjectsImageList));

		if (flag) {

			afterFavouriteModelsCount = getTotalFavouriteModelsCount();
			Assert.assertTrue(Integer.parseInt(afterFavouriteModelsCount) > Integer
					.parseInt(beforeFavouriteModelsCount));
		} else {

			afterFavouriteModelsCount = beforeFavouriteModelsCount;
			Assert.assertEquals(Integer.parseInt(afterFavouriteModelsCount),
					Integer.parseInt(beforeFavouriteModelsCount));
		}
		log.info("afterFavouriteModelsCount :" + afterFavouriteModelsCount);
		collectionDataMap.put("afterFavouriteModelsCount:: ", afterFavouriteModelsCount);

	}

	public void markModelAsUnfavourite() {
		contextClickWithLink(favouriteModel);
		clickContextMenuOptionWithText("Remove as " + favouriteString());

	}

	public void verifyFavouriteModelsTabCountDecreased() {
		String getTabCount = getTotalFavouriteModelsCount();
		log.info("getTabCount :" + getTabCount);

		Assert.assertTrue(Integer.parseInt(getTabCount) < Integer.parseInt(afterFavouriteModelsCount));

	}

	public void validateFavouriteModelWidgetOnDashBoard(String validationScenario) throws Exception {

		navigateTab(LandingPage.lnk_Dashboard);

		if (validationScenario.equalsIgnoreCase("Favourite")) {
			waitUntilElementIsDisplayed(DashboardTab.css_DashboardFavouriteModelList);
			Assert.assertTrue("Expected Model as Favourite:: " + favouriteModel,
					getElementText(DashboardTab.css_DashboardFavouriteModelList).contains(favouriteModel));
			/*
			 * clickElementAndWait(DashboardTab.css_DashboardFavouriteModelList);
			 * parentHandel = getCurrentWindow(); waitForSwitchWindow(2);
			 * switchWindow(); verifyModelInViewer();
			 */
		}

		else
			Assert.assertTrue(
					"Failure while Validation",
					getElementText(DashboardTab.lnk_DashboardFavouriteModelsBlankMsg).contains(
							AdoddleCommonStringPool.DASHBOARD_BLANK_MODEL_MESSAGE));

	}

	public void verifyModelInViewer() throws Exception {

		waitUntilElementIsDisplayed(ModelsTab.ele_ModelsTabViewModelName);
		Assert.assertTrue(isDisplayed(ModelsTab.ele_ModelsTabViewModelName));
		Assert.assertTrue(getElementText(ModelsTab.ele_ModelsTabViewModelName).contains(favouriteModel));
		takeScreenShot(TestDriverFactory.scenario);
		closeCurrentWindow();
		switchPreviousWindow(parentHandel);

	}

	public String favouriteString() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return AdoddleCommonStringPool.STRING_FAV_UK;
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return AdoddleCommonStringPool.STRING_FAV_US;
		else if (isDisplayed(GlobalPageElements.img_AusCountryLabel))
			return AdoddleCommonStringPool.STRING_FAV_AUS;
		else
			return null;
	}

}