Feature: Recent Forms Widgets - Start Workflow

@P2T3
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts AND Create New "Form" and "Start Workflow " Widget
Given Project DC <DC_Center> is available
And I am already logged in
When I click on "PAST WEEK" highcharts of Recent Forms
And I create form using "Form" widget
And I right click and select context click menu option "New" AND I click on "Form" widget
Then "Create Form" popup should open of Recent Forms
And create rights "Yes" forms should displayed And create rights "No" forms should not displayed
When I search "Create Yes Ass No Form" Form Type And I click on searched formType
And I create new form with "For Information" action for <A> user
Then form should created successfully on Recent Forms listing page
When I select created new form for "Start Workflow " widget
And I right click and select context click menu option "New" AND I click on "Project Form" widget
Then only "Create Yes Ass Yes Form" form should displayed and other form should not displayed
When I search "Create Yes Ass Yes Form" Form Type And I click on searched formType
And I click on "Attachment" button for Form
And I attach any one document And I click on "Attach" Button
And I create workflow for form with "For Information" action for <A> user
Then form should created successfully on Recent Forms listing page
And forms all "Attachments" and "Apps" should associated with workflow form
Examples: 
|DC_Center|A|
|UK|Automation UKP2|
#|US|Automation USP2|
#|AUS|Automation AUSP2|