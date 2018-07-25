Feature: Create Edit System Actions

@P2T6
Scenario Outline: Create All System Actions
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I have cloned project as <Cloned_Project> from existing template <Project_Template> for <DC_Center>
And I navigate on workflow tab
And I have clicked cloned project
And I click on "Configure System Action" link
Then "New Task Configuration" popup should open
And I create System task Name as "Auto_Distribute_SystemTask"
And I Select Action Type as "Distribute Files"
And I Select Users Distribution group Role AND Org as "Auto RFI" "PA Bloggs" "AutomationTestDistributionGroup" "SystemAction_AutoTestRole" AND "AutoTest- ! @ $ ^ & ( ) - = + { } , . ] `Org"
And I put Subject as "System_Task_Test"
And I click on "Create" button to create system task
When I click again on System Action as "Auto_Distribute_SystemTask"
Then popup "Edit Action Configuration" should open
And I edit Name as "Auto_Distribute_SystemTask_Edited"
Then "Auto_Distribute_SystemTask" action should Edited as "Auto_Distribute_SystemTask_Edited" sucessfully for workflow
And I click on "Configure System Action" link
Then "New Task Configuration" popup should open
And I put System task Name as "Auto_StatusChange_SystemTask"
And I Select Action Type as "Status Change"
And I Select Change Status from "For Approval" To "For Information"
And I put Reason for Status Change Notes as "Via Workflow"
And I click on "Create" button to create system task
Then "Auto_StatusChange_SystemTask" System Action should created sucessfully AND Available on System Actions List
And I click on "Configure System Action" link
Then "New Task Configuration" popup should open
And I put System task Name as "Auto_ClearTasks_SystemTask"
And I Select Action Type as "Clear Tasks"
And I Select Clear Actions For "All Versions" AND Actions To Be Cleared for all Actions
And I click on "Create" button to create system task
Then "Auto_ClearActions_SystemTask" System Action should created sucessfully AND Available on System Actions List
And I click on "Configure System Action" link
Then "New Task Configuration" popup should open
And I put System task Name as "Auto_UpdateFilePrivacy_SystemTask"
And I Select Action Type as "Update File Privacy"
And I Select Mark File Version As:"Private" AND Select Version as "All Verisons"
And I click on "Create" button to create system task
Then "Auto_UpdateFilePrivacy_SystemTask" System Action should created sucessfully AND Available on System Actions List
And I click on "Configure System Action" link
Then "New Task Configuration" popup should open
And I put System task Name as "Auto_PublishAsPDF_SystemTask"
And I Select Action Type as "Publish As PDF"
And I modify docStatus as "For Approval" AND poi as "For Review" AND I uncheck option "Start Workflow on PDF Publish"
And I click on "Create" button to create system task
Then "Auto_PublishAsPDF_SystemTask" System Action should created sucessfully AND Available on System Actions List
Examples:
|DC_Center|Project_Template|Cloned_Project|
|UK|AutomationTestProject_Template|Cloned_AutomationTestProject|
#|US|Automatic_Test_US_Template|Cloned_Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_Template|Cloned_Automatic_Test_AUS_WS|

@P2T6
Scenario Outline: Create-Edit Workflow and Trigger and Verify Details
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I navigate on workflow tab
And I have clicked cloned project
And I click on "Create Workflow Definition" link
Then "Create Workflow Definition" popup should open
And I put Workflow Name AND Description as "Automation_Test_Workflow" AND Workflow Days
And I click on "Create" button for WorkflowDefinition
And I should Re-directed to "Visual Workflow Designer"
And I Design Workflow with UserTask And SystemTasks
And I Saved Designed Workflow Definition
When I Right click on designed Workflow Definiton Name
Then "Edit Workflow Detail" popup should open
And I Edit Workflow Definition Name as "Automation_Edited_Test_Workflow"
And I click on "Create Trigger" link
Then "Configure Trigger" popup should open
And I Create Workflow Trigger as "Automation_Test_Workflow_Trigger"
And I Edit Created Workflow Trigger as "Automation_Edited_Test_Workflow_Trigger"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in my "Cloned_Project" Project
When I have published multiple Document(s) in folder "Automation_WF_Folder" successfully
Then All Documents Should Available in Document Listing having Status as "For Acknowledgement"
When I Search Cloned_TestFile having Status as "For Acknowledgement" AND I click "For Acknowledgement"
And I perform "For Acknowledgement" Action successfully
And I Validate Cloned_TestFile file version as "Private"
And Cloned_TestFile Workflow Status should be "COMPLETED" AND Workflow Stage as "--"
And I have download AND validate multiple files "BatchPrintTestDataFile" successfully in Local
Examples:
|DC_Center|Cloned_Project|
|UK|Cloned_AutomationTestProject|
#|US|Cloned_Automatic_Test_US_WS|
#|AUS|Cloned_Automatic_Test_AUS_WS|

@P2T6
Scenario Outline: Publish Document Revision as PDF on System Task
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I navigate on workflow tab
And I have clicked cloned project
When I click again on System Action as "Auto_PublishAsPDF_SystemTask"
Then popup "Edit Action Configuration" should open
And I edit Name as "Auto_PublishAsPDF_SystemTask_Edited"
Then "Auto_PublishAsPDF_SystemTask" action should Edited as "Auto_PublishAsPDF_SystemTask_Edited" sucessfully for workflow
And I click on "Create Trigger" link
Then "Configure Trigger" popup should open
And I Create Workflow Trigger as "PublishPDF_Workflow_Trigger"
Given I am on "Files" tab
And I have focus on "AutomationUploadFiles" folder in my "Cloned_Project" Project
And I click on Add Files button
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have selected file "AutoTestArchitectureDesign.jpg" from Local
And I have entered all mandatory attributes AND i have attached secondary file as "AutoArchitectureCorrectDesign.png"
And I click on Upload button
And I have validated upto file version "6" AND workflowStatus as "FAILED" successfully
Examples:
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T6
Scenario Outline: Distribute Documents on System Task
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I navigate on workflow tab
And I have clicked cloned project
And I click on "Create Trigger" link
Then "Configure Trigger" popup should open
And I Create Workflow Trigger as "DistributeDocument_Workflow_Trigger"
Given I am on "Files" tab
And I have focus on "PublicFolder" folder in my "Cloned_Project" Project
And I click on Add Files button
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have selected file "AutoTestArchitectureDesign.jpg" from Local
And I have entered all mandatory attributes AND i have attached secondary file as "AutoArchitectureCorrectDesign.png"
And I click on Upload button
And I have validated Audit trial for Users Distribution group Role AND Org as "Auto RFI" "Auto_PA Builder" "AutomationTestDistributionGroup" "SystemAction_AutoTestRole" AND "ABC Test Comp"
Examples:
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T6
Scenario Outline: Deactivate Test Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I Search Cloned_Project AND Deactivate
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|