/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleAdminLocators.ManageNotices;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ManageNoticesScripts extends AdoddleCommonAppMethods {

	private static int		index					= 0;
	private String			noticeTitle				= "AutoTestNotice" + epoch, noticeDate, activeNoticeTitle;
	private String			noticeDesc				= javaUtils.getRandomString(5) + " " + javaUtils.getRandomString(10) + " " + javaUtils.getRandomString(20);
	List<String>			priorityNoticeList		= new ArrayList<String>();
	List<WebElement>		noticeStatusList		= new ArrayList<WebElement>();
	List<WebElement>		activateButtonList		= new ArrayList<WebElement>();
	List<WebElement>		noticeTitles			= new ArrayList<WebElement>();
	List<WebElement>		noticeEditButtons		= new ArrayList<WebElement>();
	List<WebElement>		noticeSchedStatusList	= new ArrayList<WebElement>();
	public static Logger	log						= AutomationLogger.getInstance().getLogger(ManageNoticesScripts.class);

	public void clickManageNoticesBox() {
		clickElementAndWaitForElement(ManageNotices.img_ManageNoticeThumb, ManageNotices.btn_NoticeListingCreateNewNotice);
	}

	public void verifyCreateNoticeButton(String buttonText) {
		AutomationAssert.verifyTrue(isDisplayed(ManageNotices.btn_NoticeListingCreateNewNotice));
		AutomationAssert.verifyTrue(isDisplayedButtonWithText(buttonText));
	}

	public void enterValuesInNoticeFields() {
		sendKeys(ManageNotices.txt_PopAddNoticeTitle, noticeTitle);
		sendKeys(ManageNotices.txt_PopAddNoticeDesc, noticeDesc);
		clickElementAndWait(ManageNotices.rad_PopAddNoticePriorityHigh);

	}

	public void enterValuesInDateFields() throws InterruptedException {
		noticeDate = dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST");
		sendKeys(ManageNotices.txt_PopAddNoticeStartDate, noticeDate);
		sendKeys(ManageNotices.txt_PopAddNoticeStartDate, Keys.TAB);
		sendKeys(ManageNotices.txt_PopAddNoticeEndDate, noticeDate);
		sendKeys(ManageNotices.txt_PopAddNoticeEndDate, Keys.TAB);
		selectByIndex(ManageNotices.drp_PopAddNoticeEndTime, 47);

	}

	public void setNoticeToCurrentDCProject() throws InterruptedException {
		getWebDriver().findElement(ManageNotices.txt_PopAddNoticeApplyTo).sendKeys(globalTestProject);
		waitForCompletePageLoad();
		clickElementAndWait(ManageNotices.lbl_PopAddNoticeApplyToSearchFirstResult);
	}

	public void clickSaveButton(String buttonText) {
		AutomationAssert.verifyTrue(getElementText(ManageNotices.btn_PopAddNoticeSave).trim().equalsIgnoreCase(buttonText));
		clickElementAndWait(ManageNotices.btn_PopAddNoticeSave);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void sortNoticeByCreatedOn(String order) {
		if (order.equalsIgnoreCase("descending")) {
			if (!isDisplayed(ManageNotices.ele_NoticeListingCreatedOnDescIcon))
				clickElementAndWaitForElement(ManageNotices.ele_NoticeListingCreatedOnAscIcon, ManageNotices.ele_NoticeListingCreatedOnDescIcon);
		}
		else if (order.equalsIgnoreCase("ascending")) {
			if (!isDisplayed(ManageNotices.ele_NoticeListingCreatedOnAscIcon))
				clickElementAndWaitForElement(ManageNotices.ele_NoticeListingCreatedOnDescIcon, ManageNotices.ele_NoticeListingCreatedOnAscIcon);
		}

	}

	public void verifyNoticeIsOnTopListing() {
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingFirstNoticeTitle).equalsIgnoreCase(noticeTitle));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingFirstNoticePriority).equalsIgnoreCase("High"));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingFirstNoticeStartDate).equalsIgnoreCase(noticeDate));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingFirstNoticeEndDate).equalsIgnoreCase(noticeDate));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingFirstNoticeCreatedOn).equalsIgnoreCase(noticeDate));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingFirstNoticeStatus).equalsIgnoreCase("Active"));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingFirstNoticeScheduleStatus).equalsIgnoreCase("On going"));
	}

	public void verifyNoticeIsDisplayedOnPage(String flag) {
		if (flag.equalsIgnoreCase("yes")) {

			waitUntilElementIsDisplayed(ManageNotices.pop_NoticeInfopopup);
			while (!getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle).equalsIgnoreCase(noticeTitle)) {
				clickElementAndWaitForElement(ManageNotices.btn_NoticeInfoClose, ManageNotices.pop_NoticeInfopopup);
			}

			log.info("actual notice title" + getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle));
			log.info("expected notice title" + noticeTitle);
			AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle).equalsIgnoreCase(noticeTitle));
			AutomationAssert.verifyTrue(findElement(ManageNotices.img_NoticeInfoNoticePriority).getAttribute("src").contains("High"));
			AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeInfoNoticeApplyTo).contains(globalTestProject));
		}
		else if (flag.equalsIgnoreCase("no"))
			try {
				AutomationAssert.verifyTrue(!isDisplayed(ManageNotices.pop_NoticeInfopopup));
			}
			catch (Throwable t) {
				while (isDisplayed(ManageNotices.pop_NoticeInfopopup)) {
					AutomationAssert.verifyTrue(!getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle).equalsIgnoreCase(noticeTitle));
					clickElementAndWait(ManageNotices.btn_NoticeInfoClose);
				}
			}

	}

	public void clickReadMoreLinkOnNotice(String readMoreLinkText) {
		clickLinkWithText(readMoreLinkText);
		waitUntilElementIsDisplayed(ManageNotices.pop_NoticeReadMorePopup);
	}

	public void verifyNoticeDataOnReadMorePopup() {
		AutomationAssert.verifyTrue(findElement(ManageNotices.img_PopupNoticeReadMorePriority).getAttribute("src").contains("High"));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_PopupNoticeReadMoreCreatorInfo).contains(noticeTitle));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_PopupNoticeReadMoreCreatorInfo).contains(ResourceHandler.loadProperty("multi.project.user.name")));
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_PopupNoticeReadMoreDescription).equalsIgnoreCase(noticeDesc));

		List<WebElement> projectList = findElements(ManageNotices.css_PopupNoticeReadMoreProjectList);
		for (WebElement project : projectList)
			AutomationAssert.verifyTrue(project.getText().equalsIgnoreCase(globalTestProject));
	}

	public void verifyNoticePopupIsClosed() {
		AutomationAssert.verifyTrue(!isDisplayed(ManageNotices.pop_NoticeReadMorePopup));
	}

	public void dismissNotice(String dismisslinkText) {
		clickLinkWithText(dismisslinkText);
	}

	public void verifyNoticeIsDismissed() {
		AutomationAssert.verifyTrue(!isDisplayed(ManageNotices.pop_NoticeReadMorePopup));
		clickElementAndWaitForElement(ManageNotices.img_HeaderNotificationImage, ManageNotices.drp_HeaderNotificationMenu);
		AutomationAssert.verifyTrue(!isDisplayed(By.cssSelector("ul[class='notification-menu'] li[class='highPriorityli'][title='" + noticeTitle + "']")));
	}

	public void verifyNoticeIsNotDisplayedOnPage() {
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(!isDisplayed(ManageNotices.pop_NoticeInfopopup));
	}

	public void verifyNoticesAreAvailable(String listingHeader) {
		AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeListingHeaderText).equalsIgnoreCase(listingHeader));
		AutomationAssert.verifyTrue(getCount(ManageNotices.css_NoticeListingNoticesTitles) > 0);

		getNoticeStatuses();
		getNoticeTitles();
		getNoticeEditButtons();
		getNoticeActivateButtons();
	}

	public void selectActiveNoticeAndSetCurrent(String setStatus) throws InterruptedException {
		getNoticeStatuses();
		for (WebElement status : noticeStatusList) {
			if (status.getText().equalsIgnoreCase("Active")) {
				System.out.println(noticeTitles.get(index) + "on index" + index);
				break;
			}
			else
				index++;
			if (index == noticeStatusList.size())
				throw new cucumber.api.PendingException("active notice not found to deactivate");
		}

		getNoticeEditButtons();
		noticeEditButtons.get(index).click();
		waitUntilElementIsDisplayed(ManageNotices.txt_PopAddNoticeStartDate);
		activeNoticeTitle = getValue(ManageNotices.txt_PopAddNoticeTitle);
		noticeDate = dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST");
		sendKeys(ManageNotices.txt_PopAddNoticeDesc, javaUtils.getRandomString(10) + " " + javaUtils.getRandomString(20) + " " + javaUtils.getRandomString(10));
		sendKeys(ManageNotices.txt_PopAddNoticeStartDate, noticeDate);
		sendKeys(ManageNotices.txt_PopAddNoticeStartDate, Keys.TAB);
		sendKeys(ManageNotices.txt_PopAddNoticeEndDate, noticeDate);
		sendKeys(ManageNotices.txt_PopAddNoticeEndDate, Keys.TAB);
		selectByIndex(ManageNotices.drp_PopAddNoticeEndTime, 47);
		clickElementAndWait(ManageNotices.btn_PopAddNoticeSave);

	}

	public void verifyActiveNoticePopup(String flag) {
		if (flag.equalsIgnoreCase("yes")) {
			AutomationAssert.verifyTrue(isDisplayed(ManageNotices.pop_NoticeInfopopup));
			AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle).contains(activeNoticeTitle));
		}
		else {
			try {
				AutomationAssert.verifyTrue(!isDisplayed(ManageNotices.pop_NoticeInfopopup));
			}
			catch (Throwable t) {
				AutomationAssert.verifyTrue(!getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle).contains(activeNoticeTitle));
			}
		}
	}

	public void selectActiveNoticeAndDeactivate() {

		getNoticeStatuses();
		index = getNoticeIndexWithTitle(activeNoticeTitle);

		getNoticeActivateButtons();
		activateButtonList.get(index).click();

		waitForCompletePageLoad();
		getNoticeTitles();
		noticeTitle = noticeTitles.get(index).getText();

	}

	public int getNoticeIndexWithTitle(String title) {
		getNoticeTitles();
		for (WebElement notice : noticeTitles) {
			if (notice.getText().equals(title))
				return noticeTitles.indexOf(notice);
		}

		return 0;
	}

	public void getNoticeTitles() {
		noticeTitles = findElements(ManageNotices.css_NoticeListingNoticesTitles);

	}

	public void getNoticeStatuses() {
		noticeStatusList = findElements(ManageNotices.css_NoticeListingNoticesStatuses);
	}

	public void getNoticeScheduleStatuses() {
		noticeSchedStatusList = findElements(ManageNotices.css_NoticeListingNoticesSchedStatuses);
	}

	public void getNoticeEditButtons() {
		noticeEditButtons = findElements(ManageNotices.css_NoticeListingNoticesEditButtons);
	}

	public void getNoticeActivateButtons() {
		activateButtonList = findElements(ManageNotices.css_NoticeListingNoticesActivateButtons);
	}

	public void verifyNoticeStatus(String status) {
		getNoticeStatuses();
		AutomationAssert.verifyTrue(noticeStatusList.get(index).getText().equals(status));
	}

	public void isNoticeEditable(String flag) {

		getNoticeEditButtons();
		noticeEditButtons.get(index).click();
		waitForCompletePageLoad();
		if (flag.equalsIgnoreCase("no"))
			AutomationAssert.verifyTrue(!isDisplayed(ManageNotices.txt_PopAddNoticeTitle));
		else
			AutomationAssert.verifyTrue(isDisplayed(ManageNotices.txt_PopAddNoticeTitle));
	}

	public void selectInActiveNoticeAndActivate() {
		index = 0;
		getNoticeStatuses();
		for (WebElement status : noticeStatusList) {
			if (status.getText().equalsIgnoreCase("In-Active"))
				break;
			index++;
		}
		getNoticeActivateButtons();
		activateButtonList.get(index).click();
		waitForCompletePageLoad();
		getNoticeTitles();
		noticeTitle = noticeTitles.get(index).getText();
	}

	public void editExistingNoticeDetails() {
		waitUntilElementIsDisplayed(ManageNotices.txt_PopAddNoticeTitle);
		sendKeys(ManageNotices.txt_PopAddNoticeTitle, noticeTitle + "_Edited");
	}

	public void verifyNoticeIsEdited() {
		getNoticeTitles();
		AutomationAssert.verifyTrue(noticeTitles.get(index).getText().equalsIgnoreCase(noticeTitle + "_Edited"));
	}

	public void clickOnSaveButton() {
		clickElementAndWait(ManageNotices.btn_PopAddNoticeSave);
	}

	public void verifyScheduleStatus(String scheduleStatus) {
		getNoticeScheduleStatuses();
		AutomationAssert.verifyTrue(noticeSchedStatusList.get(index).getText().equalsIgnoreCase(scheduleStatus));
	}

	public void editNoticeSetTimeAsPast() {
		getNoticeEditButtons();
		noticeEditButtons.get(index).click();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ManageNotices.txt_PopAddNoticeTitle);
		selectByIndex(ManageNotices.drp_PopAddNoticeEndTime, 1);
		clickElementAndWait(ManageNotices.btn_PopAddNoticeSave);
	}

	public void createNoticeWithPriorityAndDueTime(String nTitle, String priority, String dueTime) throws InterruptedException {

		if (priority.equalsIgnoreCase("high") && dueTime.equalsIgnoreCase("recent"))
			priorityNoticeList.add(0, nTitle + epoch);
		else if (priority.equalsIgnoreCase("high") && dueTime.equalsIgnoreCase("later"))
			priorityNoticeList.add(1, nTitle + epoch);
		else if (priority.equalsIgnoreCase("low") && dueTime.equalsIgnoreCase("recent"))
			priorityNoticeList.add(2, nTitle + epoch);
		else if (priority.equalsIgnoreCase("low") && dueTime.equalsIgnoreCase("later"))
			priorityNoticeList.add(3, nTitle + epoch);

		sendKeys(ManageNotices.txt_PopAddNoticeTitle, nTitle + epoch);
		sendKeys(ManageNotices.txt_PopAddNoticeDesc, noticeDesc);

		if (priority.equalsIgnoreCase("High"))
			clickElementAndWait(ManageNotices.rad_PopAddNoticePriorityHigh);
		else if (priority.equalsIgnoreCase("Low"))
			clickElementAndWait(ManageNotices.rad_PopAddNoticePriorityLow);

		noticeDate = dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST");
		sendKeys(ManageNotices.txt_PopAddNoticeStartDate, noticeDate);
		sendKeys(ManageNotices.txt_PopAddNoticeStartDate, Keys.TAB);
		sendKeys(ManageNotices.txt_PopAddNoticeEndDate, noticeDate);
		sendKeys(ManageNotices.txt_PopAddNoticeEndDate, Keys.TAB);
		index = (dueTime.equalsIgnoreCase("recent")) ? 45 : 47;
		selectByIndex(ManageNotices.drp_PopAddNoticeEndTime, index);
		getWebDriver().findElement(ManageNotices.txt_PopAddNoticeApplyTo).sendKeys(globalTestProject);
		waitForCompletePageLoad();
		clickElementAndWait(ManageNotices.lbl_PopAddNoticeApplyToSearchFirstResult);
		clickElementAndWait(ManageNotices.btn_PopAddNoticeSave);

		verifyNoticeIsCreated(nTitle + epoch);
	}

	public void verifyNoticeIsCreated(String notice) {
		waitUntilElementIsDisplayed(ManageNotices.lbl_NoticeListingFirstNoticeTitle);
		AutomationAssert.verifyTrue(isDisplayed(By.cssSelector("div[id='adTableBody'] div[class*='col-title-fixed-width'] div[title='" + notice + "']")));
	}

	public void verifyNoticePriorities() {
		reloadPage();
		for (String notice : priorityNoticeList) {
			waitUntilElementIsDisplayed(ManageNotices.pop_NoticeInfopopup);
			AutomationAssert.verifyTrue(getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle) + " should equal " + notice, getElementText(ManageNotices.lbl_NoticeInfoNoticeTitle).equalsIgnoreCase(notice));
			clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
		}
	}

	public void deactivateNoticeProject() {
		deactivateProject(globalTestProject);
	}

	public void verifyNoticesRemovedFromListing() {
		navigateTabByText("Admin");
		clickElementAndWaitForElement(ManageNotices.img_ManageNoticeThumb, ManageNotices.lbl_NoticeListingFirstNoticeTitle);

		for (String notice : priorityNoticeList) {
			AutomationAssert.verifyTrue(!isDisplayed(By.cssSelector("div[id='adTableBody'] div[class*='col-title-fixed-width'] div[title='" + notice + "']")));
		}

	}

	public void setProjectAccessToUser(String user) throws InterruptedException {
		navigateTab(LandingPage.lnk_Projects);
		searchProjects(globalTestProject);
		contextClick(ProjectsTab.lnk_ProjectsListFirstProject);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditUserAccess);
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectsTab.btn_PopManageRolesCreateNewRole, ProjectsTab.txt_PopManageRolesCreateNewRoleInput);
		sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, javaUtils.getRandomString(10));
		getWebDriver().findElement(ProjectsTab.txt_PopManageRolesNewAssignUserInput).sendKeys(user);
		sendKeys(ProjectsTab.txt_PopManageRolesNewAssignUserInput, Keys.TAB);

		waitForCompletePageLoad();
		clickElementAndWait(ProjectsTab.btn_PopManageRolesSave);
		clickElementAndWait(ProjectsTab.btn_PopManageRolesCancel);
	}

	public void closeCurrentProject() {
		deactivateNoticeProject();
	}
}
