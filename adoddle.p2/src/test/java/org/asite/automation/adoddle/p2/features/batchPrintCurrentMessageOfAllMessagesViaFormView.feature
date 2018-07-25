Feature: BatchPrint Current Message of PDF - All Form Responses

@P2T7
Scenario Outline: BatchPrint Form via view Form "Current Message to PDF"
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I click on Project as <Project_Name> AND I click on Folder "Customer Forms" AND I click on "Form Status Check" Form type
And I search BatchPrint TestData form for Print
Then Form should displayed on Forms tab listing page
When I click on searched Print Form
Then New tab should opened for View Form
# "ORI" Form
When I click on "Form Details" LH-panel tab And I select "ORI" formtype
And I click on "Current Message to PDF" Button of "Export" menu
Then New third tab should opened with title as "Print"
And I have enter file name as "PrintORIFormFile" AND I click on "Save"
And I have validated "PrintORIFormFile" file size successfully with local file
# "FWD" Form
When I click on "Form Details" LH-panel tab And I select "FWD" formtype
And I click on "Current Message to PDF" Button of "Export" menu
Then New third tab should opened with title as "Print"
And I have enter file name as "PrintFWDFormFile" AND I click on "Save"
And I have validated "PrintFWDFormFile" file size successfully with local file
# "RES_FWD" Form
When I click on "Form Details" LH-panel tab And I select "RES001" formtype
And I click on "Current Message to PDF" Button of "Export" menu
Then New third tab should opened with title as "Print"
And I have enter file name as "PrintRESFFormFile" AND I click on "Save"
And I have validated "PrintRESFFormFile" file size successfully with local file
# "RES_ORI" Form
When I click on "Form Details" LH-panel tab And I select "RES002" formtype
And I click on "Current Message to PDF" Button of "Export" menu
Then New third tab should opened with title as "Print"
And I have enter file name as "PrintRESOFormFile" AND I click on "Save"
And I have validated "PrintRESOFormFile" file size successfully with local file
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|