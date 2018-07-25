Feature: Workflow Trigger via LinkDocument

@P2T2
Scenario Outline: Workflow Trigger via LinkDocument
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
When I Right Click on Project as <Project_Name> 
And drag mouse to "New" AND Click on "Folder"
Then "Add Folder" popup should open
And I Enter FolderName as "WF_LinkDocumentPrimaryFolder" AND I Click on Create button
Then "WF_LinkDocumentPrimaryFolder" should be created AND available in Folder Tree
And I Again Right Click on Project as <Project_Name>
And drag mouse to "New" AND Click on "Folder"
Then "Add Folder" popup should open
And I Enter FolderName as "WF_LinkDocumentSecondryFolder" AND I Click on Create button
Then "WF_LinkDocumentSecondryFolder" should be created AND available in Folder Tree
And I have focus on Project <Project_Name> AND folder "AutomationUploadFiles" folder 
When I clicked on "Add File (s)" button AND I have selected Multiple Files from Local for Scenario "LinkDocument"
Then All Workflow Files Should Available in Document Listing AND I edit Attributes of First Three Files for Scenario "LinkDocument"
Given I am on "Workflows" tab
And I have focus on  project <Project_Name> AND I click Link Workflow Triggers
And I Click on Existing Trigger "Automation_WF_P2_LinkDocument_Trigger"
Then popup "Edit Trigger" should open
And I Edit Folder Value in Trigger Conditions as "Automation_WF_LinkDocument"
Given I am on "Files" tab
And I have focus on Project <Project_Name> AND folder "AutomationUploadFiles" folder
And I Search AND Right Click on File
And I Select Link Files(s) Option
Then "Target Folder" Popup Should Open
And I Select Target Folder AND Click Button "Select"
Then "Link File(s)" Popup Should Open
And I Link Document With User "RFI Builder" AND Link "Static" in Target Folder
Then Link should be created sucessfully
When I switch to <Project_Users> user
Given I am on "Files" tab
And I have focus on Project <Project_Name> AND Target Folder
Then I Validate All Files Workflow status and MyActions for Scenario "LinkDocument"
Examples: 
|DC_Center|Project_Name|Project_Users|
|UK|AutomationTestProject|Automation UK|
#|US|Automatic_Test_US_WS|Automation US|
#|AUS|Automatic_Test_AUS_WS|Automation AUS|

@P2T2
Scenario Outline: Clean_Up Operations
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I Deactivated All Test_Files
And I Deactivated Created Folder <Folder1> in project <Project_Name>
And I Deactivated Created Folder <Folder2> in project <Project_Name>
Examples: 
|DC_Center|Project_Name|Folder1|Folder2|
|UK|AutomationTestProject|WF_LinkDocumentPrimaryFolder|WF_LinkDocumentSecondryFolder|
#|US|Automatic_Test_US_WS|WF_LinkDocumentPrimaryFolder|WF_LinkDocumentSecondryFolder|
#|AUS|Automatic_Test_AUS_WS|WF_LinkDocumentPrimaryFolder|WF_LinkDocumentSecondryFolder|