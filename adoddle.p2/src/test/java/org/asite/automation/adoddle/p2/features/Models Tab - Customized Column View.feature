Feature: Models Tab - Customize Column View

@P2T2
Scenario Outline: Customised View on Models tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Models" tab
Then I should redirect to "Models" tab
When I click on ListView button
When I "removed" any two Coloums of "Models Tab" from Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "not displayed" on "Models Tab" listing page
Given I am on "Dashboard" tab
And I am on "Models" tab
Then selected both Coloums should "not displayed" on "Models Tab" listing page
When I "added" selected both Coloums in Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "displayed" on "Models Tab" listing page on last Position
Given I am on "Dashboard" tab
And I am on "Models" tab
Then selected both Coloums should "displayed" on "Models Tab" listing page on last Position
When I moved Selected both "Models Tab" Coloums of Customize "Selected Fields" into Top Position
And click on Save button of Customise View
Then Selected both Coloums should displayed on "Models Tab" listing page on First Position
When I get Moved coloum width of "Models Tab" And I customize its width
Given I am on "Dashboard" tab
And I am on "Models" tab
Then Moved cutomized column width change with its previous width on "Models Tab"
And Selected both Coloums should displayed on "Models Tab" listing page on First Position
When I get Moved cutomized column new width on "Models Tab" And I again customized its width with previous width
Given I am on "Dashboard" tab
And I am on "Models" tab
Then newly moved cutomized column width of "Models Tab" should changed with its original width
And Selected both Coloums should displayed on "Models Tab" listing page on First Position
Examples: 
|DC_Center|
||
|UK|

@P2T2
Scenario Outline: TestData CleanUp
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Models" tab
And I add all fields in Customize Selected Fields
Examples: 
|DC_Center|
||
|UK|