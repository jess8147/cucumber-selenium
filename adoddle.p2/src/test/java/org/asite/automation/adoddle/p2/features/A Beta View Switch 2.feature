Feature: A Beta View Switch 2

@P2T2
Scenario Outline: Switch to Beta Or Classic view 2
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
#And I set form view as defined in config for <Username>
Given I am on "Files" tab
And I set file view as defined in config for <Username>
Examples: 
|DC_Center|Username|Password|
|UK|auto_ukp2@atest.com|Test@12345|
|UK|auto_uk1@atest.com|Test@12345|
|UK|auto.search_uk@atest.com|Test@12345|
|UK|auto.wcu_uk@atest.com|Test@12345|
#|US|auto_usp2@atest.com|Test@12345|
#|US|auto_usP2@atest.com|Test@12345|
#|US|auto.search_us@atest.com|Test@12345|
#|US|auto.wcu_us@atest.com|Test@12345|