Feature: Download Multiple Files

@P1T3
Scenario Outline: Download Multiple files with with zip option
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
And I checked file checkbox AND click on file link Distribution history
Then new tab should be opened
When I click "comments/associations" link AND I click "View Comment Details" link
Then new tab should open for comment details
And I get the all attached AND Associated Files Name And close the new opened tab
When I click on "Download Selected Documents" img icon
Then "Download Documents" window should open
When I select all checkboxes AND click on download button of Download popup
Then Batch file should be created And Zip file should be downloaded into Local Directory
When I Unzip downloaded zip file
Then all attached files and Parent Document and attachment to parent document should be available into Local Directory
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|