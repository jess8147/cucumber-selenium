Feature: View Document - Thin Client Viewer

@P1T5
Scenario Outline: View Document with Thin Client Viewer 
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
Then I have atleast one file in Files listing documents
When I click on any file
Then Popup should be open with Thin Client Viewer And Attribute of file should be available on LH Panel
And file should be open/view in viewer 
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|