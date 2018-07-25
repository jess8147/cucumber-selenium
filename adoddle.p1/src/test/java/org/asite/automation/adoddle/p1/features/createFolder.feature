Feature: Create, Edit, Deactivate - Folder

@P1T5
Scenario Outline: Create Folder via Right Click
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
Then I should redirect to "Files" tab
When I Right Click on Project as <Project_Name> 
And drag mouse to "New" AND Click on "Folder"
Then "Add Folder" popup should open
When I Enter FolderName AND I Click on "Create" button
Then Folder should be created AND Folder should be available in Folder Tree
When I Right Click on Folder AND Click on "Edit Folder" 
Then "Edit Folder" popup should open
When I Change Folder Name AND Click on "Update" button
Then Updated Folder should be available in Folder Tree
When I Right Click on Updated Folder AND Click on "Edit Folder" AND Select "Deactivate This Folder?" Checkbox AND Click on "Update" button
Then Updated Folder should not be available in Folder Tree
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|


@exclude
Scenario Outline: Create Folder via More Options
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
Then I should redirect to "Files" tab
Given I have focus on <Project_Name> project
When I click on More Options
And I click on "New Folder" link option on More Options popup
When I Enter FolderName AND I Click on "Create" button
Then Folder should be created AND Folder should be available in Folder Tree
When I Right Click on Folder AND Click on "Edit Folder" 
Then "Edit Folder" popup should open
When I Change Folder Name AND Click on "Update" button
Then Updated Folder should be available in Folder Tree
When I Right Click on Updated Folder AND Click on "Edit Folder" AND Select "Deactivate This Folder?" Checkbox AND Click on "Update" button
Then Updated Folder should not be available in Folder Tree
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|