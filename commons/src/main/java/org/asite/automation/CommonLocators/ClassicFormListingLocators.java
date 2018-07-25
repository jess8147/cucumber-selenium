package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicFormListingLocators {

	public static class FormListingPage {
		public static By txt_FormListingFormTitle = By
				.cssSelector("div[id='wrapper'] div[id='basicSearch'] form[name='frmBasicSearch'] tr[id='trBasicSearchFields'] input[name='txtFormTitle']");
		public static By btn_FormListingSearch = By
				.cssSelector("div[id='wrapper'] div[id='basicSearch'] form[name='frmBasicSearch'] tr[id='trBasicSearchFields'] input[id='basicSearchBtn']");
		public static By css_ProjectFormListingCount = By
				.cssSelector("div[id='wrapper'] div[id='main'] div[id='searchResult'] td[class='userSearchListing'] table[id='frmList'] tr[class*='trbg']");
		public static By lbl_ProjectFormListingFirstFormTitle = By
				.cssSelector("div[id='wrapper'] div[id='main'] div[id='searchResult'] td[class='userSearchListing'] table[id='frmList'] tr[class='trbg1'] td:nth-child(3)");
		public static By lnk_ProjectFormListingFirstFormID = By
				.cssSelector("div[id='wrapper'] div[id='main'] div[id='searchResult'] td[class='userSearchListing'] table[id='frmList'] tr[class='trbg1'] td:nth-child(2) a");
		public static By ele_ProjectFormDetailsAttachedDocList = By
				.cssSelector("div[id='messageDetails'] form[name='frmChangeStatus'] div[id='extdocList']");
		public static By css_ProjectFormDetailsAssociatedFormTitleList = By
				.cssSelector("div[id='messageDetails'] form[name='frmChangeStatus'] div[id='formsList'] table[id='formSearchResultTable'] tr[class*=trbg] td:nth-child(3)");

		/* Create-Form */
		public static By img_ProjectFormCreateForm = By
				.cssSelector("div[id='wrapper'] div[id='main'] div[id='searchResult'] table[id='createFormId'] a[id='createFormIcon']");
		public static By txt_CreateFormSubject = By
				.cssSelector("form[name='myform'] div[id='xdoc_view'] textArea[id*='RFI_Subject']");
		public static By txt_CreateFormDescription = By
				.cssSelector("form[name='myform'] div[id='xdoc_view'] textArea[id*='RFI_Description']");
		public static By lnk_CreateFormAddDocFolder = By
				.cssSelector("form[name='myform'] tr[id='assocDocsTD'] a[title='Add Docs from Folders']");
		public static By lnk_CrateFormAddDocFolderFromBasket = By
				.cssSelector("form[name='myform'] tr[id='assocDocsTD'] a[title='Add Docs from Basket']");
		public static By lnk_CreateFormDistribution = By
				.cssSelector("form[name='myform'] a[title='Add further recipients']");
		public static By lnk_CreateFormAddForms = By
				.cssSelector("form[name='myform'] a[title='Add Forms']");
		public static By lbl_CreateFormDistributionFirstRecipient = By
				.cssSelector("form[name='myform'] table[id='distTable'] tr[class='trbg1']:nth-child(2) td:nth-child(3)");
		public static By img_CreateFormAttachExternalDoc = By
				.cssSelector("form[name='myform'] td[class='print'] img[title='Attach External Documents']");
		public static By lnk_CreateFormAttachedExternalDoc = By
				.cssSelector("form[name='myform'] td[id='tdExtDocsList'] a[title='Attach External Documents']");
		public static By txt_CreateFormBasketListingDocRefInput = By
				.cssSelector("div[id='docSearchPanel'] div[id='basicPanel'] input[id='docref']");
		public static By btn_CreateFormAssociateDocComplete = By
				.cssSelector("div[id='docSearchResult'] form[name='bottomsearch']+table input[id='assocDocsComplete']");
		public static By btn_CreateFormCreate = By
				.cssSelector("form[name='myform'] table[id='moveContentTable'] input[id='btnSend']");
		public static By img_CreateFormAuditHistory = By
				.xpath(".//table[@id='iconSet' and @style='display: block;']//img[@title='Audit Trail']");
		public static By lbl_CreateFormAuditHistoryRecipientAction = By
				.xpath(".//table[@id='fomDistTbl']//tr[@class='trbg1']//td[7]");
		public static By lbl_CreateFormAuditHistoryActionDueDate = By
				.xpath(".//table[@id='fomDistTbl']//tr[@class='trbg1']//td[8]//span[text()]");

		/* Create Basic Form */
		public static By txt_CreateFormPNFCTitle = By
				.cssSelector("form[name='myform'] table tbody input[id='messageName']");
		public static By txt_CreateFormPNFCDescription = By
				.cssSelector("form[name='myform'] table tbody textarea[id='messageText']");
		public static By txt_CreateFormPNFCUSerRef = By
				.cssSelector("form[name='myform'] table tbody input[id='formUserRef']");
		public static By txt_CreateFormPNFCHyperLink = By
				.cssSelector("form[name='myform'] table tbody input[id='formHyperlink']");
		public static By lnk_CreateFormPNFCRespondDatePickerImg = By
				.cssSelector("form[name='myform'] table tbody a[id='anchor1'] img");
		public static By img_CreateFormPNFCRespondDatePickerCurrentDate = By
				.cssSelector("form[name='myform'] table[class='cpBorder'] td[class='cpCurrentDate'] a");

		/* Direct Link Form */
		public static By lbl_ViewFormFormTitleLabel = By
				.xpath(".//div[@id='pageWrapper']//table[@align='left']//tr[2]//td[text()][1]");
		public static By lnk_ViewFormAuditTrailLink = By
				.xpath(".//table[@id='iconSet']//img[@title='Audit Trail']");
		public static By ele_ViewFormAuditTrailFormActionStatus = By
				.xpath(".//table[@id='fomDistTbl']//tr[contains(@class,'trbg')]//td[@class='bodylred'][text()]");
		public static By lnk_ViewFormAuditTrailBackToFormLink = By
				.xpath(".//form[@name='frmAction']//img[contains(@src,'i_back.gif')]");
		public static By lbl_ViewFormActionTimeLabel = By
				.xpath(".//table[@class='trtableheading']//table[@bordercolor='#FFFFFF']//tr[2]//td[@class='body1red'][text()]");
		public static By ele_ViewFormUnauthorisedAccessPage = By
				.xpath(".//table//p[@class='subtitle'][text()]");

		/* Global Federated Search */
		public static By lnk_FormListingAdvancedSearchLink = By
				.cssSelector("div[id='basicSearch'] form a img:nth-child(2)");
		public static By txt_FormListingFormContentSearchInput = By
				.xpath(".//div[@id='advanceSearch'][@style='display: block;']//input[@name='txtFormContent']");
		public static By btn_FormListingSearchButton = By
				.xpath(".//div[@style='display: block;']//input[@value='Search'][@type='button']");
		public static By img_FormListingDeactivateFormsImage = By
				.xpath(".//a[@id='deactivateFormIcon']/img[@title='Deactivate Forms']");
		public static By btn_FormListingDeactivateFormsButton = By
				.xpath(".//input[@id='deactFormBtn'][@value='Deactivate']");
	}

}
