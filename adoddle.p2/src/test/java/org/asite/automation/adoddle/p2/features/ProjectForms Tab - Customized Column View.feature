Feature: ProjectForms Tab - Customized Column View

@P2T2
Scenario Outline: Customised View on ProjectForms tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I "removed" any two Coloums of "Project Forms Tab" from Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "not displayed" on "Project Forms Tab" listing page
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
Then selected both Coloums should "not displayed" on "Project Forms Tab" listing page
When I "added" selected both Coloums in Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "displayed" on "Project Forms Tab" listing page on last Position
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
Then selected both Coloums should "displayed" on "Project Forms Tab" listing page on last Position
When I moved Selected both "Project Forms Tab" Coloums of Customize "Selected Fields" into Top Position
And click on Save button of Customise View
Then Selected both Coloums should displayed on "Project Forms Tab" listing page on First Position
When I get Moved coloum width of "Project Forms Tab" And I customize its width
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
Then Moved cutomized column width change with its previous width on "Project Forms Tab"
And Selected both Coloums should displayed on "Project Forms Tab" listing page on First Position
When I get Moved cutomized column new width on "Project Forms Tab" And I again customized its width with previous width
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
Then newly moved cutomized column width of "Project Forms Tab" should changed with its original width
And Selected both Coloums should displayed on "Project Forms Tab" listing page on First Position
Examples: 
|DC_Center|
||
|UK|

@P2T2
Scenario Outline: TestData CleanUp
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Project Forms" tab
And I add all fields in Customize Selected Fields
Examples: 
|DC_Center|
||
|UK|


@P2T2
Scenario Outline: Customised View on ProjectForms tab with Apps Distribution listing
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
And I select "Apps Distribution" in Listing page
When I "removed" any two Coloums of "Project Forms Tab" from Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "not displayed" on "Project Forms Tab" listing page
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
And I select "Apps Distribution" in Listing page
Then selected both Coloums should "not displayed" on "Project Forms Tab" listing page
When I "added" selected both Coloums in Customize "Selected Fields"
And click on Save button of Customise View
Then selected both Coloums should "displayed" on "Project Forms Tab" listing page on last Position
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
And I select "Apps Distribution" in Listing page
Then selected both Coloums should "displayed" on "Project Forms Tab" listing page on last Position
When I moved Selected both "Project Forms Tab" Coloums of Customize "Selected Fields" into Top Position
And click on Save button of Customise View
Then Selected both Coloums should displayed on "Project Forms Tab" listing page on First Position
When I get Moved coloum width of "Project Forms Tab" And I customize its width
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
And I select "Apps Distribution" in Listing page
Then Moved cutomized column width change with its previous width on "Project Forms Tab"
And Selected both Coloums should displayed on "Project Forms Tab" listing page on First Position
When I get Moved cutomized column new width on "Project Forms Tab" And I again customized its width with previous width
Given I am on "Dashboard" tab
Given I am on "Project Forms" tab
And I select "Apps Distribution" in Listing page
Then newly moved cutomized column width of "Project Forms Tab" should changed with its original width
And Selected both Coloums should displayed on "Project Forms Tab" listing page on First Position
Examples: 
|DC_Center|
||
|UK|

@P2T2
Scenario Outline: TestData CleanUp
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Project Forms" tab
And I select "Apps Distribution" in Listing page
And I add all fields in Customize Selected Fields
Examples: 
|DC_Center|
||
|UK|