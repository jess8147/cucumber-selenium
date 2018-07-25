Feature: Lock UnLock Activity

@P2T5
Scenario Outline:Create Directories And Upload-Lock Documents
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
When I Right Click on Project as <Project_Name> 
And drag mouse to "New" AND Click on "Folder"
Then "Add Folder" popup should open
And I Enter directoryName as "Auto_LockParentFolder" AND I Reset Folder Permissions
When I Click on Create button
Then "Auto_LockParentFolder" should be created successfully AND available in Folder Tree
And I Right Click on created directory "Auto_LockParentFolder"
And drag mouse to "New" AND Click on "Sub-Folder"
Then "Add Folder" popup should open
And I Enter directoryName as "Auto_LockSubFolder" AND I Reset Folder Permissions
When I Click on Create button
Then "Auto_LockSubFolder" should be created successfully AND available in Folder Tree
And I have focus on directory name as "Auto_LockParentFolder"
And I have published multiple Documents from Local in directory "Auto_LockParentFolder" successfully
And I have focus on directory name as "Auto_LockSubFolder"
And I have published multiple Documents from Local in directory "Auto_LockSubFolder" successfully
And I have focus on directory name as "Auto_LockParentFolder"
And I have search AND Distribute multiple(s) documents with different action to user "RFI Bloggs" successfully
And I have locked multiple(s) documents with different activities successfully
Examples: 
|DC_Center|Project_Name|User1|User2|
|UK|AutomationTestProject|RFI Bloggs|TC Bloggs|
#|US|Automatic_Test_US_WS|RFI Bloggs|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|TC Bloggs|

@P2T5
Scenario Outline:Lock-Unlock Activity File Distribution
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <User1> user
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
And I have Selected all documents AND I Context click
When I select context click menu options as "Share" AND "Distribute Files"
Then "Distribute" popup should open
And I have validated all locked file(s) should filtered successfully scenario "Distribution_beforeActionCompletion"
And I have Distributed Unlocked files with action "For Information" to user "RFI Bloggs"
When I have search file(s) having docRefs suffix as "Epoch1"
Then multiple files(s) having docRefs suffix as "Epoch1" should available with completed action as "For Distribution"
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
And I have Selected all documents AND I Context click
When I select context click menu options as "Share" AND "Distribute Files"
Then "Distribute" popup should open
And I have validated all locked file(s) should filtered successfully scenario "Distribution_afterActionCompletion"
And I have Distributed Unlocked files with action "For Information" to user "RFI Bloggs"
Examples: 
|DC_Center|Project_Name|User1|User2|
|UK|AutomationTestProject|RFI Bloggs|TC Bloggs|
#|US|Automatic_Test_US_WS|RFI Bloggs|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|TC Bloggs|

@P2T5
Scenario Outline:Lock-Unlock Activity Update Status
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <User1> user
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
And I have Selected all documents AND I Context click
When I select context click menu options as "Edit" AND "Status"
Then "Status Change" popup should open
And I have validated all locked file(s) should filtered successfully scenario "Status_beforeActionCompletion"
And I have Updated Status for Unlocked files to "For Information" successfully
When I have search file(s) having docRefs suffix as "Epoch2"
Then multiple files(s) having docRefs suffix as "Epoch2" should available with completed action as "For Status Change"
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
And I have Selected all documents AND I Context click
When I select context click menu options as "Edit" AND "Status"
Then "Status Change" popup should open
And I have validated all locked file(s) should filtered successfully scenario "Status_afterActionCompletion"
And I have Updated Status for Unlocked files to "For Information" successfully
Examples: 
|DC_Center|Project_Name|User1|User2|
|UK|AutomationTestProject|RFI Bloggs|TC Bloggs|
#|US|Automatic_Test_US_WS|RFI Bloggs|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|TC Bloggs|

@P2T5
Scenario Outline:Lock-Unlock Activity Commenting
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <User1> user
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
And I have Selected all documents AND I Context click
When I select context click menu options as "New" AND "Discussion"
Then "New" popup should open
And I have validated all locked file(s) should filtered successfully scenario "Commenting_beforeActionCompletion"
And I have created comment with title as "AutoTestComment" AND I have distributed to user "TC Bloggs"
When I have search file(s) having docRefs suffix as "Epoch3"
Then multiple files(s) having docRefs suffix as "Epoch3" should available with completed action as "For Comment"
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
And I have Selected all documents AND I Context click
When I select context click menu options as "New" AND "Discussion"
Then "New" popup should open
And I have validated all locked file(s) should filtered successfully scenario "Commenting_afterActionCompletion"
And I have created comment with title as "AutoTestComment" AND I have distributed to user "TC Bloggs"
Examples: 
|DC_Center|Project_Name|User1|User2|
|UK|AutomationTestProject|RFI Bloggs|TC Bloggs|
#|US|Automatic_Test_US_WS|RFI Bloggs|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|TC Bloggs|

@exclude
Scenario Outline:Lock-Unlock Activity Edit Attributes
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
And I have Selected all documents AND I Context click
When I select context click menu options as "Edit" AND "Attributes"
Then "Edit Attributes" popup should open
And I have validated all locked file(s) for activity "Edit Attributes" should filtered successfully
And I have edited AND validated poi as "For Information" AND private flag as "True" successfully
Examples: 
|DC_Center|Project_Name|User1|User2|
|UK|AutomationTestProject|TC Bloggs|RFI Bloggs|
#|US|Automatic_Test_US_WS|TC Bloggs|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|TC Bloggs|RFI Bloggs|

@exclude
Scenario Outline:Lock-Unlock Activity Revision Upload
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on directory name as "Auto_LockParentFolder"
When I click on "Add Files" button on file Listing
Then "Upload" popup should open
And I have Selected Documents Revisions from Local in folder "Auto_LockParentFolder"
Then "Message from webpage" Pop up should open
And I have successfully validated webPage message as "locked for revision upload and cannot be published."
And I have validated all locked file(s) for activity "Revision Upload" should filtered successfully
And I have published AND validated Documents Revisions from Local in folder "Auto_LockParentFolder"
Examples: 
|DC_Center|Project_Name|User1|User2|
|UK|AutomationTestProject|TC Bloggs|RFI Bloggs|
#|US|Automatic_Test_US_WS|TC Bloggs|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|TC Bloggs|RFI Bloggs|

@P2T5
Scenario Outline: Clean Up Operation
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I deactivate lock unlock activity folder
Examples: 
|DC_Center|Project_Name|Publisher|
|UK|AutomationTestProject|TC Bloggs|
#|US|Automatic_Test_US_WS|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|TC Bloggs|