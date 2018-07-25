Feature: Dashboard LH Incomplete Tasks
	
@P1T4
Scenario Outline: Perform Incomplete Files Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Dashboard
And I have atleast one "Incomplete Tasks" on Dashboard
When I drag mouse on "Incomplete Tasks" count on Dashboard
Then Total number of "Incomplete Tasks" should display on mouse over on Dashboard
When I click "Incomplete Tasks" on Dashboard
Then I should re-directed to "Tasks" Tab	
And I validated multiple pending tasks in "Files" for "Incomplete Tasks"
When I perform multiple pending tasks in "Files" for "Incomplete Tasks"
Then Total number of "Incomplete Tasks" count should be decrease by "6"
And Total count on "Files" should be decrease by "6" on "Incomplete Tasks" page
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P1T4
Scenario Outline: Perform Incomplete App Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Dashboard
And I have atleast one "Incomplete Tasks" on Dashboard for Form
Then Total number of "Incomplete Tasks" should display on mouse over on Dashboard
When I click "Incomplete Tasks" on Dashboard
Then I should re-directed to "Tasks" Tab
And I validated multiple pending tasks in "Apps" for "Incomplete Tasks"
When I perform multiple pending tasks in "Apps" for "Incomplete Tasks"
Then Total number of "Incomplete Tasks" count should be decrease by "4"
And Total count on "Apps" should be decrease by "4" on "Incomplete Tasks" page
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|