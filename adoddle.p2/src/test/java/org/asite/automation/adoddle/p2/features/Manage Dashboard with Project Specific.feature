Feature: Manage Dashboard with Project Specific

@P2T7
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am logged in using <UserA> user and set Default Dashboard as "System Dashboard"
When I logOut and re-login for default Dashboard using <UserB> in Adoddle
And I set "System Dashboard" as default Dashboard
When I logOut and re-login for default Dashboard using <UserC> in Adoddle
And I set "System Dashboard" as default Dashboard
Examples: 
|DC_Center|UserA|UserB|UserC|
|UK|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|
#|US|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|
#|AUS|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|

@P2T7
Scenario Outline: Create Edit Customised Dashboard And Add Remove Gadgets with Project Specific
Given Project DC <DC_Center> is available
And I am already logged in
# Create, Share and Verify Dashboard with All Gadgets & "View" rights at Project Level #
When I click on "Tools" dropdown Button and select "Create Dashboard"
Then "Create Dashboard" popup should open
When I entered Dashboard "Title" and "Description" for create "Dashboard"
And I shared dashboard with <Shared_Project> project to other users
And I set Project as "View" access rights
And I click on "Create" Button of popup
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then Created "Dashboard" page should set
When I add "some" gadget in Created Dashboard
Then all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserC> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
# Set "Admin" rights at Project Level with "View" rights at User Level #
When I login using loggedIn User
And I click on "Tools" dropdown Button and select "Manage Dashboards"
When I Edit created Dashboard
Then "Edit Dashboard" popup should open
When I set Project as "Admin" access rights
And I click on "Update" Button of popup
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
When I Edit created Dashboard
And I shared dashboard with Project to <UserC> users
And I set "Admin" for Project AND "View" for User access rights
And I click on "Update" Button of popup
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "displayed" on Dashboard page
When I add "any One" gadget in Created Dashboard
And I remove "any One" gadget from Created Dashboard
Then all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
When I add "any One" gadget in Created Dashboard
And I remove "any One" gadget from Created Dashboard
Then all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserC> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
# Set "View" rights at Project Level with "Admin" rights at User Level #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
When I Edit created Dashboard
And I set "View" for Project AND "Admin" for User access rights
And I click on "Update" Button of popup
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserC> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
When I add "any One" gadget in Created Dashboard
And I remove "any One" gadget from Created Dashboard
Then all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then all Gadgets should listed on Created Dashboard
# Set Dashboard Default and Verify in Same and Other Users #
When I click on "Tools" dropdown Button and select "Manage Dashboards"
And I set created Dashboard as "default Dashboard"
And I am on "Dashboard" tab
Then created Dashboard set as "default Dashboard"
And all Gadgets should listed on Created Dashboard
When I logOut and re-login for default Dashboard using <UserB> in Adoddle
Then created Dashboard should not set as "default Dashboard" for Other Users
When I logOut and re-login for default Dashboard using <UserC> in Adoddle
Then created Dashboard should not set as "default Dashboard" for Other Users
# Remove Dashboard and Verify #
When I login using loggedIn User
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I delete "created" Dashboard
And I logOut and re-login using <UserA> Adoddle Form "AccessUser"
Then "System Dashboard" should set as default Dashboard
When I logOut and re-login for default Dashboard using <UserB> in Adoddle
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then created "Dashboard" should not displayed on "Manage Dashboards" page
When I logOut and re-login for default Dashboard using <UserC> in Adoddle
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then created "Dashboard" should not displayed on "Manage Dashboards" page
Examples: 
|DC_Center|Shared_Project|UserA|UserB|UserC|
|UK|Dashboard_Test_UK_Project|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|
#|US|Dashboard_Test_US_Project|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|
#|AUS|Dashboard_Test_AUS_Project|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|

