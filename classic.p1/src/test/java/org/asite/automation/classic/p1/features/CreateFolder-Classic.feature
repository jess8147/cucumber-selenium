Feature: Create Folder Classic

@P1T3
Scenario Outline: Create Edit Deactivate Folder
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
Then I should redirect on "All Workspace Documents" Listing
When I Click on "Create New Parent Folder" img icon
Then "Create Parent Folder" page should be open
When I Enter FolderName AND I Click on "Create Folder" button
Then Folder should be created AND Folder should be available in Folder Tree
When I Click on created Folder AND Click on "Edit Folder" from Folder dropdown options
Then "edit folder" page should be open
When I Change Folder Name AND Click on "Update Changes" button
Then Updated Folder should be available in Folder Tree
When I Click on Updated Folder AND Click on "Edit Folder" from Folder dropdown options
Then "edit folder" page should be open
When I select "Deactivate This Folder?" AND Click on "Update Changes" button
Then "Deactivate folder" Confirmation page should open
When I click on Continue button
Then Updated Folder should be deactivated AND not be available in Folder Tree
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|