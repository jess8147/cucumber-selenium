Feature: Distribute Form File Working Calendar - without Additional Holiday

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
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Without_Add_Holiday_UK_WS|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Without_Add_Holiday_US_WS|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Without_Add_Holiday_AUS_WS|

# Without Additional Holiday #

@P2T5
Scenario Outline: File Distribution With Working Calendar Without Additional Holiday Calendar
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "Project"
Then "Edit Project" popup should open
When I click on "Working Calendar" link of popup
And I set "saturday", "sunday" days as Holiday And click on Save Button
Given I am on "Files" tab
And I get active tab
And I select <Project_Name> project AND "Automation_Working_Cal_Folder" Folder And I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
And I enter all mandatory fields to upload
And I click on "Distribute Files" button of upload popup
And I distribute <Doc_Distribution_Group> Distribution Group
And I click on Upload button
Then file should uploaded Successfully on "Files" tab And I verify File Distribution History with Working Calendar
Examples: 
|DC_Center|User_ID|Project_Name|Doc_Distribution_Group|
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Without_Add_Holiday_UK_WS|Doc_Working_Cal_Group|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Without_Add_Holiday_US_WS|Doc_Working_Cal_Group|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Without_Add_Holiday_AUS_WS|Doc_Working_Cal_Group|

@P2T5
Scenario Outline: Form Distribution With Working Calendar Without Additional Holiday Calendar
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I search project as <Project_Name>
And I right click on <Project_Name> And Drag mouse to "Edit" And Click on "Project"
Then "Edit Project" popup should open
When I click on "Working Calendar" link of popup
And I set "saturday", "sunday" days as Holiday And click on Save Button
Given I am on "Project Forms" tab
And I get active tab
When I create new form in <Project_Name> project app folder <App_Folder> and apptype <App_Type> with "Form_Working_Cal_Group" Distribution Group
Then Form should uploaded Successfully on "Forms" tab And I verify Form Distribution History with Working Calendar
Examples: 
|DC_Center|User_ID|Project_Name|App_Folder|App_Type|
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Without_Add_Holiday_UK_WS|Automation_Working_Cal_Group|Automation_Working_Cal_App|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Without_Add_Holiday_US_WS|Automation_Working_Cal_Group|Automation_Working_Cal_App|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Without_Add_Holiday_AUS_WS|Automation_Working_Cal_Group|Automation_Working_Cal_App|

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
|UK|auto.wcu_uk@atest.com|Auto_Working_Cal_Without_Add_Holiday_UK_WS|
#|US|auto.wcu_us@atest.com|Auto_Working_Cal_Without_Add_Holiday_US_WS|
#|AUS|auto.wcu_aus@atest.com|Auto_Working_Cal_Without_Add_Holiday_AUS_WS|