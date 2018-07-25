Feature: Manage Admin Notice

@exclude
Scenario Outline: Create Notice on Admin Tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
And I create global test project as "Auto_Notification_WS"
And I am on "Admin" tab
When I click on "Manage Notice" box on Admin tab
Then "Create New Notice" button should be displayed
When I click on "Create New Notice" button
Then "Add Notice" popup should open
When I enter values in notice fields
And I set notice start date and end date as active values
And I apply notice to current project
And I click on "Save" button on Add Notice Popup
And I sort notice in "descending" order of "Created On"
Then Notice should be available on top of Listing
When I reload the page
Then Notice should appear on top of the page "yes"
When I login to adoddle with <Secondary User> and <Secondary Password>
Then Notice should appear on top of the page "No"
When I re-login into adoddle
Then Notice should appear on top of the page "yes"
When I give project access to <Secondary User>
And I login to adoddle with <Secondary User> and <Secondary Password>
Then Notice should appear on top of the page "Yes"
When I click on Notice "Read More" link and read it
Then Notice should have correct data available
When I click on "Close" button
Then Notice popup should get closed
When I re-login into adoddle
Then Notice should appear on top of the page "yes"
When I click on Notice "Read More" link and read it
Then Notice should have correct data available
When I click on "Close" button
Then Notice popup should get closed
When I reload the page
Then Notice should appear on top of the page "yes"
When I dismiss the Notice by clicking "Dismiss" link
Then Notice should get dismissed
When I reload the page
Then Notice should appear on top of the page "no"
Given I deactivate global test notice project
Examples: 
|DC_Center|Secondary User|Secondary Password|
||||
|UK|auto_uk@atest.com|Test@12345|
#|US|auto_us@atest.com|Test@12345|

@exclude
Scenario Outline: Edit Activate Deactivate Existing Notice
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Admin" tab
When I click on "Manage Notice" box on Admin tab
Then "Manage Notices" listing should be displayed
When I select any active notice and make it "On going"
And I reload the page
Then Active Notice should popup on the page "Yes"
Given I am on "Admin" tab
When I click on "Manage Notice" box on Admin tab
Then "Manage Notices" listing should be displayed
When I select active notice and deactivate it
Then Notice Status should be "In-Active"
And User should be able to edit notice "No"
When I reload the page
Then Active Notice should popup on the page "No"
Given I am on "Admin" tab
When I click on "Manage Notice" box on Admin tab
And I select inactive notice and activate it
Then Notice Status should be "Active"
And User should be able to edit notice "Yes"
When I edit existing Notice Details
And I set notice start date and end date as active values
And I click on "Save" button on Update Notice popup
Then All notice details should get updated on Listing
And Notice Scheduled Status should be "On going"
When I reload the page
Then Active Notice should popup on the page "Yes"
Given I am on "Admin" tab
When I click on "Manage Notice" box on Admin tab
When I set notice end time as past value
Then Notice Scheduled Status should be "Expired"
When I reload the page
Then Active Notice should popup on the page "No"
Examples: 
|DC_Center|
||
|UK|

@exclude
Scenario Outline: Manage Priorities of Notice
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
And I create global test project as "Auto_Notification_WS"
And I am on "Admin" tab
When I click on "Manage Notice" box on Admin tab
Then "Manage Notices" listing should be displayed
When I click on "Create New Notice" button
Then "Add Notice" popup should open
When I create "PriorityTestNotice1_" notice with "High" priority and having "recent" due time 
Then Notice should get created and available on listing
When I click on "Create New Notice" button
Then "Add Notice" popup should open
When I create "PriorityTestNotice2_" notice with "High" priority and having "later" due time
Then Notice should get created and available on listing 
When I click on "Create New Notice" button
Then "Add Notice" popup should open
When I create "PriorityTestNotice3_" notice with "Low" priority and having "recent" due time
Then Notice should get created and available on listing
When I click on "Create New Notice" button
Then "Add Notice" popup should open
When I create "PriorityTestNotice4_" notice with "Low" priority and having "later" due time
Then Notice should get created and available on listing
And All created Notices should be seen as per their priorities
Given I deactivate global test notice project
Then Notices should not be displayed on notice listing
Examples: 
|DC_Center|
||
|UK|
#|US|