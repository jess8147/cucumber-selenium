Feature: Move Multiple Folders - Classic

@P1T4
Scenario Outline: Test Data Setup
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
And I have validated folders "AutoMoveTestFolder1" AND "AutoMoveTestFolder2" should available in Folder Listing
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|

@P1T4
Scenario Outline: Move parent folder
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click on folder with title "AutoMoveTestFolder1"
Then In Document listing "AutoMoveTestFolder1" folder should be open
When I click on "Move Folders" from folders dropdown options for "AutoMoveTestFolder1"
Then "Move Folders" window page should open
When I select "AutoMoveTestFolder2" Folder in "Select Destination Folder" tree
And I click on move folders "Continue" button
Then Move child folders confirmation page should open
When I click on move files "Continue" button
And I click on OK confirmation button
Then "AutoMoveTestFolder1" folder should be moved into child "AutoMoveTestFolder2" with its child folder and all documents
And I goto Audit History and select "access" link And verify move folders path
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|

@P1T4
Scenario Outline: Move child folder as parent folder
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click on folder with title "AutoMoveTestFolder2"
Then In Document listing "AutoMoveTestFolder2" folder should be open
When I click on "AutoMoveTestFolder1" on LH folder tree
Then In Document listing "AutoMoveTestFolder1" folder should be open
When I click on "Move Folders" from folders dropdown options for "AutoMoveTestFolder1"
Then "Move Folders" window page should open
When I select "All Workspace Documents" Folder in "Select Destination Folder" tree
When I click on move folders "Continue" button
Then Move child folders confirmation page should open
When I click on move files "Continue" button
And I click on OK confirmation button
Then "AutoMoveTestFolder1" folder should be moved into parent "All Workspace Documents" with its child folder and all documents
And I goto Audit History and select "access" link And verify move folders path
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|