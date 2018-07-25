Feature: Associate Comments with New Forms

@P1T4
Scenario Outline: Associate-Comments with New Form via Context-Click
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Discussions" tab
When I search a comment with name as "AutomationTest_CommentTitle"
Then Atleast one unread comment should available in comment Listing with name as "AutomationTest_CommentTitle"
And I Select multiple comments available in Listing AND I Context Click AND I Select option "Project Form"
Then New "Create Form" Popup should open
And I Search Form as "Request For Information"
Then "Request For Information" Form Should Load sucessfully
And I Select user as <Users> AND subject as "Random String"
And I Click on "Send" Button
And I am on "Project Forms" tab
And I Search form AND Validated associated discussions
Examples: 
|DC_Center|Users|
|UK|Automation UK|
#|US|Automation US|
#|AUS|Automation AUS|

@P1T4
Scenario Outline: Associate-Comments with New Form via More Options
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Discussions" tab
When I search a comment with name as "AutomationTest_CommentTitle"
Then Atleast one unread comment should available in comment Listing with name as "AutomationTest_CommentTitle"
And I Select multiple comments available in Listing AND I Click Link "More Options"
And I Select Option "Project Form"
Then New "Create Form" Popup should open
And I Search Form as "Request For Information"
Then "Request For Information" Form Should Load sucessfully
And I Select user as <Users> AND subject as "Random String"
And I Click on "Send" Button
And I am on "Project Forms" tab
And I Search form AND Validated associated discussions
Examples: 
|DC_Center|Users|
|UK|Automation UK|
#|US|Automation US|
#|AUS|Automation AUS|