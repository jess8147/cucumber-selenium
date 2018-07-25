Feature: New Files Published With Actions - No Comment Widget

@P2T7
Scenario Outline: New Files Published Dashboard "PAST WEEK" Highcharts AND "No Comment" Widget
Given Project DC <DC_Center> is available
And I am already logged in using <User_ID> user
When I click on "PAST WEEK" highcharts
And I upload "3" files in "AutomationUploadFiles" folder
Given I am on "Dashboard" tab
When I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select files for perform "NoComment" Distribution widget
And I right click and select context menu option "Share" AND I click on "Distribute Files" widget
Then "Distribute" popup should open
When I assign "For Comment" and "For Distribution" actions to User <B>, <C> AND <D>
And I click on "Distribute" button
When I get total Incomplete Actions count of User <A>
And I get "Discussions" tab total Unread count of User <A>
And I switch to <B> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select Distributed files for User <B>
And I right click and select context menu option "Share" AND I click on "Distribute Files" widget
Then "Distribute" popup should open
When I assign "For Comment" action to User <C>
And I click on "Distribute" button
Then "For Distribution" action should completed for distributed documents
When I get total Incomplete Actions count of User <B>
And I get "Discussions" tab total Unread count of User <B>
And I switch to <D> user
And I get total Incomplete Actions count of User <D>
And I get "Discussions" tab total Unread count of User <D>
And I switch to <C> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select single Distributed file for User <C>
And I right click on selected Comment Files AND drag mouse to "New" AND I click on "No Comment"
Then "New" popup should open
And User <A> and User <B> should pre-populated on "To" field
And Comment "Title" AND "Description" area should displayed as disabled
When I Click on "Submit" button
Then "For Comment" action should completed for selected "Single" document
When I switch to <A> user
Then Total Incomplete Actions count of User <A> should incresed
And "Discussions" tab total Unread count of User <A> should incresed
When I switch to <B> user
Then Total Incomplete Actions count of User <B> should incresed
And "Discussions" tab total Unread count of User <B> should incresed
When I switch to <D> user
Then Total Incomplete Actions count of User <D> should not incresed
And "Discussions" tab total Unread count of User <D> should not incresed
When I switch to <C> user
And I click on "PAST WEEK" highcharts
When I select multiple Distributed files for User <C>
And I right click on selected Comment Files AND drag mouse to "New" AND I click on "No Comment" for Multiple files
Then "New" popup should open
And No Users should pre-populated on "To" field
And Comment "Title" AND "Description" area should displayed as disabled
When I Click on "Submit" button
Then "For Comment" action should completed for selected all "Multiple" documents
When I switch to <A> user
Then Total Incomplete Actions count of User <A> should incresed
And "Discussions" tab total Unread count of User <A> should incresed
When I switch to <B> user
Then Total Incomplete Actions count of User <B> should incresed
And "Discussions" tab total Unread count of User <B> should incresed
When I switch to <D> user
Then Total Incomplete Actions count of User <D> should not incresed
And "Discussions" tab total Unread count of User <D> should not incresed
# For User-E #
When I switch to <E> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select Distributed files for User <E>
And I right click and select context menu option "Share" AND I click on "Distribute Files" widget
Then "Distribute" popup should open
When I assign "For Comment" action to User <C>
And I click on "Distribute" button
And I get total Incomplete Actions count of User <E>
And I get "Discussions" tab total Unread count of User <E>
And I switch to <C> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select single Distributed file for User <C>
And I right click on selected Comment Files AND drag mouse to "New" AND I click on "No Comment"
Then "New" popup should open
And Only User <E> should pre-populated on "To" field
And Comment "Title" AND "Description" area should displayed as disabled
When I Click on "Submit" button
Then "For Comment" action should completed for selected "Single" document
When I switch to <E> user
Then Total Incomplete Actions count of User <E> should incresed
And "Discussions" tab total Unread count of User <E> should incresed
When I switch to <A> user
Then Total Incomplete Actions count of User <A> should not incresed
And "Discussions" tab total Unread count of User <A> should not incresed
When I switch to <B> user
Then Total Incomplete Actions count of User <B> should not incresed
And "Discussions" tab total Unread count of User <B> should not incresed
When I switch to <C> user
And I click on "PAST WEEK" highcharts
Then I should redirect to "Files" tab
When I select multiple Distributed files for User <C>
And I right click on selected Comment Files AND drag mouse to "New" AND I click on "No Comment" for Multiple files
Then "New" popup should open
And No Users should pre-populated on "To" field
And Comment "Title" AND "Description" area should displayed as disabled
When I Click on "Submit" button
Then "For Comment" action should completed for selected all "Multiple" documents
When I switch to <E> user
Then Total Incomplete Actions count of User <E> should incresed
And "Discussions" tab total Unread count of User <E> should incresed
When I switch to <A> user
Then Total Incomplete Actions count of User <A> should not incresed
And "Discussions" tab total Unread count of User <A> should not incresed
When I switch to <B> user
Then Total Incomplete Actions count of User <B> should not incresed
And "Discussions" tab total Unread count of User <B> should not incresed
Examples: 
|DC_Center|User_ID|A|B|C|D|E|
|UK|auto.nfpw_uk1@atest.com|Auto_FWidget UK1|Auto_FWidget UK2|Auto_FWidget User1|Auto_FWidget User2|Auto_FWidget User3|
#|US|auto.nfpw_us1@atest.com|Auto_FWidget US1|Auto_FWidget US2|Auto_FWidget User1|Auto_FWidget User2|Auto_FWidget User3|
#|AUS|auto.nfpw_aus1@atest.com|Auto_FWidget AUS1|Auto_FWidget AUS2|Auto_FWidget User1|Auto_FWidget User2|Auto_FWidget User3|