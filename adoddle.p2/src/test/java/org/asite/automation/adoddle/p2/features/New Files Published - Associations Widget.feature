Feature: New Files Published - Associations Widget

@P2T5
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND Widgets "Associations" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select More then one files for "Associations" widget
And I right click and select context menu option "More Options" AND I click on "Associations" widget
Then "Associations" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select any one file for "Associations" widget
And I right click and select context menu option "More Options" AND I click on "Associations" widget
Then New tab should opened and LH-panel "Associations" tab of "file" should activated
And file should open/view in RH-panel viewer
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|