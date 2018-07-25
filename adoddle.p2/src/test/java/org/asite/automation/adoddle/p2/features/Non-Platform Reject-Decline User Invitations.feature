Feature: Non-Platform Reject-Decline User Invitations

@P2T5
Scenario Outline: Invited Non-Platform Users for "Reject/Decline" Invitation
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
Then "Roles" popup should open
When I click on CreateNewRole button
And I enter RoleName for "NPT" users "Decline" Invitations
And I click on Save button And I click on Cancel button of Manage Roles Popup
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
Then "Invite Users" popup should open
When I enter FirstName and LastName and Email And I Select Role for invited User And entered Custom Message for "Decline" Invitations
And I click on "invite" button of Invite Users popup
Then user should be "invited" to specific ID
And I should redirect to "Projects" tab
When I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
And I click on "Status" LH-panel tab of Invite User Popup
Then Status current value should be set as "Pending"
And "Cancel" and "Resend" link should be displayed into Status History list
When I close Invite Users Popup
And I login into Web Mail of invited User ID
And I select Invitation mail from "Inbox" of "Automation User Invitations" in "Decline Invitation" folder
Then I should get Invitation Mail of Invited Users for "Decline" invitations
When I opened Mail and click on "here" link for "decline" the invitation
Then "Invitation to Workspace - Rejected" Message page Should be Displayed
When I again goto Web Mail page
And I opened Mail and click on "here" link for "accept" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
When I again goto Web Mail page
And I opened Mail and click on "here" link for "decline" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
When I cleared "Decline Invitation" inbox and login with multi-Project User
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
And I click on "Status" LH-panel tab of Invite User Popup
Then Status current value should be set as "Rejected"
Examples: 
|DC_Center|Project_Name|
|UK|Automation_ManageRoles_UK_Project|
#|US|Automation_ManageRoles_US_Project|
#|AUS|Automation_ManageRoles_AUS_Project|