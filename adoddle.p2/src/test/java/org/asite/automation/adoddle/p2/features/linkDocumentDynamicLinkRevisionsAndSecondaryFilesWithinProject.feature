Feature: Link Dynamic Conditional Document - Revisions Secondary Files and User Distribution

@P2T1
Scenario Outline: Verify Revision, Secondary Files And User Distribution
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Files" tab
And I have focus on <Project> project
And I create conditional testdata folder for "within" linking document
And I have focus on "CustomAttributes" folder
And I upload multiple documents for "Dynamic" link operation
And I have "LinkTest" document in document listing to link
#Within Project Link
And I select any document to link
And click on "Link File(s)" from more options
Then "Target Folder" popup should open
When I select link destination "Conditional link" folder "within" project And click on "Select" button
Then "Link File(s)" popup should open
When I select dynamic link condition "=" for status "---"
And I select dynamic link condition "=" for status "For Information"
When I select <DistributionUser1> and <DistributionUser2> Users in "To" field And select "Dynamic" type And Click on "Submit" button
Then Link should be successfully created
And I am on "Files" tab
And "Conditional" Link document should be available in target folder "within" project
Given I have focus on <Project> project
And I have focus on "CustomAttributes" folder
When I upload revisions of the source link documents with external attachments "true"
Then Documents matching with condition should get "revision" updated "within" project
And Documents not matching with condition should not get "revision" updated "within" project
And Documents matching with condition should get distributed to correct users "within" project
Given I am on "Files" tab
And I have focus on <Project> project
And I deactivate the "Conditional" link testdata folder
Examples: 
|DC_Center|Project|DistributionUser1|DistributionUser2|
|UK|AutomationTestProject|RFI Bloggs|RFI Builder|
#|US|Automatic_Test_US_WS|RFI Bloggs|RFI Builder|
#|AUS|Automatic_Test_AUS_WS|RFI Bloggs|RFI Builder|