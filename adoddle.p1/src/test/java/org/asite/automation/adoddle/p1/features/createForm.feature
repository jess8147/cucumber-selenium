Feature: Create Form
	
@P1T1
Scenario Outline: Create Form with Multiple Attachments - Associations
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Project Forms tab
And I have focus on <Project> project
And I have focus on "Communications" folder
And I have focus on form template "Request For Information"
When I click on "Create Form" button on Project Forms page
Then New "Create Form" popup should open
Given I have opened "Create Form" popup
And I have selected Form Users into "To"
And I input title "AutomationTestForm" and description for RFI form
And I have attached atleast one document for form
And I have associated atleast one document for form
When I Click on "Save" button on create form window
Then Form should be successfully created 
And Form should be available in "Project Forms" listing
And Attached documents should be uploaded And available in form
And Form Association should be successfully done with selected documents
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|