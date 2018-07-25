Feature: Reply Comment via Context Click

@P2T5
Scenario Outline: Reply-Comment via Context-Click
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Discussions" tab
And I switch to <Secondary User> user
And I have created testdata for mark reply discussions
And I switch to <Primary User> user
When I Navigate to "Unread Discussions" Link AND I get total Unread Discussions Count
And I search a comment with name as "Mark_Reply_Form"
Then Atleast one unread comment should available in comment Listing with name as "Mark_Reply_Form"
And I Context Click AND I Select option "Reply"
Then I should Redirected to new window with popup having comment title as "Mark_Reply_Form"
And I edit comment title AND description
And I click link "More Options"
Then "Options" Popup should open
And I have select option "Associate Files"
And I have associated first two "Files" available in Listing
And Again I Click Link "More Options"
And I have select option "Associate aMessages"
And I have associated first two "Discussions" available in Listing
And Again I Click Link "More Options"
And I have select option "Associate Forms"
And I have associated first two "Forms" available in Listing
And I "Submit" Comment Reply
Then Reply should created sucessfully AND I validated all associated documents
Examples: 
|DC_Center|Primary User|Secondary User|
|UK|Automation UKP2|Automation UK|
#|US|Automation USP2|Automation US|
#|AUS|Automation AUSP2|Automation AUS|