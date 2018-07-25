Feature: Favourite Forms Widgets - future release 20.1

@exclude
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
And I removed TestData Favourite Forms
Examples:
|DC_Center|User_ID|
|UK|auto_uk1@atest.com|
#|US|auto_us1@atest.com|
#|AUS|auto_aus1@atest.com|

@exclude
Scenario Outline: Set Form as Favourite Form on Dashboard
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
# "Project Forms Tab" "Favourite"
When I click on "Save your important Forms as Favourites here" Favourite Forms Widget hyperlink
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
# "Contracts Tab" "Favourite"
Given I am on "Contracts" tab
Then I should redirect to "Contracts" tab
When I click on <Project_Name> Project for Favourite Form Widget
When I click on <Contracts_Create_Yes> form type of "Recent Contracts form Group" form folder
Then "Create Form" button should "enabled"
When I set Create rights "yes" form type <Contracts_Create_Yes> to "Mark As Favourite"
Then <Contracts_Create_Yes> form "set as" Favourite form
When I click on <Contracts_Create_No> form type of "Recent Contracts form Group" form folder
Then "Create Form" button should "disabled"
When I set Create rights "no" form type <Contracts_Create_No> to "Mark As Favourite"
Then <Contracts_Create_No> form "set as" Favourite form
Given I am on "Dashboard" tab
Then <Contracts_Create_Yes> form should "displayed" in Favourite Forms Widgets list And form should "contain" Add Form icon
And <Contracts_Create_No> form should "displayed" in Favourite Forms Widgets list And form should "not contain" Add Form icon
# "FM Tab" "Favourite"
Given I am on "FM" tab
Then I should redirect to "FM" tab
When I click on <Project_Name> Project for Favourite Form Widget
When I click on <FM_Create_Yes> form type of "Recent FM form Group" form folder
Then "Create Form" button should "enabled"
When I set Create rights "yes" form type <FM_Create_Yes> to "Mark As Favourite"
Then <FM_Create_Yes> form "set as" Favourite form
When I click on <FM_Create_No> form type of "Recent FM form Group" form folder
Then "Create Form" button should "disabled"
When I set Create rights "no" form type <FM_Create_No> to "Mark As Favourite"
Then <FM_Create_No> form "set as" Favourite form
Given I am on "Dashboard" tab
Then <FM_Create_Yes> form should "displayed" in Favourite Forms Widgets list And form should "contain" Add Form icon
And <FM_Create_No> form should "displayed" in Favourite Forms Widgets list And form should "not contain" Add Form icon
Examples:
|DC_Center|Project_Name|User_ID|ProjectForms_Create_Yes|ProjectForms_Create_No|Contracts_Create_Yes|Contracts_Create_No|FM_Create_Yes|FM_Create_No|
|UK|AutomationTestProject|auto_uk1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|Recent Contracts Form|Contracts Tab Create No Form|Recent FM Form|FM Tab Create No Form|
#|US|Automatic_Test_US_WS|auto_us1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|Recent Contracts Form|Contracts Tab Create No Form|Recent FM Form|FM Tab Create No Form|
#|AUS|Automatic_Test_AUS_WS|auto_aus1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|Recent Contracts Form|Contracts Tab Create No Form|Recent FM Form|FM Tab Create No Form|

