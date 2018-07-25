Feature: Edit ORI - Verify Rights
	
@P2T7
Scenario Outline: Verify Edit ORI Rights
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
Given I am on "Project Forms" tab
When I context click on "multiple" Edit ORI form
Then Edit Message option should be "disabled"
When I switch to <C> user
And I search created Edit ORI Form
And I context click on "single" Edit ORI form
Then Edit Message option should be "disabled"
When I click on form to view the form
Then "Edit ORI" button should be disabled
When I switch to <A> user
And I search "Closed" form of type Edit ORI
And I context click on "single" Edit ORI form
Then Edit Message option should be "disabled"
Examples: 
|DC_Center|Project|A|B|C|
|UK|AutomationTestProject|Automation UKP2|Automation UK|PA Bloggs|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|PA Bloggs|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|PA Bloggs|