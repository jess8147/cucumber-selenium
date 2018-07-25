Feature: Discussions Tab - Customized Column View

@P2T2
Scenario Outline: Customised View on Discussions tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Discussions" tab
#Then I should redirect to "Discussions" tab
When I "removed" any two Coloums of "Discussions Tab" from Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "not displayed" on "Discussions Tab" listing page
Given I am on "Dashboard" tab
And I am on "Discussions" tab
Then selected both Coloums should "not displayed" on "Discussions Tab" listing page
When I "added" selected both Coloums in Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "displayed" on "Discussions Tab" listing page on last Position
Given I am on "Dashboard" tab
And I am on "Discussions" tab
Then selected both Coloums should "displayed" on "Discussions Tab" listing page on last Position
When I moved Selected both "Discussions Tab" Coloums of Customize "Selected Fields" into Top Position
And click on Save button of Customise View
Then Selected both Coloums should displayed on "Discussions Tab" listing page on First Position
When I get Moved coloum width of "Discussions Tab" And I customize its width
Given I am on "Dashboard" tab
And I am on "Discussions" tab
Then Moved cutomized column width change with its previous width on "Discussions Tab"
And Selected both Coloums should displayed on "Discussions Tab" listing page on First Position
When I get Moved cutomized column new width on "Discussions Tab" And I again customized its width with previous width
Given I am on "Dashboard" tab
And I am on "Discussions" tab
Then newly moved cutomized column width of "Discussions Tab" should changed with its original width
And Selected both Coloums should displayed on "Discussions Tab" listing page on First Position
Examples: 
|DC_Center|
||
|UK|

@P2T2
Scenario Outline: TestData CleanUp
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Discussions" tab
And I add all fields in Customize Selected Fields
Examples: 
|DC_Center|
||
|UK|