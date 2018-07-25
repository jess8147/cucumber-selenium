Feature: Dashboard Model Summary Widget

@P2T3
Scenario Outline: Dashboard Model Summary Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Dashboard" tab
And I get total count of document available in Model Summary via disciplines wise
# Fadrated Model
When I click on Models tab
Then I should redirect to "Models" tab
When I Click on "Add Models" button From LH Panel for "ListView" listing
Then "Add Model" popup should open
When I filled all mendatory fields And click on "Save" button
Then Model should be created And should be available in Models "ListView" listing
When I right click on "Model Name" Model And click on "Upload"
Then "Upload Model File" popup should open
When I select Wrokset And select "IFC File" from local And click on "Upload" button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
Given I am on "Dashboard" tab
Then "Architecture" type Model total count should increased And remain types Model count should not incresed
# Single Model
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select IFC File to upload
And I enter all mandatory fields for IFC upload And set Revision as "1"
And I click on Upload button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
Then IFC File should be uploaded successfully And Revison should be set as "1" And Issue number also set as "1"
And File type icon displayed as "ifc"
Given I am on "Dashboard" tab
Then "Other" type Model total count should increased And remain types Model count should not incresed
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|