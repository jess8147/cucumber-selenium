Feature: Move Multiple Files
 
@P1T4
Scenario Outline: Move files To Destination Folder
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click on folder with title "AutoMoveTestFilesFolder2"
And I get document list for "AutoMoveTestFilesFolder2"
When I click on folder with title "AutoMoveTestFilesFolder1"
And I select multiple files from document listing
And click on "Move Documents" on documents dropdown options
Then "Move Documents" window page should open
When I select "AutoMoveTestFilesFolder2" Folder in "Select Destination Folder" tree
And I click on "Continue" button
Then Move Files confirmation page should open
When I click on "Continue" button
And I click on OK confirmation button
Then All Selected documents should be moved to "AutoMoveTestFilesFolder2"
And I goto Audit History and select "access" And verify move files path
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|

@P1T4
Scenario Outline: Move files back to Source Folder
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click on folder with title "AutoMoveTestFilesFolder1"
And I get document list for "AutoMoveTestFilesFolder1"
When I click on folder with title "AutoMoveTestFilesFolder2"
And I select multiple files from document listing
And click on "Move Documents" on documents dropdown options
Then "Move Documents" window page should open
When I select "AutoMoveTestFilesFolder1" Folder in "Select Destination Folder" tree
And I click on "Continue" button
Then Move Files confirmation page should open
When I click on "Continue" button
And I click on OK confirmation button
Then All Selected documents should be moved to "AutoMoveTestFilesFolder1"
And I goto Audit History and select "access" And verify move files path
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|