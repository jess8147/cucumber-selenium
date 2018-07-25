Feature: Create Multiple Replies with Form

@P2T6
Scenario Outline: Create ORI with Multiple Attachments - Associations
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on folder "AutomationUploadFiles"
Then More than one file should Available in File Listing
And I have posted comment on multiple files Available in listing
Given I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "Communications" folder
And I have focus on form template "Request For Information"
When I click on "Create Form" button on Project Forms page
Then Pop-up "ORI:Create Form" should open
And I have selected distribution group as "MultipleRepliesFormUserGroup" into "ORI-To"
And I input title "AutoTestMultiReplyForm" and description for RFI form
When I click on "Attachments" clip icon
And I have Attached more than one documents
And I click on "More Options" associate icon
When I click on "Associate Docs" link from dropDown menuList
And I have Associated atleast one documents for "ORI0001"
And I click on "More Options" associate icon
When I click on "Associate aMessages" link from dropDown menuList
And I Associated more than one Discussions for "ORI0001"
And I click on "More Options" associate icon
When I click on "Associate Apps" link from dropDown menuList
And I have Associated more than one Forms for "ORI0001"
And I click on Send button of CreateForm popup
Then "AutomationTestMultipleReplies" form should created sucessfully AND I validated all  Attached AND Associated documents
Examples:
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T6
Scenario Outline: Create FWD with Multiple Attachments - Associations
Given Project DC <DC_Center> is available
And I am already logged in
And I switch to <User> user
Given I am on "Project Forms" tab
And I have search form "AutomationTestMultipleReplies"
And I validated all assigned actions
When I Click form "AutomationTestMultipleReplies" title
Then form "AutomationTestMultipleReplies" should open in Viewer
And I click "Actions" AND I Select option "Edit and Distribute"
Then Pop-up "FWD:Create Form" should open
And I have selected User as "Jasmin Prajapati" into "To" Field
When I click on "Attachments" clip icon
And I have Attached more than one documents
And I click on "More Options" associate icon
When I click on "Associate Docs" link from dropDown menuList
And I have Associated atleast one documents for "FWD0001"
And I click on "More Options" associate icon
When I click on "Associate aMessages" link from dropDown menuList
And I Associated more than one Discussions for "FWD0001"
And I click on "More Options" associate icon
When I click on "Associate Apps" link from dropDown menuList
And I have Associated more than one Forms for "FWD0001"
And I click on Send button of CreateForm popup
And I validated all Attached AND Associated documents for Message "FWD001"
Then "FWD0001:Forward" form action should completed sucessfully AND I validated completed form actions
Examples:
|DC_Center|Project|User|
|UK|AutomationTestProject|RFI Bloggs|
#|US|Automatic_Test_US_WS|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|

@P2T6
Scenario Outline: Create RES with Multiple Attachments - Associations
Given Project DC <DC_Center> is available
And I am already logged in
And I switch to <User> user
Given I am on "Project Forms" tab
And I have search form "AutomationTestMultipleReplies" AND I validated assigned actions
And I mouse hover form action AND I click action "ORI001:Respond"
Then Pop-up "RES:Respond Form" should open
And I have selected User as "Jasmin Prajapati" into "To" AND I put Response as "Random String"
When I click on "Attachments" clip icon
And I have Attached more than one documents
And I click on "More Options" associate icon
When I click on "Associate Docs" link from dropDown menuList
And I have Associated atleast one documents for "RES0001"
And I click on "More Options" associate icon
When I click on "Associate aMessages" link from dropDown menuList
And I Associated more than one Discussions for "RES0001"
And I click on "More Options" associate icon
When I click on "Associate Apps" link from dropDown menuList
And I have Associated more than one Forms for "RES0001"
And I click on Send button AND close form successfully
And I have search form "AutomationTestMultipleReplies"
And I validated all assigned actions
When I Click form "AutomationTestMultipleReplies" title
Then form "AutomationTestMultipleReplies" should open in Viewer
And "ORI001:Respond" form action should completed sucessfully AND I validated completed form actions
And I navigate left panel AND I click on History
Then form history should load sucessfully
When I Select filter type as "Distribution" AND Message id as "All"
Then Atleast one result should available in list sucessfully
And I Validated "Multiple Replies" of form sucessfully
And I navigate left panel AND I click on Attachments
Then Atleast one record should Available in Listing
When I Select Attachments filter type as "Attachments" AND Message id as "ORI001"
And I Validated Record count as "3" for Message id as "ORI001"
When I Select Attachments filter type as "Attachments" AND Message id as "FWD001"
And I Validated Record count as "3" for Message id as "FWD001"
When I Select Attachments filter type as "Attachments" AND Message id as "RES001"
And I Validated Record count as "3" for Message id as "RES001"
Examples:
|DC_Center|Project|User|
|UK|AutomationTestProject|TC Bloggs|
#|US|Automatic_Test_US_WS|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|TC Bloggs|

@P2T6
Scenario Outline: Download Attachments And Associations
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
And I have search form "AutomationTestMultipleReplies"
When I mouse hover "Attachments" link I select all files AND click on Download
Then Batch file should be created AND "Attachments" Zip file should be downloaded into Local Directory
And I Unzipped downloaded "Attachments" zip file
And I validated all "Attachments" in Local Directory
And I am on "Files" tab AND I select all files AND click on Download
Then Batch file should be created AND "Associations" Zip file should be downloaded into Local Directory
And I Unzipped downloaded "Associations" zip file
And I validated all "Associations" in Local Directory
Examples:
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T6
Scenario Outline: Download Attachments And Associations on form viewer
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
And I have search form "AutomationTestMultipleReplies"
When I Click form "AutomationTestMultipleReplies" title 
Then form "AutomationTestMultipleReplies" should open in Viewer
And I navigate left panel AND I click on Attachments
Then Atleast one record should Available in Listing 
When I Select Attachments filter type as "Attachments" AND Message id as "All"
And I Validated Record count as "9" for Message id as "All"
When I select all files AND click on Download Icon
Then Batch file should be created AND "Attachments" Zip file should be downloaded into Local Directory
And I Unzipped downloaded "Attachments" zip file
And I validated all "Attachments" in Local Directory
When I Select Attachments filter type as "Files" AND Message id as "All"
And I Validated Record count as "18" for Message id as "All"
When I select all files AND click on Download Icon
Then Batch file should be created AND "Associations" Zip file should be downloaded into Local Directory
And I Unzipped downloaded "Associations" zip file
And I validated all "Associations" in Local Directory
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|