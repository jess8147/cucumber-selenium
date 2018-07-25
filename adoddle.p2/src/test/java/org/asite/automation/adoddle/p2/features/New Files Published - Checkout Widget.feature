Feature: New Files Published With Tasks - Checkout Widget

@P2T6
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND "Checkout", "Undo Checkout" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I upload files in "AutomationUploadFiles" folder of <Project_Name> Project with "For Comment Incorp." actions to User <A> and User <B>
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select files for perform Checkout widget
And I right click and select context menu option "Checkout"
Then "Checkout" Batch file should be created And Zip file should be downloaded into Local Directory
When I Unzip "Checkout" zip file
Then "Checkout" files should available in Local Directory
And "Checkout" image should displayed And "For Comment Incorp." action should completed for selected Checkout files
When I switch to <B> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
And "Checkout" image should displayed And "For Comment Incorp." action should completed for selected Checkout files of user B
When I click on Project And select folder And click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select Checkout files from Local to upload
Then "Publish Documents" Checkout validation popup should opened
And "Sorry, the file you are trying to upload is checked out in same folder." validation message should displayed
When I close "Publish Documents" popup of Checkout
And I switch to <A> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select files for perform Undo Checkout widget
And I right click and select context menu option "Undo Checkout"
Then "Confirmation Message" popup should open
When I click on "Continue" button of "Confirmation Message" popup
Then checkout files should set as "Undo Checkout" files
When I switch to <B> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I click on Project And select folder And click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select Checkout files from Local to upload
And I fill all mendatory fields with other "Revision" of Checkout files
And I click on Upload button
Then removable Checkout files should uploaded successfully With different Revision in DocListing page
Examples: 
|DC_Center|User_ID|Project_Name|A|B|
|UK|auto.nfpw_uk1@atest.com|AutomationTestProject|Auto_FWidget UK1|Auto_FWidget UK2|
#|US|auto.nfpw_us1@atest.com|Automatic_Test_US_WS|Auto_FWidget US1|Auto_FWidget US2|
#|AUS|auto.nfpw_aus1@atest.com|Automatic_Test_AUS_WS|Auto_FWidget AUS1|Auto_FWidget AUS2|