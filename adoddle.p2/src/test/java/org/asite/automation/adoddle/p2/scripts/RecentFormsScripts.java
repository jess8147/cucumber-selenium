/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RecentFormsScripts extends AdoddleCommonAppMethods {
	private int i, /* j, */beforeTodayCount, beforePastWeekCount, beforeYesterdayCount;
	private int afterLastLoginCount, afterTodayCount, afterPastWeekCount;
	private String /* formAction = "For Information", */formStatus, formUpdatedStatus, /*
																						 * formID
																						 * ,
																						 */statusSetColor,
			statusSetFont,/* createdForm, */attachDoc;

	private Boolean isListPresent;

	final private String[] createYesFormList = { "Create Yes Ass No Form", "Create Yes Ass Yes Form" };
	final private String[] createNoFormList = { "Create No Ass Yes Form", "Create No Ass No Form" };

	/* final private List<String> indexList = new ArrayList<String>(); */
	final private List<String> selectedFormList = new ArrayList<String>();
	final private List<String> popHoverActionList = new ArrayList<String>();
	final private List<String> numberOfOccurencesList = new ArrayList<String>();

	/*
	 * final private Map<Integer, String> KeyValMapList = new HashMap<Integer,
	 * String>();
	 */
	final private static Logger log = AutomationLogger.getInstance().getLogger(RecentFormsScripts.class);
	private String actionFormTitle = null;

	/****** Common Methods ******/

	public void clickOnHighchartsAppAxis(String axis) {
		if (axis.contains("LAST LOGIN"))
			mouseHoverAndClickElement(DashboardTab.ele_RecentFormsLastLoginAxis,
					DashboardTab.ele_RecentFormsLastLoginAxis);
		else if (axis.contains("TODAY"))
			mouseHoverAndClickElement(DashboardTab.ele_RecentFormsTodayAxis, DashboardTab.ele_RecentFormsTodayAxis);
		else if (axis.contains("PAST WEEK"))
			mouseHoverAndClickElement(DashboardTab.ele_RecentFormsPaskWeekAxis,
					DashboardTab.ele_RecentFormsPaskWeekAxis);
	}

	/*
	 * public void getFormListValue(int i) { indexList.add(Integer.toString(i));
	 * selectedFormList.add(getElementText(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'title')]//a[text()]"))); KeyValMapList.put(i,
	 * getElementText(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'title')]//a[text()]"))); }
	 */

	public void clearedListMapValues() {
		/* indexList.clear(); */
		selectedFormList.clear();
		/* KeyValMapList.clear(); */
	}

	public void contextClickAndSelectContextOption(String contextOption1, String contextOption2) {

		if (contextOption2.length() != 0) {
			contextClickAndSelectMenuOption();
			clickContextMenuOption(contextOption1, contextOption2);
		} else {
			if (!NewFilesPublishedScripts.flag1) {
				contextClickAndSelectMenuOption();
				clickContextMenuOption(contextOption1);
			}
		}
	}

	private void contextClickAndSelectMenuOption() {
		String formTitle;
		if (selectedFormList.isEmpty())
			formTitle = actionFormTitle;
		else
			formTitle = selectedFormList.get(0);
		log.info("ContextClick formTitle : " + formTitle);

		try {
			contextClick(By.xpath(".//div[@index]//div[contains(@class,'title')]//a[text()='" + formTitle + "']"));
		} catch (Throwable t) {
			contextClick(By.xpath(".//div[@index]//div[contains(@class,'title')]//a[contains(text(),'" + formTitle
					+ "')]"));
		}
	}

	public void deSelectCheckList(String highChartAxis) {
		clickOnHighchartsAppAxis(highChartAxis);
		clearedListMapValues();
		waitForCompletePageLoad();
		List<WebElement> formCheckList = findElements(DashboardTab.css_RecentFormsFormCheckList);

		for (WebElement checkbox : formCheckList) {
			if (checkbox.isSelected()) {
				clickElementAndWait(checkbox);
			}
		}
	}

	/* ********** Create New Form ********** */

	public void createForm(String action, String user, String project, String folderType, String formType) {
		if (folderType.length() != 0) {
			clickElementWithText(project);
			clickElementWithText(folderType);
			clickElementWithText(formType);
			clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		}
		enteredFormTitleAndDetails(action, user);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void createNewForm(String folderType, String formType, String action, String user) {
		if (folderType.length() != 0) {
			clickElementWithText(System.getProperty("global.test.project"));
			clickElementWithText(folderType);
			clickElementWithText(formType);
			clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		}
		if (!selectedFormList.isEmpty()) {
			if (selectedFormList.size() > 1)
				startWorkflowForm = selectedFormList.get(1);
			else
				startWorkflowForm = selectedFormList.get(0);
		}
		log.info("startWorkflowForm : " + startWorkflowForm);

		if (ResourceHandler.loadProperty("application.url").contains(AdoddleCommonStringPool.ENV_QA1_HOST)) {
			if (user.equalsIgnoreCase("Automation UKP2") || user.equalsIgnoreCase("Automation UKP"))
				user = ResourceHandler.loadProperty("primary.uk.username");
			else if (user.equalsIgnoreCase("Automation USP2") || user.equalsIgnoreCase("Automation USP"))
				user = ResourceHandler.loadProperty("primary.us.username");
		}
		enteredFormTitleAndDetails(action, user);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	private void enteredFormTitleAndDetails(String action, String user) {

		if (!action.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)
				&& !action.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_DISTRIBUTE)) {
			log.info(": Form List Cleared : " + action);
			selectedFormList.clear();
		}
		if (startWorkflowFlag)
			selectedFormList.clear();

		switchDefault();
		String formTitle = "AutomationRecentForm" + dateUtils.getEpoch();
		log.info("formTitle :" + formTitle);
		selectedFormList.add(formTitle);
		collectionDataMap.put("FormList", selectedFormList.toString());
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		distributeForm(action, user);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		log.info("selectedFormList :" + selectedFormList);
	}

	private void distributeForm(String action, String user) {
		log.info("Distributed User :" + user);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, user);
		waitUntilElementIsDisplayed(By
				.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + user
						+ "')]"));
		clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + user
				+ "')]//preceding::input[@type='checkbox']"));
		selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
	}

	/* ***************************************************************** */

	/* ********** Form Action Completed Part Verification ********** */

	public void verifyFormActionCompleted(String actionType) throws InterruptedException {
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.actionColWidthExpandQuery);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		int key = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

			if (getElementText(formTitle).equalsIgnoreCase(selectedFormList.get(0))) {
				waitForCompletePageLoad();
				mouseHoverElement(By.xpath(".//div[@index='" + key
						+ "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
				waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
				popHoverActionList.clear();
				for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
					if (!getElementText(popCompleteAction).split(":")[0].contains("FWD")) {
						popHoverActionList.add(getElementText(popCompleteAction).split(":")[1]);
					}
				}
				AutomationAssert.verifyTrue(
						eStringUtils.getContainsStringError(popHoverActionList.toString(), (actionType)),
						popHoverActionList.contains(actionType));
				mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount);

				isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
				if (isListPresent) {
					popHoverActionList.clear();
					for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
						if (!popHoverAction.getText().split(":")[0].contains("FWD")) {
							popHoverActionList.add(popHoverAction.getText().split(":")[1]);
						}
					}
					AutomationAssert.verifyTrue(!popHoverActionList.contains(actionType));
					mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount);
				} else
					AutomationAssert.verifyTrue(true);
				break;
			}
			key++;
		}
		actionFormTitle = selectedFormList.get(0);
		log.info("actionFormTitle : " + actionFormTitle);
		if (!actionType.contains(AdoddleCommonStringPool.ACTION_RESPOND))
			selectedFormList.remove(0);
		log.info("After Removeing Index 0 : " + selectedFormList);
	}

	public void verifyForInformationAction() throws InterruptedException {
		reloadPage();
		clickOnHighchartsAppAxis("PAST WEEK");
		verifyFormActionCompleted(AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
	}

	/* ***************************************************************** */

	/* ********** Form-Action Selection Part ********** */

	/*
	 * public void selectFormForPerformAction(String action) {
	 * waitForCompletePageLoad(); List<WebElement> formCheckList =
	 * findElements(DashboardTab.css_RecentFormsFormCheckList);
	 * executeJScript(AdoddleCommonJQueries.actionColWidthExpandQuery); i =
	 * javaUtils.resetIndex(i, 0); for (WebElement checkbox : formCheckList) {
	 * 
	 * if (isDisplayed(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {
	 * 
	 * mouseHover(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
	 * waitUntilElementIsDisplayed
	 * (GlobalPageElements.pop_firstActionsPopOverContent);
	 * 
	 * isListPresent =
	 * findElements(GlobalPageElements.css_firstActionsPopoverContentLinks
	 * ).size() > 0; if (isListPresent == true) { popHoverActionList.clear();
	 * for (WebElement popHoverAction :
	 * findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
	 * if (!popHoverAction.getText().split(":")[0].contains("FWD")) {
	 * popHoverActionList.add(popHoverAction.getText().split(":")[1]); } }
	 * log.info("Action List :" + popHoverActionList);
	 * 
	 * if (popHoverActionList.contains(action)) { getFormListValue(i);
	 * checkbox.click(); if (action.contains("Assign Status")) { formStatus =
	 * getElementText(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'status')]//div[text()]")); formID =
	 * getElementText(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'code')]//a[text()]"));
	 * log.info("formStatus--> :" + formStatus + " " + "formID--> :" + formID);
	 * } break; } else { log.info(i + ": " + action +
	 * " Not Found, Check Next Form");
	 * mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount); } } else {
	 * log.info(i + " : No actions Assigned, Check Next Form");
	 * mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount); } } else
	 * log.info(i + " : Check Next Form"); i++; } }
	 */

	public void selectFormForPerformAction(String action) {
		if (action.contains(AdoddleCommonStringPool.PERMISSION_VIEW))
			action = AdoddleCommonStringPool.ACTION_FOR_INFORMATION;

		waitForCompletePageLoad();
		List<WebElement> formCheckList = findElements(DashboardTab.css_RecentFormsFormCheckList);
		List<WebElement> formTitleList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
		executeJScript(AdoddleCommonJQueries.actionColWidthExpandQuery);

		i = javaUtils.resetIndex(i, 0);
		for (WebElement form : formTitleList) {
			if (form.getText().equalsIgnoreCase(selectedFormList.get(0))) {

				if (!action.contains("Start Workflow")) {
					mouseHover(By.xpath(".//div[@index='" + i
							+ "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

					popHoverActionList.clear();
					for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
						if (!popHoverAction.getText().split(":")[0].contains("FWD")) {
							popHoverActionList.add(popHoverAction.getText().split(":")[1]);
						}
					}
					log.info("Action List :" + popHoverActionList);
					AutomationAssert.verifyTrue(popHoverActionList.contains(action));
				}

				formCheckList.get(i).click();
				if (action.contains(AdoddleCommonStringPool.ACTION_ASSIGN_STATUS)) {
					formStatus = getElementText(By.xpath(".//div[@index='" + i
							+ "']//div[contains(@class,'status')][text()]"));
					log.info("formStatus : " + formStatus);
				}
				waitForCompletePageLoad();
				break;
			}
			i++;
		}
	}


	/****** LAST LOGIN ******/

	public void getTotalHighchartsAxisAppsCount(String lastLogin, String today, String yesterday, String pastWeek) {
		String[] highchartAxis = { lastLogin, today, yesterday, pastWeek };
		int beforeLastLoginCount;
		mouseHover(DashboardTab.ele_RecentFormsPortlet);
		for (String axis : highchartAxis) {
			if (axis.contains("LAST LOGIN")) {
				log.info("Last Login :" + isDisplayed(DashboardTab.ele_RecentFormsLastLoginAxis));
				mouseHover(DashboardTab.ele_RecentFormsHiddenLastLoginAxis);
				beforeLastLoginCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("beforeLastLoginCount :" + beforeLastLoginCount);
				collectionDataMap.put("beforeLastLoginCount", Integer.toString(beforeLastLoginCount));
			} else if (axis.contains("TODAY")) {
				mouseHover(DashboardTab.ele_RecentFormsHiddenTodayAxis);
				beforeTodayCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("beforeTodayCount :" + beforeTodayCount);
				collectionDataMap.put("beforeTodayCount", Integer.toString(beforeTodayCount));
			} else if (axis.contains("PAST WEEK")) {
				mouseHover(DashboardTab.ele_RecentFormsHiddenPaskWeekAxis);
				beforePastWeekCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("beforePastWeekCount :" + beforePastWeekCount);
				collectionDataMap.put("beforePastWeekCount", Integer.toString(beforePastWeekCount));
			} else if (axis.contains("YESTERDAY")) {
				mouseHover(DashboardTab.ele_RecentFormsHiddenYesterdayAxis);
				beforeYesterdayCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("beforeYesterdayCount :" + beforeYesterdayCount);
				collectionDataMap.put("beforeYesterdayCount", Integer.toString(beforeYesterdayCount));
			}
		}
	}

	private String getHighchartsAxisAppsCount() {
		return getElementText(DashboardTab.ele_RecentFormsMouseHoverFormCount).split(" Apps")[0];
	}

	private void distributeForm(String action) {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton, ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, System.getProperty("primary.username"));
		clickElementAndWait(By
				.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'"
						+ System.getProperty("primary.username") + "')]"));
		selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
	}

	private void enteredFormTitleAndDetails(String action) {
		String formTitle = "AutomationRecentForm" + dateUtils.getEpoch();
		log.info("formTitle :" + formTitle);
		selectedFormList.add(formTitle);
		collectionDataMap.put("FormList", selectedFormList.toString());
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		distributeForm(action);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		log.info("selectedFormList :" + selectedFormList);
		collectionDataMap.put("Form List", selectedFormList.toString());
	}

	public void createNewForm(String folderType, String formType, String action) {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(folderType);
		clickElementWithText(formType);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		enteredFormTitleAndDetails(action);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void verifyCreatedFormAndAssignedAction(String action) {
		searchForms(selectedFormList.get(0));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(selectedFormList.get(0)));

		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		String assignedFormAction = getElementText(GlobalPageElements.css_firstActionsPopoverContentLinks).split(":")[1];
		log.info("assignedFormAction :" + assignedFormAction);
		AutomationAssert.verifyTrue(assignedFormAction.contains(action));
	}

	public void verifyTotalHighchartsAxisAppsCount(String lastLogin, String today, String pastWeek, String yesterday) {
		String[] highchartAxis = { lastLogin, today, yesterday, pastWeek };
		int afterYesterdayCount;

		mouseHover(DashboardTab.ele_RecentFormsPortlet);
		for (String axis : highchartAxis) {
			if (axis.contains("LAST LOGIN")) {
				log.info("Last Login :" + isDisplayed(DashboardTab.ele_RecentFormsLastLoginAxis));
				mouseHover(DashboardTab.ele_RecentFormsLastLoginAxis);
				afterLastLoginCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("afterLastLoginCount :" + afterLastLoginCount);
				collectionDataMap.put("afterLastLoginCount", Integer.toString(afterLastLoginCount));
				Assert.assertEquals(afterLastLoginCount, 3);
			} else if (axis.contains("TODAY")) {
				mouseHover(DashboardTab.ele_RecentFormsTodayAxis);
				afterTodayCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("afterTodayCount :" + afterTodayCount);
				collectionDataMap.put("afterTodayCount", Integer.toString(afterTodayCount));
				AutomationAssert.verifyTrue(afterTodayCount > beforeTodayCount);
			} else if (axis.contains("PAST WEEK")) {
				mouseHover(DashboardTab.ele_RecentFormsPaskWeekAxis);
				afterPastWeekCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("afterPastWeekCount :" + afterPastWeekCount);
				collectionDataMap.put("afterPastWeekCount", Integer.toString(afterPastWeekCount));
				AutomationAssert.verifyTrue(afterPastWeekCount > beforePastWeekCount);
			} else if (axis.contains("YESTERDAY")) {
				mouseHover(DashboardTab.ele_RecentFormsHiddenYesterdayAxis);
				afterYesterdayCount = Integer.parseInt(getHighchartsAxisAppsCount());
				log.info("afterYesterdayCount :" + afterYesterdayCount);
				collectionDataMap.put("afterYesterdayCount", Integer.toString(afterYesterdayCount));
				AutomationAssert.verifyTrue(afterYesterdayCount == beforeYesterdayCount);
			}
		}
	}

	public void verifyFileListingAndCountWithHighcharts(String axis) {
		int formCount = getTotalFormsCount();

		if (axis.contains("LAST LOGIN")) {
			collectionDataMap.put("LAST LOGIN Forms Count", Integer.toString(formCount));
			Assert.assertEquals(afterLastLoginCount, formCount);
		} else if (axis.contains("TODAY")) {
			collectionDataMap.put("TODAY Forms Count", Integer.toString(formCount));
			Assert.assertEquals(afterTodayCount, formCount);
		} else if (axis.contains("PAST WEEK")) {
			collectionDataMap.put("PAST WEEK Forms Count", Integer.toString(formCount));
			Assert.assertEquals(afterPastWeekCount, formCount);
		}
	}

	private int getTotalFormsCount() {
		String forms;
		String formCount[] = getElementText(ProjectFormsTab.lbl_ProjectFormsTabFormsCount).split("of ");
		forms = formCount[1].replace(")", "");
		return Integer.parseInt(forms);
	}

	/*
	 * public int getTotalFormsListingCount() { int totalCount = 0; boolean flag
	 * = true; List<WebElement> formList = new ArrayList<WebElement>();
	 * 
	 * do { waitUntilListOfElementIsDisplayed(ProjectFormsTab.
	 * css_ProjectFormsTabFormTitleList);
	 * executeJScript(AdoddleCommonJQueries.scrollDownJquery); formList =
	 * findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
	 * totalCount += formList.size(); log.info("formsTotalCount :" +
	 * totalCount);
	 * 
	 * if (!isDisplayed(FilesTab.lnk_FilesTabDisableNextPage)) {
	 * log.info(": To be Continue :");
	 * clickElementAndWait(FilesTab.lnk_FilesTabEnableNextPage); } else {
	 * log.info(": Stop Here :"); flag = false; break; } } while (flag);
	 * 
	 * return totalCount; }
	 */

	public void verifyLastLoginAppsCountAfterLogin(int count) {
		mouseHover(DashboardTab.ele_RecentFormsPortlet);
		mouseHover(DashboardTab.ele_RecentFormsHiddenLastLoginAxis);
		collectionDataMap.put("LASTLOGIN Count without Form Creation", getHighchartsAxisAppsCount());
		Assert.assertEquals(count, Integer.parseInt(getHighchartsAxisAppsCount()));
	}

	public void selectMoreForms(String widgetType) {
		waitForCompletePageLoad();
		int j = 0;
		for (String formTitle1 : selectedFormList) {

			int i = 0;
			for (WebElement formTitle2 : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

				if (formTitle1.contains(formTitle2.getText())) {
					clickElementAndWait(By.xpath(".//div[@index='" + i
							+ "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
					j++;
					break;
				}
				i++;
				if (widgetType.contains(AdoddleCommonStringPool.PERMISSION_VIEW) && j == 2)
					break;
			}
		}
		log.info("selectedFormList for " + widgetType + " :" + selectedFormList);
	}

	/*
	 * public void verifyFlagWithForms(String flag) { waitForCompletePageLoad();
	 * for (Integer key : KeyValMapList.keySet()) { String value =
	 * findElement(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'flagTypeImageName')]//img"
	 * )).getAttribute("title"); log.info("value :" + value);
	 * log.info("Start With :" + value.startsWith(flag + ","));
	 * AutomationAssert.verifyTrue(value.startsWith(flag + ",")); } }
	 */

	public void verifyFlagWithForms(String flag) {
		waitForCompletePageLoad();
		List<WebElement> formTitleList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);

		for (String formTitle1 : selectedFormList) {

			int i = 0;
			for (WebElement formTitle2 : formTitleList) {

				if (formTitle1.contains(formTitle2.getText())) {

					String value = findElement(
							By.xpath(".//div[@index='" + i + "']//div[contains(@class,'flagTypeImageName')]//img"))
							.getAttribute("title");
					log.info("value :" + value);
					log.info("Start With :" + value.startsWith(flag + ","));
					AutomationAssert.verifyTrue(value.startsWith(flag + ","));
					break;
				}
				i++;
			}
		}
		waitForCompletePageLoad();
	}

	/****** View & For-Information Actions Widget ******/

	/*
	 * public void selectFormForPerformForInformationAction(String widget) {
	 * waitForCompletePageLoad(); i = javaUtils.resetIndex(i, 0); j =
	 * javaUtils.resetIndex(j, 0); List<WebElement> formCheckList =
	 * findElements(DashboardTab.css_RecentFormsFormCheckList);
	 * 
	 * for (WebElement checkbox : formCheckList) { if
	 * (isDisplayed(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"))) {
	 * mouseHover(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
	 * waitUntilElementIsDisplayed
	 * (GlobalPageElements.pop_firstActionsPopOverContent);
	 * 
	 * isListPresent =
	 * findElements(GlobalPageElements.css_firstActionsPopoverContentLinks
	 * ).size() > 0; if (isListPresent == true) { for (WebElement popHoverAction
	 * : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
	 * if (!popHoverAction.getText().split(":")[0].contains("FWD")) {
	 * popHoverActionList.add(popHoverAction.getText().split(":")[1]); } }
	 * log.info("popHoverActionList :" + popHoverActionList);
	 * 
	 * if (popHoverActionList.contains(formAction)) { checkbox.click();
	 * getFormListValue(i);
	 * 
	 * if (widget.contains("For Information")) { j++; if (j == 2) break; } else
	 * break; } else log.info(": " + formAction + " not Found :");
	 * 
	 * } else log.info(": Check Next Form :");
	 * 
	 * } else log.info("There are No Actions assigned to given Form"); i++; } }
	 */

	public void switchToSecondWindow() {
		CreateModelCommentScripts.parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void verifyFormViewed() {
		switchToSecondWindow();
		waitForCompletePageLoad();
		String viewForm;
		viewForm = getElementText(ProjectFormsTab.ele_BetaViewViewFormTitle);
		log.info("viewForm :" + viewForm);
		AutomationAssert.verifyTrue(viewForm.contains(selectedFormList.get(0)));
		waitUntilElementIsDisplayed(ProjectFormsTab.ele_BetaViewViewFormDirectLink);
		waitUntilElementIsClickable(ProjectFormsTab.ele_BetaViewViewFormDirectLink);
		waitUntilElementIsDisplayed(ProjectFormsTab.ele_BetaViewViewFormContent);
		waitForCompletePageLoad();
		/*try {
			log.info("Waiting for 10 seconds server response");
			waitInterval(10);
			log.info("Wait completed of 10 seconds server response");
		} catch (Throwable t) {
			log.info(": WaitInterval Failed :");
		}*/
		takeScreenShot(TestDriverFactory.scenario);
	}

	public void verifySelectedFormsOnActionPopup() {
		List<WebElement> popFileList;
		List<String> fileList = new ArrayList<String>();
		popFileList = findElements(ModelsTab.css_PopAssociateFormsFormTitleList);
		for (WebElement fileName : popFileList)
			fileList.add(fileName.getText());
		log.info("fileList :" + fileList);
		AutomationAssert.verifyTrue(fileList.containsAll(selectedFormList));
	}

	/****** Status-Change & Status-History Widget ******/

	public void verifyChangeStatusPopup(String popTxt) {
		switchToSecondWindow();
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel), (popTxt)), getElementText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel).contains(popTxt));
	}

	public void verifyFormCurrentStatusIDAndTitle() {
		log.info(": Not Displayed Details in BetaView :");
	}

	public void selectFormStatusAndEnteredNotes() {
		selectByIndex(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, 2);
		formUpdatedStatus = new Select(findElement(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown))
				.getFirstSelectedOption().getText();
		log.info("changedStatus :" + formUpdatedStatus);
		if (formUpdatedStatus.equalsIgnoreCase(formStatus)) {
			selectByIndex(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, 3);
			formUpdatedStatus = new Select(findElement(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown))
					.getFirstSelectedOption().getText();
		}
		log.info("formUpdatedStatus :" + formUpdatedStatus);
		sendKeys(ProjectFormsTab.txt_BetaViewFormStatusChangeReasonNote, javaUtils.getRandomString(20));
		clickElementAndWait(ProjectFormsTab.btn_BetaViewPopFormStatusChangeChangeStatus);

		try {
			waitUntilElementIsDisplayed(ProjectFormsTab.msg_BetaViewFormActionCompletedSuccessfulMessage);
			waitUntilElementIsInvisible(ProjectFormsTab.msg_BetaViewFormActionCompletedSuccessfulMessage);
		} catch (Throwable t) {
			log.info(": Failed to Displayed Action Complete Message :");
		}
	}

	public void verifyUpdatedStatusOnViewForm() {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(ProjectFormsTab.btn_BetaViewViewFormUpdatedStatus);
		try {
			waitUntilElementContainsText(ProjectFormsTab.ele_BetaViewViewFormUpdatedStatus, formUpdatedStatus);
		} catch (Throwable t) {
			log.info("Failed For Waiting Status Changes");
		}
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_BetaViewViewFormUpdatedStatus), formUpdatedStatus), getElementText(ProjectFormsTab.ele_BetaViewViewFormUpdatedStatus).contains(formUpdatedStatus));
		waitForCompletePageLoad();
	}

	/*
	 * public void verifyUpdatedStatusOnRecentForm() {
	 * waitForCompletePageLoad();
	 * 
	 * for (Integer key : KeyValMapList.keySet()) { log.info("Actual Status :" +
	 * getElementText(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]")));
	 * log.info(" Expected Status :" + formUpdatedStatus);
	 * AutomationAssert.verifyTrue("Actual Status :" +
	 * getElementText(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]")) +
	 * " != Expected Status :" + formUpdatedStatus,
	 * getElementText(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]"
	 * )).equalsIgnoreCase(formUpdatedStatus)); } }
	 */

	public void verifyUpdatedStatusOnRecentForm() {
		waitForCompletePageLoad();
		int key = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

			if (formTitle.getText().equalsIgnoreCase(selectedFormList.get(0))) {
				log.info("Actual Status :"
						+ getElementText(By.xpath(".//div[@index='" + key
								+ "']//div[contains(@class,'status')][text()]")));
				log.info(" Expected Status :" + formUpdatedStatus);
				AutomationAssert.verifyTrue(
						"Actual Status :"
								+ getElementText(By.xpath(".//div[@index='" + key
										+ "']//div[contains(@class,'status')][text()]")) + " != Expected Status :"
								+ formUpdatedStatus,
						getElementText(
								By.xpath(".//div[@index='" + key + "']//div[contains(@class,'status')][text()]"))
								.equalsIgnoreCase(formUpdatedStatus));
				break;
			}
			key++;
		}
	}

	public void verifyAllHistoryWithSelectType(String historyStatus) {
		List<WebElement> historyList = findElements(FilesTab.css_HistoryTabFilesFormsHistoryRowList);
		for (WebElement hisType : historyList) {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(hisType.getText(), historyStatus), hisType.getText().contains(historyStatus));
		}
	}

	public void verifyUpdatedStatusValueOnStatusHistoryPage() {
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_BetaViewViewFormHistoryTabFirstRowStatusRemarks),
				formUpdatedStatus), getElementText(ProjectFormsTab.ele_BetaViewViewFormHistoryTabFirstRowStatusRemarks).contains(
				formUpdatedStatus));
	}

	public void verifyViewFormActivateTab(String activeTab) {
		switchToSecondWindow();
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel).contains(activeTab));
	}

	public void verifyViewFormSelectedHistoryDropdown(String historyType) {
		String selectedHistotyDropdown;
		selectedHistotyDropdown = new Select(findElement(ProjectFormsTab.drp_BetaViewHistoryTabHistoryTypeDropdown))
				.getFirstSelectedOption().getText();
		log.info("selectedHistotyDropdown :" + selectedHistotyDropdown);
		AutomationAssert.verifyTrue(selectedHistotyDropdown.contains(historyType));
	}

	/****** Share Distribute Widget ******/

	/*
	 * public void clickOnActionHyperLink(String action) { for (Integer key :
	 * KeyValMapList.keySet()) { clickElement(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
	 * mouseHover(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
	 * waitUntilElementIsDisplayed
	 * (GlobalPageElements.pop_firstActionsPopOverContent);
	 * 
	 * for (WebElement popHoverAction :
	 * findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
	 * if (popHoverAction.getText().contains(action)) { popHoverAction.click();
	 * break; } } } if(action.contains(AdoddleCommonStringPool.ACTION_RESPOND))
	 * respondFlag = true; }
	 */

	public void clickOnActionHyperLink(String action) {
		int key = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {
			if (formTitle.getText().equalsIgnoreCase(selectedFormList.get(0))) {
				waitForCompletePageLoad();
				mouseHoverElement(By.xpath(".//div[@index='" + key
						+ "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
				waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

				for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
					if (popHoverAction.getText().contains(action)) {
						popHoverAction.click();
						break;
					}
				}
				break;
			}
			key++;
		}
	}

	public void assignActionToUser(String action, String user) {
		sendKeys(DashboardTab.txt_RecentFormsDistributeFormToField, user);
		sendKeys(DashboardTab.txt_RecentFormsDistributeFormToField, Keys.ENTER);
		clickElementAndWait(FilesTab.btn_DistributePopSingleActionToggleButton);
		selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
		clickElementAndWait(FilesTab.lnk_PopDatePickerAssignedCurrentDate);
	}

	/*
	 * public void verifyFormActionAssigned(String actionType) {
	 * waitForCompletePageLoad(); for (Integer key : KeyValMapList.keySet()) {
	 * clickElement(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"
	 * )); mouseHover(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"
	 * ));
	 * waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent
	 * );
	 * 
	 * popHoverActionList.clear(); isListPresent =
	 * findElements(GlobalPageElements
	 * .css_firstActionsPopoverContentLinks).size() > 0; if (isListPresent ==
	 * true) { for (WebElement popHoverAction :
	 * findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
	 * if (!popHoverAction.getText().split(":")[0].contains("FWD")) {
	 * popHoverActionList.add(popHoverAction.getText().split(":")[1]); } }
	 * AutomationAssert.verifyTrue(popHoverActionList.contains(actionType)); } else
	 * AutomationAssert.verifyTrue(isListPresent); } }
	 */

	public void verifyFormActionAssigned(String action) {
		waitForCompletePageLoad();
		int key = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

			if (formTitle.getText().equalsIgnoreCase(actionFormTitle)) {

				clickElement(By.xpath(".//div[@index='" + key
						+ "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
				mouseHover(By.xpath(".//div[@index='" + key
						+ "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
				waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

				popHoverActionList.clear();
				isListPresent = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0;
				if (isListPresent) {
					for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
						if (!popHoverAction.getText().split(":")[0].contains("FWD")) {
							popHoverActionList.add(popHoverAction.getText().split(":")[1]);
						}
					}
					AutomationAssert.verifyTrue(popHoverActionList.contains(action));
				} else
					AutomationAssert.verifyTrue("Action Pop over links not found", false);
				break;
			}
			key++;
		}
	}

	public void verifyDistributionOnHistoryPage() {
		log.info(": Not Implemented in BetaView :");
	}

	/****** For Acknowledgement Hyperlink ******/

	public void selectCheckboxOfDashboardActionPopup() {
		if (!isSelected(DashboardTab.chk_PopDashboardActionsCheckbox))
			clickElementAndWait(DashboardTab.chk_PopDashboardActionsCheckbox);
	}

	public void clickOnSubmitButtonOfDashboardActionPopup() {
		clickElementAndWait(DashboardTab.btn_PopDashboardActionsSubmitButton);
	}

	/****** For Action Hyperlink ******/

	public void enterTextareaOfDashboardActionPopup() {
		sendKeys(DashboardTab.txt_PopDashboardActionsTextareaInput,
				javaUtils.getRandomString(20) + JavaUtils.getRandomNumber(5));
	}

	/****** Attach Docs Hyperlink ******/

	public void formReplyWithoutAttachment() {
		enteredFormReplayField();
		mouseHover(ProjectFormsTab.btn_CreateFormSendButton);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void formReplyWithAttachment() throws InterruptedException {
		enteredFormReplayField();
		sysUtils.authenticateRemoteMachine(nodeIP);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		CreateEditPOIScripts.createFile1 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("created file as: " + CreateEditPOIScripts.createFile1);
		List<String> fileList = sysUtils.getFileList(CreateEditPOIScripts.createFile1);
		collectionDataMap.put("Attachment", fileList.toString());
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		clickElementAndWaitForElement(ModelsTab.btn_PopModelCommentAttachmentsAttachButton,
				ProjectFormsTab.btn_CreateFormSendButton);
		clickAndHoldElement(ProjectFormsTab.btn_CreateFormSendButton);
		waitUtils.waitInterval(1);
		releaseElementClick(ProjectFormsTab.btn_CreateFormSendButton);
		waitForCompletePageLoad();
		if (isDisplayed(ProjectFormsTab.btn_CreateFormSendButton)) {
			clickAndHoldElement(ProjectFormsTab.btn_CreateFormSendButton);
			waitUtils.waitInterval(1);
			releaseElementClick(ProjectFormsTab.btn_CreateFormSendButton);
		}
		switchDefault();
	}

	private void enteredFormReplayField() {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton,
				ProjectFormsTab.btn_BetaViewViewFormActionDropdownReplyAllButton);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormActionDropdownReplyAllButton);
		waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
		sendKeys(ProjectFormsTab.txt_RespondFormRespondMessageInput, "RespondMessage" + dateUtils.getEpoch());
		clear(DashboardTab.txt_RespondFormGroupCodeInput);
		sendKeys(DashboardTab.txt_RespondFormGroupCodeInput, Keys.TAB);
		waitForCompletePageLoad();
	}

	public void verifyFormAttachmentOnViewForm() {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(ProjectFormsTab.btn_BetaViewViewFormAttachmentAssociationButton);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormAttachmentAssociationButton);
		AutomationAssert.verifyTrue(getElementText(
				ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList).contains(
				strUtils.extractFileNameString(CreateEditPOIScripts.createFile1)));
	}

	/****** Respond Hyperlink ******/

	public void createRespondForm(String folderType, String formType, String action, String userA, String userB,
			String userC) {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(folderType);
		clickElementWithText(formType);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		enteredFormTitleAndDetailsForMultipleUser(action, userA, userB, userC);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	private void enteredFormTitleAndDetailsForMultipleUser(String action, String userA, String userB, String userC) {
		String formTitle = "AutomationRecentForm" + dateUtils.getEpoch();
		log.info("formTitle :" + formTitle);
		selectedFormList.add(formTitle);
		collectionDataMap.put("Form Title List", selectedFormList.toString());
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		distributeFormToMultipleUsers(action, userA, userB, userC);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		waitUntilElementIsClickable(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		log.info("selectedFormList :" + selectedFormList);
	}

	public void distributeFormToMultipleUsers(String action, String userA, String userB, String userC) {
		List<String> userList = new ArrayList<String>();

		if (userA != null)
			userList.add(userA);
		if (userB != null)
			userList.add(userB);
		if (userC != null)
			userList.add(userC);

		String[] userArray = userList.toArray(new String[userList.size()]);

		if(!isDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo))
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton, ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);

		for (String user : userArray) {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, user);
			if(!isSelected(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox))
				clickElementAndWaitForElement(ProjectFormsTab.chk_BetaViewCreateRFIFormInputUserCheckbox,
						ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown);
			selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, action);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		}
	}

	/*
	 * public void selectFormForPerformRespondAction() {
	 * waitForCompletePageLoad(); indexList.clear(); KeyValMapList.clear();
	 * 
	 * List<WebElement> formTitleList =
	 * findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList); i =
	 * javaUtils.resetIndex(i, 0); for (WebElement webElement : formTitleList) {
	 * if (webElement.getText().contains(selectedFormList.get(0))) {
	 * clickElementAndWait(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
	 * indexList.add(Integer.toString(i)); KeyValMapList.put(i,
	 * getElementText(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'title')]//a[text()]")));
	 * 
	 * clickElement(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
	 * mouseHover(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
	 * waitUntilElementIsDisplayed
	 * (GlobalPageElements.pop_firstActionsPopOverContent); break; } i++; }
	 * log.info("KeyValMapList value :" + KeyValMapList); }
	 */

	public void verifyRespondFormPrePopulatedUsers(String user1, String user2, String user3) {
		List<String> actualUserList = new ArrayList<String>();
		List<String> expectedUserList = new ArrayList<String>();
		expectedUserList.add(user1);
		if (user2.length() != 0)
			expectedUserList.add(user2);
		if(user3.length() != 0)
			expectedUserList.add(user3);

		waitForCompletePageLoad();
		waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewRespondFormPrePopulatedUserList);

		List<WebElement> prePopUserList = findElements(ProjectFormsTab.css_BetaViewRespondFormPrePopulatedUserList);
		for (WebElement user : prePopUserList) {
			actualUserList.add(user.getText());
		}
		log.info("actualUserList :" + actualUserList);
		log.info("expectedUserList :" + expectedUserList);
		AutomationAssert.verifyTrue(expectedUserList.size() == actualUserList.size());
		AutomationAssert.verifyTrue(expectedUserList.containsAll(actualUserList));
	}

	public void enteredRespondFormDetails() throws InterruptedException {
		sendKeys(ProjectFormsTab.txt_RespondFormRespondMessageInput, "RespondMessage" + dateUtils.getEpoch());
		clear(DashboardTab.txt_RespondFormGroupCodeInput);
		waitForCompletePageLoad();
		mouseHover(ProjectFormsTab.btn_CreateFormSendButton);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void verifyRespondFormCompleted(String msgStatus) {
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(ProjectFormsTab.ele_BetaViewFormViewLoading);
		waitUntilElementIsDisplayed(DashboardTab.txt_RespondFormMessageStatusInput);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(
				findElement(DashboardTab.txt_RespondFormMessageStatusInput).getAttribute("value"), (msgStatus)),
				findElement(DashboardTab.txt_RespondFormMessageStatusInput).getAttribute("value").contains(msgStatus));
	}

	/****** Customize Status Widget ******/

	public void createCustomizeStatusForm(String folderType, String formType, String customizeStatus) {
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(folderType);
		clickElementWithText(formType);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		String formTitle = "AutomationRecentForm" + dateUtils.getEpoch();
		log.info("formTitle :" + formTitle);
		selectedFormList.add(formTitle);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		selectByVisibleText(ProjectFormsTab.sel_CreateFormStatusDropdown, customizeStatus);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		log.info("selectedFormList :" + selectedFormList);
	}

	public void verifyCustomizeStatusForm(String customizeStatus) {
		searchForms(selectedFormList.get(0));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(selectedFormList.get(0)));
		String actualStatus = getElementText(ProjectFormsTab.ele_FirstFormStatus);
		log.info("actualStatus :" + actualStatus);
		AutomationAssert.verifyTrue(actualStatus.contains(customizeStatus));
	}

	/*
	 * public void selectFormForPerformCustomizeStatusWidget() {
	 * waitForCompletePageLoad(); indexList.clear(); KeyValMapList.clear();
	 * 
	 * List<WebElement> formTitleList =
	 * findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList); i =
	 * javaUtils.resetIndex(i, 0); for (WebElement webElement : formTitleList) {
	 * if (webElement.getText().contains(selectedFormList.get(0))) {
	 * clickElementAndWait(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
	 * 
	 * formStatus = getElementText(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'status')]//div[text()]"));
	 * log.info("formStatus :" + formStatus);
	 * indexList.add(Integer.toString(i)); KeyValMapList.put(i,
	 * getElementText(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'title')]//a[text()]"))); break; } i++; }
	 * log.info("KeyValMapList value :" + KeyValMapList); }
	 */

	public void selectFormForPerformCustomizeStatusWidget() {
		waitForCompletePageLoad();
		int i = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

			if (formTitle.getText().contains(selectedFormList.get(0))) {
				clickElementAndWait(By.xpath(".//div[@index='" + i
						+ "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
				formStatus = getElementText(By.xpath(".//div[@index='" + i
						+ "']//div[contains(@class,'status')][text()]"));
				log.info("formStatus :" + formStatus);
				break;
			}
			i++;
		}
	}

	public void setFormStatusFontAndColor(String color, String font, String applyToRow) {
		AutomationAssert.verifyTrue(findElement(FilesTab.txt_PopManageDocStatusActiveStatusInput).getAttribute("value").contains(
				formStatus));
		clickElementAndWaitForElement(FilesTab.img_PopManageDocStatusActiveStatusSettingButton,
				ProjectsTab.img_PopManageDocStatusSettingsBgColor);
		clickElementAndWaitForElement(ProjectsTab.img_PopManageDocStatusSettingsBgColor,
				ProjectsTab.txt_PopBGColorPickerRedInput);

		if (color.equalsIgnoreCase("red")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		} else if (color.equalsIgnoreCase("green")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		}

		clickElementAndWait(ProjectsTab.btn_PopBGColorPickerOKButton);

		if (applyToRow.equalsIgnoreCase("yes"))
			clickElementAndWait(ProjectsTab.rad_DocStatusSettingsApplyToRecord);
		else if (applyToRow.equalsIgnoreCase("no"))
			clickElementAndWait(ProjectsTab.rad_DocStatusSettingsApplyToCell);

		clickElementAndWait(ProjectsTab.lnk_DocStatusSettingsFont);
		sendKeys(ProjectsTab.txt_DocStatusSettingsFontSearch, font);
		clickElementAndWaitForElement(ProjectsTab.lbl_DocStatusSettingsFontSearchResult, ProjectsTab.btn_DocStatusSettingsSave);
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_DocStatusSettingsSave, ProjectsTab.btn_DocStatusSettingsSave);
	}

	/*
	 * public void verifyFormStatusFontAndColor(String statusColor, String
	 * statusFont, String cellRecordFlag) { if
	 * (statusColor.equalsIgnoreCase("red")) statusColor = "rgb(255, 0, 0)";
	 * else if (statusColor.equalsIgnoreCase("green")) statusColor =
	 * "rgb(0, 255, 0)"; statusSetColor = statusColor; statusSetFont =
	 * statusFont; log.info("statusSetColor :" + statusSetColor + " , " +
	 * "statusSetFont :" + statusSetFont);
	 * 
	 * waitForCompletePageLoad(); for (Integer key : KeyValMapList.keySet()) {
	 * 
	 * if (isSelected(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']")))
	 * clickElementAndWait(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
	 * mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount);
	 * 
	 * AutomationAssert.verifyTrue(getElementText(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]"
	 * )).contains(formStatus)); if (cellRecordFlag.equalsIgnoreCase("yes")) {
	 * AutomationAssert.verifyTrue(findElement(By.xpath(
	 * ".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" +
	 * key + "']")).getAttribute("style") + " should contain font-family:" +
	 * statusFont, findElement(By.xpath(
	 * ".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" +
	 * key + "']")).getAttribute("style").contains(statusFont));
	 * AutomationAssert.verifyTrue(findElement(By.xpath(
	 * ".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" +
	 * key + "']")).getAttribute("style") + " should contain background-color: "
	 * + statusColor, findElement(By.xpath(
	 * ".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" +
	 * key + "']")).getAttribute("style").contains(statusColor));
	 * 
	 * } else if (cellRecordFlag.equalsIgnoreCase("no")) {
	 * mouseHover(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]"));
	 * AutomationAssert.verifyTrue(findElement(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]")).getAttribute("style")
	 * + " should contain font-family: '" + statusFont + "'",
	 * findElement(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]"
	 * )).getAttribute("style").contains(statusFont));
	 * AutomationAssert.verifyTrue(findElement(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]")).getAttribute("style")
	 * + " should contain background-color: " + statusColor,
	 * findElement(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]"
	 * )).getAttribute("style").contains(statusColor)); } } }
	 */

	public void verifyFormStatusFontAndColor(String statusColor, String statusFont, String cellRecordFlag) {
		if (statusColor.equalsIgnoreCase("red"))
			statusColor = "rgb(255, 0, 0)";
		else if (statusColor.equalsIgnoreCase("green"))
			statusColor = "rgb(0, 255, 0)";
		statusSetColor = statusColor;
		statusSetFont = statusFont;
		log.info("statusSetColor :" + statusSetColor + " , " + "statusSetFont :" + statusSetFont);

		waitForCompletePageLoad();
		int key = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

			if (formTitle.getText().equalsIgnoreCase(selectedFormList.get(0))) {

				if (isSelected(By.xpath(".//div[@index='" + key
						+ "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']")))
					clickElementAndWait(By.xpath(".//div[@index='" + key
							+ "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
				mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount);

				AutomationAssert.verifyTrue(getElementText(
						By.xpath(".//div[@index='" + key + "']//div[contains(@class,'status')][text()]"))
						.contains(formStatus));
				if (cellRecordFlag.equalsIgnoreCase("yes")) {
					AutomationAssert.verifyTrue(
							findElement(
									By.xpath(".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='"
											+ key + "']")).getAttribute("style")
									+ " should contain font-family:" + statusFont,
							findElement(
									By.xpath(".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='"
											+ key + "']")).getAttribute("style").contains(statusFont));
					AutomationAssert.verifyTrue(
							findElement(
									By.xpath(".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='"
											+ key + "']")).getAttribute("style")
									+ " should contain background-color: " + statusColor,
							findElement(
									By.xpath(".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='"
											+ key + "']")).getAttribute("style").contains(statusColor));

				} else if (cellRecordFlag.equalsIgnoreCase("no")) {
					mouseHover(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'status')][text()]"));
					AutomationAssert.verifyTrue(
							findElement(
									By.xpath(".//div[@index='" + key
											+ "']//div[contains(@class,'status')][text()]")).getAttribute("style")
									+ " should contain font-family: '" + statusFont + "'",
							findElement(
									By.xpath(".//div[@index='" + key
											+ "']//div[contains(@class,'status')][text()]")).getAttribute("style")
									.contains(statusFont));
					AutomationAssert.verifyTrue(
							findElement(
									By.xpath(".//div[@index='" + key
											+ "']//div[contains(@class,'status')][text()]")).getAttribute("style")
									+ " should contain background-color: " + statusColor,
							findElement(
									By.xpath(".//div[@index='" + key
											+ "']//div[contains(@class,'status')][text()]")).getAttribute("style")
									.contains(statusColor));
				}
				break;
			}
			key++;
		}
	}

	/*
	 * public void verifyCustomizedStatusSetAsDefault() {
	 * waitForCompletePageLoad(); for (Integer key : KeyValMapList.keySet()) {
	 * 
	 * if (isSelected(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']")))
	 * clickElementAndWait(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
	 * mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount);
	 * 
	 * AutomationAssert.verifyTrue(getElementText(By.xpath(".//div[@index='" + key +
	 * "']//div[contains(@class,'status')]//div[text()]"
	 * )).contains(formStatus));
	 * AutomationAssert.verifyTrue("Status should contain font-family:" + statusSetFont,
	 * !findElement(By.xpath(
	 * ".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" +
	 * key + "']")).getAttribute("style").contains(statusSetFont));
	 * AutomationAssert.verifyTrue("Status should contain background-color: " +
	 * statusSetColor, !findElement(By.xpath(
	 * ".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" +
	 * key + "']")).getAttribute("style").contains(statusSetColor)); } }
	 */

	public void verifyCustomizedStatusSetAsDefault() {
		waitForCompletePageLoad();
		int key = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

			if (formTitle.getText().equalsIgnoreCase(selectedFormList.get(0))) {

				if (isSelected(By.xpath(".//div[@index='" + key
						+ "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']")))
					clickElementAndWait(By.xpath(".//div[@index='" + key
							+ "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
				mouseHover(ProjectFormsTab.lbl_ProjectFormsTabFormsCount);

				AutomationAssert.verifyTrue(getElementText(
						By.xpath(".//div[@index='" + key + "']//div[contains(@class,'status')][text()]"))
						.contains(formStatus));
				AutomationAssert.verifyTrue(
						"Status should contain font-family:" + statusSetFont,
						!findElement(
								By.xpath(".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" + key
										+ "']")).getAttribute("style").contains(statusSetFont));
				AutomationAssert.verifyTrue(
						"Status should contain background-color: " + statusSetColor,
						!findElement(
								By.xpath(".//div[@id='docListingSection']//div[@id='adTableBody']//div[@index='" + key
										+ "']")).getAttribute("style").contains(statusSetColor));
			}
			key++;
		}
	}

	/******* Create Form & Start Workflow widget *******/

	public void verifyCreateFormPopup() {
		waitUntilElementIsDisplayed(ProjectFormsTab.pop_CreateFormWindow);
	}

	/*
	 * public void selectFormForPerformNewFormWidget() {
	 * waitForCompletePageLoad(); List<WebElement> formIDList =
	 * findElements(ProjectFormsTab.css_ProjectFormsTabFormIDList); i =
	 * javaUtils.resetIndex(i, 0); for (WebElement formID : formIDList) { if
	 * (!formID.getText().contains("DRAFT")) {
	 * clickElementAndWait(By.xpath(".//div[@index='" + i +
	 * "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
	 * getFormListValue(i); break; } i++; } }
	 */

	private boolean startWorkflowFlag = false;
	private String startWorkflowForm = null;

	public void selectFormForPerformNewFormWidget() {
		waitForCompletePageLoad();
		List<WebElement> formIDList = findElements(ProjectFormsTab.css_ProjectFormsTabFormIDList);
		i = javaUtils.resetIndex(i, 0);
		for (WebElement formID : formIDList) {
			if (!formID.getText().contains("DRAFT")) {
				clickElementAndWait(By.xpath(".//div[@index='" + i
						+ "']//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']"));
				selectedFormList.add(getElementText(By.xpath(".//div[@index='" + i
						+ "']//div[contains(@class,'title')]//a[text()]")));
				collectionDataMap.put("Selected Form", selectedFormList.toString());
				break;
			}
			i++;
		}
	}

	public void verifyViewFormType() {
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_PopCreateFormNameSearchFilter);
		verifyFormAccessList("no", createNoFormList, "");
		verifyFormAccessList("yes", createYesFormList, "");
	}

	private void verifyFormAccessList(String isListed, String[] arrayList, String accessForm) {
		for (String formType : arrayList) {
			sendKeys(ProjectFormsTab.txt_PopCreateFormNameSearchFilter, formType);
			if (isListed.equalsIgnoreCase("yes")) {
				if (accessForm.length() != 0) {
					if (formType.equalsIgnoreCase(accessForm))
						AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.ele_PopCreateFormFirstFormName));
					else
						AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.ele_PopCreateFormFirstFormName));
				} else
					AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.ele_PopCreateFormFirstFormName));
			} else
				AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.ele_PopCreateFormFirstFormName));
		}
	}

	public void verifyCreatedFormOnRecentFormsListing() {
		boolean flag = false;
		reloadPage();
		navigateTabByText(AdoddleCommonStringPool.TAB_DASHBOARD);
		clickOnHighchartsAppAxis("PAST WEEK");
		waitForCompletePageLoad();
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {
			if (formTitle.getText().contains(selectedFormList.get(0))) {
				flag = true;
				break;
			}
		}
		if (!flag)
			AutomationAssert.verifyTrue("Form listing doesn not contain form :" + selectedFormList.get(0), false);
	}

	public void verifyStartWorkflowFormType(String accessForm) {
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_PopCreateFormNameSearchFilter);
		verifyFormAccessList("no", createNoFormList, "");
		verifyFormAccessList("yes", createYesFormList, accessForm);
		clearedListMapValues();
	}

	public void attachExternalDocInForm() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		attachDoc = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("file created as: " + attachDoc);
		collectionDataMap.put("Form Attachment", attachDoc);
		List<String> fileList = sysUtils.getFileList(attachDoc);
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
		startWorkflowFlag = true;
	}

	public void verifyAssociationAndAttachmentViaStartWorkflow(String attachments, String forms) {
		String[] headerLinkNameList = { attachments, forms };

		int key = 0;
		for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

			if (formTitle.getText().equalsIgnoreCase(selectedFormList.get(0))) {

				mouseHoverAndClickAssociations(key);
				waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);

				for (String link : headerLinkNameList) {
					clickElementAndWait(By.xpath(".//div[@id='pageLayoutHeader']//div[@id]//a[contains(text(),'" + link
							+ "')]"));

					if (getElementText(ModelsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains(attachments)) {
						log.info("actual Attachment : "
								+ getElementText(ModelsTab.css_PopAttachmentsAndAssociationsAttachmentsTabFileNameList));
						log.info("expected Attachment : " + strUtils.extractFileNameString(attachDoc));
						AutomationAssert.verifyTrue(getElementText(
								ModelsTab.css_PopAttachmentsAndAssociationsAttachmentsTabFileNameList).contains(
								strUtils.extractFileNameString(attachDoc)));
					} else if (getElementText(ModelsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains(forms)) {
						log.info("actual Association : " + getElementText(ModelsTab.css_PopAssociateFormsFormTitleList));
						log.info("expected Association : " + startWorkflowForm);
						AutomationAssert.verifyTrue(getElementText(ModelsTab.css_PopAssociateFormsFormTitleList).contains(
								startWorkflowForm));
					}
				}
			}
			key++;
		}
	}

	private void mouseHoverAndClickAssociations(int i) {
		mouseHover(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'attachmentImageName')]//img"));
		clickElementAndWait(FilesTab.pop_AppsPopOverContent);
	}

	/* *************************************************************************** */

	/******* Forms Tab Create Reply \ ReplyAll *******/

	public void verifyCreatedRespondForm() {
		searchForms(selectedFormList.get(0));
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(selectedFormList.get(0)));
	}

	public void verifyCreatedFormWithAction(String action) {
		verifyCreatedRespondForm();
		mouseHoverElement(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		List<String> actionList = new ArrayList<String>();
		for (WebElement assignedAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks))
			actionList.add(assignedAction.getText().split(":")[1]);
		log.info("assignedFormAction :" + actionList);
		numberOfOccurencesList.clear();
		numberOfOccurencesList.addAll(actionList);
		AutomationAssert.verifyTrue(actionList.contains(action));
	}

	public void verifySearchedFormActionCompleted(String action) {
		waitForCompletePageLoad();
		searchForms(selectedFormList.get(0));
		mouseHoverAndClickElement(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList,
				GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		popHoverActionList.clear();
		for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
			if (!popCompleteAction.getText().split(":")[0].contains("FWD"))
				popHoverActionList.add(popCompleteAction.getText().split(":")[1]);
		}
		AutomationAssert.verifyTrue(popHoverActionList.contains(action));

		if (findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0) {
			popHoverActionList.clear();
			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				if (!popHoverAction.getText().split(":")[0].contains("FWD"))
					popHoverActionList.add(popHoverAction.getText().split(":")[1]);
			}
			AutomationAssert.verifyTrue(!popHoverActionList.contains(action));
		} else
			AutomationAssert.verifyTrue(true);
	}
	
	
	/******* Added New *******/
	
	public void verifySameActionsCount(String action, int count)
	{
		int occurences = Collections.frequency(numberOfOccurencesList, action);
		log.info("occurences : "+ occurences);
		Assert.assertTrue("Actual :"+occurences +" != "+"Expected :"+count, occurences==count);
	}
	
	public void verifyActionCompletionIncompletion(String resType, String oriType, String action) throws InterruptedException
	{
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.actionColWidthExpandQuery);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		if(getElementText(GlobalPageElements.lnk_ActiveTab).equalsIgnoreCase(AsiteMenu.Project_Forms.toString())) {
			verifyCreatedRespondForm();
			mouseHoverElement(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		}
		else
		{
			int key = 0;
			for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {

				if (getElementText(formTitle).equalsIgnoreCase(selectedFormList.get(0))) {
					waitForCompletePageLoad();
					mouseHoverElement(By.xpath(".//div[@index='" + key + "']//div[contains(@class,'actions-actionName')]//span[@title=''][text()]"));
				}
			}
		}
		
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		
		boolean incompleteActionFlag = false, completeActionFlag = false;;
		if(findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0)
		{
			for (WebElement assignedAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				if(assignedAction.getText().contains(AdoddleCommonStringPool.ACTION_RESPOND)) {
					incompleteActionFlag = true;
					AutomationAssert.verifyTrue("ORI :"+assignedAction.getText().split(":")[0]+" not Contains "+oriType,assignedAction.getText().split(":")[0].contains(oriType));
					AutomationAssert.verifyTrue("Action :"+assignedAction.getText().split(":")[1]+" not Contains "+action,assignedAction.getText().split(":")[1].contains(action));
				}
			}
			if(!incompleteActionFlag)
				Assert.assertTrue("ORI type Respond action not displayed...", false);
		}
		else
			Assert.assertTrue("There is no incomplete actions displayed...", false);
		
		
		if(findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList).size() > 0)
		{
			for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
				if(popCompleteAction.getText().contains(AdoddleCommonStringPool.ACTION_RESPOND)) {
					completeActionFlag = true;
					AutomationAssert.verifyTrue("RES :"+popCompleteAction.getText().split(":")[0]+" not Contains "+resType,popCompleteAction.getText().split(":")[0].contains(resType));
					AutomationAssert.verifyTrue("Action :"+popCompleteAction.getText().split(":")[1]+" not Contains "+action,popCompleteAction.getText().split(":")[1].contains(action));
				}
			}
			if(!completeActionFlag)
				Assert.assertTrue("RES type Respond action not displayed...", false);
		}
		else
			Assert.assertTrue("There is no completed actions displayed...", false);
	}

}