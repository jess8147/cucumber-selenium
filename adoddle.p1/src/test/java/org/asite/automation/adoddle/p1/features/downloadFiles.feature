Feature: Download Single File

@P1T2
Scenario Outline: Download single file
Given Project DC <DC_Center> is available
And I am already logged in 
And I am on "Files" tab 
And I have atleast one document in document listing
When I Click file icon in "Type" column 
Then Document should be downloaded in local
Examples: 
|DC_Center|
|UK|
#|US|
#|AUS|