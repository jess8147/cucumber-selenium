Feature: Projects Tab - Project Filter

@P2T2
Scenario Outline: Selected Project in Project Filter
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I clear search "Project filter"
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I select searched project <Project_Name>
Then selected project <Project_Name> should be set into Search "Project filter"
Given I am on "Files" tab
Then selected project <Project_Name> should be displayed into Files Tab
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|