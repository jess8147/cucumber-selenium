Feature: Global Federated Search

@P2T2
Scenario Outline: Content Search auto created Data before scenario Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I deactivated all Cloned projects only excepts <Project_Name> project
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.fsearch_uk@atest.com|
#|US|Automatic_Test_US_WS|auto.fsearch_us@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.fsearch_aus@atest.com|

@P2T2
Scenario Outline: Create Cloned project for Content Search texts
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
When I create Cloned project from existing template <Project_Template> for <DC_Center>
And I search created Cloned project And I give admin access to <User_ID> and <Distribute_User> users
Examples:
|DC_Center|User_ID|Distribute_User|Project_Template|
|UK|auto.fsearch_uk@atest.com|auto.noaccess_uk@atest.com|AutomationTestProject_Template|
#|US|auto.fsearch_us@atest.com|auto.noaccess_us@atest.com|Automatic_Test_US_Template|
#|AUS|auto.fsearch_aus@atest.com|auto.noaccess_aus@atest.com|Automatic_Test_AUS_Template|

@P2T2
Scenario Outline: Content Search with different texts for files
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I have already created TestData for global Content search text
# Global Search #
When I globally search Content search text
Then Content search text contain All "Files" should displayed
# Active Tab Search #
Given I am on "Files" tab
When I search Content search text on "Files" tab
Then Content search text contain All "Files" should displayed
# With New Upload/Creation Search #
Given I am on "Files" tab
When I click on Cloned project AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I upload three files in one contains Content search text, second one contains Content search text in Doc Title and other one contains Content search text in Revision notes And distribute to <Distribute_User> user with "For Information, For Comment, For Status Change" actions
Given I am on "Files" tab
When I search Content search text on "Files" tab
Then Content search text contain All "Files" should displayed
When I globally search Content search text
Then Content search text contain All "Files" should displayed
# Perform Actions with Distribute User #
When I Login to <Distribute_User> User
And I globally search Content search text
#
And I click on Global Search "Files" LH tab
Then distributed all files with assigned "For Information, For Comment, For Status Change" actions should displayed
# -> Single file -> "For Information" action :
When I select Single file and perform "For Information" action with hyperlink
Then "For Information" action should completed on selected "single" file
# -> Multiple files -> "For Information" action :
When I select all files and perform right click and select "Tasks" and "For Information"
Then "Task - For Information" popup should open
When I click on "OK" link of popup
Then "For Information" action should completed for all files
# -> Multiple files -> "For Comment" action :
When I select all files and perform right click and select "New" and "Start a Discussion"
Then "New" popup should open
When I create Comment for selected all files
Then "For Comment" action should completed for all files
# -> Multiple files -> "For Status Change" action :
When I select all files and perform right click and select "Edit" and "Status"
Then "Status Change" popup should open
When I Change Status for selected all files
Then "For Status Change" action should completed for all files
Examples:
|DC_Center|User_ID|Distribute_User|
|UK|auto.fsearch_uk@atest.com|auto.noaccess_uk@atest.com|
#|US|auto.fsearch_us@atest.com|auto.noaccess_us@atest.com|
#|AUS|auto.fsearch_aus@atest.com|auto.noaccess_aus@atest.com|

@P2T2
Scenario Outline: Content Search with different texts for comments
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I have already created TestData for global Content search text
# Global Search #
When I globally search Content search text
Then Content search text contain All "Comments" should displayed
# Active Tab Search #
Given I am on "Discussions" tab
When I search Content search text on "Discussions" tab
Then Content search text contain All "Comments" should displayed
# With New Upload/Creation Search #
Given I am on "Files" tab
When I click on Cloned project AND I click on Folder Name "AutomationUploadFiles" AND I click on "Add Files" button
Then "Upload" popup should open
When I upload new file for Discussion
And I create Content search text contain discussion on file and distribute to <Distribute_User> user
Given I am on "Discussions" tab
When I search Content search text on "Discussions" tab
Then Content search text contain All "Comments" should displayed
When I globally search Content search text
Then Content search text contain All "Comments" should displayed
# Perform Actions with Distribute User #
When I Login to <Distribute_User> User
And I globally search Content search text
#
When I click on Global Search "Discussions" LH tab
Then distributed Un Read Discussion should displayed
When I perform distributed Un Read discussion
Then distributed discussion should "Read"
Examples:
|DC_Center|User_ID|Distribute_User|
|UK|auto.fsearch_uk@atest.com|auto.noaccess_uk@atest.com|
#|US|auto.fsearch_us@atest.com|auto.noaccess_us@atest.com|
#|AUS|auto.fsearch_aus@atest.com|auto.noaccess_aus@atest.com|

