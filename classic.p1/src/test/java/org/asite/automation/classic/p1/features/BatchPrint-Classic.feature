Feature: BatchPrint Multiple Files

Background:
Given Script is Node specific

@exclude
Scenario Outline: BatchPrint from Classic with Thin Client Workspace
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
And I am on Workspace home page of workspace <Project>
And I have clicked on "All Workspace Documents"
And I have selected multiple files on document listing
When I click on Add to Basket icon
Then selected documents should be added to basket with message "Documents added to basket successfully"
When I click on ok button of confirmation popup
And click on "View Basket" Icon
Then Page with "My Selected Basket Items" header should open 
When I select "Print Documents" value from "Action Selected Documents in Basket" dropdown
Then "Print Documents" page should get opened
When I select Incl. Markups AND Incl. Changemarks AND Fit Inside Banners 
And I click on "Print" button on right side
Then New Window should open with title as "Print Setup" AND I have selected default printer as "pdfFactory Pro"
And I have redirected to new window with title as "Jobs" AND I have click on "Save as" button on top Panel
Then "Save As" Popup should open AND I have destination directory as "TestData"
And I have enter file name as "ClassicBatchPrintTestDataFile" AND I click on "Save"
Then file "ClassicBatchPrintTestDataFile" should saved successfully in Local
And I have validated "ClassicBatchPrintTestDataFile" file size with local file in <Project> successfully
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|


@P1T1
Scenario Outline: BatchPrint from Classic with Java Viewer Workspace
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
And I am on Workspace home page of workspace <Project>
And I have clicked on "All Workspace Documents"
And I have selected multiple files on document listing
When I click on Add to Basket icon
Then selected documents should be added to basket with message "Documents added to basket successfully"
When I click on ok button of confirmation popup
And click on "View Basket" Icon
Then Page with "My Selected Basket Items" header should open 
When I select "Print Documents" value from "Action Selected Documents in Basket" dropdown
Then "Print Documents" page should get opened
When I select Incl. Markups AND Incl. Changemarks AND Fit Inside Banners 
And I click on "Print" button on right side
Then New Window should open with title as "Print Setup" AND I have selected default printer as "pdfFactory Pro"
And I have redirected to new window with title as "Jobs" AND I have click on "Save as" button on top Panel
Then "Save As" Popup should open AND I have destination directory as "TestData"
And I have enter file name as "ClassicBatchPrintTestDataFile" AND I click on "Save"
Then file "ClassicBatchPrintTestDataFile" should saved successfully in Local
And I have validated "ClassicBatchPrintTestDataFile" file size with local file in <Project> successfully
Examples:
|DC_Center|Project|
|UK|Java_Viewer_UK_WS|
#|US|Java_Viewer_US_WS|
#|AUS|Java_Viewer_AUS_WS|