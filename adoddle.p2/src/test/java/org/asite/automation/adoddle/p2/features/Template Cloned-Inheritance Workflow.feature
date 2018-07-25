Feature: Template Cloned-Inheritance Workflow

@P1T1
Scenario Outline: Create Template And Cloned-Inherited Workspace
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I Switch to "Classic View" from Adoddle
And I click on tab "Workspaces"
Then <Project> should Available in Workspace List
And I click on <Project> project
Then I should redirect on <Project> home page
When I select "Edit Workspace" from Admin Dropdown
Then "Edit workspace" page should open
And I click on link "Save workspace as template"
Then I should redirect on "Create workspace template" Page
And I Edit Workspace Template name as "AutomationTestProject_Template"
And I click on "Save All" Link
Then "AutomationTestProject_Template" should created successfully AND Available in workspace template list
Examples: 
|DC_Center|Project|
|UK|TemplateClonedInheritanceAutoTest_UK_Workpace|
#|US|TemplateClonedInheritanceAutoTest_US_Workpace|
#|AUS|TemplateClonedInheritanceAutoTest_AUS_Workpace|

@P1T1
Scenario Outline: Create Cloned And Inherited Workspace
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I Switch to "Classic View" from Adoddle
And I click on tab "Workspaces"
And I have focus on "Workflow Templates" tab AND I have validated template "AutomationTestProject_Template" in listing
When I click clonned image link of "AutomationTestProject_Template"
Then I should redirect on "Create Workspace" page
And I have enter all mandatory attributes of <Cloned_Project> scenario "Like_DataCenter"
And I Break Inheritance for <Cloned_Project>
And I click on "Save All" Link
Then "Cloned_AutomationTestProject" should created sucessfully AND Available in all workspace list
And I have focus on "Workspace Templates" tab
When I click clonned image link of "AutomationTestProject_Template"
Then I should redirect on "Create Workspace" page
And I have enter all mandatory attributes of <InheritedCloned_Project> scenario "Like_DataCenter"
And I click on "Save All" Link
Then "InheritedCloned_AutomationTestProject" should created sucessfully AND Available in all workspace list
When I click on "My Home" link AND I switch to "Adoddle View" from Settings dropdown list
Then I should redirect on Adoddle View "Dashboard"
Given I navigate on workflow tab
And I have focus on "Workspace_Template"
And I click on "Configure System Action" link
Then "New Task Configuration" popup should open
And I put System task Name as "Auto_StatusChange_SystemTask"
And I Select Action Type as "Status Change"
And I Select Change Status from "For Approval" To "For Information" 
And I put Reason for Status Change Notes as "Via Workflow"
And I click on "Create" button to create system task
Then "Auto_StatusChange_SystemTask" System Action should created sucessfully AND Available on System Actions List
Given I navigate on workflow tab
And I have focus on "Workspace_Template"
And I click on "Create Workflow Definition" link
Then "Create Workflow Definition" popup should open
And I put Workflow Name as "Automation_Test_Workflow" AND Workflow Days
And I click on "Create" button for WorkflowDefinition
And I should Re-directed to "Visual Workflow Designer" page
And I Design Workflow with a UserTask AND SystemTasks
And I Saved Designed Workflow Definition
And I click on "Create Trigger" link
Then "Configure Trigger" popup should open
And I Create Workflow Trigger name as "AutomationWorkflow_Trigger" AND i put all mandatory attributes
Then "AutomationWorkflow_Trigger" created successfully AND available in trigger Listing
And I have focus on "Cloned_Project" AND I Validated "Workflow_TestData" should not be available
And I have focus on "InheritedCloned_Project" AND I Validated "Workflow_TestData" should available
Examples: 
|DC_Center|Cloned_Project|InheritedCloned_Project|
|UK|Cloned_AutoTestProject_UK|InheritedCloned_AutoTestProject_UK|
#|US|Cloned_AutoTestProject_US|InheritedCloned_AutoTestProject_US|
#|AUS|Cloned_AutoTestProject_AUS|InheritedCloned_AutoTestProject_AUS|

