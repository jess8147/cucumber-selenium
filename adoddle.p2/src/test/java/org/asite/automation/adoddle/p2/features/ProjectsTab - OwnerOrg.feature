Feature: Projects Tab - Owner Org

@P2T2
Scenario Outline: "Owner Org" Hyperlinks on the Project list view
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
And I check "Owner Org" of selected project
When I click on Hyperlink of "Owner Org" of Projects tab
Then I should redirect to "Suppliers" tab
And I verify "Owner Org" name on "Companies" block area
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|