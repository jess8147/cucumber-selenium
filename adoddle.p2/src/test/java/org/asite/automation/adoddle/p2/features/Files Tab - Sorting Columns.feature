Feature: Files Tab - Sorting Columns

@P2T2
Scenario Outline: Sorting Disabled Columns Varification on Files tab
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
And I verify "Distributed", "Workflow Status", "Workflow Stage", "Coordinates1Dimension", "Coordinates2Dimension", "Coordinates3Dimension" AND "MultiSelectionCheckbox" Columns sorting should disabled
Examples: 
|DC_Center|
||
|UK|

# Sorting for Single Page #
@P2T2
Scenario Outline: "File Name", "Doc Ref", "Doc Title" AND "Rev" Sorting for Single Page on Files tab
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I go to "AutoSortingTestFolder" folder of <Project_Name> project
And I Sort By <ColumnName> in "ascending" order
Then Valid Sorting should be applied
When I Sort By <ColumnName> in "descending" order
Then Valid Sorting should be applied
Examples: 
|DC_Center|Project_Name|ColumnName|
||||
|UK|AutomationTestProject|File Name|
|UK|AutomationTestProject|Doc Ref|
|UK|AutomationTestProject|Doc Title|
|UK|AutomationTestProject|Rev|

# Sorting for Multiple Pagination #
@P2T2
Scenario Outline: Sorting for Multiple Pages on Files tab - "File Name", "Doc Ref", "Doc Title", "Rev"
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I go to Last page of selected Projects
And I get Last page files "File Name" Column Count
# "Sorted Column ==>: File Name"
When I Sorted "File Name" Column in "ascending" order list
And I get "First" page files "File Name" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "File Name" Column Name
When I Sorted "File Name" Column in "descending" order list
Then "Last set as First" files "File Name" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "File Name" Column in "descending" order list
When I Sorted "File Name" Column in "ascending" order list
Then "First set as First" files "File Name" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "File Name" Column in "ascending" order list
# "Sorted Column ==>: Doc Ref"
When I Sorted "Doc Ref" Column in "ascending" order list
And I get "First" page files "Doc Ref" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Doc Ref" Column Name
When I Sorted "Doc Ref" Column in "descending" order list
Then "Last set as First" files "Doc Ref" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Doc Ref" Column in "descending" order list
When I Sorted "Doc Ref" Column in "ascending" order list
Then "First set as First" files "Doc Ref" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Doc Ref" Column in "ascending" order list
# "Sorted Column ==>: Doc Title"
When I Sorted "Doc Title" Column in "ascending" order list
And I get "First" page files "Doc Title" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Doc Title" Column Name
When I Sorted "Doc Title" Column in "descending" order list
Then "Last set as First" files "Doc Title" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Doc Title" Column in "descending" order list
When I Sorted "Doc Title" Column in "ascending" order list
Then "First set as First" files "Doc Title" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Doc Title" Column in "ascending" order list
# "Sorted Column ==>: Rev"
When I Sorted "Rev" Column in "ascending" order list
And I get "First" page files "Rev" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Rev" Column Name
When I Sorted "Rev" Column in "descending" order list
Then "Last set as First" files "Rev" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Rev" Column in "descending" order list
When I Sorted "Rev" Column in "ascending" order list
Then "First set as First" files "Rev" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Rev" Column in "ascending" order list
Examples: 
|DC_Center|User_ID|
|||
|UK|auto.search_uk@atest.com|

