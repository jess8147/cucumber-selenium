Feature: Cloned Inheritance Workspace Objects

@P2T2
Scenario Outline: Create Template And Cloned-Inherited Workspace
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I Switch to "Classic View" from Adoddle
And I click on tab "Workspaces"
Then <Workspace> should Available in Workspace List
And I click on <Workspace> project
Then I should redirect on <Workspace> home page
When I select "Edit Workspace" from Admin Dropdown
Then "Edit workspace" page should open
And I click on link "Save workspace as template"
Then I should redirect on "Create workspace template" Page
And I Edit Workspace Template name as "AutomationTestProject_Template" successfully
And I click on "Save All" Link
Then "AutomationTestProject_Template" should created successfully AND should Available in workspace Template list
When I click clonned image link of "AutomationTestProject_Template"
Then I should redirect on "Create Workspace" page
And I have enter all mandatory fields of <Cloned_Project> scenario "Like_DataCenter"
And I Break Inheritance for <Cloned_Project>
And I click on "Save All" Link
Then "Cloned_AutomationTestProject" should created successfully AND Available in all workspace list
And I have focus on "Workspace Templates" Tab 
When I click clonned image link of "AutomationTestProject_Template"
Then I should redirect on "Create Workspace" page
And I have enter all mandatory fields of <InheritedCloned_Project> scenario "Like_DataCenter"
And I click on "Save All" Link
Then "InheritedCloned_AutomationTestProject" should created successfully AND Available in all workspace list
Examples: 
|DC_Center|Workspace|Cloned_Project|InheritedCloned_Project|
|UK|AutomationTest_Workspace_UK|Cloned_AutoTestProject_UK|InheritedCloned_AutoTestProject_UK|
#|US|AutomationTest_Workspace_US|Cloned_AutoTestProject_US|InheritedCloned_AutoTestProject_US|
#|AUS|AutomationTest_Workspace_AUS|Cloned_AutoTestProject_AUS|InheritedCloned_AutoTestProject_AUS|

@P2T2
Scenario Outline: Clond-Inherited Document Status And POI 
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I Switch to "Classic View" from Adoddle
When I click on tab "Workspaces"
Then <Workspace_Template> should Available in Template List
And I click on <Workspace_Template> in template List
Then I should redirect on <Workspace_Template> home Page
When I select option "Manage Statuses" from Admin Dropdown
Then "Manage Status" page should open
When I click "Add Document Status" icon
Then "create doc status" page should open
And I have enter docStatus name as "Status_WFAccess_Admin" AND its mandatory attributes successfully
And I click on "Submit" Button on pop up "create doc status"
Then docStatus "Status_WFAccess_Admin" should created successfully AND Available in Manage Status list
When I select option "Manage Purpose of Issue" from Admin Dropdown
Then "Manage Purpose of Issue" page should open
When I click "Add Purpose of Issue" icon
Then "create purpose of Issue" page should open
And I have enter POI name as "POI_WFAccess_Admin" AND its mandatory attributes successfully
And I click on "Submit" Button on pop up "create poi"
Then POI "POI_WFAccess_Admin" should created successfully AND Available in Manage POI list
And I have updated existing POI "For Approval" as "Updated_ForApproval" successfully in Manage POI list
When I click on "My Home" link AND I Switch to "Adoddle View" from Settings Dropdown list
Then I should redirect on Adoddle View "Dashboard"
And I am on "Projects" tab
And I have search "InheritedCloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "File Statuses"
Then "Manage Doc. Statuses" popup should open
And I have validated docStatus "Status_WFAccess_Admin" should available AND Grayed out in "InheritedCloned_AutoTestProject"
And I have search "Cloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "File Statuses"
Then "Manage Doc. Statuses" popup should open
And I have validated docStatus "Status_WFAccess_Admin" should not available in "Cloned_AutoTestProject"
And I have search "InheritedCloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "Purpose of Issue"
Then "Manage Purpose of Issue" popup should open
And I have validated POI "POI_WFAccess_Admin" should available AND Grayed out in "InheritedCloned_AutoTestProject"
And I have search "Cloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "Purpose of Issue"
Then "Manage Purpose of Issue" popup should open
And I have validated POI "POI_WFAccess_Admin" should not available in "Cloned_AutoTestProject"
Given I am on "Files" tab
And I have focus on workspace "InheritedCloned_AutoTestProject" AND I have created Directory as "AutoTestFolder"
And I have published Document with created docStatus AND updated existing POI Successfully
Examples: 
|DC_Center|Workspace_Template|
|UK|AutomationTest_Workspace_UK|
#|US|AutomationTest_Workspace_US|
#|AUS|AutomationTest_Workspace_AUS|

