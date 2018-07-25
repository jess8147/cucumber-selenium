Feature: Create Commenting Form

Background:
Given Script is Node specific

@exclude
Scenario Outline: Download Commenting Zip from SVN
Given Project DC <DC_Center> is available
And I logged in to svn with <svnUser> and <svnPassword>
And I navigate to HTML apps download url
When I download Commenting form zip file from svn
Then Commenting zip file should get downloaded successfully
When I download package form xsn file from svn
Then Package form xsn file should get downloaded successfully
And I am already logged in with multi-Project user
And I have uploaded Commenting zip file to <Project> project with Admin user
And I have uploaded Packaging xsn file to <Project> project with Admin user
Examples:
|DC_Center|svnUser|svnPassword|Project|
|||||
|UK|jasminprajapati|asite987|Automation_TTT_Comment_Form_WS|

@P1T3
Scenario Outline: Create Package Form P1
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
When I enter all mandatory fields for package form
And I click on "Send" button to create package form
Then Package form should get created successfully
When I switch to <Document Controller> user
Given I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "package" form
Then "For Action" action should be available for form
When Document controller accepts form and changes status to "QA Accepted"
Then "Launch" button should be available to create Commenting form
When I switch to <TTT Project Manager> user
Given I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "package" form
Then "For Information" action should be available for form
Examples:
|DC_Center|Project|Document Controller|TTT Project Manager|
|||||
|UK|Automation_TTT_Comment_Form_WS|Automation UK|RFI Bloggs|

@P1T3
Scenario Outline: Create Commenting Form P1 with "Accepted" Status
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I switch to <Document Controller> user
Given I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "package" form
And I view the package form and click on "Launch Commenting Form" button
And I input all mandatory fields and click Send button to create commenting form
And I switch to <Contractor PM> user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "commenting" form
Then "Review in Progress" status should be available for commenting form
When I switch to <Reviewer1> user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "commenting" form
Then "Respond" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User selects "No Comments" radio button on comment section
Then User should be able to opt Package Recommendation options "Accepted" or "N/A"
When User selects "Package" radio button on comment section
Then User should be able to input Comment and select severity
And User should be able to opt Package Recommendation options "Accepted With Comments" or "Not Accepted"
When User selects "Document" radio button on comment section
Then User should be able to select document to comment on
And User should be able to opt Package Recommendation options "Accepted With Comments" or "Not Accepted"
#When User selects "Document" radio button on comment section
#And User selects document from dropdown and provides comment and selects severity as "Major"
#And User opts Document Acceptance Recommendation option as "Accepted With Comments"
#And User opts package Recommendation option as "Accepted With Comments"
# New Steps Added
When User selects "No Comments" radio button on comment section
Then User opts Document Acceptance Recommendation option as "Accepted"
# New Steps completed
And User clicks on "Send" button on commenting form popup
Then "Respond" action should get cleared for <Reviewer1>
When I switch to <Lead Reviewer> user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "commenting" form
Then "For Information" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
#When User selects "Package" radio button on comment section
#And User input Comment and select severity as "Moderate"
#And User opts package Recommendation option as "Not Accepted"
# New Steps completed
When User selects "No Comments" radio button on comment section
Then User opts Document Acceptance Recommendation option as "Accepted"
# New Steps completed
And User clicks on "Send" button on commenting form popup
Then "Respond" action should get cleared for <Lead Reviewer>
When I switch to <Review Co-ordinator> user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "commenting" form
Then "For Information" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
And Respond Form should contain all comments in Read-Only section
And Respond Form should contain <Lead Reviewer> comment in editable section
#When User Insert Comment to existing comment
#And User externally adds new comment on another document
And User issues form to <Technical Authoriser> as "Technical Authoriser"
And User selects status as "Accepted" for all documents
And User clicks on "Send" button on commenting form popup
Then "Respond" action should get cleared for <Review Co-ordinator>
When I switch to <Technical Authoriser> user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "commenting" form
Then "Respond" action should be available for form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User approves commenting form as "Yes"
And User sends commenting form to <Project Manager>
And User clicks on "Send" button on commenting form popup
Then "Respond" action should get cleared for <Technical Authoriser>
When I switch to <Project Manager> user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "commenting" form
Then "Respond" action should be available for form
And "Sent to PM via TA" status should be available for commenting form
When User responds to the commenting form
Then "Respond Form" form popup should open
When User accepts form comments as "Yes" with comments
And User sends commenting form to <Contractor PM>
And User clicks on "Send" button on commenting form popup
Then "Respond" action should get cleared for <Project Manager>
And "Accepted" status should be available for commenting form
When I switch to <Contractor PM> user
And I am on "Project Forms" tab
And I have focus on <Project> project
When I search created "commenting" form
Then "Accepted" status should be available for commenting form
When I search created "package" form
Then "Accepted" status should be available for commenting form
And "Accepted" status should be available for associated files
Examples:
|DC_Center|Project|Contractor PM|Document Controller|Reviewer1|Lead Reviewer|Review Co-ordinator|Technical Authoriser|Project Manager|
||||||||||
|UK|Automation_TTT_Comment_Form_WS|Auto Test|Automation UK|PA Builder|RFI Builder|Auto RFI|TC Bloggs|RFI Bloggs|

@P1T3
Scenario Outline: Verify Excel Report For Commenting Form
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Project Forms" tab
When I search created "commenting" form
And I open commenting form details in new window
Then "Export Comments" button should be available
When I export excel report by clicking "Export Comments" button
Then Excel Report should contains all comments
Examples: 
|DC_Center|
||
|UK|