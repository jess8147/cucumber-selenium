package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ManageDashboardScripts extends AdoddleCommonAppMethods {
	private static String	dashboardTitle, filterChartGadget, savedFilter, cloneDashboardTitle;
	private boolean			flag;
	final private List<String>	getGadgetList		= new ArrayList<String>();
	final private List<String>	filterSelectedList	= new ArrayList<String>();
	private String			filterType1;
	private String			filterType2;
	private String			filterType3;
	private String			sharedProject		= null;
	private int				index, filterTypeCountOnDashboard, filterTypeCountOnActiveTab;
	private int				firstTypeCount, secondTypeCount, thirdTypeCount;
	final private static Logger	log				= AutomationLogger.getInstance().getLogger(ManageDashboardScripts.class);

	/*** Create Edit Dashboard ***/

	public void clickOnToolsAndSelectOption(String toolsDropdown, String toolsOption) {
		clickElementAndWaitForElement(By.xpath(".//div[@id='tools']//button[@title='" + toolsDropdown + "']"), By.xpath(".//a[@title='" + toolsOption + "']//div"));
		clickElementAndWait(By.xpath(".//a[@title='" + toolsOption + "']//div"));
	}

	public void enteredDashboardCreationDetails(String dashboardType) {
		String dashboard, description;
		if (dashboardType.contains("Cloned")) {
			cloneDashboardTitle = "AutoTestClonedDashboard" + dateUtils.getEpoch();
			String cloneDashboardDesc = "Automation Clone Dashboard description " + dateUtils.getEpoch();
			dashboard = cloneDashboardTitle;
			description = cloneDashboardDesc;
			selectByVisibleText(DashboardTab.drp_PopDashboardCloneDashboardDropdown, dashboardTitle);
			collectionDataMap.put("Cloned Dashboard Title", dashboard);
		}
		else {
			dashboardTitle = "AutoTestDashboard" + dateUtils.getEpoch();
			String dashboardDesc = "Automation Dashboard description " + dateUtils.getEpoch();
			dashboard = dashboardTitle;
			description = dashboardDesc;
			collectionDataMap.put("Dashboard Title", dashboard);
		}
		log.info("Dashboard title : " + dashboard);
		sendKeys(DashboardTab.txt_PopDashboardTitleInput, dashboard);
		sendKeys(DashboardTab.txt_PopDashboardDescriptionInput, description);
	}

	public void verifyCreatedDashboard() {
		waitForCompletePageLoad();
		flag = false;
		index = 1;
		for (WebElement dashboardName : findElements(DashboardTab.css_ManageDashboardsPageDashboardNameList)) {
			if (dashboardName.getAttribute("title").contains(dashboardTitle)) {
				log.info("Actual Dashboard name :" + dashboardName.getAttribute("title") + " vs Expected Dashboard Name :" + dashboardTitle);
				flag = true;
				break;
			}
			else
				flag = false;
			index++;
		}
		if (!flag)
			Assert.assertTrue(flag);
	}

	public void editDashboard() {
		for (int counter = 100; counter != 5000;) {
			if (isDisplayed(By.xpath(".//div[@id='manageDashboardTable']//tr[" + index + "]//a[@title='Edit Dashboard']")))
				break;
			executeJScript("$(\"div[id='manageDashboardTable'] div[id='dashborad-listing']\").scrollTop(" + counter + ")");
			counter = counter + 100;
		}
		clickElementAndWait(By.xpath(".//div[@id='manageDashboardTable']//tr[" + index + "]//a[@title='Edit Dashboard']"));
	}

	/*** Customised Dashboard ***/

	public void selectDashboardOnSwitchDashboardDropdown() {
		clickElementAndWaitForElement(DashboardTab.btn_SwitchDashboardButton, By.xpath(".//ul[@id='dashboard-options']//li[@id='" + dashboardTitle + "']//a"));
		clickElementAndWait(By.xpath(".//ul[@id='dashboard-options']//li[@id='" + dashboardTitle + "']//a"));
	}

	public void verifyDashboardPage(String dashboardType) {
		String dashboard;
		if (dashboardType.contains("Cloned"))
			dashboard = cloneDashboardTitle;
		else
			dashboard = dashboardTitle;
		Assert.assertTrue(getElementText(DashboardTab.lbl_DashboardTitleLabel).contains(dashboard));
	}

	public void clickOnAddGadgets() {
		if (isDisplayed(DashboardTab.lnk_ClickhereToAddGadgetsLink))
			clickElementAndWait(DashboardTab.lnk_ClickhereToAddGadgetsLink);
		else
			clickElementAndWait(DashboardTab.lnk_AddGadgetLink);
	}

	public void addGadgetsOnDashboard() {
		waitUntilListOfElementIsDisplayed(DashboardTab.css_PopAddGadgetHeaderList);
		int gadgetCount = findElements(DashboardTab.css_PopAddGadgetHeaderList).size();
		log.info("gadgetCount : " + gadgetCount);

		for (index = 0; index < gadgetCount; index++) {
			if (index != 0)
				clickOnAddGadgets();

			clickElementAndWait(By.xpath(".//div[@title='Add To Dashboard'][contains(@id,'" + index + "')]"));
			verifyPopUpWithText(AdoddleCommonStringPool.LABEL_GADGET);
			if (!findElement(DashboardTab.txt_PopGadgetTitleInput).getAttribute("value").contains("Report") && !findElement(DashboardTab.txt_PopGadgetTitleInput).getAttribute("value").contains("New Call Off")) {
				clear(DashboardTab.txt_PopGadgetTitleInput);
				String gadgetName = "AutoGadget" + dateUtils.getEpoch();
				getGadgetList.add(gadgetName);
				log.info("Added Gadget : " + getGadgetList);
				sendKeys(DashboardTab.txt_PopGadgetTitleInput, gadgetName);
				clear(DashboardTab.txt_PopGadgetDescriptionInput);
				sendKeys(DashboardTab.txt_PopGadgetDescriptionInput, "Automation Test Gadget For Dashboard " + dateUtils.getEpoch());
				clickOnPopupButton(AdoddleCommonStringPool.BTN_ADD_TO_DASHBOARD);
			}
			else {
				log.info("Bug Gadget : " + findElement(DashboardTab.txt_PopGadgetTitleInput).getAttribute("value"));
				clickOnPopupButton("Cancel");
			}
			executeJScript(AdoddleCommonJQueries.scrollWindowMaxTopJQuery);
			waitForCompletePageLoad();
		}
	}

	public void verifyGadgetsAddedOnDashboard() {
		List<String> verifyGadgetList = new ArrayList<String>();
		for (WebElement gadgets : findElements(DashboardTab.css_DashboardGadgetsTitleList)) {
			verifyGadgetList.add(gadgets.getText());
		}
		log.info("actualGadgetsList :: " + getGadgetList);
		log.info("expectedGadgetsList :: " + verifyGadgetList);
		collectionDataMap.put("ActualGadgetsList", getGadgetList.toString());
		collectionDataMap.put("ExpectedGadgetsList", verifyGadgetList.toString());
		Assert.assertTrue(getGadgetList.containsAll(verifyGadgetList));
	}

	public void loginInAdoddle(String username, String password) {
		waitUntilElementIsDisplayed(LoginPage.frm_Iframe);
		switchIframe(LoginPage.frm_Iframe);
		waitUntilElementIsDisplayed(LoginPage.txt_AsiteUsername);
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).clear();
		getWebDriver().findElement(LoginPage.txt_AsiteUsername).sendKeys(username);
		getWebDriver().findElement(LoginPage.txt_AsitePassword).clear();
		getWebDriver().findElement(LoginPage.txt_AsitePassword).sendKeys(password);
		clickElementAndWait(LoginPage.btn_AsiteLogin);
		waitForCompletePageLoad();
		int counter = 0;
		while (isDisplayed(GlobalPageElements.btn_DynamicModelDisMiss)) {
			clickElementAndWait(GlobalPageElements.btn_DynamicModelDisMiss);
			counter ++;
			if(counter > 5)
				break;
		}
	}

	public void setDashboardAsDefault() {
		waitForCompletePageLoad();
		flag = false;
		index = 1;
		for (WebElement dashboardName : findElements(DashboardTab.css_ManageDashboardsPageDashboardNameList)) {

			log.info("Default Dashboard : " + dashboardName.getAttribute("title"));
			if (dashboardName.getAttribute("title").contains(dashboardTitle)) {
				clickElementAndWait(By.xpath(".//div[@id='manageDashboardTable']//tr[" + index + "]//a[@title='Set as a default Dashboard']"));
				log.info("Dashboard index : " + index);
				flag = true;
				break;
			}
			else
				flag = false;
			index++;
		}
		if (!flag)
			Assert.assertTrue(flag);
	}

	public void verifyDashboardSetAsDefaultDashboard(String dashboardType) {
		verifyDashboardPage(dashboardType);
	}

	public void deleteDashboard(String dashboardType) {
		String dashboard;
		if (dashboardType.contains("Cloned"))
			dashboard = cloneDashboardTitle;
		else
			dashboard = dashboardTitle;

		int i = 1;
		flag = false;
		for (WebElement dashbord : findElements(DashboardTab.css_ManageDashboardsPageDashboardNameList)) {
			if (dashbord.getAttribute("title").equalsIgnoreCase(dashboard)) {
				clickElementAndWait(By.xpath(".//div[@id='manageDashboardTable']//tr[" + i + "]//a[@title='Delete Dashboard']"));
				waitUntilElementIsDisplayed(By.xpath(".//div[contains(@style,'display: block')]//button[text()='" + AdoddleCommonStringPool.BTN_CONTINUE + "']"));
				clickOnPopupButton(AdoddleCommonStringPool.BTN_CONTINUE);
				flag = true;
				break;
			}
			else {
				flag = false;
				log.info("Dashboard comparison :" + dashboard + " != " + dashbord.getAttribute("title"));
			}
			i++;
		}
		if (!flag)
			Assert.assertTrue(flag);
	}

	public void verifyDefaultDashboard() {
		/*for(int counter=0; counter < 20; counter++) {
			if(getElementText(DashboardTab.lbl_DashboardTitleLabel).contains("System Dashboard"))
				break;
			try {
				waitInterval(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			reloadPage();
			waitForCompletePageLoad();
		}*/
		waitUntilElementContainsText(DashboardTab.lbl_DashboardTitleLabel, "System Dashboard");
		Assert.assertTrue(eStringUtils.getContainsStringError(getElementText(DashboardTab.lbl_DashboardTitleLabel), "System Dashboard"), getElementText(DashboardTab.lbl_DashboardTitleLabel).contains("System Dashboard"));
	}

	/*** "Shared Dashboard" with Filter ***/

	private void createSubFilter(String subFilter) {
		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter, GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);

		for (WebElement filterVal : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
			if (filterVal.getAttribute("title").equalsIgnoreCase(subFilter)) {
				clickElementAndWait(filterVal);
				break;
			}
			else
				log.info("Filter comparison :" + filterVal.getAttribute("title") + " != " + subFilter);
		}
	}

	private void createFilterWithMultipleTypes(String filter) {
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
		waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);

		int i = 1;
		for (WebElement filterType : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
			if (!filterType.getAttribute("title").equalsIgnoreCase("---")) {
				switch (i) {
					case 1:
						filterType1 = filterType.getAttribute("title");
						break;
					case 2:
						filterType2 = filterType.getAttribute("title");
						break;
					case 3:
						filterType3 = filterType.getAttribute("title");
						break;
				}

				clickElementAndWait(filterType);

				i++;
				if (i == 4)
					break;
			}
		}
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
		
		log.info("filterType1 : " + filterType1);
		log.info("filterType2 : " + filterType2);
		log.info("filterType3 : " + filterType3);
		collectionDataMap.put("Selected Filter Val", filterType1 + "," + filterType2 + "," + filterType3);
	}

	public void createFilterWithAllTypes(String filterName, String subFilter) {
		savedFilter = filterName + epoch;
		log.info("savedFilter : " + savedFilter);
		
		waitForCompletePageLoad();
		
		createSubFilter(subFilter);
		saveCreatedFilter();
	}

	public void createFilter(String filterName, String filter) {
		savedFilter = filterName + epoch;
		log.info("savedFilter : " + savedFilter);
		collectionDataMap.put("Saved Filter", savedFilter);
		
		waitForCompletePageLoad();

		if (!savedFilter.contains("Form") && !savedFilter.contains("Status"))
			createSubFilter(filter);
		if (!savedFilter.contains("All"))
			createFilterWithMultipleTypes(filter);

		saveCreatedFilter();
	}

	private void saveCreatedFilter() {
		waitForCompletePageLoad();
		log.info("Save Filter Element: "+getCount(By.id("filterSave")));
		List<WebElement> saveFilterElements = findElements(By.id("filterSave"));
		for(WebElement e: saveFilterElements) {
			if(e.isDisplayed()) {
				System.out.println("Save Filter Element is displayed !! go click it !!s");
				clickElementAndWait(e);
			}
		}
		/*executeJScript("$(\"button[id='filterSave'][title='Save Filter'] i[class*='fa-save']\").click()");
		clickElementAndWaitForElement(By.cssSelector("div[id='fileContentContainer'] button[id='filterSave'][title='Save Filter']+button span"), By.cssSelector("div[id='fileContentContainer'] a[id='filterSaveAs'] img"));
		clickElementAndWait(By.cssSelector("div[id='fileContentContainer'] a[id='filterSaveAs'] img"));*/
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		sendKeys(GlobalPageElements.txt_PopSaveFilterInput, savedFilter);
		clickOnPopupButton(AdoddleCommonStringPool.BTN_SAVE);
		waitForCompletePageLoad();
		verifySavedFilterOnActiveTab("displayed");
	}

	public void sharedDashboardToUsers(String user1, String user2, String user3) {
		List<String> userList = new ArrayList<String>();

		if (sharedProject == null)
			sharedProject = user1;

		userList.add(user1);
		if (user2 != null)
			userList.add(user2);
		if (user3 != null)
			userList.add(user3);

		collectionDataMap.put("Shared Project List", userList.toString());
		
		for (String userID : userList) {
			log.info("Sharing Dashboard with user : " + userID);
			sendKeys(DashboardTab.txt_PopShareDashboardInput, userID);
			waitUntilElementContainsText(DashboardTab.ele_PopDashboardSuggestedValue, userID);
			sendKeys(DashboardTab.txt_PopShareDashboardInput, Keys.ENTER);
		}
	}

	public void selectDashboardFromManageDashboardPage() {
		boolean foundDashboard = false;
		for(int counter=0; counter < 3; counter++) {
			for(WebElement e: findElements(By.cssSelector("div[id='manageDashboardTable'] tr td[title]"))) {
				if(e.getAttribute("title").equalsIgnoreCase(dashboardTitle)) {
					clickElementAndWait(e.findElement(By.tagName("a")));
					foundDashboard = true;
					break;
				}
				else
					log.info("Dashboard on List :"+e.getAttribute("title") +" vs "+dashboardTitle);
			}
			if(!foundDashboard) {
				reloadPage(); 
				waitForCompletePageLoad();
				clickOnToolsAndSelectOption(AdoddleCommonStringPool.DASHBOARD_TOOLS, AdoddleCommonStringPool.OPT_MANAGE_DASHBOARDS);
			}
		}
		AutomationAssert.verifyTrue("Dashboard not available on listing with title :"+dashboardTitle, foundDashboard);
	}

	public void addFilterChartGadget(String gadgetHeader, String filterChartGadgetName) {
		filterChartGadget = filterChartGadgetName;
		collectionDataMap.put("Filter Chart Gadget", filterChartGadget);
		waitForCompletePageLoad();
		int i = 1;
		for (WebElement gadget : findElements(DashboardTab.css_PopAddGadgetHeaderList)) {
			if (gadget.getText().equalsIgnoreCase(gadgetHeader)) {
				clickElementAndWaitForInvisibilityOfElement(By.xpath(".//div[@class='widgetListItem'][" + i + "]//div[@title='Add To Dashboard']"), By.xpath(".//div[@class='widgetListItem'][" + i + "]//div[@title='Add To Dashboard']"));
				verifyPopUpWithText("Gadget");
				Assert.assertTrue(findElement(DashboardTab.txt_PopGadgetTitleInput).getAttribute("value").contains(gadgetHeader));
				clear(DashboardTab.txt_PopGadgetTitleInput);
				sendKeys(DashboardTab.txt_PopGadgetTitleInput, filterChartGadget);
				clear(DashboardTab.txt_PopGadgetDescriptionInput);
				sendKeys(DashboardTab.txt_PopGadgetDescriptionInput, filterChartGadget + epoch);
				clickOnPopupButton(AdoddleCommonStringPool.BTN_ADD_TO_DASHBOARD);
				break;
			}
			i++;
		}
	}

	public void verifyFilterChartGadgetOnDashboard() {
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(DashboardTab.css_DashboardGadgetsTitleList), filterChartGadget), getElementText(DashboardTab.css_DashboardGadgetsTitleList).contains(filterChartGadget));
	}

	public void selectDropdownAndCheckboxOFGadget(String statisticsDropdownOption) {
		String postFixVal = null;
		if (savedFilter.contains("Form"))
			postFixVal = " (Apps)";
		else if (savedFilter.contains("Doc"))
			postFixVal = " (Files)";
		
		waitUntilDropdownContainsValue(DashboardTab.drp_FilterChartGadgetFilterDropdown, savedFilter + postFixVal, 180);
		selectByVisibleText(DashboardTab.drp_FilterChartGadgetFilterDropdown, savedFilter + postFixVal);
		selectByVisibleText(DashboardTab.drp_FilterChartGadgetStatisticsTypeDropdown, statisticsDropdownOption);

		if (!isSelected(DashboardTab.chk_FilterChartGadgetShowLegendsCheckbox))
			clickElementAndWait(DashboardTab.chk_FilterChartGadgetShowLegendsCheckbox);
	}

	public void clickOnFilterGadgetUpdateButton() {
		mouseHover(DashboardTab.btn_FilterChartGadgetUpdateButton);
		clickElementAndWait(DashboardTab.btn_FilterChartGadgetUpdateButton);
	}

	public void verifyHighchartsTrackerOfGadget() {
		// Assert.assertTrue(isDisplayed(By.cssSelector("div[id='playground'] svg g[class='highcharts-series highcharts-tracker']")));
		waitUntilElementIsDisplayed(DashboardTab.ele_FilterChartGadgetHighchartsTracker);

	}

	public void verifyFilterTypesOnPaichart() {
		waitUntilListOfElementIsDisplayed(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList);
		List<String> filterList = new ArrayList<String>();
		for (WebElement filterType : findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList)) {
			filterList.add(filterType.getText());
		}
		log.info("filterList : " + filterList);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(filterList.size(), Arrays.asList(filterType1, filterType2, filterType3).size()), filterList.size() == Arrays.asList(filterType1, filterType2, filterType3).size());
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(filterList.toString(), Arrays.asList(filterType1, filterType2, filterType3).toString()), filterList.containsAll(Arrays.asList(filterType1, filterType2, filterType3)));
	}

	public void getAndVerifySelectedFilterTypeOnTrackerAndActiveTab(String filter, String activeTab) throws InterruptedException {
		waitForCompletePageLoad();
		List<WebElement> filterCountList;
		List<WebElement> filterValList;

		waitUntilListOfElementIsDisplayed(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList);

		List<String> filterTypeList = new ArrayList<String>(Arrays.asList(filterType1, filterType2, filterType3));
		log.info("filterTypeList : " + filterTypeList);

		Assert.assertTrue(filterTypeList.size() == findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList).size());

		for (String filter1 : filterTypeList) {
			mouseHoverElement(DashboardTab.ele_PaiChartRootTracker);
			if (isDisplayed(DashboardTab.ele_MaximizeGadget))
				clickElementAndWaitForInvisibilityOfElement(DashboardTab.ele_MaximizeGadget, DashboardTab.ele_MaximizeGadget);

			int j = 0;
			if (!filterSelectedList.contains(filter1)) {
				log.info("Adding filter to Selection List: " + filter1);
				filterSelectedList.add(filter1);

				filterCountList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeCountList);
				filterValList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList);

				int i = 0;

				for (WebElement filter2 : filterValList) {
					if (filter1.equalsIgnoreCase(filter2.getText())) {
						log.info("Selected Type : " + filter2.getText());
						String count = filterCountList.get(i).getText().split(": ")[1];
						filterTypeCountOnDashboard = Integer.parseInt(count);
						log.info("filterTypeCountOnDashboard : " + filterTypeCountOnDashboard);
						try {
							/*mouseHoverAndClickElement(filter2, filter2);*/
							doubleClick(filter2);
							waitUntilElementIsInvisible(filter2, 30);
						}
						catch(Throwable t) {
							log.error(eStringUtils.getClickFailureError(filter2));
						}
						if (isDisplayed(filter2))
							clickElementAndWait(filter2);
						waitForCompletePageLoad();

						verifyAsiteMenuList(activeTab);
						verifySavedFilterAndListOfSavedFilter();

						clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
						waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
						for (WebElement subFilterCheckbox : findElements(GlobalPageElements.css_FilterDropdownSelectedTypeCheckboxList)) {
							Assert.assertTrue(subFilterCheckbox.getAttribute("title").contains(filter1));
						}
						clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));

						String totalCount = "";
						if (savedFilter.contains("Form"))
							totalCount = getTotalFormsCount();
						else if (savedFilter.contains("Doc"))
							totalCount = getTotalFilesCount();

						filterTypeCountOnActiveTab = Integer.parseInt(totalCount);
						log.info("statusCountOnFormsTab : " + filterTypeCountOnActiveTab);
						Assert.assertTrue(filterTypeCountOnDashboard == filterTypeCountOnActiveTab);
						break;
					}
					i++;
				}
				j++;
				if (j == 1)
					break;
			}
		}
		log.info("Filter Selection List: " + filterSelectedList.toString());
	}

	private void verifySavedFilterAndListOfSavedFilter() {
		mouseHover(GlobalPageElements.btn_SavedFilterButton);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(GlobalPageElements.btn_SavedFilterButtonToolTip), savedFilter), getElementText(GlobalPageElements.btn_SavedFilterButtonToolTip).contains(savedFilter));
		clickElementAndWait(GlobalPageElements.btn_SavedFilterButton);
		List<String> filterList = new ArrayList<String>();
		for (WebElement filter : findElements(GlobalPageElements.css_SavedFilterList)) {
			filterList.add(filter.getText());
		}
		int totalSavedFilterCount = Collections.frequency(filterList, savedFilter);
		log.info("total saved filter count : " + totalSavedFilterCount);
		Assert.assertEquals(totalSavedFilterCount, 1);
	}

	public void getAndVerifyFilterAllTypeCountOnTrackerAndActiveTab(String filter, String activeTab) throws InterruptedException {
		waitForCompletePageLoad();
		List<WebElement> filterCountList;
		List<WebElement> filterValList;
		waitUntilElementIsDisplayed(DashboardTab.ele_PaiChartRootTracker);
		waitUntilElementIsClickable(findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList).get(0));
		List<String> filterTypeList = new ArrayList<String>();
		for (WebElement filterType : findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList)) {
			filterTypeList.add(filterType.getText());
		}
		log.info("filter Type List : " + filterTypeList + ", " + "filterTypeList size : " + filterTypeList.size());
		mouseHoverAndClickElement(DashboardTab.ele_PaiChartRootTracker, DashboardTab.ele_MaximizeGadget);
		waitUntilElementIsInvisible(DashboardTab.ele_MaximizeGadget);
		waitForCompletePageLoad();

		int j = 1;
		for (String filter1 : filterTypeList) {

			mouseHoverElement(DashboardTab.ele_PaiChartRootTracker);
			if (isDisplayed(DashboardTab.ele_MaximizeGadget))
				clickElementAndWaitForInvisibilityOfElement(DashboardTab.ele_MaximizeGadget, DashboardTab.ele_MaximizeGadget);

			waitUntilElementIsClickable(findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList).get(0));
			waitUntilElementIsClickable(findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList).get(filterTypeList.size() - 1));

			filterCountList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeCountList);
			filterValList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList);

			int i = 0;
			for (WebElement filter2 : filterValList) {

				if (filter2.getText().equalsIgnoreCase(filter1)) {

					log.info("Selected Type : " + filter2.getText());
					String count = filterCountList.get(i).getText().split(": ")[1];
					filterTypeCountOnDashboard = Integer.parseInt(count);
					log.info("status count on dashboard : " + filterTypeCountOnDashboard);

					try {
						/*mouseHoverAndClickElement(filter2, filter2);*/
						doubleClick(filter2);
						waitUntilElementIsInvisible(filter2, 30);
					}
					catch(Throwable t) {
						log.error(eStringUtils.getClickFailureError(filter2));
					}
					
					if (isDisplayed(filter2))
						doubleClick(filter2);
					waitForCompletePageLoad();

					verifyAsiteMenuList(activeTab);
					verifySavedFilterAndListOfSavedFilter();

					clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
					waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
					for (WebElement subFilterCheckbox : findElements(GlobalPageElements.css_FilterDropdownSelectedTypeCheckboxList)) {
						Assert.assertTrue(eStringUtils.getContainsStringError(subFilterCheckbox.getAttribute("title"), filter1), subFilterCheckbox.getAttribute("title").contains(filter1));
					}
					clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));

					String totalCount = "";
					if (savedFilter.contains("Form"))
						totalCount = getTotalFormsCount();
					else if (savedFilter.contains("Doc"))
						totalCount = getTotalFilesCount();

					filterTypeCountOnActiveTab = Integer.parseInt(totalCount);
					log.info("statusCountOnFormsTab : " + filterTypeCountOnActiveTab);
					AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(filterTypeCountOnDashboard, filterTypeCountOnActiveTab), filterTypeCountOnDashboard == filterTypeCountOnActiveTab);

					if (j < filterTypeList.size()) {
						navigateTabByText("Dashboard");
						selectDashboardOnSwitchDashboardDropdown();
					}
					break;

				}
				else
					log.info("Filter comparison :" + filter2.getText() + " != " + filter1);
				i++;
			}
			j++;
		}
	}

	public void setProjectInSearchProjectFilter(String selectedProject) {
		clickElementAndWait(GlobalPageElements.btn_SearchProjectFilterButton);
		waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);

		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
			clickElementAndWait(GlobalPageElements.lnk_FilterProjectClearAll);

		for (WebElement project : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
			if (project.getAttribute("title").contains(selectedProject)) {
				project.click();
				break;
			}
		}
		clickElementAndWait(GlobalPageElements.btn_SearchProjectFilterButton);
		selectDashboardOnSwitchDashboardDropdown();
	}

	public void getTotalCountOfSetFilterTypes() {
		waitForCompletePageLoad();
		List<WebElement> filterTypeCountList;
		filterTypeCountList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeCountList);

		int counter = 0;
		for (WebElement filterType : findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList)) {
			log.info("Selected Filter Type : " + filterType.getText());
			if (filterType.getText().equalsIgnoreCase(filterType1))
				firstTypeCount = Integer.parseInt(filterTypeCountList.get(counter).getText().split(": ")[1]);
			else if (filterType.getText().equalsIgnoreCase(filterType2))
				secondTypeCount = Integer.parseInt(filterTypeCountList.get(counter).getText().split(": ")[1]);
			else if (filterType.getText().equalsIgnoreCase(filterType3))
				thirdTypeCount = Integer.parseInt(filterTypeCountList.get(counter).getText().split(": ")[1]);
			counter++;
		}

		log.info("firstTypeCount :" + firstTypeCount);
		log.info("secondTypeCount :" + secondTypeCount);
		log.info("thirdTypeCount :" + thirdTypeCount);
	}

	public void verifyTotalSetFilterTypeCountMissMatch() {
		int firstTypeCount1 = firstTypeCount;
		int secondTypeCount1 = secondTypeCount;
		int thirdTypeCount1 = thirdTypeCount;
		getTotalCountOfSetFilterTypes();
		int firstTypeCount2 = firstTypeCount;
		int secondTypeCount2 = secondTypeCount;
		int thirdTypeCount2 = thirdTypeCount;

		Assert.assertNotEquals(firstTypeCount1, firstTypeCount2);
		Assert.assertNotEquals(secondTypeCount1, secondTypeCount2);
		Assert.assertNotEquals(thirdTypeCount1, thirdTypeCount2);
	}

	public void deleteCustomizedDashboard() {
		int i = 1;
		flag = false;
		for (WebElement dashbord : findElements(DashboardTab.css_ManageDashboardsPageDashboardNameList)) {
			if (dashbord.getAttribute("title").equalsIgnoreCase(dashboardTitle)) {
				clickElementAndWait(By.xpath(".//div[@id='manageDashboardTable']//tr[" + i + "]//a[@title='Delete Dashboard']"));
				waitUntilElementIsDisplayed(By.xpath(".//div[contains(@style,'display: block')]//button[text()='" + AdoddleCommonStringPool.BTN_CONTINUE + "']"));
				clickOnPopupButton(AdoddleCommonStringPool.BTN_CONTINUE);
				flag = true;
				break;
			}
			else {
				flag = false;
				log.info("Dashboard Comparison :" + dashbord.getAttribute("title") + " != " + dashboardTitle);
			}
			i++;
		}
		if (!flag)
			Assert.assertTrue(flag);
	}

	public void verifyDashboardDeleted(String dashboardType) {
		String dashboard;
		if (dashboardType.contains("Cloned"))
			dashboard = cloneDashboardTitle;
		else
			dashboard = dashboardTitle;

		waitForCompletePageLoad();
		for (WebElement dashbord : findElements(DashboardTab.css_ManageDashboardsPageDashboardNameList)) {
			Assert.assertTrue("Present Dashboard : " + dashbord.getAttribute("title"), !dashbord.getAttribute("title").equalsIgnoreCase(dashboard));
		}
	}

	public void verifySavedFilterOnActiveTab(String isDisplay) {
		clickElementAndWait(GlobalPageElements.btn_SavedFilterButton);
		flag = false;

		if (isDisplay.contains("not")) {
			if (findElements(GlobalPageElements.css_SavedFilterList).size() < 1) {
				flag = true;
			}
		}
		for (WebElement filter : findElements(GlobalPageElements.css_SavedFilterList)) {

			if (!isDisplay.contains("not")) {
				if (filter.getText().equalsIgnoreCase(savedFilter)) {
					flag = true;
					AutomationAssert.verifyTrue(filter + " not found in saved filters", true);
					break;
				}
				else {
					flag = false;
				}
			}
			else {
				flag = true;
				AutomationAssert.verifyTrue("Other Filter : " + filter.getText(), !filter.getText().equalsIgnoreCase(savedFilter));
			}
		}
		if (!flag)
			Assert.assertTrue(flag);
	}

	public void deleteSavedFilter() {
		clickElementAndWait(GlobalPageElements.btn_SavedFilterButton);

		flag = false;
		for (WebElement filter : findElements(GlobalPageElements.css_SavedFilterList)) {
			if (filter.getText().equalsIgnoreCase(savedFilter)) {
				clickElementAndWait(filter);
				clickElementAndWaitForElement(GlobalPageElements.btn_SavedFilterToggleButton, GlobalPageElements.lnk_SavedFilterDeleteButton);
				clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_SavedFilterDeleteButton, GlobalPageElements.ele_overLayProcess);
				flag = true;
				break;
			}
			else {
				flag = false;
			}
		}
		if (!flag)
			AutomationAssert.verifyTrue(savedFilter + "not found for deletion", flag);
	}

	/*** Status-POI & Recipient Org With Action Due Date ***/

	public void createMultipleFiltersForActiveTab(String filter, String filterType, String recipientOrgFilter, String actionDueDateFilter, int actionTotalWeek, String filterName) {
		savedFilter = filterName + epoch;
		waitForCompletePageLoad();
		/* Status-POI Filter */
		if (filter.contains(AdoddleCommonStringPool.OPT_POI))
			createSubFilter(filter);
		selectFilterWithSingleType(filter, filterType);

		/* Recipient Org Filter */
		createSubFilter(recipientOrgFilter);

		/* Action Due Date Filter */
		createSubFilter(actionDueDateFilter);
		clickElementAndWait(GlobalPageElements.btn_ActionDueDateFilterButton);
		editDateFilterData(actionTotalWeek);

		saveCreatedFilter();
		log.info("savedFilter : " + savedFilter);
		collectionDataMap.put("Saved Filter", savedFilter);

	}

	private void selectFilterWithSingleType(String filter, String filterType) {
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
		waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
		sendKeys(By.cssSelector("div[class*='filterui'] div[class*='filter-layer-box'][class*='active'] input[filterkey='" + filter + "']"), filterType);
		waitUntilElementIsDisplayed(By.xpath(".//div[@id='assignee-suggestions']//ul//li[contains(@class,'focus')]//a[@title='" + filterType + "']//input"));
		if (!isSelected(By.xpath(".//div[@id='assignee-suggestions']//ul//li[contains(@class,'focus')]//a[@title='" + filterType + "']//input")))
			clickElementAndWait(By.xpath(".//div[@id='assignee-suggestions']//ul//li[contains(@class,'focus')]//a[@title='" + filterType + "']//input"));
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
	}

	private void editDateFilterData(int dayCount) {
		if (!isSelected(GlobalPageElements.sel_DateFilterNextRadioButton))
			clickElementAndWait(GlobalPageElements.sel_DateFilterNextRadioButton);
		sendKeys(GlobalPageElements.txt_DateFilterNumericValuesInput, Integer.toString(dayCount));
		selectByVisibleText(GlobalPageElements.drp_DateFilterTimeZoneDropdown, "weeks");
		clickElementAndWait(GlobalPageElements.btn_DateFilterUpdateButton);
	}

	private void clickOnPopupButton(String buttonName) {
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//button[text()='" + buttonName + "']"));
	}

	private String getTotalFormsCount() {
		String forms;
		String formCount[] = getElementText(ProjectFormsTab.lbl_ProjectFormsTabFormsCount).split("of ");
		forms = formCount[1].replace(")", "");
		return forms;
	}

	private String getTotalFilesCount() {
		String folderFiles;
		String fileCount[] = getElementText(FilesTab.lbl_FilesTabFilesCount).split("of ");
		folderFiles = fileCount[1].replace(")", "");
		log.info("folder files :" + folderFiles);
		return folderFiles;
	}

	public void getAndVerifySelectedMultiFilterOrgsAndCountAndActiveTab(String statusFilter, String statusFilterType, String recipientOrgFilter, String activeTab) throws InterruptedException {
		waitForCompletePageLoad();
		List<WebElement> filterCountList;
		List<WebElement> filterValList;
		
		mouseHoverAndClickElement(DashboardTab.ele_PaiChartRootTracker, DashboardTab.ele_MaximizeGadget);
		waitUntilElementIsInvisible(DashboardTab.ele_MaximizeGadget);
		
		List<String> filterTypeList = new ArrayList<String>();
		for (WebElement filterType : findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList)) {
			filterTypeList.add(filterType.getText());
		}
		log.info("filterTypeList : " + filterTypeList);

		int j = 0;
		for (String filter1 : filterTypeList) {
			mouseHoverElement(DashboardTab.ele_PaiChartRootTracker);
			if (isDisplayed(DashboardTab.ele_MaximizeGadget))
				clickElementAndWaitForInvisibilityOfElement(DashboardTab.ele_MaximizeGadget, DashboardTab.ele_MaximizeGadget);

			if (!filterSelectedList.contains(filter1)) {
				filterSelectedList.add(filter1);

				filterCountList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeCountList);
				filterValList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList);

				int i = 0;
				for (WebElement filter2 : filterValList) {

					if (filter1.equalsIgnoreCase(filter2.getText())) {

						log.info("Selected Type : " + filter2.getText());
						String count = filterCountList.get(i).getText().split(": ")[1];
						filterTypeCountOnDashboard = Integer.parseInt(count);
						log.info("filterTypeCountOnDashboard : " + filterTypeCountOnDashboard);

						doubleClick(filter2);
						waitForCompletePageLoad();

						verifyAsiteMenuList(activeTab);
						if(activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {
							waitUntilElementIsDisplayed(FilesTab.lnk_FileName);
							waitUntilElementIsClickable(FilesTab.lnk_FileName);
						} else if(activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_PROJECT_FORMS)) {
							waitUntilElementIsDisplayed(ProjectFormsTab.lnk_FormListingFirstFormTitle);
							waitUntilElementIsClickable(ProjectFormsTab.lnk_FormListingFirstFormTitle);
						}
						verifySavedFilterAndListOfSavedFilter();

						for (String filter : Arrays.asList(statusFilter, recipientOrgFilter)) {

							clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
							waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
							for (WebElement subFilterCheckbox : findElements(GlobalPageElements.css_FilterDropdownSelectedTypeCheckboxList)) {
								log.info("subFilterCheckbox Actual : " + subFilterCheckbox.getAttribute("title"));
								if (!filter.contains("Recipient")) {
									log.info("subFilterCheckbox Expected : " + statusFilterType);
									Assert.assertTrue(subFilterCheckbox.getAttribute("title").contains(statusFilterType));
								}
								else {
									log.info("subFilterCheckbox Expected : " + filter1);
									Assert.assertTrue(subFilterCheckbox.getAttribute("title").contains(filter1));
								}
							}
							clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
						}

						String totalCount ="";
						if (savedFilter.contains("Form"))
							totalCount = getTotalFormsCount();
						else if (savedFilter.contains("Doc"))
							totalCount = getTotalFilesCount();

						filterTypeCountOnActiveTab = Integer.parseInt(totalCount);
						log.info("filterTypeCountOnActiveTab : " + filterTypeCountOnActiveTab);
						Assert.assertTrue("Dashboard count: " + filterTypeCountOnDashboard + " != " + filterTypeCountOnActiveTab + " on active tab", filterTypeCountOnDashboard == filterTypeCountOnActiveTab);
						break;
					}
					i++;
				}
				j++;
				if (j == 1)
					break;
			}
		}
	}

	public void clickOnLegendAndVerifyTrackerHideAndShow() {
		waitUntilListOfElementIsDisplayed(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList);
		verifyHideLegend(filterSelectedList.get(0));
		verifyShowLegend(filterSelectedList.get(0));
	}

	private void verifyHideLegend(String legendVal) {
		log.info("Hide Legend Val :" + legendVal);
		for (WebElement legend : findElements(By.cssSelector("div[id='playground'] svg g[class*='highcharts-legend'] g text[style*='#333333'] tspan"))) {
			log.info("Comparing Legend: " + legend.getText());
			if (legend.getText().contains(legendVal) && !legendVal.equalsIgnoreCase("")) {
				clickElementAndWait(legend);
				for (WebElement filterType : findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList)) {
					if(filterType.isDisplayed()) {
						log.info(filterType.getText() + " should not contain " +(legendVal));
						AutomationAssert.verifyTrue(filterType.getText() + " should not contain " +(legendVal), !filterType.getText().contains(legendVal));
					}
				}
				break;
			}
		}
	}

	private void verifyShowLegend(String legendVal) {
		List<String> legendList = new ArrayList<String>();

		for (WebElement legend : findElements(By.cssSelector("div[id='playground'] svg g[class*='highcharts-legend'] g tspan"))) {
			if (legend.getText().contains(legendVal)) {
				legend.click();
				waitForCompletePageLoad();
				for (WebElement filterType : findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList)) {
					legendList.add(filterType.getText());
				}
				Assert.assertTrue(legendList.contains(legendVal));
				break;
			}
		}
	}

	public void getAndVerifyFilterSelectedTypesAndCountOnTrackerWithActiveTab(String filter, String activeTab) throws InterruptedException {
		waitForCompletePageLoad();
		List<WebElement> filterCountList;
		List<WebElement> filterValList;
		mouseHover(DashboardTab.ele_PaiChartRootTracker);
		if (isDisplayed(DashboardTab.ele_MaximizeGadget)) {
			mouseHoverAndClickElement(DashboardTab.ele_MaximizeGadget, DashboardTab.ele_MaximizeGadget);
			waitUntilElementIsInvisible(DashboardTab.ele_MaximizeGadget);
		}

		waitUntilElementIsClickable(findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList).get(0));

		List<String> filterTypeList = new ArrayList<String>(Arrays.asList(filterType1, filterType2, filterType3));
		log.info("filterTypeList : " + filterTypeList);

		int j = 1;
		for (String filter1 : filterTypeList) {
			mouseHoverElement(DashboardTab.ele_PaiChartRootTracker);
			if (isDisplayed(DashboardTab.ele_MaximizeGadget))
				clickElementAndWaitForInvisibilityOfElement(DashboardTab.ele_MaximizeGadget, DashboardTab.ele_MaximizeGadget);

			filterCountList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeCountList);
			filterValList = findElements(DashboardTab.css_FilterChartGadgetHighchartsTrackerFilterTypeList);

			int i = 0;
			for (WebElement filter2 : filterValList) {

				if (filter2.getText().equalsIgnoreCase(filter1)) {

					log.info("Selected Filter Type : " + filter2.getText());
					String count = filterCountList.get(i).getText().split(": ")[1];
					filterTypeCountOnDashboard = Integer.parseInt(count);
					log.info("status count on dashboard : " + filterTypeCountOnDashboard);
					try {
						/*mouseHoverAndClickElement(filter2, filter2);*/
						doubleClick(filter2);
						waitUntilElementIsInvisible(filter2, 30);
					}
					catch(Throwable t) {
						log.error(eStringUtils.getClickFailureError(filter2));
					}
					if (isDisplayed(filter2))
						clickElementAndWait(filter2);
					verifyAsiteMenuList(activeTab);
					verifySavedFilterAndListOfSavedFilter();
					clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
					waitUntilElementIsDisplayed(GlobalPageElements.chk_FilterDropdownFirstSuggestedTypeCheckbox);
					for (WebElement subFilterCheckbox : findElements(GlobalPageElements.css_FilterDropdownSelectedTypeCheckboxList)) {
						Assert.assertTrue(subFilterCheckbox.getAttribute("title").contains(filter1));
					}
					clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));

					String totalCount = "";
					if (savedFilter.contains("Form"))
						totalCount = getTotalFormsCount();
					else if (savedFilter.contains("Doc"))
						totalCount = getTotalFilesCount();

					filterTypeCountOnActiveTab = Integer.parseInt(totalCount);
					log.info("statusCountOnFormsTab : " + filterTypeCountOnActiveTab);
					AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(filterTypeCountOnDashboard, filterTypeCountOnActiveTab), filterTypeCountOnDashboard == filterTypeCountOnActiveTab);

					if (j < filterTypeList.size()) {
						navigateTabByText("Dashboard");
						selectDashboardOnSwitchDashboardDropdown();
					}
					break;

				}
				else
					log.info("Filter comparison :" + filter2.getText() + " != " + filter1);
				i++;
			}
			j++;
		}
	}

	/*** Manage Dashboard with Project ***/

	public void verifyDashboardNotSetAsDefault() {
		Assert.assertTrue(!getElementText(DashboardTab.lbl_DashboardTitleLabel).contains(dashboardTitle));
	}

	public void assignPrevilegesToProjectAndUsers(String userB, String userC, String adminRole, String viewRole) {
		sharedDashboardToUsers(userB, userC, null);
		waitForCompletePageLoad();

		for (WebElement sharedVal : findElements(DashboardTab.css_PopDashboardSharedUserProjectList)) {
			clickElementAndWait(sharedVal);
			waitUntilListOfElementIsDisplayed(DashboardTab.css_PopDashboardSharedUserProjectPrevilegesList);
			if (sharedVal.getText().contains(userC))
				clickElementAndWait(By.xpath(".//body[contains(@id,'subscription')]//ul[@class='context-menu-list context-menu-root']//li[contains(@class,'permission')]//span[text()='" + viewRole + "']"));
			else
				clickElementAndWait(By.xpath(".//body[contains(@id,'subscription')]//ul[@class='context-menu-list context-menu-root']//li[contains(@class,'permission')]//span[text()='" + adminRole + "']"));
		}
	}

	public void assignPrevilegesToProjectUsers(String projectAccessRights, String userAccessRights) {
		waitForCompletePageLoad();
		for (WebElement sharedVal : findElements(DashboardTab.css_PopDashboardSharedUserProjectList)) {
			clickElementAndWait(sharedVal);
			waitUntilListOfElementIsDisplayed(DashboardTab.css_PopDashboardSharedUserProjectPrevilegesList);
			if (sharedVal.getText().contains(sharedProject))
				clickElementAndWait(By.xpath(".//body[contains(@id,'subscription')]//ul[@class='context-menu-list context-menu-root']//li[contains(@class,'permission')]//span[text()='" + projectAccessRights + "']"));
			else
				clickElementAndWait(By.xpath(".//body[contains(@id,'subscription')]//ul[@class='context-menu-list context-menu-root']//li[contains(@class,'permission')]//span[text()='" + userAccessRights + "']"));
		}
	}

	public void verifyAddGadgetButtonOnDashboard(String isDisplay) {
		waitForCompletePageLoad();
		if (isDisplay.contains("not"))
			Assert.assertTrue(!isDisplayed(DashboardTab.lnk_AddGadgetLink));
		else
			Assert.assertTrue(isDisplayed(DashboardTab.lnk_AddGadgetLink));
	}

	public void addSingleMultipleGadgetOnDashboard(String numberOfGadget) {
		clickElementAndWait(DashboardTab.lnk_AddGadgetLink);
		waitUntilListOfElementIsDisplayed(DashboardTab.css_PopAddGadgetHeaderList);

		int gadgetCount = findElements(DashboardTab.css_PopAddGadgetHeaderList).size();
		log.info("gadgetCount : " + gadgetCount);

		for (index = 0; index < gadgetCount; index++) {
			if (index != 0)
				clickOnAddGadgets();

			clickElementAndWait(By.xpath(".//div[@title='Add To Dashboard'][contains(@id,'" + index + "')]"));
			verifyPopUpWithText("Gadget");

			if (!findElement(DashboardTab.txt_PopGadgetTitleInput).getAttribute("value").contains("Report") && !findElement(DashboardTab.txt_PopGadgetTitleInput).getAttribute("value").contains("New Call Off")) {
				clear(DashboardTab.txt_PopGadgetTitleInput);
				String gadgetName = "AdminUserGadget" + dateUtils.getEpoch();
				getGadgetList.add(gadgetName);
				log.info("Added Gadget : " + getGadgetList);
				sendKeys(DashboardTab.txt_PopGadgetTitleInput, gadgetName);
				clear(DashboardTab.txt_PopGadgetDescriptionInput);
				sendKeys(DashboardTab.txt_PopGadgetDescriptionInput, "Admin User Gadget For Dashboard " + dateUtils.getEpoch());
				clickOnPopupButton(AdoddleCommonStringPool.BTN_ADD_TO_DASHBOARD);
				waitForCompletePageLoad();
				if (numberOfGadget.contains("One"))
					break;
				else {
					if (index == 2)
						break;
				}
			}
			else {
				log.info("Bug Gadget : " + findElement(DashboardTab.txt_PopGadgetTitleInput).getAttribute("value"));
				clickOnPopupButton("Cancel");
			}
		}
	}

	public void removeSingleGadgetOnDashboard() {
		for (WebElement gadgets : findElements(DashboardTab.css_DashboardGadgetsTitleList)) {
			if (gadgets.getText().contains(getGadgetList.get(0))) {
				mouseHoverWebElement(gadgets);
				clickElementAndWaitForElement(DashboardTab.btn_DeleteGadget, FilesTab.btn_PopContinueButton);
				clickElementAndWait(FilesTab.btn_PopContinueButton);
				getGadgetList.remove(getGadgetList.get(0));
				break;
			}
			else
				log.info("Checking next gadget");
		}
	}

	public void clickOnEditDashboard() {
		verifyCreatedDashboard();
		editDashboard();
	}

	public void performTestDataCleanUp(String dashboardType) {
		try {
			waitUntilElementIsDisplayed(DashboardTab.lbl_DashboardTitleLabel);
			if (!getElementText(DashboardTab.lbl_DashboardTitleLabel).contains(dashboardType)) {
				clickOnToolsAndSelectOption(AdoddleCommonStringPool.DASHBOARD_TOOLS, AdoddleCommonStringPool.OPT_MANAGE_DASHBOARDS);
				int dashboardIndex = 1;
				for (WebElement dashboardName : findElements(DashboardTab.css_ManageDashboardsPageDashboardNameList)) {

					log.info("Default Dashboard : " + dashboardName.getAttribute("title"));
					if (dashboardName.getAttribute("title").contains(dashboardType)) {
						clickElementAndWait(By.xpath(".//div[@id='manageDashboardTable']//tr[" + dashboardIndex + "]//a[@title='Set as a default Dashboard']"));
						log.info("Dashboard index : " + dashboardIndex);
						break;
					}
					dashboardIndex++;
				}
			}
			else
				log.info("TestData already cleaned up");
		}
		catch (Throwable t) {
			log.info("Failed to Cleanup TestData");
		}
	}

	/*** New Login & Set System Dashboard as Default Dashboard ***/

	public void loginAndSetDefaultDashboard(String username, String password, String systemDashboard) throws IOException {
		if (Tests_CommonStepDefinitions.runTest) {
			TestDriverFactory.getInstance().setUp(Tests_CommonStepDefinitions.browser);
			propertyUtils.setupEnvironmentTestProperties(AdoddleCommonAppMethods.dataCenter, username);
			loginInAdoddle(username, password);
		}
		performTestDataCleanUp(systemDashboard);
		navigateTabByText(AsiteMenu.Dashboard.toString());
	}
}