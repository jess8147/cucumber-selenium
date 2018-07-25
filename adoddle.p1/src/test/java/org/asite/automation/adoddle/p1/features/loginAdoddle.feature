Feature: Verify User Login To ensure login functionality is working fine

@exclude
Scenario: First time login with incorrect password
Given I am on Login Page
When I give Username as "krupalpatel123@asite.com" and Password "k" and click on Login button
Then same page should be displayed with "Your login attempt has failed. The username or password may be incorrect. Please try again or contact the Asite Helpdesk by email at support@asite.com." message

@exclude
Scenario: First time login with correct password
Given I am on Login Page
When I give Username and Password and click on Login button
Then I should logged in successfully and redirecting on Adoddle Dashboard

@exclude
Scenario Outline: Testing BrowserMob
Given I click Add Files and capture API calls
Examples:
|DC_Center|
||
|UK|