package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleClassicLocators {
	
	public static class ClassicLocators {
		
		/* Classic Locators */
		public static By	frm_AsiteWorkingFrame										= By.cssSelector("frameset[id='defaultFramesetApp'] frame[id='AsiWorkingFrame']");
		public static By	frm_AsiteHeaderFrame										= By.cssSelector("frame[name='header']");
		public static By	frm_AsiteMainFrame											= By.cssSelector("frame[name='asite_main']");
		public static By	tab_Training												= By.cssSelector("div[id='wrapper'] div[id='navigation'] div[id='tabsBar'] ul[id='ulPageTabs'] a[onclick*='dmstrainingak.asite.com']");
		public static By	frm_AsiteNoResize											= By.cssSelector("frame[noresize='noresize']");
		public static By	frm_WorkingFrame											= By.xpath(".//frameset//frame[@name='WorkingFrame']");
		public static By	lnk_MyHome													= By.xpath(".//div[@id='topMenu']//div[@class='topMenuLinks']//a[@title='My Home']");
		public static By	frm_AsiteWorkspaceFrame										= By.xpath(".//frameset//frame[@id='workspace']");
		public static By	lnk_SettingsMenu											= By.xpath(".//a[text()='Settings']");
		public static By	lnk_AdoddleView												= By.xpath(".//div[@id='topDropMenu']//a[text()='Adoddle View']");
		/*
		public static By	tab_CreateCustomAttributesSet								= By.cssSelector("div[id='pageWrapper'] td[id='attributeSetsTab']");
		public static By	lnk_EditCustomAttributesSetFirstEditLink					= By.cssSelector("div[id='projectCustomAttributeList'] form[name='myForm'] tr[class*='trbg']:nth-child(2) a");
		public static By	frm_CreateCustomAttributeFrame								= By.cssSelector("iframe[id='iFrmCreateCustomAttribute']");
		public static By	btn_CreateCustomAttributeSetSave							= By.cssSelector("div[id='pageWrapper'] form[name='createAttribSetForm'] input[id='btnSave']");
		public static By	css_CreateCustomAttributeSetFolderList						= By.cssSelector("div[id='divSelectFolderTree'] div[id='displayFolderTree'] div[id*='sublevelSrc'] a td[nowrap='nowrap'] a");*/
		
		/* Schedule Report */
		public static By	lbl_SelectUserDropdownFirstOption							= By.xpath(".//select[@id='selectUser']//option[1]");
		public static By	drp_ReportsDropdown											= By.xpath(".//select[@id='reportsListDD']");
		public static By	ele_ExpressReportFolder										= By.xpath(".//div[contains(@id,'ReportNameCtrl_ReportsCtrl_ReportsTree_TreeContent')]//div[@class='wrTrNd wrTrFolder wrTrLf' and not(contains(@style,'display: none'))]//span[contains(text(),'Reports')]");
		public static By	ele_ExpressReportLayoutField								= By.xpath(".//div[contains(@id,'ReportLayoutSplitter')]//span[text()='LastLogin']");
		public static By	img_ReportsTabFirstReportEditAndSchedule					= By.xpath(".//div[@id='tabDetails']//tr[2]//img[@alt='Edit And Schedule']");
		public static By	btn_EditAndScheduleReportPreviewButton						= By.xpath(".//input[@id='showReport'][@value='Preview']");
		public static By	css_DocListingDocRefList									= By.cssSelector("div[id='wrapper'] td[class='userSearchListing'] table[id='docListTable'] tr[class*='trbg'][valign='top'] td[style*='word-break'] a[title*='Click here to view Document']");
		public static By	lbl_SelectOrganisationDropdownFirstOption					= By.xpath(".//select[@id='selectOrganisation']//option[1]");
		public static By	tab_Reporting												= By.xpath(".//ul[@id='ulPageTabs']//span[contains(text(),'Reporting')]");
		public static By	lbl_ReportsPage												= By.xpath(".//table[@id='table21']//strong[text()='Reports']");
		public static By	img_ReportsPageCreateReportImageIcon						= By.xpath(".//a[@title='Create Report']//img");
		public static By	lbl_CreateReportPageTitle									= By.xpath(".//table//td[@class='title' and text()]");
		public static By	txt_SearchReportTemplate									= By.xpath(".//input[@id='reportTemplate']");
		public static By	btn_ReportsPageSearchButton									= By.xpath(".//table[@id='searchContents']//input[@value='Search']");
		public static By	sel_ReportTempleteRadioButton								= By.xpath(".//tr[contains(@class,'trbg1')]//input[@type='radio' and @name='report_id']");
		public static By	btn_ReportsPageCreateReportButton							= By.xpath(".//input[@id='createReport' and @value='Create Report']");
		public static By	txt_ReportNameInput											= By.xpath(".//div[@id='reportBasicInfo']//input[@id='report_title']");
		public static By	drp_EditReportSelectWorkspaceDropdown						= By.xpath(".//select[contains(@id,'project')]");
		public static By	drp_EditReportSelectWorkspaceDropdownListOption				= By.xpath(".//select[contains(@id,'project')]//option[2]");
		public static By	drp_EditReportFrequencyDropdown								= By.xpath(".//select[contains(@id,'Frequency')]");
		public static By	sel_FirstDateOfMonthRadioButton								= By.xpath(".//div[@id='divMonthly' and contains(@style,'block')]//input[@id='FMonthlyDay']");
		public static By	drp_EditReportFrequencyHourDropdown							= By.xpath(".//select[@id='frequencyHour']");
		public static By	drp_EditReportFrequencyMinuteDropdown						= By.xpath(".//select[@id='frequencyMinute']");
		public static By	drp_DeliveryScheduleAreaSelectWorkspaceDropdown				= By.xpath(".//select[@id='selectProjectDD']");
		public static By	drp_DeliveryScheduleAreaSelectWorkspaceDropdownListOption	= By.xpath(".//select[@id='selectProjectDD']//option[2]");
		public static By	ele_EditReportFolderTreeStructure							= By.xpath(".//div[@id='folderTree']//div[contains(@id,'sublevel') and not(contains(@style,'display:none'))]");
		public static By	lnk_FolderTreeStructureReportsFolder						= By.xpath(".//a[contains(text(),'Reports')]");
		public static By	btn_FolderTreeStructureSelectFolderButton					= By.xpath(".//input[@id='submit' and @value='Select Folder']");
		public static By	lbl_EditReportDocumentDetails								= By.xpath(".//table[@id='enterdetails']//td[@class='body1bold' and text()]");
		public static By	drp_EditReportPOIdropdown									= By.xpath(".//select[@id='issuepurpose']");
		public static By	drp_EditReportStatusDropdown								= By.xpath(".//select[@id='filestatus']");
		public static By	btn_EditReportNextButton									= By.xpath(".//input[@class='btn' and @value='Next']");
		public static By	lbl_EditReportDistribute									= By.xpath(".//table[@id='tblFolderDistribution']//td[@class='body1bold' and text()]");
		public static By	drp_SelectOrganisationDropdown								= By.xpath(".//select[@id='selectOrganisation']");
		public static By	drp_SelectUserDropdown										= By.xpath(".//select[@id='selectUser']");
		public static By	btn_AddToDistributionListButton								= By.xpath(".//table[@id='tblFolderDistribution']//input[@value='Add to Distribution List']");
		public static By	drp_EditReportActionRequiredDropdown						= By.xpath(".//table[@id='distUserTbl']//select[@name='actionReq']");
		public static By	drp_EditReportActionDaysDropdown							= By.xpath(".//table[@id='distUserTbl']//select[@name='actionDays']");
		public static By	btn_EditReportDistributeButton								= By.xpath(".//table[@id='emptyDistTable']//tr[3]//input[@value='Distribute']");
		public static By	lbl_EditReportRecipientsArea								= By.xpath(".//div[@id='reportrecipients']//strong[text()]");
		public static By	btn_EditReportSaveButton									= By.xpath(".//input[@id='saveCriteria' and @value='Save']");
		public static By	chk_EditReportSendNowCheckbox								= By.xpath(".//input[@id='chkSendNow' and @type='checkbox']");
		public static By	btn_EditReportSaveAndCloseButton							= By.xpath(".//input[@id='saveAndCloseCriteria' and @value='Save & Close']");
		public static By	txt_ReportsNameInput										= By.xpath(".//input[@id='reportName']");
		public static By	lnk_ReportsPageFirstReport									= By.xpath(".//div[@id='tabDetails']//tr[2]/td[2]/a[text()]");
		public static By	lnk_DocListingFirstFileDocRef								= By.xpath(".//table[@id='docListTable']//tr[2]//a[contains(@title,'Click here to view Document')]");
		public static By	lnk_DocListingFirstFileAction								= By.xpath(".//table[@id='docListTable']//tr[2]//tr[2]//a[contains(@title,'action') and text()]");
		public static By	ele_DocListingFirstFileActionDays							= By.xpath(".//table[@id='docListTable']//tr[2]//tr[2]//font[text()]");
		public static By	frm_FramesetListingController								= By.xpath(".//frameset//frame[@src='/expo/ListingController?action_id=5']");
		public static By	drp_AdminDropdown											= By.cssSelector("body[class='headerBar'] td[class='headerBar'] select[id='projAdminDD']");
		
		/* Cloned-Inheritance Workflow */
		
		public static By	frm_WorkspaceFrame											= By.cssSelector("frame[src*='/dmsa/HomeController']");
		public static By	tab_WorkSpace												= By.cssSelector("div[id='wrapper'] div[id='navigation'] div[id='tabsBar'] ul[id='ulPageTabs'] a[onclick*='action_id=3']");
		public static By	lbl_WorkspaceHeaderName										= By.cssSelector("body[class='headerBar'] tr[class='headerMain'] span[id='projectName']");
		public static By	frm_PmFrame													= By.cssSelector("frame[id='pmfrm']");
		public static By	existingTemplateList										= By.cssSelector("table[id='tableTemplateList'] tr[class*='trbg']");
		public static By	templateClonedLink											= By.cssSelector("img[src*='clone_project.gif'][title*='Clone Workspace']");
		public static By	lbl_WorkspaceTemplatesTab									= By.cssSelector("div[id='proj_list'] td[id='tabTemplete']");
		public static By	lbl_ALlWorkspacesTab										= By.cssSelector("div[id='proj_list'] td[id='tabAll']");
		public static By	lnk_EditWorkspaceSaveWorkspaceAsTemplate					= By.cssSelector("div[id='projectBasicDetailsTable'] a[title='save workspace as template']");
		public static By	img_ManageStatusAddDocumentStatusIcon						= By.cssSelector("form[name='frmWorkspaceRolesUsers'] td[id='iconTD'] img[title='Add Document Status']");
		public static By	img_ManageStatusPoiIcon										= By.cssSelector("td[id='iconTD'] img[src*='/images/i_addsettings.gif']");
		public static By	img_ManagePoiAddPoiIcon										= By.cssSelector("td[id='iconTD'] img[src*='/images/i_addsettings.gif'][title='Add Purpose of Issue']");
		public static By	txt_ManageStatusDocumentStatus								= By.cssSelector("form[name='frmCreatedocumentStatus'] input[name='documentStatusName']");
		public static By	txt_ManageStatusAbbreviationStatus							= By.cssSelector("form[name='frmCreatedocumentStatus'] input[name='documentStatusAbbreviation']");
		public static By	img_ManageStatusAdminCheckMark								= By.cssSelector("form[name='frmCreatedocumentStatus'] table[id='tblPermissionMatrix'] :nth-child(5) img[src*='images/tick_light_gray.gif']");
		public static By	btn_ManageStatusSubmit										= By.cssSelector("form[name='frmCreatedocumentStatus'] input[class='btn'][value='Submit']");
		public static By	txt_ManageStatusPoi											= By.cssSelector("form[name='frmCreatePOI'] table[align='center'] input[name='poiName']");
		public static By	txt_ManagePoiAbbreviationPoi								= By.cssSelector("form[name='frmCreatePOI'] table[align='center'] input[name='poiAbbreviation']");
		public static By	img_ManagePoiAdminCheckMark									= By.cssSelector("form[name='frmCreatePOI'] table[id='tblPermissionMatrix'] :nth-child(5) img[src*='images/tick_light_gray.gif']");
		public static By	btn_ManagePoiSubmit											= By.cssSelector("form[name='frmCreatePOI'] input[class='btn'][value='Submit']");
		public static By	txt_CreateEditWorkspaceNameInput							= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] input[id='projectName']");
		public static By	drp_CreateEditWorkspaceClient								= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='hashedOwnerOrgID']");
		public static By	drp_CreateEditWorkspaceGeography							= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='dataCenterId']");
		public static By	lnk_CreateEditWorkspaceShowAdvanced							= By.cssSelector("div[id='basicProjInfoTab'] div[id='divSpaceObjects'] a[id='lnkShowAdvanced']");
		public static By	chk_CreateWorkspaceInheritChanges							= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] input[id='chkWorkspaceInheritance']");
		public static By	lnk_CreateEditWorkspaceSave									= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicSaveCancel'] a[title='Save changes to this section']");
		public static By	lnk_CreateEditWorkspaceTemplateStatus						= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='statusID']");
		public static By	lnk_WorkspaceMyHome											= By.cssSelector("div[id='topMenu'] a[title='My Home']");
		public static By	lnk_WorkspaceHome											= By.cssSelector("div[id='topMenu'] a[title='Workspace Home']");
		public static By	lnk_WorkspaceTemplateName									= By.cssSelector("td:nth-child(1) a");
		public static By	lnk_WorkspaceSettings										= By.cssSelector("div[id='topMenu'] a[id='lnkSettings']");
		/*
		public static By	lnk_ManageStatusDocumentStatusList							= By.cssSelector("form[name='frmWorkspaceRolesUsers'] table[id='tblListingTop'] tr[class*='trbg'] a");
		public static By	lnk_ManagePoiList											= By.cssSelector("div[style*='position:absolute'] table[id='tblListingTop'] tr[class*='trbg'] a");
		public static By	existingWorkspaceList										= By.xpath(".//table[@id='tableTemplateList']//tr[contains(@class,'trbg')]//td//a[text()]");*/

		/* Cloned-Inheritance Workspace Objects */
		
		public static By	txt_ManageUserRoleAddNewRole								= By.cssSelector("td[id='tabDetailsContainer'] input[id='btnAddRole']");
		public static By	txt_ManageUserRoleWorkspaceRoleName							= By.cssSelector("table[id='tblPermissionMatrix'] input[id*='txtNewRole']");
		public static By	txt_ManageUserRoleSearchEmailField							= By.cssSelector("td[id='tdSearchUserEmail'] input[id='txtSearchCriteriaEmail']");
		public static By	btn_ManageUserRoleSearch									= By.cssSelector("td[id='tabDetailsContainer'] input[id='permissionMatrixSearchBtn']");
		public static By	css_ManageUserRoleSearchEmailResult							= By.cssSelector("table[id='tblUsersDetails']:nth-child(2) div");
		public static By	chk_UserRoleSearchEmailResultCheckbox						= By.cssSelector("table[id='tblUsersDetails']:nth-child(2) input[type='checkbox']");
		public static By	btn_ManageUserRoleAddSelected								= By.cssSelector("table[id='tblPermissionMatrix'] input[id*='btnPermission_-1']");
		public static By	css_ManageUserRoleExistingUserRoleList						= By.cssSelector("table[id='tblPermissionMatrix'] tr[class*='trbg']");
		public static By	txt_ExistingUserRoleListRoleName							= By.cssSelector("td[id*='tdRole'] input");
		public static By	btn_ExistingUserRoleListAddSelected							= By.cssSelector("input[id*='btnPermission']");
		public static By	btn_ManageUserRoleSaveButton								= By.cssSelector("div[id='divWrappers'] tr[id='trRolesTab'] input[name='btnSubmit']");
		public static By	sel_CreateParentFolderOrg									= By.cssSelector("form[name='createFolder'] td[class='subtitle'] select[id='org']");
		public static By	sel_CreateParentFolderAvailableUsers						= By.cssSelector("form[name='createFolder'] td[class='subtitle'] select[id='usersa']");
		public static By	btn_CreateParentFolderAddUserSelect							= By.cssSelector("form[name='createFolder'] td[class='subtitle'] input[id='SubUser']");
		public static By	drp_EditFolderUserRolePermissionDropdown					= By.cssSelector("div[id='selectedUsers'] table[align*='center'] tr:nth-child(2) td:nth-child(3) select");
		public static By	css_CreateParentFolderUsersList								= By.cssSelector("div[id='selectedUsers'] table[align*='center'] tr");
		public static By	css_CreateParentFolderListOrgUser							= By.cssSelector("td:nth-child(2)");
		public static By	sel_CreateParentFolderListOrgUserPermission					= By.cssSelector("td:nth-child(3) select");
		public static By	css_WorkspaceAppTemplateList								= By.cssSelector("form[name='formTypeList'] tr[class*='trbg']:nth-child(3)");
		public static By	css_WorkspaceAppTemplateListAppName							= By.cssSelector("td:nth-child(4)");
		public static By	chk_WorkspaceAppTemplate									= By.cssSelector("input[name='selectedFormType']");
		public static By	btn_CopySelectedFormTemplatesToWorkspace					= By.cssSelector("form[name='formTypeList'] input[value='Copy Selected Form Templates to workspace']");
		public static By	css_ManageWorkspaceAppsPageTitle							= By.cssSelector("table[align='center'] td[class='title'] font:nth-child(2)");
		public static By	chk_ManageWorkspaceAppsCheckboxes							= By.cssSelector("form[name='formTypeList'] tr[class*='trbg'] input[type='checkbox']");
		public static By	btn_ManageWorkspaceAppsSubmit								= By.cssSelector("form[name='formTypeList'] input[value='Submit']");
		public static By	css_ActiveWorkspaceAppTemplateList							= By.cssSelector("form[name='formTypeList'] tr[class*='trbg']");
		public static By	chk_ActiveWorkspaceAppTemplateCheckBox						= By.cssSelector("td:nth-child(1) input");
		public static By	css_ActiveWorkspaceAppTemplateName							= By.cssSelector("td:nth-child(5)");
		public static By	chk_ActiveWorkspaceAppTemplateEditIcon						= By.cssSelector("td:nth-child(7) img[src*='i_editfgt.gif']");
		public static By	css_EditWorkspaceInstancePageTitle							= By.cssSelector("table[align='center'] td[class='title'] span[id='formDetailType']");
		public static By	txt_EditWorkspaceInstanceFormName							= By.cssSelector("table[align='center'] tr[valign='middle'] input[name='FT_NAME']");
		public static By	txt_EditWorkspaceInstanceGroupCode							= By.cssSelector("table[align='center'] tr[valign='middle'] input[name='FT_CODE']");
		public static By	txt_EditWorkspaceInstanceFormGroupName						= By.cssSelector("table[align='center'] tr[valign='middle'] input[name='form_group_name']");
		public static By	txt_EditWorkspaceInstanceFormType							= By.cssSelector("table[align='center'] tr[valign='middle'] input[name='FT_TEMPLATE_TYPE'][value='1']");
		public static By	rad_EditFormSettingsResponseAllowedNo						= By.cssSelector("table[align='center'] tr[valign='middle'] input[name='FT_RESPONSE_ALLOWED'][value='false']");
		public static By	rad_EditFormSettingsAllowDistributionYes					= By.cssSelector("table[align='center'] tr[id='trDistributingAfterCreation'] td:nth-child(1) input[name*='ALLOW_DISTRIBUTING_AFTER_CREATION']");
		public static By	rad_EditFormSettingsAllowAttachmentYes						= By.cssSelector("table[align='center'] tr[valign='middle'] td:nth-child(1) input[name*='ALLOW_ATTACHMENTS']");
		public static By	css_EditFormSettingsFormActionslist							= By.cssSelector("table[align='center'] tr[valign='middle'] table[width='auto'] tr");
		public static By	chk_EditFormSettingsFormActionCheckbox						= By.cssSelector("input[name='FT_ASSOC_ACTION']");
		public static By	sel_EditFormSettingsFormActionDueDays						= By.cssSelector("select[name*='FT_DEFAULT_ACTION_TIME']");
		public static By	css_TabRoleFormPermission									= By.cssSelector("tr[id='trRolesTab'] :nth-child(3) strong");
		public static By	css_RoleFormPermissionSearchField							= By.cssSelector("td[class='tabPane'] table[id='tblFreezeColumn'] input[id='txtSearchSpaceRole']");
		public static By	btn_RoleFormPermissionSearch								= By.cssSelector("td[class='tabPane'] table[id='tblFreezeColumn'] input[name='btnSearch']");
		public static By	btn_RoleFormPermissionList									= By.cssSelector("table[id='tblFreezeColumn'] tbody td[isformprivs='true']");
		public static By	chk_RoleFormPermissionChekbox								= By.cssSelector("input[id*='chkFormPriv']");
		public static By	btn_RoleFormPermissionSave									= By.cssSelector("tr[id='trPermissionDetails'] td[class='tabEmpty'] input[name='btnSubmit']");
		
	}
	
}