@P2T2
Scenario Outline: Content Search with different texts for forms
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I have already created TestData for global Content search text
# Global Search #
When I globally search Content search text
And Content search text contain All "Project Forms" should displayed
# Active Tab Search #
Given I am on "Project Forms" tab
When I search Content search text on "Project Forms" tab
Then Content search text contain All "Project Forms" should displayed
# With New Upload/Creation Search #
Given I am on "Project Forms" tab
When I select Cloned project AND go in "Communications" group AND select "Request For Information" app type
And I create Content search text contain "Description" Form And distribute to <Distribute_User> user with "For Information, Assign Status, Respond" actions
When I select Cloned project AND go in "Form Distibution Group" group AND select "Check Form Distribution" app type
And I create Content search text contain "Internal Ref" Form And distribute to <Distribute_User> user with "For Information, Assign Status, Respond" actions
When I select Cloned project AND go in "Form Distibution Group" group AND select "Check Form Distribution" app type
And I create Content search text contain "Custom Field" Form And distribute to <Distribute_User> user with "For Information, Assign Status, Respond" actions
Given I am on "Project Forms" tab
And I search Content search text on "Project Forms" tab
Then Content search text contain All "Project Forms" should displayed
When I globally search Content search text
Then Content search text contain All "Project Forms" should displayed
# Perform Actions with Distribute User #
When I Login to <Distribute_User> User
And I globally search Content search text
#
When I click on Global Search "Project Forms" LH tab
Then distributed all forms with assigned "For Information, Assign Status, Respond" actions should displayed
# -> Single form -> "For Information" action :
When I select Single form and perform "For Information" action with hyperlink
Then "For Information" action should completed on selected "single" form
# -> Multiple form -> "For Information" action :
And I select all forms and perform right click and select "Tasks" and "For Information"
Then "Task - For Information" popup should open
When I click on "OK" link of popup
Then "For Information" action should completed for all forms
# -> Single form -> "Respond" action :
When I select Single form and perform "Respond" action with hyperlink
Then "Respond" action should completed on selected "single" form
# -> Multiple form -> "Assign Status" action :
When I select all forms and perform right click and select "Edit" and "Status"
Then "Status Change" popup should open
When I Change Status for selected all forms
Then "Assign Status" action should completed for all forms
Examples:
|DC_Center|User_ID|Distribute_User|
|UK|auto.fsearch_uk@atest.com|auto.noaccess_uk@atest.com|
#|US|auto.fsearch_us@atest.com|auto.noaccess_us@atest.com|
#|AUS|auto.fsearch_aus@atest.com|auto.noaccess_aus@atest.com|

@P2T2
Scenario Outline: Content Search with different texts for models
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I have already created TestData for global Content search text
# Global Search #
When I globally search Content search text
And Content search text contain All "Models" should displayed
# Active Tab Search #
Given I am on "Models" tab
When I search Content search text on "Models" tab
Then Content search text contain All "Models" should displayed
# With New Upload/Creation Search #
Given I am on "Models" tab
When I create Content search text contain "Description" Model in created Cloned project
And I upload IFC file in created "Description" Model
When I search Content search text on "Models" tab
Then Content search text contain All "Models" should displayed
When I globally search Content search text
And Content search text contain All "Models" should displayed
# Perform Actions with Distribute User #
When I Login to <Distribute_User> User
And I globally search Content search text
#
And I click on Global Search "Models" LH tab
Then created Content search text contain Model should displayed
When I mousehover on Model and click on View Model
Then New tab should be opened and Model should Viewed
Examples:
|DC_Center|User_ID|Distribute_User|
|UK|auto.fsearch_uk@atest.com|auto.noaccess_uk@atest.com|
#|US|auto.fsearch_us@atest.com|auto.noaccess_us@atest.com|
#|AUS|auto.fsearch_aus@atest.com|auto.noaccess_aus@atest.com|

@P2T2
Scenario Outline: Content Search auto created Data after scenario Cleanup
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
And I deactivated all Cloned projects only excepts <Project_Name> project
Examples: 
|DC_Center|Project_Name|User_ID|
|UK|AutomationTestProject|auto.fsearch_uk@atest.com|
#|US|Automatic_Test_US_WS|auto.fsearch_us@atest.com|
#|AUS|Automatic_Test_AUS_WS|auto.fsearch_aus@atest.com|