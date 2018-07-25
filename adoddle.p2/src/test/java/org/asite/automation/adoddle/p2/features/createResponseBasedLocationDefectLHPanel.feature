Feature: Create Response Based Location Defect From LH Panel

@P2T7
Scenario Outline: Create Assign Resolve Verify - Location Defect
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Field" tab
And Field enabled project <Project> is available
When I click on existing Site <Site>
And I click on existing Location <Location>
And I get existing location defect counts
And I click on "New" option in LH panel
And I search "Defects" form  on "Create Form" window
Then "Defects" Form should get filtered on Create Form window
When I click on filtered "Defects" form
And I enter values in mandatory fields to create <Location> form
And I attach external files to Defect
And I click on "Send" button to create Defect
And I switch to default content window
Given I reload the page
And I am on "Field" tab
And Field enabled project <Project> is available
When I click on existing Site <Site>
And I click on existing Location <Location>
Then Defect count should increase for "Open" defect for "Location"
And Created defects should be available in listing
And Status "Open" should be reflected in Listing with "Red" color
When I switch to <Contractor> user
Given I am on "Field" tab
And Field enabled project <Project> is available
When I click on existing Site <Site>
And I click on existing Location <Location>
Then Created defects should be available in listing
And Defect should have action "Respond"
When I perform assigned action to defect form
And "Contractor" changes status to "Resolved"
Then Defect should have status as "Resolved" on listing
When I switch to <Inspector> user 
Given I am on "Field" tab
And Field enabled project <Project> is available
When I click on existing Site <Site>
And I click on existing Location <Location>
Then Defect count should increase for "Resolved" defect for "Location"
And Created defects should be available in listing
And Status "Resolved" should be reflected in Listing with "Blue" color
And Defect should have action "Respond"
When I perform assigned action to defect form
And "Inspector" changes status to "Verified"
Then Defect should have status as "Verified" on listing
Given I am on "Field" tab
And Field enabled project <Project> is available
And I click on existing Site <Site>
And I click on existing Location <Location>
Then Defect count should increase for "Verified" defect for "Location"
And Created defects should be available in listing
And Status "Verified" should be reflected in Listing with "Green" color
Examples: 
|DC_Center|Project|Site|Location|Inspector|Contractor|
|UK|Automation_FieldEnabled_UK_WS|LHL_Defect_Site|LHL_Defect_Location|Auto Test|RFI Builder|
#|US|Automation_FieldEnabled_US_WS|LHL_Defect_Site|LHL_Defect_Location|Auto Test|RFI Builder|
#|AUS|Automation_FieldEnabled_AUS_WS|LHL_Defect_Site|LHL_Defect_Location|Auto Test|RFI Builder|