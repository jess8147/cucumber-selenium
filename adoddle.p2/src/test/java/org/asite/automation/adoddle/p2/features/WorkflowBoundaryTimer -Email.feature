Feature:Workflow BoundaryTimer And Email Notification

Background:
Given Script is Node specific

@exclude
Scenario Outline: Complete Workflow With No Email Notification
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on subFolder "Automation_WF_Sub_Folder" in folder "Automation_WF_Folder"
When I have published multiple Documents successfully in subFolder "Automation_WF_Sub_Folder"
Then All published documents should have workStatus as "RUNING" AND workflowStage as "For Acknowledgement"
When I switch to <User1> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File1" AND I have validated action as "For Acknowledgement" successfully
When I perform action "For Acknowledgement" successfully
Then published document "AutoTest_WF_File1" should have workStatus as "RUNING" AND workflowStage as "For Acknowledgement"
When I switch to <User2> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File1" AND I have validated action as "For Acknowledgement" successfully
When I perform action "For Acknowledgement" successfully
Then published document "AutoTest_WF_File1" should have workStatus as "COMPLETED" AND workflowStage as "--"
And I have validated Email Occurrence as "This Action has not been executed yet." successfully on Pop up "Workflow Status" 
Examples: 
|DC_Center|Workspace|User1|User2|Distributed User Email|Email Password|
|UK|AutomationTestProject|RFI Builder|PA Builder|jasminprajapati@asite.com|asite987|
#|US|Automatic_Test_US_WS|RFI Builder|PA Builder|vmodi@asite.com|asite987|
#|AUS|Automatic_Test_AUS_WS|RFI Builder|PA Builder|jasminprajapati@asite.com|asite987|

@exclude
Scenario Outline: Complete Workflow After Email Notification
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <User1> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File2" AND I have validated action as "For Acknowledgement" successfully
And I have validated Email Occurrence as "1 time(s)" successfully on Pop up "Workflow Status"
When I login to <Distributed User Email> email account with password <Email Password>
Then Email should be received to distributed users with subject as "AutomationTest_EmailNotification"
And I am already logged in
When I switch to <User1> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File2" AND I have validated action as "For Acknowledgement" successfully
When I perform action "For Acknowledgement" successfully
Then All published documents should have workStatus as "RUNING" AND workflowStage as "For Acknowledgement"
When I switch to <User2> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File2" AND I have validated action as "For Acknowledgement" successfully
When I perform action "For Acknowledgement" successfully
Then published document "AutoTest_WF_File2" should have workStatus as "COMPLETED" AND workflowStage as "--"
Examples: 
|DC_Center|Workspace|User1|User2|Distributed User Email|Email Password|
|UK|AutomationTestProject|RFI Builder|PA Builder|jasminprajapati@asite.com|asite987|
#|US|Automatic_Test_US_WS|RFI Builder|PA Builder|vmodi@asite.com|asite987|
#|AUS|Automatic_Test_AUS_WS|RFI Builder|PA Builder|jasminprajapati@asite.com|asite987|

@exclude
Scenario Outline: Complete Workflow After Repeated Email Notification
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <User1> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File3" AND I have validated action as "For Acknowledgement" successfully
And I have validated Email Occurrence as "2 time(s)" successfully on Pop up "Workflow Status"
When I login to <Distributed User Email> email account with password <Email Password>
Then Email should be received to distributed users with subject as "AutomationTest_EmailNotification"
And I am already logged in
When I switch to <User1> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File3" AND I have validated action as "For Acknowledgement" successfully
When I perform action "For Acknowledgement" successfully
Then published document "AutoTest_WF_File3" should have workStatus as "RUNING" AND workflowStage as "For Acknowledgement"
When I switch to <User2> user
Given I am on "Files" tab
And I have search file with docRef as "AutoTest_WF_File3" AND I have validated action as "For Acknowledgement" successfully
When I perform action "For Acknowledgement" successfully
Then published document "AutoTest_WF_File3" should have workStatus as "COMPLETED" AND workflowStage as "--"
Examples: 
|DC_Center|Workspace|User1|User2|Distributed User Email|Email Password|
|UK|AutomationTestProject|RFI Builder|PA Builder|jasminprajapati@asite.com|asite987|
#|US|Automatic_Test_US_WS|RFI Builder|PA Builder|vmodi@asite.com|asite987|
#|AUS|Automatic_Test_AUS_WS|RFI Builder|PA Builder|jasminprajapati@asite.com|asite987|