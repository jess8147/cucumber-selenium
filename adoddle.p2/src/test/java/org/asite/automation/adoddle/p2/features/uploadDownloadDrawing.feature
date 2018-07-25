Feature: Upload - Download Drawing

@P2T5
Scenario Outline: Upload Drawing On Location - Site
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Field" tab
And Field enabled project <Project> is available
Given Site "Automation_TestSite" is created in the project <Project>
When I context click on existing Site "Automation_TestSite"
And I click on "Add Drawing" option to add drawing
Then "Upload" popup should open
When I select drawing file and upload it
Then PlanView Icon should be enabled
When I click on PlanView Icon for Site
Then Viewer should display the drawing file
Given Location "Auto_TestLocation" is created in the site "Automation_TestSite"
When I context click on existing Location "Auto_TestLocation"
And I click on "Add Drawing" option to add drawing
Then "Upload" popup should open
When I select drawing file and upload it
Then PlanView Icon should be enabled
When I click on PlanView Icon for Site
Then Viewer should display the drawing file
#When I download the uploaded drawing file
#Then Downloaded file size should match with uploaded file
Given I remove created site from project
Then Site should be removed from project
Examples: 
|DC_Center|Project|
|UK|Automation_FieldEnabled_UK_Project|
#|US|Automation_FieldEnabled_US_Project|
#|AUS|Automation_FieldEnabled_AUS_Project|