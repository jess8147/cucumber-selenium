Feature: Download Mulitiple types of Files

@P2T5
Scenario Outline: Download Mulitiple types of Files with different extension
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
When I search <Search_Files> files with linked Files
And Download Files using Type icon
Then Files should downloaded in local And Verify with files size
Examples: 
|DC_Center|Search_Files|
|UK|AutomationDownloadPDFFile,AutomationDownloadDWGFile,AutomationDownloadXLSFile|
#|US|AutomationDownloadPDFFile,AutomationDownloadDWGFile,AutomationDownloadXLSFile|
#|AUS|AutomationDownloadPDFFile,AutomationDownloadDWGFile,AutomationDownloadXLSFile|