Feature: Projects Tab - Project Name Sorting

@P2T2
Scenario Outline: "Project Name" Sorting on Projects tab
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
|UK|Name|

@exclude
Scenario Outline: "Type" Sorting on Projects tab
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
|UK|Type|

@exclude
Scenario Outline: "Owner Org" Sorting on Projects tab
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
|UK|Owner Org|