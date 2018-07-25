Feature: A Beta View Switch 1

@P2T2
Scenario Outline: Switch to Beta Or Classic view 1
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
#And I set form view as defined in config for <Username>
Given I am on "Files" tab
And I set file view as defined in config for <Username>
Examples: 
|DC_Center|Username|Password|
|UK|auto_ukp@atest.com|Test@12345|
|UK|auto_uk@atest.com|Test@12345|
|UK|auto.nfpw_uk1@atest.com|Test@12345|
|UK|auto.nfpw_uk2@atest.com|Test@12345|
#|US|auto_usp@atest.com|Test@12345|
#|US|auto_us@atest.com|Test@12345|
#|US|auto.nfpw_us1@atest.com|Test@12345|
#|US|auto.nfpw_us2@atest.com|Test@12345|
