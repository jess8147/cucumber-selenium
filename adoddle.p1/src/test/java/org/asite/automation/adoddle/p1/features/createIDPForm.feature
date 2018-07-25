Feature: Create IDP Form

@exclude
Scenario Outline: Download latest IDP xsn from SVN
Given Project DC <DC_Center> is available
And I logged in to svn with <svnUser> and <svnPassword>
And I navigate to HTML apps download url
When I download IDP files from svn
Then IDP files should get downloaded successfully
And I am already logged in with multi-Project user
And I have uploaded IDP files to idp template with Admin user
Examples:
|DC_Center|svnUser|svnPassword|
|UK|jasminprajapati|Asite@987|


@P1T7
Scenario Outline: Create IDP Form Template
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
And I have cloned existing project <Project Template> with grography <Geography>
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
Then Stage status should be "Amber" for Deliverable and "Amber" for subdeliverable1 and "Amber" for subdeliverable2
When I edit stage deliverables date to "current" by EPM user
Then Stage status should be "Amber" for Deliverable and "Amber" for subdeliverable1 and "Amber" for subdeliverable2
When I logout from adoddle
And I login with "SIM" user
And I share subdeliverables by SIM user
And I login with "EPM" user
When I edit status for "subdeliverable1" to "Published" by EPM user
Then Stage status should be "Green" for Deliverable and "Green" for subdeliverable1 and "Amber" for subdeliverable2
When I edit status for "subdeliverable2" to "Published" by EPM user
Then Stage status should be "Green" for Deliverable and "Green" for subdeliverable1 and "Green" for subdeliverable2
When I download COBieIR from IDP form
Then File should get downloaded successfully
And File should contain uploaded Deliverables
Examples: 
|DC_Center|Project|Geography|Project Template|
|UK|IDP Project|United Kingdom (EU01)|IDP_Automation_Template|

@P1T7
Scenario Outline: Close IDP  Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Then User should be able to close project
Examples: 
|DC_Center|
|UK|