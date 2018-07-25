Feature: Favourite Forms Widgets

@P2T7
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
And I removed <User> TestData Favourite Forms
Examples:
|DC_Center|User_ID|User|
|UK|auto.favforms_uk@atest.com|Automation FavForms_UK|
#|US|auto.favforms_us@atest.com|Automation FavForms_US|
#|AUS|auto.favforms_aus@atest.com|Automation FavForms_AUS|

@P2T7
Scenario Outline: Set Form as Favourite Form on Dashboard
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
# "Project Forms Tab" "Favourite"
When I click on "Set Your Favourite Forms" Favourite Forms Widget hyperlink
Then I should redirect to "Project Forms" tab
When I click on <Project_Name> Project for Favourite Form Widget
When I click on <ProjectForms_Create_Yes> form type of "Create Associate Form Group" form folder
Then "Create Form" button should "enabled"
When I set Create rights "yes" form type <ProjectForms_Create_Yes> to "Mark As Favourite"
Then <ProjectForms_Create_Yes> form "set as" Favourite form
When I click on <ProjectForms_Create_No> form type of "Create Associate Form Group" form folder
Then "Create Form" button should "disabled"
When I set Create rights "no" form type <ProjectForms_Create_No> to "Mark As Favourite"
Then <ProjectForms_Create_No> form "set as" Favourite form
Given I am on "Dashboard" tab
Then <ProjectForms_Create_Yes> form should "displayed" in Favourite Forms Widgets list And form should "contain" Add Form icon
And <ProjectForms_Create_No> form should "displayed" in Favourite Forms Widgets list And form should "not contain" Add Form icon
And I clear previous actions count from testdata form
Examples:
|DC_Center|Project_Name|User_ID|ProjectForms_Create_Yes|ProjectForms_Create_No|
|UK|AutomationTestProject|auto.favforms_uk@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|US|Automatic_Test_US_WS|auto.favforms_us@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|AUS|Automatic_Test_AUS_WS|auto.favforms_aus@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|

@P2T7
Scenario Outline: Create Form With Favourite Forms Widget AND get Incomplete Actions Count AND Assign actions more than Week
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
# "Project Forms Tab Incomplete Actions Count With Green Code"
When I click on AddForm icon of <ProjectForms_Create_Yes> form type
Then New "Create Form" popup should open
When I create new form in <ProjectForms_Create_Yes> formtype with all mandatory attributes and click on Send button
Then I should redirect to "Project Forms" tab
And Form should created and listed in "Project Forms" tab
Given I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Project Forms" tab
When I select created form and right click and select "Share" and "Distribute" 
Then "Distribute" popup should open
When I distribute "Assign Status", "For Acknowledgement" AND "For Action" actions with "10" due days to <User_ID> user
And I set "Task Status" filter as "Incomplete" actions And "Recipient" filter as <Recipient_Name>
And I get total Incomplete actions count of "Project Forms" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <ProjectForms_Create_Yes> Count with "Project Forms" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "green"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|auto.favforms_uk@atest.com|Automation FavForms_UK|Create Yes Ass Yes Form|
#|US|auto.favforms_us@atest.com|Automation FavForms_US|Create Yes Ass Yes Form|
#|AUS|auto.favforms_aus@atest.com|Automation FavForms_AUS|Create Yes Ass Yes Form|

@P2T7
Scenario Outline: Create Form With Favourite Forms Widget AND get Incomplete Actions Count AND Assign actions Within a week
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
# "Project Forms Tab Incomplete Actions Count With amber Code"
When I click on AddForm icon of <ProjectForms_Create_Yes> form type
Then New "Create Form" popup should open
When I create new form in <ProjectForms_Create_Yes> formtype with all mandatory attributes and click on Send button
Then I should redirect to "Project Forms" tab
And Form should created and listed in "Project Forms" tab
Given I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Project Forms" tab
When I select created form and right click and select "Share" and "Distribute" 
Then "Distribute" popup should open
When I distribute with "For Information" action for "2" due days to <User_ID> user
And I set "Task Status" filter as "Incomplete" actions And "Recipient" filter as <Recipient_Name>
And I get total Incomplete actions count of "Project Forms" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <ProjectForms_Create_Yes> Count with "Project Forms" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "amber"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|auto.favforms_uk@atest.com|Automation FavForms_UK|Create Yes Ass Yes Form|
#|US|auto.favforms_us@atest.com|Automation FavForms_US|Create Yes Ass Yes Form|
#|AUS|auto.favforms_aus@atest.com|Automation FavForms_AUS|Create Yes Ass Yes Form|

