Feature: ProjectForms Tab - Sorting Columns

# Sorting for Multiple Pagination #
@P2T2
Scenario Outline: "Form Title", "Last Updated", "Status" AND "Form Type" Sorting for Multiple Pages on ProjectForms tab
Given Project DC <DC_Center> is available
And I am logged in using <User_ID> user and set Default Dashboard as "System Dashboard"
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I go to Last page of selected Projects
And I get Last page files "Form Title" Column Count
# "Sorted Column ==>: Form Title"
When I Sorted "Form Title" Column in "ascending" order list
And I get "First" page files "Form Title" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Form Title" Column Name
When I Sorted "Form Title" Column in "descending" order list
Then "Last set as First" files "Form Title" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Form Title" Column in "descending" order list
When I Sorted "Form Title" Column in "ascending" order list
Then "First set as First" files "Form Title" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Form Title" Column in "ascending" order list
# "Sorted Column ==>: Last Updated"
When I Sorted "Last Updated" Column in "ascending" order list
And I get "First" page files "Last Updated" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Last Updated" Column Name
When I Sorted "Last Updated" Column in "descending" order list
Then "Last set as First" files "Last Updated" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Last Updated" Column in "descending" order list
When I Sorted "Last Updated" Column in "ascending" order list
Then "First set as First" files "Last Updated" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Last Updated" Column in "ascending" order list
# "Sorted Column ==>: Status"
When I Sorted "Status" Column in "ascending" order list
And I get "First" page files "Status" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Status" Column Name
When I Sorted "Status" Column in "descending" order list
Then "Last set as First" files "Status" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Status" Column in "descending" order list
When I Sorted "Status" Column in "ascending" order list
Then "First set as First" files "Status" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Status" Column in "ascending" order list
# "Sorted Column ==>: Form Type"
When I Sorted "Form Type" Column in "ascending" order list
And I get "First" page files "Form Type" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Form Type" Column Name
When I Sorted "Form Type" Column in "descending" order list
Then "Last set as First" files "Form Type" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Form Type" Column in "descending" order list
When I Sorted "Form Type" Column in "ascending" order list
Then "First set as First" files "Form Type" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Form Type" Column in "ascending" order list
Examples: 
|DC_Center|User_ID|
|||
|UK|auto.testuser1@atest.com|

# Sorting of "Form ID" #
@P2T2
Scenario Outline: "Form ID" Sorting for Multiple Pages on ProjectForms tab
Given Project DC <DC_Center> is available
And I am logged in using <User_ID> user and set Default Dashboard as "System Dashboard"
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I click on <Project_Name> project
And I click on "Form Sorting Group" group and click on "Form ID Sorting" formtype
And I go to Last page of selected Projects
And I get Last page files "Form Title" Column Count
# "Sorted Column ==>: Form ID"
When I Sorted "ID" Column in "ascending" order list
And I get "First" page files "ID" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "ID" Column Name
When I Sorted "ID" Column in "descending" order list
Then "Last set as First" files "ID" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "ID" Column in "descending" order list
When I Sorted "ID" Column in "ascending" order list
Then "First set as First" files "ID" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "ID" Column in "ascending" order list
Examples: 
|DC_Center|User_ID|Project_Name|
||||
|UK|auto.testuser1@atest.com|Automation_UK_TestProject1|


# Sorting for Transmittal Pagination #
@P2T2
Scenario Outline: Sorting for ProjectForms tab on Apps Distribution tab - "Assigned By", "Assigned Date"
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select "Apps Distribution" in Listing page
And I navigate to <Project> project with <Folder> folder in <Sub_Folder> sub folder
And I scrolldown window for "Form Title" Column until last Records not displyed
And I get Last page files "Form Title" Column Count
# "Sorted Column ==>: Assigned By"
When I Sorted "Assigned By" Column in "ascending" order list
And I scrolldown window for "Assigned By" Column until last Records not displyed
And I get "First" page files "Assigned By" Column Name
And I get "Last" page files "Assigned By" Column Name
When I Sorted "Assigned By" Column in "descending" order list
And I scrolldown window for "Assigned By" Column until last Records not displyed
Then "Last set as First" files "Assigned By" Column in "descending" order list
And "First set as Last" files "Assigned By" Column in "descending" order list
When I Sorted "Assigned By" Column in "ascending" order list
And I scrolldown window for "Assigned By" Column until last Records not displyed
Then "First set as First" files "Assigned By" Column in "ascending" order list
Then "Last set as Last" files "Assigned By" Column in "ascending" order list
# "Sorted Column ==>: Assigned Date"
When I Sorted "Assigned Date" Column in "ascending" order list
And I scrolldown window for "Assigned Date" Column until last Records not displyed
And I get "First" page files "Assigned Date" Column Name
And I get "Last" page files "Assigned Date" Column Name
When I Sorted "Assigned Date" Column in "descending" order list
And I scrolldown window for "Assigned Date" Column until last Records not displyed
Then "Last set as First" files "Assigned Date" Column in "descending" order list
And "First set as Last" files "Assigned Date" Column in "descending" order list
When I Sorted "Assigned Date" Column in "ascending" order list
And I scrolldown window for "Assigned Date" Column until last Records not displyed
Then "First set as First" files "Assigned Date" Column in "ascending" order list
Then "Last set as Last" files "Assigned Date" Column in "ascending" order list
Examples: 
|DC_Center|Project|Folder|Sub_Folder|
|UK|AutomationTestProject|Model Forms Group|Model Form Access Yes|
#|US|Automatic_Test_US_WS|Model Forms Group|Model Form Access Yes|
#|AUS|Automatic_Test_AUS_WS|Model Forms Group|Model Form Access Yes|