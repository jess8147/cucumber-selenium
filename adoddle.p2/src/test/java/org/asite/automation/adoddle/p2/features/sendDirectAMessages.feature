Feature: Manage Direct AMessages

@P2T7
Scenario Outline: Send Direct Message without attachment
Given Project DC <DC_Center> is available
And I login using <Receiver ID> user
And I get count of unread discussions
And I logout from adoddle and close browser
And I am already logged in
And I am on "aMessages" tab
When I search <Receiver> on AMessage LH panel and send 5 direct messages "without" attachment
Then Sent messages should be available in chat panel
Given I logout from adoddle and close browser
And I login using <Receiver ID> user
And I am on "Dashboard" tab
Then Unread discussion counts should increase by 5
Given I am on "aMessages" tab
Then <Receiver> should get messages sent from <Sender>
Examples:
|DC_Center|Project|Sender|Receiver|Receiver ID|
|UK|AutomationTestProject|Automation UKP2|Automation UK|auto_uk@atest.com|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|auto_us@atest.com|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|auto_aus@atest.com|

@P2T7
Scenario Outline: Send Direct Message with attachment - Reply
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "aMessages" tab
When I search <Receiver> on AMessage LH panel and send 1 direct messages "with" attachment
Then Sent messages should be available in chat panel
Given I logout from adoddle and close browser
And I login using <Receiver ID> user
And I am on "aMessages" tab
Then <Receiver> should get messages sent from <Sender>
And <Receiver> should be able to view attachments in message
When <Receiver> replies the last message with attachment
Then <Sender> should see the reply with attachment by <Receiver>
Examples:
|DC_Center|Project|Sender|Receiver|Receiver ID|
|UK|AutomationTestProject|Automation UKP2|Automation UK|auto_uk@atest.com|
#|US|Automatic_Test_US_WS|Automation USP2|Automation US|auto_us@atest.com|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|Automation AUS|auto_aus@atest.com|