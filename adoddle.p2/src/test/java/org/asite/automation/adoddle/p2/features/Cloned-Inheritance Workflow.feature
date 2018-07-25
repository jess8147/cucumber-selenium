Feature: Cloned Inheritance Workflow

@P2T2
Scenario Outline: Check Workflow - Action Assignment
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I have cloned existing project <Project> for <DC_Center>
When I logged in as <User1>
Then I should re-directed to Dashboard of user "TC Bloggs"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in my "Cloned_Project" Project
And I click on Add Files button
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have selected multiple Files from Local
And I have entered all mandatory attributes for multiple files
When I click on "Upload" button
Then All Workflow Files should be uploaded successfully AND I should redirected on Document listing
And Workflow status should get changed to "RUNNING" state AND Workflow Stage should not equal to  "--"
And I click on All Workflow files having status as "RUNNING"
And All  Workflow files should have status as "RUNNING" AND correct start time AND End time on "Workflow status" popup
When I Right click on All files in Document Listing having workflow status as "Running"
And drag mouse on History AND click on Distribution
Then New Tab should open with HTML viewer AND file should be open/view in viewer
When I click on first record of history
Then Records should get expanded
And Records should have correct "Due Date" and "Actions"
Examples:
|DC_Center|Project|Cloned_Project|User1|
|UK|AutomationTestProject_Template|Cloned_AutomationTestProject|TC Bloggs|
#|US|Automatic_Test_US_Template|Cloned_Automatic_Test_US_WS|TC Bloggs|
#|AUS|Automatic_Test_AUS_Template|Cloned_Automatic_Test_AUS_WS|TC Bloggs|

@P2T2
Scenario Outline: Check Workflow - All Org Users
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I logged in as <User2>
Then I should re-directed to Dashboard of user "RFI Builder"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in my "Cloned_Project" Project
Then Workflow file(s) should have "For Action" action for "Org users"
When i click on First File having  My action as "For Action" for "Org users"
Then "For Action" popup should open
When I perform For Action action in any workflow file(s)
Then Action "For Action" should be completed sucessfully
When I logged in as <User3>
Then I should re-directed to Dashboard of user "PA Builder"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in my "Cloned_Project" Project
Then Workflow file(s) should have "For Action" action for "Org users"
When i click on First File having  My action as "For Action" for "Org users"
Then "For Action" popup should open
When I perform For Action action in any workflow file(s)
Then Action "For Action" should be completed sucessfully
Then Workflow Status should be "RUNNING" AND Workflow Stage as "For Status Change" for "Org users"
Examples:
|DC_Center|Cloned_Project|User2|User3|
|UK|Cloned_AutomationTestProject|RFI Builder|PA Builder|
#|US|Cloned_Automatic_Test_US_WS|RFI Builder|PA Builder|
#|AUS|Cloned_Automatic_Test_AUS_WS|RFI Builder|PA Builder|

@P2T2
Scenario Outline: Check Workflow - User
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in my "Cloned_Project" Project
Then Workflow file(s) should have "For Status Change" action for "individual user"
When i click on First File having  My action as "For Status Change" for "individual user"
Then "Status Change" popup should open
When I perform "For Status Change" to "For Approval" action in workflow file(s) for "individual user"
Then Action "For Status Change" should be completed sucessfully
And Workflow file should receive "For Information" action
When I have context click on File AND I have completed My action as "For Information" successfully
Then Workflow Status should be "COMPLETED" AND Workflow Stage as "--" for "individual user"
Examples:
|DC_Center|Cloned_Project|
|UK|Cloned_AutomationTestProject|
#|US|Cloned_Automatic_Test_US_WS|
#|AUS|Cloned_Automatic_Test_AUS_WS|

@P2T2
Scenario Outline: Check Workflow - System Task
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I logged in as <User4>
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in my "Cloned_Project" Project
Then Workflow file(s) should have "For Status Change" action for "system task"
When i click on First File having  My action as "For Status Change" for "system task"
Then "Status Change" popup should open
When I perform "For Status Change" to "For Review" action in workflow file(s) for "system task"
Then Action "For Status Change" should be completed sucessfully
Then Workflow Status should be "COMPLETED" AND Workflow Stage as "--" for "system task"
Examples:
|DC_Center|Project|User4|
|UK|Cloned_AutomationTestProject|Auto RFI|
#|US|Automatic_Test_US_WS|Auto RFI|
#|AUS|Automatic_Test_AUS_WS|Auto RFI|

@P2T2
Scenario Outline: Check Workflow - Failed Status
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in my "Cloned_Project" Project
Then Workflow file(s) should have "For Status Change" action for "failed status"
When i click on First File having  My action as "For Status Change" for "failed status"
Then "Status Change" popup should open
When I perform "For Status Change" to "Work Done" action in workflow file(s) for "failed status"
Then Workflow Status should be "FAILED" AND Workflow Stage as "--" for "failed status"
Examples:
|DC_Center|Cloned_Project|User4|
|UK|Cloned_AutomationTestProject|Automation Primary|
#|US|Cloned_Automatic_Test_US_WS|Automation Primary|
#|AUS|Cloned_Automatic_Test_AUS_WS|Automation Primary|

@P2T2
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