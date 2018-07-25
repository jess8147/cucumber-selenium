Feature: Batch Print Files

@P1T5
Scenario Outline: BatchPrint from More Options
Given Project DC <DC_Center> is available
Given I am already logged in 
And I am on Files tab
And I have selected multiple files on document listing
When I click on More Options
And I click on Print File icon
Then "Print Document" popup should open 
And All Selected files should be avialable on Print Document popup
When I select Incl. Markups AND Incl. Changemarks AND Fit Inside Banners 
And click on "Print" link
Then New Window should open with title as "Print"
And I have enter file name as "BatchPrintAutoTestDataFile" AND I click on "Save"
And I have validated "BatchPrintAutoTestDataFile" file size successfully with local file
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P1T5
Scenario Outline: BatchPrint from Right Click
Given Project DC <DC_Center> is available
And I am already logged in 
And I am on Files tab
And I have selected multiple files on document listing
When I right click on selected documents
Then Context menu popup should open
When I click on "Print File" on Context menu popup
Then "Print Document" popup should open 
And All Selected files should be avialable on Print Document popup
When I select Incl. Markups AND Incl. Changemarks AND Fit Inside Banners 
And click on "Print" link
Then New Window should open with title as "Print"
And I have enter file name as "BatchPrintAutoTestDataFile" AND I click on "Save"
And I have validated "BatchPrintAutoTestDataFile" file size successfully with local file
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|