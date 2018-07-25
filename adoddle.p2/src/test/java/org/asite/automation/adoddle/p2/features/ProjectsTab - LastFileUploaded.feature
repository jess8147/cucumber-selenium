Feature: Projects Tab - Last File Uploaded

@P2T2
Scenario Outline: "Last File Uploaded" Hyperlinks on the Project list view
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Projects" tab
Then I get last uploaded file date into "Last File Uploaded" Hyperlink
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I select "Date" into Create filter dropdown list
Then "Date" filter dropdown set into Files tab
When I set "Last File Uploaded" Hyperlink date into "Date" filter
And I get total Last Uploaded Files Count
Given I am on "Projects" tab 
And I click on "Last File Uploaded" hyperlink of Projects tab
Then current project <Project_Name> should be set into Search "Project filter"
And I should redirect to "Files" tab
And "Date" filter dropdown set into Files tab
And I verify "Date Filter" selected date with "Last File Uploaded" Hyperlink date
And I verify all Uploaded Files date with "Last File Uploaded" Hyperlink date And Total Files Count
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|