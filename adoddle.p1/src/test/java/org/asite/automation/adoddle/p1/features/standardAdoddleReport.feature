Feature: Create Standard Report Adoddle

@P1T3
Scenario Outline: Verify Standard report scheduled from Adoddle
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I get loggedIn User and Org
Given I am on "Files" tab
And I set "Revisions" filter With both Current Set and Superseded
And I get total files count of <Project1> project and <Project2> project
And I am on "Reports" tab
When I click on "Create a New Report" icon
Then Report Types options should open in dropdown
When I click on Create New "Standard Report"
Then "New Standard Report" should open in "Manage Reports" panel
When I enter the report name "Standard Report" AND selected folder for report
And click on "Next" button of Manage Report popup
Then "Categories" tab should open
When I double click on "Files"
Then "Files" should be selected
When click on "Next" button of Manage Report popup
Then "Sorts" tab should open
When I double click on "File Name" into Sorts tab
Then "File Name" should be selected
When click on "Next" button of Manage Report popup
Then "Filters" tab should open
When click on "Next" button of Manage Report popup
Then "Layout" tab should open
When I double click on Multiple "Files" columns of Layout field
And click on "Finish" button of Manage Report popup
And Click on "Execute Selected Report" button
Then Report should be executed
And "Standard Report" should saved And downloaded in XLSX format
And I verify downloaded "Standard Report" Contect
And I click on "Edit & Schedule" LH Button
Then I should display the "Edit & Schedule" listing page
When I search created "Standard" Report And I click on "Edit Report" link
Then "Edit And Schedule" page should be opened
When I select "Selected Project" Radio button into Report Criteria And I select <Project1> and <Project2> into dropdown
When I entered "Standard Report" And I select Workspace of <Project2>
And I select "Monthly" into "Frequency" dropdown list And select "First Date of month" radio Button
And I select "00" for Hour AND "01" for Minute into "Time" dropdown list
And I click on "Folder" link of "Delivery Schedule" Area
And I select project as <Project2> + "(Document, Workflow & Forms Manager)" into select Workspace dorpdown list
Then it should display the folder tree structure
When I select the folder named "Reports" AND click on "Select Folder" Button
Then it should display "Enter Document Details" area
When I select POI AND Status 
And I click on Next button
Then it should display the "Distribute:" area
When I select organization AND select any one user
And click on "Add to Distribution List" Button
And I select "For Information" in "Action Required" dropdown AND select "10" in "No. of Days to Complete Action" dropdown
And click on Distribute Button
Then it should add the user in "Report Recipients" list
When I click on "Save" Button
And "Adoddle" Standard report for "XLS" preview downloaded and verify total Files count of both Selected Projects
And I checked "Send now" CheckBox AND click on "Save & Close" Button of "Standard Report" page
Then It should generate the report And the report should get published in the selected target folder And I close opened window
Given I am on "Files" tab
And I select <Project2> project and select "Reports" folder for scheduling
Then generated report should be available in Reports Folder And "For Information" Action should be assigned to it
Examples: 
|DC_Center|User_ID|Project1|Project2|
|UK|auto.non_admin1@atest.com|Automation_Report_UK_WS1|Automation_Report_UK_WS2|
#|US|auto.non_admin1@atest.com|Automation_Report_US_WS1|Automation_Report_US_WS2|
#|AUS|auto.non_admin1@atest.com|Automation_Report_AUS_WS1|Automation_Report_AUS_WS2|

@P1T3
Scenario Outline: Deactivate Standard Scheduled Report
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I am on "Reports" tab
And I "Inactive" Report from "Edit & Schedule" page
Examples: 
|DC_Center|User_ID|
|UK|auto.non_admin1@atest.com|
#|US|auto.non_admin1@atest.com|
#|AUS|auto.non_admin1@atest.com|