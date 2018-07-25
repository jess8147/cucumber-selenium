Feature: Copy-Folder Structure Without ACL

@P2T3
Scenario Outline: Copy Folder Structure Without ACL_Users
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I context click on <Project> AND I mouse hover context click option "New"
And I have created Test folder as "AutoTestFolder" for copy folder structure successfully
And I context click on Parent Folder "AutomationTestParent_Folder" AND I mouse hover "Copy"
When I select context click menu option as "Folder Structure"
Then "Copy Folder Structure" popup should open
When I selected last child folder "AutoTestSubFolder4"
Then All Superseded Parent AND Childs folder also get selected successfully
And I have Reset folder permissions as "Users" successfully
When I click "Continue" button AND I have selected destination folder as "AutoTestCopiedFolder"
Then created folder structure should copied successfully in destination folder "AutoTestFolder"
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T3
Scenario Outline: Copy Folder Structure Without ACL
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I context click on <Project> AND I mouse hover context click option "New"
And I have created Test folder as "AutoTestFolder" for copy folder structure successfully
And I context click on Parent Folder "AutomationTestParent_Folder" AND I mouse hover "Copy"
When I select context click menu option as "Folder Structure"
Then "Copy Folder Structure" popup should open
When I selected last child folder "AutoTestSubFolder4"
Then All Superseded Parent AND Childs folder also get selected successfully
And I have Reset All folder permissions as "Default" "Roles" AND "Users" successfully
When I click "Continue" button AND I have selected destination folder as "AutoTestCopiedFolder"
Then created folder structure should copied successfully in destination folder "AutoTestFolder"
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T3
Scenario Outline: Copy Folder Structure Without Privilege And Folder Rights
Given Project DC <DC_Center> is available
And I am already logged in
And I switch to <User> user
Given I am on "Files" tab
And I context click on Parent Folder "AutomationTestParent_Folder" AND I mouse hover "Copy"
When I select context click menu option as "Folder Structure"
Then "Copy Folder Structure" popup should open
When I have selected child folder "AutoTestSubFolder1"
Then All Superseded Parent AND Childs folder also get selected successfully
When I click "Continue" button AND I have validated workspace <Project> should Grayed out
And I click "Cancel" button AND Dismiss popup "Copy Folder Structure"
Examples: 
|DC_Center|Project|User|
|UK|AutomationTestProject|PA Bloggs|
#|US|Automatic_Test_US_WS|PA Bloggs|
#|AUS|Automatic_Test_AUS_WS|PA Bloggs|

@P2T3
Scenario Outline: Clean Up Operation
Given Project DC <DC_Center> is available
And I am already logged in
And I Clean up All Test Data folders successfully 
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|