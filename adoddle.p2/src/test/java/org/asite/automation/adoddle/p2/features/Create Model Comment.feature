Feature: Create Model Comment

@P2T7
Scenario Outline: Create/Verify/download Comment using ModelView
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I search Model And click on Model
Then New tab should be opened AND Model should be View
When I opened Views Manager for current model
And I select modelView And "Create Discussion" on selected View Manager
Then "Create Discussion" dropdown popup should open
When I select user into "To*" text field And I entered comment "Title" AND comment "Description"
And I click on "Attachment" button for Form
And I attach more than one documents And I click on "Attach" Button for Form
And I click on "Associate" button for Form
When I click on "Associate Docs" Button for file associate for "Comment"
And I associate more than one Files And I click on "Save" Button for Form of <Project> project of "AutomationUploadFiles" folder
And I click on "Associate" button for Form
When I click on "Associate aMessages" Button for comment associate for "Comment"
And I associate more than one Discussions And I click on "Save" Button for Form
And I click on "Associate" button for Form
When I click on "Associate Apps" Button for form associate for "Form"
And I associate more than one Forms And I click on "Save" Button for Form
And I click on "Associate" button for Form
When I click on "Associate Views" Button for Model View for "Form"
And I associate all existing Views And I click on "Save" Button for Form
And I Click on "Submit" button of Create Discussion page
Then Comment should successfully created and displayed in "aMessages" dropdown top list
When I click on Attach Associate hyperlink of created comment
Then All Attached AND Associated "Attachments", "Files", "aMessages", "Apps" AND "Views" should be successfully done with selected All fields for "Comment"
#When I click on Associated all views
#Then It should be opened and viewed in new window
When I goto "Attachments" link I select all files AND click on Download for "Comment"
And I select all checkboxes AND click on download button of Download popup for "Attachments"
Then Batch file should be created And "Attachments" Zip file should be downloaded into Local Directory
When I Unzip downloaded "Attachments" zip file
Then all "Attachments" should be available into Local Directory
When I goto "Files" link I select all files AND click on Download for "Comment"
And I select all checkboxes AND click on download button of Download popup for "Files"
Then Batch file should be created And "Associations" Zip file should be downloaded into Local Directory
When I Unzip downloaded "Associations" zip file
Then all "Associations" should be available into Local Directory
When I close View Model comment window
And I login using secondary user And I goto "Discussions" tab
Then created comment should available and displayed as "Un Read" on "Discussions" tab
Examples: 
|DC_Center|Project|
|UK|AutomationTestProject|
#|US|Automatic_Test_US_WS|
#|AUS|Automatic_Test_AUS_WS|