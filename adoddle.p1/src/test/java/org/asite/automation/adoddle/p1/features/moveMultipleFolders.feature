Feature: Move Multiple Folders with Files

@P1T1
Scenario Outline: Test Data Setup
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have validated folders "AutoMoveTestFolder1" AND "AutoMoveTestFolder2" should available in Folder Listing
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P1T1
Scenario Outline: Move parent folder from right click
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I click on <Project_Name> project
And I right click on "AutoMoveTestFolder1" And drag mouse to "Move Folder"
Then "Target Folder" popup should open
When I select "AutoMoveTestFolder2" Folder
And click on submit button
Then "AutoMoveTestFolder1" folder should be moved into "AutoMoveTestFolder2" with its child folder and all documents
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P1T1
Scenario Outline: Move child folder as parent folder from more Options
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I click on <Project_Name> project
And I click on "AutoMoveTestFolder2" into <Project_Name> project
And I select "AutoMoveTestFolder1" And click on More Options
When I click on Move Folder icon on Options popup
Then "Target Folder" popup should open
When I select <Project_Name> Project
And click on submit button
Then "AutoMoveTestFolder1" folder should be moved into <Project_Name> with its child folder and all documents
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|