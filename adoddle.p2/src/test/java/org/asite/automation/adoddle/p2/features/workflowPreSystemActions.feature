Feature: Workflow Pre System Actions

@P2T3
Scenario Outline: Groovy Script Pre System Action
Given Project DC <DC_Center> is available
And I am already logged in
And I have created new workflow folder under project <Project>
And I have enabled "Simple Upload" on the folder
Given I am on "Workflows" tab
And I have groovy script available with Pre conditions "xC" and "yC" and "zC"
When I update the folder for existing Pre-Post Groovy Workflow trigger
And  I try to upload "3" documents
Then Groovy script should fail "All" conditions for "All" file
When I have enabled "Attributes" on file upload dialogue
And I have satisfied "All" groovy script conditions for "First" file
Then Groovy script should fail "All" conditions for "Second" file
And Groovy script should fail "All" conditions for "Third" file
When I have satisfied "xC" groovy script conditions for "Second" file
And I have satisfied "yC" groovy script conditions for "Second" file
Then Groovy script should fail "zC" conditions for "Second" file
And Groovy script should fail "All" conditions for "Third" file
When I have satisfied "zC" groovy script conditions for "Second" file
Then Groovy script should fail "All" conditions for "Third" file
When I have satisfied "All" groovy script conditions for "Third" file
Then User should be able to upload file successfully
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T3
Scenario Outline: Clean Up Operation
Given Project DC <DC_Center> is available
And I am already logged in
And I deactivate the workflow groovy folder for <Project>
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|