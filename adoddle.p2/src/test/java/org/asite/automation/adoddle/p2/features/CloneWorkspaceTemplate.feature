Feature:Cloned-Workspace Template Workflow

Background:
Given Script is Node specific

@exclude
Scenario Outline: Create Template And Cloned-Inherited Workspace
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I Switch to "Classic View" from Adoddle
And I click on tab "Workspaces"
Then <Project> should Available in Workspace List
And I click on <Project> project
Then I should redirect on <Project> home page
When I select "Edit Workspace" from Admin Dropdown
Then "Edit workspace" page should open
And I click on link "Save workspace as template"
Then I should redirect on "Create workspace template" Page
And I Edit Workspace Template name as "AutomationTestProject_Template"
And I click on "Save All" Link
Then "AutomationTestProject_Template" should created successfully AND Available in workspace template list
Examples: 
|DC_Center|Project|
|UK|TemplateClonedInheritanceAutoTest_UK_Workpace|
#|US|TemplateClonedInheritanceAutoTest_US_Workpace|
#|AUS|TemplateClonedInheritanceAutoTest_AUS_Workpace|