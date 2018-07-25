Feature: Simple Upload Load Test

@uploadLoadTest
Scenario Outline: Simple Upload Load Test
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
Given I am on "Files" tab
Given I have focus on <Project> project
And I have focus on "San-1-Files Upload" folder
And I publish "250" files using simple upload for <n> times
Examples: 
|DC_Center|Project|Username|Password|n|
|UK|UK Channel WS|uk2018@channel.com|uk@12345|5|
#|US|Channel US|us2018@channel.com|us@12345|50|
