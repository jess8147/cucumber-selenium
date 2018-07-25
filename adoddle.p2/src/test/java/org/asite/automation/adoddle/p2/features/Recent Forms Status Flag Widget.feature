Feature: Recent Forms Widgets - Flag

@P2T7
Scenario Outline: Recent Forms Dashboard "TODAY" Highcharts AND "Flag" Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
And I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "For Information" action for <A> user
And I create new form in folder "Form Distibution Group" and form type "Check Form Distribution" with "For Information" action for <A> user
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts of Recent Forms
And I select more then one Forms for "High" flag
And I right click and select context click menu option "Flag" AND I click on "High" widget
Then selected forms flag should set as "High" flag
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts of Recent Forms
And I select more then one Forms for "Medium" flag
And I right click and select context click menu option "Flag" AND I click on "Medium" widget
Then selected forms flag should set as "Medium" flag
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts of Recent Forms
When I select more then one Forms for "Low" flag
And I right click and select context click menu option "Flag" AND I click on "Low" widget
Then selected forms flag should set as "Low" flag
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts of Recent Forms
And I select selected Forms for "No Flag" flag
And I right click and select context click menu option "Flag" AND I click on "Clear Flag" widget
Then selected forms flag should set as "No Flag" flag
Examples: 
|DC_Center|A|
|UK|Automation UKP2|
#|US|Automation USP2|
#|AUS|Automation AUSP2|