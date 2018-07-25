Feature: Download XREF Child Files

@P2T2
Scenario Outline: Download Parent File with Childs
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I search document with XRef having title "Automation_Parent_XREF"
Then XRef document should get filtered with blue icon
When I click on the XRef file docref
Then XRef file should open in new window
When I click on "Associations" on LH Panel
And I get list of all the files associated and close the window
And I select the Xref file by clicking on file checkbox
And I click on Download button on file listing
Then "Download Files" popup should open
When I select "Include XREF" checkbox "Yes" and click on Download button
And I download the Xref file as "zip" file
And I unzip all the Xref files from zip file
Then All files should be available at download location with correct file sizes
And I select the Xref file by clicking on file checkbox
And I click on Download button on file listing
Then "Download Files" popup should open
When I select "Include XREF" checkbox "No" and click on Download button
And I download the Xref file as "dwg" file
Then Parent file should get downloaded
Examples:
|DC_Center|
|UK|
#|US|
#|AUS|