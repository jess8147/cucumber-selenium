Feature: Form Mailbox Classic

@P1T5
Scenario Outline: Check Created Form through sent mail to Form mailbox
Given Project DC <DC_Center> is available
Given I setup default browser
Given I have sent mail to <MailboxEmail> With Subject "formmailbox_Email"+SYSDATE for Form Mailbox
And entered test mail template AND have attachment excel sheet
And I am on Workspace home page of workspace <Project>
When I click on "Email To Form Status_Auto_Dist" Formtype
Then Form with "formmailbox_UK"+SYSDATE value in Form title should be available in Form listing
And User should be assigned actions as per mailbox configuration
Examples: 
|DC_Center|MailboxEmail|Project|
|UK|formmailbox_ukClassic@mail.asite.com|AutomationClassic_UK_WS|
#|US|formmailbox_usClassic@mailb.asite.com|AutomationClassic_US_WS|
#|AUS|formmailbox_ausClassic@maild.asite.com|AutomationClassic_AUS_WS|