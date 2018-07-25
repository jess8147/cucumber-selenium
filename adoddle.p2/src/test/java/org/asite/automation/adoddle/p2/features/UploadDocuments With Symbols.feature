Feature: Upload Documents With Special Characters

@P2T2
Scenario Outline: Upload Documents With Symbols
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Files" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
When I have published Multiple documents with different docRef docStatus docRef And DocTitle
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace> 
Then All documents should published with correct docStatus docPoi docRef And DocTitle successfully
And I have created comment with title as "AutoTest!@$^&()_\:*?<>|;%~Comment" successfully
Given I am on "Discussions" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
And I have validated comment "AutoTest!@$^&()_\:*?<>|;%~Comment" successfully
Given I am on "Files" tab
And I have search document AND click on file name to open document in file viewer successfully
When I click "Start a Discussion" link of file
Then aMessages form loaded successfully for comment creation
And I have enter comment title as "AutoTest!@$^&()_\:*?<>|;%~CommentViewer" AND all mandatory attributes for comment successfully
And I click on "Attachment" button for file
And I have attached more than files having special characters successfully
And I click on "Associate" button for file
When I click on "Associate Docs" Button for file associate for "File"
And I associate more than one Files having special characters in "AutoTest~`!@$%^&()-+_={};[].Folder" folder in workspace <Workspace>
And I click on "Submit" button AND close file window successfully
Given I am on "Discussions" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
And I have validated comment "AutoTest!@$^&()_\:*?<>|;%~Comment" successfully
Given I am on "Project Forms" tab
And I have focus on Project as <Workspace> AND I click on Folder "Communications" AND I click on "Request For Information" Form type
When I click on "Create Form" button on Project Forms page
Then Pop-up "ORI:Create Form" should open
And I have created form "AutoTest!@$^&()_+-{}=[]`"/\:*?;%~form" AND validated in listing successfully
Given I am on "Files" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
When I have published Multiple documents revisions with different docRef docStatus docRef And DocTitle
And I have downloaded all published documents revisions in local directory successfully
Given I am on "Dashboard" tab
When I logged in as <UserA>
Then I should re-directed to Dashboard of user "RS Bloggs"
Given I am on "Files" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
Then I have validated published Documents with action as "For Comment" successfully
When I logged in as <UserB>
Then I should re-directed to Dashboard of user "RFI Builder"
Given I am on "Files" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
Then I have validated published Documents with tasks as "For Information" successfully
When I logged in as <UserC>
Then I should re-directed to Dashboard of user "TC Bloggs"
Given I am on "Files" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
Then I have validated published Documents with tasks as "For Information" successfully
When I logged in as <UserD>
Then I should re-directed to Dashboard of user "JK Bloggs"
Given I am on "Files" tab
And I have focus on folder "AutoTest~`!@$%^&()-+_={};[].Folder" in workspace <Workspace>
Then I have validated published Documents with tasks as "For Information" successfully
And I Deactivated all Test files
Examples: 
|DC_Center|Workspace|UserA|UserB|UserC|UserD|
|UK|AutomationTest!@$^&()+-{}=[]`UK_Workspace.|RS Bloggs|RFI Builder|TC Bloggs|JK Bloggs|
#|US|AutomationTest!@$^&()+-{}=[]`US_Workspace.|RS Bloggs|RFI Builder|TC Bloggs|JK Bloggs|
#|AUS|AutomationTest!@$^&()+-{}=[]`AUS_Workspace.|RS Bloggs|RFI Builder|TC Bloggs|JK Bloggs|