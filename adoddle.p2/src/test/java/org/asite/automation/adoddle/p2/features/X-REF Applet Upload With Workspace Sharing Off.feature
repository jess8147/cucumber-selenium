Feature: X-REF Applet Upload With Workspace Sharing Off

Background:
Given Script is Node specific

@exclude
Scenario Outline: Create Workspace AND Directories
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
When I click on "Add Project" in LH panel
Then "Create Project" popup should open
When I have entered <Project_Name> AND Description AND I have selected Geography as <Geography>
And I turned off "Sharing X-Refs to Project Team" successfully
And I click on "Create" button on popup
Then Project should be created AND I should be redirected on "Projects" tab AND Project with ProjectName should be available in project listing
Given I am on "Files" tab
When I Context Click on <Project_Name>
And drag mouse to "New" AND Click on "Folder"
Then "Add Folder" popup should open
And I put folderName as "XRef_AutoPrimaryFolder" AND I Click on Create button
Then "XRef_AutoPrimaryFolder" should be created AND I validate in Folder Tree
And Again I Right Click on Project as <Project_Name>
And drag mouse to "New" AND Click on "Folder"
Then "Add Folder" popup should open
And I put folderName as "XRef_AutoSecondryFolder" AND I Click on Create button
Then "XRef_AutoSecondryFolder" should be created AND I validate in Folder Tree
Examples: 
|DC_Center|Project_Name|Geography|
|UK|AutomationTestProject|United Kingdom (EU01)|
#|US|Automatic_Test_US|North America (US01)|
#|AUS|Automatic_Test_AUS|Australia (AU01)|

@exclude
Scenario Outline: Upload X-Ref With Folder Sharing Off
Given Project DC <DC_Center> is available
And Script is "IE" browser specific
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus Project <Project_Name>
And I have focus folder "XRef_AutoPrimaryFolder"
When I click on "Add Files" button on file Listing
Then "Upload" popup should open
When I click link for "Applet Upload"
Then Applet upload popup should open
When I click on Add Files button on Applet upload popup AND uploadType as "XRefSimpleAppletUpload"
Then Popup "Open" of Local file System should Open
And I have selected "Parent.dwg" X-Ref With Multiple File
And I have clicked on "Enter Document Details"  button
Then Popup "Asite" with Header "Manage-X-refs" should Open
And I have unchecked one of the child of "Parent.dwg" X-Ref File
And I click "Continue" Button
Then All Attributes of "Parent.dwg" X-Ref File should load successfully
And I Entered All mandatory Attributes of "Parent.dwg" X-Ref file
And I click on Apply To All button
Then All Attributes should be filled with values AND "Doc Ref" should be filled with combine custom attributes
And I select "Do Not Distribute" in Distribute dropdown
And click on "Start Upload" button
And I Reset current Browser to "CHROME" in <DC_Center> scenario "XRefSimpleAppletUpload"
Given I am on "Files" tab
And I have focus Project <Project_Name>
And I have focus folder "XRef_AutoPrimaryFolder"
Then I validated "Parent.dwg" as "newVersion" AND "Child.dwg" as "newRevision" successfully in Listing AND in Viewer
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@exclude
Scenario Outline: Upload X-Ref With Folder Sharing Off
Given Project DC <DC_Center> is available
And Script is "IE" browser specific
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus Project <Project_Name>
And I have focus folder "XRef_AutoSecondryFolder"
When I click on "Add Files" button on file Listing
Then "Upload" popup should open
When I click link for "Applet Upload"
Then Applet upload popup should open
When I click on Add Files button on Applet upload popup AND uploadType as "XRefSimpleAppletUpload"
Then Popup "Open" of Local file System should Open
And I have selected "Parent.dwg" X-Ref With Multiple File
And I have clicked on "Enter Document Details"  button
Then Popup "Asite" with Header "Manage-X-refs" should Open
And I have unchecked one of the child of "Parent.dwg" X-Ref File
And I click "Continue" Button
Then All Attributes of "Parent.dwg" X-Ref File should load successfully
And I Entered All mandatory Attributes of "Parent.dwg" X-Ref file
And I click on Apply To All button
Then All Attributes should be filled with values AND "Doc Ref" should be filled with combine custom attributes
And I select "Do Not Distribute" in Distribute dropdown
And click on "Start Upload" button
And I Reset current Browser to "CHROME" in <DC_Center> scenario "XRefSimpleAppletUpload"
Given I am on "Files" tab
And I have focus Project <Project_Name>
And I have focus folder "XRef_AutoSecondryFolder"
Then I validated "Parent.dwg" as "newVersion" AND "Child.dwg" as "newRevision" successfully in Listing AND in Viewer
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@exclude
Scenario Outline: Upload X-Ref Revision With Folder Sharing Off
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus Project <Project_Name>
And I have focus folder "XRef_AutoPrimaryFolder"
And I have Search file "Parent-XRef" file AND get Current version AND Current "Child_X-Ref" revision
And I Reset current Browser to "IE" in <DC_Center> scenario "XRefUploadWithoutCustomAttributes"
Given I am on "Files" tab
And I have focus Project <Project_Name>
And I have focus folder "XRef_AutoPrimaryFolder"
When I click on "Add Files" button on file Listing
Then "Upload" popup should open
When I click link for "Applet Upload"
Then Applet upload popup should open
When I click on Add Files button on Applet upload popup AND uploadType as "XRefUploadWithoutCustomAttributes"
Then Popup "Open" of Local file System should Open
And I have selected "Parent.dwg" X-Ref With Multiple File
And I have clicked on "Enter Document Details"  button
Then Popup "Asite" with Header "Manage-X-refs" should Open
And I have unchecked one of the child of "Parent.dwg" X-Ref File
And I click "Continue" Button
Then All Attributes of "Parent.dwg" X-Ref File should load successfully
And I Entered All mandatory Attributes of "Parent.dwg" X-Ref file
And I click on Apply To All button
Then All Attributes should be filled with values AND "Doc Ref" should be filled with combine custom attributes
And I select "Do Not Distribute" in Distribute dropdown
And click on "Start Upload" button
And I Reset current Browser to "CHROME" in <DC_Center> scenario "XRefUploadWithoutCustomAttributes"
Given I am on "Files" tab
And I have focus Project <Project_Name>
And I have focus folder "XRef_AutoPrimaryFolder"
Then I validated "Parent.dwg" as "UpdatedVersion" AND "Child.dwg" as "UpdatedRevision" successfully in Listing AND in Viewer
Examples: 
|DC_Center|Project_Name|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|

@exclude
Scenario Outline: Clean_Up Operation
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
And I Context Click "Cloned_Project" AND Break Inheritance and I deactivated project
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|