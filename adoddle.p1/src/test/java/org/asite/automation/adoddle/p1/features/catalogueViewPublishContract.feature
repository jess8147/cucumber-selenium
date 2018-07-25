Feature: CatalogueView Procurement

@P1T5
Scenario Outline: Publish catalogue against a contract
Given Project DC <DC_Center> is available
Given I am already logged in
And I logout and re-login with procurement user
And I am on "Procurement" tab
And I clicked on "Catalogues" in LH panel
And I select Catalogue contract value "TT1" Catalogues from catalouge listing
When I click on "Publish Catalogue" button from catalouge listing
And I have selected valid catalogue and valid zip image file from local
And I click on Submit button
Then Current popup window should be closed And Catalouge should be published successfully without any error
And "Catalogue Publishing - Successful" popup should open
When I click on "OK" button on "Catalogue Publishing - Successful" popup
Then Catalogue listing should be refreshed And Latest published catalogue should be listed on top
And Status of the catalogue should be changed to "Pending Approval"
When I click on "Pending Approval" Status of latest published catalogue
Then "View Items" popup should be opened
When I click on "Change Catalogue Status" icon on View catalogue popup
Then "Change Catalogue Status" popup should be open
When I select "Approved" in Catalogue Status dropdown AND Give "AutomationTest" in Comments textarea (Optional)
And I click on Submit button
Then Status of the catalogue should be changed to "Approved"
When I click on "ID" of recently published catalogue
Then "View Items" popup should be opened
And Catalogue items should be listed on right hand panel
And Catalogue classification tree should be displayed on left hand side
And different fields should be available in search panel like "Part Number" AND "Description" AND "Supplier Name"
When Click on "View Details" button of any Product
Then "Product Details" popup should open into View Details
And Details of product should be available on Product Details popup
Examples: 
|DC_Center|
||
|UK|