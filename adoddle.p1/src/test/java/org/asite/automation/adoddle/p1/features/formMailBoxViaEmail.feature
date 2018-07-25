Feature: Form Mailbox
	
@P1T1
Scenario Outline: Check Created Form through sent mail to Form mailbox
Given Project DC <DC_Center> is available
Given I have sent mail to <MailboxEmail> With Subject "formmailbox_UK"+SYSDATE for Form Mailbox
And entered test mail template AND have attachment excel sheet
And I am on "Project Forms" tab
And I have focus on Project
When I click on "Email To Form Status" Formtype
Then Form with "formmailbox_UK"+SYSDATE value in Form title should be available in Form listing
And User should be assigned actions as per mailbox configuration
Examples: 
|DC_Center|MailboxEmail|
|UK|formmailbox_UK@mail.asite.com|
#|US|formmailbox_US@mailb.asite.com|
#|AUS|formmailbox_AUS@maild.asite.com|