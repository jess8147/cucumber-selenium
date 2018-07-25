Feature: Recent Forms Widgets - Attach Docs

@P2T5
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts with "Attach Docs" action Hyperlink
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "Attach Docs" action for <B> user
When I switch to <B> user
And I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Attach Docs action widget with "Attach Docs" action
And I click on selected form hyperlink of "Attach Docs" action
Then form should viewed in opened new tab
When I replyed to selected form without attached external Document
And I closed opened new window
Then "Attach Docs" action should not completed to selected form
When I click on selected form hyperlink of "Attach Docs" action
Then form should viewed in opened new tab
When I Attached external Document to selected form
Then document attachment should displayed on "View Form" RH-panel
When I closed opened new window
Then "Attach Docs" action should completed to selected form
Examples: 
|DC_Center|B|
|UK|Automation UK|
#|US|Automation US|
#|AUS|Automation AUS|