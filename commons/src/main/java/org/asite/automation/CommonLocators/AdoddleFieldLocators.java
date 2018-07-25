package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleFieldLocators {
	
	public static class FieldTab {
		public static By	txt_SearchDefectInput						= By.cssSelector("div[id='docListingSection'] form[id='commu_pd_filerForm'] input[id='commu_fd_containText']");
		public static By	opt_ContextClickProjectAddSite				= By.cssSelector("ul[class*='context-menu-root'][style] li[class*='CMicon-add-location'] span");
		public static By	opt_ContextClickSiteAddLocation				= By.cssSelector("ul[class*='context-menu-root'][style] li[class*='CMicon-add-location'] span");
		public static By	opt_ContextClickSiteRemove					= By.cssSelector("ul[class*='context-menu-root'][style]  li[class*='CMicon-edit-site']+li[class*='CMicon-remove']");
		public static By	opt_ContextClickSiteRemoveSite				= By.cssSelector("ul[class*='context-menu-root'][style]  li[class*='CMicon-edit-site']+li[class*='CMicon-remove'] li[class*='CMicon-remove-location'] span");
		public static By	txt_PopAddSiteSiteName						= By.cssSelector("div[class='modal-scrollable'][style] div[id='addFolderModalBody'] input[id='txtAddFolderName']");
		public static By	btn_PopAddSiteCreate						= By.cssSelector("div[class='modal-scrollable'][style] div[id='myModal-addsite'] button[id='addFolder']");
		public static By	btn_PopAddLocationCreate					= By.cssSelector("div[class='modal-scrollable'][style] div[id='myModal-addlocation'] button[id='addFolder']");
		public static By	txt_PopAddLocationLocationName				= By.cssSelector("div[class='modal-scrollable'][style] div[id='myModal-addlocation'] input[id='txtAddFolderName']");
		public static By	opt_ContextClickSiteAddDrawing				= By.cssSelector("ul[class*='context-menu-list'][style] li[class*='CMicon-add-drawing']");
		public static By	btn_PopUploadDrawingSelectFiles				= By.cssSelector("div[class='modal-scrollable'][style] form[id='data'] span[id='selectedFileSpan'] input[id='inptfile']");
		public static By	btn_PopUploadDrawingUpload					= By.cssSelector("div[class='modal-scrollable'][style] form[id='data'] button[id='btnUploadFiles']");
		public static By	btn_PopUploadDrawingSelectedFileSize		= By.cssSelector("div[class='modal-scrollable'][style] form[id='data'] tbody[id='tblbodySelectedFiles'] tr td:nth-child(3)");
		public static By	img_SiteListingPlanView						= By.cssSelector("div[id='docListingSection'] span[id='planView']");
		public static By	lnk_SiteListingListView						= By.cssSelector("div[id='docListingSection'] span[id='locationListView']");
		public static By	css_FieldListingDefectsCount				= By.cssSelector("div[id='docListingSection'] div[id='communicationContent'] div[class*='rows']");
		public static By	lnk_FieldListingDefectsFirstTitle			= By.cssSelector("div[id='docListingSection'] div[id='communicationContent'] div[class*='rows']:nth-child(1) div[class*='col-title-fixed-width'] a");
		public static By	lbl_FieldListingDefectsFirstAction			= By.cssSelector("div[id='docListingSection'] div[id='communicationContent'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName-actionTime-fixed-width']");
		public static By	lbl_FieldListingDefectsFirstStatus			= By.cssSelector("div[id='docListingSection'] div[class*='rows']:nth-child(1) div[class*='col-status-fixed-width']");
		public static By	css_FieldListingDefectsStatus				= By.cssSelector("div[id='docListingSection'] div[id='communicationContent'] div[class*='rows'] div[class*='col-status-fixed-width'] div");
		public static By	frm_PlanViewerIframe						= By.cssSelector("div[id='docListingSection'] div[id='planViewer'] iframe");
		public static By	ele_DrawingDocumentViewerCanvas				= By.cssSelector("div[id='DocumentViewer'] div[id='viewer'] div[class='pageContainer'] canvas[class='auxiliary'][style*='z-index']");
		public static By	img_PlanViewCreateDefectIcon				= By.cssSelector("div[id='control'] div[id='toolList'] span[title='Comment Note']");
		public static By	btn_PopRemoveSiteAlertOK					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='adoddleAlertUI'] button[id='okButton']");
		public static By	txt_CreateDefectFormTitle					= By.cssSelector("form[id='myform'] div[id='xdoc_view'] input[id*='ORI_FORMTITLE'][class='xdTextBox']");
		public static By	drp_CreateDefectFormType					= By.cssSelector("form[id='myform'] div[id='xdoc_view'] select[id*='DefectTyoe'][class*='xdBehavior_Select']");
		public static By	txt_CreateDefectFormDescription				= By.cssSelector("form[id='myform'] div[id='xdoc_view'] span[id*='Description'][class*='xdRichTextBox']");
		public static By	txt_CreateDefectExpectedDueDays				= By.cssSelector("div[id='xdoc_view'] table[class='xdLayout'] input[id*='ExpectedFinishDays']");
		public static By	drp_CreateDefectFormLocation				= By.cssSelector("form[id='myform'] div[id='xdoc_view'] select[id*='Location'][class*='xdComboBox']");
		public static By	drp_CreateDefectFormAssignTo				= By.cssSelector("form[id='myform'] div[id='xdoc_view'] select[id*='AssignedToUser'][class*='xdComboBox']");
		public static By	lnk_FieldListingDefectsFirstAction			= By.xpath(".//div[@index='0']//div[contains(@class,'actionName')]//a[text()]");
		public static By	lbl_DefectViewHeaderText					= By.cssSelector("div[id='viewFormContain'] h2[id='formName']");
		public static By	lbl_DefectViewStatusValue					= By.cssSelector("div[id='viewFormContain'] div[id='accordion'] div[id='xdoc_view'] span[id*='InfoJetExprBox'][xd\\:binding*='DS_ALL_FORMSTATUS']");
		public static By	drp_EditDefectFormStatus					= By.cssSelector("form[id='myform'] div[id='xdoc_view'] select[id*='DS_ALL_FORMSTATUS'][class*='xdBehavior_Select']");
		public static By	txt_EditDefectFormComment					= By.cssSelector("form[id='myform'] div[id='xdoc_view'] textarea[id*='my:Comments']");
		public static By	btn_BetaViewCreateEditDefectSaveButton		= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnSaveForm']");
		public static By	lbl_SelectedSiteOpenDefectCount				= By.cssSelector("div[class*='selected'][style*='padding-left: 30px;'] span[title*='Open']");
		public static By	lbl_SelectedSiteResolvedDefectCount			= By.cssSelector("div[class*='selected'][style*='padding-left: 30px;'] span[title*='Resolved']");
		public static By	lbl_SelectedSiteVerifiedDefectCount			= By.cssSelector("div[class*='selected'][style*='padding-left: 30px;'] span[title*='Verified']");
		public static By	lbl_SelectedLocOpenDefectCount				= By.cssSelector("div[class*='selected'][style*='padding-left: 50px;'] span[title*='Open']");
		public static By	lbl_SelectedLocResolvedDefectCount			= By.cssSelector("div[class*='selected'][style*='padding-left: 50px;'] span[title*='Resolved']");
		public static By	lbl_SelectedLocVerifiedDefectCount			= By.cssSelector("div[class*='selected'][style*='padding-left: 50px;'] span[title*='Verified']");
		public static By	btn_FieldListingStatusFilter				= By.cssSelector("div[id='docListingSection'] div[id='commuFormStatusBasicFilter'] button[filterkey='Status']");
		public static By	txt_FieldListingStatusFilterSearchInput		= By.cssSelector("div[class*='filter-layer-box'] div[class='check-list-select'] input[placeholder='Status']");
		public static By	chk_FieldListingStatusFilteredFirstRecord	= By.cssSelector("div[class='check-list-select'] ul[id*='ui-id'] li[class*='check-list-item']:nth-child(1) input[type='checkbox']");
		/*public static By	btn_SearchDefectButton						= By.cssSelector("div[id='docListingSection'] form[id='commu_pd_filerForm'] a[class*='filter_search_btn']");
		public static By	btn_PlanViewDrawingDownload					= By.cssSelector("div[id='control'] span[id='downloadButton']");
		public static By	btn_CreateDefectFormAttachment				= By.cssSelector("div[id='headerCreateFormDiv'] div[id='FormMoreOption'] button[class*='showAttachmentLink']");
		public static By	lbl_PopCreateDefectSelectedFileSize			= By.cssSelector("table[id='filesAttachmentListTable'] tbody[id='filesAttachmentListTbody'] tr td:nth-child(3)");
		public static By	btn_PopCreateDefectAttachmentSave			= By.cssSelector("div[id='myModal-Attachment'][style*='z-index'] form[id='data_CommentForm'] button[id='btnUploadFiles_CommentForm']");
		public static By	btn_DefectViewActionDropdownbutton			= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] div[id='accordion'] ul[class*='export-dropdown']:nth-child(1) button[id='header_export']");
		public static By	opt_DefectViewActionDropdownEditORI			= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] div[id='accordion'] ul[class*='export-dropdown']:nth-child(1) span[id='editORI']");
		public static By	btn_CreateEditDefectSaveButton				= By.cssSelector("div[id='formParentDiv'] div[id='btnsaveCancelForm'] button[id='btnSaveForm']");*/


	}
	
}
