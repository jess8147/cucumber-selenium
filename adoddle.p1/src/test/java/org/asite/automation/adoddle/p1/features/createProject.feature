Feature: Create Project

@P1T5
Scenario Outline: Create Edit Deactivate Project
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
And I am on "Projects" tab
Then "Add Project" button should be displayed
When I click on "Add Project" in LH panel
Then "Create Project" popup should open
When I have entered ProjectName AND Description AND I have selected Geography as <Geography>
And I click on "Create" button on popup
Then Project should be created AND I should be redirected on "Projects" tab AND Project with ProjectName should be available in project listing
When I click again Project Tab AND I right click on ProjectName project AND I clicked on Edit Project
Then "Edit Project" popup should open
When I Change the ProjectName AND I click on save button
Then New ProjectName project should be available in project listing
When I right click again on ProjectName project AND I clicked on Edit Project AND I click on "Status" dropdown ANd selected "Closed" AND click on save button
Then ProjectName project should be closed AND ProjectName project should not be available in project listing
Examples: 
|DC_Center|Geography|
|UK|United Kingdom (EU01)|
#|US|North America (US01)|
#|AUS|Australia (AU01)|