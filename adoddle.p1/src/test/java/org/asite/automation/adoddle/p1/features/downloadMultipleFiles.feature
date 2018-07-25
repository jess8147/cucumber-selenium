Feature: Download Multiple Files

@P1T2
Scenario Outline: Download Multiple files
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
And I have atleast one document in document listing
When I click on file link
Then new tab should be opened
When I click on LH-panel in Discussions link
Then I get the all attached Files Name AND close the new opened tab
When I checked opened file checkbox AND click on Download button
Then "Download Files" popup should open
When I select all checkboxes AND click on download button of Download popup
Then Batch file should be created And Zip file should be downloaded into Local Directory
When I Unzip downloaded zip file
Then all attached files and Parent Document and attachment to parent document should be available into Local Directory
Examples:
|DC_Center|
|UK|
#|US|
#|AUS|