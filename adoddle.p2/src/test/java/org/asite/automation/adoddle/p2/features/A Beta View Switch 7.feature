Feature: A Beta View Switch 7

@P2T4
Scenario Outline: Switch to Beta Or Classic view 7
Given Project DC <DC_Center> is available
When I login to adoddle with <Username> and <Password>
#And I set form view as defined in config for <Username>
Given I am on "Files" tab
And I set file view as defined in config for <Username>
Examples: 
|DC_Center|Username|Password|
|UK|auto.non_admin2@atest.com|Test@12345|
|UK|auto.non_admin3@atest.com|Test@12345|
|UK|auto.non_admin4@atest.com|Test@12345|
|UK|auto.non_admin5@atest.com|Test@12345|
#|US|auto.non_admin2@atest.com|Test@12345|
#|US|auto.non_admin3@atest.com|Test@12345|
#|US|auto.non_admin4@atest.com|Test@12345|
#|US|auto.non_admin5@atest.com|Test@12345|