@P2T2
Scenario Outline: Clond-Inherited workspace userRoles And Directories
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I Switch to "Classic View" from Adoddle
When I click on tab "Workspaces"
Then <Workspace_Template> should Available in Template List
And I click on <Workspace_Template> in template List
Then I should redirect on <Workspace_Template> home Page
When I select option "Manage User Role Memberships" from Admin Dropdown
Then "Manage user role memberships" page should open
And I have created new userRole as "Automation_Test_Role" with users as "RFI Builder" "PA Builder"
And I have updated existing role "Automation_Test_Role" as "Updated_Automation_Test_Role" AND users as "RFI Bloggs" "PA Bloggs"
When I click "Workspace Home" Link on top Panel
Then I should redirect on <Workspace_Template> home Page
When I click "All Workspace Documents" link
Then I should redirect on "All Workspace Documents" Listing
When I Click on "Create New Parent Folder" img icon
Then "Create Parent Folder" page should be open
When I Enter FolderName "AutomationTestFolder" AND I Click on "Create Folder" button
Then Folder should be created AND Folder should be available in Folder Tree
And I have updated existing directory "Automation_WF_Folder" as "Updated_Automation_WF_Folder" successfully in listing
When I click on "My Home" link AND I Switch to "Adoddle View" from Settings Dropdown list
Then I should redirect on Adoddle View "Dashboard"
And I am on "Projects" tab
And I have search "InheritedCloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "User Access"
Then "Roles" popup should open
And I have validated userRoles "Automation_Test_Role" AND "Updated_Automation_Test_Role" should available AND Grayed out
And I have search "Cloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "User Access"
Then "Roles" popup should open
And I have validated userRoles "Automation_Test_Role" AND "Updated_Automation_Test_Role" should not available
Given I am on "Files" tab
And I have focus on "InheritedCloned_AutoTestProject" AND I have validated created AND updated Directories successfully in Listing
And I have focus on "Cloned_AutoTestProject" AND I have validated All Directories remain intact
Examples: 
|DC_Center|Workspace_Template|
|UK|AutomationTest_Workspace_UK|
#|US|AutomationTest_Workspace_US|
#|AUS|AutomationTest_Workspace_AUS|

@P2T2
Scenario Outline: Clond-Inherited workspace Apps
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I Switch to "Classic View" from Adoddle
When I click on tab "Workspaces"
Then <Workspace_Template> should Available in Template List
And I click on <Workspace_Template> in template List
Then I should redirect on <Workspace_Template> home Page
When I select option "Apps - Assign to Workspace" from Admin Dropdown
Then "assign apps to workspace" page should open
And I have copied new appTemplate as "A combined Form" successfully
And I have updated appTemplate "A Combined Form" name AND XSN as "Request_ForInformation" successfully
And I have updated existing appTemplate "AutoTestAppTemplate" name AND XSN as "AutoAppUpdated_Template" successfully
When I select option "Manage User Role Memberships" from Admin Dropdown
Then "Manage user role memberships" page should open
And I navigate tab "Role Form Permissions"
And I have assigned "Create" AND "View All Draft Forms - Own Org" Permission to All AppTemplates successfully
When I click on "My Home" link AND I Switch to "Adoddle View" from Settings Dropdown list
Then I should redirect on Adoddle View "Dashboard"
And I am on "Projects" tab
And I have search "InheritedCloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "App Settings"
Then "Manage App Settings" popup should open
And I have validated appTemplate "AutoAppTestTemplate" AND "AutoAppUpdated_Template" should available AND Grayed out
And I have search "Cloned_AutoTestProject" successfully And I context click
And I mouse hover "Edit" AND I have select context click menuOption as "App Settings"
Then "Manage App Settings" popup should open
And I have validated appTemplate "AutoAppTestTemplate" AND "AutoAppUpdated_Template" should not available
Given I am on "Project Forms" tab
And I have focus on workspace "InheritedCloned_AutomationTestProject" AND AppTemplate "AutoTestGroup"
When I click on "Create Form" button on Project Forms page
Then Pop-up "ORI-Create Form" should open
And I have created an app as "Inherited_AutoTestForm" with appTemplate "AutoAppTestTemplate" successfully 
Then "Inherited_AutoTestForm" form should created successfully AND I validated App XSN "Request_ForInformation" successfully
When I Context click on "Inherited_AutoTestForm" I mouse hover on "Share" AND I select option "Distribute"
Then "Distribute" popup should open
And I have validated "Respond" Action should not available on popUp "Distribute" AND in File Viewer successfully
And I have focus on workspace "Cloned_AutoTestProject" AND AppTemplate "AutoTestAppTemplate"
When I click on "Create Form" button on Project Forms page
Then Pop-up "ORI-Create Form" should open
And I have created an app as "Cloned_AutoTestForm" with appTemplate "AutoAppTestTemplate" successfully 
Then "Cloned_AutoTestForm" form should created successfully AND I validated App XSN "Request_ForInformation" successfully
When I Context click on "Cloned_AutoTestForm" I mouse hover on "Share" AND I select option "Distribute"
Then "Distribute" popup should open
And I Distributed form "Cloned_AutoTestForm" with Respond action to User "TC Bloogs"
When I logged in as <User>
Then I should re-directed to Dashboard of user "TC Bloggs"
Given I am on "Project Forms" tab
And I have search form "Cloned_AutoTestForm" And I validated "Respond" action successfully
And I have completed "Respond" action successfully
Examples: 
|DC_Center|Workspace_Template|User|
|UK|AutomationTest_Workspace_UK|TC Bloggs|
#|US|AutomationTest_Workspace_US|TC Bloggs|
#|AUS|AutomationTest_Workspace_AUS|TC Bloggs|

@P2T2
Scenario Outline: Clean_Up Operation
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I have deactivated Cloned AND Inherited workspaces successfully
And I Switch to "Classic View" from Adoddle
And I click on tab "Workspaces"
And I have search AND deactivated template successfully
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|