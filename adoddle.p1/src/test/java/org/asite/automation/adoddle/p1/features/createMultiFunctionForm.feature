Feature: Create Multi Function Form
	
@P1T2
Scenario Outline: Create Master Form and verify Childs
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "Automate Testing" folder
And I have focus on form template "Automate Testing Master Form"
When I click on "Create Form" button on Project Forms page
Then New "Create Form" popup should open
When I enter UserRef and Title for Automation Testing Form Title
And I attach single and multiple attachments in form
And I enter With Callback details
And I enter Without Callback details
And I enter Auto Create and Auto Distribution details
And I click on "Send" button
Then Automation Testing Master Form should get created
And Automation Testing Child Forms should be auto created
And Auto Distribution should distribute "For Information" action to selected user
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|