package org.asite.automation.adoddle.p2.scripts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DistributeFormDocumentWithWorkingCalendarScripts extends AdoddleCommonAppMethods {
	private String			activeTab;
	private String			parentWindow			= null;
	private static String	additionalHolidayDate	= null;
	final private static String holidayEventType 	= "Holiday" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	final private List<String>	holidayList			= new ArrayList<String>();
	final private static Logger	log					= AutomationLogger.getInstance().getLogger(DistributeFormDocumentWithWorkingCalendarScripts.class);
	
	public void setHolidayInWorkspaceWorkingCalendar(String holiday1, String holiday2) {
		setWorkspaceWorkingDays(holiday1, holiday2, null, null, null, null, null);
		clickOnSaveOnEditProjectPopup();
	}

	public void setWorkspaceWorkingDays(String holiday1, String holiday2, String holiday3, String holiday4, String holiday5, String holiday6, String holiday7) {
		waitForCompletePageLoad();
		for (WebElement dayCheck : findElements(ProjectsTab.chk_PopEditProjectWorkingDaysList)) {
			for (String holiday : Arrays.asList(holiday1, holiday2, holiday3, holiday4, holiday5, holiday6, holiday7)) {
				if (holiday != null && dayCheck.getAttribute("name").contains(holiday)) {
					holidayList.add(holiday);
					if (dayCheck.isSelected())
						clickElementAndWait(dayCheck);
					break;
				}
				else if (!dayCheck.isSelected())
					clickElementAndWait(dayCheck);
			}
		}
		collectionDataMap.put("Working Cal holidayList", holidayList.toString());
		log.info("holidayList : " + holidayList);
	}

	private void clickOnSaveOnEditProjectPopup() {
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditProjectEdit, ProjectsTab.btn_PopEditProjectEdit);
	}

	public void selectProjectFolderAndAddFiles(String project, String folder) {
		clickElementWithText(project);
		collectionDataMap.put("Working Calendar TestData Folder", folder);
		clickElementWithText(folder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);
	}

	public void enterDistributeGroup(String distributeGroup) {
		waitForCompletePageLoad();
		collectionDataMap.put("working calender distribution group", distributeGroup);

		if (activeTab.contains(AdoddleCommonStringPool.TAB_FILES)) {
			sendKeys(GlobalPageElements.txt_FileFormDistributeToFieldInput, distributeGroup);
			clickElementAndWait(By.xpath(".//div[@id='select2-drop'][contains(@style,'display: block')]//ul//li//div[not(text())]//span[text()='" + distributeGroup + "']"));
		}
		else {
				sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, distributeGroup);
				waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[starts-with(text(),'" + distributeGroup + "')]"));
				if (distributeGroup.equalsIgnoreCase(ResourceHandler.loadProperty("primary.user.org")))
					clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[text()='" + distributeGroup + "']//preceding::input[@type='checkbox'][1]"));
				else
					clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + distributeGroup + "')]//preceding::input[@type='checkbox']"));
		}
	}

	public void verifyUploadedFileWithWorkingCalendar(String activeTab) throws ParseException {
		verifyUploadedFile();
		validateActionAndDueDateInAuditTrail(activeTab);
	}

	public void verifyCreatedFormWithWorkingCalendar(String activeTab) throws ParseException {
		verifyCreatedForm();
		validateActionAndDueDateInAuditTrail(activeTab);
	}

	public void verifyCreatedFormWithAllWorkingDays(String activeTab) throws ParseException {
		verifyCreatedForm();
		validateActionAndDueDateInAuditTrailWithAllWorkingDays(activeTab);
	}

	private void verifyUploadedFile() {
		searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
		AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FileName).equalsIgnoreCase(strUtils.extractFileNameString(CreateEditRoleScripts.createFile)));
	}

	private void verifyCreatedForm() {
		searchForms(FormIncompleteActionsScripts.formTitle);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).equalsIgnoreCase(FormIncompleteActionsScripts.formTitle));
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

	private String getDateFormat() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_UK;
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_US;
		else
			return AdoddleCommonStringPool.DATE_FORMAT_AUS;
	}

	/*** With Additional Holiday ***/

	public void addAdditionalHolidayInWorkingCalendar() {
		String calFormat = findElement(ProjectsTab.txt_PopEditProjectAdditionalHolidayInput).getAttribute("placeholder");
		log.info("calFormat : " + calFormat);

		if (calFormat.equalsIgnoreCase("MMM dd, yyyy"))
			calFormat = AdoddleCommonStringPool.DATE_FORMAT_US;

		if (additionalHolidayDate == null) {
			additionalHolidayDate = DateUtils.addDaysToDate(calFormat, timezoneID, 5);
			sendKeys(ProjectsTab.txt_PopEditProjectAdditionalHolidayInput, additionalHolidayDate);
			sendKeys(ProjectsTab.txt_PopEditProjectHolidayEvent, holidayEventType);
			clickOnPopupButton("Add Holiday");
		}
		log.info("additionalHolidayDate : " + additionalHolidayDate);
		clickOnSaveOnEditProjectPopup();
	}

	public void contextClickAndSelectOption(String project, String option) {
		contextClickWithLink(project);
		clickContextMenuOption(option);
	}

	public void verifyWorkingDaysAndHolidayListOnWorkingCalendar() {
		for (WebElement workingDay : findElements(ProjectsTab.css_PopWorkingCalendarWorkingDaysList)) {
			for (String holiday : holidayList)
				AutomationAssert.verifyTrue(!workingDay.getText().equalsIgnoreCase(holiday));
		}
		AutomationAssert.verifyTrue(getElementText(ProjectsTab.ele_PopWorkingCalendarAdditionalHolidayDate).equalsIgnoreCase(additionalHolidayDate));
		AutomationAssert.verifyTrue(getElementText(ProjectsTab.ele_PopWorkingCalendarHolidayEvent).equalsIgnoreCase(holidayEventType));
		clickOnPopupButton("x");
	}

	private void clickOnPopupButton(String buttonName) {
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//button[text()='" + buttonName + "']"));
	}

	private void validateActionAndDueDateInAuditTrail(String activeTab) {
		String expectedDueDate;
		String dateFormat = getDateFormat();
		Boolean flag = false;

		if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {
			searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
			contextClick(FilesTab.lnk_FileName);
		}
		else {
			searchForms(FormIncompleteActionsScripts.formTitle);
			contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		}
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_HISTORY, AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		switchToSecondWindow();


		List<WebElement> auditTrailActionList;

		if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {

			if (fileBetaViewFlag) {
				waitUntilElementContainsText(FilesTab.lbl_BetaViewViewFileActiveTabLabel, AdoddleCommonStringPool.OPTION_HISTORY);
				clickElementAndWait(FilesTab.btn_BetaViewViewFileMaximizeTab);
				auditTrailActionList = findElements(GlobalPageElements.css_BetaViewViewFileActionDistributionRecords);
			}
			else {
				clickElementAndWait(GlobalPageElements.ele_FileFormHistoryFirstRecord);
				auditTrailActionList = findElements(GlobalPageElements.css_FileFormViewActionDistributionRecords);
			}
		}
		else {
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel, AdoddleCommonStringPool.OPTION_HISTORY);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormMaximizeTab);
			auditTrailActionList = findElements(GlobalPageElements.css_BetaViewViewFormActionDistributionRecords);
		}

		for (WebElement web : auditTrailActionList) {

			String user, action, actionDueDate, actionSatus;

			if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {

				if (fileBetaViewFlag) {
					user = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionUser));
					action = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionAction));
					actionSatus = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionActionSatus));
					actionDueDate = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionActionDueDate));
				}
				else {
					user = web.findElement(FilesTab.ele_ViewFileActionDistributionUser).getAttribute("title");
					action = getElementText(web.findElement(FilesTab.ele_ViewFileActionDistributionAction));
					actionDueDate = getElementText(web.findElement(FilesTab.ele_ViewFileActionDistributionActionDueDate));
					actionSatus = getElementText(web.findElement(FilesTab.ele_ViewFileActionDistributionActionSatus));
				}
			}
			else {
				user = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionUser));
				action = getElementText(web.findElement(ProjectFormsTab.ele_BetaViewViewFormActionDistributionAction));
				actionSatus = getElementText(web.findElement(ProjectFormsTab.ele_BetaViewViewFormActionDistributionActionSatus));
				actionDueDate = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionActionDueDate));
			}
			log.info("Expected User, Action, Action-date and Action-status : " + user + " : " + action + " : " + actionDueDate + " : " + actionSatus);

			if (user.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {
				if (action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION))
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 15);
				else {
					expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 6);
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
				}
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))) {

				if (action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION))
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 15);
				else {
					expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 7);
					if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
						AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));
					else
						AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_ATTACH_DOCS));
				}
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))) {
				expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 8);

				if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.FOR_COMMENT_COORDINATION));
				else
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_RESPOND));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))) {
				expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 9);

				if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));
				else
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_DISTRIBUTE));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {
				expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 10);
				AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name"))) {
				expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 11);

				if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
				else
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_ASSIGN_STATUS));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("secondary.uk.user.name")) || user.contains(ResourceHandler.loadProperty("secondary.us.user.name"))) {

				if (action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION))
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
				else {
					expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 5);
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
				}
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.uk.wcu.user.name")) || user.contains(ResourceHandler.loadProperty("test.us.wcu.user.name"))) {
				expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
				AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}
			else if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.AUS_DC)) {

				if (user.contains(ResourceHandler.loadProperty("secondary.aus.user.name"))) {

					if (action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION))
						expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
					else {
						expectedDueDate = DateUtils.addDaysInWorkspaceWorkingCalendar(holidayList, additionalHolidayDate, dateFormat, timezoneID, 5);
						AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
					}
					verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
					flag = true;
				}

				else if (user.contains(ResourceHandler.loadProperty("test.aus.wcu.user.name"))) {
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
					verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
					flag = true;
				}
			}
		}
		AutomationAssert.verifyTrue("Failure While validating HistoryRecords", flag);
		closeSecondWindow();
	}

	private void validateActionAndDueDateInAuditTrailWithAllWorkingDays(String activeTab) {
		String expectedDueDate = null;
		String dateFormat = getDateFormat();
		Boolean flag = false;

		if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {
			searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
			contextClick(FilesTab.lnk_FileName);
		}
		else {
			searchForms(FormIncompleteActionsScripts.formTitle);
			contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		}
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_HISTORY, AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		switchToSecondWindow();

		List<WebElement> auditTrailActionList;

		if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {
			clickElementAndWait(GlobalPageElements.ele_FileFormHistoryFirstRecord);
			auditTrailActionList = findElements(GlobalPageElements.css_FileFormViewActionDistributionRecords);
		}
		else {
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewViewFormActiveTabLabel, AdoddleCommonStringPool.OPTION_HISTORY);
			clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormMaximizeTab);
			auditTrailActionList = findElements(GlobalPageElements.css_BetaViewViewFormActionDistributionRecords);
		}

		for (WebElement web : auditTrailActionList) {

			String user, action, actionDueDate, actionSatus;

			if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {
				user = web.findElement(FilesTab.ele_ViewFileActionDistributionUser).getAttribute("title");
				action = getElementText(web.findElement(FilesTab.ele_ViewFileActionDistributionAction));
				actionDueDate = getElementText(web.findElement(FilesTab.ele_ViewFileActionDistributionActionDueDate));
				actionSatus = getElementText(web.findElement(FilesTab.ele_ViewFileActionDistributionActionSatus));
			}
			else {
				user = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionUser));
				action = getElementText(web.findElement(ProjectFormsTab.ele_BetaViewViewFormActionDistributionAction));
				actionSatus = getElementText(web.findElement(ProjectFormsTab.ele_BetaViewViewFormActionDistributionActionSatus));
				actionDueDate = getElementText(web.findElement(FilesTab.ele_BetaViewViewFileActionDistributionActionDueDate));
			}
			log.info("Expected String : " + user + action + actionDueDate + actionSatus);

			if (user.contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {

				if (action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION))
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 15);
				else {
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 6);
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
				}
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))) {

				if (action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION))
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 15);
				else {
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 7);
					if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
						AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));
					else
						AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_ATTACH_DOCS));
				}
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name"))) {
				expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 8);

				if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.FOR_COMMENT_COORDINATION));
				else
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_RESPOND));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))) {
				expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 9);

				if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));
				else
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_DISTRIBUTE));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {
				expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 10);
				AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.user.dc.bloggs.name"))) {
				expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 11);

				if (activeTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
				else
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_ASSIGN_STATUS));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("secondary.uk.user.name")) || user.contains(ResourceHandler.loadProperty("secondary.us.user.name"))) {

				if (action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION)) {
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
				}
				else {
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 5);
					AutomationAssert.verifyTrue("Expected Action But was" + action, action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
				}
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (user.contains(ResourceHandler.loadProperty("test.uk.wcu.user.name")) || user.contains(ResourceHandler.loadProperty("test.us.wcu.user.name"))) {
				expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(action, AdoddleCommonStringPool.ACTION_FOR_ACTION), action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
				flag = true;
			}

			else if (dataCenter.equalsIgnoreCase(AdoddleCommonStringPool.AUS_DC)) {

				if (user.contains(ResourceHandler.loadProperty("secondary.aus.user.name"))) {
					if (action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION)) {
						expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
					}
					else {
						expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 5);
						AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(action, AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT), action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
					}
					flag = true;
				}
				else if (user.contains(ResourceHandler.loadProperty("test.aus.wcu.user.name"))) {
					expectedDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 20);
					AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(action, AdoddleCommonStringPool.ACTION_FOR_ACTION), action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
					flag = true;
				}
				verifyDateAndInCompleteAction(actionDueDate, expectedDueDate, actionSatus);
			}
		}
		AutomationAssert.verifyTrue("Failure While validating HistoryRecords", flag);
		closeSecondWindow();
	}

	private void verifyDateAndInCompleteAction(String dueDate, String expectedDate, String status) {
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(dueDate, expectedDate), dueDate.contains(expectedDate));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(status, AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE), status.contains(AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE));
	}

	public void clearHolidaysFromProject() {
		waitForCompletePageLoad();
		try {
			if (findElements(ProjectsTab.css_PopEditProjectCancelHolidayImageList).size() > 0) {
				for (WebElement cancelImg : findElements(ProjectsTab.css_PopEditProjectCancelHolidayImageList))
					clickElementAndWait(cancelImg);
			}
			else
				log.info("Holidays not available in calender - no clean up required.");
			clickOnSaveOnEditProjectPopup();
		}
		catch (Throwable t) {
			log.info("Failure while clearing holidays from workspace calender");
		}
		additionalHolidayDate = null;
	}

	/****************************** Distribute "Role \ Org" For "File \ Form" With Working Calendar ******************************/

	public void selectRoleAndOrgWithAddingDueDays(String distributeRoleOrg, String roleAction, int roleAddDays, String orgAction, int orgAddDays) {
		collectionDataMap.put("Distribution Role Org", distributeRoleOrg);
		int i = 1, j = 0;
		for (String distField : distributeRoleOrg.split(",")) {
			enterDistributeGroup(distField);
			String days = DateUtils.addDaysToDate("d", timezoneID, Arrays.asList(roleAddDays, orgAddDays).get(j));
			String month = DateUtils.addDaysToDate("MMMM", timezoneID, Arrays.asList(roleAddDays, orgAddDays).get(j));

			log.info("days : " + days);
			log.info("month : " + month);

			if (activeTab.contains(AdoddleCommonStringPool.TAB_FILES)) {
				clickElementAndWaitForElement(By.xpath(".//div[contains(@id,'s2id_inptDistTo')]//ul[@class='select2-choices']//li[" + i + "]//button[@class='btn dropdown-toggle']"), ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown);
				selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, Arrays.asList(roleAction, orgAction).get(j));

				if (!getElementText(FilesTab.lbl_PopDatePickerCurrentMonthLabel).equalsIgnoreCase(month))
					clickElementAndWaitForElement(FilesTab.lnk_PopDatePickerNextMonthLink, By.xpath(".//div[@class='hasDatepicker']//span[contains(@class,'month')][text()='" + month + "']"));
				clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//td[@data-handler='selectDay']//a[text()='" + days + "']"));
			}
			else {
					selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, Arrays.asList(roleAction, orgAction).get(j));
					clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewPopCreateFormClearSelectedUserDueDays, FilesTab.lbl_BetaViewPopDatePickerCurrentMonthLabel);
					if (!getElementText(FilesTab.lbl_BetaViewPopDatePickerCurrentMonthLabel).contains(month))
						clickElementAndWaitForElement(FilesTab.lnk_BetaViewPopDatePickerNextMonthLink, By.xpath(".//div[@id='distInputTo']//ul//li[@class='ng-scope']//div[contains(@class,'focus')]//div[contains(@class,'datepicker-calendar-body')]//a[not(contains(@class,'disabled'))][text()='" + days + "']"));
					clickElementAndWaitForElement(By.xpath(".//div[@id='distInputTo']//ul//li[@class='ng-scope']//div[contains(@class,'focus')]//div[contains(@class,'datepicker-calendar-body')]//a[not(contains(@class,'disabled'))][text()='" + days + "']"), ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
					clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
			}
			i++;
			j++;
		}
	}

	public void createFormWithOrgRoleAndGroup(String distributeRoleOrg, String roleAction, int roleAddDays, String orgAction, int orgAddDays, String distributeGroup, String dropdown) throws ParseException {
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		switchDefault();
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		FormIncompleteActionsScripts.formTitle = "AutoForm" + epoch;
		collectionDataMap.put("Form Title", formTitle);
		log.info("formTitle :" + formTitle);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, FormIncompleteActionsScripts.formTitle);
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton, ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		selectRoleAndOrgWithAddingDueDays(distributeRoleOrg, roleAction, roleAddDays, orgAction, orgAddDays);

		if (dropdown != null) {
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAssociationIcon);
			clear(ProjectFormsTab.txt_PopCreateDistributeGroupFormCode);
			waitForCompletePageLoad();
			selectByVisibleText(ProjectFormsTab.drp_PopCreateDistributeGroupFormDistributionGroupDropdown, distributeGroup);
		}
		else {
			enterDistributeGroup(distributeGroup);
			clickElementAndWait(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
			clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
			waitForCompletePageLoad();
		}
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateFormCalendarCurrentDate, ProjectFormsTab.btn_CreateFormSendButton);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void getActiveTabText() {
		activeTab = getElementText(GlobalPageElements.lnk_ActiveTab);
		log.info("Active Tab : " + activeTab);
	}
}