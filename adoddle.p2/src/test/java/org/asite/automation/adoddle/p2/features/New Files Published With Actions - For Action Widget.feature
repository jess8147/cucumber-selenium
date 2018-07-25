Feature: New Files Published With Tasks - For Action Widget

@P2T4
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts with "Tasks" AND "For Action" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Action" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I get total "Incomplete Action" of files tab
And I select files for perform "For Action" Actions widget
And I right click and select context menu option "Tasks" AND I click on "For Action" widget
Then "For Action" popup should open
And selected files should displayed on "For Action" popup
When I select "Click here to complete your Action" checkbox AND I entered Remarks input text "For Action"
And I click on "Submit" Button of popup
Then "For Action" actions should completed for selected Actions documents
And total "Incomplete Action" of files tab should decreased
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|