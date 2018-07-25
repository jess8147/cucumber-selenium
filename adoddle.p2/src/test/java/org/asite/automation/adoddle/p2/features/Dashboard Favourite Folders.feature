Feature: Favourite Folders Widgets

@P2T7
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
#And I click "Files" tab
#Then I should redirect to "Files" tab
And I remove TestData "Automation_Favourite_Folder" from Favourite Folder
Examples:
|DC_Center|User_ID|
|UK|auto.favfolders_uk@atest.com|
#|US|auto.favfolders_us@atest.com|
#|AUS|auto.favfolders_aus@atest.com|

@P2T7
Scenario Outline: Set Folder as Favourite Folder and Verify Incomplete Actions Count of Favourite Folder
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I click on "Set your Favourite Folders" Favourite Folders Widget hyperlink
Then I should redirect to "Files" tab
Given I have focus on <Project> project
When I set "Automation_Favourite_Folder" folder "Mark As Favourite"
Then "Automation_Favourite_Folder" folder "set as" Favourite folder
Given I am on "Dashboard" tab
Then "Automation_Favourite_Folder" should "displayed" in Favourite Folders Widgets list
When I click on "Automation_Favourite_Folder" dashboard Favourite Folders Widgets
Then I should redirect to "Files" tab
And I only "Include Subfolders" filter with "Yes" filter type should selected
When I set "Task Status" filter as "Incomplete" actions And "Recipient" filter as <Recipient_Name>
And I get total Incomplete Actions count of Files tab Favourite Folder
And I am on "Dashboard" tab
Then Files tab incomplete actions count should verify with Dashboard Favourite Folder incomplete action counts
When I click on Incomplete Actions count of Dashboard Favourite Folder
Then I should redirect to "Files" tab
#And "Task Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> AND "Include Subfolders" filter with "Yes" AND "Revisions" filters should set
And "Revisions" filter with "Current Set,Superseded" AND "Task Status" filter with "Incomplete" AND "Recipient" filter with <Recipient_Name> AND "Include Subfolders" filter with "Yes" filters should set
And Dashboard Favourite Folder incomplete actions count should verify with Files tab favourite folder incomplete actions count
When I remove "Automation_Favourite_Folder" folder "Remove as Favourite"
Then "Automation_Favourite_Folder" folder "remove from" Favourite folder
Given I am on "Dashboard" tab
Then "Automation_Favourite_Folder" should "not displayed" in Favourite Folders Widgets list
Examples:
|DC_Center|User_ID|Recipient_Name|Project|
|UK|auto.favfolders_uk@atest.com|Automation FavFolders_UK|AutomationTestProject|
#|US|auto.favfolders_us@atest.com|Automation FavFolders_US|Automatic_Test_US_WS|
#|AUS|auto.favfolders_aus@atest.com|Automation FavFolders_AUS|Automatic_Test_AUS_WS|

