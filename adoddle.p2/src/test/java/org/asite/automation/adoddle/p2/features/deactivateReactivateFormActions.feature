Feature: Deactivate Reactivate Form  Actions

@P2T7
Scenario Outline: Deactivate Form Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
When I create "Private Form" and distribute to <Primary User> and <Secondary User> and <Third User> and <Forth User>
And I switch to <Secondary User> user
And I search created private form
And I "deactivate" actions of <Primary User> and <Secondary User> and <Third User> and <Forth User>
And I search created private form
Then Form actions count will be "Reduced" and form will be "Visible" for <Secondary User>
When I switch to <Primary User> user
And I search created private form
Then Form actions count will be "Reduced" and form will be "Visible" for <Primary User>
When I switch to <Third User> user
And I search created private form
Then Form actions count will be "Reduced" and form will be "Visible" for <Third User>
When I switch to <Forth User> user
And I search created private form
Then Form actions count will be "Reduced" and form will be "Invisible" for <Forth User>
When I switch to <Secondary User> user
And I search created private form
And I "reactivate" actions of <Primary User> and <Secondary User> and <Third User> and <Forth User>
And I search created private form
Then Form actions count will be "Increased" and form will be "Visible" for <Secondary User>
When I switch to <Primary User> user
And I search created private form
Then Form actions count will be "Increased" and form will be "Visible" for <Primary User>
When I switch to <Third User> user
And I search created private form
Then Form actions count will be "Increased" and form will be "Visible" for <Third User>
When I switch to <Forth User> user
And I search created private form
Then Form actions count will be "Increased" and form will be "Visible" for <Forth User>
Examples: 
|DC_Center|Project|Primary User|Secondary User|Third User|Forth User|
|UK|AutomationTestProject|Automation UKP2|Automation UK|RFI Builder|PA Bloggs|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|RFI Builder|PA Bloggs|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|RFI Builder|PA Bloggs|
