Feature: Clean Projects

@cleanProjects
Scenario Outline: Clean Projects with Text
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
Given I deactivate all projects with text <ProjectSearchText>
Examples: 
|DC_Center|ProjectSearchText|Username|Password|
|UK|Cloned|auto@atest.com|Test@12345|
#|US|Cloned|auto@atest.com|Test@12345|