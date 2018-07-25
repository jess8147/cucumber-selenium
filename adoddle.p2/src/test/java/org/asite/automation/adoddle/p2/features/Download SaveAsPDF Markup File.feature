Feature: Download SaveAsPDF Markup File

@P2T7
Scenario Outline: Single/Multiple files Save PDF using "Save as PDF" Widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I click on Project And select "SavePDFTestFolder" folder
And I select "Html Viewer Markup File" with file size
And I right click and select context menu option "Save as PDF"
Then file should downloaded as "PDF" File in local directory And I verify file size
#Then "Html Viewer Markup File" file should downloaded with "_Markups.pdf" extension And I verify file size
When I click on Project And select "SavePDFTestFolder" folder
And I select "Advanced Viewer Markup File" with file size
And I right click and select context menu option "Save as PDF"
Then file should downloaded as "PDF" File in local directory And I verify file size
#Then "Advanced Viewer Markup File" file should downloaded with "_Markups.pdf" extension And I verify file size
When I click on Project And select "SavePDFTestFolder" folder
And I select "Html Advanced Markup File" with file size
And I right click and select context menu option "Save as PDF"
Then file should downloaded as "PDF" File in local directory And I verify file size
#Then "Html Advanced Markup File" file should downloaded with "_Markups.pdf" extension And I verify file size
When I click on Project And select "SavePDFTestFolder" folder
And I select "Without Markup File" with file size
And I right click and select context menu option "Save as PDF"
Then file should downloaded as "PDF" File in local directory And I verify file size
#Then "Without Markup File" file should downloaded with "_Markups.pdf" extension And I verify file size
When I click on Project And select "SavePDFTestFolder" folder
And I get files name from doclisting page
And I multiple files select
And I right click and select context menu option "Save as PDF"
Then "Download Files" popup should open
When I select "Include Markups" checkbox AND click on download button of "Download Files" popup
Then "PDF" Batch file should be created And Zip file should be downloaded into Local Directory
When I Unzip "PDF" zip file
Then "PDF" files should available in Local Directory And files size match with local files size
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|