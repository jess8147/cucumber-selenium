Feature: Role Form Permissions

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
|UK|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|PA_Automation Builder|
#|US|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|PA_Automation Builder|
#|AUS|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|PA_Automation Builder|

@P2T4
Scenario Outline: Assign Form Permissions To Public and Private Forms
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
Then "Roles" popup should open
And I click on CreateNewRole button
And I enter RoleName for Form Permissions
And Click on Save button
Then New Role should be created for Form Permissions
When I assign "User" into created Role for Form Permissions
And Click on Save button
And I click on Form Permissions LH-panel tab
And I assigned "No Access" Permission to Public Form And "View All Private Forms - All Orgs" Permission to Private Form
And Click on Save button And I click on Cancel button of Role Form Permissions
And I Login to <NewOrg_UserID> User
Given I am on "Project Forms" tab
Then Public Form and Its all Forms Listing Should not displayed on "Project Form" tab
And Private Form and Its all Forms Listing Should displayed on "Project Form" tab
When I am login with multi-project user
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
And I click on Form Permissions LH-panel tab
And I removed "No Access" Permission to Public Form And "View All Private Forms - All Orgs" Permission to Private Form
And Click on Save button And I click on Cancel button of Role Form Permissions
And I Login to <NewOrg_UserID> User
Given I am on "Project Forms" tab
Then Private Form all Forms Listing Should not displayed on "Project Form" tab
And Public Form and Its all Forms Listing Should displayed on "Project Form" tab
And I remove User into Created Role
Examples: 
|DC_Center|Project_Name|Existing User|NewOrg_UserID|
|UK|Automation_ManageRoles_UK_Project|PA_Automation Builder|pa_builder@auto.com|
#|US|Automation_ManageRoles_US_Project|PA_Automation Builder|pa_builder@auto.com|
#|AUS|Automation_ManageRoles_AUS_Project|PA_Automation Builder|pa_builder@auto.com|