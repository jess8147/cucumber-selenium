Feature: New Files Published - Start Workflow Widget

@P2T5
Scenario Outline: New Files Published Dashboard "TODAY" Highcharts AND "Start Workflow" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts
Then I should redirect to "Files" tab
When I select any one file for "Workflow" widget
And I click on "More  Options" AND select "Project Form" from Options popup list
Then New "Create Form" popup should open for Model Form
When I search "Form Status Check" Form Type And I click on searched formType
And I entered form "Title" AND form details
And I click on Send button
Then "Associations" image should displayed in selected "Workflow" file
When I click on Associations image
Then "Attachments & Associations" popup should open
And created Form should displayed on "Attachments & Associations" popup
When I click on Form "ID"
Then New tab should opened And File should displayed as Associations on "Attachments & Associations" popup
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|