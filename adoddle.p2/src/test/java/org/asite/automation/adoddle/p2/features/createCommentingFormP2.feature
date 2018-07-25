Feature: Commenting Form - P2

@P1T2
Scenario Outline: Verify "Not Accepted" by TA and PM
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I switch to <Review Co-ordinator> user
Given I am on "Project Forms" tab
When I search commenting form with "Respond" action
And I respond the form and assign it to <Technical Authoriser>
When I switch to <Technical Authoriser> user
Given I am on "Project Forms" tab
When <Technical Authoriser> User responds commenting for with Accepted "No"
Then Form status should be "Further Review in Progress"
When I switch to <Review Co-ordinator> user
Given I am on "Project Forms" tab
And I respond the form and assign it to <Project Manager>
When I switch to <Project Manager> user
Given I am on "Project Forms" tab
When <Project Manager> User responds commenting for with Accepted "No"
Then Form status should be "Further Review in Progress"
Examples: 
|DC_Center|Project|Review Co-ordinator|Technical Authoriser|Project Manager|
||||||
|UK|Automation_TTT_Comment_Form_WS|Auto RFI|TC Bloggs|RFI Bloggs|

@exclude
Scenario Outline: Verify RC and Reviewer Concurrency
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I filter package form with status "Accepted" and QA status as "QA Accepted" and recipient as <Contractor PM>
Then All the package forms with "Accepted" status should be displayed
When I select one package form and distribute with "Submitted" status
Then Package form revision should be updated
When I switch to <Document Controller> user
Given I am on "Project Forms" tab
And I change QA Check status of package form to "QA Accepted"
Then Launch Commenting Form button should be displayed
When I launch commenting form by clicking "Launch Commenting Form" button
And I select Review Coordinator and Reviewer group
And I create Commenting form by clicking "Send" button
Then Comment form with updated Revision should be created 
When I switch to <Review Co-ordinator> user
And I am on "Project Forms" tab
And I perform respond action for commenting form
And I switch to <Reviewer> user
Given I am on "Project Forms" tab
When I try to respond the commenting form
Then Alert should be displayed with valid authorization message
Examples: 
|DC_Center|Project|Contractor PM|Document Controller|Review Co-ordinator|Reviewer|
|||||||
|UK|Automation_TTT_Comment_Form_WS|Auto Test|Automation UK|Auto RFI|PA Builder|