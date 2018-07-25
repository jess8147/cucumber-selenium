Feature: Direct Link of the Form - Access to Adoddle User - No Access to Adoddle User - Access to classic User

@P2T7
Scenario Outline: Direct Link of the Form - hit URL with Login
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I click on Project as <Project_Name> AND I click on Folder "Customer Forms" AND I click on "Form Status Check" Form type
And I create New Form with "For Information" action to <A_AccessUserID> Adoddle Form AccessUser
Then Form should created successfully on Forms tab listing page
When I click on created Form
Then New tab should opened And Form should viewed
When I get Direct Link of viewed Form
# Existing Session User
And I opened new tab I hit Direct Link on URL
Then Form should viewed in Existing session
# Access Adoddle User
When I logOut and re-login using <A_AccessUserID> Adoddle Form "AccessUser"
And I opened new tab I hit Direct Link on URL
##Then Form should viewed and "For Information" action should completed And "Complete" Remarks should listed in History tab
Then Form should viewed and "For Information" action should completed for <A_AccessUserName> user And "Complete" Remarks should listed in History tab
# NoAccess Classic User
When I logOut and re-login using <C_NoAccessUserID> Classic Form "NoAccessUser"
And I opened new tab I hit Direct Link on URL
Then "Unauthorised Access!" page should displayed and Form not getting Viewed
Examples: 
|DC_Center|Project_Name|A_AccessUserID|A_AccessUserName|C_NoAccessUserID|
|UK|AutomationTestProject|auto.testuser2@atest.com|Automation TestUser2|auto.classic1@atest.com|
#|US|Automatic_Test_US_WS|auto.testuser2@atest.com|Automation TestUser2|auto.classic1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.testuser2@atest.com|Automation TestUser2|auto.classic1@atest.com|

@P2T7
Scenario Outline: Direct Link of the Form - hit URL Before Login
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I click on Project as <Project_Name> AND I click on Folder "Customer Forms" AND I click on "Form Status Check" Form type
And I create New Form with "For Information" action to <A_AccessUserID> Adoddle Form AccessUser and <C_AccessUserID> Classic Form AccessUser
Then Form should created successfully on Forms tab listing page
When I click on created Form
Then New tab should opened And Form should viewed
When I get Direct Link of viewed Form
# Existing Session User
And I opened new tab I hit Direct Link on URL
Then Form should viewed in Existing session
And I logOut and hit Direct Link on URL
Then login page should redirect and "You have attempted to access a page that requires a login. Please contact the link sender if you do not have a login to Asite." validation message should displayed
# NoAccess Adoddle User
When I login using <A_NoAccessUserID> Adoddle Form "NoAccessUser"
Then It should displayed blank page and Form not getting Viewed And getting Message like "The contents of this message are restricted for View. This will be visible once the Form is closed. If you have any queries please contact your Workspace Administrator."
# Access Adoddle User
When I open new browser and hit Direct Link on URL
Then login page should redirect and "You have attempted to access a page that requires a login. Please contact the link sender if you do not have a login to Asite." validation message should displayed
When I login using <A_AccessUserID> Adoddle Form "AccessUser"
##Then Form should viewed and "For Information" action should completed And "Complete" Remarks should listed in History tab
Then Form should viewed and "For Information" action should completed for <A_AccessUserName> user And "Complete" Remarks should listed in History tab
# Access Classic User
When I open new browser and hit Direct Link on URL
Then login page should redirect and "You have attempted to access a page that requires a login. Please contact the link sender if you do not have a login to Asite." validation message should displayed
When I login using <C_AccessUserID> Classic Form "AccessUser"
Then Form should viewed and "For Information" action should completed And Action Status "Complete" should listed in Audit Trail
Examples: 
|DC_Center|Project_Name|A_NoAccessUserID|A_AccessUserID|A_AccessUserName|C_AccessUserID|
|UK|AutomationTestProject|pa_builder@auto.com|auto.testuser2@atest.com|Automation TestUser2|auto.classic1@atest.com|
#|US|Automatic_Test_US_WS|pa_builder@auto.com|auto.testuser2@atest.com|Automation TestUser2|auto.classic1@atest.com|
#|AUS|Automatic_Test_AUS_WS|pa_builder@auto.com|auto.testuser2@atest.com|Automation TestUser2|auto.classic1@atest.com|