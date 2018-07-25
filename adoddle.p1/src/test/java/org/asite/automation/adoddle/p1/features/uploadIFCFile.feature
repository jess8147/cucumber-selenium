Feature: Upload-IFC-Files In Model

@P1T4
Scenario Outline: Upload IFC file in model
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Models tab
And I have atleast one model on Models listing
And I have "List View" on models listing
When I right click on "Model Name" Model And click on "Upload"
Then "Upload Model File" popup should open for IFC Upload
When I select Wrokset And select "IFC File" from local And click on "Upload" button
Then Upload IFC file should be started
And "Activity Centre" popup should open for IFC upload
And "Upload" progress should be available on "Activity Centre" popup
And Loading image for "Merging" should be available on "Activity Centre" popup
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|