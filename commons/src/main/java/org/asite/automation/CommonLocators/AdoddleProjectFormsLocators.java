package org.asite.automation.CommonLocators;
import org.openqa.selenium.By;

public class AdoddleProjectFormsLocators {

	public static class ProjectFormsTab {

		public static By	ele_BetaViewFormViewLoading											= By.cssSelector("div[id='form-holder'][class*='loading']");
		public static By	btn_BetaViewViewFormMaximizeTab										= By.xpath(".//div[@id='form-view-page']//main[@class='open']//button[@title='Maximize']");

		/* Form Mailbox */
		public static By	lbl_ProjectFormListingCount											= By.cssSelector("div[id='docListingSection'] div[id='commuDoclist']");
		public static By	btn_BetaViewFormDetailsExportButton									= By.xpath(".//div[@id='form-view-page']//div[@id='form-holder']//div[@class='action-btn-container']//button[contains(text(),'Export')]");
		public static By	ele_CreateFormToggleActions											= By.cssSelector("ul[class*='context-menu-root'][style*='z-index']");
		public static By	ele_CreateFormYourResponse											= By.xpath(".//div[@align='center']//span[contains(text(),'Your Response')]");
		public static By	lbl_FormsIncompleteActionsCount										= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-comms-incomplete'] span");
		public static By	lbl_FormsOverdueActionsCount										= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-comms-overdue'] span");
		public static By	lbl_FormsDueTodayActionsCount										= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-duetoday'] span");
		public static By	txt_CreateFormSubject												= By.cssSelector("div[style*='MARGIN-BOTTOM']:nth-child(4) table[class='xdLayout'] tbody tr:nth-child(2) textarea[class='xdTextBox']");
		public static By	txt_CreateFormDescription											= By.cssSelector("div[style*='MARGIN-BOTTOM']:nth-child(4) table[class='xdLayout'] tbody tr:nth-child(4) textarea[class='xdTextBox']");
		public static By	btn_BetaViewCreateFormAttachment									= By.cssSelector("span[class*='dropdown '] button[title='Attachment']");
		public static By	btn_BetaViewCreateFormAttachFileSave								= By.cssSelector("div[class='upload-bottom'] button[class='btn btn-danger pull-left']");
		/*
		public static By	btn_CreateFormAttachmentsSelectFiles								= By.cssSelector("div[id='myModal-Attachment'] form[id='data_CommentForm'] span[style*='margin-top'] input");
		public static By	btn_CreateFormAttachFileSave										= By.cssSelector("div[id='myModal-Attachment'] form[id='data_CommentForm'] button[class='btn btn-danger pull-left']");
		public static By	drp_ProjectFormAppsDropdown											= By.xpath(".//select[@id='fileActiveListType']");
		public static By	btn_FormDetailsExportButton											= By.xpath(".//ul//li[@listfilterkey='exportType']//button[@id='header_export'][text()='Export']");
		public static By	lnk_FormDetailsExportAllMessage										= By.cssSelector("div[id='viewFormHeaderTable'] li[listfilterkey='exportType'] ul[class*='filterui-list-section'] li[id='printAllMsg'] a");
		public static By	txt_FormInputToField												= By.cssSelector("input[class*='dist-']");
		public static By	txt_CreateForm														= By.cssSelector("form[id='myform'] div[id='custFormTD'] div[id='xdoc_view']");
		public static By	btn_CreateFormAttachment											= By.cssSelector("div[id='headerCreateFormDiv'] div[id='FormMoreOption'] button[class*='showAttachmentLink']");
		public static By	btn_CreateFormSelectFileSave										= By.xpath(".//div[@id='docListingSection']//div[@id='assocFilesselectbutton']//button[@id='save']");
		public static By	drp_CreateFormDistributionUserAction								= By.cssSelector("div[id='divDistributeFiles_form'] button[class='btn dropdown-toggle']");
		public static By	lbl_PopFilesActionForAcknowledgementHeader							= By.cssSelector("div[id='myModal-actionforacknowledgment'][style*='margin-top'] h3");
		*/

