Feature: Recent Forms Widgets - For Acknowledgement

@P2T3
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts with "For Acknowledgement" action Hyperlink
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "For Acknowledgement" action for <B> user
When I switch to <B> user
When I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform For Acknowledgement action widget with "For Acknowledgement" action
And I click on selected form hyperlink of "For Acknowledgement" action
Then "Acknowledgement" popup should open
When I select checkbox for complete "For Acknowledgement" action
And click on Submit button of "Acknowledgement" popup
Then "For Acknowledgement" action should completed to selected form
Examples: 
|DC_Center|B|
|UK|Automation UK|
#|US|Automation US|
#|AUS|Automation AUS|