@P2T7
Scenario Outline: Verify Favourite Folder Widgets Incomplete Actions Count with Color code
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I create Folder and folder with Sub-Folder And Set parent folder as "Mark As Favourite"
Then Parent folder "set as" Favourite folder
Given I am on "Dashboard" tab
Then Parent folder should "displayed" in Favourite Folders Widgets list
# "Assign Incomplete Action to Sub Folder with More then Week due date"
When I click on Parent folder of dashboard Favourite Folders Widgets
Then I should redirect to "Files" tab
When I upload more then one files in "Sub folder"
And I click on Upload button
Then files should uploaded successfully in "Sub folder"
When I select "any" two files for "Sub folder" and right click and select "Share" and "Distribute Files"
Then "Distribute" popup should open
When I distribute "For Comment" and "For Status Change" action with "10" due days And "For Information" action without due date to <User_ID> user
And I click on "Distribute" button
And I get total Incomplete actions count of Files tab Favourite folder
And I am on "Dashboard" tab
Then I verify Dashboard Favourite folder Count with Files tab Favourite folder Count And Color of Incomplete actions of Favourite folder set as "green"
# "Assign Incomplete Action to Parent Folder with More then Week due date"
When I click on Parent folder of dashboard Favourite Folders Widgets
Then I should redirect to "Files" tab
When I upload more then one files in "Parent folder"
And I click on Upload button
Then files should uploaded successfully in "Parent folder"
When I select "any" two files for "Parent folder" and right click and select "Share" and "Distribute Files"
Then "Distribute" popup should open
When I distribute "For Comment" and "For Status Change" action with "10" due days And "For Information" action without due date to <User_ID> user
And I click on "Distribute" button
And I get total Incomplete actions count of Files tab Favourite folder
And I am on "Dashboard" tab
Then I verify Dashboard Favourite folder Count with Files tab Favourite folder Count And Color of Incomplete actions of Favourite folder set as "green"
# "Assign Incomplete Action to Sub Folder within Week due date"
When I click on Parent folder of dashboard Favourite Folders Widgets
Then I should redirect to "Files" tab
When I select "other" two files for "Sub folder" and right click and select "Share" and "Distribute Files"
Then "Distribute" popup should open
When I distribute "For Action" and "For Acknowledgement" action with "2" due days to <User_ID> user
And I click on "Distribute" button
And I get total incomplete actions count "Sub folder" of Favourite folder of Files tab
And I am on "Dashboard" tab
Then I verify Dashboard Favourite folder Count with Files tab Favourite folder Count And Color of Incomplete actions of Favourite folder set as "amber"
# "Assign Incomplete Action to Parent Folder within Week due date"
When I click on Parent folder of dashboard Favourite Folders Widgets
Then I should redirect to "Files" tab
When I select "other" two files for "Parent folder" and right click and select "Share" and "Distribute Files"
Then "Distribute" popup should open
When I distribute "For Action" and "For Acknowledgement" action with "2" due days to <User_ID> user
And I click on "Distribute" button
And I get total incomplete actions count "Parent folder" of Favourite folder of Files tab
And I am on "Dashboard" tab
Then I verify Dashboard Favourite folder Count with Files tab Favourite folder Count And Color of Incomplete actions of Favourite folder set as "amber"
# "Complete Both folders within Week actions and Verify Count and Color"
When I click on Incomplete Actions count of Dashboard Favourite Folder
Then I should redirect to "Files" tab
When I select all files and perform right click And select "Tasks" and "For Action"
Then "For Action" popup should open
When I select "Click here to complete your Action" checkbox AND I entered Remarks input text "For Action"
And I click on "Submit" Button of popup
When I select all files and perform right click And select "Tasks" and "For Acknowledgement"
Then "For Acknowledgement" popup should open
When I click on "Acknowledge Receipt" link of popup
And I get total Incomplete Actions count of Files tab Favourite Folder and Sub folder
And I am on "Dashboard" tab
Then Dashboard Incomplete Actions count should decreased And match with Files Incomplete Actions Count And Color of Incomplete actions of Favourite folder set as "green"
# "Upload File Second Revision With Actions And Verify"
When I click on Incomplete Actions count of Dashboard Favourite Folder
Then I should redirect to "Files" tab
When I get first file Name and Revision and Upload using next Revision with <User_ID> and "For Information" action
And I click on Upload button
Then File should uploaded Successfully And both Revision files should displayed and "For Information" action assign to latest Revision file
Given I am on "Dashboard" tab
Then Dashboard Incomplete Actions count should increased
Examples:
|DC_Center|User_ID|Recipient_Name|
|UK|auto.favfolders_uk@atest.com|Automation FavFolders_UK|
#|US|auto.favfolders_us@atest.com|Automation FavFolders_US|
#|AUS|auto.favfolders_aus@atest.com|Automation FavFolders_AUS|

@P2T7
Scenario Outline: Filter Preferences and Favourite Folders
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "set" Filter Preferences
And I click on Parent folder of dashboard Favourite Folders Widgets
Then I should redirect to "Files" tab
When I remove all selected filters
And I upload more then one files using "For Review" Status
#When I set "Status" filter with "For Review" status type
When I add "Status" filter with "For Review" subfilter type
Then "For Review" status all files should displayed
When I search any one uploaded file that have "For Review" status
And I am on "Dashboard" tab
And I click on Parent folder of dashboard Favourite Folders Widgets
Then I only "Status" filter with "For Review" filter type should selected
And previous searched file should displayed
Given I am on "Dashboard" tab
When I click on Incomplete Actions count of Dashboard Favourite Folder
Then I should redirect to "Files" tab
#And "Task Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> AND "Include Subfolders" filter with "Yes" AND "Revisions" filters should set
And "Revisions" filter with "Current Set,Superseded" AND "Task Status" filter with "Incomplete" AND "Recipient" filter with <Recipient_Name> AND "Include Subfolders" filter with "Yes" filters should set
And previous search files filter should reset and all incomplete files should displayed
Given I am on "Dashboard" tab
And I click on Parent folder of dashboard Favourite Folders Widgets
#Then I verify Previous all filters "Task Status" with "Incomplete", "Recipient Name" with <Recipient_Name>, "Include Subfolders" with "Yes" AND "Revisions" filters should set
Then I verify Previous all filters "Revisions" filter with "Current Set,Superseded" AND "Task Status" filter with "Incomplete" AND "Recipient" filter with <Recipient_Name> AND "Include Subfolders" filter with "Yes" filters should set
And Previous Reset search files filter and all incomplete files should displayed
When I "remove" Filter Preferences
Examples:
|DC_Center|User_ID|Recipient_Name|
|UK|auto.favfolders_uk@atest.com|Automation FavFolders_UK|
#|US|auto.favfolders_us@atest.com|Automation FavFolders_US|
#|AUS|auto.favfolders_aus@atest.com|Automation FavFolders_AUS|

@P2T7
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
And I removed TestData Favourite Folder
Examples:
|DC_Center|User_ID|
|UK|auto.favfolders_uk@atest.com|
#|US|auto.favfolders_us@atest.com|
#|AUS|auto.favfolders_aus@atest.com|