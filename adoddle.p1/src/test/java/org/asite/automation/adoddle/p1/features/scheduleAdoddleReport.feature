Feature: Create Schedule Report Adoddle

Background:
Given Script is Node specific

@scrapped
Scenario Outline: Verify Legacy report scheduled from Adoddle
Given Project DC <DC_Center> is available
Given I am already logged in
And I get loggedIn User and Org
And I am on "Files" tab
And check the Incomplete Action counts
And I goto "Classic View" from Adoddle
And I click on Reporting tab in classic
Then I should redirect on "Reports" page
When I click on "Create Report" img icon of Reports page
Then "Search for available reports" page should be opened
When I select "Incomplete Actions" Report And I click on "Create Report" button
Then "Edit And Schedule" page should be opened
When I entered "Report Name" And I select Workspace of <Project_Name>
And I click on Save&Close Button
Then It should generate the report And the report should get published in the selected target folder And I close opened window
And I verify generated report on "Reports" page
When I click on "My Home" link AND I switch to "Adoddle View" from Settings dropdown list
Then I should redirect on Adoddle View on "Dashboard" tab
Given I am on "Reports" tab
And I click on "Edit & Schedule" LH Button
Then I should display the "Edit & Schedule" listing page
And "Adoddle" report for "PDF" Preview verify on "Listing Page"
When I search created Report And I click on "Edit Report" link
Then "Edit And Schedule" page should be opened
And "Adoddle" report for "PDF" Preview verify on "Edit Page"
When I entered "Report Name" And I select Workspace of <Project_Name>
And I select "Monthly" into "Frequency" dropdown list And select "First Date of month" radio Button
And I select "00" for Hour AND "01" for Minute into "Time" dropdown list
And I click on "Folder" link of "Delivery Schedule" Area
And I select project as <Project_Name> + "(Document, Workflow & Forms Manager)" into select Workspace dorpdown list
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
And I checked "Send now" CheckBox AND click on "Save & Close" Button of "Schedule Report" page
Then It should generate the report And the report should get published in the selected target folder And I close opened window
Given I am on "Files" tab
When I select <Project_Name> project and select "Reports" folder for scheduling
Then generated report should be available in Reports Folder And "For Information" Action should be assigned to it
And Incomplete Actions counts should be increased By 1
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@scrapped
Scenario Outline: Deactivate Adoddle Schedule Report
Given Project DC <DC_Center> is available
And I am already logged in
When I am on "Reports" tab
And I "Inactive" Report from "Edit & Schedule" page
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|