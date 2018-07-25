Feature: PublishPDF via Workflow on EditAttributes

@P2T2
Scenario Outline: Publish Document Revision as PDF on Edit Attributes
Given Project DC <DC_Center> is available
And I am already logged in
Given I am on "Files" tab
And I have focus subFolder "Automation_WF_PublishPDF" in folder "Automation_WF_Folder"
And I click on Add Files button
Then "Upload" popup should open
When I clicked on "Select File (s)" button AND I have selected file "AutoTestArchitectureDesign.jpg" from Local
And I have entered all mandatory attributes AND i have attached secondary file as "AutoArchitectureCorrectDesign.png"
And I click on Upload button
Then file "AutoTestArchitectureDesign" is uploaded successfully AND available in listing
And I have created multiple comments successfully
When I switch to <User> user
Given I am on "Files" tab
And I have search document "AutoTestArchitectureDesign" successfully AND I edit attributes poi as "For Review" successfully
Then "AutoTestArchitectureDesign" file version should updated AND Published as PDF document in listing
And I have download file "AutoTestArchitectureDesign" successfully in Local
And I have validated file size with created test document successfully
When I have apply filter as "Current" AND "Superseded" on file listing
Then All Current AND Superseded Versions of published documents should available in file Listing
And I have validated Workflow Status as "COMPLETED" AND workflow Stage as "--" on previous Issue of published document successfully
Examples: 
|DC_Center|User|
|UK|TC Bloggs|
#|US|TC Bloggs|
#|AUS|TC Bloggs|