Feature: Create Site- Locations

@P2T4
Scenario Outline: Create Site
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
When I create new field enabled project with <Geography>
And I have atleast one project available
When I right click on Project And I click on Edit And I click on "Apps" option into context menu List
Then "Assign Apps" popup should open
When I select "Defect" app and click on "Add to Project" button
Then "Manage App Settings" popup should open
When I search app template and right click
And I select "Mark as Active" option on context menu
Then Status should change to "Active"
When I click on "x" button
Then Popup window should close
When I right click on Project And I click on Edit And I click on "User Access" option into context menu List
And I click on "Form Permissions" option on LH menu
Then "Form Permissions" popup should open
When I select "Create" form role permission checkbox and click on Save for "Field Administrator"
And I click on "Manage Roles" option on LH menu
Then "Roles" popup should open
When I add Current User as "Field Administrator" role and click on "Save" button
And I click on "Form Permissions" option on LH menu
And I click on "x" button
Then Popup window should close
Given I am on "Field" tab
And I context click on field enabled Project
Then "Add Site" option should be enabled
When I click on "Add Site" option to add site
Then "Add Site" popup should open
When I enter Site Name as "Automation_TestSite"
And I click on "Create" button
Then "Site Or Location Added successfully" message appears
And Site should be available in the project
Examples: 
|DC_Center|Geography|
|UK|United Kingdom (EU01)|
#|US|North America (US01)|
#|AUS|Australia (AU01)|

@P2T4
Scenario Outline: Create Location
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Field" tab
Given Site is available in the project
And I context click on Site
And I click on "Add Location" option to add location
Then "Add Location" popup should open
When I enter Location Name as "Auto_TestLocation"
And I click on "Create" button
Then "Site Or Location Added successfully" message appears
And Location should be available in the project under the selected site
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|