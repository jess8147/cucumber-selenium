Feature: Shared Public Link

@P1T2
Scenario Outline: Share Adoddle Public Link
Given Project DC <DC_Center> is available
Given I am already logged in
And I am on "Files" tab
And I have atleast one document in document listing
When I right click on "SharedLinkAutomationTestDataFile-Public" document
Then Context menu popup should open
When I drag mouse on "Share"
And Click on "Share Adoddle Link"
Then "Share link to" popup should open
When I enter "automation.user@asite.com" into "Send this link to" textbox
And I set visibility to "Anyone with the link" and Expiry to "24 Hours"
And click on "Send" button
Then Email should send to given "automation.user@asite.com" with Selected doucments link
When I click on link from received email
Then Document "SharedLinkAutomationTestDataFile-Public" should open in Adoddle view and viewing should not require credentials
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|