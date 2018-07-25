Feature: Models tab ThumbView

@P2T6
Scenario Outline: Models tab ThumbView
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I Click on "Add Models" button From LH Panel for "ThumbView" listing
Then "Add Model" popup should open
When I filled all mendatory fields And click on "Save" button
Then Model should be created And should be available in Models "ThumbView" listing
And loggedIn UserName should be set to "Publisher", "Updated By" AND "Accessed By" hyperlink of model And CurrentDate should be set to "Date", "Updated Date" AND "Access Date"
When I login with secondary user
And I click on Models tab
Then Model All coloums value should be same as First User all coloums value for "ThumbView" listing
When I mousehover on Model and click on "Upload File"
Then "Upload Model File" popup should open
When I select Wrokset And select "IFC File" from local And click on "Upload" button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
Then loggedIn UserName should be set to "Updated By" AND "Accessed By" hyperlink And "Accessed By" AND "Access Date" also should be changed but "Publisher" hyperlink and "Date" coloum should not changed
When I login with third user
And I click on Models tab
Then Model All coloums value should be same as Secondary User all coloums value
When I mousehover on Model and click on "View Model" link of Model
Then New tab should be opened and Model should be View in new opened tab
When I close New opened tab
Then loggedIn UserName should be set to "Accessed By" hyperlink AND "Access Date" should be changed but "Publisher" AND "Updated By" hyperlink and "Date" AND "Access Date" should not changed
When I login with forth user
And I click on Models tab
Then Model All coloums value should be same as Third User all coloums value
When I mousehover on Model and click on "Properties" link
Then "Properties" popup should open
When I edit Model Name And click on "Save" button
And I mousehover on edited Model and click on "Properties" link
Then Model edited all fields should displayed in "Properties" popup
When I close Properties popup
Then Edited Model should be displayed in Model "ThumbView" listing
And loggedIn User should be set to "Updated By" AND "Accessed By" hyperlink and "Updated Date" AND "Access Date" should be changed but "Publisher" AND "Date" should not changed
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T6
Scenario Outline: Models tab ThumbView - Deactivate Testdata
Given Project DC <DC_Center> is available
And I am already logged in
And I deactivate Mapping Folder
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|