Feature: Manage Form Status

@P2T4
Scenario Outline: Create Edit Assign Deactivate Form Status
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I right click on Project as <Project_Name> And I clicked on "Edit" AND I clicked on "Form Statuses" option into context menu List
Then "Manage Form Statuses" popup should open
When I create new all required Form Statuses
And I edit color and Font of "FormStatus_Access_Admin" form status to "Red" and "Arial Black" respectively with Cell Record "No"
And I edit color and Font of "FormStatus_Access_To_USE" form status to "Green" and "Aharoni" respectively with Cell Record "Yes"
And I click on Save button
Given I am on "Projects" tab
And I right click on Project as <Project_Name> And I clicked on "Edit" AND I clicked on "App Settings" option into context menu List
Then "Manage App Settings" popup should open
When I click on "Form Status Check" Fromtype "edit template" image link
Then New "Edit App settings" popup should be opened 
When I checked Created "Form Statuses" checkboxes And "No Access" checkbox disabled
And I click on Save button of Edit App setting popup
And I click on Cancel button
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I click on Project as <Project_Name> AND I click on Folder "Customer Forms" AND I click on "Form Status Check" Form type
Then "Create Form" button is enabled
When I click on "Create Form" button on Project Forms page
Then New "Create Form" popup should open
When I have entered "Title" Name And I Cleared text of "Calculation Box" And I also Cleared "Form Group Code" text
And I click on "From Status" dropdown list
Then Form Status dropdown list should not contains "No Access" accessibility
When I select "FormStatus_Access_To_USE" accessibility And entered Current Date
And I click on Send button
Then Uploaded form status should be "FormStatus_Access_To_USE" and background color "green" and font "Aharoni" with Cell Record "Yes"
When I right click on Created Form And drag mouse to "Edit" And I click on "Status" into context click menu list
Then I verify Form Status dropdown list with created Form Status that should not contains "No Access" accessibility
When I change selected Form Status into "FormStatus_Access_Admin" And I entered Status change Reason Note "This is a automation text" And I click on Change Status button
Then Uploaded form status should be "FormStatus_Access_Admin" and background color "red" and font "Arial Black" with Cell Record "No"
When I deactivated created all Form Statuses using secondary user
Then It should not be displayed in "Form Statuses" dropdown list when New Form should be created in folder "Customer Forms" of "Form Status Check" Form type
Examples: 
|DC_Center|Project_Name|
|UK|Automation_Admin_Objects_WS_UK|
#|US|Automation_Admin_Objects_WS_US|
#|AUS|Automation_Admin_Objects_WS_AUS|