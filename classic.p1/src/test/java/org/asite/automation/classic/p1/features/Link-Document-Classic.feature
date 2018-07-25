Feature: Link Document Dynamic Classic

@P1T2
Scenario Outline: Create Dynamic Link
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
When I click on "CustomAttributes" Folder for "Dynamic" link
Then Document listing of "CustomAttributes" folder should open
When I select "LinkDocumentWithCustomAttributesTestDataFile-Dynamic" file
And click on "Link Documents" from document dropdown options
Then "select location" window page should open for Link Document
When I select destination Folder
And I click on Create Link button
Then "confirm selection" window page should open for Link Document
When I select "Dynamic" link type from dropdown
And I click on any "Continue" button
Then "Distribute linked documents" window page should open for Link Document
When I select "Automation Testing Classic" company and "Automation Secondary" user from Select Individuals list
And I click on "Add to Distribution List" button
Then selected user should be added into list
When I select "For Information" action And Current Date for selected user
And I click on "Distribute" button
Then "document information" window page should open for Link Document
When I click on "Close this window" button
Then Link should be successfully created
And Link document should be available in destination folder
And selected action should assign to selected User
When I upload revision of "LinkDocumentWithCustomAttributesTestDataFile-Dynamic" in "CustomAttributes" folder
Then Revision for "LinkDocumentWithCustomAttributesTestDataFile-Dynamic" should get reflected in linked folder
And  I deactivate the testdata folder
Examples: 
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|