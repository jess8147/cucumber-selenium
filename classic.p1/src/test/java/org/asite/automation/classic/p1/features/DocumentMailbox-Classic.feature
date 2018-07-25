Feature: Document Mailbox Classic 

@P1T3
Scenario Outline: Check Created document through sent mail to document mailbox
Given Project DC <DC_Center> is available
Given I setup default browser
Given I have sent mail to <MailboxEmail> With Subject "AutomationTestDocumentMailbox"+SYSDATE
And entered test mail template AND have attachment
And I am on Workspace home page of workspace <Project>
When I click on "DocumentMailboxFolder" folder
Then Document with "AutomationTestDocumentMailbox"+SYSDATE value in Doc Ref should be available in document listing
When I click on Type icon of document with "AutomationTestDocumentMailbox"+SYSDATE value in Doc Ref
Then attached document in sent mail should download
Examples: 
|DC_Center|MailboxEmail|Project|
|UK|docmailbox_ukClassic2@mail.asite.com|AutomationClassic_UK_WS|
#|US|docmailbox_usClassic@mailb.asite.com|AutomationClassic_US_WS|
#|AUS|docmailbox_ausClassic@maild.asite.com|AutomationClassic_AUS_WS|