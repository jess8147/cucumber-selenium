Feature: Manage Dashboard - Various Permutations

@P2T7
Scenario Outline: Create Edit and Customised Dashboard with Add Remove Gadgets And Clone Dashboard
Given Project DC <DC_Center> is available
And I am logged in using <User_ID> user and set Default Dashboard as "System Dashboard"
When I click on "Tools" dropdown Button and select "Create Dashboard"
Then "Create Dashboard" popup should open
When I entered Dashboard "Title" and "Description" for create "Dashboard"
And I click on "Create" Button of popup
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on "Edit Dashboard" of created Dashboard
Then "Edit Dashboard" popup should open
When I edit Dashboard "Title" and "Description" for edit "Dashboard"
And I click on "Update" Button of popup
Then Updated Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then Created "Dashboard" page should set
When I click on "Click here" to add gadgets to your dashboard hyperlink
Then "Add Gadget" popup should open
When I add all Gadgets of Dashboard
Then all Gadgets should listed on Created Dashboard
When I click on "Tools" dropdown Button and select "Manage Dashboards"
And I set created Dashboard as "default Dashboard"
And I am on "Dashboard" tab
Then created Dashboard set as "default Dashboard"
And added Gadgets should displayed as previously on Created Dashboard
Given I am on "Files" tab
And I am on "Dashboard" tab
Then created Dashboard set as "default Dashboard"
And added Gadgets should displayed as previously on Created Dashboard
When I logOut and re-login for default Dashboard using <User_ID> in Adoddle
Then created Dashboard set as "default Dashboard"
And added Gadgets should displayed as previously on Created Dashboard
When I click on "Tools" dropdown Button and select "Clone Dashboard"
Then "Clone Dashboard" popup should open
When I entered Dashboard "Title" and "Description" and select existing Dashboard in dropdown for "Cloned Dashboard"
And I click on "Create" Button of popup
Then Created "Cloned Dashboard" page should set
And Created Dashboard all Gadgets should displayed in Created Clone Dashboard
When I click on "Tools" dropdown Button and select "Manage Dashboards"
And I delete "created" Dashboard
And I logOut and re-login using <User_ID> Adoddle Form "AccessUser"
Then "System Dashboard" should set as default Dashboard
When I click on "Tools" dropdown Button and select "Manage Dashboards"
And I delete "created Cloned" Dashboard
Then created "Cloned Dashboard" should not displayed on "Manage Dashboards" page
Examples: 
|DC_Center|User_ID|
|UK|auto.testuser1@atest.com|
#|US|auto.testuser2@atest.com|
#|AUS|auto.testuser3@atest.com|