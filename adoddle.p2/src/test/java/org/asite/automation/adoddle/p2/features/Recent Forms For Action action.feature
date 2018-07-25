Feature: Recent Forms Widgets - For Action

@P2T4
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts with "For Action" action Hyperlink
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "For Action" action for <B> user
When I switch to <B> user
When I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform For Action action widget with "For Action" action
And I click on selected form hyperlink of "For Action" action
Then "Task" popup should open
When I select checkbox for complete "For Action" action
And I entered Remarks text of "Action" popup
And click on Submit button of "Action" popup
Then "For Action" action should completed to selected form
Examples: 
|DC_Center|B|
|UK|Automation UK|
#|US|Automation US|
#|AUS|Automation AUS|