@exclude
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
And I set "Action Status" as "Incomplete" AND "Recipient Name" as <Recipient_Name> filter
And I get total Incomplete actions count of "Project Forms" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <ProjectForms_Create_Yes> Count with "Project Forms" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "green"
# "Contracts Tab Incomplete Actions Count With Green Code"
When I click on AddForm icon of <Contracts_Create_Yes> form type
Then New "Create Form" popup should open
When I create new form in <Contracts_Create_Yes> formtype with all mandatory attributes and click on Send button
Then I should redirect to "Contracts" tab
Then Form should created and listed in "Contracts" tab
Given I am on "Dashboard" tab
When I click on <Contracts_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Contracts" tab
When I select created form and right click and select "Share" and "Distribute" 
Then "Distribute" popup should open
When I distribute "Assign Status", "For Acknowledgement" AND "For Action" actions with "10" due days to <User_ID> user
And I set "Action Status" as "Incomplete" AND "Recipient Name" as <Recipient_Name> filter
And I get total Incomplete actions count of "Contracts" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <Contracts_Create_Yes> Count with "Contracts" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "green"
# "FM Tab Incomplete Actions Count With Green Code"
When I click on AddForm icon of <FM_Create_Yes> form type
Then New "Create Form" popup should open
When I create new form in <FM_Create_Yes> formtype with all mandatory attributes and click on Send button
Then I should redirect to "FM" tab
Then Form should created and listed in "FM" tab
Given I am on "Dashboard" tab
When I click on <FM_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "FM" tab
When I select created form and right click and select "Share" and "Distribute" 
Then "Distribute" popup should open
When I distribute "Assign Status", "For Acknowledgement" AND "For Action" actions with "10" due days to <User_ID> user
And I set "Action Status" as "Incomplete" AND "Recipient Name" as <Recipient_Name> filter
And I get total Incomplete actions count of "FM" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <FM_Create_Yes> Count with "FM" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "green"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|Contracts_Create_Yes|FM_Create_Yes|
|UK|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|US|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|AUS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|

@exclude
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
And I set "Action Status" as "Incomplete" AND "Recipient Name" as <Recipient_Name> filter
And I get total Incomplete actions count of "Project Forms" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <ProjectForms_Create_Yes> Count with "Project Forms" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "amber"
# "Contracts Tab Incomplete Actions Count With amber Code"
When I click on AddForm icon of <Contracts_Create_Yes> form type
Then New "Create Form" popup should open
When I create new form in <Contracts_Create_Yes> formtype with all mandatory attributes and click on Send button
Then I should redirect to "Contracts" tab
Then Form should created and listed in "Contracts" tab
Given I am on "Dashboard" tab
When I click on <Contracts_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Contracts" tab
When I select created form and right click and select "Share" and "Distribute" 
Then "Distribute" popup should open
When I distribute with "For Information" action for "2" due days to <User_ID> user
And I set "Action Status" as "Incomplete" AND "Recipient Name" as <Recipient_Name> filter
And I get total Incomplete actions count of "Contracts" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <Contracts_Create_Yes> Count with "Contracts" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "amber"
# "FM Tab Incomplete Actions Count With amber Code"
When I click on AddForm icon of <FM_Create_Yes> form type
Then New "Create Form" popup should open
When I create new form in <FM_Create_Yes> formtype with all mandatory attributes and click on Send button
Then I should redirect to "FM" tab
Then Form should created and listed in "FM" tab
Given I am on "Dashboard" tab
When I click on <FM_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "FM" tab
When I select created form and right click and select "Share" and "Distribute" 
Then "Distribute" popup should open
When I distribute with "For Information" action for "2" due days to <User_ID> user
And I set "Action Status" as "Incomplete" AND "Recipient Name" as <Recipient_Name> filter
And I get total Incomplete actions count of "FM" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <FM_Create_Yes> Count with "FM" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "amber"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|Contracts_Create_Yes|FM_Create_Yes|
|UK|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|US|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|AUS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|

@exclude
Scenario Outline: Perform Dashboard Favourite Forms Incomplete Actions
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
# "Perform ProjectForms Tab Incomplete Actions"
When I get Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count and Color "amber" And click on Actions count
Then I should redirect to "Project Forms" tab
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And total Incomplete Actions count of "Project Forms" tab should match with Dashboard Favourite Form Incomplete Actions count
When I select all forms and perform right click and select "Actions" with "For Information"
Then "Actions - For Information" popup should open
When I click on "OK" link of popup
And I select and get total Incomplete actions count of "Project Forms" tab <ProjectForms_Create_Yes> favourite formtype
And I am on "Dashboard" tab
Then Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count should decreased And match with "Project Forms" tab Incomplete Actions Count And Color of Incomplete actions should set as "green"
# "Perform Contracts Tab Incomplete Actions"
When I get Dashboard Favourite Form <Contracts_Create_Yes> Incomplete Actions count and Color "amber" And click on Actions count
Then I should redirect to "Contracts" tab
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And total Incomplete Actions count of "Contracts" tab should match with Dashboard Favourite Form Incomplete Actions count
When I select all forms and perform right click and select "Actions" with "For Information"
Then "Actions - For Information" popup should open
When I click on "OK" link of popup
And I select and get total Incomplete actions count of "Contracts" tab <Contracts_Create_Yes> favourite formtype
And I am on "Dashboard" tab
Then Dashboard Favourite Form <Contracts_Create_Yes> Incomplete Actions count should decreased And match with "Contracts" tab Incomplete Actions Count And Color of Incomplete actions should set as "green"
# "Perform FM Tab Incomplete Actions"
When I get Dashboard Favourite Form <FM_Create_Yes> Incomplete Actions count and Color "amber" And click on Actions count
Then I should redirect to "FM" tab
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And total Incomplete Actions count of "FM" tab should match with Dashboard Favourite Form Incomplete Actions count
When I select all forms and perform right click and select "Actions" with "For Information"
Then "Actions - For Information" popup should open
When I click on "OK" link of popup
And I select and get total Incomplete actions count of "FM" tab <FM_Create_Yes> favourite formtype
And I am on "Dashboard" tab
Then Dashboard Favourite Form <FM_Create_Yes> Incomplete Actions count should decreased And match with "FM" tab Incomplete Actions Count And Color of Incomplete actions should set as "green"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|Contracts_Create_Yes|FM_Create_Yes|
|UK|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|US|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|AUS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|

