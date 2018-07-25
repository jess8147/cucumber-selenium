Feature: Review Draft AutoSave Distribution - Attachments
	
@P2T4
Scenario Outline: Review Draft AutoSave - Attachments
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
And I have focus on <Project> project
When I select any form and open it
Then Form should be viewed in new window
When I distribute selected form to <Secondary User>
And I switch to <Secondary User> user
Then Form should be available to <Secondary User> 
When I edit form and attach "AutoSaveAttachment" file with AutoSave
And I click on "Cancel" button and close current window
And I search and re-open above form and select "Edit Draft"
Then Draft should have been auto saved
And "Discard Draft" button should be visible
When I edit form by removing one file and attaching "AutoSaveAttachment" file with AutoSave
And I click on "Cancel" button and close current window
And I search and re-open above form and select "Edit Draft"
Given I click on "Send" button
Then Form should get created with valid attachments
Examples: 
|DC_Center|Project|Primary User|Secondary User|
|UK|AutomationTestProject|Jasmin Prajapati|Automation UK|
#|US|Automatic_Test_US_WS|Vishal Modi|Automation US|
#|AUS|Automatic_Test_AUS_WS|Vishal Modi|Automation AUS|