Feature: Create-Edit-Workspace

@P1T2
Scenario Outline: Create Edit Deactivate Workspace
Given Project DC <DC_Center> is available
And I am already logged in 
And I am on Workspaces tab
When I click on "Add New Workspace" link on Workspaces tab
Then "create workspace" page should open
When I have entered WorkspaceName 
And Selected Installed Application 
And Select Viewer as "Thin Client Viewer"
And selected Primary Geography as <Geography>
And I click on "Save" button on popup
Then Workspace should be Saved/Created
When I click on "BACK" Image
Then I should redirect on Workspaces tab AND created workspace should be available in Favourite Workspaces
When I click created workspace
Then Workspace home page should open
When I click on ADMIN dropdown AND select "Edit Workspace"
Then "Edit workspace" page should open
When I Change the WorkspaceName AND I click on save all button
Then Workspace should be updated with new WorkspaceName
When I selected "Closed" status in "Status" dropdown AND click on save button
Then WorkspaceName workspace should be closed AND WorkspaceName workspace should not be available in workspace listing
Examples: 
|DC_Center|Geography|
|UK|United Kingdom (EU01)|
#|US|North America (US01)|
#|AUS|Australia (AU01)|