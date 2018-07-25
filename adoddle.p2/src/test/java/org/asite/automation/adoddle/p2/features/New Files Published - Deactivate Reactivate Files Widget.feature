Feature: New Files Published - Deactivate Reactivate Files Widget

@P2T6
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND Widgets "Deactivate files" AND "Reactivate files"
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
And I select any one file for "Deactivate files" widget
And I right click and select context menu option "More Options" AND I click on "Deactivate files" widget
Then "Deactivate files" popup should open
When I select all checkboxes of "Deactivate files" popup
And I click on "Deactivate" Button of popup
Then "The following document version(s) were successfully deactivated." deactivated file message should displayed
When I click on "OK" Button of popup
Then selected "Deactivated files" should not displayed in "Deactivated files" listing page
When I create "Active / Inactive" filter
And I set "Inactive Only" search filter type
Then selected "Deactivated files" should displayed in "Reactivated files" listing page
When I search selected file
And I right click and select context menu option "History" AND I click on "All" widget
Then New tab should opened and LH-panel "History" tab of "file" should activated
And selected file current status should set as "Deactivated"
When I search selected file
And I right click and select context menu option "More Options" AND I click on "Reactivate files" widget
Then "Reactivate files" popup should open
When I select all checkboxes of "Reactivate files" popup
And I click on "Reactivate" Button of popup
Then "The following document version(s) were successfully reactivated." reactivated file message should displayed
When I click on "OK" Button of popup
Then selected "Reactivated files" should not displayed in "Reactivated files" listing page
When I remove "Active / Inactive" filter
Then selected "Reactivated files" should displayed in "Deactivated files" listing page
When I search selected file
And I right click and select context menu option "History" AND I click on "All" widget
Then New tab should opened and LH-panel "History" tab of "file" should activated
And selected file current status should set as "Reactivated"
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|