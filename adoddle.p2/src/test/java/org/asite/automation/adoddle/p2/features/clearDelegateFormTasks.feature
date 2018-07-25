Feature: Clear Delegate Form Tasks

@P2T4
Scenario Outline: Distribute Tasks
#1. Create new ORI  form and distributes actions to DelegateActionsDistributionGroup1 (All actions to PA Builder, RFI Builder, TC Bloggs)
#2. Create new RE: by RFI Builder and distributes actions to DelegateActionsDistributionGroup2
#3. Create new FWD: by PA Builder form and distributes actions to DelegateActionsDistributionGroup3
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
And I have focus on Project as <Project_Name> AND I click on Folder "Communications" AND I click on "Request For Information" Form type
Then "Create Form" button is enabled
When I click on "Create Form" button on Project Forms page
Then Pop-up "ORI:Create Form" should open
And I have selected distribution group as "DelegateActionsDistributionGroup1" into "To"
And I have filled all mandatory form fields
And I have attached atleast one document for form
When I Click on "Save" button on create form window
Then Form should created sucessfully AND Available in "Project Forms" listing
And I switch to <UserB> user
Given I am on "Project Forms" tab
And I have search created form "AutomationTest_DelegateActionsForm" AND I hover actions link
And I Validate all actions with due days
And I mouse hover form action AND I click action "ORI001:Respond"
Then Pop-up "RES:Respond Form" should open
And I have selected distribution group as "DelegateActionsDistributionGroup2" into "To"
And I perform "ORI001:Respond" form action sucessfully
And I switch to <UserC> user
Given I am on "Project Forms" tab
And I have search created form "AutomationTest_DelegateActionsForm" AND I validated assigned actions count as "+13"
And I click form title
Then form "AutomationTest_DelegateActionsForm" should open in HTML Viewer
And I click "Actions" AND I select option "Edit and Distribute"
Then Pop-up "FWD:Create Form" should open
And I have selected distribution group as "DelegateActionsDistributionGroup3" into "To"
And I perform "FWD001:Forward" form action sucessfully
Examples:
|DC_Center|Project_Name|UserB|UserC|UserD|UserE|UserF|UserG|
|UK|AutomationTestProject|RFI Builder|PA Builder|TC Bloggs|RFI Bloggs|Automation UK|Auto Test|
#|US|Automatic_Test_US_WS|RFI Builder|PA Builder|TC Bloggs|RFI Bloggs|Automation US|Auto Test|
#|AUS|Automatic_Test_AUS_WS|RFI Builder|PA Builder|TC Bloggs|RFI Bloggs|Automation AUS|Auto Test|

@P2T4
Scenario Outline: Delegate Tasks
#1. Open Form History by RFI Builder and Delegate ORI with Existing Due Date option
#2. Open Form History by PA Builder and Delegate RE: with Re-calculate days by actual distribution option
#3. Open Form History by TC Bloggs and Delegate FWD: with User Definition option
Given Project DC <DC_Center> is available
And I am already logged in
And I switch to <UserB> user
Given I am on "Project Forms" tab
And I have search created form "AutomationTest_DelegateActionsForm" AND I validated assigned actions count as "+10"
And I click form title
Then form "AutomationTest_DelegateActionsForm" should open in HTML Viewer
And I navigate left panel AND I click on History
Then form history should load sucessfully
When I Select filter type as "Distribution" AND Message id as "ORI001"
Then Atleast one result should available in list sucessfully
And I validated form actions sucessfully AND I selected all incomplete actions
And I click link AND I select option as "Delegate Tasks"
Then "Delegate Tasks" Popup Should open
And I select user as <UserE> into To field AND Assign action due date as "Existing Due Date"
And I click button "Continue"
When I Select filter type as "Distribution" AND Message id as "ORI001"
Then All incomplete actions delegated sucessfully
And I switch to <UserC> user
Given I am on "Project Forms" tab
And I have search created form "AutomationTest_DelegateActionsForm" AND I validated assigned actions count as "+4"
And I click form title
Then form "AutomationTest_DelegateActionsForm" should open in HTML Viewer
And I navigate left panel AND I click on History
Then form history should load sucessfully
When I Select filter type as "Distribution" AND Message id as "RES001"
Then Atleast one result should available in list sucessfully
And I validated form actions sucessfully AND I selected all incomplete actions
And I click link AND I select option as "Delegate Tasks"
Then "Delegate Tasks" Popup Should open
And I select user as <UserF> into To field AND Assign action due date as "Re-calculate days by actual distribution"
And I click button "Continue"
When I Select filter type as "Distribution" AND Message id as "RES001"
Then All incomplete actions delegated sucessfully
And I switch to <UserD> user
Given I am on "Project Forms" tab
And I have search created form "AutomationTest_DelegateActionsForm" AND I validated assigned actions count as "+8"
And I click form title
Then form "AutomationTest_DelegateActionsForm" should open in HTML Viewer
And I navigate left panel AND I click on History
Then form history should load sucessfully
When I Select filter type as "Distribution" AND Message id as "FWD001"
Then Atleast one result should available in list sucessfully
And I validated all form assigned actions to <UserF> AND I selected all incomplete actions selectionType "All Actions"
And I click link AND I select option as "Delegate Tasks"
Then "Delegate Tasks" Popup Should open
And I select user as <UserF> into To field AND Assign action due date as "User Definition"
And I click button "Continue"
When I Select filter type as "Distribution" AND Message id as "FWD001"
Then All incomplete actions delegated sucessfully
Examples:
|DC_Center|Project_Name|UserB|UserC|UserD|UserE|UserF|UserG|
|UK|AutomationTestProject|RFI Builder|PA Builder|TC Bloggs|RFI Bloggs|Automation UK|Auto Test|
#|US|Automatic_Test_US_WS|RFI Builder|PA Builder|TC Bloggs|RFI Bloggs|Automation US|Auto Test|
#|AUS|Automatic_Test_US_WS|RFI Builder|PA Builder|TC Bloggs|RFI Bloggs|Automation AUS|Auto Test|

@P2T4
Scenario Outline: Clear Tasks
#1. Open Form History by RFI Bloggs and Clear ORI Incomplete actions by selecting actions individually
#2. Open Form History by RFI Bloggs and Clear RE: Incomplete actions by selecting all actions
Given Project DC <DC_Center> is available
And I am already logged in
And I switch to <UserE> user
Given I am on "Project Forms" tab
And I have search created form "AutomationTest_DelegateActionsForm" AND I validated assigned actions count as "+6"
And I click form title 
Then form "AutomationTest_DelegateActionsForm" should open in HTML Viewer
And I navigate left panel AND I click on History
Then form history should load sucessfully 
And I Select filter type as "Distribution" AND Message id as "ORI001"
Then Atleast one result should available in list sucessfully
And I validated all form assigned actions to <UserE> AND I selected all incomplete actions selectionType "Incomplete Actions only"
And I click link AND I select option as "Clear Tasks"
Then "Clear Tasks" Popup Should open
And I click buttons "Continue" AND "OK"
When I Select filter type as "Distribution" AND Message id as "RES001"
Then Atleast one result should available in list sucessfully
And I validated all form assigned actions to <UserE> AND I selected all incomplete actions selectionType "All Actions"
And I click link AND I select option as "Clear Actions"
Then "Clear Actions" Popup Should open
And I click buttons "Continue" AND "OK"
Then All incomplete actions cleared sucessfully
Examples:
|DC_Center|Project_Name|UserE|
|UK|AutomationTestProject|RFI Bloggs|
#|US|Automatic_Test_US_WS|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|