Feature: A Beta View Switch 5

@P2T2
Scenario Outline: Switch to Beta Or Classic view 5
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
#And I set form view as defined in config for <Username>
Given I am on "Files" tab
And I set file view as defined in config for <Username>
Examples: 
|DC_Center|Username|Password|
|UK|auto.nfpw_user2@atest.com|Test@12345|
|UK|auto.nfpw_user3@atest.com|Test@12345|
|UK|dc_tctestorg1@tctestorg1.com|Test@12345|
|UK|auto.testuser1@atest.com|Test@12345|
#|US|auto.nfpw_user2@atest.com|Test@12345|
#|US|auto.nfpw_user3@atest.com|Test@12345|
#|US|dc_tctestorg1@tctestorg1.com|Test@12345|
#|US|auto.testuser1@atest.com|Test@12345|