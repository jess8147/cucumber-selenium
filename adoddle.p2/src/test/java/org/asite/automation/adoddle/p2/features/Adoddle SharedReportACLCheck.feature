Feature: Adoddle Shared Report ACL

@P2T7
Scenario Outline: Shared Report ACL Validations
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Reports" tab
When I click link Edit AND Schedule in LH Panel
When I have search Report with name as "AutomationTest_ExpressSharedReport"
Then Report "AutomationTest_ExpressSharedReport" should available in Report listing successfully
And I validated set ACL "Admin" for user "Automation UK P2" successfully
When I logged in as <UserA>
Then I should re-directed to Dashboard of user "TC Bloggs"
Given I am on "Reports" tab
When I click link Edit AND Schedule in LH Panel
When I have search Report with name as "AutomationTest_ExpressSharedReport"
Then Report "AutomationTest_ExpressSharedReport" should not available in Report listing successfully
And I validated set ACL "No Access" for user "TC Bloggs" successfully
When I logged in as <UserB>
Then I should re-directed to Dashboard of user "RFI Bloggs"
Given I am on "Reports" tab
When I click link Edit AND Schedule in LH Panel
When I have search Report with name as "AutomationTest_ExpressSharedReport"
Then Report "AutomationTest_ExpressSharedReport" should available in Report listing successfully
And I validated set ACL "View" for user "RFI Bloggs" successfully
When I logged in as <UserC>
Then I should re-directed to Dashboard of user "PA Bloggs"
Given I am on "Reports" tab
When I click link Edit AND Schedule in LH Panel
When I have search Report with name as "AutomationTest_ExpressSharedReport"
Then Report "AutomationTest_ExpressSharedReport" should available in Report listing successfully
And I validated set ACL "Schedule Report" for user "PA Bloggs" successfully
When I logged in as <UserD>
Then I should re-directed to Dashboard of user "DC Bloggs"
Given I am on "Reports" tab
When I click link Edit AND Schedule in LH Panel
When I have search Report with name as "AutomationTest_ExpressSharedReport"
Then Report "AutomationTest_ExpressSharedReport" should available in Report listing successfully
And I validated set ACL "Delegate Permission" for user "DC Bloggs" successfully
Examples: 
|DC_Center|UserA|UserB|UserC|UserD|
|UK|TC Bloggs|RFI Bloggs|PA Bloggs|DC Bloggs|
#|US|TC Bloggs|RFI Bloggs|PA Bloggs|DC Bloggs|
#|AUS|TC Bloggs|RFI Bloggs|PA Bloggs|DC Bloggs|