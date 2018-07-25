Feature: Create Embeded Email Form Response

@P2T4
Scenario Outline: Embeded Email Form Doc Download
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
And I have focus on <Project> project
And I have focus on "Embeded Email Response" folder
When I click "Create Form" button for "Embeded_Email_Response"
And I enter form title as "Embeded_Email_Form_Download"
And I attach multiple files to the form
And I associate multiple documents to the form
And I distribute form to <Distributed User Email> and <Paper User> users
And I click on "Send" button to create form
Then Embeded Email Response form should get created
When I login to <Distributed User Email> email account with password <Email Password>
Then Email should be received to distributed users with embeded form
And Form documents should get downloaded
When I provide response to email for "Online" user email
Then "Online" Email response should get created in the form
When I provide response to email for "Paper" user email
Then "Paper" Email response should get created in the form
Examples: 
|DC_Center|Project|Paper User|Distributed User Email|Email Password|
|UK|AutomationTestProject|DC (Paper) Builder|jasminprajapati@asite.com|Asite@987|
#|US|Automatic_Test_US_WS|DC (Paper) Builder|vmodi@asite.com|Asite@987|
#|AUS|Automatic_Test_AUS_WS|DC (Paper) Builder|jasminprajapati@asite.com|Asite@987|