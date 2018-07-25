Feature: Link Document - Static Link

@P1T2
Scenario Outline: Create Static Link
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Files tab
And I have atleast two folders for "Static" link in project
And I have focus on "CustomAttributes" folder
And I have "Static" link document in document listing to link
When I select any document to link
And click on "Link File(s)" from more options
Then "Target Folder" popup should open
When I select "Static" link destination Folder And click on "Select" button
Then "Link File(s)" popup should open
When I select User in "To" field And select "Static" type And Click on "Submit" button
Then Link should be successfully created
And "Static" Link document should be available in destination folder
And For Information action should assign to selected User
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|