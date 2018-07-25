Feature: Projects Tab - Geaography Sorting

@P2T2
Scenario Outline: "Geography" Sorting on Projects tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I clear search "Project filter"
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I Sort By <ColumnName> in "ascending" order
Then Valid Sorting should be applied
When I Sort By <ColumnName> in "descending" order
Then Valid Sorting should be applied
Examples: 
|DC_Center|ColumnName|
|||
|UK|Geography |

@exclude
Scenario Outline: Change Columns Position on Projects tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I drag First column AND drop it on the place of second column
Given I am on "Projects" tab
Then First column position changed with its previous position
When I again drag First column AND drop it on the place of its previous position
Given I am on "Projects" tab
Then First column position changed and it set on its original position
Examples: 
|DC_Center|
||
|UK|

@exclude
Scenario Outline: Customize Columns Width on Projects tab
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Projects" tab
Then I should redirect to "Projects" tab
When I get any one column width And I customize its width
Given I am on "Projects" tab
Then cutomized column width change with its previous width
When I get cutomized column new width And I again customize its width with previous width
Given I am on "Projects" tab
Then newly cutomized column width changed with its original width
Examples: 
|DC_Center|
||
|UK|