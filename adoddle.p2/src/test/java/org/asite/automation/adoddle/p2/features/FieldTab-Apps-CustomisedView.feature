Feature: Field Tab - Apps Customized View

@P2T2
Scenario Outline: Customized View on Field tab with Apps listing
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Field" tab
Then I should redirect to "Field" tab
When I "removed" any two Coloums of "Field Tab" from Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "not displayed" on "Field Tab" listing page
Given I am on "Dashboard" tab
Given I am on "Field" tab
Then selected both Coloums should "not displayed" on "Field Tab" listing page
When I "added" selected both Coloums in Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "displayed" on "Field Tab" listing page on last Position
Given I am on "Dashboard" tab
Given I am on "Field" tab
Then selected both Coloums should "displayed" on "Field Tab" listing page on last Position
When I moved Selected both "Field Tab" Coloums of Customize "Selected Fields" into Top Position
And click on Save button of Customise View
Then Selected both Coloums should displayed on "Field Tab" listing page on First Position
When I get Moved coloum width of "Field Tab" And I customize its width
Given I am on "Dashboard" tab
Given I am on "Field" tab
Then Moved cutomized column width change with its previous width on "Field Tab"
And Selected both Coloums should displayed on "Field Tab" listing page on First Position
When I get Moved cutomized column new width on "Field Tab" And I again customized its width with previous width
Given I am on "Dashboard" tab
Given I am on "Field" tab
Then newly moved cutomized column width of "Field Tab" should changed with its original width
And Selected both Coloums should displayed on "Field Tab" listing page on First Position
Examples: 
|DC_Center|
||
|UK|

@P2T2
Scenario Outline: TestData CleanUp
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Field" tab
And I add all fields in Customize Selected Fields
Examples: 
|DC_Center|
||
|UK|
