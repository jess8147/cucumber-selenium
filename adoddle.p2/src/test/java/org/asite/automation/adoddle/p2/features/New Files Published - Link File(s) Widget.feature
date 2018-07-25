Feature: New Files Published - Link File(s) Widget

@P2T7
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND "Link File(s)" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I have atleast two folders in project
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select "Placeholder" for "Link File(s)"
And I right click on selected files And drag mouse to "Link File(s)" context click Option
Then "Link File(s)" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select "IFC" file for perform "Link File(s)"
And I right click on selected files And drag mouse to "Link File(s)" context click Option
Then "Link File(s)" context menu Option should displayed as disabled
Given I am on "Dashboard" tab
When I deSelect selected checkboxes for "PAST WEEK" HighchartsAxis
And I select any one file for "Link File(s)" widget
And I right click on selected files And drag mouse to "Link File(s)" context click Option
Then "Target Folder" popup should open
When I select destination Folder And click on "Select" button
Then "Link File(s)" popup should open
When I select <Secondary> User in "To" field And select "Static" type And Click on "Submit" button
Then Link should be successfully created
Given I have focus on Destination folder
And Link document should be available in destination folder
When I switch to <Secondary> user
Then For Information action should assign to selected User
Examples: 
|DC_Center|Project_Name|User_ID|Secondary|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|Auto_FWidget UK2|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|Auto_FWidget US2|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|Auto_FWidget AUS2|