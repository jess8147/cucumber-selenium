Feature: Create Embeded Email External Form Response

@P2T2
Scenario Outline: Embeded Email External Response
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "Embeded Email Response" folder
When I click "Create Form" button for "Embeded_Email_Response"
And I enter form title as "Embeded_Email_Form_Response"
And I attach multiple files to the form
And I associate multiple documents to the form
And I distribute form to <Distributed User Email> and <Paper User> users
And I click on "Send" button to create form
Then Embeded Email Response form should get created
When I login to <Distributed User Email> email account with password <Email Password>
Then Email should be received to distributed users with embeded form
When I click on "Respond" link for "Online" user email
And Online user logs with Login credentials
Then "Embeded_Email_Response" page should be displayed and "Online" user should be able to respond
And Responded text should be available in the form for "Online" user
When I login to <Distributed User Email> email account with password <Email Password>
Then Email should be received to distributed users with embeded form
When I click on "Respond" link for "Paper" user email
Then "Embeded_Email_Response" page should be displayed and "Paper" user should be able to respond
And Responded text should be available in the form for "Paper" user
Examples: 
|DC_Center|Project|Paper User|Distributed User Email|Email Password|
|UK|AutomationTestProject|DC (Paper) Builder|jasminprajapati@asite.com|Asite@987|
#|US|Automatic_Test_US_WS|DC (Paper) Builder|vmodi@asite.com|Asite@987|
#|AUS|Automatic_Test_AUS_WS|DC (Paper) Builder|jasminprajapati@asite.com|Asite@987|