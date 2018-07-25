Feature: Manage Purpose of Issues

@P2T3
Scenario Outline: Create Edit Deactivate POI
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I create new testdata folder for project <Project_Name>
Given I am on "Projects" tab
And I set project as <Project_Name> and poi as "Purpose of Issue"
And I right click on project <Project_Name> And I select "Purpose of Issue" in project edit options
Then "Manage Purpose of Issue" popup should open
When I create new all required Purpose of Issue
And I click on Save button
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I click on Project Name <Project_Name> AND I click on Folder Name "TestDataFolder" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select more then one Files from Local to upload
And I click on Bulk Apply checkbox
Then PurposeOfIssue dropdown list should not contains "No Access" AND "Access to Use - Attributes Change" accessibility
When I fill all mendatory fields
And I click on Upload button
Then Files should be uploaded successfully And assign POI "POI_Access_to_USE_Publish" should be available with uploaded files
When I multiple files select
And I right click on selected files And drag mouse to "Edit" And I click on "Attributes"
Then "Edit Attributes" popup should open
When I click on Bulk Apply checkbox on Edit Attributes popup
Then I verify PurposeOfIssue dropdown list with created PurposeOfIssue that should not contains "No Access" accessibility
When I change selected "POI" into "POI_Access_to_USE_Attribute_Change" And I click on "Assign Attributes" button
Then changed POI "POI_Access_to_USE_Attribute_Change" should be available with uploaded files
When I deactivate created all "Purpose Of Issue"
Then It should not be displayed in "Purpose Of Issue" list when files should be uploaded in folderName "POI_Status_Folder"
Examples:
|DC_Center|Project_Name|
|UK|Automation_Admin_Objects_WS_UK|
#|US|Automation_Admin_Objects_WS_US|
#|AUS|Automation_Admin_Objects_WS_AUS|

@P2T3
Scenario Outline: Clean up operations
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I deactivate all uploaded poi documents in folder
Examples:
|DC_Center|Project_Name|
|UK|Automation_Admin_Objects_WS_UK|
#|US|Automation_Admin_Objects_WS_US|
#|AUS|Automation_Admin_Objects_WS_AUS|