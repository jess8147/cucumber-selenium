Feature: Download XREF Parent File

@P2T2
Scenario Outline: Download Parent File
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I search document with XRef having title "Automation_Parent_XREF"
Then XRef document should get filtered with blue icon
When I assign "For Information" action to current user
And I click on blue icon of file with XRef
And I download the Xref file as "dwg" file
Then Parent file should get downloaded
And Downloaded file size should match with actual file size
And "For Information" action should get cleared
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|