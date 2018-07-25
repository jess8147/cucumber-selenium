Feature: Clean Users

@cleanUsers
Scenario Outline: Deactivate Users with Text
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
Given I am on "Admin" tab
And I click on "Manage Users" box on Admin tab
Given I deactivate all users with text <UserSearchText>
Examples: 
|DC_Center|UserSearchText|Username|Password|
|UK|automationuser_2018*|jasminprajapati@asite.com|Test@12345|
#|US|automationuser_2018*|jasminprajapati@asite.com|Test@12345|