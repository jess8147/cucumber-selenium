Feature: Create Comment

@P1T1
Scenario Outline: Create Comment with Attachments and Associations
Given Project DC <DC_Center> is available
Given I am already logged in
And I am already logged in And I am on Files tab And I have atleast one document in document listing
When I Right click on any document
And I select context menu option "New" and click on "Start a Discussion"
Then "New" popup should open on files page
Given I have opened "New" popup to create comment
And I have selected secondary User into "To*"
And I have entered "Title*"
And I have attached atleast one documents
And I have associated atleast one document
And I have associated atleast one discussion
And I have associated atleast one form
When I Click on "Submit" button
Then Comment should be successfully created
And Comment should be available in "aMessages" tab
And Comment should be available in Files detail
And Association should be successfully done with selected documents
And Form Association should be successfully done with selected forms
And Discussion Association should be successfully done with selected discussions
And Attached documents should be uploaded AND available in comment
Given I login using secondary user into application
And I am on "Files" tab
Then File should be available on listing with assigned action
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P1T1
Scenario Outline: Download and Validate Comment Attachments
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Discussions" tab
And I have Search "Created_Comment"
Then Comment Should open sucessfully AND I mouse hover association clip icon And I click "Attachments"
Then "Attachments & Associations" Popup should open
When I goto "Attachments" link I select all files AND click on Download
And I select all checkboxes AND click on download button of Download popup for "Attachments"
Then Batch file should be created And "Attachments" Zip file should be downloaded into Local Directory
When I Unzip downloaded "Attachments" zip file
Then all "Attachments" should be available into Local Directory
When I goto "Files" link I select all files AND click on Download
And I select all checkboxes AND click on download button of Download popup for "Files"
Then Batch file should be created And "Associations" Zip file should be downloaded into Local Directory
When I Unzip downloaded "Associations" zip file
Then all "Associations" should be available into Local Directory
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|