Feature: Create Model

@P1T1
Scenario Outline: Create Model
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Models tab
When I Click on "Add Models" button From LH Panel
Then "Add Model" popup should open on Models Page
When I filled all mendatory fields And click on "Save" button
Then Model should be created
And Model should be available in Models listing
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|