# Sorting for Multiple Pagination #
@P2T2
Scenario Outline: Sorting for Multiple Pages on Files tab - "LettersNumbersTextbox", "Cities", "Radio", AND "DatePicker"
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
Given I am on "Files" tab
Then I should redirect to "Files" tab
When I go to Last page of selected Projects
And I get Last page files "File Name" Column Count
# "Sorted Column ==>: LettersNumbersTextbox"
When I Sorted "LettersNumbersTextbox" Column in "ascending" order list
And I get "First" page files "LettersNumbersTextbox" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "LettersNumbersTextbox" Column Name
When I Sorted "LettersNumbersTextbox" Column in "descending" order list
Then "Last set as First" files "LettersNumbersTextbox" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "LettersNumbersTextbox" Column in "descending" order list
When I Sorted "LettersNumbersTextbox" Column in "ascending" order list
Then "First set as First" files "LettersNumbersTextbox" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "LettersNumbersTextbox" Column in "ascending" order list
# "Sorted Column ==>: Radio"
When I Sorted "Radio" Column in "ascending" order list
And I get "First" page files "Radio" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "Radio" Column Name
When I Sorted "Radio" Column in "descending" order list
Then "Last set as First" files "Radio" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "Radio" Column in "descending" order list
When I Sorted "Radio" Column in "ascending" order list
Then "First set as First" files "Radio" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "Radio" Column in "ascending" order list
# "Sorted Column ==>: DatePicker"
When I Sorted "DatePicker" Column in "ascending" order list
And I get "First" page files "DatePicker" Column Name
And I go to Last page of selected Projects
And I get "Last" page files "DatePicker" Column Name
When I Sorted "DatePicker" Column in "descending" order list
Then "Last set as First" files "DatePicker" Column in "descending" order list
When I go to Last page of selected Projects
Then "First set as Last" files "DatePicker" Column in "descending" order list
When I Sorted "DatePicker" Column in "ascending" order list
Then "First set as First" files "DatePicker" Column in "ascending" order list
When I go to Last page of selected Projects
Then "Last set as Last" files "DatePicker" Column in "ascending" order list
# "Sorted Column ==>: Cities"                                  // Bug (NOODLE-66659)
#When I Sorted "Cities" Column in "ascending" order list
#And I get "First" page files "Cities" Column Name
#And I go to Last page of selected Projects
#And I get "Last" page files "Cities" Column Name
#When I Sorted "Cities" Column in "descending" order list
#Then "Last set as First" files "Cities" Column in "descending" order list
#When I go to Last page of selected Projects
#Then "First set as Last" files "Cities" Column in "descending" order list
#When I Sorted "Cities" Column in "ascending" order list
#Then "First set as First" files "Cities" Column in "ascending" order list
#When I go to Last page of selected Projects
#Then "Last set as Last" files "Cities" Column in "ascending" order list
Examples: 
|DC_Center|User_ID|
|||
|UK|auto.search_uk@atest.com|

# Sorting for Transmittal Pagination #
@P2T2
Scenario Outline: Sorting for Files tab on Files Distribution tab - "Transmittal No.", "Assigned Date"
Given Project DC <DC_Center> is available
And I am already logged in
And I am on "Files" tab
Then I should redirect to "Files" tab
When I select "Files Distribution" in Listing page
And I navigate to <Project> project with <Folder> folder in <Sub_Folder> sub folder
And I scrolldown window for "Transmittal No." Column until last Records not displyed
And I get Last page files "Transmittal No." Column Count
# "Sorted Column ==>: Transmittal No."
When I Sorted "Transmittal No." Column in "ascending" order list
And I scrolldown window for "Transmittal No." Column until last Records not displyed
And I get "First" page files "Transmittal No." Column Name
And I scrolldown window for "Transmittal No." Column until last Records not displyed
And I get "Last" page files "Transmittal No." Column Name
When I Sorted "Transmittal No." Column in "descending" order list
And I scrolldown window for "Transmittal No." Column until last Records not displyed
Then "Last set as First" files "Transmittal No." Column in "descending" order list
And "First set as Last" files "Transmittal No." Column in "descending" order list
When I Sorted "Transmittal No." Column in "ascending" order list
And I scrolldown window for "Transmittal No." Column until last Records not displyed
Then "First set as First" files "Transmittal No." Column in "ascending" order list
Then "Last set as Last" files "Transmittal No." Column in "ascending" order list
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
|UK|AutomationTestProject|Automation_Favourite_Folder|Automation_Favourite_Sub_Folder|
#|US|Automatic_Test_US_WS|Automation_Favourite_Folder|Automation_Favourite_Sub_Folder|
#|AUS|Automatic_Test_AUS_WS|Automation_Favourite_Folder|Automation_Favourite_Sub_Folder|