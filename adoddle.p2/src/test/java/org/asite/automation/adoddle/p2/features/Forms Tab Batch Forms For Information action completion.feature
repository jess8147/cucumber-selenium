Feature: Forms Tab Batch Forms For Information action completion

@P2T5
Scenario Outline: Batch Forms For Information action completion - via Right Click
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
And I cleared formTitle and formList
When I select <Project_Name> project and <App_Group1> group and <App_Type1> app
And I create new form with No Distribute Action
When I select <Project_Name> project and <App_Group1> group and <App_Type2> app
And I create new form and Distribute to User <A> with <Action> action
When I select <Project_Name> project and <App_Group2> group and <App_Type3> app
And I create new form and Distribute to User <A> with <Action> action
When I click on <Project_Name> project
Then created two Forms should displayed with "For Information" action And one Form should displayed with No action
When I select Batch Forms And select "Tasks" and "For Information" context menu option
#Then "Actions - For Information" popup should open
Then "Task - For Information" popup should open
And Forms that have "For Information" actions should displayed and other forms should not displayed in popup
When I click on "OK" link of popup
Then Forms that have "For Information" actions should completed
Examples: 
|DC_Center|Project_Name|A|Action|App_Group1|App_Group2|App_Type1|App_Type2|App_Type3|
|UK|AutomationTestProject|Automation UKP2|For Information|Customer Forms|Form Distibution Group|Form Status Check|Form Status Check2|Check Form Distribution|
#|US|Automatic_Test_US_WS|Automation USP2|For Information|Customer Forms|Form Distibution Group|Form Status Check|Form Status Check2|Check Form Distribution|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|For Information|Customer Forms|Form Distibution Group|Form Status Check|Form Status Check2|Check Form Distribution|

@P2T5
Scenario Outline: Batch Forms For Information action completion - via More Options
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
And I cleared formTitle and formList
When I select <Project_Name> project and <App_Group1> group and <App_Type1> app
And I create new form with No Distribute Action
When I select <Project_Name> project and <App_Group1> group and <App_Type2> app
And I create new form and Distribute to User <A> with <Action> action
When I select <Project_Name> project and <App_Group2> group and <App_Type3> app
And I create new form and Distribute to User <A> with <Action> action
When I click on <Project_Name> project
Then created two Forms should displayed with "For Information" action And one Form should displayed with No action
When I select Batch Forms And click on "More Options" and select "Tasks - For Information" Option
#Then "Actions - For Information" popup should open
Then "Task - For Information" popup should open
And Forms that have "For Information" actions should displayed and other forms should not displayed in popup
When I click on "OK" link of popup
Then Forms that have "For Information" actions should completed
Examples: 
|DC_Center|Project_Name|A|Action|App_Group1|App_Group2|App_Type1|App_Type2|App_Type3|
|UK|AutomationTestProject|Automation UKP2|For Information|Customer Forms|Form Distibution Group|Form Status Check|Form Status Check2|Check Form Distribution|
#|US|Automatic_Test_US_WS|Automation USP2|For Information|Customer Forms|Form Distibution Group|Form Status Check|Form Status Check2|Check Form Distribution|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|For Information|Customer Forms|Form Distibution Group|Form Status Check|Form Status Check2|Check Form Distribution|