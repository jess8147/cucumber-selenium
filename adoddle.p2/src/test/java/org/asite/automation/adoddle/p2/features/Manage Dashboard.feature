Feature: Manage Dashboard

@exclude
Scenario Outline: Create Edit and Customised Dashboard with Add Remove Gadgets And Clone Dashboard
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
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


# "Filter-Chart" Gadget Scenarios

@exclude
Scenario Outline: Create and Share Dashboard with "Doc POI" FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
When I create "DocPOI" filter with any three "Purpose of Issue"
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
When I add "Filter Chart" Gadget and Save as "DashboardDocPOIFilterGadget" to Dashboard
Then "DashboardDocPOIFilterGadget" Gadget should added Successfully on Dashboard
When I select "DocPOI" into Filter dropdown And "Purpose of Issue" into Statistics Type dropdown And select "Show Legends" checkbox of gadget
And I click on "Update" button of gadget
##  'PrimaryUser' Verification  ##
Then "highcharts-tracker" should displayed on Filter gadgets
And created three all "Purpose of Issue" should displayed on paichart
##  'UserA' Verification  ##
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then "DashboardFormStatusFilterGadget" Gadget should displayed on Dashboard
And "highcharts-tracker" should displayed on Filter gadgets
When I set "Favourite Projects" in search Project filter
Then I get and Verify "Purpose of Issue" filter and filtertype and total count with Dashboard and "Files" tab and set saved "DocPOI" filter
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
When I am on "Files" tab
Then "DocPOI" should "displayed" on Saved Filter list
##  Delete Dashboard Filter  ##
When I logOut and re-login using <PrimaryUser> Adoddle Form "AccessUser"
When I am on "Files" tab
And I delete "DocPOI" Saved Filter
Then "DocPOI" should "not displayed" on Saved Filter list
#  'UserA'  #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I am on "Files" tab
Then "DocPOI" should "not displayed" on Saved Filter list
Examples: 
|DC_Center|Project_Name|PrimaryUser|UserA|
|UK|AutomationTestProject|auto_ukp2@atest.com|auto.multi_dc_user@atest.com|
#|US|Automatic_Test_US_WS|auto_usp2@atest.com|auto.multi_dc_user@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto_ausp2@atest.com|auto.multi_dc_user@atest.com|

@exclude
Scenario Outline: Create and Share Dashboard with "Form Status" FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in
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
|UK|AutomationTestProject|auto_ukp2@atest.com|auto.multi_dc_user@atest.com|
#|US|Automatic_Test_US_WS|auto_usp2@atest.com|auto.multi_dc_user@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto_ausp2@atest.com|auto.multi_dc_user@atest.com|

@exclude
Scenario Outline: Create and Share Dashboard with "Documents All POI" FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
When I create "DocAllPOI" filter for All "Purpose of Issue"
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
When I add "Filter Chart" Gadget and Save as "DashboardDocAllPOIFilterGadget" to Dashboard
Then "DashboardDocAllPOIFilterGadget" Gadget should added Successfully on Dashboard
When I select "DocAllPOI" into Filter dropdown And "Purpose of Issue" into Statistics Type dropdown And select "Show Legends" checkbox of gadget
And I click on "Update" button of gadget
##  'PrimaryUser' Verification  ##
Then "highcharts-tracker" should displayed on Filter gadgets
And I get and verify "Purpose of Issue" filter and Its all active types on Dashboard with "Files" tab AND selected saved "DocAllPOI" filter
##  'UserA' Verification  ##
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I set "Favourite Projects" in search Project filter
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then "DashboardDocAllPOIFilterGadget" Gadget should displayed on Dashboard
And "highcharts-tracker" should displayed on Filter gadgets
And I get and verify "Purpose of Issue" filter and Its all active types on Dashboard with "Files" tab AND selected saved "DocAllPOI" filter
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
When I am on "Files" tab
Then "DocAllPOI" should "displayed" on Saved Filter list
##  Delete Dashboard Filter  ##
When I logOut and re-login using <PrimaryUser> Adoddle Form "AccessUser"
When I am on "Files" tab
And I delete "DocAllPOI" Saved Filter
Then "DocAllPOI" should "not displayed" on Saved Filter list
#  'UserA'  #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I am on "Files" tab
Then "DocAllPOI" should "not displayed" on Saved Filter list
Examples: 
|DC_Center|Project_Name|PrimaryUser|UserA|
|UK|AutomationTestProject|auto_ukp2@atest.com|auto.multi_dc_user@atest.com|
#|US|Automatic_Test_US_WS|auto_usp2@atest.com|auto.multi_dc_user@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto_ausp2@atest.com|auto.multi_dc_user@atest.com|

