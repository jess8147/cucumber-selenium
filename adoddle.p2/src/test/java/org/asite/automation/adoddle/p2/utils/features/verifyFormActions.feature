Feature: Verify Form Actions

@formActions
Scenario Outline: Verify Form Actions
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>

And I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "Communications" folder
And I have focus on form template "Request For Information"
When I click on "Create Form" button on Project Forms page
And I distribute form actions to user <User>
|For Information|
|Respond|
And I input title "AutomationActionsCheck" and description for RFI form
Given I click on "Send" button
And I switch to default content window

#And I am on "Project Forms" tab
#And I have focus on <Project> project
#And I have focus on "Communications" folder
#And I have focus on form template "Request For Information"
#When I click on "Create Form" button on Project Forms page
#And I distribute form actions to user <User>
#|For Information|
#|Respond|
#And I input title "AutomationActionsCheck" and description for RFI form
#Given I click on "Send" button
#And I switch to default content window
Examples: 
|DC_Center|Project|Username|Password|User|
|UK|AutomationTestProject|auto_ukp2@atest.com|Test@12345|Automation UK|

