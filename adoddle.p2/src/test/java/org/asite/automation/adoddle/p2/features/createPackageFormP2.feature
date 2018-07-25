Feature: Create Package-Commenting Form - Accepted With Comments

@P2T3
Scenario Outline: Verify "Not Submitted" status for Package Form
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "POW" folder
When I click "02 Submission Package" form link and click on "Create Form" button
Then "Create Form" form popup should open
When I enter all mandatory fields for package form "except" status
And I click on "Send" button to create package form
Then Package form should get created successfully
And Package form status should be "Not Submitted"
When I switch to <Document Controller> user
Given I am on "Project Forms" tab
When I search created "package" form
Then Form should not be available to <Document Controller>
When I switch to <Contractor PM> user
Given I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "package" form
And I update package form status to "Submitted"
And I switch to <Document Controller> user
Given I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "package" form
Then "For Action" action should be available for form
Examples:
|DC_Center|Project|Contractor PM|Document Controller|
|||||
|UK|Automation_TTT_Comment_Form_WS|Auto Test|Automation UK|

@P2T3
Scenario Outline: Verify "QA Not Accepted" status for Package Form
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus on <Project> project
And I upload multiple documents in "TIDP" folder
When I switch to <Document Controller> user
Given I am on "Project Forms" tab
When I search created "package" form
And Document controller accepts form and changes status to "QA Not Accepted"
Then Launch button should be available to create Commenting form "No"
When I switch to <Contractor PM> user
Given I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "package" form
Then Package form status should be "Not Accepted"
When I try to edit and distribute package form
Then New package version "2.0" should be created
Examples:
|DC_Center|Project|Contractor PM|Document Controller|
|||||
|UK|Automation_TTT_Comment_Form_WS|Auto Test|Automation UK|

@P2T3
Scenario Outline: Create Commenting Form for "Accepted with Comments"
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I switch to <Document Controller> user
Given I am on "Project Forms" tab
When I search created "package" form
And Document controller accepts form and changes status to "QA Accepted"
Then Launch button should be available to create Commenting form "Yes"
When I view the package form and click on "Launch Commenting Form" button
And I input all mandatory fields "with" reviewer group and create commenting form
When I switch to <Reviewer1> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "Respond" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User selects "Document" radio button on comment section
And User selects document from dropdown and provides comment and selects severity as "Minor"
And User opts Document Acceptance Recommendation option as "Accepted with comments"
And User opts package Recommendation option as "Accepted with comments"
And User clicks on "Send" button on commenting form popup
When I switch to <Lead Reviewer> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "For Information" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User selects "Package" radio button on comment section
And User input Comment and select severity as "Moderate"
And User opts package Recommendation option as "Not Accepted"
And User clicks on "Send" button on commenting form popup
When I switch to <Review Co-ordinator> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "For Information" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
And Respond Form should contain all comments in Read-Only section
And Respond Form should contain <Lead Reviewer> comment in editable section
When User Insert Comment to existing comment
And User issues form to <Technical Authoriser> as "Technical Authoriser"
And User selects status as "Accepted with comments" for all documents
And User clicks on "Send" button on commenting form popup
When I switch to <Technical Authoriser> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "Respond" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User approves commenting form as "Yes"
And User sends commenting form to <Project Manager> 
And User clicks on "Send" button on commenting form popup
When I switch to <Project Manager> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "Respond" action should be available for form
And "Sent to PM via TA" status should be available for commenting form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User accepts form comments as "Yes" with comments
And User sends commenting form to <Contractor PM> 
And User clicks on "Send" button on commenting form popup
And "Accepted With Comments" status should be available for commenting form
When I switch to <Contractor PM> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "Accepted With Comments" status should be available for commenting form
When I search created "package" form
Then "Accepted With Comments" status should be available for commenting form
And "Accepted With Comments" status should be available for associated files
Examples: 
|DC_Center|Project|Contractor PM|Document Controller|Reviewer1|Lead Reviewer|Review Co-ordinator|Technical Authoriser|Project Manager|
||||||||||
|UK|Automation_TTT_Comment_Form_WS|Auto Test|Automation UK|PA Builder|RFI Builder|Auto RFI|TC Bloggs|RFI Bloggs|