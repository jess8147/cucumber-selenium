Feature: Applet Upload With Custom Attribute

Background:
Given Script is Node specific

@P1T3
Scenario Outline: Applet upload with Custom Attributes with Distribution
Given Project DC <DC_Center> is available
And Script is "IE" browser specific
And I am already logged in 
And I am on "Files" tab
And I have focus on "CustomAttributes" folder in <Project> Project 
And I have Add Files button enabled
When I click on Add Files button
Then "Upload" popup should open
When I click on Click here for Applet Upload link
Then Applet upload popup should open
When I click on Add Files button on Applet upload popup
Then Local file system popup should open
When I have selected multiple files from local system AND I have clicked on Enter Document Details button
Then Attributes should be load for all uploading documents
When I click on Copy FileName button
Then all documents File Name Without extension should be copied in Doc Title 
When I enter <Values> into header for <Attributes> AND Select Overwrite
And click on Apply To All button
Then all attributes values should be filled with Values AND "Doc Ref" should be filled with combine custom attributes
When I select "Distribute" in Distribute dropdown
And click on Start Upload button
Then Uploaded document should be available in document listing
Examples: 
|DC_Center|Project|	
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|