package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProcurementLocators.ProcurementTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CatalogueViewScripts extends AdoddleCommonAppMethods {

	private List<WebElement>	contractList;
	private String				catalogueID;
	private HSSFWorkbook		workbook;
	private HSSFSheet			sheet;
	private Row					row;
	private Cell				cell;
	private List<String>		catalogueItemsWebFileList	= new ArrayList<String>();
	private List<String>		catalogueItemsXlsFileList	= new ArrayList<String>();
	private List<String>		productDetailsWebFileList	= new ArrayList<String>();
	private List<String>		productDetailsXlsFileList	= new ArrayList<String>();
	public static Logger		log							= AutomationLogger.getInstance().getLogger(CatalogueViewScripts.class);

	public void clickOnCatalogues(String lhPanelMenu) {
		clickLinkWithText(lhPanelMenu);
	}

	public void catalogueContractCheckboxSelect(String contractValue) {
		catalogueID = contractValue;
		collectionDataMap.put("Catalog ID", catalogueID);
		boolean flag = true;
		do {
			contractList = findElements(ProcurementTab.css_CataloguesContractCheckboxList);
			for (WebElement e : contractList) {
				if (e.isDisplayed() && e.getAttribute("value").equalsIgnoreCase(contractValue) && !e.isSelected()) {
					flag = false;
					e.click();
					break;
				}
			}
			if (flag == true)
				clickElementAndWait(ProcurementTab.lnk_CataloguesNextPage);
		}
		while (flag);
	}

	public void clickOnPublishCatalogue() {
		clickElementAndWait(ProcurementTab.lnk_PublishCatalogue);
	}

	public void selectCatalogueAndImageIntoLocal() {
		uploadFileUsingKeys(ProcurementTab.btn_UploadCataloguePublishFile, sysUtils.getFileList(ResourceHandler.loadProperty("catalogue.view.publish.file").trim()));
		uploadFileUsingKeys(ProcurementTab.btn_UploadCataloguePublishImage, sysUtils.getFileList(ResourceHandler.loadProperty("catalogue.view.publish.image").trim()));
	}

	public void clickOnSubmit() {
		clickElementAndWait(FilesTab.btn_PopSubmit);
	}

	public void verifyPublishedCatalogue() {
		verifyPopUpWithText(AdoddleCommonStringPool.CATALOG_PUBLISH_SUCCESS);
	}

	public void clickOnOk() {
		clickElementAndWait(FilesTab.btn_PopupOK);
	}

	public void verifyLatestCatalogueOnTop() {
		Assert.assertEquals(getElementText(ProcurementTab.lnk_FirstPublishCatalogueID), catalogueID.trim());
	}

	public void verifyCatalogueStatus(String status) {
		try {
			Assert.assertEquals(getElementText(ProcurementTab.lnk_FirstPublishCatalogueStatus), status.trim());
		}
		catch (Throwable t) {
			Assert.assertEquals(getElementText(ProcurementTab.ele_FirstPublishCatalogueStatus), status.trim());
		}
	}

	public void clickOnCatalogueStatus() {
		clickElementAndWait(ProcurementTab.lnk_FirstPublishCatalogueStatus);
	}

	public void verifyViewCataloguePopup(String popup) {
		Assert.assertTrue(isDisplayed(GlobalPageElements.pop_PopUpElement));
		Assert.assertTrue(getElementText(ProcurementTab.lbl_ViewCataloguePopup).contains(popup));
	}

	public void clickOnChangeCatalogueStatus() {
		clickElementAndWaitForElement(ProcurementTab.img_ChangeCatalogueStatus, ProcurementTab.pop_ChangeCatalogueStatus);
	}

	public void verifyChangeCatalogueStatusPopup(String popup) {
		Assert.assertTrue(isDisplayed(ProcurementTab.pop_ChangeCatalogueStatus));
		Assert.assertEquals(getElementText(GlobalPageElements.lbl_PopUpHeader1), popup.trim());
	}

	public void CatalogueStatusDropdownSelect(String catalogueStatus, String comment) {
		selectByVisibleText(ProcurementTab.sel_CatalogueStatus, catalogueStatus);
		sendKeys(ProcurementTab.txt_ChangeCatalogueStatusComment, comment);
	}

	public void clickOncatalogueId() {
		verifyLatestCatalogueOnTop();
		clickElementAndWait(ProcurementTab.lnk_FirstPublishCatalogueID);
	}

	public void verifyCatalogueItems() {
		if (isDisplayed(ProcurementTab.lnk_ViewItemsPopupListView)) {
			clickElementAndWait(ProcurementTab.lnk_ViewItemsPopupListView);
			waitUntilElementIsInvisible(ProcurementTab.lnk_ViewItemsPopupListView);
		}

		getProductsNameIntoExcelSheet();
		List<WebElement> catalogueList = findElements(ProcurementTab.css_CatalogueProductItems);
		for (WebElement e : catalogueList) {
			catalogueItemsWebFileList.add(e.getText().trim());
		}
		log.info("catalogueItemsWebFileList :" + catalogueItemsWebFileList);

		javaUtils.compareUnorderedList(catalogueItemsXlsFileList, catalogueItemsWebFileList);
	}

	public void verifyCatalogueClassificationTree() {
		Assert.assertEquals(getElementText(ProcurementTab.lbl_ClientClassificationTree), ResourceHandler.loadProperty("catalogue.view.classification.tree").trim());
	}

	public void verifySearchPanelFields(String partNum, String description, String supplierName) {
		String[] searchFields = { partNum, description, supplierName };
		List<WebElement> searchFieldList = findElements(ProcurementTab.css_SearchPanelFields);

		int i = 0;
		for (WebElement e : searchFieldList) {
			log.error("XPATH : " + e.getText() + "  " + "TEXT : " + searchFields[i]);
			Assert.assertEquals(e.getText(), searchFields[i]);
			i++;
		}
	}

	public void clickOnViewDetails() throws InterruptedException {
		sendKeys(ProcurementTab.txt_SearchCatalogueDescription, catalogueItemsXlsFileList.get(0));
		sendKeys(ProcurementTab.txt_SearchCatalogueDescription, Keys.ENTER);

		clickElementAndWait(ProcurementTab.btn_FirstProductViewDetailsButtton);
	}

	public void verifyProductDetailsPopup(String popup) {
		Assert.assertTrue(isDisplayed(ProcurementTab.pop_ProductDetails));
		Assert.assertTrue(getElementText(GlobalPageElements.lbl_PopUpHeader1).contains(popup));
	}

	public void verifyProductDetails() {
		getProductDetailsIntoExcelSheet();
		for (int i = 1; i <= 3; i++) {
			productDetailsWebFileList.add(getElementText(By.xpath(".//div[@id='featuresSectionDetails']//tr[" + i + "]/td[2]")));
		}
		log.info("productDetailsWebFileList :" + productDetailsWebFileList);

		Assert.assertEquals(productDetailsXlsFileList, productDetailsWebFileList);
	}

	public void getProductsNameIntoExcelSheet() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		try {
			FileInputStream file = new FileInputStream(new File(nodeIP + ResourceHandler.loadProperty("catalogue.view.publish.file.remote")));

			workbook = new HSSFWorkbook(file);
			sheet = workbook.getSheetAt(1);

			if (sheet != null) {
				for (int i = 2; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
					cell = row.getCell(2);
					catalogueItemsXlsFileList.add(cell.toString().trim());
				}
				log.info("catalogueItemsXlsFileList :" + catalogueItemsXlsFileList);
			}
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getProductDetailsIntoExcelSheet() {
		try {
			FileInputStream file = new FileInputStream(new File(nodeIP + ResourceHandler.loadProperty("catalogue.view.publish.file.remote")));

			workbook = new HSSFWorkbook(file);
			sheet = workbook.getSheetAt(1);

			if (sheet != null) {
				for (int j = 0; j <= 2; j++) {
					for (int i = 2; i <= j + 3;) {
						row = sheet.getRow(i);
						cell = row.getCell(j);
						productDetailsXlsFileList.add(cell.toString().trim());
						break;
					}
				}
				log.info("productDetailsXlsFileList :" + productDetailsXlsFileList);
			}
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
