Feature: PublishDocumentWithCutomAttributesFiles via Applet-Classic
	
Background:
Given Script is Node specific

@P1T1
Scenario Outline: Upload files with Applet upload With distribution
Given Project DC <DC_Center> is available
Given Script is "IE" browser specific
And I am on Workspace home page of workspace <Project>
And I have focus on "CustomAttributes" folder in <Project> Project
When I click on Public Standard Dcoument
Then "Publish Documents - Publish" page should be opened
When I click on Click here to use the Advanced Upload
Then Applet upload popup should open
When I click on Add Files button on Applet upload popup
Then Local file system popup should open
When I have selected multiple files from local system AND I have clicked on Enter Document Details button
Then Attributes should be load for all uploading documents
When I click on Copy FileName button of Applet Upload
Then all documents File Name Without extension should be copied in Doc Title 
When I enter <Values> into header for <Attributes> AND Select Overwrite
And click on Apply To All button of Applet Upload
Then all attributes values should be filled with Values AND "Doc Ref" should be filled with combine custom attributes
When I select "Do Not Distribute" in Distribute dropdown of Applet Upload
And click on Start Upload button
Then Uploaded document should be available in document listing
Examples: 
|DC_Center|Project|
|UK|Applet_Upload_Classic_UK_WS|
#|US|Applet_Upload_Classic_US_WS|
#|AUS|Applet_Upload_Classic_AUS_WS|

@exclude
Scenario Outline: Upload revision files with Applet upload With distribution
Given I am already logged in 
And I am on Classic View
And I have focus on "CustomAttributes" folder in "AutomationTestProject" Project 
And I have Public Standard Dcoument option enabled
When I click on Public Standard Dcoument
Then "Publish Documents - Publish" popup should open
When I click on Click here to use the Advanced Upload
Then Applet upload popup should open
When I click on Add Files button on Applet upload popup
Then Local file system popup should open
When I have selected multiple files from local system which are already uploaded AND I have clicked on Enter Document Details button
Then Attributes should be load for all uploading documents AND Attributes should be auto filled by previous version values
When I enter "2" into header for "Rev"
And click on Apply To All button
Then "Rev" attribute value should be filled by "2" for all documents
When I click on Document Distribution icon
Then Distribution popup should open
When I select current user from Select Individuals 
And click on Add to distributionlist button
Then Selected user should be available in distribution list
When I select "For Comment" in Action Required dropdown
And enter SYSDATE in Action Due date
And click on Distribut button
Then I should redirected on Apple upload popup AND current user should be available in distribution list 
When I click on Start Uplaod button
Then All Files should be uploaded successfully AND I should redirected on Document listing
And Uploaded document should be available in document listing
Examples: 
|DC_Center|Project|
|UK|Applet_Upload_Classic_UK_WS|
#|US|Applet_Upload_Classic_US_WS|
#|AUS|Applet_Upload_Classic_AUS_WS|