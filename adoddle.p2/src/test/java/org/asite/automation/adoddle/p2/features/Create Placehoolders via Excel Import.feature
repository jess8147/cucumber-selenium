Feature: Create Placehoolders via Excel Import

@P2T4
Scenario Outline: Create Placeholders via Excel Import
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on <Project_Name> projects "PlaceHolders" folder
And I right click on "Placeholder via Excelsheet" folder And drag mouse to "New" And click on "Placeholder" into context menu options
Then "Create Placeholder" popup should open for publishing Placeholders
When I click on "placeholder template" for download template
Then template should be downloaded into Local Directory
When I enter all mendatory fields into downloaded template
And I import "placeholder Excel template" sheet
Then "Import Placeholders" popup should be open for Upload XslPlaceholder
When I click on "Add Placeholders" button link
Then "Confirm" popup should be opened for XslPlaceholder verifications
When I click on "OK" button of Confirm popup
Then XslPlaceholder template All fields should be set into "Create Placeholder" popup list And Status default value should be set as "Prepublished"
When I select Distribution User for Distribute "Placeholders" And "For Publishing" action With Current Date
And I click on "Assign Placeholder task" button for for Creating Placeholder
Then All Xsl template Placeholder Created Successfully and should be listed on Files listing page And All Fields should be displayed in Files Listing page
When I login with secondary user
Given I am on "Files" tab
And I have focus on <Project_Name> projects "PlaceHolders" folder
And I click on "Placeholder via Excelsheet" placeholder Excel Import folder
Then "For Publishing" action should be displayed on created all PlaceHolders
When I upload files into created placeholders using "For Publishing" action And I check only "status" dropdown value should be blanked
Then File should be uploaded successfully and placeholder All attributes value should be set to uploaded file And "For Publishing" action should be completed And File Version should be same "1"
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|