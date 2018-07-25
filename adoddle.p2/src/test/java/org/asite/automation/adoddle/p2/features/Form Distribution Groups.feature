Feature: Manage Form Distribution Groups

@P2T5
Scenario Outline: Create Form Distribution Groups
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on ProjectName as <Project_Name> Drag mouse to "Edit" And Click on "Distribution Groups"
Then "Manage Distribution Groups" popup should open
When I click on AddDistributionGroup button And I create "Apps" type Distribution Group for "Admin" AND "AccesstoUse" AND "NoAccess" for FormDistributionGroups
And I click on Save All button
And I search project as <Project_Name>
And I right click on ProjectName as <Project_Name> Drag mouse to "Edit" And Click on "Distribution Groups"
Then "Admin" and "AccesstoUse" groups should be displayed and "NoAccess" group should not displayed for FormDistributionGroups
When I click on Cancel button
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I click on Project as <Project_Name> AND I click on Folder "Form Distibution Group" AND I click on "Check Form Distribution" Form type
Then "Create Form" button is enabled
When I click on "Create Form" button on Project Forms page
Then New "Create Form" popup should open
When I click on Distribute button of Create Form popup
Then Entered groups "To" text field should be displayed
When I select Admin Form Distribution Group in textbox
And I have entered "Title" Name And I Cleared "Form Group Code" text And entered Current Date
And I click on Send button of CreateForm popup
Then Form should be Created successfully And assigned all actions should displayed in "My Actions" column popup
When I click on Project as <Project_Name> AND I click on Folder "Form Distibution Group" AND I click on "Check Form Distribution" Form type
And I click on Create "New" Form link
#And I search "Check Form Distribution" Form Type And I click on searched formType
And I search "Check Form Distribution" Form Type And I click on searched formType ADDED
And I click on Distribute button of Create Form popup
And I select Admin Form Distribution Group in textbox
And I have entered "Title" Name And I Cleared "Form Group Code" text And entered Current Date
And I click on "Save Draft" button And handle alert popup
Then Form should be Created successfully And "Review Draft" Action should be assigned to created Form with assigned "Due Date"
When I click on Project as <Project_Name> AND I click on Folder "Form Distibution Group" AND I click on "Check Form Distribution" Form type
And I click on "Create Form" button on Project Forms page
And I click on Distribute button of Create Form popup
And I select AccessToUse Form Distribution Group in textbox
And I have entered "Title" Name And I Cleared "Form Group Code" text And entered Current Date
And I click on Send button of CreateForm popup
Then Form should be Created successfully And "For Information" Action should be assigned to uploaded file without "Due Date"
When I click on Project as <Project_Name> AND I click on Folder "Form Distibution Group" AND I click on "Check Form Distribution" Form type
And I click on Create "New" Form link
#And I search "Check Form Distribution" Form Type And I click on searched formType
And I search "Check Form Distribution" Form Type And I click on searched formType ADDED
And I click on Distribute button of Create Form popup
And I search NoAccess Form Distribution Group in textbox
Then It should not displayed in Form Distribution List
When I close Form
And I deactivate created all groups
Then Groups should not displayed in "Manage Distribution Groups" popup
And It should not displayed in Distribution List when I create Form in FolderName "Form Distibution Group" in "Check Form Distribution" Form type
Examples: 
|DC_Center|Project_Name|
|UK|Automation_Admin_Objects_WS_UK|
#|US|Automation_Admin_Objects_WS_US|
#|AUS|Automation_Admin_Objects_WS_AUS|