Feature: A Message Load Testing2

@loadTest2
Scenario Outline: A Message Load Testing
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
And I send test messages to channel <Channel> every <n> seconds for <x> duration
Examples: 
|DC_Center|Username|Password|Channel|n|x|
|UK|uk2018@channel.com|uk@12345|Asite Team - internal load test Group|0|45m|
#|US|us2018@channel.com|us@12345|Asite Team - internal load test Group|0|45m|
