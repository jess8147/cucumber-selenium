Feature: Dashboard Due Today Tasks

@exclude
Scenario Outline: Verify DueToday Tasks  
Given Project DC <DC_Center> is available
And I am already logged in
Then "Files - Incomplete Tasks" widget should be displayed
Given I am on "Files" tab
And I distribute multiple actions to <Secondary User>
And I logOut and re login using <Secondary User> User
Then "Files - Incomplete Tasks" widget should have multiple actions
When User performs "DueToday" action from  
Examples: 
|DC_Center|Secondary User|
|UK|auto_uk@atest.com|
#|US|auto_uk@atest.com|
#|AUS|auto_uk@atest.com|