		/* IDP Form */
		public static By	lnk_FormDetailsCoBieIR												= By.cssSelector("div[id='tbl-header'] a[id='cobieIR']");
		public static By	lnk_ClassicWorkspaceTemplate										= By.cssSelector("div[id='content-wrapper'] ul[class='tabs ui-tabs'] li[id='templateWorkspace'] a");
		public static By	lnk_ClassicManageAppsFirstEdit										= By.cssSelector("div[id='main'] td[id='ProjectFormsList'] form[action='FormsAdminController'] tr[class='trbg2']:nth-child(2) a[onclick*='editProjectFormType'] img[src*='i_editfgt.gif']");
		public static By	btn_ClassicEditFormSettingsBrowse									= By.cssSelector("form[name='formUploadTemplate'] input[id='txtinfopath']");
		public static By	btn_ClassicEditFormSettingsSubmit									= By.cssSelector("div[id='main'] td[id='ProjectFormsList'] form[action='FormsAdminController'] input[id='bnsubmit']");
		public static By	frm_FormUploadTemplate												= By.cssSelector("iframe[id='frmUploadTemplate']");
		public static By	btn_ClassicEditFormSettingsUpload									= By.cssSelector("input[name='btnPreview'][value='Upload']");
		public static By	btn_UploadFormTemplateSuccessFullOK									= By.cssSelector("input[name='btnOk']");
		public static By	lbl_FormsListingFirstStatus											= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-status-fixed-width']");
		public static By	rad_RespondCommentingFormSecondPackageRecommendation				= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] table[class='cmntngFrmDetails'] input:nth-child(2)");
		public static By	lbl_FormsFirstAction												= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName']");
		public static By	frm_createFormIframe												= By.cssSelector("iframe[id='createFormIframe']");
		public static By	frm_replyFormIframe													= By.cssSelector("iframe[id='reply-frame']");
		public static By	lnk_EmbeddedEmailCreateForm											= By.cssSelector("div[class='tree-children'] div[id*='ws_']:nth-child(1) div[class*='tree-children'] a[title='Request For Information'] span[class='tree-label']");
		public static By	txt_FormListingFormSearchInput										= By.cssSelector("div[id='docListingSection'] input[id='commu_fd_containText']");
		public static By	txt_BetaViewCreateRFIFormInputToFieldCount							= By.cssSelector("div[id='distInputTo'] div[class='dropdown-toggle'] li");
		public static By	txt_CreateRFIFormInputToField										= By.cssSelector("div[id='divDistributeFiles_form'] div[id='s2id_inptDistTo1']  input[id='s2id_autogen1']");
		public static By	txt_BetaViewCreateRFIFormInputToField								= By.cssSelector("div[id='distInputTo'] div[class='dropdown-toggle'] input[class*='dist-inpt']");
		public static By	chk_BetaViewCreateRFIFormInputUserCheckbox							= By.cssSelector("div[id='distInputTo'] input[type='checkbox']");
		public static By	btn_BetaViewCreateRFIFormAttachment									= By.cssSelector("div[id='create-form-page'] button[title='Attachment']");
		public static By	btn_BetaViewCreateRFIFormAttachmentsSelectFiles						= By.cssSelector("div[class*='upload-attach-form'] div[class='upload-bottom'] input[id='inptFileUpload']");
		public static By	btn_BetaViewCreateRFIFormAttachmentsAttach							= By.cssSelector("div[class*='upload-attach-form'] div[class='upload-bottom'] button[class='btn btn-danger pull-left']");
		public static By	btn_BetaViewCreateRFIFormAttachmentsClose							= By.cssSelector("div[id='create-form-page'] div[class*='upload-attach-form'] div[class='upload-bottom'] button[ng-click*='closeUpload()']");
		public static By	btn_CreateRFIFormSave												= By.cssSelector("div[id='formParentDiv'] div[id='btnsaveCancelForm'] button[id='btnSaveForm']");
		public static By	btn_BetaViewCreateRFIFormSave										= By.cssSelector("div[id='create-form-page'] div[id='btnsaveCancelForm'] button[id='btnSaveForm']");
		public static By	lbl_BetaViewFormDetailsHeader										= By.cssSelector("div[class*='main-header'] h1");
		public static By	lbl_BetaViewFormDetailsFormName										= By.cssSelector("div[id='form-view-page'] div[id='xdoc_view'] span[id='InfoJetExprBox6']");
		public static By	btn_FormDetailsActionDropdownButton									= By.cssSelector("div[class*='dropdown'] button[id='form-action-btn']");
		public static By	btn_BetaViewFormDetailsActionDropdownButton							= By.cssSelector("div[id='form-view-page'] div[class='thread-header'] button[id='form-action-btn']");
		public static By	lnk_BetaViewFormDetailsActionDistribute								= By.cssSelector("li[class*='ng-scope'] a[ng-click='distribute()']");
		public static By	lnk_BetaViewFormDetailsActionReply									= By.cssSelector("ul[class*='dropdown-menu-right'] li[ng-if*='canReplyAll'] a");
		public static By	lnk_FormDetailsActionEditDraft										= By.cssSelector("ul[class*='export-dropdown']:nth-child(1) li[class='check-list-item']:nth-child(1) a");
		public static By	lnk_BetaViewFormDetailsActionEditDraft								= By.cssSelector("div[id='form-view-page'] ul[class*='dropdown-menu'] a[ng-click*='editDraft']");
		public static By	lnk_BetaViewFormViewDetailsActionEditDraft							= By.cssSelector("div[id='form-view-page'] a[ng-click*='editORI']");
		public static By	txt_PopUpDistributeToInput											= By.cssSelector("div[id='appsDistributionAction'] form[id='newAppsDistributionAction'] div[id='divAppsDistributionTo'] input[id*='s2id_autogen']");
		public static By	txt_PopDistributeToInput											= By.cssSelector("div[class='dist-component'] div[class='dropdown-toggle'] input");
		public static By	txt_BetaViewPopDistributeToInput									= By.cssSelector("div[id='form-view-page'] div[class*='dist-component'] input[class*='dist-inpt']");
		public static By	txt_BetaViewDistributeToInput										= By.cssSelector("div[class*='dist-component'] input[class*='dist-inpt']");
		public static By	chk_BetaViewSelectFirstDistributeUser1								= By.cssSelector("li[class='ng-scope']:nth-child(2) input[class*='ng-pristine ']");
		public static By	chk_SelectFirstDistributeUser										= By.cssSelector("div[class='ibox'] ul[class='user-list'] input[class*='ng-pristine'][type='checkbox']");
		public static By	chk_BetaViewPopDistributeToUserCheckbox								= By.cssSelector("div[id='form-view-page'] div[class*='dist-component'] input[type='checkbox']");
		public static By	txt_PopDistributeSubjectInput										= By.cssSelector("div[id='appsDistributionAction'] form[id='newAppsDistributionAction'] input[id='inptAppsDistActionSub']");
		public static By	txt_BetaViewPopDistributeSubjectInput								= By.cssSelector("div[id='form-view-page'] div[class='ibox'] input[name='subject']");
		public static By	txt_BetaViewDistributeSubjectInput									= By.cssSelector("div[class='form-group'] input[name='subject']");
		public static By	btn_PopUpDistributeButtonDistribute									= By.cssSelector("div[id='appsDistributionAction'] button[id='filesDistributeValidationContinue']");
		public static By	btn_PopDistributeButtonDistribute									= By.cssSelector("div[class*='dist-form'] div[class='form-group'] button[ng-click='submitDistribution()']");
		public static By	btn_BetaViewPopDistributeButtonDistribute							= By.cssSelector("div[id='form-view-page'] div[class='ibox'] div[class='form-group'] button[ng-click*='submitDistribution']");
		public static By	pop_BetaViewDistribution											= By.cssSelector("div[class*='dropdown-menu-right'][style*='right'] div[class='ibox']");
		public static By	css_CreateFormToggleActions											= By.cssSelector("div[id='distInputTo'] button[class='btn dropdown-toggle']");
		public static By	css_BetaViewCreateFormToggleActions									= By.cssSelector("div[id='distInputTo'] div[class='dropdown-toggle'] li strong");
		public static By	drp_CreateFormAssignActionToUser									= By.cssSelector("ul[class*='context-menu-root'] li[clasS*='CMicon-actions'] select[name='context-menu-input-actions']");
		public static By	drp_BetaViewCreateFormAssignActionToUser							= By.cssSelector("div[id='distInputTo'] div[class='item-detail'] div[class*='bottom-action'] select");
		public static By	btn_BetaViewFormDetailsViewPageFormDetails							= By.cssSelector("div[id='form-view-page'] button[title='Form Details']");
		public static By	ele_BetaViewFormDetailsViewPageFormReply							= By.cssSelector("div[id='form-view-page'] ul[class*='thread-list'] li:nth-child(2) a");
		public static By	btn_BetaViewEditDraftFormDistributeAttachment						= By.cssSelector("div[id='create-form-page'] span[is-open='form.isAttachOpen'] button[title='Attachment']");
		public static By	ele_BetaViewCreateFormHeaderDiv										= By.cssSelector("div[id='create-form-page'] div[class*='main-header'] h1 span");
		public static By	img_CreateControllerFormAngularDatePicker							= By.cssSelector("i[class*='ang-datepicker-icon']");
		public static By	img_CreateControllerFormDatePicker									= By.cssSelector("div[id='distInputTo'] div[class='dropdown-toggle'] i[class*='ang-datepicker-icon']");
		public static By	img_CreateControllerFormAngularActiveDate							= By.cssSelector("div[class*='datepicker-calendar-body'] a[class*='datepicker-active']");
		public static By	btn_CreateControllerFormSendButton									= By.cssSelector("div[id='formParentDiv'] button[id='btnSaveForm']");
		public static By	btn_BetaViewCreateControllerFormSendButton							= By.cssSelector("div[id='create-form-page'] button[id='btnSaveForm']");
		public static By	btn_CreateControllerFormAttachment									= By.cssSelector("div[id='formParentDiv'] div[id='headerCreateFormDiv'] button[class*='showAttachmentLink']");
		public static By	btn_BetaViewCreateControllerFormAttachment							= By.cssSelector("div[id='create-form-page'] button[title='Attachment']");
		public static By	ele_BetaViewControllerFormViewXDocView								= By.cssSelector("div[id='form-view-page'] div[id='xdoc_view']");
		public static By	btn_BetaViewControllerFormViewActionButton							= By.cssSelector("div[id='form-view-page'] button[id='form-action-btn']");
		public static By	pop_BetaViewCreateControllerFormAttachments							= By.cssSelector("div[id='create-form-page'] div[class*='upload-attach-form']");
		public static By	txt_CreateRFIFormSubject											= By.cssSelector("div[style*='MARGIN-BOTTOM']:nth-child(4) table[class='xdLayout'] tbody tr:nth-child(2) textarea[class='xdTextBox']");
		public static By	txt_CreateRFIFormDescription										= By.cssSelector("div[style*='MARGIN-BOTTOM']:nth-child(4) table[class='xdLayout'] tbody tr:nth-child(4) textarea[class='xdTextBox']");
		public static By	txt_CreateFormField													= By.cssSelector("form[id='myform'] div[id='custFormTD'] div[id='xdoc_view']");
		public static By	txt_CreateControllerFormTitle										= By.cssSelector("form[id='myform'] div[id='xdoc_view'] input[id*='ORI_FORMTITLE'][class='xdTextBox']");
		public static By	drp_CreateControllerFormTo											= By.cssSelector("form[id='myform'] div[id='xdoc_view'] select[id*='DS_PROJDISTUSERS']");
		public static By	txt_CreateControllerFormOnDate										= By.cssSelector("form[id='myform'] div[id='xdoc_view'] input[id*='Instruction_Date']");
		public static By	img_CreateControllerFormOnDateIcon									= By.cssSelector("form[id='myform'] div[id='custFormTD'] div[id='xdoc_view'] div[class='xdDTPicker'] a[class='xdDTButton'] img");
		public static By	lnk_CreateControllerFormOnDateCalenderToday							= By.cssSelector("div[class='calendar'] table[style='visibility: visible;'] tbody tr[class='daysrow'] td[class*='selected']");
		public static By	txt_CreateControllerRespondByDate									= By.cssSelector("form[id='myform'] div[id='rrbDiv'] input[id*='respondBy']");
		public static By	btn_BetaViewControllerFormViewActionsEditORI						= By.cssSelector("div[id='form-view-page'] a[ng-click*='editORI']");
		public static By	btn_CreateFormAttachmentSelectFiles									= By.cssSelector("input[type='file'][id='inptFileUpload']");
		public static By	lbl_BetaViewPopAttachFileControllerFormAttachedFile					= By.cssSelector("div[class='gbody'] ul[class='grow ng-scope'] li:nth-child(2)");
		public static By	ele_FormListingFirstFormActionDiv									= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName']");
		public static By	lnk_FormListingFirstFormAction										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName'] a");
		public static By	lnk_FormListingFirstFormTitle										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-title-fixed-width'] a");
		public static By	lnk_FormListingSecondFormTitle										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(2) div[class*='col-title-fixed-width'] a");
		public static By	css_FormsFirstAdditionalActions										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows'] div[class*='col-actions-actionName'] span");
		public static By	css_FormsFirstAdditionalActionsList									= By.cssSelector("div[class='popover-content'] a[class='completeAction'] :nth-child(1)");
		public static By	lnk_FormListingFirstFormID											= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-code-fixed-width'] a");
		public static By	txt_PopCreateFormSearchFormInput									= By.cssSelector("div[id='appSelectionWrapper'] div[id='appsListSection'] input[id='formTypeNameInp']");
		public static By	lbl_PopCreateFormFirstFormName										= By.cssSelector("div[id='appSelectionWrapper'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-formTypeName-fixed-width']");
		public static By	drp_CreateFormControllerDropdown									= By.cssSelector("form[id='myform'] select[id='controller_id']");
		public static By	img_ProjectFormsFirstFormAssociatedDiscussion						= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows'] div[class*='col-attachmentImageName-fixed-width'] img");
		public static By	lnk_FirstFormDiscussionLink											= By.cssSelector("div[class*='popover-content'] div[id='Discussions'] a");
		public static By	lnk_FirstFormAttachmentsLink										= By.cssSelector("div[class*='popover-content'] div[id='Attachments'] a");
		public static By	pop_AttachmentAndAssociationPopup									= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;']");
		public static By	pop_AttachmentAndAssociationCancel									= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] button[class='close']");
		public static By	lbl_CreateFormHeader												= By.cssSelector("div[id='appSelectionHeader'] h2");
		public static By	btn_BetaViewPopFormStatusChangeChangeStatus							= By.xpath(".//button[@id='btn-status-change'][text()='Save']");
		public static By	css_ProjectFormsTabFormIDList										= By.xpath(".//div[@index]//div[contains(@class,'code')]//a[text()]");
		public static By	btn_SearchCreateAppsFilterDropdown									= By.xpath(".//div[@id='appsmoreFilterCriteria']//button[@filterkey='more']");
		public static By	lbl_RequestForInformationFormTitle									= By.cssSelector("div[id*='myFields'] :nth-child(2) table[class='xdLayout'] tr[style*='MIN-HEIGHT:']:nth-child(1) font");
		public static By	lbl_RFIMobileFormTitle												= By.cssSelector("div[id*='myFields'] table[class*='xdFormLayout '] tr[style*='MIN-HEIGHT:']:nth-child(1) strong");
		/*
		public static By	btn_ControllerFormViewActionButton									= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] ul[class*='export-dropdown']:nth-child(1) button[id='header_export']");
		public static By	pop_CreateControllerFormAttachments									= By.cssSelector("div[id='myModal-Attachment']");
		public static By	lbl_ViewFormHeaderFormTitle											= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] div[class*='firstcol'] strong+span");
		public static By	lbl_BetaViewViewFormHeaderFormTitle									= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] div[class*='firstcol'] strong+span");
		public static By	lnk_PopAttachmentsShowAttachments									= By.cssSelector("div[id='headerCreateFormDiv'] div[id='FormMoreOption'] button[class*='showAttachmentLink']");
		public static By	lnk_PopAttachmentsSecondFileRemoveIcon								= By.cssSelector("div[id='divAttachedFiles'] tbody[id='filesAttachmentListTbody'] tr:nth-child(2) td[class='filename'] span a");
		public static By	lnk_PopAttachmentsSecondFileName									= By.cssSelector("div[id='divAttachedFiles'] tbody[id='filesAttachmentListTbody'] tr:nth-child(2) td[class='filename']");
		public static By	btn_CreateRFIFormAttachFileSave										= By.cssSelector("div[id='myModal-Attachment'] form[id='data_CommentForm'] button[class='btn btn-danger pull-left']");
		public static By	btn_ControllerFormViewActionsEditORI								= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] ul[class*='export-dropdown']:nth-child(1) span[id='editORI']");
		public static By	btn_CreateControllerFormAttachFileSave								= By.cssSelector("div[id='myModal-Attachment'] button[id='btnUploadFiles_CommentForm']");
		public static By	sel_Status															= By.id("editselectStatus");
		public static By	btn_Save															= By.xpath(".//*[@id='editProject']//input[@value='Save']");
		public static By	lbl_PopAttachFileControllerFormAttachedFile							= By.cssSelector("div[id='divAttachedFiles'] table[id='filesAttachmentListTable'] tbody[id='filesAttachmentListTbody'] td[class='filename']");
		public static By	btn_filterProjectForm												= By.cssSelector("a[id='commu_basic_search']");
		public static By	txt_CreateRFIFormInputToFieldCount									= By.cssSelector("div[id='divDistributeFiles_form'] div[id='s2id_inptDistTo1'] ul[class='select2-choices'] a");
		public static By	btn_CreateRFIFormAttachment											= By.cssSelector("div[id='headerCreateFormDiv'] div[id='FormMoreOption'] button[class*='showAttachmentLink']");
		public static By	btn_CreateRFIFormAttachmentsSelectFiles								= By.cssSelector("div[id='myModal-Attachment'] form[id='data_CommentForm'] span[style*='margin-top'] input");
		public static By	lnk_BetaViewFormDistributeEditDraft									= By.cssSelector("div[class='component-wrapper'] button[class*='btn '][title='Distribution'] span");
		public static By	chk_BetaViewSelectFirstDistributeUser								= By.cssSelector("div[class='focus'] label input[class*='ng-valid']");
		public static By	btn_BetaViewVerifyDistributePoPup									= By.cssSelector("div[class='dist-wrapper ng-scope']");
		public static By	ele_BetaViewCreateFormToggleActionsPopMenu							= By.cssSelector("div[id='distInputTo'] div[class='item-detail']");
		public static By	lnk_FormDetailsActionReply											= By.cssSelector("ul[class*='export-dropdown']:nth-child(1) li[class='check-list-item']:nth-child(3) a");
		public static By	lbl_FormDetailsHeader												= By.cssSelector("div[id='viewFormContain'] div[id='viewFormMainSection'] h2[id='formName']");
		public static By	lbl_FormDetailsFormName												= By.cssSelector("div[id='wrap'] div[id='viewFormMainSection'] div[id='viewFormHeaderTable'] div[class*='firstcol'] strong+span");
		public static By	img_RespondCommentingFormCommentAdditional							= By.cssSelector("div[id='reviewerCoordinatorGrp'] div[title='Comment on package or another document'] img");
		public static By	rad_RespondCommentingFormCommentAdditionalDocument					= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] tr:nth-child(3) div[class='cmntMain'] input[value='Document']");
		public static By	txt_RespondCommentingFormCommentAdditionalDocTextArea				= By.cssSelector("tr[class*='repeatingCmntMain']:nth-child(3) div[class='cmntMain'] table[class='userCommentsTbl'] textarea");
		public static By	drp_RespondCommentingFormCommentAdditionalDocSeverity				= By.cssSelector("tr[class*='repeatingCmntMain']:nth-child(3) div[class='cmntMain'] table[class='userCommentsTbl'] select[name='reviewerPkgCommentDrpDwn']");
		public static By	frm_BetaViewEditDraftFormIframe										= By.cssSelector("div[id='custFormTD']");
		public static By	ele_BetaViewFormDetailsViewPageFormSecondReply						= By.cssSelector("div[id='form-view-page'] ul[class*='thread-list'] li:nth-child(3) a");
		public static By	btn_EditDraftFormDistributeAttachment								= By.cssSelector("div[id='divDistributeFiles_form'] div[id='FormMoreOption'] button[class*='showAttachmentLink']");
		public static By	ele_CreateFormHeaderDiv												= By.cssSelector("div[id='formParentDiv'] div[id='headerCreateFormDiv'] table[class='table-createForm'] tr[class='intro-scheme']");
		public static By	ele_ControllerFormViewXDocView										= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] div[id='accordion'] div[id='xdoc_view'] ");
		public static By	lnk_PopAttachmentAndAssociationPopupDiscussions						= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='pageLayoutHeader'] div[id='Discussions'] a");
		public static By	css_PopAttachmentAndAssociationPopupDiscussionIDs					= By.cssSelector("div[id='biDirectionAssociationModal'] div[class*='innerContainer'] div[id='adTableBody'] div[class*='rows'] :nth-child(4) div[class*='tbody-data'] a");
		public static By	btn_PopFormStatusChangeChangeStatus									= By.cssSelector("div[id='FormChangeStatusModal'] div[class='modal-footer'] button[id='btnbatchchangestatus']");*/

		/* Create Private Comment */
		public static By	lnk_CreateFormDistributionActionActiveDate							= By.cssSelector("table[class='ui-datepicker-calendar'] tr td[class*='ui-datepicker-today'] a[class*='ui-state-highlight']");

		/* Draft Distribution */
		public static By	lnk_FormDetailsActionDistribute										= By.cssSelector("ul[class*='dropdown-menu'] a[ng-click*='distribute']");
		public static By	lnk_BetaViewFormActionDistribute									= By.cssSelector("ul[class*='dropdown-menu'] a[ng-click*='distribute']");
		public static By	dd_BetaViewFormActionDistribute										= By.cssSelector("div[class*='list-wrapper'] ul[class='user-list'] li[class='ng-scope'] span[class*='dist-'] select");
		public static By	lnk_FormDetailsActionEditAndDistribute								= By.cssSelector("ul[class*='dropdown-'] li[class='ng-scope'] a[ng-click='editAndDistribute()']");
		public static By	lnk_BetaViewFormDetailsActionEditAndDistribute						= By.cssSelector("ul[class*='dropdown-menu-right'] li[ng-if*='canForward'] a");
		public static By	lnk_BetaViewFormDetailsAttachments									= By.cssSelector("div[id='form-view-page'] div[id='form-holder'] ul[class*='attach-assoc']  li[ng-click*='Attachments'] a");
		public static By	pop_BetaViewFormAttachmentsAndAssociations							= By.cssSelector("div[id='form-view-page'] main[class='open'] div[class='ibox'] div[class*='ibox-content']");
		public static By	css_BetaViewPopAttachmentsFormAttachments							= By.cssSelector("div[class='gbody'] div[class*='list-'] ul[class='repeated-item'] li a[class='text-ellipsis']");
		public static By	btn_PopDistributeButtonCancel										= By.cssSelector("div[id='appsDistributionAction'] button[class*='btn-inverse']");
		public static By	css_PopDistributeToggleDropdowns									= By.cssSelector("div[id='appsDistributionAction'] button[class='btn dropdown-toggle']");
		public static By	txt_CreateFormYourEditableResponse									= By.cssSelector("form[id='myform'] div[id='xdoc_view'] div[id*='my:Asite_System_Data_Read_Write'] textarea[id*='my:Response']");
		public static By	txt_BetaViewCreateFormYourEditableResponse							= By.cssSelector("div[id*='my:All_Responses'] div textarea[id*='my:Response'][class='xdTextBox']");
		public static By	pop_CreateFormTogglePopOverContent									= By.cssSelector("div[id='formParentDiv'] div[class='popover-content']");
		public static By	ele_CreateFormToggleActionsClose									= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-close'] span");
		public static By	ele_CreateFormToggleActionsPopMenu									= By.cssSelector("ul[class*='context-menu-root'][style*='z-index']");
		public static By	btn_CreateFormSaveDraft												= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnSaveForm']+button[id='btnSaveDraft']");
		public static By	btn_BetaViewCreateFormSaveDraft										= By.xpath(".//button[@id='btnSaveDraft'][@title][text()='Save Draft']");
		public static By	btn_CreateFormSend													= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnSaveForm']");
		public static By	btn_CreateFormSaveCancel											= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnCancelForm']");
		public static By	btn_CreateFormDiscardDraft											= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnDiscardDraft']");
		public static By	txt_EditDraftFormFirstResponse										= By.cssSelector("div[id*='my:Asite_System_Data_Read_Only'] div[id*='my:All_Responses']:nth-child(1) textarea[id*='my:Response']");
		public static By	css_FormListingFormIDs												= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows'] div[class*='col-code-fixed-width'] a");
		public static By	css_FormListingFormTitles											= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows'] div[class*='col-title-fixed-width'] a");
		public static By	css_ProjectFormListingCount											= By.cssSelector("div[id = 'communicationContent'] div[id='listing'] div[class*='innerContainer'] div[id='adTableBody'] div[class*='rows']");
		public static By	lnk_ProjectFormsFirstFormTitle										= By.cssSelector("div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-title'] a");
		public static By	lnk_FormsListingFirstAction											= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName'] a[onclick*='event);']");
		public static By	lnk_FormsListingSecondAction										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(2) div[class*='col-actions-actionName'] a[onclick*='event);']");
		public static By	lbl_FormsListingFirstAction											= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName']");
		public static By	css_CreateFormResponsesTextAreas									= By.cssSelector("textarea[id*='my:Response']");
		/*
		public static By	lnk_FormDetailsAttachments											= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] div[id='Attachments'] a");
		public static By	css_PopAttachmentsFormAttachments									= By.cssSelector("div[id='biDirectionAssociationModal'] div[id='listing'] div[id='adTableBody'] div[class*='rows'] div[class*='col-fileName-fixed-width'] a");
		public static By	btn_CreateFormDiscrardDraftYes										= By.cssSelector("div[id='adoddleConfirmUI'][style*='z-index'] button[id='continueButton']");
		public static By	css_EditDraftFormToListUsers										= By.cssSelector("div[id='distInputTo'] ul[class='select2-choices'] li[class*='select2-search-choice']");
		public static By	ele_EditDraftFormResponseSection									= By.cssSelector("div[id*='my:Asite_System_Data_Read_Only'] div:nth-child(2) div[id*='my:All_Responses']");
		*/


		/* Form Status */
		public static By	btn_CreateFormDisabledButton										= By.xpath(".//span[@title='Create Form']//a[contains(@class,'disabled')]");
		public static By	btn_CreateForm														= By.cssSelector("span[title='Create Form'] a");
		public static By	pop_CreateFormWindow												= By.cssSelector("div[id='creatFormModal'] iframe[id='createFormIframe']");
		public static By	lbl_BetaViewCreateFormWindowHeader									= By.cssSelector("div[class*='main-header'] h1");
		public static By	txt_CreateFormTitle													= By.xpath(".//textarea[contains(@id,'FORMTITLE') and @title='Enter Form Title']");
		public static By	txt_CreateFormCalculationBox										= By.xpath(".//input[contains(@id,'FORMGROUPCODE') and @type='text']");
		public static By	sel_CreateFormStatusDropdown										= By.xpath(".//select[contains(@id,'ALL_FORMSTATUS')]");
		public static By	css_CreateFormStatusDropdownList									= By.xpath(".//select[contains(@id,'ALL_FORMSTATUS')]//option");
		public static By	img_BetaViewCreateFormCalendar										= By.xpath(".//div[@class='xdDTPicker']//img[contains(@src,'calendar')]");
		public static By	ele_CreateFormCalendarCurrentDate									= By.xpath(".//div[@class='calendar']//td[contains(@class,'today')]");
		public static By	btn_CreateFormSendButton											= By.xpath(".//button[@id='btnSaveForm' and text()='Send']");
		public static By	lbl_FormListingFirstFormStatus										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-status-fixed-width']");
		public static By	sel_BetaViewStatusChangeFormStatusDropdown							= By.xpath(".//div[@id='form-view-page']//form-status-change//form[contains(@class,'change-status-form')]//div[@class='form-group']//select");
		public static By	css_BetaViewStatusChangeFormStatusDropdownList						= By.xpath(".//div[@id='form-view-page']//status-change//form[contains(@class,'change-status-form')]//div[@class='form-group']//select//option");
		public static By	txt_BetaViewFormStatusChangeReasonNote								= By.xpath(".//div[@id='form-view-page']//form-status-change//form[contains(@class,'change-status-form')]//div[@class='form-group']//textarea[@placeholder='Message']");
		/*
		public static By	pop_RespondFormWindow												= By.cssSelector("div[id='creatFormModal'] iframe[id='createFormIframe']");
		public static By	lbl_CreateFormWindowHeader											= By.cssSelector("div[id='creatFormModal'] div[class='modal-header']");
		public static By	lbl_CreatePackageFormHeader											= By.cssSelector("h2[id='form-title']");
		public static By	img_CreateFormCalendar												= By.xpath(".//img[contains(@src,'calendar') and not(contains(@linked,'true'))]");
		public static By	ele_ContextClickStatusOption										= By.xpath(".//li[contains(@class,'edit hover')]//span[text()='Status']");
		public static By	opt_FormListingContextClickEditPrintFile							= By.cssSelector("ul[class*='context-menu-root'][style*='z-index'] li[class*='CMicon-print-form']");
		public static By	sel_StatusChangeFormStatusDropdown									= By.cssSelector("div[id='FormChangeStatusModal'] form[id='formchangeStatusForm'] select[id='formStatusChangeDropdown']");
		public static By	txt_FormStatusChangeReasonNote										= By.cssSelector("div[id='FormChangeStatusModal'] form[id='formchangeStatusForm'] textarea[id='form_newstatus_reason']");
		public static By	css_StatusChangeFormStatusDropdownList								= By.cssSelector("div[id='FormChangeStatusModal'] form[id='formchangeStatusForm'] select[id='formStatusChangeDropdown'] option");*/

		/* View Project */
		public static By	ele_FormsTabFirstFormCreatedDate									= By.xpath(".//div[@index='0']//div[contains(@class,'formCreationDate')][text()]");
		public static By	css_NumberOfFormsUpdatedDate										= By.xpath(".//div[@index]//div[contains(@class,'updated')][text()]");
		public static By	css_NumberOfFormsCreatedDate										= By.xpath(".//div[@index]//div[contains(@class,'formCreationDate')]//div[text()]");
		public static By	lbl_ProjectFormsTabFormsCount										= By.xpath(".//div[@id='commuDoclist' and text()]");
		public static By	css_ProjectFormsTabFormTitleList									= By.xpath(".//div[@index]//div[contains(@class,'title')]//a[text()]");

		/* Form Distribution Groups */
		public static By	btn_BetaViewPopCreateFormDistributeFormButton						= By.xpath(".//div[@id='create-form-page']//button[@title='Distribute']");
		public static By	txt_BetaViewPopCreateFormDistributeTo								= By.xpath(".//div[@id='distInputTo']//input[@placeholder='To'][@type='text']");
		public static By	drp_BetaViewPopCreateFormSelectedUserActionDropdown					= By.xpath(".//div[@id='distInputTo']//ul//li[@class='ng-scope']//div[contains(@class,'focus')]//select");
		public static By	img_BetaViewPopCreateFormClearSelectedUserDueDays					= By.xpath(".//div[@id='distInputTo']//ul//li[@class='ng-scope']//div[contains(@class,'focus')]//span[@class='remove-date']//i");
		public static By	txt_PopCreateFormGroupCode											= By.xpath(".//textarea[contains(@id,'FORMGROUPCODE')]");
		public static By	txt_PopCreateDistributeGroupFormCode								= By.xpath(".//input[contains(@id,'FORMGROUPCODE')][@type='text']");
		public static By	drp_PopCreateDistributeGroupFormDistributionGroupDropdown			= By.xpath(".//select[contains(@id,'PROJDISTGROUPS')]");
		public static By	lnk_FirstFormTitle													= By.xpath(".//div[@index='0']//div[contains(@class,'title')]//a");
		public static By	ele_FirstFormStatus													= By.xpath(".//div[@index='0']//div[contains(@class,'status')][text()]");
		public static By	lnk_FirstFormDraftID												= By.xpath(".//div[@index='0']//div[contains(@class,'code')]//a");
		public static By	txt_PopCreateFormNameSearchFilter									= By.xpath(".//input[@id='formTypeNameInp']");
		public static By	txt_BetaViewPopCreateFormNameSearchFilter							= By.xpath(".//div[contains(@id,'view-page')]//div[contains(@class,'open')]//input");
		public static By	txt_PopCreateFormPorjectNameSearchFilter							= By.xpath(".//input[@id='projectNameInp']");
		public static By	ele_PopCreateFormFirstFormName										= By.xpath(".//div[@index='0']//div[contains(@class,'formTypeName')][text()]");
		public static By	ele_BetaViewViewFilePopCreateFormFirstFormName						= By.xpath(".//div[contains(@id,'view-page')]//div[contains(@class,'open')]//div[@class='gbody']//a[1]//ul//li[@title][1]");
		public static By	btn_BetaViewPopCreateFormCancelButton								= By.xpath(".//button[@id='btnCancelForm'][text()='Cancel']");
		public static By	css_FormsTabAppTypesGroupList										= By.xpath(".//div[contains(@id,'appGroup')]//a[@class='appGroup']");
		public static By	ele_BetaViewPopCreateFormNoDistributeMatchFoundLabel				= By.xpath(".//div[@id='distInputTo']//dist-select//p[text()='No Match Found']");
		public static By	chk_BetaViewSelectDistributeUserCheckBox							= By.cssSelector("input[class*='ng-valid '][type='checkbox']");
		public static By	img_BetaViewFormDistributionCloneIcon								= By.cssSelector("a[class*='clone-icon']");
		public static By	lbl_BetaViewFormDistributionUserSearchList							= By.cssSelector("label span[class='ng-binding']");
		public static By	lnk_CreateFormDistributeToCloseButton								= By.cssSelector("a[ng-click='closeDropdown()']");
		/*
		public static By	txt_SelectDistributeUser											= By.cssSelector("li[class*='select2'] div[class='select2-result-label'] span[class='select2-match']");
		public static By	btn_PopCreateFormDistributeFormButton								= By.xpath(".//div[@id='FormMoreOption' and not(contains(@style,'display:none'))]//button[text()='Distribute']");
		public static By	chk_PopCreateFormPrintViewCheckbox									= By.xpath(".//input[@id='printView' and @type='checkbox']");
		public static By	btn_PopCreateFormCloseFormButton									= By.xpath(".//button[contains(@id,'close') and not(contains(@id,'Button')) and text()='x']");
		public static By	txt_PopCreateFormDistributeTo										= By.xpath(".//div[@class='distORICreation0false' and contains(@style,'block')]//ul[@class='select2-choices']//input");
		public static By	ele_FirstFormStatus													= By.xpath(".//div[@index='0']//div[contains(@class,'status')]//div[text()]");
		public static By	ele_PopCreateFormFirstFormName										= By.xpath(".//div[@index='0']//div[contains(@class,'formTypeName')]//div[text()]");*/

		/* Edit ORI Form */
		public static By	txt_EOFFormGroupCode												= By.cssSelector("div[id='xdoc_view'] table[class='xdLayout'] textarea[id*='my:DS_FORMGROUPCODE']");
		public static By	txt_EOFFormDueDatePicker											= By.cssSelector("form[id='myform'] div[id='xdoc_view'] select[id*='DS_ALL_FORMSTATUS']+div[class='xdDTPicker'] a[class='xdDTButton'] img");
		public static By	opt_FormListingContextClickEdit										= By.cssSelector("div+ul[class*='context-menu-root'][style*='top'] li[class*='CMicon-edit']:nth-child(3)");
		public static By	opt_FormListingContectClickEditMessage								= By.cssSelector("ul[class*='context-menu-root'][style*='top'] li[class*='CMicon-edit'] ul li[class*='CMicon-edit-ori']");
		public static By	chk_FormListingFirstCheckbox										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='filelistchkbox'] input");
		public static By	chk_FormListingSecondCheckbox										= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(2) div[class*='filelistchkbox'] input");
		public static By	btn_FormListingStatusFilter											= By.cssSelector("div[id='docListingSection'] div[id='commuFormStatusBasicFilter'] button[filterkey='Status']");
		public static By	txt_FormListingStatusFilterSearch									= By.cssSelector("div[class*='filterui'] div[class*='filter-layer-box'][class*='active'] input[placeholder='Status']");
		public static By    drp_FormListingActionNameFilterPanelLabel							= By.cssSelector("div[id='fileContentContainer'] div[id='filterCells'] div[fieldname='action_name']");
		public static By    txt_FormListingActionNameFilterInput								= By.cssSelector("input[filterkey='Action Name']");
		public static By 	drp_FormListingActionStatusFilterPanelLabel							= By.cssSelector("div[id='fileContentContainer'] div[id='filterCells'] div[fieldname='action_status']");
		public static By    txt_FormListingActionStatusFilterInput								= By.cssSelector("input[filterkey='Action Status']");
		public static By 	drp_FormListingRecipientNameFilterPanelLabel						= By.cssSelector("div[id='fileContentContainer'] div[id='filterCells'] div[fieldname='distribution_list']");
		public static By    txt_FormListingRecipientNameFilterInput								= By.cssSelector("input[filterkey='Recipient Name']");
		public static By	chk_FormListingStatusFilterClosed									= By.cssSelector("div[class*='filterui'] div[class*='filter-layer-box'][class*='active'] a[title='Closed'] input");
		public static By	lbl_BetaViewPopEditORIHeader										= By.cssSelector("div[id='form-view-page'] div[id='form-holder'] div[class='ibox'] div[class*='ibox-title'] h5");
		public static By	btn_BetaViewPopEditORIEditUsingSavedDraft							= By.cssSelector("div[id='form-view-page'] div[class='ibox'] button[ng-click*='openOriDraft']");
		public static By	btn_BetaViewPopEditORICreateNewEdit									= By.cssSelector("div[id='form-view-page'] div[class='ibox'] button[ng-click*='createNewOri']");
		public static By	txt_EOFFormRichTestBox												= By.cssSelector("span[id*='Confidential_QueryText_Rtext']");
		public static By	txt_EOFFormRichTestBox2												= By.cssSelector("span[class*='xdRichTextBox']");
		public static By	lbl_FormDetailsEOFFormRichTestBox									= By.cssSelector("div[id='xdoc_view'] span[class='xdRichTextBox']");
		/*
		public static By	lbl_PopEditORIHeader												= By.cssSelector("div[id='myModal-EditORIUsingDraft'][style*='display: block'] div[class='modal-header'] h3");
		public static By	btn_PopEditORIEditUsingSavedDraft									= By.cssSelector("div[id='myModal-EditORIUsingDraft'][style*='display: block'] button[id='btnEditSavedDraft']");
		public static By	btn_PopEditORICreateNewEdit											= By.cssSelector("div[id='myModal-EditORIUsingDraft'][style*='display: block'] button[id='btnCreateNewEdit']");
		*/


		/* Commenting Form */
		public static By	pop_CommentingFormViewPopover										= By.cssSelector("div[id='adoddleDockInformationUI'][style*='display:block'] h3[class='popover-title']");
		public static By	rad_RespondCommentingFormIssueToTA									= By.cssSelector("div[id='custFormTD'] div[id='reviewerCoordinatorGrp'] table[class='cmntngFrmDetails'] input[value='Technical Authoriser']");
		public static By	rad_RespondCommentingFormIssueToPM									= By.cssSelector("div[id='custFormTD'] div[id='reviewerCoordinatorGrp'] table[class='cmntngFrmDetails'] input[value='Project Manager']");
		public static By	drp_RespondCommentingFormSelectTA									= By.cssSelector("div[id='custFormTD'] div[id='reviewerCoordinatorGrp'] table[class='cmntngFrmDetails'] select[name='Selected_Technical_Authoriser']");
		public static By	drp_RespondCommentingFormSendToPM									= By.cssSelector("div[id='custFormTD'] div[id='technicalAuthoriserRes'] table[class='cmntngFrmDetails'] select[name='SelectedProjectManager'][ng-model*='PMName']");
		public static By	drp_RespondCommentingFormSendToPM2									= By.cssSelector("select[name='Selected_Project_Manager']");
		public static By	css_RespondCommentingFormRequiredDocStatusDropdowns					= By.cssSelector("div[id='custFormTD'] table[class='docDetailsTbl'] select[name='statusDrpDwn']");
		public static By	rad_RespondCommentingFormTAApproveNo								= By.cssSelector("div[id='custFormTD'] div[id='technicalAuthoriserRes'] table[class='cmntngFrmDetails'] input[name='TAapprove'][value='NO']");
		public static By	rad_RespondCommentingFormPMAcceptsNo								= By.cssSelector("div[id='custFormTD'] div[id='projMngrRes']  table[class='cmntngFrmDetails'] input[ng-model*='PM_Approve_Comments'][value='NO']");
		public static By	txt_RespondCommentingFormTARemarks									= By.cssSelector("div[id='custFormTD'] div[id='technicalAuthoriserRes'] table[class='cmntngFrmDetails'] textarea[name='Tech_Res_comments']");
		public static By	txt_RespondCommentingFormPMReason									= By.cssSelector("div[id='custFormTD'] div[id='projMngrRes']  table[class='cmntngFrmDetails'] textarea[ng-required*='PM_Approve_Comments']");
		public static By	txt_RespondCommentingFormInternalRemarks							= By.cssSelector("div[id='custFormTD'] div[id='reviewerCoordinatorGrp'] table[class='commentsTbl'] textarea[name='PM_Draft_Response']");
		public static By	rad_RespondCommentingFormFirstPackageRecommendationPM				= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] input:nth-child(1)");
		public static By	rad_RespondCommentingFormFirstPackageRecommendation					= By.cssSelector("div[id*='reviewer'] table[class='cmntngFrmDetails'] input:nth-child(1)");
		public static By	rad_RespondCommentingFormPackage									= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] div[class='cmntMain'] input:nth-child(1)");
		public static By	txt_RespondCommentingFormCommentBox									= By.cssSelector("div[id*='reviewer'] div[class='cmntMain'] table[class='repeatingCommentsTbl'] textarea[class*='comment']");
		public static By	drp_RespondCommentingFormSeverity									= By.cssSelector("div[id*='reviewer'] div[class='cmntMain'] select[name='reviewerPkgCommentDrpDwn']");
		public static By	txt_GlobalListingCreatedFilterDCResStatus							= By.cssSelector("div[id='docListingSection'] div[id='filterCells'] div[fieldname='CFID_DC_Res_Status'] input[id*='filterCellText']");
		public static By	drp_GlobalListingCreatedFilterRecipientName							= By.cssSelector("div[id='docListingSection'] div[id='filterCells'] li[listfilterkey = 'Recipient Name'] button");
		public static By	txt_GlobalListingCreatedFilterRecipientNameSearch					= By.cssSelector("div[class*='filter-layer-box'][class*='active'] div[class='check-list-select'] input[filterkey='Recipient Name']");
		public static By	chk_EditCommentingFormStatusSubmitted								= By.cssSelector("div[id*='ORI_MSG_Custom_Fields'] table[class='xdLayout'] input[id*='my:Status']");
		public static By	lbl_FormDetailsPackageFormRevision									= By.cssSelector("div[id='accordion'] div[id='xdoc_view'] table[class*='MsoNormalTable'] tr:nth-child(1) td:nth-child(4)");
		public static By	lbl_EditFormDetailsPackageFormRevision								= By.cssSelector("div[id='custFormTD'] div[id='xdoc_view'] table[class*='MsoNormalTable'] tr:nth-child(4) td:nth-child(4)");
		public static By	btn_LaunchCommentingFormButton										= By.cssSelector("div[id='xdoc'] div[id='xdoc_view'] input[value='Launch Commenting Form']");
		public static By	drp_EditPackageFormQACheck											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:DC_Res_Status']");
		public static By	txt_EditPackageFormDocControllerComment								= By.cssSelector("div[id='xdoc_view'] table[class='xdLayout'] textarea[id*='my:DC_Comments'][class='xdTextBox']");
		public static By	drp_EditPackageFormSendtoPM											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:Reviewer']");
		public static By	drp_CreateCommentingFormReviewCoGroup								= By.cssSelector("form[id='myform'] div[class*='cmntngFrmDetails'] select[class*='ReviewCoordinatorGroup']");
		public static By	drp_CreateCommentingFormReviewerGroup								= By.cssSelector("form[id='myform'] table[class*='reviewerGrpTbl'] select[class*='ReviewGroup']");
		public static By	lbl_CreateCommentingFormReviewerActionDays							= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(2) td strong");
		public static By	lbl_CreateCommentingFormReviewerActionDaysValue						= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(2) td[class='ng-binding']");
		public static By	lbl_CreateCommentingFormRCActionDays								= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(3) td strong");
		public static By	lbl_CreateCommentingFormRCActionDaysValue							= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(3) td[class='ng-binding']");
		public static By	lbl_CreateCommentingFormTAActionDays								= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(4) td strong");
		public static By	lbl_CreateCommentingFormTAActionDaysValue							= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(4) td[class='ng-binding']");
		public static By	lbl_CreateCommentingFormPMActionDays								= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(5) td strong");
		public static By	lbl_CreateCommentingFormPMActionDaysValue							= By.cssSelector("div[class='cmntngFrmDetails'] tbody tr:nth-child(5) td[class='ng-binding']");
		public static By	lbl_CreateCommentingFormViewHolderFormTitle							= By.cssSelector("div[class='cmntgFrmBody'] table[class*='cmntgFrmBodyTbl'] tbody tr:first-child td:last-child");
		public static By	chk_DisplayFormPrintViewAfterSaving									= By.cssSelector("div[id='displayPrintViewAfterSaving'] input[id='printView']");
		public static By	img_CreateCommentingFormRemoveReviewerGroup							= By.cssSelector("div[id='custFormTD'] div[class*='reviewerGrp'] table[class*='reviewerGrpTbl '] img[src*='trash-delete']");
		public static By	txt_CreateCommentingFormTitle										= By.cssSelector("form[id='myform'] table[class*='cmntgFrmBodyTbl'] input[id='ORI_FORMTITLE']");
		public static By	lbl_RespondCommentingFormReviewerAlertMessage						= By.cssSelector("div[id='custFormTD'] div[class*='commentingFormContainer'] div[class*='noAccessMsg']");
		public static By	btn_CreatePackageMoreOption											= By.cssSelector("button[id='create-form-options']");
		public static By	lnk_CreatePackageMoreOptionAssociateDocs							= By.cssSelector("ul[class*='dropdown-menu '] li a");
		public static By	pop_CreatePackageAssocFileModal										= By.cssSelector("ol[class*='breadcrumb ']");
		public static By	drp_CreatePackageFormProjectManager									= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:ToProjectManager']");
		public static By	drp_CreatePackageFormDocController									= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:Trans_TO']");
		public static By	drp_CreatePackageFormGate											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='_my:Gate']");
		public static By	drp_CreatePackagePOI												= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:SubmissionPurpose']");
		public static By	img_CreatePackageFormDateButton										= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] a[class='xdDTButton']");
		public static By	img_BetaViewCreatePackageFormDateButton								= By.cssSelector("div[class='xdDTPicker'] img[src*='infojet_calendar.gif']");
		public static By	ele_DatePickerTodayDate												= By.cssSelector("div[class='calendar'] tr[class='daysrow'] td[class*='today']");
		public static By	txt_CreatePackageFormTitle											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] input[id*='my:ORI_FORMTITLE']");
		public static By	txt_CreatePackageP6Number											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] input[id*='_my:P6number']");
		public static By	txt_CreatePackageTaskSearch											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] input[id*='my:Deli_search']");
		public static By	drp_CreatePackageTask												= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:Deliverable']");
		public static By	drp_CreatePackagePeriodOfReply										= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:PeriodOfReply']");
		public static By	drp_CreatePackageConsentType										= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:Consent_Type']");
		public static By	drp_CreatePackageConsentStage										= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:Consent_Stage']");
		public static By	txt_CreatePackageConsentBody										= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] input[id*='my:Consent_Granting_Body']");
		public static By	txt_CreatePackageLocationSearch										= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] input[id*='my:LocationSearch']");
		public static By	drp_CreatePackageLocation											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:Location']");
		public static By	drp_CreatePackageLifeCycle											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:Lifecycle']");
		public static By	drp_CreatePackageWorkType											= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] select[id*='my:WorkType']");
		public static By	chk_CreatePackageStatus												= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] input[id*='my:Status'][class='xdBehavior_Boolean']");
		public static By	btn_CreateFormSave													= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnSaveForm']");
		public static By	btn_BetaViewCreateFormSave											= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnSaveForm']");
		public static By	lnk_FormsFirstAction												= By.cssSelector("div[id='communicationContent'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName'] a[onclick*='event);']");
		public static By	txt_filterProjectForm												= By.cssSelector("div[id='docListingSection'] input[id='commu_fd_containText']");
		public static By	lbl_CreateCommentingFormReviewCoGroupError							= By.cssSelector("form[id='myform'] table[class='docDetailsTbl'] div[style='color:red']");
		public static By	rad_RespondCommentingFormDocumentAcceptanceWithComment				= By.cssSelector("div[id*='reviewer'] div[class*='accptStatusRecmndtn'] input[name*='AcceptanceStatusRecommendation'][value='Accepted With Comments']");
		public static By	rad_RespondCommentingFormDocument									= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] div[class='cmntMain'] input[value='Document']");
		public static By	rad_RespondCommentingFormNoComment									= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] div[class='cmntMain'] input[value='No Comment']");
		public static By	drp_RespondCommentingFormDocument									= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] select[name*='DS_ASSOC_FORM_ASSOCDOCS_METADATA']");
		public static By	rad_RespondCommentingFormDocumentNotAccepted						= By.cssSelector("div[id*='reviewer'] div[class*='accptStatusRecmndtn'] input[name*='AcceptanceStatusRecommendation'][value='Not Accepted']");
		public static By	rad_RespondCommentingFormDocumentAccepted							= By.cssSelector("div[id*='reviewer'] input[name*='packageRecommendationNoComment'][value='Accepted']");
		public static By	rad_RespondCommentingFormPackageAcceptanceWithComment				= By.cssSelector("div[id*='reviewer'] input[name*='packageRecommendation'][value='Accepted With Comments']");
		public static By	txt_RespondCommentingFormPackageAcceptanceComment					= By.cssSelector("div[id*='reviewer'] textArea[ng-required*='Accepted With Comments']");
		public static By	rad_RespondCommentingFormPackageNotAccepted							= By.cssSelector("div[id*='reviewer'] input[name*='packageRecommendation'][value='Not Accepted']");
		public static By	lnk_RespondCommentingFormReadOnlyExpand								= By.cssSelector("div[id='custFormTD'] div[id='reviewerCoordinatorGrp'] a[class*='expand-btn']");
		public static By	lnk_RespondCommentingFormInsertComment								= By.cssSelector("div[id='reviewerCoordinatorGrp'] tr[class*='repeatingCmntMain']:nth-child(1) div[class='cmntMain'] div[title='Insert Comment']");
		public static By	txt_RespondCommentingFormInsertedCommentInput						= By.cssSelector("table[class='userCommentsTbl'] tbody:nth-of-type(2) table[class='repeatingCommentsTbl'] textarea[ng-model*='repeatCommentGroup']");
		public static By	drp_RespondCommentingFormInsertedCommentSeverity					= By.cssSelector("table[class='userCommentsTbl'] tbody:nth-of-type(2) select[name='reviewerPkgCommentDrpDwn']");
		public static By	rad_RespondCommentingFormTAApproveYes								= By.cssSelector("div[id='custFormTD'] div[id='technicalAuthoriserRes'] table[class='cmntngFrmDetails'] input[name='TAapprove'][value='YES']");
		public static By	drp_RespondCommentingFormSendToContractor							= By.cssSelector("div[id='custFormTD'] div[id='projMngrRes'] table[class='cmntngFrmDetails'] select[name='statusDrpDwn'][ng-model*='selContractorObj']");
		public static By	rad_RespondCommentingFormPMAcceptsYes								= By.cssSelector("div[id='custFormTD'] div[id='projMngrRes']  table[class='cmntngFrmDetails'] input[ng-model*='PM_Approve_Comments'][value='YES']");
		public static By	drp_RespondCommentingFormPMPackageStatus							= By.cssSelector("div[id='custFormTD'] div[id='projMngrRes']  table[class='cmntngFrmDetails'] select[ng-options*='filterDS_ALL_FORMSTATUS']");
		public static By	txt_RespondCommentingFormPMComments									= By.cssSelector("div[id='custFormTD'] div[id='projMngrRes'] table[class='cmntngFrmDetails'] textarea");
		public static By	css_RespondCommentingFormReadOnlyUserSections						= By.cssSelector("div[id='reviewerCoordinatorGrp'] div[class='reviewerTigCommentsContainer'] div[ng-show='showReadOnlyComments'] div[class='ng-scope'][ng-if*='Reviewer_id']");
		public static By	lbl_RespondCommentingFormReadOnlyHeaders							= By.cssSelector("div[class*='RCReadOnlyHeader']");
		public static By	btn_ExportCommentingFormComments									= By.cssSelector("button[title='Export Comments']");
		public static By	btn_DownloadCommentingFormReport									= By.cssSelector("div[id='downloadButton'] input[value='Click here to Download Report']");
		public static By	opt_FormContextClickActions											= By.cssSelector("ul[class*='context-menu-list'][style*='width'] li[class*='CMicon-actions']");
		public static By	opt_FormContextClickShare											= By.cssSelector("ul[class*='context-menu-list'][style*='width'] li[class*='CMicon-share']");
		public static By	opt_FormContextClickActionsForInformation							= By.cssSelector("ul[class*='context-menu-list'][style*='width'] li[class*='CMicon-actions'] li[class*='CMicon-forinfo']");
		public static By	btn_PopActionsForInformationOk										= By.cssSelector("div[class='modal-scrollable'][style*='z-index: 1060'] div[id='modalFormsbatchforinfo'] a[id='btnSubmitFormsForInfo']");
		/*
		public static By	img_PopManageAppsModelFilteredAppEditImage							= By.cssSelector("div[id='manageAppsModal'] div[id='apps'] div[id='manageAppsMainDiv'] div[id='adTableBody'] div[class*='rows'][style*='display: block'] div[class*='col-editFormImage'] img");
		public static By	lbl_CommentingFormDetailsHeader										= By.cssSelector("div[class*='cmntgFrmHeaderTitle']");
		public static By	img_CommentingFormDetailsActionCompleteIcon							= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] img[alt='completed']");
		public static By	lbl_GlobalListingCreateFilterFilteredFirstLabel						= By.cssSelector("div[class*='filterui'] div[class*='box-shadow'][class*='active'] div[class='check-list-select'] div[class='filterui-list-scroll'] ul[id*='ui-id-'][style*='display: block'] li:nth-child(1) a label");
		public static By	css_GlobalListingCreateFilterFilteredLabels							= By.cssSelector("div[class*='filterui'] div[class*='box-shadow'][class*='active'] div[class='check-list-select'] div[class='filterui-list-scroll'] ul[id*='ui-id-'][style*='display: block'] li label");
		public static By	css_GlobalListingCreateFilterFilteredChekboxes						= By.cssSelector("div[class*='filterui'] div[class*='box-shadow'][class*='active'] div[class='check-list-select'] div[class='filterui-list-scroll'] ul[id*='ui-id-'][style*='display: block'] li input");
		public static By	lbl_FormDetailsCommentingFormRevision								= By.cssSelector("div[id='accordion'] div[class='cmntgFrmBody'] div[class*='cmntngFrmDetails']:nth-child(1) tr:nth-child(2) td:nth-child(4)");
		public static By	txt_PopCreateFormAssocFileSearch									= By.cssSelector("div[id='assocFilesModal'] input[id='assFile_containText']");
		public static By	img_PopCreatePackageAssocFileDateDescending							= By.cssSelector("ul[class*='ng-pristine '] li[class*='ng-scope '][title='Date - Sorted descending']  b[class*='ng-binding']");
		public static By	ele_PopCreatePackageAssocFileDateHeader								= By.cssSelector("ul[class*='ng-pristine '] li[class*='ng-scope '][title='Date - Sorted descending'] i");
		public static By	txt_CreatePackageContractorComment									= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout'] input[id*='my:Contractor_Comment']");
		public static By	lbl_CommentingFormViewFormTitle										= By.cssSelector("table[class*='cmntgFrmBodyTbl'] tbody tr:nth-child(1) td[class='ng-binding']");
		public static By	drp_RespondCommentingFormAdditionalDocument							= By.cssSelector("div[id*='reviewer'] table[class='repeatingCommentsTbl'] tr:nth-child(3) select[name*='DS_ASSOC_FORM_ASSOCDOCS_METADATA']");
		public static By	lbl_FormDetailViewFormStatus										= By.cssSelector("div[id='viewFormContain'] div[id='viewFormHeaderTable'] div[class='firstcol'] a[class='status-disabled'] span");*/

		/* Commenting Form */
		public static By	lnk_svnHTMLAppsCommentingZipFile									= By.cssSelector("a[href='commenting.zip']");
		public static By	lnk_svnHTMLAppsPackageFormXSN										= By.cssSelector("a[href='TIDP_Transmittal_01Form.xsn']");

		/* IDP Form */
		public static By	lnk_svnHTMLAppsIDPZipFile											= By.cssSelector("a[href='PIDP.zip']");
		public static By	lnk_IDPFormTemplateFormLink											= By.cssSelector("div[id='folderTree'] div[id='treeView'] a[class='formType'][title='IDPT']");
		public static By	lnk_IDPFormLink														= By.cssSelector("div[id='folderTree'] div[id='treeView'] a[class='formType'][title='1.01 Information Delivery Plan']");
		public static By	drp_CreateIDPFormPopStageItemNotifyTo								= By.cssSelector("div[id='subitemstage'] div[id='idpDropArea'] select[class*='edRoleSelect']");
		public static By	lnk_PopStageManageMentDatePickerFooterToday							= By.cssSelector("div[class*='_720kb-datepicker-calendar-footer'] a[ng-click='setToday();']");
		public static By	ele_CreateIDPFormPTitleTable										= By.cssSelector("table[id='deliverables-header'] thead tr:nth-child(1) table[class='ptitle']");
		public static By	btn_CreateIDPFormProjectDetailsUpdate								= By.cssSelector("div[id='projectdetails'] footer[class='m-footer'] button[class*='update-btn']");
		public static By	txt_CreateIDPFormProjectDetailsFacility								= By.cssSelector("div[id='projectdetails'] input[id='edpfacility']");
		public static By	drp_CreateIDPFormProjectDetailsRef									= By.cssSelector("table div[class='item-holder'] button[type='button']");
		public static By	drp_CreateIDPFormProjectDetailsRefFirstSelect						= By.cssSelector("item-selection[list='pCodeListData'] div[class='itemContainer'] div[ng-repeat='item in list'] label:nth-child(1) span");
		public static By	drp_CreateIDPFormProjectDetailsPlanStatus							= By.cssSelector("div[id='projectdetails'] select[id='edprojectStatus']");
		public static By	drp_CreateIDPFormProjectDetailsRegion								= By.cssSelector("div[id='projectdetails'] select[id='edregion']");
		public static By	ele_CreateIDPFormStageManagementFirstStage							= By.cssSelector("table[id='deliverables-header'] tr:nth-child(3) td[class*='stage_prog']:nth-child(2)");
		public static By	ele_CreateIDPFormStageManagementInformationDate						= By.cssSelector("datepicker[class='ng-isolate-scope'] input[class*='ang-datepciker'][type='text']");
		public static By	drp_PopStageManageMentSIM											= By.cssSelector("item-selection[list='orgListData'] button");
		public static By	drp_PopStageManageMentSupplierOrg									= By.cssSelector("item-selection[list='orgListData'] div[class='drop-show']");
		public static By	drp_PopStageManageMentSupplierOrgValue								= By.cssSelector("item-selection[list='orgListData'] div[class='drop-show'] div[ng-repeat='item in list'] label:nth-child(4) span");
		public static By	drp_PopStageManageMentStatus										= By.cssSelector("modal[class='ng-isolate-scope'] div[id='stagemanagement'] table[edform='stageMan'] select[id='edLodSelect']");
		public static By	drp_PopStageManageMentLOD											= By.cssSelector("modal[class='ng-isolate-scope'] div[id='stagemanagement'] table[edform='stageMan'] select[class*='edLodSelect']");
		public static By	txt_PopStageManageMentInfoByDate									= By.cssSelector("modal[class='ng-isolate-scope'] div[id='stagemanagement'] table[edform='stageMan'] datepicker input:nth-child(1)");
		public static By	btn_PopStageManageMentUpdate										= By.cssSelector("div[id='stagemanagement'] section[class='m-inner'] button[class*='update-btn']");
		public static By	lnk_PopStageManageMentDatePickerPrevious							= By.cssSelector("div[class*='_720kb-datepicker-calendar-header'] a[title='Prev']");
		public static By	ele_PopStageManageMentDatePickerRandomDate							= By.cssSelector("div[class*='_720kb-datepicker-calendar-body'] a[ng-repeat='item in days']:nth-child(10)");
		public static By	lnk_CreateIDPFormFirstDeliverable									= By.cssSelector("div[class*='tbl-content'] table[id='deliverables-body'] tbody tr:nth-child(5) td[class='inumber']:nth-child(7)");
		public static By	lnk_CreateIDPFormFirstDeliverableItemTitle							= By.cssSelector("input[class*='edItemTitle']");
		public static By	lnk_CreateIDPFormFirstDeliverableItemVolumeEdit						= By.cssSelector("button[class*='manage-entity'][title*='Volume ']");
		public static By	lnk_CreateIDPFormFirstDeliverableItemLocationEdit					= By.cssSelector("button[class*='manage-entity'][title*='Location']");
		public static By	lnk_CreateIDPFormFirstDeliverableItemAdd							= By.cssSelector("ul[class='entity-list'] a");
		public static By	txt_CreateIDPFormFirstDeliverableItemAddCode						= By.cssSelector("ul[class='entity-list'] li:nth-child(1) span input");
		public static By	txt_CreateIDPFormFirstDeliverableItemAddName						= By.cssSelector("ul[class='entity-list'] li div[class='name-text'] input");
		public static By	btn_CreateIDPFormFirstDeliverableItemClose							= By.cssSelector("footer[class='m-footer'] button");
		public static By	drp_CreateIDPFormFirstDeliverableItemVolumeDropDown					= By.cssSelector("div[class='subitem-wrapper'] table tbody tr:nth-child(8) td:nth-child(2) button[class*='text-ellipsis']");
		public static By	drp_CreateIDPFormFirstDeliverableItemVolumeSelect					= By.cssSelector("table tbody tr:nth-child(8) td:nth-child(2) label:last-child span");
		public static By	drp_CreateIDPFormFirstDeliverableItemLocationDropDown				= By.cssSelector("div[class='subitem-wrapper'] table tbody tr:nth-child(9) td:nth-child(2) button[class*='text-ellipsis']");
		public static By	drp_CreateIDPFormFirstDeliverableItemLocationSelect					= By.cssSelector("table tbody tr:nth-child(9) td:nth-child(2) label:last-child span");
		public static By	btn_CreateIDPFormFirstDeliverableItemFileType						= By.cssSelector("div[class='subitem-wrapper'] table tbody tr:nth-child(10) td:nth-child(2) button");
		public static By	ele_CreateIDPFormFirstDeliverableItemFileTypeSeletion				= By.cssSelector("div[class='subitem-wrapper'] table tbody tr:nth-child(10) td:nth-child(2) label:nth-child(1) span");
		public static By	btn_CreateIDPFormFirstDeliverableItemRole							= By.cssSelector("div[class='subitem-wrapper'] table tbody tr:nth-child(11) td:nth-child(2) button");
		public static By	ele_CreateIDPFormFirstDeliverableItemRoleSeletion					= By.cssSelector("div[class='subitem-wrapper'] table tbody tr:nth-child(11) td:nth-child(2) label:nth-child(1) span");
		public static By	txt_CreateIDPFormFirstDeliverableItemNumber							= By.cssSelector("div[class='subitem-wrapper'] table tbody tr:nth-child(12) td:nth-child(2) input");
		public static By	lnk_CreateIDPFormLastDeliverable									= By.cssSelector("div[id='tbl-wrap'] table[id='deliverables-body'] tbody:last-child tr[class*='ng-scope']:last-child");
		public static By	ele_CreateIDPFormFirstStageDeliverable								= By.cssSelector("table[id='deliverables-body'] tbody tr:nth-child(3) td[class*='dstage']:nth-child(8)");
		public static By	ele_CreateIDPFormFirstStageSubDeliverable							= By.cssSelector("table[id='deliverables-body'] tr[class='irow ng-scope']:nth-child(5) td[class*='istage']:nth-child(8)");
		public static By	btn_PopStageDeliverablesSupplierRole								= By.cssSelector("table[edform='itemStage'] tbody tr:nth-child(3) td:nth-child(2) button");
		public static By	lbl_PopStageDeliverablesFileMask									= By.cssSelector("div[id='stagedeliverables'] table[edform='itemStage'] span[class*='edFileMask']");
		public static By	txt_PopStageDeliverablesDeliveryNote								= By.cssSelector("div[id='stagedeliverables'] table[edform='itemStage'] textarea[class*='edDnote']");
		public static By	btn_PopStageDeliverablesUpdate										= By.cssSelector("footer[class='m-footer'] button[class*='update-btn']");
		public static By	btn_PopStageItemUpdate												= By.cssSelector("div[id='subitemstage'] footer[class='m-footer'] button[class*='update-btn']");
		public static By	btn_EditIDPFormFirstStageVerify										= By.cssSelector("table[id='deliverables-header'] tr[class*='ng-scope'] td[class*='ng-scope']:nth-child(2) button[class*='refresh-cobie-btn']");
		public static By	img_EditIDPFormFirstStageDeliverableColorIcon						= By.cssSelector("table[id='deliverables-body'] tr[class*='ng-scope']:nth-child(3) td[class*='dstage ng-scope']:nth-child(8) img");
		public static By	img_EditIDPFormFirstStageSubDeliverableColorIcon					= By.cssSelector("table[id='deliverables-body'] tr[class='irow ng-scope']:nth-child(5) td:nth-child(8) img[class='rag ng-scope']");
		public static By	img_CreateControllerRespondByDate									= By.cssSelector("form[id='myform'] div[id='rrbDiv'] input[id*='respondBy']+img[class='ui-datepicker-trigger']");
		public static By	img_NotifyToUserDueDateCalender										= By.cssSelector("datepicker[class='ng-isolate-scope'] img[class='ang-datepicker-icon']");
		public static By	lnk_NotifyToUserDueDate												= By.cssSelector("div[class='_720kb-datepicker-calendar-body clearfix']");
		public static By 	lbl_PopDatePickerCurrentMonthLabel									= By.cssSelector("div[class*='_720kb-datepicker'] a[ng-click*='!showMonths'] span");
		public static By 	lnk_PopDatePickerNextMonthLink										= By.cssSelector("div[class*='_720kb-datepicker-calendar'][ng-hide*='showYearsPagination'] a[ng-click='nextMonth()']");
		public static By 	ele_StageItemPopUp													= By.cssSelector("div[class='m-content'] div[class='m-body']");
		public static By 	btn_StageItemPopUpSelectFileButton									= By.cssSelector("div[id='idpDropArea'] tr td div[class='drop-section'] span[class*='select-file-btn'] input");
		public static By 	drp_StageItemUploadDocumentStatus									= By.cssSelector("div[class='ng-scope'] tbody tr:nth-child(3) td:nth-child(2) select");
		public static By 	ele_StageItemUploadDocumentRevision									= By.cssSelector("div[class='ng-scope'] tbody tr:nth-child(4) td:nth-child(2) input");
		public static By 	btn_StageItemUploadDocumentUpdateButton								= By.cssSelector("div[class='m-content'] footer[class*='m-footer'] button[class*='update-btn']");
		public static By 	chk_SelectAllCheckbox												= By.cssSelector("div[id='fileContent'] div[id='adTableHead'] div[class*='filelistchkbox'] input");
		public static By 	opt_ContextClickOnSelectedFiles										= By.cssSelector("div[id='listing'] div[id='adTableBody'] div[class*='divtr rows'] div:nth-child(16)");
		public static By 	drp_BatchChangeStatusDropDown										= By.cssSelector("div[id='batchChangeStatusModal'] form[id='batchchangeStatusForm'] span select");
		public static By 	ele_BatchChangeStatusReason											= By.cssSelector("div[id='batchChangeStatusModal'] form[id='batchchangeStatusForm'] textarea[id*='batch_newstatus']");
		public static By 	btn_BatchChangeStatusSubmit											= By.cssSelector("div[id='batchChangeStatusModal'] button[id='btnbatchchangestatus']");
		public static By 	ele_IDPFormFileMask													= By.cssSelector("div[class='m-body'] table tbody tr:nth-child(2) td:last-child div");
		public static By 	ele_IDPFormHideShow													= By.cssSelector("div[class='pcontrol'] div[class='item-holder showHideMenu']");
		public static By 	chk_IDPFormHideShowHeaderCheckbox									= By.cssSelector("div[class='pcontrol'] div[class='item-holder showHideMenu'] div[class='checkboxitem'] label input[ng-model='data.showHeader']");
		public static By 	ele_IDPFormHideShowPriority 										= By.cssSelector("table[id='deliverables-header'] tr[ng-show='data.showHeader'] td:nth-child(3)");
		public static By 	chk_IDPFormHideShowFileMaskCheckBox									= By.cssSelector("div[class='pcontrol'] div[class='item-holder showHideMenu'] div[class='checkboxitem'] label input[ng-model='showMask']");
		public static By 	ele_IDPFormHideShowFileMask											= By.cssSelector("table[id='deliverables-body'] tr:nth-child(3) td:nth-child(8) span[ng-show='showMask']");
		public static By 	ele_IDPFormHideShowSection											= By.cssSelector("div[class='pcontrol'] item-selection[list='sectionListData']");
		public static By 	chk_IDPFormHideShowSectionDeliverableFirst							= By.cssSelector("div[class='pcontrol'] item-selection[list='sectionListData'] div[class='drop-show'] div[class='itemContainer'] label:nth-child(1) input");
		public static By 	ele_IDPFormHideShowSectionDeliverableFirstListing					= By.cssSelector("table[id='deliverables-body'] tbody tr[class='drow shade2 ng-scope']:nth-child(3)");
		public static By 	ele_IDPFormHideShowStages											= By.cssSelector("div[class='pcontrol'] item-selection[list='filterStageListData']");
		public static By 	chk_IDPFormHideShowStagesIdentificationCheckbox						= By.cssSelector("div[class='pcontrol'] item-selection[list='filterStageListData'] div[class='drop-show'] div[class='itemContainer'] label:nth-child(1) input");
		public static By 	ele_IDPFormHighlightedStageDeliverable								= By.cssSelector("table[id='deliverables-body'] tbody tr:nth-child(3) td[class='dstage ng-scope highlight']:nth-child(8)");
		
		
		
		/*
		public static By	txt_CreateIDPFormTemplateTitle										= By.cssSelector("div[id='custFormTD'] table[id='deliverables-header'] input[id='ORI_FORMTITLE']");
		public static By	btn_CreateIDPFormMoreOptions										= By.cssSelector("div[id='formParentDiv'] div[id='headerCreateFormDiv'] button[onclick*='moreoptions_form']");
		public static By	btn_CreateIDPFormMoreOptionsImportExcel								= By.cssSelector("div[id='moreoptions_form'] a[id='optionsmore-importFromExcel']");
		public static By	btn_BetaViewCreateIDPFormMoreOptionsImportExcel						= By.cssSelector("div[id='create-form-page'] div[class*='main-header'] button[title*='Import From Excel']");
		public static By	btn_CreateIDPFormMoreOptionsImportExcelBrowse						= By.cssSelector("div[id='moreoptions_form'] div[id='divformUploadXML'] input[id='txtImportXML']");
		public static By	btn_BetaViewCreateIDPFormMoreOptionsImportExcelBrowse				= By.cssSelector("div[id='create-form-page'] form[class*='formUploadXML'] input[id='txtImportXML']");
		public static By	btn_CreateIDPFormMoreOptionsImportExcelImport						= By.cssSelector("div[id='moreoptions_form'] div[id='divformUploadXML'] input[value='Import']");
		public static By	btn_BetaViewCreateIDPFormMoreOptionsImportExcelImport				= By.cssSelector("div[id='create-form-page'] div[class='ibox'] button[ng-click*='submitImportForm']");
		public static By	txt_CreateIDPFormTemplateRef										= By.cssSelector("div[id='custFormTD'] table[id='deliverables-header'] input[name='tplRef']");
		public static By	txt_CreateIDPFormTemplateRefTitle									= By.cssSelector("div[id='custFormTD'] table[id='deliverables-header'] input[name='tplTitle']");
		public static By	txt_CreateIDPFormTemplatePlanOfWork									= By.cssSelector("div[id='custFormTD'] table[id='deliverables-header'] input[name='powTitle']");
		public static By	css_CreateIDPFormTemplateStageRef									= By.cssSelector("div[id='custFormTD'] table[id='deliverables-header'] input[name='stgRef']");
		public static By	css_CreateIDPFormTemplateStageTitles								= By.cssSelector("div[id='custFormTD'] table[id='deliverables-header'] input[name='stgTitle']");
		public static By	txt_CreateIDPFormTemplateFirstDeliverableInput						= By.cssSelector("div[id='custFormTD'] table[id='deliverables-body']  tbody[class='ng-scope'] tr:nth-child(2) td[class='setitle'] input[name='secTitle']");
		public static By	img_CreateIDPFormTemplateAddDeliverableFirst						= By.cssSelector("div[id='custFormTD'] table[id='deliverables-body']  tbody[class='ng-scope'] tr:nth-child(2) td[class='setitle'] img[class='addDel']");
		public static By	css_CreateIDPFormTemplateFirstDeliverableDocuments					= By.cssSelector("div[id='custFormTD'] table[id='deliverables-body']  tbody[class='ng-scope'] tr[class*='deli-rows'] td[class='setitle'] input[name='delTitle']");
		public static By	ele_PopStageManageMentDatePickerActive								= By.cssSelector("div[class*='_720kb-datepicker-calendar-body'] a[class*='datepicker-active']");
		public static By	ele_PopStageManageMentDatePickerActiveNext							= By.cssSelector("div[class*='_720kb-datepicker-calendar-body'] a[class*='datepicker-active']+a");
		public static By	drp_CreateIDPFormProjectDetailsProjectTemplate						= By.cssSelector("div[id='projectdetails'] select[id='edtemplate']");
		public static By	txt_CreateIDPFormProjectDetailsRefNo								= By.cssSelector("div[id='projectdetails'] input[name='pref']");
		public static By	drp_CreateIDPFormProjectDetailsManager								= By.cssSelector("div[id='projectdetails'] select[id='edpmanager']");
		public static By	drp_CreateIDPFormProjectDetailsEditor								= By.cssSelector("div[id='projectdetails'] select[id='newman']");
		public static By	lnk_CreateIDPFormSecondDeliverable									= By.cssSelector("div[id='tbl-wrap'] table[id='deliverables-body'] tbody:nth-child(1) tr[class*='ng-scope']:nth-child(4) td[class='dtitle'] div[id='dtitle']");
		public static By	rad_PopItemDeliverableNativeFormat									= By.cssSelector("div[id='itemdeliverables'] div[class='m-body'] table[edform='itemDeliverables'] input[name='edNativeFile'][value='Y']");
		public static By	rad_PopItemDeliverableAssuranceFormat								= By.cssSelector("div[id='itemdeliverables'] div[class='m-body'] table[edform='itemDeliverables'] input[name='edAssurance'][value='Y']");
		public static By	drp_PopItemDeliverableCapability									= By.cssSelector("div[id='itemdeliverables'] div[class='m-body'] table[edform='itemDeliverables'] select[class*='edCapability']");
		public static By	drp_PopItemDeliverableOpenFormat									= By.cssSelector("div[id='itemdeliverables'] div[class='m-body'] table[edform='itemDeliverables'] select[class*='edOpenFormat']");
		public static By	drp_PopItemDeliverable3DModelFederation								= By.cssSelector("div[id='itemdeliverables'] div[class='m-body'] table[edform='itemDeliverables'] select[class*='edFileType']");
		public static By	txt_PopItemDeliverableDeliveryScope									= By.cssSelector("div[id='itemdeliverables'] div[class='m-body'] table[edform='itemDeliverables'] textarea[class*='edScope']");
		public static By	btn_PopItemDeliverableUpdate										= By.cssSelector("div[id='itemdeliverables'] footer[class='m-footer'] button[class*='update-btn']");
		public static By	ele_OpenStageDeliverablesEditDetails								= By.cssSelector("table[id='deliverables-body'] tbody:nth-child(1) tr:nth-child(3) td:nth-child(8)");
		public static By	drp_PopStageDeliverablesLOD											= By.cssSelector("div[id='stagedeliverables'] table[edform='itemStage'] select[id='edLodSelect']");
		public static By	btn_PopItemDeliverableAddItem										= By.cssSelector("div[id='itemdeliverables'] div[class='m-body'] button[ng-click*='addItem']");
		public static By	txt_PopDeliverableItemItemTitle										= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] input[class*='edItemTitle']");
		public static By	drp_PopDeliverableItemVolume										= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemVolume']");
		public static By	img_PopDeliverableItemVolumeAdd										= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemVolume']+img");
		public static By	txt_PopDeliverableItemVolumeCode									= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemVolume']+img+div[class*='add-entity'] input[placeholder='Code']");
		public static By	txt_PopDeliverableItemVolumeName									= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemVolume']+img+div[class*='add-entity'] input[placeholder='Name']");
		public static By	btn_PopDeliverableItemVolumeButtonAdd								= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemVolume']+img+div[class*='add-entity'] button[ng-click*='addEntity']");
		public static By	drp_PopDeliverableItemLocation										= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemLocation']");
		public static By	img_PopDeliverableItemLocationAdd									= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemLocation']+img");
		public static By	txt_PopDeliverableItemLocationCode									= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemLocation']+img+div[class*='add-entity'] input[placeholder='Code']");
		public static By	txt_PopDeliverableItemLocationName									= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemLocation']+img+div[class*='add-entity'] input[placeholder='Name']");
		public static By	btn_PopDeliverableItemLocationButtonAdd								= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemLocation']+img+div[class*='add-entity'] button[ng-click*='addEntity']");
		public static By	drp_PopDeliverableItemFileType										= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemType']");
		public static By	drp_PopDeliverableItemFileRole										= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] select[class*='edItemRole']");
		public static By	drp_PopDeliverableItemFileNumber									= By.cssSelector("div[id='subitemdeliverables'] div[class='m-body'] input[class*='edItemNumber']");
		public static By	btn_PopDeliverableItemUpdate										= By.cssSelector("div[id='subitemdeliverables'] footer[class='m-footer'] button[class*='update-btn']");
		public static By	ele_CreateIDPFormSecondStageSubDeliverable							= By.cssSelector("table[id='deliverables-body'] tr[class='irow ng-scope']:nth-child(6) td[class*='istage']:nth-child(8)");
		public static By	drp_PopStageItemAuthor												= By.cssSelector("div[id='subitemstage'] div[id='idpDropArea'] select[class*='edRoleSelect ']");
		public static By	txt_PopStageItemDueDate												= By.cssSelector("div[id='subitemstage'] div[id='idpDropArea'] datepicker[class='ng-isolate-scope'] input[class*='ang-datepciker']");
		public static By	lbl_PopStageItemFileMask											= By.cssSelector("table tbody tr:nth-child(9) td[class='ng-binding']");
		public static By	img_EditIDPFormSecondStageSubDeliverableColorIcon					= By.cssSelector("table[id='deliverables-body'] tr[class='irow ng-scope']:nth-child(6) td:nth-child(8) img[class='rag ng-scope']");
		public static By	lbl_CreateIDPTFormWindowHeader										= By.cssSelector("div[id='viewFormContain'] h2[id='formName']");
		public static By	lbl_CreateIDPTFormWindowFooterText									= By.cssSelector("table[id='deliverables-body'] tr[class='ng-scope']:nth-child(2) td");
		public static By	lnk_CreateControllerRespondByCalenderToday							= By.cssSelector("div[id='ui-datepicker-div'] table[class='ui-datepicker-calendar'] td[class*='ui-datepicker-today'] a[class*='ui-state-highlight']");
		public static By 	opt_ContextClickHoverOnEdit											= By.cssSelector("ul[class='context-menu-list context-menu-root'][style*='display: block;'] li[class*='CMicon-edit']:nth-child(3)");
		public static By 	opt_ContextClickOnEditStatus										= By.cssSelector("ul[class='context-menu-list context-menu-root'] li[class*='CMicon-edit hover']:nth-child(3) ul[class='context-menu-list'] li[class*='CMicon-status-Change'] span");*/

		/* Embeded Response Form */
		public static By	txt_CERFormTitle													= By.cssSelector("div[id='xdoc_view'] table[class*='xdFormLayout '] textarea[id='_0_1_my:ORI_FORMTITLE']");
		public static By	txt_CERFormRichTextBox												= By.cssSelector("form[id='myform'] div[id='xdoc_view'] span[class*='xdRichTextBox']");
		public static By	txt_CERFormGroupCode												= By.cssSelector("div[id='xdoc_view'] table[class='xdLayout'] input[id*='my:DS_FORMGROUPCODE']");
		public static By	txt_CERFormDueDatePicker											= By.cssSelector("form[id='myform'] div[id='xdoc_view'] div[class='xdDTPicker'] a[class='xdDTButton'] img");
		public static By	ele_CERFormDatePickerSelectedDate									= By.cssSelector("div[class='calendar'] tr[class='daysrow'] td[class*='selected']");
		public static By	btn_BetaViewCERFormAttachments										= By.cssSelector("div[class='pull-right'] span[class*='dropdown'] button[class='btn btn-default dropdown-toggle']");
		public static By	frm_BetaViewFilesAttachmentsFrame									= By.cssSelector("div[class='drag-file'] i[class='fa fa-upload']");
		public static By	css_BetaViewPopAssociateFilesCheckboxes								= By.cssSelector("div[class='assoc-body'] div[class='gbody'] ul li input[class='check-row']");
		public static By	css_BetaViewCERPopAssociateFilesFileNames							= By.cssSelector("div[class='assoc-body'] div[class='gbody'] ul li:nth-child(2) a[class='text-ellipsis']");
		public static By	btn_BetaViewCERFormDistribute										= By.cssSelector("div[id='create-form-page'] button[title='Distribute']");
		public static By	txt_BetaViewCERFormDistributeTo										= By.cssSelector("div[id='distInputTo'] div[class='dropdown-toggle'] input[class*='dist-inpt']");
		public static By    css_BetaViewFormDistributionToList									= By.cssSelector("div[id='distInputTo'] ul[class='user-list'] li[ng-repeat]");
		public static By	txt_BetaViewRFIFormDistributeAction									= By.cssSelector("div[id='distInputTo'] div[class='dropdown-toggle'] :nth-child(3)");
		public static By	lbl_CERFormViewEmailResponse										= By.cssSelector("div[id='xdoc_view'] span[id*='my:DS_EMAIL_RESPONSE']");
		public static By	lnk_FormViewRichTextView											= By.cssSelector("div[id='xdoc_view'] span[class*='xdRichTextBox']");
		public static By	lnk_BetaViewCreateFormMoreOptionsAssociateDocs						= By.cssSelector("div[id='create-form-page'] div[id='more-options-dd'] a[ng-click*='files']");
		public static By	lnk_BetaViewCreateFormAssociateDocsPaginationNext					= By.cssSelector("ul[class*='pagination'] a[ng-click='nextPage()']");
		public static By	lnk_BetaViewCreateFormAssociateDocsPaginationRecordCounter			= By.cssSelector("span[class*='record-counter']");
		public static By	txt_BetaViewCreateFormAssociateDocsSearch							= By.cssSelector("div[id='create-form-page'] div[class='assoc-body'] span[class='preselected-filters'] input[placeholder='Contains Text']");
		public static By	css_BetaViewCreateFormAssociateDocsListingCheckBoxes				= By.cssSelector("div[class='assoc-body'] div[class='gbody'] ul[class='repeated-item'] li input[class='check-row']");
		public static By	btn_BetaViewPopCreateFormAssociateDocsAssociate						= By.cssSelector("div[class*='submit-'] button[ng-click='associateAndClose($event)']");
		public static By	img_FormViewViaEmailReplyButton										= By.cssSelector("div[id='messageDetails'] form[name='frmChangeStatus'] img[title='Reply to Originator']");
		public static By	img_FormViewViaEmailReplyAllButton									= By.cssSelector("div[id='messageDetails'] form[name='frmChangeStatus'] img[title='Reply All']");
		public static By	txt_FormViewViaEmailResponseInput									= By.cssSelector("div[id='xdoc_view'] div[id*='my:myFields'] span[id*='my:DS_EMAIL_RESPONSE']");
		public static By	txt_FormViewViaEmailFormGroupCode									= By.cssSelector("div[id='xdoc_view'] input[class*='xdBehavior_Formatting']");
		public static By	btn_FormViewViaEmailResponseCreate									= By.cssSelector("form[name='myform'] table[id='moveContentTable'] input[id='btnSend']");
		/*
		public static By	frm_FilesAttachmentFrame											= By.cssSelector("div[id='myModal-Attachment']");
		public static By	btn_CERFormAttachments												= By.cssSelector("div[id='formParentDiv'] div[id='headerCreateFormDiv'] button[class*='showAttachmentLink']");
		public static By	frm_BetaViewFilesAttachmentFrame									= By.cssSelector("div[id='attachment-drop-zone']");
		public static By	chk_BetaViewQueryFormCheckBox										= By.cssSelector("input[id*='my:Query_Confidential,1']");
		public static By	chk_QueryFormCheckBox												= By.cssSelector("form[id='myform'] div[id='xdoc_view'] input[id*='Query_Confidential,1'][class='xdBehavior_Boolean']");
		public static By	btn_CERFormMoreOptions												= By.cssSelector("div[id='formParentDiv'] div[id='headerCreateFormDiv'] button[onclick*='moreoptions_form']");
		public static By	lnk_PopOptionsCERFormAssociateDocs									= By.cssSelector("div[id='moreoptions_form'][style*='display: block'] a[id='optionsmore-assocDoc']");
		public static By	css_CERPopAssociateFilesCheckboxes									= By.cssSelector("div[id='assocFileContent'] div[id='adTableBody'] div[class*='rows'] div[class*='filelistchkbox']");
		public static By	css_CERPopAssociateFilesFileNames									= By.cssSelector("div[id='assocFileContent'] div[id='adTableBody'] div[class*='rows'] div[class*='col-uploadFilename-fixed-width'] a");
		public static By	btn_CERPopAssociateSelectFilesSave									= By.cssSelector("div[id='assocFiles'] div[id='assocFilesModal'] div[id='assocFilesselectbutton'] button[id='save']");
		public static By	btn_CERPopAssociateSelectedFilesSave								= By.cssSelector("div[id='myModal-AssocDocs'] button[id='btnsaveAssocDocs']");
		public static By	btn_CERFormDistribute												= By.cssSelector("div[id='formParentDiv']  div[id='headerCreateFormDiv'] div[id='FormMoreOption'] button[class*='distribute-users']");
		public static By	txt_CERFormDistributeTo												= By.cssSelector("div[id='divDistributeFiles_form'] div[id='s2id_inptDistTo1']  input[id='s2id_autogen1']");
		public static By	lbl_CERFormViewViaLoginEmailResponse								= By.cssSelector("form[id='myform'] div[id='custFormTD'] div[id='xdoc_view'] span[id*='my:DS_EMAIL_RESPONSE']");
		public static By	lnk_FormViewLHFormDetails											= By.cssSelector("div[id='left-nav-blocks'] a[id='formnav-details']");
		public static By	lbl_FormViewLHFormDetailsFirstResponse								= By.cssSelector("div[id='formThreadTree'] div[id*='subComments']:nth-of-type(1) span[id='ctitle']");
		public static By	lbl_FormViewLHFormDetailsSecondResponse								= By.cssSelector("div[id='formThreadTree'] div[id*='subComments']:nth-of-type(2) span[id='ctitle']");*/

		/* Clear-Delegate Actions */
		public static By	lnk_FormViewLHFormHistory											= By.cssSelector("div[id='left-nav-blocks'] a[id='formnav-history']");
		public static By	btn_BetaViewFormViewFormHistory										= By.cssSelector("div[id='form-view-page'] button[title='History'] i");
		public static By	sel_FormViewHistoryType												= By.cssSelector("div[id='formAllHistorySection'] select[id='formHistoryTypeDDL']");
		public static By	sel_BetaViewFormViewHistoryType										= By.cssSelector("div[class*='dropdown-menu-right'] div[class*='ibox-title'] select[ng-change='typeChange()']");
		public static By	sel_BetaViewFormViewHistoryMsgID									= By.cssSelector("div[class*='dropdown-menu-right'] div[class*='ibox-title'] select[ng-change='msgCodeChange()']");
		public static By	drp_BetaViewViewFormAttachmentAssociationMsgID						= By.cssSelector("main[class='open'] div[class*='dropdown-menu-right'] div[class*='ibox-title'] span[class='title-actions'] select");
		public static By	chk_BetaViewViewFormAttachmentAssociationMultiCheckbox				= By.cssSelector("div[class*='ibox-content'] div[class='tab-content'] div[class*='active'] ul[class*='ng-isolate-scope'] input[type='checkbox']");
		public static By	btn_BetaViewViewFormAttachmentAssociationDownloadButton				= By.cssSelector("file-download[conf='downloadOpt'] div[class*='dropdown'] button[title='Download'] i[class*='fa-download']");
		public static By	btn_BetaViewFormViewHistoryResultsDelegateActionDatepicker			= By.cssSelector("div[id='form-view-page'] main[class='open'] form-history[class*='history-panel'] div[class*='ghead'] input[class*='ang-datepciker'][type='text']");
		public static By	btn_BetaViewFormViewHistoryResultsDelegateActionActiveDate			= By.cssSelector("div[id='form-view-page'] main[class='open'] div[class*='datepicker-open'] div[class*='datepicker-calendar-body'] a[class*='datepicker-active']");
		public static By	btn_BetaViewFormViewHistoryResultsDelegateActionBulkApplyDateIcon	= By.xpath("//main[@class='open']//form-history//li[contains(@class,'gldate')]//i[contains(@class,'fa-arrow-down')]");
		public static By	chk_BetaViewFormViewHistoryResults									= By.cssSelector(" :nth-child(1) input");
		public static By	css_BetaViewFormViewHistoryResultsActionStatus						= By.cssSelector(" :nth-child(5)");
		public static By	chk_FormViewHistoryMsgIDCheckbox									= By.cssSelector("div[class*='dropdown-menu-right'] form-history[class*='history-panel'] div[class*='ghead'] input[type='checkbox']");
		public static By	css_BetaViewFormViewHistoryResults									= By.cssSelector("div[class*='dropdown-menu-right'] form-history[class*='history-panel'] div[class='ng-scope'] ul");
		public static By	btn_BetaViewFormViewHistoryResultsClose								= By.cssSelector("div[id='form-view-page'] main[class='open'] div[class*='dropdown-menu-right'] div[class*='ibox-title'] button[class*='close-link']");
		public static By	btn_BetaViewFormViewHistoryResultsDelegateActionClose				= By.xpath(".//div[@id='form-view-page']//main[contains(@class,'open')]//form-history[contains(@class,'history-panel')]//div[contains(@class,'delegate-controls')]//button[text()='Close']");
		public static By	css_FormHistoryRecordsListRemarks									= By.cssSelector("div[id='formAllHistoryList'] div[class='historyRow'] div[class='subInfo'] span");
		public static By	css_BetaViewFormViewHistoryDistributionRowList						= By.cssSelector("div[class='ibox'] div[class='history-container'] div[class='gbody'] ul[class*='grow']");
		public static By	chk_BetaViewFormHistoryRecordCheckbox								= By.cssSelector("div[class='ibox'] div[class='history-container'] div[class='gbody'] li[class='gcheck'] input");
		public static By	lbl_BetaViewFileHistoryRecordDetailRecipient						= By.cssSelector("div[class='gbody'] li[class='gpuser']");
		public static By	lbl_BetaViewFileHistoryRecordDetailActionStatus						= By.cssSelector("li[class*='gstatus'][title='']:nth-child(5)");
		public static By	chk_FormViewHistorycheckboxListCheckbox								= By.cssSelector(" :nth-child(1) input[type='checkbox']");
		public static By	chk_FormViewHistorycheckboxListActionTitle							= By.cssSelector(" :nth-child(4)");
		public static By	chk_FormViewHistorycheckboxListActionStatus							= By.cssSelector(" :nth-child(5)");
		public static By	btn_BetaViewFormViewHistoryActionDropdown							= By.cssSelector("div[class*='dropdown-menu-right'] div[class*='ibox-title'] span[class='title-actions'] span[class*='dist-history-action'] button[title='More Options']");
		public static By	opt_BetaViewFormViewHistoryDeactivateAction							= By.cssSelector("div[class='ibox'] span[class='title-actions'] li[ng-click*='deactiveAction'] a");
		public static By	img_BetaViewActionLoading											= By.cssSelector("div[class='ibox'] span[class='title-actions'] span[class*='loading']");
		public static By	opt_BetaViewFormViewHistoryReactivateAction							= By.cssSelector("div[class='ibox'] span[class='title-actions'] li[ng-click*='reactiveAction'] a");
		public static By	sel_BetaViewFormViewHistoryDelegateAction							= By.xpath(".//div[contains(@class,'dropdown-menu-right')]//span[contains(@class,'dist-history-action')]//ul//a[text()='Delegate Tasks']");
		public static By	sel_BetaViewFormViewHistoryClearAction								= By.xpath(".//div[contains(@class,'dropdown-menu-right')]//span[contains(@class,'dist-history-action')]//ul//a[text()='Clear Tasks']");
		public static By	Pop_BetaViewFormViewHistoryDelegateActionsTo						= By.cssSelector("div[class*='dropdown-menu-right'] form-history[class*='history-panel'] div[class*='delegate-controls'] input[class*='dist-inpt']");
		public static By	rad_BetaViewFormViewHistoryDelegateActionsExistingDueDays			= By.xpath(".//div[@id='form-view-page']//main[@class='open']//form-history[contains(@class,'history-panel')]//label[contains(text(),'Existing Due Date')]//input");
		public static By	rad_BetaViewFormViewHistoryDelegateActionsRecalculate				= By.xpath(".//div[@id='form-view-page']//main[@class='open']//form-history[contains(@class,'history-panel')]//label[contains(text(),'Re-calculate days')]//input");
		public static By	rad_BetaViewFormViewHistoryDelegateActionsUserDefinition			= By.xpath(".//div[@id='form-view-page']//main[@class='open']//form-history[contains(@class,'history-panel')]//label[contains(text(),'User Definition')]//input");
		public static By	css_FormViewHistoryDelegateActions									= By.cssSelector("div[class*='dropdown-menu-right'] form-history[class*='history-panel'] div[class*='delegate-controls']");
		/*
		public static By	rad_FormViewHistoryDelegateActionsExistingDueDays					= By.cssSelector("div[class='action-due-date-container'] input[type='radio'][value='existing_due_date']");
		public static By	rad_FormViewHistoryDelegateActionsRecalculate						= By.cssSelector("div[class='action-due-date-container'] input[type='radio'][value='re-calculate']");
		public static By	rad_FormViewHistoryDelegateActionsUserDefinition					= By.cssSelector("div[class='action-due-date-container'] input[type='radio'][value='user_define']");
		public static By	btn_FormViewHistoryDelegateActionsContinue							= By.cssSelector("form[id='delegateActionForm'] div[class='button-container'] button[id='btnDelegateContinue']");
		public static By	btn_FormViewHistoryDeReactivateContinue								= By.cssSelector("div[id='modalDisplayDeReactivateAction'] button[id='btnDeReactivateAction']");
		public static By	btn_FormViewHistoryClearActionsContinue								= By.cssSelector("div[id='formClearActionModal'] button[id='btnClearContinue']");
		public static By	btn_FormViewHistoryDelegateActionsOk								= By.cssSelector("div[id='formDelegateConfirmation'] button[class*='btn']");
		public static By	btn_FormViewHistoryClearActionsOk									= By.cssSelector("div[id='formClearConfirmation'] button[class*='btn']");
		public static By	pop_FormViewHistoryDelegateActions									= By.cssSelector("div[id='formDelegateAction'] div[class='modal-header'] h3");
		public static By	pop_FormViewHistoryClearActions										= By.cssSelector("div[id='formClearActionModal'] div[class='modal-header'] h3");
		public static By	sel_FormViewHistoryActionDropdown									= By.cssSelector("div[id='formAllHistoryList'] :nth-child(1) div[class*='floatright'] div[id='actionsDropdown']");
		public static By	opt_FormViewHistoryDeactivateAction									= By.cssSelector("div[id='formAllHistoryList'] div[class*='floatright'] ul[class*='dropdown-menu'] li[class*='deactiaveAction'] span");
		public static By	sel_FormViewHistoryDelegateAction									= By.cssSelector("div[id='formAllHistoryList'] div[class*='floatright'] ul[class*='dropdown-menu'] li[class*='deleagateAction'] span");
		public static By	css_FormViewHistoryActionRemarksList								= By.cssSelector("div[id='formAllHistoryList'] div[class='historyRow']:nth-child(2) div[class*='actionTable'] div[class='actionRow'] :nth-child(8) span[class='textOverflow']");
		public static By	sel_FormViewHistoryClearAction										= By.cssSelector("div[id='formAllHistoryList'] :nth-child(1) div[class*='floatright'] ul[class*='dropdown-menu'] li[class*='clearAction'] span");
		public static By	Pop_FormViewHistoryDelegateActionsTo								= By.xpath(".//form[@id='delegateActionForm']//div[contains(@id,'users')]//ul[@class='select2-choices']//li[contains(@class,'search-field')]//input");
		public static By	opt_FormViewHistoryReactivateAction									= By.cssSelector("div[id='formAllHistoryList'] div[class*='floatright'] ul[class*='dropdown-menu'] li[class*='reactiveAction'] span");
		public static By	css_FormViewHistoryResults											= By.cssSelector("div[id='formAllHistoryList'] div[class='historyRow']");
		public static By	css_FormViewAttachmentsResults										= By.cssSelector("div[id='formAllAssociationList'] div[class*='associationRow']");
		public static By	css_FormViewHistoryDistributionRowList								= By.cssSelector("div[id='formAllHistorySection'] div[id='formAllHistoryList'] div[class='actionTable historyMax'] div[class='actionRow']");
		public static By	css_FormViewHistoryActionlist										= By.cssSelector("div[id='formAllHistoryList'] div[class*='actionTable'] div[class='actionRow'] :nth-child(3)");
		public static By	css_FormViewHistoryActionStatusList1								= By.cssSelector("div[id='formAllHistoryList'] div[class*='actionTable'] div[class='actionRow'] :nth-child(5)");
		public static By	css_FormViewHistoryActionStatusList2								= By.cssSelector("div[id='formAllHistoryList'] :nth-child(1) div[class*='actionTable'] div[class='actionRow'] :nth-child(5)");
		public static By	chk_FormViewHistorycheckboxList1									= By.cssSelector("div[id='formAllHistoryList'] div[class*='actionTable'] div[class='actionRow'] :nth-child(1) input[type='checkbox']");
		public static By	chk_FormViewHistorycheckboxList2									= By.cssSelector("div[id='formAllHistoryList'] :nth-child(1) div[class*='actionTable'] div[class='actionRow'] :nth-child(1) input[type='checkbox']");
		public static By	lnk_FormViewLHFormAttachment										= By.cssSelector("div[id='left-nav-blocks'] a[id='formnav-association']");
		public static By	btn_FormViewHistoryResultsDelegateActionDatepicker					= By.cssSelector("div[id='formDistDelegateContent'] div[class*='outerContainer'] input[class*='due-date-user-define']");
		public static By	btn_FormViewHistoryResultsDelegateActionActiveDate					= By.cssSelector("div[id*='ui-datepicker'] td[class*='ui-datepicker-today'] a");
		public static By	btn_FormViewHistoryResultsDelegateActionBulkApplyDateIcon			= By.cssSelector("div[id='formDistDelegateContent'] div[class*='outerContainer'] a[class='apply-duedate-all']");
		public static By	sel_FormViewAttachmentsType											= By.cssSelector("div[id='formAllAssociationSection'] select[id='formAssociationTypeDDL']");
		public static By	sel_FormViewHistoryMsgID											= By.cssSelector("select[id='historyMsgDDl']");
		public static By	sel_FormViewAttachmentsMsgID										= By.cssSelector("select[id='allAssociationMsgDDl']");*/


		/* Create Multiple Replies */
		public static By	btn_BetaViewCreateFormMoreOptions									= By.cssSelector("div[id='more-options-dd'] button[id='create-form-options']");
		public static By	css_BetaViewPopCreateFormAttachmentsFileNameList					= By.cssSelector("div[class='selected-files'] ul[class*='ng-scope'] li[class*='gfname ']");
		public static By	css_BetaViewPopCreateFormAssociateDocsDocTitleList					= By.cssSelector("div[class='tab-content'] div[class*='ng-scope active'] ul[class='repeated-item'] :nth-child(5) span[class='text-ellipsis']");
		public static By	css_BetaViewPopCreateFormAssociateDiscussionsTitleList				= By.cssSelector("div[class*='listingContainer'] ul[class='repeated-item'] :nth-child(4) a[class='text-ellipsis']");
		public static By	css_BetaViewPopCreateFormAssociateFormsFormTitleList				= By.cssSelector("div[class='tab-content'] div[class*='listingContainer'] ul[class='repeated-item'] :nth-child(5) a[class='text-ellipsis']");
		public static By	css_RFIAssignedActionsListLink										= By.cssSelector("div[class*='popover'][style*='display: block'] div[class='popover-content']");
		public static By	btn_BetaViewCreateFormAttachmentClipIcon							= By.cssSelector("div[id='create-form-page'] button[title='Attachment']");
		public static By	btn_BetaViewCreateFormAssociationIcon								= By.cssSelector("div[id='create-form-page'] div[id*='more-options'] button[id*='create-form-options']");
		public static By	btn_BetaViewCreateFormAttachmentsSelectFiles						= By.xpath(".//span[contains(@class,'open')]//attachment//span[text()='Select File (s)']//following-sibling::input[@id='inptFileUpload']");
		public static By	lbl_CreateFormUploadSucessMsg										= By.cssSelector("div[class*='ui-notification'] div[class*='bold-msg']");
		public static By	lnk_BetaViewCreateFormFilesListingRowDocRef							= By.xpath(".//a[not(@title)]");
		public static By	chk_BetaViewCreateFormFilesListingRowchecbox						= By.cssSelector("input[class='check-row'][type='checkbox']");
		public static By	lbl_BetaViewCreateFormFilesFolderLabel								= By.cssSelector("ol[class*='breadcrumb'] b[class='ng-binding ng-scope']");
		public static By	css_BetaViewCreateFormAttributeHeaderLabelList						= By.xpath(".//association//table-listing//div[@class='ghead']//ul//li");
		public static By	lnk_BetaViewCreateFormAssociateDoc									= By.cssSelector("ul[class*='dropdown'] li:nth-child(1) a");
		public static By	lnk_BetaViewFormDetailsAttachmentsLink								= By.cssSelector("div[class*='component-wrapper'] button[title='Attachments & Associations'] i");
		public static By	btn_BetaViewCreateFormAssociatedDocSave								= By.cssSelector("button[ng-click*='associate']");
		public static By	btn_BetaViewCreateFormAssociatedAndCloseDocSave						= By.cssSelector("button[ng-click='associateAndClose($event)']");
		public static By	lbl_FormAddSuccessMsg												= By.cssSelector("div[id='adoddleTopMsgBox'] span");
		public static By	lbl_BetaViewFormAssociateAndAttachmentDiv							= By.cssSelector("div[class='assoc-panel-wrapper']");
		public static By	chk_BetaViewSingleFileAssociateDoc									= By.cssSelector("ul[class='repeated-item']:nth-child(1) li input[class='check-row']");
		public static By	btn_SendButtonCreateForm											= By.cssSelector("div[id='btnsaveCancelForm'] button[id='btnSaveForm']");
		/*
		public static By	btn_CreateFormMoreOptions											= By.cssSelector("div[id='headerCreateFormDiv'] div[id='FormMoreOption'] button[class='btn-mini btn-inverse']");
		public static By	css_PopCreateFormAttachmentsFileNameList							= By.cssSelector("div[class='selected-files'] ul[class*='ng-scope'] li[class*='gfname ']");
		public static By	css_PopCreateFormAssociateDocsDocTitleList							= By.cssSelector("div[class='tab-content'] div[class*='ng-scope active'] ul[class='repeated-item'] :nth-child(5) span[class='text-ellipsis']");
		public static By	css_PopCreateFormAssociateDiscussionsTitleList						= By.cssSelector("div[class='tab-content'] div[class*='listingContainer'] ul[class='repeated-item'] :nth-child(4) a[class='text-ellipsis']");
		public static By	css_PopCreateFormAssociateFormsFormTitleList						= By.cssSelector("div[class='tab-content'] div[class*='listingContainer'] ul[class='repeated-item'] :nth-child(5) a[class='text-ellipsis']");
		public static By	btn_BetaViewCreateFormAttachmentsSelectFiles						= By.cssSelector("div[id='create-form-page'] input[id='inptFileUpload']");
		public static By	css_RFIViewerLHPanelAttachmentsList									= By.cssSelector("div[id='formAllAssociationList'] div[class='panelBody'] div[class*='associationRow']");
		public static By	chk_RFIViewerMultilAttachmentsSelectChekbox							= By.cssSelector("div[id='formAllAssociationList'] input[id='selAllAssociation']");
		public static By	img_RFIViewerMultilAttachmentsDownloadIcon							= By.cssSelector("div[id='formAllAssociationList'] img[src*='images/icons/downloads.png']");
		public static By	pop_RFIViewerMultilAttachmentsDownloadFiles							= By.cssSelector("div[id='myModal-download'] div[class='modal-header'] h3");
		public static By	lbl_ViewFormActiveTabLabel											= By.cssSelector("div[class*='fullwidth'] h2[id='formName']");
		public static By	chk_SingleFileAssociateDoc											= By.cssSelector("div[id='assocFileContent'] div[class*='rows']:nth-child(1) div[class*='filelistchkbox'] input");
		public static By	btn_AssociateDocFinalSave											= By.cssSelector("div[id='myModal-AssocDocs'] button[id='btnsaveAssocDocs']");
		public static By	css_RFIViewerActionDetailsList										= By.cssSelector("div[class*='popover'][style*='display: block'] div[class='popover-content'] a");
		public static By	lnk_CreateFormAssociateDoc											= By.cssSelector("div[id='moreoptions_form'] div[class='modal-body'] a[id='optionsmore-assocDoc']");
		public static By	lnk_RFIFormDetailsActionCompletionLink								= By.cssSelector("div[id='viewFormHeaderTable'] div[class*='intro-scheme'] a[id='formActionDisplayevent']");
		public static By	lnk_FormDetailsAttachmentsLink										= By.cssSelector("div[class='assocHeader'] div[id='Attachments'] a");
		public static By	css_RFIFormDetailsCompletedActionList								= By.cssSelector("div[class='popover-content'] table:nth-child(2) tr");
		public static By	css_RFIFormDetailsCompletedActionName								= By.cssSelector("td[style='color: black;']");
		public static By	btn_CreateFormAssociatedDocSave										= By.cssSelector("div[id='assocFileFilterContainer'] div[id='assocFilesselectbutton'] button[id='save'][class='btn btn-danger pull-left']");
		public static By	lbl_BetaViewFormActiveTabLabel										= By.cssSelector("div[class='main-header clearfix']");*/


		/* Dashboard Favourite Forms Widget */
		public static By	ele_PopCreateFormFormtypeLabel										= By.xpath(".//div[@id='headerCreateFormDiv']//table//tr[1]//td[2][text()]");
		public static By	ele_BetaViewPopCreateFormFormtypeLabel								= By.xpath(".//div[@id='create-form-page']//h1[@title]");
		public static By	sel_PopStatusChangeDropdown											= By.xpath(".//div[contains(@style,'display: block')]//select[contains(@id,'StatusChangeDropdown')]");
		public static By	css_PopStatusChangeDropdownOptionList								= By.xpath(".//div[contains(@style,'display: block')]//select[contains(@id,'StatusChangeDropdown')]//option");
		public static By	txt_PopChangeStatusReasonInput										= By.xpath(".//div[contains(@style,'display: block')]//textarea[contains(@id,'newstatus_reason')]");
		public static By	btn_PopChangeStatusButton											= By.xpath(".//div[contains(@style,'display: block')]//button[text()='Change Status']");

		/* Sorting Locators */
		public static By	css_FormsTabFormTypeList											= By.xpath(".//div[@index]//div[contains(@class,'formTypeName')][text()]");
		public static By	css_FormsTabStatusList												= By.xpath(".//div[@index]//div[contains(@class,'status')][text()]");
		public static By	css_FormListingDistributionTabAssignedByList						= By.xpath(".//div[@index]//div[contains(@class,'assignedBy')]//a[text()]");

		/* Direct Link Form */
		public static By	ele_BetaViewViewFormDirectLink										= By.xpath(".//div[@id='form-holder']//span[contains(@class,'direct-link')]//following::a[@title]");
		public static By	lnk_BetaViewCreateFormDistributeToCloseButton						= By.xpath(".//div[@id='distInputTo']//div[contains(@class,'bulk-apply')]/a[text()='Close']");
		public static By	lnk_BetaViewCreateFormDistributeToClearButton						= By.xpath(".//div[@id='distInputTo']//div[contains(@class,'bulk-apply')]/a[text()='Clear']");
		public static By	ele_BetaViewViewFormTitle											= By.xpath(".//div[@id='form-view-page']//h1//span[contains(@class,'scope')][text()]");
		public static By	btn_BetaViewViewFormHistoryButton									= By.xpath(".//div[contains(@class,'component-wrapper')]//button[@title='History']");
		public static By	btn_BetaViewFormViewFormDetailsButton								= By.xpath(".//div[contains(@class,'component-wrapper')]//button[@title='Form Details']");
		public static By	ele_BetaViewViewFormSingleActionButton								= By.xpath(".//div[@id='form-view-page']//main[@class='open']//form-thread//form-action-list//button[@disabled='disabled']//span[text()]");
		public static By	msg_BetaViewDirectLinkNotAccessableMessage							= By.xpath(".//div[@id='formWrapper'][text()]");
		/*
		public static By	ele_ViewFormDirectLink												= By.xpath(".//div[@id='accordion']//span[@class='formdirectLink'][@title='Copy Link'][@directlink]");
		public static By	ele_ViewFormActionsPopup											= By.xpath(".//div[contains(@style,'display: block;')]//div[@class='popover-content']//div");
		public static By	css_ViewFormActionsPopupCompletedActionsList						= By.xpath(".//div[contains(@style,'display: block;')]//div[@class='popover-content']//div//td[text()]");
		public static By	ele_ViewFormHistoryTabSecondRowRemarks								= By.xpath(".//div[@id='formAllHistoryList']//div[@class='historyRow'][2]//div[contains(@class,'historyRemark')]//span[text()]");
		public static By	ele_BetaViewFormsDistributeHistoryFirstRecipientAction				= By.xpath(".//div[@id='form-view-page']//main[@class='open']//div[@class='gbody']//div[@class='ng-scope'][1]//ul//li[contains(@class,'gstatus')][text()][not(@title)]");
		public static By	css_BetaViewFormsDistributeHistoryRecipientActionList				= By.xpath(".//div[@id='form-view-page']//main[@class='open']//div[@class='gbody']//li[contains(@class,'gstatus')][text()][not(@title)]");
		public static By	ele_BetaViewFormsDistributeHistoryFirstActionStatus					= By.xpath(".//div[@id='form-view-page']//main[@class='open']//div[@class='gbody']//div[@class='ng-scope'][1]//ul//li[contains(@class,'gstatus')][text()][@title]");
		public static By	css_BetaViewFormsDistributeHistoryActionStatusList					= By.xpath(".//div[@id='form-view-page']//main[@class='open']//div[@class='gbody']//li[contains(@class,'gstatus')][text()][@title]");*/

		/* Recent Forms */
		public static By	btn_BetaViewViewFormActionDropdownReplyAllButton					= By.xpath(".//div[@id='form-view-page']//div[@id='form-holder']//ul/li[contains(@ng-if,'canReplyAll')]//a");
		public static By	txt_RespondFormRespondMessageInput									= By.xpath(".//input[contains(@id,'my:field')][@class='xdTextBox']");
		public static By	msg_BetaViewFormActionCompletedSuccessfulMessage					= By.xpath(".//html//body//div[@ng-bind-html='message']//div[text()]");
		public static By	ele_BetaViewViewFormContent											= By.xpath(".//textarea[contains(@id,'FORMTITLE')][text()]");
		/*public static By	btn_ViewFormActionDropdownReplyAllButton							= By.xpath(".//div[@id='accordion']//span[@class='form-action-li-span'][text()='Reply All']");*/

		/* Deacticate Reactivate Actions */
		public static By	css_PopFormDistributeToggleDropdowns								= By.cssSelector("div[id='formScroll'] div[id='distInputTo'] ul[class='select2-choices'] button[class*='dropdown-toggle']");
		public static By	drp_PopFormDistributeToggleActionDropDown							= By.cssSelector("ul[class*='context-menu-root'][style*='z-index: 10'] li[class*='CMicon-actions'] select[name='context-menu-input-actions']");

		/* Create Workflow */
		public static By    lnk_FormListingMoreOptions											= By.cssSelector("a[id='comunnication_moreoption'][title='More Options']");
		public static By	img_FormsTabFirstFormAssociationImage								= By.xpath(".//div[@index='0']//div[contains(@class,'attachment')]//img");
		//public static By	lnk_PopMoreOptionsProjectFormLink									= By.xpath(".//div[contains(@style,'display: block')]//a[@id='optionsmore-associateToApp']");
		public static By	lnk_PopMoreOptionsProjectFormLink									= By.cssSelector("a[id='comunnication_moreoption']+div a[id='optionsmore-associateToApp']");

		/* Form All Incomplete Actions */
		public static By	css_BetaViewFormsDistributeHistoryIncompleteActionStatusList		= By.xpath(".//div[@id='form-view-page']//div[@class='history-container']//div[@class='gbody']//div//ul[@class='grow dist']//li[contains(@class,'gstatus')][@title!=text()]");
		public static By	css_BetaViewFormsDistributeHistoryIncompleteActionsList				= By.xpath(".//div[@id='form-view-page']//div[@class='history-container']//div[@class='gbody']//div//ul[@class='grow dist']//li[contains(@class,'gstatus') and not(contains(@class,'scope'))][@title=text()]");
		public static By	frm_BetaViewReplyFormIframe											= By.xpath("//iframe[@id='reply-frame']");
		public static By	css_BetaViewRespondFormPrePopulatedUserList							= By.xpath(".//div[@id='distInputTo']//dist-select//div[@class='dropdown-toggle']/ul//li//span[1][text()]");
		public static By	lbl_BetaViewViewFormActiveTabLabel									= By.xpath(".//div[@id='form-view-page']//main[@class='open']//div[@class='ibox-title clearfix']//h5[@class][text()]");
		public static By	drp_BetaViewHistoryTabHistoryTypeDropdown							= By.xpath(".//div[@id='right-content']//main[@class='open']//div[@class='ibox-title clearfix']//select[1]");
		public static By	btn_BetaViewViewFormUpdatedStatus									= By.xpath(".//div[@id='form-view-page']//custom-common-dropdown//button[@title='Status Change']");
		public static By	ele_BetaViewViewFormUpdatedStatus									= By.xpath(".//div[@id='form-view-page']//custom-common-dropdown//button[@title='Status Change']//span[contains(text(),'Status')]");
		public static By	btn_BetaViewViewFormAttachmentAssociationButton						= By.xpath(".//div[contains(@class,'component-wrapper')]//button[@title='Attachments & Associations']");
		public static By	css_BetaViewViewFormAttachmentAndAssociationTabAssociateFileList	= By.xpath(".//div[@id='form-view-page']//main[@class='open']//div[@class='ibox-content clearfix']//div[contains(@class,'active')]//ul//li//div[@class='text-ellipsis']//a[text()]");
		public static By	css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList		= By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='ibox']//div[contains(@class,'active')]//ul//li//a[@class='text-ellipsis'][text()]");
		public static By	ele_BetaViewViewFormHistoryTabFirstRowStatusRemarks					= By.xpath(".//div[@id='right-content']//main[@class='open']//div[@class='gbody']//ul[1]//li[contains(@class,'remarks')][text()]");
		/*
		public static By	css_FormsDistributeHistoryIncompleteActionStatusList				= By.xpath(".//div[@id='formAllHistoryList']//div[@class='actionTable historyMax']//div[text()][2]");
		public static By	css_FormsDistributeHistoryIncompleteActionsList						= By.xpath(".//div[@id='formAllHistoryList']//div[@class='actionTable historyMax']//div[text()][1]");
		public static By	css_FormAllHistoryList												= By.xpath(".//div[@id='formAllHistoryList']//div//div[@class=''][text()]");
		public static By	css_FormHistorySelectedRowCompleteActionStatusList					= By.xpath(".//div[@id='formAllHistoryList']//div[contains(@class,'rowSelected')]//div[@class='actionTable historyMax']//span[contains(@title,',')][text()]");
		public static By	css_FormHistorySelectedRowCompleteActionsList						= By.xpath(".//div[@id='formAllHistoryList']//div[contains(@class,'rowSelected')]//div[@class='actionTable historyMax']//div[text()][1]");
		public static By	drp_BetaViewViewFormHistoryTabHistoryTypeMsgID						= By.xpath(".//div[@id='right-content']//main[@class='open']//div[@class='ibox-title clearfix']//select[2]");
		public static By	lnk_ViewFormUpdatedStatus											= By.xpath(".//div[@id='viewFormHeaderTable']//a[@class='openFormStatus']//span[text()]");
		public static By	ele_HistoryTabFirstRowNewStatus										= By.xpath(".//div[contains(@id,'AllHistoryList')]/div[contains(@class,'historyRow')][1]//div[contains(@class,'floatleft')]//span[text()]");*/

		/* Single \ Batch Form Status */
		public static By	lnk_FormsTabPopMoreOptionsActionsChangeStatusLink					= By.xpath(".//div[contains(@style,'display: block')]//a[contains(@id,'formsbatchchangestatus')][not(contains(@class,'disabled'))]");
		public static By	lnk_FormsTabPopMoreOptionsActionsForInformationLink					= By.cssSelector("a[title='More Options']+div a[id='optionsmore-for-formsinfo']");
		public static By	css_FormsTabCheckboxList											= By.xpath(".//div[@index]//div[contains(@class,'chkbox')]//input[@type='checkbox']");
		public static By	css_PopStatusChangeChangeableFormsList								= By.xpath(".//div[contains(@style,'display: block')]//tbody[@id='commNameListForStatus']//td[last()]");
		public static By	btn_BetaViewViewFormStatusChangeButtonDisabled						= By.xpath(".//div[@id='form-view-page']//div[contains(@class,'header')]//button[contains(@ng-if,'canChangeStatus')]//span[text()]");
		public static By	lnk_BetaViewViewFormHistoryORI										= By.cssSelector("form-thread ul[class*='thread-list'] li:nth-child(1) a");
		public static By	lnk_BetaViewViewFormHistoryORIFWD									= By.cssSelector("form-thread ul[class*='thread-list'] li:nth-child(2) a");
		public static By	lnk_BetaViewViewFormHistoryFWDRES1									= By.cssSelector("form-thread ul[class*='thread-list'] li:nth-child(3) a");
		public static By	lnk_BetaViewViewFormHistoryORIRES1									= By.cssSelector("form-thread ul[class*='thread-list'] li:nth-child(4) a");
		/*public static By	lnk_FormsTabViewFormStatusHyperLink									= By.xpath(".//div[@id='viewFormHeaderTable']//a[@role='link']//span[text()]");*/

		/* Cloned Inheritance Workspace Objects */
		public static By	txt_AutoTestAppTemplateInternalRef									= By.cssSelector("tr[style*='MIN-HEIGHT:']:nth-child(1) textarea[title*='Enter Your Internal Reference']");
		public static By	txt_AutoTestAppTemplateTitle										= By.cssSelector("tr[style*='MIN-HEIGHT:']:nth-child(2) textarea[id*='ORI_FORMTITLE']");
		public static By	txt_AutoTestAppTemplateDescription									= By.cssSelector("tr[style*='MIN-HEIGHT:']:nth-child(3) input[class='xdTextBox']");
		public static By	txt_AutoTestAppTemplateRespondDueDate								= By.cssSelector("tr[style*='MIN-HEIGHT:']:nth-child(4) input[class='xdTextBox']");
		public static By	txt_AutoTestAppTemplateReply										= By.cssSelector("form[id='myform'] div[id='xdoc_view'] input[class='xdTextBox']");
		public static By	css_FormViewerFormActionsList										= By.cssSelector("div[class='action-btn-wrapper'] ul[class*='dropdown-menu-right'] li[class='ng-scope']");
		public static By	sel_DistributeContextClickActionsList								= By.cssSelector("ul[class*='context-menu-root'][style*='z-index'] li[clasS*='CMicon-actions'] select[name='context-menu-input-actions'] option");

		/* Create Multi Function Form */
		public static By	txt_MultiFunctionFormUserRef										= By.cssSelector("input[id*='ORI_USERREF'][class='xdTextBox']");
		public static By	txt_MultiFunctionFormTitle											= By.cssSelector("input[id*='ORI_FORMTITLE'][class='xdTextBox']");
		public static By	btn_MultiFunctionFormSetFormTitle									= By.cssSelector("input[id='InfoJet_Control1']");
		public static By	lnk_MultiFunctionFormAttachSingleFile								= By.cssSelector("a[id*='_my:Attachment_Single']");
		public static By	lnk_MultiFunctionFormAttachMultipleFiles							= By.cssSelector("a[id*='my:Document'][href*='javascript']");
		public static By	img_MultiFunctionFormInsertItem										= By.cssSelector("div[id='InfoJetPlaceholder0'] img:first-child");
		public static By	txt_MultiFunctionFormAttachFileUpload								= By.cssSelector("input[id='UploadFile'][type='file']");
		public static By	btn_MultiFunctionFormAttachFileOK									= By.cssSelector("input[id='Submit1']");
		public static By	txt_MultiFunctionFormYourSalary										= By.cssSelector("input[id*='my:Your_Salary'][class*='xdTextBox']");
		public static By	txt_MultiFunctionFormPartnerSalary									= By.cssSelector("input[id*='my:Partner_Salary'][class*='xdTextBox']");
		public static By	txt_MultiFunctionFormTotalNumber									= By.cssSelector("span[id*='InfoJetExprBox1']");
		public static By	txt_MultiFunctionFormNumber1										= By.cssSelector("input[id*='Wcallback_Field1'][class*='xdTextBox']");
		public static By	txt_MultiFunctionFormNumber2										= By.cssSelector("input[id*='Wcallback_Field2'][class*='xdTextBox']");
		public static By	txt_MultiFunctionFormTotalOfNumber									= By.cssSelector("input[id*='Wcallback_Field3'][class*='xdTextBox']");
		public static By	txt_MultiFunctionFormChildFormTitle									= By.cssSelector("input[id*='Child_Form_Title'][class*='xdTextBox']");
		public static By	drp_MultiFunctionFormDistributeUser									= By.cssSelector("select[id*='Distribution_User']");
		public static By	css_BetaViewCreateFormAssociationCheckboxList						= By.cssSelector("ul[class='repeated-item'] input[class='check-row']");
		public static By	css_BetaViewCreateFormAssociationDocRefList							= By.cssSelector("div[class*='listingContainer'] div[class*='gbody'] div[class*='list-item-container'] ul li div[title] a");
		/*
		public static By	css_CreateFormAssociationCheckboxList								= By.cssSelector("div[id='adTableBody'] div[class*='rows'] div[class*='filelistchkbox'] input");
		public static By	css_CreateFormAssociationDocRefList									= By.cssSelector("div[id='adTableBody'] div[class*='rows'] div[class*='col-docRef-fixed-width'] a");
		*/

		/* Working Calendar Sub Locators */
		public static By	ele_BetaViewViewFormActionDistributionAction						= By.xpath("li[@class='gstatus ng-binding'][@title!='']");
		public static By	ele_BetaViewViewFormActionDistributionActionSatus					= By.xpath("li[@class='gstatus ng-binding'][@title='']");
	}

}