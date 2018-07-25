Feature: Models ACL Checking

@P2T7
Scenario Outline: ACL Checking
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I Click on "Add Models" button From LH Panel
Then "Add Model" popup should open
When I filled all mendatory fields And I create 3 different Worksets on Model And click on "Save" button
Then Model should be created And should be available in Model listing
When I right click on Model And click on "Upload"
Then "Upload Model File" popup should open
When I select Wrokset for "primary" User And select "IFC File" from local And click on "Upload" button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
And I right click on Model And click on "Upload"
Then "Upload Model File" popup should open
When I select Wrokset for "secondary" User And select "IFC File" from local And click on "Upload" button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
And I right click on Model And click on "Upload"
Then "Upload Model File" popup should open
When I select Wrokset for "third" User And select "IFC File" from local And click on "Upload" button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
Given I am on "Files" tab
And I click on <Project_Name> Project AND I click on "ModelACLCheckFolder" Folder
And I give "first" folder for "primary" user AND "second" folder for "secondary" user AND "third" folder for "third" user total "No Access" rights
When I click on Models tab
And I click on created Model
Then New tab should be opened AND Model should be View in new opened tab And I take screenshot of "Model Creater" User ACL Model
When I close View Model opened tab
And I login using "primary" User for ACL check
And I click on Models tab
And I click on created Model
Then New tab should be opened AND Model should be View in new opened tab And I take screenshot of "primary" User ACL Model
When I close View Model opened tab
And I login using "secondary" User for ACL check
And I click on Models tab
And I click on created Model
Then New tab should be opened AND Model should be View in new opened tab And I take screenshot of "secondary" User ACL Model
When I close View Model opened tab
And I login using "third" User for ACL check
And I click on Models tab
And I click on created Model
Then New tab should be opened AND Model should be View in new opened tab And I take screenshot of "third" User ACL Model
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|