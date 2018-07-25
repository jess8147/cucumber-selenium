Feature: Recent Forms Widgets - For Information and View

@P2T5
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts with "Tasks" AND "For Information" Widget And "View" Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "For Information" action for <B> user
When I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "For Information" action for <B> user
Given I am on "FM" tab
When I create new form in folder "Recent FM form Group" and form type "Recent FM Form" with "For Information" action for <B> user
Given I am on "Contracts" tab
When I create new form in folder "Recent Contracts form Group" and form type "Recent Contracts Form" with "For Information" action for <B> user
When I switch to <B> user
When I click on "PAST WEEK" highcharts of Recent Forms
And I select More then one forms for "View" widget
And I right click and select context click menu option "View"
Then "View" context menu Option should displayed as disabled
When I click on "PAST WEEK" highcharts of Recent Forms
And I select single form for perform "View" widget
And I right click and select context click menu option "View"
Then form should viewed in opened new tab
When I closed opened new window
Then "For Information" action should completed for viewed form
Given I reload the page
When I click on "PAST WEEK" highcharts of Recent Forms
And I select single form for perform For Information action widget with "For Information" action
And I click on selected form hyperlink of "For Information" action
Then form should viewed in opened new tab
When I closed opened new window
#Then "For Information" action should completed for viewed form
Then "For Information" action should completed successfully for viewed form
Given I reload the page
When I click on "PAST WEEK" highcharts of Recent Forms
And I select More then one forms for "For Information" widget
And I right click and select context click menu option "Tasks" AND I click on "For Information" widget
#Then "Actions - For Information" popup should open
Then "Task - For Information" popup should open
And selected forms should displayed on "Tasks - For Information" popup
When I click on "OK" link of popup
Then "For Information" action should completed to selected form
Examples: 
|DC_Center|B|
|UK|Automation UK|
#|US|Automation US|
#|AUS|Automation AUS|