Feature: Workflow Trigger via Status Change

@P2T2
Scenario Outline: Workflow Trigger via StatusChange
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
When I Right Click on Project as <Project_Name> 
And drag mouse to "New" AND Click on "Folder"
Then "Add Folder" popup should open
And I Enter FolderName as "WF_StatusChangeFolder" AND I Click on Create button
Then "WF_StatusChangeFolder" should be created AND available in Folder Tree
And I have focus on Folder "WF_StatusChangeFolder"
When I clicked on "Add File (s)" button AND I have selected Multiple Files from Local for Scenario "StatusChange"
Then All Workflow Files Should Available in Document Listing AND I edit Attributes of First Three Files for Scenario "StatusChange"
Given I am on "Workflows" tab
And I have focus on  project <Project_Name> AND I click Link Workflow Triggers
And I Click on Existing Trigger "Automation_WF_P2_StatusChange_Trigger"
Then popup "Edit Trigger" should open
And I Edit Folder Value in Trigger Conditions as "Automation_WF_StatusChange"
When I switch to <User> user
Given I am on "Files" tab
And I have focus on Folder "WF_StatusChangeFolder"
And I Search All Files having Status as "For Status Change"
And I Click on Files having My Actions as "For Status Change"
Then "Status Change" Popup Should Open
And I perform "For Status Change" action successfully for All files's
Then I Validate All Files Workflow status and MyActions for Scenario "StatusChange"
Examples: 
|DC_Center|Project_Name|User|
|UK|AutomationTestProject|RFI Bloggs|
#|US|Automatic_Test_US_WS|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|

@P2T2
Scenario Outline: Clean_Up Operations
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I Deactivated All Test_Files
And I Deactivated Created Folder <Folder3> in project <Project_Name>
Examples: 
|DC_Center|Project_Name|Folder3|
|UK|AutomationTestProject|WF_StatusChangeFolder|
#|US|Automatic_Test_US_WS|WF_StatusChangeFolder|
#|AUS|Automatic_Test_AUS_WS|WF_StatusChangeFolder|