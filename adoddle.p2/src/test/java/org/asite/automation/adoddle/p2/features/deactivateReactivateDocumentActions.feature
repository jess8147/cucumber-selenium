Feature: Deactivate Reactivate Document Actions

@P2T4
Scenario Outline: Deactivate Document Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I create automation folder in project <Project>
And I open folder edit dialog to provide user access
And I provided "Publish" access to <Primary User>
And I provided "Admin" access to <Secondary User>
And I provided "Publish & Link" access to <Third User>
And I provided "Publish & Link" access to <Forth User>
And I saved updated folder
When I publish private document and distribute to <Primary User> and <Secondary User> and <Third User> and <Forth User>
And I switch to <Secondary User> user
And I search created private document
And I "deactivate" actions of <Primary User> and <Secondary User> and <Third User> and <Forth User> for revision "1"
And I search created private document
Then Document actions count will be "Reduced" and document will be "Visible" for <Secondary User>
When I switch to <Primary User> user
And I search created private document
Then Document actions count will be "Reduced" and document will be "Visible" for <Primary User>
When I switch to <Third User> user
And I search created private document
Then Document actions count will be "Reduced" and document will be "Visible" for <Third User>
When I switch to <Forth User> user
And I search created private document
Then Document actions count will be "Reduced" and document will be "Invisible" for <Forth User>
When I switch to <Secondary User> user
And I search created private document
And I "reactivate" actions of <Primary User> and <Secondary User> and <Third User> and <Forth User> for revision "1"
And I search created private document
Then Document actions count will be "Increased" and document will be "Visible" for <Secondary User>
When I switch to <Primary User> user
And I search created private document
Then Document actions count will be "Increased" and document will be "Visible" for <Primary User>
When I switch to <Third User> user
And I search created private document
Then Document actions count will be "Increased" and document will be "Visible" for <Third User>
When I switch to <Forth User> user
And I search created private document
Then Document actions count will be "Increased" and document will be "Visible" for <Forth User>
Examples: 
|DC_Center|Project|Primary User|Secondary User|Third User|Forth User|
|UK|AutomationTestProject|Automation UKP2|Automation UK|RFI Builder|RFI Bloggs|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|RFI Builder|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|RFI Builder|RFI Bloggs|


@P2T4
Scenario Outline: Reactivate Document Actions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
When I upload revision of private document and distribute to <Primary User> and <Secondary User> and <Third User> and <Forth User>
And I switch to <Secondary User> user
And I search created private document
And I "deactivate" actions of <Primary User> and <Secondary User> and <Third User> and <Forth User> for revision "2"
When I switch to <Forth User> user
And I search created private document
Then Document actions count will be "Reduced" and "previous" Revision should be displayed
When I switch to <Secondary User> user
And I search created private document
And I "reactivate" actions of <Primary User> and <Secondary User> and <Third User> and <Forth User> for revision "2"
When I switch to <Forth User> user
And I search created private document
Then Document actions count will be "Increased" and "current" Revision should be displayed
When I switch to <Secondary User> user
Given I deactivate folder in project <Project>
Examples: 
|DC_Center|Project|Primary User|Secondary User|Third User|Forth User|
|UK|AutomationTestProject|Automation UKP2|Automation UK|RFI Builder|RFI Bloggs|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|RFI Builder|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|RFI Builder|RFI Bloggs|