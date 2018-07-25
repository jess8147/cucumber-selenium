Feature: Single and Batch Forms Status Change - Restrict Status Change in ViewForm - No

@P2T7
Scenario Outline: Single & Batch Forms Status Change when Restrict Status Change in View Form "No" via right click
Given Project DC <DC_Center> is available
And I am already logged in
When I switch to <A> user
Given I am on "Project Forms" tab
Then I should redirect to "Project Forms" tab
When I select <Project_Name> project and <App_Group> group and <App_Type> app
And I create new form And Distribute to Users <Users> with <Actions> action
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
When I switch to <A> user
Given I am on "Project Forms" tab
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status" context Option should disabled
Given I am on "Project Forms" tab
And I select "Single Form" And perform "Edit" and "Status" for change status
When I change "Single Form" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then "Single Form" Status should changed and displayed on View Form RH-panel
When I closed opened new window
Then "Single Form" updated Status should set on Forms tab
When I switch to <B> user
Given I am on "Project Forms" tab
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status" context Option should disabled
Given I am on "Project Forms" tab
And I select "Single Form" And perform "Edit" and "Status" for change status
And I change "Single Form" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then "Single Form" Status should changed and displayed on View Form RH-panel and status link disabled
When I closed opened new window
Then "Single Form" updated Status should set on Forms tab And "Assign Status" action completed
When I select "Single Form" And perform "Edit" and "Status" for change status
Then "Status Change" popup should not Opened And Status Change hyperlink should "disabled" on View Form
When I closed opened new window
And I switch to <C> user
Given I am on "Project Forms" tab
And I select "Batch Forms" And perform "Edit" and "Status" for change status
And I change "Batch Forms" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then "Batch Forms" updated Status should set on Forms tab
# Closed Status And Verify All Actions Cleared #
When I select "Batch Forms" And perform "Edit" and "Status" for change status
And I change "Forms" Status as "Closed" and entered Status Change notes And I click on "Change Status" Button of popup
Then "Batch Forms" updated Status should set on Forms tab And "Distribute" action cleared
When I switch to <D> user
Given I am on "Project Forms" tab
Then "Batch Forms" updated Status should set on Forms tab And "For Acknowledgement" action cleared And "For Information" action assigned
When I switch to <E> user
Given I am on "Project Forms" tab
Then "Batch Forms" updated Status should set on Forms tab And "For Action" action cleared And "For Information" action assigned
When I switch to <B> user
Given I am on "Project Forms" tab
Then "Batch Forms" updated Status should set on Forms tab And "Assign Status" action cleared
# Verify Status Not Changed #
When I switch to <A> user
Given I am on "Project Forms" tab
Then I verify both Form could not Change Status And "Status Change" hyperlink "disabled" on View Form
When I switch to <B> user
Given I am on "Project Forms" tab
Then I verify both Form could not Change Status And "Status Change" hyperlink "disabled" on View Form
When I switch to <C> user
Given I am on "Project Forms" tab
Then I verify both Form could not Change Status And "Status Change" hyperlink "disabled" on View Form
# Change Status With Re-open Single Form Previlage User #
When I switch to <D> user
Given I am on "Project Forms" tab
## Verify Batch Form Disabled ##
And I select "Batch Forms" And perform "Edit" and "Status" for change status
Then "Status" context Option should disabled
Given I am on "Project Forms" tab
## Open Closed Single Form ##
And I select "Single Form" And perform "Edit" and "Status" for change status
And I change "Single Form" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then "Single Form" Status should changed and displayed on View Form RH-panel
When I closed opened new window
## Re-Open Opened Single Form ##
And I select "Single Form" And perform "Edit" and "Status" for change status
And I change "Single Form" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then "Single Form" Status should changed and displayed on View Form RH-panel
When I closed opened new window
# Change Status With Re-open Batch Forms Previlage User #
When I switch to <E> user
Given I am on "Project Forms" tab
## Open Closed Batch Forms ##
And I select "Batch Closed Forms" And perform "Edit" and "Status" for change status
And I change "Batch Forms" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then "Batch Closed Forms" updated Status should set on Forms tab
## Re-Open Opened Batch Forms ##
And I select "Batch Forms" And perform "Edit" and "Status" for change status
And I change "Batch Forms" Status and entered Status Change notes And I click on "Change Status" Button of popup
Then "Batch Forms" updated Status should set on Forms tab
Examples: 
|DC_Center|Project_Name|App_Group|App_Type|Users|Actions|A|B|C|D|E|
|UK|AutomationTestProject|Customer Forms|Form Status Check|Automation Non_Admin2,Automation Non_Admin3,Automation Non_Admin4,Automation Non_Admin5|Assign Status,Distribute,For Acknowledgement,For Action|Automation Non_Admin1|Automation Non_Admin2|Automation Non_Admin3|Automation Non_Admin4|Automation Non_Admin5|
#|US|Automatic_Test_US_WS|Customer Forms|Form Status Check|Automation Non_Admin2,Automation Non_Admin3,Automation Non_Admin4,Automation Non_Admin5|Assign Status,Distribute,For Acknowledgement,For Action|Automation Non_Admin1|Automation Non_Admin2|Automation Non_Admin3|Automation Non_Admin4|Automation Non_Admin5|
#|AUS|Automatic_Test_AUS_WS|Customer Forms|Form Status Check|Automation Non_Admin2,Automation Non_Admin3,Automation Non_Admin4,Automation Non_Admin5|Assign Status,Distribute,For Acknowledgement,For Action|Automation Non_Admin1|Automation Non_Admin2|Automation Non_Admin3|Automation Non_Admin4|Automation Non_Admin5|