Feature: View Document - JavaViewer

Background:
Given Script is Node specific

@P1T1
Scenario Outline: View Document with Java Viewer
Given Project DC <DC_Center> is available
And Script is "IE" browser specific
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
Then I have atleast one file in Files listing documents
When I click on any file for file viewing
Then Popup should be open with Java Viewer And Attribute of file should be available on LH Panel
And file should be open/view in Java Viewer
Examples: 
|DC_Center|Project|
|UK|Java_Viewer_UK_WS|
#|US|Java_Viewer_US_WS|
#|AUS|Java_Viewer_AUS_WS|