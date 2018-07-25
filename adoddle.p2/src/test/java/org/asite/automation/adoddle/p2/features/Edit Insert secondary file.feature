Feature: Edit Insert Secondary File

@P2T3
Scenario Outline: Verify Edit Insert Option is Disabled
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
When I select "multiple" files on file listing
And I right click on file and select Edit option
Then "Secondary Files" option should be disabled
Given I reload the page
And I am on "Files" tab
When I search linked file with "Static" link 
And I right click on file and select Edit option
Then "Secondary Files" option should be disabled
Given I reload the page
And I am on "Files" tab
When I search linked file with "Dynamic" link 
And I right click on file and select Edit option
Then "Secondary Files" option should be disabled
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T3
Scenario Outline: Verify Single file secondary upload only
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
When I select "single" files on file listing
And I right click on file and select Edit option
And I select "Secondary Files" option from context menu
Then User should be able to upload only single file
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T3
Scenario Outline: Download Edited Secondary File
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on "AutomationUploadFiles" folder
When I upload file with ".jpg" as secondary file
And I search uploaded file to edit secondary file
And I right click on file and select Edit option
And I select "Secondary Files" option from context menu
Then "Attachments" popup should open
When User attaches ".pdf" file as secondary file
Then Secondary file should get replaced for uploaded doc
And Audit trail history should show correct data
When I download primary file with all associations
Then Secondary file should get downloaded as ".pdf" file
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|