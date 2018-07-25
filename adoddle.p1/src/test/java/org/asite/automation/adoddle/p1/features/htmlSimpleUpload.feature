Feature: Upload Files - HTML Simple upload

@P1T5
Scenario Outline: Upload Files with HTML upload
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
When I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have selected more then one Files from Local
And I have entered all mendatory attributes
And I click on "Distribute Files" button AND I distribute to Distribution Group
And I click on Upload button
Then All Files should be uploaded successfully AND I should redirected on Document listing
And Uploaded documents should be available in document listing
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@exclude
Scenario Outline: Upload Files with DragNDrop
Given Project DC <DC_Center> is available
And I am on "Files" tab
When I click on Project Name "AutomationTestProject" AND I click on Folder Name "AutomationUploadFiles" AND I have drag and dropped file on document listing
And I have entered all mendatory attributes
And I click on "Distribute Files" button AND I distribute to Distribution Group
And I click on Upload button
Then All Files should be uploaded successfully AND I should redirected on Document listing
And Uploaded documents should be available in document listing
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|