@P1T1
Scenario Outline:Cloned Vs Inherited project verification
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I navigate on workflow tab
And I have focus on "Workspace_Template"
And I have updated "Clear Action System Task" as "ClearActionEdited_Systemtask" sucessfully for "Workspace_Template"
And I have Context click on "Automation_WF_P1_Scenario" definition AND I updated as "Automation_WF_P1_EditedScenario"
And I Validated updated "Automation_WF_P1_EditedScenario" workflow Definition AND I click on it
Then I should Re-directed to "Visual Workflow Designer" page
And I updated "Automation_WF_P1_EditedScenario" workflow design succesfully for "Workspace_Template"
And I have updated "Automation_WF_P1_Scenario_Trigger" trigger as "Automation_WF_P1_Scenario_EditedTrigger" successfully for "Workspace_Template"
And I have focus on "InheritedCloned_Project" AND I Validated "UpdatedWorkflow_TestData" should available AND It should not Editable
Given I am on "Files" tab
And I have focus on folder "AutomationUploadFiles" in project <InheritedCloned_Project>
And I have published "3" file in folder "AutomationUploadFiles"
And I have focus on folder "AutomationUploadFiles" in project <InheritedCloned_Project>
Then File "InheritedCloned_File's" should published successfully with workflowStatus as "RUNNING" AND workflowStage as "For Acknowledgement,For Status Change"
And I Search file "InheritedCloned_File" AND I click on file title
Then file "InheritedCloned_File" should open in HTML Viewer successfully
And I navigate LH panel AND I click on History
And I Validated "InheritedCloned_File" distributed actions as "For Acknowledgement" AND "For Status Change"
Given I am on "Files" tab
And I have focus on folder "Automation_WF_Folder" in project <Cloned_Project>
And I have published "3" file in folder "Automation_WF_Folder"
And I have focus on folder "Automation_WF_Folder" in project <Cloned_Project>
Then File "Cloned_TestFile's" should published successfully with workflowStatus as "RUNNING" AND workflowStage as "For Action,For Status Change"
And I Search file "Cloned_File" AND I click on file title
Then file "Cloned_File" should open in HTML Viewer successfully
And I navigate LH panel AND I click on History
And I Validated "Cloned_File" distributed actions as "For Action" AND "For Status Change"
When I logged in as <User1>
Given I am on "Files" tab
And I have focus on folder "AutomationUploadFiles" in project <InheritedCloned_Project>
And I have Search "InheritedCloned_File's" AND I Validated workflowStatus as "RUNNING" AND workflowStage as "For Acknowledgement,For Status Change"
And I am already logged in with multi-Project user
Given I navigate on workflow tab
And I have focus on cloned project <Cloned_Project>
And I have updated "Clear Action System Task" as "ClearActionEdited_ClonedSystask" sucessfully for "Cloned_Project"
And I have Context click on "Automation_WF_P1_Scenario" definition AND I updated as "Automation_WF_P1_EditedScenario" 
And I Validated updated "Automation_WF_P1_EditedScenario" workflow Definition AND I click on it
Then I should Re-directed to "Visual Workflow Designer" page
And I updated "Automation_WF_P1_EditedScenario" workflow design succesfully for "Cloned_Project"
And I have updated "Automation_WF_P1_Scenario_Trigger" trigger as "Automation_WF_P1_Scenario_EditedTrigger " successfully for "Cloned_Project"
Given I am on "Files" tab
And I have focus on folder "PublicFolder" in project <Cloned_Project>
And I have published "3" file in folder "PublicFolder"
And I have focus on folder "PublicFolder" in project <Cloned_Project>
Then File "Cloned_TestFile's" should published successfully with workflowStatus as "RUNNING" AND workflowStage as "For Information,For Distribution"
And I Search file "Cloned_File" AND I click on file title
Then file "Cloned_File" should open in HTML Viewer successfully
And I navigate LH panel AND I click on History
And I Validated "Cloned_File" distributed actions as "For Information" AND "For Distribution"
Given I am on "Files" tab
And I have focus on folder "Automation_WF_Folder" in project <Cloned_Project>
And I have published "2" file in folder "Automation_WF_Folder"
And I have focus on folder "Automation_WF_Folder" in project <Cloned_Project>
Then File "Cloned_TestFile's" should published successfully with workflowStatus as "Blank" AND workflowStage as "--"
When I logged in as <User2>
Given I am on "Files" tab
And I have focus on folder "PublicFolder" in project <Cloned_Project>
And I have Search "Cloned_File" AND I Validated workflowStatus as "RUNNING" AND workflowStage as "For Information,For Distribution"
Examples: 
|DC_Center|Cloned_Project|InheritedCloned_Project|User1|User2|
|UK|Cloned_AutoTestProject_UK|InheritedCloned_AutoTestProject_UK|RFI Builder|TC Bloggs|
#|US|Cloned_AutoTestProject_US|InheritedCloned_AutoTestProject_US|RFI Builder|TC Bloggs|
#|AUS|Cloned_AutoTestProject_AUS|InheritedCloned_AutoTestProject_AUS|RFI Builder|TC Bloggs|

@P1T1
Scenario Outline: Clean_Up Operation
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I Context Click "Cloned_Project" AND Break Inheritance and I deactivated project
Given I am on "Projects" tab
And I Context Click "InheritedCloned_Project" AND Break Inheritance and I deactivated project
And I Switch to "Classic View" from Adoddle
And I click on tab "Workspaces"
And I have search AND deactivate template successfully
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|