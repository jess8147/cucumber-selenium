/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ManageFormStatusScripts extends AdoddleCommonAppMethods {
	private int counter;
	private String parentHandle, projectName, noAccessType;
	private final String formTitle = "AutomationTestForm" + epoch;
	private final String formStatusTextList[] = { AdoddleCommonStringPool.FORM_STATUS_ACCESS_ADMIN,
			AdoddleCommonStringPool.FORM_STATUS_ACCESS_TO_USE, AdoddleCommonStringPool.FORM_STATUS_NO_ACCESS };
	private final String formStatusText[] = { AdoddleCommonStringPool.ADMIN, AdoddleCommonStringPool.ACCESS_TO_USE,
			AdoddleCommonStringPool.NO_ACCESS };
	private List<WebElement> statusWebList;
	final private List<String> createFormStatusList = new ArrayList<String>();
	private List<WebElement> formStatusElementList = new ArrayList<WebElement>();
	private final static Logger log = AutomationLogger.getInstance().getLogger(ManageFormStatusScripts.class);

	public void clickOnFormStatuses(String project, String formStatuses) {
		projectName = project;
		searchProjects(project);
		contextClickWithLink(project);
		clickContextMenuOption("Edit", formStatuses);
	}

	public void createFormStatuses() {

		clickElementAndWait(ProjectsTab.btn_AddNewStatus);
		clickElementAndWait(ProjectsTab.btn_AddNewStatus);
		clickElementAndWait(ProjectsTab.btn_AddNewStatus);
		statusWebList = findElements(ProjectsTab.css_AddPOIAndStatusTextList);
		List<WebElement> userCloseList = findElements(ProjectsTab.css_CloseAssignedUserList);
		List<WebElement> defaultUserList = findElements(ProjectsTab.css_DefaultUserList);

		counter = javaUtils.resetIndex(counter, 0);

		for (String formStatus : formStatusTextList) {
			statusWebList.get(counter).sendKeys(formStatus + epoch);
			if (formStatus.contains(AdoddleCommonStringPool.FORM_STATUS_ACCESS_ADMIN)
					|| formStatus.contains(AdoddleCommonStringPool.FORM_STATUS_ACCESS_TO_USE))
				createFormStatusList.add(formStatus + epoch);
			else if (formStatus.contains("FormStatus_No_Access"))
				noAccessType = formStatus + epoch;
			else
				log.info("formStatus :" + formStatus);
			userCloseList.get(counter).click();
			defaultUserList.get(counter).click();
			clickElement(ProjectsTab.btn_ContextClickMenuAdmin);
			counter++;
		}

		collectionDataMap.put("Created Form Status List1", createFormStatusList.toString());
		collectionDataMap.put("Created Form Status List2", noAccessType);
		log.info("createFormStatusList :" + createFormStatusList);
		log.info("noAccessType :" + noAccessType);

		statusWebList = findElements(ProjectsTab.css_AddUserTextList);

		for (WebElement e : statusWebList) {
			e.clear();
			e.sendKeys(System.getProperty("primary.username"));
			e.sendKeys(Keys.ENTER);
		}

		statusWebList = findElements(ProjectsTab.css_PrimaryUserList);

		counter = javaUtils.resetIndex(counter, 0);

		for (String formStatus : formStatusText) {
			statusWebList.get(counter).click();
			waitUntilElementIsDisplayed(ProjectsTab.btn_ContextClickMenuAdmin);

			if (formStatus.equalsIgnoreCase(AdoddleCommonStringPool.ADMIN))
				clickElementAndWait(ProjectsTab.opt_PopFormStatusNewStatusRoleAdmin);
			else if (formStatus.equalsIgnoreCase(AdoddleCommonStringPool.NO_ACCESS))
				clickElementAndWait(ProjectsTab.opt_PopFormStatusNewStatusRoleNoAccess);
			else if (formStatus.equalsIgnoreCase(AdoddleCommonStringPool.ACCESS_TO_USE))
				clickElementAndWait(ProjectsTab.opt_PopFormStatusNewStatusRoleAccessToUse);

			clickElementAndWait(ProjectsTab.txt_PopManageFormStatusesStatusNameFilter);

			counter++;

		}
	}

	public void setFormStatusFontAndColor(String status, String color, String font, String applyToRow) {

		if (status.equalsIgnoreCase(AdoddleCommonStringPool.FORM_STATUS_ACCESS_ADMIN)
				|| status.equalsIgnoreCase(AdoddleCommonStringPool.FORM_STATUS_ACCESS_TO_USE)) {

			sendKeys(ProjectsTab.txt_PopManageFormStatusesStatusNameFilter, status + epoch);
			waitForCompletePageLoad();

			List<WebElement> statusRows = findElements(ProjectsTab.css_PopManageDocFormStatusesRows);

			for (WebElement e : statusRows) {
				if (e.findElements(ProjectsTab.txt_PopManageDocFormStatusesFilteredStatuses).size() > 0) {
					e.findElement(ProjectsTab.lnk_PopManageDocFormFilteredStatusSettings).click();
					break;
				}
			}

		}

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
		clickElementAndWait(ProjectsTab.lbl_DocStatusSettingsFontSearchResult);
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_DocStatusSettingsSave, ProjectsTab.btn_DocStatusSettingsSave);
	}

	public void clickOnEditTemplateImageLink(String formTitle) {
		sendKeys(ProjectsTab.txt_ManageAppPopupSearchFormTitle, formTitle);
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//div[contains(@class,'rows')]//div[contains(@class,'formTypeName')][text()='"+ formTitle + "']//following::div[contains(@class,'editFormImage')][1]//img"));
	}

	public void verifyEditAppSettingsPopup(String popupTitle) {
		Assert.assertTrue(isDisplayed(ProjectsTab.pop_EditAppSettings));
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PopUpHeader1).contains(popupTitle));
	}

	public void selectCreatedFormCheckboxes() {
		executeJScript(AdoddleCommonJQueries.editAppsModalscrollMaxDownJQuery);
		statusWebList = findElements(ProjectsTab.css_OverallFormStatuesList);
		for (String status : createFormStatusList) {
			for (WebElement formStatus : statusWebList) {
				if (status.contains(formStatus.getText().trim())) {
					clickElementAndWait(By
							.xpath(".//div[@class='formStatusTable']//div[@class='formStatusDiv']//label[text()='"
									+ status + "']//input[@type='checkbox' and not(contains(@disabled,'disabled'))]"));
				}
			}
		}
	}

	public void clickOnSave() {
		clickElementAndWaitForElement(ProjectsTab.btn_POIAndStatusSaveButton, FilesTab.btn_UplaodConfirmOKButton);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_UplaodConfirmOKButton,ProjectsTab.btn_POIAndStatusSaveButton);
	}

	public void clickOnCancel() {
		clickElementAndWait(ProjectsTab.btn_ManageAppSettingsCancelButton);
	}

	public void clickOnProjectAndFolderAndFormType(String project, String formFolder, String formType) {
		clickElementWithText(project);
		clickElementWithText(formFolder);
		clickElementWithText(formType);
	}

	public void verifyCreateFormEnabled() {
		Assert.assertTrue(isDisplayed(ProjectFormsTab.btn_CreateForm));
	}

	public void verifyCreateFormPopup(String expectedText) {
		try {
			Assert.assertTrue(isDisplayed(ProjectFormsTab.pop_CreateFormWindow) && getElementText(ProjectFormsTab.pop_CreateFormWindow).contains(expectedText));
		} catch (Throwable t) {
			log.error(t.getLocalizedMessage());
		}
	}

	public void enterFormTitleText() {
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		collectionDataMap.put("Form Title", formTitle);
		clearCalculationBox();
	}

	private void clearCalculationBox() {
		clear(ProjectFormsTab.txt_CreateFormCalculationBox);
		waitForCompletePageLoad();
	}

	public void clickOnFormStatusDropdownList() {
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWait(ProjectFormsTab.sel_CreateFormStatusDropdown);
	}

	public void verifyAccessableFormStatuses() {
		log.info("createFormStatusList :" + createFormStatusList);
		formStatusElementList = findElements(ProjectFormsTab.css_CreateFormStatusDropdownList);
		compareWebStringList(createFormStatusList, formStatusElementList);
	}

	public void selectFormStatusAndCurrentDate(String formStatus) {
		log.info("formStatus :::: " + formStatus + epoch);
		log.info("formStatus displayed :::: " + isDisplayed(ProjectFormsTab.sel_CreateFormStatusDropdown));
		selectByVisibleText(ProjectFormsTab.sel_CreateFormStatusDropdown, formStatus + epoch);
		clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
	}

	public void clickOnSend() {
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}

	public void verifyCreatedFormAndFormStatus(String formStatus, String statusColor, String statusFont,
			String recordFlag) {

		if (statusColor.equalsIgnoreCase("red"))
			statusColor = "rgb(255, 0, 0)";
		else if (statusColor.equalsIgnoreCase("green"))
			statusColor = "rgb(0, 255, 0)";

		navigateTab(LandingPage.lnk_ProjectForms);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_FormListingFormSearchInput);
		searchForms(formTitle);
		Assert.assertTrue("\nExpected# " + getElementText(ProjectFormsTab.ele_FirstFormStatus) + "\nActual# "
				+ formStatus + epoch, getElementText(ProjectFormsTab.ele_FirstFormStatus).contains(formStatus + epoch));

		if (recordFlag.equalsIgnoreCase("yes")) {
			Assert.assertTrue(findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style")
					+ " should contain font-family: " + statusFont, findElement(ProjectsTab.ele_DocListingFirstRow)
					.getAttribute("style").contains(statusFont));
			Assert.assertTrue(
					findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style")
							+ " should contain background-color: " + statusColor,
					findElement(ProjectsTab.ele_DocListingFirstRow).getAttribute("style").contains(
							"background-color: " + statusColor));

		} else if (recordFlag.equalsIgnoreCase("no")) {

			mouseHover(ProjectFormsTab.ele_FirstFormStatus);
			Assert.assertTrue(findElement(ProjectFormsTab.ele_FirstFormStatus).getAttribute("style")
					+ " should contain font-family: '" + statusFont + "'",
					findElement(ProjectFormsTab.ele_FirstFormStatus).getAttribute("style").contains(statusFont));
			Assert.assertTrue(
					findElement(ProjectFormsTab.ele_FirstFormStatus).getAttribute("style")
							+ " should contain background-color: " + statusColor,
					findElement(ProjectFormsTab.ele_FirstFormStatus).getAttribute("style").contains(
							"background-color: " + statusColor));
		}
	}

	public void clickOnFormAndEditAndStatus(String edit, String status) {
		parentHandle = getCurrentWindow();
		searchForms(formTitle);
		contextClick(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		waitUntilContextMenuOptionIsDisplayed(edit);
		clickContextMenuOption(edit, status);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
	}

	public void verifyChangeableFormStatus() {
		clickElementAndWait(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown);
		formStatusElementList = findElements(ProjectFormsTab.css_BetaViewStatusChangeFormStatusDropdownList);
		compareWebStringList(createFormStatusList, formStatusElementList);
	}

	public void changeFormStatusAndNoteAndClickOnChangeStatus(String formStatus, String formStatusChangeNote) {
		selectByVisibleText(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, formStatus + epoch);
		sendKeys(ProjectFormsTab.txt_BetaViewFormStatusChangeReasonNote, formStatusChangeNote);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewPopFormStatusChangeChangeStatus);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void deactivateFormStatuses() {
		String status = AdoddleCommonStringPool.FORM_STATUSES;
		clickOnFormStatuses(projectName, status);
		sendKeys(ProjectsTab.txt_SearchPOIAndStatusFilter, epoch);
		statusWebList = findElements(ProjectsTab.css_AllPOIAndStatusCheckList);
		for (WebElement statusCheckBox : statusWebList) {
			if (!statusCheckBox.isSelected()) {
				statusCheckBox.click();
			}
		}
		clickElementAndWait(ProjectsTab.btn_POIAndStatusSaveButton);
	}

	public void verifyFormStatusesDeactivate(String formFolder, String formType) {
		clickOnProjectAndFolderAndFormType(projectName, formFolder, formType);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		clickOnFormStatusDropdownList();
		formStatusElementList = findElements(ProjectFormsTab.css_CreateFormStatusDropdownList);
		verifyDeactivateFormStatuses(formStatusElementList);
	}

	private void verifyDeactivateFormStatuses(List<WebElement> list) {
		for (WebElement statusWeb : list) {
			for (String statusValue : formStatusTextList) {
				if (!statusWeb.getText().contains(statusValue + epoch))
					log.info("Status Successfully deactivated");
				else {
					Assert.assertTrue("Incorrect status on App: " + statusWeb.getText(), false);
				}
			}
		}
	}

	private void compareWebStringList(List<String> stringList, List<WebElement> elementList) {
		boolean flag = false;
		for (WebElement valueList2 : elementList) {
			for (String valueList1 : stringList) {
				if (valueList2.getText().trim().equalsIgnoreCase(valueList1)) {
					log.info("Verified valueList1: " + valueList1);
					log.info("Verified valueList2: " + valueList2.getText().trim());
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			if (!flag) {
				if (valueList2.getText().trim().contains(epoch)) {
					Assert.assertTrue("List Value not Matched", false);
				} else {
					log.info("skip status :" + valueList2.getText());
				}
			}
		}
	}
}