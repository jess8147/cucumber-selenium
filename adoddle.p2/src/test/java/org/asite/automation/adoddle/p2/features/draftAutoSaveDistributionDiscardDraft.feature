Feature: Review Draft AutoSave Distribution - Discard Draft
	
@P2T4
Scenario Outline: Review Draft AutoSave - Discard Draft
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
When I click on "Discard Draft" button to discard the form
Then Auto saved Draft should get discarded
And Reply to form should get discarded
Examples: 
|DC_Center|Project|Primary User|Secondary User|A|B|C|
|UK|AutomationTestProject|Jasmin Prajapati|Automation UK|PA Builder|RFI Builder|TC Bloggs|
#|US|Automatic_Test_US_WS|Vishal Modi|Automation US|PA Builder|RFI Builder|TC Bloggs|
#|AUS|Automatic_Test_AUS_WS|Vishal Modi|Automation AUS|PA Builder|RFI Builder|TC Bloggs|