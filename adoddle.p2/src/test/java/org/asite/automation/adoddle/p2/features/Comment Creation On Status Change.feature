Feature: Comment Creation On Status Change

@P2T4
Scenario Outline: Document Status Change Via Right Click
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
And I have focus on "AutomationUploadFiles" folder
And I have uploaded "1" documents
When I change status of uploaded document via "Right Click"
Then Comment should get created for status change
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T4
Scenario Outline: Document Status Change Via More Options
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
And I have focus on "AutomationUploadFiles" folder
And I have uploaded "1" documents
When I change status of uploaded document via "More Options"
Then Comment should get created for status change
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|