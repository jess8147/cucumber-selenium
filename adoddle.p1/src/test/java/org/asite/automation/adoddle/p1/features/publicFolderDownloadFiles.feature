Feature: Public Folder - Download Files

@P1T5
Scenario Outline: Download documents from public folder without session
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I click on <Project_Name> project
And right click on "PublicFolder" AND Click on "Edit Folder"
Then "Edit Folder" popup should open
When I click on clip icon of direct link
And I copy link of opened "Copy to clipboard" window popup And click on ok button
Then link should be copied to clipboard AND "Copy to clipboard" window popup should be closed
When logout from Adoddle
And paste copied link on URL AND hit the URL
Then Folder should be open as public with <Project_Name> project
When Select multiple files from opened public folder
And click on Download button
Then "Download Files" popup should open
When I select all checkboxes AND click on download button of Download popup
Then Batch file should be created And Zip file should be downloaded into Local Directory folder
When I Unzip downloaded zip file Into Local
Then all selected documents files should be available into Local Directory
And User should be able to view file successfully
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|