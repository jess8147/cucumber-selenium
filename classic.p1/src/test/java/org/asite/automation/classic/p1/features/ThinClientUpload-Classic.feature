Feature: Thin Client Upload - Classic

@P1T2
Scenario Outline: Thin Client Upload with Custom Attributes with Distribution
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
#When I select "Edit Workspace" from ADMIN dropdown list
#And I have disabled Simple Upload
And I have focus on "AutomationUploadFiles" folder
#And I have Public Standard Document option enabled
When I click on Upload Standard Document
Then "Publish Documents" window should open for upload
When I click on "Add Files" AND I have selected more then one Files from Local for "Thin Client Upload"
And I click on "Enter Document Details" input button
Then Attributes should open for selected files
When I have entered all mandatory attributes
And I click on "Document Distribution" on Publish window
Then "Publish Documents - Distribute" popup window should open
When I select "Automation Secondary" user with "For Information" Action AND Due dates
And Click on Distribute button
Then selected users should be available on Publish Documents - Publish popup with Actions
When I click on Start Upload button
Then Selected documents should be uploaded AND Upload summary popup should open
When I click on OK button on upload summary popup
Then Uploaded documents should be available on document listing
And Actions should be assigned to secondary user
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|

@P1T2
Scenario Outline: Think Client upload without Distribution
Given Project DC <DC_Center> is available
And I am already logged in 
And I am on Workspace home page of workspace <Project>
#When I select "Edit Workspace" from ADMIN dropdown list
#And I have disabled Simple Upload
And I have focus on "CustomAttributes" folder with attributes assigned
#And I have Public Standard Document option enabled
When I click on Upload Standard Document
Then "Publish Documents" window should open for upload
When I click on "Add Files" AND I have selected more then one Files from Local for "Thin Client Upload"
And I click on "Enter Document Details" input button
Then Attributes should open for selected files
When I click on Copy FileName button
Then All documents File Name Without extension should be copied in Doc Title
When I enter Values into header for Attributes AND Select Overwrite
And click on Apply To All button
Then All attributes values should be filled with Values AND Doc Ref should be filled with combine custom attributes
When I select "Do not Distribute" in Distribute dropdown
And I click on Start Upload button
Then Selected documents should be uploaded AND Upload summary popup should open
When I click on OK button on upload summary popup
Then Uploaded documents should be available on document listing
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|