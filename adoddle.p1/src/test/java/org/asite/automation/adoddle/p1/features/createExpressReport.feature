Feature: Create Express Report

@P1T5
Scenario Outline: Create Report And Save as Excel
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Reports" tab
When I click on "Create a New Report" icon
Then Report Types options should open in dropdown
When I click on Create New "Express Report"
Then "New Express Report" should open in "Manage Reports" panel
When I enter the report name "Express Report Name" AND selected folder for report
And click on "Next" button of Manage Report popup
Then "Categories" tab should open
When I double click on "Comment Report"
Then "Comment Report" should be selected
When click on "Next" button of Manage Report popup
Then "Sorts" tab should open
When I double click on "Comment Title" into Sorts tab
Then "Comment Title" should be selected
When click on "Next" button of Manage Report popup
Then "Filters" tab should open
When click on "Next" button of Manage Report popup
Then "Layout" tab should open
When I double click on Multiple columns
And Click on "Execute Selected Report" button
Then Report should be executed
And "Express Report" should saved And downloaded in XLSX format
And I verify downloaded "Express Report" Contect
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|