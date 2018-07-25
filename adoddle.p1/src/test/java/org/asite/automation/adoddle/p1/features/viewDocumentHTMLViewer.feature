Feature: View Document 

@P1T2
Scenario Outline: View Document in HTML Viewer
Given Project DC <DC_Center> is available
And I am already logged in 
And I am on "Files" tab 
And I have atleast one document in document listing
When I click on any file
Then New Tab should be open with HTML viewer AND file should be open/view in viewer 
And Attribute of file should be available on top
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|