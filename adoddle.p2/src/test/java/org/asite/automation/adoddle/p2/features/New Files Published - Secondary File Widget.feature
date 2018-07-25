Feature: New Files Published With Tasks - Secondary File Widget

@P2T7
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND "Secondary File" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select More then one files for "Attachment" widget
And I right click and select context menu option "Edit"
Then "Secondary File" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select "Link Document" for "Secondary File" Attachment
And I right click and select context menu option "Edit"
Then "Secondary File" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select any one file for "Attachment" widget
And I right click and select context menu option "Edit" AND I click on "Secondary File" widget
Then "Attachments" popup should open
When I clicked on "Select File (s)" button And I click on "Attach" Button for Attachment
Then "Attachment" Image should displayed And after click on "Attachment" Image New tab should opened And Attached file should be open/view in viewer
When I click on Download Image
Then "Attachment" File should download in local directory
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|