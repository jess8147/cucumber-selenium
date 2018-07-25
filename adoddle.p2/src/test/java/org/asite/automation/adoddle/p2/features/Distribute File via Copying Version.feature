Feature: Distribute File via Copying Version

@P2T3
Scenario Outline: Publish File Revisions And Distribute via Copying Version
Given Project DC <DC_Center> is available
And I am already logged in 
Given I am on "Files" tab
And I have focus on <Project_Name> project
And I have focus on Folder "AutomationUploadFiles"
And I click on Add Files button
Then "Upload" popup should open
And I click on "Select File (s)" button AND I have selected multiple file from Local
And I click on "Distribute Files" button
And I have Distributed files to users "TC Bloggs" "RFI Bloggs" AND "PA Bloggs" with different actions AND Due Date
And I have enter all mandatory attributes for all files successfully
When I click on "Upload" button
Then All files should uploaded successfully AND Available in file listing
And I have Search "AutoTestDistributeFile" file AND Go to Audit Trail AND Validated due date for actions successfully
When I switch to <Doc_Controller> user
Given I am on "Files" tab
And I have focus on Folder "AutomationUploadFiles"
When I have Search files with name as "AutoTestDistributeFile"
Then All files with docRef as "AutoTestDistributeFile" should Available in listing
And I have Selected multiple files AND I Context click on doc title
When I select context click menu option "Share" AND "Distribute Files"
Then Popup "Distribute" should open
And I have Distributed files to users "RFI Builder" "PA Builder" AND "Auto RFI" with different actions AND Due Date
And I click on "Distribute" button
Then all selected files should distributed successfully to respective users 
And I have selected different file AND I Context click on doc title
When I select context click menu option "Share" AND "Distribute Files"
Then Popup "Distribute" should open
And I have selected users as "Automation Primary" AND "Auto Test" with different actions AND Due Date
And I click on "Distribute" button
Then all selected files should distributed successfully to respective users 
When I switch to <Publisher> user
Given I am on "Files" tab
And I have focus on Folder "AutomationUploadFiles"
And I click on Add Files button
Then "Upload" popup should open
When I click on "Select File (s)" button AND I have selected uploaded file revisions from Local
And I click on "Distribute Files" button
When I click icon for copying user from previous revision on Popup "Upload"
Then Users as "TC Bloggs" "RFI Bloggs" AND "PA Bloggs" with respective actions AND Due Date should copied successfully
And I enter all mandatory attributes for all revisions sucessfully 
And I click on "Upload" button
Then All Revisions of file "AutoTestDistributeFile" should uploaded successfully
When I switch to <Doc_Controller> user
Given I am on "Files" tab
And I have focus on Folder "AutomationUploadFiles"
When I have Search file with title as "AutoTestDistributeFile"
Then All latest Revisions of "AutoTestDistributeFile" should Available in listing
And I Select All file revisions AND I Context click on doc title
When I select context click menu option "Share" AND "Distribute Files"
Then Popup "Distribute" should open
When I click icon for copying user from previous revision on Popup "Distribute"
Then Users "RFI Builder" "PA Builder" "Auto RFI" "Automation Primary" AND "Auto Test" with respective actions AND Due Date should copied from previous version successfully
And I click on "Distribute" button
Then All Revisions of file "AutoTestDistributeFile" should distributed successfully to respective user
#And I have Search "AutoTestDistributeFile" file AND Go to Audit Trail AND Validated due date for actions successfully
Examples: 
|DC_Center|Project_Name|Doc_Controller|Publisher|
|UK|AutomationTestProject|TC Bloggs|Automation UKP2|
#|US|Automatic_Test_US_WS|TC Bloggs|Automation USP2|
#|AUS|Automatic_Test_AUS_WS|TC Bloggs|Automation AUSP2|

@exclude
Scenario Outline: Clean Up Operation
Given Project DC <DC_Center> is available
And I am already logged in 
Given I am on "Files" tab
And I have Deactivated All files name with title "AutoTestDistributeFile"
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|