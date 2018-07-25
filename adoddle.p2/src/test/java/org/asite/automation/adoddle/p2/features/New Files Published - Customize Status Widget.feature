Feature: New Files Published - Customize Status Widget

@P2T5
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND "Customize Status" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select More then one files for "Customize Status" widget
And I right click and select context menu option "Edit" AND I click on "Customize Status" widget
Then "Customize Status" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select any one file for "Customize Status" widget
And I right click and select context menu option "Edit" AND I click on "Customize Status" widget
Then "Manage Doc. Statuses" popup should open
When I edit color and Font of selected status to "Green" and "Arial Black" respectively with Cell Record "Yes"
And I click on Save button of popup
Then selected file status background color "Green" and font "Arial Black" with Cell Record "Yes"
When I search selected file
And I right click and select context menu option "Edit" AND I click on "Customize Status" widget
Then "Manage Doc. Statuses" popup should open
When I reset "Reset to Default" Customize Status
And I click on Save button of popup
Then selected file status set as default
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|