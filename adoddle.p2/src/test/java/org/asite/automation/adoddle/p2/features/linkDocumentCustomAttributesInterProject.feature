Feature: Link Dynamic Conditional Document - Custom Attributes Inter Project

@P2T5
Scenario Outline: Custom Attributes Inter Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
When I have cloned existing project <Template> for <DC_Center>
Given I create conditional testdata folder for "inter" linking document
And I am on "Files" tab
And I have focus on <Project> project
And I have focus on "CustomAttributes" folder
And I upload multiple documents for "Dynamic" link operation
And I have "LinkTest" document in document listing to link
#Inter Project Link
And I select any document to link
And click on "Link File(s)" from more options
Then "Target Folder" popup should open
When I select link destination "Conditional link" folder "inter" project And click on "Select" button
Then "Link File(s)" popup should open
When I select dynamic link condition "=" for status "For Review"
And I select dynamic link condition "≠" for status "For Training"
When I select <DistributionUser1> and <DistributionUser2> Users in "To" field And select "Dynamic" type And Click on "Submit" button
Then Link should be successfully created
When I upload revisions of the source link documents with external attachments "true"
Then "Conditional" Link document should be available in target folder "inter" project
And Documents matching with condition should get "attributes" updated "inter" project
And Documents not matching with condition should not get "attributes" updated "inter" project
Given I deactivate cloned test project
Examples: 
|DC_Center|Project|Template|DistributionUser1|DistributionUser2|
|UK|AutomationTestProject|AutomationTestProject_Template|RFI Bloggs|RFI Builder|
#|US|Automatic_Test_US_WS|Automatic_Test_US_Template|RFI Bloggs|RFI Builder|
#|AUS|Automatic_Test_AUS_WS|Automatic_Test_AUS_Template|RFI Bloggs|RFI Builder|