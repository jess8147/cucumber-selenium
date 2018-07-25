Feature: View-Document-JavaViewer To ensure View Document functionality is working fine

@exclude
Scenario Outline: View Document with Java Viewer
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
Then I have atleast one document in document listing
When I click on any file for file viewing
Then Popup should be open with Java Viewer And Attribute of file should be available on LH Panel
And file should be open/view in Java Viewer
Examples: 
|DC_Center|Project|
|UK|Java_Viewer_UK_WS|
#|US|Java_Viewer_US_WS|
#|AUS|Java_Viewer_AUS_WS|