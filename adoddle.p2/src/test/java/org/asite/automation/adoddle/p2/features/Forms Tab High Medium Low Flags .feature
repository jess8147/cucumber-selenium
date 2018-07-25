Feature: Forms Tab High - Medium - Low flags

@P2T5
Scenario Outline: Set High \ Medium \ Low flags with Single apptype on Forms Tab
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
And I clear all flags for "Forms"
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select <Project_Name> project AND select <App_Folder> App folder AND select <App_Type> Apptype AND I get total forms title
And I select <Project_Name> project AND select <App_Folder> App folder AND select <App_Type> Apptype AND select more then one Forms for "High" flag
And I right click on selected Forms and select "Flag" AND I click on "High" widget
When I select <Project_Name> project AND select <App_Folder> App folder AND select <App_Type> Apptype AND select more then one Forms for "Medium" flag
And I right click on selected Forms and select "Flag" AND I click on "Medium" widget
When I select <Project_Name> project AND select <App_Folder> App folder AND select <App_Type> Apptype AND select more then one Forms for "Low" flag
And I right click on selected Forms and select "Flag" AND I click on "Low" widget
When I create "Flag" filter
And I set "High" flag into filter
Then only "High" flag Forms should displayed
When I set "Medium" flag into filter
Then only "Medium" flag Forms should displayed
When I set "Low" flag into filter
Then only "Low" flag Forms should displayed
When I set "High", "Medium" AND "Low" flag in Flag filter
Then all active flags Forms should displayed
When I select all Forms AND right click on selected Forms And drag mouse to "Flag" And I click on "Clear Flag"
Then all active flags Forms should set as "No Flag" Forms
Examples: 
|DC_Center|Project_Name|App_Folder|App_Type|
|UK|AutomationTestProject|Customer Forms|Form Status Check|
#|US|Automatic_Test_US_WS|Customer Forms|Form Status Check|
#|AUS|Automatic_Test_AUS_WS|Customer Forms|Form Status Check|

@P2T5
Scenario Outline: Set High \ Medium \ Low flags with Multiple apptypes on Forms Tab
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
And I clear all flags for "Forms"
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select <Project_Name> project
And I set more than one Forms "Flag" as "High", "Medium" and "Low" flags
When I select <Project_Name> project
And I create "Flag" filter
And I set "High" flag into filter
Then only "High" flag Forms should displayed
When I set "Medium" flag into filter
Then only "Medium" flag Forms should displayed
When I set "Low" flag into filter
Then only "Low" flag Forms should displayed
When I set "High", "Medium" AND "Low" flag in Flag filter
Then all active flags Forms should displayed
When I select all Forms AND right click on selected Forms And drag mouse to "Flag" And I click on "Clear Flag"
Then all active flags Forms should set as "No Flag" Forms
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|