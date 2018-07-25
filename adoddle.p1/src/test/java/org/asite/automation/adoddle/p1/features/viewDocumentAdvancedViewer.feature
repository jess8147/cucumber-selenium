Feature: View Document Advanced Viewer

Background:
Given Script is Node specific

@exclude
Scenario Outline: View Document in Advanced Viewer
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
And I am already logged in 
And I am on "Files" tab 
And I have atleast one document in document listing
When I click on any file
Then New Tab should be open with Advanced Viewer AND file should be open/view in viewer 
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|