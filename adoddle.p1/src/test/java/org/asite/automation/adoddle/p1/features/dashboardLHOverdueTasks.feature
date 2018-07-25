Feature: Dashboard LH Overdue Tasks
	
@P1T4
Scenario Outline: Perform Overdue Files Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Dashboard
And I have atleast one "Overdue Tasks" on Dashboard
When I drag mouse on "Overdue Tasks" count on Dashboard
Then Total number of "Overdue Tasks" should display on mouse over on Dashboard
When I click "Overdue Tasks" on Dashboard
Then I should re-directed to "Tasks" Tab	
And I validated multiple pending tasks in "Files" for "Overdue Tasks"
When I perform multiple pending tasks in "Files" for "Overdue Tasks"
Then Total number of "Overdue Tasks" count should be decrease by "1"
And Total count on "Files" should be decrease by "1" on "Overdue Tasks" page
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|