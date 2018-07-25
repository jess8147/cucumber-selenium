Feature: Create Multi Documents Comment

@P2T1
Scenario Outline: Create Comment with Attachments and Associations
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
And I have atleast one document in document listing
When I Right click on multiple documents
And I select context menu option "New" and click on "Start a Discussion"
Then "New" popup should open on files page
Given I have opened "New" popup to create comment
And I have selected Users into "To*"
And I have entered "Title*"
And I have associated atleast one document
And I have associated atleast one discussion
And I have associated atleast one form
When I Click on "Submit" button
Then Comment should be successfully created
And "Multiple" Comment should be available in "Discussions" tab
Given I reload the page
Given I am on "Files" tab
And "Multiple" Comment should be available in Files detail
And Association should be successfully done with selected documents
And Form Association should be successfully done with selected forms
And Discussion Association should be successfully done with selected discussions
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|