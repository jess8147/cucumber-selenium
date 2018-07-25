Feature: Edit Document Attributes - Assign Metadata Priviledge

@P2T4
Scenario Outline: Verify Assign Document Metadata Privilege
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Projects" tab
And "Assign Document Metadata" privilege is available for <User Role> on <Project>
When I "uncheck" checkbox for "Assign Document Metadata" privilege and save
Given I am on "Files" tab
And I have created new test folder "AutomationTestFolder" for <Project>
And I open edit folder dialogue for created folder
And I provided "Publish" access to <Primary User>
And I saved updated folder
When I upload "2" documents in "New" folder
And I switch to <Secondary User> user
Given I am on "Files" tab
And I have focus on <Project> project
When I upload "2" documents in "New" folder
When I select all document and right click on them
And I click on "Edit Attributes" option in context options
Then "Edit Attributes" popup should open
And User should be able to edit 4 documents
And I switch to <Primary User> user
Given I am on "Files" tab
And I have focus on new test folder
When I select all document and right click on them
And I click on "Edit Attributes" option in context options
Then "Edit Attributes" popup should open
And User should be able to edit 2 documents
Given I am on "Projects" tab
And "Assign Document Metadata" privilege is available for <User Role> on <Project>
When I "check" checkbox for "Assign Document Metadata" privilege and save
Given I am on "Files" tab
And I have focus on new test folder
When I select all document and right click on them
And I click on "Edit Attributes" option in context options
Then "Edit Attributes" popup should open
And User should be able to edit 4 documents
Examples: 
|DC_Center|Project|Primary User|Secondary User|User Role|
|UK|AutomationTestProject|Automation UKP2|Automation UK|Workspace - Administrator|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|Workspace - Administrator|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|Workspace - Administrator|

@P2T4
Scenario Outline: Clean up Testdata
Given Project DC <DC_Center> is available
And I am already logged in
And I switch to <Secondary User> user
And I deactivate the edit attributes folder for <Project>
Examples: 
|DC_Center|Project|Secondary User|
|UK|AutomationTestProject|Automation UK|
#|US|Automatic_Test_US_WS|Automation US|
#|AUS|Automatic_Test_AUS_WS|Automation AUS|
