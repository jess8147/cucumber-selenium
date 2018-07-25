Feature: Create API Based Form
	
@P1T2
Scenario Outline: Create Query Form
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I switch to <Tender User> user
And I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "1.Tender Query Form" folder
And I have focus on form template "1.Query Form"
When I click on "Create Form" button on Project Forms page
Then New "Create Form" popup should open
Given I have opened "Create Form" popup
And I have filled all mandatory query form fields
And I have attached multiple documents for query form
When I Click on "Save" button on create form window
Then Form should be successfully created 
And User should be able to search Query Form on listing
Examples: 
|DC_Center|Project|Tender User|
||||
|UK|Automation_NDFA_Workspace|RFI Bloggs|