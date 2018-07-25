Feature: Forms Tab Create Workflow

@P2T5
Scenario Outline: Forms Tab Create Workflow via Right Click
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select <Project_Name> project
And I select more than one Forms for Create Workflow
And I right click on selected Forms and I select "New" and "Project Form" context option
Then "Create Form" popup should open of Recent Forms
And only "Create Yes Ass Yes Form" form should displayed and other form should not displayed
When I search "Create Yes Ass Yes Form" Form Type And I click on searched formType
And I click on "Attachment" button for Form
And I attach a document And I click on "Attach" Button
And I create workflow form with "For Information" action to <A> User
Then workflow form should created successfully And "Attachments" AND selected "Apps" should associated with it
Examples: 
|DC_Center|Project_Name|A|
|UK|AutomationTestProject|Automation UKP2|
#|US|Automatic_Test_US_WS|Automation USP2|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|

@P2T5
Scenario Outline: Forms Tab Create Workflow via More Options
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select <Project_Name> project
And I select more than one Forms for Create Workflow
When I click on "Project Form" link of More Option popup
Then "Create Form" popup should open of Recent Forms
And only "Create Yes Ass Yes Form" form should displayed and other form should not displayed
When I search "Create Yes Ass Yes Form" Form Type And I click on searched formType
And I click on "Attachment" button for Form
And I attach a document And I click on "Attach" Button
And I create workflow form with "For Information" action to <A> User
Then workflow form should created successfully And "Attachments" AND selected "Apps" should associated with it
Examples: 
|DC_Center|Project_Name|A|
|UK|AutomationTestProject|Automation UKP2|
#|US|Automatic_Test_US_WS|Automation USP2|
#|AUS|Automatic_Test_AUS_WS|Automation AUSP2|