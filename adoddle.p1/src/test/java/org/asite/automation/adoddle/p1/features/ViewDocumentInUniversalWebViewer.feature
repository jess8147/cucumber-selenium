Feature: View Document in Universal Web Viewer Project

@P1T2
Scenario Outline: View Document in Universal Web Viewer Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I set Landing URL 
And I am on Files tab
And I focus on Universal Web Viewer <project>
And I have atleast one document in document listing
When I view any file of Universal Web Viewer project
Then New Tab should be open with PDFTron viewer AND file should be open in viewer 
And Attribute of file should be available on top
Examples: 
|DC_Center|project|
|UK|Automation_Universal_Web_Viewer_UK_WS|
#|US|Automation_Universal_Web_Viewer_US_WS|
#|AUS|Automation_Universal_Web_Viewer_AUS_WS|