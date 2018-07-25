Feature: Working Calendar with Role and Org - Distribute Form File

@P2T5
Scenario Outline: Perform TestData Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "Project"
Then "Edit Project" popup should open
When I click on "Working Calendar" link of popup
And I perform Working Calendar Holidays Cleanup
Examples: 
|DC_Center|User_ID|Project_Name|
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Role_Org_UK_WS|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Role_Org_US_WS|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Role_Org_AUS_WS|

# With Additional Holiday #

@P2T5
Scenario Outline: File Distribution With Working Calendar With Additional Holiday Calendar - via Role & Org Distribution
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "Project"
Then "Edit Project" popup should open
When I click on "Working Calendar" link of popup
And I set "saturday", "sunday" and one extra day "tuesday" days as Holiday
And I add Additional Holiday in Working Calendar And click on Save Button
When I search project as <Project_Name>
And I right click on <Project_Name> project And click on "Working Calendar"
Then "Working Calendar" popup should open
And Working Days should be displayed and Holidays should not be displayed And Additional Holiday should be displayed in Holiday List
Given I am on "Files" tab
And I get active tab
And I select <Project_Name> project AND "Automation_Working_Cal_Folder" Folder And I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
And I enter all mandatory fields to upload
And I click on "Distribute Files" button of upload popup
And I distribute <Distribute_Role_Org> AND I select "For Information" action with adding 15 days in Role AND "For Action" action with 20 days in Org
And I distribute <Doc_Distribution_Group> Distribution Group
And I click on Upload button
Then file should uploaded Successfully on "Files" tab And I verify File Distribution History with Working Calendar
Examples: 
|DC_Center|User_ID|Project_Name|Distribute_Role_Org|Doc_Distribution_Group|
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Role_Org_UK_WS|Working_Cal_Role,Automation Testing|Doc_Working_Cal_Group|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Role_Org_US_WS|Working_Cal_Role,Automation Testing|Doc_Working_Cal_Group|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Role_Org_AUS_WS|Working_Cal_Role,Automation Testing|Doc_Working_Cal_Group|

@P2T5
Scenario Outline: Form Distribution With Working Calendar With Additional Holiday Calendar - via Role & Org Distribution
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "Project"
Then "Edit Project" popup should open
When I click on "Working Calendar" link of popup
And I set "saturday", "sunday" and one extra day "tuesday" days as Holiday
And I add Additional Holiday in Working Calendar And click on Save Button
When I search project as <Project_Name>
And I right click on <Project_Name> project And click on "Working Calendar"
Then "Working Calendar" popup should open
And Working Days should be displayed and Holidays should not be displayed And Additional Holiday should be displayed in Holiday List
Given I am on "Project Forms" tab
And I get active tab
When I select <Project_Name> project and <App_Group> group and <App_Type> app
And I create new Form with distribute <Distribute_Role_Org> AND I select "For Information" action with adding 15 days in Role AND "For Action" action with 20 days in Org AND "Form_Working_Cal_Group" Distribution Group
Then Form should uploaded Successfully on "Forms" tab And I verify Form Distribution History with Working Calendar
When I select <Project_Name> project and <App_Group> group and <Dist_App_Type> app
And I create new Form with distribute <Distribute_Role_Org> AND I select "For Information" action with adding 15 days in Role AND "For Action" action with 20 days in Org AND "Form_Working_Cal_Group" Distribution Group into "Dropdown"
Then Form should created Successfully on "Forms" tab And I verify Form Distribution History with All Working Days
Examples: 
|DC_Center|User_ID|Project_Name|App_Group|App_Type|Dist_App_Type|Distribute_Role_Org|
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Role_Org_UK_WS|Automation_Working_Cal_Group|Automation_Working_Cal_App|Automation_Working_Cal_Distribute_App|Working_Cal_Role,Automation Testing|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Role_Org_US_WS|Automation_Working_Cal_Group|Automation_Working_Cal_App|Automation_Working_Cal_Distribute_App|Working_Cal_Role,Automation Testing|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Role_Org_AUS_WS|Automation_Working_Cal_Group|Automation_Working_Cal_App|Automation_Working_Cal_Distribute_App|Working_Cal_Role,Automation Testing|

@P2T5
Scenario Outline: Perform TestData Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "Project"
Then "Edit Project" popup should open
When I click on "Working Calendar" link of popup
And I perform Working Calendar Holidays Cleanup
Examples: 
|DC_Center|User_ID|Project_Name|
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Role_Org_UK_WS|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Role_Org_US_WS|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Role_Org_AUS_WS|