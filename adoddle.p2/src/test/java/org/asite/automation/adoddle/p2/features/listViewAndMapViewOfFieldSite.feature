Feature: List View And Map View - Site

@P2T3
Scenario Outline: List View And Map View on Fields Tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Field" tab
And Field enabled project <Project> is available
Given Site <Site> is available in the project <Project>
When I click on existing Site <Site>
And I click on PlanView Icon for Site
Then Viewer should display the drawing file
When I click on existing Site <Site>
And I click on ListView Icon for Site
Then Defect Listing page should be displayed
Given Location <Location> is available in the Site <Site>
When I click on existing Site <Site>
And I click on existing Location <Location>
And I click on PlanView Icon for Site
Then Viewer should display the drawing file
When I click on existing Site <Site>
And I click on existing Location <Location>
And I click on ListView Icon for Site
Then Defect Listing page should be displayed
Examples: 
|DC_Center|Project|Site|Location|
|UK|Automation_FieldEnabled_UK_Project|Automation_UK_TestSite1|Automation_UK_TestLocation1|
#|US|Automation_FieldEnabled_US_Project|Automation_US_TestSite1|Automation_US_TestLocation1|
#|AUS|Automation_FieldEnabled_AUS_Project|Automation_AUS_TestSite1|Automation_AUS_TestLocation1|