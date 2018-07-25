Feature: Single and Batch Forms Status Change - Restrict Status Change in ViewForm - Yes

@P2T7
Scenario Outline: Single & Batch Forms Status Change when Restrict Status Change in View Form "Yes" via right click
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <A> user
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select <Project_Name> project and <App_Group> group and <App_Type> app
And I create new form And Distribute to Users <Users> with <Actions> action
When I select <Project_Name> project and <App_Group> group and <App_Type> app
And I create new form And Distribute to Users <Users> with <Actions> action
Then Form should created Successfully with No action
When I switch to <B> user
Given I am on "Project Forms" tab
Then created all Forms should displayed with "Assign Status" action
When I switch to <C> user
Given I am on "Project Forms" tab
Then created all Forms should displayed with "Distribute" action
When I switch to <D> user
Given I am on "Project Forms" tab
Then created all Forms should displayed with "For Acknowledgement" action
When I switch to <E> user
Given I am on "Project Forms" tab
Then created all Forms should displayed with "For Action" action
When I switch to <B> user
Given I am on "Project Forms" tab
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status" context Option should disabled
Given I am on "Project Forms" tab
And I select "Single Form" And perform "Edit" and "Status" for change status
Then "Status Change" popup should not Opened And Status Change hyperlink "Open" should "disabled" on View Form
When I closed opened new window
When I switch to <C> user
Given I am on "Project Forms" tab
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status Change" popup should open
And "Status Change" dropdown empty And "Change Status" button should disabled
When I switch to <D> user
Given I am on "Project Forms" tab
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status" context Option should disabled
Given I am on "Project Forms" tab
And I select "Single Form" And perform "Edit" and "Status" for change status
Then "Status Change" popup should not Opened And Status Change hyperlink "Open" should "disabled" on View Form
When I closed opened new window
When I switch to <E> user
Given I am on "Project Forms" tab
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status Change" popup should open
And "Status Change" dropdown empty And "Change Status" button should disabled
When I switch to <B> user
Given I am on "Project Forms" tab
And I select "Single Form" And View Form
And I "Edit and Distribute" Form with Change Status
Then "Single Form" Status should changed and displayed on View Form RH-panel And Button disabled
When I closed opened new window
Then "Single Form" updated Status should set on Forms tab And "Assign Status" action completed
Examples: 
|DC_Center|Project_Name|App_Group|App_Type|Users|Actions|A|B|C|D|E|
|UK|AutomationTestProject|Customer Forms|Form Status Check2|Automation Non_Admin2,Automation Non_Admin3,Automation Non_Admin4,Automation Non_Admin5|Assign Status,Distribute,For Acknowledgement,For Action|Automation Non_Admin1|Automation Non_Admin2|Automation Non_Admin3|Automation Non_Admin4|Automation Non_Admin5|
#|US|Automatic_Test_US_WS|Customer Forms|Form Status Check2|Automation Non_Admin2,Automation Non_Admin3,Automation Non_Admin4,Automation Non_Admin5|Assign Status,Distribute,For Acknowledgement,For Action|Automation Non_Admin1|Automation Non_Admin2|Automation Non_Admin3|Automation Non_Admin4|Automation Non_Admin5|
#|AUS|Automatic_Test_AUS_WS|Customer Forms|Form Status Check2 |Automation Non_Admin2,Automation Non_Admin3,Automation Non_Admin4,Automation Non_Admin5|Assign Status,Distribute,For Acknowledgement,For Action|Automation Non_Admin1|Automation Non_Admin2|Automation Non_Admin3|Automation Non_Admin4|Automation Non_Admin5|