@P2T7
Scenario Outline: Perform Dashboard Favourite Forms Incomplete Actions
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
# "Perform ProjectForms Tab Incomplete Actions"
When I get Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count and Color "amber" And click on Actions count
Then I should redirect to "Project Forms" tab
And "Task Status" filter with "Incomplete" AND "Recipient" filter with <Recipient_Name> filters should set
And total Incomplete Actions count of "Project Forms" tab should match with Dashboard Favourite Form Incomplete Actions count
When I select all forms and perform right click and select "Tasks" with "For Information"
Then "Task - For Information" popup should open
When I click on "OK" link of popup
And I select and get total Incomplete actions count of "Project Forms" tab <ProjectForms_Create_Yes> favourite formtype
And I am on "Dashboard" tab
Then Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count should decreased And match with "Project Forms" tab Incomplete Actions Count And Color of Incomplete actions should set as "green"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|auto.favforms_uk@atest.com|Automation FavForms_UK|Create Yes Ass Yes Form|
#|US|auto.favforms_us@atest.com|Automation FavForms_US|Create Yes Ass Yes Form|
#|AUS|auto.favforms_aus@atest.com|Automation FavForms_AUS|Create Yes Ass Yes Form|

@P2T7
Scenario Outline: Filter Preferences and Favourite Forms
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "set" Filter Preferences
# "'Project Forms' tab filter Preferences"
And I am on "Project Forms" tab
And I create New Form in <Project_Name> project of <ProjectForms_Create_Yes> formtype of "Create Associate Form Group" formgroup with "For Information" action to User <User_ID> without due days
And I add "Task Status" filter with "Incomplete" subfilter type
And I search created new form that have Incomplete Actions
And I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Project Forms" tab
And "Task Status" filter with "Incomplete" sub filter should set
And previous searched form should displayed
Given I am on "Dashboard" tab
When I click on Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count
Then I should redirect to "Project Forms" tab
And "Task Status" filter with "Incomplete" AND "Recipient" filter with <Recipient_Name> filters should set
And previous search forms filter should reset and all incomplete forms should displayed
Given I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
Then I verify Previous all filters "Task Status" filter with "Incomplete" AND "Recipient" filter with <Recipient_Name> filters should set
And Previous Reset search forms filter and all incomplete forms should displayed
Examples:
|DC_Center|Project_Name|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|AutomationTestProject|auto.favforms_uk@atest.com|Automation FavForms_UK|Create Yes Ass Yes Form|
#|US|Automatic_Test_US_WS|auto.favforms_us@atest.com|Automation FavForms_US|Create Yes Ass Yes Form|
#|AUS|Automatic_Test_AUS_WS|auto.favforms_aus@atest.com|Automation FavForms_AUS|Create Yes Ass Yes Form|

@P2T7
Scenario Outline: Clear Incomplete Actions Count AND Remove Form as Favourite Form on Dashboard
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
# "Perform ProjectForms Tab"
When I get Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count and Color "green" And click on Actions count
Then I should redirect to "Project Forms" tab
When I change status as "Closed" of all forms AND I click on "Change Status" Button of popup
And I am on "Dashboard" tab
Then Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count should removed from Form
Given I am on "Project Forms" tab
When I click on <Project_Name> Project for Favourite Form Widget
When I click on <ProjectForms_Create_Yes> form type of "Create Associate Form Group" form folder
And I remove <ProjectForms_Create_Yes> form "Remove as Favourite"
Then <ProjectForms_Create_Yes> form "remove from" Favourite form
When I click on <ProjectForms_Create_No> form type of "Create Associate Form Group" form folder
And I remove <ProjectForms_Create_No> form "Remove as Favourite"
Then <ProjectForms_Create_No> form "remove from" Favourite form
Given I am on "Dashboard" tab
Then <ProjectForms_Create_Yes> should "not displayed" in Favourite Forms Widgets list
And <ProjectForms_Create_No> should "not displayed" in Favourite Forms Widgets list
Examples:
|DC_Center|Project_Name|User_ID|ProjectForms_Create_Yes|ProjectForms_Create_No|
|UK|AutomationTestProject|auto.favforms_uk@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|US|Automatic_Test_US_WS|auto.favforms_us@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|AUS|Automatic_Test_AUS_WS|auto.favforms_aus@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|

@P2T7
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
And I removed <User> TestData Favourite Forms
Examples:
|DC_Center|User_ID|User|
|UK|auto.favforms_uk@atest.com|Automation FavForms_UK|
#|US|auto.favforms_us@atest.com|Automation FavForms_US|
#|AUS|auto.favforms_aus@atest.com|Automation FavForms_AUS|