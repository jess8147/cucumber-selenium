Feature: Edit ORI - Save Draft - Update
	
@P2T7
Scenario Outline: Save Draft - Edit using saved draft - Update
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "Edit ORI Message" folder
When I create form of type Edit ORI
Then Form should be created successfully
When I switch to <B> user
And I search created Edit ORI Form
And I context click on "single" Edit ORI form
Then Edit Message option should be "enabled"
When User edits form of type EDIT ORI
And User saves EDIT ORI form as Draft
When User again tries to edit form of type EDIT ORI
Then "Edit ORI" popup should be displayed
When User opts "Edit using saved draft" option and clicks it
And User modifies form details and clicks on "Update" button
Then Form Draft should get updated to Form
When I context click on "single" Edit ORI form
Then Edit Message option should be "enabled"
When User edits form of type EDIT ORI
And User saves EDIT ORI form as Draft
And User again tries to edit form of type EDIT ORI
And User opts "Edit using saved draft" option and clicks it
And User clicks "Discard Draft" button for form of type EDIT ORI
Then Saved ORI Draft should get discarded
Examples: 
|DC_Center|Project|A|B|
|UK|AutomationTestProject|Automation UKP2|Automation UK|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|