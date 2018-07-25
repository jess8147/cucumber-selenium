Feature: Files Tab Start Workflow

@P2T7
Scenario Outline: Files Tab Start Workflow from Right click
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I select "AutomationUploadFiles" folder AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
And I enter all mandatory fields to upload
And I click on Upload button
Then file should uploaded successfully for Start Workflow
When I right click on selected files And drag mouse to "New" And I click on "Project Form" into context click menu list
Then "Create Form" popup should open of Recent Forms
And only "Create Yes Ass Yes Form" form should displayed and other form should not displayed
When I search "Create Yes Ass Yes Form" Form Type And I click on searched formType
And I create new form on selected document using Start Workflow
Then "Associations" image should displayed in selected document
When I click on Associations image
Then "Attachments & Associations" popup should open
And created new Form should displayed on popup
When I click on Form "ID"
Then New tab should opened And selected Document should displayed as Associations on popup
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T7
Scenario Outline: Files Tab Start Workflow from More Options
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I select "AutomationUploadFiles" folder AND I click on "Add Files" button
Then "Upload" popup should open
When I clicked on "Select File (s)" button And I select one Files to upload
And I enter all mandatory fields to upload
And I click on Upload button
Then file should uploaded successfully for Start Workflow
When I select file AND click on "More  Options" and select "Project Form" from Options popup list
Then "Create Form" popup should open of Recent Forms
And only "Create Yes Ass Yes Form" form should displayed and other form should not displayed
When I search "Create Yes Ass Yes Form" Form Type And I click on searched formType
And I create new form on selected document using Start Workflow
Then "Associations" image should displayed in selected document
When I click on Associations image
Then "Attachments & Associations" popup should open
And created new Form should displayed on popup
When I click on Form "ID"
Then New tab should opened And selected Document should displayed as Associations on popup
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|