Feature: Non-Platform Resend User Invitations

@P2T5
Scenario Outline: Invited Non-Platform Users for "Resend" Invitation
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
# Pre-condition Starts #
When I login into Web Mail of invited User ID
And I select Invitation mail from "Inbox" of "Automation User Invitations" in "Resend Invitation" folder
And I cleared "Resend Invitation" folder as "Read All" and login with multi-Project User
# Pre-condition Ends #
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
Then "Roles" popup should open
When I click on CreateNewRole button
And I enter RoleName for "NPT" users "Resend" Invitations
And I click on Save button And I click on Cancel button of Manage Roles Popup
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
Then "Invite Users" popup should open
When I enter FirstName and LastName and Email And I Select Role for invited User And entered Custom Message for "Resend" Invitations
And I click on "invite" button of Invite Users popup
Then user should be "invited" to specific ID
And I should redirect to "Projects" tab
When I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
And I click on "Status" LH-panel tab of Invite User Popup
Then Status current value should be set as "Pending"
And "Cancel" and "Resend" link should be displayed into Status History list
When I click on "Resend" link of Invited User in Status History Popup
Then user should be "Re-Sent" to specific ID
And Status current value should be set as "Resent"
When I close Invite Users Popup
And I login into Web Mail of invited User ID
And I select Invitation mail from "Inbox" of "Automation User Invitations" in "Resend Invitation" folder
Then I should get before Resend Invitation Mail of Invited Users
When I opened Before Sent Previous Mail and click on "here" link for "accept" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
When I again goto Web Mail page
And I opened Before Sent Previous Mail and click on "here" link for "decline" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
When I again goto Web Mail page
Then I should get Invitation Mail of Invited Users for "Resend" invitations
When I opened Mail and click on "here" link for "accept" the invitation
#Then "Congratulations, you have been invited to a workspace" Message page Should be Displayed
Then "Congratulations You have been invited to a workspace." Message page Should be Displayed
#And "Create an account now" image button should displayed for workspace invite to User
And "clicking here" link should displayed for workspace invite to User for creating new account
#When I click on "Create an account now" image icon for workspace invite
When I click on "clicking here" link for creating new account
Then "Sign up for an Asite Account" page should be opened
And "Sign up for an Asite Account" should displayed for Signup Account into Asite
When I again goto Web Mail page
And I opened Mail and click on "here" link for "decline" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
When I again goto Web Mail page
And I opened Mail and click on "here" link for "accept" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
When I cleared "Resend Invitation" inbox and login with multi-Project User
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
And I click on "Status" LH-panel tab of Invite User Popup
Then Status current value should be set as "Accepted - Pending User Registration"
Examples: 
|DC_Center|Project_Name|
|UK|Automation_ManageRoles_UK_Project|
#|US|Automation_ManageRoles_US_Project|
#|AUS|Automation_ManageRoles_AUS_Project|