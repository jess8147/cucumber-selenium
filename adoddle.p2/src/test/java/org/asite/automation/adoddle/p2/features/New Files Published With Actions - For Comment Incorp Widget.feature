Feature: New Files Published With Tasks - For Comment Incorp Widget

@P2T2
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts with "Tasks" AND "For Comment Incorp." Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I click on Project And select "CustomAttributes" folder And select "CustomAttributesMyActionsFolder" sub folder
And I perform Custom Attributes upload Operation using <User_ID> user for "For Comment Incorp." action type
And I click on Project And select "CustomAttributes" folder And select "CustomAttributesMyActionsFolder" sub folder
And I select single file for perform "For Comment Incorp." Action widget
And I search selected "For Comment Incorp." Action file And I get all Custom Attributes value of selected action file
And I right click and select context menu option "Tasks" AND I click on "For Comment Incorporation" widget
Then "For Comment Incorporation" popup should open
And selected files should displayed on "For Comment Incorporation" popup
When I select "complete Comment Incorporation Action" checkbox AND select estimated publication date for Action "For Comment Incorporation"
And I click on "Submit" link of popup
Then selected file should create Placeholder And "For Publishing" action assigned to Placeholder And only status value should set as "Prepublished"
And "For Comment Incorp." actions should completed for selected Actions documents
When I search selected Placeholder file and click on "For Publishing" action type
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
Then Only Status dropdown value should be blanked
And All Attributes value should match to selected file Attributes value
When I select Status for file upload And I get all attributes values of File upload
And I select <Secondary> User for Distribute File And "For Information" action With Current Date
And I click on Upload button
Then File should uploaded successfully in Placeholder And "For Publishing" action should completed
And all attributes values of uploaded File should verified with previous file attributes values
And I deactivate Action files
Examples: 
|DC_Center|User_ID|Secondary|
|UK|auto.nfpw_uk1@atest.com|Auto_FWidget UK2|
#|US|auto.nfpw_us1@atest.com|Auto_FWidget US2|
#|AUS|auto.nfpw_aus1@atest.com|Auto_FWidget AUS2|