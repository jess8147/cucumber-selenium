Feature: Workflow Trigger via Custom Attributes

@P2T3
Scenario Outline: Workflow Trigger via CustomAttributes
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on Folder "Automation_WF_CustomAttributes"
When I clicked on "Add File (s)" button AND I have selected Multiple Files from Local for Scenario "CustomAttributes"
Then All Workflow Files Should Available in Document Listing AND I edit Attributes of First Three Files for Scenario "CustomAttributes"
When I switch to <User> user
Given I am on "Files" tab
And I have focus on Folder "Automation_WF_CustomAttributes"
And I Search All Files having Status as "For Information"
Then I Validate All Files Workflow status and MyActions for Scenario "CustomAttributes"
Examples: 
|DC_Center|Project_Name|User|
|UK|AutomationTestProject|RFI Builder|
#|US|Automatic_Test_US_WS|RFI Builder|
#|AUS|Automatic_Test_AUS_WS|RFI Builder|