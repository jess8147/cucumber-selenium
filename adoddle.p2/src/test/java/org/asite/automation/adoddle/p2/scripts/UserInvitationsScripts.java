/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.Outlook365Locators;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UserInvitationsScripts extends AdoddleCommonAppMethods {
	private String			parentWindow;
	private String			inviteUserFirstName				= "Automation" + epoch;
	private String			inviteUserLastName				= "User";
	private String			inviteUserEmailID				= ResourceHandler.loadProperty("invitation.non.platform.user.id");
	private String			invitePlatformUserID			= ResourceHandler.loadProperty("invitation.platform.user.id");
	private String			setCustomMessageForInviteUser	= "User Invitations Test";
	private String			inviteUserDeclineRole			= "Automation_Decline_Invitation" + epoch;
	private String			inviteUserAcceptRole			= "Automation_Accept_Invitation" + epoch;
	private String			inviteUserCancelRole			= "Automation_Cancel_Invitation" + epoch;
	private String			inviteUserResendRole			= "Automation_Resend_Invitation" + epoch;
	private String			invitationType;
	private String			userType						=  null;
	private String			invitedUserSelectedRole;
	public static Logger	log								= AutomationLogger.getInstance().getLogger(UserInvitationsScripts.class);

	/******* Decline Invitation *******/

	public void clickOnInvite() {
		clickElement(ProjectsTab.btn_PopInviteUsersInviteButton);
	}

	public void verifyUserInvitation() {
		try {
			waitUntilElementIsDisplayed(ProjectsTab.msg_InviteUsersSuccessfulMessage, 10);
		}
		catch (Throwable t) {
			log.info("Failed to Displayed Invitation Message");
		}
		waitForCompletePageLoad();
	}

	private int	roleStatusHistoryRow;

	/*public void verifyStatusHistory(String statusValue) {
		waitForCompletePageLoad();
		int i = 1;
		for (WebElement inviteUserHeaderPanel : findElements(ProjectsTab.ele_InviteUserStatusHeaderPanel)) {
			if (inviteUserHeaderPanel.getText().equalsIgnoreCase(AdoddleCommonStringPool.SECURITY_ROLES)) {
				log.info("Roles Column number : " + i);

				roleStatusHistoryRow = 1;
				for (WebElement invitedRole : findElements(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr//td[" + i + "]"))) {

					if (invitedRole.getText().equalsIgnoreCase(invitedUserSelectedRole)) {
						log.info("Roles Column number : " + roleStatusHistoryRow);

						if (statusValue.contains("Resent")) {
							roleStatusHistoryRow = roleStatusHistoryRow + 1;
							log.info("Resent Status History Row : " + roleStatusHistoryRow);
						}
						Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[1]")).contains(statusValue));
						Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[2]")).contains(inviteUserFirstName));
						break;
					}
					roleStatusHistoryRow++;
				}
				break;
			}
			i++;
		}
	}*/
	
	public void verifyStatusHistory(String statusValue) {
		waitForCompletePageLoad();
		int i = 1; boolean resentFlag = false;
		for (WebElement inviteUserHeaderPanel : findElements(ProjectsTab.ele_InviteUserStatusHeaderPanel)) {
			if (inviteUserHeaderPanel.getText().equalsIgnoreCase(AdoddleCommonStringPool.SECURITY_ROLES)) {
				log.info("Roles Column number : " + i);

				roleStatusHistoryRow = 1;
				for (WebElement invitedRole : findElements(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr//td[" + i + "]"))) {

					if (invitedRole.getText().equalsIgnoreCase(invitedUserSelectedRole)) {
						
						log.info("Roles Column number : " + roleStatusHistoryRow);
						
						if(!statusValue.contains("Resent")) {
							
							Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[1]")).contains(statusValue));
							Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[2]")).contains(inviteUserFirstName));
							break;
						}
						else {
							
							if(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[1]")).contains(statusValue)) {
								resentFlag = true;
								Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[1]")).contains(statusValue));
								Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[2]")).contains(inviteUserFirstName));
								break;
							}
						}
					}
					roleStatusHistoryRow++;
				}
				break;
			}
			i++;
		}
		if(statusValue.contains("Resent"))
			Assert.assertTrue("User Failed to verify Resent Request... ", resentFlag);
	}
	

	public void verifyPendingStatusLinks(String cancel, String resend) {

		log.info("roleStatusHistoryRow : " + roleStatusHistoryRow);
		Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[last()]//a")).contains(resend));
		Assert.assertTrue(getElementText(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//td[last()-1]//a")).contains(cancel));
	}

	public void closeInviteUserPop() throws InterruptedException {
		sendKeys(ProjectsTab.btn_PopInviteUsersCloseButton, Keys.ESCAPE);
	}

	public void loginToPlatformUnavailableUser() throws IOException {
		logOut();
		signInToOutLook365WebMail(inviteUserEmailID = ResourceHandler.loadProperty("invitation.non.platform.user.id"), ResourceHandler.getPropertyValue("mail.smtp.gatepass"));
		//loginToWebMail(inviteUserEmailID = ResourceHandler.loadProperty("invitation.non.platform.user.id"));
	}

	public void selectWebMailInvitationInbox(String inbox, String invitationParentFolder, String invitationFolder) {
		
		if(isDisplayed(Outlook365Locators.img_Office365InboxChevronDown))
			clickElementAndWaitForElement(Outlook365Locators.img_Office365InboxChevronDown, Outlook365Locators.img_Office365InboxChevronUp);
		clickElementWithText(invitationParentFolder);
		
		if (invitationType != null && "Resend".contains(invitationType))
			waitUntilElementContainsText(Outlook365Locators.ele_Office365ResendInvitationFolderUnreadCount, "2");
		
		clickElementWithText(invitationFolder);
		
		
		/*clickElementAndWait(By.xpath(".//div[@id='Mail']//div[@id='mailtree']//div[@id='divTrNdCC']//span[contains(@fldrnm,'" + inbox + "')]//preceding::img[contains(@class,'plus-png')]"));
		clickElementAndWait(By.xpath(".//div[@id='Mail']//div[@id='mailtree']//div[@id='divTrNdCC']//span[contains(@fldrnm,'" + invitationParentFolder + "')]//preceding::img[contains(@class,'plus-png')]"));
		clickElementAndWait(By.xpath(".//div[@id='Mail']//div[@id='mailtree']//div[@id='divTrNdCC']//span[contains(@fldrnm,'" + invitationFolder + "')]"));

		if (invitationType != null && invitationType.contains("Resend"))
			waitUntilElementIsDisplayed(GlobalPageElements.ele_WebMailUserInvitationsUnreadCount);*/
	}

	public void verifyInvitedUserMailForInvitations(String inviteType) throws InterruptedException {
		if (inviteType.contains("Decline"))
			verifyInvitedUserMail(inviteUserDeclineRole);
		else if (inviteType.contains("Accept"))
			verifyInvitedUserMail(inviteUserAcceptRole);
		else if (inviteType.contains("Cancel"))
			verifyInvitedUserMail(inviteUserCancelRole);
		else if (inviteType.contains("Resend"))
			verifyInvitedUserReadMail(inviteUserResendRole);
	}

	public void clickOnHereLinkForInvitation(String inviteLink) {
		if ("accept".contains(inviteLink))
			clickElementAndWait(Outlook365Locators.lnk_Office365EmailContentAcceptInvitationsLink);
		else if ("decline".contains(inviteLink))
			clickElementAndWait(Outlook365Locators.lnk_Office365EmailContentDeclineInvitationsLink);
	}

	public void verifyWorkspaceValidationPage(String workspaceValidationMsg) {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(By.cssSelector("img[src*='name=logo.png']"));
		
		if(isDisplayed(By.cssSelector("input[id='hs-pwd-widget-password']"))) {
			sendKeys(By.cssSelector("input[id='hs-pwd-widget-password']"), "TND@2018");
			clickElementAndWaitForInvisibilityOfElement(By.cssSelector("input[class*='hs-button']"), By.cssSelector("input[class*='hs-button']"));
		}
		log.info("User type is: "+this.userType)
		;
		if(workspaceValidationMsg.contains("Congratulations") && "pt".equalsIgnoreCase(this.userType)) 
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(GlobalPageElements.lbl_WorkspaceInvitationValidationLabel).replace("\n", " "), workspaceValidationMsg), getElementText(GlobalPageElements.lbl_WorkspaceInvitationValidationLabel).replace("\n", " ").contains(workspaceValidationMsg));
		else if (workspaceValidationMsg.contains("Congratulations") && "npt".equalsIgnoreCase(this.userType))
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(GlobalPageElements.lbl_WorkspaceInvitationExpirationLabel).replace("\n", " "), workspaceValidationMsg), getElementText(GlobalPageElements.lbl_WorkspaceInvitationExpirationLabel).replace("\n", " ").contains(workspaceValidationMsg));
		else
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(GlobalPageElements.lbl_WorkspaceInvitationExpirationLabel).replace("\n", " "), workspaceValidationMsg), getElementText(GlobalPageElements.lbl_WorkspaceInvitationExpirationLabel).replace("\n", " ").contains(workspaceValidationMsg));
	}

	public void gotoWebMailPage() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
		waitUntilElementIsDisplayed(Outlook365Locators.ele_Office365EmailContentWorkspaceRole);
	}

	/******* Accept Invitation *******/

	public void createRoleForInvitations(String userType, String invitations) {
		this.userType = userType;
		
		if (invitations.contains("Decline")) {
			inviteUserDeclineRole = userType + AdoddleCommonStringPool.UNDERSCORE_STRING + inviteUserDeclineRole;
			collectionDataMap.put("inviteUserDeclineRole", inviteUserDeclineRole);

			invitedUserSelectedRole = inviteUserDeclineRole;
			sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, inviteUserDeclineRole);
		}
		else if (invitations.contains("Accept")) {
			inviteUserAcceptRole = userType + AdoddleCommonStringPool.UNDERSCORE_STRING + inviteUserAcceptRole;
			collectionDataMap.put("inviteUserAcceptRole", inviteUserAcceptRole);

			invitedUserSelectedRole = inviteUserAcceptRole;
			sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, inviteUserAcceptRole);
		}
		else if (invitations.contains("Resend")) {
			inviteUserResendRole = userType + AdoddleCommonStringPool.UNDERSCORE_STRING + inviteUserResendRole;
			collectionDataMap.put("inviteUserResendRole", inviteUserResendRole);

			invitedUserSelectedRole = inviteUserResendRole;
			sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, inviteUserResendRole);
		}
		else if (invitations.contains("Cancel")) {
			inviteUserCancelRole = userType + AdoddleCommonStringPool.UNDERSCORE_STRING + inviteUserCancelRole;
			collectionDataMap.put("inviteUserCancelRole", inviteUserCancelRole);

			invitedUserSelectedRole = inviteUserCancelRole;
			sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, inviteUserCancelRole);
		}
		log.info("invitedUserSelectedRole : " + invitedUserSelectedRole);
	}

	public void clickOnSaveAndCancel() {
		clickElementAndWaitForElement(ProjectsTab.btn_ManageRolesTabSaveButton, ProjectsTab.btn_ManageUserDetailsPopupCancelButton);
		clickElementAndWait(ProjectsTab.btn_ManageUserDetailsPopupCancelButton);
	}

	public void verifyCreateAccountLink(String linkText) {
		AutomationAssert.verifyTrue(isDisplayedLinkWithText(linkText));
	}
	
	public void clickOnCreateAccount(String linkText) {
		AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lnk_CreateAccountClickingHere).equalsIgnoreCase(linkText));
		clickElementAndWait(GlobalPageElements.lnk_CreateAccountClickingHere);
		if(isDisplayed(By.cssSelector("input[id='hs-pwd-widget-password']"))) {
				sendKeys(By.cssSelector("input[id='hs-pwd-widget-password']"), "TND@2018");
				clickElementAndWaitForElement(By.cssSelector("input[class*='hs-button']"), GlobalPageElements.lbl_WorkspaceInvitationValidationLabel);
		}
	}

	public void verifySignUpPage(String signupPageLabel) {
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_WorkspaceInvitationValidationLabel).contains(signupPageLabel));
	}

	public void verifySignupAsiteAccount(String signupAccountLabel) {
		switchIframe(LoginPage.frm_Iframe);
		AutomationAssert.verifyTrue(isDisplayed(GlobalPageElements.ele_SignUpAsiteAccountForm));
		AutomationAssert.verifyTrue(isDisplayed(GlobalPageElements.txt_SignUpAsiteAccountFormLastName));
		AutomationAssert.verifyTrue(isDisplayed(GlobalPageElements.txt_SignUpAsiteAccountFormFirstName));
	}

	public void loginWithMultiProjectUser(String invitationFolder) throws InterruptedException {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
		clearOutlookInboxAndLoginWithMultiProjectUser(invitationFolder);
	}

	public void clearOutlookInboxAndLoginWithMultiProjectUser(String invitationFolder) throws InterruptedException {
		/*waitUntilElementIsDisplayed(Outlook365Locators.ele_Office365EmailContentWorkspaceRole);*/
		waitForElementWithText(invitationFolder);
		contextClickWithText(invitationFolder);
		clickElementAndWaitForInvisibilityOfElement(Outlook365Locators.lnk_Office365FolderContextMenuMarkAllRead, Outlook365Locators.lnk_Office365FolderContextMenuMarkAllRead);
		signOutFromOutLook365WebMail();
		tearDown();
		try {
			setUp(browser);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}
		/*waitUntilElementIsDisplayed(GlobalPageElements.ele_WebMailWorkspaceRole);
		waitUntilElementIsDisplayed((By.xpath(".//div[@id='Mail']//div[@id='mailtree']//div[@id='divTrNdCC']//span[contains(@fldrnm,'" + invitationFolder + "')]")));
		contextClick((By.xpath(".//div[@id='Mail']//div[@id='mailtree']//div[@id='divTrNdCC']//span[contains(@fldrnm,'" + invitationFolder + "')]")));
		clickElementWithText(AdoddleCommonStringPool.WEBMAIL_MARK_ALL_READ);
		clickElementWithText(AdoddleCommonStringPool.WEBMAIL_SIGN_OUT);*/
		navigateURL(ResourceHandler.getPropertyValue("application.url"));
		login(System.getProperty("multi.project.username"), System.getProperty("multi.project.password"));
	}

	/******* Cancel Invitation *******/

	public void clickOnCancelOrResendInvitedUserLink(String userLink) {
		clickElementAndWait(By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[" + roleStatusHistoryRow + "]//a[text()='" + userLink + "']"));
	}

	public void verifyInvitedUserMailForResend() throws InterruptedException {
		verifyInvitedUserReadMail(inviteUserResendRole);
	}

	public void enterInviteUserDetailsInvitations(String inviteType) throws InterruptedException {
		invitationType = inviteType;
		collectionDataMap.put("User Invitation Type", invitationType);
		if (inviteType.contains("Decline"))
			enterInviteUserDetails(inviteUserDeclineRole, inviteUserEmailID);
		else if (inviteType.contains("Accept"))
			enterInviteUserDetails(inviteUserAcceptRole, inviteUserEmailID);
		else if (inviteType.contains("Cancel"))
			enterInviteUserDetails(inviteUserCancelRole, inviteUserEmailID);
		else if (inviteType.contains("Resend"))
			enterInviteUserDetails(inviteUserResendRole, inviteUserEmailID);
	}

	/******* Platform Users Invitation *******/

	public void enterPlatformInviteUserDetailsInvitations(String inviteType) throws InterruptedException {
		invitationType = inviteType;
		if (inviteType.contains("Decline"))
			enterInviteUserDetails(inviteUserDeclineRole, invitePlatformUserID);
		else if (inviteType.contains("Accept"))
			enterInviteUserDetails(inviteUserAcceptRole, invitePlatformUserID);
		else if (inviteType.contains("Cancel"))
			enterInviteUserDetails(inviteUserCancelRole, invitePlatformUserID);
		else if (inviteType.contains("Resend"))
			enterInviteUserDetails(inviteUserResendRole, invitePlatformUserID);
	}

	public void loginToPlatformAvailableUser() throws IOException {
		signInToOutLook365WebMail(invitePlatformUserID = ResourceHandler.loadProperty("invitation.platform.user.id"), ResourceHandler.getPropertyValue("asite.web.mail.password"));
		//loginToWebMail(invitePlatformUserID = ResourceHandler.loadProperty("invitation.platform.user.id"));
	}

	public void verifyPlatformUserInCreatedRole(String inviteType) throws InterruptedException {
		clear(ProjectsTab.txt_CreateRolesSearchFilter);
		if (inviteType.contains("Accept"))
			sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, inviteUserAcceptRole);
		else if (inviteType.contains("Resend"))
			sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, inviteUserResendRole);

		try {
			Assert.assertTrue(getElementText(ProjectsTab.ele_FirstAssignedUserName).contains(inviteUserFirstName));
			Assert.assertTrue(getElementText(ProjectsTab.ele_FirstAssignedUserName).contains(inviteUserLastName));
		}
		catch (Throwable t) {
			Assert.assertTrue(getElementText(ProjectsTab.ele_FirstAssignedUserName).contains(ResourceHandler.loadProperty("invitation.platform.user.name")));
		}
	}

	public void verifyPlatformUserOnHistoryTab(String inviteType) {
		if (inviteType.contains("Accept"))
			sendKeys(ProjectsTab.txt_HistorySearchFilter, inviteUserAcceptRole);
		else if (inviteType.contains("Resend"))
			sendKeys(ProjectsTab.txt_HistorySearchFilter, inviteUserResendRole);

		Assert.assertTrue(getElementText(ProjectsTab.ele_PopRoleHistoryAssignedFirstUser).contains(ResourceHandler.loadProperty("invitation.platform.user.name")));
	}

	public void enterInviteUserDetails(String role, String userID) throws InterruptedException {
		sendKeys(ProjectsTab.txt_PopInviteUsersFirstNameInput, inviteUserFirstName);
		sendKeys(ProjectsTab.txt_PopInviteUsersLastNameInput, inviteUserLastName);
		sendKeys(ProjectsTab.txt_PopInviteUsersEmailInput, userID);
		sendKeys(ProjectsTab.txt_PopInviteUsersRolesInput, role);
		sendKeys(ProjectsTab.txt_PopInviteUsersRolesInput, Keys.ENTER);

		collectionDataMap.put("Invited User FirstName", inviteUserFirstName);
		collectionDataMap.put("Invited User LastName", inviteUserLastName);
		collectionDataMap.put("Invited UserID", userID);
		collectionDataMap.put("Invited UserRole", role);

		sendKeys(ProjectsTab.txt_PopInviteUsersCustomMessageInput, setCustomMessageForInviteUser);
	}

	public void loginToWebMail(String webMailID) throws IOException {
		logOut();
		getWebDriver().get(ResourceHandler.loadProperty("asite.web.mail.url"));
		sendKeys(GlobalPageElements.txt_WebMailUserNameInput, webMailID);
		sendKeys(GlobalPageElements.txt_WebMailPasswordInput, ResourceHandler.getPropertyValue("asite.web.mail.password"));
		clickElementAndWaitForElement(GlobalPageElements.btn_WebMailSignIn, GlobalPageElements.lnk_WebNewMail);
	}

	/******* Resend Invitation *******/
	public void verifyInvitedUserBeforeResendMail() throws InterruptedException {
		
		for (int index = 0; index < Integer.parseInt(ResourceHandler.loadProperty("wait.timeout")); index++) {
			if(isDisplayed(Outlook365Locators.ele_Office365SecondEmailUnreadSubject)) {
				clickElementAndWait(Outlook365Locators.ele_Office365SecondEmailUnreadSubject);
				if (getElementText(Outlook365Locators.ele_Office365SecondEmailUnreadSubject).contains(CreateEditRoleScripts.projectName)) {
					if (getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceName).contains(CreateEditRoleScripts.projectName) && getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceRole).contains(inviteUserResendRole))
						break;
				}
				else
					waitUtils.waitInterval(2);
			}
			else
				waitUtils.waitInterval(2);
			
		/*	if (isDisplayed(GlobalPageElements.ele_WebMailSecondEmailUnreadSubject)) {
				clickElementAndWait(GlobalPageElements.ele_WebMailSecondEmailUnreadSubject);
				if (getElementText(GlobalPageElements.ele_WebMailSecondEmailUnreadSubject).contains(CreateEditRoleScripts.projectName)) {
					if (getElementText(GlobalPageElements.ele_WebMailWorkspaceName).contains(CreateEditRoleScripts.projectName) && getElementText(GlobalPageElements.ele_WebMailWorkspaceRole).contains(inviteUserResendRole))
						break;
				}
				else
					waitInterval(2);
			}
			else
				waitInterval(2);*/	
		}
		AutomationAssert.verifyTrue(getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceName).contains(CreateEditRoleScripts.projectName));
		AutomationAssert.verifyTrue(getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceRole).contains(inviteUserResendRole));
	}
	
	public void verifyInvitedUserMail(String role) throws InterruptedException {
		for (int index = 0; index < 10; index++) {
			if(isDisplayed(Outlook365Locators.ele_Office365FirstEmailUnreadSubject)) {
				clickElementAndWait(Outlook365Locators.ele_Office365FirstEmailUnreadSubject);
				if (getElementText(Outlook365Locators.ele_Office365FirstEmailUnreadSubject).contains(CreateEditRoleScripts.projectName)) {
					if (getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceName).contains(CreateEditRoleScripts.projectName) && getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceRole).contains(role))
						break;
				}
				else
					waitUtils.waitInterval(20);
			}
			else
				waitUtils.waitInterval(20);
		}
		
		AutomationAssert.verifyTrue("Email should contain workspace :" + CreateEditRoleScripts.projectName, getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceName).contains(CreateEditRoleScripts.projectName));
		AutomationAssert.verifyTrue("Email should contain role :" + role, getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceRole).contains(role));
	}

	public void verifyInvitedUserReadMail(String role) throws InterruptedException {
		for (int index = 0; index < 100; index++) {
			if (isDisplayed(Outlook365Locators.ele_Office365FirstEmailUnreadSubject)) {
				clickElementAndWait(Outlook365Locators.ele_Office365FirstEmailUnreadSubject);
				if (getElementText(Outlook365Locators.ele_Office365FirstEmailUnreadSubject).contains(CreateEditRoleScripts.projectName)) {
					if (getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceName).contains(CreateEditRoleScripts.projectName) && getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceRole).contains(role))
						break;
				}
				else
					waitUtils.waitInterval(2);
			}
			else
				waitUtils.waitInterval(2);
		}
		Assert.assertTrue(getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceName).contains(CreateEditRoleScripts.projectName));
		Assert.assertTrue(getElementText(Outlook365Locators.ele_Office365EmailContentWorkspaceRole).contains(role));
	}

	/******* cancel All Invitations(Pre-Condition) *******/

	public void cancelAllInvitations() throws InterruptedException {
		contextClick(ProjectsTab.lnk_ProjectsFirstProject);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_EDIT, AdoddleCommonStringPool.LABEL_USER_INVITATIONS);
		waitForCompletePageLoad();
		clickLinkWithText(AdoddleCommonStringPool.STATUS);
		if (findElements(ProjectsTab.css_PopInviteUsersStatusCancelLinkList).size() > 0)
			cancelInvitations();
		reloadPage();
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Projects);
	}

	public void cancelInvitations() {
		List<WebElement> cancelList = new ArrayList<WebElement>();
		waitForCompletePageLoad();

		boolean flag = false;
		cancelList = findElements(ProjectsTab.css_PopInviteUsersStatusCancelLinkList);
		if (cancelList.size() != 0) {
			for (WebElement cancelBtn : cancelList) {
				clickElementAndWait(cancelBtn);
				flag = true;
				break;
			}
		}
		else
			log.info(": User Invitation List Cancelled :");

		if (flag == true)
			cancelInvitations();
	}
}
