Feature: New Files Published - Compare Files Widget

@P2T7
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND Widgets "Compare Files" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I set compare Files Flag
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select any one file for "Compare Files" widget
And I right click and select context menu option "Compare" AND I click on "Files" widget
Then "Files" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select more then two files for "Compare Files" widget
And I right click and select context menu option "Compare" AND I click on "Files" widget
Then "Files" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select files for perform Compare Files widget
And I right click and select context menu option "Compare" AND I click on "Files" widget
Then New tab should open with both compared files in HTML viewer
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|