Feature: Simple Upload Classic

@P1T2
Scenario Outline: Simple Upload file
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
And I click "All Workspace Documents"
Then I should redirect on "All Workspace Documents" Listing
When I select "AutomationUploadFiles" folder into "All Workspace Documents" Listing
And click on "Upload standard Document" img
Then "Publish Documents" window should open for upload
When I click on "Add Files" AND I have selected more then one Files from Local for "Simple Upload"
And I click on Start Upload button for Simple upload
And I click on OK button on upload summary popup
Then All Documents should be uploaded successfully AND I should redirected on Document listing page
And Uploaded documents should be available into document listing page
Examples: 
|DC_Center|Project|
|UK|Simple_Upload_Classic_UK_WS|
#|US|Simple_Upload_Classic_US_WS|
#|AUS|Simple_Upload_Classic_AUS_WS|