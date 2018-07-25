Feature: Document Mailbox

@P1T2
Scenario Outline: Publish document through sent mail to document mailbox
Given Project DC <DC_Center> is available
Given I have sent mail to <MailboxEmail> With Subject "AutomationTestDocumentMailbox"+SYSDATE
#And entered test mail template AND have attachment
And I am already logged in
And I am on "Files" tab
And I have focus on Project
When I click on "AutomationMailboxDocuments" folder
Then Document with "AutomationTestDocumentMailbox"+SYSDATE value in Doc Ref should be available in document listing
When I click on clip icon of document with "AutomationTestDocumentMailbox"+SYSDATE value in Doc Ref
Then "Download Files" popup should open
Given I click on "Download" button
Then attached document in sent mail should download
Examples:
|DC_Center|MailboxEmail|
|UK|docmailboxuk@mail.asite.com|
#|US|docmailboxus@mailb.asite.com|
#|AUS|docmailboxaus@maild.asite.com|