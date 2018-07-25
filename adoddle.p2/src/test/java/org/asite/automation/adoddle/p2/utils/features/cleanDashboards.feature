Feature: Clean Dashboards

@cleanDashboards
Scenario Outline: Clean Projects with Text
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
Given I deactivate all dashboards with text <DashboardText>
Examples: 
|DC_Center|DashboardText|Username|Password|
|UK|AutoTestDashboard|auto_ukp2@atest.com|Test@12345|
#|US|AutoTestDashboard|auto_usp2@atest.com|Test@12345|
#|AUS|AutoTestDashboard|auto_ausp2@atest.com|Test@12345|