Feature: Clean Folders

@cleanFolders
Scenario Outline: Clean Folders with Text
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
And I am on "Files" tab
And I have focus on <Project> project
Given I deactivate all folders with text <FolderText>
Examples: 
|DC_Center|Project|Username|Password|FolderText|
|UK|AutomationTestProject|auto@atest.com|Test@12345|201804|
#|US|Automatic_Test_US_WS|auto@atest.com|Test@12345|201804|
#|AUS|Automatic_Test_AUS_WS|auto@atest.com|Test@12345|201804|