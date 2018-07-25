Feature: Dashboard LastLogin Forms

@P2T3
Scenario Outline: Recent Forms Dashboard "LAST LOGIN", "TODAY", AND "PAST WEEK"
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "set" <Project_Name> project as Default
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
When I create new form with "For Information" action to <User_ID> user in <Project_Name> project "Form Distibution Group" folder in "Check Form Distribution" form type
And I cleared list and map values
And I logOut and re login using <User_ID> User
When I get "LAST LOGIN", "TODAY", "YESTERDAY" AND "PAST WEEK" total Recent Forms highcharts count
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form with "For Information" action to <User_ID> user in <Project_Name> project "Form Distibution Group" folder in "Check Form Distribution" form type
Then form should created successfully on "Project Forms" tab And "For Information" action assigned to given form
And I cleared list and map values
Given I am on "FM" tab
When I create new form with "Distribute" action to <User_ID> user in <Project_Name> project "Recent FM form Group" folder in "Recent FM Form" form type
Then form should created successfully on "FM" tab And "Distribute" action assigned to given form
And I cleared list and map values
Given I am on "Contracts" tab
When I create new form with "Distribute" action to <User_ID> user in <Project_Name> project "Recent Contracts form Group" folder in "Recent Contracts Form" form type
Then form should created successfully on "Contracts" tab And "Distribute" action assigned to given form
And I cleared list and map values
When I logOut and re login using <User_ID> User
Then I should redirect to "Dashboard" tab
And total "LAST LOGIN", "TODAY", AND "PAST WEEK" count of Recent Forms axis should increased And "YESTERDAY" count same as previous
When I click on "LAST LOGIN" highcharts of Recent Forms
Then Total Forms listing and forms count same as "LAST LOGIN" highcharts of Recent Forms Count
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts of Recent Forms
Then Total Forms listing and forms count same as "TODAY" highcharts of Recent Forms Count
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts of Recent Forms
Then Total Forms listing and forms count same as "PAST WEEK" highcharts of Recent Forms Count
When I logOut and re login using <User_ID> User
Then I should redirect to "Dashboard" tab
And total "LAST LOGIN" count of Recent Forms should set as 0
When I "reset" <Project_Name> project from Default
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|Automation_Forms_LastLogin_UK_Project|auto.lastlogin_user2@atest.com|
#|US|Automation_Forms_LastLogin_US_Project|auto.lastlogin_user2@atest.com|
#|AUS|Automation_Forms_LastLogin_AUS_Project|auto.lastlogin_user2@atest.com|