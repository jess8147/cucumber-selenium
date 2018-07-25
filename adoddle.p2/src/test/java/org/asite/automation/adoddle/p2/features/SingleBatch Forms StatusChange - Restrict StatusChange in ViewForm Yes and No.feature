Feature: Single and Batch Forms Status Change - Restrict Status Change in ViewForm - Yes and No

@P2T7
Scenario Outline: Batch Forms Status Change with Restrict Status Change in View Form "Yes" & "No"
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <A> user
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select <Project_Name> project and <App_Group> group and <No_App_Type> app
And I create new form And Distribute to Users <B> with <Actions> action
When I select <Project_Name> project and <App_Group> group and <Yes_App_Type> app
And I create new form And Distribute to Users <B> with <Actions> action
And I select <Project_Name> project
Then Form should created Successfully with No action
When I switch to <B> user
Given I am on "Project Forms" tab
Then created all Forms should displayed with "Assign Status" action
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status Change" popup should open
And only <No_App_Type> app type form should displayed on popup
When I change "Batch Forms" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then only <No_App_Type> form status should changed and "Assign Status" action completed But <Yes_App_Type> form status and "Assign Status" action as it is
Examples: 
|DC_Center|Project_Name|App_Group|No_App_Type|Yes_App_Type|Actions|A|B|
|UK|AutomationTestProject|Customer Forms|Form Status Check|Form Status Check2|Assign Status|Automation Non_Admin1|Automation Non_Admin3|
#|US|Automatic_Test_US_WS|Customer Forms|Form Status Check|Form Status Check2|Assign Status|Automation Non_Admin1|Automation Non_Admin3|
#|AUS|Automatic_Test_AUS_WS|Customer Forms|Form Status Check|Form Status Check2|Assign Status|Automation Non_Admin1|Automation Non_Admin3|