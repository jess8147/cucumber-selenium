Feature: Commenting Form External Distribution

@P2T2
Scenario Outline: Create Package Form
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus on <Project> project
And I upload multiple documents in "TIDP" folder
Given I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "POW" folder
When I click "02 Submission Package" form link and click on "Create Form" button
Then "Create Form" form popup should open
And I enter all mandatory fields for package form "with" status
And I click on "Send" button to create package form
Then Package form should get created successfully
And Package form status should be "Submitted"
Examples:
|DC_Center|Project|Contractor PM|Document Controller|
|||||
|UK|Automation_TTT_Comment_Form_WS|Auto Test|Automation UK|

@P2T2
Scenario Outline: External Distribution to Users - Read Only Section
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I switch to <Document Controller> user
Given I am on "Project Forms" tab
When I search created "package" form
And Document controller accepts form and changes status to "QA Accepted"
Then Launch button should be available to create Commenting form "Yes"
When I view the package form and click on "Launch Commenting Form" button
And I input all mandatory fields "without" reviewer group and create commenting form
When I distribute commenting form to <TIG Reviewer> and <Technical Authorizer> and <Project Manager>
And I switch to <TIG Reviewer> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "Respond" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User selects "Package" radio button on comment section
And User input Comment and select severity as "Moderate"
And User opts package Recommendation option as "Not Accepted"
And User clicks on "Send" button on commenting form popup
When I switch to <Technical Authorizer> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "Respond" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User selects "Package" radio button on comment section
And User input Comment and select severity as "Moderate"
And User opts package Recommendation option as "Not Accepted"
And User clicks on "Send" button on commenting form popup
When I switch to <Project Manager> user
And I am on "Project Forms" tab
When I search created "commenting" form
Then "Respond" action should be available for form
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
And Respond Form should contain <TIG Reviewer> comment for "TIG Reviewer" in Read-Only section
And Respond Form should contain <Technical Authorizer> comment for "Technical Authorizer" in Read-Only section
And Respond Form should contain <Project Manager> comment for "Project Manager" in Read-Only section
Examples: 
|DC_Center|Project|Contractor PM|Document Controller|Review Co-ordinator|TIG Reviewer|Technical Authorizer|Project Manager|
|||||||||
|UK|Automation_TTT_Comment_Form_WS|Auto Test|Automation UK|Auto RFI|PA Bloggs|TC Bloggs|RFI Bloggs|