Feature: Projects Tab - Files Hyperlink

@P2T2
Scenario Outline: "Files" Hyperlinks on the Project list view
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I select "Revisions" into "Create filter" dropdown list
Then "Revision" filter dropdown set into file listing page
When I checked "Current Set" and "Superseded" into Revision filter dropdown
And I get total Active Files count
Given I am on "Projects" tab
Then I get total Files count on "Files" hyperlink
When I click on "Files" hyperlink
Then current project <Project_Name> should be set into Search "Project filter"
And I should redirect to "Files" tab
And I verify "Current Set" and "Superseded" checked into "Revision" filter dropdown
And I verify total Active files count
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|