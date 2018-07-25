Feature: Link Document - Static Link Within And Inter Project

@P2T2
Scenario Outline: Link Document - Verify Static Link Within Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Files" tab
And I have focus on <Project> project
And I create testdata folder for "Static" link
#Static Link Within Project
And I have focus on "CustomAttributes" folder
And I upload multiple documents for "Static" link operation
And I have "Automation_LinkTestDocument" document in document listing to link
And I select any document to link
And click on "Link File(s)" from more options
Then "Target Folder" popup should open
When I select link destination "Static link" folder "within" project And click on "Select" button
Then "Link File(s)" popup should open
When I select User in "To" field And select "Static" type And Click on "Submit" button
Then Link should be successfully created
Given I have focus on "CustomAttributes" folder
When I update poi of linked source files with "Update the status for Static Links" as checked to "true"
When I change status of source file with "Update the status for Static Links" as checked to "true"
Then "Static" Link document should be available in target folder "within" project
Then Target file "within" project with "Static" link should have updated poi as source file to "true"
Then Target file "within" project with "Static" link should have updated status as source file to "true"
Given I deactivate the "Static" link testdata folder
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T2
Scenario Outline: Verify Static Link Inter Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I have cloned existing project <Template> for <DC_Center>
And I break inheritance of the cloned project
Given I create testdata folder in cloned test project
And I am on "Files" tab
Given I have focus on <Project> project
#Static Link Inter Project
And I have focus on "CustomAttributes" folder
And I have "Automation_LinkTestFile" document in document listing to link
And I select any document to link
And click on "Link File(s)" from more options
Then "Target Folder" popup should open
When I select cloned test project on popup
And I select link destination "Static link" folder "inter" project And click on "Select" button
Then "Link File(s)" popup should open
When I select User in "To" field And select "Static" type And Click on "Submit" button
Then Link should be successfully created
Given I have focus on "CustomAttributes" folder
When I update poi of linked source files with "Update the status for Static Links" as checked to "true"
When I change status of source file with "Update the status for Static Links" as checked to "true"
When I click on cloned test workspace
Then "Static" Link document should be available in target folder "inter" project
Then Target file "inter" project with "Static" link should have updated poi as source file to "false"
Then Target file "inter" project with "Static" link should have updated status as source file to "false"
Given I deactivate cloned test project
Examples: 
|DC_Center|Project|Template|
|UK|AutomationTestProject|AutomationTestProject_Template|
#|US|Automatic_Test_US_WS|Automatic_Test_US_Template|
#|AUS|Automatic_Test_AUS_WS|Automatic_Test_AUS_Template|