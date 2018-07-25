Feature: Create-Comment

@P1T4
Scenario Outline: Create Comment with Multiple Attachments - Associations
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Workspace home page of workspace <Project>
And I have clicked on "All Workspace Documents"
Then Document listing should open with all documents of workspace
When I click on "Click here to view Document Distribution history" of any document
Then "Document Distribution" window page should open
When I click on "comments/associations" link text
#When I click on link with text "comments/associations"
Then "comments and associations" window page should open
When I click on icon with title "Add a Comment"
Then "create new comment on:" page should open in new tab
Given I have entered "Automation-Comment-Title" in Title
And I have entered "Automation-Test-Comment" into Comment
And I have selected User "Automation Secondary" into Document Distribution with Action "For Information"
And I have associated atleast one document to comment
And I have attached atleast one external document to comment
And I have associated atleast one form to comment
When I click on "Send" button on create comment window
Then Comment should be successfully created
And Comment should be available in comments tab into "comments and associations" page
When I click on "View Comment Details" of comment from "comments and associations" tab
Then comment detail window should open
And Associated documents AND discussions should be available on comment details
And Associated forms should be available on comment details
And Attached docs should be uploaded and available on comment details
Examples:
|DC_Center|Project|
|UK|AutomationClassic_UK_WS|
#|US|AutomationClassic_US_WS|
#|AUS|AutomationClassic_AUS_WS|