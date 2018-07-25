Feature: Public Folder - Classic

@P1T2
Scenario Outline: Download documents from public folder without session for classic
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click on folder named "PublicFolder"
Then Document listing should open for "PublicFolder"
When I click on "Edit Folder" from Folder dropdown options
Then "Edit Folder" page should open
When I copy Direct Access URL
And logout from Classic
And paste copied link on URL AND hit the URL
Then Folder should be open as public with <Project> Workspace
When Select multiple files from opened public folder
And click on "Download Selected" button
Then "Download Documents" window should open
When I select all checkboxes except "Extract files on Download" AND click on download button of Download popup
Then Batch file should be created And Zip file should be downloaded into Local Directory folder
When I Unzip downloaded zip file Into Local
Then all selected documents files should be available into Local Directory
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|