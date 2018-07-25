Feature: A Beta View Switch 4

@P2T4
Scenario Outline: Switch to Beta Or Classic view 4
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
#And I set form view as defined in config for <Username>
Given I am on "Files" tab
And I set file view as defined in config for <Username>
Examples: 
|DC_Center|Username|Password|
|UK|pa_tctestorg2@tctestorg2.com|Test@12345|
|UK|tc_tctestorg1@tctestorg1.com|Test@12345|
|UK|auto_pa@atest.com|Test@12345|
|UK|auto.nfpw_user1@atest.com|Test@12345|
#|US|pa_tctestorg2@tctestorg2.com|Test@12345|
#|US|tc_tctestorg1@tctestorg1.com|Test@12345|
#|US|auto_pa@atest.com|Test@12345|
#|US|auto.nfpw_user1@atest.com|Test@12345|