####### If Required Then Need Remove This Script #######
@exclude
Scenario Outline: Create and Share Dashboard with "Forms All Status" FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
When I create filter "Status" with filtername "FormAllStatus" with sub filter "Active / Inactive"
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
When I add "Filter Chart" Gadget and Save as "DashboardFormAllStatusFilterGadget" to Dashboard
Then "DashboardFormAllStatusFilterGadget" Gadget should added Successfully on Dashboard
When I select "FormAllStatus" into Filter dropdown And "Status" into Statistics Type dropdown And select "Show Legends" checkbox of gadget
And I click on "Update" button of gadget
##  'PrimaryUser' Verification  ##
Then "highcharts-tracker" should displayed on Filter gadgets
And I get and verify "Status" filter and Its all active types on Dashboard with "Project Forms" tab AND selected saved "FormAllStatus" filter
##  'UserA' Verification  ##
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then "DashboardFormAllStatusFilterGadget" Gadget should displayed on Dashboard
And "highcharts-tracker" should displayed on Filter gadgets
## Added With Multi-Project Selection And Count ##
When I set "Favourite Projects" in search Project filter
And I get total counts of selected "Status"
And I set <Project_Name> project in search Project Filter
Then total counts of selected "Status" miss-match with Previous counts of "Status"
When I set "Favourite Projects" in search Project filter
And I get and verify "Status" filter and Its all active types on Dashboard with "Project Forms" tab AND selected saved "FormAllStatus" filter
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
Then "FormAllStatus" should "displayed" on Saved Filter list
##  Delete Dashboard Filter  ##
When I logOut and re-login using <PrimaryUser> Adoddle Form "AccessUser"
When I am on "Project Forms" tab
And I delete "FormAllStatus" Saved Filter
Then "FormAllStatus" should "not displayed" on Saved Filter list
#  'UserA'  #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I am on "Project Forms" tab
Then "FormAllStatus" should "not displayed" on Saved Filter list
Examples: 
|DC_Center|Project_Name|PrimaryUser|UserA|
|UK|AutomationTestProject|auto_ukp2@atest.com|auto.nfpw_user1@atest.com|
#|US|Automatic_Test_US_WS|auto_usp2@atest.com|auto.nfpw_user1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto_ausp2@atest.com|auto.nfpw_user1@atest.com|

@exclude
Scenario Outline: Create and Share Dashboard with "Recipient Organisation", "Action Due Date" AND "Status" filters FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
When I create "Status" filter with "Open" type And "Recipient Organisation" filter And "Action Due Date" filter within 2 week And save with filtername "FormRecipientFilter"
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
When I select "FormRecipientFilter" into Filter dropdown And "Recipient Organisation" into Statistics Type dropdown And select "Show Legends" checkbox of gadget
And I click on "Update" button of gadget
##  'PrimaryUser' Verification  ##
Then "highcharts-tracker" should displayed on Filter gadgets
And I get and verify "Status" filter with "Open" selected type And "Recipient Organisation" filter and filtertype on Dashboard with "Project Forms" tab And "Action Due Date" filter within 2 week And set saved "FormRecipientFilter" filter
##  'UserA' Verification  ##
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then "DashboardFormRecipientFilterGadget" Gadget should displayed on Dashboard
And "highcharts-tracker" should displayed on Filter gadgets
And I verify "highcharts-tracker" hide and show using "legend" click
And I get and verify "Status" filter with "Open" selected type And "Recipient Organisation" filter and filtertype on Dashboard with "Project Forms" tab And "Action Due Date" filter within 2 week And set saved "FormRecipientFilter" filter
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
|UK|AutomationTestProject|auto_ukp2@atest.com|auto.nfpw_user1@atest.com|
#|US|Automatic_Test_US_WS|auto_usp2@atest.com|auto.nfpw_user1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto_ausp2@atest.com|auto.nfpw_user1@atest.com|

@exclude
Scenario Outline: Create and Share Dashboard with "Recipient Organisation", "Action Due Date" AND "POI" filters FilterChart Gadgets
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
When I create "Purpose of Issue" filter with "For Review" type And "Recipient Organisation" filter And "Action Due Date" filter within 2 week And save with filtername "DocRecipientFilter"
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
When I add "Filter Chart" Gadget and Save as "DashboardDocRecipientFilterGadget" to Dashboard
Then "DashboardDocRecipientFilterGadget" Gadget should added Successfully on Dashboard
When I select "DocRecipientFilter" into Filter dropdown And "Recipient Organisation" into Statistics Type dropdown And select "Show Legends" checkbox of gadget
And I click on "Update" button of gadget
##  'PrimaryUser' Verification  ##
Then "highcharts-tracker" should displayed on Filter gadgets
And I get and verify "Purpose of Issue" filter with "For Review" selected type And "Recipient Organisation" filter and filtertype on Dashboard with "Files" tab And "Action Due Date" filter within 2 week And set saved "DocRecipientFilter" filter
##  'UserA' Verification  ##
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I click on "Tools" dropdown Button and select "Manage Dashboards"
Then Created New Dashboard should displayed in "Manage Dashboards" list
When I click on Created Dashboard of "Manage Dashboards" page
Then "DashboardDocRecipientFilterGadget" Gadget should displayed on Dashboard
And "highcharts-tracker" should displayed on Filter gadgets
And I verify "highcharts-tracker" hide and show using "legend" click
And I get and verify "Purpose of Issue" filter with "For Review" selected type And "Recipient Organisation" filter and filtertype on Dashboard with "Files" tab And "Action Due Date" filter within 2 week And set saved "DocRecipientFilter" filter
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
When I am on "Files" tab
Then "DocRecipientFilter" should "displayed" on Saved Filter list
##  Delete Dashboard Filter  ##
When I logOut and re-login using <PrimaryUser> Adoddle Form "AccessUser"
When I am on "Files" tab
And I delete "DocRecipientFilter" Saved Filter
Then "DocRecipientFilter" should "not displayed" on Saved Filter list
#  'UserA'  #
When I logOut and re-login using <UserA> Adoddle Form "AccessUser"
And I set DC vise URL
And I am on "Files" tab
Then "DocRecipientFilter" should "not displayed" on Saved Filter list
Examples: 
|DC_Center|Project_Name|PrimaryUser|UserA|
|UK|AutomationTestProject|auto_ukp2@atest.com|auto.nfpw_user1@atest.com|
#|US|Automatic_Test_US_WS|auto_usp2@atest.com|auto.nfpw_user1@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto_ausp2@atest.com|auto.nfpw_user1@atest.com|