Feature: New Files Published With Actions - View Widget

@P2T4
Scenario Outline: New Files Published Dashboard "TODAY", "PAST WEEK" Highcharts AND "View" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts
Then I should redirect to "Files" tab
When I select More then one files for "View" widget
And I right click and select context menu option "View"
Then "View" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select file for perform View widget
And I right click and select context menu option "View"
Then New tab should opened And file should be open/view in viewer 
And Attribute of file should be available on top
When I closed opened new window
Then "For Information" action should completed for viewed document
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|