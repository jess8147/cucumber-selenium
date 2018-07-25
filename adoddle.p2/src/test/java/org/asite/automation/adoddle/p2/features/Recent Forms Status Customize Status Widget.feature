Feature: Recent Forms Widgets - Customize Status

@P2T3
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts AND "Customize Status" Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder"Form Distibution Group" and form type "Check Form Distribution" with form status "RecentForm_Customize_Status"
Then form should created successfully on "Project Forms" tab And form status should set as "RecentForm_Customize_Status"
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Customize Status widget
And I right click and select context click menu option "Edit" AND I click on "Customize Status" widget
Then "Manage Form Statuses" popup should open
When I edit color and Font of selected form status to "Green" and "Arial Black" respectively with Cell Record "Yes"
And I click on Save button of popup
Then selected form status background color "Green" and font "Arial Black" with Cell Record "Yes"
When I right click and select context click menu option "Edit" AND I click on "Customize Status" widget
Then "Manage Form Statuses" popup should open
When I reset "Reset to Default" Customize Status
And I click on Save button of popup
Then selected form status set as default
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|