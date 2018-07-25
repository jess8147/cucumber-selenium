Feature: View IFC Files

@P1T4
Scenario Outline: View Model
Given Project DC <DC_Center> is available
And I am already logged in
And I am on Models tab
And I have atleast one model on Models listing
#And I have selected "Browser-Rendering" viewer on Models Page
When I Click on "Model Name"
Then Selected model should be open in new tab
And Model should be open in "Browser-Rendering" viewer
And IFC files should be available to view in Browser-Rendering Viewer
#And I have selected "Server-Rendering" viewer on Models Page
#When I Click on "Model Name"
#Then Selected model should be open in new tab
#And Model should be open in "Server-Rendering" viewer
#And IFC files should be available to view in Server-Rendering Viewer
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|