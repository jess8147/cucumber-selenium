Feature: Design Layout

@P1T5
Scenario Outline: Manage Design Layout for Bill to Org Users
Given Project DC <DC_Center> is available
And I login using  <Layout User> user
And I set Landing URL
And I am on "Admin" tab
When I click on "Design Layout" box on Admin tab
Then "Design Layout" page should be displayed
Given <Bill To Org> is available for Design Layout
When I click on edit icon of <Bill To Org> design layout
Then "Edit Layout" popup should open
And I collect all layout configurations for <Bill To Org> organization
Given <Users Org> is available for Design Layout
When I click on edit icon of <Users Org> design layout
Then "Edit Layout" popup should open
And I collect all layout configurations for <Users Org> organization
Given I am on "Admin" tab
When I click on "Design Layout" box on Admin tab
And I set editable "No" to <Bill To Org>
And I set editable "No" to <Users Org>
Then <Layout User> should follow layout of <Bill To Org> organization
Given I am on "Admin" tab
When I click on "Design Layout" box on Admin tab
And I set editable "Yes" to <Bill To Org>
Then <Layout User> should follow customized options "Yes"
Given I am on "Admin" tab
When I click on "Design Layout" box on Admin tab
And I set editable "No" to <Bill To Org>
And I edit <Bill To Org> and set ApplyTo option to "Organization Users"
Then <Layout User> should follow layout of <Users Org> organization
Given I am on "Admin" tab
When I click on "Design Layout" box on Admin tab
And I edit <Users Org> and set ApplyTo option to "All"
And I set editable "Yes" to <Users Org>
Then <Layout User> should follow customized options "Yes"
Given I am on "Admin" tab
When I click on "Design Layout" box on Admin tab
When I edit <Users Org> and set ApplyTo option to "Bill To Org Users"
And I set editable "No" to <Users Org>
Then <Layout User> should follow customized options "Yes"
Examples: 
|DC_Center|Layout User|Bill To Org|Users Org|
|UK|layoutuser2@utest.com|Automation Bill-To-Org|Automation User-Org|
#|US|layoutuser2@utest.com|Automation Bill-To-Org|Automation User-Org|
#|AUS|layoutuser2@utest.com|Automation Bill-To-Org|Automation User-Org|