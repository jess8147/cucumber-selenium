Feature: Models Tab Permutations

@P2T7
Scenario Outline: Swapping Models from ListView to ThumbView and Vise Versa
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I click on ThumbView button
Then Model listing should be displayed as ThumbView in style
When I click on ListView button
Then Model listing should be displayed as ListView in style
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T7
Scenario Outline: Add \ Remove Model from Favourite models
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I get total "Favorite Models" count
And I search any one model and I set model "Add as " Favourite
And I click on LH-Panel Of Favorite Models tab
Then selected Model should be displayed on Favorite Models tabs
And It should be displayed as "Add as Favourite" And total Favorite Models Count should be increased
When I click on "All" Models tab
And I remove selected Model "Add as " Favourite into "Remove as " Favourite
And I click on LH-Panel Of Favorite Models tab
Then selected Model should not displayed into Favorite Models tab And total Favorite Models Count should be decreased
When I click on "All" Models tab
Then selected Model should displayed into All Models tab And It should be displayed as "Remove as Favourite"
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T7
Scenario Outline: View Model from Recent Models
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I Sorted "Access Date" Column in "descending" order list
And I get All Models Name List
And I click on Recent Models tab
And I get All Recent Models Name List And I search any one Model in "Recent" Models tab
Then searched Model should not displayed in Recent Models tab
When I click on "All" Models tab
And I search searched Model in "All" Models tab
Then New tab should be opened and searched Model should be View in new opened tab
When I close New opened tab
And I click on Recent Models tab
Then searched Model should be displayed in "Recent" Models tab
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T7
Scenario Outline: Models tab Hyperlinks on the Models list view
Given Project DC <DC_Center> is available
And I am already logged in
When I click on Models tab
Then I should redirect to "Models" tab
When I click on ListView button
And I search any one model into Models listing
And I check Model "Publisher" value on "Publisher" hyperlink
And I click on "Publisher" hyperlink of Model list View
#Then "Contact Details" popup should open
Then "Publisher" name should displayed on "Contact Details" popup
#When I closed opened "Contact Details" popup
When I check Model "Updated By" value on "Updated By" hyperlink
And I click on "Updated By" hyperlink of Model list View
#Then "Contact Details" popup should open
Then "Updated By" name should displayed on "Contact Details" popup
#When I closed opened "Contact Details" popup
When I check Model "Accessed By" value on "Accessed By" hyperlink
And I click on "Accessed By" hyperlink of Model list View
#Then "Contact Details" popup should open
And "Accessed By" name should displayed on "Contact Details" popup
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|