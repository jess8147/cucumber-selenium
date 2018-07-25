Feature: Create Basic Form - Classic

@P1T3
Scenario Outline: Create Basic form with multiple Attachments - Associations
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click on "Public_No_Controller" link of All Apps
And I click on "Create New Form" of any form of Apps
Then New "Create PNCF" popup should open
When I have selected Form User "Automation Secondary" into Distribution With action "For Information"
And I have filled all mandatory fields for "PNCF Form"
And I have attached atleast one document for form and click "Start Upload"
And I have associated atleast one document for form
And I have associated atleast one form for form
When I Click on "Save" button on create form window
Then Form should be successfully created 
And Form should be available in "Project Forms" listing
And Attached documents should be uploaded And available in form
And Form Association should be successfully done with selected documents
And Distribution action and due date should be valid for Distributed User of workspace <Project>
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|