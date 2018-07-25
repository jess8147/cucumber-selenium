Feature: Review Draft AutoSave Distribution
	
@P2T4
Scenario Outline: Review Draft AutoSave - Save Draft
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
And I have focus on <Project> project
When I select any form and open it
Then Form should be viewed in new window
When I distribute selected form to <Secondary User>
And I switch to <Secondary User> user
Then Form should be available to <Secondary User> 
When I edit form AND assign "Review Draft" action to user <A>, <B> and <C> using AutoSave
And I click on "Cancel" button and close current window
And I search and re-open above form and select "Edit Draft"
Then Draft should have been auto saved
And "Discard Draft" button should be visible
When I click on "Cancel" button and switch to previous window 
And I switch to <A> user
Then "Review Draft" action should not be assigned to user <A>
When I switch to <B> user
Then "Review Draft" action should not be assigned to user <B>
When I switch to <C> user
Then "Review Draft" action should not be assigned to user <C>
Examples: 
|DC_Center|Project|Primary User|Secondary User|A|B|C|D|
|UK|AutomationTestProject|Jasmin Prajapati|Automation UK|PA Builder|RFI Builder|TC Bloggs|RFI Bloggs|
#|US|Automatic_Test_US_WS|Vishal Modi|Automation US|PA Builder|RFI Builder|TC Bloggs|RFI Bloggs|
#|AUS|Automatic_Test_AUS_WS|Vishal Modi|Automation AUS|PA Builder|RFI Builder|TC Bloggs|RFI Bloggs|