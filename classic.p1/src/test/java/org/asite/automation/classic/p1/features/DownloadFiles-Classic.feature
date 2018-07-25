Feature: Download single Files

@P1T3
Scenario Outline: Download single file
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click "All Workspace Documents"
Then I have atleast one file in Files listing documents
When I Click file icon in "Type" column of any file
Then Document should be downloaded in local
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|

@exclude
Scenario Outline: Download Multiple files with with extract option
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
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
When I select all checkboxes with "Extract files on Download" checkbox
And I select download files path AND click on download button of Download popup
Then Batch file should be created
And all files should be available into Local Directory
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|