@exclude
Scenario Outline: Filter Preferences and Favourite Forms
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "set" Filter Preferences
# "'Project Forms' tab filter Preferences"
And I am on "Project Forms" tab
And I create New Form in <Project_Name> project of <ProjectForms_Create_Yes> formtype of "Create Associate Form Group" formgroup with "For Information" action to User <User_ID> without due days
And I set "Action Status" filter with "Incomplete" sub filter
And I search created new form that have Incomplete Actions
And I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Project Forms" tab
And "Action Status" filter with "Incomplete" sub filter should set
And previous searched form should displayed
Given I am on "Dashboard" tab
When I click on Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count
Then I should redirect to "Project Forms" tab
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And previous search forms filter should reset and all incomplete forms should displayed
Given I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
Then I verify Previous all filters "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And Previous Reset search forms filter and all incomplete forms should displayed
# "'Contracts' tab filter Preferences"
And I am on "Contracts" tab
And I create New Form in <Project_Name> project of <Contracts_Create_Yes> formtype of "Recent Contracts form Group" formgroup with "For Information" action to User <User_ID> without due days
And I set "Action Status" filter with "Incomplete" sub filter
And I search created new form that have Incomplete Actions
And I am on "Dashboard" tab
When I click on <Contracts_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Contracts" tab
And "Action Status" filter with "Incomplete" sub filter should set
And previous searched form should displayed
Given I am on "Dashboard" tab
When I click on Dashboard Favourite Form <Contracts_Create_Yes> Incomplete Actions count
Then I should redirect to "Contracts" tab
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And previous search forms filter should reset and all incomplete forms should displayed
Given I am on "Dashboard" tab
When I click on <Contracts_Create_Yes> dashboard Favourite Forms Widgets
Then I verify Previous all filters "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And Previous Reset search forms filter and all incomplete forms should displayed
# "'FM' tab filter Preferences"
And I am on "FM" tab
And I create New Form in <Project_Name> project of <FM_Create_Yes> formtype of "Recent FM form Group" formgroup with "For Information" action to User <User_ID> without due days
And I set "Action Status" filter with "Incomplete" sub filter
And I search created new form that have Incomplete Actions
And I am on "Dashboard" tab
When I click on <FM_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "FM" tab
And "Action Status" filter with "Incomplete" sub filter should set
And previous searched form should displayed
Given I am on "Dashboard" tab
When I click on Dashboard Favourite Form <FM_Create_Yes> Incomplete Actions count
Then I should redirect to "FM" tab
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And previous search forms filter should reset and all incomplete forms should displayed
Given I am on "Dashboard" tab
When I click on <FM_Create_Yes> dashboard Favourite Forms Widgets
Then I verify Previous all filters "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And Previous Reset search forms filter and all incomplete forms should displayed
When I "remove" Filter Preferences
Examples:
|DC_Center|Project_Name|User_ID|Recipient_Name|ProjectForms_Create_Yes|Contracts_Create_Yes|FM_Create_Yes|
|UK|AutomationTestProject|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|US|Automatic_Test_US_WS|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|
#|AUS|Automatic_Test_AUS_WS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|Recent Contracts Form|Recent FM Form|

