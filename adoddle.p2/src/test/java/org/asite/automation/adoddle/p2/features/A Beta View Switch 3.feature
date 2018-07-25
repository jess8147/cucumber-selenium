Feature: A Beta View Switch 3

@P2T3
Scenario Outline: Switch to Beta Or Classic view 3
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
#And I set form view as defined in config for <Username>
Given I am on "Files" tab
And I set file view as defined in config for <Username>
Examples: 
|DC_Center|Username|Password|
|UK|auto@atest.com|Test@12345|
|UK|auto_rfi@atest.com |Test@12345|
|UK|pa_builder@auto.com|Test@12345|
|UK|rfi_tctestorg2@tctestorg2.com|Test@12345|
#|US|auto@atest.com|Test@12345|
#|US|auto_rfi@atest.com |Test@12345|
#|US|pa_builder@auto.com|Test@12345|
#|US|rfi_tctestorg2@tctestorg2.com|Test@12345|