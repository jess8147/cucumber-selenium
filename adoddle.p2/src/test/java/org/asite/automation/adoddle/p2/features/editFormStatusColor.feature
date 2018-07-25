Feature: Edit Form Status Color

@P2T4
Scenario Outline: Edit Form Status Colors
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
And I have project <Project> available
When I right click on Project And I click on Edit And I click on "Form Statuses" option into context menu List
Then "Form Statuses" popup should open
When I search form status "Open"
And I set status color as "blue" and save the form status
Then Form status should be saved
Given I am on "Field" tab
And Field enabled project <Project> is available
When I search defects with status "Open"
Then All defects should have status color as "blue"
When I click on "New" option in LH panel
And I search "Defects" form  on "Create Form" window
Then "Defects" Form should get filtered on Create Form window
When I click on filtered "Defects" form
And I enter values in mandatory fields to create <Site> form
And I click on "Send" button to create Defect
And I switch to default content window
Given I reload the page
And I am on "Field" tab
And Field enabled project <Project> is available
When I click on existing Site <Site>
Then Created defects should be available in listing
And Defect color should be "blue"
Given I am on "Projects" tab
And I have project <Project> available
When I right click on Project And I click on Edit And I click on "Form Statuses" option into context menu List
Then "Form Statuses" popup should open
When I search form status "Open"
And I set status color as "red" and save the form status
Then Form status should be saved
Given I am on "Field" tab
And Field enabled project <Project> is available
When I search defects with status "Open"
Then All defects should have status color as "red"
Examples: 
|DC_Center|Project|Site|
|UK|Automation_FieldEnabled_UK_Project|Automation_UK_TestSite1|
#|US|Automation_FieldEnabled_US_Project|Automation_US_TestSite1|
#|AUS|Automation_FieldEnabled_AUS_Project|Automation_AUS_TestSite1|