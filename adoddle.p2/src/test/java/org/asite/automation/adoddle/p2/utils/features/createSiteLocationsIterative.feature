Feature: Create Site Locations for Existing Project

@createBulkSitesLocations
Scenario Outline: Create Site Location
Given Project DC <DC_Center> is available
And I am already logged in with multi-Project user
Given I am on "Field" tab
Given I read data from excel and create site locations
Examples: 
|DC_Center|
|UK|