Feature: New Files Published With Tasks - For Comment Coordination Widget

@P2T3
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts with "Tasks" AND "For Comment Coordination" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Comment Coord." action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I get total "Incomplete Action" of files tab
And I select files for perform "For Comment Coordination" Actions widget
And I right click and select context menu option "Tasks" AND I click on "For Comment Coordination" widget
Then "For Comment Coordination" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select single file for perform "For Comment Coordination" Action widget
And I right click and select context menu option "Tasks" AND I click on "For Comment Coordination" widget
Then "For Comment Coordination" popup should open
When I select "Release Comments" checkbox for Action "For Comment Coordination"
And I click on "Submit" link of popup
Then "For Comment Coordination" actions should completed for selected Actions documents
And total "Incomplete Action" of files tab should decreased
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|