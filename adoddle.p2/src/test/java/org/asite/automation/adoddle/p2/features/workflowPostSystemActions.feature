Feature: Workflow Post System Actions

@P2T3
Scenario Outline: Post System Actions
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I have cloned existing project <Project> for <DC_Center>
And I have created new workflow folder under cloned project
Given I am on "Workflows" tab
When I verify "Pre" and "Post" system actions
And I create System task of type "Distribute Files"
And I create trigger with "Folder" and "Purpose of issue" and "Document Status" conditions on "Publish Documents" event with action mode "Post"
Given I am on "Files" tab
And I publish "2" documents with trigger "favorable" conditions
Then Actions should be distributed to only "favorable" documents
When I publish "2" documents with trigger "non-favorable" conditions
And Actions should not be distributed to "non-favoratble" documents
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject_Template|
#|US|Automatic_Test_US_Template|
#|AUS|Automatic_Test_AUS_Template|

@P2T3
Scenario Outline: Clean Up Operation
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I deactivate cloned project
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|