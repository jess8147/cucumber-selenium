Feature: Edit Document Attributes - Pattern vs Folder Specific Attributes

@P2T4
Scenario Outline: Folder Specific and Pattern Specific Attributes
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus on "CustomAttributes" folder
When I upload "4" documents in "Pattern Specific" folder
And I upload "4" documents in "Folder Specific" folder
And I search all the documents uploaded at project level
Then "8" documents should be displayed on listing
When I select all document and right click on them
And I click on "Edit Attributes" option in context options
Then "Edit Attributes" popup should open
When I edit attributes of uploaded custom attributes specific files
Then Document counter should change for "Pattern Specific" folder "Yes"
And Document counter should change for "Folder Specific" folder "No"
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|