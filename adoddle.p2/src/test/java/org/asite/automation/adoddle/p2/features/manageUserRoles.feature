Feature: Manage User Roles

@P2T8
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
And I remove existing proxy user <Existing User>
Examples:
|DC_Center|Project_UK|Project_US|Project_AUS|Existing User|
|UK|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|Auto_FWidget User2|
#|US|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|Auto_FWidget User2|
#|AUS|Automation_ManageRoles_UK_Project|Automation_ManageRoles_US_Project|Automation_ManageRoles_AUS_Project|Auto_FWidget User2|

@P2T8
Scenario Outline: Create Role and assign user in it
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
Then "Roles" popup should open
When I click on CreateNewRole button
And I enter RoleName
And Click on Save button
Then New Role should be created
When I edited RoleName
And Click on Save button
Then updated Role should be displayed
When I assign <Proxy User> into created Role
And Click on Save button
Then assigned "User" should be displayed in Created Role panel
When I click on assigned User
Then "Manage User Details" popup should open for Proxy User
When I click on "Add Proxy User" button
And I entered "Proxy User" in "Users" input text field
And I select "Start Date" AND "End Date"
And I click on Save button And I click on Cancel button on Proxy Popup
And I Click on "Switch User" into Header dropdown menu list
Then "Switch User" popup should open
And "Assigned User" should be displayed in "Switch User" popup
When I switch into Assigned User
Given I am on "Files" tab
Then selected project <Project_Name> should be displayed into Files Tab
When I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
And I enter all mandatory fields to upload
And I click on Upload button
Then File should be uploaded successfully And "Assigned User" name should be displayed in "Publisher" column
When I Click on "Switch User" into Header dropdown menu list
Then "Switch User" popup should open
When I switch to Proxy User
Given I am on "Projects" tab
And I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "User Access"
And I remove User into Created Role panel
Then "WARNING" popup should be open for remove user
When I click on Continue button
And Click on Save button
Then Assigned "User" should be removed into Created Role panel
When I click on Save button And I click on Cancel button
And I Click on "Switch User" into Header dropdown menu list
Then "Assigned User" should be removed in "Switch User" popup
Examples: 
|DC_Center|Project_Name|Proxy User|Existing User|
|UK|Automation_ManageRoles_UK_Project|auto.nfpw_user2@atest.com|Auto_FWidget User2|
#|US|Automation_ManageRoles_US_Project|auto.nfpw_user2@atest.com|Auto_FWidget User2|
#|AUS|Automation_ManageRoles_AUS_Project|auto.nfpw_user2@atest.com|Auto_FWidget User2|