Feature: Batch Print Files with Universal Web Viewer Project

@P1T4
Scenario Outline: BatchPrint with Universal Web Viewer Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I set Landing URL 
And I am on Files tab
And I focus on Universal Web Viewer <project> 
And I have selected multiple files on document listing
When I click on More Options
And I click on Print File icon
Then "Print Document" popup should open 
And All Selected files should be avialable on Print Document popup
When I select Incl. Markups AND Fit Inside Banners 
And click on "Print" link
Then New Window should open with title as "Print"
And I have enter file name as "BatchPrintAutoTestDataFile" AND I click on "Save"
And I have validated "BatchPrintAutoTestDataFile" file size successfully with local file
Examples: 
|DC_Center|project|
|UK|Automation_Universal_Web_Viewer_UK_WS|
#|US|Automation_Universal_Web_Viewer_US_WS|
#|AUS|Automation_Universal_Web_Viewer_AUS_WS|