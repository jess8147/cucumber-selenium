Feature: Review Draft Distribution
	
@P1T1
Scenario Outline: Review Draft Distribution
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Project Forms tab
And I have focus on <Project> project
And I have focus on form template "Communications"
When I select any form and open it
Then Form should be viewed in new window
When I distribute selected form to <Secondary User>
And I switch to <Secondary User> user
Then Form should be available to <Secondary User> 
When I edit form AND assign "Review Draft" action to user <PA_Builder_ID>, <B> and <C>
And I switch to <A> user
Then "Review Draft" action should be assigned to user <A>
When I switch to <B> user
Then "Review Draft" action should be assigned to user <B>
When I switch to <C> user
Then "Review Draft" action should be assigned to user <C>
When I switch to <A> user
And I perform review draft and assign "Review Draft" action to user <D>
Then "Review Draft" actions should get cleared
When I switch to <D> user
Then "Review Draft" action should be assigned to user <D>
When User <D> creates form from draft distribution
And I switch to <B> user
Then "Review Draft" actions should get cleared
When I switch to <C> user
Then "Review Draft" actions should get cleared
When I switch to <A> user
Then "For Information" action should be assigned to user <A>
When I switch to <Primary User> user
Then "For Information" action should be assigned to user <Primary User>
Examples: 
|DC_Center|Project|Primary User|Secondary User|A|B|C|D|PA_Builder_ID|
|UK|AutomationTestProject|Automation Primary|Automation UK|PA Builder|RFI Builder|TC Bloggs|RFI Bloggs|pa_tctestorg2@tctestorg2.com|
#|US|Automatic_Test_US_WS|Automation Primary|Automation US|PA Builder|RFI Builder|TC Bloggs|RFI Bloggs|pa_tctestorg2@tctestorg2.com|
#|AUS|Automatic_Test_AUS_WS|Automation Primary|Automation AUS|PA Builder|RFI Builder|TC Bloggs|RFI Bloggs|pa_tctestorg2@tctestorg2.com|