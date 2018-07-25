Feature: Create Organization User

@P1T2
Scenario Outline: Create Org User from Adoddle
Given Project DC <DC_Center> is available
And I am already logged in
And I set Landing URL
And I am on "Admin" tab
When I click on "Manage Users" box on Admin tab
Then "Manage Users & Subscription" page should be displayed
When I click on "Add New User" button on Users Listing page
Then "Create New User" popup should open
When I add Email Address as mandatory field
And I select Organization as <Organization>
And I enter all fields to create new user and click "Save" button
Then User should get created successfully
When I search user on Manage Users & Subscription page
Then User should be available
Examples: 
|DC_Center|Organization|
|UK|Automation User-Org|
#|US|Automation User-Org|
#|AUS|Automation User-Org|