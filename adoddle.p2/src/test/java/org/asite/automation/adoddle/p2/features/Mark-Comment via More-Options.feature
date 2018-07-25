Feature: Mark-Reply Comments with More Options

@P2T4
Scenario Outline: Mark-Comments via More-Options
Given Project DC <DC_Center> is available
And I am already logged in
And I switch to <Secondary User> user
And I have created testdata for mark reply discussions
And I switch to <Primary User> user
When I Navigate to "Unread Discussions" Link AND I get total Unread Discussions Count
Then Atleast one unread comment should available in comment Listing 
When I search a comment with name as "Mark_Reply_Form"
Then Atleast one unread comment should available in comment Listing 
And I Select first available comment in listing AND I click link "More Options"
And I Select Link "Mark as Read"
Then "Mark as Read" popup should open
And I Click Link "Mark As Read"
Then I Validate "Unread Discussion" Count AND Comment
Examples: 
|DC_Center|Primary User|Secondary User|
|UK|Automation UKP2|Automation UK|
#|US|Automation USP2|Automation US|
#|AUS|Automation AUSP2|Automation AUS|