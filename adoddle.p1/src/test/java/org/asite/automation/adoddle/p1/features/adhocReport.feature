Feature: Adhoc Report Creation

@P1T2
Scenario Outline: Classic Adhoc Report creation
Given Project DC <DC_Center> is available
Given I have sent an initiation email
Given I am already logged in
And I get loggedIn User and Org
Given I am on "Files" tab
And check the Incomplete Action counts
And I goto "Classic View" from Adoddle
And I click on workspace of <Project_Name>
And I select "Create new report" option into REPORTS dropdown list
Then "New Express Report" should open in "Manage Reports" panel
When I entered the "Adhoc" Report name AND select folder for "Classic" report
And click on "Next" button of Manage Report popup
Then "Categories" tab should open
When I double click on "Workspace Team Directory"
Then "Workspace Team Directory" should be selected
When click on "Next" button of Manage Report popup
Then "Sorts" tab should open
When I double click on "JobTitle" into Sorts tab
Then "JobTitle" should be selected
When click on "Next" button of Manage Report popup
Then "Filters" tab should open
When click on "Next" button of Manage Report popup
Then "Layout" tab should open
When I double click on Multiple columns of Layout tab
And Click on "Execute Selected Report" button
Then Adhoc Report should be executed
And "Adhoc" Report should be saved And Report downloaded in XLS format And I Close the opened Window
And I verify downloaded Adhoc Report Contect
When I click on "My Home" link AND I click on Reporting tab
Then I should redirect on "Reports" page
And I verify generated report on "Reports" page in "Classic"
When I goto project as <Project_Name> AND click on "Reports" folder
When I click on "Adoddle View" into Settings dropdown list
Then I should redirect on Adoddle View on "Dashboard" tab
Given I am on "Reports" tab
Then I verify generated report on "Reports" page in "Adoddle"
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|