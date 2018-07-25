Feature: Link Document - Dynamic Link

@P1T2
Scenario Outline: Create Dynamic Link
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Files tab
And I have atleast two folders for "Dynamic" link in project
And I have focus on "CustomAttributes" folder
And I have "Dynamic" link document in document listing to link
When I select any document to link
And click on "Link File(s)" from more options
Then "Target Folder" popup should open
When I select "Dynamic" link destination Folder And click on "Select" button
Then "Link File(s)" popup should open
When I select User in "To" field And select "Dynamic" type And Click on "Submit" button
Then Link should be successfully created
And "Dynamic" Link document should be available in destination folder
And For Information action should assign to selected User
And I deactivate test folder
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P1T2
Scenario Outline: Verify Dynamic Link Revision
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Files tab
And I have focus on "CustomAttributes" folder
When I upload revision of document
Then Revision for document should get reflected in linked folder
And Secondary file attachment should get updated 
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|