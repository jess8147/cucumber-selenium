Feature: Public and Private Folders with Files

@P2T2
Scenario Outline: Hit the link of Public folder in your browser and verify the public folder page with public folder Files
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I click on <Project_Name> project
# ": Set Folders as Public Folder :" #
And I "set" and Verify <Public_Folders> folders as public and private
And I get total Files name and Count of <Public_Folders> folders
# ": Parent Public Folder With Verification :"
And right click on "ParentFolder" AND Click on "Edit Folder"
Then "Edit Folder" popup should open
When I copied public link of "ParentFolder" And I closed "Edit Folder" popup
And logout from Adoddle
And paste copied link on URL AND hit the URL
Then Folder should be open as public with <Project_Name> project
And Only FolderA, FolderC and FolderE files should displayed And FolderB and FolderD files should not displayed
When I opened new tab I hit Asite Login URL
And I Login for <Non_Admin_User1> User
And I opened new "third" 3 tab
And paste copied link on URL AND hit the URL
Then I should redirect to "Files" tab
And ParentFolder selected as default And All Folders only "Public" Files should displayed And "Private" Files should not displayed
When I closed "third" 3 tab
# ": Sub Public Folder With Verification :"
When logout from Adoddle
And I login using loggedIn User
Given I am on "Files" tab
And I click on <Project_Name> project
And right click on "SubPublicFolder" AND Click on "Edit Folder"
Then "Edit Folder" popup should open
When I copied public link of "SubPublicFolder" And I closed "Edit Folder" popup
And logout from Adoddle
And paste copied link on URL AND hit the URL
Then Folder should be open as public with <Project_Name> project
And Only FolderC and FolderE files should displayed And FolderA, FolderB and FolderD files should not displayed
When I click on "Login" link of Public Folder
And I Login for <Non_Admin_User2> User
Then I should redirect to "Files" tab
And SubPublicFolder selected as default And Folder and its child All Folders only "Public" Files should displayed And "Private" Files should not displayed
# ": No Access User With Public Folder Verification :"
When logout from Adoddle
And I login using loggedIn User
Given I am on "Files" tab
And I click on <Project_Name> project
And right click on "ParentFolder" AND Click on "Edit Folder"
Then "Edit Folder" popup should open
When I copied public link of "ParentFolder" And I closed "Edit Folder" popup
And logout from Adoddle
And I Login for <Non_Admin_User3> User
And I opened new "second" 2 tab
And paste copied link on URL AND hit the URL
Then "Unauthorised Access!" page should displayed for NoAccess User
When I closed "second" 2 tab
# ": No Access Override Admin User With Public Folder Verification :"
When I login using loggedIn User
And logout from Adoddle
When I Login for <Non_Admin_User4> User
And I opened new "second" 2 tab
And paste copied link on URL AND hit the URL
Then I should redirect to "Files" tab
And ParentFolder selected as default And only ParentFolder all public and private Files should displayed
# ": Set Folders as Private Folder :" #
When I login using loggedIn User
Given I am on "Files" tab
And I click on <Project_Name> project
And I "Reset" and Verify <Public_Folders> folders as public and private
# ": Private Folder With Verification :"
When right click on "ParentFolder" AND Click on "Edit Folder"
And I copied public link of "ParentFolder" And I closed "Edit Folder" popup
And logout from Adoddle
When I Login for <Non_Admin_User1> User
And I opened new "second" 2 tab
And paste copied link on URL AND hit the URL
Then I should redirect to "Files" tab
And ParentFolder selected as default And All Folders only "Public" Files should displayed And "Private" Files should not displayed
When I closed "second" 2 tab
And logout from Adoddle
When I Login for <Non_Admin_User3> User
And I opened new "second" 2 tab
And paste copied link on URL AND hit the URL
Then "Unauthorised Access!" page should displayed for NoAccess User
Examples: 
|DC_Center|Project_Name|Public_Folders|Non_Admin_User1|Non_Admin_User2|Non_Admin_User3|Non_Admin_User4|
|UK|AutomationTestProject|Public_Folder_A,Public_Folder_B,Public_Folder_C,Public_Folder_D,Public_Folder_E|auto.non_admin1@atest.com|auto.non_admin2@atest.com|auto.non_admin3@atest.com|auto.non_admin4@atest.com|
#|US|Automatic_Test_US_WS|Public_Folder_A,Public_Folder_B,Public_Folder_C,Public_Folder_D,Public_Folder_E|auto.non_admin1@atest.com|auto.non_admin2@atest.com|auto.non_admin3@atest.com|auto.non_admin4@atest.com|
#|AUS|Automatic_Test_AUS_WS|Public_Folder_A,Public_Folder_B,Public_Folder_C,Public_Folder_D,Public_Folder_E|auto.non_admin1@atest.com|auto.non_admin2@atest.com|auto.non_admin3@atest.com|auto.non_admin4@atest.com|