/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateEditRoleScripts extends AdoddleCommonAppMethods {
	public static String	projectName;
	private String			roleName		= "Auto_Manage_User_Role" + epoch;
	private String			editRoleName	= "Auto_Manage_User_Role_Edited" + epoch;
	private String			assignedUserEmail, assignedUser, loggdInUser;
	private String			rev				= "1";
	public static String	createFile;
	private boolean			activeProject;
	public static Logger	log				= AutomationLogger.getInstance().getLogger(CreateEditRoleScripts.class);


	public void clickOnUserAccess(String project, String edit, String userAccess) {
		globalProjectTitle = project;
		projectName = project;
		loggdInUser = findElement(LandingPage.ele_LoggedInUserProfile).getAttribute("title").split(", ")[0];
		log.info("loggdInUser :" + loggdInUser);
		contextClickWithLink(project);
		waitForCompletePageLoad();
		clickContextMenuOption(edit, userAccess);
		waitForCompletePageLoad();
	}

	public void clickOnCreateNewRole() {
		clickElementAndWait(ProjectsTab.btn_PopManageRolesCreateNewRole);
	}

	public void createNewRole() {
		sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, roleName);
		collectionDataMap.put("Role Name", roleName);
	}

	public void clickOnSave() {
		clickElementAndWait(ProjectsTab.btn_ManageRolesTabSaveButton);
	}

	public void verifyCreatedRole() {
		updateRolesDisplayMessage();
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		log.info("Created Role :" + findElement(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput).getAttribute("value"));
		Assert.assertTrue(findElement(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput).getAttribute("value").contains(roleName));
	}

	public void editCreatedRole() {
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		sendKeys(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput, editRoleName);
		collectionDataMap.put("Edited Role Name", editRoleName);
	}

	public void verifyEditedRole() {
		updateRolesDisplayMessage();
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, editRoleName);
		log.info("Edited Role :" + findElement(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput).getAttribute("value"));
		Assert.assertTrue(findElement(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput).getAttribute("value").contains(editRoleName));
	}

	public void assignUserInCreatedRole(String proxyUser) throws InterruptedException {
		assignedUserEmail = proxyUser;
		collectionDataMap.put("Proxy User", assignedUserEmail);
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, editRoleName);
		clear(ProjectsTab.txt_PopManageRolesFirstAssignUserInput);
		sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, proxyUser);
		sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, Keys.ENTER);

		assignedUser = getElementText(ProjectsTab.ele_FirstAssignedUserName);
		collectionDataMap.put("Assigned User", assignedUser);
		log.info("assignedUser :" + assignedUser);
	}

	public void verifyAssignedUser() {
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, editRoleName);
		try {
			Assert.assertTrue(getElementText(ProjectsTab.ele_FirstAssignedUserName).contains(assignedUser));
		}
		catch (Throwable t) {
			Assert.assertTrue(getElementText(ProjectsTab.ele_FirstAssignedUserName).contains(assignedUserEmail));
		}
	}

	public void clickOnAssignedUser() {
		clickElementAndWait(ProjectsTab.ele_FirstAssignedUserName);
	}

	public void verifyManageUserDetailsPopup(String popUpText) {
		Assert.assertTrue(isDisplayed(ProjectsTab.pop_ManageUserDetailsPopup));
		Assert.assertTrue(getElementText(ProjectsTab.lbl_ManageUserDetailsPopupLabel).contains(popUpText));

	}

	public void enterProxyUser() throws InterruptedException {

		clear(ProjectsTab.txt_ProxyUsersInput);
		sendKeys(ProjectsTab.txt_ProxyUsersInput, System.getProperty("multi.project.username"));
		sendKeys(ProjectsTab.txt_ProxyUsersInput, Keys.ENTER);
	}

	public void enterStartDateAndEndDate() {
		List<WebElement> datePickerImageList = findElements(ProjectsTab.css_DatePickerImageList);
		for (WebElement selectDate : datePickerImageList) {
			selectDate.click();
//			clickElementAndWait(FilesTab.lnk_PopDatePickerCurrentDate);
			clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//td[@data-handler='selectDay']//a[text()='"+dateUtils.getUserCurrentDate("d")+"']"));
		}
	}

	public void clickOnSaveAndCancel() {
		clickElementAndWait(ProjectsTab.btn_ManageRolesPopupSaveButton);
		clickElementAndWait(ProjectsTab.btn_ManageRolesTabCancelButton);
	}

	public void clickOnSaveAndCancelProxyPopup() {
		clickElementAndWait(ProjectsTab.btn_PopManageRolesProxyDetailsSave);
		clickElementAndWait(ProjectsTab.btn_ManageUserDetailsPopupCancelButton);

	}

	public void clickOnSwitchUser() {
		reloadPage();
		waitForCompletePageLoad();
		while (isDisplayed(GlobalPageElements.lnk_HeaderNotificationClose))
			clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_SwitchUser);
		clickElementAndWait(LandingPage.lnk_SwitchUser);
	}

	public void verifyAssignedUserInSwitchUserPopup() {
		searchUser(assignedUser);
		Assert.assertTrue(isDisplayed(LandingPage.ele_FirstSwitchUserBox));
		Assert.assertTrue(getElementText(LandingPage.ele_FirstSwitchUserBoxName).contains(assignedUser));
	}

	public void switchIntoAssignedUser() {
		searchUserAndSwitch(assignedUser);
//		switchToUser(assignedUser);
	}

	public void clickOnSelectFilesAndUpload() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("file created as: " + createFile);
		collectionDataMap.put("Uploaded File Path", createFile);
		List<String> fileList = sysUtils.getFileList(createFile);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
	}

	public void enterMendatoryAttributes() {
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_RevFile1, rev);
		selectByIndex(FilesTab.drp_PurposeOfIssueFile1, 1);
		selectByIndex(FilesTab.drp_StatusFile1, 1);
	}

	public void verifyUploadedFileAndPublisher() {
		searchFiles(strUtils.extractFileNameString(createFile));
		Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));
		Assert.assertTrue(isDisplayed(FilesTab.lnk_FirstFilePublisher));
		Assert.assertTrue(getElementText(FilesTab.lnk_FirstFilePublisher).contains(assignedUser.split(" ")[0]));
	}

	public void switchIntoProxyUser() {
		searchUserAndSwitch(loggdInUser);
//		switchToUser(loggdInUser);
	}

	public void removeAssignedUser() {
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, editRoleName);
		clickElementAndWait(ProjectsTab.lnk_FirstAssignedUserRemoveLink);
	}

	public void searchUser(String userName) {
		sendKeys(LandingPage.txt_SwitchUserSearchFilter, userName);
		waitForCompletePageLoad();
//		clickElementAndWait(LandingPage.ele_FirstSwitchUserBox);
//		clickElementAndWait(LandingPage.img_SwitchUserSearchImage);
	}

	public void verifyWarningPopup(String popUpText) {
		waitUntilElementIsDisplayed(ProjectsTab.pop_WarningPopup);
		Assert.assertTrue(getElementText(ProjectsTab.lbl_WarningPopupLabel).contains(popUpText));
	}

	public void clickOnContinue() {
		clickElementAndWait(ProjectsTab.btn_PopWarningContinueButton);
	}

	public void verifyRemoveAssignedUser() {
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, editRoleName);
		Assert.assertTrue(!isDisplayed(ProjectsTab.ele_FirstAssignedUserName));
	}

	public void verifyRemoveAssignedUserInSwitchUserPopup() {
		searchUser(assignedUser);
		Assert.assertTrue(!isDisplayed(LandingPage.ele_FirstSwitchUserBox));
	}

	public void searchUserAndSwitch(String userName) {
		searchUser(userName);
		clickElementAndWait(LandingPage.ele_FirstSwitchUserBox);
	}

	public void updateRolesDisplayMessage() {
		clickElement(ProjectsTab.lnk_PopRolePrivileges);
		clickElementAndWait(ProjectsTab.lnk_PopManageRoles);
	}

	/*** TestData Cleanup ***/

	public void searchProject(String roleProject) {
		
		try {
			waitUntilElementIsDisplayed(ProjectsTab.txt_ProjectsFilterInput);
			findElement(ProjectsTab.txt_ProjectsFilterInput).clear();
			sendKeys(ProjectsTab.txt_ProjectsFilterInput, roleProject);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			sendKeys(ProjectsTab.txt_ProjectsFilterInput, Keys.ENTER);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitForCompletePageLoad();
			
			setListingStyle("ListView");
			if (isDisplayedLinkWithText(roleProject))
				activeProject = true;
			else {
				activeProject = false;
				log.info("Project not displayed : " + projectName + " : " + activeProject);
			}
			
		} catch (Throwable t) {
			log.info(": Failed to Search Project :");
		}
		
		
		/*try {
			waitUntilElementIsDisplayed(ProjectsTab.txt_ProjectsFilterInput);
			findElement(ProjectsTab.txt_ProjectsFilterInput).clear();
			sendKeys(ProjectsTab.txt_ProjectsFilterInput, roleProject);
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			findElement(ProjectsTab.btn_ProjectsFilterSearch).click();
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitForCompletePageLoad();
			if (!isDisplayed(GlobalPageElements.lnk_ActiveTabListView))
				clickElementAndWait(ProjectsTab.lnk_ListView);

			if (isDisplayedLinkWithText(roleProject))
				activeProject = true;
			else {
				activeProject = false;
				log.info("Project not displayed : " + projectName + " : " + activeProject);
			}
			
		} catch (Throwable t) {
			log.info(": Failed to Search Project :");
		}*/
	}

	public void clickOnEditAndUserAccess(String project, String edit, String userAccess)
	{
		try {
			if (activeProject)
				clickOnUserAccess(project, edit, userAccess);
			else
				log.info("Project not displayed : " + projectName + " : " + activeProject);
			
		} catch (Throwable t) {
			log.info(": Failed to Perform ContextClick :");
		}
	}

	public void removeExistingProxyUserData(String existingUser) {
		
		try {
			if (activeProject) {
				waitForCompletePageLoad();
				waitUntilElementIsDisplayed(ProjectsTab.txt_PopManageRolesSearchUserInput);
				sendKeys(ProjectsTab.txt_PopManageRolesSearchUserInput, existingUser);

				List<WebElement> removeProxyUsers = findElements(By.cssSelector("table[id='manageRolesTable'] tbody tr div[title='" + existingUser + "']+a"));
				for (WebElement e : removeProxyUsers) {
					e.click();
					waitUntilElementIsDisplayed(ProjectsTab.btn_PopWarningContinueButton);
					clickElementAndWait(ProjectsTab.btn_PopWarningContinueButton);
				}
				clickElementAndWait(ProjectsTab.btn_ManageRolesTabSaveButton);
				waitForCompletePageLoad();
				clickElementAndWait(ProjectsTab.btn_ManageUserDetailsPopupCancelButton);
			}
			else
				log.info("Project not displayed : " + projectName + " : " + activeProject);
			
		} catch (Throwable t) {
			log.info(": Failed to Remove Existing Proxy User :");
		}
	}
}
