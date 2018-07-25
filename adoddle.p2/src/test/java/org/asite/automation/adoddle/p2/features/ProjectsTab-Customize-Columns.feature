Feature: Projects Tab - Customize columns

@P2T2
Scenario Outline: Customised View on Projects tab
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I "removed" any two Coloums of "Projects Tab" from Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "not displayed" on "Projects Tab" listing page
Given I am on "Dashboard" tab
Given I am on "Projects" tab
Then selected both Coloums should "not displayed" on "Projects Tab" listing page
When I "added" selected both Coloums in Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "displayed" on "Projects Tab" listing page on last Position
Given I am on "Dashboard" tab
Given I am on "Projects" tab
Then selected both Coloums should "displayed" on "Projects Tab" listing page on last Position
When I moved Selected both "Projects Tab" Coloums of Customize "Selected Fields" into Top Position
And click on Save button of Customise View
Then Selected both Coloums should displayed on "Projects Tab" listing page on First Position
When I get Moved coloum width of "Projects Tab" And I customize its width
Given I am on "Dashboard" tab
Given I am on "Projects" tab
Then Moved cutomized column width change with its previous width on "Projects Tab"
And Selected both Coloums should displayed on "Projects Tab" listing page on First Position
When I get Moved cutomized column new width on "Projects Tab" And I again customized its width with previous width
Given I am on "Dashboard" tab
And I am on "Projects" tab
Then newly moved cutomized column width of "Projects Tab" should changed with its original width
And Selected both Coloums should displayed on "Projects Tab" listing page on First Position
Examples: 
|DC_Center|User_ID|
|||
|UK|tc_tctestorg1@tctestorg1.com|

@P2T2
Scenario Outline: TestData CleanUp
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Projects" tab
And I add all fields in Customize Selected Fields
Examples: 
|DC_Center|User_ID|
|||
|UK|tc_tctestorg1@tctestorg1.com|