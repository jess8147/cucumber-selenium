Feature: Form All Actions Completion

@P2T7
Scenario Outline: Perform Form All Incomplete Actions
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in <Project_Name> project app folder <App_Folder> and apptype <App_Type> with "FormIncompleteActionsGroup" Distribution Group
Then Form should created successfully
And Form all Actions should displayed in Distribution "History" And Action status set as "Incomplete"
When I switch to <A> user
Given I am on "Project Forms" tab
Then Created Form should displayed with all Incomplete Actions
When I perform all Incomplete Actions one by one
Then Forms all Actions should Completed And on "History" tab Action status set as "Complete"
Examples: 
|DC_Center|Project_Name|App_Folder|App_Type|A|
|UK|AutomationTestProject|Form Distibution Group|Check Form Distribution|Automation UK|
#|US|Automatic_Test_US_WS|Form Distibution Group|Check Form Distribution|Automation US|
#|AUS|Automatic_Test_AUS_WS|Form Distibution Group|Check Form Distribution|Automation AUS|