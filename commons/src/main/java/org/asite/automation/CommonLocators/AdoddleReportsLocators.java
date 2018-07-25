package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleReportsLocators {

	public static class ReportsTab {
		/* Create-Report */
		public static By	txt_ReportNameInput										= By.xpath(".//input[contains(@id,'ReportName')]");
		public static By	css_ExpressReportFieldList								= By.xpath(".//div[@class='wrTabInnerContent' and not(contains(@style,'display: none'))]//div[contains(@id,'DataFieldsCtrl')]//span");

		/* Create-Report */
		public static By	frm_WebReportIframe										= By.cssSelector("iframe[id='webreportFrame']");
		public static By	img_CreateReportButton									= By.cssSelector("button[id='WebReportsCtrl_MainSplitter_NewReportBtn']");
		public static By	ele_ReportTypeOptionMenu								= By.xpath(".//div[@id='wrNewReportMenu' and not(contains(@style,'display: none'))]");
		public static By	css_ArrowExpandList										= By.xpath(".//div[contains(@id,'ReportNameCtrl_ReportsCtrl_ReportsTree_TreeContent')]//div[@class='wrTrNd wrTrFolder']//img[contains(@src,'ArrowExpand.svg')]");
		public static By	ele_ReportFolderSelection								= By.xpath(".//div[contains(@id,'ReportNameCtrl_ReportsCtrl_ReportsTree_TreeContent')]//div[@class='wrTrLv' and not(contains(@style,'display: none'))]//span[text()='My Reports']");
		public static By	txt_SearchCategoriesInput								= By.xpath(".//input[contains(@id,'SearchBox') and @placeholder='Search...']");
		public static By	ele_ExpressReportLayoutSubField							= By.xpath(".//div[contains(@id,'ReportLayoutSplitter')]//span[text()='Comment Code']");
		public static By	ele_StandardReportLayoutField							= By.xpath(".//div[contains(@id,'ReportLayoutSplitter')]//span[text()='File Name']");
		public static By	btn_ExecuteReportMenu									= By.xpath(".//div[contains(@id,'DesignToolbarContainer')]//button[contains(@id,'_ExportSelectCtrl_ExportBtn')]");
		public static By	img_ExecuteReportMenuGaugeWizardButton					= By.xpath(".//div[contains(@id,'DesignToolbarContainer')]//img[contains(@src,'Gauge.svg')]");
		public static By	img_ExecuteExcelReportButton							= By.xpath(".//div[contains(@id,'ExportTypeMenu_')][not(contains(@id,'Home'))]//div[contains(@class,'wrPopupMenuItemSlim')]//img[contains(@src,'ExecuteExcel')]");
		public static By	pop_ReportsTabProcessDialog								= By.xpath(".//div[@id='dialogMasterContainer_0']");
		public static By	txt_SearchReportInput									= By.xpath(".//input[contains(@id,'ReportsCtrl_ReportsTree_SearchBox') and @placeholder='Search report names...']");
		public static By	ele_SelectedReport										= By.xpath(".//*[@id='WebReportsCtrl_MainSplitter_ReportsCtrl_ReportsTree_TreeContent']//div[@class='wrTrNd wrTrLf' and not(contains(@style,'none'))]//span");
		public static By	sel_ReportsTabDelete									= By.xpath(".//*[@id='ReportTreeReportMenu']//div[@class='wrPopupMenuItem wrPopupMenuItemContext' and @data-key='delete']");
		public static By	btn_ReportsTabProcessDialogOkButton						= By.xpath(".//div[@id='OkBtn']//span[text()='OK']");
		/*
		public static By	ele_CategoriesField										= By.xpath(".//div[contains(@id,'ReportCategoriesSplitter') and @class='wrTreeContent']//div[not(contains(@style,'display: none'))]");
		public static By	img_ProcessDialog										= By.xpath(".//*[@id='MainReportsContainer']/img");
		*/

		/* Schedule Report */
		public static By	btn_EditReportSaveButton								= By.xpath(".//button[@id='saveCriteria' and contains(text(),'Save')]");
		public static By	btn_EditReportSaveAndCloseButton						= By.xpath(".//button[@id='saveAndCloseCriteria' and contains(text(),'Save & Close')]");
		public static By	lnk_EditAndScheduleReports								= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-legacyReport'] i");
		public static By	lbl_EditAndSchedulePage									= By.xpath(".//div[@id='legacyReportContainer']//h2[text()]");
		public static By	txt_SearchLegacyReportInput								= By.xpath(".//input[@id='legacyReport_fd_containText']");
		public static By	lnk_FirstLegacyReport									= By.xpath(".//div[@index='0']//a[text()]");
		public static By	lnk_FirstLegacyEditReport								= By.xpath(".//div[@index='0']//div[contains(@class,'EditImageName')]//a");
		public static By	lnk_FirstLegacyEditReportDisabled						= By.cssSelector("div[index='0'] div[class*='EditImageName'] a[class*='btn-disabled']");
		public static By	btn_EditAndScheduleReportPreviewButton					= By.xpath(".//button[@id='showReport'][contains(text(),'Preview')]");
		public static By	btn_PreviewToReturnPreviousScreenButton					= By.xpath(".//div[contains(@style,'display: block')]//input[@id='previewAgain']");
		public static By	btn_PopEditAndScheduleReportDownloadButton				= By.xpath(".//div[@id='generateLegacyReportModal'][contains(@style,'display: block')]//button[@id='downloadLegacyReport']");
		public static By	sel_PopEditAndScheduleReportSelectProjectRadioButton	= By.xpath(".//table[@id='tblReportCriteria']//label[contains(text(),'Selected Project')]//input[@type='radio']");
		public static By	drp_PopEditAndScheduleReportCriteriaDropdown			= By.xpath(".//table[@id='tblReportCriteria']//table//tr[@class='criteriaDropdown' and not(contains(@style,'display: none'))]//div[@class='fs-label']");
		public static By	ele_PopEditAndScheduleReportCriteriaListFrame			= By.cssSelector("div[class='fs-dropdown']");
		public static By	css_PopEditAndScheduleReportCriteriaDropdownList		= By.xpath(".//table[@id='tblReportCriteria']//div[@class='fs-options']//label[text()]");
		public static By	drp_DeliveryScheduleDropdown							= By.xpath(".//select[@id='delivery_schedule_status']");
		/*
		public static By	btn_EditReportCancelButton								= By.xpath(".//button[@id='cancel' and contains(text(),'Cancel')]");
		public static By	lnk_LegacyReports										= By.xpath(".//a[not(contains(@class,'disabled')) and text()='Legacy Reports']");
		*/

		/* Shared Report ACL Check */
		public static By	frm_ReportACLIframe										= By.cssSelector("Iframe[id='ifrmACL']");
		public static By	lbl_EditReportAccessUserGroups							= By.cssSelector("table[id='editReportAccess'] td[name*='User Groups'] strong");
		public static By	btn_EditReportAccessScheduleReportAddSelected			= By.cssSelector("table[id='editReportAccess'] form[id='aclMatrix'] :nth-child(4) input[value*='Add Selected']");

	}
}
