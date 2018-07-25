Feature: Projects Tab - ListView ThumbView

@P2T2
Scenario Outline: Swapping from ListView to ThumbView and Vise Versa
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I clear search "Project filter"
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I click on ThumbView button
Then project listing should be displayed as ThumbView in style
When I click on ListView button
Then project listing should be displayed as ListView in style
Examples: 
|DC_Center|
||
|UK|