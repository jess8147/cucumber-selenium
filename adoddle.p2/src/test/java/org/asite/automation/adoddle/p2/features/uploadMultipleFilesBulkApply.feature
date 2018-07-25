Feature: Upload Multiple Files - Bulk Apply

@P2T2
Scenario Outline: Bulk Apply - Multiple Files upload
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab
And I have focus on <Project_Name> project
And I have focus on "CustomAttributes" folder
And I have current revision of existing files 
When I click on "Add Files" button on file Listing
Then "Upload" popup should open
When I select multiple revision files "AutomationBulkApplyTestFile1.pdf" and "AutomationBulkApplyTestFile2.pdf" 
And I select multiple newly created files
When I enter all Values into header and apply all after selecting Overwrite
And I click on "Distribute Files" button of upload popup
Then "To :" AND "Subject :" textboxes should be available on upload popup
When I select Distribution User in textbox and click on Upload button
Then New files should get uploaded with new attributes
And Revision files should get updated with new attributes
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|