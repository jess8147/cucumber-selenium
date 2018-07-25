Feature: Move Multiple Files

@P1T1
Scenario Outline: Move files via right click option
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I click on <Project_Name> project
And I click on "AutoMoveTestFilesFolder1" into <Project_Name> project
And I click on "AutoMoveFileFolder1" of subfolder
And I select multiple files
And right click on selected files And drag mouse to "Move Files"
Then "Target Folder" popup should open
When I select "AutoMoveTestFilesFolder2" Folder
And click on submit button
Then Selected documents should be moved to "AutoMoveTestFilesFolder2"
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P1T1
Scenario Outline: Move files via more Options
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I click on <Project_Name> project
And I click on "AutoMoveTestFilesFolder2" into <Project_Name> project
And I select multiple files
And click on More Options
When I click on Move Files icon on Options popup
Then "Target Folder" popup should open
When I select "AutoMoveTestFilesFolder1" Folder
And I select "AutoMoveFileFolder1" Folder
And click on submit button
Then all Selected documents should be moved to "AutoMoveTestFilesFolder1" in "AutoMoveFileFolder1"
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|