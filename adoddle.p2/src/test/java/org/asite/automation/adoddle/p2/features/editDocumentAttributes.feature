Feature: Edit Document Attributes

@P2T4
Scenario Outline: Edit Custom and Standard attributes
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
And I have created new test folder "AutomationTestFolder" for <Project>
When I upload "4" documents in "Existing" folder
And I upload "3" documents in "New" folder
And I upload "3" documents in "CustomAttributes" folder
And I search all the documents uploaded at project level
Then "10" documents should be displayed on listing
When I select all document and right click on them
And I click on "Edit Attributes" option in context options
Then "Edit Attributes" popup should open
When I edit attributes of uploaded custom attributes specific files
And I edit attributes of uploaded simple attributes specific files
Then DocRef and custom attributes of custom attributes specific files should be edited
And Attributes of simple attributes specific files should be edited
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@P2T4
Scenario Outline: Clean up testdata
Given Project DC <DC_Center> is available
And I am already logged in
And I deactivate the edit attributes folder for <Project>
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|