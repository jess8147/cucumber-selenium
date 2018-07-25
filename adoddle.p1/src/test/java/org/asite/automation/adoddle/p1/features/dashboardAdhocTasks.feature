Feature: Dashboard Adhoc Tasks
	
@P1T4
Scenario Outline:Perform Adhoc Tasks
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Tasks" tab
And I have created new group "AutomationTestGroup" with two members as "ActionsTest User1" AND "ActionsTest User2" in <Project> successfully
And I have assigned multiple "AdhocAutoUserTask" tasks to group members "ActionsTest User1" AND "ActionsTest User2" successfully
And I have atleast one "Adhoc -Incomplete Tasks" on Dashboard
When I drag mouse on "Incomplete Tasks" count on Dashboard
Then Total number of "Incomplete Tasks" should display on mouse over on Dashboard
When I click "Incomplete Tasks" on Dashboard	
Then I should re-directed to "Tasks" Tab	
And I validated multiple pending "Adhoc-Incomplete Tasks" of group "AutomationTestGroup" successfully
And I have updated AND delegated tasks to group member "ActionsTest User2"
Then Total number of "Incomplete Tasks" count should be decrease by "2"
When I logged in as <ActionsTest User2>
Then I should re-directed to Dashboard of user "ActionsTest User2"
And I am on Dashboard
When I drag mouse on "Incomplete Tasks" count on Dashboard
Then Total number of "Incomplete Tasks" should display on mouse over on Dashboard
When I click "Incomplete Tasks" on Dashboard	
Then I should re-directed to "Tasks" Tab
And I have updated multiple pending adhoc tasks successfully by user "ActionsTest User2"
Then Total number of "Incomplete Tasks" count should be decrease by "3"
Examples: 
|DC_Center|Project|
#|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
|AUS|Automatic_Test_AUS_WS|

@P1T4
Scenario Outline:Clean up operation
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Tasks" tab
And I have updated all pending tasks AND mark Group "AutomationTestGroup" inactive successfully
Examples: 
|DC_Center|Project|
#|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
|AUS|Automatic_Test_AUS_WS|