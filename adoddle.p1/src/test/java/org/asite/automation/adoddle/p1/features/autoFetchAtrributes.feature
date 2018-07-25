Feature: Auto Fetch Attributes

@P1T1
Scenario Outline: Auto Fetch Attributes while file upload
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Admin" tab
When I click on "Auto-Fetch Attributes" box on Admin tab
Then "Auto-Fetch Attributes" page should be displayed
When I click on Edit icon of existing rule
Then "Edit Auto-Fetch Rule" popup should open
When I edit existing rule title
Then Rule should get saved successfully
When I get valid file attributes with dropdown "Code"
And I create file with conjuction of all attribute values
Given I am on "Files" tab
And I have focus on <Project> project
And I have focus on "AutomationUploadFiles" folder
And I have focus on "Automation_Fetch_Attributes_Folder" folder
When I upload file with conjuction of all attribute values
Then Attributes should get auto fetched in upload popup "Yes"
When I get valid file attributes with dropdown "Value"
And I create file with conjuction of all attribute values
When I upload file with conjuction of all attribute values
Then Attributes should get auto fetched in upload popup "Yes"
When I get invalid file attributes with dropdown "Code"
And I create file with conjuction of all attribute values
When I upload file with conjuction of all attribute values
Then Attributes should get auto fetched in upload popup "No"
When I get invalid file attributes with dropdown "Value"
And I create file with conjuction of all attribute values
When I upload file with conjuction of all attribute values
Then Attributes should get auto fetched in upload popup "No"
When I upload all above files all together
Then Respective attributes should be seen for each file
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|