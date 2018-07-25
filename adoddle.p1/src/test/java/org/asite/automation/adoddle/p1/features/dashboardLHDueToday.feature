Feature: Dashboard LH DueToday Tasks
	
@P1T4
Scenario Outline: Perform Due Today Files Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Dashboard
And I have atleast one "Due Today" on Dashboard
When I drag mouse on "Due Today" count on Dashboard
Then Total number of "Due Today" should display on mouse over on Dashboard
When I click "Due Today" on Dashboard
Then I should re-directed to "Tasks" Tab	
And I validated multiple pending tasks in "Files" for "Due Today"
When I perform multiple pending tasks in "Files" for "Due Today"
Then Total number of "Due Today" count should be decrease by "2"
And Total count on "Files" should be decrease by "2" on "Due Today" page
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P1T4
Scenario Outline: Perform Due Today App Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Dashboard
And I have atleast one "Due Today" on Dashboard for Form
Then Total number of "Due Today" should display on mouse over on Dashboard
When I click "Due Today" on Dashboard
Then I should re-directed to "Tasks" Tab
And I validated multiple pending tasks in "Apps" for "Due Today"
When I perform multiple pending tasks in "Apps" for "Due Today"
Then Total number of "Due Today" count should be decrease by "1"
And Total count on "Apps" should be decrease by "1" on "Due Today" page
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|