@exclude
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
# "Perform Contracts Tab"
When I get Dashboard Favourite Form <Contracts_Create_Yes> Incomplete Actions count and Color "green" And click on Actions count
Then I should redirect to "Contracts" tab
When I change status as "Closed" of all forms AND I click on "Change Status" Button of popup
And I am on "Dashboard" tab
Then Dashboard Favourite Form <Contracts_Create_Yes> Incomplete Actions count should removed from Form
Given I am on "Contracts" tab
When I click on <Project_Name> Project for Favourite Form Widget
When I click on <Contracts_Create_Yes> form type of "Recent Contracts form Group" form folder
And I remove <Contracts_Create_Yes> form "Remove as Favourite"
Then <Contracts_Create_Yes> form "remove from" Favourite form
When I click on <Contracts_Create_No> form type of "Recent Contracts form Group" form folder
And I remove <Contracts_Create_No> form "Remove as Favourite"
Then <Contracts_Create_No> form "remove from" Favourite form
Given I am on "Dashboard" tab
Then <Contracts_Create_Yes> should "not displayed" in Favourite Forms Widgets list
And <Contracts_Create_No> should "not displayed" in Favourite Forms Widgets list
# "Perform FM Tab"
When I get Dashboard Favourite Form <FM_Create_Yes> Incomplete Actions count and Color "green" And click on Actions count
Then I should redirect to "FM" tab
When I change status as "Closed" of all forms AND I click on "Change Status" Button of popup
And I am on "Dashboard" tab
Then Dashboard Favourite Form <FM_Create_Yes> Incomplete Actions count should removed from Form
Given I am on "FM" tab
When I click on <Project_Name> Project for Favourite Form Widget
When I click on <FM_Create_Yes> form type of "Recent FM form Group" form folder
And I remove <FM_Create_Yes> form "Remove as Favourite"
Then <FM_Create_Yes> form "remove from" Favourite form
When I click on <FM_Create_No> form type of "Recent FM form Group" form folder
And I remove <FM_Create_No> form "Remove as Favourite"
Then <FM_Create_No> form "remove from" Favourite form
Given I am on "Dashboard" tab
Then <FM_Create_Yes> should "not displayed" in Favourite Forms Widgets list
And <FM_Create_No> should "not displayed" in Favourite Forms Widgets list
Examples:
|DC_Center|Project_Name|User_ID|ProjectForms_Create_Yes|ProjectForms_Create_No|Contracts_Create_Yes|Contracts_Create_No|FM_Create_Yes|FM_Create_No|
|UK|AutomationTestProject|auto_uk1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|Recent Contracts Form|Contracts Tab Create No Form|Recent FM Form|FM Tab Create No Form|
#|US|Automatic_Test_US_WS|auto_us1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|Recent Contracts Form|Contracts Tab Create No Form|Recent FM Form|FM Tab Create No Form|
#|AUS|Automatic_Test_AUS_WS|auto_aus1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|Recent Contracts Form|Contracts Tab Create No Form|Recent FM Form|FM Tab Create No Form|

@exclude
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
And I removed TestData Favourite Forms
Examples:
|DC_Center|User_ID|
|UK|auto_uk1@atest.com|
#|US|auto_us1@atest.com|
#|AUS|auto_aus1@atest.com|


######################################################


# "Single Form Tab"

@exclude
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
And I removed TestData Favourite Forms
Examples:
|DC_Center|User_ID|
|UK|auto_uk1@atest.com|
#|US|auto_us1@atest.com|
#|AUS|auto_aus1@atest.com|

@exclude
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
Examples:
|DC_Center|Project_Name|User_ID|ProjectForms_Create_Yes|ProjectForms_Create_No|
|UK|AutomationTestProject|auto_uk1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|US|Automatic_Test_US_WS|auto_us1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|AUS|Automatic_Test_AUS_WS|auto_aus1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|

@exclude
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
When I set "Action Status" filter as "Incomplete" actions And "Recipient Name" filter as <Recipient_Name>
And I get total Incomplete actions count of "Project Forms" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <ProjectForms_Create_Yes> Count with "Project Forms" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "green"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|
#|US|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|
#|AUS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|

