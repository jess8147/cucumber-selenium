Feature: HTML Upload With Custom Attributes

@P1T2
Scenario Outline: Upload files through HTML upload with custom attributes AND Verify Files Tab Incomplete Action Count
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab
And I drag mouse on "Incomplete Actions" count on Files tab for upload
And I have focus on "CustomAttributes" folder
When I click on "Add Files" button of file Listing
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have selected more then one Files into Local
And I click on Bulk Apply checkbox
Then Header Panel should be displayed
When I enter all Values into header for Attributes AND Select Overwrite
And click on Apply to All button
Then all attributes values should be filled with Values
When I click on "Distribute Files" button of upload popup
Then "To :" AND "Subject :" textboxes should be available on upload popup
When I select Distribution Group in textbox
And I click on Upload button
Then Total number of "Incomplete Actions" action count should be increase for upload
And "For Information" Action should be assigned to "CurrentUser" AND All Files should be uploaded successfully AND It should be available in document listing
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|