@exclude
Scenario Outline: Create Edit Customised Dashboard And Add Remove Gadgets with Project Specific
Given Project DC <DC_Center> is available
And I am already logged in using <UserA> user
# Create, Share and Verify Dashboard with All Gadgets #
When I click on "Tools" dropdown Button and select "Create Dashboard"
Then "Create Dashboard" popup should open
When I entered Dashboard "Title" and "Description" for create "Dashboard"
And I shared dashboard with <Shared_Project> project to other users
And I click on "Create" Button of popup
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then Created "Dashboard" page should set
When I add "some" gadget in Created Dashboard
Then all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then Created "Dashboard" page should set
And all Gadgets should listed on Created Dashboard
# Set "View" rights at Project Level #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
When I Edit created Dashboard
Then "Edit Dashboard" popup should open
When I set Project as "View" access rights
And I click on "Update" Button of popup
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
# Set "Admin" rights at Project Level #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I Edit created Dashboard
And I set Project as "Admin" access rights
And I click on "Update" Button of popup
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "displayed" on Dashboard page
When I add "any One" gadget in Created Dashboard
And I remove "any One" gadget from Created Dashboard
Then all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
When I add "any One" gadget in Created Dashboard
And I remove "any One" gadget from Created Dashboard
And all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then all Gadgets should listed on Created Dashboard
# Set Project level "Admin" amd User level "View" rights #
When I click on "Tools" dropdown Button and select "Manage Dashboards"
And I Edit created Dashboard
And I shared dashboard with Project to <UserB> users
And I set "Admin" for Project AND "View" for User access rights
And I click on "Update" Button of popup
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "displayed" on Dashboard page
When I add "any One" gadget in Created Dashboard
And I remove "any One" gadget from Created Dashboard
And all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "not displayed" on Dashboard page
And all Gadgets should listed on Created Dashboard
# Set Project level "View" amd User level "Admin" rights #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I Edit created Dashboard
And I set "View" for Project AND "Admin" for User access rights
And I click on "Update" Button of popup
When I logOut and re-login using <UserB> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then "Add Gadget" Button should "displayed" on Dashboard page
When I add "any One" gadget in Created Dashboard
And I remove "any One" gadget from Created Dashboard
And all Gadgets should listed on Created Dashboard
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on Created Dashboard of "Manage Dashboards" page
Then all Gadgets should listed on Created Dashboard
# Set Dashboard Default and Verify in Same and Other Users #
When I click on "Tools" dropdown Button and select "Manage Dashboards"
And I set created Dashboard as "default Dashboard"
And I am on "Dashboard" tab
Then created Dashboard set as "default Dashboard"
And all Gadgets should listed on Created Dashboard
When I logOut and re-login for default Dashboard using <UserB> in Adoddle
Then created Dashboard should not set as "default Dashboard" for Other Users
# Remove Dashboard and Verify #
When I logOut and re-login for default Dashboard using <UserA> in Adoddle
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I delete "created" Dashboard
And I logOut and re-login using <UserA> Adoddle Form "AccessUser"
Then "System Dashboard" should set as default Dashboard
When I logOut and re-login for default Dashboard using <UserB> in Adoddle
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then created "Dashboard" should not displayed on "Manage Dashboards" page
Examples: 
|DC_Center|Shared_Project|UserA|UserB|
|UK|Dashboard_Test_UK_Project|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|
#|US|Dashboard_Test_US_Project|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|
#|AUS|Dashboard_Test_AUS_Project|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|

@P2T7
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am logged in using <UserA> user and set Default Dashboard as "System Dashboard"
When I logOut and re-login for default Dashboard using <UserB> in Adoddle
And I set "System Dashboard" as default Dashboard
When I logOut and re-login for default Dashboard using <UserC> in Adoddle
And I set "System Dashboard" as default Dashboard
Examples: 
|DC_Center|UserA|UserB|UserC|
|UK|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|
#|US|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|
#|AUS|auto.nfpw_user1@atest.com|auto.nfpw_user2@atest.com|auto.nfpw_user3@atest.com|