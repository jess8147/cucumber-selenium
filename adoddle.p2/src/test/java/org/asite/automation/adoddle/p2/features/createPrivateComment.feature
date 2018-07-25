Feature: Create Private Comment

@P2T7
Scenario Outline: Create Private Comment with Attachments and Associations
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab
And I have focus on "AutomationUploadFiles" folder
And I have atleast one document to create comment on listing
When I Right click on any document
And I select context menu option "New" and click on "Start a Discussion"
Given I have opened "New" popup to create comment
And I have selected users into "To*" as <Second User> and <Third User>
And I have entered "Title*"
And I have attached atleast one documents
And I have associated atleast one document
And I have associated atleast one discussion
And I have associated atleast one form
And I have checked private checkbox
When I Click on "Submit" button
Then Comment should be successfully created
And "Single Private" Comment should be available in "Discussions" tab
And "Single Private" Comment should be available in Files detail
And Association should be successfully done with selected documents
And Form Association should be successfully done with selected forms
And Discussion Association should be successfully done with selected discussions
Given I switch to <Second User> user
And I am on "Discussions" tab
Then "Single Private" Comment should be available in "Discussions" tab
Given I switch to <Forth User> user
And I am on "Discussions" tab
Then Comment should not be available on Discussions listing
Examples: 
|DC_Center|Second User|Third User|Forth User|
|UK|Automation UK|RFI Builder|RFI Bloggs|
#|US|Automation US|RFI Builder|RFI Bloggs|
#|AUS|Automation AUS|RFI Builder|RFI Bloggs|

@P2T7
Scenario Outline: Reply to Private Comment
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Discussions" tab
Then "Single Private" Comment should be available in "Discussions" tab
When I Right click on private discussion
And I select context menu option "New" and click on "Reply"
Then User should be switched to new window
When User creates reply to <Second User> user from To list
Then Reply should get created sucessfully
Given I logout from adoddle and close browser
And I login using <Secondary UID> user
And I am on "Discussions" tab
Then "Single Private" Comment should be available in "Discussions" tab
And User should be able to view private discussion reply "yes"
Given I am on "aMessages" tab
Then <Secondary User> should get comment reply as aMessage from <Primary User>
Given I logout from adoddle and close browser
And I am already logged in
Given I switch to <Third User> user
And I am on "Discussions" tab
Then "Single Private" Comment should be available in "Discussions" tab
And User should be able to view private discussion reply "no"
Examples: 
|DC_Center|Primary User|Second User|Third User|Secondary UID|
|UK|Automation UKP2|Automation UK|RFI Builder|auto_uk@atest.com|
#|US|Automation USP2|Automation US|RFI Builder|auto_us@atest.com|
#|AUS|Automation AUSP2|Automation AUS|RFI Builder|auto_aus@atest.com|