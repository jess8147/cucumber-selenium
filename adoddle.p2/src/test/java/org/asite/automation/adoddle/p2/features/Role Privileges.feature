Feature: Role Privileges - Role Audit History

@P2T4
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I search <Project_UK> project
And I right click on <Project_UK> and click on "Edit" AND "User Access"
And I remove existing proxy user <Existing User>
Given I am on "Projects" tab
And I search <Project_US> project
And I right click on <Project_US> and click on "Edit" AND "User Access"
And I remove existing proxy user <Existing User>
Given I am on "Projects" tab
And I search <Project_AUS> project
And I right click on <Project_AUS> and click on "Edit" AND "User Access"
When I remove existing proxy user <Existing User>
Examples:
|DC_Center|Project_UK|Project_US|Project_AUS|Existing User|
|UK|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|RS Bloggs|
#|US|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|RS Bloggs|
#|AUS|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|RS Bloggs|

@P2T4
Scenario Outline: Assign Role Privileges to User
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
Then "Roles" popup should open
When I click on CreateNewRole button
And I enter RoleName for Role Privileges
And Click on Save button
Then New Role should be created for Role Privileges
When I assigned other Org <NewOrg_Name> UserID "NewOrgID" into created Role
And Click on Save button And I get current Date and Time
And I click on "History" LH-panel tab
Then Assigned User <NewOrg_UserName> And Current Date and Time should be displayed on "History" tab
When I click on "Role Privileges" LH-panel tab
Then fixed assigned "Role Privilages" should be checked to Created new Role
When I click on Cancel button of "Role Privilages" tab
And I login to NewOrg UserID
Given I am on "Files" tab
And I click on <Project_Name> project and I click on "AutomationUploadFiles" folder for PA builder
And I Right Click on file and click on "Share" and "Distribute Files" context click button
Then "Distribute" popup should open
And In "To" text field List Current Org <NewOrg_Name> and Its All Users like "NewOrgID" user only Displayed
When I am login with multi-project user
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
And I click on "Role Privileges" LH-panel tab
And I Assigned "Allow Custom Distribution - All Org" Permissions to Created new Role
And Click on Save button And I click on Cancel button of Role Privileges
And I login to NewOrg UserID
Given I am on "Files" tab
And I click on <Project_Name> project and I click on "AutomationUploadFiles" folder for PA builder
And I Right Click on file and click on "Share" and "Distribute Files" context click button
Then In "To" text field List All Orgs and All Users Displayed
When I deactivate User into Created Role
And I click on "History" LH-panel tab
Then Assigned User "Removed role" should be listed on "History" tab
Examples: 
|DC_Center|Project_Name|NewOrg_UserName|NewOrg_Name|
|UK|Automation_ManageRoles_UK_Project|RS Bloggs|ABC Test Comp|
#|US|Automation_ManageRoles_US_Project|RS Bloggs|ABC Test Comp|
#|AUS|Automation_ManageRoles_AUS_Project|RS Bloggs|ABC Test Comp|