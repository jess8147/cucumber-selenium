Feature: A Beta View Switch 6

@P2T3
Scenario Outline: Switch to Beta Or Classic view 6
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
#And I set form view as defined in config for <Username>
Given I am on "Files" tab
And I set file view as defined in config for <Username>
Examples: 
|DC_Center|Username|Password|
|UK|auto.testuser2@atest.com|Test@12345|
|UK|pa_tctestorg1@tctestorg1.com|Test@12345|
|UK|auto.multi_dc_user@atest.com|Test@12345|
|UK|auto.non_admin1@atest.com|Test@12345|
#|US|auto.testuser2@atest.com|Test@12345|
#|US|pa_tctestorg1@tctestorg1.com|Test@12345|
#|US|auto.multi_dc_user@atest.com|Test@12345|
#|US|auto.non_admin1@atest.com|Test@12345|