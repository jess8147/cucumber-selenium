package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicAssociateLocators {

	public static class AssociateFormPage {
		public static By	txt_AssociateFormTitleInput			= By.cssSelector("div[id='wrapper'] div[id='basicSearch'] tr[id='trBasicSearchFields'] input[name='txtFormTitle']");
		public static By	btn_AssociateFormSearch				= By.cssSelector("div[id='wrapper'] div[id='basicSearch'] tr[id='trBasicSearchFields'] div[id='basicSearchBtnDiv'] input[id='basicSearchBtn']");
		public static By	chk_AssociateFormCheckAll			= By.cssSelector("div[id='wrapper'] table[id='frmList'] tr[class='trtableheading']:nth-child(1) input[name='sltAll']");
		public static By	chk_AssociateFormFirstForm			= By.cssSelector("div[id='wrapper'] table[id='frmList'] tr[class*='trbg']:nth-child(2) input[type='checkbox']");
		public static By	chk_AssociateFormSecondForm			= By.cssSelector("div[id='wrapper'] table[id='frmList'] tr[class*='trbg']:nth-child(3) input[type='checkbox']");
		public static By	lbl_AssociateFormFirstFormTitle		= By.cssSelector("div[id='wrapper'] table[id='frmList'] tr[class*='trbg']:nth-child(2) td:nth-child(3)");
		public static By	lbl_AssociateFormSecondFormTitle	= By.cssSelector("div[id='wrapper'] table[id='frmList'] tr[class*='trbg']:nth-child(3) td:nth-child(3)");
		public static By	btn_AssociateFormComplete			= By.cssSelector("div[id='wrapper'] div[id='searchResult'] table tbody tr:nth-child(3) input[value='Associate Forms Complete']");

	}

	public static class AssociateDocPage {
		public static By	frm_AssociateDocNotesFrame		= By.cssSelector("frameset[resize='yes'] frame[name='notes']");
		public static By	chk_AssociateDocCheckAll		= By.cssSelector("div[id='docSearchResult'] form[name='bottomsearch'] table[id='docListTable'] tr:nth-child(1) input[id='topCheck'][title='Select / Deselect All']");
		public static By	btn_AssociateDocComplete		= By.cssSelector("div[id='docSearchResult'] form[name='bottomsearch']+table input[id='assocDocsComplete']");
		public static By	css_AssociateDocDocumentList	= By.cssSelector("div[id='docSearchResult'] form[name='bottomsearch'] table[id='docListTable'] tr[class*='trbg']");

	}

	public static class DistributionPage {
		public static By	lbl_DocDistributionTitle						= By.cssSelector("div[id='docDivWrapper'] div[id='DocDistribute'] form[id='frmdocDist'] td[class='title']");
		public static By	sel_DocDistributionselectIndividuals			= By.cssSelector("div[id='docDivWrapper'] div[id='DocDistribute'] form[id='frmdocDist'] table[id='tblDistributionSelect'] select[id='selectUsers']");
		public static By	sel_DocDistributionselectCompany				= By.cssSelector("div[id='docDivWrapper'] div[id='DocDistribute'] form[id='frmdocDist'] table[id='tblDistributionSelect'] select[id='selectGrpComp']");
		public static By	btn_DocDistributionAddToDistList				= By.cssSelector("div[id='docDivWrapper'] div[id='DocDistribute'] form[id='frmdocDist'] table[id='tblDistributionSelect'] input[id='btnAddToList']");
		public static By	drp_DocDistributionAction						= By.cssSelector("form[id='frmdocDist'] div[id='dataTableDiv'] table[id='dataTable'] tr[class='trbg1'] select[id*='ActionID']");
		public static By	drp_DocDistributionActionHeader					= By.cssSelector("table[id='dataTable'] tr[class='trbg2'] select[id='selectAll']");
		public static By	drp_DocDistributionActionHeaderDownArrow		= By.cssSelector("div[id='dataTableDiv'] table[id='dataTable'] tr[class='trbg2'] select+img");
		public static By	txt_DocDistributionActionDate					= By.cssSelector("form[id='frmdocDist'] div[id='dataTableDiv'] table[id='dataTable'] tr[class='trbg1'] input[id*='DueDate']");
		public static By	drp_DocDistributionTo							= By.cssSelector("form[id='frmdocDist'] div[id='dataTableDiv'] table[id='dataTable'] tr[class='trbg1'] td:nth-child(3)");
		public static By	btn_DocDistributionDistribute					= By.cssSelector("form[id='frmdocDist'] div[id='dataTableDiv'] input[id='Distribute2']");
		public static By	drp_DocDistributionGroupType					= By.xpath(".//table[@id='tblDistributionSelect']//select[@name='selectGroupType']");
		public static By	sel_DocDistributionGroupCompaniesFirstOption	= By.xpath(".//select[@id='selectGrpComp']//option[1]");
		public static By	sel_DocDistributionUsersFirstOption				= By.xpath(".//select[@id='selectUsers']/option[1]");
		public static By	sel_DocDistributionGroupCompanies				= By.xpath(".//select[@id='selectGrpComp']");

	}

	public static class AttachDocPage {
		public static By	btn_AttachDocFirstBrowse	= By.cssSelector("form[name='uploaddoc'] table[id='fileTbl'] tr:nth-child(1) input[id*='upFile'][type='file']");
		public static By	btn_AttachDocSecondBrowse	= By.cssSelector("form[name='uploaddoc'] table[id='fileTbl'] tr:nth-child(2) input[id*='upFile'][type='file']");
		public static By	btn_AttachDocStartUpload	= By.cssSelector("div[id='upload_div'] input[id='upload'][value='Start Upload']");
		public static By	btn_AttachDocFirstChoose	= By.cssSelector("table[id='fileTbl'] tr:nth-child(1) input[id*='uploadFile'][type='file']");
		public static By	btn_AttachDocSecondChoose	= By.cssSelector("table[id='fileTbl'] tr:nth-child(2) input[id*='uploadFile'][type='file']");

	}

}
