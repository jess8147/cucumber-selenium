/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class RolePrivilegesScripts extends AdoddleCommonAppMethods {
	private int i, j, counter;
	final private String roleName = "Auto_Manage_User_Role" + epoch;
	private String orgID, OrgUserName;
	private String currentDate, currentTime, currentBeforeTime, currentAfterTime;
	final private static Logger log = AutomationLogger.getInstance().getLogger(RolePrivilegesScripts.class);

	public void createNewRole() {
		sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, roleName);
		collectionDataMap.put("Role Name", roleName);
	}

	public void verifyCreatedRole() {
		clickElement(ProjectsTab.lnk_PopRolePrivileges);
		clickElementAndWait(ProjectsTab.lnk_PopManageRoles);
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		Assert.assertTrue(findElement(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput).getAttribute("value")
				.contains(roleName));
	}

	public void assignUserToCreatedRole(String orgUserID) {
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, orgUserID);
		sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, Keys.ENTER);
		collectionDataMap.put("New Org Name", orgUserID);
	}

	public void clickOnSaveAndGetCurrentDateAndTime() {
		clickElement(ProjectsTab.btn_ManageRolesTabSaveButton);
		currentDate = dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy", AdoddleCommonStringPool.TIMEZONE_WET);
		log.info("current Date :" + currentDate);
		currentTime = dateUtils.getCurrentDateTimeWithZone("HH:mm", AdoddleCommonStringPool.TIMEZONE_WET);
		log.info("current Time :" + currentTime);
		currentBeforeTime = getBeforeCurrentDateTimeWithZone();
		log.info("current Before Time :" + currentBeforeTime);
		currentAfterTime = getAfterCurrentDateTimeWithZone();
		log.info("current After Time :" + currentAfterTime);
		waitForCompletePageLoad();
	}

	public void verifyAssignedUserAndDateTimeOnHistoryTab(String orgUserName) {
		sendKeys(ProjectsTab.txt_HistorySearchFilter, roleName);

		Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryAssignedFirstRoleRemarksLabel)
				.contains(roleName));
		Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryAssignedFirstUser).contains(orgUserName));
		Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryFirstRoleCurrentDateTime).contains(currentDate));

		try {
			log.info("CurrentTime :"
					+ getElementText(ProjectsTab.ele_PopRoleHistoryFirstRoleCurrentDateTime).contains(currentTime));
			Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryFirstRoleCurrentDateTime).contains(
					currentTime));
		} catch (Throwable t) {
			try {
				log.info("CurrentBeforeTime :"
						+ getElementText(ProjectsTab.ele_PopRoleHistoryFirstRoleCurrentDateTime).contains(
								currentBeforeTime));
				Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryFirstRoleCurrentDateTime).contains(
						currentBeforeTime));
			} catch (Throwable tx) {
				log.info("CurrentAfterTime :"
						+ getElementText(ProjectsTab.ele_PopRoleHistoryFirstRoleCurrentDateTime).contains(
								currentAfterTime));
				Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryFirstRoleCurrentDateTime).contains(
						currentAfterTime));
			}
		}
	}

	public void verifyAssignedUnAssignedRolePrivileges() {
		clickElementAndWait(ProjectsTab.btn_RolePrivilegesRoleNameFilterButton);
		isClearDisplayed();
		sendKeys(ProjectsTab.txt_PopManageRolesSearchFilter, roleName);
		clickElementAndWaitForElement(ProjectsTab.chk_SelectedRoleCheckbox, GlobalPageElements.lnk_FilterClose);
		clickElementAndWait(GlobalPageElements.lnk_FilterClose);
		checkAssignedUnassignedRoles();
	}

	private void checkAssignedUnassignedRoles() {
		String[] selectedRolePrivileges = { AdoddleCommonStringPool.ACCESS_ALLOW_CUSTOM_DIST_OWN_ORG,
				AdoddleCommonStringPool.ACCESS_CAN_ACCESS_AUDIT_INFO,
				AdoddleCommonStringPool.ACCESS_CAN_ACCESS_WS_CALENDER,
				AdoddleCommonStringPool.ACCESS_CAN_CREATE_COMMENTS,
				AdoddleCommonStringPool.ACCESS_CAN_CREATE_PRIVATE_COMMENTS,
				AdoddleCommonStringPool.ACCESS_CAN_DOWNLOAD_DOCS, AdoddleCommonStringPool.ACCESS_CAN_MOVE_OWN_DOCS,
				AdoddleCommonStringPool.ACCESS_CAN_PRINT_DOCS };

		List<WebElement> rolesPrivilegesCheckList =  findElements(ProjectsTab.css_RolePrivilegesCheckList);
		i = javaUtils.resetIndex(i, 0);
		j = javaUtils.resetIndex(j, 2);
		counter = javaUtils.resetIndex(counter, 0);
		for (WebElement privilegesCheckbox : rolesPrivilegesCheckList) {
			if (counter < selectedRolePrivileges.length
					&& privilegesCheckbox.getAttribute("title").contains(selectedRolePrivileges[i])) {
				log.info("checked :" + privilegesCheckbox.getAttribute("title"));
				Assert.assertTrue(isSelected(By
						.xpath(".//div[@id='roles-table']//tr[not(contains(@style,'none'))]//td[" + j + "]//input")));
				i++;
				counter++;
			} else
				log.info("UnChecked :" + privilegesCheckbox.getAttribute("title"));
			log.info("j counter :" + j);
			j++;
		}
	}

	public void clickOnCancel() {
		clickElementAndWait(ProjectsTab.btn_PopRolePrivilegesCancelButton);
	}

	public void contextClickOnShareAndDistributeFiles(String share, String distributeFiles) {
		waitUntilElementIsDisplayed(FilesTab.lnk_FileName);
		contextClick(FilesTab.lnk_FileName);
		clickContextMenuOption(share, distributeFiles);
	}

	public void verifyDistributeUsersAndOrgs(String orgName, String orgUserID) {
		orgID = orgUserID;
		OrgUserName = orgUserID;
		List<String> userOrgNameList = new ArrayList<String>();
		userOrgNameList.add(orgName);
		userOrgNameList.add(orgUserID);
		clickElementAndWait(FilesTab.txt_PopDistributeToAccessField);
		List<WebElement> userOrgDistributeList  = findElements(FilesTab.css_PopDistributeToAccessibleFieldList);
		compareTextWebList(userOrgDistributeList, userOrgNameList);
		sendKeys(FilesTab.txt_PopDistributeToAccessField, Keys.ESCAPE);
		clickElementAndWait(ProjectsTab.btn_ManageAppSettingsCancelButton);
	}

	public void assignedRolePrivilegesToCreatedRole(String rolePrivilege) {
		collectionDataMap.put("Assigned Permission to New Role", rolePrivilege);
		clickElementAndWait(ProjectsTab.btn_RolePrivilegesRoleNameFilterButton);
		isClearDisplayed();
		sendKeys(ProjectsTab.txt_PopManageRolesSearchFilter, roleName);
		waitUntilElementIsDisplayed(ProjectsTab.chk_SelectedRoleCheckbox);
		clickElementAndWaitForElement(ProjectsTab.chk_SelectedRoleCheckbox, GlobalPageElements.lnk_FilterClose);
		clickElementAndWaitForElement(GlobalPageElements.lnk_FilterClose, ProjectsTab.btn_RolePrivilegesFilterButton);
		clickElementAndWait(ProjectsTab.btn_RolePrivilegesFilterButton);
		isClearDisplayed();
		List<WebElement> rolePrivilegesList = findElements(ProjectsTab.css_AccessPermissionLabelList);
		i = 1;
		for (WebElement privilege : rolePrivilegesList) {
			if (privilege.getAttribute("title").contains(rolePrivilege)) {
				clickElementAndWait(By
						.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li[" + i
								+ "]//label[@title]//input"));
				break;
			}
			i++;
		}
		clickElementAndWait(GlobalPageElements.lnk_FilterClose);

		List<WebElement> roleAccessCheckList = findElements(ProjectsTab.css_FormPermissionFormAccessCheckList);
		for (WebElement checkbox : roleAccessCheckList)
			if (!checkbox.getAttribute("class").contains("checked"))
				checkbox.click();
	}

	public void clickOnSaveAndCancel() {
		clickElementAndWait(ProjectsTab.btn_PopManageRolesPrivilegesSave);
		clickLinkWithText("Role Privileges");
		clickOnCancel();
	}

	public void verifyDistributeAllUsersAndAllOrgs() {
		List<String> allUsersOrgsNameList = new ArrayList<String>();
		allUsersOrgsNameList.add(orgID);
		allUsersOrgsNameList.add(OrgUserName);
		allUsersOrgsNameList.add(ResourceHandler.loadProperty("primary.user.org"));
		allUsersOrgsNameList.add(System.getProperty("multi.project.username"));

		clickElementAndWait(FilesTab.txt_PopDistributeToAccessField);
		for (String userOrg : allUsersOrgsNameList) {
			sendKeys(FilesTab.txt_PopDistributeToAccessField, userOrg);
			waitUntilElementIsDisplayed(FilesTab.ele_PopDistributeToAccessField);
		}
		sendKeys(FilesTab.txt_PopDistributeToAccessField, Keys.ESCAPE);
		clickElementAndWait(ProjectsTab.btn_ManageAppSettingsCancelButton);
	}

	public void deactivateUserIntoCreatedRole() {
		searchProjects(CreateEditRoleScripts.projectName);
		contextClickWithLink(CreateEditRoleScripts.projectName);
		clickContextMenuOption("Edit", AdoddleCommonStringPool.ROLE_USER_ACCESS);
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		clickElementAndWaitForElement(ProjectsTab.lnk_FirstAssignedUserRemoveLink, ProjectsTab.btn_PopWarningContinueButton);
		clickElementAndWaitForElement(ProjectsTab.btn_PopWarningContinueButton, ProjectsTab.btn_ManageRolesTabSaveButton);
		clickElementAndWait(ProjectsTab.btn_ManageRolesTabSaveButton);
	}

	public void verifyRemovedUserOnHistoryTab(String removeRole) {
		sendKeys(ProjectsTab.txt_HistorySearchFilter, roleName);
		Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryRemovedFirstRoleRemarksLabel).contains(
				removeRole));
		Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryRemovedFirstRoleRemarksLabel).contains(roleName));
	}

	private void isClearDisplayed() {
		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll)) {
			mouseHover(ProjectsTab.lnk_PopRolePrivileges);
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterProjectClearAll, GlobalPageElements.lnk_FilterProjectDisableClear);
		} else
			mouseHover(ProjectsTab.lnk_PopRolePrivileges);
	}

	private String getBeforeCurrentDateTimeWithZone() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(AdoddleCommonStringPool.TIMEZONE_WET));
		cal.add(Calendar.MINUTE, 1);
		return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}

	private String getAfterCurrentDateTimeWithZone() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(AdoddleCommonStringPool.TIMEZONE_WET));
		cal.add(Calendar.MINUTE, -1);
		return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}

	private void compareTextWebList(List<WebElement> webList, List<String> textList) {
		if (textList.size() == webList.size()) {
			boolean flag = false;
			for (WebElement actualValue : webList) {
				for (String expectedValue : textList) {
					if (getElementText(actualValue).contains(expectedValue)) {
						log.error("actualValue :" + getElementText(actualValue) + " vs ExpectedValue: " + expectedValue);
						flag = true;
						break;
					} else {

						log.error("not Verified");
						flag = false;
					}
				}
				if (!flag)
					Assert.assertTrue("List Value not Matched", false);
			}
		} else
			Assert.assertTrue("List size not Matched: " + textList.size() + " != " + webList.size(), false);
	}
}
