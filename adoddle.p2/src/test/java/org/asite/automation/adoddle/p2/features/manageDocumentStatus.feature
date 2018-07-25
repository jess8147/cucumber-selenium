Feature: Manage Document Status

@P2T2
Scenario Outline: Manage Document Statuses
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I create new testdata folder for project <Project_Name>
Given I am on "Projects" tab
And I set project as <Project_Name> and status as "File Statuses"
And I right click on project <Project_Name> And I select "File Statuses" in project edit options
Then "Manage Doc. Statuses" popup should open
When I create new all required Statuses
And I edit color and Font of "Status_Access_to_USE_Publish" status to "Red" and "Arial Black" respectively with Cell Record "No"
And I edit color and Font of "Status_Access_to_USE_Status_Change" status to "Green" and "Aharoni" respectively with Cell Record "Yes"
And I click on Save button
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I click on Project Name <Project_Name> AND I click on Folder Name "TestDataFolder" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select more then one Files to upload
And I click on Bulk Apply checkbox
And Status dropdown list should not contains "No Access" AND "Access to Use - Status Change" accessibility
When I fill all mendatory fields for Status
And I click on Upload button
Then Uploaded files status should be "Status_Access_to_USE_Publish" and background color "red" and font "Arial Black" with Cell Record "No"
When I multiple files select
And I right click on selected files And drag mouse to "Edit" And I click on "Status" into context click menu list
Then "Status Change" popup should open
And I verify Status dropdown list with created Status that should not contains "No Access" accessibility
When I change selected Status into "Status_Access_to_USE_Status_Change" And I entered Status change Reason "This is a automation text" And I click on Change Status button
Then Uploaded files status should be "Status_Access_to_USE_Status_Change" and background color "Green" and font "Aharoni" with Cell Record "Yes"
When I deactivate created all "Statuses" using secondary user
Then It should not be displayed in "Statuses" dropdown list when files should be upload in folder "TestDataFolder"
Examples: 
|DC_Center|Project_Name|
|UK|Automation_Admin_Objects_WS_UK|
#|US|Automation_Admin_Objects_WS_US|
#|AUS|Automation_Admin_Objects_WS_AUS|

@P2T2
Scenario Outline: Clean up operations
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I deactivate all uploaded status documents in folder
Examples:
|DC_Center|
|UK|
#|US|
#|AUS|