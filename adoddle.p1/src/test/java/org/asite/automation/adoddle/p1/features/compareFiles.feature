Feature: File Comparison

@P1T4
Scenario Outline: Compare two files
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab 
And I have atleast one document in document listing
When I select two files
And right click on selected files
Then Context menu popup should open
When I click on Compare Files for file comparison
Then New tab should open with both compared files in viewer
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|