Feature: Send Reporting Email

@sendEmail
Scenario Outline: Send Reports to End Users
Given Project DC <DC_Center> is available
Given I send reporting email to end users
Examples:
|DC_Center|
|UK|
|US|
|AUS|