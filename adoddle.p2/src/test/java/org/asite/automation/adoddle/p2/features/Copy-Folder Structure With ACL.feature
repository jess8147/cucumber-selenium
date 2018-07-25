Feature: Copy-Folder With ACL

@P2T4
Scenario Outline: Copy Folder Structure With ACL
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
And I have selected folder permissions as "Default" "Roles" AND "Users"
When I click "Continue" button AND I have selected destination folder as "AutoTestCopiedFolder"
Then created folder structure should copied successfully in destination folder "AutoTestFolder"
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T4
Scenario Outline: Copy Folder Structure Without ACL_Default
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
And I have Reset folder permissions as "Default" successfully
When I click "Continue" button AND I have selected destination folder as "AutoTestCopiedFolder"
Then created folder structure should copied successfully in destination folder "AutoTestFolder"
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T4
Scenario Outline: Copy Folder Structure Without ACL_Roles
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
And I have Reset folder permissions as "Roles" successfully
When I click "Continue" button AND I have selected destination folder as "AutoTestCopiedFolder"
Then created folder structure should copied successfully in destination folder "AutoTestFolder"
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T4
Scenario Outline: Clean Up Operation
Given Project DC <DC_Center> is available
And I am already logged in
And I Clean up All Test Data folders successfully 
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|