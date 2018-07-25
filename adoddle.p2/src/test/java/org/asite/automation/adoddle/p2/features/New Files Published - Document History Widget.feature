Feature: New Files Published - Document History Widget

@P2T7
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND "History" Widget with "All", "Distribution", "Revisions" AND "Status" type
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select More then one files for "History" widget
And I right click and select context menu option "History"
Then "History" context menu Option should displayed as disabled
# "Distribution History"
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select file for perform History widget in "Distribution" type
And I right click and select context menu option "History" AND I click on "Distribution" widget
Then New tab should opened and LH-panel "History" tab of "file" should activated
And "Distribution" type selected in dropdown list of History tab
And file all "Distribution" history should displayed as "Distributed"
And I close History tab page
# "Status History"
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select file for perform History widget in "Status" type
And I right click and select context menu option "Edit" AND I click on "Status" widget
Then "Status Change" popup should open
When I change status And I entered Status change Reason "For Check Status History" And I click on "Change Status" button of popup
Then updated status should set to selected document
When I right click and select context menu option "History" AND I click on "Status" widget
Then New tab should opened and LH-panel "History" tab of "file" should activated
And "Status" type selected in dropdown list of History tab
And file all "Status" history should displayed as "Status Changed"
And Old Status value and New Status value should displayed on "Status" history page
And I close History tab page
# "Revision History"
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select file for perform History widget in "Revisions" type
And I right click and select context menu option "History" AND I click on "Revisions" widget
Then New tab should opened and LH-panel "History" tab of "file" should activated
And "Revision" type selected in dropdown list of History tab
And file Latest Revision should displayed on top row
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|