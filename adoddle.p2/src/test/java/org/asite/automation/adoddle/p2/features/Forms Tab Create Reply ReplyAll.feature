Feature: Forms Tab Create Reply ReplyAll

@P2T7
Scenario Outline: Forms Tab Create Reply ReplyAll via Right Click Option
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in "Form Distibution Group" appfolder and "Check Form Distribution" form type with "Respond" action to User <B> and <C>
Then Form should created successfully with No action
When I switch to <B> user
Given I am on "Project Forms" tab
Then created form should displayed with "Respond" action
When I right click on selected Forms and I select "New" and "Reply" context option
Then new tab should opened
Then form reply should opened in new tab and User <A> should pre-populated in Distributed field for single reply
When I externally assign "Respond" action to User <C>
When I create "Respond message" and clear "Form Group Code" and click on Send button
Then Message Status set as "Sent"
When I closed opened new window
Then selected form "Respond" action should completed
When I switch to <C> user
Given I am on "Project Forms" tab
Then created form should displayed with "Respond" action
And verify number of "Respond" action count is 2
When I right click on selected Forms and I select "New" and "Reply All" context option
Then new tab should opened
#Then form reply should opened in new tab and User <A> and User <B> both should pre-populated in Distributed field for multiple reply
Then form reply should opened in new tab and User <B> should pre-populated in Distributed field for single reply
When I create "Respond message" and clear "Form Group Code" and click on Send button
Then Message Status set as "Sent"
When I closed opened new window
#Then selected form "Respond" action should completed
Then selected form "Respond" action should completed for "RES" type form and should not completed for "ORI" type form
Examples: 
|DC_Center|User_ID|A|B|C|
|UK|auto_ukp2@atest.com|Automation UKP2|Automation UK|Auto Test|
#|US|auto_usp2@atest.com|Automation USP2|Automation US|Auto Test|
#|AUS|auto_ausp2@atest.com|Automation AUSP2|Automation AUS|Auto Test|