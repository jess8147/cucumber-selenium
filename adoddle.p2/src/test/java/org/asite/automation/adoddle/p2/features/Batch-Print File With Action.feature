Feature: Batch-Print File With Action

@P2T7
Scenario Outline: Batch-Print Files With Action from Right click
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I select "AutomationUploadFiles" folder AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I upload more than one Files
And I fill all mendatory fields And assign "For Information" action
And I click on Upload button
Then files should uploaded successfully And "For Information" action should assigned to uploaded documents
When I select all files and perform right click and select context menu option "Print File"
Then "Print Document" popup should open
And selected all files should displayed on popup
When I select all checkboxes of Print Document popup
And I click on "Print" link of popup
Then New tab should opened And "Print" popup should open for BatchPrint files
When I closed opened new window
Then "For Information" action should completed for selected all BatchPrint documents
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T7
Scenario Outline: Batch-Print Files With Action from More Options
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I select "AutomationUploadFiles" folder AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I upload more than one Files
And I fill all mendatory fields And assign "For Information" action
And I click on Upload button
Then files should uploaded successfully And "For Information" action should assigned to uploaded documents
When I select all files AND click on "More  Options" and select "Print File" from Options popup list
Then "Print Document" popup should open
And selected all files should displayed on popup
When I select all checkboxes of Print Document popup
And I click on "Print" link of popup
Then New tab should opened And "Print" popup should open for BatchPrint files
When I closed opened new window
Then "For Information" action should completed for selected all BatchPrint documents
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|