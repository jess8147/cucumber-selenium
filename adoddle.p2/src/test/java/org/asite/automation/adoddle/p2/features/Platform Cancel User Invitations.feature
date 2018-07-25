Feature: Platform Cancel User Invitations

@P2T5
Scenario Outline: Invited Platform Users for "Cancel" Invitation
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
Then "Roles" popup should open
When I click on CreateNewRole button
And I enter RoleName for "PT" users "Cancel" Invitations
And I click on Save button And I click on Cancel button of Manage Roles Popup
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
Then "Invite Users" popup should open
When I enter FirstName and LastName and Email And I Select Role for Platform User And entered Custom Message for "Cancel" Invitations
And I click on "invite" button of Invite Users popup
Then user should be "invited" to specific ID
And I should redirect to "Projects" tab
When I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Invitations"
And I click on "Status" LH-panel tab of Invite User Popup
Then Status current value should be set as "Pending"
And "Cancel" and "Resend" link should be displayed into Status History list
When I click on "Cancel" link of Invited User in Status History Popup
Then user should be "Cancelled" to specific ID
And Status current value should be set as "Cancelled"
When I close Invite Users Popup
And I login into Web Mail of Platform invited User ID
And I select Invitation mail from "Inbox" of "Automation User Invitations" in "Cancel Invitation" folder
Then I should get Invitation Mail of Invited Users for "Cancel" invitations
When I opened Mail and click on "here" link for "accept" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
When I again goto Web Mail page
And I opened Mail and click on "here" link for "decline" the invitation
Then "Sorry, this Invitation is no longer valid." Message page Should be Displayed
Examples: 
|DC_Center|Project_Name|
|UK|Automation_ManageRoles_UK_Project|
#|US|Automation_ManageRoles_US_Project|
#|AUS|Automation_ManageRoles_AUS_Project|