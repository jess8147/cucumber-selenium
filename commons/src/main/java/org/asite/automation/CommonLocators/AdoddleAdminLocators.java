package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleAdminLocators {
	
	public static class ManageNotices {
		
		public static By	img_ManageNoticeThumb						= By.cssSelector("div[id='adminOptionListDiv'] div[title='Manage Notice'] img[class='adminTabThumbImg']");
		public static By	btn_NoticeListingCreateNewNotice			= By.cssSelector("div[id='noticeLayout'] div[id='noticeTemplateDetailDiv'] button[id='createNewNotice']");
		public static By	txt_PopAddNoticeTitle						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] input[id='noticeTitle']");
		public static By	txt_PopAddNoticeDesc						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] div[class*='nicEdit-main']");
		public static By	rad_PopAddNoticePriorityHigh				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] input[id='highPriority']");
		public static By	rad_PopAddNoticePriorityLow					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] input[id='lowPriority']");
		public static By	txt_PopAddNoticeStartDate					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] input[id='startDate']");
		public static By	txt_PopAddNoticeEndDate						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] input[id='endDate']");
		public static By	drp_PopAddNoticeEndTime						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] select[id='select_endtime']");
		public static By	txt_PopAddNoticeApplyTo						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] input[class='select2-input']");
		public static By	lbl_PopAddNoticeApplyToSearchFirstResult	= By.cssSelector("div[id='select2-drop'] ul[class='select2-results'] li[class*='select2-highlighted'] span");
		public static By	btn_PopAddNoticeSave						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] button[id='managenoticeSaveBtn']");
		public static By	lbl_NoticeListingFirstNoticeTitle			= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-title-fixed-width'] div");
		public static By	lbl_NoticeListingFirstNoticePriority		= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-fieldPriority-fixed-width'] div");
		public static By	lbl_NoticeListingFirstNoticeStartDate		= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-formattedStartDateTime-fixed-width'] div");
		public static By	lbl_NoticeListingFirstNoticeEndDate			= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-formattedEndDateTime-fixed-width'] div");
		public static By	lbl_NoticeListingFirstNoticeCreatedOn		= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-publishDate-fixed-width'] div");
		public static By	lbl_NoticeListingFirstNoticeStatus			= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-fieldStatus-fixed-width'] div");
		public static By	lbl_NoticeListingFirstNoticeScheduleStatus	= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-fieldPublishStatus-fixed-width'] div");
		public static By	ele_NoticeListingCreatedOnDescIcon			= By.cssSelector("div[id='listing'] div[id='adTableHead'] div[class*='col-publishDate-fixed-width'] span[alt*='Sorted descending'] div[class*='ui-icon-carat']");
		public static By	ele_NoticeListingCreatedOnAscIcon			= By.cssSelector("div[id='listing'] div[id='adTableHead'] div[class*='col-publishDate-fixed-width'] span[alt*='Sorted ascending'] div[class*='ui-icon-carat']");
		public static By	lbl_NoticeInfoNoticeTitle					= By.cssSelector("div[id='noticeInfo'][style*='display: block'] span[id='noticeText']");
		public static By	pop_NoticeInfopopup							= By.cssSelector("div[id='noticeInfo'][style*='display: block']");
		public static By	img_NoticeInfoNoticePriority				= By.cssSelector("div[id='noticeInfo'][style*='display: block'] span[class*='priorityImage'] img");
		public static By	lbl_NoticeInfoNoticeApplyTo					= By.cssSelector("div[id='noticeInfo'][style*='display: block'] span[id='noticeApplyTo']");
		public static By	btn_NoticeInfoClose							= By.cssSelector("div[id='noticeInfo'] button[class*='close']");
		public static By	pop_NoticeReadMorePopup						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='notificationModal']");
		public static By	img_PopupNoticeReadMorePriority				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='notificationModal'] div[class='notificationBodyImage'] img");
		public static By	lbl_PopupNoticeReadMoreCreatorInfo			= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='notificationModal'] div[class='creatorPart'] b");
		public static By	lbl_PopupNoticeReadMoreDescription			= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='notificationModal'] div[class='descriptionPart'] p");
		public static By	css_PopupNoticeReadMoreProjectList			= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='notificationModal'] div[class='projectList'] span");
		public static By	img_HeaderNotificationImage					= By.cssSelector("div[class='header-right-side'] div[id='notification'] a[class='dropdown-toggle'] img");
		public static By	drp_HeaderNotificationMenu					= By.cssSelector("div[class='header-right-side'] div[id='notification'] ul[class*='dropdown-menu']");
		public static By	css_NoticeListingNoticesTitles				= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows'] div[class*='col-title-fixed-width'] div");
		public static By	lbl_NoticeListingHeaderText					= By.cssSelector("div[id='noticeLayout'] h2[id='adminTabHeadeTxt']");
		public static By	css_NoticeListingNoticesStatuses			= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows'] div[class*='col-fieldStatus-fixed-width'] div");
		public static By	css_NoticeListingNoticesActivateButtons		= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows'] div[class*='col-activeDeactiveNoticeImage-fixed-width'] div a");
		public static By	css_NoticeListingNoticesSchedStatuses		= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows'] div[class*='col-fieldPublishStatus-fixed-width'] div");
		public static By	css_NoticeListingNoticesEditButtons			= By.cssSelector("div[id='noticeListingblocks'] div[id='adTableBody'] div[class*='rows'] div[class*='col-editNoticeImage-fixed-width'] div a");
		public static By	drp_PopAddNoticeStartTime					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='setupNoticeLayout'] form[id='manageNoticeForm'] select[id='select_starttime']");
		public static By	lnk_NoticeInfoReadMore						= By.cssSelector("div[id='noticeInfo'] div[id='readMore']");

	}
	
	public static class AdminTab {
		
		/* Auto Fetch Attributes */
		public static By	img_AutoFetchAttributesBox								= By.cssSelector("div[id='adminOptionListTemplete'] div[id='adminOptionListDiv'] img[src*='autofetchattributes']");
		public static By	img_ManageUsersBox										= By.cssSelector("div[id='adminOptionListTemplete'] div[id='adminOptionListDiv'] img[src*='manageusers']");
		public static By	img_DesignLayoutBox										= By.cssSelector("div[id='adminOptionListTemplete'] div[id='adminOptionListDiv'] img[src*='designlayout']");
		
		public static By	lbl_AutoFetchAttributePageHeader						= By.cssSelector("div[id='autoFetchAttributeDetailTemplete'] h2[id='adminTabHeadeTxt']");
		public static By	lbl_DesignLayoutPageHeader								= By.cssSelector("div[id='pagelayoutOrgList'] h2[id='adminTabHeadeTxt']");
		public static By	lbl_AutoFetchAttributeRulesFirstTitle					= By.cssSelector("div[id='autoFetchAttributeDetailDiv'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-fetchAttributeSetName-fixed-width']");
		public static By	txt_PopEditAutoFetchRuleTitleInput						= By.cssSelector("div[id='modalCreateOrEditAttrRule'] form[id='adminAttrRuleForm'] input[id='adminRuleTitleInput']");
		public static By	btn_PopEditAutoFetchRuleSave							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modalCreateOrEditAttrRule'] button[id='adminAttrRulesSaveBtn']");
		public static By	css_PopEditAutoFetchRuleSelectedAttributes				= By.cssSelector("form[id='adminAttrRuleForm'] div[id='selectedFetchAttContent'] ul[id='draggablePanelList'] div[class='admin-selectAttr-name']");
		/*
		public static By	img_AutoFetchAttributeRulesFirstEditIcon				= By.cssSelector("div[id='autoFetchAttributeDetailDiv'] div[id='adTableBody'] div[class*='col-editOptionImage-fixed-width'] img[title='Click here to Edit Rule']");
		public static By	chk_PopEditAutoFetchRuleSelectedFolderCheckbox			= By.cssSelector("div[id='adminTargetFolderDiv'] div[id='fetchAttrRuleTreeListing'] div[class='tree-children'] div[class='tree-row selected'] input[type='checkbox']");
		*/

		/* Manage Notices */
		/*public static By	btn_NoticeInfoClose										= By.cssSelector("div[id='noticeInfo'][style*='display: block'] button[class*='close']");*/
		
		/* Manage Users */
		
		public static By	txt_PopCreateUserEmailAddress							= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='inputEmail']");
		public static By	drp_PopCreateUserOrganization							= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] select[id='selectOrgName']");
		public static By	drp_PopCreateUserPrefix									= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] select[id='selectPrefix']");
		public static By	txt_PopCreateUserFirstName								= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='inputFName']");
		public static By	txt_PopCreateUserMiddleName								= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='inputMName']");
		public static By	txt_PopCreateUserLastName								= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='inputLName']");
		public static By	txt_PopCreateUserJobTitle								= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='inputJobTitle']");
		public static By	drp_PopCreateUserLanguage								= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] select[id='selectLanguage']");
		public static By	drp_PopCreateUserStatus									= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] select[name='is_active_user']");
		public static By	drp_PopCreateUserView									= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] select[id='selectView']");
		public static By	drp_PopCreateUserTimeZoneDropdown						= By.xpath(".//div[contains(@style,'display: block')]//select[@id='select_timeZone']");
		public static By	txt_PopCreateUserSubscriptionEnd						= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='dtSubEnd']");
		public static By	chk_PopCreateUserSubscriptionUnlimited					= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='chkSubNeverExpire']");
		public static By	drp_PopCreateUserSubsriptionPlan						= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] select[id='selectSubPlan']");
		public static By	txt_PopCreateUserBillToOrg								= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] div[id='s2id_inputBillToOrg'] input");
		public static By	txt_PopCreateUserAsiteContact							= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='inputAsiteContractNo']");
		public static By	btn_PopCreateUserSaveButton								= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] button[id='btnSaveUser']");
		public static By	btn_PopCreateUserUpdateButton							= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] button[id='btnUpdateUser']");
		public static By	txt_ManageUsersUserSearchInput							= By.cssSelector("div[id='manageUserLayout'] div[id='manageUserDetailDiv'] input[id='manageUser_fd_containText']");
		public static By	lbl_ManageUsersListingFirstEmail						= By.cssSelector("div[id='manageUserListing'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-userEmail-fixed-width'] div");
		public static By	img_ManageUsersListingFirstUserEdit						= By.cssSelector("div[id='manageUserListing'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-editUserSubscriptionImage-fixed-width'] a");
		public static By	css_ManageUsersListingUserEmails						= By.cssSelector("div[class*='col-userEmail-fixed-width'] div");
		public static By	css_ManageUsersListingUserStatus						= By.cssSelector("div[class*='col-isActiveUser-fixed-width'] div[title]");
		public static By	css_ManageUsersListingUserRows							= By.cssSelector("div[id='adTableBody'] div[class*='rows']");
		public static By	btn_ManageUsersAddNewUser								= By.cssSelector("div[id='manageUserLayout'] div[id='manageUserDetailDiv'] button[id='manageUserCreate']");
		/*public static By	txt_PopCreateUserSubscriptionStart						= By.cssSelector("div[class*='modal-scrollable'][style*='z-index'] div[id='modalManageUsers'] form[id='manageUsersForm'] input[id='muserfromDate']");*/


		/* Design Layout */
		public static By	rad_PopEditLayoutEditableYes							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='isEditable'] input[id='editableYes']");
		public static By	btn_PopEditLayoutSave									= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] button[id='saveCreatOrg']");
		public static By	rad_PopEditLayoutEditableNo								= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='isEditable'] input[id='editableNo']");
		public static By	btn_PopEditLayoutApplyTo								= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='applyTo'] button");
		public static By	chk_PopEditLayoutApplyToOrgUsers						= By.cssSelector("div[class='check-list-select'] label[title='Organisation Users'] input[type='checkbox']");
		public static By	chk_PopEditLayoutApplyToBillOrgUsers					= By.cssSelector("div[class='check-list-select'] label[title='Bill To Org Users'] input[type='checkbox']");
		public static By	lbl_PopEditLayoutActiveHeader							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='accordion'] h3[class*='ui-accordion-header-active']");
		public static By	ele_PopEditLayoutFilesExpandArrow						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='accordion'] h3[id='accSection_1'] span[class*='accordion-rightarrow']");
		public static By	ele_PopEditLayoutDiscussionsExpandArrow					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='accordion'] h3[id='accSection_2'] span[class*='accordion-rightarrow']");
		public static By	ele_PopEditLayoutModelsExpandArrow						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='accordion'] h3[id='accSection_3'] span[class*='accordion-rightarrow']");
		public static By	ele_PopEditLayoutProcureMentExpandArrow					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] div[id='accordion'] h3[id='accSection_4'] span[class*='accordion-rightarrow']");
		public static By	css_PopEditLayoutProjectsSelectedFields					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-0'] select[id='customColDropdownselected_0'] option[value]");
		public static By	css_PopEditLayoutProjectsDefaultFilters					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-0'] div[class*='default-filter-fields'] div[class='layoutDisplayFilter']");
		public static By	rad_PopEditLayoutProjectsViewSettingsTabular			= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-0'] div[class*='view-settings'] input[value='listing']");
		public static By	rad_PopEditLayoutProjectsViewSettingsTile				= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-0'] div[class*='view-settings'] input[value='thumb']");
		public static By	drp_PopEditLayoutProjectsViewSettingsRecPerPage			= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-0'] div[class*='view-settings'] select[id='recPerPage_0']");
		public static By	drp_PopEditLayoutProjectsViewSettingsDefaultSorting		= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-0'] div[class*='view-settings'] select[id='defaultSorting_0']");
		public static By	css_PopEditLayoutFilesSelectedFields					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-1'] select[id='customColDropdownselected_1'] option[value]");
		public static By	css_PopEditLayoutFilesDefaultFilters					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-1'] div[class*='default-filter-fields'] div[class='layoutDisplayFilter']");
		public static By	drp_PopEditLayoutFilesViewSettingsRecPerPage			= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-1'] div[class*='view-settings'] select[id='recPerPage_1']");
		public static By	drp_PopEditLayoutFilesViewSettingsDefaultSorting		= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-1'] div[class*='view-settings'] select[id='defaultSorting_1']");
		public static By	css_PopEditLayoutDiscussionsSelectedFields				= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-2'] select[id='customColDropdownselected_2'] option[value] ");
		public static By	css_PopEditLayoutDiscussionsDefaultFilters				= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-2'] div[class*='default-filter-fields'] div[class='layoutDisplayFilter']");
		public static By	drp_PopEditLayoutDiscussionsViewSettingsRecPerPage		= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-2'] div[class*='view-settings'] select[id='recPerPage_2']");
		public static By	css_PopEditLayoutModelsSelectedFields					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-3'] select[id='customColDropdownselected_3'] option[value]");
		public static By	css_PopEditLayoutModelsDefaultFilters					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-3'] div[class*='default-filter-fields'] div[class='layoutDisplayFilter']");
		public static By	rad_PopEditLayoutModelsViewSettingsTabular				= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-3'] div[class*='view-settings'] input[value='listing']");
		public static By	rad_PopEditLayoutModelsViewSettingsTile					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-3'] div[class*='view-settings'] input[value='thumb']");
		public static By	drp_PopEditLayoutModelsViewSettingsRecPerPage			= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-3'] div[class*='view-settings'] select[id='recPerPage_3']");
		public static By	drp_PopEditLayoutModelsViewSettingsDefaultSorting		= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-3'] div[class*='view-settings'] select[id='defaultSorting_3']");
		public static By	css_PopEditLayoutProcurementSelectedFields				= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-4'] select[id='customColDropdownselected_3'] option[value]");
		public static By	css_PopEditLayoutProcurementDefaultFilters				= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-4'] div[class*='default-filter-fields'] div[class='layoutDisplayFilter']");
		public static By	drp_PopEditLayoutProcurementViewSettingsRecPerPage		= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-4'] div[class*='view-settings'] select[id='recPerPage_4']");
		public static By	drp_PopEditLayoutProcurementViewSettingsDefaultSorting	= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-4'] div[class*='view-settings'] select[id='defaultSorting_4']");
		public static By	css_ProjectListingColumnHeaders							= By.cssSelector("div[id='project-blocks'] div[id='listing'] div[id='adTableHead'] div[id*='columnId']");
		public static By	css_GlobalListingDefaultFiltersText						= By.cssSelector("div[id='filterCells'] div[id*='moreFilterCell']");
		public static By	lnk_ProjectListingFirstProjectThumbView					= By.cssSelector("div[id='project-blocks'] div[id='projectTabThumbListCont'] div[class='boxgrid']:nth-child(1) div[class='box-projectname']");
		public static By	css_DocumentListingColumnHeaders						= By.cssSelector("div[id='fileContentContainer'] div[id='listing'] div[id='adTableHead'] div[id*='columnId']");
		public static By	css_DiscussionsListingColumnHeaders						= By.cssSelector("div[id='discussionsContentContainer']  div[id='listing'] div[id='adTableHead'] div[id*='columnId']");
		public static By	css_ModelsListingColumnHeaders							= By.cssSelector("div[id='modelContentContainer'] div[id='listing'] div[id='adTableHead'] div[id*='columnId']");
		public static By	drp_GlobalListingShowRecords							= By.cssSelector("div[class*='listing-setting open'] div[class='manage-toolbar-header dropdown-menu'] select[id='optshow']");
		public static By	drp_GlobalListingCustomize								= By.cssSelector("div[class*='listing-setting open'] div[class='btn-group'] a span");
		public static By	lnk_DesignLayoutListingPaginationNext					= By.cssSelector("div[id='orgLayoutPaging'] li[title='Next page'] a");
		public static By	img_PopCreateUserSubscriptionStartDatePicker			= By.cssSelector("form[id='manageUsersForm'] input[id='muserfromDate']+img[class*='ui-datepicker-trigger']");
		public static By	img_PopCreateUserSubscriptionEndDatePicker				= By.cssSelector("form[id='manageUsersForm'] input[id='dtSubEnd']+img[class*='ui-datepicker-trigger']");
		/*
		public static By	rad_PopEditLayoutProcurementViewSettingsTabular			= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-4'] div[class*='view-settings'] input[value='listing']");
		public static By	rad_PopEditLayoutProcurementViewSettingsTile			= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-4'] div[class*='view-settings'] input[value='thumb']");
		public static By	rad_PopEditLayoutFilesViewSettingsTabular				= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-1'] div[class*='view-settings'] input[value='listing']");
		public static By	rad_PopEditLayoutFilesViewSettingsTile					= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-1'] div[class*='view-settings'] input[value='thumb']");
		public static By	drp_PopEditLayoutDiscussionsViewSettingsDefaultSorting	= By.cssSelector("div[id='modaladdNeditLayout'] div[id='ui-accordion-accordion-panel-2'] div[class*='view-settings'] select[id='defaultSorting_2']");
		public static By	css_DesignLayoutListingOrgList							= By.cssSelector("div[id='pagelayoutOrgList'] div[id='layoutOrgListDiv'] tbody tr td:nth-child(1)");
		public static By	btn_PopEditLayoutCancel									= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modaladdNeditLayout'] button[class='btn btn-inverse']");
		*/
	}
}
