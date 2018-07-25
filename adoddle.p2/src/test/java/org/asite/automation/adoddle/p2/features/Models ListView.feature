Feature: Models tab ListView

@P2T6
Scenario Outline: Models tab ListView
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I Click on "Add Models" button From LH Panel for "ListView" listing
Then "Add Model" popup should open
When I filled all mendatory fields And click on "Save" button
Then Model should be created And should be available in Models "ListView" listing
And loggedIn UserName should be set to "Publisher", "Updated By" AND "Accessed By" hyperlink of model And CurrentDate should be set to "Date", "Updated Date" AND "Access Date"
When I login with secondary user
And I click on Models tab
Then Model All coloums value should be same as First User all coloums value for "ListView" listing
When I right click on "Model Name" Model And click on "Upload"
Then "Upload Model File" popup should open
When I select Wrokset And select "IFC File" from local And click on "Upload" button
Then "Activity Centre" popup should open for IFC upload
And "Merging" AND "Loading" progress should be available on "Activity Centre" popup
When I click on "OK" button of Activity Centre popup
Then loggedIn UserName should be set to "Updated By" AND "Accessed By" hyperlink And "Accessed By" AND "Access Date" also should be changed but "Publisher" hyperlink and "Date" coloum should not changed
When I login with third user
And I click on Models tab
Then Model All coloums value should be same as Secondary User all coloums value
When I click on Model Name
Then New tab should be opened and Model should be View in new opened tab
When I close New opened tab
Then loggedIn UserName should be set to "Accessed By" hyperlink AND "Access Date" should be changed but "Publisher" AND "Updated By" hyperlink and "Date" AND "Access Date" should not changed
When I login with forth user
And I click on Models tab
Then Model All coloums value should be same as Third User all coloums value
When I right click on "Model Name" Model And click on "Properties"
Then "Properties" popup should open
When I edit Model Name And click on "Save" button
And I right click on "Edit Model Name" Model And click on "Properties"
Then Model edited all fields should displayed in "Properties" popup
When I close Properties popup
Then Edited Model should be displayed in Model "ListView" listing
And loggedIn User should be set to "Updated By" AND "Accessed By" hyperlink and "Updated Date" AND "Access Date" should be changed but "Publisher" AND "Date" should not changed
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T6
Scenario Outline: Models tab ListView - Deactivate Testdata
Given Project DC <DC_Center> is available
And I am already logged in
And I deactivate Mapping Folder
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|