Feature: Recent Forms Widgets - Status History

@P2T4
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts AND "Status" Widget And "Status History" Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "Assign Status" action for <B> user
When I switch to <B> user
And I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Status widget with "Assign Status" action
And I click on selected form hyperlink of "Assign Status" action
Then "Status Change" popup should opened in new tab
When I change Form Status from current status AND entered Status Change notes AND click on ChangeStatus Button
Then updated Status should displayed on View Form RH-panel
When I closed opened new window
Then updated Status should set on selected Recent form
And "Assign Status" action should completed to selected form
When I switch to <A> user
Given I am on "Contracts" tab
When I create new form in folder "Recent Contracts form Group" and form type "Recent Contracts Form" with "Assign Status" action for <B> user
When I switch to <B> user
And I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Status widget with "Assign Status" action
And I right click and select context click menu option "Edit" AND I click on "Status" widget
Then "Status Change" popup should opened in new tab
When I change Form Status from current status AND entered Status Change notes AND click on ChangeStatus Button
Then updated Status should displayed on View Form RH-panel
When I closed opened new window
Then updated Status should set on selected Recent form
And "Assign Status" action should completed to selected form
## /******* Remove this Comment After Resolving [NOODLE-72532] & [NOODLE-72536] *******/ ##
#When I right click and select context click menu option "History" AND I click on "Status" widget
#Then New tab should opened and Form "History" tab should activated
#And "Status" dropdown selected in Form History tab
#And form all "Status" history should displayed as "Status Changed"
#And updated new status should displayed in "Status" history page
#And I closed opened new window
Examples: 
|DC_Center|A|B|
|UK|Automation UKP2|Automation UK|
#|US|Automation USP2|Automation US|
#|AUS|Automation AUSP2|Automation AUS|