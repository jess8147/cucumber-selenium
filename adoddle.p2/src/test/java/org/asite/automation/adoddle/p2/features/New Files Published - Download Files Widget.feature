Feature: New Files Published With Tasks - Download Files Widget

@P2T6
Scenario Outline: New Files Published Dashboard "PAST WEEK" AND Widgets "Download Files" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select "Placeholder" for "Download"
And I right click and select context menu option "Download Files"
Then "Download Files" context menu Option should displayed as disabled
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select files for perform Download Files widget
And I right click and select context menu option "Download Files"
Then "Download Files" popup should open
When I select all checkboxes AND click on download button of "Download Files" popup
Then "Download" Batch file should be created And Zip file should be downloaded into Local Directory
When I Unzip "Download" zip file
Then "Download" files should available in Local Directory
And "For Information" action should completed for downloaded documents
Examples: 
|DC_Center|User_ID|Project_Name|
|UK|auto.nfpw_uk1@atest.com|AutomationTestProject|
#|US|auto.nfpw_us1@atest.com|Automatic_Test_US_WS|
#|AUS|auto.nfpw_aus1@atest.com|Automatic_Test_AUS_WS|