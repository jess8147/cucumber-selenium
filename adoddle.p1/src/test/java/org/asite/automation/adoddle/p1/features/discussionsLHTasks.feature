Feature: Discussion LH Tasks
	
@P1T1
Scenario Outline: Perform Action on Unread Discussions
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Discussions tab
And I have atleast one "Unread Discussions"
When I drag mouse on "Unread Discussions" count
Then Total number of action should display on mouse over
When I click on "Unread Discussions"
Then My all "Unread Discussions" documents should be available in Discussions listing 
And "Comment Status: Unread" filter should be applied for Action Status
And "Recipient: Current User" filter should be applied for Recipient Name
When I perform on any action from Discussions listing
Then Total number of action count should "Unread Discussions" be decrease by one
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|