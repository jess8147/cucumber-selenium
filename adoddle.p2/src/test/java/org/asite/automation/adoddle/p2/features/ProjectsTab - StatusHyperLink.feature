Feature: Projects Tab - Status HyperLink

@P2T2
Scenario Outline: "Status" Hyperlinks on the Project list view
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
And I check Project "Status" value on "Status" hyperlink
When I click on "Status" hyperlink of Project list View
Then "Edit Project" popup should open
And I verify Status dropdown selected value with "Status" hyperlink value
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|