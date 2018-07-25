Feature: Discussions Tab - Sorting Columns

# Sorting for Multiple Pagination #
@P2T2
Scenario Outline: "ID", "Title", "Doc Ref", "Doc Title", AND "Rev" Sorting for Multiple Pages on Discussions tab
Given Project DC <DC_Center> is available
And I am logged in using <User_ID> user and set Default Dashboard as "System Dashboard"
And I am on "Discussions" tab
And I select <Project_Name> project
#Then I should redirect to "Discussions" tab
When I go to Last page of selected Projects
And I get Last page files "Title" Column Count
# "Sorted Column ==>: Title"
When I Sorted "Title" Column in "ascending" order list
And I get "First" page files "Title" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Title" Column Name
When I Sorted "Title" Column in "descending" order list
Then "Last set as First" files "Title" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Title" Column in "descending" order list
When I Sorted "Title" Column in "ascending" order list
Then "First set as First" files "Title" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Title" Column in "ascending" order list
# "Sorted Column ==>: ID"                                  // Bug (NOODLE-66659)
#When I Sorted "ID" Column in "ascending" order list
#And I get "First" page files "ID" Column Name
#And I go to Last page of selected Projects
#And I get "Last" page files "ID" Column Name
#When I Sorted "ID" Column in "descending" order list
#Then "Last set as First" files "ID" Column in "descending" order list
#When I go to Last page of selected Projects
#Then "First set as Last" files "ID" Column in "descending" order list
#When I Sorted "ID" Column in "ascending" order list
#Then "First set as First" files "ID" Column in "ascending" order list
#When I go to Last page of selected Projects
#Then "Last set as Last" files "ID" Column in "ascending" order list
# "Sorted Column ==>: Doc Ref"                                  // Bug (NOODLE-66659)
#When I Sorted "Doc Ref" Column in "ascending" order list
#And I get "First" page files "Doc Ref" Column Name
#And I go to Last page of selected Projects
#And I get "Last" page files "Doc Ref" Column Name
#When I Sorted "Doc Ref" Column in "descending" order list
#Then "Last set as First" files "Doc Ref" Column in "descending" order list
#When I go to Last page of selected Projects
#Then "First set as Last" files "Doc Ref" Column in "descending" order list
#When I Sorted "Doc Ref" Column in "ascending" order list
#Then "First set as First" files "Doc Ref" Column in "ascending" order list
#When I go to Last page of selected Projects
#Then "Last set as Last" files "Doc Ref" Column in "ascending" order list
# "Sorted Column ==>: Doc Title"                                  // Bug (NOODLE-66659)
#When I Sorted "Doc Title" Column in "ascending" order list
#And I get "First" page files "Doc Title" Column Name
#And I go to Last page of selected Projects
#And I get "Last" page files "Doc Title" Column Name
#When I Sorted "Doc Title" Column in "descending" order list
#Then "Last set as First" files "Doc Title" Column in "descending" order list
#When I go to Last page of selected Projects
#Then "First set as Last" files "Doc Title" Column in "descending" order list
#When I Sorted "Doc Title" Column in "ascending" order list
#Then "First set as First" files "Doc Title" Column in "ascending" order list
#When I go to Last page of selected Projects
#Then "Last set as Last" files "Doc Title" Column in "ascending" order list
# "Sorted Column ==>: Rev"                                  // Bug (NOODLE-66659)
#When I Sorted "Rev" Column in "ascending" order list
#And I get "First" page files "Rev" Column Name
#And I go to Last page of selected Projects
#And I get "Last" page files "Rev" Column Name
#When I Sorted "Rev" Column in "descending" order list
#Then "Last set as First" files "Rev" Column in "descending" order list
#When I go to Last page of selected Projects
#Then "First set as Last" files "Rev" Column in "descending" order list
#When I Sorted "Rev" Column in "ascending" order list
#Then "First set as First" files "Rev" Column in "ascending" order list
#When I go to Last page of selected Projects
#Then "Last set as Last" files "Rev" Column in "ascending" order list
Examples: 
|DC_Center|User_ID|Project_Name|
||||
|UK|auto.testuser1@atest.com|Automation_UK_TestProject1|