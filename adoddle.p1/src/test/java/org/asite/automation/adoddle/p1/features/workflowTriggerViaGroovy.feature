Feature: Workflow Trigger via Groovy script

@P1T4
Scenario Outline: Workflow with Groovy script
Given Project DC <DC_Center> is available
Given I am already logged in
Given I am on "Files" tab
And I have focus on "Automation_WF_Groovy" folder
And I click on Add Files button
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have uploaded multiple Files from Local
And I have entered all mandatory files attributes for multiple files
And I click Upload Button
Then All Workflow Files should be uploaded successfully with workflow status as "RUNNING" AND stage as "For Acknowledgement"
When I switch to <User> user
Then I should re-directed to Dashboard of user "TC Bloggs"
Given I am on "Files" tab
When I Search "WF_TestFile" having MyActions as "For Acknowledgement" AND I click "For Acknowledgement" action
Then Popup "For Acknowledgement" should open
And I perform "For Acknowledgement"  action on file sucessfully 
And I Validated all "WF_TestFile" files attributes updated with groovy script
Examples: 
|DC_Center|Project|User|
|UK|AutomationTestProject|TC Bloggs|
#|US|Automatic_Test_US_WS|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|TC Bloggs|

@exclude
Scenario Outline: CleanUp Test Data
Given Project DC <DC_Center> is available
Given I am already logged in
Given I am on "Files" tab
And I have search files AND deactivated
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|