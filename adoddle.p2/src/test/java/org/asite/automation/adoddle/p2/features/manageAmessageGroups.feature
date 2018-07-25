Feature: Manage AMessage Groups

@P2T7
Scenario Outline: Create Private Group
Given Project DC <DC_Center> is available
And I login using <Member User ID> user
And I get count of unread discussions
And I logout from adoddle and close browser
And I am already logged in
And I am on "aMessages" tab
When I create "private" AMesssage group as "timeStampInput" with project <Project> and users <Users>
Then AMessage group should get created as "timeStampInput"
When I send message as "specialchars" to AMessage group "timeStamp"
Then "timeStampInput" message should be received to all <Users>
When <Member User1> user leaves the group "timeStampInput"
Then "timeStampInput" group should be "invisible" to <Member User1> user
And <Member User1> should not be displayed as part of group to Administrator
When Administrator user removes the user <Member User2> from group "timeStampInput"
Then "timeStampInput" group should be "invisible" to <Member User2> user
And <Member User2> should not be displayed as part of group to Administrator
Given I deactivate AMessage group as "timeStampInput"
Examples:
|DC_Center|Project|Users|Member User1|Member User ID|Member User2|Administrator|
|UK|AutomationTestProject|Automation UKP2,Automation UK,Auto Test|Automation UK|auto_uk@atest.com|Auto Test|Automation UKP2|
#|US|Automatic_Test_US_WS|Automation USP2,Automation US,Auto Test|Automation US|auto_us@atest.com|Auto Test|Automation USP2|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2,Automation AUS,Auto Test|Automation AUS|auto_aus@atest.com|Auto Test|Automation AUSP2|

@P2T7
Scenario Outline: Create Public Group
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "aMessages" tab
When I create "public" AMesssage group as "timeStampInput" with project <Project> and users <Users>
Then AMessage group should get created as "timeStampInput"
When I send message as "specialCharString" to AMessage group "timeStamp"
Then "timeStampInput" message should be received to all <Users>
And "timeStampInput" group should be "visible" to <Public Member> user
Then "timeStampInput" message should be received to all <Public Member>
Given I deactivate group as "timeStampInput"
Examples:
|DC_Center|Project|Users|Public Member|
|UK|AutomationTestProject|Automation UKP2,Automation UK|Auto Test|
#|US|Automatic_Test_US_WS|Automation USP2,Automation US|Auto Test|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2,Automation AUS|Auto Test|