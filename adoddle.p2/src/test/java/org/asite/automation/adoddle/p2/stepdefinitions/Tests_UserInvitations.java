package org.asite.automation.adoddle.p2.stepdefinitions;

import org.asite.automation.adoddle.p2.scripts.UserInvitationsScripts;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assume;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tests_UserInvitations
{
	UserInvitationsScripts scripts = new UserInvitationsScripts();
	
	/******* Decline Invitation *******/
	
	@When("^I canceled All Invitation$")
	public void i_canceled_All_Invitation() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.cancelAllInvitations();
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I enter FirstName and LastName and Email And I Select Role for invited User And entered Custom Message for \"(.*?)\" Invitations$")
	public void i_enter_FirstName_and_LastName_and_Email_And_I_Select_Role_for_invited_User_And_entered_Custom_Message(String inviteType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.enterInviteUserDetailsInvitations(inviteType);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I click on \"(.*?)\" button of Invite Users popup$")
	public void i_click_on_button_of_Invite_Users_popup(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnInvite();
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^user should be \"(.*?)\" to specific ID$")
	public void user_should_be_to_specific_ID(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyUserInvitation();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on \"(.*?)\" LH-panel tab of Invite User Popup$")
	public void i_click_on_LH_panel_tab_of_Invite_User_Popup(String inviteUsersPopLHTab) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickLinkWithText(inviteUsersPopLHTab);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^Status current value should be set as \"(.*?)\"$")
	public void status_current_value_should_be_set_as(String statusValue) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyStatusHistory(statusValue);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^\"(.*?)\" and \"(.*?)\" link should be displayed into Status History list$")
	public void and_link_should_be_displayed_into_Status_History_list(String cancel, String resend) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyPendingStatusLinks(cancel, resend);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I close Invite Users Popup$")
	public void i_click_on_Cancel_button_of_Invite_User_Popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.closeInviteUserPop();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I login into Web Mail of invited User ID$")
	public void i_login_into_Web_Mail_of_invited_User_ID() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.loginToPlatformUnavailableUser();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I select Invitation mail from \"(.*?)\" of \"(.*?)\" in \"(.*?)\" folder$")
	public void i_select_Invitation_mail_from_of_in_folder(String inbox, String invitationParentFolder, String invitationFolder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.selectWebMailInvitationInbox(inbox, invitationParentFolder, invitationFolder);
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^I should get Invitation Mail of Invited Users for \"(.*?)\" invitations$")
	public void i_should_get_Invitation_Mail_of_Invited_Users_for_invitations(String inviteType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyInvitedUserMailForInvitations(inviteType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I opened Mail and click on \"(.*?)\" link for \"(.*?)\" the invitation$")
	public void i_opened_Mail_and_click_on_link_for_the_invitation(String arg1, String inviteLink) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnHereLinkForInvitation(inviteLink);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" Message page Should be Displayed$")
	public void message_page_Should_be_Displayed(String workspaceValidationMsg) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyWorkspaceValidationPage(workspaceValidationMsg);
		}
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I again goto Web Mail page$")
	public void i_again_goto_Web_Mail_page() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.gotoWebMailPage();
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Accept Invitation *******/
	
	@When("^I enter RoleName for \"(.*?)\" users \"(.*?)\" Invitations$")
	public void i_enter_RoleName_for_users_Invitations(String userType, String invitations) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.createRoleForInvitations(userType, invitations);
		else
			Assume.assumeTrue(true);
	}

	@When("^I click on Save button And I click on Cancel button of Manage Roles Popup$")
	public void i_click_on_Save_button_And_I_click_on_Cancel_button_of_Manage_Roles_Popup() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnSaveAndCancel();
		}
		else
			Assume.assumeTrue(true);
	}
	
	/*@Then("^\"(.*?)\" image button should displayed for workspace invite to User$")
	public void image_button_should_displayed_for_workspace_invite_to_User(String arg1) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyCreateAccountImageButton();
		}
		else
			Assume.assumeTrue(true);
	}*/
	
	@Then("^\"(.*?)\" link should displayed for workspace invite to User for creating new account$")
	public void link_should_displayed_for_workspace_invite_to_user_for_creating_new_account(String linkText) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyCreateAccountLink(linkText);
		}
		else
			Assume.assumeTrue(true);
	}
	
	
	
	@When("^I click on \"(.*?)\" link for creating new account$")
	public void i_click_on_image_icon_for_workspace_invite(String linkText) throws Throwable
	{
		if (Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnCreateAccount(linkText);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" page should be opened$")
	public void page_should_be_opened(String signupPageLabel) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifySignUpPage(signupPageLabel);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^\"(.*?)\" should displayed for Signup Account into Asite$")
	public void should_displayed_for_Signup_Account_into_Asite(String signupAccountLabel) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifySignupAsiteAccount(signupAccountLabel);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I cleared \"(.*?)\" folder as \"(.*?)\" and login with multi-Project User$")
	public void i_cleared_folder_as_and_login_with_multi_Project_User(String invitationFolder, String arg2) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.clearOutlookInboxAndLoginWithMultiProjectUser(invitationFolder);
		else
			Assume.assumeTrue(true);
	}
	
	@When("^I cleared \"(.*?)\" inbox and login with multi-Project User$")
	public void i_cleared_inbox_and_login_with_multi_Project_User(String invitationFolder) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
			scripts.loginWithMultiProjectUser(invitationFolder);
		else
			Assume.assumeTrue(true);
	}
	
	/******* Cancel Invitation *******/
	
	@When("^I click on \"(.*?)\" link of Invited User in Status History Popup$")
	public void i_click_on_link_of_Invited_User_in_Status_History_Popup(String userLink) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnCancelOrResendInvitedUserLink(userLink);
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Resend Invitation *******/
	
	@Then("^I should get before Resend Invitation Mail of Invited Users$")
	public void i_should_get_before_Resend_Invitation_Mail_of_Invited_Users() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyInvitedUserBeforeResendMail();
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I opened Before Sent Previous Mail and click on \"(.*?)\" link for \"(.*?)\" the invitation$")
	public void i_opened_Before_Sent_Previous_Mail_and_click_on_link_for_the_invitation(String arg1, String inviteLink) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.clickOnHereLinkForInvitation(inviteLink);
		}
		else
			Assume.assumeTrue(true);
	}

	/******* Platform Users Invitation *******/
	
	@When("^I enter FirstName and LastName and Email And I Select Role for Platform User And entered Custom Message for \"(.*?)\" Invitations$")
	public void i_enter_FirstName_and_LastName_and_Email_And_I_Select_Role_for_Platform_User_And_entered_Custom_Message_for_Invitations(String inviteType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.enterPlatformInviteUserDetailsInvitations(inviteType);
		}
		else
			Assume.assumeTrue(true);
	}

	@When("^I login into Web Mail of Platform invited User ID$")
	public void i_login_into_Web_Mail_of_Platform_invited_User_ID() throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.loginToPlatformAvailableUser();
		}
		else
			Assume.assumeTrue(true);
	}
	
	@Then("^Invited Platform User should be displayed in Created Role panel for \"(.*?)\" Invitations$")
	public void invited_Platform_User_should_be_displayed_in_Created_Role_panel_for_Invitations(String inviteType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyPlatformUserInCreatedRole(inviteType);
		}
		else
			Assume.assumeTrue(true);
	}

	@Then("^invited Platform User should be displayed on \"(.*?)\" tab for \"(.*?)\" Invitations$")
	public void i_removed_Platform_User_into_Created_Role(String arg1, String inviteType) throws Throwable
	{
		if(Tests_CommonStepDefinitions.runTest)
		{
			scripts.verifyPlatformUserOnHistoryTab(inviteType);
		}
		else
			Assume.assumeTrue(true);
	}
}
