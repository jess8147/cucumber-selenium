package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleProcurementLocators {
	
	public static class ProcurementTab {
		/* CatalogueView-Procurement */
		/*public static By	pop_ViewCatalogueModel				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='viewCatalogModal']");*/
		public static By	css_CataloguesContractCheckboxList	= By.xpath(".//div[@class='divtr rows proc-view-catalog' and @index]//input");
		public static By	lnk_CataloguesNextPage				= By.xpath(".//div[@id='procCatPaging']//li[@title='Next Page']//a");
		public static By	lnk_PublishCatalogue				= By.cssSelector("div[id='catalogContainer'] a[class*='btn-default'][data-toggle='Upload']");
		public static By	btn_UploadCataloguePublishFile		= By.xpath(".//div[@class='controls']//div[@data-provides='fileupload']//input[@id='selectFile']");
		public static By	btn_UploadCataloguePublishImage		= By.xpath(".//div[@class='controls']//div[@data-provides='fileupload']//input[@id='selectImage']");
		public static By	lnk_FirstPublishCatalogueID			= By.xpath(".//div[@class='divtr rows proc-view-catalog' and @index='0']//div[contains(@class,'col-id')]//a");
		public static By	lnk_FirstPublishCatalogueStatus		= By.xpath(".//div[@class='divtr rows proc-view-catalog' and @index='0']//div[contains(@class,'statusText')]//a");
		public static By	ele_FirstPublishCatalogueStatus		= By.xpath(".//div[@class='divtr rows proc-view-catalog' and @index='0']//div[contains(@class,'statusText')]");
		public static By	lbl_ViewCataloguePopup				= By.xpath(".//div[@aria-hidden='false']//div[@id='productListingSection']//h2");
		public static By	img_ChangeCatalogueStatus			= By.xpath(".//div[@id='productStaticContent']//img[@class='change-catalogue-status' and contains(@style,'display')]");
		public static By	pop_ChangeCatalogueStatus			= By.xpath(".//div[@id='change-catalog-status-modal' and @aria-hidden='false' and contains(@style,'display: block')]");
		public static By	sel_CatalogueStatus					= By.xpath(".//div[@aria-hidden='false']//select[@name='status']");
		public static By	txt_ChangeCatalogueStatusComment	= By.xpath(".//div[@aria-hidden='false']//textarea[@name='reason']");
		public static By	css_CatalogueProductItems			= By.xpath(".//div[@aria-hidden='false']//div[@id='list-view-product']/div[@index]//span[contains(@class,'productName')]");
		public static By	lbl_ClientClassificationTree		= By.xpath(".//div[@aria-hidden='false']//div[@class='categoryRoot']//span");
		public static By	css_SearchPanelFields				= By.xpath(".//div[@aria-hidden='false']//div[@id='productListingSection']//span[not(contains(@class,'hide'))]//label");
		public static By	txt_SearchCatalogueDescription		= By.xpath(".//div[contains(@style,'display: block')]//input[@name='description']");
		public static By	btn_FirstProductViewDetailsButtton	= By.xpath(".//div[@aria-hidden='false']//div[@id='list-view-product']/div[@index='0']//button[@class='btn btn-mini btn-danger']");
		public static By	pop_ProductDetails					= By.xpath(".//div[@id='viewProcutDetailsModal' and @aria-hidden='false']");
		public static By	lnk_ViewItemsPopupListView			= By.xpath(".//div[@aria-hidden='false']//a[@class='view-grid list-view switch-view' and @title='List View']");
		
	}
	
}