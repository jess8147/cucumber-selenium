Feature: Recent Forms Widgets - Distribute

@P2T2
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts AND "Share" and "Distribute" Widget with "Distribution History" type Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "Distribute" action for <B> user
And I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "Distribute" action for <C> user
When I switch to <B> user
And I click on "PAST WEEK" highcharts of Recent Forms
And I select More then one forms for "Distribution" widget
And I right click and select context click menu option "Share" AND I click on "Distribute" widget
Then "Distribute" context menu Option should displayed as disabled
When I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Distribution widget with "Distribute" action
And I click on selected form hyperlink of "Distribute" action
Then "Distribute" popup should open
When I assign "For Information" action to <B> users
And I click on "Distribute" button
Then "Distribute" action should completed AND "For Information" action should assigned to given form
When I switch to <C> user
And I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Distribution widget with "Distribute" action
And I right click and select context click menu option "Share" AND I click on "Distribute" widget
Then "Distribute" popup should open
When I assign "For Information" action to <C> users
And I click on "Distribute" button
Then "Distribute" action should completed AND "For Information" action should assigned to given form
When I right click and select context click menu option "History" AND I click on "Distribution" widget
Then New tab should opened and Form "History" tab should activated
And "Distribution" dropdown selected in Form History tab
And form all "Distribution" history should displayed as "Distributed"
And from "Distributed" status should displayed in Distribution history page
And I closed opened new window
Examples: 
|DC_Center|A|B|C|
|UK|Automation UKP2|Automation UK|Auto Test|
#|US|Automation USP2|Automation US|Auto Test|
#|AUS|Automation AUSP2|Automation AUS|Auto Test|