Feature: New Files Published - High Medium Low Flags Widget

@P2T3
Scenario Outline: Set High \ Medium \ Low flags
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
And I upload "7" files in "AutomationUploadFiles" folder
And I clear all flags for "Files"
When I click on "TODAY" highcharts
Then I should redirect to "Files" tab
When I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" on Files tab And I get total files name
When I select more then one Files for "High" flag
And I right click on selected files And drag mouse to "Flag" And I click on "High" flag in context menu options
When I select more then one Files for "Medium" flag
And I right click on selected files And drag mouse to "Flag" And I click on "Medium" flag in context menu options
When I select more then one Files for "Low" flag
And I right click on selected files And drag mouse to "Flag" And I click on "Low" flag in context menu options
When I create "Flag" filter
And I set "High" flag into filter
Then only "High" flag files should displayed
When I set "Medium" flag into filter
Then only "Medium" flag files should displayed
When I set "Low" flag into filter
Then only "Low" flag files should displayed
When I set "High", "Medium" AND "Low" flag in Flag filter
Then all flag filter files should displayed
When I select all files AND right click on selected files And drag mouse to "Flag" And I click on "Clear Flag"
Then all flag filter files should set as "No Flag" files
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.nfpw_uk1@atest.com|
#|US|Automatic_Test_US_WS|auto.nfpw_us1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.nfpw_aus1@atest.com|