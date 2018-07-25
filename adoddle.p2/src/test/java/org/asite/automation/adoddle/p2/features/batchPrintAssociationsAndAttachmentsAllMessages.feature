Feature: BatchPrint Associations and Attachments of All Form messages

@P2T5
Scenario Outline: BatchPrint form via right click to print all associations - attachments of all messages
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I click on Project as <Project_Name> AND I click on Folder "Customer Forms" AND I click on "Form Status Check" Form type
And I search BatchPrint TestData form for Print
Then Form should displayed on Forms tab listing page
When I right click on Form and select "Print All" on Context click menu options
Then New tab should opened with title as "Print"
And I have enter file name as "BatchPrintTestFile" AND I click on "Save"
And I have validated "BatchPrintTestFile" file size successfully with local file
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|