Feature: Models Tab - Sorting Columns

# Sorting for Multiple Pagination #
@P2T2
Scenario Outline: "Model Name", "Model Creation Date", "Model Last Updated Date", "Last Access Model Date" AND "Last Access By" Sorting for Multiple Pages on Models tab
Given Project DC <DC_Center> is available
And I am logged in using <User_ID> user and set Default Dashboard as "System Dashboard"
And I am on "Models" tab
Then I should redirect to "Models" tab
When I click on ListView button
When I go to Last page of selected Projects
And I get Last page files "Model Name" Column Count
# "Sorted Column ==>: Model Name"
When I Sorted "Model Name" Column in "ascending" order list
And I get "First" page files "Model Name" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Model Name" Column Name
When I Sorted "Model Name" Column in "descending" order list
Then "Last set as First" files "Model Name" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Model Name" Column in "descending" order list
When I Sorted "Model Name" Column in "ascending" order list
Then "First set as First" files "Model Name" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Model Name" Column in "ascending" order list
# "Sorted Column ==>: Accessed By"
When I Sorted "Accessed By" Column in "ascending" order list
And I get "First" page files "Accessed By" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Accessed By" Column Name
When I Sorted "Accessed By" Column in "descending" order list
Then "Last set as First" files "Accessed By" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Accessed By" Column in "descending" order list
When I Sorted "Accessed By" Column in "ascending" order list
Then "First set as First" files "Accessed By" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Accessed By" Column in "ascending" order list
# "Sorted Column ==>: Date"
When I Sorted "Date" Column in "ascending" order list
And I get "First" page files "Date" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Date" Column Name
When I Sorted "Date" Column in "descending" order list
Then "Last set as First" files "Date" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Date" Column in "descending" order list
When I Sorted "Date" Column in "ascending" order list
Then "First set as First" files "Date" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Date" Column in "ascending" order list
# "Sorted Column ==>: Updated Date"
When I Sorted "Updated Date" Column in "ascending" order list
And I get "First" page files "Updated Date" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Updated Date" Column Name
When I Sorted "Updated Date" Column in "descending" order list
Then "Last set as First" files "Updated Date" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Updated Date" Column in "descending" order list
When I Sorted "Updated Date" Column in "ascending" order list
Then "First set as First" files "Updated Date" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Updated Date" Column in "ascending" order list
# "Sorted Column ==>: Access Date"
When I Sorted "Access Date" Column in "ascending" order list
And I get "First" page files "Access Date" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Access Date" Column Name
When I Sorted "Access Date" Column in "descending" order list
Then "Last set as First" files "Access Date" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Access Date" Column in "descending" order list
When I Sorted "Access Date" Column in "ascending" order list
Then "First set as First" files "Access Date" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Access Date" Column in "ascending" order list
Examples: 
|DC_Center|User_ID|
|||
|UK|auto.testuser1@atest.com|