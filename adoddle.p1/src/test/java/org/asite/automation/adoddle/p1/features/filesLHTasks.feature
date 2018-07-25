Feature: Files LH Actions

@exclude
Scenario Outline: Files Incomplete Actions
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab
And I have atleast one "Incomplete Actions" on Files tab
When I drag mouse on "Incomplete Actions" count on Files tab
Then Total number of "Incomplete Actions" should display on mouse over on Files tab
When I click on "Incomplete Actions" on Files tab
Then My all "Incomplete Actions" documents should be available in Files listing
And "Action Status: Incomplete" filter should applied
And "Revisions: Current Set, Superseded" filter should applied
And "Recipient Name: Current User" filter should applied
When I perform on any "Incomplete Actions" from Files listing
Then Total number of "Incomplete Actions" action count should be decrease by one
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@exclude
Scenario Outline: Files Overdue Actions
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab
And I have atleast one "Overdue Actions" on Files tab
When I drag mouse on "Overdue Actions" count on Files tab
Then Total number of "Overdue Actions" should display on mouse over on Files tab
When I click on "Overdue Actions" on Files tab
Then My all "Overdue Actions" documents should be available in Files listing
And "Action Status: Incomplete and Overdue" filter should applied
And "Revisions: Current Set, Superseded" filter should applied
And "Recipient Name: Current User" filter should applied
When I perform on any "Overdue Actions" from Files listing
Then Total number of "Overdue Actions" action count should be decrease by one
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@exclude
Scenario Outline: Files Due Today Actions
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab
And I have atleast one "Due Today" on Files tab
When I drag mouse on "Due Today" count on Files tab
Then Total number of "Due Today" should display on mouse over on Files tab
When I click on "Due Today" on Files tab
Then My all "Due Today" documents should be available in Files listing
And "Action Status: Incomplete" filter should applied
And "Revisions: Current Set, Superseded" filter should applied
And "Recipient Name: Current User" filter should applied
And "Action due date: Today" filter should applied
When I perform on any "Due Today" from Files listing
Then Total number of "Due Today" action count should be decrease by one
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|