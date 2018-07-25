package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleProjectsLocators {

	public static class ProjectsTab {

		public static By	lnk_PopEditAppSettingsDownloadFormZip					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='appBuilderOptions'] a[class='downLoadFormTemplate zip']");
		public static By	img_PopManageAppsModelFilteredAppEditImage				= By.cssSelector("div[id='manageAppsModal'] div[id='apps'] div[id='manageAppsMainDiv'] div[id='adTableBody'] div[class*='rows'][style*='display: block'] div[class*='col-editFormImage'] img");
		public static By	lnk_PopEditAppSettingsDownloadFormXSN					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='appBuilderOptions'] a[class='downLoadFormTemplate xsn']");
		public static By	lnk_AddProject											= By.xpath(".//a[starts-with(@id,'sidenav-')]//div[text()='Add Project']");
		public static By	css_ProjectRowsCount									= By.cssSelector("div[id='adTableBody'] div[class*='rows']");
		public static By	pop_ProjectsTabEditProject								= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='myModal-projectEdit']");
		public static By	drp_PopEditProjectStatus								= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] div[id='edit-projects'] select[id='editselectStatus']");
		public static By	lnk_ProjectsTabFirstProjectName							= By.cssSelector("div[id='project-blocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-projectName'] a");
		public static By	opt_ProjectContextClickEditProject						= By.cssSelector("li[class*='hover'] ul[class='context-menu-list'] li[class*='CMicon-edit-attributes'] span");
		public static By	chk_PopEditProjectFieldEnabled							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='edit-projects'] input[id='chkIsFieldEnabled']");
		public static By	btn_PopEditProjectSave									= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] div[class='modal-footer'] input[value='Save']");
		public static By	btn_PopEditProjectEdit									= By.cssSelector("div[id='myModal-projectEdit'][aria-hidden='false'] form[id='editProject'] input[id='projectEditSubmit'] ");
		public static By	lnk_PopManageRolesManageRoles							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] a[id='sidenav-manageRole-main']");
		public static By	lnk_PopManageRolesRolePrivileges						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] a[id='sidenav-role-privileges']");
		public static By	btn_PopManageRolesRolePrivilegesFilter					= By.cssSelector("div[id='projectmanageRolesModal'] div[id='rolePrivilegesMainDiv'] div[id='role-privilege-filter'] button[filterkey='manageRoles']");
		public static By	btn_PopManageRolesRolePrivilegesRoleNameFilter			= By.cssSelector("div[id='projectmanageRolesModal'] div[id='rolePrivilegesMainDiv'] div[id='workspace-role-filter'] button[filterkey='manageRoles']");
		public static By	txt_PopManageRolesRolePrivilegesSearchInput				= By.cssSelector("div[class*='filter-layer-box'] div[class='check-list-select'] input[filterkey='manageRoles']");
		public static By	txt_PopManageRolesFilteredUserInput						= By.cssSelector("div[id='projectmanageRolesModal'] table[id='manageRolesTable'] tr:nth-child(1) div[class*='manageRolesAssignTo'] input");
		public static By	txt_PopManageRolesNewAssignUserInput					= By.cssSelector("div[id='projectmanageRolesModal'] table[id='manageRolesTable'] tr[class='newRoleRow'] td:nth-child(3) input[class*='select2-input']");
		public static By	txt_PopManageRolesSearchRole							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] tr[class='manageRolesSearch'] td:nth-child(2) input");
		public static By	btn_PopManageRolesSave									= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] div[id='manageRolesMainDiv'] button[id='manageRolesSaveBtn']");
		public static By	btn_PopManageRolesPrivilegesSave						= By.cssSelector("div[id='projectmanageRolesModal'] div[id='rolePrivilegesMainDiv'] button[id='save-role-privileges']");
		public static By	btn_PopManageRolesCancel								= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] div[id='manageRolesMainDiv'] button[class*='btn-inverse']");
		public static By	lnk_PopEditProjectInheritanceOption						= By.cssSelector("div[id='myModal-projectEdit'][aria-hidden='false'] div[id='inheritanceSetting'] a[id='inheriTab']");
		public static By	lnk_PopCreateProjectSettings							= By.cssSelector("div[id='workspaceSetting'] a[id='settingTab']");
		public static By	lnk_PopEditProjectWorkingCalender						= By.cssSelector("div[id='projectCalendar'] a");
		public static By	chk_PopEditProjetInheritanceOptioncheckboxlist			= By.cssSelector("div[id='inheritanceTable'] input[id*='chkInheritObject']");
		public static By	chk_PopEditProjectWorkingDaysList						= By.cssSelector("div[id='project_calender'] table[class='table-project'] tr[id='working_day_label'] input");
		public static By	ele_CustomizeListingOptions 							= By.cssSelector("div[class='manage-files tab-listing'] div[class*='listing-setting'] a[class='btn btn-default dropdown-toggle']");
		/*
		public static By	drp_CreateCloneProjectGeography							= By.cssSelector("form[id='createCProject'] div[id='create-cloneprojects'] select[id='cselectGeograpy']");
		public static By	css_ProjectsTabFirstTemplateName						= By.cssSelector("div[id='project-blocks'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-projectName']");
		public static By	lbl_PopEditProjectFieldEnabled							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[class='projectform'] div[id='edit-projects'] label[for='chkIsFieldEnabled']");
		public static By	chk_PopManageRolesFilteredPrivilegeWSAdminCheckbox		= By.cssSelector("div[id='roles-table'] tbody[class='role-val'] tr:last-child td[style*='display: table-cell'] input");
		public static By	chk_PopManageRolesFilteredPrivilegeAndRoleCheckbox		= By.cssSelector("div[id='roles-table'] tbody[class='role-val'] tr:not([style]) td[style*='table-cell'] input");
		public static By	ele_ProjectFilterResetAll								= By.cssSelector("div[id='project_searchfilter'] div[id='savedFilters'] li[listfilterkey='Active'] i[id='filterResetAll']");
		public static By 	drp_CustomListingCount 									= By.cssSelector("div[class*='listing-setting open'] div[class='manage-toolbar-header dropdown-menu'] select[id='optshow']");
		public static By	lnk_PopEditProjectBasicInformation						= By.cssSelector("div[id='basicSetting'] a");
		*/

		/* Manage Form Statuses */
		public static By	txt_PopManageFormStatusesStatusNameFilter				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectManageFormStatusModal'] table[id='formStatusTable'] input[id='projectAttrSearchInput']");
		public static By	btn_PopManageFormStatusesBGColorSave					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectManageFormStatusModal'] table[id='formStatusTable'] form[id='customSetting'] button[id='manageFormStatusSettingSaveBtn']");
		public static By	img_PopManageFormStatusesBGColorPicker					= By.cssSelector("table[id='formStatusTable'] tr[class='showSettingRow'] form[id='customSetting'] img[id='bgColorPicker']");
		public static By	txt_PopBGColorPickerRedInput							= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class*='colpick_rgb_r'] input[type='text']");
		public static By	txt_PopBGColorPickerGreenInput							= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class*='colpick_rgb_g'] input[type='text']");
		public static By	txt_PopBGColorPickerBlueInput							= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class*='colpick_rgb_b'] input[type='text']");
		public static By	btn_PopBGColorPickerOKButton							= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class='colpick_submit']");
		public static By	btn_PopManageFormStatusesSave							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectManageFormStatusModal'] button[id='manageFormStatusSaveBtn']");
		public static By	css_PopManageDocFormStatusesRows						= By.cssSelector("table[id='formStatusTable'] tbody tr[entityid]");
		public static By	txt_PopManageDocFormStatusesFilteredStatuses			= By.cssSelector("input[class='formStatusName'][style*='color: rgb']");
		public static By	lnk_PopManageDocFormFilteredStatusSettings				= By.cssSelector("a[class='showSettingDiv'] img");
		public static By	img_PopManageDocStatusSettingsBgColor					= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] form[id='customSetting'] img[id='bgColorPicker']");
		public static By	rad_DocStatusSettingsApplyToRecord						= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] form[id='customSetting'] input[value='2']");
		public static By	rad_DocStatusSettingsApplyToCell						= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] form[id='customSetting'] input[value='1']");
		public static By	lnk_DocStatusSettingsFont								= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] form[id='customSetting'] div[id='fontType_chosen'] a");
		public static By	btn_DocStatusSettingsSave								= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] form[id='customSetting'] button[id='manageFormStatusSettingSaveBtn']");
		public static By	ele_DocListingFirstStatusCell							= By.cssSelector("div[id='docListingSection'] div[id='listing'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-status-fixed-width'] div[class*='first']");
		public static By	ele_DocListingFirstFileStatusCell						= By.xpath(".//div[@index='0']//div[contains(@class,'status')]");
		public static By	ele_DocListingFirstRow									= By.cssSelector("div[id='docListingSection'] div[id='listing'] div[id='adTableBody'] div[class*='rows']:nth-child(1)");
		public static By	txt_DocStatusSettingsFontSearch							= By.cssSelector("form[id='customSetting'] div[id='fontType_chosen'] input");
		public static By	lbl_DocStatusSettingsFontSearchResult					= By.cssSelector("form[id='customSetting'] div[id='fontType_chosen'] li[class*='highlighted']");
		/*
		public static By	txt_DocStatusSettingsBGColorRInput						= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class='colpick_rgb_r colpick_field']");
		public static By	txt_DocStatusSettingsBGColorGInput						= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class='colpick_rgb_g colpick_field']");
		public static By	txt_DocStatusSettingsBGColorBInput						= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class='colpick_rgb_b colpick_field']");
		public static By	btn_DocStatusSettingsBGColorOK							= By.cssSelector("div[id*='collorpicker'][style*='display: block'] div[class='colpick_submit']");
		public static By	lnk_PopManageDocStatusSecondStatusSettings				= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] tr[class='newStatusRow']:nth-child(4) a[class='showSettingDiv'] img");
		public static By	lnk_PopManageDocStatusThirdStatusSettings				= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] tr[class='newStatusRow']:nth-child(6) a[class='showSettingDiv'] img");
		*/

		/* Manage Project Form Template Settings */
		public static By	txt_PopCreateProjectClientInput							= By.cssSelector("div[id='addProjectSelectClientName'] ul[class='select2-choices'] li[class='select2-search-field'] input");
		public static By	lnk_ProjectsTabLHAddProject								= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-projectadd']");
		public static By	lnk_ProjectsFirstProject								= By.xpath(".//div[@index='0']//div[contains(@class,'projectName')]//a");
		public static By	txt_PopCreateProjectProjectName							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] div[class='projectform'] div[id='edit-projects'] input[id='editinputproname']");
		/*
		 * public static By txt_PopCreateProjectDescription = By .cssSelector( "div[class='modal-scrollable'][style*='z-index'] form[id='createProject'] div[class='projectform'] textarea[id='textareadesc']" );
		 */
		public static By	drp_PopCreateProjectGeography							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] select[id='editselectGeograpy']");
		public static By	chk_PopCreateProjectFieldEnabled						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] input[id='chkIsFieldEnabled']");
		public static By	btn_PopCreateProjectSave								= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] input[id='projectEditSubmit'] ");
		public static By	txt_ProjectsFilterInput									= By.cssSelector("form[id='projects_pd_filerForm'] input[id='projects_fd_containText']");
		public static By	lnk_ProjectsListFirstProject							= By.cssSelector("div[id='project-blocks'] div[id='listing'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-projectName'] a");
		public static By	opt_ProjectContextClickEdit								= By.cssSelector("ul[class*='context-menu-list'] li[class*='CMicon-removefavourite']+li[class*='CMicon-edit']");
		public static By	opt_ProjectContextClickEditAppSettings					= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-edit'] ul[class='context-menu-list'][style] li[class*='CMicon-manage-app-settings'] span");
		public static By	opt_ProjectContextClickEditApps							= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-edit'] ul[class='context-menu-list'] li[class*='CMicon-assign-apps']:nth-child(7) span");
		public static By	opt_ProjectContextClickEditUserAccess					= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-edit'] ul[class='context-menu-list'][style] li[class*='CMicon-manage-roles'] span");
		public static By	opt_ProjectContextClickEditFormStatues					= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-edit'] ul[class='context-menu-list'][style] li[class*='CMicon-manage-statuses'] span");
		public static By	opt_ProjectContextClickEditFileStatues					= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-edit'] ul[class='context-menu-list'][style] li[class*='CMicon-manage-doc-statuses'] span");
		public static By	opt_ProjectContextClickEditPoi							= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-edit'] ul[class='context-menu-list'][style] li[class*='CMicon-manage-poi'] span");
		public static By	pop_AssignAppsModal										= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='assignAppsModal']");
		public static By	txt_PopAssignAppModalSearchInput						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='assignAppsModal'] input[id*='assignAppsBox_fd_containText']");
		public static By	chk_PopAssignAppsFirstFormCheckbox						= By.cssSelector("div[id='assignAppsModal'] div[class='innerWrapper'] table tbody tr:nth-child(2) input[class='templateType']");
		public static By	chk_PopAssignAppsFirstFilteredFormCheckbox				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='assignAppsModal'] tr[style*='display: table-row'] input[type='checkbox']");
		public static By	lbl_PopAssignAppsFirstFormTemplateDesc					= By.cssSelector("div[id='assignAppsModal'] div[class='innerWrapper'] table tbody tr:nth-child(2) td:nth-child(3)");
		public static By	btn_PopAssignAppsAddtoProject							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='assignAppsModal'] button[id='assignAppsToProject']");
		public static By	txt_PopManageAppsModalSearchInput						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='manageAppsModal'] input[id='manageApps_fd_containText']");
		public static By	ele_PopManageAppsFirstFormStatus						= By.cssSelector("div[id='manageAppsModal'] div[id='listing'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-status-fixed-width']");
		public static By	ele_PopManageAppsFormStatusContextMenu					= By.cssSelector("ul[class*='context-menu-list'][style] li[class='context-menu-item active'] span");
		public static By	btn_PopRoleFormPermissionsFormNameSelector				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='manageRolesSection'] div[id='roleFormPermissionsMainDiv'] div[id='form-name-filter'] button");
		public static By	txt_PopRoleFormPermissionFormNameSearch					= By.cssSelector("div[class='filterui top-label'] div[class*='filter-layer-box'] input[class*='filterui-field']");
		public static By	chk_PopRoleFormPermissionFormNameFirstCheckbox			= By.cssSelector("div[class='filterui top-label'] div[class*='filter-layer-box'] div[class='filterui-list-scroll'] li:nth-child(1) a[id*='ui-id'] input");
		public static By	lbl_PopRoleFormPermissionFilteredFormNameHeader			= By.cssSelector("div[id='form-permission-table'] header ul[class='form-name'] li");
		public static By	btn_PopRoleFormPermissionSave							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='manageRolesSection'] div[id='roleFormPermissionsMainDiv'] button[id='save-form-permissions']");
		public static By    css_PopRoleFormPermissionRolesHeaders					= By.cssSelector("div[id='form-permission-table'] ul[class='role-form-permission-header'] li");
		public static By	css_PopRoleFormPermissionRolesLabelRows					= By.cssSelector("div[id='form-permission-table'] div[class*='side-wrapper'] li");
		public static By	css_PopRoleFormPermissionRolesLabels					= By.cssSelector("div[id='form-permission-table'] div[class*='side-wrapper'] li input");
		public static By	img_PopManageAppsModelFirstEditImage					= By.cssSelector("div[id='manageAppsModal'] div[id='apps'] div[id='manageAppsMainDiv'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-editFormImage'] img");
		public static By	txt_PopEditAppSettingsFormName							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='formDetailSettings'] input[class*='formName']");
		public static By	txt_PopEditAppSettingsFormGroupCode						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='formDetailSettings'] input[class*='formGroupCode']");
		public static By	txt_PopEditAppSettingsFormGroupName						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='formDetailSettings'] input[class*='formGroupName']");
		public static By	txt_PopEditAppSettingsFormAppBuilderCode				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class*='appBuilderOptions'] input[class*='appBuilderFormIdCode']");
		public static By	lnk_PopEditAppSettingsDownloadFormXsn					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='appBuilderOptions'] a[class='downLoadFormTemplate xsn']");
		public static By	btn_PopEditAppSettingsXsnBrowseButton					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='appBuilderOptions'] input[id='infoPathTemplete']");
		public static By	btn_PopEditAppSettingsXsnUpload							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] div[class='appBuilderOptions'] input[id='infoPathTemplete']+button[id='upload']");
		public static By	Pop_TemplateUploadWaitPopup								= By.cssSelector("div[id='templateUploadwait'][style*='display: block']");
		/*
		public static By	css_ProjectContextClickEditOptions						= By.cssSelector("li[class*='context-menu-item'][class*='hover'][class*='CMicon-edit'] li[class*='context-menu-item'] span");
		public static By	pop_ManageAppsModal										= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='manageAppsModal']");
		public static By	chk_PopRoleFormPermissionWSAdminCreate					= By.cssSelector("div[id='form-permission-table'] tbody[class='role-val'] tr:last-child td[title='Create'][style*='display: table-cell'] input");
		public static By	chk_PopRoleFormPermissionWSAdminControl					= By.cssSelector("div[id='form-permission-table'] tbody[class='role-val'] tr:last-child td[title='Control'][style*='display: table-cell'] input");
		*/

		/* Yes Checkboxes */
		public static By	rad_PopEditAppSettingsCrossWSDataConnectYes				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_WORKSPACE_LINK']");
		public static By	rad_PopEditAppSettingsCrossWSDataConnectNo				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_WORKSPACE_LINK']");
		public static By	txt_PopEditAppSettingsCrossWSName						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] input[id='workspaceName']");
		public static By	lnk_PopEditAppSettingsCrossWSAutoComplete				= By.cssSelector("div[id='editAppsModal'] div[class='appBuilderOptions'] li[class='ui-menu-item'] a");
		public static By	rad_PopEditAppSettingsUserControllerYes					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='controllerSettings'] label[class*='radio']:nth-child(1) input[name='FT_USE_CONTROLLER']");
		public static By	rad_PopEditAppSettingsControlChangeStatusYes			= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='controllerSettings'] label[class*='radio']:nth-child(1) input[name='FT_CTRL_CHANGE_STATUS']");
		public static By	rad_PopEditappSettingsAllowEditORIYes					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_EDITING_ORI']");
		public static By	rad_PopEditAppSettingsResponseAllowedYes				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(1) input[name='FT_RESPONSE_ALLOWED']");
		public static By	rad_PopEditAppSettingsEnableDraftResponseYes			= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(1) input[name='FT_ENABLE_DRAFT_RESPONSES']");
		public static By	rad_PopEditAppSettingsResponderCollabYes				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(1) input[name='FT_RESPONDERS_COLLABORATE']");
		public static By	rad_PopEditAppSettingsShowResponsesYes					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(1) input[name='FT_SHOW_RESPONSES']");
		public static By	rad_PopEditAppSettingsDistributionOnORIMandatory		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_DISTRIBUTION']");
		public static By	rad_PopEditAppSettingsDistributionByOriginatorNo		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(2) input[name='FT_DISTRIBUTE_ORIGINATOR']");
		public static By	rad_PopEditAppSettingsDistributionByRecipientsNo		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(2) input[name='FT_DISTRIBUTE_RECIPIENTS']");
		public static By	rad_PopEditAppSettingsAllowEditForwardYes				= By.cssSelector("div[id='editAppsModal'] fieldset[class='editAndForwardFieldSet'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_FORWARDING']");
		public static By	rad_PopEditAppSettingsAllowAttachmentYes				= By.cssSelector("div[id='editAppsModal'] fieldset[class='attachmentFieldSet'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_ATTACHMENTS']");
		public static By	rad_PopEditAppSettingsAutoPublishFolderYes				= By.cssSelector("div[id='editAppsModal'] fieldset[class='attachmentFieldSet'] label[class*='radio']:nth-child(1) input[name='FT_AUTO_PUBLISH_TO_FOLDER']");
		public static By	rad_PopEditAppSettingsAllowDocAssociationYes			= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_DOC_ASSOCIATIONS']");
		public static By	btn_PopEditAppSettingsSelectFolder						= By.cssSelector("div[id='editAppsModal'] fieldset[class='attachmentFieldSet'] button[id='selectFolder']");
		public static By	lnk_PopSelectFolderProjectFirstFolder					= By.cssSelector("div[id='selectFolderManageAppsModal'] div[id='treeView'] div[id*='fold_'] a[class='docList']");
		public static By	lbl_PopSelectFolderSelectedFolder						= By.cssSelector("div[id='selectFolderManageAppsModal'] div[id='listingHeaderSection'] h2");
		public static By	btn_PopSelectFolderSaveButton							= By.cssSelector("div[id='selectFolderManageAppsModal'] button[id='saveSelectedFolder']");
		public static By	rad_PopEditAppSettingsAssocExtendDocIssueYes			= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(1) input[name='FT_ASSOC_EXTEND_DOC_ISSUE']");
		public static By	rad_PopEditAppSettingsAllowCommentAssocYes				= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_COMMENT_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsAssocBypassFolderSecurityYes		= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(1) input[name='FT_VIEW_ALWAYS_DOC_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsAllowViewAssociationYes			= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_VIEW_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsOverAllFormStatusesYes			= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_HAS_OVERALL_STATUS']");
		public static By	rad_PopEditAppSettingsAllowReopenFormsYes				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_REOPENING_FORM']");
		public static By	rad_PopEditAppSettingsOrigChangeStatusYes				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_ORIG_CHANGE_STATUS']");
		public static By	rad_PopEditAppSettingsFormIsPublicYes					= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_IS_PUBLIC']");
		public static By	rad_PopEditAppSettingsFormContentInEmailYes				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_EMBED_FORM_CONTENT_IN_INSTANT_EMAIL']");
		public static By	rad_PopEditAppSettingsCanReplyViaEmailsYes				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_CAN_REPLY_VIA_EMAIL']");
		public static By	rad_PopEditAppSettingsAllowExternalAccessYes			= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_EXTERNAL_ACCESS']");
		public static By	chk_PopEditAppSettingsEnableSpellCheckOnRequest			= By.cssSelector("div[id='editAppsModal'] input[class='onRequest']");
		public static By	chk_PopEditAppSettingsEnableSpellCheckOnSavingForm		= By.cssSelector("div[id='editAppsModal'] input[class='onSavingForm']");
		public static By	drp_PopEditAppSettingsRestrictStatusChange				= By.cssSelector("div[id='editAppsModal'] select[class='restrictStatusChangeInViewFormField']");
		public static By	rad_PopEditAppSettingsUseFormDistrinGroupYes			= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='distributionFromGroupOnly']");
		public static By	rad_PopEditAppSettingsAllowAutoCreationStatusChangeYes	= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='allowAutoCreateOnStatusChange']");
		public static By	txt_PopEditAppSettingsNotificaitonEmailSubject			= By.cssSelector("div[id='editAppsModal'] input[name='FT_FORM_ACTION_EMAIL_SUBJECT']");
		public static By	rad_PopEditAppSettingsAFormAvailableOfflineYes			= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(1) input[name='FT_IS_FORM_AVAILABLE_OFFLINE']");
		public static By	rad_PopEditAppSettingsAllowFormAssociationYes			= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_FORM_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsAssocBypassSecurityYes			= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(1) input[name='FT_VIEW_ALWAYS_FORM_ASSOCIATIONS']");
		public static By	css_PopEditAppSettingsActionRequiredCheckboxes			= By.cssSelector("div[id='editAppsModal'] fieldset[class='actionsRequired'] div[class='assocAction'] label[class='checkbox'] input[type='checkbox']");
		public static By	css_PopEditAppSettingsActionRequiredTimeDropdowns		= By.cssSelector("div[id='editAppsModal'] fieldset[class='actionsRequired'] select[id*='FT_DEFAULT_ACTION_TIME']");
		public static By	drp_PopEditAppSettingsDefaultActionRequired				= By.cssSelector("div[id='editAppsModal'] fieldset[class='actionsRequired'] select[id='defaultAction']");
		public static By	css_PopEditAppSettingsFormFinalStatusesCheckboxes		= By.cssSelector("div[id='editAppsModal'] div[class='formStatusTable'] input[name='FT_ASSOC_STATUS']");
		public static By	css_PopEditAppSettingsCloseOutStatuesCheckboxes			= By.cssSelector("div[id='editAppsModal'] div[class='formStatusTable'] input[name='FT_CLOSE_OUT_STATUS']");
		/*public static By	rad_PopEditAppSettingsResponseFromAll					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(1) input[name='FT_RESPONSE_FROM']");
		public static By	rad_PopEditAppSettingsContinueDiscussionYes				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(1) input[name='FT_CONTINUE_DISCUSSION']");
		public static By	rad_PopEditAppSettingsDistributionAfterCreationYes		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(1) input[name='FT_ALLOW_DISTRIBUTING_AFTER_CREATION']");
		public static By	rad_PopEditAppSettingsDistributionByOriginatorYes		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(1) input[name='FT_DISTRIBUTE_ORIGINATOR']");
		public static By	rad_PopEditAppSettingsDistributionByRecipientsYes		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(1) input[name='FT_DISTRIBUTE_RECIPIENTS']");*/

		/* No Checkboxes */
		public static By	rad_PopEditAppSettingsUserControllerNo					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='controllerSettings'] label[class*='radio']:nth-child(2) input[name='FT_USE_CONTROLLER']");
		public static By	rad_PopEditAppSettingsControlChangeStatusNo				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='controllerSettings'] label[class*='radio']:nth-child(2) input[name='FT_CTRL_CHANGE_STATUS']");
		public static By	rad_PopEditAppSettingsResponseAllowedNo					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(2) input[name='FT_RESPONSE_ALLOWED']");
		public static By	rad_PopEditAppSettingsEnableDraftResponseNo				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(2) input[name='FT_ENABLE_DRAFT_RESPONSES']");
		public static By	rad_PopEditAppSettingsResponderCollabNo					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(2) input[name='FT_RESPONDERS_COLLABORATE']");
		public static By	rad_PopEditAppSettingsDistributionOnORINotRequired		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(3) input[name='FT_ALLOW_DISTRIBUTION']");
		public static By	rad_PopEditAppSettingsDistributionAfterCreationNo		= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_DISTRIBUTING_AFTER_CREATION']");
		public static By	rad_PopEditAppSettingsAllowEditForwardNo				= By.cssSelector("div[id='editAppsModal'] fieldset[class='editAndForwardFieldSet'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_FORWARDING']");
		public static By	rad_PopEditAppSettingsAllowAttachmentNo					= By.cssSelector("div[id='editAppsModal'] fieldset[class='attachmentFieldSet'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_ATTACHMENTS']");
		public static By	rad_PopEditAppSettingsAutoPublishFolderNo				= By.cssSelector("div[id='editAppsModal'] fieldset[class='attachmentFieldSet'] label[class*='radio']:nth-child(2) input[name='FT_AUTO_PUBLISH_TO_FOLDER']");
		public static By	rad_PopEditAppSettingsAllowDocAssociationNo				= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_DOC_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsAssocExtendDocIssueNo				= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(2) input[name='FT_ASSOC_EXTEND_DOC_ISSUE']");
		public static By	rad_PopEditAppSettingsAllowCommentAssocNo				= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_COMMENT_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsAssocBypassFolderSecurityNo		= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(2) input[name='FT_VIEW_ALWAYS_DOC_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsAllowViewAssociationNo			= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_VIEW_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsOverAllFormStatusesNo				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_HAS_OVERALL_STATUS']");
		public static By	rad_PopEditAppSettingsAllowReopenFormsNo				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_REOPENING_FORM']");
		public static By	rad_PopEditAppSettingsOrigChangeStatusNo				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_ORIG_CHANGE_STATUS']");
		public static By	rad_PopEditAppSettingsFormIsPublicNo					= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_IS_PUBLIC']");
		public static By	rad_PopEditAppSettingsFormContentInEmailNo				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_EMBED_FORM_CONTENT_IN_INSTANT_EMAIL']");
		public static By	rad_PopEditAppSettingsCanReplyViaEmailsNo				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_CAN_REPLY_VIA_EMAIL']");
		public static By	rad_PopEditAppSettingsAllowExternalAccessNo				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_EXTERNAL_ACCESS']");
		public static By	rad_PopEditAppSettingsUseFormDistrinGroupNo				= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='distributionFromGroupOnly']");
		public static By	rad_PopEditAppSettingsAllowAutoCreationStatusChangeNo	= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='allowAutoCreateOnStatusChange']");
		public static By	rad_PopEditAppSettingsAFormAvailableOfflineNo			= By.cssSelector("div[id='editAppsModal'] label[class*='radio']:nth-child(2) input[name='FT_IS_FORM_AVAILABLE_OFFLINE']");
		public static By	rad_PopEditAppSettingsAllowFormAssociationNo			= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_FORM_ASSOCIATIONS']");
		public static By	rad_PopEditAppSettingsAssocBypassSecurityNo				= By.cssSelector("div[id='editAppsModal'] div[class*='wrpperCER'] label[class*='radio']:nth-child(2) input[name='FT_VIEW_ALWAYS_FORM_ASSOCIATIONS']");
		public static By	btn_PopEditAppSettingsSave								= By.cssSelector("div[id='editAppsModal'] button[id='saveAppSetting']");
		public static By	btn_PopEditAppSettingsCancel							= By.cssSelector("div[id='editAppsModal'] button[data-dismiss='modal'][class*='btn-inverse']");
		/*
		public static By	rad_PopEditAppSettingsResponseFromRecipient				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(2) input[name='FT_RESPONSE_FROM']");
		public static By	rad_PopEditAppSettingsContinueDiscussionNo				= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(2) input[name='FT_CONTINUE_DISCUSSION']");
		public static By	rad_PopEditAppSettingsShowResponsesNo					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] fieldset[class='responseSettings'] label[class*='radio']:nth-child(2) input[name='FT_SHOW_RESPONSES']");
		public static By	rad_PopEditAppSettingsDistributionOnORIOptional			= By.cssSelector("div[id='editAppsModal'] fieldset[class='distributionFieldSet'] label[class*='radio']:nth-child(2) input[name='FT_ALLOW_DISTRIBUTION']");
		public static By	btn_PopEditAppSettingsClose								= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'] button[class='close']");
		public static By	btn_ProjectsFilterSearch								= By.xpath(".//a[@id='projects_basic_search' and @class='filter_search_btn']");
		*/

		/* Purpose Of Issue, Status And Form Status */
		public static By	opt_PopFileStatusNewStatusRoleAdmin						= By.cssSelector("li[class*='CMicon-doc-admin-acl'] span");
		public static By	opt_PopFileStatusNewStatusRolePublish					= By.cssSelector("li[class*='CMicon-accessPublish'] span");
		public static By	opt_PopFileStatusNewStatusRoleStatusChange				= By.cssSelector("li[class*='CMicon-accessStatusChange'] span");
		public static By	opt_PopFileStatusNewStatusRoleNoAccess					= By.cssSelector("li[class*='CMicon-noAccess-doc-acl'] span");
		public static By	opt_PopManagePOIRoleAdmin								= By.cssSelector("li[class*='CMicon-doc-admin-acl'] span");
		public static By	opt_PopManagePOIRolePublish								= By.cssSelector("li[class*='CMicon-accessPublish'] span");
		public static By	opt_PopManagePOIRoleAttributeChange						= By.cssSelector("li[class*='CMicon-accessAttrChange'] span");
		public static By	opt_PopManagePOIRoleNoAccess							= By.cssSelector("li[class*='CMicon-noAccess-doc-acl'] span");
		public static By	opt_PopFormStatusNewStatusRoleAdmin						= By.cssSelector("li[class*='CMicon-admin'] span");
		public static By	opt_PopFormStatusNewStatusRoleNoAccess					= By.cssSelector("li[class*='CMicon-noAccess'] span");
		public static By	opt_PopFormStatusNewStatusRoleAccessToUse				= By.cssSelector("li[class*='CMicon-access'] span");
		public static By	btn_AddNewPurposeOfIssue								= By.xpath(".//a[text()='Add a new Purpose of Issue']");
		public static By	btn_AddNewStatus										= By.xpath(".//a[text()='Add New Status']");
		public static By	txt_WorkspaceDocStatusAndPoiList						= By.cssSelector("div[id='formStatusContainer'] table[id='formStatusTable'] input[class='formStatusName']");
		public static By	css_AddPOIAndStatusTextList								= By.xpath(".//table[@id='formStatusTable']//tr[@class='newStatusRow']//input[contains(@name,'formStatusName')]");
		public static By	css_CloseAssignedUserList								= By.xpath(".//ul[@class='select2-choices']/li[not(contains(@class,' docdistgroup'))]//a");
		public static By	css_DefaultUserList										= By.cssSelector("ul[class*='select2-choices'] li[class*='locked']:nth-child(1) div[title*='No Access']");
		public static By	css_adminRoleList										= By.cssSelector("ul[class*='select2-choices'] li[class*=' docdistgroup']:nth-child(2) div[title*='Administrator']");
		public static By	btn_ContextClickMenuAdmin								= By.xpath(".//ul[not(contains(@style,'display: none'))]//li[contains(@class,'admin')]");
		public static By	css_AddUserTextList										= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] tr[class='newStatusRow'] input[class='select2-input']");
		public static By	css_PrimaryUserList										= By.cssSelector("div[id='projectManageFormStatusModal'] table[id='formStatusTable'] tr[class='newStatusRow'] li:nth-child(3) div");
		public static By	btn_POIAndStatusSaveButton								= By.xpath(".//div[@aria-hidden='false']//button[contains(text(),'Save')]");
		public static By	btn_POIAndStatusCancelButton							= By.cssSelector("div[id='projectManageFormStatusModal'] div[class='modal-footer'] button[class*='btn-inverse']");
		public static By	css_AllPOIAndStatusCheckList							= By.xpath(".//div[@id='formStatusContainer']//tr[not(contains(@class,'filterTd1'))]//input[@type='checkbox' and not(contains(@disabled,'disable'))]");
		public static By	txt_SearchPOIAndStatusFilter							= By.xpath(".//input[@id='projectAttrSearchInput']");
		public static By	txt_ManageAppPopupSearchFormTitle						= By.xpath(".//input[@id='manageApps_fd_containText']");
		public static By	pop_EditAppSettings										= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='editAppsModal'][style*='display: block']");
		public static By	css_OverallFormStatuesList								= By.xpath(".//div[@class='formStatusTable']//div[@class='formStatusDiv']//label[text()]");
		public static By	btn_ManageAppSettingsCancelButton						= By.xpath(".//div[@aria-hidden='false' and contains(@style,'display: block')]//button[contains(text(),'Cancel')]");
		/*
		public static By	lnk_ManageAppPopEditTemplateLink						= By.xpath(".//div[contains(@class,'rows') and contains(@style,'block')]//div[contains(@class,'editFormImage')]//a");
		public static By	css_ManageAppPopupFormTypeList							= By.xpath(".//div[@index]//div[contains(@class,'formTypeName')]//div[text()]");
		*/

		/* View Project */
		public static By	lnk_ProjectTabFirstOwnerOrg								= By.xpath(".//div[@index='0']//div[contains(@class,'ownerOrgName')]//a[text()]");
		public static By	lnk_ProjectTabFirstActiveFilesHyperLink					= By.xpath(".//div[@index='0']//div[contains(@class,'activeFilesCount')]//a[text()]");
		public static By	lnk_FirstProjectLastFileUploadedHyperLink				= By.xpath(".//div[@index='0']//div[contains(@class,'lastFilePublishedDate')]//a[text()]");
		public static By	ele_FirstProjectLastFormActivityDate					= By.xpath(".//div[@index='0']//div[contains(@class,'lastFormCreatedDate')][text()]");
		public static By	ele_FirstProjectFormsCount								= By.xpath(".//div[@index='0']//div[contains(@class,'formsCount')][text()]");
		public static By	lnk_FirstProjectStatusHyperLink							= By.xpath(".//div[@index='0']//div[contains(@class,'projectStatus')]//a[text()]");
		public static By	drp_EditProjectStatusDropdown							= By.xpath(".//select[@id='editselectStatus']");
		public static By	lbl_ProjectsTabProjectsCount							= By.xpath(".//div[@id='projectRecCounter']");
		public static By	chk_ProjectsTabCheckAllCheckbox							= By.xpath(".//div[@id='adTableHead']//input[@type='checkbox']");
		public static By	lnk_ProjectsTabFavoriteProjectsTab						= By.xpath(".//a[contains(@id,'FavoriteProject')]");
		public static By	css_NumberOfProjects									= By.xpath(".//div[@index]//div[contains(@class,'projectName')]//a[text()]");
		public static By	css_MarkAsFavouriteProjectsImageList					= By.xpath(".//div[@index]//div[contains(@class,'favImagePath')]//img[@title='Remove as Favourite' or @title='Remove as Favorite']");
		public static By	css_RemoveAsFavouriteProjectsImageList					= By.xpath(".//div[@index]//div[contains(@class,'favImagePath')]//img[@title='Add as Favourite' or @title='Add as Favorite']");
		public static By	lnk_ThumbViewActiveLink									= By.xpath(".//a[contains(@class,'active')][@title='Thumb View']");
		public static By	lnk_ThumbView											= By.xpath(".//a[not(contains(@class,'active'))][@title='Thumb View']");
		public static By	css_ThumbViewProjectsList								= By.xpath(".//div[@id='projectTabThumbListCont' and @style='display: block;']//div[@class='box-projectname' and text()]");
		public static By	lnk_ListViewActiveLink									= By.xpath(".//a[contains(@class,'active')][@title='List View']");
		public static By	lnk_ListView											= By.xpath(".//a[not(contains(@class,'active'))][@title='List View']");
		public static By	css_ListViewProjectsList								= By.xpath(".//div[@id='listing' and @style='display: block;']//div[@index]//div[contains(@class,'projectName')]//a");
		public static By	ele_ProjectsTabTypeColumn								= By.cssSelector("div[id='adTableHead'] div[class*='col-projectType-fixed-width']");
		public static By	ele_ProjectsTabTypeColumnResizedElement					= By.cssSelector("div[id='adTableHead'] div[class*='col-projectType-fixed-width'] span[class='resize']");
		public static By	ele_ProjectsTabTypeAdjacentColumn						= By.cssSelector("div[id='adTableHead'] div[class*='col-projectType-fixed-width']+div");
		public static By	css_ProjectsTabColumnList								= By.xpath(".//div[@id='listing']//div[@id='adTableHead']//div[contains(@id,'columnId')]");
		/*public static By	ele_ProjectsTabGeographyColumn							= By.cssSelector("div[id='adTableHead'] div[class*='col-geography-fixed-width']");*/

		/* Create Edit Roles */
		public static By	btn_PopManageRolesCreateNewRole							= By.xpath(".//a[contains(text(),'Create New Role')]");
		public static By	txt_PopManageRolesCreateNewRoleInput					= By.xpath(".//table[@id='manageRolesTable']//tr[@class='newRoleRow']//input[contains(@name,'manageRolesName')]");
		public static By	btn_ManageRolesPopupSaveButton							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] div[id='manageRolesContainer'] button[id='manageRolesSaveBtn']");
		public static By	btn_ManageRolesFormPopupSaveButton						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] div[id='roleFormPermissionsMainDiv'] button[id='save-form-permissions']");
		public static By	lnk_PopManageRolesFormPermissionLHPanelLink				= By.xpath(".//a[contains(@id,'form-permissions')]");
		public static By	btn_ManageRolesTabSaveButton							= By.cssSelector("div[id='projectmanageRolesModal'] div[id='manageRolesMainDiv'] button[id='manageRolesSaveBtn']");
		public static By	btn_ManageRolesTabCancelButton							= By.cssSelector("div[id='projectmanageRolesModal'] div[id='manageRolesMainDiv'] button[id='manageRolesSaveBtn']+button");
		public static By	btn_PopManageRolesProxyDetailsSave						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modal-manage-proxy-user-detail'] button[id='manage-proxy-user-detail-save']");
		public static By	btn_ManageRolesPopupCancelButton						= By.xpath(".//button[@id='close-form-permission' and contains(text(),'Cancel')]");
		public static By	txt_CreateRolesSearchFilter								= By.xpath(".//table[@id='manageRolesTable']//tr[@class='manageRolesSearch']//td[2]//input");
		public static By	txt_PopManageRolesFirstFilteredRoleInput				= By.xpath(".//table[@id='manageRolesTable']//tr[not(contains(@class,'filterTd1'))]//input[contains(@name,'manageRolesName')]");
		public static By	txt_PopManageRolesFirstAssignUserInput					= By.xpath(".//table[@id='manageRolesTable']//tr[not(contains(@class,'filterTd1'))]//ul[@class='select2-choices']//input");
		public static By	ele_FirstAssignedUserName								= By.xpath(".//table[@id='manageRolesTable']//tr[not(contains(@class,'filterTd1'))]//ul[@class='select2-choices']//div[text()]");
		public static By	pop_ManageUserDetailsPopup								= By.xpath(".//div[contains(@id,'manage-proxy-user-detail') and @aria-hidden='false']");
		public static By	lbl_ManageUserDetailsPopupLabel							= By.xpath(".//div[contains(@id,'manage-proxy-user-detail') and @aria-hidden='false']//h3");
		public static By	txt_ProxyUsersInput										= By.xpath(".//div[contains(@class,'proxy-users')]//ul[@class='select2-choices']//input");
		public static By	css_DatePickerImageList									= By.cssSelector("div[id='modal-manage-proxy-user-detail'] tr[class*='newUserProxyRo'] img[class='ui-datepicker-trigger']");
		public static By	btn_ManageUserDetailsPopupCancelButton					= By.xpath(".//div[@aria-hidden='false']//div[@id='manageRolesContainer']//button[contains(text(),'Cancel')]");
		public static By	lnk_FirstAssignedUserRemoveLink							= By.xpath(".//table[@id='manageRolesTable']//tr[not(contains(@class,'filterTd1'))]//ul[@class='select2-choices']//a");
		public static By	pop_WarningPopup										= By.xpath(".//div[@id='deleteUserConfirmModal' and @aria-hidden='false']");
		public static By	lbl_WarningPopupLabel									= By.xpath(".//div[@id='deleteUserConfirmModal' and @aria-hidden='false']//h3");
		public static By	btn_PopWarningContinueButton							= By.cssSelector("div[id='deleteUserConfirmModal'] button[id='deleteUserConfirmBtn']");
		/*
		public static By	msg_UpdatedRoleMessage									= By.xpath(".//div[@id='adoddleTopMsgBox' and not(contains(@style,'display: none'))]//span");
		public static By	btn_CreateNewRoleButton									= By.xpath(".//a[contains(text(),'Create New Role')]");
		public static By	btn_PopManageRolesProxyDetailsCancel					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='modal-manage-proxy-user-detail'] button[id='manage-proxy-user-detail-cancel']");
		public static By	lnk_PopManageUserDetailsRemoveProxy						= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] table[id='manage-proxy-user-list-table'] a[class='remove-proxy-row-btn']");
		public static By	txt_ProxyUserStartDate									= By.xpath(".//input[contains(@class,'user-start-date datePicker')]");
		public static By	txt_ProxyUserEndDate									= By.xpath(".//input[contains(@class,'user-end-date datePicker')]");
		public static By	btn_ManageUserDetailsPopupSaveButton					= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='projectmanageRolesModal'] button[id='manageRolesSaveBtn']");
		*/

		/* Document Distribution Groups */
		public static By	btn_AddDistributionGroup								= By.xpath(".//button[@id='addManageDistributionGroupBtn']");
		public static By	txt_FirstDistributionGroupInput							= By.xpath(".//div[@id='distContainer']/div[1]//div[@class='group-name']//input");
		public static By	drp_FirstDistributionGroupTypeDropdown					= By.xpath(".//div[@id='distContainer']/div[1]//div[contains(@class,'groupType')]//select");
		public static By	txt_FirstDistributionUserInput							= By.xpath(".//div[@class='dist-content']//div[1]//div[@class='distList']//ul[@class='select2-choices']//input");
		public static By	sel_DistributionGroupContextMenuActionsDropdown			= By.cssSelector("select[name*='context-menu-input-actions']");
		public static By	css_DistributionGroupContextMenuActionsDropdownOptions	= By.cssSelector("select[name*='context-menu-input-actions'] option");
		public static By	sel_DistributionGroupContextMenuActionDaysDropdown		= By.xpath(".//select[@name='context-menu-input-actionDay']");
		public static By	ele_DistributionGroupContextMenuCloseButton				= By.cssSelector("ul[class*='context-menu-root'] li[class='context-menu-item icon CMicon-close'] span");
		public static By	btn_DistributionUserDropdownToggleButton				= By.xpath(".//div[@class='dist-content']//div[1]//div[@class='distList']//ul[@class='select2-choices']//button");
		public static By	btn_DistributionGroupSaveAll							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='project-dist-groups'] button[id='distSaveBtn']");
		public static By	txt_DistributionGroupSearchFilter						= By.xpath(".//input[@id='searchDistributionGroupsName']");
		public static By	txt_FirstDistributedGroupInput							= By.xpath(".//div[@class='dist-group-container edit-group 2 0' or @class='dist-group-container edit-group 1 0']//div[@class='group-name']//input");
		public static By	txt_FirstDisabledDistributedGroup						= By.xpath(".//div[@class='dist-group-container edit-group 1 0' or @class='dist-group-container edit-group 2 0']//div[contains(@class,'disabled')]//div[@class='group-name']//input");
		public static By	lnk_FirstDistributionGroupRemoveAdminRoleLink			= By.xpath(".//div[@class='dist-group-container edit-group 1 0' or @class='dist-group-container edit-group 2 0']//div[contains(@class,'securityList')]//ul[@class='select2-choices']//li[contains(@class,'distgroup')]//a[@class='select2-search-choice-close']");
		public static By	lnk_FirstDistributionGroupDefaultSecurity				= By.xpath(".//div[@class='dist-group-container edit-group 1 0' or @class='dist-group-container edit-group 2 0']//div[contains(@class,'securityList')]//ul[@class='select2-choices']//li[contains(@class,'locked')]");
		public static By	lnk_FirstDistributionGroupFirstRemovableSecurityUser	= By.xpath(".//div[@class='dist-group-container edit-group 1 0' or @class='dist-group-container edit-group 2 0']//div[contains(@class,'securityList')]//ul[@class='select2-choices']//li[contains(@class,'select2-search-choice')][not(contains(@class,'locked'))]//a");
		public static By	ele_FirstDistributionGroupFirstSecurityUser				= By.xpath(".//div[@class='dist-group-container edit-group 1 0' or @class='dist-group-container edit-group 2 0']//div[contains(@class,'securityList')]//ul[@class='select2-choices']//li[contains(@class,'select2-search-choice')][not(contains(@class,'locked'))]//div");
		public static By	txt_FirstDistributionGroupSecurityUserInput				= By.xpath(".//div[@class='dist-group-container edit-group 1 0' or @class='dist-group-container edit-group 2 0']//div[contains(@class,'securityList')]//ul[@class='select2-choices']//li[@class='select2-search-field']//input");
		public static By	css_DistributionGroupSecurityLatestGroupUsersList		= By.cssSelector("div[class='dist-content'] div[class*='newGroupRow']:first-child div[class='securityList'] ul[class='select2-choices'] li[class*='select2-search-choice']");
		/*public static By	lnk_FirstDistributionGroupRemoveUserLink				= By.xpath(".//div[@class='dist-group-container edit-group 1 0']//div[contains(@class,'securityList')]//div[2]//ul[@class='select2-choices']//a");*/

		/* Form Distribution Groups */
		public static By	txt_FirstFormTypeDistributionInput						= By.xpath(".//div[@class='dist-content']//div[1]//div[@class='form-type group-position']//ul[@class='select2-choices']//input");

		/* Role Privileges & Form Permissions */
		public static By	lnk_PopManageRoles										= By.xpath(".//a[contains(@id,'manageRole')]");
		public static By	lnk_PopActiveManageRoles								= By.xpath(".//a[contains(@id,'privileges') and contains(@class,'active')]");
		public static By	btn_RolePrivilegesRoleNameFilterButton					= By.xpath(".//div[@id='workspace-role-filter']//button");
		public static By	btn_RoleFormPermissionFilterButton						= By.xpath(".//div[@id='role-form-permission-filter']//button");
		public static By	btn_FormPermissionRoleNameFilterButton					= By.xpath(".//div[@id='workspaceRoleFilter']//button");
		public static By	btn_FormPermissionFormNameFilterButton					= By.xpath(".//div[@id='form-name-filter']//button");
		public static By	btn_RolePrivilegesFilterButton							= By.xpath(".//div[@id='role-privilege-filter']//button");
		public static By	txt_PopManageRolesSearchFilter							= By.xpath(".//input[@type='text' and @placeholder='Search...']");
		public static By	chk_SelectedRoleCheckbox								= By.xpath(".//div[@id='assignee-suggestions' and @style='display: block;']//ul[contains(@class,'ui-autocomplete') and contains(@style,'block')]//input[@type='checkbox']");
		public static By	lnk_PopRolePrivileges									= By.xpath(".//a[contains(@id,'role-privileges')]");
		public static By	css_RolePrivilegesCheckList								= By.xpath(".//div[@id='roles-table']//tr[not(contains(@style,'none'))]//td[@title and not(contains(@title,'Select All'))]");
		public static By	css_AccessPermissionLabelList							= By.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title]");
		public static By	css_FormPermissionFormAccessCheckList					= By.xpath(".//div[contains(@id,'table')]//div[@class='role-val']//ul[not(contains(@class,'ch'))]//li[not(contains(@class,'ch')) or contains(@class,'checked')]");
		public static By	btn_PopMessageFormWebpageOkButton						= By.xpath(".//div[@aria-hidden='false']//button[text()='OK']");
		public static By	txt_HistorySearchFilter									= By.xpath(".//input[@id='history-free-search']");
		public static By	ele_PopRoleHistoryAssignedFirstRoleRemarksLabel			= By.xpath(".//div[@id='historyMainDiv' and contains(@style,'block')]//div[@id and @style='display: block;']//div[@class='remarks' and text()]");
		public static By	ele_PopRoleHistoryAssignedFirstUser						= By.xpath(".//div[@id='historyMainDiv' and contains(@style,'block')]//div[@id and @style='display: block;']//span[@class='userName' and text()]");
		public static By	ele_PopRoleHistoryFirstRoleCurrentDateTime				= By.xpath(".//div[@id='historyMainDiv' and contains(@style,'block')]//div[@id and @style='display: block;']//span[@class='time row-fluid' and text()]");
		public static By	ele_PopRoleHistoryRemovedFirstRoleRemarksLabel			= By.xpath(".//div[@id='historyMainDiv' and contains(@style,'block')]//div[@id and @style='display: block;']//div[@class='remarks' and not(contains(text(),'ON'))]");
		public static By	btn_PopRolePrivilegesCancelButton						= By.xpath(".//button[@id='close-role-privileges' and contains(text(),'Cancel')]");
		public static By	css_RoleFormAccessPermissionLabelList					= By.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'all-suggested-projects')]//li//label[@title]");
		public static By	txt_PopManageRolesSearchUserInput						= By.xpath(".//div[contains(@style,'display: block')]//input[@id='manageRolesUserSearch']");
		/*public static By	chk_FormPermissionCheckRolePrivileges					= By.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='Check Role Privileges']//input");*/

		/* Invite Users */
		public static By	txt_PopInviteUsersFirstNameInput						= By.xpath(".//table[@id='inviteUsersTable']//input[contains(@class,'inviteUsersFirstName')]");
		public static By	txt_PopInviteUsersLastNameInput							= By.xpath(".//table[@id='inviteUsersTable']//input[contains(@class,'inviteUsersLastName')]");
		public static By	txt_PopInviteUsersEmailInput							= By.xpath(".//table[@id='inviteUsersTable']//input[contains(@class,'inviteUsersEmail')]");
		public static By	txt_PopInviteUsersRolesInput							= By.xpath(".//table[@id='inviteUsersTable']//ul[@class='select2-choices']//li[@class='select2-search-field']//input");
		public static By	txt_PopInviteUsersCustomMessageInput					= By.xpath(".//div[contains(@class,'nicEdit-main')]");
		public static By	btn_PopInviteUsersInviteButton							= By.xpath(".//div[@aria-hidden='false']//button[contains(text(),'Invite')]");
		public static By	msg_InviteUsersSuccessfulMessage						= By.xpath(".//div[@id='adoddleTopMsgBox' and not(contains(@style,'none'))]");
		public static By	btn_PopInviteUsersCloseButton							= By.xpath(".//section[@id='invitedUsersStatusSection']//button[text()='x']");
		public static By	css_PopInviteUsersStatusCancelLinkList					= By.xpath(".//table[@id='invitedStatusTable']//tbody//td[9]//a[text()='Cancel']");
		/*
		public static By	ele_PopInviteUsersFirstStatusValue						= By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[1]//td[1]");
		public static By	ele_PopInviteUsersFirstInvitedUserName					= By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[1]//td[2]");
		public static By	ele_PopInviteUsersSecondStatusValue						= By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[2]//td[1]");
		public static By	lnk_PopInviteUsersFirstStatusCancelLink					= By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[1]//td[9]//a[text()]");
		public static By	lnk_PopInviteUsersFirstStatusResendLink					= By.xpath(".//table[@id='invitedStatusTable']//tbody//tr[1]//td[10]//a[text()]");
		*/

		/* Workflow Cloned Project */
		public static By	lnk_ProjectsTemplates									= By.cssSelector("div[class*='container-fluid row-fluid'] div[class*='sidebar-nav left'] ul a[id='leftNavTemplates']");
		public static By	ele_ProjectsTemplatesName								= By.cssSelector("div[id='adTableBody'] div[class*='rows'] div[class*='col-projectName-fixed-width']");
		public static By	sel_CloneProject										= By.cssSelector("ul[class='context-menu-list context-menu-root'] li[class='context-menu-item icon CMicon-cloneproject'] span");
		public static By	lbl_CloneProject										= By.cssSelector("form[id='createCProject'] div[class='modal-header'] h3");
		public static By	txt_CloneProjectName									= By.cssSelector("form[id='createCProject'] div[class='modal-body'] div[class='projectform'] input[id='cinputproname']");
		public static By	txt_CloneProjectClientName								= By.cssSelector("div[id='caddProjectSelectClientName'] ul[class='select2-choices'] input[class='select2-input']");
		public static By	sel_CloneProjectClientNameFirstResult					= By.cssSelector("div[class*='select2-drop'][style*='display: block'] ul[class='select2-results'] li:nth-child(1) div");
		public static By	txt_CloneProjectSubmit									= By.cssSelector("form[id='createCProject'] div[class='modal-footer'] input[id='cprojectAddSubmit']");
		/*
		public static By	drp_PopEditProjectStatusDropdown						= By.cssSelector("select[id='editselectStatus']");
		public static By	btn_PopEditProjectSaveButton							= By.cssSelector("div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] div[class='modal-footer'] input[value='Save']");
		*/

		/* Working Calendar */
		public static By	txt_PopEditProjectAdditionalHolidayInput				= By.xpath(".//div[contains(@style,'display: block')]//input[@id='calendar_date'][@placeholder]");
		public static By	txt_PopEditProjectHolidayEvent							= By.xpath(".//div[contains(@style,'display: block')]//input[@id='holiday_name']");
		public static By	css_PopWorkingCalendarWorkingDaysList					= By.xpath(".//div[contains(@style,'display: block')]//div[@class='_calDays'][text()]");
		public static By	ele_PopWorkingCalendarAdditionalHolidayDate				= By.xpath(".//div[contains(@style,'display: block')]//table[@id='holidaylistgroup']//td[@class='cellBody'][2]");
		public static By	ele_PopWorkingCalendarHolidayEvent						= By.xpath(".//div[contains(@style,'display: block')]//table[@id='holidaylistgroup']//td[@class='cellBody'][3]");
		public static By	css_PopEditProjectCancelHolidayImageList				= By.xpath(".//div[contains(@style,'display: block')]//table[@id='holiday_list']//tbody//tr//img[contains(@src,'cancel.png')]");
		public static By    ele_InviteUserStatusHeaderPanel							= By.xpath(".//table[@id='invitedStatusTable']//thead//tr//td[text()]");
	}
}
