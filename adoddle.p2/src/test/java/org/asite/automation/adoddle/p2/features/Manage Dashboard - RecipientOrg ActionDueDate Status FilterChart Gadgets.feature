Feature: Manage Dashboard - Recipient Organisation, Task Due Date, Status FilterChart Gadgets

@P2T4
Scenario Outline: Create and Share Dashboard with "Recipient Organisation", "Task Due Date" AND "Status" filters FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in using <PrimaryUser> user
And I am on "Project Forms" tab
When I create "Status" filter with "Open" type And "Recipient Org" filter And "Task Due Date" filter within 2 week And save with filtername "FormRecipientFilter"
And I am on "Dashboard" tab
When I click on "Tools" dropdown Button and select "Manage Dashboards"
And I click on "Create Dashboard" plus Icon
Then "Create Dashboard" popup should open
When I entered Dashboard "Title" and "Description" for create "Dashboard"
And I shared dashboard to user <UserA> with filter
And I click on "Create" Button of popup
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then Created "Dashboard" page should set
When I click on "Click here" to add gadgets to your dashboard hyperlink
Then "Add Gadget" popup should open
When I add "Filter Chart" Gadget and Save as "DashboardFormRecipientFilterGadget" to Dashboard
Then "DashboardFormRecipientFilterGadget" Gadget should added Successfully on Dashboard
When I select "FormRecipientFilter" into Filter dropdown And "Recipient Org" into Statistics Type dropdown And select "Show Legends" checkbox of gadget
And I click on "Update" button of gadget
##  'PrimaryUser' Verification  ##
Then "highcharts-tracker" should displayed on Filter gadgets
And I get and verify "Status" filter with "Open" selected type And "Recipient Org" filter and filtertype on Dashboard with "Project Forms" tab And "Task Due Date" filter within 2 week And set saved "FormRecipientFilter" filter
##  'UserA' Verification  ##
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then "DashboardFormRecipientFilterGadget" Gadget should displayed on Dashboard
And "highcharts-tracker" should displayed on Filter gadgets
And I verify "highcharts-tracker" hide and show using "legend" click
And I get and verify "Status" filter with "Open" selected type And "Recipient Org" filter and filtertype on Dashboard with "Project Forms" tab And "Task Due Date" filter within 2 week And set saved "FormRecipientFilter" filter
##  Delete Dashboard  ##
When I logOut and re-login using <PrimaryUser> Adoddle Form "AccessUser"
And I click on "Tools" dropdown Button and select "Manage Dashboards"
And I delete created customized Dashboard
Then created "Dashboard" should not displayed on "Manage Dashboards" page
#  'UserA'  #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then created "Dashboard" should not displayed on "Manage Dashboards" page
When I am on "Project Forms" tab
Then "FormRecipientFilter" should "displayed" on Saved Filter list
##  Delete Dashboard Filter  ##
When I logOut and re-login using <PrimaryUser> Adoddle Form "AccessUser"
When I am on "Project Forms" tab
And I delete "FormRecipientFilter" Saved Filter
Then "FormRecipientFilter" should "not displayed" on Saved Filter list
#  'UserA'  #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I am on "Project Forms" tab
Then "FormRecipientFilter" should "not displayed" on Saved Filter list
Examples: 
|DC_Center|Project_Name|PrimaryUser|UserA|
|UK|AutomationTestProject|auto.dfg_uk2@atest.com|auto.nfpw_user1@atest.com|
#|US|Automatic_Test_US_WS|auto.dfg_us2@atest.com|auto.nfpw_user1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.dfg_aus2@atest.com|auto.nfpw_user1@atest.com|