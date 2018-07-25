Feature: Projects Tab - Favourite Projects

@P2T2
Scenario Outline: Set Favourite Projects Pre-Condition
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I remove some projects into Favorite Projects List
Examples: 
|DC_Center|User_ID|
|||
|UK|tc_tctestorg1@tctestorg1.com|

@P2T2
Scenario Outline: Set Project as Favourite Projects
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I clear search "Project filter"
And I select more than one Projects into Search "Project filter"
Given I am on "Projects" tab
And I set all selected project "Mark As Favourite"
And I click on LH-Panel Of Favorite Projects tab
Then selected all Projects should be displayed on Favorite Projects tab
And all Projects should be displayed as "Mark As Favourite" And I get Total Favorite Projects Count
When I click on "All" Projects tab
And I remove all Favourite Projects "Mark As Favourite" into "Remove as Favourite"
And I click on LH-Panel Of Favorite Projects tab
Then No projects should displayed into Projects tab And Total Favorite Projects Count Should be 0
When I click on "All" Projects tab
Then selected all projects should displayed into Projects tab And I verify selected all projects displayed as "Remove as Favourite"
When I clear search "Project filter"
Examples: 
|DC_Center|User_ID|
|||
|UK|tc_tctestorg1@tctestorg1.com|