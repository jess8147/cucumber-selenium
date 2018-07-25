Feature: Projects Tab - Last Form Activity

@P2T2
Scenario Outline: Verify "Last Form Activity" AND total "Apps" count on Project list view
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I get last uploaded form date from "Last Form Activity" coloum
And I get total Forms Count from "Apps" coloum
And I goto "Project Forms" tab
And I Sorted "Created Date" Column in "descending" order list
Then I verify last uploaded form date with "Last Form Activity" coloum date And all Forms Count
When I goto "Contracts" tab
And I Sorted "Created Date" Column in "descending" order list
Then I verify last uploaded form date with "Last Form Activity" coloum date And all Forms Count
When I goto "FM" tab
And I Sorted "Created Date" Column in "descending" order list
Then I verify last uploaded form date with "Last Form Activity" coloum date And all Forms Count
And I verify total Forms count with "Apps" coloum count
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|