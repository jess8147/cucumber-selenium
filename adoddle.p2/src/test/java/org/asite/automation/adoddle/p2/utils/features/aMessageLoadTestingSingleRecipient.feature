Feature: A Message Load Testing Single Recipient

@exclude
Scenario Outline: A Message Load Testing
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
And I send test messages to user <User> every <n> seconds for <x> duration
Examples: 
|DC_Center|Username|Password|User|n|x|
|UK|uk2018@channel.com|uk@12345|Priyam Patel (5290)|2|3600s|
#|UK|uk2018@channel.com|uk@12345|Jasmin Prajapati (5375)|2|3600s|
#|US|us2018@channel.com|us@12345|US Channel|30|36000s|
