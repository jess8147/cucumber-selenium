Feature: Workflow functionality

@P1T5
Scenario Outline: Check Workflow - Action Assignment - P1
Given Project DC <DC_Center> is available
Given I am already logged in
When I switch to <User> user
Then I should re-directed to Dashboard of user "TC Bloggs"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in <Project> Project
And I click on Add Files button
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have selected multiple Files from Local
And I have entered all mandatory attributes for multiple files
And I click Upload Button
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
|DC_Center|Project|User|
|UK|AutomationTestProject|TC Bloggs|
#|US|Automatic_Test_US_WS|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|TC Bloggs|

@P1T5
Scenario Outline: Check Workflow - All Org Users P1
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <User1> user
Then I should re-directed to Dashboard of user "RFI Builder"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in <Project> Project
Then Workflow file(s) should have "For Comment" action for "Org users"
When i click on First File having  My action as "For Comment" for "Org users"
Then New Tab should be open with HTML viewer AND file should open in viewer
When I perform "For Comment" action in any workflow file(s)
Then Action "For Comment" should be completed successfully
When I switch to <User2> user
Then I should re-directed to Dashboard of user "PA Builder"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in <Project> Project
Then Workflow file(s) should have "For Comment" action for "Org users"
When i click on First File having  My action as "For Comment" for "Org users"
Then New Tab should be open with HTML viewer AND file should open in viewer
When I perform "For Comment" action in any workflow file(s)
Then Action "For Comment" should be completed successfully
Then Workflow Status should be "RUNNING" AND Workflow Stage as "For Status Change" for "Org users"
Examples:
|DC_Center|Project|User1|User2|
|UK|AutomationTestProject|RFI Builder|PA Builder|
#|US|Automatic_Test_US_WS|RFI Builder|PA Builder|
#|AUS|Automatic_Test_AUS_WS|RFI Builder|PA Builder|

@P1T5
Scenario Outline: Check Workflow - User P1
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in <Project> Project
Then Workflow file(s) should have "For Status Change" action for "individual user"
When i click on First File having  My action as "For Status Change" for "individual user"
Then "Status Change" popup should open
When I perform "For Status Change" to "For Approval" action in workflow file(s) for "individual user"
Then Action "For Status Change" should be completed successfully
When I switch to <User> user
Then I should re-directed to Dashboard of user "RS Bloggs"
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in <Project> Project
Then Workflow file(s) should have "For Information" action for "individual user"
When i click on First File having  My action as "For Information" for "individual user"
Then New Tab should be open with HTML viewer AND file should open in viewer
When I close current window and switch to previous window
Then Action "For Information" should be completed successfully
Then Workflow Status should be "COMPLETED" AND Workflow Stage as "--" for "individual user"
Examples:
|DC_Center|Project|User|
|UK|AutomationTestProject|RS Bloggs|
#|US|Automatic_Test_US_WS|RS Bloggs|
#|AUS|Automatic_Test_AUS_WS|RS Bloggs|

@P1T5
Scenario Outline: Check Workflow - System Task P1
Given Project DC <DC_Center> is available
And I am already logged in
When I select Third User as <Secondary User>
Then I should re-directed to Dashboard of Third User <Secondary User>
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in <Project> Project
Then Workflow file(s) should have "For Status Change" action for "system task"
When i click on First File having  My action as "For Status Change" for "system task"
Then "Status Change" popup should open
When I perform "For Status Change" to "For Review" action in workflow file(s) for "system task"
Then Action "For Status Change" should be completed successfully
Then Workflow Status should be "COMPLETED" AND Workflow Stage as "--" for "system task"
Examples:
|DC_Center|Project|Secondary User|
|UK|AutomationTestProject|Automation UK|
#|US|Automatic_Test_US_WS|Automation US|
#|AUS|Automatic_Test_AUS_WS|Automation AUS|

@P1T5
Scenario Outline: Check Workflow - Failed Status P1
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on "Automation_WF_Folder" folder in <Project> Project
Then Workflow file(s) should have "For Status Change" action for "failed status"
When i click on First File having  My action as "For Status Change" for "failed status"
Then "Status Change" popup should open
When I perform "For Status Change" to "Work Done" action in workflow file(s) for "failed status"
Then Workflow Status should be "FAILED" AND Workflow Stage as "--" for "failed status"
And I Deactivated all Workflow  files 
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|