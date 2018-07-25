Feature: Single Model

@P2T6
Scenario Outline: Single Model
Given Project DC <DC_Center> is available
And I am already logged in
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
When I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I upload same IFC File second issue for content change
And I enter all mandatory fields for IFC upload And set Revision as "2"
And I click on Upload button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
Then IFC File should be uploaded successfully And Revison should be set as "2" And Issue number also set as "2"
And File type icon displayed as "ifc"
When I click on Models tab
And I search uploaded IFC file as Single Model in Models tab
Then IFC file as "Single Model" should be displayed in Models tab
When I click on IFC file
Then IFC file should be viewed in new window
And uploaded both version should be displayed in left panel
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|