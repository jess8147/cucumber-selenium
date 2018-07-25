Feature: Manage Form Template Settings

@P2T2
Scenario Outline: Assign Form & Permissions And Create Form
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
When I create new project "TestProject"
And I have atleast one project available
When I right click on Project And I click on Edit And I click on "Apps" option into context menu List
Then "Assign Apps" popup should open
When I select form template and click on "Add to Project" button
Then "Manage App Settings" popup should open
When I search form template and right click
And I select "Mark as Active" option on context menu
Then Status should change to "Active"
When I click on "x" button
Then Popup window should close
Given I right click on Project And I click on Edit And I click on "User Access" option into context menu List
When I click on "Form Permissions" option on LH menu
Then "Form Permissions" popup should open
When I click on "Form Name" dropdown and search Form
When I select correct form and click on "Close" Link
Then "Form Permissions" should get filtered for selected form
When I select "Create" form role permission checkbox and click on Save for "Workspace - Administrator"
#And I click on "x" button
Given I reload the page
Then Popup window should close
Given I am on "Project Forms" tab
And I clik on above created project
When I click on "New" option in LH panel
And I search form  on "Create Form" window
Then Form should get filtered on Create Form window	
#Scenario : Edit App Settings to "Yes"
Given I am on "Projects" tab
And I have atleast one project available
When I search created Project
And I right click on Project And I click on Edit And I click on "App Settings" option into context menu List 
Then "Manage App Settings" popup should open
When I search form template and click "Edit" icon
Then "Edit App settings" popup should open
When I edit Form Name Form Group Code and Form Group Name
When I edit all Form Template Settings to "Yes" for <Project_Name>
And I download xsn file rename it and re-upload it
And I edit all Controller Settings to "Yes"
And I edit all Response Settings to "Yes"
And I edit all Distribution settings to "Yes"
And I edit all Edit and Forward settings to "Yes"
And I edit all Attachments settings to "Yes"
And I edit all Associations settings to "Yes"
And I edit Actions Required to "Yes" for all actions
And I edit all Form Statuses settings to "Yes"
And I edit all Additional Form Settings to "Yes"
# Re=checking Yes option
And I edit all Controller Settings to "Yes"
And I edit all Response Settings to "Yes"
And I edit all Distribution settings to "Yes"
And I edit all Edit and Forward settings to "Yes"
And I edit all Attachments settings to "Yes"
And I edit all Associations settings to "Yes"
And I edit Actions Required to "Yes" for all actions
And I edit all Form Statuses settings to "Yes"
And I edit all Additional Form Settings to "Yes"
And I save Form Template Settings changes
Then App Settings should get saved successfully to "Yes"
When I click on "x" button of App Settings window
And I click on "x" button
And I right click on Project And I click on Edit And I click on "User Access" option into context menu List
When I click on "Form Permissions" option on LH menu
Then "Form Permissions" popup should open
When I click on "Form Name" dropdown and search Form
When I select correct form and click on "Close" Link
Then "Form Permissions" should get filtered for selected form
When I select "Control" form role permission checkbox and click on Save for "Workspace - Administrator"
And I click on "x" button
Then Popup window should close
Given I am on "Project Forms" tab
And I clik on above created project
When I click on "New" option in LH panel
And I search updated form  on "Create Form" window
And I click on updated Form Name
Then Controller dropdown should be available "Yes"
When I select valid user from Controller dropdown
And I enter mandatory values to create form
And I attach external file to the form and click on Send button with Controller "Yes"
Then Form with Controller "Yes" should be created successfully
#Scenario : Edit App Settings to "No"
Given I am on "Projects" tab
And I have atleast one project available
When I search created Project
And I right click on Project And I click on Edit And I click on "App Settings" option into context menu List 
Then "Manage App Settings" popup should open
When I search form template and click "Edit" icon
Then "Edit App settings" popup should open
When I edit all Form Template Settings to "No" for <Project_Name>
When I edit all Controller Settings to "No"
And I edit all Response Settings to "No"
And I edit all Distribution settings to "No"
And I edit all Edit and Forward settings to "No"
And I edit all Attachments settings to "No"
And I edit all Associations settings to "No"
And I edit Actions Required to "No" for all actions
And I edit all Form Statuses settings to "No"
And I edit all Additional Form Settings to "No"
# Rechecking all options "No"
When I edit all Controller Settings to "No"
And I edit all Response Settings to "No"
And I edit all Distribution settings to "No"
And I edit all Edit and Forward settings to "No"
And I edit all Attachments settings to "No"
And I edit all Associations settings to "No"
And I edit Actions Required to "No" for all actions
And I edit all Form Statuses settings to "No"
And I edit all Additional Form Settings to "No"
And I save Form Template Settings changes
Then App Settings should get saved successfully to "No"
When I click on "x" button of App Settings window
And I click on "x" button
Given I am on "Project Forms" tab
And I clik on above created project
When I click on "New" option in LH panel
And I search form  on "Create Form" window
And I click on updated Form Name
Then Controller dropdown should be available "No"
When I enter mandatory values to create form
And I attach external file to the form and click on Send button with Controller "no"
Then Form with Controller "No" should be created successfully
Given I am on "Project Forms" tab
And I clik on above created project
And I search created form and click on it
Then Created Form should be viewed
Then User should be able to edit the form
#Scenario : Close Project
Then User should be able to close project
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|