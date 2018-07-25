Feature: Recent Forms Widgets - Reply and Reply All

@P2T2
Scenario Outline: Recent Forms Dashboard "PAST WEEK" Highcharts with "Respond" action Hyperlink And "Reply" AND "Reply All" Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I create new form in folder"Form Distibution Group" and form type "Check Form Distribution" with "Respond" action to User <B> User <C> and User <D>
Then Form should created successfully with No action
#Then form should created successfully on "Project Forms" tab And "Respond" action assigned to given form
When I switch to <B> user
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Respond action widget with "Respond" action
And I click on selected form hyperlink of "Respond" action
Then new tab should opened
#Then "Respond Form" page should opened AND User <B> and User <C> should pre-populated in Distributed field
Then "Respond Form" page should opened AND User <A> User <C> and User <D> should pre-populated in Distributed field for multiple reply
When I externally assign "Respond" action to User <C>
When I create "Respond message" and clear "Form Group Code" and click on Send button
When I closed opened new window
Then "Respond" action should completed to selected form
When I switch to <C> user
And I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Respond action widget with "Respond" action
And I right click and select context click menu option "New" AND I click on "Reply" widget
Then new tab should opened
Then form reply should opened in new tab and User <B> should pre-populated in Distributed field for single reply
When I externally assign "Respond" action to User <D>
When I create "Respond message" and clear "Form Group Code" and click on Send button
Then Message Status set as "Sent"
When I closed opened new window
#Then "Respond" action should completed to selected form
Then selected form "Respond" action should completed for "RES" type form and should not completed for "ORI" type form
When I switch to <D> user
And I click on "PAST WEEK" highcharts of Recent Forms
And I select form for perform Respond action widget with "Respond" action
And I right click and select context click menu option "New" AND I click on "Reply All" widget
Then new tab should opened
Then form reply should opened in new tab and User <B> and User <C> both should pre-populated in Distributed field for multiple reply
#Then "Respond Form" page should opened AND User <A> User <B> and User <C> should pre-populated in Distributed field for multiple reply
When I create "Respond message" and clear "Form Group Code" and click on Send button
Then Message Status set as "Sent"
When I closed opened new window
#Then "Respond" action should completed to selected form
Then selected form "Respond" action should completed for "RES" type form and should not completed for "ORI" type form
Examples: 
|DC_Center|A|B|C|D|User_ID|
|UK|Automation UKP2|Automation UK|Auto Test|Auto RFI|auto_ukp2@atest.com|
#|US|Automation USP2|Automation US|Auto Test|Auto RFI|auto_usp2@atest.com|
#|AUS|Automation AUSP2|Automation AUS|Auto Test|Auto RFI|auto_ausp2@atest.com|