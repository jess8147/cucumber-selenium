Feature: Dashboard LastLogin Files

@P2T5
Scenario Outline: New Files Published Dashboard "LAST LOGIN", "TODAY", AND "PAST WEEK"
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "set" <Project_Name> project as Default
Given I am on "Dashboard" tab
Given I am on "Files" tab
And I upload files for <User_ID> user with "For Information" action in <Project_Name> project "AutomationUploadFiles" folder
And I logOut and re login using <User_ID> User
And I get "LAST LOGIN", "TODAY", AND "PAST WEEK" total highcharts count
Given I am on "Files" tab
And I click on Project Name <Project_Name> AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select more then one Files from Local to upload
And I click on Bulk Apply checkbox
When I fill all mendatory fields for "LAST LOGIN"
And I click on Upload button
Then Files should be uploaded successfully in DocListing page
When I logOut and re login using <User_ID> User
Then I should redirect to "Dashboard" tab
And total "LAST LOGIN", "TODAY", AND "PAST WEEK" count should increased
When I click on "LAST LOGIN" highcharts
Then I should redirect to "Files" tab
And Total Files listing and files count same as "LAST LOGIN" highcharts of Dashboard Count
Given I am on "Dashboard" tab
When I click on "TODAY" highcharts
Then I should redirect to "Files" tab
And Total Files listing and files count same as "TODAY" highcharts of Dashboard Count
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
And Total Files listing and files count same as "PAST WEEK" highcharts of Dashboard Count
When I logOut and re login using <User_ID> User
Then I should redirect to "Dashboard" tab
And total "LAST LOGIN" count should set as 0
When I "reset" <Project_Name> project from Default
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|Automation_Files_LastLogin_UK_Project|auto.lastlogin_user1@atest.com|
#|US|Automation_Files_LastLogin_US_Project|auto.lastlogin_user1@atest.com|
#|AUS|Automation_Files_LastLogin_AUS_Project|auto.lastlogin_user1@atest.com|