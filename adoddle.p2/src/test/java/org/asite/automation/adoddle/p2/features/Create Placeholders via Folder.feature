Feature: Create Placeholders via Folder - Right Click

@P2T5
Scenario Outline: Create Placeholders via Folder right click
Given Project DC <DC_Center> is available
And I am already logged in
And I drag mouse on "Incomplete Actions" and "Due Today" count of Dashboard
Given I am on "Files" tab
And I drag mouse on "Incomplete Actions" and "Due Today" count of Files tab
And I have focus on <Project_Name> projects "PlaceHolders" folder
And I right click on "PlaceHolders" folder And drag mouse to "New" And click on "Placeholder" into context menu options
Then "Create Placeholder" popup should open for publishing Placeholders
When I select Distribution User for Distribute "Placeholder" And "For Publishing" action With Current Date
And I click on "Add Placeholders" And I entered all mendatory attributes for Creating both Placeholders
And I get all attributes values of first Placeholder
And I click on "Assign Placeholder task" button for for Creating Placeholder
Then Placeholders Created Successfully and should be listed on Files listing page And "For Publishing" action should be displayed AND all attributes of Placeholders should be listed on Created Placeholders
When I am on "Dashboard" tab
Then Total number of "Incomplete Actions" and "Due Today" count of Dashboard should be increased
Given I am on "Files" tab
Then Total number of "Incomplete Actions" and "Due Today" count of Files tab should be increased
When I have focus on <Project_Name> projects "PlaceHolders" folder
And I search Created First Placeholder and click on "For Publishing" action type
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
Then Only Status dropdown value should be blanked
And All Attributes should be match to previous created Placeholder
When I select Status into Status dropdown list
And I attach external file in created Placeholder
And I select Distribution User for Distribute "File" And "For Information" action With Current Date
And I get all attributes values of File
And I click on Upload button
Then File should be uploaded successfully into Created Placeholder And "For Publishing" action should be completed
And All Attributes should be listed on Files tab listing page
And All Attributes of Placeholder should be set into Uploaded file And "Status" value should be changed And File Name should be set
And after click on attachment attached file should viewed in viewer
When I am on "Dashboard" tab
Then Total number of "Incomplete Actions" and "Due Today" count of Dashboard should be decreased
Given I am on "Files" tab
Then Total number of "Incomplete Actions" and "Due Today" count of Files tab should be decreased
When I have focus on <Project_Name> projects "PlaceHolders" folder
And I right click on "Non-PlaceHolder" folder And drag mouse to "New" of context click
Then create "Placeholder" option should be "disabled" on context menu options
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|