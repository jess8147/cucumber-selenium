package org.asite.automation.adoddle.p2.scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DashboardFavouriteFormsScripts extends AdoddleCommonAppMethods {
	private int				i, formsTabCount, dashboardWidgetCount;
	private boolean			flag;
	private String			parentWindow;
	private Boolean			isListPresent;
	private String			formTitle;
	final private static Logger	log					= AutomationLogger.getInstance().getLogger(DashboardFavouriteFormsScripts.class);
	final private List<String>	favouriteFormList	= new ArrayList<String>();

	/******* Set As Favourite Forms *******/
	public void clickOnDashboardWidgetHyperLink(String favFormWidgetLink) {
		waitForCompletePageLoad();
		if (isDisplayedLinkWithText(favFormWidgetLink))
			clickLinkWithText(favFormWidgetLink);
		else
			navigateTab(LandingPage.lnk_ProjectForms);
	}

	public void clickOnFormType(String formType, String folder) {
		clickElementWithText(folder);
		clickElementWithText(formType);
	}

	public void verifyFromCreationRights(String activeInactiveButton) {
		log.info(activeInactiveButton + " : " + isDisplayed(ProjectFormsTab.btn_CreateFormDisabledButton));
		if (activeInactiveButton.contains("disabled"))
			Assert.assertTrue(isDisplayed(ProjectFormsTab.btn_CreateFormDisabledButton));
		else
			Assert.assertTrue(!isDisplayed(ProjectFormsTab.btn_CreateFormDisabledButton));

	}

	public void addRemoveFavouriteForm(String form, String addRemoveFavourite) {
		contextClickWithText(form);
		clickContextMenuOptionWithText(addRemoveFavourite);
		favouriteFormList.add(form);
		collectionDataMap.put("favourite FormList", favouriteFormList.toString());
	}

	public void verifyFavouriteFormOnProjectFormsTab(String form, String setRemove) {
		waitForCompletePageLoad();
		log.info("Favourite Form on Files Tab : " + findElement(By.xpath(".//a[@title='" + form + "']//span[1][@class]")).getAttribute("class").contains("fav"));

		if (setRemove.contains("set"))
			Assert.assertTrue(findElement(By.xpath(".//a[@title='" + form + "']//span[1][@class]")).getAttribute("class").contains("fav"));
		else
			Assert.assertTrue(!findElement(By.xpath(".//a[@title='" + form + "']//span[1][@class]")).getAttribute("class").contains("fav"));
	}

	public void verifyFavouriteFormOnDashboardWidget(String form, String isDisplay, String addIcon) {
		waitForCompletePageLoad();
		log.info("favourite form on dashboard : " + isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item']//div[@title='" + form + "']")));

		flag = false;
		if (isDisplay.contains("not")) {
			Assert.assertTrue(!isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item']//div[@title='" + form + "']")));
		}
		else {
			Assert.assertTrue(isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item']//div[@title='" + form + "']")));

			i = javaUtils.resetIndex(i, 1);
			for (WebElement favForm : findElements(DashboardTab.css_DashboardFavouriteFormsList)) {
				if (favForm.getAttribute("title").contains(form)) {
					flag = true;
					log.info(addIcon + " : " + isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//i[contains(@class,'plus')]")));
					if (addIcon.contains("not"))
						Assert.assertTrue(!isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//i[contains(@class,'plus')]")));
					else
						Assert.assertTrue(isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//i[contains(@class,'plus')]")));
					break;
				}
				else
					log.info("flag :" + flag);
				i++;
			}
			if (!flag)
				Assert.assertTrue(flag);
		}
	}

	/*public void clickOnDashboardFavouriteFormWidget(String form) {
		clickElementAndWait(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item']//div[@title='" + form + "']"));
	}*/

	/******* Favourite Forms with Color Code "Green & Amber" *******/

	public void clickOnDashboardAddFormIcon(String form) {
		flag = false;
		waitForCompletePageLoad();
		i = javaUtils.resetIndex(i, 1);
		for (WebElement favForm : findElements(DashboardTab.css_DashboardFavouriteFormsList)) {
			if (favForm.getAttribute("title").contains(form)) {
				flag = true;
				clickElementAndWait(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//i[contains(@class,'plus')]"));
				break;
			}
			i++;
		}
		if (!flag)
			AutomationAssert.verifyTrue(form + " form type not found", flag);
	}

	final private List<String>	formCollectionList	= new ArrayList<String>();

	public void createForm(String form) {
		switchDefault();
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true"))
			Assert.assertTrue(getElementText(ProjectFormsTab.ele_BetaViewPopCreateFormFormtypeLabel).contains(form));
		/*else
			Assert.assertTrue(getElementText(ProjectFormsTab.ele_PopCreateFormFormtypeLabel).contains(form));*/

		formTitle = "AutoFavouriteForm" + dateUtils.getEpoch();
		formCollectionList.add(formTitle);
		collectionDataMap.put("FormTitle List", formCollectionList.toString());
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
			clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		/*}
		else
			clickElementAndWait(ProjectFormsTab.img_CreateFormCalendar);*/
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateFormCalendarCurrentDate, ProjectFormsTab.btn_CreateFormSendButton);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void verifyCreatedForm() {
		searchForms(formTitle);
		Assert.assertTrue(isDisplayed(ProjectFormsTab.lnk_FirstFormTitle));
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(formTitle));
	}

	public void clickOnDashboardFavouriteForm(String form) {
		flag = false;
		i = javaUtils.resetIndex(i, 1);
		for (WebElement favForm : findElements(DashboardTab.css_DashboardFavouriteFormsList)) {
			if (favForm.getAttribute("title").contains(form)) {
				flag = true;
				clickElementAndWait(favForm);
				break;
			}
			i++;
		}
		if (!flag)
			Assert.assertTrue(form + " form type not found", flag);
	}

	public void selectFormForDistribute(String option1, String option2) {
		searchForms(formTitle);
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);

		contextClickWithLink(formTitle);
		clickContextMenuOption(option1, option2);
	}

	public void assignFormActionsToUsers(String action1, String action2, String action3, String dueDays, String userId) {
		List<String> actionArrayList = new ArrayList<String>();
		actionArrayList.add(action1);

		if (action2 != null)
			actionArrayList.add(action2);
		if (action3 != null)
			actionArrayList.add(action3);

		i = javaUtils.resetIndex(i, 1);
		for (String action : actionArrayList) {
			sendKeys(DashboardTab.txt_RecentFormsDistributeFormToField, userId);
			sendKeys(DashboardTab.txt_RecentFormsDistributeFormToField, Keys.ENTER);
			clickElementAndWaitForElement(By.xpath(".//div[contains(@style,'block')]//ul[@class='select2-choices']//li[" + i + "]//button"), ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown);
			selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);

			if (!getElementText(FilesTab.lbl_PopDatePickerCurrentMonthLabel).contains(getCurrentMonth()))
				clickElementAndWait(FilesTab.lnk_PopDatePickerPreviousMonthLink);
			selectCustomizedDateFromCalendar(Integer.parseInt(dueDays));
			i++;
		}
		clickElementAndWait(ProjectFormsTab.btn_PopUpDistributeButtonDistribute);
	}

	public void getTotalIncompleteActionCountOfFormsTabFavouriteForm(String activeTab) {
		formsTabCount = getIncompleteActionsCountOfFormsTab();
		log.info(activeTab + " Count :" + formsTabCount);
	}

	public void verifyDashboardFavouriteFormActionsCountAndColorCode(String form, String color) {
		waitForCompletePageLoad();
		i = javaUtils.resetIndex(i, 1);
		for (WebElement favForm : findElements(DashboardTab.css_DashboardFavouriteFormsList)) {
			if (favForm.getAttribute("title").contains(form)) {
				flag = true;
				String count = getElementText(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]"));
				dashboardWidgetCount = Integer.parseInt(count);
				log.info("dashboardWidgetCount : " + dashboardWidgetCount);
				String colorCode = findElement(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]")).getAttribute("class");
				log.info("actual code : " + colorCode + " vs expected code : " + color);
				Assert.assertTrue(colorCode.contains(color));
				break;
			}
			else
				log.info("flag :" + flag);
			i++;
		}
		if (!flag) {
			log.info(form + " formtype not found");
			Assert.assertTrue(flag);
		}
		Assert.assertTrue(dashboardWidgetCount == formsTabCount);
	}

	private String getCurrentMonth() {
		DateFormat dateFormat = new SimpleDateFormat("MMM");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void searchBlankForm() {
		searchForms("");
	}

	public void setFilterWithSubFilter(String filter1, String subFilter1, String filter2, String subFilter2) {
		searchBlankForm();
		List<String> filterArrayList = new ArrayList<String>();
		List<String> subFilterArrayList = new ArrayList<String>();

		filterArrayList.add(filter1);
		subFilterArrayList.add(subFilter1);

		if (filter2 != null) {
			filterArrayList.add(filter2);
			subFilterArrayList.add(subFilter2);
		}

		i = javaUtils.resetIndex(i, 0);

		for (String filter : filterArrayList) {
			clickElementAndWait(ProjectFormsTab.btn_SearchCreateAppsFilterDropdown);
			selectFilter(filter);
			clickElementAndWait(By.xpath(".//div[contains(@id,'moreFilterCell')]//button[@filterkey='" + filter + "']"));
			selectFilter(subFilterArrayList.get(i));
			i++;
		}
	}

	private void selectFilter(String filter) {
		waitUntilElementIsDisplayed(FilesTab.txt_SearchCreateFilterInput);
		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterProjectClearAll, GlobalPageElements.lnk_FilterProjectDisableClear);

		sendKeys(FilesTab.txt_SearchCreateFilterInput, filter);

		if (filter.equalsIgnoreCase("Status"))
			clickElement(By.xpath(".//a[@title='" + filter + "']//input[@type='checkbox']"));
		else
			clickElement(By.xpath(".//a[contains(@title,'" + filter + "')]//input[@type='checkbox']"));

		if (isDisplayed(GlobalPageElements.lnk_FilterClose))
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
		else
			waitForCompletePageLoad();
	}

	private int getIncompleteActionsCountOfFormsTab() {
		int totalCount;
		isListPresent = findElements(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList).size() > 0;
		if (!isListPresent) {
			totalCount = 0;
		}
		else {
			executeJScript(AdoddleCommonJQueries.scrollDownJquery);
			waitForCompletePageLoad();
			totalCount = 0;
			for (WebElement actionPlusImg : findElements(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList)) {
				String plusVal = actionPlusImg.getText().trim().replace("+", "");
				if (plusVal.isEmpty()) {
					totalCount += 1;
				}
				else {
					totalCount += Integer.parseInt(plusVal) + 1;
				}
			}
			log.info("total action count: " + totalCount);

		}
		return totalCount;
	}

	/******* Perform Actions And verify Before And After Color *******/

	public void getDashboardFavouriteFormActionsCountWithColorAndClickOnActionsCount(String form, String color) {
		waitForCompletePageLoad();

		i = javaUtils.resetIndex(i, 1);

		for (WebElement favForm : findElements(DashboardTab.css_DashboardFavouriteFormsList)) {
			if (favForm.getAttribute("title").contains(form)) {
				flag = true;
				String colorCode = findElement(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]")).getAttribute("class");
				Assert.assertTrue("actual code : " + colorCode + ":::: expected code : " + color, colorCode.contains(color));
				String count = getElementText(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]"));
				dashboardWidgetCount = Integer.parseInt(count);
				log.info("dashboardWidgetCount : " + dashboardWidgetCount);
				clickElementAndWait(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]"));
				break;
			}

			i++;
		}
		if (!flag) {
			Assert.assertTrue(form + " formtype not found", flag);
		}
	}

	public void verifySetFiltersAndFilterSelection(String filter1, String subFilter1, String filter2, String subFilter2, String filter3, String subFilter3) {
		List<String> expectedFilterList = new ArrayList<String>();
		List<String> expectedSubFilterList = new ArrayList<String>();

		expectedFilterList.add(filter1);
		expectedSubFilterList.add(subFilter1);
		if (filter2 != null) {
			expectedFilterList.add(filter2);
			expectedSubFilterList.add(subFilter2);
		}
		if (filter3 != null) {
			expectedFilterList.add(filter3);
			expectedSubFilterList.add(subFilter3);
		}

		waitForCompletePageLoad();

		i = javaUtils.resetIndex(i, 0);

		for (WebElement filter : findElements(FilesTab.css_CreatedFiltersList)) {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(filter.getText(), expectedFilterList.get(i)), filter.getText().contains(expectedFilterList.get(i)));
			clickElementAndWait(filter);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.css_FilesTabFilterSelectedTypeList), expectedSubFilterList.get(i)), getElementText(FilesTab.css_FilesTabFilterSelectedTypeList).contains(expectedSubFilterList.get(i)));
			clickElementAndWait(filter);
			i++;
		}
	}

	public void verifyTotalIncompleteActionsCountOfFormsTabFavouriteForm(String tab) {
		waitForCompletePageLoad();
		getTotalIncompleteActionCountOfFormsTabFavouriteForm(tab);
		Assert.assertTrue(formsTabCount == dashboardWidgetCount);
	}

	public void getIncompleteActionsCountOfFormsTabFavouriteForm(String tab, String form) {
		waitForCompletePageLoad();
		clickElementWithText(form);
		getTotalIncompleteActionCountOfFormsTabFavouriteForm(tab);
	}

	/******* Filter Preferences & Favourite Forms *******/

	public void createFormWithDistribution(String project, String formtype, String formGroup, String userID, String action, String dueDays) {
		if (formGroup != null) {
			clickElementWithText(project);
			clickElementWithText(formGroup);
			clickElementWithText(formtype);
			clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		}
		distributeForm(userID, action, dueDays);
		createForm(formtype);
	}

	private void distributeForm(String userID, String action, String dueDays) {
		switchDefault();
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, userID);
			waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + userID + "')]"));
			clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + userID + "')]//preceding::input[@type='checkbox']"));
			waitUntilElementIsDisplayed(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown);
			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);

			if (action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION) && dueDays == null) {
				if (isDisplayed(ProjectFormsTab.img_BetaViewPopCreateFormClearSelectedUserDueDays))
					clickElementAndWait(ProjectFormsTab.img_BetaViewPopCreateFormClearSelectedUserDueDays);
			}
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		/*}
		else {
			clickElementAndWait(ProjectFormsTab.btn_PopCreateFormDistributeFormButton);
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, userID);
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, Keys.ENTER);
			clickElementAndWait(ModelsTab.btn_FirstDistributeUserDropdownToggleButton);
			selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
			if (action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION) && dueDays == null) {
				if (!isSelected(FilesTab.chk_PopDatePickerClearActionDaysCheckbox))
					clickElementAndWait(FilesTab.chk_PopDatePickerClearActionDaysCheckbox);
			}
			clickElementAndWait(FilesTab.lnk_PopDatePickerAssignedCurrentDate);
		}*/
	}

	public void verifyPreviousFormAndSearchFormInput() {
		String formInputValue = getElementAttributeValue(ProjectFormsTab.txt_FormListingFormSearchInput, "value");
		Assert.assertTrue("formInputValue : " + formInputValue, formInputValue.contains(formTitle));
		Assert.assertTrue(findElements(ProjectFormsTab.css_FormListingFormTitles).size() == 1);
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(formTitle));
	}

	public void clickOnDashboardFavouriteFormsIncompleteActionsCount(String formtype) {
		waitForCompletePageLoad();

		i = javaUtils.resetIndex(i, 1);

		for (WebElement favForm : findElements(DashboardTab.css_DashboardFavouriteFormsList)) {
			if (favForm.getAttribute("title").contains(formtype)) {
				flag = true;
				clickElementAndWait(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]"));
				break;
			}
			i++;
		}
		if (!flag)
			Assert.assertTrue(formtype + " form type not found", flag);
	}

	public void verifyResetSearchFormInputAndFormListing() {
		String formInputValue = getElementAttributeValue(ProjectFormsTab.txt_FormListingFormSearchInput, "value");
		Assert.assertTrue("form input value: " + formInputValue, formInputValue.isEmpty() && !formInputValue.contains(formTitle));
		Assert.assertTrue(findElements(ProjectFormsTab.css_FormListingFormTitles).size() > 1);
	}

	/******* Perform Actions Remove From Favourite *******/

	public void changeAllFormsStatus(String statusVal, String button) {
		isListPresent = findElements(ProjectFormsTab.css_FormListingFormTitles).size() > 1;
		log.info("multiple forms present :" + isListPresent);

		selectFormsForPerformForInfo("Edit", "Status");
		if (! isListPresent) {
			switchToSecondWindow();
			//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true"))
				changeBetaViewFormStatus(statusVal);
			/*else
				changeStatus(statusVal, button);*/
			closeSecondWindow();
		}
		else {
			changeStatus(statusVal, button);
			try {
				waitUtils.waitInterval(1);
			}
			catch (Throwable t) {
				log.info("failed for waitInterval");
			}
			selectFormsForPerformForInfo(AdoddleCommonStringPool.OPTION_TASKS, AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
			clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//a[text()='OK']"));
		}
		reloadPage();
	}

	public void selectFormsForPerformForInfo(String option1, String option2) {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(ProjectFormsTab.lnk_FirstFormTitle);

		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		clickContextMenuOption(option1, option2);
	}

	private void changeStatus(String statusVal, String button) {
		selectByVisibleText(ProjectFormsTab.sel_PopStatusChangeDropdown, statusVal);
		sendKeys(ProjectFormsTab.txt_PopChangeStatusReasonInput, javaUtils.getRandomString(20));
		clickOnPopupButton(button);
		waitUntilElementIsInvisible(ProjectFormsTab.sel_PopStatusChangeDropdown);
		waitForCompletePageLoad();
	}

	private void changeBetaViewFormStatus(String statusVal) {
		selectByVisibleText(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, statusVal);
		sendKeys(ProjectFormsTab.txt_BetaViewFormStatusChangeReasonNote, javaUtils.getRandomString(20));
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BetaViewPopFormStatusChangeChangeStatus, ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown);
		waitForCompletePageLoad();
	}

	private void switchToSecondWindow() {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	private void closeSecondWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
		waitForCompletePageLoad();
	}

	public void verifyActionsCountRemovedFromDashboardFavouriteForm(String form) {
		waitForCompletePageLoad();
		i = javaUtils.resetIndex(i, 1);
		for (WebElement favForm : findElements(DashboardTab.css_DashboardFavouriteFormsList)) {
			if (favForm.getAttribute("title").contains(form)) {
				flag = true;
				log.info("Count Removed :" + !isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]")));
				Assert.assertTrue(!isDisplayed(By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]")));
				break;
			}
			i++;
		}
		if (!flag)
			AutomationAssert.verifyTrue(form + " form type not found", flag);
	}

	private void clickOnPopupButton(String buttonName) {
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//button[text()='" + buttonName + "']"));
	}

}
