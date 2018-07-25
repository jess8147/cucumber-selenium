Feature: File Comparison Classic

Background:
Given Script is Node specific

@exclude
Scenario Outline: Compare two files with Thin Client Workspace
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
Then Document listing should open with all documents of workspace
When I select two files
And click on "Compare Documents" icon
Then popup should open with both compared files in viewer
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|

@P1T1
Scenario Outline: Compare two files with Java Viewer Workspace
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
Then Document listing should open with all documents of workspace
When I select two files
And click on "Compare Documents" icon
Then popup should open with both compared files in viewer
Examples: 
|DC_Center|Project|
|UK|Java_Viewer_UK_WS|
#|US|Java_Viewer_US_WS|
#|AUS|Java_Viewer_AUS_WS|