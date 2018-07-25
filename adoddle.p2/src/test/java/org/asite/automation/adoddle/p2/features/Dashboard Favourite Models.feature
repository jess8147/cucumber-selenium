Feature: Dashboard Favourite Models 

@P2T3
Scenario Outline: Test Data Validation
Given Project DC <DC_Center> is available
And I am already logged in
And I have validated favourite Models widget is Blank successfully
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|

@P2T3
Scenario Outline: Dashboard Favourite Models widget
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Models" tab
Then I should redirect to "Models" tab
When I get total "Favorite Models" count available in Model Listing
When I search first available model AND I mark as Favourite
And I click on LH-Panel Of Favorite Models tab
Then Marked Favourite Model should displayed in Favorite Models tabs
Then "Favourite Models" count should increased by one
And I Navigate to Dasboard AND validated Marked "Favourite" model should available in Favourite model widget
Given I am on "Models" tab
And I click on LH-Panel Of Favorite Models tab
Then Marked Favourite Model should displayed in Favorite Models tabs
And I Context click AND unmarked Favourite model as unfavourite
Then "Favourite Models" count should decreased by one
And I Navigate to Dasboard AND validated Marked "UnFavourite" model should not available in Favourite model widget
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|