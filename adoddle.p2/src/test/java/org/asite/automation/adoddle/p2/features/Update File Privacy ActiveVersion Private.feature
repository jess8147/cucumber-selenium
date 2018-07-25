Feature: Update File Privacy ActiveVersion Private

@P2T7
Scenario Outline: UpdateFilePrivacy-ActiveVersion_Private
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus on "AutomationTest_Folder7" folder in <Project>
And I Search "WF_UpdatePrivacy_AutoTestFile" AND I validate all oldRevisions access modifiers for scenario "ActiveVersion_Private"
When I switch to <User1> user
Given I am on "Files" tab
And I have focus on "AutomationTest_Folder7" folder in <Project>
And I Search "WF_UpdatePrivacy_AutoTestFile" AND I get current revision
And I click on Add Files button
Then "Upload" popup should open
When I click on "Select File (s)" button AND I have selected "single" file revisions from Local
And I Enter Mandatory Attributes of TestFile for scenario "ActiveVersion_Private"
And I Click Upload Button
And I have focus on "AutomationTest_Folder7" folder in <Project>
Then "WF_UpdatePrivacy_AutoTestFile" Should Available in Document Listing having Workflow Status as "RUNNING" AND Workflow Stage as "For Status Change" for scenario "ActiveVersion_Private"
And I switch to <User2> user
Given I am on "Files" tab
And I have focus on "AutomationTest_Folder7" folder in <Project>
When I Search "WF_UpdatePrivacy_AutoTestFile" having MyActions as "For Status Change" AND I click "For Status Change" for scenario "ActiveVersion_Private"
Then Popup "Status Change" should open
And I perform "Status Change" Action sucessfully for scenario "ActiveVersion_Private"
And I Validate "WF_UpdatePrivacy_AutoTestFile" file version for scenario "ActiveVersion_Private"
And "WF_UpdatePrivacy_AutoTestFile" Workflow Status should be "COMPLETED" AND Workflow Stage as "--"
Examples: 
|DC_Center|Project|User1|User2|
|UK|Automation_UpdatePrivacy_WF_UK_Project|TC Bloggs|RFI Bloggs|
#|US|Automation_UpdatePrivacy_WF_US_Project|TC Bloggs|RFI Bloggs|
#|AUS|Automation_UpdatePrivacy_WF_AUS_Project|TC Bloggs|RFI Bloggs|

@exclude
Scenario Outline: UpdateFilePrivacy-Clean_Up File Revisions
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus on "AutomationTest_Folder7" folder in <Project>
And I have deactivated "ActiveVersion" of files
Examples: 
|DC_Center|Project|
|UK|Automation_UpdatePrivacy_WF_UK_Project|
#|US|Automation_UpdatePrivacy_WF_US_Project|
#|AUS|Automation_UpdatePrivacy_WF_AUS_Project|