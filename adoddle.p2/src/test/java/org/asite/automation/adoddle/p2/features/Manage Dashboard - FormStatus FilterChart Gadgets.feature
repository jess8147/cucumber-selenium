Feature: Manage Dashboard - Form Status FilterChart Gadgets

@P2T7
Scenario Outline: Create and Share Dashboard with "Form Status" FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in using <PrimaryUser> user
And I am on "Project Forms" tab
When I create "FormStatus" filter with any three "Status"
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
When I add "Filter Chart" Gadget and Save as "DashboardFormStatusFilterGadget" to Dashboard
Then "DashboardFormStatusFilterGadget" Gadget should added Successfully on Dashboard
When I select "FormStatus" into Filter dropdown And "Status" into Statistics Type dropdown And select "Show Legends" checkbox of gadget
And I click on "Update" button of gadget
##  'PrimaryUser' Verification  ##
Then "highcharts-tracker" should displayed on Filter gadgets
And created three all "Status" should displayed on paichart
##  'UserA' Verification  ##
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then "DashboardFormStatusFilterGadget" Gadget should displayed on Dashboard
And "highcharts-tracker" should displayed on Filter gadgets
When I set "Favourite Projects" in search Project filter
Then I get and Verify "Status" filter and filtertype and total count with Dashboard and "Project Forms" tab and set saved "FormStatus" filter
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
Then "FormStatus" should "displayed" on Saved Filter list
##  Delete Dashboard Filter  ##
When I logOut and re-login using <PrimaryUser> Adoddle Form "AccessUser"
When I am on "Project Forms" tab
And I delete "FormStatus" Saved Filter
Then "FormStatus" should "not displayed" on Saved Filter list
#  'UserA'  #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I am on "Project Forms" tab
Then "FormStatus" should "not displayed" on Saved Filter list
Examples: 
|DC_Center|Project_Name|PrimaryUser|UserA|
|UK|AutomationTestProject|auto.dfg_uk1@atest.com|auto.multi_dc_user@atest.com|
#|US|Automatic_Test_US_WS|auto.dfg_us1@atest.com|auto.multi_dc_user@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.dfg_aus1@atest.com|auto.multi_dc_user@atest.com|