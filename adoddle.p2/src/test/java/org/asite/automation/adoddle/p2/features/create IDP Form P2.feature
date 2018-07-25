Feature: Create IDP P2 Form

@P2T3
Scenario Outline: Create IDP Form Template
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
And I have cloned existing project <Project Template> for <Geography>
And I break inheritance of the cloned project
Given I am on "Project Forms" tab
And I focus on cloned project
And I have focus on "1.0 Information Delivery Plan" folder
And I click on "1.0 Information Delivery Plan" form link
When I create IDP form by EPM user
And I edit project details in IDP form by EPM user	
And I edit stage details in IDP form by EPM user
And I edit stage deliverables details in IDP form by EPM user
And I edit deliverables details in IDP form by EPM user
When I logout from adoddle
And I login with "SIM" user
And I share subdeliverables by SIM user
And I verify expected subdeliverables vs actual subdeliverables
And I verify variuos filters picklist on IDP form 
Examples: 
|DC_Center|Project|Geography|Project Template|
|UK|IDP Project|United Kingdom (EU01)|IDP_Automation_Template|

@P2T3
Scenario Outline: Close IDP  Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Then User should be able to close project
Examples: 
|DC_Center|
|UK|