@exclude
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
When I set "Action Status" filter as "Incomplete" actions And "Recipient Name" filter as <Recipient_Name>
And I get total Incomplete actions count of "Project Forms" tab Favourite formtype
And I am on "Dashboard" tab
Then I verify Dashboard Favourite Form <ProjectForms_Create_Yes> Count with "Project Forms" tab Favourite form Count And Color of Incomplete actions of Favourite form set as "amber"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|
#|US|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|
#|AUS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|

@exclude
Scenario Outline: Perform Dashboard Favourite Forms Incomplete Actions
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
# "Perform ProjectForms Tab Incomplete Actions"
When I get Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count and Color "amber" And click on Actions count
Then I should redirect to "Project Forms" tab
And "Apps Distribution" page should selected on "Project Forms" tab dropdown
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And total Incomplete Actions count of "Project Forms" tab should match with Dashboard Favourite Form Incomplete Actions count
When I add "Action Name" filter with "For Information" subfilter type
When I select all "Forms" and perform right click And select "Clear Actions"
And I remove "Action Name" filter type
And I select and get total Incomplete actions count of "Project Forms" tab <ProjectForms_Create_Yes> favourite formtype
And I am on "Dashboard" tab
Then Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count should decreased And match with "Project Forms" tab Incomplete Actions Count And Color of Incomplete actions should set as "green"
Examples:
|DC_Center|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|
#|US|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|
#|AUS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|

@exclude
Scenario Outline: Filter Preferences and Favourite Forms
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "set" Filter Preferences
# "'Project Forms' tab filter Preferences"
And I am on "Project Forms" tab
And I create New Form in <Project_Name> project of <ProjectForms_Create_Yes> formtype of "Create Associate Form Group" formgroup with "For Information" action to User <User_ID> without due days
When I add "Action Status" filter with "Incomplete" subfilter type
And I search created new form that have Incomplete Actions
And I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
Then I should redirect to "Project Forms" tab
And "Action Status" filter with "Incomplete" sub filter should set
And previous searched form should displayed
Given I am on "Dashboard" tab
When I click on Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count
Then I should redirect to "Project Forms" tab
And "Apps Distribution" page should selected on "Project Forms" tab dropdown
And "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And previous search forms filter should reset and all incomplete forms should displayed
Given I am on "Dashboard" tab
When I click on <ProjectForms_Create_Yes> dashboard Favourite Forms Widgets
And "Apps" page should selected on "Project Forms" tab dropdown
Then I verify Previous all filters "Action Status" filter with "Incomplete" AND "Recipient Name" filter with <Recipient_Name> filters should set
And Previous Reset search forms filter and all incomplete forms should displayed
Examples:
|DC_Center|Project_Name|User_ID|Recipient_Name|ProjectForms_Create_Yes|
|UK|AutomationTestProject|auto_uk1@atest.com|Automation UK1|Create Yes Ass Yes Form|
#|US|Automatic_Test_US_WS|auto_us1@atest.com|Automation US1|Create Yes Ass Yes Form|
#|AUS|Automatic_Test_AUS_WS|auto_aus1@atest.com|Automation AUS1|Create Yes Ass Yes Form|

@exclude
Scenario Outline: Clear Incomplete Actions Count AND Remove Form as Favourite Form on Dashboard
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
# "Perform ProjectForms Tab"
When I get Dashboard Favourite Form <ProjectForms_Create_Yes> Incomplete Actions count and Color "green" And click on Actions count
Then I should redirect to "Project Forms" tab
And "Apps Distribution" page should selected on "Project Forms" tab dropdown
When I select all "Forms" and perform right click And select "Clear Actions"
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
|UK|AutomationTestProject|auto_uk1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|US|Automatic_Test_US_WS|auto_us1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|
#|AUS|Automatic_Test_AUS_WS|auto_aus1@atest.com|Create Yes Ass Yes Form|Create No Ass Yes Form|

@exclude
Scenario Outline: Perform Test Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I "remove" Filter Preferences
And I removed TestData Favourite Forms
Examples:
|DC_Center|User_ID|
|UK|auto_uk1@atest.com|
#|US|auto_us1@atest.com|
#|AUS|auto_aus1@atest.com|