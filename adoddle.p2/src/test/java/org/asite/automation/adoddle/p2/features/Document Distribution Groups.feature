Feature: Manage Document Distribution Groups

@P2T3
Scenario Outline: Create Document Distribution Groups
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on ProjectName as <Project_Name> Drag mouse to "Edit" And Click on "Distribution Groups"
Then "Manage Distribution Groups" popup should open
When I click on AddDistributionGroup button And I create "Documents" type Distribution Group for "Admin" AND "AccesstoUse" AND "NoAccess"
And I click on Save All button
And I search project as <Project_Name>
And I right click on ProjectName as <Project_Name> Drag mouse to "Edit" And Click on "Distribution Groups"
Then "Admin" and "AccesstoUse" groups should be displayed and "NoAccess" group should not displayed
When I click on Cancel button
Given I am on "Files" tab
And I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
And I enter all mandatory fields to upload
And I click on "Distribute Files" button of upload popup
Then "To :" AND "Subject :" textboxes should be available on upload popup
When I select Admin Distribution Group in textbox
And I click on Upload button
Then File should be uploaded successfully And assigned all actions should displayed in "My Actions" column popup
And I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
And I enter all mandatory fields to upload
And I click on "Distribute Files" button of upload popup
And I select AccessToUse Distribution Group in textbox
And I click on Upload button
Then File should be uploaded successfully And "For Information" action should be assigned to uploaded file without "Due Date"
When I deactivate created all groups
Then Groups should not displayed in "Manage Distribution Groups" popup
And It should not displayed in Distribution List when files should be upload on folderName "AutomationUploadFiles" with Distribution
Examples: 
|DC_Center|Project_Name|
|UK|Automation_Admin_Objects_WS_UK|
#|US|Automation_Admin_Objects_WS_US|
#|AUS|Automation_Admin_Objects_WS_AUS|